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

public class SaveFileEditorAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.fileEditor.SaveFile";

	public SaveFileEditorAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Save");
		setToolTipText("Save File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.SAVE));
	}

	public void dispose()
	{
	}

	/**
	 * Calls runOnEditor, because it takes a parameter so that FileEditor 
	 * may also call it.
	 */
	public void run()
	{
		FileEditor editor = (FileEditor) window.getActivePage().getActiveEditor();
		runOnEditor(editor);
	}

	/**
	 * Saves the currently open file in the passed in editor, 
	 * but does not unlock it.
	 * 
	 */
	public void runOnEditor(FileEditor editor)
	{
		FileEditorInput input = (FileEditorInput) editor.getEditorInput();
		GelloNode gelloNode = input.getGelloNode();

		Status status = null;
		try
		{
			status = (Status) window.getActivePage().showView(Status.ID, null, IWorkbenchPage.VIEW_CREATE);
			status.setText(status.getText() + "Saving " + gelloNode.getPath() + " ... ");
		}
		catch (PartInitException e1)
		{
			e1.printStackTrace();
		}

		boolean success = false;

		// commit the file, update the input, and refresh the editor
		try
		{
			success = Application.getManager().saveFile(gelloNode.getPath(), editor.getData());
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}
		if (success)
		{
			status.setText(status.getText() + "done.\r\n");
			input.setData(editor.getData());
			input.resetEditable();
			editor.refreshEditor();
		}
		else
			status.setText(status.getText() + "FAILED!\r\n");

		Browser.viewer.refresh();
	}
}
