//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.HL7StandardVersionCode;


/**
 * <p>Java class for HL7StandardVersionCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HL7StandardVersionCode">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="ActRelationshipExpectedSubset"/>
 *     &lt;enumeration value="ActRelationshipPastSubset"/>
 *     &lt;enumeration value="_ParticipationSubset"/>
 *     &lt;enumeration value="FUTURE"/>
 *     &lt;enumeration value="LAST"/>
 *     &lt;enumeration value="NEXT"/>
 *     &lt;enumeration value="FIRST"/>
 *     &lt;enumeration value="FUTSUM"/>
 *     &lt;enumeration value="MAX"/>
 *     &lt;enumeration value="MIN"/>
 *     &lt;enumeration value="RECENT"/>
 *     &lt;enumeration value="PAST"/>
 *     &lt;enumeration value="PREVSUM"/>
 *     &lt;enumeration value="SUM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum HL7StandardVersionCode {

    @XmlEnumValue("ActRelationshipExpectedSubset")
    ACT_RELATIONSHIP_EXPECTED_SUBSET("ActRelationshipExpectedSubset"),
    @XmlEnumValue("ActRelationshipPastSubset")
    ACT_RELATIONSHIP_PAST_SUBSET("ActRelationshipPastSubset"),
    FIRST("FIRST"),
    FUTSUM("FUTSUM"),
    FUTURE("FUTURE"),
    LAST("LAST"),
    MAX("MAX"),
    MIN("MIN"),
    NEXT("NEXT"),
    @XmlEnumValue("_ParticipationSubset")
    PARTICIPATION_SUBSET("_ParticipationSubset"),
    PAST("PAST"),
    PREVSUM("PREVSUM"),
    RECENT("RECENT"),
    SUM("SUM");
    private final String value;

    HL7StandardVersionCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HL7StandardVersionCode fromValue(String v) {
        for (HL7StandardVersionCode c: HL7StandardVersionCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
