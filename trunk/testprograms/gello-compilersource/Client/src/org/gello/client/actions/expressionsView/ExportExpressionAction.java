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


package org.gello.client.actions.expressionsView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.model.ExpressionInfo;
import org.gello.client.views.Expressions;

public class ExportExpressionAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{
	public final static String ID = "org.gello.client.actions.projectsView.ExportExpression";
	private IStructuredSelection selection;
	private IWorkbenchWindow window;

	public ExportExpressionAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Export");
		setToolTipText("Export Expression");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.EXPORT_EXPRESSION));
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming)
	{
		if (part instanceof Expressions)
		{
			if (incoming instanceof IStructuredSelection)
			{
				selection = (IStructuredSelection) incoming;
				if (selection.getFirstElement() != null && selection.getFirstElement() instanceof ExpressionInfo)
					setEnabled(true);
				else
					setEnabled(false);
			}
		}
	}

	public void run()
	{
		// TODO Implement expression exporter.
		
		MessageBox messageBox = new MessageBox(window.getShell(), SWT.OK | SWT.ICON_INFORMATION);
		messageBox.setMessage("This feature is not yet implemented.");
		messageBox.setText("Not Implemented");
		
		messageBox.open();
	}

	public void dispose()
	{
	}

}
