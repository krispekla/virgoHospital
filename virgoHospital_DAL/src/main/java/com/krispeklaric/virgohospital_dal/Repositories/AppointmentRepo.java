package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.IAppointmentRepo;
import com.krispeklaric.virgohospital_dal.Models.Appointment;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.Doctor;
import com.krispeklaric.virgohospital_dal.Models.Bill;
import com.krispeklaric.virgohospital_dal.Models.Test;
import java.util.ArrayList;
import java.util.List;
import com.krispeklaric.virgohospital_dal.Models.Prescription;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class AppointmentRepo extends BaseRepo implements IAppointmentRepo, Serializable {

    public AppointmentRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Appointment create(Appointment appointment) {
        if (appointment.getTests() == null) {
            appointment.setTests(new ArrayList<Test>());
        }
        if (appointment.getPrescriptions() == null) {
            appointment.setPrescriptions(new ArrayList<Prescription>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Doctor doctor = appointment.getDoctor();
            if (doctor != null) {
                doctor = em.getReference(doctor.getClass(), doctor.getId());
                appointment.setDoctor(doctor);
            }
            Bill bill = appointment.getBill();
            if (bill != null) {
                bill = em.getReference(bill.getClass(), bill.getId());
                appointment.setBill(bill);
            }
            List<Test> attachedTests = new ArrayList<Test>();
            for (Test testsTestToAttach : appointment.getTests()) {
                testsTestToAttach = em.getReference(testsTestToAttach.getClass(), testsTestToAttach.getId());
                attachedTests.add(testsTestToAttach);
            }
            appointment.setTests(attachedTests);
            List<Prescription> attachedPrescriptions = new ArrayList<Prescription>();
            for (Prescription prescriptionsPrescriptionToAttach : appointment.getPrescriptions()) {
                prescriptionsPrescriptionToAttach = em.getReference(prescriptionsPrescriptionToAttach.getClass(), prescriptionsPrescriptionToAttach.getId());
                attachedPrescriptions.add(prescriptionsPrescriptionToAttach);
            }
            appointment.setPrescriptions(attachedPrescriptions);
            em.persist(appointment);
            if (doctor != null) {
                doctor.getAppointments().add(appointment);
                doctor = em.merge(doctor);
            }
            if (bill != null) {
                Appointment oldAppointmentOfBill = bill.getAppointment();
                if (oldAppointmentOfBill != null) {
                    oldAppointmentOfBill.setBill(null);
                    oldAppointmentOfBill = em.merge(oldAppointmentOfBill);
                }
                bill.setAppointment(appointment);
                bill = em.merge(bill);
            }
            for (Test testsTest : appointment.getTests()) {
                Appointment oldAppointmentOfTestsTest = testsTest.getAppointment();
                testsTest.setAppointment(appointment);
                testsTest = em.merge(testsTest);
                if (oldAppointmentOfTestsTest != null) {
                    oldAppointmentOfTestsTest.getTests().remove(testsTest);
                    oldAppointmentOfTestsTest = em.merge(oldAppointmentOfTestsTest);
                }
            }
            for (Prescription prescriptionsPrescription : appointment.getPrescriptions()) {
                Appointment oldAppointmentOfPrescriptionsPrescription = prescriptionsPrescription.getAppointment();
                prescriptionsPrescription.setAppointment(appointment);
                prescriptionsPrescription = em.merge(prescriptionsPrescription);
                if (oldAppointmentOfPrescriptionsPrescription != null) {
                    oldAppointmentOfPrescriptionsPrescription.getPrescriptions().remove(prescriptionsPrescription);
                    oldAppointmentOfPrescriptionsPrescription = em.merge(oldAppointmentOfPrescriptionsPrescription);
                }
            }
            em.getTransaction().commit();
            return appointment;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Appointment edit(Appointment appointment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointment persistentAppointment = em.find(Appointment.class, appointment.getId());
            Doctor doctorOld = persistentAppointment.getDoctor();
            Doctor doctorNew = appointment.getDoctor();
            Bill billOld = persistentAppointment.getBill();
            Bill billNew = appointment.getBill();
            List<Test> testsOld = persistentAppointment.getTests();
            List<Test> testsNew = appointment.getTests();
            List<Prescription> prescriptionsOld = persistentAppointment.getPrescriptions();
            List<Prescription> prescriptionsNew = appointment.getPrescriptions();
            if (doctorNew != null) {
                doctorNew = em.getReference(doctorNew.getClass(), doctorNew.getId());
                appointment.setDoctor(doctorNew);
            }
            if (billNew != null) {
                billNew = em.getReference(billNew.getClass(), billNew.getId());
                appointment.setBill(billNew);
            }
            List<Test> attachedTestsNew = new ArrayList<Test>();
            for (Test testsNewTestToAttach : testsNew) {
                testsNewTestToAttach = em.getReference(testsNewTestToAttach.getClass(), testsNewTestToAttach.getId());
                attachedTestsNew.add(testsNewTestToAttach);
            }
            testsNew = attachedTestsNew;
            appointment.setTests(testsNew);
            List<Prescription> attachedPrescriptionsNew = new ArrayList<Prescription>();
            for (Prescription prescriptionsNewPrescriptionToAttach : prescriptionsNew) {
                prescriptionsNewPrescriptionToAttach = em.getReference(prescriptionsNewPrescriptionToAttach.getClass(), prescriptionsNewPrescriptionToAttach.getId());
                attachedPrescriptionsNew.add(prescriptionsNewPrescriptionToAttach);
            }
            prescriptionsNew = attachedPrescriptionsNew;
            appointment.setPrescriptions(prescriptionsNew);
            appointment = em.merge(appointment);
            if (doctorOld != null && !doctorOld.equals(doctorNew)) {
                doctorOld.getAppointments().remove(appointment);
                doctorOld = em.merge(doctorOld);
            }
            if (doctorNew != null && !doctorNew.equals(doctorOld)) {
                doctorNew.getAppointments().add(appointment);
                doctorNew = em.merge(doctorNew);
            }
            if (billOld != null && !billOld.equals(billNew)) {
                billOld.setAppointment(null);
                billOld = em.merge(billOld);
            }
            if (billNew != null && !billNew.equals(billOld)) {
                Appointment oldAppointmentOfBill = billNew.getAppointment();
                if (oldAppointmentOfBill != null) {
                    oldAppointmentOfBill.setBill(null);
                    oldAppointmentOfBill = em.merge(oldAppointmentOfBill);
                }
                billNew.setAppointment(appointment);
                billNew = em.merge(billNew);
            }
            for (Test testsOldTest : testsOld) {
                if (!testsNew.contains(testsOldTest)) {
                    testsOldTest.setAppointment(null);
                    testsOldTest = em.merge(testsOldTest);
                }
            }
            for (Test testsNewTest : testsNew) {
                if (!testsOld.contains(testsNewTest)) {
                    Appointment oldAppointmentOfTestsNewTest = testsNewTest.getAppointment();
                    testsNewTest.setAppointment(appointment);
                    testsNewTest = em.merge(testsNewTest);
                    if (oldAppointmentOfTestsNewTest != null && !oldAppointmentOfTestsNewTest.equals(appointment)) {
                        oldAppointmentOfTestsNewTest.getTests().remove(testsNewTest);
                        oldAppointmentOfTestsNewTest = em.merge(oldAppointmentOfTestsNewTest);
                    }
                }
            }
            for (Prescription prescriptionsOldPrescription : prescriptionsOld) {
                if (!prescriptionsNew.contains(prescriptionsOldPrescription)) {
                    prescriptionsOldPrescription.setAppointment(null);
                    prescriptionsOldPrescription = em.merge(prescriptionsOldPrescription);
                }
            }
            for (Prescription prescriptionsNewPrescription : prescriptionsNew) {
                if (!prescriptionsOld.contains(prescriptionsNewPrescription)) {
                    Appointment oldAppointmentOfPrescriptionsNewPrescription = prescriptionsNewPrescription.getAppointment();
                    prescriptionsNewPrescription.setAppointment(appointment);
                    prescriptionsNewPrescription = em.merge(prescriptionsNewPrescription);
                    if (oldAppointmentOfPrescriptionsNewPrescription != null && !oldAppointmentOfPrescriptionsNewPrescription.equals(appointment)) {
                        oldAppointmentOfPrescriptionsNewPrescription.getPrescriptions().remove(prescriptionsNewPrescription);
                        oldAppointmentOfPrescriptionsNewPrescription = em.merge(oldAppointmentOfPrescriptionsNewPrescription);
                    }
                }
            }
            em.getTransaction().commit();
            return appointment;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = appointment.getId();
                if (findAppointment(id) == null) {
                    throw new NonexistentEntityException("The appointment with id " + id + " no longer exists.");
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
            Appointment appointment;
            try {
                appointment = em.getReference(Appointment.class, id);
                appointment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The appointment with id " + id + " no longer exists.", enfe);
            }
            Doctor doctor = appointment.getDoctor();
            if (doctor != null) {
                doctor.getAppointments().remove(appointment);
                doctor = em.merge(doctor);
            }
            Bill bill = appointment.getBill();
            if (bill != null) {
                bill.setAppointment(null);
                bill = em.merge(bill);
            }
            List<Test> tests = appointment.getTests();
            for (Test testsTest : tests) {
                testsTest.setAppointment(null);
                testsTest = em.merge(testsTest);
            }
            List<Prescription> prescriptions = appointment.getPrescriptions();
            for (Prescription prescriptionsPrescription : prescriptions) {
                prescriptionsPrescription.setAppointment(null);
                prescriptionsPrescription = em.merge(prescriptionsPrescription);
            }
            em.remove(appointment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Appointment> findAppointmentEntities() {
        return findAppointmentEntities(true, -1, -1);
    }

    public List<Appointment> findAppointmentEntities(int maxResults, int firstResult) {
        return findAppointmentEntities(false, maxResults, firstResult);
    }

    private List<Appointment> findAppointmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Appointment.class));
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

    public Appointment findAppointment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Appointment.class, id);
        } finally {
            em.close();
        }
    }

    public int getAppointmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Appointment> rt = cq.from(Appointment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
