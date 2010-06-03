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


package org.gello.server.DAO.database;

import java.util.List;
import org.gello.server.model.Project;

public interface ProjectDAO
{
    public Project getProject(String projectName);
    public Project updateProject(Project project);
    public List<Project> getProjectList();
    public boolean removeProject(Project project);    
}
