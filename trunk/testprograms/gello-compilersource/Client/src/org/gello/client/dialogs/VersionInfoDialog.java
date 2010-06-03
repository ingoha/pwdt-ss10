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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A dialog to contain version info.
 *
 */
public class VersionInfoDialog extends Dialog
{
	private String content;

	public VersionInfoDialog(Shell parentShell, String content)
	{
		super(parentShell);
		this.content = content;
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Version Info");

	}

	protected Control createDialogArea(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());

		GridData gridData = new GridData();
		gridData.widthHint = 400;
		gridData.heightHint = 100;
		Text text = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		text.setText(content);
		text.setLayoutData(gridData);
		text.setEditable(false);
		text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		return composite;
	}

	protected void okPressed()
	{

		super.okPressed();
	}

	/**
	 * Remove the OK button and change "Cancel" to "Close".
	 * 
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		super.createButtonsForButtonBar(parent);
		getButton(IDialogConstants.OK_ID).setVisible(false);
		getButton(IDialogConstants.CANCEL_ID).setText("Close");
	}

}
