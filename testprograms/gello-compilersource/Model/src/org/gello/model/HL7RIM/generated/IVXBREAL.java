//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2006.11.08 at 12:07:49 PM PST
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.gello.model.HL7RIM.generated.IVXBREAL;
import org.gello.model.HL7RIM.generated.REAL;


/**
 * <p>Java class for IVXB_REAL complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="IVXB_REAL">
 *   &lt;complexContent>
 *     &lt;extension base="{}REAL">
 *       &lt;attribute name="inclusive" type="{}bl" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVXB_REAL")
public class IVXBREAL
    extends REAL
{

    @XmlAttribute
    protected Boolean inclusive;

    /**
     * Gets the value of the inclusive property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public boolean isInclusive() {
        if (inclusive == null) {
            return true;
        } else {
            return inclusive;
        }
    }

    /**
     * Sets the value of the inclusive property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setInclusive(Boolean value) {
        this.inclusive = value;
    }

}