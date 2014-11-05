
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
 *         &lt;element name="FinderCheckoutShoppingCartResult" type="{http://clients.mindbodyonline.com/api/0_5}FinderCheckoutShoppingCartResult" minOccurs="0"/>
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
    "finderCheckoutShoppingCartResult"
})
@XmlRootElement(name = "FinderCheckoutShoppingCartResponse")
public class FinderCheckoutShoppingCartResponse {

    @XmlElement(name = "FinderCheckoutShoppingCartResult")
    protected FinderCheckoutShoppingCartResult finderCheckoutShoppingCartResult;

    /**
     * Gets the value of the finderCheckoutShoppingCartResult property.
     * 
     * @return
     *     possible object is
     *     {@link FinderCheckoutShoppingCartResult }
     *     
     */
    public FinderCheckoutShoppingCartResult getFinderCheckoutShoppingCartResult() {
        return finderCheckoutShoppingCartResult;
    }

    /**
     * Sets the value of the finderCheckoutShoppingCartResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinderCheckoutShoppingCartResult }
     *     
     */
    public void setFinderCheckoutShoppingCartResult(FinderCheckoutShoppingCartResult value) {
        this.finderCheckoutShoppingCartResult = value;
    }

}
