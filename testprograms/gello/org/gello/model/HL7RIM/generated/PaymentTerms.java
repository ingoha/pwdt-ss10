//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.PaymentTerms;


/**
 * <p>Java class for PaymentTerms.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PaymentTerms">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="COD"/>
 *     &lt;enumeration value="N30"/>
 *     &lt;enumeration value="N60"/>
 *     &lt;enumeration value="N90"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum PaymentTerms {

    COD("COD"),
    @XmlEnumValue("N30")
    N_30("N30"),
    @XmlEnumValue("N60")
    N_60("N60"),
    @XmlEnumValue("N90")
    N_90("N90");
    private final String value;

    PaymentTerms(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaymentTerms fromValue(String v) {
        for (PaymentTerms c: PaymentTerms.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}