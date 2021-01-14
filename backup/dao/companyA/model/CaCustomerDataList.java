//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.14 at 03:39:28 PM CET 
//


package pl.edu.wit.jpa.dao.companyA.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.XMLGregorianCalendarAsDateTime;
import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.XmlAdapterUtils;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for customerDataList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerDataList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="synchronizeNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerDatas" type="{firmaA}customerData" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "customerDatas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerDataList", propOrder = {
    "id",
    "date",
    "synchronizeNo",
    "customerDatas"
})
@Entity(name = "CaCustomerDataList")
@Table(name = "CACUSTOMERDATALIST")
@Inheritance(strategy = InheritanceType.JOINED)
public class CaCustomerDataList
    implements Serializable, Equals, HashCode
{

    protected Long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    protected String synchronizeNo;
    @XmlElement(required = true)
    protected List<CaCustomerData> customerDatas;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Id
    @Column(name = "id", scale = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Transient
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the synchronizeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "SYNCHRONIZENO", length = 255)
    public String getSynchronizeNo() {
        return synchronizeNo;
    }

    /**
     * Sets the value of the synchronizeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSynchronizeNo(String value) {
        this.synchronizeNo = value;
    }

    /**
     * Gets the value of the customerDatas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerDatas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerDatas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CaCustomerData }
     * 
     * 
     */
    @OneToMany(targetEntity = CaCustomerData.class, orphanRemoval = true, cascade = {
        CascadeType.ALL
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMERDATAS_CACUSTOMERDATA_0")
    public List<CaCustomerData> getCustomerDatas() {
        if (customerDatas == null) {
            customerDatas = new ArrayList<CaCustomerData>();
        }
        return this.customerDatas;
    }

    /**
     * 
     * 
     */
    public void setCustomerDatas(List<CaCustomerData> customerDatas) {
        this.customerDatas = customerDatas;
    }

    @Basic
    @Column(name = "DATEITEM")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateItem() {
        return XmlAdapterUtils.unmarshall(XMLGregorianCalendarAsDateTime.class, this.getDate());
    }

    public void setDateItem(Date target) {
        setDate(XmlAdapterUtils.marshall(XMLGregorianCalendarAsDateTime.class, target));
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CaCustomerDataList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CaCustomerDataList that = ((CaCustomerDataList) object);
        {
            Long lhsId;
            lhsId = this.getId();
            Long rhsId;
            rhsId = that.getId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsDate;
            lhsDate = this.getDate();
            XMLGregorianCalendar rhsDate;
            rhsDate = that.getDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "date", lhsDate), LocatorUtils.property(thatLocator, "date", rhsDate), lhsDate, rhsDate)) {
                return false;
            }
        }
        {
            String lhsSynchronizeNo;
            lhsSynchronizeNo = this.getSynchronizeNo();
            String rhsSynchronizeNo;
            rhsSynchronizeNo = that.getSynchronizeNo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "synchronizeNo", lhsSynchronizeNo), LocatorUtils.property(thatLocator, "synchronizeNo", rhsSynchronizeNo), lhsSynchronizeNo, rhsSynchronizeNo)) {
                return false;
            }
        }
        {
            List<CaCustomerData> lhsCustomerDatas;
            lhsCustomerDatas = (((this.customerDatas!= null)&&(!this.customerDatas.isEmpty()))?this.getCustomerDatas():null);
            List<CaCustomerData> rhsCustomerDatas;
            rhsCustomerDatas = (((that.customerDatas!= null)&&(!that.customerDatas.isEmpty()))?that.getCustomerDatas():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerDatas", lhsCustomerDatas), LocatorUtils.property(thatLocator, "customerDatas", rhsCustomerDatas), lhsCustomerDatas, rhsCustomerDatas)) {
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
            Long theId;
            theId = this.getId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
        }
        {
            XMLGregorianCalendar theDate;
            theDate = this.getDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "date", theDate), currentHashCode, theDate);
        }
        {
            String theSynchronizeNo;
            theSynchronizeNo = this.getSynchronizeNo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "synchronizeNo", theSynchronizeNo), currentHashCode, theSynchronizeNo);
        }
        {
            List<CaCustomerData> theCustomerDatas;
            theCustomerDatas = (((this.customerDatas!= null)&&(!this.customerDatas.isEmpty()))?this.getCustomerDatas():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "customerDatas", theCustomerDatas), currentHashCode, theCustomerDatas);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}
