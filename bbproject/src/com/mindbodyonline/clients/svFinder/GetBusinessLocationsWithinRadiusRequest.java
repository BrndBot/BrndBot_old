
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetBusinessLocationsWithinRadiusRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetBusinessLocationsWithinRadiusRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBRequest">
 *       &lt;sequence>
 *         &lt;element name="SearchLatitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SearchLongitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SearchRadius" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SearchLocationID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SearchText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SortOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetBusinessLocationsWithinRadiusRequest", propOrder = {
    "searchLatitude",
    "searchLongitude",
    "searchRadius",
    "searchLocationID",
    "searchText",
    "sortOption",
    "searchDomain"
})
public class GetBusinessLocationsWithinRadiusRequest
    extends MBRequest
{

    @XmlElement(name = "SearchLatitude", required = true, type = Double.class, nillable = true)
    protected Double searchLatitude;
    @XmlElement(name = "SearchLongitude", required = true, type = Double.class, nillable = true)
    protected Double searchLongitude;
    @XmlElement(name = "SearchRadius", required = true, type = Double.class, nillable = true)
    protected Double searchRadius;
    @XmlElement(name = "SearchLocationID", required = true, type = Integer.class, nillable = true)
    protected Integer searchLocationID;
    @XmlElement(name = "SearchText")
    protected String searchText;
    @XmlElement(name = "SortOption")
    protected String sortOption;
    @XmlElement(name = "SearchDomain")
    protected String searchDomain;

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

}
