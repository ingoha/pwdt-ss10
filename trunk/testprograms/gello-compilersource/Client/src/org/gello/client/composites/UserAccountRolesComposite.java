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
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.gello.client.manager.RoleType;
import org.gello.client.manager.User;

public class UserAccountRolesComposite extends Composite
{

	private Label labelUserRoles = null;
	private Label labelRoles = null;
	private Button checkBoxCIEAuthor = null;
	private Button checkBoxCIETest = null;
	private Button checkBoxDemonstrator = null;
	private Button checkBoxExpAuthor = null;
	private Button checkBoxExpTestAuthor = null;
	private Button checkBoxExtCert = null;
	private Button checkBoxIntApprover = null;
	private Button checkBoxProjMan = null;
	private Button checkBoxSysAdmin = null;

	public UserAccountRolesComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		GridData gridData30 = new GridData();
		gridData30.horizontalSpan = 7;
		GridData gridData29 = new GridData();
		gridData29.horizontalSpan = 7;
		GridData gridData28 = new GridData();
		gridData28.horizontalSpan = 7;
		GridData gridData27 = new GridData();
		gridData27.horizontalSpan = 7;
		GridData gridData26 = new GridData();
		gridData26.horizontalSpan = 7;
		GridData gridData25 = new GridData();
		gridData25.horizontalSpan = 7;
		GridData gridData24 = new GridData();
		gridData24.horizontalSpan = 7;
		GridData gridData231 = new GridData();
		gridData231.horizontalSpan = 7;
		GridData gridData221 = new GridData();
		gridData221.horizontalSpan = 7;
		GridData gridData211 = new GridData();
		gridData211.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		gridData211.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData211.verticalSpan = 9;
		GridData gridData20 = new GridData();
		gridData20.horizontalSpan = 8;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 8;
		labelUserRoles = new Label(this, SWT.NONE);
		labelUserRoles.setText("User Roles");
		labelUserRoles.setLayoutData(gridData20);
		labelUserRoles.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelRoles = new Label(this, SWT.NONE);
		labelRoles.setText("Current Roles :");
		labelRoles.setLayoutData(gridData211);
		checkBoxCIEAuthor = new Button(this, SWT.CHECK);
		checkBoxCIEAuthor.setText("CIE Author");
		checkBoxCIEAuthor.setLayoutData(gridData221);
		checkBoxCIETest = new Button(this, SWT.CHECK);
		checkBoxCIETest.setText("CIE Test Case Author");
		checkBoxCIETest.setLayoutData(gridData231);
		checkBoxDemonstrator = new Button(this, SWT.CHECK);
		checkBoxDemonstrator.setText("Demonstrator");
		checkBoxDemonstrator.setLayoutData(gridData24);
		checkBoxExpAuthor = new Button(this, SWT.CHECK);
		checkBoxExpAuthor.setText("Expression Author");
		checkBoxExpAuthor.setLayoutData(gridData25);
		checkBoxExpTestAuthor = new Button(this, SWT.CHECK);
		checkBoxExpTestAuthor.setText("Expression Test Case Author");
		checkBoxExpTestAuthor.setLayoutData(gridData26);
		checkBoxExtCert = new Button(this, SWT.CHECK);
		checkBoxExtCert.setText("External Certifier");
		checkBoxExtCert.setLayoutData(gridData27);
		checkBoxIntApprover = new Button(this, SWT.CHECK);
		checkBoxIntApprover.setText("Internal Approver");
		checkBoxIntApprover.setLayoutData(gridData28);
		checkBoxProjMan = new Button(this, SWT.CHECK);
		checkBoxProjMan.setText("Project Manager");
		checkBoxProjMan.setLayoutData(gridData29);
		checkBoxSysAdmin = new Button(this, SWT.CHECK);
		checkBoxSysAdmin.setText("System Administrator");
		checkBoxSysAdmin.setLayoutData(gridData30);
		checkBoxSysAdmin.setSelection(false);
		this.setLayout(gridLayout);
		this.setSize(new Point(251, 218));
	}

	/**
	 * Populate the user roles with a user object.
	 * @param user User object to populate from.
	 */
	public void setUser(User user)
	{
		for (int i = 0; i < user.getRoles().size(); i++)
		{
			RoleType roleType = user.getRoles().get(i);

			if (roleType.equals(RoleType.CIE_AUTHOR))
				checkBoxCIEAuthor.setSelection(true);
			else if (roleType.equals(RoleType.CIE_TEST_CASE_AUTHOR))
				checkBoxCIETest.setSelection(true);
			else if (roleType.equals(RoleType.DEMONSTRATOR))
				checkBoxDemonstrator.setSelection(true);
			else if (roleType.equals(RoleType.EXPRESSION_AUTHOR))
				checkBoxExpAuthor.setSelection(true);
			else if (roleType.equals(RoleType.EXPRESSION_TEST_CASE_AUTHOR))
				checkBoxExpTestAuthor.setSelection(true);
			else if (roleType.equals(RoleType.EXTERNAL_CERTIFIER))
				checkBoxExtCert.setSelection(true);
			else if (roleType.equals(RoleType.INTERNAL_APPROVER))
				checkBoxIntApprover.setSelection(true);
			else if (roleType.equals(RoleType.PROJECT_MANAGER))
				checkBoxProjMan.setSelection(true);
			else if (roleType.equals(RoleType.SYSTEM_ADMINISTRATOR))
				checkBoxSysAdmin.setSelection(true);
		}

	}

	/**
	 * Update a user object with the currently checked
	 * roles in the dialog.
	 * @param user User object to update.
	 */
	public void updateUser(User user)
	{
		List<RoleType> newRoleList = new ArrayList<RoleType>();

		if (checkBoxCIEAuthor.getSelection())
			newRoleList.add(RoleType.CIE_AUTHOR);
		if (checkBoxCIETest.getSelection())
			newRoleList.add(RoleType.CIE_TEST_CASE_AUTHOR);
		if (checkBoxDemonstrator.getSelection())
			newRoleList.add(RoleType.DEMONSTRATOR);
		if (checkBoxExpAuthor.getSelection())
			newRoleList.add(RoleType.EXPRESSION_AUTHOR);
		if (checkBoxExpTestAuthor.getSelection())
			newRoleList.add(RoleType.EXPRESSION_TEST_CASE_AUTHOR);
		if (checkBoxExtCert.getSelection())
			newRoleList.add(RoleType.EXTERNAL_CERTIFIER);
		if (checkBoxIntApprover.getSelection())
			newRoleList.add(RoleType.INTERNAL_APPROVER);
		if (checkBoxProjMan.getSelection())
			newRoleList.add(RoleType.PROJECT_MANAGER);
		if (checkBoxSysAdmin.getSelection())
			newRoleList.add(RoleType.SYSTEM_ADMINISTRATOR);

		user.getRoles().clear();

		for (int i = 0; i < newRoleList.size(); i++)
		{
			user.getRoles().add(newRoleList.get(i));
		}
	}

} // @jve:decl-index=0:visual-constraint="10,10"
