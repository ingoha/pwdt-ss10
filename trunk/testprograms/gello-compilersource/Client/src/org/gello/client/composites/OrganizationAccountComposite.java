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
import org.eclipse.swt.widgets.Text;
import org.gello.client.manager.CertifiableExpressionType;
import org.gello.client.manager.EmailAddress;
import org.gello.client.manager.EmailAddressType;
import org.gello.client.manager.Organization;
import org.gello.client.manager.PhoneNumber;
import org.gello.client.manager.PhoneNumberType;

/**
 * The composite to edit an organization.
 * 
 */
public class OrganizationAccountComposite extends Composite
{
	private Label labelOrgName = null;
	private Text textOrgName = null;
	private Label labelAddress1 = null;
	private Label labelCity = null;
	private Text textCity = null;
	private Label labelState = null;
	private Text textState = null;
	private Label labelZip = null;
	private Text textZip = null;
	private Label labelCountry = null;
	private Text textCountry = null;
	private Label labelContact = null;
	private Label labelWebsite = null;
	private Label labelOrganization = null;
	private Text textWebsite = null;
	private Label labelOrganizationSeparator = null;
	private Label labelContactName = null;
	private Label labelContactEmail = null;
	private Label labelContactPhone = null;
	private Text textContactName = null;
	private Text textContactEmail = null;
	private Text textContactPhone = null;
	private Label labelContactSeparator = null;
	private Label labelExpressions = null;
	private Button checkBoxDrug = null;
	private Button checkBoxGuideline = null;
	private Button checkBoxTrial = null;
	private Button checkBoxPOC = null;
	private Text textAddress1 = null;
	private Label labelAddress2 = null;
	private Text textAddress2 = null;
	private Label labelAddress3 = null;
	private Text textAddress3 = null;

	public OrganizationAccountComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		GridData gridData104 = new GridData();
		gridData104.grabExcessHorizontalSpace = true;
		gridData104.horizontalSpan = 5;
		gridData104.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData91 = new GridData();
		gridData91.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData31 = new GridData();
		gridData31.grabExcessHorizontalSpace = true;
		gridData31.horizontalSpan = 5;
		gridData31.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData21 = new GridData();
		gridData21.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData11 = new GridData();
		gridData11.horizontalSpan = 5;
		gridData11.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData11.grabExcessHorizontalSpace = true;
		this.setSize(new Point(409, 405));
		GridData gridData103 = new GridData();
		gridData103.grabExcessHorizontalSpace = true;
		gridData103.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData102 = new GridData();
		gridData102.widthHint = 20;
		GridData gridData101 = new GridData();
		gridData101.widthHint = 35;
		GridData gridData100 = new GridData();
		gridData100.horizontalSpan = 5;
		GridData gridData99 = new GridData();
		gridData99.horizontalSpan = 5;
		GridData gridData98 = new GridData();
		gridData98.horizontalSpan = 5;
		GridData gridData97 = new GridData();
		gridData97.horizontalSpan = 5;
		GridData gridData96 = new GridData();
		gridData96.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData18 = new GridData();
		gridData18.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData17 = new GridData();
		gridData17.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData15 = new GridData();
		gridData15.horizontalSpan = 6;
		GridData gridData14 = new GridData();
		gridData14.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData14.horizontalSpan = 6;
		GridData gridData13 = new GridData();
		gridData13.horizontalSpan = 5;
		gridData13.grabExcessHorizontalSpace = true;
		gridData13.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData10 = new GridData();
		gridData10.horizontalSpan = 5;
		gridData10.grabExcessHorizontalSpace = true;
		gridData10.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData1 = new GridData();
		gridData1.horizontalSpan = 5;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData7 = new GridData();
		gridData7.horizontalSpan = 5;
		gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData7.grabExcessHorizontalSpace = true;
		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData5 = new GridData();
		gridData5.horizontalSpan = 6;
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.grabExcessHorizontalSpace = true;
		GridData gridData = new GridData();
		gridData.horizontalSpan = 6;
		labelOrganization = new Label(this, SWT.NONE);
		labelOrganization.setText("Organization");
		labelOrganization.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelOrganization.setLayoutData(gridData);
		GridData gridData12 = new GridData();
		gridData12.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData111 = new GridData();
		gridData111.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData9 = new GridData();
		gridData9.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		gridData9.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		GridData gridData8 = new GridData();
		gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData4 = new GridData();
		gridData4.horizontalSpan = 6;
		GridData gridData3 = new GridData();
		gridData3.horizontalSpan = 5;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.grabExcessHorizontalSpace = true;
		GridData gridData2 = new GridData();
		gridData2.horizontalSpan = 5;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		labelOrgName = new Label(this, SWT.RIGHT);
		labelOrgName.setText("Organization :");
		labelOrgName.setLayoutData(gridData8);
		textOrgName = new Text(this, SWT.BORDER);
		textOrgName.setText("");
		textOrgName.setEditable(false);
		textOrgName.setLayoutData(gridData2);
		labelAddress1 = new Label(this, SWT.NONE);
		labelAddress1.setText("Address 1 :");
		labelAddress1.setLayoutData(gridData9);
		textAddress1 = new Text(this, SWT.BORDER);
		textAddress1.setLayoutData(gridData11);
		labelAddress2 = new Label(this, SWT.NONE);
		labelAddress2.setText("Address 2 :");
		labelAddress2.setLayoutData(gridData21);
		textAddress2 = new Text(this, SWT.BORDER);
		textAddress2.setLayoutData(gridData31);
		labelAddress3 = new Label(this, SWT.NONE);
		labelAddress3.setText("Address 3 :");
		labelAddress3.setLayoutData(gridData91);
		textAddress3 = new Text(this, SWT.BORDER);
		textAddress3.setLayoutData(gridData104);
		labelCity = new Label(this, SWT.NONE);
		labelCity.setText("City :");
		labelCity.setLayoutData(gridData111);
		textCity = new Text(this, SWT.BORDER);
		textCity.setText("");
		textCity.setLayoutData(gridData103);
		labelState = new Label(this, SWT.NONE);
		labelState.setText("State :");
		textState = new Text(this, SWT.BORDER);
		textState.setText("");
		textState.setLayoutData(gridData102);
		labelZip = new Label(this, SWT.NONE);
		labelZip.setText("Zip Code:");
		textZip = new Text(this, SWT.BORDER);
		textZip.setText("");
		textZip.setLayoutData(gridData101);
		labelCountry = new Label(this, SWT.NONE);
		labelCountry.setText("Country:");
		labelCountry.setLayoutData(gridData12);
		textCountry = new Text(this, SWT.BORDER);
		textCountry.setLayoutData(gridData3);
		textCountry.setText("");
		labelWebsite = new Label(this, SWT.NONE);
		labelWebsite.setText("Website :");
		labelWebsite.setLayoutData(gridData6);
		textWebsite = new Text(this, SWT.BORDER);
		textWebsite.setText("");
		textWebsite.setLayoutData(gridData7);
		labelOrganizationSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelOrganizationSeparator.setText("Label");
		labelOrganizationSeparator.setLayoutData(gridData5);
		labelContact = new Label(this, SWT.NONE);
		labelContact.setText("Contact");
		labelContact.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelContact.setLayoutData(gridData4);
		labelContactName = new Label(this, SWT.NONE);
		labelContactName.setText("Name :");
		labelContactName.setLayoutData(gridData17);
		textContactName = new Text(this, SWT.BORDER);
		textContactName.setText("");
		textContactName.setLayoutData(gridData1);
		labelContactEmail = new Label(this, SWT.NONE);
		labelContactEmail.setText("Email :");
		labelContactEmail.setLayoutData(gridData18);
		textContactEmail = new Text(this, SWT.BORDER);
		textContactEmail.setText("");
		textContactEmail.setLayoutData(gridData10);
		labelContactPhone = new Label(this, SWT.NONE);
		labelContactPhone.setText("Phone :");
		labelContactPhone.setLayoutData(gridData96);
		textContactPhone = new Text(this, SWT.BORDER);
		textContactPhone.setText("");
		textContactPhone.setLayoutData(gridData13);
		labelContactSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelContactSeparator.setText("Label");
		labelContactSeparator.setLayoutData(gridData14);
		labelExpressions = new Label(this, SWT.NONE);
		labelExpressions.setText("GELLO Expressions Certifiable");
		labelExpressions.setLayoutData(gridData15);
		labelExpressions.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		Label filler14 = new Label(this, SWT.NONE);
		checkBoxDrug = new Button(this, SWT.CHECK);
		checkBoxDrug.setText("Drug SPL Expressions");
		checkBoxDrug.setSelection(false);
		checkBoxDrug.setLayoutData(gridData98);
		Label filler13 = new Label(this, SWT.NONE);
		checkBoxGuideline = new Button(this, SWT.CHECK);
		checkBoxGuideline.setText("Clinical Guidelines Expressions");
		checkBoxGuideline.setLayoutData(gridData97);
		Label filler12 = new Label(this, SWT.NONE);
		checkBoxTrial = new Button(this, SWT.CHECK);
		checkBoxTrial.setText("Clinical Trial Expressions");
		checkBoxTrial.setLayoutData(gridData99);
		Label filler11 = new Label(this, SWT.NONE);
		checkBoxPOC = new Button(this, SWT.CHECK);
		checkBoxPOC.setText("POC Decision Support Expressions");
		checkBoxPOC.setLayoutData(gridData100);
		this.setLayout(gridLayout);
		this.setSize(500, 500);
	}

	/**
	 * Populate the dialog fields with an organization object.
	 * @param organization Organization object to populate fields from.
	 */
	public void setOrganization(Organization organization)
	{
		textOrgName.setText((organization.getName() == null) ? "" : organization.getName());

		textAddress1.setText((organization.getAddress1() == null) ? "" : organization.getAddress1());
		textAddress2.setText((organization.getAddress2() == null) ? "" : organization.getAddress2());
		textAddress3.setText((organization.getAddress3() == null) ? "" : organization.getAddress3());
		textCity.setText((organization.getCity() == null) ? "" : organization.getCity());
		textState.setText((organization.getState() == null) ? "" : organization.getState());
		textZip.setText((organization.getZip() == null) ? "" : organization.getZip());
		textCountry.setText((organization.getCountry() == null) ? "" : organization.getCountry());
		textWebsite.setText((organization.getWebsite() == null) ? "" : organization.getWebsite());

		textContactName.setText((organization.getContactName() == null) ? "" : organization.getContactName());
		if (organization.getEmailAddresses().size() > 0)
			textContactEmail.setText((organization.getEmailAddresses().get(0).getValue() == null) ? "" : organization.getEmailAddresses().get(0).getValue());
		if (organization.getPhoneNumbers().size() > 0)
			textContactPhone.setText((organization.getPhoneNumbers().get(0).getValue() == null) ? "" : organization.getPhoneNumbers().get(0).getValue());

		for (int i = 0; i < organization.getCertifiableExpressions().size(); i++)
		{
			CertifiableExpressionType certifiable = organization.getCertifiableExpressions().get(i);

			if (certifiable.equals(CertifiableExpressionType.DRUG_SPL_EXPRESSIONS))
				checkBoxDrug.setSelection(true);
			else if (certifiable.equals(CertifiableExpressionType.CLINICAL_GUIDELINE_EXPRESSIONS))
				checkBoxGuideline.setSelection(true);
			else if (certifiable.equals(CertifiableExpressionType.CLINICAL_TRIAL_EXPRESSIONS))
				checkBoxTrial.setSelection(true);
			else if (certifiable.equals(CertifiableExpressionType.POC_DECISION_SUPPORT_EXPRESSIONS))
				checkBoxPOC.setSelection(true);
		}
	}

	/**
	 * Update an organization object with the currently entered
	 * information in the dialog.
	 * @param organization Organization object to update.
	 */
	public void updateOrganization(Organization organization)
	{
		organization.setName(textOrgName.getText());

		organization.setAddress1(textAddress1.getText());
		organization.setAddress2(textAddress2.getText());
		organization.setAddress3(textAddress3.getText());
		organization.setCity(textCity.getText());
		organization.setState(textState.getText());
		organization.setZip(textZip.getText());
		organization.setCountry(textCountry.getText());
		organization.setWebsite(textWebsite.getText());

		organization.setContactName(textContactName.getText());

		organization.getEmailAddresses().clear();
		if (!textContactEmail.getText().equals(""))
		{
			EmailAddress email = new EmailAddress();
			email.setValue(textContactEmail.getText());
			email.setEmailAddressType(EmailAddressType.ORGANIZATION_CONTACT);
			email.setPartyId(organization.getId());
			organization.getEmailAddresses().add(email);
		}

		organization.getPhoneNumbers().clear();
		if (!textContactPhone.getText().equals(""))
		{
			PhoneNumber phone = new PhoneNumber();
			phone.setValue(textContactPhone.getText());
			phone.setPhoneNumberType(PhoneNumberType.ORGANIZATION_CONTACT);
			phone.setPartyId(organization.getId());
			organization.getPhoneNumbers().add(phone);
		}

		List<CertifiableExpressionType> typeList = new ArrayList<CertifiableExpressionType>();

		if (checkBoxDrug.getSelection())
			typeList.add(CertifiableExpressionType.DRUG_SPL_EXPRESSIONS);
		if (checkBoxGuideline.getSelection())
			typeList.add(CertifiableExpressionType.CLINICAL_GUIDELINE_EXPRESSIONS);
		if (checkBoxTrial.getSelection())
			typeList.add(CertifiableExpressionType.CLINICAL_TRIAL_EXPRESSIONS);
		if (checkBoxPOC.getSelection())
			typeList.add(CertifiableExpressionType.POC_DECISION_SUPPORT_EXPRESSIONS);

		organization.getCertifiableExpressions().clear();

		for (int i = 0; i < typeList.size(); i++)
			organization.getCertifiableExpressions().add(typeList.get(i));
	}

	/**
	 * Set whether the organization name is editable (for new organization).
	 * @param editable
	 */
	public void setEditableOrganizationName(boolean editable)
	{
		textOrgName.setEditable(editable);
	}

} // @jve:decl-index=0:visual-constraint="27,25"
