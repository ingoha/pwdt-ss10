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


package org.gello.client.threads;

import java.util.ArrayList;

import org.gello.client.manager.Project;
import org.gello.client.model.CommonActions;
import org.gello.client.model.Session;
import org.gello.client.views.Browser;
import org.gello.client.views.CompilerOutput;

/**
 * A thread to update the UI and create output files
 * after a project is tested.
 *
 */
public class TestProjectThread implements Runnable
{
	Project project = null;
	ArrayList<String[]> output = null;

	public TestProjectThread(Project project, ArrayList<String[]> output)
	{
		this.project = project;
		this.output = output;
	}

	public void run()
	{
		String overallOutput = "";
		for (String[] s : output)
		{
			Project currentProject = Session.getInstance().getCurrentProject();
			if (currentProject != null && currentProject.getName().equals(project.getName()))
				CommonActions.getInstance().createTestOutputFile(s[0], s[1]);
			else
				CommonActions.getInstance().createTestOutputFileWithoutBrowser(project.getTemplateType(), s[0], s[1]);

			if (!overallOutput.equals(""))
				overallOutput += "\r\n--------------------\r\n\r\n";
			overallOutput += s[1];
		}

		CommonActions.getInstance().setOutput(CompilerOutput.ID, overallOutput);

		Browser.progressMonitor.done();
	}
}
