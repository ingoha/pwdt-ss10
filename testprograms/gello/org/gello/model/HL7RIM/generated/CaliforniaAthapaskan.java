//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.CaliforniaAthapaskan;


/**
 * <p>Java class for CaliforniaAthapaskan.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CaliforniaAthapaskan">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-KTW"/>
 *     &lt;enumeration value="x-HUP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum CaliforniaAthapaskan {

    @XmlEnumValue("x-HUP")
    X_HUP("x-HUP"),
    @XmlEnumValue("x-KTW")
    X_KTW("x-KTW");
    private final String value;

    CaliforniaAthapaskan(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CaliforniaAthapaskan fromValue(String v) {
        for (CaliforniaAthapaskan c: CaliforniaAthapaskan.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}