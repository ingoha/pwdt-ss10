/*******************************************************************************
 * Copyright (c) 2006, 2007 Pfizer, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jacob Brauer (WebReach, Inc.) - initial implementation
 *******************************************************************************/


package org.gello.client;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.gello.client.dialogs.LoginDialog;
import org.gello.client.manager.Manager;
import org.gello.client.manager.ManagerService;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.ConnectionDetails;
import org.gello.client.model.Session;
import org.gello.client.preferences.GeneralPreferencePage;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IPlatformRunnable
{
	public static final String PLUGIN_ID = "org.gello.client";
	private static HashMap<String, IWorkbenchAction> actions;
	private static Manager manager = null;

	public Application()
	{
		actions = new HashMap<String, IWorkbenchAction>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IPlatformRunnable#run(java.lang.Object)
	 */
	public Object run(Object args) throws Exception
	{
		Display display = PlatformUI.createDisplay();
		try
		{
			final Session session = Session.getInstance();
			Platform.endSplash();
			if (!login(session))
				return IPlatformRunnable.EXIT_OK;

			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
			{
				return IPlatformRunnable.EXIT_RESTART;
			}
			return IPlatformRunnable.EXIT_OK;
		}
		finally
		{
			display.dispose();
		}
	}

	private boolean login(final Session session)
	{
		boolean firstTry = true;
		LoginDialog loginDialog = new LoginDialog(null);
		while (!session.isAuthenticated())
		{
			IPreferencesService service = Platform.getPreferencesService();
			boolean auto_login = service.getBoolean(Application.PLUGIN_ID, GeneralPreferencePage.AUTO_LOGIN, false, null);
			ConnectionDetails details = loginDialog.getConnectionDetails();
			if (!auto_login || details == null || !firstTry)
			{
				if (loginDialog.open() != Window.OK)
					return false;
				details = loginDialog.getConnectionDetails();
			}
			firstTry = false;
			session.setConnectionDetails(details);

			ProgressMonitorDialog progress = new ProgressMonitorDialog(null);
			progress.setCancelable(true);

			/**
			 * Establishes the connection to the server and logs in. The
			 * connection details must have already been set.
			 */
			try
			{
				progress.run(true, true, new IRunnableWithProgress()
				{
					public void run(IProgressMonitor monitor) throws InvocationTargetException
					{
						ConnectionDetails details = session.getConnectionDetails();

						monitor.beginTask("Connecting with given credentials...", IProgressMonitor.UNKNOWN);
						monitor.subTask("Contacting " + details.getServer() + "...");

						try
						{
							session.setAuthenticated(Application.getManager().login(details.getUsername(), details.getPassword()));
						}
						catch (ServerException_Exception e)
						{
							e.printStackTrace();
						}

						monitor.done();

					}
				});
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
			catch (InterruptedException e)
			{
				// do nothing
			}
		}
		return true;
	}

	/**
	 * Add an action to the static action HashMap.
	 * @param action Action to be added.
	 */
	public static void addAction(IWorkbenchAction action)
	{
		actions.put(action.getId(), action);
	}

	/**
	 * Get an action from the static action HashMap.
	 * @param id ID of the action to get.
	 * @return Action.
	 */
	public static Action getAction(String id)
	{
		return (Action) actions.get(id);
	}

	/**
	 * Get the current web service manager that talks to the server.  If one has
	 * not been created then it is created now.
	 * @return Manager to talk to the server.
	 */
	public static Manager getManager()
	{
		if (manager == null)
		{
			URL url = null;
			try
			{
				url = new URL(Session.getInstance().getConnectionDetails().getServer());
			}
			catch (MalformedURLException e)
			{
				e.printStackTrace();
			}
			QName serviceName = new QName("http://manager.server.gello.org/", "ManagerService");
			manager = new ManagerService(url, serviceName).getManagerPort();
			((BindingProvider) manager).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
		}
		return manager;
	}

}
