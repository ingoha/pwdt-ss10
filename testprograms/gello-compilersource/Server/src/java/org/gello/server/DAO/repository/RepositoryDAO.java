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


package org.gello.server.DAO.repository;

import org.gello.server.model.ConnectionDetails;
import org.gello.server.model.GelloNode;
import org.gello.server.model.Project;

public interface RepositoryDAO
{
    public boolean isLoggedIn();
    public void logout();
    public GelloNode getProjectContents(String projectName, boolean includeFileData);
    public String openFile(String path);
    public String checkoutFile(String path);
    public boolean checkinFile(String path, String contents);
    public boolean saveFile(String path, String contents);
    public String getVersionInfo(String path);
    public GelloNode addStructure(String template, String type, String path, String name);
    public boolean addStructure(GelloNode structure);
    public boolean remove(String path);
    public boolean move(String path, String newPath);
}
