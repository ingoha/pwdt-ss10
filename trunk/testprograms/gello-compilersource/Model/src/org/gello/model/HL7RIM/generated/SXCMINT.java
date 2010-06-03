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
import org.gello.model.HL7RIM.generated.INT;
import org.gello.model.HL7RIM.generated.SXCMINT;
import org.gello.model.HL7RIM.generated.SetOperator;


/**
 * <p>Java class for SXCM_INT complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SXCM_INT">
 *   &lt;complexContent>
 *     &lt;extension base="{}INT">
 *       &lt;attribute name="operator" type="{}SetOperator" default="I" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SXCM_INT")
public class SXCMINT
    extends INT
{

    @XmlAttribute
    protected SetOperator operator;

    /**
     * Gets the value of the operator property.
     *
     * @return
     *     possible object is
     *     {@link SetOperator }
     *
     */
    public SetOperator getOperator() {
        if (operator == null) {
            return SetOperator.I;
        } else {
            return operator;
        }
    }

    /**
     * Sets the value of the operator property.
     *
     * @param value
     *     allowed object is
     *     {@link SetOperator }
     *
     */
    public void setOperator(SetOperator value) {
        this.operator = value;
    }

}
