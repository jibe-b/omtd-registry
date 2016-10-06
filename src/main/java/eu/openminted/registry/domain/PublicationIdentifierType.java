//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.29 at 03:36:47 PM EEST 
//


package eu.openminted.registry.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Base type for identifier schemes for publications
 * 
 * <p>Java class for publicationIdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="publicationIdentifierType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="publicationIdentifierSchemeName" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="doi"/>
 *             &lt;enumeration value="hdl"/>
 *             &lt;enumeration value="ark"/>
 *             &lt;enumeration value="arXiv"/>
 *             &lt;enumeration value="bibcode"/>
 *             &lt;enumeration value="ean13"/>
 *             &lt;enumeration value="eissn"/>
 *             &lt;enumeration value="isbn"/>
 *             &lt;enumeration value="issn"/>
 *             &lt;enumeration value="istc"/>
 *             &lt;enumeration value="lissn"/>
 *             &lt;enumeration value="lsid"/>
 *             &lt;enumeration value="purl"/>
 *             &lt;enumeration value="upc"/>
 *             &lt;enumeration value="url"/>
 *             &lt;enumeration value="urn"/>
 *             &lt;enumeration value="fct"/>
 *             &lt;enumeration value="oai"/>
 *             &lt;enumeration value="pmc"/>
 *             &lt;enumeration value="pmid"/>
 *             &lt;enumeration value="other"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="schemeURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "publicationIdentifierType", propOrder = {
    "value"
})
public class PublicationIdentifierType<T extends IdentifierSchema> {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "publicationIdentifierSchemeName", required = true)
    protected T publicationIdentifierSchemeName;
    @XmlAttribute(name = "schemeURI")
    @XmlSchemaType(name = "anyURI")
    protected String schemeURI;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the publicationIdentifierSchemeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public T getPublicationIdentifierSchemeName() {
        return publicationIdentifierSchemeName;
    }

    /**
     * Sets the value of the publicationIdentifierSchemeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicationIdentifierSchemeName(T value) {
        this.publicationIdentifierSchemeName = value;
    }

    /**
     * Gets the value of the schemeURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchemeURI() {
        return schemeURI;
    }

    /**
     * Sets the value of the schemeURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchemeURI(String value) {
        this.schemeURI = value;
    }

}