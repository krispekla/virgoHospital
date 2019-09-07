package com.krispeklaric.virgohospital_dal.Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "NextOfKins")
public class NextOfKin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String firstname;

    private String middlename;

    @Column(nullable = true)
    private String lastname;

    @Column(nullable = true)
    private String outpatientRelationship;

    @OneToOne
    @JoinColumn(name = "contactDetail_ID")
    private ContactDetail contactDetailNextOf;

    @OneToOne
    @JoinColumn(name = "patient_ID")
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOutpatientRelationship() {
        return outpatientRelationship;
    }

    public void setOutpatientRelationship(String outpatientRelationship) {
        this.outpatientRelationship = outpatientRelationship;
    }

    public ContactDetail getContactDetailNextOf() {
        return contactDetailNextOf;
    }

    public void setContactDetailNextOf(ContactDetail contactDetailNextOf) {
        this.contactDetailNextOf = contactDetailNextOf;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
        if (!(object instanceof NextOfKin)) {
            return false;
        }
        NextOfKin other = (NextOfKin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.NextOfKin[ id=" + id + " ]";
    }

}
