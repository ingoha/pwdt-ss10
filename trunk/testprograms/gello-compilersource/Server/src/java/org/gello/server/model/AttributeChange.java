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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entity class AttributeChange
 * 
 */
@Entity
@Table(name = "attribute_change")
@NamedQueries( {
        @NamedQuery(name = "AttributeChange.findByAttributechangeid", query = "SELECT a FROM AttributeChange a WHERE a.attributechangeid = :attributechangeid"),
        @NamedQuery(name = "AttributeChange.findByAffectedattribute", query = "SELECT a FROM AttributeChange a WHERE a.affectedattribute = :affectedattribute"),
        @NamedQuery(name = "AttributeChange.findByPretext", query = "SELECT a FROM AttributeChange a WHERE a.pretext = :pretext"),
        @NamedQuery(name = "AttributeChange.findByPreinteger", query = "SELECT a FROM AttributeChange a WHERE a.preinteger = :preinteger"),
        @NamedQuery(name = "AttributeChange.findByPredate", query = "SELECT a FROM AttributeChange a WHERE a.predate = :predate"),
        @NamedQuery(name = "AttributeChange.findByPrechar", query = "SELECT a FROM AttributeChange a WHERE a.prechar = :prechar"),
        @NamedQuery(name = "AttributeChange.findByPosttext", query = "SELECT a FROM AttributeChange a WHERE a.posttext = :posttext"),
        @NamedQuery(name = "AttributeChange.findByPostinteger", query = "SELECT a FROM AttributeChange a WHERE a.postinteger = :postinteger"),
        @NamedQuery(name = "AttributeChange.findByPostdate", query = "SELECT a FROM AttributeChange a WHERE a.postdate = :postdate"),
        @NamedQuery(name = "AttributeChange.findByPostchar", query = "SELECT a FROM AttributeChange a WHERE a.postchar = :postchar")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class AttributeChange implements Serializable {

    @Id
    @Column(name = "attributechangeid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer attributechangeid;

    @Column(name = "affectedattribute")
    private String affectedattribute;

    @Column(name = "pretext")
    private String pretext;

    @Column(name = "preinteger")
    private Integer preinteger;

    @Column(name = "predate")
    @Temporal(TemporalType.DATE)
    private Date predate;

    @Column(name = "prechar")
    private String prechar;

    @Column(name = "posttext")
    private String posttext;

    @Column(name = "postinteger")
    private Integer postinteger;

    @Column(name = "postdate")
    @Temporal(TemporalType.DATE)
    private Date postdate;

    @Column(name = "postchar")
    private String postchar;

    @JoinColumn(name = "datatypeid", referencedColumnName = "datatypeid")
    @ManyToOne
    private DataType datatypeid;

    @JoinColumn(name = "rowchangeid", referencedColumnName = "rowchangeid")
    @ManyToOne
    private RowChange rowchangeid;

    /** Creates a new instance of AttributeChange */
    public AttributeChange() {
    }

    /**
     * Creates a new instance of AttributeChange with the specified values.
     * @param attributechangeid the attributechangeid of the AttributeChange
     */
    public AttributeChange(Integer attributechangeid) {
        this.attributechangeid = attributechangeid;
    }

    /**
     * Gets the attributechangeid of this AttributeChange.
     * @return the attributechangeid
     */
    public Integer getAttributechangeid() {
        return this.attributechangeid;
    }

    /**
     * Sets the attributechangeid of this AttributeChange to the specified value.
     * @param attributechangeid the new attributechangeid
     */
    public void setAttributechangeid(Integer attributechangeid) {
        this.attributechangeid = attributechangeid;
    }

    /**
     * Gets the affectedattribute of this AttributeChange.
     * @return the affectedattribute
     */
    public String getAffectedattribute() {
        return this.affectedattribute;
    }

    /**
     * Sets the affectedattribute of this AttributeChange to the specified value.
     * @param affectedattribute the new affectedattribute
     */
    public void setAffectedattribute(String affectedattribute) {
        this.affectedattribute = affectedattribute;
    }

    /**
     * Gets the pretext of this AttributeChange.
     * @return the pretext
     */
    public String getPretext() {
        return this.pretext;
    }

    /**
     * Sets the pretext of this AttributeChange to the specified value.
     * @param pretext the new pretext
     */
    public void setPretext(String pretext) {
        this.pretext = pretext;
    }

    /**
     * Gets the preinteger of this AttributeChange.
     * @return the preinteger
     */
    public Integer getPreinteger() {
        return this.preinteger;
    }

    /**
     * Sets the preinteger of this AttributeChange to the specified value.
     * @param preinteger the new preinteger
     */
    public void setPreinteger(Integer preinteger) {
        this.preinteger = preinteger;
    }

    /**
     * Gets the predate of this AttributeChange.
     * @return the predate
     */
    public Date getPredate() {
        return this.predate;
    }

    /**
     * Sets the predate of this AttributeChange to the specified value.
     * @param predate the new predate
     */
    public void setPredate(Date predate) {
        this.predate = predate;
    }

    /**
     * Gets the prechar of this AttributeChange.
     * @return the prechar
     */
    public String getPrechar() {
        return this.prechar;
    }

    /**
     * Sets the prechar of this AttributeChange to the specified value.
     * @param prechar the new prechar
     */
    public void setPrechar(String prechar) {
        this.prechar = prechar;
    }

    /**
     * Gets the posttext of this AttributeChange.
     * @return the posttext
     */
    public String getPosttext() {
        return this.posttext;
    }

    /**
     * Sets the posttext of this AttributeChange to the specified value.
     * @param posttext the new posttext
     */
    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }

    /**
     * Gets the postinteger of this AttributeChange.
     * @return the postinteger
     */
    public Integer getPostinteger() {
        return this.postinteger;
    }

    /**
     * Sets the postinteger of this AttributeChange to the specified value.
     * @param postinteger the new postinteger
     */
    public void setPostinteger(Integer postinteger) {
        this.postinteger = postinteger;
    }

    /**
     * Gets the postdate of this AttributeChange.
     * @return the postdate
     */
    public Date getPostdate() {
        return this.postdate;
    }

    /**
     * Sets the postdate of this AttributeChange to the specified value.
     * @param postdate the new postdate
     */
    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    /**
     * Gets the postchar of this AttributeChange.
     * @return the postchar
     */
    public String getPostchar() {
        return this.postchar;
    }

    /**
     * Sets the postchar of this AttributeChange to the specified value.
     * @param postchar the new postchar
     */
    public void setPostchar(String postchar) {
        this.postchar = postchar;
    }

    /**
     * Gets the datatypeid of this AttributeChange.
     * @return the datatypeid
     */
    public DataType getDatatypeid() {
        return this.datatypeid;
    }

    /**
     * Sets the datatypeid of this AttributeChange to the specified value.
     * @param datatypeid the new datatypeid
     */
    public void setDatatypeid(DataType datatypeid) {
        this.datatypeid = datatypeid;
    }

    /**
     * Gets the rowchangeid of this AttributeChange.
     * @return the rowchangeid
     */
    public RowChange getRowchangeid() {
        return this.rowchangeid;
    }

    /**
     * Sets the rowchangeid of this AttributeChange to the specified value.
     * @param rowchangeid the new rowchangeid
     */
    public void setRowchangeid(RowChange rowchangeid) {
        this.rowchangeid = rowchangeid;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.attributechangeid != null ? this.attributechangeid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this AttributeChange.  The result is 
     * <code>true</code> if and only if the argument is not null and is a AttributeChange object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttributeChange)) {
            return false;
        }
        AttributeChange other = (AttributeChange)object;
        if (this.attributechangeid != other.attributechangeid && (this.attributechangeid == null || !this.attributechangeid.equals(other.attributechangeid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.AttributeChange[attributechangeid=" + attributechangeid + "]";
    }

}
