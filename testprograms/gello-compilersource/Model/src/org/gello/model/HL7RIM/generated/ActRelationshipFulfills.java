//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import org.gello.model.HL7RIM.generated.ActRelationshipFulfills;


/**
 * <p>Java class for ActRelationshipFulfills.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActRelationshipFulfills">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="FLFS"/>
 *     &lt;enumeration value="OCCR"/>
 *     &lt;enumeration value="OREF"/>
 *     &lt;enumeration value="SCH"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ActRelationshipFulfills {

    FLFS,
    OCCR,
    OREF,
    SCH;

    public String value() {
        return name();
    }

    public ActRelationshipFulfills fromValue(String v) {
        return valueOf(v);
    }

}
