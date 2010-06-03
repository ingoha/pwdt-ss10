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
import org.gello.client.views.Browser;

public class CleanTestsMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.CleanTests";
	private IStructuredSelection selection;

	public CleanTestsMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Clean Tests");
		setToolTipText("Clean Tests.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CLEAN_TESTS));
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
	 * Cleans all tests in the currently open project by calling remove
	 * on teh server and removing them from the Browser tree.
	 */
	public void run()
	{
		GelloNode gelloNode = (GelloNode) selection.getFirstElement();

		int i = 0;
		while (i < gelloNode.getChildren().size())
		{
			if (gelloNode.getChildren().get(i).isDeletable() && !gelloNode.getChildren().get(i).getName().equals("Expected Results"))
			{
				try
				{
					Application.getManager().remove(gelloNode.getChildren().get(i).getPath());
					gelloNode.getChildren().remove(i);
				}
				catch (ServerException_Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				i++;
		}

		Browser.viewer.refresh();
	}
}
