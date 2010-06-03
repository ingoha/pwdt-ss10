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


package org.gello.client.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

/**
 * A view to hold status information.
 *
 */
public class Status extends ViewPart
{
	public static final String ID = "org.gello.client.views.Status";
	private static Text text;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent)
	{
		text = new Text(parent, SWT.MULTI | SWT.V_SCROLL);
		text.setEditable(false);
		text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	}

	@Override
	public void setFocus()
	{
		text.setFocus();

	}

	/**
	 * Sets the current text and moves the scrollbar to the bottom.
	 * @param newText
	 */
	public void setText(String newText)
	{
		text.setText(newText);
		text.setTopIndex(text.getLineCount());
	}

	public String getText()
	{
		return text.getText();
	}

}
