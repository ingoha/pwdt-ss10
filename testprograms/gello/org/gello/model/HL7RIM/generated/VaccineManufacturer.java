//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import org.gello.model.HL7RIM.generated.VaccineManufacturer;


/**
 * <p>Java class for VaccineManufacturer.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VaccineManufacturer">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="AB"/>
 *     &lt;enumeration value="AD"/>
 *     &lt;enumeration value="ALP"/>
 *     &lt;enumeration value="AR"/>
 *     &lt;enumeration value="PMC"/>
 *     &lt;enumeration value="AVI"/>
 *     &lt;enumeration value="BA"/>
 *     &lt;enumeration value="BAY"/>
 *     &lt;enumeration value="BPC"/>
 *     &lt;enumeration value="BP"/>
 *     &lt;enumeration value="MIP"/>
 *     &lt;enumeration value="CEN"/>
 *     &lt;enumeration value="CHI"/>
 *     &lt;enumeration value="CON"/>
 *     &lt;enumeration value="EVN"/>
 *     &lt;enumeration value="GRE"/>
 *     &lt;enumeration value="IAG"/>
 *     &lt;enumeration value="IUS"/>
 *     &lt;enumeration value="KGC"/>
 *     &lt;enumeration value="LED"/>
 *     &lt;enumeration value="MA"/>
 *     &lt;enumeration value="MED"/>
 *     &lt;enumeration value="MSD"/>
 *     &lt;enumeration value="IM"/>
 *     &lt;enumeration value="MIL"/>
 *     &lt;enumeration value="NAB"/>
 *     &lt;enumeration value="NYB"/>
 *     &lt;enumeration value="NAV"/>
 *     &lt;enumeration value="NOV"/>
 *     &lt;enumeration value="OTC"/>
 *     &lt;enumeration value="ORT"/>
 *     &lt;enumeration value="PD"/>
 *     &lt;enumeration value="PRX"/>
 *     &lt;enumeration value="SCL"/>
 *     &lt;enumeration value="SKB"/>
 *     &lt;enumeration value="SI"/>
 *     &lt;enumeration value="JPN"/>
 *     &lt;enumeration value="USA"/>
 *     &lt;enumeration value="WAL"/>
 *     &lt;enumeration value="WA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum VaccineManufacturer {

    AB,
    AD,
    ALP,
    AR,
    AVI,
    BA,
    BAY,
    BP,
    BPC,
    CEN,
    CHI,
    CON,
    EVN,
    GRE,
    IAG,
    IM,
    IUS,
    JPN,
    KGC,
    LED,
    MA,
    MED,
    MIL,
    MIP,
    MSD,
    NAB,
    NAV,
    NOV,
    NYB,
    ORT,
    OTC,
    PD,
    PMC,
    PRX,
    SCL,
    SI,
    SKB,
    USA,
    WA,
    WAL;

    public String value() {
        return name();
    }

    public VaccineManufacturer fromValue(String v) {
        return valueOf(v);
    }

}
