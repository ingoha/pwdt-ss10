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
import java.util.Collection;

/**
 * The implementation of the GELLO Bag data type.
 * @author Will Hartung
 * @author Erik Horstkotte
 */
public class GelloBag<T> extends GelloCollection<T> {
    
    /** Creates a new instance of GelloBag */
    public GelloBag() {
        super();
    }
    
    public GelloBag(Collection<T> col) {
        super(new ArrayList<T>(col.size()));
        addAll(col);
    }
    
    public GelloBag<T> union(GelloBag<T> bag) {
        GelloBag<T> result = new GelloBag<T>();
        result.addAll(this);
        result.addAll(bag);
        return result;
    }
    
    public GelloBag<T> union(GelloSet<T> set) {
        return union(set.asBag());
    }
    
    public GelloBag<T> intersection(GelloBag<T> bag) {
        GelloBag<T> result = new GelloBag<T>();
        result.addAll(this);
        result.retainAll(bag);
        return result;
    }
    
    public GelloSet<T> intersection(GelloSet<T> set) {
        GelloSet<T> result = new GelloSet<T>();
        result.addAll(this);
        result.retainAll(set);
        return result;
    }
    
    public GelloBag<T> including(T o) {
        GelloBag<T> result = new GelloBag<T>(this);
        result.add(o);
        return result;
    }
    
    public GelloBag<T> excluding(T o) {
        GelloBag<T> result = new GelloBag<T>();
        for(T i:this) {
            if (i.equals(o)) {
                result.add(i);
            }
        }
        return result;
    }
    
    public GelloBag<T> asBag() {
        GelloBag<T> result = new GelloBag<T>();
        result.addAll(this);
        return result;
    }
    
    public GelloSet<T> asSet() {
        GelloSet<T> result = new GelloSet<T>();
        result.addAll(this);
        return result;
    }
    
    public GelloSequence<T> asSequence() {
        GelloSequence<T> result = new GelloSequence<T>();
        result.addAll(this);
        return result;
    }
}
