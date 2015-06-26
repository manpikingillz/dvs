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
import javax.persistence.Lob;
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
@Table(name = "INVESTOR_DATA_PAYMENT_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvestorDataPaymentDetail.findAll", query = "SELECT i FROM InvestorDataPaymentDetail i"),
    @NamedQuery(name = "InvestorDataPaymentDetail.findById", query = "SELECT i FROM InvestorDataPaymentDetail i WHERE i.id = :id"),
    @NamedQuery(name = "InvestorDataPaymentDetail.findByDateOfPayment", query = "SELECT i FROM InvestorDataPaymentDetail i WHERE i.dateOfPayment = :dateOfPayment"),
    @NamedQuery(name = "InvestorDataPaymentDetail.findByAmountPaid", query = "SELECT i FROM InvestorDataPaymentDetail i WHERE i.amountPaid = :amountPaid"),
    @NamedQuery(name = "InvestorDataPaymentDetail.findByPaymentSlipNumber", query = "SELECT i FROM InvestorDataPaymentDetail i WHERE i.paymentSlipNumber = :paymentSlipNumber"),
    @NamedQuery(name = "InvestorDataPaymentDetail.findByBank", query = "SELECT i FROM InvestorDataPaymentDetail i WHERE i.bank = :bank"),
    @NamedQuery(name = "InvestorDataPaymentDetail.findByBankBranch", query = "SELECT i FROM InvestorDataPaymentDetail i WHERE i.bankBranch = :bankBranch"),
    @NamedQuery(name = "InvestorDataPaymentDetail.findByAutoTimestamp", query = "SELECT i FROM InvestorDataPaymentDetail i WHERE i.autoTimestamp = :autoTimestamp")})
public class InvestorDataPaymentDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfPayment")
    @Temporal(TemporalType.DATE)
    private Date dateOfPayment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amountPaid")
    private float amountPaid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "paymentSlipNumber")
    private String paymentSlipNumber;
    @Lob
    @Column(name = "paymentSlipAttachment")
    private byte[] paymentSlipAttachment;
    @Size(max = 100)
    @Column(name = "bank")
    private String bank;
    @Size(max = 100)
    @Column(name = "bankBranch")
    private String bankBranch;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "investorDataPaymentDetail")
    private Collection<PaymentUpload> paymentUploadCollection;
    @JoinColumn(name = "INVESTOR_GENERAL_DATA_REQUEST", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InvestorGeneralDataRequest investorGeneralDataRequest;
    @JoinColumn(name = "BRANCH_COUNTRY", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country branchCountry;

    public InvestorDataPaymentDetail() {
    }

    public InvestorDataPaymentDetail(Integer id) {
        this.id = id;
    }

    public InvestorDataPaymentDetail(Integer id, Date dateOfPayment, float amountPaid, String paymentSlipNumber) {
        this.id = id;
        this.dateOfPayment = dateOfPayment;
        this.amountPaid = amountPaid;
        this.paymentSlipNumber = paymentSlipNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentSlipNumber() {
        return paymentSlipNumber;
    }

    public void setPaymentSlipNumber(String paymentSlipNumber) {
        this.paymentSlipNumber = paymentSlipNumber;
    }

    public byte[] getPaymentSlipAttachment() {
        return paymentSlipAttachment;
    }

    public void setPaymentSlipAttachment(byte[] paymentSlipAttachment) {
        this.paymentSlipAttachment = paymentSlipAttachment;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    @XmlTransient
    public Collection<PaymentUpload> getPaymentUploadCollection() {
        return paymentUploadCollection;
    }

    public void setPaymentUploadCollection(Collection<PaymentUpload> paymentUploadCollection) {
        this.paymentUploadCollection = paymentUploadCollection;
    }

    public InvestorGeneralDataRequest getInvestorGeneralDataRequest() {
        return investorGeneralDataRequest;
    }

    public void setInvestorGeneralDataRequest(InvestorGeneralDataRequest investorGeneralDataRequest) {
        this.investorGeneralDataRequest = investorGeneralDataRequest;
    }

    public Country getBranchCountry() {
        return branchCountry;
    }

    public void setBranchCountry(Country branchCountry) {
        this.branchCountry = branchCountry;
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
        if (!(object instanceof InvestorDataPaymentDetail)) {
            return false;
        }
        InvestorDataPaymentDetail other = (InvestorDataPaymentDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.InvestorDataPaymentDetail[ id=" + id + " ]";
    }
    
}
