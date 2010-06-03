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

import org.gello.grammar.analysis.DepthFirstAdapter;
import org.gello.grammar.node.Node;
import org.gello.grammar.node.Token;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A sable tree adapter that dumps the parse tree for debugging
 * @author Erik Horstkotte
 */
public class DumpingTreeAdapter extends DepthFirstAdapter {

    //private static Logger log = Logger.getLogger(DumpingTreeAdapter.class.getName());
    
    private int level;

    /** Creates a new instance of DumpingTreeAdapter */
    public DumpingTreeAdapter() {
        
        this.level = 0;
    }

    private String spaces(int count) {
        
        if (count <= 0) {
            return "";
        }
        
        StringBuffer buf = new StringBuffer(count);
        while (count-- > 0) {
            buf.append(' ');
        }
        return buf.toString();
    }

    private void debug(Object o)
    {
        //log.finest(o.toString());
        System.out.println(spaces(level * 2) + o.toString());
    }

    /** Called when each node is entered during the tree walk */
    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Print this node
       	debug(node.getClass().getName() + " - " + node);

        // Increase the level
        level++;
    }

    /** Called when each node is exited during the tree walk */
    public void defaultOut(Node node)
    {
        // Decrease the level
        level--;
    }
}
