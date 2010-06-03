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
 * Entity class UserPreference
 * 
 */
@Entity
@Table(name = "user_preference")
@NamedQueries( {
        @NamedQuery(name = "UserPreference.findByUserpreferenceid", query = "SELECT u FROM UserPreference u WHERE u.id = :userpreferenceid"),
        @NamedQuery(name = "UserPreference.findByName", query = "SELECT u FROM UserPreference u WHERE u.name = :name"),
        @NamedQuery(name = "UserPreference.findByValue", query = "SELECT u FROM UserPreference u WHERE u.value = :value"),
        @NamedQuery(name = "UserPreference.findByDescr", query = "SELECT u FROM UserPreference u WHERE u.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class UserPreference implements Serializable {

    @Id
    @Column(name = "userpreferenceid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "descr")
    private String descr;

    private Integer userId;

    /** Creates a new instance of UserPreference */
    public UserPreference() {
    }

    /**
     * Creates a new instance of UserPreference with the specified values.
     * @param userpreferenceid the userpreferenceid of the UserPreference
     */
    public UserPreference(Integer userpreferenceid) {
        this.id = userpreferenceid;
    }

    /**
     * Creates a new instance of UserPreference with the specified values.
     * @param userpreferenceid the userpreferenceid of the UserPreference
     * @param name the name of the UserPreference
     */
    public UserPreference(Integer userpreferenceid, String name) {
        this.id = userpreferenceid;
        this.name = name;
    }

    /**
     * Gets the userpreferenceid of this UserPreference.
     * @return the userpreferenceid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the userpreferenceid of this UserPreference to the specified value.
     * @param id the new userpreferenceid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of this UserPreference.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this UserPreference to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of this UserPreference.
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value of this UserPreference to the specified value.
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the descr of this UserPreference.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this UserPreference to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the userid of this UserPreference.
     * @return the userid
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * Sets the userid of this UserPreference to the specified value.
     * @param userId the new userid
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * Determines whether another object is equal to this UserPreference.  The result is 
     * <code>true</code> if and only if the argument is not null and is a UserPreference object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPreference)) {
            return false;
        }
        UserPreference other = (UserPreference)object;
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
        return "UserPreference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", descr='" + descr + '\'' +
                ", userId=" + userId +
                '}';
    }
}
