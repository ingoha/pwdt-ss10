//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.SouthernAlaska;


/**
 * <p>Java class for SouthernAlaska.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SouthernAlaska">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-AHT"/>
 *     &lt;enumeration value="x-TFN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum SouthernAlaska {

    @XmlEnumValue("x-AHT")
    X_AHT("x-AHT"),
    @XmlEnumValue("x-TFN")
    X_TFN("x-TFN");
    private final String value;

    SouthernAlaska(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SouthernAlaska fromValue(String v) {
        for (SouthernAlaska c: SouthernAlaska.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
