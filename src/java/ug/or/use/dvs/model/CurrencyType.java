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
@Table(name = "CURRENCY_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CurrencyType.findAll", query = "SELECT c FROM CurrencyType c"),
    @NamedQuery(name = "CurrencyType.findById", query = "SELECT c FROM CurrencyType c WHERE c.id = :id"),
    @NamedQuery(name = "CurrencyType.findByCurrencyTypeShortName", query = "SELECT c FROM CurrencyType c WHERE c.currencyTypeShortName = :currencyTypeShortName"),
    @NamedQuery(name = "CurrencyType.findByCurrencyTypeLongName", query = "SELECT c FROM CurrencyType c WHERE c.currencyTypeLongName = :currencyTypeLongName"),
    @NamedQuery(name = "CurrencyType.findByAutoTimestamp", query = "SELECT c FROM CurrencyType c WHERE c.autoTimestamp = :autoTimestamp")})
public class CurrencyType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currencyTypeShortName")
    private String currencyTypeShortName;
    @Size(max = 45)
    @Column(name = "currencyTypeLongName")
    private String currencyTypeLongName;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyType")
    private Collection<Bond> bondCollection;

    public CurrencyType() {
    }

    public CurrencyType(Integer id) {
        this.id = id;
    }

    public CurrencyType(Integer id, String currencyTypeShortName) {
        this.id = id;
        this.currencyTypeShortName = currencyTypeShortName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyTypeShortName() {
        return currencyTypeShortName;
    }

    public void setCurrencyTypeShortName(String currencyTypeShortName) {
        this.currencyTypeShortName = currencyTypeShortName;
    }

    public String getCurrencyTypeLongName() {
        return currencyTypeLongName;
    }

    public void setCurrencyTypeLongName(String currencyTypeLongName) {
        this.currencyTypeLongName = currencyTypeLongName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CurrencyType)) {
            return false;
        }
        CurrencyType other = (CurrencyType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.CurrencyType[ id=" + id + " ]";
    }
    
}
