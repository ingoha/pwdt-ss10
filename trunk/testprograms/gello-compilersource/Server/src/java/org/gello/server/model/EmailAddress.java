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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class EmailAddress
 *
 */
@Entity
@Table(name = "email_address")
@NamedQueries( {
        @NamedQuery(name = "EmailAddress.findById", query = "SELECT e FROM EmailAddress e WHERE e.id = :emailaddressid"),
        @NamedQuery(name = "EmailAddress.findByValue", query = "SELECT e FROM EmailAddress e WHERE e.value = :value")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class EmailAddress implements Serializable {

    @Id
    @Column(name = "emailaddressid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer id;

    @Column(name = "value")
    private String value;

    @Column(name = "emailaddresstypeid")
    private Integer emailAddressType;

    private Integer partyId;

    /** Creates a new instance of EmailAddress */
    public EmailAddress() {
    }

    /**
     * Creates a new instance of EmailAddress with the specified values.
     * @param emailaddressid the emailaddressid of the EmailAddress
     */
    public EmailAddress(Integer emailaddressid) {
        this.id = emailaddressid;
    }

    /**
     * Gets the emailaddressid of this EmailAddress.
     * @return the emailaddressid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the emailaddressid of this EmailAddress to the specified value.
     * @param id the new emailaddressid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the value of this EmailAddress.
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value of this EmailAddress to the specified value.
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the emailaddresstypeid of this EmailAddress.
     * @return the emailaddresstypeid
     */
    public EmailAddressType getEmailAddressType() {
        return EmailAddressType.getTypeFromKey(this.emailAddressType);
    }

    /**
     * Sets the emailaddresstypeid of this EmailAddress to the specified value.
     * @param emailaddresstypeid the new emailaddresstypeid
     */
    public void setEmailAddressType(EmailAddressType emailaddresstypeid) {
        this.emailAddressType = emailaddresstypeid.getDbKey();
    }

    /**
     * Gets the partyid of this EmailAddress.
     * @return the partyid
     */
    public Integer getPartyId() {
        return this.partyId;
    }

    /**
     * Sets the partyid of this EmailAddress to the specified value.
     * @param partyId the new partyid
     */
    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
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
     * Determines whether another object is equal to this EmailAddress.  The result is
     * <code>true</code> if and only if the argument is not null and is a EmailAddress object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailAddress)) {
            return false;
        }
        EmailAddress other = (EmailAddress)object;
        if (this.value != null && other.value != null && this.value.equals(other.value) && 
                this.emailAddressType != null && other.emailAddressType != null && this.emailAddressType.equals(other.emailAddressType))
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
        return "EmailAddress{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", emailAddressType=" + emailAddressType +
                ", partyId=" + partyId +
                '}';
    }
}
