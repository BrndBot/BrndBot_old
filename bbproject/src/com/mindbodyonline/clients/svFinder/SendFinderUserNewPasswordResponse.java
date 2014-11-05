
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
 *         &lt;element name="SendFinderUserNewPasswordResult" type="{http://clients.mindbodyonline.com/api/0_5}SendFinderUserNewPasswordResult" minOccurs="0"/>
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
    "sendFinderUserNewPasswordResult"
})
@XmlRootElement(name = "SendFinderUserNewPasswordResponse")
public class SendFinderUserNewPasswordResponse {

    @XmlElement(name = "SendFinderUserNewPasswordResult")
    protected SendFinderUserNewPasswordResult sendFinderUserNewPasswordResult;

    /**
     * Gets the value of the sendFinderUserNewPasswordResult property.
     * 
     * @return
     *     possible object is
     *     {@link SendFinderUserNewPasswordResult }
     *     
     */
    public SendFinderUserNewPasswordResult getSendFinderUserNewPasswordResult() {
        return sendFinderUserNewPasswordResult;
    }

    /**
     * Sets the value of the sendFinderUserNewPasswordResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SendFinderUserNewPasswordResult }
     *     
     */
    public void setSendFinderUserNewPasswordResult(SendFinderUserNewPasswordResult value) {
        this.sendFinderUserNewPasswordResult = value;
    }

}
