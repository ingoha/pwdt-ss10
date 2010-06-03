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

import org.gello.compiler.*;

/**
 * The interface to compiled GELLO units.
 * @author Erik Horstkotte
 */
public interface GelloRunnable {
    
    /** Tests if the information required to execute this runnable is available from the specified model */
    public boolean isRunnable(IGelloModel model);

    /** Executes this runnable against the specified model, and returns the computed result. */
    public String run(IGelloModel model) throws GelloException;
}
