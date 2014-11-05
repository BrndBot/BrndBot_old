
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
 *         &lt;element name="GetSessionTypesWithinRadiusResult" type="{http://clients.mindbodyonline.com/api/0_5}GetSessionTypesWithinRadiusResult" minOccurs="0"/>
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
    "getSessionTypesWithinRadiusResult"
})
@XmlRootElement(name = "GetSessionTypesWithinRadiusResponse")
public class GetSessionTypesWithinRadiusResponse {

    @XmlElement(name = "GetSessionTypesWithinRadiusResult")
    protected GetSessionTypesWithinRadiusResult getSessionTypesWithinRadiusResult;

    /**
     * Gets the value of the getSessionTypesWithinRadiusResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetSessionTypesWithinRadiusResult }
     *     
     */
    public GetSessionTypesWithinRadiusResult getGetSessionTypesWithinRadiusResult() {
        return getSessionTypesWithinRadiusResult;
    }

    /**
     * Sets the value of the getSessionTypesWithinRadiusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSessionTypesWithinRadiusResult }
     *     
     */
    public void setGetSessionTypesWithinRadiusResult(GetSessionTypesWithinRadiusResult value) {
        this.getSessionTypesWithinRadiusResult = value;
    }

}
