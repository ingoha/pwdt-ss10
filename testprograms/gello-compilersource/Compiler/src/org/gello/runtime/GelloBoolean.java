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
 * A GELLO "Boolean" value. Which has three possible values.
 * Poor George is probably spinning in his grave.
 *
 * TODO: Is there a cleaner way to do this, or to implement the operations?
 *
 * @author Erik Horstkotte
 */
public class GelloBoolean {

    /** The possible values of a GELLO boolean */
    public static final int FALSE = 0;
    public static final int TRUE = 1;
    public static final int UNKNOWN = 2;

    /** The value of this instance */
    protected int value = UNKNOWN;

    /** Creates a new instance of GelloBoolean */
    public GelloBoolean(int value) {
        this.value = value;
    }
    
    /** Creates a new instance of GelloBoolean */
    public GelloBoolean(boolean value) {
        
        if (value)
            this.value = TRUE;
        else
            this.value = FALSE;
    }

    /** Creates a new instance of GelloBoolean */
    public GelloBoolean(Boolean value) {
        
        if (value == null)
            this.value = UNKNOWN;
        else if (value.booleanValue())
            this.value = TRUE;
        else
            this.value = FALSE;
    }

    public String toString() {
        
        if (value == UNKNOWN)
            return "GelloBoolean(UNKNOWN)";
        else if (value == TRUE)
            return "GelloBoolean(TRUE)";
        else if (value == FALSE)
            return "GelloBoolean(FALSE)";
        else
            return "GelloBoolean(ERROR)";
    }
    
    // From the GELLO spec
//  this    v2      and     or      xor     not     implies
//  false   false   false   false   false   true    true
//  false   true    false   true    true    true    true
//  false   unknown false   unknown unknown true    true
//  true    false   false   true    true    false   false
//  true    true    true    true    false   false   true
//  true    unknown unknown true    unknown false   unknown
//  unknown false   false   unknown unknown unknown unknown
//  unknown true    unknown true    unknown unknown true
//  unknown unknown unknown unknown unknown unknown unknown
        
    /** General table-driven implementation of two-operand boolean operators.
     */
    protected GelloBoolean dyadicOp(GelloBoolean v2, int[] opTable)
    {
        return new GelloBoolean(opTable[(3 * value) + v2.value]);
    }

    private static final int[] equalsTable = { 
        TRUE,       // FALSE, FALSE
        FALSE,      // FALSE, TRUE
        UNKNOWN,    // FALSE, UNKNOWN
        FALSE,      // TRUE, FALSE
        TRUE,       // TRUE, TRUE
        UNKNOWN,    // TRUE, UNKNOWN
        UNKNOWN,    // UNKNOWN, FALSE
        UNKNOWN,    // UNKNOWN, TRUE
        UNKNOWN     // UNKNOWN, UNKNOWN
    };

    public GelloBoolean equals(GelloBoolean v2) {
        
        return dyadicOp(v2, equalsTable);
    }

    private static final int[] andTable = { 
        FALSE,
        FALSE,
        FALSE,
        FALSE,
        TRUE,
        UNKNOWN,
        FALSE,
        UNKNOWN,
        UNKNOWN
    };

    public GelloBoolean and(GelloBoolean v2) {

        return dyadicOp(v2, andTable);
    }
    
    private static final int[] orTable = { 
        FALSE,
        TRUE,
        UNKNOWN,
        TRUE,
        TRUE,
        TRUE,
        UNKNOWN,
        TRUE,
        UNKNOWN
    };

    /** Logical-or operation on GELLO booleans */
    public GelloBoolean or(GelloBoolean v2) {

        return dyadicOp(v2, orTable);
    }
    
    private static final int[] xorTable = { 
        FALSE,
        TRUE,
        UNKNOWN,
        TRUE,
        FALSE,
        UNKNOWN,
        UNKNOWN,
        UNKNOWN,
        UNKNOWN
    };

    /** Logical-xor operation on GELLO booleans */
    public GelloBoolean xor(GelloBoolean v2) {

        return dyadicOp(v2, xorTable);
    }
    
    private static final int[] impliesTable = { 
        TRUE,
        TRUE,
        TRUE,
        FALSE,
        TRUE,
        UNKNOWN,
        UNKNOWN,
        TRUE,
        UNKNOWN
    };

    /** Logical-implies operation on GELLO booleans */
    public GelloBoolean implies(GelloBoolean v2) {
        
        return dyadicOp(v2, impliesTable);
    }
    
    /** Logical-not operation on GELLO booleans */
    public GelloBoolean not() {
        
        if (value == UNKNOWN)
            return new GelloBoolean(UNKNOWN);
        else
            return new GelloBoolean(value == FALSE);
    }

    /** Tests if the value is TRUE */
    public boolean isTrue() {
        return value == TRUE;
    }

    /** Tests if the value is FALSE */
    public boolean isFalse() {
        return value == FALSE;
    }

    /** Tests if the value is UNKNOWN */
    public boolean isUnknown() {
        return value == UNKNOWN;
    }
}
