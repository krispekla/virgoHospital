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
@Table(name = "Prescriptions")
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int quantity;

    @Column(nullable = false)
    private LocalDate prescriptionDate;

    @ManyToOne
    @JoinColumn
    private Appointment appointment;

    @OneToMany(mappedBy = "prescription")
    private List<DrugPrescriptions> drugs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DrugPrescriptions> getPhones() {
        return drugs;
    }

    public void setPhones(List<DrugPrescriptions> drugs) {
        this.drugs = drugs;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.Prescription[ id=" + id + " ]";
    }

}
