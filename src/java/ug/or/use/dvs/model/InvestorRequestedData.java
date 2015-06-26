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
@Table(name = "INVESTOR_REQUESTED_DATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvestorRequestedData.findAll", query = "SELECT i FROM InvestorRequestedData i"),
    @NamedQuery(name = "InvestorRequestedData.findById", query = "SELECT i FROM InvestorRequestedData i WHERE i.id = :id"),
    @NamedQuery(name = "InvestorRequestedData.findByActiveStatus", query = "SELECT i FROM InvestorRequestedData i WHERE i.activeStatus = :activeStatus"),
    @NamedQuery(name = "InvestorRequestedData.findByRequestDate", query = "SELECT i FROM InvestorRequestedData i WHERE i.requestDate = :requestDate"),
    @NamedQuery(name = "InvestorRequestedData.findByAutoTimestamp", query = "SELECT i FROM InvestorRequestedData i WHERE i.autoTimestamp = :autoTimestamp")})
public class InvestorRequestedData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activeStatus")
    private Boolean activeStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requestDate")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinColumn(name = "TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TYPEOFDATATOSELLhasDATADURATIONCATEGORY tYPEOFDATATOSELLhasDATADURATIONCATEGORY;
    @JoinColumn(name = "INVESTOR_GENERAL_DATA_REQUEST", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InvestorGeneralDataRequest investorGeneralDataRequest;

    public InvestorRequestedData() {
    }

    public InvestorRequestedData(Integer id) {
        this.id = id;
    }

    public InvestorRequestedData(Integer id, Date requestDate) {
        this.id = id;
        this.requestDate = requestDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public TYPEOFDATATOSELLhasDATADURATIONCATEGORY getTYPEOFDATATOSELLhasDATADURATIONCATEGORY() {
        return tYPEOFDATATOSELLhasDATADURATIONCATEGORY;
    }

    public void setTYPEOFDATATOSELLhasDATADURATIONCATEGORY(TYPEOFDATATOSELLhasDATADURATIONCATEGORY tYPEOFDATATOSELLhasDATADURATIONCATEGORY) {
        this.tYPEOFDATATOSELLhasDATADURATIONCATEGORY = tYPEOFDATATOSELLhasDATADURATIONCATEGORY;
    }

    public InvestorGeneralDataRequest getInvestorGeneralDataRequest() {
        return investorGeneralDataRequest;
    }

    public void setInvestorGeneralDataRequest(InvestorGeneralDataRequest investorGeneralDataRequest) {
        this.investorGeneralDataRequest = investorGeneralDataRequest;
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
        if (!(object instanceof InvestorRequestedData)) {
            return false;
        }
        InvestorRequestedData other = (InvestorRequestedData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.InvestorRequestedData[ id=" + id + " ]";
    }
    
}
