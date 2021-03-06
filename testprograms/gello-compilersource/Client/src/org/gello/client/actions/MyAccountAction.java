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
import org.gello.client.dialogs.EditUserDialog;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.manager.User;
import org.gello.client.model.Session;

public class MyAccountAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.MyAccount";

	public MyAccountAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&My Account");
		setToolTipText("My Account");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.MY_ACCOUNT));
	}

	/**
	 * Open the currently logged in user's account.
	 * 
	 */
	public void run()
	{
		EditUserDialog d = new EditUserDialog(window.getShell());
		d.create();

		User user = null;
		try
		{
			user = Application.getManager().getUser(Session.getInstance().getConnectionDetails().getUsername());
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}
		d.setUser(user);

		int code = d.open();

		if (code == EditUserDialog.OK)
		{
		}
	}

	public void dispose()
	{
	}

}
