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

import java.util.Arrays;

/**
 * One compilation result message returned from Compiler.compile.
 * @author Erik Horstkotte
 */
public class CompileMessage {
    
    public enum Severity { informational, warning, error, fatal };

    /** Creates a new instance of CompileMessage */
    public CompileMessage(int line, int column, Severity severity, String key, Object[] parameters) {
        
        this.line = line;
        this.column = column;
        this.severity = severity;
        this.key = key;
        this.parameters = parameters;
    }

    private String asList(Object[] array) {
        
        if (array == null)
            return "null";
        else
            return Arrays.asList(array).toString();
    }
    
    public String toString() {

        return "CompileMessage(" +
                    "line=" + line +
                    ", column=" + column +
                    ", severity=" + severity +
                    ", key=" + key +
                    ", parameters=" + asList(parameters) +
               ")";
    }
    
    /** The source line number this message is associated with (base 1). */
    private int line;

    /**
     * Getter for property line.
     * @return Value of property line.
     */
    public int getLine() {
        return this.line;
    }

    /**
     * Setter for property line.
     * @param line New value of property line.
     */
    public void setLine(int line) {
        this.line = line;
    }

    /** The source column number this message is associated with (base 1). */
    private int column;

    /**
     * Getter for property column.
     * @return Value of property column.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Setter for property column.
     * @param column New value of property column.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /** The unique key for this message. */
    private String key;

    /**
     * Getter for property key.
     * @return Value of property key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Setter for property key.
     * @param key New value of property key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /** The substitution parameters for the message. */
    private Object[] parameters;

    /**
     * Indexed getter for property parameters.
     * @param index Index of the property.
     * @return Value of the property at <CODE>index</CODE>.
     */
    public Object getParameters(int index) {
        return this.parameters[index];
    }

    /**
     * Getter for property parameters.
     * @return Value of property parameters.
     */
    public Object[] getParameters() {
        return this.parameters;
    }

    /**
     * Indexed setter for property parameters.
     * @param index Index of the property.
     * @param parameters New value of the property at <CODE>index</CODE>.
     */
    public void setParameters(int index, Object parameters) {
        this.parameters[index] = parameters;
    }

    /**
     * Setter for property parameters.
     * @param parameters New value of property parameters.
     */
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    /**
     * Holds value of property severity.
     */
    private Severity severity;

    /**
     * Getter for property severity.
     * @return Value of property severity.
     */
    public Severity getSeverity() {
        return this.severity;
    }

    /**
     * Setter for property severity.
     * @param severity New value of property severity.
     */
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
    
}
