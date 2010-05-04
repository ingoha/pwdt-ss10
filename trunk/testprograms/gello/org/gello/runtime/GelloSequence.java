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
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/*
 * The implementation of the GELLO Sequence data type.
 * @author Will Hartung
 * @author Erik Horstkotte
 * @author Zubin Tiku
 */
public class GelloSequence<T> extends GelloCollection<T> implements List<T> {
    
    /** Creates a new instance of GelloSequence */
    public GelloSequence() {
        super();
    }
    
    public GelloSequence(Collection<T> col) {
        super(new ArrayList<T>(col.size()));
        addAll(col);
        //Collections.sort((List)getCollection());
    }
    
    public GelloSequence<T> union(GelloSequence<T> seq) {
        GelloSequence<T> result = new GelloSequence<T>();
        result.addAll(this);
        result.addAll(seq);
        //Collections.sort((List)result);
        return result;
    }
    
    public GelloSequence<T> append(T o) {
        GelloSequence<T> result = new GelloSequence<T>(this);
        result.add(o);
        //Collections.sort((List)result);
        return result;
    }
    
    public GelloSequence<T> prepend(T o) {
        GelloSequence<T> result = new GelloSequence<T>(this);
        result.add(o);
        //Collections.sort((List)result);
        return result;
    }
    
    public GelloSequence<T> insertAt(T o) {
        GelloSequence<T> result = new GelloSequence<T>(this);
        result.add(o);
        //Collections.sort((List)result);
        return result;
    }
    
    public GelloSequence<T> subSequence(int lower, int upper) {
        GelloSequence<T> result = new GelloSequence();
        result.addAll(this.subList(lower, upper));
        return result;
    }
    
    public T at(int i) {
        return this.get(i);
    }
        
    public T first() {
        return get(0);
    }
    
    public T last() {
        return get(size() - 1);
    }
    
    public GelloSequence<T> including(T o) {
        GelloSequence<T> result = new GelloSequence<T>(this);
        result.add(o);
        return result;
    }
    
    public GelloSequence<T> excluding(T o) {
        GelloSequence<T> result = new GelloSequence<T>();
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
    
    public boolean addAll(int index, Collection<? extends T> c) {
        return getList().addAll(index, c);
    }
    
    private List<T> getList() {
        return (List<T>)getCollection();
    }
    
    public T get(int index) {
        return getList().get(index);
    }
    
    public T set(int index, T element) {
        return getList().set(index, element);
    }
    
    public void add(int index, T element) {
        getList().add(index, element);
    }
    
    public T remove(int index) {
        return getList().remove(index);
    }
    
    public int indexOf(Object o) {
        return getList().indexOf(o);
    }
    
    public int lastIndexOf(Object o) {
        return getList().lastIndexOf(o);
    }
    
    public ListIterator<T> listIterator() {
        return getList().listIterator();
    }
    
    public ListIterator<T> listIterator(int index) {
        return getList().listIterator(index);
    }
    
    public List<T> subList(int fromIndex, int toIndex) {
        return getList().subList(fromIndex, toIndex);
    }
}
