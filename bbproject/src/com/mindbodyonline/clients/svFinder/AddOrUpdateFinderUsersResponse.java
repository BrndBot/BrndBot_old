/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */

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
 *         &lt;element name="AddOrUpdateFinderUsersResult" type="{http://clients.mindbodyonline.com/api/0_5}AddOrUpdateFinderUsersResult" minOccurs="0"/>
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
    "addOrUpdateFinderUsersResult"
})
@XmlRootElement(name = "AddOrUpdateFinderUsersResponse")
public class AddOrUpdateFinderUsersResponse {

    @XmlElement(name = "AddOrUpdateFinderUsersResult")
    protected AddOrUpdateFinderUsersResult addOrUpdateFinderUsersResult;

    /**
     * Gets the value of the addOrUpdateFinderUsersResult property.
     * 
     * @return
     *     possible object is
     *     {@link AddOrUpdateFinderUsersResult }
     *     
     */
    public AddOrUpdateFinderUsersResult getAddOrUpdateFinderUsersResult() {
        return addOrUpdateFinderUsersResult;
    }

    /**
     * Sets the value of the addOrUpdateFinderUsersResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddOrUpdateFinderUsersResult }
     *     
     */
    public void setAddOrUpdateFinderUsersResult(AddOrUpdateFinderUsersResult value) {
        this.addOrUpdateFinderUsersResult = value;
    }

}
