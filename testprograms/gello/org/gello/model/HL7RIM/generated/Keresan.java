//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.Keresan;


/**
 * <p>Java class for Keresan.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Keresan">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-KJQ"/>
 *     &lt;enumeration value="x-KEE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Keresan {

    @XmlEnumValue("x-KEE")
    X_KEE("x-KEE"),
    @XmlEnumValue("x-KJQ")
    X_KJQ("x-KJQ");
    private final String value;

    Keresan(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Keresan fromValue(String v) {
        for (Keresan c: Keresan.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
