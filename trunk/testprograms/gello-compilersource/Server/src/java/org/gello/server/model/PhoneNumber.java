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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class PhoneNumber
 *
 */
@Entity
@Table(name = "phone_number")
@NamedQueries( {
        @NamedQuery(name = "PhoneNumber.findById", query = "SELECT p FROM PhoneNumber p WHERE p.id = :phonenumberid"),
        @NamedQuery(name = "PhoneNumber.findByValue", query = "SELECT p FROM PhoneNumber p WHERE p.value = :value")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class PhoneNumber implements Serializable {

    @Id
    @Column(name = "phonenumberid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer id;

    @Column(name = "value", nullable = false)
    private String value;

    private Integer partyId;

    @Column(name = "phonenumbertypeid")
    private Integer phoneNumberType;

    /** Creates a new instance of PhoneNumber */
    public PhoneNumber() {
    }

    /**
     * Creates a new instance of PhoneNumber with the specified values.
     * @param phonenumberid the phonenumberid of the PhoneNumber
     */
    public PhoneNumber(Integer phonenumberid) {
        this.id = phonenumberid;
    }

    /**
     * Creates a new instance of PhoneNumber with the specified values.
     * @param phonenumberid the phonenumberid of the PhoneNumber
     * @param value the value of the PhoneNumber
     */
    public PhoneNumber(Integer phonenumberid, String value) {
        this.id = phonenumberid;
        this.value = value;
    }

    /**
     * Gets the phonenumberid of this PhoneNumber.
     * @return the phonenumberid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the phonenumberid of this PhoneNumber to the specified value.
     * @param id the new phonenumberid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the value of this PhoneNumber.
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value of this PhoneNumber to the specified value.
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the partyid of this PhoneNumber.
     * @return the partyid
     */
    public Integer getPartyId() {
        return this.partyId;
    }

    /**
     * Sets the partyid of this PhoneNumber to the specified value.
     * @param partyId the new partyid
     */
    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    /**
     * Gets the phonenumbertypeid of this PhoneNumber.
     * @return the phonenumbertypeid
     */
    public PhoneNumberType getPhoneNumberType() {
        return PhoneNumberType.getTypeFromKey(this.phoneNumberType);
    }

    /**
     * Sets the phonenumbertypeid of this PhoneNumber to the specified value.
     * @param phoneNumberType the new phonenumbertypeid
     */
    public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType.getDbKey();
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
     * Determines whether another object is equal to this PhoneNumber.  The result is
     * <code>true</code> if and only if the argument is not null and is a PhoneNumber object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber other = (PhoneNumber)object;
        if (this.value != null && other.value != null && this.value.equals(other.value) && 
                this.phoneNumberType != null && other.phoneNumberType != null && this.phoneNumberType.equals(other.phoneNumberType))
            return true;
        return false;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", partyId=" + partyId +
                ", phoneNumberType=" + phoneNumberType +
                '}';
    }
}
