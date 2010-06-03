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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.manager.GelloNode;
import org.gello.client.model.CommonActions;
import org.gello.client.views.Browser;
import org.gello.client.views.TestOutput;

public class TestFileMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.TestFile";
	private IStructuredSelection selection;

	public TestFileMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Test File");
		setToolTipText("Test File");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.TEST));
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming)
	{
		if (part instanceof Browser)
		{
			if (incoming instanceof IStructuredSelection)
			{
				selection = (IStructuredSelection) incoming;
				setEnabled(true);
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
	 * Uses CommonActions to test the currently selected file.
	 * 
	 */
	public void run()
	{
		// Set the current selection to the test case...
		GelloNode gelloNode = (GelloNode) selection.getFirstElement();

		GelloNode testCase = null;

		if (!gelloNode.isFile())
			testCase = getTestCase(gelloNode);
		else
			testCase = gelloNode;

		String testFilePath = testCase.getPath();
		String testFileData = CommonActions.getInstance().loadFileData(testFilePath);

		String output = CommonActions.getInstance().testFile(testFilePath, testFileData);
		CommonActions.getInstance().setOutput(TestOutput.ID, output);
		CommonActions.getInstance().createTestOutputFile(testFilePath, output);
	}

	private GelloNode getTestCase(GelloNode parent)
	{
		for (int i = 0; i < parent.getChildren().size(); i++)
		{
			if (parent.getChildren().get(i).isTestable())
				return parent.getChildren().get(i);
		}
		return null;
	}

}
