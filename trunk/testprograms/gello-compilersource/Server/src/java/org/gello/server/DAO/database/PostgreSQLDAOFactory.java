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

public class PostgreSQLDAOFactory extends DatabaseDAOFactory
{
    public UserDAO getUserDAO()
    {
        return new PostgreSQLUserDAO();
    }
    
    public ResourceDAO getResourceDAO()
    {
        return new PostgreSQLResourceDAO();
    }
    
    public OrganizationDAO getOrganizationDAO()
    {
        return new PostgreSQLOrganizationDAO();
    }
    
    public ProjectDAO getProjectDAO()
    {
        return new PostgreSQLProjectDAO();
    }
    
}
