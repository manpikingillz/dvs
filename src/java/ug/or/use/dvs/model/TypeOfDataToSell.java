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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "TYPE_OF_DATA_TO_SELL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeOfDataToSell.findAll", query = "SELECT t FROM TypeOfDataToSell t"),
    @NamedQuery(name = "TypeOfDataToSell.findById", query = "SELECT t FROM TypeOfDataToSell t WHERE t.id = :id"),
    @NamedQuery(name = "TypeOfDataToSell.findByTypeOfDataToSell", query = "SELECT t FROM TypeOfDataToSell t WHERE t.typeOfDataToSell = :typeOfDataToSell"),
    @NamedQuery(name = "TypeOfDataToSell.findByDescription", query = "SELECT t FROM TypeOfDataToSell t WHERE t.description = :description"),
    @NamedQuery(name = "TypeOfDataToSell.findByAutoTimestamp", query = "SELECT t FROM TypeOfDataToSell t WHERE t.autoTimestamp = :autoTimestamp")})
public class TypeOfDataToSell implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "typeOfDataToSell")
    private String typeOfDataToSell;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeOfDataToSell")
    private Collection<TYPEOFDATATOSELLhasDATADURATIONCATEGORY> tYPEOFDATATOSELLhasDATADURATIONCATEGORYCollection;
    @JoinColumn(name = "INSTRUMENT", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Instrument instrument;

    public TypeOfDataToSell() {
    }

    public TypeOfDataToSell(Integer id) {
        this.id = id;
    }

    public TypeOfDataToSell(Integer id, String typeOfDataToSell) {
        this.id = id;
        this.typeOfDataToSell = typeOfDataToSell;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeOfDataToSell() {
        return typeOfDataToSell;
    }

    public void setTypeOfDataToSell(String typeOfDataToSell) {
        this.typeOfDataToSell = typeOfDataToSell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
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
        if (!(object instanceof TypeOfDataToSell)) {
            return false;
        }
        TypeOfDataToSell other = (TypeOfDataToSell) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.TypeOfDataToSell[ id=" + id + " ]";
    }
    
}
