
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
 *         &lt;element name="GetFinderUserResult" type="{http://clients.mindbodyonline.com/api/0_5}GetFinderUserResult" minOccurs="0"/>
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
    "getFinderUserResult"
})
@XmlRootElement(name = "GetFinderUserResponse")
public class GetFinderUserResponse {

    @XmlElement(name = "GetFinderUserResult")
    protected GetFinderUserResult getFinderUserResult;

    /**
     * Gets the value of the getFinderUserResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetFinderUserResult }
     *     
     */
    public GetFinderUserResult getGetFinderUserResult() {
        return getFinderUserResult;
    }

    /**
     * Sets the value of the getFinderUserResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetFinderUserResult }
     *     
     */
    public void setGetFinderUserResult(GetFinderUserResult value) {
        this.getFinderUserResult = value;
    }

}
