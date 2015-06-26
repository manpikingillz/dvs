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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "PAYMENT_UPLOAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentUpload.findAll", query = "SELECT p FROM PaymentUpload p"),
    @NamedQuery(name = "PaymentUpload.findById", query = "SELECT p FROM PaymentUpload p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentUpload.findByPaymentUploadName", query = "SELECT p FROM PaymentUpload p WHERE p.paymentUploadName = :paymentUploadName"),
    @NamedQuery(name = "PaymentUpload.findByAutoTimestamp", query = "SELECT p FROM PaymentUpload p WHERE p.autoTimestamp = :autoTimestamp")})
public class PaymentUpload implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "paymentUploadName")
    private String paymentUploadName;
    @Lob
    @Column(name = "upload")
    private byte[] upload;
    @Lob
    @Size(max = 65535)
    @Column(name = "uploadPath")
    private String uploadPath;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinColumn(name = "INVESTOR_DATA_PAYMENT_DETAIL", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private InvestorDataPaymentDetail investorDataPaymentDetail;

    public PaymentUpload() {
    }

    public PaymentUpload(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentUploadName() {
        return paymentUploadName;
    }

    public void setPaymentUploadName(String paymentUploadName) {
        this.paymentUploadName = paymentUploadName;
    }

    public byte[] getUpload() {
        return upload;
    }

    public void setUpload(byte[] upload) {
        this.upload = upload;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public InvestorDataPaymentDetail getInvestorDataPaymentDetail() {
        return investorDataPaymentDetail;
    }

    public void setInvestorDataPaymentDetail(InvestorDataPaymentDetail investorDataPaymentDetail) {
        this.investorDataPaymentDetail = investorDataPaymentDetail;
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
        if (!(object instanceof PaymentUpload)) {
            return false;
        }
        PaymentUpload other = (PaymentUpload) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.PaymentUpload[ id=" + id + " ]";
    }
    
}
