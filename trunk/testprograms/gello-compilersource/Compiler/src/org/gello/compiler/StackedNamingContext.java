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

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * A stack of naming contexts, which are consulted in top to bottom order to resolve ids.
 * @author Erik Horstkotte
 */
public class StackedNamingContext implements NamingContext {
    
    private static Logger log = Logger.getLogger(StackedNamingContext.class.getName());

    /** The stack of naming contexts */
    private List<NamingContext> contextStack = new ArrayList<NamingContext>();

    /** Creates a new instance of StackedNamingContext */
    public StackedNamingContext() {
    }

    /** Map a user identifier to the object it refers to in this context */
    public Symbol resolveId(String id) {

        // For each context in the stack, from the top down
        ListIterator<NamingContext> ci = contextStack.listIterator();
        while (ci.hasNext()) {

            // Get the context
            NamingContext nc = ci.next();
            
            // Ask the context to resolve the id
            Symbol sym = nc.resolveId(id);
            
            // If the context resolved the id
            if (sym != null) {
                
                // Return the symbol
                return sym;
            }
        }

        // The id didn't appear in any context
        return null;
    }
    
    /** Push a new context onto the stack */
    public void push(NamingContext nc)
    {
        // Insert the new context at the front (top) of the stack.
        contextStack.add(0, nc);
    }
    
    /** Pop the top context off the stack */
    public NamingContext pop()
    {
        // Get the top context
        NamingContext nc = contextStack.get(0);
        
        // Drop it from the stack
        contextStack.remove(0);
        
        // Return it
        return nc;
    }
}
