//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.UpperChinook;


/**
 * <p>Java class for UpperChinook.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpperChinook">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-WAC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum UpperChinook {

    @XmlEnumValue("x-WAC")
    X_WAC("x-WAC");
    private final String value;

    UpperChinook(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UpperChinook fromValue(String v) {
        for (UpperChinook c: UpperChinook.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}