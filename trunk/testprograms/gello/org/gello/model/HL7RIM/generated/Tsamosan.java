//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.Tsamosan;


/**
 * <p>Java class for Tsamosan.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Tsamosan">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-COW"/>
 *     &lt;enumeration value="x-CEA"/>
 *     &lt;enumeration value="x-QUN"/>
 *     &lt;enumeration value="x-CJH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Tsamosan {

    @XmlEnumValue("x-CEA")
    X_CEA("x-CEA"),
    @XmlEnumValue("x-CJH")
    X_CJH("x-CJH"),
    @XmlEnumValue("x-COW")
    X_COW("x-COW"),
    @XmlEnumValue("x-QUN")
    X_QUN("x-QUN");
    private final String value;

    Tsamosan(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Tsamosan fromValue(String v) {
        for (Tsamosan c: Tsamosan.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
