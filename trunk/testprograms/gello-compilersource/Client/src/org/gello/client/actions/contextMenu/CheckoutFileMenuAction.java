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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.editors.FileEditor;
import org.gello.client.editors.FileEditorInput;
import org.gello.client.manager.GelloNode;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.Session;
import org.gello.client.views.Browser;
import org.gello.client.views.Status;

public class CheckoutFileMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.CheckoutFile";
	private IStructuredSelection selection;

	public CheckoutFileMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Check Out File");
		setToolTipText("Check Out File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CHECK_OUT));
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming)
	{
		if (part instanceof Browser)
		{
			if (incoming instanceof IStructuredSelection)
			{
				selection = (IStructuredSelection) incoming;
				setEnabled(selection.size() == 1 && (selection.getFirstElement() instanceof GelloNode) && ((GelloNode) selection.getFirstElement()).isFile()
						&& ((GelloNode) selection.getFirstElement()).getLockOwner() == null);
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
	 * Checks out the currently selected file.
	 * 
	 */
	public void run()
	{
		// Get current selection
		GelloNode gelloNode = (GelloNode) selection.getFirstElement();
		Session session = Session.getInstance();

		// Set the current status
		Status status = null;
		try
		{
			status = (Status) window.getActivePage().showView(Status.ID, null, IWorkbenchPage.VIEW_CREATE);
			status.setText(status.getText() + "Checking out " + gelloNode.getPath() + " ... ");
		}
		catch (PartInitException e1)
		{
			e1.printStackTrace();
		}

		// Check out the file
		String fileData = "";
		try
		{
			fileData = Application.getManager().checkoutFile(gelloNode.getPath()).replaceAll("\n", "\r\n");
		}
		catch (ServerException_Exception e1)
		{
			e1.printStackTrace();
		}

		// If it worked, lock it in the UI and open it, otherwise, 
		// say it didn't work in the status.
		if (fileData != null)
		{
			status.setText(status.getText() + "done.\r\n");
			gelloNode.setLockOwner(session.getConnectionDetails().getUsername());
			IWorkbenchPage page = window.getActivePage();
			FileEditorInput input = new FileEditorInput(gelloNode, fileData);
			IEditorPart editor = page.findEditor(input);
			if (editor != null)
				input = (FileEditorInput) editor.getEditorInput();

			input.resetEditable();

			try
			{
				page.openEditor(input, FileEditor.ID);
				((FileEditor) page.findEditor(input)).refreshEditor();
			}
			catch (PartInitException e)
			{
				e.printStackTrace();
			}
		}
		else
			status.setText(status.getText() + "FAILED!\r\n");

		Browser.viewer.refresh();
	}

}
