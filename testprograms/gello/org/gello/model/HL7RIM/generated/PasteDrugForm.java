//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import org.gello.model.HL7RIM.generated.PasteDrugForm;


/**
 * <p>Java class for PasteDrugForm.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PasteDrugForm">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="PASTE"/>
 *     &lt;enumeration value="PUD"/>
 *     &lt;enumeration value="TPASTE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum PasteDrugForm {

    PASTE,
    PUD,
    TPASTE;

    public String value() {
        return name();
    }

    public PasteDrugForm fromValue(String v) {
        return valueOf(v);
    }

}
