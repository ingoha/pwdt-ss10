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
import org.gello.model.HL7RIM.generated.TS;
import org.gello.model.HL7RIM.generated.UVPTS;


/**
 * <p>Java class for UVP_TS complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="UVP_TS">
 *   &lt;complexContent>
 *     &lt;extension base="{}TS">
 *       &lt;attribute name="probability" type="{}probability" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UVP_TS")
public class UVPTS
    extends TS
{

    @XmlAttribute
    protected Double probability;

    /**
     * Gets the value of the probability property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getProbability() {
        return probability;
    }

    /**
     * Sets the value of the probability property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setProbability(Double value) {
        this.probability = value;
    }

}