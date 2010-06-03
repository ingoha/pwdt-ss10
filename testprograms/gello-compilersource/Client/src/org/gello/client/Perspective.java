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

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.gello.client.views.Browser;
import org.gello.client.views.CompilerOutput;
import org.gello.client.views.Expressions;
import org.gello.client.views.Projects;
import org.gello.client.views.Status;
import org.gello.client.views.TestOutput;

/**
 * The perspective for Gello.
 *
 */
public class Perspective implements IPerspectiveFactory
{
	public void createInitialLayout(IPageLayout layout)
	{
		layout.setEditorAreaVisible(true);
		layout.addStandaloneView(Browser.ID, false, IPageLayout.LEFT, 0.32f, layout.getEditorArea());

		layout.addShowViewShortcut(Status.ID);
		layout.addShowViewShortcut(CompilerOutput.ID);
		layout.addShowViewShortcut(TestOutput.ID);
		layout.addShowViewShortcut(Projects.ID);
		layout.addShowViewShortcut(Expressions.ID);

		IFolderLayout folder = layout.createFolder("Output", IPageLayout.BOTTOM, 0.70f, layout.getEditorArea());
		folder.addPlaceholder(Status.ID + ":*");
		folder.addPlaceholder(CompilerOutput.ID + ":*");
		folder.addPlaceholder(TestOutput.ID + ":*");
		folder.addPlaceholder(Projects.ID + ":*");
		folder.addPlaceholder(Expressions.ID + ":*");

		folder.addView(Status.ID);
		folder.addView(CompilerOutput.ID);
		folder.addView(TestOutput.ID);
	}
}
