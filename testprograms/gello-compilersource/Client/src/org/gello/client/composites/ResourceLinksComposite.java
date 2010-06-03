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


package org.gello.client.composites;

import java.io.IOException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.gello.client.Application;
import org.gello.client.BrowserLauncher;
import org.gello.client.manager.Resource;
import org.gello.client.manager.ServerException_Exception;

public class ResourceLinksComposite extends Composite
{
	private Label labelContactAdmin = null;

	public ResourceLinksComposite(Composite parent, int style)
	{
		super(parent, style);
		initialize();
	}

	/**
	 * Initialize the resources page with resources retrieved from
	 * the server.
	 *
	 */
	private void initialize()
	{
		List<Resource> resources = null;
		try
		{
			resources = (List<Resource>) Application.getManager().getResourceList();
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		gridLayout.numColumns = 3;
		gridLayout.marginHeight = 0;

		GridData contactGridData = new GridData();
		contactGridData.grabExcessHorizontalSpace = true;
		contactGridData.grabExcessVerticalSpace = false;
		contactGridData.horizontalSpan = 3;
		contactGridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		labelContactAdmin = new Label(this, SWT.NONE);
		labelContactAdmin.setText(" Contact administrator to add or remove resources.");
		labelContactAdmin.setBackground(new Color(Display.getCurrent(), 192, 192, 192));
		labelContactAdmin.setLayoutData(contactGridData);

		// Add each resource individually.
		for (Resource r : resources)
		{
			GridData gridDataText = new GridData();
			gridDataText.grabExcessHorizontalSpace = true;
			gridDataText.grabExcessVerticalSpace = false;
			gridDataText.widthHint = 540;
			gridDataText.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;

			GridData gridDataText1 = new GridData();
			gridDataText1.grabExcessHorizontalSpace = true;
			gridDataText1.grabExcessVerticalSpace = false;
			gridDataText1.widthHint = 540;
			gridDataText1.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;

			GridData gridDataText2 = new GridData();
			gridDataText2.grabExcessHorizontalSpace = true;
			gridDataText2.grabExcessVerticalSpace = false;
			gridDataText2.widthHint = 540;
			gridDataText2.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;

			GridData gridDataSeparator = new GridData();
			gridDataSeparator.grabExcessHorizontalSpace = true;
			gridDataSeparator.horizontalSpan = 3;
			gridDataSeparator.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;

			Label titleFiller = new Label(this, SWT.NONE);
			Label labelTitle = new Label(this, SWT.WRAP);
			labelTitle.setText(r.getTitle());
			labelTitle.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD));
			labelTitle.setLayoutData(gridDataText);
			Label titleFiller1 = new Label(this, SWT.NONE);

			Label URLFiller = new Label(this, SWT.NONE);
			final Label labelURL = new Label(this, SWT.WRAP);
			labelURL.setText(r.getUrl());
			labelURL.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLUE));
			labelURL.setLayoutData(gridDataText1);

			// Add a listener to chagne the cursor to a hand when
			// hovering a link.
			labelURL.addMouseTrackListener(new MouseTrackListener()
			{
				public void mouseEnter(MouseEvent e)
				{
					Display.getDefault().getActiveShell().setCursor(new Cursor(Display.getDefault(), SWT.CURSOR_HAND));
				}

				public void mouseExit(MouseEvent e)
				{
					Display.getDefault().getActiveShell().setCursor(new Cursor(Display.getDefault(), SWT.CURSOR_ARROW));
				}

				public void mouseHover(MouseEvent e)
				{
				}
			});

			// Add mouse listener to open a browser when clicking
			// a link.
			labelURL.addMouseListener(new MouseListener()
			{
				public void mouseDoubleClick(MouseEvent e)
				{
				}

				public void mouseDown(MouseEvent e)
				{
					try
					{
						BrowserLauncher.openURL(labelURL.getText());
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}

				public void mouseUp(MouseEvent e)
				{
				}
			});

			Label URLFiller1 = new Label(this, SWT.NONE);

			Label DescriptionFiller = new Label(this, SWT.NONE);
			Label labelDescription = new Label(this, SWT.WRAP);
			labelDescription.setText(r.getBody());
			labelDescription.setLayoutData(gridDataText2);
			Label DescriptionFiller1 = new Label(this, SWT.NONE);

			Label labelSeparator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
			labelSeparator.setText("");
			labelSeparator.setLayoutData(gridDataSeparator);
		}

		this.setLayout(gridLayout);
		this.setSize(new Point(550, 200));
		this.setSize(550, 50);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
