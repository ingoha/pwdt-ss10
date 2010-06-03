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

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;

public class FilterProjectsAction extends Action implements ActionFactory.IWorkbenchAction
{
	public final static String ID = "org.gello.client.actions.projectsView.FilterProjects";
	private IWorkbenchWindow window;
	
	public FilterProjectsAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Filter");
		setToolTipText("Filter Projects");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.FILTER_PROJECTS));
	}

	public void run()
	{
		// TODO Implement Project Filtering.
		
		MessageBox messageBox = new MessageBox(window.getShell(), SWT.OK | SWT.ICON_INFORMATION);
		messageBox.setMessage("This feature is not yet implemented.");
		messageBox.setText("Not Implemented");
		
		messageBox.open();
	}

	public void dispose()
	{
	}

}
