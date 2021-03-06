//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2006.11.08 at 12:07:49 PM PST
//


package org.gello.model.HL7RIM.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.gello.model.HL7RIM.generated.BXITCD;
import org.gello.model.HL7RIM.generated.CD;


/**
 * <p>Java class for BXIT_CD complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BXIT_CD">
 *   &lt;complexContent>
 *     &lt;extension base="{}CD">
 *       &lt;attribute name="qty" type="{}int" default="1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BXIT_CD")
public class BXITCD
    extends CD
{

    @XmlAttribute
    protected BigInteger qty;

    /**
     * Gets the value of the qty property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getQty() {
        if (qty == null) {
            return new BigInteger("1");
        } else {
            return qty;
        }
    }

    /**
     * Sets the value of the qty property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setQty(BigInteger value) {
        this.qty = value;
    }

}
