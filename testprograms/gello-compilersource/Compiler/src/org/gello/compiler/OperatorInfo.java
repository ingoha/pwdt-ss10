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

import java.lang.reflect.Type;

/**
 * Compile-time information about an operator.
 * @author Erik Horstkotte
 */
public class OperatorInfo {
    
    /** The name of the runtime library method that implements this operator. */
    private String libraryName;
    /** The type returned by the runtime library method. */
    private Type returnType;
    /** True iff the return type from the runtime library method is parameterized. */
    private boolean returnTypeParameterized;

    /** Creates a new instance of OperatorInfo */
    public OperatorInfo(String libraryName, Type returnType, boolean returnTypeParameterized) {
        
        this.libraryName = libraryName;
        this.returnType = returnType;
        this.returnTypeParameterized = returnTypeParameterized;
    }

    public String toString() {
        
        return "OperatorInfo(" +
                    "libraryName=" + libraryName +
                    ", returnType=" + returnType +
                    ", returnTypeParameterized=" + returnTypeParameterized +
               ")";
    }
    
    /**
     * Gets the name of the runtime library method that implements this operator.
     * @return The name of the runtime library method that implements this operator.
     */
    public String getLibraryName() {
        return this.libraryName;
    }

    /**
     * Gets the type returned by the runtime library method. For methods that
     * return a parameterize type, this is the generic type.
     *
     * @return The type returned by the runtime library method.
     */
    public Type getReturnType() {
        return this.returnType;
    }

    /**
     * Tests if the return type from the runtime library method is parameterized.
     * 
     * @return True iff the return type from the runtime library method is parameterized.
     */
    public boolean isReturnTypeParameterized() {
        return this.returnTypeParameterized;
    }
}
