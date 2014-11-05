
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FinderCheckoutShoppingCartRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FinderCheckoutShoppingCartRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBRequest">
 *       &lt;sequence>
 *         &lt;element name="Test" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NoClientEmail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MBFClassID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MBFSessionTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SessionDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="StaffID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PartnerID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SearchLatitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SearchLongitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SaveCCInfo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SpaFinderWellnessCard" type="{http://clients.mindbodyonline.com/api/0_5}SpaFinderWellnessCard" minOccurs="0"/>
 *         &lt;element name="PaymentInfo" type="{http://clients.mindbodyonline.com/api/0_5}PaymentInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FinderCheckoutShoppingCartRequest", propOrder = {
    "test",
    "noClientEmail",
    "mbfClassID",
    "mbfSessionTypeID",
    "sessionDateTime",
    "staffID",
    "partnerID",
    "searchLatitude",
    "searchLongitude",
    "saveCCInfo",
    "spaFinderWellnessCard",
    "paymentInfo"
})
public class FinderCheckoutShoppingCartRequest
    extends MBRequest
{

    @XmlElement(name = "Test", required = true, type = Boolean.class, nillable = true)
    protected Boolean test;
    @XmlElement(name = "NoClientEmail", required = true, type = Boolean.class, nillable = true)
    protected Boolean noClientEmail;
    @XmlElement(name = "MBFClassID")
    protected int mbfClassID;
    @XmlElement(name = "MBFSessionTypeID")
    protected int mbfSessionTypeID;
    @XmlElement(name = "SessionDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sessionDateTime;
    @XmlElement(name = "StaffID")
    protected long staffID;
    @XmlElement(name = "PartnerID")
    protected int partnerID;
    @XmlElement(name = "SearchLatitude")
    protected double searchLatitude;
    @XmlElement(name = "SearchLongitude")
    protected double searchLongitude;
    @XmlElement(name = "SaveCCInfo", required = true, type = Boolean.class, nillable = true)
    protected Boolean saveCCInfo;
    @XmlElement(name = "SpaFinderWellnessCard")
    protected SpaFinderWellnessCard spaFinderWellnessCard;
    @XmlElement(name = "PaymentInfo")
    protected PaymentInfo paymentInfo;

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
     * Gets the value of the mbfClassID property.
     * 
     */
    public int getMBFClassID() {
        return mbfClassID;
    }

    /**
     * Sets the value of the mbfClassID property.
     * 
     */
    public void setMBFClassID(int value) {
        this.mbfClassID = value;
    }

    /**
     * Gets the value of the mbfSessionTypeID property.
     * 
     */
    public int getMBFSessionTypeID() {
        return mbfSessionTypeID;
    }

    /**
     * Sets the value of the mbfSessionTypeID property.
     * 
     */
    public void setMBFSessionTypeID(int value) {
        this.mbfSessionTypeID = value;
    }

    /**
     * Gets the value of the sessionDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSessionDateTime() {
        return sessionDateTime;
    }

    /**
     * Sets the value of the sessionDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSessionDateTime(XMLGregorianCalendar value) {
        this.sessionDateTime = value;
    }

    /**
     * Gets the value of the staffID property.
     * 
     */
    public long getStaffID() {
        return staffID;
    }

    /**
     * Sets the value of the staffID property.
     * 
     */
    public void setStaffID(long value) {
        this.staffID = value;
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
     * Gets the value of the searchLatitude property.
     * 
     */
    public double getSearchLatitude() {
        return searchLatitude;
    }

    /**
     * Sets the value of the searchLatitude property.
     * 
     */
    public void setSearchLatitude(double value) {
        this.searchLatitude = value;
    }

    /**
     * Gets the value of the searchLongitude property.
     * 
     */
    public double getSearchLongitude() {
        return searchLongitude;
    }

    /**
     * Sets the value of the searchLongitude property.
     * 
     */
    public void setSearchLongitude(double value) {
        this.searchLongitude = value;
    }

    /**
     * Gets the value of the saveCCInfo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaveCCInfo() {
        return saveCCInfo;
    }

    /**
     * Sets the value of the saveCCInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaveCCInfo(Boolean value) {
        this.saveCCInfo = value;
    }

    /**
     * Gets the value of the spaFinderWellnessCard property.
     * 
     * @return
     *     possible object is
     *     {@link SpaFinderWellnessCard }
     *     
     */
    public SpaFinderWellnessCard getSpaFinderWellnessCard() {
        return spaFinderWellnessCard;
    }

    /**
     * Sets the value of the spaFinderWellnessCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpaFinderWellnessCard }
     *     
     */
    public void setSpaFinderWellnessCard(SpaFinderWellnessCard value) {
        this.spaFinderWellnessCard = value;
    }

    /**
     * Gets the value of the paymentInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentInfo }
     *     
     */
    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * Sets the value of the paymentInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentInfo }
     *     
     */
    public void setPaymentInfo(PaymentInfo value) {
        this.paymentInfo = value;
    }

}
