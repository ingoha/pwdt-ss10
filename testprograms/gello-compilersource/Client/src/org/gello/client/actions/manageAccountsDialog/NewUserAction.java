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


package org.gello.client.actions.manageAccountsDialog;

import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.composites.ManageAccountsComposite;
import org.gello.client.dialogs.EditUserDialog;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.manager.User;

public class NewUserAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.manageAccountsDialog.NewAccount";

	public NewUserAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&New");
		setToolTipText("New Account");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.NEW_ACCOUNT));
	}

	/**
	 * Creates a new user on the server, after pressing OK.
	 * If successful, that user is added to the list on the client.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void run()
	{
		EditUserDialog d = new EditUserDialog(window.getShell());
		d.create();
		d.setEditableUsername(true);

		User user = new User();
		d.setUser(user);

		int code = d.open();

		if (code == EditUserDialog.OK)
		{
			User updatedUser = null;
			try
			{
				updatedUser = Application.getManager().updateUser(user);
			}
			catch (ServerException_Exception e)
			{
				e.printStackTrace();
			}
			if (updatedUser != null)
			{
				ArrayList<User> userList = (ArrayList<User>) ManageAccountsComposite.usersViewer.getInput();
				userList.add(updatedUser);
				ManageAccountsComposite.usersViewer.refresh();
			}
		}
	}

	public void dispose()
	{
	}
}
