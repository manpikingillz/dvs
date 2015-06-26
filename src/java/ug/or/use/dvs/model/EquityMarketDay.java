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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "EQUITY_MARKET_DAY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquityMarketDay.findAll", query = "SELECT e FROM EquityMarketDay e"),
    @NamedQuery(name = "EquityMarketDay.findById", query = "SELECT e FROM EquityMarketDay e WHERE e.id = :id"),
    @NamedQuery(name = "EquityMarketDay.findByDateOfMarket", query = "SELECT e FROM EquityMarketDay e WHERE e.dateOfMarket = :dateOfMarket"),
    @NamedQuery(name = "EquityMarketDay.findByALSICurrent", query = "SELECT e FROM EquityMarketDay e WHERE e.aLSICurrent = :aLSICurrent"),
    @NamedQuery(name = "EquityMarketDay.findByLSICurrent", query = "SELECT e FROM EquityMarketDay e WHERE e.lSICurrent = :lSICurrent"),
    @NamedQuery(name = "EquityMarketDay.findByAutoTimestamp", query = "SELECT e FROM EquityMarketDay e WHERE e.autoTimestamp = :autoTimestamp")})
public class EquityMarketDay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfMarket")
    @Temporal(TemporalType.DATE)
    private Date dateOfMarket;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALSICurrent")
    private float aLSICurrent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LSICurrent")
    private float lSICurrent;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equityMarketDay")
    private Collection<EquityDailyPrice> equityDailyPriceCollection;

    public EquityMarketDay() {
    }

    public EquityMarketDay(Integer id) {
        this.id = id;
    }

    public EquityMarketDay(Integer id, Date dateOfMarket, float aLSICurrent, float lSICurrent) {
        this.id = id;
        this.dateOfMarket = dateOfMarket;
        this.aLSICurrent = aLSICurrent;
        this.lSICurrent = lSICurrent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfMarket() {
        return dateOfMarket;
    }

    public void setDateOfMarket(Date dateOfMarket) {
        this.dateOfMarket = dateOfMarket;
    }

    public float getALSICurrent() {
        return aLSICurrent;
    }

    public void setALSICurrent(float aLSICurrent) {
        this.aLSICurrent = aLSICurrent;
    }

    public float getLSICurrent() {
        return lSICurrent;
    }

    public void setLSICurrent(float lSICurrent) {
        this.lSICurrent = lSICurrent;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquityMarketDay)) {
            return false;
        }
        EquityMarketDay other = (EquityMarketDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.EquityMarketDay[ id=" + id + " ]";
    }
    
}
