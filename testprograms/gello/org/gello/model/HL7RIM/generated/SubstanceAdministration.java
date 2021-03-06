//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2006.11.08 at 12:07:49 PM PST
//


package org.gello.model.HL7RIM.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.gello.model.HL7RIM.generated.Act;
import org.gello.model.HL7RIM.generated.CD;
import org.gello.model.HL7RIM.generated.CE;
import org.gello.model.HL7RIM.generated.IVLPQ;
import org.gello.model.HL7RIM.generated.RTO;
import org.gello.model.HL7RIM.generated.SubstanceAdministration;


/**
 * Hl7 Patient @ http://www.hl7.org/v3ballot/html/infrastructure/rim/rim.htm#SubstanceAdministration-cls
 *
 * <p>Java class for SubstanceAdministration complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SubstanceAdministration">
 *   &lt;complexContent>
 *     &lt;extension base="{}Act">
 *       &lt;sequence>
 *         &lt;element name="routeCode" type="{}CE" minOccurs="0"/>
 *         &lt;element name="approachSiteCode" type="{}CD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="doseQuantity" type="{}IVL_PQ" minOccurs="0"/>
 *         &lt;element name="rateQuantity" type="{}IVL_PQ" minOccurs="0"/>
 *         &lt;element name="doseCheckQuantity" type="{}RTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxDoseQuantity" type="{}RTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="administrationUnitCode" type="{}CE" minOccurs="0"/>
 *         &lt;element name="methodCode" type="{}CD" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubstanceAdministration", propOrder = {
    "routeCode",
    "approachSiteCode",
    "doseQuantity",
    "rateQuantity",
    "doseCheckQuantity",
    "maxDoseQuantity",
    "administrationUnitCode",
    "methodCode"
})
public class SubstanceAdministration
    extends Act
{

    protected CE routeCode;
    protected List<CD> approachSiteCode;
    protected IVLPQ doseQuantity;
    protected IVLPQ rateQuantity;
    protected List<RTO> doseCheckQuantity;
    protected List<RTO> maxDoseQuantity;
    protected CE administrationUnitCode;
    protected List<CD> methodCode;

    /**
     * Gets the value of the routeCode property.
     *
     * @return
     *     possible object is
     *     {@link CE }
     *
     */
    public CE getRouteCode() {
        return routeCode;
    }

    /**
     * Sets the value of the routeCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CE }
     *
     */
    public void setRouteCode(CE value) {
        this.routeCode = value;
    }

    /**
     * Gets the value of the approachSiteCode property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the approachSiteCode property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApproachSiteCode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CD }
     *
     *
     */
    public List<CD> getApproachSiteCode() {
        if (approachSiteCode == null) {
            approachSiteCode = new ArrayList<CD>();
        }
        return this.approachSiteCode;
    }

    /**
     * Gets the value of the doseQuantity property.
     *
     * @return
     *     possible object is
     *     {@link IVLPQ }
     *
     */
    public IVLPQ getDoseQuantity() {
        return doseQuantity;
    }

    /**
     * Sets the value of the doseQuantity property.
     *
     * @param value
     *     allowed object is
     *     {@link IVLPQ }
     *
     */
    public void setDoseQuantity(IVLPQ value) {
        this.doseQuantity = value;
    }

    /**
     * Gets the value of the rateQuantity property.
     *
     * @return
     *     possible object is
     *     {@link IVLPQ }
     *
     */
    public IVLPQ getRateQuantity() {
        return rateQuantity;
    }

    /**
     * Sets the value of the rateQuantity property.
     *
     * @param value
     *     allowed object is
     *     {@link IVLPQ }
     *
     */
    public void setRateQuantity(IVLPQ value) {
        this.rateQuantity = value;
    }

    /**
     * Gets the value of the doseCheckQuantity property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doseCheckQuantity property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoseCheckQuantity().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RTO }
     *
     *
     */
    public List<RTO> getDoseCheckQuantity() {
        if (doseCheckQuantity == null) {
            doseCheckQuantity = new ArrayList<RTO>();
        }
        return this.doseCheckQuantity;
    }

    /**
     * Gets the value of the maxDoseQuantity property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the maxDoseQuantity property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaxDoseQuantity().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RTO }
     *
     *
     */
    public List<RTO> getMaxDoseQuantity() {
        if (maxDoseQuantity == null) {
            maxDoseQuantity = new ArrayList<RTO>();
        }
        return this.maxDoseQuantity;
    }

    /**
     * Gets the value of the administrationUnitCode property.
     *
     * @return
     *     possible object is
     *     {@link CE }
     *
     */
    public CE getAdministrationUnitCode() {
        return administrationUnitCode;
    }

    /**
     * Sets the value of the administrationUnitCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CE }
     *
     */
    public void setAdministrationUnitCode(CE value) {
        this.administrationUnitCode = value;
    }

    /**
     * Gets the value of the methodCode property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the methodCode property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMethodCode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CD }
     *
     *
     */
    public List<CD> getMethodCode() {
        if (methodCode == null) {
            methodCode = new ArrayList<CD>();
        }
        return this.methodCode;
    }

}
