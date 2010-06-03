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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.gello.client.model.ProjectProperties;

public class ProjectPropertiesComposite extends Composite
{

	private Label labelGeneral = null;
	private Label labelTemplateType = null;
	private Label labelDrugClass = null;
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
	private Label labelCurrentStatus = null;
	private Text textCurrentStatus = null;
	private Label labelCreatedBy = null;
	private Text textCreatedBy = null;
	private Label labelCreatedDate = null;
	private Text textCreatedDate = null;
	private Label labelRevision = null;
	private Text textRevision = null;
	private Label labelDescriptionBorder = null;
	private Label labelProjectStatus = null;
	private Button buttonChangeStaus = null;
	private Button buttonApproval = null;
	private Table table = null;
	private Text textTemplateType = null;
	private Text textDrugClass = null;

	public ProjectPropertiesComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		GridData gridData62 = new GridData();
		gridData62.horizontalSpan = 3;
		gridData62.grabExcessHorizontalSpace = true;
		gridData62.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData51 = new GridData();
		gridData51.horizontalSpan = 3;
		gridData51.grabExcessHorizontalSpace = true;
		gridData51.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData45 = new GridData();
		gridData45.horizontalSpan = 4;
		gridData45.heightHint = 120;
		gridData45.verticalSpan = 3;
		gridData45.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData44 = new GridData();
		gridData44.widthHint = 120;
		gridData44.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		GridData gridData28 = new GridData();
		gridData28.widthHint = 120;
		gridData28.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData28.grabExcessHorizontalSpace = false;
		GridData gridData121 = new GridData();
		gridData121.horizontalSpan = 2;
		gridData121.grabExcessHorizontalSpace = true;
		GridData gridData111 = new GridData();
		gridData111.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData111.horizontalSpan = 4;
		GridData gridData101 = new GridData();
		gridData101.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData101.grabExcessHorizontalSpace = true;
		gridData101.horizontalSpan = 3;
		GridData gridData91 = new GridData();
		gridData91.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData71 = new GridData();
		gridData71.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData71.grabExcessHorizontalSpace = true;
		gridData71.horizontalSpan = 3;
		GridData gridData61 = new GridData();
		gridData61.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData41 = new GridData();
		gridData41.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData41.grabExcessHorizontalSpace = true;
		gridData41.horizontalSpan = 3;
		GridData gridData31 = new GridData();
		gridData31.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData21 = new GridData();
		gridData21.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData21.grabExcessHorizontalSpace = true;
		gridData21.horizontalSpan = 3;
		GridData gridData16 = new GridData();
		gridData16.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData15 = new GridData();
		gridData15.horizontalSpan = 4;
		gridData15.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData15.grabExcessHorizontalSpace = true;
		gridData15.widthHint = 450;
		gridData15.heightHint = 80;
		GridData gridData14 = new GridData();
		gridData14.horizontalSpan = 4;
		GridData gridData13 = new GridData();
		gridData13.horizontalSpan = 4;
		gridData13.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData12 = new GridData();
		gridData12.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData12.horizontalSpan = 3;
		gridData12.grabExcessHorizontalSpace = true;
		GridData gridData11 = new GridData();
		gridData11.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData10 = new GridData();
		gridData10.grabExcessHorizontalSpace = true;
		gridData10.horizontalSpan = 3;
		gridData10.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData9 = new GridData();
		gridData9.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData8 = new GridData();
		gridData8.grabExcessHorizontalSpace = true;
		gridData8.horizontalSpan = 3;
		gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData7 = new GridData();
		gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData6 = new GridData();
		gridData6.grabExcessHorizontalSpace = true;
		gridData6.horizontalSpan = 3;
		gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData5 = new GridData();
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		labelGeneral = new Label(this, SWT.NONE);
		labelGeneral.setText("General Project Information");
		labelGeneral.setLayoutData(gridData);
		labelGeneral.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		this.setLayout(gridLayout);
		labelProjectName = new Label(this, SWT.NONE);
		labelProjectName.setText("Project Name :");
		labelProjectName.setLayoutData(gridData5);
		textProjectName = new Text(this, SWT.BORDER);
		textProjectName.setText("");
		textProjectName.setEnabled(false);
		textProjectName.setLayoutData(gridData6);
		labelDrugName = new Label(this, SWT.NONE);
		labelDrugName.setText("Drug Name :");
		labelDrugName.setLayoutData(gridData7);
		textDrugName = new Text(this, SWT.BORDER);
		textDrugName.setText("");
		textDrugName.setEnabled(false);
		textDrugName.setLayoutData(gridData8);
		labelSPLReference = new Label(this, SWT.NONE);
		labelSPLReference.setText("Drug SPL Reference :");
		labelSPLReference.setLayoutData(gridData9);
		textSPLReference = new Text(this, SWT.BORDER);
		textSPLReference.setText("");
		textSPLReference.setEnabled(false);
		textSPLReference.setLayoutData(gridData10);
		labelTemplateType = new Label(this, SWT.NONE);
		labelTemplateType.setText("Template Type :");
		labelTemplateType.setLayoutData(gridData2);
		textTemplateType = new Text(this, SWT.BORDER);
		textTemplateType.setEnabled(false);
		textTemplateType.setLayoutData(gridData51);
		labelDrugClass = new Label(this, SWT.NONE);
		labelDrugClass.setText("Drug Class :");
		labelDrugClass.setLayoutData(gridData1);
		textDrugClass = new Text(this, SWT.BORDER);
		textDrugClass.setEnabled(false);
		textDrugClass.setLayoutData(gridData62);
		labelCurrentStatus = new Label(this, SWT.NONE);
		labelCurrentStatus.setText("Current Status :");
		labelCurrentStatus.setLayoutData(gridData16);
		textCurrentStatus = new Text(this, SWT.BORDER);
		labelID = new Label(this, SWT.NONE);
		labelID.setText("ID :");
		labelID.setLayoutData(gridData11);
		textID = new Text(this, SWT.BORDER);
		textID.setEnabled(false);
		textID.setText("");
		textID.setLayoutData(gridData12);
		textCurrentStatus.setEnabled(false);
		textCurrentStatus.setLayoutData(gridData21);
		textCurrentStatus.setText("");
		labelCreatedBy = new Label(this, SWT.NONE);
		labelCreatedBy.setText("Created By :");
		labelCreatedBy.setLayoutData(gridData31);
		textCreatedBy = new Text(this, SWT.BORDER);
		textCreatedBy.setEnabled(false);
		textCreatedBy.setText("");
		textCreatedBy.setLayoutData(gridData41);
		labelCreatedDate = new Label(this, SWT.NONE);
		labelCreatedDate.setText("Created Date :");
		labelCreatedDate.setLayoutData(gridData61);
		textCreatedDate = new Text(this, SWT.BORDER);
		textCreatedDate.setEnabled(false);
		textCreatedDate.setText("");
		textCreatedDate.setLayoutData(gridData71);
		labelRevision = new Label(this, SWT.NONE);
		labelRevision.setText("Revision :");
		labelRevision.setLayoutData(gridData91);
		textRevision = new Text(this, SWT.BORDER);
		textRevision.setText("");
		textRevision.setEnabled(false);
		textRevision.setLayoutData(gridData101);
		labelGeneralSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelGeneralSeparator.setText("Label");
		labelGeneralSeparator.setLayoutData(gridData13);
		labelProjDescription = new Label(this, SWT.NONE);
		labelProjDescription.setText("Project Description");
		labelProjDescription.setLayoutData(gridData14);
		labelProjDescription.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		textProjDescription = new Text(this, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		textProjDescription.setText("");
		textProjDescription.setEnabled(false);
		textProjDescription.setLayoutData(gridData15);
		labelDescriptionBorder = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelDescriptionBorder.setText("Label");
		labelDescriptionBorder.setLayoutData(gridData111);
		labelProjectStatus = new Label(this, SWT.NONE);
		labelProjectStatus.setText(" Project Status");
		labelProjectStatus.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelProjectStatus.setToolTipText("");
		labelProjectStatus.setLayoutData(gridData121);
		Label filler3 = new Label(this, SWT.NONE);
		Label filler4 = new Label(this, SWT.NONE);
		buttonChangeStaus = new Button(this, SWT.NONE);
		buttonChangeStaus.setText("Change Status");
		buttonChangeStaus.setLayoutData(gridData28);
		buttonApproval = new Button(this, SWT.NONE);
		buttonApproval.setText("Approval Information");
		buttonApproval.setLayoutData(gridData44);
		Label filler1 = new Label(this, SWT.NONE);
		Label filler2 = new Label(this, SWT.NONE);
		table = new Table(this, SWT.NONE);
		table.setHeaderVisible(true);
		table.setLayoutData(gridData45);
		table.setLinesVisible(true);
		this.setSize(new Point(438, 586));
	}

	/**
	 * Populate the fields in this composite from a ProjectProperties object.
	 * @param p ProjectProperties object to use.
	 */
	public void setProperties(ProjectProperties p)
	{
		textProjectName.setText((p.getProjectName() == null) ? "" : p.getProjectName());
		textDrugName.setText((p.getDrugName() == null) ? "" : p.getDrugName());
		textSPLReference.setText((p.getDrugSPLReference() == null) ? "" : p.getDrugSPLReference());
		textTemplateType.setText((p.getTemplateType() == null) ? "" : p.getTemplateType());
		textDrugClass.setText((p.getDrugClass() == null) ? "" : p.getDrugClass());
		textCurrentStatus.setText((p.getCurrentProjectStatus() == null) ? "" : p.getCurrentProjectStatus());
		textID.setText((p.getGUID() == null) ? "" : p.getGUID());
		textCreatedBy.setText((p.getCreatedBy() == null) ? "" : p.getCreatedBy());
		textCreatedDate.setText((p.getCreatedDate() == null) ? "" : p.getCreatedDate());
		textRevision.setText((p.getProjectRevision() == null) ? "" : p.getProjectRevision());
		textProjDescription.setText((p.getProjectDescription() == null) ? "" : p.getProjectDescription());
	}

	public void updateProperties(ProjectProperties p)
	{
		// TODO Allow the user to update a properties object after typing in information.
	}

} // @jve:decl-index=0:visual-constraint="10,10"
