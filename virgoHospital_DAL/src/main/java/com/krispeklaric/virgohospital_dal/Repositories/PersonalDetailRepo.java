package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IPersonalDetailRepo;
import com.krispeklaric.virgohospital_dal.Models.PersonalDetail;
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
public class PersonalDetailRepo extends BaseRepo implements IPersonalDetailRepo, Serializable {

    public PersonalDetailRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonalDetail personalDetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personalDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonalDetail personalDetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personalDetail = em.merge(personalDetail);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = personalDetail.getId();
                if (findPersonalDetail(id) == null) {
                    throw new NonexistentEntityException("The personalDetail with id " + id + " no longer exists.");
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
            PersonalDetail personalDetail;
            try {
                personalDetail = em.getReference(PersonalDetail.class, id);
                personalDetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personalDetail with id " + id + " no longer exists.", enfe);
            }
            em.remove(personalDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonalDetail> findPersonalDetailEntities() {
        return findPersonalDetailEntities(true, -1, -1);
    }

    public List<PersonalDetail> findPersonalDetailEntities(int maxResults, int firstResult) {
        return findPersonalDetailEntities(false, maxResults, firstResult);
    }

    private List<PersonalDetail> findPersonalDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonalDetail.class));
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

    public PersonalDetail findPersonalDetail(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonalDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonalDetail> rt = cq.from(PersonalDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
