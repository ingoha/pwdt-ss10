//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.ActRelationshipSplit;


/**
 * <p>Java class for ActRelationshipSplit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActRelationshipSplit">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="E1"/>
 *     &lt;enumeration value="EW"/>
 *     &lt;enumeration value="I1"/>
 *     &lt;enumeration value="IW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ActRelationshipSplit {

    EW("EW"),
    @XmlEnumValue("E1")
    E_1("E1"),
    IW("IW"),
    @XmlEnumValue("I1")
    I_1("I1");
    private final String value;

    ActRelationshipSplit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ActRelationshipSplit fromValue(String v) {
        for (ActRelationshipSplit c: ActRelationshipSplit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
