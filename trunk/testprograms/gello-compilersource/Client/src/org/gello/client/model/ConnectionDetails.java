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

/**
 * A place to store connection details for a user.
 *
 */
public class ConnectionDetails
{
	private String username, server, password;

	public ConnectionDetails(String username, String server, String password)
	{
		this.username = username;
		this.server = server;
		this.password = password;
	}

	public String getUsername()
	{
		return username;
	}

	public String getServer()
	{
		return server;
	}

	public String getPassword()
	{
		return password;
	}
}
