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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The compile-time representation of a GELLO Tuple type.
 * @author Erik Horstkotte
 */
public class CompilerTuple {
    
    public static class Part {

        /** Construct an instance
         * @param name The name for the newly constructed Part.
         * @param value The CompilerExpression to compute the value of the newly constructed Part.
         */
        public Part(String name, CompilerExpression value) {
            
            this.name = name;
            this.value = value;
        }

        public String toString() {
            
            return "Part(" + 
                        "name=" + name +
                        ", value=" + value +
                   ")";
        }

        /** The name of this Part. */
        private String name;

        /** Gets the name of this Part.
         * @return Value of property name.
         */
        public String getName() {
            return this.name;
        }

        /** The value of this Part. */
        private CompilerExpression value;

        /** Gets the value of this Part.
         * @return Value of property value.
         */
        public CompilerExpression getValue() {
            return this.value;
        }
    }

    /** The parts that make up this Tuple. */
    protected ArrayList<Part> parts;
    protected Map<String, Part> partsByName;
    
    /** Creates a new instance of CompilerTuple */
    public CompilerTuple() {

        parts = new ArrayList<Part>();
        partsByName = new HashMap<String, Part>();
    }
    
    /** Creates a new instance of CompilerTuple */
    public CompilerTuple(String name, CompilerExpression value) {

        parts = new ArrayList<Part>();
        partsByName = new HashMap<String, Part>();

        add(name, value);
    }
    
    /** Tests if this Tuple contains a part with the specified name */
    public boolean containsPart(String name) {

        return partsByName.containsKey(name);
    }

    /** Adds a part to this Tuple */
    public void add(String name, CompilerExpression value) {

        add(new Part(name, value));
    }

    /** Adds a part to this Tuple */
    public void add(Part newPart) {

        parts.add(newPart);
        partsByName.put(newPart.getName(), newPart);
    }

    /** Gets the CompilerExpression to compute the value of the named part */
    public CompilerExpression getValue(String name) {
        
        Part p = partsByName.get(name);
        if (p == null)
            return null;
        else
            return p.getValue();
    }

    /** Gets the Parts in this Tuple */
    public Part[] getParts() {

        return parts.toArray(new Part[0]);
    }
}
