//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import org.gello.model.HL7RIM.generated.ActInvoicePaymentCode;


/**
 * <p>Java class for ActInvoicePaymentCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ActInvoicePaymentCode">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="BONUS"/>
 *     &lt;enumeration value="CFWD"/>
 *     &lt;enumeration value="EPYMT"/>
 *     &lt;enumeration value="EDU"/>
 *     &lt;enumeration value="GARN"/>
 *     &lt;enumeration value="PINV"/>
 *     &lt;enumeration value="PPRD"/>
 *     &lt;enumeration value="PROA"/>
 *     &lt;enumeration value="RECOV"/>
 *     &lt;enumeration value="RETRO"/>
 *     &lt;enumeration value="INVOICE"/>
 *     &lt;enumeration value="TRAN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum ActInvoicePaymentCode {

    BONUS,
    CFWD,
    EDU,
    EPYMT,
    GARN,
    INVOICE,
    PINV,
    PPRD,
    PROA,
    RECOV,
    RETRO,
    TRAN;

    public String value() {
        return name();
    }

    public ActInvoicePaymentCode fromValue(String v) {
        return valueOf(v);
    }

}
