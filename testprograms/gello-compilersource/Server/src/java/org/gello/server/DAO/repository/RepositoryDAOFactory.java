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

public abstract class RepositoryDAOFactory
{
    public static final int SVN = 0;
    
    public abstract RepositoryDAO getRepositoryDAO(ConnectionDetails details);
    
    public static RepositoryDAOFactory getDAOFactory(int whichFactory)
    {
        if (whichFactory == SVN)
            return new SVNDAOFactory();
        else
            return null;
    }
    
}
