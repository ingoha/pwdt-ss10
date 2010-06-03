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
import org.gello.client.composites.ManageAccountsComposite;
import org.gello.client.manager.Organization;

public class OrganizationViewerSorter extends ViewerSorter
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
		Organization o1 = (Organization) e1;
		Organization o2 = (Organization) e2;

		// Determine which column and do the appropriate sort
		switch (column)
		{
			case ManageAccountsComposite.COLUMN_FIRST_NAME:
				rc = collator.compare(o1.getName(), o2.getName());
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
