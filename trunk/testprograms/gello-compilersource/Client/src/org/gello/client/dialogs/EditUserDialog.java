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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.gello.client.composites.UserAccountComposite;
import org.gello.client.manager.User;

/**
 * A dialog to edit users.
 * It contains the UserAccountComposite.
 *
 */
public class EditUserDialog extends Dialog
{

	private User user;

	public EditUserDialog(Shell parentShell)
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

		UserAccountComposite composite = new UserAccountComposite(parent, SWT.NONE);
		return composite;
	}

	/**
	 * Update the user when OK is pressed.  Also checks passwords.
	 * 
	 */
	protected void okPressed()
	{
		UserAccountComposite composite = ((UserAccountComposite) this.getDialogArea());
		if (!composite.checkPassword())
		{
			MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(), SWT.OK | SWT.ICON_WARNING);
			messageBox.setMessage("Your passwords do no match!");
			messageBox.open();
			return;
		}
		composite.updateUser(user);
		super.okPressed();
	}

	/**
	 * Set the user account composite fields from a user object.
	 * @param user User object to populate from.
	 */
	public void setUser(User user)
	{
		this.user = user;
		if (user.getUserName() == null)
			this.getShell().setText("New Account");
		else
			this.getShell().setText(user.getUserName() + "'s Account");
		((UserAccountComposite) this.getDialogArea()).setUser(user);
	}

	/**
	 * Get the current user that is being modified.
	 * @return The user being modified.  If OK has been pressed, it will be updated.
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Sets if the username is editable (new user).
	 * @param editable
	 */
	public void setEditableUsername(boolean editable)
	{
		((UserAccountComposite) this.getDialogArea()).setEditableUsername(editable);
	}

}
