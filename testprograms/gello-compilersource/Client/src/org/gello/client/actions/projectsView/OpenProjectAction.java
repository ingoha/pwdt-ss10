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


package org.gello.client.actions.projectsView;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.manager.GelloNode;
import org.gello.client.manager.Project;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.Session;
import org.gello.client.threads.OpenProjectThread;
import org.gello.client.views.Browser;
import org.gello.client.views.Projects;

public class OpenProjectAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{
	public final static String ID = "org.gello.client.actions.projectsView.OpenProject";
	private IStructuredSelection selection;

	public OpenProjectAction(IWorkbenchWindow window)
	{
		setId(ID);
		setText("&Open");
		setToolTipText("Open Project");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.OPEN_PROJECT));
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming)
	{
		if (part instanceof Projects)
		{
			if (incoming instanceof IStructuredSelection)
			{
				selection = (IStructuredSelection) incoming;
				if (selection.getFirstElement() != null && selection.getFirstElement() instanceof Project)
					setEnabled(true);
				else
					setEnabled(false);
			}
		}
	}

	/**
	 * Opens the selected project by calling getProject on the server
	 * and then using the OpenProjectThread to update the UI.
	 * 
	 */
	public void run()
	{
		Project selectedProject = ((Project) selection.getFirstElement());

		// Nothing is selected, nothing to do.
		if (selectedProject == null)
			return;

		final String projectName = selectedProject.getName();

		Browser.progressMonitor.beginTask("Opening Project...", IProgressMonitor.UNKNOWN);

		Thread t = new Thread(new Runnable()
		{
			public void run()
			{
				Display display = Display.getDefault();

				Project project = null;
				try
				{
					project = Application.getManager().getProject(projectName, false);
				}
				catch (ServerException_Exception e)
				{
					e.printStackTrace();
				}

				GelloNode gelloNode = project.getContents();
				Session.getInstance().setCurrentProject(project);

				if (!(display == null || display.isDisposed()))
				{
					display.asyncExec(new OpenProjectThread(gelloNode));
				}
			}
		});
		t.start();
	}

	public void dispose()
	{
	}
}
