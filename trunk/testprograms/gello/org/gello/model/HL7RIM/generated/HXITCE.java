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
import org.gello.model.HL7RIM.generated.CE;
import org.gello.model.HL7RIM.generated.HXITCE;
import org.gello.model.HL7RIM.generated.IVLTS;


/**
 * <p>Java class for HXIT_CE complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="HXIT_CE">
 *   &lt;complexContent>
 *     &lt;extension base="{}CE">
 *       &lt;sequence>
 *         &lt;element name="validTime" type="{}IVL_TS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HXIT_CE", propOrder = {
    "validTime"
})
public class HXITCE
    extends CE
{

    protected IVLTS validTime;

    /**
     * Gets the value of the validTime property.
     *
     * @return
     *     possible object is
     *     {@link IVLTS }
     *
     */
    public IVLTS getValidTime() {
        return validTime;
    }

    /**
     * Sets the value of the validTime property.
     *
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *
     */
    public void setValidTime(IVLTS value) {
        this.validTime = value;
    }

}
