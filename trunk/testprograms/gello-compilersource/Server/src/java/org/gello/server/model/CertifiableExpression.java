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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity class CertifiableExpressionType
 *
 */
@Entity
@Table(name = "certifiable_expression")
@NamedQueries( {
        @NamedQuery(name = "CertifiableExpression.findByCertifiableexpressiontypeid", query = "SELECT c FROM CertifiableExpression c WHERE c.id = :certifiableexpressiontypeid"),
        @NamedQuery(name = "CertifiableExpression.findByName", query = "SELECT c FROM CertifiableExpression c WHERE c.name = :name"),
        @NamedQuery(name = "CertifiableExpression.findByDescr", query = "SELECT c FROM CertifiableExpression c WHERE c.descr = :descr")
    })
public class CertifiableExpression implements Serializable {

    @Id
    @Column(name = "certifiableexpressionid", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @JoinTable(name = "certifiable_expression_organization", joinColumns =  {
            @JoinColumn(name = "certifiableexpressionid", referencedColumnName = "certifiableexpressionid")
        }, inverseJoinColumns =  {
            @JoinColumn(name = "organizationid", referencedColumnName = "organizationid")
        })
    @ManyToMany
    private Collection<Organization> organization;

    /** Creates a new instance of CertifiableExpressionType */
    public CertifiableExpression() {
    }

    /**
     * Creates a new instance of CertifiableExpressionType with the specified values.
     * @param certifiableexpressiontypeid the certifiableexpressiontypeid of the CertifiableExpressionType
     */
    public CertifiableExpression(Integer certifiableexpressiontypeid) {
        this.id = certifiableexpressiontypeid;
    }

    /**
     * Creates a new instance of CertifiableExpressionType with the specified values.
     * @param certifiableexpressiontypeid the certifiableexpressiontypeid of the CertifiableExpressionType
     * @param name the name of the CertifiableExpressionType
     */
    public CertifiableExpression(Integer certifiableexpressiontypeid, String name) {
        this.id = certifiableexpressiontypeid;
        this.name = name;
    }

    /**
     * Gets the certifiableexpressiontypeid of this CertifiableExpressionType.
     * @return the certifiableexpressiontypeid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the certifiableexpressiontypeid of this CertifiableExpressionType to the specified value.
     * @param id the new certifiableexpressiontypeid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of this CertifiableExpressionType.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this CertifiableExpressionType to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descr of this CertifiableExpressionType.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this CertifiableExpressionType to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the organizationidCollection of this CertifiableExpressionType.
     * @return the organizationidCollection
     */
    public Collection<Organization> getOrganization() {
        return this.organization;
    }

    /**
     * Sets the organizationidCollection of this CertifiableExpressionType to the specified value.
     * @param organization the new organizationidCollection
     */
    public void setOrganization(Collection<Organization> organization) {
        this.organization = organization;
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
     * Determines whether another object is equal to this CertifiableExpressionType.  The result is
     * <code>true</code> if and only if the argument is not null and is a CertifiableExpressionType object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CertifiableExpression)) {
            return false;
        }
        CertifiableExpression other = (CertifiableExpression)object;
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
        return "org.gello.server.model.CertifiableExpressionType[certifiableexpressiontypeid=" + id + "]";
    }

}
