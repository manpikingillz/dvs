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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "TYPE_OF_DATA_TO_SELL_has_DATA_DURATION_CATEGORY",
        uniqueConstraints = @UniqueConstraint(columnNames = {"TYPE_OF_DATA_TO_SELL","DATA_DURATION_CATEGORY"})
)
@XmlRootElement
//@UniqueConstraint(columnNames = {"TYPE_OF_DATA_TO_SELL","DATA_DURATION_CATEGORY"})
@NamedQueries({
    @NamedQuery(name = "TYPEOFDATATOSELLhasDATADURATIONCATEGORY.findAll", query = "SELECT t FROM TYPEOFDATATOSELLhasDATADURATIONCATEGORY t"),
    @NamedQuery(name = "TYPEOFDATATOSELLhasDATADURATIONCATEGORY.findById", query = "SELECT t FROM TYPEOFDATATOSELLhasDATADURATIONCATEGORY t WHERE t.id = :id"),
    @NamedQuery(name = "TYPEOFDATATOSELLhasDATADURATIONCATEGORY.findByPrice", query = "SELECT t FROM TYPEOFDATATOSELLhasDATADURATIONCATEGORY t WHERE t.price = :price"),
    @NamedQuery(name = "TYPEOFDATATOSELLhasDATADURATIONCATEGORY.findByRemarks", query = "SELECT t FROM TYPEOFDATATOSELLhasDATADURATIONCATEGORY t WHERE t.remarks = :remarks"),
    @NamedQuery(name = "TYPEOFDATATOSELLhasDATADURATIONCATEGORY.findByAutoTimestamp", query = "SELECT t FROM TYPEOFDATATOSELLhasDATADURATIONCATEGORY t WHERE t.autoTimestamp = :autoTimestamp")})
public class TYPEOFDATATOSELLhasDATADURATIONCATEGORY implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Float price;
    @Size(max = 255)
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinColumn(name = "TYPE_OF_DATA_TO_SELL", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TypeOfDataToSell typeOfDataToSell;
    @JoinColumn(name = "DATA_DURATION_CATEGORY", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DataDurationCategory dataDurationCategory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tYPEOFDATATOSELLhasDATADURATIONCATEGORY")
    private Collection<InvestorRequestedData> investorRequestedDataCollection;

    public TYPEOFDATATOSELLhasDATADURATIONCATEGORY() {
    }

    public TYPEOFDATATOSELLhasDATADURATIONCATEGORY(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public TypeOfDataToSell getTypeOfDataToSell() {
        return typeOfDataToSell;
    }

    public void setTypeOfDataToSell(TypeOfDataToSell typeOfDataToSell) {
        this.typeOfDataToSell = typeOfDataToSell;
    }

    public DataDurationCategory getDataDurationCategory() {
        return dataDurationCategory;
    }

    public void setDataDurationCategory(DataDurationCategory dataDurationCategory) {
        this.dataDurationCategory = dataDurationCategory;
    }

    @XmlTransient
    public Collection<InvestorRequestedData> getInvestorRequestedDataCollection() {
        return investorRequestedDataCollection;
    }

    public void setInvestorRequestedDataCollection(Collection<InvestorRequestedData> investorRequestedDataCollection) {
        this.investorRequestedDataCollection = investorRequestedDataCollection;
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
        if (!(object instanceof TYPEOFDATATOSELLhasDATADURATIONCATEGORY)) {
            return false;
        }
        TYPEOFDATATOSELLhasDATADURATIONCATEGORY other = (TYPEOFDATATOSELLhasDATADURATIONCATEGORY) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.TYPEOFDATATOSELLhasDATADURATIONCATEGORY[ id=" + id + " ]";
    }
    
}
