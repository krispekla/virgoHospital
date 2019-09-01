package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IAddressRepo;
import com.krispeklaric.virgohospital_dal.Models.Address;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class AddressRepo extends BaseRepo implements IAddressRepo, Serializable {

    public AddressRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Address create(Address address) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactDetail contactDetailPresent = address.getContactDetailPresent();
            if (contactDetailPresent != null) {
                contactDetailPresent = em.getReference(contactDetailPresent.getClass(), contactDetailPresent.getId());
                address.setContactDetailPresent(contactDetailPresent);
            }
            ContactDetail contactDetailPermanent = address.getContactDetailPermanent();
            if (contactDetailPermanent != null) {
                contactDetailPermanent = em.getReference(contactDetailPermanent.getClass(), contactDetailPermanent.getId());
                address.setContactDetailPermanent(contactDetailPermanent);
            }
            em.persist(address);
            if (contactDetailPresent != null) {
                Address oldPresentAddressOfContactDetailPresent = contactDetailPresent.getPresentAddress();
                if (oldPresentAddressOfContactDetailPresent != null) {
                    oldPresentAddressOfContactDetailPresent.setContactDetailPresent(null);
                    oldPresentAddressOfContactDetailPresent = em.merge(oldPresentAddressOfContactDetailPresent);
                }
                contactDetailPresent.setPresentAddress(address);
                contactDetailPresent = em.merge(contactDetailPresent);
            }
            if (contactDetailPermanent != null) {
                Address oldPermanentAddressOfContactDetailPermanent = contactDetailPermanent.getPermanentAddress();
                if (oldPermanentAddressOfContactDetailPermanent != null) {
                    oldPermanentAddressOfContactDetailPermanent.setContactDetailPermanent(null);
                    oldPermanentAddressOfContactDetailPermanent = em.merge(oldPermanentAddressOfContactDetailPermanent);
                }
                contactDetailPermanent.setPermanentAddress(address);
                contactDetailPermanent = em.merge(contactDetailPermanent);
            }
            em.getTransaction().commit();
            return address;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Address edit(Address address) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Address persistentAddress = em.find(Address.class, address.getId());
            ContactDetail contactDetailPresentOld = persistentAddress.getContactDetailPresent();
            ContactDetail contactDetailPresentNew = address.getContactDetailPresent();
            ContactDetail contactDetailPermanentOld = persistentAddress.getContactDetailPermanent();
            ContactDetail contactDetailPermanentNew = address.getContactDetailPermanent();
            if (contactDetailPresentNew != null) {
                contactDetailPresentNew = em.getReference(contactDetailPresentNew.getClass(), contactDetailPresentNew.getId());
                address.setContactDetailPresent(contactDetailPresentNew);
            }
            if (contactDetailPermanentNew != null) {
                contactDetailPermanentNew = em.getReference(contactDetailPermanentNew.getClass(), contactDetailPermanentNew.getId());
                address.setContactDetailPermanent(contactDetailPermanentNew);
            }
            address = em.merge(address);
            if (contactDetailPresentOld != null && !contactDetailPresentOld.equals(contactDetailPresentNew)) {
                contactDetailPresentOld.setPresentAddress(null);
                contactDetailPresentOld = em.merge(contactDetailPresentOld);
            }
            if (contactDetailPresentNew != null && !contactDetailPresentNew.equals(contactDetailPresentOld)) {
                Address oldPresentAddressOfContactDetailPresent = contactDetailPresentNew.getPresentAddress();
                if (oldPresentAddressOfContactDetailPresent != null) {
                    oldPresentAddressOfContactDetailPresent.setContactDetailPresent(null);
                    oldPresentAddressOfContactDetailPresent = em.merge(oldPresentAddressOfContactDetailPresent);
                }
                contactDetailPresentNew.setPresentAddress(address);
                contactDetailPresentNew = em.merge(contactDetailPresentNew);
            }
            if (contactDetailPermanentOld != null && !contactDetailPermanentOld.equals(contactDetailPermanentNew)) {
                contactDetailPermanentOld.setPermanentAddress(null);
                contactDetailPermanentOld = em.merge(contactDetailPermanentOld);
            }
            if (contactDetailPermanentNew != null && !contactDetailPermanentNew.equals(contactDetailPermanentOld)) {
                Address oldPermanentAddressOfContactDetailPermanent = contactDetailPermanentNew.getPermanentAddress();
                if (oldPermanentAddressOfContactDetailPermanent != null) {
                    oldPermanentAddressOfContactDetailPermanent.setContactDetailPermanent(null);
                    oldPermanentAddressOfContactDetailPermanent = em.merge(oldPermanentAddressOfContactDetailPermanent);
                }
                contactDetailPermanentNew.setPermanentAddress(address);
                contactDetailPermanentNew = em.merge(contactDetailPermanentNew);
            }
            em.getTransaction().commit();
            return address;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = address.getId();
                if (findAddress(id) == null) {
                    throw new NonexistentEntityException("The address with id " + id + " no longer exists.");
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
            Address address;
            try {
                address = em.getReference(Address.class, id);
                address.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The address with id " + id + " no longer exists.", enfe);
            }
            ContactDetail contactDetailPresent = address.getContactDetailPresent();
            if (contactDetailPresent != null) {
                contactDetailPresent.setPresentAddress(null);
                contactDetailPresent = em.merge(contactDetailPresent);
            }
            ContactDetail contactDetailPermanent = address.getContactDetailPermanent();
            if (contactDetailPermanent != null) {
                contactDetailPermanent.setPermanentAddress(null);
                contactDetailPermanent = em.merge(contactDetailPermanent);
            }
            em.remove(address);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Address> findAddressEntities() {
        return findAddressEntities(true, -1, -1);
    }

    public List<Address> findAddressEntities(int maxResults, int firstResult) {
        return findAddressEntities(false, maxResults, firstResult);
    }

    private List<Address> findAddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Address.class));
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

    public Address findAddress(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Address.class, id);
        } finally {
            em.close();
        }
    }

    public int getAddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Address> rt = cq.from(Address.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
