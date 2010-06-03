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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.branding.IProductConstants;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.model.ConnectionDetails;
import org.gello.client.preferences.GeneralPreferencePage;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * The login dialog that appears to log into Gello.
 *
 */
public class LoginDialog extends Dialog
{
	private Combo usernameText;
	private Text serverText;
	private Text passwordText;
	private ConnectionDetails connectionDetails;
	private HashMap<String, ConnectionDetails> savedDetails = new HashMap<String, ConnectionDetails>();
	private Image[] images;
	private static final String PASSWORD = "password";
	private static final String SERVER = "server";
	private static final String SAVED = "saved-connections";
	private static final String LAST_USER = "prefs_last_connection";

	public LoginDialog(Shell parentShell)
	{
		super(parentShell);
		loadDescriptors();
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Gello Login");

		// load the image from the product definition
		IProduct product = Platform.getProduct();
		if (product != null)
		{
			String[] imageURLs = parseCSL(product.getProperty(IProductConstants.WINDOW_IMAGES));
			if (imageURLs.length > 0)
			{
				images = new Image[imageURLs.length];
				for (int i = 0; i < imageURLs.length; i++)
				{
					String url = imageURLs[i];
					ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(product.getDefiningBundle().getSymbolicName(), url);
					images[i] = descriptor.createImage(true);
				}
				newShell.setImages(images);
			}
		}
	}

	/**
	 * Returns image URL's from the product property WINDOW_IMAGES.
	 * @param csl
	 * @return Image URL's
	 */
	public static String[] parseCSL(String csl)
	{
		if (csl == null)
			return null;

		StringTokenizer tokens = new StringTokenizer(csl, ","); //$NON-NLS-1$
		ArrayList<String> array = new ArrayList<String>();
		while (tokens.hasMoreTokens())
			array.add(tokens.nextToken().trim());

		return array.toArray(new String[array.size()]);
	}

	/**
	 * Dispose all images and close.
	 * 
	 */
	public boolean close()
	{
		if (images != null)
		{
			for (int i = 0; i < images.length; i++)
				images[i].dispose();
		}
		return super.close();
	}

	protected Control createDialogArea(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);

		Label accountLabel = new Label(composite, SWT.NONE);
		accountLabel.setText("Account details:");
		accountLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 2, 1));

		Label usernameLabel = new Label(composite, SWT.NONE);
		usernameLabel.setText("&User ID:");
		usernameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		usernameText = new Combo(composite, SWT.BORDER);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, false);
		gridData.widthHint = convertHeightInCharsToPixels(20);
		usernameText.setLayoutData(gridData);
		usernameText.addListener(SWT.Modify, new Listener()
		{
			public void handleEvent(Event event)
			{
				ConnectionDetails d = (ConnectionDetails) savedDetails.get(usernameText.getText());
				if (d != null)
				{
					serverText.setText(d.getServer());
					passwordText.setText(d.getPassword());
				}
			}
		});

		Label serverLabel = new Label(composite, SWT.NONE);
		serverLabel.setText("&Server:");
		serverLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		serverText = new Text(composite, SWT.BORDER);
		serverText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		Label passwordLabel = new Label(composite, SWT.NONE);
		passwordLabel.setText("&Password:");
		passwordLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));

		passwordText = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		passwordText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		final Button autoLogin = new Button(composite, SWT.CHECK);
		autoLogin.setText("Login &automatically at startup");
		autoLogin.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, true, 2, 1));
		autoLogin.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				IEclipsePreferences prefs = new ConfigurationScope().getNode(Application.PLUGIN_ID);
				prefs.putBoolean(GeneralPreferencePage.AUTO_LOGIN, autoLogin.getSelection());
			}
		});
		IPreferencesService service = Platform.getPreferencesService();
		boolean auto_login = service.getBoolean(Application.PLUGIN_ID, GeneralPreferencePage.AUTO_LOGIN, false, null);

		autoLogin.setSelection(auto_login);

		String lastUser = "none";
		if (connectionDetails != null)
			lastUser = connectionDetails.getUsername();
		initializeUsers(lastUser);

		return composite;
	}

	protected void createButtonsForButtonBar(Composite parent)
	{
		Button removeCurrentUser = createButton(parent, IDialogConstants.CLIENT_ID, "&Delete User", false);
		removeCurrentUser.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				savedDetails.remove(usernameText.getText());
				initializeUsers("");

				// ADDED CODE
				Preferences preferences = new ConfigurationScope().getNode(Application.PLUGIN_ID);
				try
				{
					preferences.node(SAVED).removeNode();
				}
				catch (BackingStoreException e1)
				{
					e1.printStackTrace();
				}

				Preferences connections = preferences.node(SAVED);

				for (Iterator it = savedDetails.keySet().iterator(); it.hasNext();)
				{
					String name = (String) it.next();
					ConnectionDetails d = (ConnectionDetails) savedDetails.get(name);
					Preferences connection = connections.node(name);
					connection.put(SERVER, d.getServer());
					connection.put(PASSWORD, d.getPassword());
				}
				try
				{
					preferences.flush();
				}
				catch (BackingStoreException e2)
				{
					e2.printStackTrace();
				}

			}
		});
		createButton(parent, IDialogConstants.OK_ID, "&Login", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

	}

	protected void initializeUsers(String defaultUser)
	{
		usernameText.removeAll();
		passwordText.setText("");
		serverText.setText("");
		for (Iterator it = savedDetails.keySet().iterator(); it.hasNext();)
			usernameText.add((String) it.next());
		int index = Math.max(usernameText.indexOf(defaultUser), 0);
		usernameText.select(index);
	}

	protected void okPressed()
	{
		if (connectionDetails.getUsername().equals(""))
		{
			MessageDialog.openError(getShell(), "Invalid User ID", "User ID field must not be blank.");
			return;
		}
		if (connectionDetails.getServer().equals(""))
		{
			MessageDialog.openError(getShell(), "Invalid Server", "Server field must not be blank.");
			return;
		}
		super.okPressed();
	}

	protected void buttonPressed(int buttonId)
	{
		String userId = usernameText.getText();
		String server = serverText.getText();
		if (server.indexOf("Manager?wsdl") == -1)
		{
			if (server.lastIndexOf('/') != server.length()-1)
				server += "/";
			server += "Manager?wsdl";
		}
		
		String password = passwordText.getText();
		connectionDetails = new ConnectionDetails(userId, server, password);
		savedDetails.put(userId, connectionDetails);
		if (buttonId == IDialogConstants.OK_ID || buttonId == IDialogConstants.CANCEL_ID)
			saveDescriptors();
		super.buttonPressed(buttonId);
	}

	public void saveDescriptors()
	{
		Preferences preferences = new ConfigurationScope().getNode(Application.PLUGIN_ID);
		preferences.put(LAST_USER, connectionDetails.getUsername());
		Preferences connections = preferences.node(SAVED);

		for (Iterator it = savedDetails.keySet().iterator(); it.hasNext();)
		{
			String name = (String) it.next();
			ConnectionDetails d = (ConnectionDetails) savedDetails.get(name);
			Preferences connection = connections.node(name);
			connection.put(SERVER, d.getServer());
			connection.put(PASSWORD, d.getPassword());
		}
		try
		{
			preferences.flush();
		}
		catch (BackingStoreException e)
		{
			e.printStackTrace();
		}

	}

	private void loadDescriptors()
	{
		try
		{
			Preferences preferences = new ConfigurationScope().getNode(Application.PLUGIN_ID);
			Preferences connections = preferences.node(SAVED);
			String[] userNames = connections.childrenNames();
			for (int i = 0; i < userNames.length; i++)
			{
				String userName = userNames[i];
				Preferences node = connections.node(userName);
				savedDetails.put(userName, new ConnectionDetails(userName, node.get(SERVER, ""), node.get(PASSWORD, "")));
			}
			connectionDetails = (ConnectionDetails) savedDetails.get(preferences.get(LAST_USER, ""));
		}
		catch (BackingStoreException e)
		{
			e.printStackTrace();
		}
	}

	public ConnectionDetails getConnectionDetails()
	{
		return connectionDetails;
	}
}
