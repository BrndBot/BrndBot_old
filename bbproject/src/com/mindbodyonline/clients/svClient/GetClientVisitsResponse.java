/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */

package com.mindbodyonline.clients.svClient;

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
 *         &lt;element name="GetClientVisitsResult" type="{http://clients.mindbodyonline.com/api/0_5}GetClientVisitsResult" minOccurs="0"/>
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
    "getClientVisitsResult"
})
@XmlRootElement(name = "GetClientVisitsResponse")
public class GetClientVisitsResponse {

    @XmlElement(name = "GetClientVisitsResult")
    protected GetClientVisitsResult getClientVisitsResult;

    /**
     * Gets the value of the getClientVisitsResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetClientVisitsResult }
     *     
     */
    public GetClientVisitsResult getGetClientVisitsResult() {
        return getClientVisitsResult;
    }

    /**
     * Sets the value of the getClientVisitsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetClientVisitsResult }
     *     
     */
    public void setGetClientVisitsResult(GetClientVisitsResult value) {
        this.getClientVisitsResult = value;
    }

}
