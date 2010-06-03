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

/**
 * The compile-time representation of an object naming context.
 *
 * The majority of the time, object names are relative to the base
 * naming context, which contains the all user-defined variables
 * that are currently in scope, the predefined constants, and the
 * data model root objects.
 *
 * When compiling certain operators, such as the collection select
 * operator, the inner expression needs to be compiled against a
 * naming context that also includes the implicit loop variable.
 * This will be handled by pushing a NestedNamingContext onto the
 * naming context stack before compiling the inner expression, and
 * popping it off afterwards.
 *
 * Also, when compiling dotted name references, the members need
 * to be looked up in the base object - and only in the base object.
 * This will be handled by using a NestedNamingContext *instead*
 * of the active naming context stack.
 *
 * @author Erik Horstkotte
 */
public interface NamingContext {

    /** Map a user identifier to the object it refers to in this context */
    public Symbol resolveId(String id);
}
