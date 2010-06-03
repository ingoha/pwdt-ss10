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
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The naming context for a symbol table, used for both the
 * predefined symbols and the user-defined symbols.
 * @author Erik Horstkotte
 */
public class TableNamingContext implements NamingContext {
    
    private static Logger log = Logger.getLogger(TableNamingContext.class.getName());

    /** The stack of in-scope symbols. The front of the list is the top of the stack. */
    protected List<Symbol> symbols = new ArrayList<Symbol>();

    /** Creates a new instance of PredefinedNamingContext */
    public TableNamingContext() {
    }

    /** Map a user identifier to the object it refers to in this context */
    public Symbol resolveId(String id) {
        
        ListIterator<Symbol> it = symbols.listIterator();
        while (it.hasNext()) {
            
            Symbol s = it.next();
            if (s.getName().equals(id)) {
                return s;
            }
        }

        return null;
    }
    
    /** The code is entering the scope of symbol s */
    public void enterScope(Symbol s) {

        symbols.add(0, s);
    }
    
    /** The code is leaving the scope of symbol s */
    public void leaveScope(Symbol s) {

        assert(symbols.get(0) == s);
        symbols.remove(s);
    }
}
