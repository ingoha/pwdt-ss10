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

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class Role
 * 
 */
@Entity
@Table(name = "role")
@NamedQueries( {
        @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :roleid"),
        @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name"),
        @NamedQuery(name = "Role.findByDescr", query = "SELECT r FROM Role r WHERE r.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Role implements Serializable {

    @Id
    @Column(name = "roleid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Collection<Privilege> privileges;

    /** Creates a new instance of Role */
    public Role() {
    }

    /**
     * Creates a new instance of Role with the specified values.
     * @param roleid the roleid of the Role
     */
    public Role(Integer roleid) {
        this.id = roleid;
    }

    /**
     * Creates a new instance of Role with the specified values.
     * @param roleid the roleid of the Role
     * @param name the name of the Role
     */
    public Role(Integer roleid, String name) {
        this.id = roleid;
        this.name = name;
    }

    /**
     * Gets the roleid of this Role.
     * @return the roleid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the roleid of this Role to the specified value.
     * @param id the new roleid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of this Role.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this Role to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descr of this Role.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this Role to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the useridCollection of this Role.
     * @return the useridCollection
     */
    public Collection<User> getUsers() {
        return this.users;
    }

    /**
     * Sets the useridCollection of this Role to the specified value.
     * @param users the new useridCollection
     */
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    /**
     * Gets the privilegeCollection of this Role.
     * @return the privilegeCollection
     */
    public Collection<Privilege> getPrivileges() {
        return this.privileges;
    }

    /**
     * Sets the privilegeCollection of this Role to the specified value.
     * @param privileges the new privilegeCollection
     */
    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Role.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Role object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", users=" + users +
                ", privileges=" + privileges +
                '}';
    }
}
