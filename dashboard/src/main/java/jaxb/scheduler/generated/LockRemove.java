//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.03 at 02:04:24 PM CEST 
//


package jaxb.scheduler.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lock.remove complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lock.remove">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="lock" use="required" type="{}Path" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lock.remove")
public class LockRemove {

    @XmlAttribute(required = true)
    protected String lock;

    /**
     * Gets the value of the lock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLock() {
        return lock;
    }

    /**
     * Sets the value of the lock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLock(String value) {
        this.lock = value;
    }

}
