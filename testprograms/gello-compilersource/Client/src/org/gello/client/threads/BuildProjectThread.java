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

import org.gello.client.manager.GelloNode;
import org.gello.client.model.CommonActions;
import org.gello.client.views.Browser;
import org.gello.client.views.CompilerOutput;

/**
 * A thread to update the UI after a project is built.
 *
 */
public class BuildProjectThread implements Runnable
{
	GelloNode gelloNode = null;
	ArrayList<String> output = null;

	public BuildProjectThread(GelloNode gelloNode, ArrayList<String> output)
	{
		this.gelloNode = gelloNode;
		this.output = output;
	}

	public void run()
	{
		String overallOutput = "";
		for (String s : output)
		{
			if (!overallOutput.equals(""))
				overallOutput += "\r\n--------------------\r\n\r\n";
			overallOutput += s;
		}

		CommonActions.getInstance().setOutput(CompilerOutput.ID, overallOutput);

		Browser.progressMonitor.done();
	}
}
