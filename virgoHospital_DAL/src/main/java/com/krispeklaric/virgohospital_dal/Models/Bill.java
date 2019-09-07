package com.krispeklaric.virgohospital_dal.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 *
 * @author Kris
 */
@Entity
@Table(name = "Bills")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Money default '0'", nullable = true)
    private BigDecimal consultationPrice;

    @Column(columnDefinition = "Money default '0'")
    private BigDecimal testsPrice;

    private int testQuantity;

    @Column(columnDefinition = "Money default '0'")
    private BigDecimal prescriptionsPrice;

    private int prescriptionQuantity;

    @Column(nullable = false)
    private LocalDateTime timeIssued;

    @Column(columnDefinition = "Money default '0'")
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "appointment_ID")
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "payment_ID")
    private Payment payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(BigDecimal consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

    public BigDecimal getTestsPrice() {
        return testsPrice;
    }

    public void setTestsPrice(BigDecimal testsPrice) {
        this.testsPrice = testsPrice;
    }

    public int getTestQuantity() {
        return testQuantity;
    }

    public void setTestQuantity(int testQuantity) {
        this.testQuantity = testQuantity;
    }

    public BigDecimal getPrescriptionsPrice() {
        return prescriptionsPrice;
    }

    public void setPrescriptionsPrice(BigDecimal prescriptionsPrice) {
        this.prescriptionsPrice = prescriptionsPrice;
    }

    public int getPrescriptionQuantity() {
        return prescriptionQuantity;
    }

    public void setPrescriptionQuantity(int prescriptionQuantity) {
        this.prescriptionQuantity = prescriptionQuantity;
    }

    public LocalDateTime getTimeIssued() {
        return timeIssued;
    }

    public void setTimeIssued(LocalDateTime timeIssued) {
        this.timeIssued = timeIssued;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krispeklaric.virgohospital_dal.Models.Bill[ id=" + id + " ]";
    }

}
