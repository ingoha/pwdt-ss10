//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.KutchinHan;


/**
 * <p>Java class for KutchinHan.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="KutchinHan">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="x-HAA"/>
 *     &lt;enumeration value="x-KUC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum KutchinHan {

    @XmlEnumValue("x-HAA")
    X_HAA("x-HAA"),
    @XmlEnumValue("x-KUC")
    X_KUC("x-KUC");
    private final String value;

    KutchinHan(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KutchinHan fromValue(String v) {
        for (KutchinHan c: KutchinHan.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}