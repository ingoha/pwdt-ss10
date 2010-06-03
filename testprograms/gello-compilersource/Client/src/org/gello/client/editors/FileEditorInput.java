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


package org.gello.client.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.Assert;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.gello.client.manager.GelloNode;
import org.gello.client.model.Session;

public class FileEditorInput implements IEditorInput
{

	private String fileName;
	private String filePath;
	private String fileData;
	private GelloNode gelloNode;
	private boolean editable = false;

	public FileEditorInput(GelloNode gelloNode, String fileData)
	{
		super();
		Assert.isNotNull(gelloNode);
		this.fileName = gelloNode.getName();
		this.filePath = gelloNode.getPath();
		this.fileData = fileData;
		this.gelloNode = gelloNode;
	}

	public boolean exists()
	{
		return false;
	}

	public ImageDescriptor getImageDescriptor()
	{
		return null;
	}

	public String getName()
	{
		return fileName;
	}

	public GelloNode getGelloNode()
	{
		return gelloNode;
	}

	public IPersistableElement getPersistable()
	{
		return null;
	}

	public String getToolTipText()
	{
		return filePath;
	}

	public String getData()
	{
		return fileData;
	}

	public void setData(String data)
	{
		this.fileData = data;
	}

	public Object getAdapter(Class adapter)
	{
		return null;
	}

	/**
	 * One input is equal to antoher if the file paths are the same.
	 * 
	 */
	public boolean equals(Object obj)
	{
		if (super.equals(obj))
			return true;
		if (!(obj instanceof FileEditorInput))
			return false;
		FileEditorInput other = (FileEditorInput) obj;
		return this.filePath.equals(other.filePath);
	}

	public boolean isEditable()
	{
		return editable;
	}

	/**
	 * Gets whether the input is potentially editable or not.
	 * @return True if there is no lock owner or the current user is the lock owner.  False otherwise.
	 */
	public boolean isPotentiallyEditable()
	{
		return (gelloNode.getLockOwner() == null || gelloNode.getLockOwner().equalsIgnoreCase(Session.getInstance().getConnectionDetails().getUsername()));
	}

	/**
	 * Resets the inputs editable state.
	 *
	 */
	public void resetEditable()
	{
		if (gelloNode.getLockOwner() != null && gelloNode.getLockOwner().equalsIgnoreCase(Session.getInstance().getConnectionDetails().getUsername()))
			this.editable = true;
		else
			this.editable = false;
	}

}
