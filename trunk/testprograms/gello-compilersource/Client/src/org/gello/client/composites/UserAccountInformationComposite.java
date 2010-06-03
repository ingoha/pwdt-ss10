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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.gello.client.UnixPassword;
import org.gello.client.manager.EmailAddress;
import org.gello.client.manager.EmailAddressType;
import org.gello.client.manager.PhoneNumber;
import org.gello.client.manager.PhoneNumberType;
import org.gello.client.manager.User;

public class UserAccountInformationComposite extends Composite
{
	private Label labelGeneral = null;
	private Label labelFirstName = null;
	private Text textFirstName = null;
	private Label labelUsername = null;
	private Text textUsername = null;
	private Label labelPassword = null;
	private Text textPassword = null;
	private Label labelRetype = null;
	private Text textRetype = null;
	private Label labelGeneralSeperator = null;
	private Label labelEmail = null;
	private Label labelPrimaryEmail = null;
	private Label labelAlt1Email = null;
	private Label labelAlt2Email = null;
	private Text textPrimaryEmail = null;
	private Text textAlt1Email = null;
	private Text textAlt2Email = null;
	private Label labelEmailSeparator = null;
	private Label labelWork = null;
	private Label labelCompany = null;
	private Label labelJobTitle = null;
	private Label labelCity = null;
	private Text textCompany = null;
	private Text textJobTitle = null;
	private Text textCity = null;
	private Label labelState = null;
	private Text textState = null;
	private Label labelZip = null;
	private Text textZip = null;
	private Label labelWorkSeparator = null;
	private Label labelPhone = null;
	private Label labelHomePhone = null;
	private Label labelWorkPhone = null;
	private Label labelMobilePhone = null;
	private Text textHomePhone = null;
	private Text textWorkPhone = null;
	private Text textMobilePhone = null;
	private Text textLastName = null;
	private Label labelLastName = null;
	private Text textAddress1 = null;
	private Label labelAddress1 = null;
	private Label labelAddress2 = null;
	private Label labelAddress3 = null;
	private Text textAddress2 = null;
	private Text textAddress3 = null;
	private Label labelCountry = null;
	private Text textCountry = null;
	private Label labelWebsite = null;
	private Text textWebsite = null;

	public UserAccountInformationComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	private void initialize()
	{
		GridData gridData43 = new GridData();
		gridData43.grabExcessHorizontalSpace = true;
		gridData43.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData43.horizontalSpan = 7;
		GridData gridData34 = new GridData();
		gridData34.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData24 = new GridData();
		gridData24.grabExcessHorizontalSpace = true;
		gridData24.horizontalSpan = 3;
		gridData24.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData16 = new GridData();
		gridData16.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData29 = new GridData();
		gridData29.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData29.grabExcessHorizontalSpace = true;
		gridData29.horizontalSpan = 7;
		GridData gridData28 = new GridData();
		gridData28.horizontalSpan = 7;
		gridData28.grabExcessHorizontalSpace = true;
		gridData28.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData27 = new GridData();
		gridData27.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData191 = new GridData();
		gridData191.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData112 = new GridData();
		gridData112.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData15 = new GridData();
		gridData15.horizontalSpan = 7;
		gridData15.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData15.grabExcessHorizontalSpace = true;
		GridData gridData33 = new GridData();
		gridData33.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData113 = new GridData();
		gridData113.horizontalSpan = 7;
		gridData113.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData113.grabExcessHorizontalSpace = true;
		GridData gridData181 = new GridData();
		gridData181.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData171 = new GridData();
		gridData171.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData161 = new GridData();
		gridData161.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData131 = new GridData();
		gridData131.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData121 = new GridData();
		gridData121.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData71 = new GridData();
		gridData71.horizontalSpan = 7;
		gridData71.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData71.grabExcessHorizontalSpace = true;
		GridData gridData62 = new GridData();
		gridData62.horizontalSpan = 7;
		gridData62.grabExcessHorizontalSpace = true;
		gridData62.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData52 = new GridData();
		gridData52.horizontalSpan = 7;
		gridData52.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData52.grabExcessHorizontalSpace = true;
		GridData gridData42 = new GridData();
		gridData42.horizontalSpan = 8;
		GridData gridData32 = new GridData();
		gridData32.horizontalSpan = 8;
		gridData32.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData23 = new GridData();
		gridData23.widthHint = 35;
		GridData gridData110 = new GridData();
		gridData110.widthHint = 20;
		GridData gridData22 = new GridData();
		gridData22.grabExcessHorizontalSpace = true;
		gridData22.horizontalSpan = 3;
		gridData22.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData19 = new GridData();
		gridData19.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData18 = new GridData();
		gridData18.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData17 = new GridData();
		gridData17.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData14 = new GridData();
		gridData14.horizontalSpan = 7;
		gridData14.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData14.grabExcessHorizontalSpace = true;
		GridData gridData13 = new GridData();
		gridData13.horizontalSpan = 7;
		gridData13.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData13.grabExcessHorizontalSpace = true;
		GridData gridData12 = new GridData();
		gridData12.horizontalSpan = 8;
		GridData gridData111 = new GridData();
		gridData111.horizontalSpan = 8;
		gridData111.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData111.grabExcessHorizontalSpace = true;
		GridData gridData10 = new GridData();
		gridData10.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData10.horizontalSpan = 7;
		gridData10.grabExcessHorizontalSpace = true;
		GridData gridData9 = new GridData();
		gridData9.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData9.horizontalSpan = 7;
		gridData9.grabExcessHorizontalSpace = true;
		GridData gridData8 = new GridData();
		gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData8.horizontalSpan = 7;
		gridData8.grabExcessHorizontalSpace = true;
		GridData gridData7 = new GridData();
		gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData61 = new GridData();
		gridData61.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData51 = new GridData();
		gridData51.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData41 = new GridData();
		gridData41.horizontalSpan = 8;
		GridData gridData31 = new GridData();
		gridData31.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData31.grabExcessHorizontalSpace = true;
		gridData31.horizontalSpan = 8;
		GridData gridData21 = new GridData();
		gridData21.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData21.horizontalSpan = 7;
		gridData21.grabExcessHorizontalSpace = true;
		GridData gridData11 = new GridData();
		gridData11.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData6.horizontalSpan = 7;
		GridData gridData5 = new GridData();
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData4.horizontalSpan = 7;
		gridData4.grabExcessHorizontalSpace = true;
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		GridData gridData2 = new GridData();
		gridData2.horizontalSpan = 8;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.horizontalSpan = 7;
		gridData1.grabExcessHorizontalSpace = true;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 8;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		labelGeneral = new Label(this, SWT.NONE);
		labelGeneral.setText("General");
		labelGeneral.setLayoutData(gridData2);
		labelGeneral.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelUsername = new Label(this, SWT.NONE);
		labelUsername.setText("Username :");
		labelUsername.setLayoutData(gridData3);
		textUsername = new Text(this, SWT.BORDER);
		labelFirstName = new Label(this, SWT.NONE);
		labelFirstName.setText("First Name :");
		labelFirstName.setLayoutData(gridData);
		textFirstName = new Text(this, SWT.BORDER);
		textFirstName.setText("");
		textFirstName.setEditable(true);
		textFirstName.setLayoutData(gridData1);
		textUsername.setEditable(false);
		textUsername.setLayoutData(gridData4);
		textUsername.setText("");
		labelLastName = new Label(this, SWT.NONE);
		labelLastName.setText("Last Name :");
		labelLastName.setLayoutData(gridData33);
		textLastName = new Text(this, SWT.BORDER);
		textLastName.setLayoutData(gridData113);
		labelPassword = new Label(this, SWT.NONE);
		labelPassword.setText("Password :");
		labelPassword.setLayoutData(gridData5);
		textPassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		textPassword.setToolTipText("");
		textPassword.setText("");
		textPassword.setLayoutData(gridData6);
		labelRetype = new Label(this, SWT.NONE);
		labelRetype.setText("Retype Password :");
		labelRetype.setLayoutData(gridData11);
		textRetype = new Text(this, SWT.BORDER | SWT.PASSWORD);
		textRetype.setText("");
		textRetype.setLayoutData(gridData21);
		labelGeneralSeperator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelGeneralSeperator.setText("Label");
		labelGeneralSeperator.setLayoutData(gridData31);
		labelEmail = new Label(this, SWT.NONE);
		labelEmail.setText("Email Addresses");
		labelEmail.setLayoutData(gridData41);
		labelEmail.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelPrimaryEmail = new Label(this, SWT.NONE);
		labelPrimaryEmail.setText("Primary :");
		labelPrimaryEmail.setLayoutData(gridData51);
		textPrimaryEmail = new Text(this, SWT.BORDER);
		textPrimaryEmail.setText("");
		textPrimaryEmail.setLayoutData(gridData8);
		labelAlt1Email = new Label(this, SWT.NONE);
		labelAlt1Email.setText("Alternate 1 :");
		labelAlt1Email.setLayoutData(gridData61);
		textAlt1Email = new Text(this, SWT.BORDER);
		textAlt1Email.setLayoutData(gridData9);
		labelAlt2Email = new Label(this, SWT.NONE);
		labelAlt2Email.setText("Alternate 2 :");
		labelAlt2Email.setLayoutData(gridData7);
		textAlt2Email = new Text(this, SWT.BORDER);
		textAlt2Email.setLayoutData(gridData10);
		labelEmailSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelEmailSeparator.setText("Label");
		labelEmailSeparator.setLayoutData(gridData111);
		labelWork = new Label(this, SWT.NONE);
		labelWork.setText("Work Address");
		labelWork.setLayoutData(gridData12);
		labelWork.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelCompany = new Label(this, SWT.NONE);
		labelCompany.setText("Company :");
		labelCompany.setLayoutData(gridData17);
		textCompany = new Text(this, SWT.BORDER);
		textCompany.setText("");
		textCompany.setLayoutData(gridData13);
		labelJobTitle = new Label(this, SWT.NONE);
		labelJobTitle.setText("Job Title:");
		labelJobTitle.setLayoutData(gridData18);
		textJobTitle = new Text(this, SWT.BORDER);
		textJobTitle.setText("");
		textJobTitle.setLayoutData(gridData14);
		labelAddress1 = new Label(this, SWT.NONE);
		labelAddress1.setText("Address 1 :");
		labelAddress1.setLayoutData(gridData112);
		textAddress1 = new Text(this, SWT.BORDER);
		textAddress1.setLayoutData(gridData15);
		labelAddress2 = new Label(this, SWT.NONE);
		labelAddress2.setText("Address 2 :");
		labelAddress2.setLayoutData(gridData191);
		textAddress2 = new Text(this, SWT.BORDER);
		textAddress2.setLayoutData(gridData28);
		labelAddress3 = new Label(this, SWT.NONE);
		labelAddress3.setText("Address 3 :");
		labelAddress3.setLayoutData(gridData27);
		textAddress3 = new Text(this, SWT.BORDER);
		textAddress3.setLayoutData(gridData29);
		labelCity = new Label(this, SWT.NONE);
		labelCity.setText("City :");
		labelCity.setLayoutData(gridData19);
		textCity = new Text(this, SWT.BORDER);
		textCity.setText("");
		textCity.setLayoutData(gridData22);
		labelState = new Label(this, SWT.NONE);
		labelState.setText("State :");
		labelState.setLayoutData(gridData131);
		textState = new Text(this, SWT.BORDER);
		textState.setText("");
		textState.setLayoutData(gridData110);
		labelZip = new Label(this, SWT.NONE);
		labelZip.setText("Zip Code :");
		labelZip.setLayoutData(gridData121);
		textZip = new Text(this, SWT.BORDER);
		textZip.setText("");
		textZip.setLayoutData(gridData23);
		labelCountry = new Label(this, SWT.NONE);
		labelCountry.setText("Country :");
		labelCountry.setLayoutData(gridData16);
		textCountry = new Text(this, SWT.BORDER);
		textCountry.setLayoutData(gridData24);
		Label filler3 = new Label(this, SWT.NONE);
		Label filler4 = new Label(this, SWT.NONE);
		Label filler5 = new Label(this, SWT.NONE);
		Label filler6 = new Label(this, SWT.NONE);
		labelWebsite = new Label(this, SWT.NONE);
		labelWebsite.setText("Website :");
		labelWebsite.setLayoutData(gridData34);
		textWebsite = new Text(this, SWT.BORDER);
		textWebsite.setLayoutData(gridData43);
		labelWorkSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelWorkSeparator.setText("Label");
		labelWorkSeparator.setLayoutData(gridData32);
		labelPhone = new Label(this, SWT.NONE);
		labelPhone.setText("Phone Numbers");
		labelPhone.setFont(new Font(Display.getDefault(), "Tahoma", 10, SWT.BOLD));
		labelPhone.setLayoutData(gridData42);
		labelHomePhone = new Label(this, SWT.NONE);
		labelHomePhone.setText("Home :");
		labelHomePhone.setLayoutData(gridData161);
		textHomePhone = new Text(this, SWT.BORDER);
		textHomePhone.setText("");
		textHomePhone.setLayoutData(gridData52);
		labelWorkPhone = new Label(this, SWT.NONE);
		labelWorkPhone.setText("Work :");
		labelWorkPhone.setLayoutData(gridData171);
		textWorkPhone = new Text(this, SWT.BORDER);
		textWorkPhone.setLayoutData(gridData62);
		labelMobilePhone = new Label(this, SWT.NONE);
		labelMobilePhone.setText("Mobile :");
		labelMobilePhone.setLayoutData(gridData181);
		textMobilePhone = new Text(this, SWT.BORDER);
		textMobilePhone.setLayoutData(gridData71);
		this.setLayout(gridLayout);
		this.setSize(new Point(484, 568));
	}

	/**
	 * Populate the dialog fields with a user object.
	 * @param user User object to populate from.
	 */
	public void setUser(User user)
	{
		textUsername.setText((user.getUserName() == null) ? "" : user.getUserName());
		textFirstName.setText((user.getFirstName() == null) ? "" : user.getFirstName());
		textLastName.setText((user.getLastName() == null) ? "" : user.getLastName());

		for (int i = 0; i < user.getEmailAddresses().size(); i++)
		{
			if (user.getEmailAddresses().get(i).getEmailAddressType().equals(EmailAddressType.FIRST))
				textPrimaryEmail.setText(user.getEmailAddresses().get(i).getValue());
			else if (user.getEmailAddresses().get(i).getEmailAddressType().equals(EmailAddressType.SECOND))
				textAlt1Email.setText(user.getEmailAddresses().get(i).getValue());
			else if (user.getEmailAddresses().get(i).getEmailAddressType().equals(EmailAddressType.THIRD))
				textAlt2Email.setText(user.getEmailAddresses().get(i).getValue());
		}

		textCompany.setText((user.getCompany() == null) ? "" : user.getCompany());
		textJobTitle.setText((user.getJobTitle() == null) ? "" : user.getJobTitle());
		textAddress1.setText((user.getAddress1() == null) ? "" : user.getAddress1());
		textAddress2.setText((user.getAddress2() == null) ? "" : user.getAddress2());
		textAddress3.setText((user.getAddress3() == null) ? "" : user.getAddress3());
		textCity.setText((user.getCity() == null) ? "" : user.getCity());
		textState.setText((user.getState() == null) ? "" : user.getState());
		textZip.setText((user.getZip() == null) ? "" : user.getZip());
		textCountry.setText((user.getCountry() == null) ? "" : user.getCountry());
		textWebsite.setText((user.getWebsite() == null) ? "" : user.getWebsite());

		for (int i = 0; i < user.getPhoneNumbers().size(); i++)
		{
			if (user.getPhoneNumbers().get(i).getPhoneNumberType().equals(PhoneNumberType.HOME))
				textHomePhone.setText(user.getPhoneNumbers().get(i).getValue());
			else if (user.getPhoneNumbers().get(i).getPhoneNumberType().equals(PhoneNumberType.WORK))
				textWorkPhone.setText(user.getPhoneNumbers().get(i).getValue());
			else if (user.getPhoneNumbers().get(i).getPhoneNumberType().equals(PhoneNumberType.MOBILE))
				textMobilePhone.setText(user.getPhoneNumbers().get(i).getValue());
		}
	}

	/**
	 * Update a user object with the currently entered
	 * information in the dialog.
	 * @param user User object to update.
	 */
	public void updateUser(User user)
	{
		user.setUserName(textUsername.getText());
		user.setFirstName(textFirstName.getText());
		user.setLastName(textLastName.getText());

		if (textPassword.getEditable() && !textPassword.getText().equals(""))
		{
			if (textPassword.getText().equals(textRetype.getText()))
				user.setPassword(UnixPassword.crypt(textPassword.getText(), UnixPassword.getRandomSalt()));
		}

		user.getEmailAddresses().clear();
		if (!textPrimaryEmail.getText().equals(""))
		{
			EmailAddress email = new EmailAddress();
			email.setPartyId(user.getId());
			email.setEmailAddressType(EmailAddressType.FIRST);
			email.setValue(textPrimaryEmail.getText());
			user.getEmailAddresses().add(email);
		}
		if (!textAlt1Email.getText().equals(""))
		{
			EmailAddress email = new EmailAddress();
			email.setPartyId(user.getId());
			email.setEmailAddressType(EmailAddressType.SECOND);
			email.setValue(textAlt1Email.getText());
			user.getEmailAddresses().add(email);
		}
		if (!textAlt2Email.getText().equals(""))
		{
			EmailAddress email = new EmailAddress();
			email.setPartyId(user.getId());
			email.setEmailAddressType(EmailAddressType.THIRD);
			email.setValue(textAlt2Email.getText());
			user.getEmailAddresses().add(email);
		}

		user.setCompany(textCompany.getText());
		user.setJobTitle(textJobTitle.getText());
		user.setAddress1(textAddress1.getText());
		user.setAddress2(textAddress2.getText());
		user.setAddress3(textAddress3.getText());
		user.setCity(textCity.getText());
		user.setState(textState.getText());
		user.setZip(textZip.getText());
		user.setCountry(textCountry.getText());
		user.setWebsite(textWebsite.getText());

		user.getPhoneNumbers().clear();
		if (!textHomePhone.getText().equals(""))
		{
			PhoneNumber phone = new PhoneNumber();
			phone.setPartyId(user.getId());
			phone.setPhoneNumberType(PhoneNumberType.HOME);
			phone.setValue(textHomePhone.getText());
			user.getPhoneNumbers().add(phone);
		}
		if (!textWorkPhone.getText().equals(""))
		{
			PhoneNumber phone = new PhoneNumber();
			phone.setPartyId(user.getId());
			phone.setPhoneNumberType(PhoneNumberType.WORK);
			phone.setValue(textWorkPhone.getText());
			user.getPhoneNumbers().add(phone);
		}
		if (!textMobilePhone.getText().equals(""))
		{
			PhoneNumber phone = new PhoneNumber();
			phone.setPartyId(user.getId());
			phone.setPhoneNumberType(PhoneNumberType.MOBILE);
			phone.setValue(textMobilePhone.getText());
			user.getPhoneNumbers().add(phone);
		}
	}

	/**
	 * Check to see that the passwords match (if entered).
	 * @return False if new user and they are not entered, or if they do not match.  True otherwise.
	 */
	public boolean checkPassword()
	{
		if (textUsername.getEditable() && textPassword.getText().equals(""))
			return false;

		if (textPassword.getEditable() && !textPassword.getText().equals(""))
		{
			if (!textPassword.getText().equals(textRetype.getText()))
				return false;
		}
		return true;
	}

	/**
	 * Set whether the username is editable (for new user).
	 * @param editable
	 */
	public void setEditableUsername(boolean editable)
	{
		textUsername.setEditable(editable);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
