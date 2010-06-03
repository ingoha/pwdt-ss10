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
import org.gello.client.views.Browser;
import org.gello.client.views.Status;

public class CheckinFileEditorAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.fileEditor.CheckinFile";

	public CheckinFileEditorAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Check In");
		setToolTipText("Check In File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CHECK_IN));
	}

	public void dispose()
	{
	}

	/**
	 * Checks in the file opened in the editor.
	 * 
	 */
	public void run()
	{
		IWorkbenchPage page = window.getActivePage();
		FileEditor editor = (FileEditor) page.getActiveEditor();
		FileEditorInput input = (FileEditorInput) editor.getEditorInput();
		GelloNode gelloNode = input.getGelloNode();

		// Set the current status.
		Status status = null;
		try
		{
			status = (Status) window.getActivePage().showView(Status.ID, null, IWorkbenchPage.VIEW_CREATE);
			status.setText(status.getText() + "Checking in " + gelloNode.getPath() + " ... ");
		}
		catch (PartInitException e1)
		{
			e1.printStackTrace();
		}

		boolean success = false;

		// Check in the file
		try
		{
			success = Application.getManager().checkinFile(gelloNode.getPath(), editor.getData());
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		// If successful, remove the lock and refresh the editor.
		if (success)
		{
			status.setText(status.getText() + "done.\r\n");
			// unlock it client side
			gelloNode.setLockOwner(null);

			// set the new data in the input, set its editable state, and
			// refresh the editor
			input.setData(editor.getData());
			input.resetEditable();
			editor.refreshEditor();
		}
		else
			status.setText(status.getText() + "FAILED!\r\n");

		Browser.viewer.refresh();
	}
}
