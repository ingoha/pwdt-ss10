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
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.gello.server.model.Project;
import org.gello.server.model.User;

public class PostgreSQLProjectDAO implements ProjectDAO
{
    public Project getProject(String projectName)
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        Project project = (Project) em.createNamedQuery("Project.findByName").setParameter("name", projectName).getSingleResult();
        
        et.commit();
        em.close();
        
        return project;
    }
    
    public Project updateProject(Project project)
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        Project updatedProject = (Project)em.merge(project);
        
        et.commit();
        em.close();
        
        return updatedProject;
    }
    
    public List<Project> getProjectList()
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        List<Project> projectList = em.createNamedQuery("Project.all").getResultList();
        
        et.commit();
        em.close();
        
        return projectList;
    }
    
    public boolean removeProject(Project project)
    {
        try
        {
            EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            
            em.remove(em.merge(project));
            
            et.commit();
            em.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
