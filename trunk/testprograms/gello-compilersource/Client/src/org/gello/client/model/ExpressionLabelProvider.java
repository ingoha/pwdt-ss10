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


package org.gello.client.model;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.gello.client.views.Expressions;

public class ExpressionLabelProvider implements ITableLabelProvider
{
	public Image getColumnImage(Object element, int columnIndex)
	{
		return null;
	}

	/**
	 * Gets the text for the specified column
	 */
	public String getColumnText(Object arg0, int arg1)
	{
		ExpressionInfo expression = (ExpressionInfo) arg0;
		String text = "";
		switch (arg1)
		{
			case Expressions.COLUMN_TYPE:
				text = expression.getType();
				break;
			case Expressions.COLUMN_DRUG_CLASS:
				text = expression.getDrugClass();
				break;
			case Expressions.COLUMN_DRUG_NAME:
				text = expression.getDrugName();
				break;
			case Expressions.COLUMN_CATEGORY:
				text = expression.getCategory();
				break;
			case Expressions.COLUMN_REPOSITORY:
				text = expression.getRepository();
				break;
		}
		return text;
	}

	public void addListener(ILabelProviderListener listener)
	{
	}

	public void dispose()
	{
	}

	public boolean isLabelProperty(Object element, String property)
	{
		return false;
	}

	public void removeListener(ILabelProviderListener listener)
	{
	}
}
