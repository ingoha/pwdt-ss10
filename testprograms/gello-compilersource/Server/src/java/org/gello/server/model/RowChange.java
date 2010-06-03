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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class RowChange
 * 
 */
@Entity
@Table(name = "row_change")
@NamedQueries( {
        @NamedQuery(name = "RowChange.findByRowchangeid", query = "SELECT r FROM RowChange r WHERE r.rowchangeid = :rowchangeid"),
        @NamedQuery(name = "RowChange.findByChangedate", query = "SELECT r FROM RowChange r WHERE r.changedate = :changedate"),
        @NamedQuery(name = "RowChange.findByRowprimaryid", query = "SELECT r FROM RowChange r WHERE r.rowprimaryid = :rowprimaryid")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class RowChange implements Serializable {

    @Id
    @Column(name = "rowchangeid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer rowchangeid;

    @Column(name = "changedate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date changedate;

    @Column(name = "rowprimaryid", nullable = false)
    private int rowprimaryid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rowchangeid")
    private Collection<AttributeChange> attributeChangeCollection;

    @JoinColumn(name = "auditableentityid", referencedColumnName = "auditableentityid")
    @ManyToOne
    private AuditableEntity auditableentityid;

    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private User userid;

    /** Creates a new instance of RowChange */
    public RowChange() {
    }

    /**
     * Creates a new instance of RowChange with the specified values.
     * @param rowchangeid the rowchangeid of the RowChange
     */
    public RowChange(Integer rowchangeid) {
        this.rowchangeid = rowchangeid;
    }

    /**
     * Creates a new instance of RowChange with the specified values.
     * @param rowchangeid the rowchangeid of the RowChange
     * @param changedate the changedate of the RowChange
     * @param rowprimaryid the rowprimaryid of the RowChange
     */
    public RowChange(Integer rowchangeid, Date changedate, int rowprimaryid) {
        this.rowchangeid = rowchangeid;
        this.changedate = changedate;
        this.rowprimaryid = rowprimaryid;
    }

    /**
     * Gets the rowchangeid of this RowChange.
     * @return the rowchangeid
     */
    public Integer getRowchangeid() {
        return this.rowchangeid;
    }

    /**
     * Sets the rowchangeid of this RowChange to the specified value.
     * @param rowchangeid the new rowchangeid
     */
    public void setRowchangeid(Integer rowchangeid) {
        this.rowchangeid = rowchangeid;
    }

    /**
     * Gets the changedate of this RowChange.
     * @return the changedate
     */
    public Date getChangedate() {
        return this.changedate;
    }

    /**
     * Sets the changedate of this RowChange to the specified value.
     * @param changedate the new changedate
     */
    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    /**
     * Gets the rowprimaryid of this RowChange.
     * @return the rowprimaryid
     */
    public int getRowprimaryid() {
        return this.rowprimaryid;
    }

    /**
     * Sets the rowprimaryid of this RowChange to the specified value.
     * @param rowprimaryid the new rowprimaryid
     */
    public void setRowprimaryid(int rowprimaryid) {
        this.rowprimaryid = rowprimaryid;
    }

    /**
     * Gets the attributeChangeCollection of this RowChange.
     * @return the attributeChangeCollection
     */
    public Collection<AttributeChange> getAttributeChangeCollection() {
        return this.attributeChangeCollection;
    }

    /**
     * Sets the attributeChangeCollection of this RowChange to the specified value.
     * @param attributeChangeCollection the new attributeChangeCollection
     */
    public void setAttributeChangeCollection(Collection<AttributeChange> attributeChangeCollection) {
        this.attributeChangeCollection = attributeChangeCollection;
    }

    /**
     * Gets the auditableentityid of this RowChange.
     * @return the auditableentityid
     */
    public AuditableEntity getAuditableentityid() {
        return this.auditableentityid;
    }

    /**
     * Sets the auditableentityid of this RowChange to the specified value.
     * @param auditableentityid the new auditableentityid
     */
    public void setAuditableentityid(AuditableEntity auditableentityid) {
        this.auditableentityid = auditableentityid;
    }

    /**
     * Gets the userid of this RowChange.
     * @return the userid
     */
    public User getUserid() {
        return this.userid;
    }

    /**
     * Sets the userid of this RowChange to the specified value.
     * @param userid the new userid
     */
    public void setUserid(User userid) {
        this.userid = userid;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.rowchangeid != null ? this.rowchangeid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this RowChange.  The result is 
     * <code>true</code> if and only if the argument is not null and is a RowChange object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RowChange)) {
            return false;
        }
        RowChange other = (RowChange)object;
        if (this.rowchangeid != other.rowchangeid && (this.rowchangeid == null || !this.rowchangeid.equals(other.rowchangeid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.RowChange[rowchangeid=" + rowchangeid + "]";
    }

}
