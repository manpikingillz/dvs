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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "BOND_DAILY_PRICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BondDailyPrice.findAll", query = "SELECT b FROM BondDailyPrice b"),
    @NamedQuery(name = "BondDailyPrice.findById", query = "SELECT b FROM BondDailyPrice b WHERE b.id = :id"),
    @NamedQuery(name = "BondDailyPrice.findByTradedYield", query = "SELECT b FROM BondDailyPrice b WHERE b.tradedYield = :tradedYield"),
    @NamedQuery(name = "BondDailyPrice.findByDirtyPrice", query = "SELECT b FROM BondDailyPrice b WHERE b.dirtyPrice = :dirtyPrice"),
    @NamedQuery(name = "BondDailyPrice.findByCleanPrice", query = "SELECT b FROM BondDailyPrice b WHERE b.cleanPrice = :cleanPrice"),
    @NamedQuery(name = "BondDailyPrice.findByAutoTimestamp", query = "SELECT b FROM BondDailyPrice b WHERE b.autoTimestamp = :autoTimestamp")})
public class BondDailyPrice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tradedYield")
    private Float tradedYield;
    @Column(name = "dirtyPrice")
    private Float dirtyPrice;
    @Column(name = "cleanPrice")
    private Float cleanPrice;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinColumn(name = "BOND", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bond bond;

    public BondDailyPrice() {
    }

    public BondDailyPrice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTradedYield() {
        return tradedYield;
    }

    public void setTradedYield(Float tradedYield) {
        this.tradedYield = tradedYield;
    }

    public Float getDirtyPrice() {
        return dirtyPrice;
    }

    public void setDirtyPrice(Float dirtyPrice) {
        this.dirtyPrice = dirtyPrice;
    }

    public Float getCleanPrice() {
        return cleanPrice;
    }

    public void setCleanPrice(Float cleanPrice) {
        this.cleanPrice = cleanPrice;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public Bond getBond() {
        return bond;
    }

    public void setBond(Bond bond) {
        this.bond = bond;
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
        if (!(object instanceof BondDailyPrice)) {
            return false;
        }
        BondDailyPrice other = (BondDailyPrice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.BondDailyPrice[ id=" + id + " ]";
    }
    
}
