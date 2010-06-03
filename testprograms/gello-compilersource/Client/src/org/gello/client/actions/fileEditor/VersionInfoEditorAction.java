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
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.dialogs.VersionInfoDialog;
import org.gello.client.editors.FileEditor;
import org.gello.client.editors.FileEditorInput;
import org.gello.client.manager.GelloNode;
import org.gello.client.manager.ServerException_Exception;

public class VersionInfoEditorAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.fileEditor.VersionInfo";

	public VersionInfoEditorAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Version Info");
		setToolTipText("Version Info.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.VERSION_INFO));
	}

	public void dispose()
	{
	}

	/**
	 * Get the version info for an open file and display it in a dialog.
	 * 
	 */
	public void run()
	{
		IWorkbenchPage page = window.getActivePage();
		FileEditor editor = (FileEditor) page.getActiveEditor();
		FileEditorInput input = (FileEditorInput) editor.getEditorInput();
		GelloNode gelloNode = input.getGelloNode();

		String versionInfo = "";
		try
		{
			versionInfo = Application.getManager().getVersionInfo(gelloNode.getPath());
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		VersionInfoDialog d = new VersionInfoDialog(window.getShell(), versionInfo);

		d.open();

	}

}
