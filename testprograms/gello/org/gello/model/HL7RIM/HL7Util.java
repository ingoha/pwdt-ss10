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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.gello.model.HL7RIM.generated.ObjectFactory;
import org.gello.model.HL7RIM.generated.Patient;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/**
 * Static singleton utility class for serializing etc. within the HL7 Model
 *
 * @author zubint
 *
 */
public class HL7Util {

    /**
     * Static Constructor
     */
    protected HL7Util() throws RuntimeException {
        try {
            context = JAXBContext.newInstance(LIBRARY_PACKAGE);
            URL xsdURL = this.getClass().getClassLoader().getResource(SCHEMA_PATH);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(xsdURL);
            marshaller = context.createMarshaller();
            marshaller.setSchema(schema);
            unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);
            factory = new ObjectFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a reference to the object factory
     *
     * @return
     */
    protected ObjectFactory getFactory() {
        return factory;
    }

    /**
     * Validates an input stream using a handler to capture errors.
     *
     * @param stream
     * @param handler
     */
    protected void validate(InputStream stream, ErrorHandler handler) throws RuntimeException {
        try {
            StreamSource source = new StreamSource(stream);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(handler);
            validator.validate(source);
        } catch (Exception e) {
            // Do not rethrow the SAXParseExceptions because they should be handled by the error handler
            if (!(e instanceof SAXParseException)) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Outputs a patient to an XML file
     *
     * @param p
     * @param filename
     */
    protected void marshall(Patient p, OutputStream stream) throws RuntimeException {
        try {
            marshaller.marshal(p, stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a patient from an XML file
     *
     * @param filename
     * @return
     */
    protected Patient unmarshall(InputStream stream) throws RuntimeException {
        try {
            Object obj = unmarshaller.unmarshal(stream);
            Patient p = (Patient) obj;
            return p;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JAXBContext context;

    private Marshaller marshaller;

    private Unmarshaller unmarshaller;

    private Schema schema;

    private ObjectFactory factory;

    private final String SCHEMA_PATH = "org/gello/model/HL7RIM/Patient.xsd";

    private final String LIBRARY_PACKAGE = "org.gello.model.HL7RIM.generated";
}