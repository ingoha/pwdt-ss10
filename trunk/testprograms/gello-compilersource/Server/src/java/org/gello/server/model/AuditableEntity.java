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
 * Entity class AuditableEntity
 * 
 */
@Entity
@Table(name = "auditable_entity")
@NamedQueries( {
        @NamedQuery(name = "AuditableEntity.findByAuditableentityid", query = "SELECT a FROM AuditableEntity a WHERE a.auditableentityid = :auditableentityid"),
        @NamedQuery(name = "AuditableEntity.findByName", query = "SELECT a FROM AuditableEntity a WHERE a.name = :name"),
        @NamedQuery(name = "AuditableEntity.findByDescr", query = "SELECT a FROM AuditableEntity a WHERE a.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class AuditableEntity implements Serializable {

    @Id
    @Column(name = "auditableentityid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer auditableentityid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditableentityid")
    private Collection<RowChange> rowChangeCollection;

    /** Creates a new instance of AuditableEntity */
    public AuditableEntity() {
    }

    /**
     * Creates a new instance of AuditableEntity with the specified values.
     * @param auditableentityid the auditableentityid of the AuditableEntity
     */
    public AuditableEntity(Integer auditableentityid) {
        this.auditableentityid = auditableentityid;
    }

    /**
     * Creates a new instance of AuditableEntity with the specified values.
     * @param auditableentityid the auditableentityid of the AuditableEntity
     * @param name the name of the AuditableEntity
     */
    public AuditableEntity(Integer auditableentityid, String name) {
        this.auditableentityid = auditableentityid;
        this.name = name;
    }

    /**
     * Gets the auditableentityid of this AuditableEntity.
     * @return the auditableentityid
     */
    public Integer getAuditableentityid() {
        return this.auditableentityid;
    }

    /**
     * Sets the auditableentityid of this AuditableEntity to the specified value.
     * @param auditableentityid the new auditableentityid
     */
    public void setAuditableentityid(Integer auditableentityid) {
        this.auditableentityid = auditableentityid;
    }

    /**
     * Gets the name of this AuditableEntity.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this AuditableEntity to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descr of this AuditableEntity.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this AuditableEntity to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the rowChangeCollection of this AuditableEntity.
     * @return the rowChangeCollection
     */
    public Collection<RowChange> getRowChangeCollection() {
        return this.rowChangeCollection;
    }

    /**
     * Sets the rowChangeCollection of this AuditableEntity to the specified value.
     * @param rowChangeCollection the new rowChangeCollection
     */
    public void setRowChangeCollection(Collection<RowChange> rowChangeCollection) {
        this.rowChangeCollection = rowChangeCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.auditableentityid != null ? this.auditableentityid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this AuditableEntity.  The result is 
     * <code>true</code> if and only if the argument is not null and is a AuditableEntity object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditableEntity)) {
            return false;
        }
        AuditableEntity other = (AuditableEntity)object;
        if (this.auditableentityid != other.auditableentityid && (this.auditableentityid == null || !this.auditableentityid.equals(other.auditableentityid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.AuditableEntity[auditableentityid=" + auditableentityid + "]";
    }

}
