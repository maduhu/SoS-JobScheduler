//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.03 at 02:04:24 PM CEST 
//


package jaxb.scheduler.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for web_service complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="web_service">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}params" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="debug" type="{}Yes_no" />
 *       &lt;attribute name="forward_xslt_stylesheet" type="{}File" />
 *       &lt;attribute name="job_chain" type="{}Path" />
 *       &lt;attribute name="name" type="{}Name" />
 *       &lt;attribute name="request_xslt_stylesheet" type="{}File" />
 *       &lt;attribute name="response_xslt_stylesheet" type="{}File" />
 *       &lt;attribute name="timeout" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="url_path" use="required" type="{}Url_path" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "web_service", propOrder = {
    "params"
})
public class WebService {

    protected Params params;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String debug;
    @XmlAttribute(name = "forward_xslt_stylesheet")
    protected String forwardXsltStylesheet;
    @XmlAttribute(name = "job_chain")
    protected String jobChain;
    @XmlAttribute
    protected String name;
    @XmlAttribute(name = "request_xslt_stylesheet")
    protected String requestXsltStylesheet;
    @XmlAttribute(name = "response_xslt_stylesheet")
    protected String responseXsltStylesheet;
    @XmlAttribute
    protected BigInteger timeout;
    @XmlAttribute(name = "url_path", required = true)
    protected String urlPath;

    /**
     * Gets the value of the params property.
     * 
     * @return
     *     possible object is
     *     {@link Params }
     *     
     */
    public Params getParams() {
        return params;
    }

    /**
     * Sets the value of the params property.
     * 
     * @param value
     *     allowed object is
     *     {@link Params }
     *     
     */
    public void setParams(Params value) {
        this.params = value;
    }

    /**
     * Gets the value of the debug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebug() {
        return debug;
    }

    /**
     * Sets the value of the debug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebug(String value) {
        this.debug = value;
    }

    /**
     * Gets the value of the forwardXsltStylesheet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForwardXsltStylesheet() {
        return forwardXsltStylesheet;
    }

    /**
     * Sets the value of the forwardXsltStylesheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForwardXsltStylesheet(String value) {
        this.forwardXsltStylesheet = value;
    }

    /**
     * Gets the value of the jobChain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobChain() {
        return jobChain;
    }

    /**
     * Sets the value of the jobChain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobChain(String value) {
        this.jobChain = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the requestXsltStylesheet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestXsltStylesheet() {
        return requestXsltStylesheet;
    }

    /**
     * Sets the value of the requestXsltStylesheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestXsltStylesheet(String value) {
        this.requestXsltStylesheet = value;
    }

    /**
     * Gets the value of the responseXsltStylesheet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseXsltStylesheet() {
        return responseXsltStylesheet;
    }

    /**
     * Sets the value of the responseXsltStylesheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseXsltStylesheet(String value) {
        this.responseXsltStylesheet = value;
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

    /**
     * Gets the value of the urlPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlPath() {
        return urlPath;
    }

    /**
     * Sets the value of the urlPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlPath(String value) {
        this.urlPath = value;
    }

}
