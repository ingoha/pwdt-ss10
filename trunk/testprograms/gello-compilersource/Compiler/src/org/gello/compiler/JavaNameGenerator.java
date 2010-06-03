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


package org.gello.compiler;

/**
 * Generates unique Java names for objects.
 * @author Erik Horstkotte
 */
public class JavaNameGenerator {
    
    /** The numeric part of the last generated name. */
    protected int numberPart = 0;

    /** Creates a new instance of JavaNameGenerator */
    public JavaNameGenerator() {
    }

    /** Gets the next generated name */
    public String next() {
        return "v" + ++numberPart;
    }
}
