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


package org.gello.server.manager;

import java.util.List;
import java.util.Properties;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;
import org.gello.server.DAO.database.DatabaseDAOFactory;
import org.gello.server.DAO.repository.RepositoryDAO;
import org.gello.server.DAO.repository.RepositoryDAOFactory;
import org.gello.server.model.ConnectionDetails;
import org.gello.server.model.GelloNode;
import org.gello.server.model.User;
import org.gello.server.model.Organization;
import org.gello.server.model.Project;
import org.gello.server.model.PropertyLoader;
import org.gello.server.model.Resource;
import org.gello.server.model.ServerException;

@WebService()
public class Manager
{
    @javax.annotation.Resource
    private WebServiceContext wsContext;
    
    @WebMethod
    public boolean login(@WebParam(name = "username")String username, @WebParam(name = "password")String password) throws ServerException
    {
        HttpSession session = getSession();
        
        ConnectionDetails details = new ConnectionDetails(username, password);
        RepositoryDAO repositoryDAO = RepositoryDAOFactory.getDAOFactory(RepositoryDAOFactory.SVN).getRepositoryDAO(details);
        
        if (!repositoryDAO.isLoggedIn())
            return false;
        
        session.setAttribute("connectionDetails", details);
        session.setAttribute("loggedIn", new Boolean(true));
        return true;
    }
    
    @WebMethod
    public void logout() throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        repositoryDAO.logout();
        session.invalidate();
    }
    
    @WebMethod
    public Project getProject(@WebParam(name = "projectName")String projectName, @WebParam(name = "includeFileData")boolean includeFileData) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        Project project = getDatabaseDAOFactory().getProjectDAO().getProject(projectName);
        GelloNode projectContents = repositoryDAO.getProjectContents(projectName, includeFileData);
        
        project.buildContents(projectContents);
        
        return project;
    }
    
    @WebMethod
    public String openFile(@WebParam(name = "path")String path) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        return repositoryDAO.openFile(path);
    }

    @WebMethod
    public String checkoutFile(@WebParam(name = "path")String path) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        return repositoryDAO.checkoutFile(path);
    }

    @WebMethod
    public boolean checkinFile(@WebParam(name = "path")String path, @WebParam(name = "contents")String contents) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        return repositoryDAO.checkinFile(path, contents);
    }

    @WebMethod
    public boolean saveFile(@WebParam(name = "path")String path, @WebParam(name = "contents")String contents) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        return repositoryDAO.saveFile(path, contents);
    }

    @WebMethod
    public String getVersionInfo(@WebParam(name = "path")String path) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        return repositoryDAO.getVersionInfo(path);
    }

    @WebMethod
    public GelloNode addStructure(@WebParam(name = "template")String template, @WebParam(name = "type")String type, @WebParam(name = "path")String path, @WebParam(name = "name")String name) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        return repositoryDAO.addStructure(template, type, path, name);
    }
    
    @WebMethod
    public Project addProject(@WebParam(name = "project")Project project) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        // For adding a new project
        if (project.getContents() == null)
        {
            GelloNode gelloNode = repositoryDAO.addStructure(project.getTemplateType(), "Project", "/", project.getName());

            if (gelloNode == null)
                return null;

        }
        // For importing a project
        else
        {
            boolean added = repositoryDAO.addStructure(project.getContents());
            if (!added)
                return null;
        }
        
        Project p = getDatabaseDAOFactory().getProjectDAO().updateProject(project);
        
        return (p);
    }
    
    @WebMethod
    public boolean removeProject(@WebParam(name = "project")Project project) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        boolean repoRemove = repositoryDAO.remove("/" + project.getName());
        
        if (repoRemove)
            return getDatabaseDAOFactory().getProjectDAO().removeProject(project);
        
        return false;
    }
    
    @WebMethod
    public boolean remove(@WebParam(name = "path")String path) throws ServerException
    {
        HttpSession session = getSession();
        RepositoryDAO repositoryDAO = getSessionRepositoryDAO(session);
        
        return repositoryDAO.remove(path);
    }
    
    @WebMethod
    public User getUser(@WebParam(name = "username")String username) throws ServerException
    {
        return getDatabaseDAOFactory().getUserDAO().getUser(username);        
    }
    
    @WebMethod
    public User updateUser(@WebParam(name = "user")User user) throws ServerException
    {
        return getDatabaseDAOFactory().getUserDAO().updateUser(user);
    }
    
    @WebMethod
    public boolean removeUser(@WebParam(name = "user")User user) throws ServerException
    {
        return getDatabaseDAOFactory().getUserDAO().removeUser(user);
    }
    
    @WebMethod
    public List<User> getUserList() throws ServerException
    {
        return getDatabaseDAOFactory().getUserDAO().getUserList();
    }

    @WebMethod
    public Organization getOrganization(@WebParam(name = "organizationName")String organizationName) throws ServerException
    {
        return getDatabaseDAOFactory().getOrganizationDAO().getOrganization(organizationName);        
    }
    
    @WebMethod
    public Organization updateOrganization(@WebParam(name = "organization")Organization organization) throws ServerException
    {
        return getDatabaseDAOFactory().getOrganizationDAO().updateOrganization(organization);
    }
    
    @WebMethod
    public boolean removeOrganization(@WebParam(name = "organization")Organization organization) throws ServerException
    {
        return getDatabaseDAOFactory().getOrganizationDAO().removeOrganization(organization);
    }
    
    @WebMethod
    public List<Organization> getOrganizationList() throws ServerException
    {
        return getDatabaseDAOFactory().getOrganizationDAO().getOrganizationList();
    }
    
    @WebMethod
    public List<Project> getProjectList() throws ServerException
    {
        return getDatabaseDAOFactory().getProjectDAO().getProjectList();
    }
    
    @WebMethod
    public List<Resource> getResourceList() throws ServerException
    {
        return getDatabaseDAOFactory().getResourceDAO().getResourceList();
    }
    
    @WebMethod
    public String getFisheyeURL() throws ServerException
    {
        Properties properties = PropertyLoader.loadProperties("SVN.properties");
        return properties.getProperty("FisheyeURL", "https://gello.org:8081");
    }

    // Private helper methods
    
    private DatabaseDAOFactory getDatabaseDAOFactory()
    {
        return DatabaseDAOFactory.getDAOFactory(DatabaseDAOFactory.POSTGRESQL);
    }
    
    private RepositoryDAO getSessionRepositoryDAO(HttpSession session) throws ServerException
    {   
        if (!isLoggedIn(session))
            throw new ServerException("Not Authorized");
        
        ConnectionDetails details = (ConnectionDetails)session.getAttribute("connectionDetails");
        RepositoryDAO repositoryDAO = RepositoryDAOFactory.getDAOFactory(RepositoryDAOFactory.SVN).getRepositoryDAO(details);
        return repositoryDAO;
    }
    
    private HttpSession getSession()
    {
        MessageContext mc = wsContext.getMessageContext();
        HttpSession session = ((HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
        if (session == null)
            throw new WebServiceException("No session in WebServiceContext");  
        return session;
    }
    
    private boolean isLoggedIn(HttpSession session)
    {
        Boolean loggedIn = (Boolean)session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn.booleanValue())
            return true;
        
        return false;
    }
    
}
