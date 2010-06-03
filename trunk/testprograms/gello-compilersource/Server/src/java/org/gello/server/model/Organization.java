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
import java.util.Set;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

/**
 * Entity class Organization
 * 
 */
@Entity
@Table(name = "organization")
@NamedQueries( {
        @NamedQuery(name = "Organization.findById", query = "SELECT o FROM Organization o WHERE o.id = :organizationid"),
        @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name"),
        @NamedQuery(name = "Organization.all", query = "SELECT o FROM Organization o")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
@PrimaryKeyJoinColumn(name = "organizationid", referencedColumnName = "partyid")
public class Organization extends Party implements Serializable {


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contactname")
    private String contactName;

    @JoinTable(name = "certifiable_expression_organization", joinColumns = {
    @JoinColumn(name = "organizationid", referencedColumnName = "organizationid")
            }, inverseJoinColumns = {
    @JoinColumn(name = "certifiableexpressionid", referencedColumnName = "certifiableexpressionid")
            })
    @ManyToMany(fetch = FetchType.EAGER)    
    private Collection<CertifiableExpression> certifiableExpressions;

    /** Creates a new instance of Organization */
    public Organization() {
    }

    /**
     * Creates a new instance of Organization with the specified values.
     * @param id the id of the Organization
     */
    public Organization(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new instance of Organization with the specified values.
     * @param id the id of the Organization
     * @param name the name of the Organization
     * @param iscertifying the iscertifying of the Organization
     */
    public Organization(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the name of this Organization.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this Organization to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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
     * Determines whether another object is equal to this Organization.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Organization object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization)object;
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
        return "Organization{" +
                "organizationid=" + id +
                ", name='" + name + '\'' +
                ", " + super.toString() +
                '}';
    }

    public Collection<CertifiableExpressionType> getCertifiableExpressions() {
        initializeCertifiableExpressionsIfNull();
        Set<CertifiableExpressionType> c = new HashSet<CertifiableExpressionType>();
        for (CertifiableExpression ce : certifiableExpressions) {
            c.add(CertifiableExpressionType.getTypeFromCertifiableExpression(ce));
        }
        return c;
    }

    public void setCertifiableExpressions(Collection<CertifiableExpressionType> certifiableExpressions) {
        initializeCertifiableExpressionsIfNull();
        this.certifiableExpressions.clear();
        for (CertifiableExpressionType cet : certifiableExpressions) {
            this.certifiableExpressions.add(cet.getCertifiableExpression());
        }
    }

    private void initializeCertifiableExpressionsIfNull() {
        if (certifiableExpressions == null) {
            certifiableExpressions = new HashSet<CertifiableExpression>();
        }
    }
}
