/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Factory;

import com.krispeklaric.virgohospital_dal.Factory.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import com.krispeklaric.virgohospital_dal.Models.NextOfKin;
import com.krispeklaric.virgohospital_dal.Models.PersonalDetail;
import com.krispeklaric.virgohospital_dal.Models.PatientLifestyle;
import com.krispeklaric.virgohospital_dal.Models.BasicComplaint;
import com.krispeklaric.virgohospital_dal.Models.MedicalComplaint;
import com.krispeklaric.virgohospital_dal.Models.Doctor;
import com.krispeklaric.virgohospital_dal.Models.Appointment;
import com.krispeklaric.virgohospital_dal.Models.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Kris
 */
public class PatientJpaController implements Serializable {

    public PatientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Patient patient) {
        if (patient.getAppointments() == null) {
            patient.setAppointments(new ArrayList<Appointment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactDetail contactDetail = patient.getContactDetail();
            if (contactDetail != null) {
                contactDetail = em.getReference(contactDetail.getClass(), contactDetail.getId());
                patient.setContactDetail(contactDetail);
            }
            NextOfKin nextOfKin = patient.getNextOfKin();
            if (nextOfKin != null) {
                nextOfKin = em.getReference(nextOfKin.getClass(), nextOfKin.getId());
                patient.setNextOfKin(nextOfKin);
            }
            PersonalDetail personalDetail = patient.getPersonalDetail();
            if (personalDetail != null) {
                personalDetail = em.getReference(personalDetail.getClass(), personalDetail.getId());
                patient.setPersonalDetail(personalDetail);
            }
            PatientLifestyle patientLifestyle = patient.getPatientLifestyle();
            if (patientLifestyle != null) {
                patientLifestyle = em.getReference(patientLifestyle.getClass(), patientLifestyle.getId());
                patient.setPatientLifestyle(patientLifestyle);
            }
            BasicComplaint basicComplaints = patient.getBasicComplaints();
            if (basicComplaints != null) {
                basicComplaints = em.getReference(basicComplaints.getClass(), basicComplaints.getId());
                patient.setBasicComplaints(basicComplaints);
            }
            MedicalComplaint medicalComplaints = patient.getMedicalComplaints();
            if (medicalComplaints != null) {
                medicalComplaints = em.getReference(medicalComplaints.getClass(), medicalComplaints.getId());
                patient.setMedicalComplaints(medicalComplaints);
            }
            Doctor doctor = patient.getDoctor();
            if (doctor != null) {
                doctor = em.getReference(doctor.getClass(), doctor.getId());
                patient.setDoctor(doctor);
            }
            List<Appointment> attachedAppointments = new ArrayList<Appointment>();
            for (Appointment appointmentsAppointmentToAttach : patient.getAppointments()) {
                appointmentsAppointmentToAttach = em.getReference(appointmentsAppointmentToAttach.getClass(), appointmentsAppointmentToAttach.getId());
                attachedAppointments.add(appointmentsAppointmentToAttach);
            }
            patient.setAppointments(attachedAppointments);
            em.persist(patient);
            if (contactDetail != null) {
                Patient oldPatientOfContactDetail = contactDetail.getPatient();
                if (oldPatientOfContactDetail != null) {
                    oldPatientOfContactDetail.setContactDetail(null);
                    oldPatientOfContactDetail = em.merge(oldPatientOfContactDetail);
                }
                contactDetail.setPatient(patient);
                contactDetail = em.merge(contactDetail);
            }
            if (nextOfKin != null) {
                Patient oldPatientOfNextOfKin = nextOfKin.getPatient();
                if (oldPatientOfNextOfKin != null) {
                    oldPatientOfNextOfKin.setNextOfKin(null);
                    oldPatientOfNextOfKin = em.merge(oldPatientOfNextOfKin);
                }
                nextOfKin.setPatient(patient);
                nextOfKin = em.merge(nextOfKin);
            }
            if (personalDetail != null) {
                Patient oldPatientOfPersonalDetail = personalDetail.getPatient();
                if (oldPatientOfPersonalDetail != null) {
                    oldPatientOfPersonalDetail.setPersonalDetail(null);
                    oldPatientOfPersonalDetail = em.merge(oldPatientOfPersonalDetail);
                }
                personalDetail.setPatient(patient);
                personalDetail = em.merge(personalDetail);
            }
            if (patientLifestyle != null) {
                Patient oldPatientOfPatientLifestyle = patientLifestyle.getPatient();
                if (oldPatientOfPatientLifestyle != null) {
                    oldPatientOfPatientLifestyle.setPatientLifestyle(null);
                    oldPatientOfPatientLifestyle = em.merge(oldPatientOfPatientLifestyle);
                }
                patientLifestyle.setPatient(patient);
                patientLifestyle = em.merge(patientLifestyle);
            }
            if (basicComplaints != null) {
                Patient oldPatientOfBasicComplaints = basicComplaints.getPatient();
                if (oldPatientOfBasicComplaints != null) {
                    oldPatientOfBasicComplaints.setBasicComplaints(null);
                    oldPatientOfBasicComplaints = em.merge(oldPatientOfBasicComplaints);
                }
                basicComplaints.setPatient(patient);
                basicComplaints = em.merge(basicComplaints);
            }
            if (medicalComplaints != null) {
                Patient oldPatientOfMedicalComplaints = medicalComplaints.getPatient();
                if (oldPatientOfMedicalComplaints != null) {
                    oldPatientOfMedicalComplaints.setMedicalComplaints(null);
                    oldPatientOfMedicalComplaints = em.merge(oldPatientOfMedicalComplaints);
                }
                medicalComplaints.setPatient(patient);
                medicalComplaints = em.merge(medicalComplaints);
            }
            if (doctor != null) {
                doctor.getPatient().add(patient);
                doctor = em.merge(doctor);
            }
            for (Appointment appointmentsAppointment : patient.getAppointments()) {
                Patient oldPatientOfAppointmentsAppointment = appointmentsAppointment.getPatient();
                appointmentsAppointment.setPatient(patient);
                appointmentsAppointment = em.merge(appointmentsAppointment);
                if (oldPatientOfAppointmentsAppointment != null) {
                    oldPatientOfAppointmentsAppointment.getAppointments().remove(appointmentsAppointment);
                    oldPatientOfAppointmentsAppointment = em.merge(oldPatientOfAppointmentsAppointment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Patient patient) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Patient persistentPatient = em.find(Patient.class, patient.getId());
            ContactDetail contactDetailOld = persistentPatient.getContactDetail();
            ContactDetail contactDetailNew = patient.getContactDetail();
            NextOfKin nextOfKinOld = persistentPatient.getNextOfKin();
            NextOfKin nextOfKinNew = patient.getNextOfKin();
            PersonalDetail personalDetailOld = persistentPatient.getPersonalDetail();
            PersonalDetail personalDetailNew = patient.getPersonalDetail();
            PatientLifestyle patientLifestyleOld = persistentPatient.getPatientLifestyle();
            PatientLifestyle patientLifestyleNew = patient.getPatientLifestyle();
            BasicComplaint basicComplaintsOld = persistentPatient.getBasicComplaints();
            BasicComplaint basicComplaintsNew = patient.getBasicComplaints();
            MedicalComplaint medicalComplaintsOld = persistentPatient.getMedicalComplaints();
            MedicalComplaint medicalComplaintsNew = patient.getMedicalComplaints();
            Doctor doctorOld = persistentPatient.getDoctor();
            Doctor doctorNew = patient.getDoctor();
            List<Appointment> appointmentsOld = persistentPatient.getAppointments();
            List<Appointment> appointmentsNew = patient.getAppointments();
            if (contactDetailNew != null) {
                contactDetailNew = em.getReference(contactDetailNew.getClass(), contactDetailNew.getId());
                patient.setContactDetail(contactDetailNew);
            }
            if (nextOfKinNew != null) {
                nextOfKinNew = em.getReference(nextOfKinNew.getClass(), nextOfKinNew.getId());
                patient.setNextOfKin(nextOfKinNew);
            }
            if (personalDetailNew != null) {
                personalDetailNew = em.getReference(personalDetailNew.getClass(), personalDetailNew.getId());
                patient.setPersonalDetail(personalDetailNew);
            }
            if (patientLifestyleNew != null) {
                patientLifestyleNew = em.getReference(patientLifestyleNew.getClass(), patientLifestyleNew.getId());
                patient.setPatientLifestyle(patientLifestyleNew);
            }
            if (basicComplaintsNew != null) {
                basicComplaintsNew = em.getReference(basicComplaintsNew.getClass(), basicComplaintsNew.getId());
                patient.setBasicComplaints(basicComplaintsNew);
            }
            if (medicalComplaintsNew != null) {
                medicalComplaintsNew = em.getReference(medicalComplaintsNew.getClass(), medicalComplaintsNew.getId());
                patient.setMedicalComplaints(medicalComplaintsNew);
            }
            if (doctorNew != null) {
                doctorNew = em.getReference(doctorNew.getClass(), doctorNew.getId());
                patient.setDoctor(doctorNew);
            }
            List<Appointment> attachedAppointmentsNew = new ArrayList<Appointment>();
            for (Appointment appointmentsNewAppointmentToAttach : appointmentsNew) {
                appointmentsNewAppointmentToAttach = em.getReference(appointmentsNewAppointmentToAttach.getClass(), appointmentsNewAppointmentToAttach.getId());
                attachedAppointmentsNew.add(appointmentsNewAppointmentToAttach);
            }
            appointmentsNew = attachedAppointmentsNew;
            patient.setAppointments(appointmentsNew);
            patient = em.merge(patient);
            if (contactDetailOld != null && !contactDetailOld.equals(contactDetailNew)) {
                contactDetailOld.setPatient(null);
                contactDetailOld = em.merge(contactDetailOld);
            }
            if (contactDetailNew != null && !contactDetailNew.equals(contactDetailOld)) {
                Patient oldPatientOfContactDetail = contactDetailNew.getPatient();
                if (oldPatientOfContactDetail != null) {
                    oldPatientOfContactDetail.setContactDetail(null);
                    oldPatientOfContactDetail = em.merge(oldPatientOfContactDetail);
                }
                contactDetailNew.setPatient(patient);
                contactDetailNew = em.merge(contactDetailNew);
            }
            if (nextOfKinOld != null && !nextOfKinOld.equals(nextOfKinNew)) {
                nextOfKinOld.setPatient(null);
                nextOfKinOld = em.merge(nextOfKinOld);
            }
            if (nextOfKinNew != null && !nextOfKinNew.equals(nextOfKinOld)) {
                Patient oldPatientOfNextOfKin = nextOfKinNew.getPatient();
                if (oldPatientOfNextOfKin != null) {
                    oldPatientOfNextOfKin.setNextOfKin(null);
                    oldPatientOfNextOfKin = em.merge(oldPatientOfNextOfKin);
                }
                nextOfKinNew.setPatient(patient);
                nextOfKinNew = em.merge(nextOfKinNew);
            }
            if (personalDetailOld != null && !personalDetailOld.equals(personalDetailNew)) {
                personalDetailOld.setPatient(null);
                personalDetailOld = em.merge(personalDetailOld);
            }
            if (personalDetailNew != null && !personalDetailNew.equals(personalDetailOld)) {
                Patient oldPatientOfPersonalDetail = personalDetailNew.getPatient();
                if (oldPatientOfPersonalDetail != null) {
                    oldPatientOfPersonalDetail.setPersonalDetail(null);
                    oldPatientOfPersonalDetail = em.merge(oldPatientOfPersonalDetail);
                }
                personalDetailNew.setPatient(patient);
                personalDetailNew = em.merge(personalDetailNew);
            }
            if (patientLifestyleOld != null && !patientLifestyleOld.equals(patientLifestyleNew)) {
                patientLifestyleOld.setPatient(null);
                patientLifestyleOld = em.merge(patientLifestyleOld);
            }
            if (patientLifestyleNew != null && !patientLifestyleNew.equals(patientLifestyleOld)) {
                Patient oldPatientOfPatientLifestyle = patientLifestyleNew.getPatient();
                if (oldPatientOfPatientLifestyle != null) {
                    oldPatientOfPatientLifestyle.setPatientLifestyle(null);
                    oldPatientOfPatientLifestyle = em.merge(oldPatientOfPatientLifestyle);
                }
                patientLifestyleNew.setPatient(patient);
                patientLifestyleNew = em.merge(patientLifestyleNew);
            }
            if (basicComplaintsOld != null && !basicComplaintsOld.equals(basicComplaintsNew)) {
                basicComplaintsOld.setPatient(null);
                basicComplaintsOld = em.merge(basicComplaintsOld);
            }
            if (basicComplaintsNew != null && !basicComplaintsNew.equals(basicComplaintsOld)) {
                Patient oldPatientOfBasicComplaints = basicComplaintsNew.getPatient();
                if (oldPatientOfBasicComplaints != null) {
                    oldPatientOfBasicComplaints.setBasicComplaints(null);
                    oldPatientOfBasicComplaints = em.merge(oldPatientOfBasicComplaints);
                }
                basicComplaintsNew.setPatient(patient);
                basicComplaintsNew = em.merge(basicComplaintsNew);
            }
            if (medicalComplaintsOld != null && !medicalComplaintsOld.equals(medicalComplaintsNew)) {
                medicalComplaintsOld.setPatient(null);
                medicalComplaintsOld = em.merge(medicalComplaintsOld);
            }
            if (medicalComplaintsNew != null && !medicalComplaintsNew.equals(medicalComplaintsOld)) {
                Patient oldPatientOfMedicalComplaints = medicalComplaintsNew.getPatient();
                if (oldPatientOfMedicalComplaints != null) {
                    oldPatientOfMedicalComplaints.setMedicalComplaints(null);
                    oldPatientOfMedicalComplaints = em.merge(oldPatientOfMedicalComplaints);
                }
                medicalComplaintsNew.setPatient(patient);
                medicalComplaintsNew = em.merge(medicalComplaintsNew);
            }
            if (doctorOld != null && !doctorOld.equals(doctorNew)) {
                doctorOld.getPatient().remove(patient);
                doctorOld = em.merge(doctorOld);
            }
            if (doctorNew != null && !doctorNew.equals(doctorOld)) {
                doctorNew.getPatient().add(patient);
                doctorNew = em.merge(doctorNew);
            }
            for (Appointment appointmentsOldAppointment : appointmentsOld) {
                if (!appointmentsNew.contains(appointmentsOldAppointment)) {
                    appointmentsOldAppointment.setPatient(null);
                    appointmentsOldAppointment = em.merge(appointmentsOldAppointment);
                }
            }
            for (Appointment appointmentsNewAppointment : appointmentsNew) {
                if (!appointmentsOld.contains(appointmentsNewAppointment)) {
                    Patient oldPatientOfAppointmentsNewAppointment = appointmentsNewAppointment.getPatient();
                    appointmentsNewAppointment.setPatient(patient);
                    appointmentsNewAppointment = em.merge(appointmentsNewAppointment);
                    if (oldPatientOfAppointmentsNewAppointment != null && !oldPatientOfAppointmentsNewAppointment.equals(patient)) {
                        oldPatientOfAppointmentsNewAppointment.getAppointments().remove(appointmentsNewAppointment);
                        oldPatientOfAppointmentsNewAppointment = em.merge(oldPatientOfAppointmentsNewAppointment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = patient.getId();
                if (findPatient(id) == null) {
                    throw new NonexistentEntityException("The patient with id " + id + " no longer exists.");
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
            Patient patient;
            try {
                patient = em.getReference(Patient.class, id);
                patient.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The patient with id " + id + " no longer exists.", enfe);
            }
            ContactDetail contactDetail = patient.getContactDetail();
            if (contactDetail != null) {
                contactDetail.setPatient(null);
                contactDetail = em.merge(contactDetail);
            }
            NextOfKin nextOfKin = patient.getNextOfKin();
            if (nextOfKin != null) {
                nextOfKin.setPatient(null);
                nextOfKin = em.merge(nextOfKin);
            }
            PersonalDetail personalDetail = patient.getPersonalDetail();
            if (personalDetail != null) {
                personalDetail.setPatient(null);
                personalDetail = em.merge(personalDetail);
            }
            PatientLifestyle patientLifestyle = patient.getPatientLifestyle();
            if (patientLifestyle != null) {
                patientLifestyle.setPatient(null);
                patientLifestyle = em.merge(patientLifestyle);
            }
            BasicComplaint basicComplaints = patient.getBasicComplaints();
            if (basicComplaints != null) {
                basicComplaints.setPatient(null);
                basicComplaints = em.merge(basicComplaints);
            }
            MedicalComplaint medicalComplaints = patient.getMedicalComplaints();
            if (medicalComplaints != null) {
                medicalComplaints.setPatient(null);
                medicalComplaints = em.merge(medicalComplaints);
            }
            Doctor doctor = patient.getDoctor();
            if (doctor != null) {
                doctor.getPatient().remove(patient);
                doctor = em.merge(doctor);
            }
            List<Appointment> appointments = patient.getAppointments();
            for (Appointment appointmentsAppointment : appointments) {
                appointmentsAppointment.setPatient(null);
                appointmentsAppointment = em.merge(appointmentsAppointment);
            }
            em.remove(patient);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Patient> findPatientEntities() {
        return findPatientEntities(true, -1, -1);
    }

    public List<Patient> findPatientEntities(int maxResults, int firstResult) {
        return findPatientEntities(false, maxResults, firstResult);
    }

    private List<Patient> findPatientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Patient.class));
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

    public Patient findPatient(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Patient.class, id);
        } finally {
            em.close();
        }
    }

    public int getPatientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Patient> rt = cq.from(Patient.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
