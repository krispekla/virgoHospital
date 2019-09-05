package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.ITestRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.TestType;
import com.krispeklaric.virgohospital_dal.Models.Appointment;
import com.krispeklaric.virgohospital_dal.Models.Test;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class TestRepo extends BaseRepo implements ITestRepo, Serializable {

    public TestRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Test create(Test test) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TestType testType = test.getTestType();
            if (testType != null) {
                testType = em.getReference(testType.getClass(), testType.getId());
                test.setTestType(testType);
            }
            Appointment appointment = test.getAppointment();
            if (appointment != null) {
                appointment = em.getReference(appointment.getClass(), appointment.getId());
                test.setAppointment(appointment);
            }
            em.persist(test);
            if (testType != null) {
                Test oldTestOfTestType = testType.getTest();
                if (oldTestOfTestType != null) {
                    oldTestOfTestType.setTestType(null);
                    oldTestOfTestType = em.merge(oldTestOfTestType);
                }
                testType.setTest(test);
                testType = em.merge(testType);
            }
            if (appointment != null) {
                appointment.getTests().add(test);
                appointment = em.merge(appointment);
            }
            em.getTransaction().commit();
            return test;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Test edit(Test test) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Test persistentTest = em.find(Test.class, test.getId());
            TestType testTypeOld = persistentTest.getTestType();
            TestType testTypeNew = test.getTestType();
            Appointment appointmentOld = persistentTest.getAppointment();
            Appointment appointmentNew = test.getAppointment();
            if (testTypeNew != null) {
                testTypeNew = em.getReference(testTypeNew.getClass(), testTypeNew.getId());
                test.setTestType(testTypeNew);
            }
            if (appointmentNew != null) {
                appointmentNew = em.getReference(appointmentNew.getClass(), appointmentNew.getId());
                test.setAppointment(appointmentNew);
            }
            test = em.merge(test);
            if (testTypeOld != null && !testTypeOld.equals(testTypeNew)) {
                testTypeOld.setTest(null);
                testTypeOld = em.merge(testTypeOld);
            }
            if (testTypeNew != null && !testTypeNew.equals(testTypeOld)) {
                Test oldTestOfTestType = testTypeNew.getTest();
                if (oldTestOfTestType != null) {
                    oldTestOfTestType.setTestType(null);
                    oldTestOfTestType = em.merge(oldTestOfTestType);
                }
                testTypeNew.setTest(test);
                testTypeNew = em.merge(testTypeNew);
            }
            if (appointmentOld != null && !appointmentOld.equals(appointmentNew)) {
                appointmentOld.getTests().remove(test);
                appointmentOld = em.merge(appointmentOld);
            }
            if (appointmentNew != null && !appointmentNew.equals(appointmentOld)) {
                appointmentNew.getTests().add(test);
                appointmentNew = em.merge(appointmentNew);
            }
            em.getTransaction().commit();
            return test;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = test.getId();
                if (findTest(id) == null) {
                    throw new NonexistentEntityException("The test with id " + id + " no longer exists.");
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
            Test test;
            try {
                test = em.getReference(Test.class, id);
                test.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The test with id " + id + " no longer exists.", enfe);
            }
            TestType testType = test.getTestType();
            if (testType != null) {
                testType.setTest(null);
                testType = em.merge(testType);
            }
            Appointment appointment = test.getAppointment();
            if (appointment != null) {
                appointment.getTests().remove(test);
                appointment = em.merge(appointment);
            }
            em.remove(test);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Test> findTestEntities() {
        return findTestEntities(true, -1, -1);
    }

    public List<Test> findTestEntities(int maxResults, int firstResult) {
        return findTestEntities(false, maxResults, firstResult);
    }

    private List<Test> findTestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Test.class));
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

    public Test findTest(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Test.class, id);
        } finally {
            em.close();
        }
    }

    public int getTestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Test> rt = cq.from(Test.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
