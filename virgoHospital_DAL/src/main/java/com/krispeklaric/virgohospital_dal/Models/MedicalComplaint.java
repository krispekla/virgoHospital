package com.krispeklaric.virgohospital_dal.Models;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "MedicalComplaints")
public class MedicalComplaint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String diabetic;

    @Column(length = 255)
    private String hypertensive;

    @Column(length = 255)
    private String cardiacCondition;

    @Column(length = 255)
    private String respiratoryCondition;

    @Column(length = 255)
    private String digestiveCondition;

    @Column(length = 255)
    private String orthopedicCondition;

    @Column(length = 255)
    private String muscularCondition;

    @Column(length = 255)
    private String neurologicalCondition;

    @Column(length = 255)
    private String allergies;

    @Column(length = 255)
    private String drugsReaction;

    @Column(length = 255)
    private String historyOfSurgeries;

    @OneToOne(mappedBy = "medicalComplaints")
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(String diabetic) {
        this.diabetic = diabetic;
    }

    public String getHypertensive() {
        return hypertensive;
    }

    public void setHypertensive(String hypertensive) {
        this.hypertensive = hypertensive;
    }

    public String getCardiacCondition() {
        return cardiacCondition;
    }

    public void setCardiacCondition(String cardiacCondition) {
        this.cardiacCondition = cardiacCondition;
    }

    public String getRespiratoryCondition() {
        return respiratoryCondition;
    }

    public void setRespiratoryCondition(String respiratoryCondition) {
        this.respiratoryCondition = respiratoryCondition;
    }

    public String getDigestiveCondition() {
        return digestiveCondition;
    }

    public void setDigestiveCondition(String digestiveCondition) {
        this.digestiveCondition = digestiveCondition;
    }

    public String getOrthopedicCondition() {
        return orthopedicCondition;
    }

    public void setOrthopedicCondition(String orthopedicCondition) {
        this.orthopedicCondition = orthopedicCondition;
    }

    public String getMuscularCondition() {
        return muscularCondition;
    }

    public void setMuscularCondition(String muscularCondition) {
        this.muscularCondition = muscularCondition;
    }

    public String getNeurologicalCondition() {
        return neurologicalCondition;
    }

    public void setNeurologicalCondition(String neurologicalCondition) {
        this.neurologicalCondition = neurologicalCondition;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDrugsReaction() {
        return drugsReaction;
    }

    public void setDrugsReaction(String drugsReaction) {
        this.drugsReaction = drugsReaction;
    }

    public String getHistoryOfSurgeries() {
        return historyOfSurgeries;
    }

    public void setHistoryOfSurgeries(String historyOfSurgeries) {
        this.historyOfSurgeries = historyOfSurgeries;
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
        if (!(object instanceof MedicalComplaint)) {
            return false;
        }
        MedicalComplaint other = (MedicalComplaint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.MedicalComplaint[ id=" + id + " ]";
    }

}
