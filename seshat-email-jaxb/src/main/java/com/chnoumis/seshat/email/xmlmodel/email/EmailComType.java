//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.11 at 03:22:22 AM AEST 
//


package com.chnoumis.seshat.email.xmlmodel.email;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmailComType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmailComType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{http://chnoumis.com/seshat/email/xmlmodel/email}From"/&gt;
 *         &lt;element ref="{http://chnoumis.com/seshat/email/xmlmodel/email}To" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{http://chnoumis.com/seshat/email/xmlmodel/email}Cc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://chnoumis.com/seshat/email/xmlmodel/email}Bcc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="msg" type="{http://chnoumis.com/seshat/email/xmlmodel/email}MsgComType"/&gt;
 *         &lt;element name="subject" type="{http://chnoumis.com/seshat/email/xmlmodel/email}MsgComType"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmailComType", propOrder = {
    "userId",
    "from",
    "to",
    "cc",
    "bcc",
    "msg",
    "subject",
    "status"
})
public class EmailComType {

    @XmlElement(required = true)
    protected String userId;
    @XmlElement(name = "From", required = true)
    protected FromComType from;
    @XmlElement(name = "To", required = true)
    protected List<ToComType> to;
    @XmlElement(name = "Cc")
    protected List<CcComType> cc;
    @XmlElement(name = "Bcc")
    protected List<BccComType> bcc;
    @XmlElement(required = true)
    protected MsgComType msg;
    @XmlElement(required = true)
    protected MsgComType subject;
    protected String status;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link FromComType }
     *     
     */
    public FromComType getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link FromComType }
     *     
     */
    public void setFrom(FromComType value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the to property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ToComType }
     * 
     * 
     */
    public List<ToComType> getTo() {
        if (to == null) {
            to = new ArrayList<ToComType>();
        }
        return this.to;
    }

    /**
     * Gets the value of the cc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CcComType }
     * 
     * 
     */
    public List<CcComType> getCc() {
        if (cc == null) {
            cc = new ArrayList<CcComType>();
        }
        return this.cc;
    }

    /**
     * Gets the value of the bcc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bcc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBcc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BccComType }
     * 
     * 
     */
    public List<BccComType> getBcc() {
        if (bcc == null) {
            bcc = new ArrayList<BccComType>();
        }
        return this.bcc;
    }

    /**
     * Gets the value of the msg property.
     * 
     * @return
     *     possible object is
     *     {@link MsgComType }
     *     
     */
    public MsgComType getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgComType }
     *     
     */
    public void setMsg(MsgComType value) {
        this.msg = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link MsgComType }
     *     
     */
    public MsgComType getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgComType }
     *     
     */
    public void setSubject(MsgComType value) {
        this.subject = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
