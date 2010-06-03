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


package org.gello.model.sample;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import org.gello.runtime.CompileMessage;
import org.gello.runtime.GelloModelException;
import org.gello.runtime.IGelloModel;

/**
 * A simplified data model that reflects the current samples we've seen.
 * @author Will Hartung
 * @author Erik Horstkotte
 */
public class SampleModel implements IGelloModel {
    
    private static Logger log = Logger.getLogger(SampleModel.class.getName());
    
    /** The root object for this model */
    private Object root;
    
    /** Creates a new instance of the model */
    public SampleModel(Object root) {
        this.root = root;
    }
    
    /**
     * Returns the root object in the model
     */
    public Object getContextRoot() {
        return root;
    }
    
    /**
     * Returns the class of the root object in the selected context of the model.
     *
     * @return The java Class of the root object in the selected context of the model.
     */
    public Class getContextRootClass() {
        return Patient.class;
    }
    
    /**
     * Save the information in the model to an input stream
     *
     * @param path
     */
    public void saveData(OutputStream stream) throws GelloModelException {
        // TODO
    }
    
    /**
     * Parses an input stream for errors and returns a list of exceptions that occurred on compilation
     *
     * @param path
     */
    public CompileMessage[] parseData(InputStream stream) throws GelloModelException {
        // TODO
        return new CompileMessage[0];
    }
    
    /**
     * Loads the model with information from an input stream
     *
     * @param path
     */
    public void loadData(InputStream stream) throws GelloModelException {
        // TODO
    }
    
    /**
     * Loads the model with default information
     */
    public void loadDataDefault() throws GelloModelException {
        // TODO
    }
}
