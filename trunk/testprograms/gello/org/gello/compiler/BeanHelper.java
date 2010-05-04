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

import java.lang.StringBuffer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Static method class to inspect information about Java Beans.
 * @author Erik Horstkotte
 */
public class BeanHelper {

    private static Logger log = Logger.getLogger(BeanHelper.class.getName());
    
    /** Hashmap of Method information for getter methods by class,property */
    private static HashMap<Key, Method> getterMap = new HashMap<Key, Method>();

    /** A class,property key for the maps */
    public static class Key {
        
        public Key(Class type, String property) {
            
            this.type = type;
            this.property = property.toLowerCase();     // Java property naming interferes with GELLO casing.
        }

        public String toString() {
            
            return "Key(type=" + type + ", property=" + property + ")";
        }
        
        public int hashCode() {

            return type.hashCode() + property.hashCode();
        }

        public boolean equals(Object o) {
            
            if (!(o instanceof Key))
                return false;
            
            Key right = (Key) o;
            return this.type == right.type && this.property.equals(right.property);
        }

        /**
         * Holds value of property type.
         */
        private Class type;

        /**
         * Getter for property type.
         * @return Value of property type.
         */
        public Class getType() {
            return this.type;
        }

        /**
         * Holds value of property property.
         */
        private String property;

        /**
         * Getter for property property.
         * @return Value of property property.
         */
        public String getProperty() {
            return this.property;
        }
    }

    /** Creates a new instance of BeanHelper */
    public BeanHelper() {
    }

    /** Helper to load the information for a class into the cache */
    protected static void loadClassInfo(Class mapType, Class realType)
    {
        log.fine("mapType=" + mapType + ", realType=" + realType);

        // If this class extends another
        if (realType.getSuperclass() != null) {

            // Load the property getters for the superclass. We'll overwrite them if necessary.
            loadClassInfo(mapType, realType.getSuperclass());
        }

        // For each method of this class
        Method[] methods = realType.getMethods();
        for (int i = 0; i < methods.length; i++) {

            Method m = methods[i];
            
            // If the method is public and takes no arguments
            if (Modifier.isPublic(m.getModifiers()) && m.getParameterTypes().length == 0) {

                // If the method name starts with "get", add info for this method to the cache
                addMethodInfo("get", mapType, m);
                
                // If the method name starts with "is", add info for this method to the cache
                addMethodInfo("is", mapType, m);
            }
        }
    }

    /** If the name of the method starts with the prefix, infer the property name and add the method to the map */
    protected static void addMethodInfo(String prefix, Class mapType, Method m) {
        
        String methodName = m.getName();
        if (methodName.startsWith(prefix)) {

            // Compute the inferred property name
            methodName = methodName.substring(prefix.length());
            String property = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

            log.finest("mapType=" + mapType + ", property=" + property + ", method=" + m);

            // Add the info for this method to the cache
            getterMap.put(new Key(mapType, property), m);
        }
    }

    /** If a getter method for the named property exists in the specified class, return its Method */
    public static Method getGetterMethod(Class type, String property)
    {
        // If the cache doesn't already have info for this class
        Method m = getterMap.get(new Key(type, property));
        if (m == null) {

            // Load it
            loadClassInfo(type, type);

            // Get it again
            m = getterMap.get(new Key(type, property));
        }
        
        // Return what ya got
        return m;
    }
    
    /** If a getter method for the named property exists in the specified Type, return its Method */
    public static Method getGetterMethod(Type type, String property)
    {
        if (type instanceof ParameterizedType) {
            
            // Try again from the raw type.
            // TODO: Will this need decoration with the type arguments?
            ParameterizedType pType = (ParameterizedType) type;
            return getGetterMethod(pType.getRawType(), property);
        }
        else if (type instanceof Class) {
            
            return getGetterMethod((Class) type, property);
        }
        else {

            // Some kind of type we don't understand
            log.severe("don't know how to get methods for Type \"" + type + "\"");
            return null;
        }
    }
    
    /** Gets the Java name for the specified type */
    public static String getTypeName(Type type) {

        if (type instanceof ParameterizedType) {
            
            ParameterizedType pType = (ParameterizedType) type;
            Type[] parameterTypes = pType.getActualTypeArguments();

            StringBuffer buf = new StringBuffer(getTypeName(pType.getRawType()));
            buf.append("<");
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i != 0) {
                    buf.append(", ");
                }
                buf.append(getTypeName(parameterTypes[i]));
            }
            buf.append(">");
            return buf.toString();
        }
        else if (type instanceof Class) {

            return ((Class) type).getName();
        }
        else {

            // How did you get here?
            log.severe("don't know how to construct the Java Name of Type \"" + type + "\"");
            return "java.lang.Object";
        }
    }
}
