//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2015.04.28 � 05:45:56 AM EDT 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="job_chain" use="required" type="{}Path" /&gt;
 *       &lt;attribute name="state"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{}String"&gt;
 *             &lt;enumeration value="running"/&gt;
 *             &lt;enumeration value="stopped"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "job_chain.modify")
public class JobChainModify {

    @XmlAttribute(name = "job_chain", required = true)
    protected String jobChain;
    @XmlAttribute(name = "state")
    protected String state;

    /**
     * Obtient la valeur de la propri�t� jobChain.
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
     * D�finit la valeur de la propri�t� jobChain.
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
     * Obtient la valeur de la propri�t� state.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * D�finit la valeur de la propri�t� state.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

}
