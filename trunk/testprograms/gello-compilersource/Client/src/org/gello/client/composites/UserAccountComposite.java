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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.gello.client.manager.User;

/**
 * The composite to edit a user's account.
 * 
 */
public class UserAccountComposite extends Composite
{
	private TabFolder tabFolder = null;
	private UserAccountInformationComposite informationTab = null;
	private UserAccountRolesComposite rolesTab = null;
	private UserAccountPreferencesComposite preferencesTab = null;

	public UserAccountComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		createTabFolder();
		setSize(new Point(447, 542));
		setLayout(new FillLayout());
	}

	/**
	 * This method initializes tabFolder
	 * 
	 */
	private void createTabFolder()
	{
		tabFolder = new TabFolder(this, SWT.NONE);
		createInformationTab();
		createRolesTab();
		createPreferencesTab();
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Information");
		tabItem.setControl(informationTab);

		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("Roles");
		tabItem1.setControl(rolesTab);
		TabItem tabItem11 = new TabItem(tabFolder, SWT.NONE);
		tabItem11.setText("Preferences");
		tabItem11.setControl(preferencesTab);
	}

	/**
	 * This method initializes informationTab
	 * 
	 */
	private void createInformationTab()
	{
		informationTab = new UserAccountInformationComposite(tabFolder, SWT.NONE);
	}

	/**
	 * This method initializes rolesTab
	 * 
	 */
	private void createRolesTab()
	{
		rolesTab = new UserAccountRolesComposite(tabFolder, SWT.NONE);
	}

	/**
	 * This method initializes preferencesTab
	 * 
	 */
	private void createPreferencesTab()
	{
		preferencesTab = new UserAccountPreferencesComposite(tabFolder, SWT.NONE);
	}

	/**
	 * Populate the dialog fields with a user object.
	 * @param user User object to populate from.
	 */
	public void setUser(User user)
	{
		informationTab.setUser(user);
		rolesTab.setUser(user);
		preferencesTab.setUser(user);
	}

	/**
	 * Update a user object with the currently entered
	 * information in the dialog.
	 * @param user User object to update.
	 */
	public void updateUser(User user)
	{
		informationTab.updateUser(user);
		rolesTab.updateUser(user);
		preferencesTab.updateUser(user);
	}

	/**
	 * Check to see that the passwords match (if entered).
	 * @return False if new user and they are not entered, or if they do not match.  True otherwise.
	 */
	public boolean checkPassword()
	{
		return informationTab.checkPassword();
	}

	/**
	 * Set whether the username is editable (for new user).
	 * @param editable
	 */
	public void setEditableUsername(boolean editable)
	{
		informationTab.setEditableUsername(editable);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
