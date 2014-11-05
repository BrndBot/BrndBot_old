
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetFinderUserResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetFinderUserResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBResult">
 *       &lt;sequence>
 *         &lt;element name="FinderUser" type="{http://clients.mindbodyonline.com/api/0_5}FinderUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetFinderUserResult", propOrder = {
    "finderUser"
})
public class GetFinderUserResult
    extends MBResult
{

    @XmlElement(name = "FinderUser")
    protected FinderUser finderUser;

    /**
     * Gets the value of the finderUser property.
     * 
     * @return
     *     possible object is
     *     {@link FinderUser }
     *     
     */
    public FinderUser getFinderUser() {
        return finderUser;
    }

    /**
     * Sets the value of the finderUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinderUser }
     *     
     */
    public void setFinderUser(FinderUser value) {
        this.finderUser = value;
    }

}
