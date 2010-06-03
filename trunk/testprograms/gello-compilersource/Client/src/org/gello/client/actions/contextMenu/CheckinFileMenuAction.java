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

public class CheckinFileMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.CheckinFile";
	private IStructuredSelection selection;

	public CheckinFileMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Check In File");
		setToolTipText("Check In File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CHECK_IN));
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
						&& ((GelloNode) selection.getFirstElement()).getLockOwner() != null
						&& ((GelloNode) selection.getFirstElement()).getLockOwner().equalsIgnoreCase(Session.getInstance().getConnectionDetails().getUsername()));
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
	 * Checks in the currently selected file.
	 * 
	 */
	public void run()
	{
		GelloNode gelloNode = (GelloNode) selection.getFirstElement();
		Status status = null;
		try
		{
			status = (Status) window.getActivePage().showView("org.gello.client.views.Status", null, IWorkbenchPage.VIEW_CREATE);
			status.setText(status.getText() + "Checking in " + gelloNode.getPath() + " ... ");
		}
		catch (PartInitException e1)
		{
			e1.printStackTrace();
		}

		// Get the current fileData from the repository
		String fileData = "";
		try
		{
			fileData = Application.getManager().openFile(gelloNode.getPath());
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		// See if an editor is currently open for that input. If it is,
		// check in that data, if not, check in the data just retrieved.
		IWorkbenchPage page = window.getActivePage();
		FileEditorInput input = new FileEditorInput(gelloNode, fileData);
		FileEditor editor = (FileEditor) page.findEditor(input);
		boolean success = false;
		if (editor != null)
		{
			input = (FileEditorInput) editor.getEditorInput();
			try
			{
				success = Application.getManager().checkinFile(gelloNode.getPath(), editor.getData());
			}
			catch (ServerException_Exception e)
			{
				e.printStackTrace();
			}
			if (success)
				input.setData(editor.getData());
		}
		else
		{
			try
			{
				success = Application.getManager().checkinFile(gelloNode.getPath(), fileData);
			}
			catch (ServerException_Exception e1)
			{
				e1.printStackTrace();
			}
		}

		// If the checkin was successful, open up the editor with the
		// contents and unlock it in the model.
		if (success)
		{
			status.setText(status.getText() + "done.\r\n");
			gelloNode.setLockOwner(null);
			input.resetEditable();
			try
			{
				page.openEditor(input, FileEditor.ID);
				((FileEditor) page.findEditor(input)).refreshEditor();
			}
			catch (PartInitException e)
			{
				// handle error
			}
		}
		else
			status.setText(status.getText() + "FAILED!\r\n");

		Browser.viewer.refresh();
	}

}
