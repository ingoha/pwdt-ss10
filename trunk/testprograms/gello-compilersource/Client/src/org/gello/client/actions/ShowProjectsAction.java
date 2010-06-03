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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.views.Projects;

public class ShowProjectsAction extends Action implements ActionFactory.IWorkbenchAction
{
	private final IWorkbenchWindow window;

	public final static String ID = "org.gello.client.actions.ShowProjects";

	public ShowProjectsAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Projects");
		setToolTipText("Open Projects");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.OPEN_PROJECTS));
	}

	/**
	 * Shows the Projects view.
	 * 
	 */
	public void run()
	{

		try
		{
			window.getActivePage().showView(Projects.ID);
		}
		catch (PartInitException e)
		{
			e.printStackTrace();
		}
	}

	public void dispose()
	{
	}
}
