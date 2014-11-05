
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetBusinessLocationsWithinRadiusResult" type="{http://clients.mindbodyonline.com/api/0_5}GetBusinessLocationsWithinRadiusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getBusinessLocationsWithinRadiusResult"
})
@XmlRootElement(name = "GetBusinessLocationsWithinRadiusResponse")
public class GetBusinessLocationsWithinRadiusResponse {

    @XmlElement(name = "GetBusinessLocationsWithinRadiusResult")
    protected GetBusinessLocationsWithinRadiusResult getBusinessLocationsWithinRadiusResult;

    /**
     * Gets the value of the getBusinessLocationsWithinRadiusResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetBusinessLocationsWithinRadiusResult }
     *     
     */
    public GetBusinessLocationsWithinRadiusResult getGetBusinessLocationsWithinRadiusResult() {
        return getBusinessLocationsWithinRadiusResult;
    }

    /**
     * Sets the value of the getBusinessLocationsWithinRadiusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetBusinessLocationsWithinRadiusResult }
     *     
     */
    public void setGetBusinessLocationsWithinRadiusResult(GetBusinessLocationsWithinRadiusResult value) {
        this.getBusinessLocationsWithinRadiusResult = value;
    }

}
