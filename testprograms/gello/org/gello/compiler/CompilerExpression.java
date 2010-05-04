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
 * The compile-time representation of a (partial) compiled GELLO expression.
 * @author Erik Horstkotte
 */
public class CompilerExpression {
    
    /** The data type of the expression */
    protected Type type;
    /** True iff the expression is constant */
    protected boolean constant;
    /** The Java source to evaluate the expression */
    protected String expression;

    /** Creates a new instance of GelloExpression */
    public CompilerExpression(Type type, boolean constant, String expression) {
        
        this.type = type;
        this.constant = constant;
        this.expression = expression;
    }
    
    public String toString() {
        
        return "GelloExpression(" +
                    "type=" + type +
                    ", constant=" + constant +
                    ", expression=" + expression +
               ")";
    }
    
    public Type getType() {
        return type;
    }
    
    public boolean isConstant() {
        return constant;
    }
    
    public String getExpression() {
        return expression;
    }
}
