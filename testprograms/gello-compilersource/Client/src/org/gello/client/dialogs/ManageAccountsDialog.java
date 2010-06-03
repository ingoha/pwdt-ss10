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
import org.gello.client.composites.ManageAccountsComposite;

/**
 * A dialog to contain the ManageAccountsComposite.  The button bar is null.
 *
 */
public class ManageAccountsDialog extends Dialog
{
	public ManageAccountsDialog(Shell parentShell)
	{
		super(parentShell);
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Manage Accounts");
	}

	protected Control createDialogArea(Composite parent)
	{
		ManageAccountsComposite composite = new ManageAccountsComposite(parent, SWT.NONE);
		return composite;
	}

	protected void okPressed()
	{

		super.okPressed();
	}

	@Override
	protected Control createButtonBar(Composite parent)
	{
		return null;
	}
}
