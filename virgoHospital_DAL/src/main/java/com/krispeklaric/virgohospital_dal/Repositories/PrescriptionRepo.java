package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IPrescriptionRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.Appointment;
import com.krispeklaric.virgohospital_dal.Models.Drug;
import com.krispeklaric.virgohospital_dal.Models.Prescription;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class PrescriptionRepo extends BaseRepo implements IPrescriptionRepo, Serializable {

    public PrescriptionRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prescription prescription) {
       EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointment appointment = prescription.getAppointment();
            if (appointment != null) {
                appointment = em.getReference(appointment.getClass(), appointment.getId());
                prescription.setAppointment(appointment);
            }
            em.persist(prescription);
            if (appointment != null) {
                appointment.getPrescriptions().add(prescription);
                appointment = em.merge(appointment);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prescription prescription) throws NonexistentEntityException, Exception {
       EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prescription persistentPrescription = em.find(Prescription.class, prescription.getId());
            Appointment appointmentOld = persistentPrescription.getAppointment();
            Appointment appointmentNew = prescription.getAppointment();
            if (appointmentNew != null) {
                appointmentNew = em.getReference(appointmentNew.getClass(), appointmentNew.getId());
                prescription.setAppointment(appointmentNew);
            }
            prescription = em.merge(prescription);
            if (appointmentOld != null && !appointmentOld.equals(appointmentNew)) {
                appointmentOld.getPrescriptions().remove(prescription);
                appointmentOld = em.merge(appointmentOld);
            }
            if (appointmentNew != null && !appointmentNew.equals(appointmentOld)) {
                appointmentNew.getPrescriptions().add(prescription);
                appointmentNew = em.merge(appointmentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prescription.getId();
                if (findPrescription(id) == null) {
                    throw new NonexistentEntityException("The prescription with id " + id + " no longer exists.");
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
            Prescription prescription;
            try {
                prescription = em.getReference(Prescription.class, id);
                prescription.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prescription with id " + id + " no longer exists.", enfe);
            }
            Appointment appointment = prescription.getAppointment();
            if (appointment != null) {
                appointment.getPrescriptions().remove(prescription);
                appointment = em.merge(appointment);
            }
            em.remove(prescription);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prescription> findPrescriptionEntities() {
        return findPrescriptionEntities(true, -1, -1);
    }

    public List<Prescription> findPrescriptionEntities(int maxResults, int firstResult) {
        return findPrescriptionEntities(false, maxResults, firstResult);
    }

    private List<Prescription> findPrescriptionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prescription.class));
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

    public Prescription findPrescription(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prescription.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrescriptionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prescription> rt = cq.from(Prescription.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
