//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.Tepiman;


/**
 * <p>Java class for Tepiman.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Tepiman">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-PAP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Tepiman {

    @XmlEnumValue("x-PAP")
    X_PAP("x-PAP");
    private final String value;

    Tepiman(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Tepiman fromValue(String v) {
        for (Tepiman c: Tepiman.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
