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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByPersonId", query = "SELECT u FROM User u WHERE u.personId = :personId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAutoTimestamp", query = "SELECT u FROM User u WHERE u.autoTimestamp = :autoTimestamp"),
    @NamedQuery(name = "User.findByEnabledOrDisabled", query = "SELECT u FROM User u WHERE u.enabledOrDisabled = :enabledOrDisabled")})
public class User extends Person implements Serializable {
    protected static final long serialVersionUID = 1L;

    @Size(max = 45)
    @Column(name = "username")
    protected String username;
    @Size(max = 255)
    @Column(name = "password")
    protected String password;
    @Column(name = "autoTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date autoTimestamp;
    @Column(name = "enabledOrDisabled")
    protected Boolean enabledOrDisabled;
    @JoinTable(name = "USER_has_USER_GROUP", joinColumns = {
        @JoinColumn(name = "personId", referencedColumnName = "personId")}, inverseJoinColumns = {
        @JoinColumn(name = "USER_GROUP_ID", referencedColumnName = "id")})
    @ManyToMany
    protected Collection<UserGroup> userGroupCollection;
    @JoinColumn(name = "personId", referencedColumnName = "personId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    protected Person person;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    protected Employee employee;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    protected Investor investor;

    public User() {
    }

    public User(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public Boolean getEnabledOrDisabled() {
        return enabledOrDisabled;
    }

    public void setEnabledOrDisabled(Boolean enabledOrDisabled) {
        this.enabledOrDisabled = enabledOrDisabled;
    }

    @XmlTransient
    public Collection<UserGroup> getUserGroupCollection() {
        return userGroupCollection;
    }

    public void setUserGroupCollection(Collection<UserGroup> userGroupCollection) {
        this.userGroupCollection = userGroupCollection;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ug.or.use.dvs.model.User[ personId=" + personId + " ]";
    }
    
}
