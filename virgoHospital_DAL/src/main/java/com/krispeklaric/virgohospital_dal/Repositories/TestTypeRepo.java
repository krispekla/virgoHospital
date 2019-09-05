package com.krispeklaric.virgohospital_dal.Repositories;

import com.krispeklaric.virgohospital_dal.Interfaces.ITestTypeRepo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.krispeklaric.virgohospital_dal.Models.Test;
import com.krispeklaric.virgohospital_dal.Models.TestType;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kris
 */
public class TestTypeRepo extends BaseRepo implements ITestTypeRepo, Serializable {

    public TestTypeRepo() {
        super();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TestType create(TestType testType) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Test test = testType.getTest();
            if (test != null) {
                test = em.getReference(test.getClass(), test.getId());
                testType.setTest(test);
            }
            em.persist(testType);
            if (test != null) {
                TestType oldTestTypeOfTest = test.getTestType();
                if (oldTestTypeOfTest != null) {
                    oldTestTypeOfTest.setTest(null);
                    oldTestTypeOfTest = em.merge(oldTestTypeOfTest);
                }
                test.setTestType(testType);
                test = em.merge(test);
            }
            em.getTransaction().commit();
            return testType;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public TestType edit(TestType testType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TestType persistentTestType = em.find(TestType.class, testType.getId());
            Test testOld = persistentTestType.getTest();
            Test testNew = testType.getTest();
            if (testNew != null) {
                testNew = em.getReference(testNew.getClass(), testNew.getId());
                testType.setTest(testNew);
            }
            testType = em.merge(testType);
            if (testOld != null && !testOld.equals(testNew)) {
                testOld.setTestType(null);
                testOld = em.merge(testOld);
            }
            if (testNew != null && !testNew.equals(testOld)) {
                TestType oldTestTypeOfTest = testNew.getTestType();
                if (oldTestTypeOfTest != null) {
                    oldTestTypeOfTest.setTest(null);
                    oldTestTypeOfTest = em.merge(oldTestTypeOfTest);
                }
                testNew.setTestType(testType);
                testNew = em.merge(testNew);
            }
            em.getTransaction().commit();
            return testType;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = testType.getId();
                if (findTestType(id) == null) {
                    throw new NonexistentEntityException("The testType with id " + id + " no longer exists.");
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
            TestType testType;
            try {
                testType = em.getReference(TestType.class, id);
                testType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The testType with id " + id + " no longer exists.", enfe);
            }
            Test test = testType.getTest();
            if (test != null) {
                test.setTestType(null);
                test = em.merge(test);
            }
            em.remove(testType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TestType> findTestTypeEntities() {
        return findTestTypeEntities(true, -1, -1);
    }

    public List<TestType> findTestTypeEntities(int maxResults, int firstResult) {
        return findTestTypeEntities(false, maxResults, firstResult);
    }

    private List<TestType> findTestTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TestType.class));
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

    public TestType findTestType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TestType.class, id);
        } finally {
            em.close();
        }
    }

    public int getTestTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TestType> rt = cq.from(TestType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
