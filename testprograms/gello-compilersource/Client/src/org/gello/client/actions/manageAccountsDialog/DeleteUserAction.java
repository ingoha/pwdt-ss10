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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.composites.ManageAccountsComposite;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.manager.User;

public class DeleteUserAction extends Action implements ActionFactory.IWorkbenchAction
{
	public final static String ID = "org.gello.client.actions.manageAccountsDialog.DeleteAccount";
	private User user;

	public DeleteUserAction(IWorkbenchWindow window)
	{
		setId(ID);
		setText("&Delete");
		setToolTipText("Delete Account");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.DELETE_ACCOUNT));
	}

	/**
	 * Deletes a user from the server, and if successful, 
	 * updates the list by removing it on the client.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void run()
	{
		MessageBox messageBox = new MessageBox(Display.getCurrent().getActiveShell(), SWT.YES | SWT.NO | SWT.ICON_QUESTION);
		messageBox.setMessage("Are you sure you would like to delete " + user.getUserName() + "'s account?");

		if (messageBox.open() == SWT.YES)
		{
			boolean success = false;
			try
			{
				success = Application.getManager().removeUser(user);
			}
			catch (ServerException_Exception e)
			{
				e.printStackTrace();
			}
			if (success)
			{
				ArrayList<User> userList = (ArrayList<User>) ManageAccountsComposite.usersViewer.getInput();
				for (int i = 0; i < userList.size(); i++)
				{
					if (userList.get(i).getUserName().equals(user.getUserName()))
						userList.remove(i);
				}
				ManageAccountsComposite.usersViewer.setSelection(null);
				ManageAccountsComposite.usersViewer.refresh();
			}
		}

	}

	public void dispose()
	{
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
