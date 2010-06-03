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
import org.gello.client.threads.ExportProjectThread;
import org.gello.client.views.Browser;

public class ExportProjectMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.ExportProject";
	private IStructuredSelection selection;

	public ExportProjectMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Export Project");
		setToolTipText("Export Project.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.EXPORT_PROJECT));
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
	 * Exports the currently open project by using the ExportProjectThread
	 * to update the UI after the project is retrieved from the server.
	 *  
	 */
	public void run()
	{
		final String projectName = Session.getInstance().getCurrentProject().getName();

		Browser.progressMonitor.beginTask("Exporting Project...", IProgressMonitor.UNKNOWN);

		Thread t = new Thread(new Runnable()
		{
			public void run()
			{
				Display display = Display.getDefault();

				Project project = null;
				try
				{
					project = Application.getManager().getProject(projectName, true);
				}
				catch (ServerException_Exception e)
				{
					e.printStackTrace();
				}

				if (!(display == null || display.isDisposed()))
				{
					display.asyncExec(new ExportProjectThread(project));
				}
			}
		});
		t.start();
	}
}
