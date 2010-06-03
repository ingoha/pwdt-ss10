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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.gello.server.connectors.SVNConnector;
import org.gello.server.model.ConnectionDetails;
import org.gello.server.model.GelloNode;
import org.gello.server.model.PropertyLoader;
import org.gello.server.model.Util;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.io.SVNRepository;

public class SVNRepositoryDAO implements RepositoryDAO
{
    SVNConnector svn = null;
    String server = null;
    ConnectionDetails details = null;
    SVNRepository repository = null;
    boolean isLoggedIn = false;
    
    public SVNRepositoryDAO(ConnectionDetails details)
    {
        Properties properties = PropertyLoader.loadProperties("SVN.properties");
        this.server = properties.getProperty("SVNServer", "https://localhost/svn/gello/");
        this.details = details;
        this.repository = SVNRepositoryConnection.getConnection(details, server);
        this.isLoggedIn = (repository != null);
        this.svn = new SVNConnector(repository);
    }
    
    public GelloNode getProjectContents(String projectName, boolean includeFileData)
    {
        return svn.getSVNList(projectName, includeFileData);
    }
    
    public boolean isLoggedIn()
    {
        return isLoggedIn;
    }
    
    public void logout()
    {
        SVNRepositoryConnection.closeConnection(details);
    }
    
    public String openFile(String path)
    {
        return svn.getFile(path);
    }
    
    public String checkoutFile(String path)
    {
        boolean success = svn.lock(path, false);
        if (!success)
            return null;
        return svn.getFile(path);
    }
    
    public boolean checkinFile(String path, String contents)
    {
        SVNCommitInfo commitInfo = svn.modifyFile(path, path + " was checked in.", contents);
        svn.unlock(path, false);
        return (commitInfo != null);
    }
    
    public boolean saveFile(String path, String contents)
    {
        SVNCommitInfo commitInfo = svn.modifyFile(path, path + " was checked in.", contents);
        return (commitInfo != null);
    }
    
    public String getVersionInfo(String path)
    {
        return svn.getVersionInfo(path);
    }
    
    public GelloNode addStructure(String template, String type, String path, String name)
    {
        GelloNode structure = Util.readXMLTemplate(template, type);
        
        String nodeName = type;
        int nodeNumber = 1;
        while (svn.checkPath(path + "/" + nodeName + " " + nodeNumber))
            nodeNumber++;
        
        Util.removeTemplateMetaChars(structure);
        Util.changeNodeName(structure, (name != null) ? name : (nodeName + " " + nodeNumber));
        Util.prefixPaths(structure, path);
        SVNCommitInfo commitInfo = svn.addGelloNodeStructure(structure, structure.getPath() + " was added.");
        if (commitInfo == null)
            structure = null;
        return (structure);
    }
    
    public boolean addStructure(GelloNode structure)
    {
        SVNCommitInfo commitInfo = svn.addGelloNodeStructure(structure, structure.getPath() + " was added.");
        return (commitInfo != null);
    }
    
    public boolean remove(String path)
    {
        SVNCommitInfo commitInfo = svn.delete(path, path + " was removed.");
        return (commitInfo != null);
    }
    
    public boolean move(String path, String newPath)
    {
        SVNCommitInfo commitInfo = svn.moveFile(path, newPath, path + " was moved to " + newPath + ".");
        return (commitInfo != null);
    }
    
}
