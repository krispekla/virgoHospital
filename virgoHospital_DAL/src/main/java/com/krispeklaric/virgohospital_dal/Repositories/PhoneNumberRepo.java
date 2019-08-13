package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IPhoneNumberRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import com.krispeklaric.virgohospital_dal.Models.PhoneNumber;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class PhoneNumberRepo extends BaseRepo implements IPhoneNumberRepo, Serializable {

    public PhoneNumberRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PhoneNumber phoneNumber) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactDetail contact = phoneNumber.getContact();
            if (contact != null) {
                contact = em.getReference(contact.getClass(), contact.getId());
                phoneNumber.setContact(contact);
            }
            em.persist(phoneNumber);
            if (contact != null) {
                contact.getPhones().add(phoneNumber);
                contact = em.merge(contact);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PhoneNumber phoneNumber) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PhoneNumber persistentPhoneNumber = em.find(PhoneNumber.class, phoneNumber.getId());
            ContactDetail contactOld = persistentPhoneNumber.getContact();
            ContactDetail contactNew = phoneNumber.getContact();
            if (contactNew != null) {
                contactNew = em.getReference(contactNew.getClass(), contactNew.getId());
                phoneNumber.setContact(contactNew);
            }
            phoneNumber = em.merge(phoneNumber);
            if (contactOld != null && !contactOld.equals(contactNew)) {
                contactOld.getPhones().remove(phoneNumber);
                contactOld = em.merge(contactOld);
            }
            if (contactNew != null && !contactNew.equals(contactOld)) {
                contactNew.getPhones().add(phoneNumber);
                contactNew = em.merge(contactNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = phoneNumber.getId();
                if (findPhoneNumber(id) == null) {
                    throw new NonexistentEntityException("The phoneNumber with id " + id + " no longer exists.");
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
            PhoneNumber phoneNumber;
            try {
                phoneNumber = em.getReference(PhoneNumber.class, id);
                phoneNumber.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The phoneNumber with id " + id + " no longer exists.", enfe);
            }
            ContactDetail contact = phoneNumber.getContact();
            if (contact != null) {
                contact.getPhones().remove(phoneNumber);
                contact = em.merge(contact);
            }
            em.remove(phoneNumber);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PhoneNumber> findPhoneNumberEntities() {
        return findPhoneNumberEntities(true, -1, -1);
    }

    public List<PhoneNumber> findPhoneNumberEntities(int maxResults, int firstResult) {
        return findPhoneNumberEntities(false, maxResults, firstResult);
    }

    private List<PhoneNumber> findPhoneNumberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PhoneNumber.class));
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

    public PhoneNumber findPhoneNumber(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PhoneNumber.class, id);
        } finally {
            em.close();
        }
    }

    public int getPhoneNumberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PhoneNumber> rt = cq.from(PhoneNumber.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
