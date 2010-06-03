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
import org.gello.client.actions.projectsView.BuildProjectAction;
import org.gello.client.actions.projectsView.DeleteProjectAction;
import org.gello.client.actions.projectsView.ExportProjectAction;
import org.gello.client.actions.projectsView.FilterProjectsAction;
import org.gello.client.actions.projectsView.ForwardProjectAction;
import org.gello.client.actions.projectsView.ImportProjectAction;
import org.gello.client.actions.projectsView.NewProjectAction;
import org.gello.client.actions.projectsView.OpenProjectAction;
import org.gello.client.actions.projectsView.TestProjectAction;
import org.gello.client.manager.Project;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.ProjectContentProvider;
import org.gello.client.model.ProjectLabelProvider;
import org.gello.client.model.ProjectViewerSorter;

/**
 * A view to display projects in.
 *
 */
public class Projects extends ViewPart
{
	public static final String ID = "org.gello.client.views.Projects";
	public static final int COLUMN_TYPE = 0;
	public static final int COLUMN_DRUG_CLASS = 1;
	public static final int COLUMN_NAME = 2;
	public static final int COLUMN_STATUS = 3;
	public static final int COLUMN_REPOSITORY = 4;

	private IToolBarManager toolBarManager;
	public static TableViewer viewer;

	/**
	 * The constructor.
	 */
	public Projects()
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
		viewer.setContentProvider(new ProjectContentProvider());
		viewer.setLabelProvider(new ProjectLabelProvider());
		viewer.setSorter(new ProjectViewerSorter());

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
				ProjectViewerSorter sorter = ((ProjectViewerSorter) viewer.getSorter());
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
				ProjectViewerSorter sorter = ((ProjectViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_DRUG_CLASS);
				table.setSortColumn(table.getColumn(COLUMN_DRUG_CLASS));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		// Add the Name column
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Name");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				ProjectViewerSorter sorter = ((ProjectViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_NAME);
				table.setSortColumn(table.getColumn(COLUMN_NAME));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		// Add the Status column
		tc = new TableColumn(table, SWT.LEFT);
		tc.setText("Status");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				ProjectViewerSorter sorter = ((ProjectViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_STATUS);
				table.setSortColumn(table.getColumn(COLUMN_STATUS));
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
				ProjectViewerSorter sorter = ((ProjectViewerSorter) viewer.getSorter());
				sorter.doSort(COLUMN_REPOSITORY);
				table.setSortColumn(table.getColumn(COLUMN_REPOSITORY));
				table.setSortDirection(sorter.getDirection());
				viewer.refresh();
			}
		});

		List<Project> projectModel = new ArrayList<Project>();

		try
		{
			projectModel = Application.getManager().getProjectList();
		}
		catch (ServerException_Exception e1)
		{
			e1.printStackTrace();
		}

		viewer.setInput(projectModel);

		// Pack the columns
		for (int i = 0, n = table.getColumnCount(); i < n; i++)
		{
			table.getColumn(i).pack();
		}

		viewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				Application.getAction(OpenProjectAction.ID).run();
			}
		});

		getSite().setSelectionProvider(viewer);
	}

	private void createToolbar(ToolBar toolBar)
	{
		toolBarManager = new ToolBarManager(toolBar);

		ActionContributionItem filterProjects = new ActionContributionItem(Application.getAction(FilterProjectsAction.ID));
		filterProjects.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem newProject = new ActionContributionItem(Application.getAction(NewProjectAction.ID));
		newProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem openProject = new ActionContributionItem(Application.getAction(OpenProjectAction.ID));
		openProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem buildProject = new ActionContributionItem(Application.getAction(BuildProjectAction.ID));
		buildProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem testProject = new ActionContributionItem(Application.getAction(TestProjectAction.ID));
		testProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem exportProject = new ActionContributionItem(Application.getAction(ExportProjectAction.ID));
		exportProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem importProject = new ActionContributionItem(Application.getAction(ImportProjectAction.ID));
		importProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem forwardProject = new ActionContributionItem(Application.getAction(ForwardProjectAction.ID));
		forwardProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem deleteProject = new ActionContributionItem(Application.getAction(DeleteProjectAction.ID));
		deleteProject.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		toolBarManager.add(filterProjects);
		toolBarManager.add(newProject);
		toolBarManager.add(openProject);
		toolBarManager.add(buildProject);
		toolBarManager.add(testProject);
		toolBarManager.add(exportProject);
		toolBarManager.add(importProject);
		toolBarManager.add(forwardProject);
		toolBarManager.add(deleteProject);

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
