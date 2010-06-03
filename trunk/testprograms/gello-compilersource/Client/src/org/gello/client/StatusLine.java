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


package org.gello.client;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.model.Session;

/**
 * The status line to display for gello.  Has the username and icon.
 *
 */
public class StatusLine extends ContributionItem
{
	private Label userName;
	private Label userPic;
	private Image userImage;

	public StatusLine(String id)
	{
		super(id);
	}

	public void fill(Composite parent)
	{
		Composite data = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.marginHeight = 2;
		data.setLayout(gridLayout);

		userName = new Label(data, SWT.NONE);
		userName.setText(Session.getInstance().getConnectionDetails().getUsername());
		userPic = new Label(data, SWT.NONE);
		userImage = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.USER).createImage();
		userPic.setImage(userImage);
	}

	@Override
	public void dispose()
	{
		userName.dispose();
		userPic.dispose();
		userImage.dispose();
	}
}
