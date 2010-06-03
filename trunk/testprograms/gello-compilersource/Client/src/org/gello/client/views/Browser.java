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


package org.gello.client.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.gello.client.Application;
import org.gello.client.actions.contextMenu.OpenFileMenuAction;
import org.gello.client.manager.GelloNode;
import org.gello.client.model.BrowserViewContentProvider;
import org.gello.client.model.BrowserViewLabelProvider;

/**
 * The main SVN browser tree view.
 *
 */
public class Browser extends ViewPart
{
	public static final String ID = "org.gello.client.views.Browser";
	public static TreeViewer viewer;
	public static Label projectName;
	public static IProgressMonitor progressMonitor;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent)
	{

		progressMonitor = getViewSite().getActionBars().getStatusLineManager().getProgressMonitor();

		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);

		GridData projectNameGrid = new GridData();
		projectNameGrid.grabExcessHorizontalSpace = true;
		projectNameGrid.horizontalAlignment = SWT.FILL;
		projectNameGrid.verticalIndent = 4;
		projectNameGrid.horizontalIndent = 5;

		GridData treeGrid = new GridData();
		treeGrid.grabExcessHorizontalSpace = true;
		treeGrid.grabExcessVerticalSpace = true;
		treeGrid.horizontalAlignment = SWT.FILL;
		treeGrid.verticalAlignment = SWT.FILL;

		projectName = new Label(parent, 0);
		projectName.setLayoutData(projectNameGrid);
		projectName.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD));
		projectName.setText("Open Project...");

		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.getTree().setLayoutData(treeGrid);

		// Whenever a fiel is double clicked, run the OpenFileMenuAction.
		viewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				Application.getAction(OpenFileMenuAction.ID).run();
			}
		});

		getSite().setSelectionProvider(viewer);
		viewer.setContentProvider(new BrowserViewContentProvider());
		viewer.setLabelProvider(new BrowserViewLabelProvider());

		// Whenever a selection is changed, re-create the right click actions.
		getSite().getSelectionProvider().addSelectionChangedListener(new ISelectionChangedListener()
		{

			public void selectionChanged(SelectionChangedEvent event)
			{

				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				GelloNode gelloNode = (GelloNode) selection.getFirstElement();

				List<String> actions;

				if (gelloNode == null)
					actions = new ArrayList<String>();
				else
					actions = gelloNode.getMenuActions();

				MenuManager mgr = new MenuManager();
				for (int i = 0; i < actions.size(); i++)
					mgr.add(Application.getAction(actions.get(i)));

				viewer.getControl().setMenu(mgr.createContextMenu(viewer.getControl()));

			}
		});

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{
		viewer.getControl().setFocus();
	}
}
