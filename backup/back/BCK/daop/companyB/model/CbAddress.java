//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.14 at 03:39:28 PM CET 
//


package pl.edu.wit.jpa.dao.companyB.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="countryCode" type="{firmaB}ISO3166CountyCode"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="streetNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="homeNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{firmaB}addressType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
    "countryCode",
    "city",
    "postalCode",
    "street",
    "streetNo",
    "homeNo"
})
@Entity(name = "CbAddress")
@Table(name = "CBADDRESS")
@Inheritance(strategy = InheritanceType.JOINED)
public class CbAddress
    implements Serializable, Equals, HashCode
{

    @XmlElement(required = true)
    protected CbISO3166CountyCode countryCode;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String postalCode;
    @XmlElement(required = true)
    protected String street;
    @XmlElement(required = true)
    protected String streetNo;
    @XmlElement(required = true)
    protected String homeNo;
    @XmlAttribute(name = "type")
    protected CbAddressType type;
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link CbISO3166CountyCode }
     *     
     */
    @Basic
    @Column(name = "COUNTRYCODE", length = 255)
    @Enumerated(EnumType.STRING)
    public CbISO3166CountyCode getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CbISO3166CountyCode }
     *     
     */
    public void setCountryCode(CbISO3166CountyCode value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "CITY", length = 255)
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "POSTALCODE", length = 255)
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "STREET", length = 255)
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the streetNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "STREETNO", length = 255)
    public String getStreetNo() {
        return streetNo;
    }

    /**
     * Sets the value of the streetNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetNo(String value) {
        this.streetNo = value;
    }

    /**
     * Gets the value of the homeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "HOMENO", length = 255)
    public String getHomeNo() {
        return homeNo;
    }

    /**
     * Sets the value of the homeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeNo(String value) {
        this.homeNo = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CbAddressType }
     *     
     */
    @Basic
    @Column(name = "TYPE_", length = 255)
    @Enumerated(EnumType.STRING)
    public CbAddressType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CbAddressType }
     *     
     */
    public void setType(CbAddressType value) {
        this.type = value;
    }

    /**
     * Gets the value of the hjid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Id
    @Column(name = "HJID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getHjid() {
        return hjid;
    }

    /**
     * Sets the value of the hjid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHjid(Long value) {
        this.hjid = value;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CbAddress)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CbAddress that = ((CbAddress) object);
        {
            CbISO3166CountyCode lhsCountryCode;
            lhsCountryCode = this.getCountryCode();
            CbISO3166CountyCode rhsCountryCode;
            rhsCountryCode = that.getCountryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "countryCode", lhsCountryCode), LocatorUtils.property(thatLocator, "countryCode", rhsCountryCode), lhsCountryCode, rhsCountryCode)) {
                return false;
            }
        }
        {
            String lhsCity;
            lhsCity = this.getCity();
            String rhsCity;
            rhsCity = that.getCity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "city", lhsCity), LocatorUtils.property(thatLocator, "city", rhsCity), lhsCity, rhsCity)) {
                return false;
            }
        }
        {
            String lhsPostalCode;
            lhsPostalCode = this.getPostalCode();
            String rhsPostalCode;
            rhsPostalCode = that.getPostalCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "postalCode", lhsPostalCode), LocatorUtils.property(thatLocator, "postalCode", rhsPostalCode), lhsPostalCode, rhsPostalCode)) {
                return false;
            }
        }
        {
            String lhsStreet;
            lhsStreet = this.getStreet();
            String rhsStreet;
            rhsStreet = that.getStreet();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "street", lhsStreet), LocatorUtils.property(thatLocator, "street", rhsStreet), lhsStreet, rhsStreet)) {
                return false;
            }
        }
        {
            String lhsStreetNo;
            lhsStreetNo = this.getStreetNo();
            String rhsStreetNo;
            rhsStreetNo = that.getStreetNo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "streetNo", lhsStreetNo), LocatorUtils.property(thatLocator, "streetNo", rhsStreetNo), lhsStreetNo, rhsStreetNo)) {
                return false;
            }
        }
        {
            String lhsHomeNo;
            lhsHomeNo = this.getHomeNo();
            String rhsHomeNo;
            rhsHomeNo = that.getHomeNo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "homeNo", lhsHomeNo), LocatorUtils.property(thatLocator, "homeNo", rhsHomeNo), lhsHomeNo, rhsHomeNo)) {
                return false;
            }
        }
        {
            CbAddressType lhsType;
            lhsType = this.getType();
            CbAddressType rhsType;
            rhsType = that.getType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "type", lhsType), LocatorUtils.property(thatLocator, "type", rhsType), lhsType, rhsType)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            CbISO3166CountyCode theCountryCode;
            theCountryCode = this.getCountryCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "countryCode", theCountryCode), currentHashCode, theCountryCode);
        }
        {
            String theCity;
            theCity = this.getCity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "city", theCity), currentHashCode, theCity);
        }
        {
            String thePostalCode;
            thePostalCode = this.getPostalCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "postalCode", thePostalCode), currentHashCode, thePostalCode);
        }
        {
            String theStreet;
            theStreet = this.getStreet();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "street", theStreet), currentHashCode, theStreet);
        }
        {
            String theStreetNo;
            theStreetNo = this.getStreetNo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "streetNo", theStreetNo), currentHashCode, theStreetNo);
        }
        {
            String theHomeNo;
            theHomeNo = this.getHomeNo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "homeNo", theHomeNo), currentHashCode, theHomeNo);
        }
        {
            CbAddressType theType;
            theType = this.getType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "type", theType), currentHashCode, theType);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}
