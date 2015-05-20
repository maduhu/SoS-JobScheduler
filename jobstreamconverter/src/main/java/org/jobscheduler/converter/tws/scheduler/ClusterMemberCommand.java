//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.22 at 12:28:08 AM PDT 
//


package org.jobscheduler.converter.tws.scheduler;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jobscheduler.converter.tws.scheduler.ClusterMemberCommand.Terminate;


/**
 * <p>Java class for cluster_member_command element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="cluster_member_command">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;choice>
 *           &lt;element name="terminate">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;attribute name="restart" type="{}Yes_no" />
 *                   &lt;attribute name="timeout" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "terminate"
})
@XmlRootElement(name = "cluster_member_command")
public class ClusterMemberCommand {

    protected Terminate terminate;

    /**
     * Gets the value of the terminate property.
     * 
     * @return
     *     possible object is
     *     {@link Terminate }
     *     
     */
    public Terminate getTerminate() {
        return terminate;
    }

    /**
     * Sets the value of the terminate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Terminate }
     *     
     */
    public void setTerminate(Terminate value) {
        this.terminate = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="restart" type="{}Yes_no" />
     *       &lt;attribute name="timeout" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Terminate {

        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String restart;
        @XmlAttribute
        protected BigInteger timeout;

        /**
         * Gets the value of the restart property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRestart() {
            return restart;
        }

        /**
         * Sets the value of the restart property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRestart(String value) {
            this.restart = value;
        }

        /**
         * Gets the value of the timeout property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTimeout() {
            return timeout;
        }

        /**
         * Sets the value of the timeout property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTimeout(BigInteger value) {
            this.timeout = value;
        }

    }

}
