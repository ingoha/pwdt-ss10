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
 * A temporary class until Expressions are implemented in the server model.
 * @author jacobb
 *
 */
// TODO Implement Expressions in the server model and remove ExpressionInfo.
public class ExpressionInfo
{
	private String type;
	private String drugClass;
	private String drugName;
	private String category;
	private String repository;

	public ExpressionInfo(String type, String drugClass, String drugName, String category, String repository)
	{
		this.type = type;
		this.drugClass = drugClass;
		this.drugName = drugName;
		this.category = category;
		this.repository = repository;
	}

	public String getDrugClass()
	{
		return drugClass;
	}

	public void setDrugClass(String drugClass)
	{
		this.drugClass = drugClass;
	}

	public String getDrugName()
	{
		return drugName;
	}

	public void setDrugName(String drugName)
	{
		this.drugName = drugName;
	}

	public String getRepository()
	{
		return repository;
	}

	public void setRepository(String repository)
	{
		this.repository = repository;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
