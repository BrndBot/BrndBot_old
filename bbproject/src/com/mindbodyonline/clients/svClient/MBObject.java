
package com.mindbodyonline.clients.svClient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MBObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MBObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Site" type="{http://clients.mindbodyonline.com/api/0_5}Site" minOccurs="0"/>
 *         &lt;element name="Messages" type="{http://clients.mindbodyonline.com/api/0_5}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Execute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MBObject", propOrder = {
    "site",
    "messages",
    "execute",
    "errorCode"
})
@XmlSeeAlso({
    ClientContract.class,
    Staff.class,
    SessionType.class,
    ContactLog.class,
    ContactLogType.class,
    ClientIndexValue.class,
    Client.class,
    ContactLogComment.class,
    Program.class,
    Visit.class,
    SaleItem.class,
    ScheduleItem.class,
    ContactLogSubtype.class,
    ClientIndex.class,
    Resource.class,
    ClientRelationship.class,
    Location.class,
    Rep.class,
    ClientService.class,
    FormulaNote.class
})
public class MBObject {

    @XmlElement(name = "Site")
    protected Site site;
    @XmlElement(name = "Messages")
    protected ArrayOfString messages;
    @XmlElement(name = "Execute")
    protected String execute;
    @XmlElement(name = "ErrorCode")
    protected String errorCode;

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
     * Gets the value of the messages property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getMessages() {
        return messages;
    }

    /**
     * Sets the value of the messages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setMessages(ArrayOfString value) {
        this.messages = value;
    }

    /**
     * Gets the value of the execute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecute() {
        return execute;
    }

    /**
     * Sets the value of the execute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecute(String value) {
        this.execute = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

}
