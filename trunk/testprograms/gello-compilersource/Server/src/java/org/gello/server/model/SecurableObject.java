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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class SecurableObject
 * 
 */
@Entity
@Table(name = "securable_object")
@NamedQueries( {
        @NamedQuery(name = "SecurableObject.findBySecurableobjectid", query = "SELECT s FROM SecurableObject s WHERE s.securableobjectid = :securableobjectid"),
        @NamedQuery(name = "SecurableObject.findByName", query = "SELECT s FROM SecurableObject s WHERE s.name = :name")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class SecurableObject implements Serializable {

    @Id
    @Column(name = "securableobjectid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer securableobjectid;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "securableObject")
    private Collection<Privilege> privilegeCollection;

    @OneToMany(mappedBy = "actiononid")
    private Collection<Action> actionCollection;

    @JoinColumn(name = "securableobjecttypeid", referencedColumnName = "securableobjecttypeid")
    @ManyToOne
    private SecurableObjectType securableobjecttypeid;

    /** Creates a new instance of SecurableObject */
    public SecurableObject() {
    }

    /**
     * Creates a new instance of SecurableObject with the specified values.
     * @param securableobjectid the securableobjectid of the SecurableObject
     */
    public SecurableObject(Integer securableobjectid) {
        this.securableobjectid = securableobjectid;
    }

    /**
     * Creates a new instance of SecurableObject with the specified values.
     * @param securableobjectid the securableobjectid of the SecurableObject
     * @param name the name of the SecurableObject
     */
    public SecurableObject(Integer securableobjectid, String name) {
        this.securableobjectid = securableobjectid;
        this.name = name;
    }

    /**
     * Gets the securableobjectid of this SecurableObject.
     * @return the securableobjectid
     */
    public Integer getSecurableobjectid() {
        return this.securableobjectid;
    }

    /**
     * Sets the securableobjectid of this SecurableObject to the specified value.
     * @param securableobjectid the new securableobjectid
     */
    public void setSecurableobjectid(Integer securableobjectid) {
        this.securableobjectid = securableobjectid;
    }

    /**
     * Gets the name of this SecurableObject.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this SecurableObject to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the privilegeCollection of this SecurableObject.
     * @return the privilegeCollection
     */
    public Collection<Privilege> getPrivilegeCollection() {
        return this.privilegeCollection;
    }

    /**
     * Sets the privilegeCollection of this SecurableObject to the specified value.
     * @param privilegeCollection the new privilegeCollection
     */
    public void setPrivilegeCollection(Collection<Privilege> privilegeCollection) {
        this.privilegeCollection = privilegeCollection;
    }

    /**
     * Gets the actionCollection of this SecurableObject.
     * @return the actionCollection
     */
    public Collection<Action> getActionCollection() {
        return this.actionCollection;
    }

    /**
     * Sets the actionCollection of this SecurableObject to the specified value.
     * @param actionCollection the new actionCollection
     */
    public void setActionCollection(Collection<Action> actionCollection) {
        this.actionCollection = actionCollection;
    }

    /**
     * Gets the securableobjecttypeid of this SecurableObject.
     * @return the securableobjecttypeid
     */
    public SecurableObjectType getSecurableobjecttypeid() {
        return this.securableobjecttypeid;
    }

    /**
     * Sets the securableobjecttypeid of this SecurableObject to the specified value.
     * @param securableobjecttypeid the new securableobjecttypeid
     */
    public void setSecurableobjecttypeid(SecurableObjectType securableobjecttypeid) {
        this.securableobjecttypeid = securableobjecttypeid;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.securableobjectid != null ? this.securableobjectid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this SecurableObject.  The result is 
     * <code>true</code> if and only if the argument is not null and is a SecurableObject object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecurableObject)) {
            return false;
        }
        SecurableObject other = (SecurableObject)object;
        if (this.securableobjectid != other.securableobjectid && (this.securableobjectid == null || !this.securableobjectid.equals(other.securableobjectid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.SecurableObject[securableobjectid=" + securableobjectid + "]";
    }

}
