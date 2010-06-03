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
import org.gello.client.editors.FileEditor;
import org.gello.client.editors.FileEditorInput;
import org.gello.client.manager.GelloNode;
import org.gello.client.model.CommonActions;
import org.gello.client.views.TestOutput;

public class TestFileEditorAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.fileEditor.TestFile";

	public TestFileEditorAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Test");
		setToolTipText("Test File");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.TEST));
	}

	public void dispose()
	{
	}

	/**
	 * Test the currently open test case by using CommonActions.
	 * 
	 */
	public void run()
	{
		IWorkbenchPage page = window.getActivePage();
		FileEditor editor = (FileEditor) page.getActiveEditor();
		FileEditorInput input = (FileEditorInput) editor.getEditorInput();
		GelloNode gelloNode = input.getGelloNode();

		String testFilePath = gelloNode.getPath();
		String testFileData = editor.getData();

		String output = CommonActions.getInstance().testFile(testFilePath, testFileData);
		CommonActions.getInstance().setOutput(TestOutput.ID, output);
		CommonActions.getInstance().createTestOutputFile(testFilePath, output);
	}
}
