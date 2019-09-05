package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IDoctorRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import com.krispeklaric.virgohospital_dal.Models.DoctorSchedule;
import java.util.ArrayList;
import java.util.List;
import com.krispeklaric.virgohospital_dal.Models.Appointment;
import com.krispeklaric.virgohospital_dal.Models.Doctor;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class DoctorRepo extends BaseRepo implements IDoctorRepo, Serializable {

    public DoctorRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Doctor create(Doctor doctor) {
        if (doctor.getSchedule() == null) {
            doctor.setSchedule(new ArrayList<DoctorSchedule>());
        }
        if (doctor.getAppointments() == null) {
            doctor.setAppointments(new ArrayList<Appointment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactDetail contactDetail = doctor.getContactDetail();
            if (contactDetail != null) {
                contactDetail = em.getReference(contactDetail.getClass(), contactDetail.getId());
                doctor.setContactDetail(contactDetail);
            }
            List<DoctorSchedule> attachedSchedule = new ArrayList<DoctorSchedule>();
            for (DoctorSchedule scheduleDoctorScheduleToAttach : doctor.getSchedule()) {
                scheduleDoctorScheduleToAttach = em.getReference(scheduleDoctorScheduleToAttach.getClass(), scheduleDoctorScheduleToAttach.getId());
                attachedSchedule.add(scheduleDoctorScheduleToAttach);
            }
            doctor.setSchedule(attachedSchedule);
            List<Appointment> attachedAppointments = new ArrayList<Appointment>();
            for (Appointment appointmentsAppointmentToAttach : doctor.getAppointments()) {
                appointmentsAppointmentToAttach = em.getReference(appointmentsAppointmentToAttach.getClass(), appointmentsAppointmentToAttach.getId());
                attachedAppointments.add(appointmentsAppointmentToAttach);
            }
            doctor.setAppointments(attachedAppointments);
            em.persist(doctor);
            if (contactDetail != null) {
                Doctor oldDoctorOfContactDetail = contactDetail.getDoctor();
                if (oldDoctorOfContactDetail != null) {
                    oldDoctorOfContactDetail.setContactDetail(null);
                    oldDoctorOfContactDetail = em.merge(oldDoctorOfContactDetail);
                }
                contactDetail.setDoctor(doctor);
                contactDetail = em.merge(contactDetail);
            }
            for (DoctorSchedule scheduleDoctorSchedule : doctor.getSchedule()) {
                Doctor oldDoctorOfScheduleDoctorSchedule = scheduleDoctorSchedule.getDoctor();
                scheduleDoctorSchedule.setDoctor(doctor);
                scheduleDoctorSchedule = em.merge(scheduleDoctorSchedule);
                if (oldDoctorOfScheduleDoctorSchedule != null) {
                    oldDoctorOfScheduleDoctorSchedule.getSchedule().remove(scheduleDoctorSchedule);
                    oldDoctorOfScheduleDoctorSchedule = em.merge(oldDoctorOfScheduleDoctorSchedule);
                }
            }
            for (Appointment appointmentsAppointment : doctor.getAppointments()) {
                Doctor oldDoctorOfAppointmentsAppointment = appointmentsAppointment.getDoctor();
                appointmentsAppointment.setDoctor(doctor);
                appointmentsAppointment = em.merge(appointmentsAppointment);
                if (oldDoctorOfAppointmentsAppointment != null) {
                    oldDoctorOfAppointmentsAppointment.getAppointments().remove(appointmentsAppointment);
                    oldDoctorOfAppointmentsAppointment = em.merge(oldDoctorOfAppointmentsAppointment);
                }
            }
            em.getTransaction().commit();
            return doctor;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Doctor edit(Doctor doctor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Doctor persistentDoctor = em.find(Doctor.class, doctor.getId());
            ContactDetail contactDetailOld = persistentDoctor.getContactDetail();
            ContactDetail contactDetailNew = doctor.getContactDetail();
            List<DoctorSchedule> scheduleOld = persistentDoctor.getSchedule();
            List<DoctorSchedule> scheduleNew = doctor.getSchedule();
            List<Appointment> appointmentsOld = persistentDoctor.getAppointments();
            List<Appointment> appointmentsNew = doctor.getAppointments();
            if (contactDetailNew != null) {
                contactDetailNew = em.getReference(contactDetailNew.getClass(), contactDetailNew.getId());
                doctor.setContactDetail(contactDetailNew);
            }
            List<DoctorSchedule> attachedScheduleNew = new ArrayList<DoctorSchedule>();
            for (DoctorSchedule scheduleNewDoctorScheduleToAttach : scheduleNew) {
                scheduleNewDoctorScheduleToAttach = em.getReference(scheduleNewDoctorScheduleToAttach.getClass(), scheduleNewDoctorScheduleToAttach.getId());
                attachedScheduleNew.add(scheduleNewDoctorScheduleToAttach);
            }
            scheduleNew = attachedScheduleNew;
            doctor.setSchedule(scheduleNew);
            List<Appointment> attachedAppointmentsNew = new ArrayList<Appointment>();
            for (Appointment appointmentsNewAppointmentToAttach : appointmentsNew) {
                appointmentsNewAppointmentToAttach = em.getReference(appointmentsNewAppointmentToAttach.getClass(), appointmentsNewAppointmentToAttach.getId());
                attachedAppointmentsNew.add(appointmentsNewAppointmentToAttach);
            }
            appointmentsNew = attachedAppointmentsNew;
            doctor.setAppointments(appointmentsNew);
            doctor = em.merge(doctor);
            if (contactDetailOld != null && !contactDetailOld.equals(contactDetailNew)) {
                contactDetailOld.setDoctor(null);
                contactDetailOld = em.merge(contactDetailOld);
            }
            if (contactDetailNew != null && !contactDetailNew.equals(contactDetailOld)) {
                Doctor oldDoctorOfContactDetail = contactDetailNew.getDoctor();
                if (oldDoctorOfContactDetail != null) {
                    oldDoctorOfContactDetail.setContactDetail(null);
                    oldDoctorOfContactDetail = em.merge(oldDoctorOfContactDetail);
                }
                contactDetailNew.setDoctor(doctor);
                contactDetailNew = em.merge(contactDetailNew);
            }
            for (DoctorSchedule scheduleOldDoctorSchedule : scheduleOld) {
                if (!scheduleNew.contains(scheduleOldDoctorSchedule)) {
                    scheduleOldDoctorSchedule.setDoctor(null);
                    scheduleOldDoctorSchedule = em.merge(scheduleOldDoctorSchedule);
                }
            }
            for (DoctorSchedule scheduleNewDoctorSchedule : scheduleNew) {
                if (!scheduleOld.contains(scheduleNewDoctorSchedule)) {
                    Doctor oldDoctorOfScheduleNewDoctorSchedule = scheduleNewDoctorSchedule.getDoctor();
                    scheduleNewDoctorSchedule.setDoctor(doctor);
                    scheduleNewDoctorSchedule = em.merge(scheduleNewDoctorSchedule);
                    if (oldDoctorOfScheduleNewDoctorSchedule != null && !oldDoctorOfScheduleNewDoctorSchedule.equals(doctor)) {
                        oldDoctorOfScheduleNewDoctorSchedule.getSchedule().remove(scheduleNewDoctorSchedule);
                        oldDoctorOfScheduleNewDoctorSchedule = em.merge(oldDoctorOfScheduleNewDoctorSchedule);
                    }
                }
            }
            for (Appointment appointmentsOldAppointment : appointmentsOld) {
                if (!appointmentsNew.contains(appointmentsOldAppointment)) {
                    appointmentsOldAppointment.setDoctor(null);
                    appointmentsOldAppointment = em.merge(appointmentsOldAppointment);
                }
            }
            for (Appointment appointmentsNewAppointment : appointmentsNew) {
                if (!appointmentsOld.contains(appointmentsNewAppointment)) {
                    Doctor oldDoctorOfAppointmentsNewAppointment = appointmentsNewAppointment.getDoctor();
                    appointmentsNewAppointment.setDoctor(doctor);
                    appointmentsNewAppointment = em.merge(appointmentsNewAppointment);
                    if (oldDoctorOfAppointmentsNewAppointment != null && !oldDoctorOfAppointmentsNewAppointment.equals(doctor)) {
                        oldDoctorOfAppointmentsNewAppointment.getAppointments().remove(appointmentsNewAppointment);
                        oldDoctorOfAppointmentsNewAppointment = em.merge(oldDoctorOfAppointmentsNewAppointment);
                    }
                }
            }
            em.getTransaction().commit();
            return doctor;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = doctor.getId();
                if (findDoctor(id) == null) {
                    throw new NonexistentEntityException("The doctor with id " + id + " no longer exists.");
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
            Doctor doctor;
            try {
                doctor = em.getReference(Doctor.class, id);
                doctor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The doctor with id " + id + " no longer exists.", enfe);
            }
            ContactDetail contactDetail = doctor.getContactDetail();
            if (contactDetail != null) {
                contactDetail.setDoctor(null);
                contactDetail = em.merge(contactDetail);
            }
            List<DoctorSchedule> schedule = doctor.getSchedule();
            for (DoctorSchedule scheduleDoctorSchedule : schedule) {
                scheduleDoctorSchedule.setDoctor(null);
                scheduleDoctorSchedule = em.merge(scheduleDoctorSchedule);
            }
            List<Appointment> appointments = doctor.getAppointments();
            for (Appointment appointmentsAppointment : appointments) {
                appointmentsAppointment.setDoctor(null);
                appointmentsAppointment = em.merge(appointmentsAppointment);
            }
            em.remove(doctor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Doctor> findDoctorEntities() {
        return findDoctorEntities(true, -1, -1);
    }

    public List<Doctor> findDoctorEntities(int maxResults, int firstResult) {
        return findDoctorEntities(false, maxResults, firstResult);
    }

    private List<Doctor> findDoctorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Doctor.class));
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

    public Doctor findDoctor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Doctor.class, id);
        } finally {
            em.close();
        }
    }

    public int getDoctorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Doctor> rt = cq.from(Doctor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
