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
import org.gello.model.HL7RIM.generated.QTY;
import org.gello.model.HL7RIM.generated.RTOQTYQTY;


/**
 * <p>Java class for RTO_QTY_QTY complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RTO_QTY_QTY">
 *   &lt;complexContent>
 *     &lt;extension base="{}QTY">
 *       &lt;sequence>
 *         &lt;element name="numerator" type="{}QTY"/>
 *         &lt;element name="denominator" type="{}QTY"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTO_QTY_QTY", propOrder = {
    "numerator",
    "denominator"
})
public class RTOQTYQTY
    extends QTY
{

    protected QTY numerator;
    protected QTY denominator;

    /**
     * Gets the value of the numerator property.
     *
     * @return
     *     possible object is
     *     {@link QTY }
     *
     */
    public QTY getNumerator() {
        return numerator;
    }

    /**
     * Sets the value of the numerator property.
     *
     * @param value
     *     allowed object is
     *     {@link QTY }
     *
     */
    public void setNumerator(QTY value) {
        this.numerator = value;
    }

    /**
     * Gets the value of the denominator property.
     *
     * @return
     *     possible object is
     *     {@link QTY }
     *
     */
    public QTY getDenominator() {
        return denominator;
    }

    /**
     * Sets the value of the denominator property.
     *
     * @param value
     *     allowed object is
     *     {@link QTY }
     *
     */
    public void setDenominator(QTY value) {
        this.denominator = value;
    }

}
