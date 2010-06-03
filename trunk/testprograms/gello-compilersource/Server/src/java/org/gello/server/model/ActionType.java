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
 * Entity class ActionType
 * 
 */
@Entity
@Table(name = "action_type")
@NamedQueries( {
        @NamedQuery(name = "ActionType.findByActiontypeid", query = "SELECT a FROM ActionType a WHERE a.actiontypeid = :actiontypeid"),
        @NamedQuery(name = "ActionType.findByName", query = "SELECT a FROM ActionType a WHERE a.name = :name"),
        @NamedQuery(name = "ActionType.findByDescr", query = "SELECT a FROM ActionType a WHERE a.descr = :descr")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class ActionType implements Serializable {

    @Id
    @Column(name = "actiontypeid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer actiontypeid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "descr")
    private String descr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actiontypeid")
    private Collection<Action> actionCollection;

    /** Creates a new instance of ActionType */
    public ActionType() {
    }

    /**
     * Creates a new instance of ActionType with the specified values.
     * @param actiontypeid the actiontypeid of the ActionType
     */
    public ActionType(Integer actiontypeid) {
        this.actiontypeid = actiontypeid;
    }

    /**
     * Creates a new instance of ActionType with the specified values.
     * @param actiontypeid the actiontypeid of the ActionType
     * @param name the name of the ActionType
     */
    public ActionType(Integer actiontypeid, String name) {
        this.actiontypeid = actiontypeid;
        this.name = name;
    }

    /**
     * Gets the actiontypeid of this ActionType.
     * @return the actiontypeid
     */
    public Integer getActiontypeid() {
        return this.actiontypeid;
    }

    /**
     * Sets the actiontypeid of this ActionType to the specified value.
     * @param actiontypeid the new actiontypeid
     */
    public void setActiontypeid(Integer actiontypeid) {
        this.actiontypeid = actiontypeid;
    }

    /**
     * Gets the name of this ActionType.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this ActionType to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descr of this ActionType.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this ActionType to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the actionCollection of this ActionType.
     * @return the actionCollection
     */
    public Collection<Action> getActionCollection() {
        return this.actionCollection;
    }

    /**
     * Sets the actionCollection of this ActionType to the specified value.
     * @param actionCollection the new actionCollection
     */
    public void setActionCollection(Collection<Action> actionCollection) {
        this.actionCollection = actionCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.actiontypeid != null ? this.actiontypeid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this ActionType.  The result is 
     * <code>true</code> if and only if the argument is not null and is a ActionType object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionType)) {
            return false;
        }
        ActionType other = (ActionType)object;
        if (this.actiontypeid != other.actiontypeid && (this.actiontypeid == null || !this.actiontypeid.equals(other.actiontypeid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.ActionType[actiontypeid=" + actiontypeid + "]";
    }

}
