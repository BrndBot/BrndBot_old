
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddOrUpdateFinderUsersRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddOrUpdateFinderUsersRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBRequest">
 *       &lt;sequence>
 *         &lt;element name="UpdateAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Test" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NoClientEmail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PartnerID" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "AddOrUpdateFinderUsersRequest", propOrder = {
    "updateAction",
    "test",
    "noClientEmail",
    "partnerID",
    "finderUsers"
})
public class AddOrUpdateFinderUsersRequest
    extends MBRequest
{

    @XmlElement(name = "UpdateAction")
    protected String updateAction;
    @XmlElement(name = "Test", required = true, type = Boolean.class, nillable = true)
    protected Boolean test;
    @XmlElement(name = "NoClientEmail", required = true, type = Boolean.class, nillable = true)
    protected Boolean noClientEmail;
    @XmlElement(name = "PartnerID")
    protected int partnerID;
    @XmlElement(name = "FinderUsers")
    protected ArrayOfFinderUser finderUsers;

    /**
     * Gets the value of the updateAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateAction() {
        return updateAction;
    }

    /**
     * Sets the value of the updateAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateAction(String value) {
        this.updateAction = value;
    }

    /**
     * Gets the value of the test property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTest() {
        return test;
    }

    /**
     * Sets the value of the test property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTest(Boolean value) {
        this.test = value;
    }

    /**
     * Gets the value of the noClientEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoClientEmail() {
        return noClientEmail;
    }

    /**
     * Sets the value of the noClientEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoClientEmail(Boolean value) {
        this.noClientEmail = value;
    }

    /**
     * Gets the value of the partnerID property.
     * 
     */
    public int getPartnerID() {
        return partnerID;
    }

    /**
     * Sets the value of the partnerID property.
     * 
     */
    public void setPartnerID(int value) {
        this.partnerID = value;
    }

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
