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


package org.gello.runtime;

/**
 * Checked exception for returning exceptions within the model.
 * 
 * @author zubint
 *
 */
public class GelloModelException extends Exception {

	/**
	 * Default version identifier
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Wrapped constructor
	 * @param exception
	 * @param e
	 */
	public GelloModelException(String exception, Exception e) {
		super(exception, e);
	}
}
