//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2006.11.08 at 12:07:49 PM PST
//


package org.gello.model.HL7RIM.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import org.gello.model.HL7RIM.generated.ANY;
import org.gello.model.HL7RIM.generated.PQ;
import org.gello.model.HL7RIM.generated.SLISTTS;
import org.gello.model.HL7RIM.generated.TS;


/**
 * <p>Java class for SLIST_TS complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SLIST_TS">
 *   &lt;complexContent>
 *     &lt;extension base="{}ANY">
 *       &lt;sequence>
 *         &lt;element name="origin" type="{}TS"/>
 *         &lt;element name="scale" type="{}PQ"/>
 *         &lt;element name="digits" type="{}list_int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLIST_TS", propOrder = {
    "origin",
    "scale",
    "digits"
})
public class SLISTTS
    extends ANY
{

    protected TS origin;
    protected PQ scale;
    @XmlList
    protected List<BigInteger> digits;

    /**
     * Gets the value of the origin property.
     *
     * @return
     *     possible object is
     *     {@link TS }
     *
     */
    public TS getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     *
     * @param value
     *     allowed object is
     *     {@link TS }
     *
     */
    public void setOrigin(TS value) {
        this.origin = value;
    }

    /**
     * Gets the value of the scale property.
     *
     * @return
     *     possible object is
     *     {@link PQ }
     *
     */
    public PQ getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     *
     * @param value
     *     allowed object is
     *     {@link PQ }
     *
     */
    public void setScale(PQ value) {
        this.scale = value;
    }

    /**
     * Gets the value of the digits property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the digits property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDigits().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     *
     *
     */
    public List<BigInteger> getDigits() {
        if (digits == null) {
            digits = new ArrayList<BigInteger>();
        }
        return this.digits;
    }

}
