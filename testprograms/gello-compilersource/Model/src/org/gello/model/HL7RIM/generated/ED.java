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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.gello.model.HL7RIM.generated.BIN;
import org.gello.model.HL7RIM.generated.CompressionAlgorithm;
import org.gello.model.HL7RIM.generated.ED;
import org.gello.model.HL7RIM.generated.IntegrityCheckAlgorithm;
import org.gello.model.HL7RIM.generated.TEL;
import org.gello.model.HL7RIM.generated.Thumbnail;


/**
 *
 *             Data that is primarily intended for human interpretation
 *             or for further machine processing is outside the scope of
 *             HL7. This includes unformatted or formatted written language,
 *             multimedia data, or structured information as defined by a
 *             different standard (e.g., XML-signatures.)  Instead of the
 *             data itself, an ED may contain
 *             only a reference (see TEL.) Note
 *             that the ST data type is a
 *             specialization of
 *             when the  is text/plain.
 *
 *
 * <p>Java class for ED complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ED">
 *   &lt;complexContent>
 *     &lt;extension base="{}BIN">
 *       &lt;sequence>
 *         &lt;element name="reference" type="{}TEL" minOccurs="0"/>
 *         &lt;element name="thumbnail" type="{}thumbnail" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="compression" type="{}CompressionAlgorithm" />
 *       &lt;attribute name="integrityCheck" type="{}bin" />
 *       &lt;attribute name="integrityCheckAlgorithm" type="{}IntegrityCheckAlgorithm" default="SHA-1" />
 *       &lt;attribute name="language" type="{}cs" />
 *       &lt;attribute name="mediaType" type="{}cs" default="text/plain" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ED", propOrder = {
    "reference",
    "thumbnail"
})
public class ED
    extends BIN
{

    protected TEL reference;
    protected Thumbnail thumbnail;
    @XmlAttribute
    protected CompressionAlgorithm compression;
    @XmlAttribute
    protected byte[] integrityCheck;
    @XmlAttribute
    protected IntegrityCheckAlgorithm integrityCheckAlgorithm;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String language;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mediaType;

    /**
     * Gets the value of the reference property.
     *
     * @return
     *     possible object is
     *     {@link TEL }
     *
     */
    public TEL getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     *
     * @param value
     *     allowed object is
     *     {@link TEL }
     *
     */
    public void setReference(TEL value) {
        this.reference = value;
    }

    /**
     * Gets the value of the thumbnail property.
     *
     * @return
     *     possible object is
     *     {@link Thumbnail }
     *
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * Sets the value of the thumbnail property.
     *
     * @param value
     *     allowed object is
     *     {@link Thumbnail }
     *
     */
    public void setThumbnail(Thumbnail value) {
        this.thumbnail = value;
    }

    /**
     * Gets the value of the compression property.
     *
     * @return
     *     possible object is
     *     {@link CompressionAlgorithm }
     *
     */
    public CompressionAlgorithm getCompression() {
        return compression;
    }

    /**
     * Sets the value of the compression property.
     *
     * @param value
     *     allowed object is
     *     {@link CompressionAlgorithm }
     *
     */
    public void setCompression(CompressionAlgorithm value) {
        this.compression = value;
    }

    /**
     * Gets the value of the integrityCheck property.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIntegrityCheck() {
        return integrityCheck;
    }

    /**
     * Sets the value of the integrityCheck property.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIntegrityCheck(byte[] value) {
        this.integrityCheck = ((byte[]) value);
    }

    /**
     * Gets the value of the integrityCheckAlgorithm property.
     *
     * @return
     *     possible object is
     *     {@link IntegrityCheckAlgorithm }
     *
     */
    public IntegrityCheckAlgorithm getIntegrityCheckAlgorithm() {
        if (integrityCheckAlgorithm == null) {
            return IntegrityCheckAlgorithm.SHA_1;
        } else {
            return integrityCheckAlgorithm;
        }
    }

    /**
     * Sets the value of the integrityCheckAlgorithm property.
     *
     * @param value
     *     allowed object is
     *     {@link IntegrityCheckAlgorithm }
     *
     */
    public void setIntegrityCheckAlgorithm(IntegrityCheckAlgorithm value) {
        this.integrityCheckAlgorithm = value;
    }

    /**
     * Gets the value of the language property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the mediaType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMediaType() {
        if (mediaType == null) {
            return "text/plain";
        } else {
            return mediaType;
        }
    }

    /**
     * Sets the value of the mediaType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMediaType(String value) {
        this.mediaType = value;
    }

}
