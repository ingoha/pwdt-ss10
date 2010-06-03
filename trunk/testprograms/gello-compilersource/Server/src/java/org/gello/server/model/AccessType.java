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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class AccessType
 */
@Entity
@Table(name = "access_type")
@NamedQueries( {
        @NamedQuery(name = "AccessType.findByAccesstypeid", query = "SELECT a FROM AccessType a WHERE a.accesstypeid = :accesstypeid"),
        @NamedQuery(name = "AccessType.findByName", query = "SELECT a FROM AccessType a WHERE a.name = :name"),
        @NamedQuery(name = "AccessType.findByDescr", query = "SELECT a FROM AccessType a WHERE a.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class AccessType implements Serializable {

    @Id
    @Column(name = "accesstypeid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer accesstypeid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accessType")
    private Collection<Privilege> privilegeCollection;

    /** Creates a new instance of AccessType */
    public AccessType() {
    }

    /**
     * Creates a new instance of AccessType with the specified values.
     * @param accesstypeid the accesstypeid of the AccessType
     */
    public AccessType(Integer accesstypeid) {
        this.accesstypeid = accesstypeid;
    }

    /**
     * Creates a new instance of AccessType with the specified values.
     * @param accesstypeid the accesstypeid of the AccessType
     * @param name the name of the AccessType
     */
    public AccessType(Integer accesstypeid, String name) {
        this.accesstypeid = accesstypeid;
        this.name = name;
    }

    /**
     * Gets the accesstypeid of this AccessType.
     * @return the accesstypeid
     */
    public Integer getAccesstypeid() {
        return this.accesstypeid;
    }

    /**
     * Sets the accesstypeid of this AccessType to the specified value.
     * @param accesstypeid the new accesstypeid
     */
    public void setAccesstypeid(Integer accesstypeid) {
        this.accesstypeid = accesstypeid;
    }

    /**
     * Gets the name of this AccessType.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this AccessType to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descr of this AccessType.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this AccessType to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the privilegeCollection of this AccessType.
     * @return the privilegeCollection
     */
    public Collection<Privilege> getPrivilegeCollection() {
        return this.privilegeCollection;
    }

    /**
     * Sets the privilegeCollection of this AccessType to the specified value.
     * @param privilegeCollection the new privilegeCollection
     */
    public void setPrivilegeCollection(Collection<Privilege> privilegeCollection) {
        this.privilegeCollection = privilegeCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.accesstypeid != null ? this.accesstypeid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this AccessType.  The result is 
     * <code>true</code> if and only if the argument is not null and is a AccessType object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessType)) {
            return false;
        }
        AccessType other = (AccessType)object;
        if (this.accesstypeid != other.accesstypeid && (this.accesstypeid == null || !this.accesstypeid.equals(other.accesstypeid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.AccessType[accesstypeid=" + accesstypeid + "]";
    }

}
