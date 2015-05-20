//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.22 at 12:28:08 AM PDT 
//


package org.jobscheduler.converter.tws.scheduler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Job.Settings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Job.Settings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mail_on_error" type="{}Yes_no" minOccurs="0"/>
 *         &lt;element name="mail_on_warning" type="{}Yes_no" minOccurs="0"/>
 *         &lt;element name="mail_on_success" type="{}Yes_no" minOccurs="0"/>
 *         &lt;element name="mail_on_process" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union memberTypes=" {}Yes_no {http://www.w3.org/2001/XMLSchema}positiveInteger">
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="mail_on_delay_after_error" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *               &lt;enumeration value="all"/>
 *               &lt;enumeration value="first_only"/>
 *               &lt;enumeration value="last_only"/>
 *               &lt;enumeration value="first_and_last_only"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="log_mail_to" type="{}Non_empty_string" minOccurs="0"/>
 *         &lt;element name="log_mail_cc" type="{}Non_empty_string" minOccurs="0"/>
 *         &lt;element name="log_mail_bcc" type="{}Non_empty_string" minOccurs="0"/>
 *         &lt;element name="log_level" type="{}Log_level" minOccurs="0"/>
 *         &lt;element name="history" type="{}Yes_no" minOccurs="0"/>
 *         &lt;element name="history_on_process" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union memberTypes=" {}Yes_no {http://www.w3.org/2001/XMLSchema}nonNegativeInteger">
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="history_with_log" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *               &lt;enumeration value="yes"/>
 *               &lt;enumeration value="no"/>
 *               &lt;enumeration value="gzip"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Job.Settings", propOrder = {
    "mailOnError",
    "mailOnWarning",
    "mailOnSuccess",
    "mailOnProcess",
    "mailOnDelayAfterError",
    "logMailTo",
    "logMailCc",
    "logMailBcc",
    "logLevel",
    "history",
    "historyOnProcess",
    "historyWithLog"
})
public class JobSettings {

    @XmlElement(name = "mail_on_error")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mailOnError;
    @XmlElement(name = "mail_on_warning")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mailOnWarning;
    @XmlElement(name = "mail_on_success")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mailOnSuccess;
    @XmlElement(name = "mail_on_process")
    protected String mailOnProcess;
    @XmlElement(name = "mail_on_delay_after_error")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mailOnDelayAfterError;
    @XmlElement(name = "log_mail_to")
    protected String logMailTo;
    @XmlElement(name = "log_mail_cc")
    protected String logMailCc;
    @XmlElement(name = "log_mail_bcc")
    protected String logMailBcc;
    @XmlElement(name = "log_level")
    protected LogLevel logLevel;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String history;
    @XmlElement(name = "history_on_process")
    protected String historyOnProcess;
    @XmlElement(name = "history_with_log")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String historyWithLog;

    /**
     * Gets the value of the mailOnError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailOnError() {
        return mailOnError;
    }

    /**
     * Sets the value of the mailOnError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailOnError(String value) {
        this.mailOnError = value;
    }

    /**
     * Gets the value of the mailOnWarning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailOnWarning() {
        return mailOnWarning;
    }

    /**
     * Sets the value of the mailOnWarning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailOnWarning(String value) {
        this.mailOnWarning = value;
    }

    /**
     * Gets the value of the mailOnSuccess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailOnSuccess() {
        return mailOnSuccess;
    }

    /**
     * Sets the value of the mailOnSuccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailOnSuccess(String value) {
        this.mailOnSuccess = value;
    }

    /**
     * Gets the value of the mailOnProcess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailOnProcess() {
        return mailOnProcess;
    }

    /**
     * Sets the value of the mailOnProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailOnProcess(String value) {
        this.mailOnProcess = value;
    }

    /**
     * Gets the value of the mailOnDelayAfterError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailOnDelayAfterError() {
        return mailOnDelayAfterError;
    }

    /**
     * Sets the value of the mailOnDelayAfterError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailOnDelayAfterError(String value) {
        this.mailOnDelayAfterError = value;
    }

    /**
     * Gets the value of the logMailTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogMailTo() {
        return logMailTo;
    }

    /**
     * Sets the value of the logMailTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogMailTo(String value) {
        this.logMailTo = value;
    }

    /**
     * Gets the value of the logMailCc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogMailCc() {
        return logMailCc;
    }

    /**
     * Sets the value of the logMailCc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogMailCc(String value) {
        this.logMailCc = value;
    }

    /**
     * Gets the value of the logMailBcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogMailBcc() {
        return logMailBcc;
    }

    /**
     * Sets the value of the logMailBcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogMailBcc(String value) {
        this.logMailBcc = value;
    }

    /**
     * Gets the value of the logLevel property.
     * 
     * @return
     *     possible object is
     *     {@link LogLevel }
     *     
     */
    public LogLevel getLogLevel() {
        return logLevel;
    }

    /**
     * Sets the value of the logLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogLevel }
     *     
     */
    public void setLogLevel(LogLevel value) {
        this.logLevel = value;
    }

    /**
     * Gets the value of the history property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHistory() {
        return history;
    }

    /**
     * Sets the value of the history property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHistory(String value) {
        this.history = value;
    }

    /**
     * Gets the value of the historyOnProcess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHistoryOnProcess() {
        return historyOnProcess;
    }

    /**
     * Sets the value of the historyOnProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHistoryOnProcess(String value) {
        this.historyOnProcess = value;
    }

    /**
     * Gets the value of the historyWithLog property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHistoryWithLog() {
        return historyWithLog;
    }

    /**
     * Sets the value of the historyWithLog property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHistoryWithLog(String value) {
        this.historyWithLog = value;
    }

}
