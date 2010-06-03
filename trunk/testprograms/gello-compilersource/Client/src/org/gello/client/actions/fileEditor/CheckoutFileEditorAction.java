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


package org.gello.client.actions.fileEditor;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
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

public class CheckoutFileEditorAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.fileEditor.CheckoutFile";

	public CheckoutFileEditorAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Check Out");
		setToolTipText("Check Out File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CHECK_OUT));
	}

	public void dispose()
	{
	}

	/**
	 * Checks out the file opened in the editor.
	 * 
	 */
	public void run()
	{
		IWorkbenchPage page = window.getActivePage();
		FileEditor editor = (FileEditor) page.getActiveEditor();
		FileEditorInput input = (FileEditorInput) editor.getEditorInput();
		GelloNode gelloNode = input.getGelloNode();
		Session session = Session.getInstance();

		// Set the status.
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

		String fileData = "";
		
		// Check out the file.
		try
		{
			fileData = Application.getManager().checkoutFile(gelloNode.getPath()).replaceAll("\n", "\r\n");
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		// If it worked, do required actions.
		if (fileData != null)
		{
			status.setText(status.getText() + "done.\r\n");
			// lock the file client side
			gelloNode.setLockOwner(session.getConnectionDetails().getUsername());

			// set the new data in the input, set its editable state, and
			// refresh the editor
			input.setData(fileData);
			input.resetEditable();
			editor.refreshEditor();
		}
		else
			status.setText(status.getText() + "FAILED!\r\n");

		Browser.viewer.refresh();
	}

}
