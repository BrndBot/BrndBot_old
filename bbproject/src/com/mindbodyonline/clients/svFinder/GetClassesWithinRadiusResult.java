
package com.mindbodyonline.clients.svFinder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetClassesWithinRadiusResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetClassesWithinRadiusResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://clients.mindbodyonline.com/api/0_5}MBResult">
 *       &lt;sequence>
 *         &lt;element name="FinderClasses" type="{http://clients.mindbodyonline.com/api/0_5}ArrayOfFinderClass" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetClassesWithinRadiusResult", propOrder = {
    "finderClasses"
})
public class GetClassesWithinRadiusResult
    extends MBResult
{

    @XmlElement(name = "FinderClasses")
    protected ArrayOfFinderClass finderClasses;

    /**
     * Gets the value of the finderClasses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFinderClass }
     *     
     */
    public ArrayOfFinderClass getFinderClasses() {
        return finderClasses;
    }

    /**
     * Sets the value of the finderClasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFinderClass }
     *     
     */
    public void setFinderClasses(ArrayOfFinderClass value) {
        this.finderClasses = value;
    }

}
