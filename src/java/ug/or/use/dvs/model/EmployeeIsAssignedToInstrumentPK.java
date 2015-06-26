/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ug.or.use.dvs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Dell
 */
@Embeddable
public class EmployeeIsAssignedToInstrumentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTRUMENT")
    private int instrument;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMPLOYEE")
    private int employee;

    public EmployeeIsAssignedToInstrumentPK() {
    }

    public EmployeeIsAssignedToInstrumentPK(int instrument, int employee) {
        this.instrument = instrument;
        this.employee = employee;
    }

    public int getInstrument() {
        return instrument;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) instrument;
        hash += (int) employee;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeIsAssignedToInstrumentPK)) {
            return false;
        }
        EmployeeIsAssignedToInstrumentPK other = (EmployeeIsAssignedToInstrumentPK) object;
        if (this.instrument != other.instrument) {
            return false;
        }
        if (this.employee != other.employee) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.EmployeeIsAssignedToInstrumentPK[ instrument=" + instrument + ", employee=" + employee + " ]";
    }
    
}
