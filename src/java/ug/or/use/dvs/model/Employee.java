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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByPersonId", query = "SELECT e FROM Employee e WHERE e.personId = :personId"),
    @NamedQuery(name = "Employee.findByEmployeeNumber", query = "SELECT e FROM Employee e WHERE e.employeeNumber = :employeeNumber"),
    @NamedQuery(name = "Employee.findByAutoTimestamp", query = "SELECT e FROM Employee e WHERE e.autoTimestamp = :autoTimestamp")})
public class Employee extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "employeeNumber")
    private String employeeNumber;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date autoTimestamp;
    @JoinTable(name = "EMPLOYEE_has_INSTRUMENT", joinColumns = {
        @JoinColumn(name = "personId", referencedColumnName = "personId")}, inverseJoinColumns = {
        @JoinColumn(name = "INSTRUMENT", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Instrument> instrumentCollection;
    @JoinColumn(name = "personId", referencedColumnName = "personId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;
    @JoinColumn(name = "JOBTITLE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobtitle jobtitle;

    public Employee() {
    }

    public Employee(Integer personId) {
        this.personId = personId;
    }

    public Employee(Integer personId, String employeeNumber) {
        this.personId = personId;
        this.employeeNumber = employeeNumber;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    @XmlTransient
    public Collection<Instrument> getInstrumentCollection() {
        return instrumentCollection;
    }

    public void setInstrumentCollection(Collection<Instrument> instrumentCollection) {
        this.instrumentCollection = instrumentCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Jobtitle getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(Jobtitle jobtitle) {
        this.jobtitle = jobtitle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.Employee[ personId=" + personId + " ]";
    }
    
}
