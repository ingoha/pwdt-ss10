//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2006.11.08 at 12:07:49 PM PST
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.gello.model.HL7RIM.generated.RTO;
import org.gello.model.HL7RIM.generated.RTOQTYQTY;


/**
 *
 *             A quantity constructed as the quotient of a numerator
 *             quantity divided by a denominator quantity. Common
 *             factors in the numerator and denominator are not
 *             automatically cancelled out.   supports titers
 *             (e.g., "1:128") and other quantities produced by
 *             laboratories that truly represent ratios. Ratios are
 *             not simply "structured numerics", particularly blood
 *             pressure measurements (e.g. "120/60") are not ratios.
 *             In many cases REAL should be used instead
 *             of .
 *
 *
 * <p>Java class for RTO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RTO">
 *   &lt;complexContent>
 *     &lt;extension base="{}RTO_QTY_QTY">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTO")
public class RTO
    extends RTOQTYQTY
{


}
