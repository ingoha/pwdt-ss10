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
 * Entity class SystemPreference
 * 
 */
@Entity
@Table(name = "system_preference")
@NamedQueries( {
        @NamedQuery(name = "SystemPreference.findBySystempreferenceid", query = "SELECT s FROM SystemPreference s WHERE s.systempreferenceid = :systempreferenceid"),
        @NamedQuery(name = "SystemPreference.findByName", query = "SELECT s FROM SystemPreference s WHERE s.name = :name"),
        @NamedQuery(name = "SystemPreference.findByValue", query = "SELECT s FROM SystemPreference s WHERE s.value = :value"),
        @NamedQuery(name = "SystemPreference.findByDescr", query = "SELECT s FROM SystemPreference s WHERE s.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class SystemPreference implements Serializable {

    @Id
    @Column(name = "systempreferenceid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer systempreferenceid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "descr")
    private String descr;

    /** Creates a new instance of SystemPreference */
    public SystemPreference() {
    }

    /**
     * Creates a new instance of SystemPreference with the specified values.
     * @param systempreferenceid the systempreferenceid of the SystemPreference
     */
    public SystemPreference(Integer systempreferenceid) {
        this.systempreferenceid = systempreferenceid;
    }

    /**
     * Creates a new instance of SystemPreference with the specified values.
     * @param systempreferenceid the systempreferenceid of the SystemPreference
     * @param name the name of the SystemPreference
     */
    public SystemPreference(Integer systempreferenceid, String name) {
        this.systempreferenceid = systempreferenceid;
        this.name = name;
    }

    /**
     * Gets the systempreferenceid of this SystemPreference.
     * @return the systempreferenceid
     */
    public Integer getSystempreferenceid() {
        return this.systempreferenceid;
    }

    /**
     * Sets the systempreferenceid of this SystemPreference to the specified value.
     * @param systempreferenceid the new systempreferenceid
     */
    public void setSystempreferenceid(Integer systempreferenceid) {
        this.systempreferenceid = systempreferenceid;
    }

    /**
     * Gets the name of this SystemPreference.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this SystemPreference to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of this SystemPreference.
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value of this SystemPreference to the specified value.
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the descr of this SystemPreference.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this SystemPreference to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.systempreferenceid != null ? this.systempreferenceid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this SystemPreference.  The result is 
     * <code>true</code> if and only if the argument is not null and is a SystemPreference object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemPreference)) {
            return false;
        }
        SystemPreference other = (SystemPreference)object;
        if (this.systempreferenceid != other.systempreferenceid && (this.systempreferenceid == null || !this.systempreferenceid.equals(other.systempreferenceid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "SystemPreference{" +
                "systempreferenceid=" + systempreferenceid +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", descr='" + descr + '\'' +
                '}';
    }
}
