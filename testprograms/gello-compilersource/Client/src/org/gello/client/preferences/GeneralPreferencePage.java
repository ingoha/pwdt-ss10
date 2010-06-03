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


package org.gello.client.preferences;

import java.io.IOException;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.gello.client.Application;

/**
 * A general preference's page.
 *
 */
// TODO Implement more preferences based off of this example preference.
public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	public static final String AUTO_LOGIN = "prefs_auto_login";

	private ScopedPreferenceStore preferences;

	public GeneralPreferencePage()
	{
		super(GRID);
		this.preferences = new ScopedPreferenceStore(new ConfigurationScope(), Application.PLUGIN_ID);
		setPreferenceStore(preferences);
	}

	public void init(IWorkbench workbench)
	{
	}

	protected void createFieldEditors()
	{
		BooleanFieldEditor boolEditor = new BooleanFieldEditor(AUTO_LOGIN, "Login automatically at startup", getFieldEditorParent());
		addField(boolEditor);
	}

	public boolean performOk()
	{
		try
		{
			preferences.save();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return super.performOk();
	}
}
