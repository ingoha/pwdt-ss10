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

import java.lang.reflect.Type;

/**
 * Type information about an object that is inferred from location to be a collection.
 * @author Erik Horstkotte
 */
public class CollectionInfo {

    public Type collectionType;
    public Type elementType;
    boolean needsWrapping;

    public CollectionInfo(Type collectionType, Type elementType, boolean needsWrapping) {

        this.collectionType = collectionType;
        this.elementType = elementType;
        this.needsWrapping = needsWrapping;
    }

    public String toString() {

        return "CollectionInfo(" + 
                    "collectionType=" + collectionType +
                    ", elementType=" + elementType +
                    ", needsWrapping=" + needsWrapping +
               ")";
    }
}

