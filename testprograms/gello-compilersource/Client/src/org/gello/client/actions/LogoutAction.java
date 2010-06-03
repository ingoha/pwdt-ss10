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


package org.gello.client.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.manager.ServerException_Exception;

public class LogoutAction extends Action implements ActionFactory.IWorkbenchAction
{
	public final static String ID = "org.gello.client.actions.Logout";

	public LogoutAction(IWorkbenchWindow window)
	{
		setId(ID);
		setText("&Logout");
		setToolTipText("Logout from Gello");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.LOGOUT));
	}

	/**
	 * Logs a user out on the server, and then restarts the workbench.
	 * 
	 */
	public void run()
	{
		try
		{
			Application.getManager().logout();
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}
		PlatformUI.getWorkbench().restart();
		
		// TODO If auto login is checked, do not let it automatically log back in if a user just logged out.
	}

	public void dispose()
	{
	}

}
