//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.Cupan;


/**
 * <p>Java class for Cupan.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Cupan">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-CHL"/>
 *     &lt;enumeration value="x-CUP"/>
 *     &lt;enumeration value="x-LUI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Cupan {

    @XmlEnumValue("x-CHL")
    X_CHL("x-CHL"),
    @XmlEnumValue("x-CUP")
    X_CUP("x-CUP"),
    @XmlEnumValue("x-LUI")
    X_LUI("x-LUI");
    private final String value;

    Cupan(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Cupan fromValue(String v) {
        for (Cupan c: Cupan.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
