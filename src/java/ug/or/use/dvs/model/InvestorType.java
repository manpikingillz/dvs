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
@Table(name = "INVESTOR_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvestorType.findAll", query = "SELECT i FROM InvestorType i"),
    @NamedQuery(name = "InvestorType.findById", query = "SELECT i FROM InvestorType i WHERE i.id = :id"),
    @NamedQuery(name = "InvestorType.findByPersonType", query = "SELECT i FROM InvestorType i WHERE i.personType = :personType"),
    @NamedQuery(name = "InvestorType.findByAutoTimestamp", query = "SELECT i FROM InvestorType i WHERE i.autoTimestamp = :autoTimestamp")})
public class InvestorType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "personType")
    private String personType;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "investorType")
    private Collection<Investor> investorCollection;

    public InvestorType() {
    }

    public InvestorType(Integer id) {
        this.id = id;
    }

    public InvestorType(Integer id, String personType) {
        this.id = id;
        this.personType = personType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    @XmlTransient
    public Collection<Investor> getInvestorCollection() {
        return investorCollection;
    }

    public void setInvestorCollection(Collection<Investor> investorCollection) {
        this.investorCollection = investorCollection;
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
        if (!(object instanceof InvestorType)) {
            return false;
        }
        InvestorType other = (InvestorType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.InvestorType[ id=" + id + " ]";
    }
    
}
