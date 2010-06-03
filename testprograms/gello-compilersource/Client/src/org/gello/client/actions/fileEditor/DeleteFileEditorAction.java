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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.editors.FileEditor;
import org.gello.client.editors.FileEditorInput;
import org.gello.client.manager.GelloNode;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.BrowserViewContentProvider;
import org.gello.client.views.Browser;

public class DeleteFileEditorAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.fileEditor.DeleteFile";

	public DeleteFileEditorAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Delete File");
		setToolTipText("Delete File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.CHECK_IN));
	}

	public void dispose()
	{
	}

	/**
	 * Delete the currently open file.
	 * 
	 */
	public void run()
	{
		IWorkbenchPage page = window.getActivePage();
		FileEditor editor = (FileEditor) page.getActiveEditor();
		FileEditorInput input = (FileEditorInput) editor.getEditorInput();
		GelloNode gelloNode = input.getGelloNode();

		try
		{
			// Remove the file from the server.
			boolean success = Application.getManager().remove(gelloNode.getPath());
			
			// If successful, unselect it (if selected) in the Browser tree
			// and then remove it.
			if (success)
			{
				GelloNode selectedNode = (GelloNode) ((IStructuredSelection) Browser.viewer.getSelection()).getFirstElement();
				if (selectedNode != null && selectedNode.getPath().equals(gelloNode.getPath()))
					Browser.viewer.setSelection(null);

				page.closeEditor(editor, false);

				GelloNode parentNode = ((BrowserViewContentProvider) Browser.viewer.getContentProvider()).getParentFromPath((GelloNode) Browser.viewer.getInput(), gelloNode.getPath());
				int i = 0;
				while (!parentNode.getChildren().get(i).getPath().equals(gelloNode.getPath()))
					i++;
				parentNode.getChildren().remove(i);
			}
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		Browser.viewer.refresh();
	}
}
