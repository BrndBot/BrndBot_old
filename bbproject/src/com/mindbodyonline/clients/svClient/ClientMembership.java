/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */

package com.mindbodyonline.clients.svClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClientMembership complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClientMembership">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}ClientService">
 *       &lt;sequence>
 *         &lt;element name="RestrictedLocations" type="{http://clients.mindbodyonline.com/api/0_5}ArrayOfLocation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClientMembership", propOrder = {
    "restrictedLocations"
})
public class ClientMembership
    extends ClientService
{

    @XmlElement(name = "RestrictedLocations")
    protected ArrayOfLocation restrictedLocations;

    /**
     * Gets the value of the restrictedLocations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLocation }
     *     
     */
    public ArrayOfLocation getRestrictedLocations() {
        return restrictedLocations;
    }

    /**
     * Sets the value of the restrictedLocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLocation }
     *     
     */
    public void setRestrictedLocations(ArrayOfLocation value) {
        this.restrictedLocations = value;
    }

}
