/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ug.or.use.dvs.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "INSTRUMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instrument.findAll", query = "SELECT i FROM Instrument i"),
    @NamedQuery(name = "Instrument.findById", query = "SELECT i FROM Instrument i WHERE i.id = :id"),
    @NamedQuery(name = "Instrument.findByInstrumentName", query = "SELECT i FROM Instrument i WHERE i.instrumentName = :instrumentName")})
public class Instrument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "instrumentName")
    private String instrumentName;
    @ManyToMany(mappedBy = "instrumentCollection")
    private Collection<Employee> employeeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instrument1")
    private Collection<EmployeeIsAssignedToInstrument> employeeIsAssignedToInstrumentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instrument")
    private Collection<TypeOfDataToSell> typeOfDataToSellCollection;

    public Instrument() {
    }

    public Instrument(Integer id) {
        this.id = id;
    }

    public Instrument(Integer id, String instrumentName) {
        this.id = id;
        this.instrumentName = instrumentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @XmlTransient
    public Collection<EmployeeIsAssignedToInstrument> getEmployeeIsAssignedToInstrumentCollection() {
        return employeeIsAssignedToInstrumentCollection;
    }

    public void setEmployeeIsAssignedToInstrumentCollection(Collection<EmployeeIsAssignedToInstrument> employeeIsAssignedToInstrumentCollection) {
        this.employeeIsAssignedToInstrumentCollection = employeeIsAssignedToInstrumentCollection;
    }

    @XmlTransient
    public Collection<TypeOfDataToSell> getTypeOfDataToSellCollection() {
        return typeOfDataToSellCollection;
    }

    public void setTypeOfDataToSellCollection(Collection<TypeOfDataToSell> typeOfDataToSellCollection) {
        this.typeOfDataToSellCollection = typeOfDataToSellCollection;
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
        if (!(object instanceof Instrument)) {
            return false;
        }
        Instrument other = (Instrument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.Instrument[ id=" + id + " ]";
    }
    
}
