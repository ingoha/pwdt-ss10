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

/**
 * The interface for the anonymous class emitted by the compiler to implement
 * a join() operation without an order expression.
 * @author Erik Horstkotte
 */
public interface GelloBagJoiner {
    public GelloBag join();
}
