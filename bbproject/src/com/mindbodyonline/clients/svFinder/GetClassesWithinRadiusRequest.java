
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GetClassesWithinRadiusRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetClassesWithinRadiusRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBRequest">
 *       &lt;sequence>
 *         &lt;element name="SearchLatitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SearchLongitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SearchRadius" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="StartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SearchLocationID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SearchClassID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SearchText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SortOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetClassesWithinRadiusRequest", propOrder = {
    "searchLatitude",
    "searchLongitude",
    "searchRadius",
    "startDateTime",
    "endDateTime",
    "searchLocationID",
    "searchClassID",
    "searchText",
    "sortOption",
    "searchDomain",
    "ipAddress"
})
public class GetClassesWithinRadiusRequest
    extends MBRequest
{

    @XmlElement(name = "SearchLatitude", required = true, type = Double.class, nillable = true)
    protected Double searchLatitude;
    @XmlElement(name = "SearchLongitude", required = true, type = Double.class, nillable = true)
    protected Double searchLongitude;
    @XmlElement(name = "SearchRadius", required = true, type = Double.class, nillable = true)
    protected Double searchRadius;
    @XmlElement(name = "StartDateTime", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDateTime;
    @XmlElement(name = "EndDateTime", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDateTime;
    @XmlElement(name = "SearchLocationID", required = true, type = Integer.class, nillable = true)
    protected Integer searchLocationID;
    @XmlElement(name = "SearchClassID", required = true, type = Integer.class, nillable = true)
    protected Integer searchClassID;
    @XmlElement(name = "SearchText")
    protected String searchText;
    @XmlElement(name = "SortOption")
    protected String sortOption;
    @XmlElement(name = "SearchDomain")
    protected String searchDomain;
    @XmlElement(name = "IPAddress")
    protected String ipAddress;

    /**
     * Gets the value of the searchLatitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSearchLatitude() {
        return searchLatitude;
    }

    /**
     * Sets the value of the searchLatitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSearchLatitude(Double value) {
        this.searchLatitude = value;
    }

    /**
     * Gets the value of the searchLongitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSearchLongitude() {
        return searchLongitude;
    }

    /**
     * Sets the value of the searchLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSearchLongitude(Double value) {
        this.searchLongitude = value;
    }

    /**
     * Gets the value of the searchRadius property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSearchRadius() {
        return searchRadius;
    }

    /**
     * Sets the value of the searchRadius property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSearchRadius(Double value) {
        this.searchRadius = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDateTime(XMLGregorianCalendar value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the endDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the value of the endDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDateTime(XMLGregorianCalendar value) {
        this.endDateTime = value;
    }

    /**
     * Gets the value of the searchLocationID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSearchLocationID() {
        return searchLocationID;
    }

    /**
     * Sets the value of the searchLocationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSearchLocationID(Integer value) {
        this.searchLocationID = value;
    }

    /**
     * Gets the value of the searchClassID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSearchClassID() {
        return searchClassID;
    }

    /**
     * Sets the value of the searchClassID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSearchClassID(Integer value) {
        this.searchClassID = value;
    }

    /**
     * Gets the value of the searchText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Sets the value of the searchText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchText(String value) {
        this.searchText = value;
    }

    /**
     * Gets the value of the sortOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortOption() {
        return sortOption;
    }

    /**
     * Sets the value of the sortOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortOption(String value) {
        this.sortOption = value;
    }

    /**
     * Gets the value of the searchDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchDomain() {
        return searchDomain;
    }

    /**
     * Sets the value of the searchDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchDomain(String value) {
        this.searchDomain = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPAddress(String value) {
        this.ipAddress = value;
    }

}
