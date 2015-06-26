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
@Table(name = "COUNTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findById", query = "SELECT c FROM Country c WHERE c.id = :id"),
    @NamedQuery(name = "Country.findByCountryName", query = "SELECT c FROM Country c WHERE c.countryName = :countryName"),
    @NamedQuery(name = "Country.findByCountryShortName", query = "SELECT c FROM Country c WHERE c.countryShortName = :countryShortName"),
    @NamedQuery(name = "Country.findByAutoTimestamp", query = "SELECT c FROM Country c WHERE c.autoTimestamp = :autoTimestamp")})
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "countryName")
    private String countryName;
    @Size(max = 10)
    @Column(name = "countryShortName")
    private String countryShortName;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryOfInitialIssue")
    private Collection<Bond> bondCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Collection<Person> personCollection;
    @OneToMany(mappedBy = "countryOfInitialIssue")
    private Collection<Equity> equityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branchCountry")
    private Collection<InvestorDataPaymentDetail> investorDataPaymentDetailCollection;

    public Country() {
    }

    public Country(Integer id) {
        this.id = id;
    }

    public Country(Integer id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryShortName() {
        return countryShortName;
    }

    public void setCountryShortName(String countryShortName) {
        this.countryShortName = countryShortName;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    @XmlTransient
    public Collection<Bond> getBondCollection() {
        return bondCollection;
    }

    public void setBondCollection(Collection<Bond> bondCollection) {
        this.bondCollection = bondCollection;
    }

    @XmlTransient
    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }

    @XmlTransient
    public Collection<Equity> getEquityCollection() {
        return equityCollection;
    }

    public void setEquityCollection(Collection<Equity> equityCollection) {
        this.equityCollection = equityCollection;
    }

    @XmlTransient
    public Collection<InvestorDataPaymentDetail> getInvestorDataPaymentDetailCollection() {
        return investorDataPaymentDetailCollection;
    }

    public void setInvestorDataPaymentDetailCollection(Collection<InvestorDataPaymentDetail> investorDataPaymentDetailCollection) {
        this.investorDataPaymentDetailCollection = investorDataPaymentDetailCollection;
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
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.Country[ id=" + id + " ]";
    }
    
}
