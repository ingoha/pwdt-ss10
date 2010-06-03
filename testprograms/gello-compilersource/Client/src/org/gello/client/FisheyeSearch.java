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

import java.io.IOException;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.manager.Project;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.Session;

/**
 * The fisheye search toolbar.
 *
 */
public class FisheyeSearch extends ControlContribution
{

	protected FisheyeSearch(String id)
	{
		super(id);
	}

	@Override
	protected Control createControl(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.marginHeight = 2;
		layout.numColumns = 3;
		composite.setLayout(layout);
		
		String tempURL = null;
		try
		{
			tempURL = Application.getManager().getFisheyeURL();
		}
		catch (ServerException_Exception e2)
		{
			e2.printStackTrace();
		}
		final String fisheyeURL = tempURL;
		

		// The image icon.  When clicking it, fisheye is opened.
		// If a project is opened, that fisheye project is opened.
		Label label = new Label(composite, SWT.NONE);
		label.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("org.gello.client", IImageKeys.FISHEYE).createImage());
		label.addMouseListener(new MouseListener()
		{
			public void mouseDoubleClick(MouseEvent e)
			{
			}

			public void mouseDown(MouseEvent e)
			{
			}

			public void mouseUp(MouseEvent e)
			{
				try
				{
					Project project = Session.getInstance().getCurrentProject();
					if (project == null)
						BrowserLauncher.openURL(fisheyeURL);
					else
						BrowserLauncher.openURL(fisheyeURL + "/browse/" + project.getName().replaceAll(" ", "_"));
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});

		// The search box.
		final Text text = new Text(composite, SWT.BORDER);
		text.setText("Search Repository...");
		text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
		text.pack();

		// The search button.
		final Button button = new Button(composite, SWT.PUSH);
		button.setText("Search");
		button.setEnabled(false);
		button.setFont(new Font(Display.getDefault(), "Tahoma", 7, SWT.NORMAL));

		// Listeners to activate the button and search text box.
		text.addMouseListener(new MouseListener()
		{
			public void mouseDoubleClick(MouseEvent e)
			{
			}

			public void mouseDown(MouseEvent e)
			{
				if (!button.isEnabled())
				{
					text.setText("");
					button.setEnabled(true);
					text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
				}
			}

			public void mouseUp(MouseEvent e)
			{
			}
		});

		button.addMouseListener(new MouseListener()
		{
			public void mouseDoubleClick(MouseEvent e)
			{
			}

			public void mouseDown(MouseEvent e)
			{
			}

			public void mouseUp(MouseEvent e)
			{
				try
				{
					Project project = Session.getInstance().getCurrentProject();
					if (project == null)
					{
						MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(), SWT.OK | SWT.ICON_INFORMATION);
						messageBox.setMessage("Please open a project in order to search its repository.");
						messageBox.open();
					}
					else
						BrowserLauncher.openURL(fisheyeURL + "/qsearch/" + project.getName().replaceAll(" ", "_") + "/?q=" + text.getText().replaceAll(" ", "%20"));
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});

		return composite;
	}
}
