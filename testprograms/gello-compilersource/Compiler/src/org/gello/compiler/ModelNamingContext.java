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

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gello.runtime.GelloBoolean;
import org.gello.runtime.IGelloModel;

/**
 * A naming context based solely on the GELLO data model.
 * @author Erik Horstkotte
 */
public class ModelNamingContext implements NamingContext {
    
    private static Logger log = Logger.getLogger(ModelNamingContext.class.getName());
    
    /** The GELLO model we are compiling against */
    private IGelloModel model;
    /** The Java name of the model object */
    private String modelJavaName;
    
    /** Creates a new instance of ModelNamingContext */
    public ModelNamingContext(IGelloModel model, String modelJavaName) {

        this.model = model;
        this.modelJavaName = modelJavaName;
    }

    /** Map a user identifier to the object it refers to in this context */
    public Symbol resolveId(String id) {

        log.fine("id=" + id);

        // Get the class of the context root object from the model
        Class contextRootClass = model.getContextRootClass();

        // Find the getter method for the named member of the context root object class.
        Method getterMethod = BeanHelper.getGetterMethod(contextRootClass, id);
        if (getterMethod != null) {

            String methodName = getterMethod.getName();
            Type returnType = getterMethod.getGenericReturnType();

            // ((type) model.getContextRoot()).get<PropertyName>()
            // Build the Java expression to get its value
            String memberJavaName = "((" + contextRootClass.getName() + ") " + modelJavaName + ".getContextRoot())." + methodName + "()";
            
            // If the type is java.lang.Boolean
            if (returnType == Boolean.class) {

                // Wrap the value in a GelloBoolean
                memberJavaName = "new GelloBoolean(" + memberJavaName + ")";

                // Change the returnType
                returnType = GelloBoolean.class;
            }

            // TODO: We need to also special-case the situation where the property is a collection
            // to wrap the collection in a GelloSet or GelloSequence.

            Symbol s = new Symbol(null, memberJavaName, returnType, false, memberJavaName);

            log.fine("Symbol=" + s);
            return s;
        }
        else {

            log.fine("The context root object has no member named \"" + id + "\"");
            return null;
        }
    }
}
