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
@Table(name = "BOND_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BondType.findAll", query = "SELECT b FROM BondType b"),
    @NamedQuery(name = "BondType.findById", query = "SELECT b FROM BondType b WHERE b.id = :id"),
    @NamedQuery(name = "BondType.findByBondType", query = "SELECT b FROM BondType b WHERE b.bondType = :bondType"),
    @NamedQuery(name = "BondType.findByAutoTimestamp", query = "SELECT b FROM BondType b WHERE b.autoTimestamp = :autoTimestamp")})
public class BondType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "bondType")
    private String bondType;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bondType")
    private Collection<Bond> bondCollection;

    public BondType() {
    }

    public BondType(Integer id) {
        this.id = id;
    }

    public BondType(Integer id, String bondType) {
        this.id = id;
        this.bondType = bondType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    @XmlTransient
    public Collection<Bond> getBondCollection() {
        return bondCollection;
    }

    public void setBondCollection(Collection<Bond> bondCollection) {
        this.bondCollection = bondCollection;
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
        if (!(object instanceof BondType)) {
            return false;
        }
        BondType other = (BondType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.BondType[ id=" + id + " ]";
    }
    
}
