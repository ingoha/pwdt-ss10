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


package org.gello.client.composites;

import java.util.ArrayList;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.gello.client.Application;
import org.gello.client.actions.manageAccountsDialog.DeleteOrganizationAction;
import org.gello.client.actions.manageAccountsDialog.DeleteUserAction;
import org.gello.client.actions.manageAccountsDialog.EditOrganizationAction;
import org.gello.client.actions.manageAccountsDialog.EditUserAction;
import org.gello.client.actions.manageAccountsDialog.NewOrganizationAction;
import org.gello.client.actions.manageAccountsDialog.NewUserAction;
import org.gello.client.manager.Organization;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.manager.User;
import org.gello.client.model.OrganizationContentProvider;
import org.gello.client.model.OrganizationLabelProvider;
import org.gello.client.model.OrganizationViewerSorter;
import org.gello.client.model.UserContentProvider;
import org.gello.client.model.UserLabelProvider;
import org.gello.client.model.UserViewerSorter;

/**
 * Contains the user accounts and organizations tables in different tabs,
 * as well as new, edit, and delete actions for each of those.
 * 
 */ 
public class ManageAccountsComposite extends Composite
{
	private TabFolder tabFolder = null;
	private Composite usersComposite = null;
	private Composite organizationsComposite = null;
	private ToolBar toolBar = null;
	private IToolBarManager toolBarManager = null;

	public static final int COLUMN_FIRST_NAME = 0;
	public static final int COLUMN_LAST_NAME = 1;
	public static final int COLUMN_USERNAME = 2;

	public static final int COLUMN_ORGANIZATION_NAME = 0;

	public static TableViewer usersViewer;
	public static TableViewer organizationsViewer;

	public ManageAccountsComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		createToolBar();
		createTabFolder();
		tabChanged();
		setSize(new Point(300, 200));
		setLayout(new GridLayout());
	}

	/**
	 * This method initializes tabFolder
	 * 
	 */
	private void createTabFolder()
	{
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.grabExcessVerticalSpace = true;
		tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(gridData1);
		createUsersComposite();
		createOrganizationsComposite();
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Users");
		tabItem.setControl(usersComposite);
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("Organizations");
		tabItem1.setControl(organizationsComposite);

		// When a tab is selected, call the tabChanged() method
		// to update the visible toolbar items.
		tabFolder.addSelectionListener(new SelectionListener()
		{
			public void widgetDefaultSelected(SelectionEvent e)
			{
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e)
			{
				tabChanged();
			}
		});
	}

	/**
	 * This method initializes usersComposite
	 * 
	 */
	private void createUsersComposite()
	{
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.horizontalSpacing = 0;
		gridLayout1.marginWidth = 0;
		gridLayout1.verticalSpacing = 0;
		gridLayout1.marginHeight = 0;
		final GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.widthHint = 400;
		gridData2.heightHint = 200;
		gridData2.grabExcessHorizontalSpace = true;
		usersComposite = new Composite(tabFolder, SWT.NONE);
		usersComposite.setLayout(gridLayout1);

		// Create the table viewer to display the players
		usersViewer = new TableViewer(usersComposite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);

		// Set the content and label providers
		usersViewer.setContentProvider(new UserContentProvider());
		usersViewer.setLabelProvider(new UserLabelProvider());
		usersViewer.setSorter(new UserViewerSorter());

		// Set up the table
		final Table usersTable = usersViewer.getTable();
		usersTable.setLayoutData(gridData2);
		usersTable.setHeaderVisible(true);
		usersTable.setLinesVisible(true);

		usersTable.addControlListener(new ControlListener()
		{
			public void controlMoved(ControlEvent e)
			{
			}

			public void controlResized(ControlEvent e)
			{
				int width = usersTable.getSize().x - usersTable.getBorderWidth() * 2;
				for (int i = 0, n = usersTable.getColumnCount(); i < n; i++)
					usersTable.getColumn(i).setWidth(width / 3);
			}
		});

		TableColumn tc = new TableColumn(usersTable, SWT.LEFT);
		tc.setText("First Name");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				UserViewerSorter sorter = ((UserViewerSorter) usersViewer.getSorter());
				sorter.doSort(COLUMN_FIRST_NAME);
				usersTable.setSortColumn(usersTable.getColumn(COLUMN_FIRST_NAME));
				usersTable.setSortDirection(sorter.getDirection());
				usersViewer.refresh();
			}
		});

		// Add the Drug Class column
		tc = new TableColumn(usersTable, SWT.LEFT);
		tc.setText("Last Name");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				UserViewerSorter sorter = ((UserViewerSorter) usersViewer.getSorter());
				sorter.doSort(COLUMN_LAST_NAME);
				usersTable.setSortColumn(usersTable.getColumn(COLUMN_LAST_NAME));
				usersTable.setSortDirection(sorter.getDirection());
				usersViewer.refresh();
			}
		});

		// Add the Name column
		tc = new TableColumn(usersTable, SWT.LEFT);
		tc.setText("Username");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				UserViewerSorter sorter = ((UserViewerSorter) usersViewer.getSorter());
				sorter.doSort(COLUMN_USERNAME);
				usersTable.setSortColumn(usersTable.getColumn(COLUMN_USERNAME));
				usersTable.setSortDirection(sorter.getDirection());
				usersViewer.refresh();
			}
		});

		usersViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				setEnabledUserActions();
			}
		});

		// Get the user list from the server.
		ArrayList<User> userList = null;
		try
		{
			userList = (ArrayList<User>) Application.getManager().getUserList();
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		usersViewer.setInput(userList);

		// When double clicking a uesr, open it for editing.
		usersViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				Application.getAction(EditUserAction.ID).run();
			}
		});
	}

	/**
	 * This method initializes organizationsComposite
	 * 
	 */
	private void createOrganizationsComposite()
	{
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.horizontalSpacing = 0;
		gridLayout1.marginWidth = 0;
		gridLayout1.verticalSpacing = 0;
		gridLayout1.marginHeight = 0;
		final GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.widthHint = 400;
		gridData2.heightHint = 200;
		gridData2.grabExcessHorizontalSpace = true;
		organizationsComposite = new Composite(tabFolder, SWT.NONE);
		organizationsComposite.setLayout(gridLayout1);

		// Create the table viewer to display the players
		organizationsViewer = new TableViewer(organizationsComposite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);

		// Set the content and label providers
		organizationsViewer.setContentProvider(new OrganizationContentProvider());
		organizationsViewer.setLabelProvider(new OrganizationLabelProvider());
		organizationsViewer.setSorter(new OrganizationViewerSorter());

		// Set up the table
		final Table organizationsTable = organizationsViewer.getTable();
		organizationsTable.setLayoutData(gridData2);
		organizationsTable.setHeaderVisible(true);
		organizationsTable.setLinesVisible(true);

		organizationsTable.addControlListener(new ControlListener()
		{
			public void controlMoved(ControlEvent e)
			{
			}

			public void controlResized(ControlEvent e)
			{
				int width = organizationsTable.getSize().x - organizationsTable.getBorderWidth() * 2;
				for (int i = 0, n = organizationsTable.getColumnCount(); i < n; i++)
					organizationsTable.getColumn(i).setWidth(width);
			}
		});

		// Add the Organization Name column
		TableColumn tc = new TableColumn(organizationsTable, SWT.LEFT);
		tc.setText("Organization Name");
		tc.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				OrganizationViewerSorter sorter = ((OrganizationViewerSorter) organizationsViewer.getSorter());
				sorter.doSort(COLUMN_FIRST_NAME);
				organizationsTable.setSortColumn(organizationsTable.getColumn(COLUMN_FIRST_NAME));
				organizationsTable.setSortDirection(sorter.getDirection());
				organizationsViewer.refresh();
			}
		});

		organizationsViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				setEnabledOrganizationActions();
			}
		});

		// Get the organization list from the server.
		ArrayList<Organization> organizationList = null;
		try
		{
			organizationList = (ArrayList<Organization>) Application.getManager().getOrganizationList();
		}
		catch (ServerException_Exception e1)
		{
			e1.printStackTrace();
		}

		organizationsViewer.setInput(organizationList);

		// When double clicking an organization, open it for editing.
		organizationsViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				Application.getAction(EditOrganizationAction.ID).run();
			}
		});
	}

	/**
	 * This method initializes toolBar.
	 * It adds new, edit, and delete items for both users and organizations.
	 * These are different actions, so they must be set visible accordingly.
	 * 
	 */
	private void createToolBar()
	{
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		toolBar = new ToolBar(this, SWT.NONE);
		toolBar.setLayoutData(gridData);
		toolBarManager = new ToolBarManager(toolBar);

		ActionContributionItem newUser = new ActionContributionItem(Application.getAction(NewUserAction.ID));
		newUser.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		ActionContributionItem editUser = new ActionContributionItem(Application.getAction(EditUserAction.ID));
		editUser.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		ActionContributionItem deleteUser = new ActionContributionItem(Application.getAction(DeleteUserAction.ID));
		deleteUser.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem newOrganization = new ActionContributionItem(Application.getAction(NewOrganizationAction.ID));
		newOrganization.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		ActionContributionItem editOrganization = new ActionContributionItem(Application.getAction(EditOrganizationAction.ID));
		editOrganization.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		ActionContributionItem deleteOrganization = new ActionContributionItem(Application.getAction(DeleteOrganizationAction.ID));
		deleteOrganization.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		toolBarManager.add(newUser);
		toolBarManager.add(editUser);
		toolBarManager.add(deleteUser);
		toolBarManager.add(newOrganization);
		toolBarManager.add(editOrganization);
		toolBarManager.add(deleteOrganization);

		// Set "New" to enabled, the others to disabled..
		Application.getAction(NewUserAction.ID).setEnabled(true);
		Application.getAction(EditUserAction.ID).setEnabled(false);
		Application.getAction(DeleteUserAction.ID).setEnabled(false);

		Application.getAction(NewOrganizationAction.ID).setEnabled(true);
		Application.getAction(EditOrganizationAction.ID).setEnabled(false);
		Application.getAction(DeleteOrganizationAction.ID).setEnabled(false);

	}

	/**
	 * Updates which toolbar items are visible.  Items 0-2 are
	 * user actions, and items 3-5 are organization actions.
	 * 
	 */
	private void tabChanged()
	{
		if (tabFolder.getSelectionIndex() == 0)
		{
			toolBarManager.getItems()[0].setVisible(true);
			toolBarManager.getItems()[1].setVisible(true);
			toolBarManager.getItems()[2].setVisible(true);
			toolBarManager.getItems()[3].setVisible(false);
			toolBarManager.getItems()[4].setVisible(false);
			toolBarManager.getItems()[5].setVisible(false);
			toolBarManager.update(true);
		}
		else if (tabFolder.getSelectionIndex() == 1)
		{
			toolBarManager.getItems()[0].setVisible(false);
			toolBarManager.getItems()[1].setVisible(false);
			toolBarManager.getItems()[2].setVisible(false);
			toolBarManager.getItems()[3].setVisible(true);
			toolBarManager.getItems()[4].setVisible(true);
			toolBarManager.getItems()[5].setVisible(true);
			toolBarManager.update(true);
		}
	}

	/**
	 * Sets which organization actions are enabled.  If something
	 * is selected, they are all enabled.  Otherwise, only "New" is.
	 * 
	 */
	private void setEnabledOrganizationActions()
	{
		Organization selectedOrganization = getSelectedOrganization();
		if (selectedOrganization != null)
		{
			((EditOrganizationAction) Application.getAction(EditOrganizationAction.ID)).setOrganization(selectedOrganization);
			((DeleteOrganizationAction) Application.getAction(DeleteOrganizationAction.ID)).setOrganization(selectedOrganization);
			Application.getAction(NewOrganizationAction.ID).setEnabled(true);
			Application.getAction(EditOrganizationAction.ID).setEnabled(true);
			Application.getAction(DeleteOrganizationAction.ID).setEnabled(true);
		}
		else
		{
			Application.getAction(NewOrganizationAction.ID).setEnabled(true);
			Application.getAction(EditOrganizationAction.ID).setEnabled(false);
			Application.getAction(DeleteOrganizationAction.ID).setEnabled(false);
		}
	}

	/**
	 * Sets which user actions are enabled.  If something
	 * is selected, they are all enabled.  Otherwise, only "New" is.
	 * 
	 */
	private void setEnabledUserActions()
	{
		User selectedUser = getSelectedUser();
		if (selectedUser != null)
		{
			((EditUserAction) Application.getAction(EditUserAction.ID)).setUser(getSelectedUser());
			((DeleteUserAction) Application.getAction(DeleteUserAction.ID)).setUser(getSelectedUser());
			Application.getAction(NewUserAction.ID).setEnabled(true);
			Application.getAction(EditUserAction.ID).setEnabled(true);
			Application.getAction(DeleteUserAction.ID).setEnabled(true);
		}
		else
		{
			Application.getAction(NewUserAction.ID).setEnabled(true);
			Application.getAction(EditUserAction.ID).setEnabled(false);
			Application.getAction(DeleteUserAction.ID).setEnabled(false);
		}
	}

	/**
	 * Get the currently selected user.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private User getSelectedUser()
	{
		if (usersViewer.getTable().getSelection().length == 0)
			return null;
		String username = usersViewer.getTable().getSelection()[0].getText(COLUMN_USERNAME);
		ArrayList<User> userList = (ArrayList<User>) usersViewer.getInput();
		for (int i = 0; i < userList.size(); i++)
		{
			if (userList.get(i).getUserName().equals(username))
				return userList.get(i);
		}
		return null;
	}

	/**
	 * Get the currently selected organization.
	 * 
	 */
	@SuppressWarnings("unchecked")
	private Organization getSelectedOrganization()
	{
		if (organizationsViewer.getTable().getSelection().length == 0)
			return null;
		String organizationName = organizationsViewer.getTable().getSelection()[0].getText(COLUMN_ORGANIZATION_NAME);
		ArrayList<Organization> organizationList = (ArrayList<Organization>) organizationsViewer.getInput();
		for (int i = 0; i < organizationList.size(); i++)
		{
			if (organizationList.get(i).getName().equals(organizationName))
				return organizationList.get(i);
		}
		return null;
	}

}
