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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;

/**
 * Entity class Privilege
 * 
 */
@Entity
@Table(name = "privilege")
@NamedQueries( {
        @NamedQuery(name = "Privilege.findBySecurableobjectid", query = "SELECT p FROM Privilege p WHERE p.privilegePK.securableobjectid = :securableobjectid"),
        @NamedQuery(name = "Privilege.findByAccesstypeid", query = "SELECT p FROM Privilege p WHERE p.privilegePK.accesstypeid = :accesstypeid"),
        @NamedQuery(name = "Privilege.findByRoleid", query = "SELECT p FROM Privilege p WHERE p.privilegePK.roleid = :roleid")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Privilege implements Serializable {

    /**
     * EmbeddedId primary key field
     */
    @EmbeddedId
    protected PrivilegePK privilegePK;

    @JoinColumn(name = "accesstypeid", referencedColumnName = "accesstypeid", insertable = false, updatable = false)
    @ManyToOne
    private AccessType accessType;

    @JoinColumn(name = "roleid", referencedColumnName = "roleid", insertable = false, updatable = false)
    @ManyToOne
    private Role role;

    @JoinColumn(name = "securableobjectid", referencedColumnName = "securableobjectid", insertable = false, updatable = false)
    @ManyToOne
    private SecurableObject securableObject;

    /** Creates a new instance of Privilege */
    public Privilege() {
    }

    /**
     * Creates a new instance of Privilege with the specified values.
     * @param privilegePK the privilegePK of the Privilege
     */
    public Privilege(PrivilegePK privilegePK) {
        this.privilegePK = privilegePK;
    }

    /**
     * Creates a new instance of PrivilegePK with the specified values.
     * @param roleid the roleid of the PrivilegePK
     * @param accesstypeid the accesstypeid of the PrivilegePK
     * @param securableobjectid the securableobjectid of the PrivilegePK
     */
    public Privilege(int roleid, int accesstypeid, int securableobjectid) {
        this.privilegePK = new PrivilegePK(roleid, accesstypeid, securableobjectid);
    }

    /**
     * Gets the privilegePK of this Privilege.
     * @return the privilegePK
     */
    public PrivilegePK getPrivilegePK() {
        return this.privilegePK;
    }

    /**
     * Sets the privilegePK of this Privilege to the specified value.
     * @param privilegePK the new privilegePK
     */
    public void setPrivilegePK(PrivilegePK privilegePK) {
        this.privilegePK = privilegePK;
    }

    /**
     * Gets the accessType of this Privilege.
     * @return the accessType
     */
    public AccessType getAccessType() {
        return this.accessType;
    }

    /**
     * Sets the accessType of this Privilege to the specified value.
     * @param accessType the new accessType
     */
    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    /**
     * Gets the role of this Privilege.
     * @return the role
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Sets the role of this Privilege to the specified value.
     * @param role the new role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the securableObject of this Privilege.
     * @return the securableObject
     */
    public SecurableObject getSecurableObject() {
        return this.securableObject;
    }

    /**
     * Sets the securableObject of this Privilege to the specified value.
     * @param securableObject the new securableObject
     */
    public void setSecurableObject(SecurableObject securableObject) {
        this.securableObject = securableObject;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.privilegePK != null ? this.privilegePK.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Privilege.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Privilege object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege)object;
        if (this.privilegePK != other.privilegePK && (this.privilegePK == null || !this.privilegePK.equals(other.privilegePK))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.Privilege[privilegePK=" + privilegePK + "]";
    }

}
