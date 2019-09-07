package com.krispeklaric.virgohospital_dal.Models;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "BasicComplaints")
public class BasicComplaint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String complaintStatement;

    private String treatmentHistory;

    private String hospitalTreated;

    @OneToOne(mappedBy = "basicComplaints")
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplaintStatement() {
        return complaintStatement;
    }

    public void setComplaintStatement(String complaintStatement) {
        this.complaintStatement = complaintStatement;
    }

    public String getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(String treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }

    public String getHospitalTreated() {
        return hospitalTreated;
    }

    public void setHospitalTreated(String hospitalTreated) {
        this.hospitalTreated = hospitalTreated;
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
        if (!(object instanceof BasicComplaint)) {
            return false;
        }
        BasicComplaint other = (BasicComplaint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.BasicComplaints[ id=" + id + " ]";
    }

}
