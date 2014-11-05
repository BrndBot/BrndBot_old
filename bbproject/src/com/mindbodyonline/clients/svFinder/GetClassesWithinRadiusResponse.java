
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
 *         &lt;element name="GetClassesWithinRadiusResult" type="{http://clients.mindbodyonline.com/api/0_5}GetClassesWithinRadiusResult" minOccurs="0"/>
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
    "getClassesWithinRadiusResult"
})
@XmlRootElement(name = "GetClassesWithinRadiusResponse")
public class GetClassesWithinRadiusResponse {

    @XmlElement(name = "GetClassesWithinRadiusResult")
    protected GetClassesWithinRadiusResult getClassesWithinRadiusResult;

    /**
     * Gets the value of the getClassesWithinRadiusResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetClassesWithinRadiusResult }
     *     
     */
    public GetClassesWithinRadiusResult getGetClassesWithinRadiusResult() {
        return getClassesWithinRadiusResult;
    }

    /**
     * Sets the value of the getClassesWithinRadiusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetClassesWithinRadiusResult }
     *     
     */
    public void setGetClassesWithinRadiusResult(GetClassesWithinRadiusResult value) {
        this.getClassesWithinRadiusResult = value;
    }

}
