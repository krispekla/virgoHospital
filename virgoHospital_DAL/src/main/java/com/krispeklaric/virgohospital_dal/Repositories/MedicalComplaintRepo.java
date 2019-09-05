package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IMedicalComplaintRepo;
import com.krispeklaric.virgohospital_dal.Models.MedicalComplaint;
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
public class MedicalComplaintRepo extends BaseRepo implements IMedicalComplaintRepo, Serializable {

    public MedicalComplaintRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MedicalComplaint create(MedicalComplaint medicalComplaint) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(medicalComplaint);
            em.getTransaction().commit();
            return medicalComplaint;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public MedicalComplaint edit(MedicalComplaint medicalComplaint) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            medicalComplaint = em.merge(medicalComplaint);
            em.getTransaction().commit();
            return medicalComplaint;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = medicalComplaint.getId();
                if (findMedicalComplaint(id) == null) {
                    throw new NonexistentEntityException("The medicalComplaint with id " + id + " no longer exists.");
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
            MedicalComplaint medicalComplaint;
            try {
                medicalComplaint = em.getReference(MedicalComplaint.class, id);
                medicalComplaint.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicalComplaint with id " + id + " no longer exists.", enfe);
            }
            em.remove(medicalComplaint);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicalComplaint> findMedicalComplaintEntities() {
        return findMedicalComplaintEntities(true, -1, -1);
    }

    public List<MedicalComplaint> findMedicalComplaintEntities(int maxResults, int firstResult) {
        return findMedicalComplaintEntities(false, maxResults, firstResult);
    }

    private List<MedicalComplaint> findMedicalComplaintEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicalComplaint.class));
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

    public MedicalComplaint findMedicalComplaint(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicalComplaint.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicalComplaintCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicalComplaint> rt = cq.from(MedicalComplaint.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
