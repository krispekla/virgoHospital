
package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IBillRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.Appointment;
import com.krispeklaric.virgohospital_dal.Models.Bill;
import com.krispeklaric.virgohospital_dal.Models.Payment;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class BillRepo extends BaseRepo implements IBillRepo, Serializable {

    public BillRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public Bill create(Bill bill) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointment appointment = bill.getAppointment();
            if (appointment != null) {
                appointment = em.getReference(appointment.getClass(), appointment.getId());
                bill.setAppointment(appointment);
            }
            Payment payment = bill.getPayment();
            if (payment != null) {
                payment = em.getReference(payment.getClass(), payment.getId());
                bill.setPayment(payment);
            }
            em.persist(bill);
            if (appointment != null) {
                Bill oldBillOfAppointment = appointment.getBill();
                if (oldBillOfAppointment != null) {
                    oldBillOfAppointment.setAppointment(null);
                    oldBillOfAppointment = em.merge(oldBillOfAppointment);
                }
                appointment.setBill(bill);
                appointment = em.merge(appointment);
            }
            if (payment != null) {
                Bill oldBillOfPayment = payment.getBill();
                if (oldBillOfPayment != null) {
                    oldBillOfPayment.setPayment(null);
                    oldBillOfPayment = em.merge(oldBillOfPayment);
                }
                payment.setBill(bill);
                payment = em.merge(payment);
            }
            em.getTransaction().commit();
            return bill;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Bill edit(Bill bill) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bill persistentBill = em.find(Bill.class, bill.getId());
            Appointment appointmentOld = persistentBill.getAppointment();
            Appointment appointmentNew = bill.getAppointment();
            Payment paymentOld = persistentBill.getPayment();
            Payment paymentNew = bill.getPayment();
            if (appointmentNew != null) {
                appointmentNew = em.getReference(appointmentNew.getClass(), appointmentNew.getId());
                bill.setAppointment(appointmentNew);
            }
            if (paymentNew != null) {
                paymentNew = em.getReference(paymentNew.getClass(), paymentNew.getId());
                bill.setPayment(paymentNew);
            }
            bill = em.merge(bill);
            if (appointmentOld != null && !appointmentOld.equals(appointmentNew)) {
                appointmentOld.setBill(null);
                appointmentOld = em.merge(appointmentOld);
            }
            if (appointmentNew != null && !appointmentNew.equals(appointmentOld)) {
                Bill oldBillOfAppointment = appointmentNew.getBill();
                if (oldBillOfAppointment != null) {
                    oldBillOfAppointment.setAppointment(null);
                    oldBillOfAppointment = em.merge(oldBillOfAppointment);
                }
                appointmentNew.setBill(bill);
                appointmentNew = em.merge(appointmentNew);
            }
            if (paymentOld != null && !paymentOld.equals(paymentNew)) {
                paymentOld.setBill(null);
                paymentOld = em.merge(paymentOld);
            }
            if (paymentNew != null && !paymentNew.equals(paymentOld)) {
                Bill oldBillOfPayment = paymentNew.getBill();
                if (oldBillOfPayment != null) {
                    oldBillOfPayment.setPayment(null);
                    oldBillOfPayment = em.merge(oldBillOfPayment);
                }
                paymentNew.setBill(bill);
                paymentNew = em.merge(paymentNew);
            }
            em.getTransaction().commit();
            return bill;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = bill.getId();
                if (findBill(id) == null) {
                    throw new NonexistentEntityException("The bill with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bill bill;
            try {
                bill = em.getReference(Bill.class, id);
                bill.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bill with id " + id + " no longer exists.", enfe);
            }
            Appointment appointment = bill.getAppointment();
            if (appointment != null) {
                appointment.setBill(null);
                appointment = em.merge(appointment);
            }
            Payment payment = bill.getPayment();
            if (payment != null) {
                payment.setBill(null);
                payment = em.merge(payment);
            }
            em.remove(bill);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bill> findBillEntities() {
        return findBillEntities(true, -1, -1);
    }

    public List<Bill> findBillEntities(int maxResults, int firstResult) {
        return findBillEntities(false, maxResults, firstResult);
    }

    private List<Bill> findBillEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bill.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bill findBill(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bill.class, id);
        } finally {
            em.close();
        }
    }

    public int getBillCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bill> rt = cq.from(Bill.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
