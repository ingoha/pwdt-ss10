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
import org.gello.client.dialogs.EditOrganizationDialog;
import org.gello.client.manager.Organization;
import org.gello.client.manager.ServerException_Exception;

public class NewOrganizationAction extends Action implements ActionFactory.IWorkbenchAction
{

	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.manageAccountsDialog.NewOrganization";

	public NewOrganizationAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&New");
		setToolTipText("New Account");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.NEW_ACCOUNT));
	}

	/**
	 * Creates a new organization on the server, after pressing OK.
	 * If successful, that organization is added to the list on the client.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void run()
	{
		EditOrganizationDialog d = new EditOrganizationDialog(window.getShell());
		d.create();
		d.setEditableOrganizationName(true);

		Organization organization = new Organization();
		d.setOrganization(organization);

		int code = d.open();

		if (code == EditOrganizationDialog.OK)
		{
			Organization updatedOrganization = null;
			try
			{
				updatedOrganization = Application.getManager().updateOrganization(organization);
			}
			catch (ServerException_Exception e)
			{
				e.printStackTrace();
			}
			if (updatedOrganization != null)
			{
				ArrayList<Organization> organizationList = (ArrayList<Organization>) ManageAccountsComposite.organizationsViewer.getInput();
				organizationList.add(updatedOrganization);
				ManageAccountsComposite.organizationsViewer.refresh();
			}
		}
	}

	public void dispose()
	{
	}
}
