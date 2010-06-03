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

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.gello.client.manager.Project;
import org.gello.client.views.Projects;

public class ProjectViewerSorter extends ViewerSorter
{
	private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;
	private int column;
	private int direction;

	/**
	 * Does the sort. If it's a different column from the previous sort, do an
	 * ascending sort. If it's the same column as the last sort, toggle the sort
	 * direction.
	 * 
	 * @param column
	 */
	public void doSort(int column)
	{
		if (column == this.column)
		{
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		}
		else
		{
			// New column; do an ascending sort
			this.column = column;
			direction = ASCENDING;
		}
	}

	/**
	 * Compares the object for sorting
	 */
	public int compare(Viewer viewer, Object e1, Object e2)
	{
		int rc = 0;
		Project p1 = (Project) e1;
		Project p2 = (Project) e2;

		// Determine which column and do the appropriate sort
		switch (column)
		{
			case Projects.COLUMN_TYPE:
				rc = collator.compare(p1.getTemplateType(), p2.getTemplateType());
				break;
			case Projects.COLUMN_DRUG_CLASS:
				rc = collator.compare(p1.getDrugClass(), p2.getDrugClass());
				break;
			case Projects.COLUMN_NAME:
				rc = collator.compare(p1.getName(), p2.getName());
				break;
			case Projects.COLUMN_STATUS:
				rc = collator.compare(p1.getStatus(), p2.getStatus());
				break;
			case Projects.COLUMN_REPOSITORY:
				rc = collator.compare(p1.getRepository(), p2.getRepository());
				break;
		}

		// If descending order, flip the direction
		if (direction == DESCENDING)
			rc = -rc;

		return rc;
	}

	public int getDirection()
	{
		if (direction == ASCENDING)
			return SWT.UP;
		return SWT.DOWN;
	}
}
