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

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.dialogs.NewProjectDialog;
import org.gello.client.manager.Project;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.views.Projects;

public class NewProjectAction extends Action implements ActionFactory.IWorkbenchAction
{
	public final static String ID = "org.gello.client.actions.projectsView.NewProject";
	private final IWorkbenchWindow window;

	public NewProjectAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&New");
		setToolTipText("New Project");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.NEW_PROJECT));
	}

	/**
	 * Adds a new project to the server from the new project dialog.
	 * If successful, updates the project list.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void run()
	{
		NewProjectDialog d = new NewProjectDialog(window.getShell());
		int resultCode = d.open();
		if (resultCode == NewProjectDialog.OK)
		{
			Project updatedProject = null;
			try
			{
				updatedProject = Application.getManager().addProject(d.getProject());
			}
			catch (ServerException_Exception e)
			{
				e.printStackTrace();
			}
			if (updatedProject != null)
			{
				ArrayList<Project> projectList = (ArrayList<Project>) Projects.viewer.getInput();
				projectList.add(updatedProject);
				Projects.viewer.refresh();
			}
		}
	}

	public void dispose()
	{
	}

}
