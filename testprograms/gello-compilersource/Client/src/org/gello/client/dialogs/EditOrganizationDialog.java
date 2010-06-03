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


package org.gello.client.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.gello.client.composites.OrganizationAccountComposite;
import org.gello.client.manager.Organization;

/**
 * A dialog to edit organizations.
 * It contains the OrganizationAccountComposite.
 *
 */
public class EditOrganizationDialog extends Dialog
{

	private Organization organization;

	public EditOrganizationDialog(Shell parentShell)
	{
		super(parentShell);
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Account");
	}

	protected Control createDialogArea(Composite parent)
	{
		OrganizationAccountComposite composite = new OrganizationAccountComposite(parent, SWT.NONE);
		return composite;
	}

	/**
	 * Update the organization when OK is pressed.
	 * 
	 */
	protected void okPressed()
	{
		((OrganizationAccountComposite) this.getDialogArea()).updateOrganization(organization);
		super.okPressed();
	}

	/**
	 * Set the organization composite fields from an organization
	 * object.
	 * @param organization Organization object to populate from.
	 */
	public void setOrganization(Organization organization)
	{
		this.organization = organization;
		if (organization.getName() == null)
			this.getShell().setText("New Account");
		else
			this.getShell().setText(organization.getName() + "'s Account");
		((OrganizationAccountComposite) this.getDialogArea()).setOrganization(organization);
	}

	/**
	 * Get the current organization that is being modified,
	 * @return The organization being modified.  If OK has been pressed, it will be updated.
	 */
	public Organization getOrganization()
	{
		return organization;
	}

	/**
	 * Sets if the organization name is editable (new organization).
	 * @param editable
	 */
	public void setEditableOrganizationName(boolean editable)
	{
		((OrganizationAccountComposite) this.getDialogArea()).setEditableOrganizationName(editable);
	}

}
