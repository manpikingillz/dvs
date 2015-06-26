/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ug.or.use.dvs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "EQUITY_DAILY_PRICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquityDailyPrice.findAll", query = "SELECT e FROM EquityDailyPrice e"),
    @NamedQuery(name = "EquityDailyPrice.findById", query = "SELECT e FROM EquityDailyPrice e WHERE e.id = :id"),
    @NamedQuery(name = "EquityDailyPrice.findBySharesTraded", query = "SELECT e FROM EquityDailyPrice e WHERE e.sharesTraded = :sharesTraded"),
    @NamedQuery(name = "EquityDailyPrice.findByTurnOver", query = "SELECT e FROM EquityDailyPrice e WHERE e.turnOver = :turnOver"),
    @NamedQuery(name = "EquityDailyPrice.findByVwap", query = "SELECT e FROM EquityDailyPrice e WHERE e.vwap = :vwap"),
    @NamedQuery(name = "EquityDailyPrice.findByLow", query = "SELECT e FROM EquityDailyPrice e WHERE e.low = :low"),
    @NamedQuery(name = "EquityDailyPrice.findByHigh", query = "SELECT e FROM EquityDailyPrice e WHERE e.high = :high"),
    @NamedQuery(name = "EquityDailyPrice.findByOutstandingBid", query = "SELECT e FROM EquityDailyPrice e WHERE e.outstandingBid = :outstandingBid"),
    @NamedQuery(name = "EquityDailyPrice.findByOutstandingOffer", query = "SELECT e FROM EquityDailyPrice e WHERE e.outstandingOffer = :outstandingOffer"),
    @NamedQuery(name = "EquityDailyPrice.findByPERatio", query = "SELECT e FROM EquityDailyPrice e WHERE e.pERatio = :pERatio"),
    @NamedQuery(name = "EquityDailyPrice.findByMarketCap", query = "SELECT e FROM EquityDailyPrice e WHERE e.marketCap = :marketCap"),
    @NamedQuery(name = "EquityDailyPrice.findByAutoTimestamp", query = "SELECT e FROM EquityDailyPrice e WHERE e.autoTimestamp = :autoTimestamp")})
public class EquityDailyPrice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sharesTraded")
    private int sharesTraded;
    @Basic(optional = false)
    @NotNull
    @Column(name = "turnOver")
    private int turnOver;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VWAP")
    private int vwap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "low")
    private int low;
    @Basic(optional = false)
    @NotNull
    @Column(name = "high")
    private int high;
    @Basic(optional = false)
    @NotNull
    @Column(name = "outstandingBid")
    private int outstandingBid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "outstandingOffer")
    private int outstandingOffer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERatio")
    private float pERatio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marketCap")
    private float marketCap;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinColumn(name = "EQUITY", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Equity equity;
    @JoinColumn(name = "EQUITY_MARKET_DAY", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EquityMarketDay equityMarketDay;

    public EquityDailyPrice() {
    }

    public EquityDailyPrice(Integer id) {
        this.id = id;
    }

    public EquityDailyPrice(Integer id, int sharesTraded, int turnOver, int vwap, int low, int high, int outstandingBid, int outstandingOffer, float pERatio, float marketCap) {
        this.id = id;
        this.sharesTraded = sharesTraded;
        this.turnOver = turnOver;
        this.vwap = vwap;
        this.low = low;
        this.high = high;
        this.outstandingBid = outstandingBid;
        this.outstandingOffer = outstandingOffer;
        this.pERatio = pERatio;
        this.marketCap = marketCap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSharesTraded() {
        return sharesTraded;
    }

    public void setSharesTraded(int sharesTraded) {
        this.sharesTraded = sharesTraded;
    }

    public int getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(int turnOver) {
        this.turnOver = turnOver;
    }

    public int getVwap() {
        return vwap;
    }

    public void setVwap(int vwap) {
        this.vwap = vwap;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getOutstandingBid() {
        return outstandingBid;
    }

    public void setOutstandingBid(int outstandingBid) {
        this.outstandingBid = outstandingBid;
    }

    public int getOutstandingOffer() {
        return outstandingOffer;
    }

    public void setOutstandingOffer(int outstandingOffer) {
        this.outstandingOffer = outstandingOffer;
    }

    public float getPERatio() {
        return pERatio;
    }

    public void setPERatio(float pERatio) {
        this.pERatio = pERatio;
    }

    public float getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(float marketCap) {
        this.marketCap = marketCap;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public Equity getEquity() {
        return equity;
    }

    public void setEquity(Equity equity) {
        this.equity = equity;
    }

    public EquityMarketDay getEquityMarketDay() {
        return equityMarketDay;
    }

    public void setEquityMarketDay(EquityMarketDay equityMarketDay) {
        this.equityMarketDay = equityMarketDay;
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
        if (!(object instanceof EquityDailyPrice)) {
            return false;
        }
        EquityDailyPrice other = (EquityDailyPrice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.EquityDailyPrice[ id=" + id + " ]";
    }
    
}
