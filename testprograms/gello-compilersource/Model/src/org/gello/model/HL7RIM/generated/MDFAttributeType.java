//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import org.gello.model.HL7RIM.generated.MDFAttributeType;


/**
 * <p>Java class for MDFAttributeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MDFAttributeType">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="ADDR"/>
 *     &lt;enumeration value="CD"/>
 *     &lt;enumeration value="COM"/>
 *     &lt;enumeration value="DTTM"/>
 *     &lt;enumeration value="DESC"/>
 *     &lt;enumeration value="EXPR"/>
 *     &lt;enumeration value="FRC"/>
 *     &lt;enumeration value="TIME"/>
 *     &lt;enumeration value="ID"/>
 *     &lt;enumeration value="IND"/>
 *     &lt;enumeration value="NM"/>
 *     &lt;enumeration value="NBR"/>
 *     &lt;enumeration value="PHON"/>
 *     &lt;enumeration value="QTY"/>
 *     &lt;enumeration value="TXT"/>
 *     &lt;enumeration value="TMR"/>
 *     &lt;enumeration value="VALUE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum MDFAttributeType {

    ADDR,
    CD,
    COM,
    DESC,
    DTTM,
    EXPR,
    FRC,
    ID,
    IND,
    NBR,
    NM,
    PHON,
    QTY,
    TIME,
    TMR,
    TXT,
    VALUE;

    public String value() {
        return name();
    }

    public MDFAttributeType fromValue(String v) {
        return valueOf(v);
    }

}
