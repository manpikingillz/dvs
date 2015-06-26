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
@Table(name = "BOND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bond.findAll", query = "SELECT b FROM Bond b"),
    @NamedQuery(name = "Bond.findById", query = "SELECT b FROM Bond b WHERE b.id = :id"),
    @NamedQuery(name = "Bond.findByIssuerCode", query = "SELECT b FROM Bond b WHERE b.issuerCode = :issuerCode"),
    @NamedQuery(name = "Bond.findByIsin", query = "SELECT b FROM Bond b WHERE b.isin = :isin"),
    @NamedQuery(name = "Bond.findByShortName", query = "SELECT b FROM Bond b WHERE b.shortName = :shortName"),
    @NamedQuery(name = "Bond.findByParValue", query = "SELECT b FROM Bond b WHERE b.parValue = :parValue"),
    @NamedQuery(name = "Bond.findByQuantityIssued", query = "SELECT b FROM Bond b WHERE b.quantityIssued = :quantityIssued"),
    @NamedQuery(name = "Bond.findByDateIssued", query = "SELECT b FROM Bond b WHERE b.dateIssued = :dateIssued"),
    @NamedQuery(name = "Bond.findByDateExpire", query = "SELECT b FROM Bond b WHERE b.dateExpire = :dateExpire"),
    @NamedQuery(name = "Bond.findByCouponRate", query = "SELECT b FROM Bond b WHERE b.couponRate = :couponRate"),
    @NamedQuery(name = "Bond.findByDateFirstCoupon", query = "SELECT b FROM Bond b WHERE b.dateFirstCoupon = :dateFirstCoupon"),
    @NamedQuery(name = "Bond.findByDateFinalCoupon", query = "SELECT b FROM Bond b WHERE b.dateFinalCoupon = :dateFinalCoupon"),
    @NamedQuery(name = "Bond.findByTerm", query = "SELECT b FROM Bond b WHERE b.term = :term"),
    @NamedQuery(name = "Bond.findByAutoTimestamp", query = "SELECT b FROM Bond b WHERE b.autoTimestamp = :autoTimestamp")})
public class Bond implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "issuerCode")
    private String issuerCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ISIN")
    private String isin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "shortName")
    private String shortName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parValue")
    private float parValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantityIssued")
    private float quantityIssued;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateIssued")
    @Temporal(TemporalType.DATE)
    private Date dateIssued;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateExpire")
    @Temporal(TemporalType.DATE)
    private Date dateExpire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "couponRate")
    private float couponRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateFirstCoupon")
    @Temporal(TemporalType.DATE)
    private Date dateFirstCoupon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateFinalCoupon")
    @Temporal(TemporalType.DATE)
    private Date dateFinalCoupon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "term")
    private int term;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinColumn(name = "CURRENCY_TYPE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CurrencyType currencyType;
    @JoinColumn(name = "COUNTRY_OF_INITIAL_ISSUE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country countryOfInitialIssue;
    @JoinColumn(name = "BOND_TYPE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BondType bondType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bond")
    private Collection<BondDailyPrice> bondDailyPriceCollection;

    public Bond() {
    }

    public Bond(Integer id) {
        this.id = id;
    }

    public Bond(Integer id, String issuerCode, String isin, String shortName, float parValue, float quantityIssued, Date dateIssued, Date dateExpire, float couponRate, Date dateFirstCoupon, Date dateFinalCoupon, int term) {
        this.id = id;
        this.issuerCode = issuerCode;
        this.isin = isin;
        this.shortName = shortName;
        this.parValue = parValue;
        this.quantityIssued = quantityIssued;
        this.dateIssued = dateIssued;
        this.dateExpire = dateExpire;
        this.couponRate = couponRate;
        this.dateFirstCoupon = dateFirstCoupon;
        this.dateFinalCoupon = dateFinalCoupon;
        this.term = term;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public float getParValue() {
        return parValue;
    }

    public void setParValue(float parValue) {
        this.parValue = parValue;
    }

    public float getQuantityIssued() {
        return quantityIssued;
    }

    public void setQuantityIssued(float quantityIssued) {
        this.quantityIssued = quantityIssued;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    public float getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(float couponRate) {
        this.couponRate = couponRate;
    }

    public Date getDateFirstCoupon() {
        return dateFirstCoupon;
    }

    public void setDateFirstCoupon(Date dateFirstCoupon) {
        this.dateFirstCoupon = dateFirstCoupon;
    }

    public Date getDateFinalCoupon() {
        return dateFinalCoupon;
    }

    public void setDateFinalCoupon(Date dateFinalCoupon) {
        this.dateFinalCoupon = dateFinalCoupon;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Country getCountryOfInitialIssue() {
        return countryOfInitialIssue;
    }

    public void setCountryOfInitialIssue(Country countryOfInitialIssue) {
        this.countryOfInitialIssue = countryOfInitialIssue;
    }

    public BondType getBondType() {
        return bondType;
    }

    public void setBondType(BondType bondType) {
        this.bondType = bondType;
    }

    @XmlTransient
    public Collection<BondDailyPrice> getBondDailyPriceCollection() {
        return bondDailyPriceCollection;
    }

    public void setBondDailyPriceCollection(Collection<BondDailyPrice> bondDailyPriceCollection) {
        this.bondDailyPriceCollection = bondDailyPriceCollection;
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
        if (!(object instanceof Bond)) {
            return false;
        }
        Bond other = (Bond) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.Bond[ id=" + id + " ]";
    }
    
}
