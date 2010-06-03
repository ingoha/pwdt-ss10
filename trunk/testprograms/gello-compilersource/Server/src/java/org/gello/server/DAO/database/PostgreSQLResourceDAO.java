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

import org.gello.server.model.Resource;

public class PostgreSQLResourceDAO implements ResourceDAO
{
    public List<Resource> getResourceList()
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        List<Resource> resourceList = em.createNamedQuery("Resource.all").getResultList();
        
        et.commit();
        em.close();
        
        return resourceList;
    }
}
