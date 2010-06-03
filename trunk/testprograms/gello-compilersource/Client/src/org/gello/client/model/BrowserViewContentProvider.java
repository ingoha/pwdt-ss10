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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.gello.client.manager.GelloNode;

public class BrowserViewContentProvider implements IStructuredContentProvider, ITreeContentProvider
{
	public void inputChanged(Viewer v, Object oldInput, Object newInput)
	{
	}

	public void dispose()
	{
	}

	public Object[] getElements(Object parent)
	{
		return getChildren(parent);
	}

	public Object getParent(Object child)
	{
		return this.getParent(child);
	}

	public Object[] getChildren(Object parent)
	{
		if (parent instanceof GelloNode && !((GelloNode) parent).isFile())
		{
			List<GelloNode> children = ((GelloNode) parent).getChildren();
			Collections.sort(children, new Comparator<GelloNode>()
			{
				public int compare(GelloNode arg0, GelloNode arg1)
				{
					return (arg0).getName().compareTo((arg1).getName());
				}
			});
			return ((GelloNode) parent).getChildren().toArray();
		}
		return new Object[0];
	}

	public boolean hasChildren(Object parent)
	{
		if (parent instanceof GelloNode && !((GelloNode) parent).isFile())
			return (((GelloNode) parent).getChildren().size() != 0);
		return false;
	}

	/**
	 * Gets the parent node of a path given in a model given.
	 * @param model The model to search in.
	 * @param path The path of the child.
	 * @return The parent of the path given in the model given.
	 */
	public GelloNode getParentFromPath(GelloNode model, String path)
	{
		StringTokenizer tokenizer = new StringTokenizer(path, "/");
		String partPath = "/" + tokenizer.nextToken() + "/" + tokenizer.nextToken();
		while (!partPath.equals(path))
		{
			int i = 0;
			while (!model.getChildren().get(i).getPath().equals(partPath))
				i++;
			model = model.getChildren().get(i);
			partPath += "/" + tokenizer.nextToken();
		}

		return model;
	}
}
