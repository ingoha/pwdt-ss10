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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The implementation of the GELLO Set data type.
 * @author Will Hartung
 * @author Erik Horstkotte
 */
public class GelloSet<T> extends GelloCollection<T> implements Set<T> {
    
    /** Creates a new instance of GelloSet */
    public GelloSet() {
        super();
    }
    
    public GelloSet(Collection<T> col) {
        super(new HashSet<T>(col.size()));
        addAll(col);
    }
    
    public GelloSet<T> union(GelloBag<T> bag) {
        GelloSet<T> result = new GelloSet<T>();
        result.addAll(this);
        result.addAll(bag);
        return result;
    }
    
    public GelloSet<T> union(GelloSet<T> set) {
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
    
    public GelloSet<T> including(T o) {
        GelloSet<T> result = new GelloSet<T>(this);
        result.add(o);
        return result;
    }
    
    public GelloSet<T> excluding(T o) {
        GelloSet<T> result = new GelloSet<T>();
        for(T i:this) {
            if (i.equals(o)) {
                result.add(i);
            }
        }
        return result;
    }
    
    public GelloSet<T> symetricDifference(GelloSet<T> set) {
        GelloSet<T> da = new GelloSet<T>();
        GelloSet<T> db = new GelloSet<T>();

        da.addAll(this);
        da.removeAll(set);
        db.addAll(set);
        db.removeAll(this);
        da.addAll(db);
        return da;
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
