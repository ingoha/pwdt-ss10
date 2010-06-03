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


package org.gello.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class Guser
 *
 */
@Entity
@Table(name = "guser")
@NamedQueries({
@NamedQuery(name = "User.findById", query = "SELECT g FROM User g WHERE g.id = :userId"),
@NamedQuery(name = "User.findByUserName", query = "SELECT g FROM User g WHERE g.userName = :userName"),
@NamedQuery(name = "User.findByPassword", query = "SELECT g FROM User g WHERE g.password = :password"),
@NamedQuery(name = "User.all", query = "SELECT g FROM User g")
        })
@PrimaryKeyJoinColumn(name = "userid", referencedColumnName = "partyid")
public class User extends Party implements Serializable {

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "jobtitle")
    private String jobTitle;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "company")
    private String company;

    @JoinTable(name = "user_role", joinColumns = {
    @JoinColumn(name = "userid", referencedColumnName = "userid")
            }, inverseJoinColumns = {
    @JoinColumn(name = "roleid", referencedColumnName = "roleid")
            })
    @ManyToMany(fetch = FetchType.EAGER)    
    private Set<Role> roles = new HashSet<Role>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<UserPreference> userPreferences = new HashSet<UserPreference>();

    /**
     * Creates a new instance of Guser
     */
    public User() {
    }

    /**
     * Gets the username of this Guser.
     *
     * @return the username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the username of this Guser to the specified value.
     *
     * @param userName the new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password of this Guser.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of this Guser to the specified value.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the jobtitle of this Guser.
     *
     * @return the jobtitle
     */
    public String getJobTitle() {
        return this.jobTitle;
    }

    /**
     * Sets the jobtitle of this Guser to the specified value.
     *
     * @param jobTitle the new jobtitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Gets the roleidCollection of this Guser.
     *
     * @return the roleidCollection
     */
    public Set<RoleType> getRoles() {
        initializeRolesIfNull();
        Set<RoleType> c = new HashSet<RoleType>();
        for (Role r : roles) {
            c.add(RoleType.getRoleTypeFromRole(r));
        }
        return c;
    }

    /**
     * Sets the roleidCollection of this Guser to the specified value.
     *
     * @param roles the new roleidCollection
     */
    public void setRoles(Set<RoleType> roles) {
        initializeRolesIfNull();
        this.roles.clear();
        for (RoleType rt : roles) {
            this.roles.add(rt.getRole());
        }
    }

    private void initializeRolesIfNull() {
        if (roles == null) {
            roles = new HashSet<Role>();
        }
    }

    /**
     * Gets the userPreferenceCollection of this Guser.
     *
     * @return the userPreferenceCollection
     */
    public Set<UserPreference> getUserPreferences() {
        return this.userPreferences;
    }

    /**
     * Sets the userPreferenceCollection of this Guser to the specified value.
     *
     * @param userPreferences the new userPreferenceCollection
     */
    public void setUserPreferences(Set<UserPreference> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes
     * a hash code value based on the id fields in this object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Guser.  The result is
     * <code>true</code> if and only if the argument is not null and is a Guser object that
     * has the same id field values as this object.
     *
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs
     * that representation based on the id fields.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", roles=" + roles +
                ", userPreferences=" + userPreferences +
                '}';
    }
}
