package com.krispeklaric.virgohospital_dal.Models;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "Appointments")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Patient patient;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;

    @Column(nullable = true)
    private LocalDateTime startDateHour;

    private LocalDateTime endDateHour;

    private String diagnosis;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
    private List<Test> tests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointment")
    private List<Prescription> prescriptions;

    @OneToOne(mappedBy = "appointment")
    private Bill bill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getStartDateHour() {
        return startDateHour;
    }

    public void setStartDateHour(LocalDateTime startDateHour) {
        this.startDateHour = startDateHour;
    }

    public LocalDateTime getEndDateHour() {
        return endDateHour;
    }

    public void setEndDateHour(LocalDateTime endDateHour) {
        this.endDateHour = endDateHour;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.Appointment[ id=" + id + " ]";
    }

}
