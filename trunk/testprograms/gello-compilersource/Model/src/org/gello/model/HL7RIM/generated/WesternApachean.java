//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.WesternApachean;


/**
 * <p>Java class for WesternApachean.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WesternApachean">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-NAV"/>
 *     &lt;enumeration value="x-APM"/>
 *     &lt;enumeration value="x-APW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum WesternApachean {

    @XmlEnumValue("x-APM")
    X_APM("x-APM"),
    @XmlEnumValue("x-APW")
    X_APW("x-APW"),
    @XmlEnumValue("x-NAV")
    X_NAV("x-NAV");
    private final String value;

    WesternApachean(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WesternApachean fromValue(String v) {
        for (WesternApachean c: WesternApachean.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}