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
 * Miscellaneous static runtime support methods.
 *
 * TODO:
 * - null value handling is underspecified and omitted.
 *
 * @author Erik Horstkotte
 */
public class GelloRuntime {

    /** Compare two integer values for GELLO equality */
    public static GelloBoolean equals(Integer left, Integer right) {

        return new GelloBoolean(left.compareTo(right) == 0);
    }

    /** Compare two real values for GELLO equality */
    public static GelloBoolean equals(Double left, Double right) {

        return new GelloBoolean(left.compareTo(right) == 0);
    }

    /** Compare two boolean values for GELLO equality */
    public static GelloBoolean equals(GelloBoolean left, GelloBoolean right) {

        return left.equals(right);
    }

    /** Compare two string values for GELLO equality */
    public static GelloBoolean equals(String left, String right) {

        return new GelloBoolean(left.compareTo(right) == 0);
    }
}
