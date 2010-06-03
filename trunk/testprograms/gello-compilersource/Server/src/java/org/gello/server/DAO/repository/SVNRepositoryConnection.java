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

import java.util.HashMap;
import org.gello.server.connectors.SVNConnector;
import org.gello.server.model.ConnectionDetails;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepository;

public class SVNRepositoryConnection {
    
    private static HashMap<String, SVNRepository> connections = new HashMap<String, SVNRepository>();
    
    public static SVNRepository getConnection(ConnectionDetails details, String server)
    {
        SVNRepository repository = connections.get(details.getUsername());
        if (repository == null)
        {
            repository = SVNConnector.login(server, details.getUsername(), details.getPassword());
            connections.put(details.getUsername(), repository);
        }
        
        return repository;
    }
    
    public static void closeConnection(ConnectionDetails details)
    {
        SVNRepository repository = connections.get(details.getUsername());
        try
        {
            repository.closeSession();
        } catch (SVNException ex)
        {
            ex.printStackTrace();
        }
        if (connections.containsKey(details.getUsername()))
            connections.remove(details.getUsername());
    }
    
}
