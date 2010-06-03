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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The runtime representation of a GELLO "Tuple". Tuples are
 * collections of name / value pairs, somewhat like a Java Map.
 * Unlike a Java Map, however, the parts of a Tuple are ordered.
 * @author Erik Horstkotte
 */
public class GelloTuple {
    
    public static class Part {

        /** Construct an instance
         * @param name The name for the newly constructed Part.
         * @param value The value of the newly constructed Part.
         */
        public Part(String name, Object value) {
            
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
        private Object value;

        /** Gets the value of this Part.
         * @return Value of property value.
         */
        public Object getValue() {
            return this.value;
        }
    }

    /** The parts that make up this Tuple, in declaration order. */
    protected ArrayList<Part> parts;
    /** The parts that make up this Tuple, by name */
    protected Map<String, Part> partsByName;

    /** Creates a new instance of Tuple */
    public GelloTuple() {
        
        parts = new ArrayList<Part>();
        partsByName = new HashMap<String, Part>();
    }
    
    /** Creates a new Tuple from the attributes of a Part */
    public GelloTuple(String name, Object value) {

        parts = new ArrayList<Part>(1);
        partsByName = new HashMap<String, Part>(1);

        add(name, value);
    }

    /** Creates a new instance of Tuple */
    public GelloTuple(Collection<Part> src) {

        parts = new ArrayList<Part>(src.size());
        partsByName = new HashMap<String, Part>(parts.size());

        Iterator<Part> it = src.iterator();
        while (it.hasNext()) {
            
            add(it.next());
        }
    }

    /** Creates a new instance of Tuple */
    public GelloTuple(Part[] src) {

        parts = new ArrayList<Part>(src.length);
        partsByName = new HashMap<String, Part>(parts.size());
        
        for (int i = 0; i < src.length; i++) {
            add(src[i]);
        }
    }

    /** Convert to a human-readable string for debugging */
    public String toString() {

        return "GelloTuple(" + parts + ")";
    }

    /** Add a part to a Tuple.
     * NOTE: We enforce nonduplication of part names at compile time,
     * so we don't need to check it here.
     */
    public void add(String name, Object value) {

        add(new Part(name, value));
    }

    /** Add a part to a Tuple.
     * NOTE: We enforce nonduplication of part names at compile time,
     * so we don't need to check it here.
     */
    public void add(Part newPart) {

        parts.add(newPart);
        partsByName.put(newPart.getName(), newPart);
    }

    /** Gets the number of parts in the Tuple.
     * See: GELLO-spec-031004.pdf section 6.10.1.
     */
    public int size() {
        return parts.size();
    }

    /** Gets the value of the named part of the Tuple.
     * See: GELLO-spec-031004.pdf section 6.10.2.
     */
    public Object getValue(String name) {

        Part p = partsByName.get(name);
        if (p != null) 
            return p.getValue();
        else
            return new GelloBoolean(GelloBoolean.UNKNOWN);
    }
    
    /** Gets the name of the part at the specified index in the Tuple.
     * See: GELLO-spec-031004.pdf section 6.10.3.
     *
     * NOTE: The specification calls for this method to return a GELLO
     * undefined value if the index is out of range, or the String name
     * of the part if the index is in range. But these are not
     * compatible types in our implementation. Given that the
     * alternative is returning some type of wrapper class, and that no
     * one is likely to ever actually use this method, we choose to
     * violate the spec here by throwing GelloException instead of
     * returning undefined.
     */
    public String getElemName(int index) throws GelloException {
        
        if (index < 0 || index >= parts.size()) {
            throw new GelloException("index out of bounds");
        }

        return parts.get(index).getName();
    }
    
    /** Gets the type of the value of the part at the specified index
     * in the Tuple.
     * See: GELLO-spec-031004.pdf section 6.10.4.
     *
     * NOTE: The specification calls for this method to return a GELLO
     * undefined value if the index is out of range, or the String name
     * of the type of the value of the part if the index is in range.
     * But these are not compatible types in our implementation. Given
     * that the alternative is returning some type of wrapper class,
     * and that no one is likely to ever actually use this method, we
     * choose to violate the spec here by throwing GelloException
     * instead of returning undefined.
     */
    public String getElemType(int index) throws GelloException {
        
        if (index < 0 || index >= parts.size()) {
            throw new GelloException("index out of bounds");
        }

        return parts.get(index).getName();
    }
    
    /** Gets the type of the value of the named part of this Tuple.
     * See: GELLO-spec-031004.pdf section 6.10.4.
     *
     * NOTE: The specification calls for this method to return a GELLO
     * undefined value if the Tuple has no part with the specified
     * name, or the String name of the type of the value of the part
     * if the Tuple has a part with the specified name.
     * But these are not compatible types in our implementation. Given
     * that the alternative is returning some type of wrapper class,
     * and that no one is likely to ever actually use this method, we
     * choose to violate the spec here by throwing GelloException
     * instead of returning undefined.
     */
    public String getElemType(String name) throws GelloException {
        
        Part p = partsByName.get(name);
        if (p != null)
            return p.getValue().getClass().getName();
        else 
            throw new GelloException("Tuple has no part with the specified name.");
    }
}
