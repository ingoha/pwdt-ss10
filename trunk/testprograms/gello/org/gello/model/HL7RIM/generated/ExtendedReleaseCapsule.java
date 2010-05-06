//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.ExtendedReleaseCapsule;


/**
 * <p>Java class for ExtendedReleaseCapsule.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExtendedReleaseCapsule">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="ERCAP"/>
 *     &lt;enumeration value="ERCAP12"/>
 *     &lt;enumeration value="ERCAP24"/>
 *     &lt;enumeration value="ERECCAP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ExtendedReleaseCapsule {

    ERCAP("ERCAP"),
    @XmlEnumValue("ERCAP12")
    ERCAP_12("ERCAP12"),
    @XmlEnumValue("ERCAP24")
    ERCAP_24("ERCAP24"),
    ERECCAP("ERECCAP");
    private final String value;

    ExtendedReleaseCapsule(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExtendedReleaseCapsule fromValue(String v) {
        for (ExtendedReleaseCapsule c: ExtendedReleaseCapsule.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}