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

import org.gello.client.manager.Project;

/**
 * The current session of the client.  Stores user and project information.
 *
 */
public class Session
{
	private ConnectionDetails connectionDetails;
	private static Session INSTANCE;
	private boolean authenticated = false;
	Project project;

	public static Session getInstance()
	{
		if (INSTANCE == null)
			INSTANCE = new Session();
		return INSTANCE;
	}

	public ConnectionDetails getConnectionDetails()
	{
		return connectionDetails;
	}

	public void setConnectionDetails(ConnectionDetails connectionDetails)
	{
		this.connectionDetails = connectionDetails;
	}

	public void setAuthenticated(boolean authenticated)
	{
		this.authenticated = authenticated;
	}

	public boolean isAuthenticated()
	{
		return authenticated;
	}

	public Project getCurrentProject()
	{
		return project;
	}

	public void setCurrentProject(Project project)
	{
		this.project = project;
	}
}
