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


package org.gello.model.HL7RIM;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import org.gello.model.HL7RIM.generated.Patient;
import org.gello.runtime.CompileMessage;
import org.gello.runtime.CompileMessage.Severity;
import org.gello.runtime.GelloModelException;
import org.gello.runtime.IGelloModel;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This is a concrete model of the IGelloModel for use with the Expression
 * Authoring tool prototype
 *
 * @author zubint
 * @author Erik Horstkotte
 */
public class HL7Model implements IGelloModel {

    /**
     * Default Constructor
     */
    public HL7Model() {
        util = new HL7Util();
        root = util.getFactory().createPatient();
    }

    public Object getContextRoot() {
        return root;
    }

    public Class getContextRootClass() {
        return Patient.class;
    }

    public void saveData(OutputStream stream) throws GelloModelException {
        try {
            util.marshall((Patient)root, stream);
        } catch (Exception e) {
            throw new GelloModelException(
                    "The data could not be saved", e);
        }
    }

    public CompileMessage[] parseData(InputStream stream)
    throws GelloModelException {
        try {
            ValidationErrorHandler handler = new ValidationErrorHandler();
            util.validate(stream, handler);
            return handler.retrieveMessages();
        } catch (Exception e) {
            throw new GelloModelException(
                    "The file could not be parsed for exceptions", e);
        }

    }

    public void loadData(InputStream stream) throws GelloModelException {
        try {
            root = util.unmarshall(stream);
        } catch (Exception e) {
            throw new GelloModelException("The data could not be loaded", e);
        }
    }

    public void loadDataDefault() throws GelloModelException {
        try {
            URL xmlURL = this.getClass().getClassLoader().getResource(DEFAULT_PATH);
            FileInputStream stream = new FileInputStream(xmlURL.getFile());
            root = util.unmarshall(stream);
        } catch (Exception e) {
            throw new GelloModelException("The default data could not be loaded", e);
        }
    }

    private final String DEFAULT_PATH = "org/gello/model/HL7RIM/PatientDefault.xml";

    private HL7Util util;

    private Object root;

    /**
     * Inner class used to capture validation exceptions.
     *
     * @author zubint
     */
    private class ValidationErrorHandler implements ErrorHandler {

        /**
         * Catches errors
         */
        public void error(SAXParseException arg0) throws SAXException {
            CompileMessage message = getCompileMessage(Severity.error, arg0);
            messageList.add(message);
        }

        /*
         * Catches fatal errors
         */
        public void fatalError(SAXParseException arg0) throws SAXException {
            CompileMessage message = getCompileMessage(Severity.fatal, arg0);
            messageList.add(message);
        }

        /*
         * Catches warnings
         */
        public void warning(SAXParseException arg0) throws SAXException {
            CompileMessage message = getCompileMessage(Severity.warning, arg0);
            messageList.add(message);
        }

        /**
         * Returns a list of compile messages
         */
        public CompileMessage[] retrieveMessages() {
            return messageList.toArray(new CompileMessage[0]);
        }

        /**
         * Turns an SAX ParseException into a compile message
         */
        private CompileMessage getCompileMessage(Severity sev, SAXParseException e) {
            CompileMessage message = new CompileMessage(e.getLineNumber(), e.getColumnNumber(), sev, e.getClass().getName(), new Object[]{e.getMessage()});
            return message;
        }

        private ArrayList<CompileMessage> messageList = new ArrayList<CompileMessage>();
    }
}
