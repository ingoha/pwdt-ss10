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

import org.gello.client.manager.GelloNode;
import org.gello.client.views.Browser;

/**
 * A thread to update the UI after a project is opened.
 *
 */
public class OpenProjectThread implements Runnable
{

	GelloNode gelloNode = null;

	public OpenProjectThread(GelloNode gelloNode)
	{
		this.gelloNode = gelloNode;
	}

	public void run()
	{
		Browser.projectName.setText(gelloNode.getName());
		Browser.viewer.setSelection(null);
		Browser.viewer.setInput(gelloNode);
		Browser.viewer.expandAll();
		Browser.viewer.reveal(gelloNode.getChildren().get(0));

		Browser.progressMonitor.done();
	}
}
