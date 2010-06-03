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
 * Entity class SecurableObjectType
 * 
 */
@Entity
@Table(name = "securable_object_type")
@NamedQueries( {
        @NamedQuery(name = "SecurableObjectType.findBySecurableobjecttypeid", query = "SELECT s FROM SecurableObjectType s WHERE s.securableobjecttypeid = :securableobjecttypeid"),
        @NamedQuery(name = "SecurableObjectType.findByName", query = "SELECT s FROM SecurableObjectType s WHERE s.name = :name"),
        @NamedQuery(name = "SecurableObjectType.findByDescr", query = "SELECT s FROM SecurableObjectType s WHERE s.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class SecurableObjectType implements Serializable {

    @Id
    @Column(name = "securableobjecttypeid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer securableobjecttypeid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "securableobjecttypeid")
    private Collection<SecurableObject> securableObjectCollection;

    /** Creates a new instance of SecurableObjectType */
    public SecurableObjectType() {
    }

    /**
     * Creates a new instance of SecurableObjectType with the specified values.
     * @param securableobjecttypeid the securableobjecttypeid of the SecurableObjectType
     */
    public SecurableObjectType(Integer securableobjecttypeid) {
        this.securableobjecttypeid = securableobjecttypeid;
    }

    /**
     * Creates a new instance of SecurableObjectType with the specified values.
     * @param securableobjecttypeid the securableobjecttypeid of the SecurableObjectType
     * @param name the name of the SecurableObjectType
     */
    public SecurableObjectType(Integer securableobjecttypeid, String name) {
        this.securableobjecttypeid = securableobjecttypeid;
        this.name = name;
    }

    /**
     * Gets the securableobjecttypeid of this SecurableObjectType.
     * @return the securableobjecttypeid
     */
    public Integer getSecurableobjecttypeid() {
        return this.securableobjecttypeid;
    }

    /**
     * Sets the securableobjecttypeid of this SecurableObjectType to the specified value.
     * @param securableobjecttypeid the new securableobjecttypeid
     */
    public void setSecurableobjecttypeid(Integer securableobjecttypeid) {
        this.securableobjecttypeid = securableobjecttypeid;
    }

    /**
     * Gets the name of this SecurableObjectType.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this SecurableObjectType to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descr of this SecurableObjectType.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this SecurableObjectType to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the securableObjectCollection of this SecurableObjectType.
     * @return the securableObjectCollection
     */
    public Collection<SecurableObject> getSecurableObjectCollection() {
        return this.securableObjectCollection;
    }

    /**
     * Sets the securableObjectCollection of this SecurableObjectType to the specified value.
     * @param securableObjectCollection the new securableObjectCollection
     */
    public void setSecurableObjectCollection(Collection<SecurableObject> securableObjectCollection) {
        this.securableObjectCollection = securableObjectCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.securableobjecttypeid != null ? this.securableobjecttypeid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this SecurableObjectType.  The result is 
     * <code>true</code> if and only if the argument is not null and is a SecurableObjectType object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecurableObjectType)) {
            return false;
        }
        SecurableObjectType other = (SecurableObjectType)object;
        if (this.securableobjecttypeid != other.securableobjecttypeid && (this.securableobjecttypeid == null || !this.securableobjecttypeid.equals(other.securableobjecttypeid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.SecurableObjectType[securableobjecttypeid=" + securableobjecttypeid + "]";
    }

}
