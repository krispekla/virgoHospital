package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IPaymentRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.Bill;
import com.krispeklaric.virgohospital_dal.Models.Payment;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class PaymentRepo extends BaseRepo implements IPaymentRepo, Serializable {

    public PaymentRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payment payment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bill bill = payment.getBill();
            if (bill != null) {
                bill = em.getReference(bill.getClass(), bill.getId());
                payment.setBill(bill);
            }
            em.persist(payment);
            if (bill != null) {
                Payment oldPaymentOfBill = bill.getPayment();
                if (oldPaymentOfBill != null) {
                    oldPaymentOfBill.setBill(null);
                    oldPaymentOfBill = em.merge(oldPaymentOfBill);
                }
                bill.setPayment(payment);
                bill = em.merge(bill);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payment payment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payment persistentPayment = em.find(Payment.class, payment.getId());
            Bill billOld = persistentPayment.getBill();
            Bill billNew = payment.getBill();
            if (billNew != null) {
                billNew = em.getReference(billNew.getClass(), billNew.getId());
                payment.setBill(billNew);
            }
            payment = em.merge(payment);
            if (billOld != null && !billOld.equals(billNew)) {
                billOld.setPayment(null);
                billOld = em.merge(billOld);
            }
            if (billNew != null && !billNew.equals(billOld)) {
                Payment oldPaymentOfBill = billNew.getPayment();
                if (oldPaymentOfBill != null) {
                    oldPaymentOfBill.setBill(null);
                    oldPaymentOfBill = em.merge(oldPaymentOfBill);
                }
                billNew.setPayment(payment);
                billNew = em.merge(billNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = payment.getId();
                if (findPayment(id) == null) {
                    throw new NonexistentEntityException("The payment with id " + id + " no longer exists.");
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
            Payment payment;
            try {
                payment = em.getReference(Payment.class, id);
                payment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payment with id " + id + " no longer exists.", enfe);
            }
            Bill bill = payment.getBill();
            if (bill != null) {
                bill.setPayment(null);
                bill = em.merge(bill);
            }
            em.remove(payment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payment> findPaymentEntities() {
        return findPaymentEntities(true, -1, -1);
    }

    public List<Payment> findPaymentEntities(int maxResults, int firstResult) {
        return findPaymentEntities(false, maxResults, firstResult);
    }

    private List<Payment> findPaymentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payment.class));
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

    public Payment findPayment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payment.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payment> rt = cq.from(Payment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
