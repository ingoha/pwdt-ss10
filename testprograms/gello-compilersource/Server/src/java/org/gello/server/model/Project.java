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
import java.util.Set;
import java.util.HashSet;
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
import javax.persistence.FetchType;
import javax.persistence.Transient;

/**
 * Entity class Project
 * 
 */
@Entity
@Table(name = "project")
@NamedQueries( {
        @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :projectid"),
        @NamedQuery(name = "Project.findByUniqueid", query = "SELECT p FROM Project p WHERE p.uniqueId = :uniqueId"),
        @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
        @NamedQuery(name = "Project.findByCreatedby", query = "SELECT p FROM Project p WHERE p.createdby = :createdby"),
        @NamedQuery(name = "Project.findByDescr", query = "SELECT p FROM Project p WHERE p.descr = :descr"),
        @NamedQuery(name = "Project.all", query = "SELECT p FROM Project p")
    })
@SequenceGenerator(name = "id_seq", sequenceName = "id_seq")
public class Project implements Serializable {

    @Id
    @Column(name = "projectid", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Integer id;

    @Column(name = "uniqueid", nullable = false)
    private String uniqueId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "createdby", nullable = false)
    private String createdby;

    @Column(name = "descr")
    private String descr;

    @Column(name = "repository")
    private String repository;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectid", fetch = FetchType.EAGER)
    private Set<Expression> expression = new HashSet<Expression>();

    private String templateType;

    private String drugClass;

    private String status;
    
    @Transient
    private GelloNode contents;

    /** Creates a new instance of Project */
    public Project() {
    }

    /**
     * Creates a new instance of Project with the specified values.
     * @param projectid the projectid of the Project
     */
    public Project(Integer projectid) {
        this.id = projectid;
    }

    /**
     * Creates a new instance of Project with the specified values.
     * @param projectid the projectid of the Project
     * @param uniqueid the uniqueid of the Project
     * @param name the name of the Project
     * @param createdby the createdby of the Project
     */
    public Project(Integer projectid, String uniqueid, String name, String createdby) {
        this.id = projectid;
        this.uniqueId = uniqueid;
        this.name = name;
        this.createdby = createdby;
    }

    /**
     * Gets the projectid of this Project.
     * @return the projectid
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the projectid of this Project to the specified value.
     * @param id the new projectid
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the uniqueid of this Project.
     * @return the uniqueid
     */
    public String getUniqueId() {
        return this.uniqueId;
    }

    /**
     * Sets the uniqueid of this Project to the specified value.
     * @param uniqueId the new uniqueid
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Gets the name of this Project.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of this Project to the specified value.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the createdby of this Project.
     * @return the createdby
     */
    public String getCreatedby() {
        return this.createdby;
    }

    /**
     * Sets the createdby of this Project to the specified value.
     * @param createdby the new createdby
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    /**
     * Gets the descr of this Project.
     * @return the descr
     */
    public String getDescr() {
        return this.descr;
    }

    /**
     * Sets the descr of this Project to the specified value.
     * @param descr the new descr
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Gets the expressionCollection of this Project.
     * @return the expressionCollection
     */
    public Set<Expression> getExpression() {
        return this.expression;
    }

    /**
     * Sets the expressionCollection of this Project to the specified value.
     * @param expression the new expressionCollection
     */
    public void setExpression(Set<Expression> expression) {
        this.expression = expression;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getDrugClass() {
        return drugClass;
    }

    public void setDrugClass(String drugClass) {
        this.drugClass = drugClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }
    
    public GelloNode getContents() {
        return contents;
    }
    
    public void setContents(GelloNode contents) {
        this.contents = contents;
    }
    
    public void buildContents(GelloNode contents) {
        Util.buildNodeActions(contents, this.templateType);
        this.contents = contents;
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
     * Determines whether another object is equal to this Project.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Project object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project)object;
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
        return "org.gello.server.model.Project[projectid=" + id + "]";
    }

}
