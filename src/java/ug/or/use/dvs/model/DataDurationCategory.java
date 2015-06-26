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
@Table(name = "DATA_DURATION_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataDurationCategory.findAll", query = "SELECT d FROM DataDurationCategory d"),
    @NamedQuery(name = "DataDurationCategory.findById", query = "SELECT d FROM DataDurationCategory d WHERE d.id = :id"),
    @NamedQuery(name = "DataDurationCategory.findByDataDurationCategory", query = "SELECT d FROM DataDurationCategory d WHERE d.dataDurationCategory = :dataDurationCategory"),
    @NamedQuery(name = "DataDurationCategory.findByAutoTimestamp", query = "SELECT d FROM DataDurationCategory d WHERE d.autoTimestamp = :autoTimestamp")})
public class DataDurationCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dataDurationCategory")
    private String dataDurationCategory;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dataDurationCategory")
    private Collection<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> tYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection;

    public DataDurationCategory() {
    }

    public DataDurationCategory(Integer id) {
        this.id = id;
    }

    public DataDurationCategory(Integer id, String dataDurationCategory) {
        this.id = id;
        this.dataDurationCategory = dataDurationCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataDurationCategory() {
        return dataDurationCategory;
    }

    public void setDataDurationCategory(String dataDurationCategory) {
        this.dataDurationCategory = dataDurationCategory;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    @XmlTransient
    public Collection<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> getTYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection() {
        return tYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection;
    }

    public void setTYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection(Collection<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> tYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection) {
        this.tYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection = tYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection;
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
        if (!(object instanceof DataDurationCategory)) {
            return false;
        }
        DataDurationCategory other = (DataDurationCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.DataDurationCategory[ id=" + id + " ]";
    }
    
}
