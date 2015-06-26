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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "INVESTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Investor.findAll", query = "SELECT i FROM Investor i"),
    @NamedQuery(name = "Investor.findByPersonId", query = "SELECT i FROM Investor i WHERE i.personId = :personId"),
    @NamedQuery(name = "Investor.findByAddress", query = "SELECT i FROM Investor i WHERE i.address = :address"),
    @NamedQuery(name = "Investor.findByAutoTimestamp", query = "SELECT i FROM Investor i WHERE i.autoTimestamp = :autoTimestamp")})
public class Investor extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinColumn(name = "personId", referencedColumnName = "personId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;
    @JoinColumn(name = "INVESTOR_TYPE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InvestorType investorType;

    public Investor() {
    }

    public Investor(Integer personId) {
        this.personId = personId;
    }

    public Investor(Integer personId, String address) {
        this.personId = personId;
        this.address = address;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InvestorType getInvestorType() {
        return investorType;
    }

    public void setInvestorType(InvestorType investorType) {
        this.investorType = investorType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Investor)) {
            return false;
        }
        Investor other = (Investor) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.Investor[ personId=" + personId + " ]";
    }
    
}
