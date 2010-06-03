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
import org.gello.client.composites.NewProjectComposite;
import org.gello.client.manager.Project;

/**
 * A dialog to contain the NewProjectComposite.
 *
 */
public class NewProjectDialog extends Dialog
{
	Project project = new Project();

	public NewProjectDialog(Shell parentShell)
	{
		super(parentShell);
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("New Project");
	}

	protected Control createDialogArea(Composite parent)
	{
		NewProjectComposite composite = new NewProjectComposite(parent, SWT.NONE);

		return composite;
	}

	/**
	 * When OK is pressed, update the project.
	 */
	protected void okPressed()
	{
		((NewProjectComposite) this.getDialogArea()).updateProject(project);
		super.okPressed();
	}

	/**
	 * Gets the current project, updated after OK is pressed.
	 * @return
	 */
	public Project getProject()
	{
		return this.project;
	}
}
