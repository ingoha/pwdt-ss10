//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.InteriorSalish;


/**
 * <p>Java class for InteriorSalish.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InteriorSalish">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-CRD"/>
 *     &lt;enumeration value="x-COL"/>
 *     &lt;enumeration value="x-FLA"/>
 *     &lt;enumeration value="x-OKA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum InteriorSalish {

    @XmlEnumValue("x-COL")
    X_COL("x-COL"),
    @XmlEnumValue("x-CRD")
    X_CRD("x-CRD"),
    @XmlEnumValue("x-FLA")
    X_FLA("x-FLA"),
    @XmlEnumValue("x-OKA")
    X_OKA("x-OKA");
    private final String value;

    InteriorSalish(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InteriorSalish fromValue(String v) {
        for (InteriorSalish c: InteriorSalish.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}