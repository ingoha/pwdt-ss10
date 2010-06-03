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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.gello.client.composites.ProjectPropertiesComposite;
import org.gello.client.model.ProjectProperties;

/**
 * A dialog to contain the ProjectPropertiesComposite.
 *
 */
public class ProjectPropertiesDialog extends Dialog
{
	ProjectProperties properties;

	public ProjectPropertiesDialog(Shell parentShell)
	{
		super(parentShell);
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("Project Properties");
	}

	protected Control createDialogArea(Composite parent)
	{
		ProjectPropertiesComposite composite = new ProjectPropertiesComposite(parent, SWT.NONE);

		return composite;
	}

	/**
	 * When ok is pressed, the ProjectProperites object is updated.
	 * 
	 */
	protected void okPressed()
	{
		((ProjectPropertiesComposite) this.getDialogArea()).updateProperties(properties);
		super.okPressed();
	}

	/**
	 * Set the current project properties object.
	 * @param p
	 */
	public void setProjectProperties(ProjectProperties p)
	{
		this.properties = p;
		((ProjectPropertiesComposite) this.getDialogArea()).setProperties(properties);
	}

	/**
	 * Get the current project properties object.  Updated when OK is pressed.
	 * @return
	 */
	public ProjectProperties getProjectProperties()
	{
		return properties;
	}

}
