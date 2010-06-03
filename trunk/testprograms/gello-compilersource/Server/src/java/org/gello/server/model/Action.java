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
 * Entity class Action
 * 
 */
@Entity
@Table(name = "action")
@NamedQueries( {
        @NamedQuery(name = "Action.findByActionid", query = "SELECT a FROM Action a WHERE a.actionid = :actionid"),
        @NamedQuery(name = "Action.findByActiondate", query = "SELECT a FROM Action a WHERE a.actiondate = :actiondate")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Action implements Serializable {

    @Id
    @Column(name = "actionid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer actionid;

    @Column(name = "actiondate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date actiondate;

    @JoinColumn(name = "actiontypeid", referencedColumnName = "actiontypeid")
    @ManyToOne
    private ActionType actiontypeid;

    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne
    private User userid;

    @JoinColumn(name = "actiononid", referencedColumnName = "securableobjectid")
    @ManyToOne
    private SecurableObject actiononid;

    /** Creates a new instance of Action */
    public Action() {
    }

    /**
     * Creates a new instance of Action with the specified values.
     * @param actionid the actionid of the Action
     */
    public Action(Integer actionid) {
        this.actionid = actionid;
    }

    /**
     * Creates a new instance of Action with the specified values.
     * @param actionid the actionid of the Action
     * @param actiondate the actiondate of the Action
     */
    public Action(Integer actionid, Date actiondate) {
        this.actionid = actionid;
        this.actiondate = actiondate;
    }

    /**
     * Gets the actionid of this Action.
     * @return the actionid
     */
    public Integer getActionid() {
        return this.actionid;
    }

    /**
     * Sets the actionid of this Action to the specified value.
     * @param actionid the new actionid
     */
    public void setActionid(Integer actionid) {
        this.actionid = actionid;
    }

    /**
     * Gets the actiondate of this Action.
     * @return the actiondate
     */
    public Date getActiondate() {
        return this.actiondate;
    }

    /**
     * Sets the actiondate of this Action to the specified value.
     * @param actiondate the new actiondate
     */
    public void setActiondate(Date actiondate) {
        this.actiondate = actiondate;
    }

    /**
     * Gets the actiontypeid of this Action.
     * @return the actiontypeid
     */
    public ActionType getActiontypeid() {
        return this.actiontypeid;
    }

    /**
     * Sets the actiontypeid of this Action to the specified value.
     * @param actiontypeid the new actiontypeid
     */
    public void setActiontypeid(ActionType actiontypeid) {
        this.actiontypeid = actiontypeid;
    }

    /**
     * Gets the userid of this Action.
     * @return the userid
     */
    public User getUserid() {
        return this.userid;
    }

    /**
     * Sets the userid of this Action to the specified value.
     * @param userid the new userid
     */
    public void setUserid(User userid) {
        this.userid = userid;
    }

    /**
     * Gets the actiononid of this Action.
     * @return the actiononid
     */
    public SecurableObject getActiononid() {
        return this.actiononid;
    }

    /**
     * Sets the actiononid of this Action to the specified value.
     * @param actiononid the new actiononid
     */
    public void setActiononid(SecurableObject actiononid) {
        this.actiononid = actiononid;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.actionid != null ? this.actionid.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Action.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Action object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action)object;
        if (this.actionid != other.actionid && (this.actionid == null || !this.actionid.equals(other.actionid))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "org.gello.server.model.Action[actionid=" + actionid + "]";
    }

}
