
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetSessionTypesWithinRadiusResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetSessionTypesWithinRadiusResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBResult">
 *       &lt;sequence>
 *         &lt;element name="FinderAppointments" type="{http://clients.mindbodyonline.com/api/0_5}ArrayOfFinderAppointment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSessionTypesWithinRadiusResult", propOrder = {
    "finderAppointments"
})
public class GetSessionTypesWithinRadiusResult
    extends MBResult
{

    @XmlElement(name = "FinderAppointments")
    protected ArrayOfFinderAppointment finderAppointments;

    /**
     * Gets the value of the finderAppointments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFinderAppointment }
     *     
     */
    public ArrayOfFinderAppointment getFinderAppointments() {
        return finderAppointments;
    }

    /**
     * Sets the value of the finderAppointments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFinderAppointment }
     *     
     */
    public void setFinderAppointments(ArrayOfFinderAppointment value) {
        this.finderAppointments = value;
    }

}
