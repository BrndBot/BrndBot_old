/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */

package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppointmentStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AppointmentStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Booked"/>
 *     &lt;enumeration value="Completed"/>
 *     &lt;enumeration value="Confirmed"/>
 *     &lt;enumeration value="Arrived"/>
 *     &lt;enumeration value="NoShow"/>
 *     &lt;enumeration value="Cancelled"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AppointmentStatus")
@XmlEnum
public enum AppointmentStatus {

    @XmlEnumValue("Booked")
    BOOKED("Booked"),
    @XmlEnumValue("Completed")
    COMPLETED("Completed"),
    @XmlEnumValue("Confirmed")
    CONFIRMED("Confirmed"),
    @XmlEnumValue("Arrived")
    ARRIVED("Arrived"),
    @XmlEnumValue("NoShow")
    NO_SHOW("NoShow"),
    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled");
    private final String value;

    AppointmentStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AppointmentStatus fromValue(String v) {
        for (AppointmentStatus c: AppointmentStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
