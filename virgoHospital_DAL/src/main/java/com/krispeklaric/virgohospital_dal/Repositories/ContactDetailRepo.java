package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IContactDetailRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.Address;
import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import com.krispeklaric.virgohospital_dal.Models.NextOfKin;
import com.krispeklaric.virgohospital_dal.Models.Doctor;
import com.krispeklaric.virgohospital_dal.Models.PhoneNumber;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class ContactDetailRepo extends BaseRepo implements IContactDetailRepo, Serializable {

    public ContactDetailRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ContactDetail create(ContactDetail contactDetail) {
        if (contactDetail.getPhones() == null) {
            contactDetail.setPhones(new ArrayList<PhoneNumber>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Address presentAddress = contactDetail.getPresentAddress();
            if (presentAddress != null) {
                presentAddress = em.getReference(presentAddress.getClass(), presentAddress.getId());
                contactDetail.setPresentAddress(presentAddress);
            }
            Address permanentAddress = contactDetail.getPermanentAddress();
            if (permanentAddress != null) {
                permanentAddress = em.getReference(permanentAddress.getClass(), permanentAddress.getId());
                contactDetail.setPermanentAddress(permanentAddress);
            }
            NextOfKin nextOfKin = contactDetail.getNextOfKin();
            if (nextOfKin != null) {
                nextOfKin = em.getReference(nextOfKin.getClass(), nextOfKin.getId());
                contactDetail.setNextOfKin(nextOfKin);
            }
            Doctor doctor = contactDetail.getDoctor();
            if (doctor != null) {
                doctor = em.getReference(doctor.getClass(), doctor.getId());
                contactDetail.setDoctor(doctor);
            }
            List<PhoneNumber> attachedPhones = new ArrayList<PhoneNumber>();
            for (PhoneNumber phonesPhoneNumberToAttach : contactDetail.getPhones()) {
                phonesPhoneNumberToAttach = em.getReference(phonesPhoneNumberToAttach.getClass(), phonesPhoneNumberToAttach.getId());
                attachedPhones.add(phonesPhoneNumberToAttach);
            }
            contactDetail.setPhones(attachedPhones);
            em.persist(contactDetail);
            if (presentAddress != null) {
                ContactDetail oldContactDetailPresentOfPresentAddress = presentAddress.getContactDetailPresent();
                if (oldContactDetailPresentOfPresentAddress != null) {
                    oldContactDetailPresentOfPresentAddress.setPresentAddress(null);
                    oldContactDetailPresentOfPresentAddress = em.merge(oldContactDetailPresentOfPresentAddress);
                }
                presentAddress.setContactDetailPresent(contactDetail);
                presentAddress = em.merge(presentAddress);
            }
            if (permanentAddress != null) {
                ContactDetail oldContactDetailPresentOfPermanentAddress = permanentAddress.getContactDetailPresent();
                if (oldContactDetailPresentOfPermanentAddress != null) {
                    oldContactDetailPresentOfPermanentAddress.setPermanentAddress(null);
                    oldContactDetailPresentOfPermanentAddress = em.merge(oldContactDetailPresentOfPermanentAddress);
                }
                permanentAddress.setContactDetailPresent(contactDetail);
                permanentAddress = em.merge(permanentAddress);
            }
            if (nextOfKin != null) {
                ContactDetail oldContactDetailNextOfOfNextOfKin = nextOfKin.getContactDetailNextOf();
                if (oldContactDetailNextOfOfNextOfKin != null) {
                    oldContactDetailNextOfOfNextOfKin.setNextOfKin(null);
                    oldContactDetailNextOfOfNextOfKin = em.merge(oldContactDetailNextOfOfNextOfKin);
                }
                nextOfKin.setContactDetailNextOf(contactDetail);
                nextOfKin = em.merge(nextOfKin);
            }
            if (doctor != null) {
                ContactDetail oldContactDetailOfDoctor = doctor.getContactDetail();
                if (oldContactDetailOfDoctor != null) {
                    oldContactDetailOfDoctor.setDoctor(null);
                    oldContactDetailOfDoctor = em.merge(oldContactDetailOfDoctor);
                }
                doctor.setContactDetail(contactDetail);
                doctor = em.merge(doctor);
            }
            for (PhoneNumber phonesPhoneNumber : contactDetail.getPhones()) {
                ContactDetail oldContactOfPhonesPhoneNumber = phonesPhoneNumber.getContact();
                phonesPhoneNumber.setContact(contactDetail);
                phonesPhoneNumber = em.merge(phonesPhoneNumber);
                if (oldContactOfPhonesPhoneNumber != null) {
                    oldContactOfPhonesPhoneNumber.getPhones().remove(phonesPhoneNumber);
                    oldContactOfPhonesPhoneNumber = em.merge(oldContactOfPhonesPhoneNumber);
                }
            }
            em.getTransaction().commit();
            return contactDetail;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public ContactDetail edit(ContactDetail contactDetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactDetail persistentContactDetail = em.find(ContactDetail.class, contactDetail.getId());
            Address presentAddressOld = persistentContactDetail.getPresentAddress();
            Address presentAddressNew = contactDetail.getPresentAddress();
            Address permanentAddressOld = persistentContactDetail.getPermanentAddress();
            Address permanentAddressNew = contactDetail.getPermanentAddress();
            NextOfKin nextOfKinOld = persistentContactDetail.getNextOfKin();
            NextOfKin nextOfKinNew = contactDetail.getNextOfKin();
            Doctor doctorOld = persistentContactDetail.getDoctor();
            Doctor doctorNew = contactDetail.getDoctor();
            List<PhoneNumber> phonesOld = persistentContactDetail.getPhones();
            List<PhoneNumber> phonesNew = contactDetail.getPhones();
            if (presentAddressNew != null) {
                presentAddressNew = em.getReference(presentAddressNew.getClass(), presentAddressNew.getId());
                contactDetail.setPresentAddress(presentAddressNew);
            }
            if (permanentAddressNew != null) {
                permanentAddressNew = em.getReference(permanentAddressNew.getClass(), permanentAddressNew.getId());
                contactDetail.setPermanentAddress(permanentAddressNew);
            }
            if (nextOfKinNew != null) {
                nextOfKinNew = em.getReference(nextOfKinNew.getClass(), nextOfKinNew.getId());
                contactDetail.setNextOfKin(nextOfKinNew);
            }
            if (doctorNew != null) {
                doctorNew = em.getReference(doctorNew.getClass(), doctorNew.getId());
                contactDetail.setDoctor(doctorNew);
            }
            List<PhoneNumber> attachedPhonesNew = new ArrayList<PhoneNumber>();
            for (PhoneNumber phonesNewPhoneNumberToAttach : phonesNew) {
                phonesNewPhoneNumberToAttach = em.getReference(phonesNewPhoneNumberToAttach.getClass(), phonesNewPhoneNumberToAttach.getId());
                attachedPhonesNew.add(phonesNewPhoneNumberToAttach);
            }
            phonesNew = attachedPhonesNew;
            contactDetail.setPhones(phonesNew);
            contactDetail = em.merge(contactDetail);
            if (presentAddressOld != null && !presentAddressOld.equals(presentAddressNew)) {
                presentAddressOld.setContactDetailPresent(null);
                presentAddressOld = em.merge(presentAddressOld);
            }
            if (presentAddressNew != null && !presentAddressNew.equals(presentAddressOld)) {
                ContactDetail oldContactDetailPresentOfPresentAddress = presentAddressNew.getContactDetailPresent();
                if (oldContactDetailPresentOfPresentAddress != null) {
                    oldContactDetailPresentOfPresentAddress.setPresentAddress(null);
                    oldContactDetailPresentOfPresentAddress = em.merge(oldContactDetailPresentOfPresentAddress);
                }
                presentAddressNew.setContactDetailPresent(contactDetail);
                presentAddressNew = em.merge(presentAddressNew);
            }
            if (permanentAddressOld != null && !permanentAddressOld.equals(permanentAddressNew)) {
                permanentAddressOld.setContactDetailPresent(null);
                permanentAddressOld = em.merge(permanentAddressOld);
            }
            if (permanentAddressNew != null && !permanentAddressNew.equals(permanentAddressOld)) {
                ContactDetail oldContactDetailPresentOfPermanentAddress = permanentAddressNew.getContactDetailPresent();
                if (oldContactDetailPresentOfPermanentAddress != null) {
                    oldContactDetailPresentOfPermanentAddress.setPermanentAddress(null);
                    oldContactDetailPresentOfPermanentAddress = em.merge(oldContactDetailPresentOfPermanentAddress);
                }
                permanentAddressNew.setContactDetailPresent(contactDetail);
                permanentAddressNew = em.merge(permanentAddressNew);
            }
            if (nextOfKinOld != null && !nextOfKinOld.equals(nextOfKinNew)) {
                nextOfKinOld.setContactDetailNextOf(null);
                nextOfKinOld = em.merge(nextOfKinOld);
            }
            if (nextOfKinNew != null && !nextOfKinNew.equals(nextOfKinOld)) {
                ContactDetail oldContactDetailNextOfOfNextOfKin = nextOfKinNew.getContactDetailNextOf();
                if (oldContactDetailNextOfOfNextOfKin != null) {
                    oldContactDetailNextOfOfNextOfKin.setNextOfKin(null);
                    oldContactDetailNextOfOfNextOfKin = em.merge(oldContactDetailNextOfOfNextOfKin);
                }
                nextOfKinNew.setContactDetailNextOf(contactDetail);
                nextOfKinNew = em.merge(nextOfKinNew);
            }
            if (doctorOld != null && !doctorOld.equals(doctorNew)) {
                doctorOld.setContactDetail(null);
                doctorOld = em.merge(doctorOld);
            }
            if (doctorNew != null && !doctorNew.equals(doctorOld)) {
                ContactDetail oldContactDetailOfDoctor = doctorNew.getContactDetail();
                if (oldContactDetailOfDoctor != null) {
                    oldContactDetailOfDoctor.setDoctor(null);
                    oldContactDetailOfDoctor = em.merge(oldContactDetailOfDoctor);
                }
                doctorNew.setContactDetail(contactDetail);
                doctorNew = em.merge(doctorNew);
            }
            for (PhoneNumber phonesOldPhoneNumber : phonesOld) {
                if (!phonesNew.contains(phonesOldPhoneNumber)) {
                    phonesOldPhoneNumber.setContact(null);
                    phonesOldPhoneNumber = em.merge(phonesOldPhoneNumber);
                }
            }
            for (PhoneNumber phonesNewPhoneNumber : phonesNew) {
                if (!phonesOld.contains(phonesNewPhoneNumber)) {
                    ContactDetail oldContactOfPhonesNewPhoneNumber = phonesNewPhoneNumber.getContact();
                    phonesNewPhoneNumber.setContact(contactDetail);
                    phonesNewPhoneNumber = em.merge(phonesNewPhoneNumber);
                    if (oldContactOfPhonesNewPhoneNumber != null && !oldContactOfPhonesNewPhoneNumber.equals(contactDetail)) {
                        oldContactOfPhonesNewPhoneNumber.getPhones().remove(phonesNewPhoneNumber);
                        oldContactOfPhonesNewPhoneNumber = em.merge(oldContactOfPhonesNewPhoneNumber);
                    }
                }
            }
            em.getTransaction().commit();
            return contactDetail;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = contactDetail.getId();
                if (findContactDetail(id) == null) {
                    throw new NonexistentEntityException("The contactDetail with id " + id + " no longer exists.");
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
            ContactDetail contactDetail;
            try {
                contactDetail = em.getReference(ContactDetail.class, id);
                contactDetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contactDetail with id " + id + " no longer exists.", enfe);
            }
            Address presentAddress = contactDetail.getPresentAddress();
            if (presentAddress != null) {
                presentAddress.setContactDetailPresent(null);
                presentAddress = em.merge(presentAddress);
            }
            Address permanentAddress = contactDetail.getPermanentAddress();
            if (permanentAddress != null) {
                permanentAddress.setContactDetailPresent(null);
                permanentAddress = em.merge(permanentAddress);
            }
            NextOfKin nextOfKin = contactDetail.getNextOfKin();
            if (nextOfKin != null) {
                nextOfKin.setContactDetailNextOf(null);
                nextOfKin = em.merge(nextOfKin);
            }
            Doctor doctor = contactDetail.getDoctor();
            if (doctor != null) {
                doctor.setContactDetail(null);
                doctor = em.merge(doctor);
            }
            List<PhoneNumber> phones = contactDetail.getPhones();
            for (PhoneNumber phonesPhoneNumber : phones) {
                phonesPhoneNumber.setContact(null);
                phonesPhoneNumber = em.merge(phonesPhoneNumber);
            }
            em.remove(contactDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContactDetail> findContactDetailEntities() {
        return findContactDetailEntities(true, -1, -1);
    }

    public List<ContactDetail> findContactDetailEntities(int maxResults, int firstResult) {
        return findContactDetailEntities(false, maxResults, firstResult);
    }

    private List<ContactDetail> findContactDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContactDetail.class));
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

    public ContactDetail findContactDetail(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContactDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getContactDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContactDetail> rt = cq.from(ContactDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
