/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IDrugRepo;
import com.krispeklaric.virgohospital_dal.Models.Drug;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.DrugPrescriptions;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Kris
 */
public class DrugRepo extends BaseRepo implements IDrugRepo, Serializable {

    public DrugRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Drug create(Drug drug) {
        if (drug.getPrescriptions() == null) {
            drug.setPrescriptions(new ArrayList<DrugPrescriptions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DrugPrescriptions> attachedPrescriptions = new ArrayList<DrugPrescriptions>();
            for (DrugPrescriptions prescriptionsDrugPrescriptionsToAttach : drug.getPrescriptions()) {
                prescriptionsDrugPrescriptionsToAttach = em.getReference(prescriptionsDrugPrescriptionsToAttach.getClass(), prescriptionsDrugPrescriptionsToAttach.getId());
                attachedPrescriptions.add(prescriptionsDrugPrescriptionsToAttach);
            }
            drug.setPrescriptions(attachedPrescriptions);
            em.persist(drug);
            for (DrugPrescriptions prescriptionsDrugPrescriptions : drug.getPrescriptions()) {
                Drug oldDrugOfPrescriptionsDrugPrescriptions = prescriptionsDrugPrescriptions.getDrug();
                prescriptionsDrugPrescriptions.setDrug(drug);
                prescriptionsDrugPrescriptions = em.merge(prescriptionsDrugPrescriptions);
                if (oldDrugOfPrescriptionsDrugPrescriptions != null) {
                    oldDrugOfPrescriptionsDrugPrescriptions.getPrescriptions().remove(prescriptionsDrugPrescriptions);
                    oldDrugOfPrescriptionsDrugPrescriptions = em.merge(oldDrugOfPrescriptionsDrugPrescriptions);
                }
            }
            em.getTransaction().commit();
            return drug;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Drug edit(Drug drug) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drug persistentDrug = em.find(Drug.class, drug.getId());
            List<DrugPrescriptions> prescriptionsOld = persistentDrug.getPrescriptions();
            List<DrugPrescriptions> prescriptionsNew = drug.getPrescriptions();
            List<DrugPrescriptions> attachedPrescriptionsNew = new ArrayList<DrugPrescriptions>();
            for (DrugPrescriptions prescriptionsNewDrugPrescriptionsToAttach : prescriptionsNew) {
                prescriptionsNewDrugPrescriptionsToAttach = em.getReference(prescriptionsNewDrugPrescriptionsToAttach.getClass(), prescriptionsNewDrugPrescriptionsToAttach.getId());
                attachedPrescriptionsNew.add(prescriptionsNewDrugPrescriptionsToAttach);
            }
            prescriptionsNew = attachedPrescriptionsNew;
            drug.setPrescriptions(prescriptionsNew);
            drug = em.merge(drug);
            for (DrugPrescriptions prescriptionsOldDrugPrescriptions : prescriptionsOld) {
                if (!prescriptionsNew.contains(prescriptionsOldDrugPrescriptions)) {
                    prescriptionsOldDrugPrescriptions.setDrug(null);
                    prescriptionsOldDrugPrescriptions = em.merge(prescriptionsOldDrugPrescriptions);
                }
            }
            for (DrugPrescriptions prescriptionsNewDrugPrescriptions : prescriptionsNew) {
                if (!prescriptionsOld.contains(prescriptionsNewDrugPrescriptions)) {
                    Drug oldDrugOfPrescriptionsNewDrugPrescriptions = prescriptionsNewDrugPrescriptions.getDrug();
                    prescriptionsNewDrugPrescriptions.setDrug(drug);
                    prescriptionsNewDrugPrescriptions = em.merge(prescriptionsNewDrugPrescriptions);
                    if (oldDrugOfPrescriptionsNewDrugPrescriptions != null && !oldDrugOfPrescriptionsNewDrugPrescriptions.equals(drug)) {
                        oldDrugOfPrescriptionsNewDrugPrescriptions.getPrescriptions().remove(prescriptionsNewDrugPrescriptions);
                        oldDrugOfPrescriptionsNewDrugPrescriptions = em.merge(oldDrugOfPrescriptionsNewDrugPrescriptions);
                    }
                }
            }
            em.getTransaction().commit();
            return drug;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = drug.getId();
                if (findDrug(id) == null) {
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
            Drug drug;
            try {
                drug = em.getReference(Drug.class, id);
                drug.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The drug with id " + id + " no longer exists.", enfe);
            }
            List<DrugPrescriptions> prescriptions = drug.getPrescriptions();
            for (DrugPrescriptions prescriptionsDrugPrescriptions : prescriptions) {
                prescriptionsDrugPrescriptions.setDrug(null);
                prescriptionsDrugPrescriptions = em.merge(prescriptionsDrugPrescriptions);
            }
            em.remove(drug);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Drug> findDrugEntities() {
        return findDrugEntities(true, -1, -1);
    }

    public List<Drug> findDrugEntities(int maxResults, int firstResult) {
        return findDrugEntities(false, maxResults, firstResult);
    }

    private List<Drug> findDrugEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Drug.class));
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

    public Drug findDrug(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Drug.class, id);
        } finally {
            em.close();
        }
    }

    public int getDrugCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Drug> rt = cq.from(Drug.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
