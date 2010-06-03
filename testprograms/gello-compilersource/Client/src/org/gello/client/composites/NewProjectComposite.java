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

import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.gello.client.manager.Project;
import org.gello.client.model.Session;

public class NewProjectComposite extends Composite
{

	private Label labelGeneral = null;
	private Label labelTemplateType = null;
	private Combo comboTemplateType = null;
	private Label labelDrugClass = null;
	private Combo comboDrugClass = null;
	private Label labelProjectName = null;
	private Text textProjectName = null;
	private Label labelDrugName = null;
	private Text textDrugName = null;
	private Label labelSPLReference = null;
	private Text textSPLReference = null;
	private Label labelID = null;
	private Text textID = null;
	private Label labelGeneralSeparator = null;
	private Label labelProjDescription = null;
	private Text textProjDescription = null;

	private String[] templateNames = { "Drug SPL", "Clinical Trials", "Clinical Guidelines", "Active Surveilance", "Snomed" };

	private String[] classNames = { "Opiods" };

	public NewProjectComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		GridData gridData15 = new GridData();
		gridData15.horizontalSpan = 2;
		gridData15.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData15.grabExcessHorizontalSpace = true;
		gridData15.widthHint = 350;
		gridData15.heightHint = 80;
		GridData gridData14 = new GridData();
		gridData14.horizontalSpan = 2;
		GridData gridData13 = new GridData();
		gridData13.horizontalSpan = 2;
		gridData13.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData12 = new GridData();
		gridData12.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData12.grabExcessHorizontalSpace = true;
		GridData gridData11 = new GridData();
		gridData11.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData10 = new GridData();
		gridData10.grabExcessHorizontalSpace = true;
		gridData10.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData9 = new GridData();
		gridData9.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData8 = new GridData();
		gridData8.grabExcessHorizontalSpace = true;
		gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData7 = new GridData();
		gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData6 = new GridData();
		gridData6.grabExcessHorizontalSpace = true;
		gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData5 = new GridData();
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		labelGeneral = new Label(this, SWT.NONE);
		labelGeneral.setText("General");
		labelGeneral.setLayoutData(gridData);
		labelGeneral.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelTemplateType = new Label(this, SWT.NONE);
		labelTemplateType.setText("Template Type :");
		labelTemplateType.setLayoutData(gridData2);
		this.setLayout(gridLayout);
		createComboTemplateType();
		labelDrugClass = new Label(this, SWT.NONE);
		labelDrugClass.setText("Drug Class :");
		labelDrugClass.setLayoutData(gridData1);
		createComboDrugClass();
		labelProjectName = new Label(this, SWT.NONE);
		labelProjectName.setText("Project Name :");
		labelProjectName.setLayoutData(gridData5);
		textProjectName = new Text(this, SWT.BORDER);
		textProjectName.setText("");
		textProjectName.setLayoutData(gridData6);
		labelDrugName = new Label(this, SWT.NONE);
		labelDrugName.setText("Drug Name :");
		labelDrugName.setLayoutData(gridData7);
		textDrugName = new Text(this, SWT.BORDER);
		textDrugName.setText("");
		textDrugName.setLayoutData(gridData8);
		labelSPLReference = new Label(this, SWT.NONE);
		labelSPLReference.setText("Drug SPL Reference :");
		labelSPLReference.setLayoutData(gridData9);
		textSPLReference = new Text(this, SWT.BORDER);
		textSPLReference.setText("");
		textSPLReference.setLayoutData(gridData10);
		labelID = new Label(this, SWT.NONE);
		labelID.setText("ID :");
		labelID.setLayoutData(gridData11);
		textID = new Text(this, SWT.BORDER);
		textID.setEnabled(false);
		textID.setText(UUID.randomUUID().toString());
		textID.setLayoutData(gridData12);
		labelGeneralSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelGeneralSeparator.setText("Label");
		labelGeneralSeparator.setLayoutData(gridData13);
		labelProjDescription = new Label(this, SWT.NONE);
		labelProjDescription.setText("Project Description");
		labelProjDescription.setLayoutData(gridData14);
		labelProjDescription.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		textProjDescription = new Text(this, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		textProjDescription.setText("");
		textProjDescription.setLayoutData(gridData15);
		setSize(new Point(386, 311));
	}

	/**
	 * This method initializes comboTemplateType
	 * 
	 */
	private void createComboTemplateType()
	{
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		comboTemplateType = new Combo(this, SWT.READ_ONLY);
		comboTemplateType.setLayoutData(gridData3);
		for (String name : this.templateNames)
		{
			comboTemplateType.add(name);
		}
		comboTemplateType.select(0);
	}

	/**
	 * This method initializes comboDrugClass
	 * 
	 */
	private void createComboDrugClass()
	{
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		comboDrugClass = new Combo(this, SWT.READ_ONLY);
		comboDrugClass.setLayoutData(gridData4);
		for (String name : this.classNames)
		{
			comboDrugClass.add(name);
		}
		comboDrugClass.select(0);
	}

	/**
	 * Update a project object with the currently entered
	 * information in the dialog.
	 * @param project Project to update.
	 */
	public void updateProject(Project project)
	{
		project.setTemplateType(comboTemplateType.getItem(comboTemplateType.getSelectionIndex()));
		project.setDrugClass(comboDrugClass.getItem(comboDrugClass.getSelectionIndex()));
		project.setName(textProjectName.getText());
		project.setUniqueId(textID.getText());
		project.setDescr(textProjDescription.getText());

		project.setCreatedby(Session.getInstance().getConnectionDetails().getUsername());
		project.setStatus("New Project");
		project.setRepository("Local Repository");
	}

} // @jve:decl-index=0:visual-constraint="10,10"
