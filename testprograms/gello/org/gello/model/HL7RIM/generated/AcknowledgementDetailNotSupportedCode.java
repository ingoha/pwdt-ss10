//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.AcknowledgementDetailNotSupportedCode;


/**
 * <p>Java class for AcknowledgementDetailNotSupportedCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AcknowledgementDetailNotSupportedCode">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="NS260"/>
 *     &lt;enumeration value="NS261"/>
 *     &lt;enumeration value="NS200"/>
 *     &lt;enumeration value="NS250"/>
 *     &lt;enumeration value="NS202"/>
 *     &lt;enumeration value="NS203"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum AcknowledgementDetailNotSupportedCode {

    @XmlEnumValue("NS200")
    NS_200("NS200"),
    @XmlEnumValue("NS202")
    NS_202("NS202"),
    @XmlEnumValue("NS203")
    NS_203("NS203"),
    @XmlEnumValue("NS250")
    NS_250("NS250"),
    @XmlEnumValue("NS260")
    NS_260("NS260"),
    @XmlEnumValue("NS261")
    NS_261("NS261");
    private final String value;

    AcknowledgementDetailNotSupportedCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AcknowledgementDetailNotSupportedCode fromValue(String v) {
        for (AcknowledgementDetailNotSupportedCode c: AcknowledgementDetailNotSupportedCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
