
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FinderAppointment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FinderAppointment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Site" type="{http://clients.mindbodyonline.com/api/0_5}Site" minOccurs="0"/>
 *         &lt;element name="Organization" type="{http://clients.mindbodyonline.com/api/0_5}Organization" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://clients.mindbodyonline.com/api/0_5}Location" minOccurs="0"/>
 *         &lt;element name="MBFSessionTypeID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="GUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SessionType" type="{http://clients.mindbodyonline.com/api/0_5}FinderSessionType" minOccurs="0"/>
 *         &lt;element name="Program" type="{http://clients.mindbodyonline.com/api/0_5}Program" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TaxRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TaxAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Featured" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FinderAppointment", propOrder = {
    "site",
    "organization",
    "location",
    "mbfSessionTypeID",
    "guid",
    "sessionType",
    "program",
    "price",
    "taxRate",
    "taxAmount",
    "featured"
})
public class FinderAppointment {

    @XmlElement(name = "Site")
    protected Site site;
    @XmlElement(name = "Organization")
    protected Organization organization;
    @XmlElement(name = "Location")
    protected Location location;
    @XmlElement(name = "MBFSessionTypeID")
    protected long mbfSessionTypeID;
    @XmlElement(name = "GUID")
    protected String guid;
    @XmlElement(name = "SessionType")
    protected FinderSessionType sessionType;
    @XmlElement(name = "Program")
    protected Program program;
    @XmlElement(name = "Price", required = true, type = Double.class, nillable = true)
    protected Double price;
    @XmlElement(name = "TaxRate")
    protected double taxRate;
    @XmlElement(name = "TaxAmount")
    protected double taxAmount;
    @XmlElement(name = "Featured")
    protected boolean featured;

    /**
     * Gets the value of the site property.
     * 
     * @return
     *     possible object is
     *     {@link Site }
     *     
     */
    public Site getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     * 
     * @param value
     *     allowed object is
     *     {@link Site }
     *     
     */
    public void setSite(Site value) {
        this.site = value;
    }

    /**
     * Gets the value of the organization property.
     * 
     * @return
     *     possible object is
     *     {@link Organization }
     *     
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Organization }
     *     
     */
    public void setOrganization(Organization value) {
        this.organization = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the mbfSessionTypeID property.
     * 
     */
    public long getMBFSessionTypeID() {
        return mbfSessionTypeID;
    }

    /**
     * Sets the value of the mbfSessionTypeID property.
     * 
     */
    public void setMBFSessionTypeID(long value) {
        this.mbfSessionTypeID = value;
    }

    /**
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGUID() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGUID(String value) {
        this.guid = value;
    }

    /**
     * Gets the value of the sessionType property.
     * 
     * @return
     *     possible object is
     *     {@link FinderSessionType }
     *     
     */
    public FinderSessionType getSessionType() {
        return sessionType;
    }

    /**
     * Sets the value of the sessionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinderSessionType }
     *     
     */
    public void setSessionType(FinderSessionType value) {
        this.sessionType = value;
    }

    /**
     * Gets the value of the program property.
     * 
     * @return
     *     possible object is
     *     {@link Program }
     *     
     */
    public Program getProgram() {
        return program;
    }

    /**
     * Sets the value of the program property.
     * 
     * @param value
     *     allowed object is
     *     {@link Program }
     *     
     */
    public void setProgram(Program value) {
        this.program = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrice(Double value) {
        this.price = value;
    }

    /**
     * Gets the value of the taxRate property.
     * 
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Sets the value of the taxRate property.
     * 
     */
    public void setTaxRate(double value) {
        this.taxRate = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     */
    public double getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     */
    public void setTaxAmount(double value) {
        this.taxAmount = value;
    }

    /**
     * Gets the value of the featured property.
     * 
     */
    public boolean isFeatured() {
        return featured;
    }

    /**
     * Sets the value of the featured property.
     * 
     */
    public void setFeatured(boolean value) {
        this.featured = value;
    }

}
