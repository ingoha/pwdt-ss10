//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.KoyukonIngalik;


/**
 * <p>Java class for KoyukonIngalik.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="KoyukonIngalik">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-ING"/>
 *     &lt;enumeration value="x-HOI"/>
 *     &lt;enumeration value="x-KOY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum KoyukonIngalik {

    @XmlEnumValue("x-HOI")
    X_HOI("x-HOI"),
    @XmlEnumValue("x-ING")
    X_ING("x-ING"),
    @XmlEnumValue("x-KOY")
    X_KOY("x-KOY");
    private final String value;

    KoyukonIngalik(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KoyukonIngalik fromValue(String v) {
        for (KoyukonIngalik c: KoyukonIngalik.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
