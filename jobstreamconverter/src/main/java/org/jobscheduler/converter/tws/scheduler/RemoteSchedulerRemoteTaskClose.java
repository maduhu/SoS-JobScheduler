//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.22 at 12:28:08 AM PDT 
//


package org.jobscheduler.converter.tws.scheduler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for remote_scheduler.remote_task.close element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="remote_scheduler.remote_task.close">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;attribute name="kill" type="{}Yes_no" />
 *         &lt;attribute name="process_id" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "remote_scheduler.remote_task.close")
public class RemoteSchedulerRemoteTaskClose {

    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String kill;
    @XmlAttribute(name = "process_id", required = true)
    protected long processId;

    /**
     * Gets the value of the kill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKill() {
        return kill;
    }

    /**
     * Sets the value of the kill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKill(String value) {
        this.kill = value;
    }

    /**
     * Gets the value of the processId property.
     * 
     */
    public long getProcessId() {
        return processId;
    }

    /**
     * Sets the value of the processId property.
     * 
     */
    public void setProcessId(long value) {
        this.processId = value;
    }

}
