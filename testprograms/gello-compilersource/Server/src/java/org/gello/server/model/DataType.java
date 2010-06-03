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
 * Entity class DataType
 * 
 */
@Entity
@Table(name = "data_type")
@NamedQueries( {
        @NamedQuery(name = "DataType.findByDatatypeid", query = "SELECT d FROM DataType d WHERE d.datatypeid = :datatypeid"),
        @NamedQuery(name = "DataType.findByName", query = "SELECT d FROM DataType d WHERE d.name = :name"),
        @NamedQuery(name = "DataType.findByDescr", query = "SELECT d FROM DataType d WHERE d.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class DataType implements Serializable {

    @Id
    @Column(name = "datatypeid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer datatypeid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datatypeid")
    private Collection<AttributeChange> attributeChangeCollection;

    /** Creates a new instance of DataType */
    public DataType() {
    }

    /**
     * Creates a new instance of DataType with the specified values.
     * @param datatypeid the datatypeid of the DataType
     */
    public DataType(Integer datatypeid) {
        this.datatypeid = datatypeid;
    }

    /**
     * Creates a new instance of DataType with the specified values.
     * @param datatypeid the datatypeid of the DataType
     * @param name the name of the DataType
     */
    public DataType(Integer datatypeid, String name) {
        this.datatypeid = datatypeid;
        this.name = name;
    }

    /**
     * Gets the datatypeid of this DataType.
     * @return the datatypeid
     */
    public Integer getDatatypeid() {
        return this.datatypeid;
    }

    /**
     * Sets the datatypeid of this DataType to the specified value.
     * @param datatypeid the new datatypeid
     */
    public void setDatatypeid(Integer datatypeid) {
        this.datatypeid = datatypeid;
    }

    /**
     * Gets the name of this DataType.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this DataType to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descr of this DataType.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this DataType to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the attributeChangeCollection of this DataType.
     * @return the attributeChangeCollection
     */
    public Collection<AttributeChange> getAttributeChangeCollection() {
        return this.attributeChangeCollection;
    }

    /**
     * Sets the attributeChangeCollection of this DataType to the specified value.
     * @param attributeChangeCollection the new attributeChangeCollection
     */
    public void setAttributeChangeCollection(Collection<AttributeChange> attributeChangeCollection) {
        this.attributeChangeCollection = attributeChangeCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.datatypeid != null ? this.datatypeid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this DataType.  The result is 
     * <code>true</code> if and only if the argument is not null and is a DataType object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataType)) {
            return false;
        }
        DataType other = (DataType)object;
        if (this.datatypeid != other.datatypeid && (this.datatypeid == null || !this.datatypeid.equals(other.datatypeid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.DataType[datatypeid=" + datatypeid + "]";
    }

}
