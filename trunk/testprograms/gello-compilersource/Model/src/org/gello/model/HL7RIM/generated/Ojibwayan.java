//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.Ojibwayan;


/**
 * <p>Java class for Ojibwayan.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Ojibwayan">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-POT"/>
 *     &lt;enumeration value="x-OJB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Ojibwayan {

    @XmlEnumValue("x-OJB")
    X_OJB("x-OJB"),
    @XmlEnumValue("x-POT")
    X_POT("x-POT");
    private final String value;

    Ojibwayan(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Ojibwayan fromValue(String v) {
        for (Ojibwayan c: Ojibwayan.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
