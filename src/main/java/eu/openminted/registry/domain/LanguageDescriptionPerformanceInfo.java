//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.29 at 02:58:21 PM EEST 
//


package eu.openminted.registry.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for languageDescriptionPerformanceInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="languageDescriptionPerformanceInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="robustness" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="shallowness" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="200"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="output" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="500"/>
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
@XmlType(name = "languageDescriptionPerformanceInfoType", propOrder = {
    "robustness",
    "shallowness",
    "output"
})
public class LanguageDescriptionPerformanceInfo {

    protected String robustness;
    protected String shallowness;
    protected String output;

    /**
     * Gets the value of the robustness property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRobustness() {
        return robustness;
    }

    /**
     * Sets the value of the robustness property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRobustness(String value) {
        this.robustness = value;
    }

    /**
     * Gets the value of the shallowness property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShallowness() {
        return shallowness;
    }

    /**
     * Sets the value of the shallowness property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShallowness(String value) {
        this.shallowness = value;
    }

    /**
     * Gets the value of the output property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutput() {
        return output;
    }

    /**
     * Sets the value of the output property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutput(String value) {
        this.output = value;
    }

}
