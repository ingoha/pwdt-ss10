//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.11.08 at 12:07:49 PM PST 
//


package org.gello.model.HL7RIM.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import org.gello.model.HL7RIM.generated.MaterialEntityAdditive;


/**
 * <p>Java class for MaterialEntityAdditive.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MaterialEntityAdditive">
 *   &lt;restriction base="{}cs">
 *     &lt;enumeration value="F10"/>
 *     &lt;enumeration value="C32"/>
 *     &lt;enumeration value="C38"/>
 *     &lt;enumeration value="HCL6"/>
 *     &lt;enumeration value="ACDA"/>
 *     &lt;enumeration value="ACDB"/>
 *     &lt;enumeration value="ACET"/>
 *     &lt;enumeration value="AMIES"/>
 *     &lt;enumeration value="HEPA"/>
 *     &lt;enumeration value="BACTM"/>
 *     &lt;enumeration value="BOR"/>
 *     &lt;enumeration value="BOUIN"/>
 *     &lt;enumeration value="BF10"/>
 *     &lt;enumeration value="WEST"/>
 *     &lt;enumeration value="BSKM"/>
 *     &lt;enumeration value="CTAD"/>
 *     &lt;enumeration value="CARS"/>
 *     &lt;enumeration value="CARY"/>
 *     &lt;enumeration value="CHLTM"/>
 *     &lt;enumeration value="ENT"/>
 *     &lt;enumeration value="JKM"/>
 *     &lt;enumeration value="KARN"/>
 *     &lt;enumeration value="LIA"/>
 *     &lt;enumeration value="HEPL"/>
 *     &lt;enumeration value="M4"/>
 *     &lt;enumeration value="M4RT"/>
 *     &lt;enumeration value="M5"/>
 *     &lt;enumeration value="MMDTM"/>
 *     &lt;enumeration value="MICHTM"/>
 *     &lt;enumeration value="HNO3"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="PAGE"/>
 *     &lt;enumeration value="PHENOL"/>
 *     &lt;enumeration value="PVA"/>
 *     &lt;enumeration value="KOX"/>
 *     &lt;enumeration value="EDTK15"/>
 *     &lt;enumeration value="EDTK75"/>
 *     &lt;enumeration value="RLM"/>
 *     &lt;enumeration value="SST"/>
 *     &lt;enumeration value="SILICA"/>
 *     &lt;enumeration value="NAF"/>
 *     &lt;enumeration value="FL100"/>
 *     &lt;enumeration value="FL10"/>
 *     &lt;enumeration value="SPS"/>
 *     &lt;enumeration value="HEPN"/>
 *     &lt;enumeration value="EDTN"/>
 *     &lt;enumeration value="STUTM"/>
 *     &lt;enumeration value="THROM"/>
 *     &lt;enumeration value="FDP"/>
 *     &lt;enumeration value="THYMOL"/>
 *     &lt;enumeration value="THYO"/>
 *     &lt;enumeration value="TOLU"/>
 *     &lt;enumeration value="URETM"/>
 *     &lt;enumeration value="VIRTM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum MaterialEntityAdditive {

    ACDA("ACDA"),
    ACDB("ACDB"),
    ACET("ACET"),
    AMIES("AMIES"),
    BACTM("BACTM"),
    @XmlEnumValue("BF10")
    BF_10("BF10"),
    BOR("BOR"),
    BOUIN("BOUIN"),
    BSKM("BSKM"),
    CARS("CARS"),
    CARY("CARY"),
    CHLTM("CHLTM"),
    CTAD("CTAD"),
    @XmlEnumValue("C32")
    C_32("C32"),
    @XmlEnumValue("C38")
    C_38("C38"),
    @XmlEnumValue("EDTK15")
    EDTK_15("EDTK15"),
    @XmlEnumValue("EDTK75")
    EDTK_75("EDTK75"),
    EDTN("EDTN"),
    ENT("ENT"),
    FDP("FDP"),
    @XmlEnumValue("FL10")
    FL_10("FL10"),
    @XmlEnumValue("FL100")
    FL_100("FL100"),
    @XmlEnumValue("F10")
    F_10("F10"),
    @XmlEnumValue("HCL6")
    HCL_6("HCL6"),
    HEPA("HEPA"),
    HEPL("HEPL"),
    HEPN("HEPN"),
    @XmlEnumValue("HNO3")
    HNO_3("HNO3"),
    JKM("JKM"),
    KARN("KARN"),
    KOX("KOX"),
    LIA("LIA"),
    MICHTM("MICHTM"),
    MMDTM("MMDTM"),
    @XmlEnumValue("M4")
    M_4("M4"),
    @XmlEnumValue("M4RT")
    M_4_RT("M4RT"),
    @XmlEnumValue("M5")
    M_5("M5"),
    NAF("NAF"),
    NONE("NONE"),
    PAGE("PAGE"),
    PHENOL("PHENOL"),
    PVA("PVA"),
    RLM("RLM"),
    SILICA("SILICA"),
    SPS("SPS"),
    SST("SST"),
    STUTM("STUTM"),
    THROM("THROM"),
    THYMOL("THYMOL"),
    THYO("THYO"),
    TOLU("TOLU"),
    URETM("URETM"),
    VIRTM("VIRTM"),
    WEST("WEST");
    private final String value;

    MaterialEntityAdditive(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MaterialEntityAdditive fromValue(String v) {
        for (MaterialEntityAdditive c: MaterialEntityAdditive.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
