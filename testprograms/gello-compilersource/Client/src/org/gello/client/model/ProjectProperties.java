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


package org.gello.client.model;

/**
 * A class to store ProjectProperties in.  This is serialized to XML
 * and stored under /projectName/Properties in the repository.
 *
 */
public class ProjectProperties
{
	String projectName;
	String drugName;
	String drugSPLReference;
	String templateType;
	String drugClass;
	String currentProjectStatus;
	String GUID;
	String createdBy;
	String createdDate;
	String projectRevision;
	String projectDescription;

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(String createdDate)
	{
		this.createdDate = createdDate;
	}

	public String getCurrentProjectStatus()
	{
		return currentProjectStatus;
	}

	public void setCurrentProjectStatus(String currentProjectStatus)
	{
		this.currentProjectStatus = currentProjectStatus;
	}

	public String getDrugName()
	{
		return drugName;
	}

	public void setDrugName(String drugName)
	{
		this.drugName = drugName;
	}

	public String getDrugSPLReference()
	{
		return drugSPLReference;
	}

	public void setDrugSPLReference(String drugSPLReference)
	{
		this.drugSPLReference = drugSPLReference;
	}

	public String getGUID()
	{
		return GUID;
	}

	public void setGUID(String guid)
	{
		GUID = guid;
	}

	public String getProjectDescription()
	{
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription)
	{
		this.projectDescription = projectDescription;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	public String getProjectRevision()
	{
		return projectRevision;
	}

	public void setProjectRevision(String projectRevision)
	{
		this.projectRevision = projectRevision;
	}

	public String getTemplateType()
	{
		return templateType;
	}

	public void setTemplateType(String templateType)
	{
		this.templateType = templateType;
	}

	public String getDrugClass()
	{
		return drugClass;
	}

	public void setDrugClass(String drugClass)
	{
		this.drugClass = drugClass;
	}
}
