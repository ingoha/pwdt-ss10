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
import org.gello.server.model.PhoneNumber;
import org.gello.server.model.User;

public class PostgreSQLUserDAO implements UserDAO
{
    public User getUser(String username)
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        User user = (User) em.createNamedQuery("User.findByUserName").setParameter("userName", username).getSingleResult();
        
        et.commit();
        em.close();
        
        return user;
    }
    
    public User updateUser(User user)
    {
            EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();

            User updatedUser = null;
            
            if (user.getId() == null)
            {
                Set<EmailAddress> emailAddresses = user.getEmailAddresses();
                user.setEmailAddresses(null);
                Set<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
                user.setPhoneNumbers(null);
                
                updatedUser = (User)em.merge(user);
                
                for (EmailAddress e : emailAddresses)
                    e.setPartyId(updatedUser.getId());
                for (PhoneNumber p : phoneNumbers)
                    p.setPartyId(updatedUser.getId());
                
                updatedUser.setEmailAddresses(emailAddresses);
                updatedUser.setPhoneNumbers(phoneNumbers);
                                
                updatedUser = (User)em.merge(updatedUser);
            }
            else
                updatedUser = (User)em.merge(user);

            et.commit();
            em.close();
            
            return updatedUser;
    }
    
    public List<User> getUserList()
    {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        
        List<User> userList = em.createNamedQuery("User.all").getResultList();
        
        et.commit();
        em.close();
        
        return userList;
    }
    
    public boolean removeUser(User user)
    {
        try
        {
            EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction et = em.getTransaction();
            et.begin();
            
            em.remove(em.merge(user));

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
