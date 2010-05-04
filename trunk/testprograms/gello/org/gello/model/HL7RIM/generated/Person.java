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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.gello.model.HL7RIM.generated.CE;
import org.gello.model.HL7RIM.generated.ED;
import org.gello.model.HL7RIM.generated.EN;
import org.gello.model.HL7RIM.generated.II;
import org.gello.model.HL7RIM.generated.Person;
import org.gello.model.HL7RIM.generated.TS;


/**
 * Hl7 Person from  @ http://gim.upv.es/hl7/html/domains/ct/editable/COCT_HD030000-NoEdit.htm#Person
 *
 * <p>Java class for Person complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Person">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="name" type="{}EN" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="desc" type="{}ED" minOccurs="0"/>
 *         &lt;element name="administrativeGenderCode" type="{}CE" minOccurs="0"/>
 *         &lt;element name="birthTime" type="{}TS" minOccurs="0"/>
 *         &lt;element name="deceasedTime" type="{}TS" minOccurs="0"/>
 *         &lt;element name="maritalStatusCode" type="{}CE" minOccurs="0"/>
 *         &lt;element name="educationLevelCode" type="{}CE" minOccurs="0"/>
 *         &lt;element name="disabilityCode" type="{}CE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="livingArrangementCode" type="{}CE" minOccurs="0"/>
 *         &lt;element name="religiousAffiliationCode" type="{}CE" minOccurs="0"/>
 *         &lt;element name="raceCode" type="{}CE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ethnicGroupCode" type="{}CE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="classCode" use="required" type="{}cs" />
 *       &lt;attribute name="deceasedInd" type="{}bl" />
 *       &lt;attribute name="determinerCode" use="required" type="{}cs" />
 *       &lt;attribute name="multipleBirthInd" type="{}bl" />
 *       &lt;attribute name="multipleBirthOrder" type="{}int" />
 *       &lt;attribute name="organDonorInd" type="{}bl" />
 *       &lt;attribute name="statusCode" type="{}cs" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
    "id",
    "name",
    "desc",
    "administrativeGenderCode",
    "birthTime",
    "deceasedTime",
    "maritalStatusCode",
    "educationLevelCode",
    "disabilityCode",
    "livingArrangementCode",
    "religiousAffiliationCode",
    "raceCode",
    "ethnicGroupCode"
})
public class Person {

    protected List<II> id;
    protected List<EN> name;
    protected ED desc;
    protected CE administrativeGenderCode;
    protected TS birthTime;
    protected TS deceasedTime;
    protected CE maritalStatusCode;
    protected CE educationLevelCode;
    protected List<CE> disabilityCode;
    protected CE livingArrangementCode;
    protected CE religiousAffiliationCode;
    protected List<CE> raceCode;
    protected List<CE> ethnicGroupCode;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String classCode;
    @XmlAttribute
    protected Boolean deceasedInd;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String determinerCode;
    @XmlAttribute
    protected Boolean multipleBirthInd;
    @XmlAttribute
    protected BigInteger multipleBirthOrder;
    @XmlAttribute
    protected Boolean organDonorInd;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String statusCode;

    /**
     * Gets the value of the id property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the id property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     *
     *
     */
    public List<II> getId() {
        if (id == null) {
            id = new ArrayList<II>();
        }
        return this.id;
    }

    /**
     * Gets the value of the name property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EN }
     *
     *
     */
    public List<EN> getName() {
        if (name == null) {
            name = new ArrayList<EN>();
        }
        return this.name;
    }

    /**
     * Gets the value of the desc property.
     *
     * @return
     *     possible object is
     *     {@link ED }
     *
     */
    public ED getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     *
     * @param value
     *     allowed object is
     *     {@link ED }
     *
     */
    public void setDesc(ED value) {
        this.desc = value;
    }

    /**
     * Gets the value of the administrativeGenderCode property.
     *
     * @return
     *     possible object is
     *     {@link CE }
     *
     */
    public CE getAdministrativeGenderCode() {
        return administrativeGenderCode;
    }

    /**
     * Sets the value of the administrativeGenderCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CE }
     *
     */
    public void setAdministrativeGenderCode(CE value) {
        this.administrativeGenderCode = value;
    }

    /**
     * Gets the value of the birthTime property.
     *
     * @return
     *     possible object is
     *     {@link TS }
     *
     */
    public TS getBirthTime() {
        return birthTime;
    }

    /**
     * Sets the value of the birthTime property.
     *
     * @param value
     *     allowed object is
     *     {@link TS }
     *
     */
    public void setBirthTime(TS value) {
        this.birthTime = value;
    }

    /**
     * Gets the value of the deceasedTime property.
     *
     * @return
     *     possible object is
     *     {@link TS }
     *
     */
    public TS getDeceasedTime() {
        return deceasedTime;
    }

    /**
     * Sets the value of the deceasedTime property.
     *
     * @param value
     *     allowed object is
     *     {@link TS }
     *
     */
    public void setDeceasedTime(TS value) {
        this.deceasedTime = value;
    }

    /**
     * Gets the value of the maritalStatusCode property.
     *
     * @return
     *     possible object is
     *     {@link CE }
     *
     */
    public CE getMaritalStatusCode() {
        return maritalStatusCode;
    }

    /**
     * Sets the value of the maritalStatusCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CE }
     *
     */
    public void setMaritalStatusCode(CE value) {
        this.maritalStatusCode = value;
    }

    /**
     * Gets the value of the educationLevelCode property.
     *
     * @return
     *     possible object is
     *     {@link CE }
     *
     */
    public CE getEducationLevelCode() {
        return educationLevelCode;
    }

    /**
     * Sets the value of the educationLevelCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CE }
     *
     */
    public void setEducationLevelCode(CE value) {
        this.educationLevelCode = value;
    }

    /**
     * Gets the value of the disabilityCode property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the disabilityCode property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisabilityCode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CE }
     *
     *
     */
    public List<CE> getDisabilityCode() {
        if (disabilityCode == null) {
            disabilityCode = new ArrayList<CE>();
        }
        return this.disabilityCode;
    }

    /**
     * Gets the value of the livingArrangementCode property.
     *
     * @return
     *     possible object is
     *     {@link CE }
     *
     */
    public CE getLivingArrangementCode() {
        return livingArrangementCode;
    }

    /**
     * Sets the value of the livingArrangementCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CE }
     *
     */
    public void setLivingArrangementCode(CE value) {
        this.livingArrangementCode = value;
    }

    /**
     * Gets the value of the religiousAffiliationCode property.
     *
     * @return
     *     possible object is
     *     {@link CE }
     *
     */
    public CE getReligiousAffiliationCode() {
        return religiousAffiliationCode;
    }

    /**
     * Sets the value of the religiousAffiliationCode property.
     *
     * @param value
     *     allowed object is
     *     {@link CE }
     *
     */
    public void setReligiousAffiliationCode(CE value) {
        this.religiousAffiliationCode = value;
    }

    /**
     * Gets the value of the raceCode property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the raceCode property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRaceCode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CE }
     *
     *
     */
    public List<CE> getRaceCode() {
        if (raceCode == null) {
            raceCode = new ArrayList<CE>();
        }
        return this.raceCode;
    }

    /**
     * Gets the value of the ethnicGroupCode property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ethnicGroupCode property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEthnicGroupCode().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CE }
     *
     *
     */
    public List<CE> getEthnicGroupCode() {
        if (ethnicGroupCode == null) {
            ethnicGroupCode = new ArrayList<CE>();
        }
        return this.ethnicGroupCode;
    }

    /**
     * Gets the value of the classCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Sets the value of the classCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setClassCode(String value) {
        this.classCode = value;
    }

    /**
     * Gets the value of the deceasedInd property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isDeceasedInd() {
        return deceasedInd;
    }

    /**
     * Sets the value of the deceasedInd property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setDeceasedInd(Boolean value) {
        this.deceasedInd = value;
    }

    /**
     * Gets the value of the determinerCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDeterminerCode() {
        return determinerCode;
    }

    /**
     * Sets the value of the determinerCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDeterminerCode(String value) {
        this.determinerCode = value;
    }

    /**
     * Gets the value of the multipleBirthInd property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isMultipleBirthInd() {
        return multipleBirthInd;
    }

    /**
     * Sets the value of the multipleBirthInd property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setMultipleBirthInd(Boolean value) {
        this.multipleBirthInd = value;
    }

    /**
     * Gets the value of the multipleBirthOrder property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getMultipleBirthOrder() {
        return multipleBirthOrder;
    }

    /**
     * Sets the value of the multipleBirthOrder property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setMultipleBirthOrder(BigInteger value) {
        this.multipleBirthOrder = value;
    }

    /**
     * Gets the value of the organDonorInd property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isOrganDonorInd() {
        return organDonorInd;
    }

    /**
     * Sets the value of the organDonorInd property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setOrganDonorInd(Boolean value) {
        this.organDonorInd = value;
    }

    /**
     * Gets the value of the statusCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

}