package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IPatientLifestyleRepo;
import com.krispeklaric.virgohospital_dal.Models.PatientLifestyle;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kris
 */
public class PatientLifestyleRepo extends BaseRepo implements IPatientLifestyleRepo, Serializable {

    public PatientLifestyleRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PatientLifestyle create(PatientLifestyle patientLifestyle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(patientLifestyle);
            em.getTransaction().commit();
            return patientLifestyle;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public PatientLifestyle edit(PatientLifestyle patientLifestyle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            patientLifestyle = em.merge(patientLifestyle);
            em.getTransaction().commit();
            return patientLifestyle;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = patientLifestyle.getId();
                if (findPatientLifestyle(id) == null) {
                    throw new NonexistentEntityException("The patientLifestyle with id " + id + " no longer exists.");
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
            PatientLifestyle patientLifestyle;
            try {
                patientLifestyle = em.getReference(PatientLifestyle.class, id);
                patientLifestyle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The patientLifestyle with id " + id + " no longer exists.", enfe);
            }
            em.remove(patientLifestyle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PatientLifestyle> findPatientLifestyleEntities() {
        return findPatientLifestyleEntities(true, -1, -1);
    }

    public List<PatientLifestyle> findPatientLifestyleEntities(int maxResults, int firstResult) {
        return findPatientLifestyleEntities(false, maxResults, firstResult);
    }

    private List<PatientLifestyle> findPatientLifestyleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PatientLifestyle.class));
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

    public PatientLifestyle findPatientLifestyle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PatientLifestyle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPatientLifestyleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PatientLifestyle> rt = cq.from(PatientLifestyle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
