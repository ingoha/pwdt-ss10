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
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary Key class PrivilegePK for entity class Privilege
 * 
 */
@Embeddable
public class PrivilegePK implements Serializable {

    @Column(name = "securableobjectid", nullable = false)    
    private int securableobjectid;

    @Column(name = "accesstypeid", nullable = false)
    private int accesstypeid;

    @Column(name = "roleid", nullable = false)
    private int roleid;
    
    /** Creates a new instance of PrivilegePK */
    public PrivilegePK() {
    }

    /**
     * Creates a new instance of PrivilegePK with the specified values.
     * @param roleid the roleid of the PrivilegePK
     * @param accesstypeid the accesstypeid of the PrivilegePK
     * @param securableobjectid the securableobjectid of the PrivilegePK
     */
    public PrivilegePK(int roleid, int accesstypeid, int securableobjectid) {
        this.roleid = roleid;
        this.accesstypeid = accesstypeid;
        this.securableobjectid = securableobjectid;
    }

    /**
     * Gets the securableobjectid of this PrivilegePK.
     * @return the securableobjectid
     */
    public int getSecurableobjectid() {
        return this.securableobjectid;
    }

    /**
     * Sets the securableobjectid of this PrivilegePK to the specified value.
     * @param securableobjectid the new securableobjectid
     */
    public void setSecurableobjectid(int securableobjectid) {
        this.securableobjectid = securableobjectid;
    }

    /**
     * Gets the accesstypeid of this PrivilegePK.
     * @return the accesstypeid
     */
    public int getAccesstypeid() {
        return this.accesstypeid;
    }

    /**
     * Sets the accesstypeid of this PrivilegePK to the specified value.
     * @param accesstypeid the new accesstypeid
     */
    public void setAccesstypeid(int accesstypeid) {
        this.accesstypeid = accesstypeid;
    }

    /**
     * Gets the roleid of this PrivilegePK.
     * @return the roleid
     */
    public int getRoleid() {
        return this.roleid;
    }

    /**
     * Sets the roleid of this PrivilegePK to the specified value.
     * @param roleid the new roleid
     */
    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int)roleid;
        hash += (int)accesstypeid;
        hash += (int)securableobjectid;
        return hash;
    }

    /**
     * Determines whether another object is equal to this PrivilegePK.  The result is 
     * <code>true</code> if and only if the argument is not null and is a PrivilegePK object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivilegePK)) {
            return false;
        }
        PrivilegePK other = (PrivilegePK)object;
        if (this.roleid != other.roleid) return false;
        if (this.accesstypeid != other.accesstypeid) return false;
        if (this.securableobjectid != other.securableobjectid) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.PrivilegePK[roleid=" + roleid + ", accesstypeid=" + accesstypeid + ", securableobjectid=" + securableobjectid + "]";
    }
    
}
