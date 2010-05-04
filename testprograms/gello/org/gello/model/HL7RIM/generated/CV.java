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
import org.gello.model.HL7RIM.generated.CV;


/**
 *
 *             Coded data, consists of a code, display name, code system,
 *             and original text. Used when a single code value must be sent.
 *
 *
 * <p>Java class for CV complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CV">
 *   &lt;complexContent>
 *     &lt;restriction base="{}CE">
 *       &lt;sequence>
 *         &lt;element name="originalText" type="{}ED" minOccurs="0"/>
 *         &lt;element name="translation" type="{}CD" maxOccurs="0" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="code" type="{}cs" />
 *       &lt;attribute name="codeSystem" type="{}uid" />
 *       &lt;attribute name="codeSystemName" type="{}st" />
 *       &lt;attribute name="codeSystemVersion" type="{}st" />
 *       &lt;attribute name="displayName" type="{}st" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CV")
public class CV
    extends CE
{


}