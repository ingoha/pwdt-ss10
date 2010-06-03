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
 * Entity class Expression
 * 
 */
@Entity
@Table(name = "expression")
@NamedQueries( {
        @NamedQuery(name = "Expression.findByExpressionid", query = "SELECT e FROM Expression e WHERE e.expressionid = :expressionid")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Expression implements Serializable {

    @Id
    @Column(name = "expressionid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer expressionid;

    @JoinColumn(name = "projectid", referencedColumnName = "projectid")
    @ManyToOne
    private Project projectid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expressionid")
    private Collection<Property> propertyCollection;

    /** Creates a new instance of Expression */
    public Expression() {
    }

    /**
     * Creates a new instance of Expression with the specified values.
     * @param expressionid the expressionid of the Expression
     */
    public Expression(Integer expressionid) {
        this.expressionid = expressionid;
    }

    /**
     * Gets the expressionid of this Expression.
     * @return the expressionid
     */
    public Integer getExpressionid() {
        return this.expressionid;
    }

    /**
     * Sets the expressionid of this Expression to the specified value.
     * @param expressionid the new expressionid
     */
    public void setExpressionid(Integer expressionid) {
        this.expressionid = expressionid;
    }

    /**
     * Gets the projectid of this Expression.
     * @return the projectid
     */
    public Project getProjectid() {
        return this.projectid;
    }

    /**
     * Sets the projectid of this Expression to the specified value.
     * @param projectid the new projectid
     */
    public void setProjectid(Project projectid) {
        this.projectid = projectid;
    }    

    /**
     * Gets the propertyCollection of this Expression.
     * @return the propertyCollection
     */
    public Collection<Property> getPropertyCollection() {
        return this.propertyCollection;
    }

    /**
     * Sets the propertyCollection of this Expression to the specified value.
     * @param propertyCollection the new propertyCollection
     */
    public void setPropertyCollection(Collection<Property> propertyCollection) {
        this.propertyCollection = propertyCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.expressionid != null ? this.expressionid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Expression.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Expression object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expression)) {
            return false;
        }
        Expression other = (Expression)object;
        if (this.expressionid != other.expressionid && (this.expressionid == null || !this.expressionid.equals(other.expressionid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.Expression[expressionid=" + expressionid + "]";
    }

}
