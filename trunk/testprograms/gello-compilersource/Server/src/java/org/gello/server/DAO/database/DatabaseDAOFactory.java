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

public abstract class DatabaseDAOFactory
{
    public static final int POSTGRESQL = 0;
    
    public abstract UserDAO getUserDAO();
    public abstract ResourceDAO getResourceDAO();
    public abstract OrganizationDAO getOrganizationDAO();
    public abstract ProjectDAO getProjectDAO();
    
    public static DatabaseDAOFactory getDAOFactory(int whichFactory)
    {
        if (whichFactory == POSTGRESQL)
            return new PostgreSQLDAOFactory();
        else
            return null;
    }
    
}
