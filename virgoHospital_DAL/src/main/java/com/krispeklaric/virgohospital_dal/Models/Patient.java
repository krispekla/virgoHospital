/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "Patients")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    private String middlename;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private LocalDate birthdate;

    @OneToOne
    @JoinColumn(name = "contactDetail_ID")
    private ContactDetail contactDetail;

    @OneToOne(mappedBy = "patient")
    private NextOfKin nextOfKin;

    @OneToOne
    @JoinColumn(name = "personalDetail_ID")
    private PersonalDetail personalDetail;

    @OneToOne
    @JoinColumn(name = "patientLifestyle_ID")
    private PatientLifestyle patientLifestyle;

    @OneToOne
    @JoinColumn(name = "basicComplaints_ID")
    private BasicComplaint basicComplaints;

    @OneToOne
    @JoinColumn(name = "medicalComplaint_ID")
    private MedicalComplaint medicalComplaints;

    @OneToOne
    @JoinColumn(name = "doctor_ID")
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Appointment> appointments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.Patient[ id=" + id + " ]";
    }

}
