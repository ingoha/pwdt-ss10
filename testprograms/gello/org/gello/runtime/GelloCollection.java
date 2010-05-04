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
import java.util.Iterator;

/**
 * The common base class for the implementation of the GELLO collection data types.
 * @author Will Hartung
 * @author Erik Horstkotte
 */
public class GelloCollection<T> implements Collection<T> {

    private Collection<T> col;
    
    /** Creates a new instance of GelloCollection */
    public GelloCollection() {
        col = new ArrayList<T>();
    }
    
    public GelloCollection(T singleton) {
        col = new ArrayList<T>();
        col.add(singleton);
    }
    
    public GelloCollection(Collection<T> col) {
        this.col = col;
    }
    
    public int size() {
        return col.size();
    }
    
    public boolean includes(T o) {
        return col.contains(o);
    }
    
    public boolean excludes(T o) {
        return !col.contains(o);
    }
    
    public int count(T o) {
        int cnt = 0;
        for(T i:col) {
            if (i != null && i.equals(o)) {
                cnt++;
            }
        }
        return cnt;
    }
    
    public boolean includesAll(GelloCollection<T> c) {
        return col.containsAll(c);
    }
    
    public boolean excludesAll(GelloCollection<T> c) {
        boolean result = true;
        for (T o:c) {
            result = result && includes(o);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return col.isEmpty();
    }

    public boolean contains(Object o) {
        Collection c = (Collection)col;
        return c.contains(o);
    }

    public Iterator<T> iterator() {
        return col.iterator();
    }

    public Object[] toArray() {
        return col.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return col.toArray(a);
    }

    public boolean add(T o) {
        return col.add(o);
    }

    public boolean remove(Object o) {
        return col.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return col.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return col.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return col.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return col.retainAll(c);
    }

    public void clear() {
        col.clear();
    }
    
    protected Collection<T> getCollection() {
        return col;
    }
    
    /** Tests if the collection contains an element that matches the supplied filter.
     * @param filter The filter class that checks whether single elements match.
     * @return true iff the collection contains a matching element.
     */
    public GelloBoolean exists(GelloFilter<T> filter) {
        Iterator<T> it = col.iterator();
        while(it.hasNext()) {
            if (filter.matches(it.next()).isTrue()) {
                return new GelloBoolean(true);
            }
        }
        return new GelloBoolean(false);
    }
}
