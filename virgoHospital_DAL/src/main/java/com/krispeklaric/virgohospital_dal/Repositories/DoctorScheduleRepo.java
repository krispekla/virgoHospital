package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IDoctorScheduleRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.Doctor;
import com.krispeklaric.virgohospital_dal.Models.DoctorSchedule;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class DoctorScheduleRepo extends BaseRepo implements IDoctorScheduleRepo, Serializable {

    public DoctorScheduleRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DoctorSchedule doctorSchedule) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Doctor doctor = doctorSchedule.getDoctor();
            if (doctor != null) {
                doctor = em.getReference(doctor.getClass(), doctor.getId());
                doctorSchedule.setDoctor(doctor);
            }
            em.persist(doctorSchedule);
            if (doctor != null) {
                doctor.getSchedule().add(doctorSchedule);
                doctor = em.merge(doctor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DoctorSchedule doctorSchedule) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DoctorSchedule persistentDoctorSchedule = em.find(DoctorSchedule.class, doctorSchedule.getId());
            Doctor doctorOld = persistentDoctorSchedule.getDoctor();
            Doctor doctorNew = doctorSchedule.getDoctor();
            if (doctorNew != null) {
                doctorNew = em.getReference(doctorNew.getClass(), doctorNew.getId());
                doctorSchedule.setDoctor(doctorNew);
            }
            doctorSchedule = em.merge(doctorSchedule);
            if (doctorOld != null && !doctorOld.equals(doctorNew)) {
                doctorOld.getSchedule().remove(doctorSchedule);
                doctorOld = em.merge(doctorOld);
            }
            if (doctorNew != null && !doctorNew.equals(doctorOld)) {
                doctorNew.getSchedule().add(doctorSchedule);
                doctorNew = em.merge(doctorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = doctorSchedule.getId();
                if (findDoctorSchedule(id) == null) {
                    throw new NonexistentEntityException("The doctorSchedule with id " + id + " no longer exists.");
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
            DoctorSchedule doctorSchedule;
            try {
                doctorSchedule = em.getReference(DoctorSchedule.class, id);
                doctorSchedule.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The doctorSchedule with id " + id + " no longer exists.", enfe);
            }
            Doctor doctor = doctorSchedule.getDoctor();
            if (doctor != null) {
                doctor.getSchedule().remove(doctorSchedule);
                doctor = em.merge(doctor);
            }
            em.remove(doctorSchedule);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DoctorSchedule> findDoctorScheduleEntities() {
        return findDoctorScheduleEntities(true, -1, -1);
    }

    public List<DoctorSchedule> findDoctorScheduleEntities(int maxResults, int firstResult) {
        return findDoctorScheduleEntities(false, maxResults, firstResult);
    }

    private List<DoctorSchedule> findDoctorScheduleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DoctorSchedule.class));
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

    public DoctorSchedule findDoctorSchedule(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DoctorSchedule.class, id);
        } finally {
            em.close();
        }
    }

    public int getDoctorScheduleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DoctorSchedule> rt = cq.from(DoctorSchedule.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
