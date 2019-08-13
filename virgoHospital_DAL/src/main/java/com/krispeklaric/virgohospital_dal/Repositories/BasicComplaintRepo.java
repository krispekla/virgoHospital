package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IBasicComplaintRepo;
import com.krispeklaric.virgohospital_dal.Models.BasicComplaint;
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
public class BasicComplaintRepo extends BaseRepo implements IBasicComplaintRepo, Serializable {

    public BasicComplaintRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BasicComplaint basicComplaint) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(basicComplaint);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BasicComplaint basicComplaint) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            basicComplaint = em.merge(basicComplaint);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = basicComplaint.getId();
                if (findBasicComplaint(id) == null) {
                    throw new NonexistentEntityException("The basicComplaint with id " + id + " no longer exists.");
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
            BasicComplaint basicComplaint;
            try {
                basicComplaint = em.getReference(BasicComplaint.class, id);
                basicComplaint.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The basicComplaint with id " + id + " no longer exists.", enfe);
            }
            em.remove(basicComplaint);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BasicComplaint> findBasicComplaintEntities() {
        return findBasicComplaintEntities(true, -1, -1);
    }

    public List<BasicComplaint> findBasicComplaintEntities(int maxResults, int firstResult) {
        return findBasicComplaintEntities(false, maxResults, firstResult);
    }

    private List<BasicComplaint> findBasicComplaintEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BasicComplaint.class));
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

    public BasicComplaint findBasicComplaint(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BasicComplaint.class, id);
        } finally {
            em.close();
        }
    }

    public int getBasicComplaintCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BasicComplaint> rt = cq.from(BasicComplaint.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
