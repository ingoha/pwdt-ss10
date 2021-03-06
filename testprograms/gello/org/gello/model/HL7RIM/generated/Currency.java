//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import org.gello.model.HL7RIM.generated.Currency;


/**
 * <p>Java class for Currency.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Currency">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="ARS"/>
 *     &lt;enumeration value="AUD"/>
 *     &lt;enumeration value="THB"/>
 *     &lt;enumeration value="BRL"/>
 *     &lt;enumeration value="CAD"/>
 *     &lt;enumeration value="DEM"/>
 *     &lt;enumeration value="EUR"/>
 *     &lt;enumeration value="FRF"/>
 *     &lt;enumeration value="INR"/>
 *     &lt;enumeration value="TRL"/>
 *     &lt;enumeration value="FIM"/>
 *     &lt;enumeration value="MXN"/>
 *     &lt;enumeration value="NLG"/>
 *     &lt;enumeration value="NZD"/>
 *     &lt;enumeration value="PHP"/>
 *     &lt;enumeration value="GBP"/>
 *     &lt;enumeration value="ZAR"/>
 *     &lt;enumeration value="RUR"/>
 *     &lt;enumeration value="ILS"/>
 *     &lt;enumeration value="ESP"/>
 *     &lt;enumeration value="CHF"/>
 *     &lt;enumeration value="TWD"/>
 *     &lt;enumeration value="USD"/>
 *     &lt;enumeration value="CLF"/>
 *     &lt;enumeration value="KRW"/>
 *     &lt;enumeration value="JPY"/>
 *     &lt;enumeration value="CNY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Currency {

    ARS,
    AUD,
    BRL,
    CAD,
    CHF,
    CLF,
    CNY,
    DEM,
    ESP,
    EUR,
    FIM,
    FRF,
    GBP,
    ILS,
    INR,
    JPY,
    KRW,
    MXN,
    NLG,
    NZD,
    PHP,
    RUR,
    THB,
    TRL,
    TWD,
    USD,
    ZAR;

    public String value() {
        return name();
    }

    public Currency fromValue(String v) {
        return valueOf(v);
    }

}
