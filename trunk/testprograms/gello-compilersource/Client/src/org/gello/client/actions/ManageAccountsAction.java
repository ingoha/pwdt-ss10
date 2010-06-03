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


package org.gello.client.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.dialogs.ManageAccountsDialog;

public class ManageAccountsAction extends Action implements ActionFactory.IWorkbenchAction
{
	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.ManageAccounts";

	public ManageAccountsAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Manage Accounts");
		setToolTipText("Manage Accounts");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.MANAGE_ACCOUNTS));
	}

	/**
	 * Open the Manage Accounts dialog.
	 * 
	 */
	public void run()
	{
		ManageAccountsDialog d = new ManageAccountsDialog(window.getShell());
		int code = d.open();

		if (code == ManageAccountsDialog.OK)
		{
		}
	}

	public void dispose()
	{
	}

}
