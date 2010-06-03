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
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.gello.server.model.EmailAddress;
import org.gello.server.model.Organization;
import org.gello.server.model.PhoneNumber;

public class PostgreSQLOrganizationDAO implements OrganizationDAO
{
    public Organization getOrganization(String organizationName)
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        Organization organization = (Organization) em.createNamedQuery("Organization.findByName").setParameter("name", organizationName).getSingleResult();
        
        et.commit();
        em.close();
        
        return organization;
    }
    
    public Organization updateOrganization(Organization organization)
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        Organization updatedOrganization = null;
        
        if (organization.getId() == null)
        {
            Set<EmailAddress> emailAddresses = organization.getEmailAddresses();
            organization.setEmailAddresses(null);
            Set<PhoneNumber> phoneNumbers = organization.getPhoneNumbers();
            organization.setPhoneNumbers(null);

            updatedOrganization = (Organization)em.merge(organization);

            for (EmailAddress e : emailAddresses)
                e.setPartyId(updatedOrganization.getId());
            for (PhoneNumber p : phoneNumbers)
                p.setPartyId(updatedOrganization.getId());

            updatedOrganization.setEmailAddresses(emailAddresses);
            updatedOrganization.setPhoneNumbers(phoneNumbers);

            updatedOrganization = (Organization)em.merge(updatedOrganization);
        }
        else
            updatedOrganization = (Organization)em.merge(organization);
        
        et.commit();
        em.close();
        
        return updatedOrganization;
    }
    
    public List<Organization> getOrganizationList()
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        List<Organization> organizationList = em.createNamedQuery("Organization.all").getResultList();
        
        et.commit();
        em.close();
        
        return organizationList;
    }
    
    public boolean removeOrganization(Organization organization)
    {
        try
        {
            EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            
            em.remove(em.merge(organization));
            
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
