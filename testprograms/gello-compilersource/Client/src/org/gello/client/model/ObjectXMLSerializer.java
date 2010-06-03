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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Serialize java objects to or from XML.
 *
 */
public class ObjectXMLSerializer
{
	private XStream xstream;

	public ObjectXMLSerializer()
	{
		xstream = new XStream(new DomDriver());
	}

	public String toXML(Object source)
	{
		return xstream.toXML(source);
	}

	public Object fromXML(String source)
	{
		return xstream.fromXML(source);
	}
}
