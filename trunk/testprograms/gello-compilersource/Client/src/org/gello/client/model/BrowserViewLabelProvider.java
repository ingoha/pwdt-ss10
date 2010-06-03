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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.IImageKeys;
import org.gello.client.manager.GelloNode;

public class BrowserViewLabelProvider extends LabelProvider
{
	public String getText(Object obj)
	{
		if (obj instanceof GelloNode)
			return ((GelloNode) obj).getName();
		return obj.toString();
	}

	public Image getImage(Object obj)
	{
		if (!((GelloNode) obj).isFile())
		{
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		}
		else
		{
			String lockOwner = ((GelloNode) obj).getLockOwner();
			if (lockOwner == null)
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
			else if (lockOwner.equalsIgnoreCase(Session.getInstance().getConnectionDetails().getUsername()))
				return new Image(PlatformUI.getWorkbench().getDisplay(), AbstractUIPlugin.imageDescriptorFromPlugin("org.gello.client", IImageKeys.CHECK_OUT).getImageData());
			else
				return new Image(PlatformUI.getWorkbench().getDisplay(), AbstractUIPlugin.imageDescriptorFromPlugin("org.gello.client", IImageKeys.LOCK).getImageData());
		}
	}
}
