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
        if (prescription.getDrugs() == null) {
            prescription.setDrugs(new ArrayList<Drug>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointment appointment = prescription.getAppointment();
            if (appointment != null) {
                appointment = em.getReference(appointment.getClass(), appointment.getId());
                prescription.setAppointment(appointment);
            }
            List<Drug> attachedDrugs = new ArrayList<Drug>();
            for (Drug drugsDrugToAttach : prescription.getDrugs()) {
                drugsDrugToAttach = em.getReference(drugsDrugToAttach.getClass(), drugsDrugToAttach.getId());
                attachedDrugs.add(drugsDrugToAttach);
            }
            prescription.setDrugs(attachedDrugs);
            em.persist(prescription);
            if (appointment != null) {
                appointment.getPrescriptions().add(prescription);
                appointment = em.merge(appointment);
            }
            for (Drug drugsDrug : prescription.getDrugs()) {
                Prescription oldPrescriptionOfDrugsDrug = drugsDrug.getPrescription();
                drugsDrug.setPrescription(prescription);
                drugsDrug = em.merge(drugsDrug);
                if (oldPrescriptionOfDrugsDrug != null) {
                    oldPrescriptionOfDrugsDrug.getDrugs().remove(drugsDrug);
                    oldPrescriptionOfDrugsDrug = em.merge(oldPrescriptionOfDrugsDrug);
                }
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
            List<Drug> drugsOld = persistentPrescription.getDrugs();
            List<Drug> drugsNew = prescription.getDrugs();
            if (appointmentNew != null) {
                appointmentNew = em.getReference(appointmentNew.getClass(), appointmentNew.getId());
                prescription.setAppointment(appointmentNew);
            }
            List<Drug> attachedDrugsNew = new ArrayList<Drug>();
            for (Drug drugsNewDrugToAttach : drugsNew) {
                drugsNewDrugToAttach = em.getReference(drugsNewDrugToAttach.getClass(), drugsNewDrugToAttach.getId());
                attachedDrugsNew.add(drugsNewDrugToAttach);
            }
            drugsNew = attachedDrugsNew;
            prescription.setDrugs(drugsNew);
            prescription = em.merge(prescription);
            if (appointmentOld != null && !appointmentOld.equals(appointmentNew)) {
                appointmentOld.getPrescriptions().remove(prescription);
                appointmentOld = em.merge(appointmentOld);
            }
            if (appointmentNew != null && !appointmentNew.equals(appointmentOld)) {
                appointmentNew.getPrescriptions().add(prescription);
                appointmentNew = em.merge(appointmentNew);
            }
            for (Drug drugsOldDrug : drugsOld) {
                if (!drugsNew.contains(drugsOldDrug)) {
                    drugsOldDrug.setPrescription(null);
                    drugsOldDrug = em.merge(drugsOldDrug);
                }
            }
            for (Drug drugsNewDrug : drugsNew) {
                if (!drugsOld.contains(drugsNewDrug)) {
                    Prescription oldPrescriptionOfDrugsNewDrug = drugsNewDrug.getPrescription();
                    drugsNewDrug.setPrescription(prescription);
                    drugsNewDrug = em.merge(drugsNewDrug);
                    if (oldPrescriptionOfDrugsNewDrug != null && !oldPrescriptionOfDrugsNewDrug.equals(prescription)) {
                        oldPrescriptionOfDrugsNewDrug.getDrugs().remove(drugsNewDrug);
                        oldPrescriptionOfDrugsNewDrug = em.merge(oldPrescriptionOfDrugsNewDrug);
                    }
                }
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
            List<Drug> drugs = prescription.getDrugs();
            for (Drug drugsDrug : drugs) {
                drugsDrug.setPrescription(null);
                drugsDrug = em.merge(drugsDrug);
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
