//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import org.gello.model.HL7RIM.generated.AgeDetectedIssueCode;


/**
 * <p>Java class for AgeDetectedIssueCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AgeDetectedIssueCode">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="AGE"/>
 *     &lt;enumeration value="DOSEHINDA"/>
 *     &lt;enumeration value="DOSELINDA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum AgeDetectedIssueCode {

    AGE,
    DOSEHINDA,
    DOSELINDA;

    public String value() {
        return name();
    }

    public AgeDetectedIssueCode fromValue(String v) {
        return valueOf(v);
    }

}