package com.krispeklaric.virgohospital_dal.Models;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "PatientLifestyles")
public class PatientLifestyle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean vegeterian;

    @Column(nullable = false)
    private boolean smoker;

    @Column(nullable = false)
    private boolean alcoholConsumer;

    @Column(nullable = false)
    private String usingStimulans;

    @Column(nullable = false)
    private int coffePerDay;

    @Column(nullable = false)
    private int softDrinksPerDay;

    @Column(nullable = false)
    private String regularMelas;

    @Column(nullable = false)
    private boolean eatingHomePredominantly;

    @Column(nullable = false)
    private int avgCigarettesDay;

    @Column(nullable = false)
    private int avgDrinksDay;

    @OneToOne(mappedBy = "patientLifestyle")
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isVegeterian() {
        return vegeterian;
    }

    public void setVegeterian(boolean vegeterian) {
        this.vegeterian = vegeterian;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isAlcoholConsumer() {
        return alcoholConsumer;
    }

    public void setAlcoholConsumer(boolean alcoholConsumer) {
        this.alcoholConsumer = alcoholConsumer;
    }

    public String getUsingStimulans() {
        return usingStimulans;
    }

    public void setUsingStimulans(String usingStimulans) {
        this.usingStimulans = usingStimulans;
    }

    public int getCoffePerDay() {
        return coffePerDay;
    }

    public void setCoffePerDay(int coffePerDay) {
        this.coffePerDay = coffePerDay;
    }

    public int getSoftDrinksPerDay() {
        return softDrinksPerDay;
    }

    public void setSoftDrinksPerDay(int softDrinksPerDay) {
        this.softDrinksPerDay = softDrinksPerDay;
    }

    public String getRegularMelas() {
        return regularMelas;
    }

    public void setRegularMelas(String regularMelas) {
        this.regularMelas = regularMelas;
    }

    public boolean isEatingHomePredominantly() {
        return eatingHomePredominantly;
    }

    public void setEatingHomePredominantly(boolean eatingHomePredominantly) {
        this.eatingHomePredominantly = eatingHomePredominantly;
    }

    public int getAvgCigarettesDay() {
        return avgCigarettesDay;
    }

    public void setAvgCigarettesDay(int avgCigarettesDay) {
        this.avgCigarettesDay = avgCigarettesDay;
    }

    public int getAvgDrinksDay() {
        return avgDrinksDay;
    }

    public void setAvgDrinksDay(int avgDrinksDay) {
        this.avgDrinksDay = avgDrinksDay;
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
        if (!(object instanceof PatientLifestyle)) {
            return false;
        }
        PatientLifestyle other = (PatientLifestyle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.PatientLifestyle[ id=" + id + " ]";
    }

}
