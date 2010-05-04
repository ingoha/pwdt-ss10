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

import org.gello.grammar.node.Token;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.ArrayList;
import org.gello.runtime.CompileMessage;

/**
 * The result returned by Compiler.compile.
 * @author Erik Horstkotte
 */
public class CompileResult {
    
    /** Creates a new default instance of CompileResult.
     */
    public CompileResult() {
        
        this.successful = true;
        this.messages = new ArrayList<CompileMessage>();
        
    }

    /** Creates a new instance of CompileResult.
     * @param successful True iff the result is successful.
     * @param messages The collection of messages in this result.
     */
    public CompileResult(boolean successful, CompileMessage[] messages) {
        this.successful = successful;
        this.messages = new ArrayList<CompileMessage>(Arrays.asList(messages));
    }

    /** Converts the result to a human-readable string for debugging. */
    public String toString() {
        
        return "CompileResult(" + 
                    "successful=" + successful +
                    ", messages=" + messages +
               ")";
    }
    
    /** Adds a message to the result, marking the result as not successful if the
     * level is error or fatal.
     * @param token A scanning token to define the location of the message.
     * @param key The properties file key for the message text.
     * @param args The subsitution parameter values for the message text.
     */
    public void message(CompileMessage.Severity level, Token t, String key, Object[] args) {
        
        int line = 0;
        int column = 0;
        if (t != null) {
            line = t.getLine();
            column = t.getPos();
        }
        
        // If the level is a "failing level"
        if (level == CompileMessage.Severity.error || level == CompileMessage.Severity.fatal) {

            // We're no longer successful
            setSuccessful(false);
        }
        
        // Add the new message
        messages.add(new CompileMessage(line, column, level, key, args));
    }

    public void info(Token t, String key, Object[] args) {
        
        message(CompileMessage.Severity.informational, t, key, args);
    }
    
    public void warning(Token t, String key, Object[] args) {
        
        message(CompileMessage.Severity.warning, t, key, args);
    }
    
    public void error(Token t, String key, Object[] args) {
        
        message(CompileMessage.Severity.error, t, key, args);
    }
    
    public void fatal(Token t, String key, Object[] args) {
        
        message(CompileMessage.Severity.fatal, t, key, args);
    }
    
    /** True iff the compilation was successful (no errors). */
    private boolean successful;

    /**
     * Getter for property successful.
     * @return Value of property successful.
     */
    public boolean isSuccessful() {
        return this.successful;
    }

    /**
     * Setter for property successful.
     * @param successful New value of property successful.
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /** The Collection of messages generated during compilation. */
    private ArrayList<CompileMessage> messages;

    /**
     * Indexed getter for property messages.
     * @param index Index of the property.
     * @return Value of the property at <CODE>index</CODE>.
     */
    public CompileMessage getMessages(int index) {
        return this.messages.get(index);
    }

    /**
     * Getter for property messages.
     * @return Value of property messages.
     */
    public CompileMessage[] getMessages() {
        
        if (this.messages == null) {
            return null;
        }
        return this.messages.toArray(new CompileMessage[0]);
    }

    /**
     * Indexed setter for property messages.
     * @param index Index of the property.
     * @param messages New value of the property at <CODE>index</CODE>.
     */
    public void setMessages(int index, CompileMessage message) {
        this.messages.set(index, message);
    }

    /**
     * Setter for property messages.
     * @param messages New value of property messages.
     */
    public void setMessages(CompileMessage[] messages) {
        this.messages = new ArrayList<CompileMessage>(Arrays.asList(messages));
    }
}
