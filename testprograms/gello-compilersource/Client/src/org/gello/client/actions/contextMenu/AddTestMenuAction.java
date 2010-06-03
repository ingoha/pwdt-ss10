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


package org.gello.client.actions.contextMenu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.manager.GelloNode;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.Session;
import org.gello.client.views.Browser;

public class AddTestMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{
	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.AddTest";
	private IStructuredSelection selection;

	public AddTestMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Add Test");
		setToolTipText("Add Test.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.ADD_TEST));
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming)
	{
		if (part instanceof Browser)
		{
			if (incoming instanceof IStructuredSelection)
			{
				selection = (IStructuredSelection) incoming;
				setEnabled(true);
			}
			else
				setEnabled(false);
		}
	}

	public void dispose()
	{
		window.getSelectionService().removeSelectionListener(this);
	}

	/**
	 * Adds a structure to the svn repository, and if successful, 
	 * also adds it to the browser tree.
	 *
	 */
	public void run()
	{
		GelloNode gelloNode = (GelloNode) selection.getFirstElement();

		try
		{
			GelloNode structure = Application.getManager().addStructure(Session.getInstance().getCurrentProject().getTemplateType(), "Test", gelloNode.getPath(), null);
			if (structure != null)
				gelloNode.getChildren().add(structure);
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}
		Browser.viewer.refresh();
	}
}
