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
 * The base of the class of exceptions thrown by the GELLO system.
 * @author Erik Horstkotte
 */
public class GelloException extends Exception {
    
    /** Creates a new instance of GelloException */
    public GelloException() {
    }
    
    public GelloException(String msg) {
        super(msg);
    }
}
