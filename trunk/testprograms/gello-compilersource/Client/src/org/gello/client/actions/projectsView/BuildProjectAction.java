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

import java.util.ArrayList;

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
import org.gello.client.model.CommonActions;
import org.gello.client.threads.BuildProjectThread;
import org.gello.client.views.Browser;
import org.gello.client.views.Projects;

public class BuildProjectAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{
	public final static String ID = "org.gello.client.actions.projectsView.BuildProject";
	private IStructuredSelection selection;

	public BuildProjectAction(IWorkbenchWindow window)
	{
		setId(ID);
		setText("&Build");
		setToolTipText("Build Project");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.BUILD_PROJECT));
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
	 * Builds the currently selected project by getting it from the
	 * server and then using CommonActions to compile all Gello Expressions
	 * in it.  BuildProjectThread is then used to udpate the UI.
	 * 
	 */
	public void run()
	{
		Project project = (Project) selection.getFirstElement();

		if (project == null)
			return;

		final String projectName = project.getName();

		Browser.progressMonitor.beginTask("Building Project...", IProgressMonitor.UNKNOWN);

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

				ArrayList<String> output = new ArrayList<String>();
				CommonActions.getInstance().compileAll(gelloNode, output);

				if (!(display == null || display.isDisposed()))
				{
					display.asyncExec(new BuildProjectThread(gelloNode, output));
				}
			}
		});
		t.start();
	}

	public void dispose()
	{
	}
}
