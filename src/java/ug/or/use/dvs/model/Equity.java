/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ug.or.use.dvs.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "EQUITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equity.findAll", query = "SELECT e FROM Equity e"),
    @NamedQuery(name = "Equity.findById", query = "SELECT e FROM Equity e WHERE e.id = :id"),
    @NamedQuery(name = "Equity.findByNameOfSecurity", query = "SELECT e FROM Equity e WHERE e.nameOfSecurity = :nameOfSecurity"),
    @NamedQuery(name = "Equity.findByShortName", query = "SELECT e FROM Equity e WHERE e.shortName = :shortName"),
    @NamedQuery(name = "Equity.findByIsin", query = "SELECT e FROM Equity e WHERE e.isin = :isin"),
    @NamedQuery(name = "Equity.findByIssuerCode", query = "SELECT e FROM Equity e WHERE e.issuerCode = :issuerCode"),
    @NamedQuery(name = "Equity.findByFHLimit", query = "SELECT e FROM Equity e WHERE e.fHLimit = :fHLimit"),
    @NamedQuery(name = "Equity.findByReferencePrice", query = "SELECT e FROM Equity e WHERE e.referencePrice = :referencePrice"),
    @NamedQuery(name = "Equity.findByQuantityIssued", query = "SELECT e FROM Equity e WHERE e.quantityIssued = :quantityIssued"),
    @NamedQuery(name = "Equity.findByDateAdded", query = "SELECT e FROM Equity e WHERE e.dateAdded = :dateAdded"),
    @NamedQuery(name = "Equity.findByAutoTimestamp", query = "SELECT e FROM Equity e WHERE e.autoTimestamp = :autoTimestamp")})
public class Equity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nameOfSecurity")
    private String nameOfSecurity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "shortName")
    private String shortName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ISIN")
    private String isin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "issuerCode")
    private String issuerCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FHLimit")
    private float fHLimit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "referencePrice")
    private float referencePrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantityIssued")
    private float quantityIssued;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equity")
    private Collection<EquityDailyPrice> equityDailyPriceCollection;
    @JoinColumn(name = "COUNTRY_OF_INITIAL_ISSUE", referencedColumnName = "id")
    @ManyToOne
    private Country countryOfInitialIssue;

    public Equity() {
    }

    public Equity(Integer id) {
        this.id = id;
    }

    public Equity(Integer id, String nameOfSecurity, String shortName, String isin, String issuerCode, float fHLimit, float referencePrice, float quantityIssued, Date dateAdded) {
        this.id = id;
        this.nameOfSecurity = nameOfSecurity;
        this.shortName = shortName;
        this.isin = isin;
        this.issuerCode = issuerCode;
        this.fHLimit = fHLimit;
        this.referencePrice = referencePrice;
        this.quantityIssued = quantityIssued;
        this.dateAdded = dateAdded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfSecurity() {
        return nameOfSecurity;
    }

    public void setNameOfSecurity(String nameOfSecurity) {
        this.nameOfSecurity = nameOfSecurity;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public float getFHLimit() {
        return fHLimit;
    }

    public void setFHLimit(float fHLimit) {
        this.fHLimit = fHLimit;
    }

    public float getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(float referencePrice) {
        this.referencePrice = referencePrice;
    }

    public float getQuantityIssued() {
        return quantityIssued;
    }

    public void setQuantityIssued(float quantityIssued) {
        this.quantityIssued = quantityIssued;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    @XmlTransient
    public Collection<EquityDailyPrice> getEquityDailyPriceCollection() {
        return equityDailyPriceCollection;
    }

    public void setEquityDailyPriceCollection(Collection<EquityDailyPrice> equityDailyPriceCollection) {
        this.equityDailyPriceCollection = equityDailyPriceCollection;
    }

    public Country getCountryOfInitialIssue() {
        return countryOfInitialIssue;
    }

    public void setCountryOfInitialIssue(Country countryOfInitialIssue) {
        this.countryOfInitialIssue = countryOfInitialIssue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equity)) {
            return false;
        }
        Equity other = (Equity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.Equity[ id=" + id + " ]";
    }
    
}
