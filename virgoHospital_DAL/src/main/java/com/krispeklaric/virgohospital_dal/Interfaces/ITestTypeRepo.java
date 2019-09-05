/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.TestType;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface ITestTypeRepo {

    public TestType create(TestType testType);

    public TestType edit(TestType testType) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<TestType> findTestTypeEntities();

    public List<TestType> findTestTypeEntities(int maxResults, int firstResult);

    public TestType findTestType(Long id);

    public int getTestTypeCount();
}
