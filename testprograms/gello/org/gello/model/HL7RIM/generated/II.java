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
import org.gello.model.HL7RIM.generated.ANY;
import org.gello.model.HL7RIM.generated.II;


/**
 *
 *             An identifier that uniquely identifies a thing or object.
 *             Examples are object identifier for HL7 RIM objects,
 *             medical record number, order id, service catalog item id,
 *             Vehicle Identification Number (VIN), etc. Instance
 *             identifiers are defined based on ISO object identifiers.
 *
 *
 * <p>Java class for II complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="II">
 *   &lt;complexContent>
 *     &lt;extension base="{}ANY">
 *       &lt;attribute name="assigningAuthorityName" type="{}st" />
 *       &lt;attribute name="displayable" type="{}bl" />
 *       &lt;attribute name="extension" type="{}st" />
 *       &lt;attribute name="root" type="{}uid" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "II")
public class II
    extends ANY
{

    @XmlAttribute
    protected String assigningAuthorityName;
    @XmlAttribute
    protected Boolean displayable;
    @XmlAttribute
    protected String extension;
    @XmlAttribute
    protected String root;

    /**
     * Gets the value of the assigningAuthorityName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAssigningAuthorityName() {
        return assigningAuthorityName;
    }

    /**
     * Sets the value of the assigningAuthorityName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAssigningAuthorityName(String value) {
        this.assigningAuthorityName = value;
    }

    /**
     * Gets the value of the displayable property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isDisplayable() {
        return displayable;
    }

    /**
     * Sets the value of the displayable property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setDisplayable(Boolean value) {
        this.displayable = value;
    }

    /**
     * Gets the value of the extension property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setExtension(String value) {
        this.extension = value;
    }

    /**
     * Gets the value of the root property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRoot() {
        return root;
    }

    /**
     * Sets the value of the root property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRoot(String value) {
        this.root = value;
    }

}