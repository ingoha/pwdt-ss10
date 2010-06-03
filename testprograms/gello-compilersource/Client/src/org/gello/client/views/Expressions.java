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

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.part.ViewPart;
import org.gello.client.Application;
import org.gello.client.actions.expressionsView.ExportExpressionAction;
import org.gello.client.actions.expressionsView.FilterExpressionsAction;
import org.gello.client.actions.expressionsView.ImportExpressionAction;
import org.gello.client.actions.expressionsView.NewExpressionAction;
import org.gello.client.actions.expressionsView.PrintExpressionAction;
import org.gello.client.actions.expressionsView.ViewExpressionAction;
import org.gello.client.model.ExpressionContentProvider;
import org.gello.client.model.ExpressionInfo;
import org.gello.client.model.ExpressionLabelProvider;
import org.gello.client.model.ExpressionViewerSorter;

/**
 * A view to display expressions in.
 *
 */
public class Expressions extends ViewPart
{
	public static final String ID = "org.gello.client.views.Expressions";
	public static final int COLUMN_TYPE = 0;
	public static final int COLUMN_DRUG_CLASS = 1;
	public static final int COLUMN_DRUG_NAME = 2;
	public static final int COLUMN_CATEGORY = 3;
	public static final int COLUMN_REPOSITORY = 4;

	private IToolBarManager toolBarManager;
	private TableViewer viewer;

	/**
	 * The constructor.
	 */
	public Expressions()
	{
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent)
	{

		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 2;
		gridLayout.marginWidth = 0;
		parent.setLayout(gridLayout);

		GridData toolbarGrid = new GridData();
		toolbarGrid.horizontalAlignment = GridData.FILL;
		toolbarGrid.grabExcessHorizontalSpace = true;
		toolbarGrid.verticalAlignment = GridData.FILL;
		toolbarGrid.horizontalIndent = 2;
		toolbarGrid.verticalIndent = 2;

		ToolBar toolBar = new ToolBar(parent, SWT.NONE);
		toolBar.setLayoutData(toolbarGrid);

		createToolbar(toolBar);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;

		// Create the table viewer to display the players
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);

		// Set the content and label providers
		viewer.setContentProvider(new ExpressionContentProvider());
		viewer.setLabelProvider(new ExpressionLabelProvider());
		viewer.setSorter(new ExpressionViewerSorter());

		// Set up the table
		final Table table = viewer.getTable();
		table.setLayoutData(gridData);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		table.addControlListener(new ControlListener()
		{
			public void controlMoved(ControlEvent e)
			{
			}

			public void controlResized(ControlEvent e)
			{
				int width = table.getSize().x - table.getBorderWidth() * 2;
				for (int i = 0, n = table.getColumnCount(); i < n; i++)
					table.getColumn(i).setWidth(width / 5);
			}
		});

		// Add the Type column
		TableColumn tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Type");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				ExpressionViewerSorter sorter = ((ExpressionViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_TYPE);
				table.setSortColumn(table.getColumn(COLUMN_TYPE));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		// Add the Drug Class column
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Drug Class");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				ExpressionViewerSorter sorter = ((ExpressionViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_DRUG_CLASS);
				table.setSortColumn(table.getColumn(COLUMN_DRUG_CLASS));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		// Add the Drug Name column
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Drug Name");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				ExpressionViewerSorter sorter = ((ExpressionViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_DRUG_NAME);
				table.setSortColumn(table.getColumn(COLUMN_DRUG_NAME));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		// Add the Category column
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Category");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				ExpressionViewerSorter sorter = ((ExpressionViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_CATEGORY);
				table.setSortColumn(table.getColumn(COLUMN_CATEGORY));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		// Add the Repository column
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Repository");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				ExpressionViewerSorter sorter = ((ExpressionViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_REPOSITORY);
				table.setSortColumn(table.getColumn(COLUMN_REPOSITORY));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		ArrayList<ExpressionInfo> expressionModel = new ArrayList<ExpressionInfo>();
		expressionModel.add(new ExpressionInfo("Type 1", "Drug Classs 1", "Drug Name 1", "Category 1", "Repository 1"));
		expressionModel.add(new ExpressionInfo("Type 2", "Drug Classs 2", "Drug Name 2", "Category 2", "Repository 2"));
		expressionModel.add(new ExpressionInfo("Type 3", "Drug Classs 3", "Drug Name 3", "Category 3", "Repository 3"));
		expressionModel.add(new ExpressionInfo("Type 4", "Drug Classs 4", "Drug Name 4", "Category 4", "Repository 4"));

		viewer.setInput(expressionModel);

		// Pack the columns
		for (int i = 0, n = table.getColumnCount(); i < n; i++)
		{
			table.getColumn(i).pack();
		}

		viewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				Application.getAction(ViewExpressionAction.ID).run();
			}
		});

		getSite().setSelectionProvider(viewer);
	}

	private void createToolbar(ToolBar toolBar)
	{
		toolBarManager = new ToolBarManager(toolBar);

		ActionContributionItem filterExpressions = new ActionContributionItem(Application.getAction(FilterExpressionsAction.ID));
		filterExpressions.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem newExpression = new ActionContributionItem(Application.getAction(NewExpressionAction.ID));
		newExpression.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem viewExpression = new ActionContributionItem(Application.getAction(ViewExpressionAction.ID));
		viewExpression.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem exportExpression = new ActionContributionItem(Application.getAction(ExportExpressionAction.ID));
		exportExpression.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem importExpression = new ActionContributionItem(Application.getAction(ImportExpressionAction.ID));
		importExpression.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem printExpression = new ActionContributionItem(Application.getAction(PrintExpressionAction.ID));
		printExpression.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		toolBarManager.add(filterExpressions);
		toolBarManager.add(newExpression);
		toolBarManager.add(viewExpression);
		toolBarManager.add(exportExpression);
		toolBarManager.add(importExpression);
		toolBarManager.add(printExpression);

		toolBarManager.update(true);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{
		viewer.getControl().setFocus();
	}
}
