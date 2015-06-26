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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "INVESTOR_GENERAL_DATA_REQUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvestorGeneralDataRequest.findAll", query = "SELECT i FROM InvestorGeneralDataRequest i"),
    @NamedQuery(name = "InvestorGeneralDataRequest.findById", query = "SELECT i FROM InvestorGeneralDataRequest i WHERE i.id = :id"),
    @NamedQuery(name = "InvestorGeneralDataRequest.findByRequestDate", query = "SELECT i FROM InvestorGeneralDataRequest i WHERE i.requestDate = :requestDate"),
    @NamedQuery(name = "InvestorGeneralDataRequest.findByAmount", query = "SELECT i FROM InvestorGeneralDataRequest i WHERE i.amount = :amount"),
    @NamedQuery(name = "InvestorGeneralDataRequest.findByActiveStatus", query = "SELECT i FROM InvestorGeneralDataRequest i WHERE i.activeStatus = :activeStatus"),
    @NamedQuery(name = "InvestorGeneralDataRequest.findByAutoTimestamp", query = "SELECT i FROM InvestorGeneralDataRequest i WHERE i.autoTimestamp = :autoTimestamp"),
    @NamedQuery(name = "InvestorGeneralDataRequest.findByPaymentStatus", query = "SELECT i FROM InvestorGeneralDataRequest i WHERE i.paymentStatus = :paymentStatus")})
public class InvestorGeneralDataRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requestDate")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private float amount;
    @Column(name = "activeStatus")
    private Boolean activeStatus;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @Column(name = "paymentStatus")
    private Boolean paymentStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "investorGeneralDataRequest")
    private Collection<InvestorRequestedData> investorRequestedDataCollection;
    @JoinColumn(name = "personId", referencedColumnName = "personId")
    @ManyToOne(optional = false)
    private Person personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "investorGeneralDataRequest")
    private Collection<InvestorDataPaymentDetail> investorDataPaymentDetailCollection;

    public InvestorGeneralDataRequest() {
    }

    public InvestorGeneralDataRequest(Integer id) {
        this.id = id;
    }

    public InvestorGeneralDataRequest(Integer id, Date requestDate, float amount) {
        this.id = id;
        this.requestDate = requestDate;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @XmlTransient
    public Collection<InvestorRequestedData> getInvestorRequestedDataCollection() {
        return investorRequestedDataCollection;
    }

    public void setInvestorRequestedDataCollection(Collection<InvestorRequestedData> investorRequestedDataCollection) {
        this.investorRequestedDataCollection = investorRequestedDataCollection;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
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
        if (!(object instanceof InvestorGeneralDataRequest)) {
            return false;
        }
        InvestorGeneralDataRequest other = (InvestorGeneralDataRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.InvestorGeneralDataRequest[ id=" + id + " ]";
    }
    
}
