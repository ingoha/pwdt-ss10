//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.ParameterizedDataTypeAnnotated;


/**
 * <p>Java class for ParameterizedDataTypeAnnotated.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ParameterizedDataTypeAnnotated">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="ANT&lt;T>"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ParameterizedDataTypeAnnotated {

    @XmlEnumValue("ANT<T>")
    ANT_T("ANT<T>");
    private final String value;

    ParameterizedDataTypeAnnotated(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ParameterizedDataTypeAnnotated fromValue(String v) {
        for (ParameterizedDataTypeAnnotated c: ParameterizedDataTypeAnnotated.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}