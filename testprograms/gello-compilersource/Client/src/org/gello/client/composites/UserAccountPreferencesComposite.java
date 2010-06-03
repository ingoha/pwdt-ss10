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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.gello.client.manager.User;

public class UserAccountPreferencesComposite extends Composite
{

	private Label labelPreferences = null;
	private Label labelSignIn = null;
	private Button checkBoxOpenHome = null;
	private Button checkBoxLoadProject = null;
	private Combo comboFontSize = null;
	private Combo comboFontStyle = null;
	private Label labelSample = null;
	private Label labelFont = null;

	public UserAccountPreferencesComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		GridData gridData92 = new GridData();
		gridData92.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData911 = new GridData();
		gridData911.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		gridData911.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData911.verticalSpan = 5;
		GridData gridData90 = new GridData();
		gridData90.horizontalSpan = 5;
		GridData gridData53 = new GridData();
		gridData53.horizontalSpan = 7;
		gridData53.verticalSpan = 4;
		GridData gridData43 = new GridData();
		gridData43.horizontalSpan = 7;
		GridData gridData33 = new GridData();
		gridData33.horizontalSpan = 8;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 8;
		labelPreferences = new Label(this, SWT.NONE);
		labelPreferences.setText("Preferences");
		labelPreferences.setLayoutData(gridData33);
		labelPreferences.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelSignIn = new Label(this, SWT.NONE);
		labelSignIn.setText("On Signing In :");
		labelSignIn.setLayoutData(gridData911);
		checkBoxOpenHome = new Button(this, SWT.CHECK);
		checkBoxOpenHome.setText("Open the home page");
		checkBoxOpenHome.setSelection(false);
		checkBoxOpenHome.setLayoutData(gridData43);
		checkBoxLoadProject = new Button(this, SWT.CHECK);
		checkBoxLoadProject.setText("Load the last project used");
		checkBoxLoadProject.setLayoutData(gridData53);
		this.setLayout(gridLayout);
		labelFont = new Label(this, SWT.NONE);
		labelFont.setText("Editor Font :");
		labelFont.setLayoutData(gridData92);
		createComboFontStyle();
		createComboFontSize();
		labelSample = new Label(this, SWT.NONE);
		labelSample.setText("Label");
		labelSample.setLayoutData(gridData90);
		this.setSize(new Point(315, 97));
	}

	/**
	 * This method initializes comboFontSize
	 * 
	 */
	private void createComboFontSize()
	{
		GridData gridData35 = new GridData();
		gridData35.widthHint = 35;
		comboFontSize = new Combo(this, SWT.READ_ONLY);
		comboFontSize.setLayoutData(gridData35);
		comboFontSize.add("8 pt");
		comboFontSize.add("10 pt");
		comboFontSize.add("12 pt");
		comboFontSize.select(1);
	}

	/**
	 * This method initializes comboFontStyle
	 * 
	 */
	private void createComboFontStyle()
	{
		GridData gridData34 = new GridData();
		gridData34.widthHint = 100;
		comboFontStyle = new Combo(this, SWT.READ_ONLY);
		comboFontStyle.setLayoutData(gridData34);
		comboFontStyle.add("Arial");
		comboFontStyle.add("Courier");
		comboFontStyle.add("Tahoma");
		comboFontStyle.add("Verdana");
		comboFontStyle.select(1);
	}

	/**
	 * Populate the dialog fields with a user object.
	 * @param user User object to populate from.
	 */
	public void setUser(User user)
	{
		// TODO Allow setting of user preferences from a user object.
	}

	/**
	 * Update a user object with the currently entered
	 * information in the dialog.
	 * @param user User object to update.
	 */
	public void updateUser(User user)
	{
		// TODO Allow updating user object preferences.
	}

} // @jve:decl-index=0:visual-constraint="10,10"
