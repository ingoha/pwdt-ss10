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
import org.gello.client.manager.Project;
import org.gello.client.views.Projects;

public class ProjectLabelProvider implements ITableLabelProvider
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
		Project project = (Project) arg0;
		String text = "";
		switch (arg1)
		{
			case Projects.COLUMN_TYPE:
				text = project.getTemplateType();
				break;
			case Projects.COLUMN_DRUG_CLASS:
				text = project.getDrugClass();
				break;
			case Projects.COLUMN_NAME:
				text = project.getName();
				break;
			case Projects.COLUMN_STATUS:
				text = project.getStatus();
				break;
			case Projects.COLUMN_REPOSITORY:
				text = project.getRepository();
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
