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
import org.gello.client.model.CommonActions;
import org.gello.client.threads.BuildProjectThread;
import org.gello.client.views.Browser;

public class BuildProjectMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.BuildProject";
	private IStructuredSelection selection;

	public BuildProjectMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Build Project");
		setToolTipText("Build Project.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.BUILD_PROJECT));
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
	 * Builds the currently open project by using CommonActions for
	 * building and the BuildProjectThread for updating the UI.
	 * 
	 */
	public void run()
	{
		final GelloNode gelloNode = (GelloNode) Browser.viewer.getInput();

		Browser.progressMonitor.beginTask("Building Project...", IProgressMonitor.UNKNOWN);

		Thread t = new Thread(new Runnable()
		{
			public void run()
			{
				Display display = Display.getDefault();

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

}
