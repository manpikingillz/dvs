/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ug.or.use.dvs.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "EMPLOYEE_IS_ASSIGNED_TO_INSTRUMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeIsAssignedToInstrument.findAll", query = "SELECT e FROM EmployeeIsAssignedToInstrument e"),
    @NamedQuery(name = "EmployeeIsAssignedToInstrument.findByInstrument", query = "SELECT e FROM EmployeeIsAssignedToInstrument e WHERE e.employeeIsAssignedToInstrumentPK.instrument = :instrument"),
    @NamedQuery(name = "EmployeeIsAssignedToInstrument.findByEmployee", query = "SELECT e FROM EmployeeIsAssignedToInstrument e WHERE e.employeeIsAssignedToInstrumentPK.employee = :employee")})
public class EmployeeIsAssignedToInstrument implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmployeeIsAssignedToInstrumentPK employeeIsAssignedToInstrumentPK;
    @JoinColumn(name = "INSTRUMENT", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Instrument instrument1;

    public EmployeeIsAssignedToInstrument() {
    }

    public EmployeeIsAssignedToInstrument(EmployeeIsAssignedToInstrumentPK employeeIsAssignedToInstrumentPK) {
        this.employeeIsAssignedToInstrumentPK = employeeIsAssignedToInstrumentPK;
    }

    public EmployeeIsAssignedToInstrument(int instrument, int employee) {
        this.employeeIsAssignedToInstrumentPK = new EmployeeIsAssignedToInstrumentPK(instrument, employee);
    }

    public EmployeeIsAssignedToInstrumentPK getEmployeeIsAssignedToInstrumentPK() {
        return employeeIsAssignedToInstrumentPK;
    }

    public void setEmployeeIsAssignedToInstrumentPK(EmployeeIsAssignedToInstrumentPK employeeIsAssignedToInstrumentPK) {
        this.employeeIsAssignedToInstrumentPK = employeeIsAssignedToInstrumentPK;
    }

    public Instrument getInstrument1() {
        return instrument1;
    }

    public void setInstrument1(Instrument instrument1) {
        this.instrument1 = instrument1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeIsAssignedToInstrumentPK != null ? employeeIsAssignedToInstrumentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeIsAssignedToInstrument)) {
            return false;
        }
        EmployeeIsAssignedToInstrument other = (EmployeeIsAssignedToInstrument) object;
        if ((this.employeeIsAssignedToInstrumentPK == null && other.employeeIsAssignedToInstrumentPK != null) || (this.employeeIsAssignedToInstrumentPK != null && !this.employeeIsAssignedToInstrumentPK.equals(other.employeeIsAssignedToInstrumentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.EmployeeIsAssignedToInstrument[ employeeIsAssignedToInstrumentPK=" + employeeIsAssignedToInstrumentPK + " ]";
    }
    
}
