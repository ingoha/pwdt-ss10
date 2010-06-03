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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class Party
 *
 */
@Entity
@Table(name = "party")
@NamedQueries({
@NamedQuery(name = "Party.findById", query = "SELECT p FROM Party p WHERE p.id = :partyid")
        })
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Party implements Serializable {

    @Id
    @Column(name = "partyid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    protected Integer id;

    @Column(name = "website")
    private String website;

    private String address1;

    private String address2;

    private String address3;

    private String city;

    private String state;

    private String zip;

    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partyId", fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partyId", fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<EmailAddress> emailAddresses = new HashSet<EmailAddress>();

    /**
     * Creates a new instance of Party
     */
    public Party() {
    }

    /**
     * Creates a new instance of Party with the specified values.
     *
     * @param partyid the partyid of the Party
     */
    public Party(Integer partyid) {
        this.id = partyid;
    }

    /**
     * Gets the partyid of this Party.
     *
     * @return the partyid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the partyid of this Party to the specified value.
     *
     * @param id the new partyid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the phoneNumberCollection of this Party.
     *
     * @return the phoneNumberCollection
     */
    public Set<PhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    /**
     * Sets the phoneNumberCollection of this Party to the specified value.
     *
     * @param phoneNumbers the new phoneNumberCollection
     */
    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * Gets the emailAddressCollection of this Party.
     *
     * @return the emailAddressCollection
     */
    public Set<EmailAddress> getEmailAddresses() {
        return this.emailAddresses;
    }

    /**
     * Sets the emailAddressCollection of this Party to the specified value.
     *
     * @param emailAddresses the new emailAddressCollection
     */
    public void setEmailAddresses(Set<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
     * Determines whether another object is equal to this Party.  The result is
     * <code>true</code> if and only if the argument is not null and is a Party object that
     * has the same id field values as this object.
     *
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Party)) {
            return false;
        }
        Party other = (Party) object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs
     * that representation based on the id fields.
     *
     * @return a string representation of the object.
     */


    public String toString() {
        return "Party{" +
                "id=" + id +
                ", phoneNumbers=" + phoneNumbers +
                ", emailAddresses=" + emailAddresses +
                '}';
    }
}
