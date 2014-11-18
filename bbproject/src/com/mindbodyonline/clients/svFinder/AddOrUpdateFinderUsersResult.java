/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */

package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddOrUpdateFinderUsersResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddOrUpdateFinderUsersResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBResult">
 *       &lt;sequence>
 *         &lt;element name="FinderUsers" type="{http://clients.mindbodyonline.com/api/0_5}ArrayOfFinderUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddOrUpdateFinderUsersResult", propOrder = {
    "finderUsers"
})
public class AddOrUpdateFinderUsersResult
    extends MBResult
{

    @XmlElement(name = "FinderUsers")
    protected ArrayOfFinderUser finderUsers;

    /**
     * Gets the value of the finderUsers property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFinderUser }
     *     
     */
    public ArrayOfFinderUser getFinderUsers() {
        return finderUsers;
    }

    /**
     * Sets the value of the finderUsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFinderUser }
     *     
     */
    public void setFinderUsers(ArrayOfFinderUser value) {
        this.finderUsers = value;
    }

}
