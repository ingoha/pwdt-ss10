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
import org.gello.client.model.CommonActions;
import org.gello.client.views.Browser;
import org.gello.client.views.CompilerOutput;

public class CompileFileMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.CompileFile";
	private IStructuredSelection selection;

	public CompileFileMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Compile File");
		setToolTipText("Compile File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.COMPILE));
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming)
	{
		if (part instanceof Browser)
		{
			if (incoming instanceof IStructuredSelection)
			{
				selection = (IStructuredSelection) incoming;
				setEnabled(selection.size() == 1 && (selection.getFirstElement() instanceof GelloNode) && ((GelloNode) selection.getFirstElement()).isFile());
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
	 * Compiles the selected node by using CommonActions.
	 * 
	 */
	public void run()
	{
		GelloNode gelloNode = (GelloNode) selection.getFirstElement();

		String filePath = gelloNode.getPath();
		String fileData = CommonActions.getInstance().loadFileData(filePath);

		String output = CommonActions.getInstance().compileFile(filePath, fileData);
		CommonActions.getInstance().setOutput(CompilerOutput.ID, output);
	}

}
