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

import org.gello.runtime.GelloBoolean;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A single symbol in the symbol table.
 * @author Will Hartung
 * @author Erik Horstkotte
 */
public class Symbol implements NamingContext {

    private static Logger log = Logger.getLogger(Symbol.class.getName());
    
    /** The GELLO name for this symbol */
    private String name;
    /** The Java name for this symbol, or null if this symbol has no location. */
    private String javaName;
    /** The data type of this symbol */
    private Type type;
    /** True iff this symbol's value is constant. */
    private boolean constant;
    /** If constant==true, the constant Java value expression for this symbol, 
     * otherwise the Java initial value expression for this symbol. */
    private String value;

    public Symbol(String name, String javaName, Type type, boolean constant, String value) {
        this.name = name;
        this.javaName = javaName;
        this.type = type;
        this.constant = constant;
        this.value = value;
    }

    /** Map a user identifier to the object it refers to in this context */
    public Symbol resolveId(String id) {

        log.fine("id=" + id);

        // Ask the model if it has the specified member.
        Method getterMethod = BeanHelper.getGetterMethod(type, id);
        if (getterMethod != null) {

            // Build the Java expression to get its value
            // TODO: We need to special-case the situation where the property is a collection
            // to wrap the collection in a GelloSequence.
            String memberJavaName = getJavaName() + "." + getterMethod.getName() + "()";
            Type returnType = getterMethod.getGenericReturnType();

            // If the type is java.lang.Boolean
            if (returnType == Boolean.class) {

                // Wrap the value in a GelloBoolean
                memberJavaName = "new GelloBoolean(" + memberJavaName + ")";

                // Change the returnType
                returnType = GelloBoolean.class;
            }

            // TODO: We need to also special-case the situation where the property is a collection
            // to wrap the collection in a GelloSet or GelloSequence.

            Symbol s = new Symbol(null, memberJavaName, returnType, false, memberJavaName);
            
            log.fine("Symbol=" + s);
            return s;
        }
        else {

            log.fine("no member named \"" + id + "\"");
            return null;
        }
    }
    
    public String toString() {
        
        return "Symbol(" + 
                    "name=" + name +
                    ", javaName=" + javaName +
                    ", type=" + type +
                    ", constant=" + constant +
                    ", value=" + value +
                    ", super=" + super.toString() +
               ")";
    }
    
    public String getName() {
        return name;
    }

    public String getJavaName() {
        return javaName;
    }
    
    public Type getType() {
        return type;
    }

    public boolean isConstant() {
        return constant;
    }
    
    public String getValue() {
        return value;
    }
}
