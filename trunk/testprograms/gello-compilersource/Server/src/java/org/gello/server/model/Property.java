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
 * Entity class Property
 * 
 */
@Entity
@Table(name = "property")
@NamedQueries( {
        @NamedQuery(name = "Property.findByPropertyid", query = "SELECT p FROM Property p WHERE p.propertyid = :propertyid"),
        @NamedQuery(name = "Property.findByDontKnowYet", query = "SELECT p FROM Property p WHERE p.dontKnowYet = :dontKnowYet")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Property implements Serializable {

    @Id
    @Column(name = "propertyid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer propertyid;

    @Column(name = "dont_know_yet")
    private String dontKnowYet;

    @JoinColumn(name = "expressionid", referencedColumnName = "expressionid")
    @ManyToOne
    private Expression expressionid;

    /** Creates a new instance of Property */
    public Property() {
    }

    /**
     * Creates a new instance of Property with the specified values.
     * @param propertyid the propertyid of the Property
     */
    public Property(Integer propertyid) {
        this.propertyid = propertyid;
    }

    /**
     * Gets the propertyid of this Property.
     * @return the propertyid
     */
    public Integer getPropertyid() {
        return this.propertyid;
    }

    /**
     * Sets the propertyid of this Property to the specified value.
     * @param propertyid the new propertyid
     */
    public void setPropertyid(Integer propertyid) {
        this.propertyid = propertyid;
    }

    /**
     * Gets the dontKnowYet of this Property.
     * @return the dontKnowYet
     */
    public String getDontKnowYet() {
        return this.dontKnowYet;
    }

    /**
     * Sets the dontKnowYet of this Property to the specified value.
     * @param dontKnowYet the new dontKnowYet
     */
    public void setDontKnowYet(String dontKnowYet) {
        this.dontKnowYet = dontKnowYet;
    }

    /**
     * Gets the expressionid of this Property.
     * @return the expressionid
     */
    public Expression getExpressionid() {
        return this.expressionid;
    }

    /**
     * Sets the expressionid of this Property to the specified value.
     * @param expressionid the new expressionid
     */
    public void setExpressionid(Expression expressionid) {
        this.expressionid = expressionid;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.propertyid != null ? this.propertyid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Property.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Property object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property)object;
        if (this.propertyid != other.propertyid && (this.propertyid == null || !this.propertyid.equals(other.propertyid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.Property[propertyid=" + propertyid + "]";
    }

}
