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
 * Entity class Resource
 * 
 */
@Entity
@Table(name = "resource")
@NamedQueries( {
        @NamedQuery(name = "Resource.findById", query = "SELECT r FROM Resource r WHERE r.id = :resourceid"),
        @NamedQuery(name = "Resource.findByUrl", query = "SELECT r FROM Resource r WHERE r.url = :url"),
        @NamedQuery(name = "Resource.all", query = "SELECT r FROM Resource r")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Resource implements Serializable {

    @Id
    @Column(name = "resourceid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer id;

    @Column(name = "url")
    private String url;

    private String title;

    private String body;

    private Integer sort;

    /** Creates a new instance of Resource */
    public Resource() {
    }

    /**
     * Creates a new instance of Resource with the specified values.
     * @param resourceid the resourceid of the Resource
     */
    public Resource(Integer resourceid) {
        this.id = resourceid;
    }

    /**
     * Gets the resourceid of this Resource.
     * @return the resourceid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the resourceid of this Resource to the specified value.
     * @param id the new resourceid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the url of this Resource.
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets the url of this Resource to the specified value.
     * @param url the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
     * Determines whether another object is equal to this Resource.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Resource object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource)object;
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
        return "org.gello.server.model.Resource[resourceid=" + id + "]";
    }

}
