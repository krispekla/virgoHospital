/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IDrugPrescriptionRepo;
import com.krispeklaric.virgohospital_dal.Models.Drug;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.DrugPrescriptions;
import com.krispeklaric.virgohospital_dal.Models.Prescription;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class DrugPrescriptionRepo extends BaseRepo implements IDrugPrescriptionRepo, Serializable {

    public DrugPrescriptionRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DrugPrescriptions create(DrugPrescriptions drugPrescriptions) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prescription prescription = drugPrescriptions.getPrescription();
            if (prescription != null) {
                prescription = em.getReference(prescription.getClass(), prescription.getId());
                drugPrescriptions.setPrescription(prescription);
            }
            Drug drug = drugPrescriptions.getDrug();
            if (drug != null) {
                drug = em.getReference(drug.getClass(), drug.getId());
                drugPrescriptions.setDrug(drug);
            }
            em.persist(drugPrescriptions);
            if (prescription != null) {
                prescription.getPhones().add(drugPrescriptions);
                prescription = em.merge(prescription);
            }
            if (drug != null) {
                drug.getPrescriptions().add(drugPrescriptions);
                drug = em.merge(drug);
            }
            em.getTransaction().commit();
            return drugPrescriptions;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public DrugPrescriptions edit(DrugPrescriptions drugPrescriptions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DrugPrescriptions persistentDrugPrescriptions = em.find(DrugPrescriptions.class, drugPrescriptions.getId());
            Prescription prescriptionOld = persistentDrugPrescriptions.getPrescription();
            Prescription prescriptionNew = drugPrescriptions.getPrescription();
            Drug drugOld = persistentDrugPrescriptions.getDrug();
            Drug drugNew = drugPrescriptions.getDrug();
            if (prescriptionNew != null) {
                prescriptionNew = em.getReference(prescriptionNew.getClass(), prescriptionNew.getId());
                drugPrescriptions.setPrescription(prescriptionNew);
            }
            if (drugNew != null) {
                drugNew = em.getReference(drugNew.getClass(), drugNew.getId());
                drugPrescriptions.setDrug(drugNew);
            }
            drugPrescriptions = em.merge(drugPrescriptions);
            if (prescriptionOld != null && !prescriptionOld.equals(prescriptionNew)) {
                prescriptionOld.getPhones().remove(drugPrescriptions);
                prescriptionOld = em.merge(prescriptionOld);
            }
            if (prescriptionNew != null && !prescriptionNew.equals(prescriptionOld)) {
                prescriptionNew.getPhones().add(drugPrescriptions);
                prescriptionNew = em.merge(prescriptionNew);
            }
            if (drugOld != null && !drugOld.equals(drugNew)) {
                drugOld.getPrescriptions().remove(drugPrescriptions);
                drugOld = em.merge(drugOld);
            }
            if (drugNew != null && !drugNew.equals(drugOld)) {
                drugNew.getPrescriptions().add(drugPrescriptions);
                drugNew = em.merge(drugNew);
            }
            em.getTransaction().commit();
            return drugPrescriptions;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = drugPrescriptions.getId();
                if (findDrugPrescriptions(id) == null) {
                    throw new NonexistentEntityException("The drug with id " + id + " no longer exists.");
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
            DrugPrescriptions drugPrescriptions;
            try {
                drugPrescriptions = em.getReference(DrugPrescriptions.class, id);
                drugPrescriptions.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The drugPrescriptions with id " + id + " no longer exists.", enfe);
            }
            Prescription prescription = drugPrescriptions.getPrescription();
            if (prescription != null) {
                prescription.getPhones().remove(drugPrescriptions);
                prescription = em.merge(prescription);
            }
            Drug drug = drugPrescriptions.getDrug();
            if (drug != null) {
                drug.getPrescriptions().remove(drugPrescriptions);
                drug = em.merge(drug);
            }
            em.remove(drugPrescriptions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DrugPrescriptions> findDrugPrescriptionsEntities() {
        return findDrugPrescriptionsEntities(true, -1, -1);
    }

    public List<DrugPrescriptions> findDrugPrescriptionsEntities(int maxResults, int firstResult) {
        return findDrugPrescriptionsEntities(false, maxResults, firstResult);
    }

    private List<DrugPrescriptions> findDrugPrescriptionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DrugPrescriptions.class));
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

    public DrugPrescriptions findDrugPrescriptions(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DrugPrescriptions.class, id);
        } finally {
            em.close();
        }
    }

    public int getDrugPrescriptionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DrugPrescriptions> rt = cq.from(DrugPrescriptions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
