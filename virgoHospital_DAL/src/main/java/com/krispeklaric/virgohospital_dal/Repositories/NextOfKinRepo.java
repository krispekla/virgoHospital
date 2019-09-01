package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.INextOfKinRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import com.krispeklaric.virgohospital_dal.Models.NextOfKin;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class NextOfKinRepo extends BaseRepo implements INextOfKinRepo, Serializable {

    public NextOfKinRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public NextOfKin create(NextOfKin nextOfKin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactDetail contactDetailNextOf = nextOfKin.getContactDetailNextOf();
            if (contactDetailNextOf != null) {
                contactDetailNextOf = em.getReference(contactDetailNextOf.getClass(), contactDetailNextOf.getId());
                nextOfKin.setContactDetailNextOf(contactDetailNextOf);
            }
            em.persist(nextOfKin);
            if (contactDetailNextOf != null) {
                NextOfKin oldNextOfKinOfContactDetailNextOf = contactDetailNextOf.getNextOfKin();
                if (oldNextOfKinOfContactDetailNextOf != null) {
                    oldNextOfKinOfContactDetailNextOf.setContactDetailNextOf(null);
                    oldNextOfKinOfContactDetailNextOf = em.merge(oldNextOfKinOfContactDetailNextOf);
                }
                contactDetailNextOf.setNextOfKin(nextOfKin);
                contactDetailNextOf = em.merge(contactDetailNextOf);
            }
            em.getTransaction().commit();
            return nextOfKin;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public NextOfKin edit(NextOfKin nextOfKin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NextOfKin persistentNextOfKin = em.find(NextOfKin.class, nextOfKin.getId());
            ContactDetail contactDetailNextOfOld = persistentNextOfKin.getContactDetailNextOf();
            ContactDetail contactDetailNextOfNew = nextOfKin.getContactDetailNextOf();
            if (contactDetailNextOfNew != null) {
                contactDetailNextOfNew = em.getReference(contactDetailNextOfNew.getClass(), contactDetailNextOfNew.getId());
                nextOfKin.setContactDetailNextOf(contactDetailNextOfNew);
            }
            nextOfKin = em.merge(nextOfKin);
            if (contactDetailNextOfOld != null && !contactDetailNextOfOld.equals(contactDetailNextOfNew)) {
                contactDetailNextOfOld.setNextOfKin(null);
                contactDetailNextOfOld = em.merge(contactDetailNextOfOld);
            }
            if (contactDetailNextOfNew != null && !contactDetailNextOfNew.equals(contactDetailNextOfOld)) {
                NextOfKin oldNextOfKinOfContactDetailNextOf = contactDetailNextOfNew.getNextOfKin();
                if (oldNextOfKinOfContactDetailNextOf != null) {
                    oldNextOfKinOfContactDetailNextOf.setContactDetailNextOf(null);
                    oldNextOfKinOfContactDetailNextOf = em.merge(oldNextOfKinOfContactDetailNextOf);
                }
                contactDetailNextOfNew.setNextOfKin(nextOfKin);
                contactDetailNextOfNew = em.merge(contactDetailNextOfNew);
            }
            em.getTransaction().commit();
            return nextOfKin;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = nextOfKin.getId();
                if (findNextOfKin(id) == null) {
                    throw new NonexistentEntityException("The nextOfKin with id " + id + " no longer exists.");
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
            NextOfKin nextOfKin;
            try {
                nextOfKin = em.getReference(NextOfKin.class, id);
                nextOfKin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nextOfKin with id " + id + " no longer exists.", enfe);
            }
            ContactDetail contactDetailNextOf = nextOfKin.getContactDetailNextOf();
            if (contactDetailNextOf != null) {
                contactDetailNextOf.setNextOfKin(null);
                contactDetailNextOf = em.merge(contactDetailNextOf);
            }
            em.remove(nextOfKin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NextOfKin> findNextOfKinEntities() {
        return findNextOfKinEntities(true, -1, -1);
    }

    public List<NextOfKin> findNextOfKinEntities(int maxResults, int firstResult) {
        return findNextOfKinEntities(false, maxResults, firstResult);
    }

    private List<NextOfKin> findNextOfKinEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NextOfKin.class));
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

    public NextOfKin findNextOfKin(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NextOfKin.class, id);
        } finally {
            em.close();
        }
    }

    public int getNextOfKinCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NextOfKin> rt = cq.from(NextOfKin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
