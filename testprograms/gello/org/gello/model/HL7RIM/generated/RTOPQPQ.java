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
import org.gello.model.HL7RIM.generated.PQ;
import org.gello.model.HL7RIM.generated.QTY;
import org.gello.model.HL7RIM.generated.RTOPQPQ;


/**
 * <p>Java class for RTO_PQ_PQ complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RTO_PQ_PQ">
 *   &lt;complexContent>
 *     &lt;extension base="{}QTY">
 *       &lt;sequence>
 *         &lt;element name="numerator" type="{}PQ"/>
 *         &lt;element name="denominator" type="{}PQ"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTO_PQ_PQ", propOrder = {
    "numerator",
    "denominator"
})
public class RTOPQPQ
    extends QTY
{

    protected PQ numerator;
    protected PQ denominator;

    /**
     * Gets the value of the numerator property.
     *
     * @return
     *     possible object is
     *     {@link PQ }
     *
     */
    public PQ getNumerator() {
        return numerator;
    }

    /**
     * Sets the value of the numerator property.
     *
     * @param value
     *     allowed object is
     *     {@link PQ }
     *
     */
    public void setNumerator(PQ value) {
        this.numerator = value;
    }

    /**
     * Gets the value of the denominator property.
     *
     * @return
     *     possible object is
     *     {@link PQ }
     *
     */
    public PQ getDenominator() {
        return denominator;
    }

    /**
     * Sets the value of the denominator property.
     *
     * @param value
     *     allowed object is
     *     {@link PQ }
     *
     */
    public void setDenominator(PQ value) {
        this.denominator = value;
    }

}
