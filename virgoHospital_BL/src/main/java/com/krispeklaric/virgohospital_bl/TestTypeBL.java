/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.test_type.GetSingleTestTypeResult;
import com.krispeklaric.virgohospital_bl.Messages.test_type.GetTestTypeResult;
import com.krispeklaric.virgohospital_bl.Messages.test_type.InsertTestTypeResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.ITestRepo;
import com.krispeklaric.virgohospital_dal.Interfaces.ITestTypeRepo;
import com.krispeklaric.virgohospital_dal.Models.TestType;
import java.util.List;

/**
 *
 * @author Kris
 */
public class TestTypeBL {

    private ITestTypeRepo repo;

    public TestTypeBL() {
        this.repo = RepoFactory.getTestTypeRepo();
    }

    public InsertTestTypeResult insertContactDetail(TestType p) {
        InsertTestTypeResult result = new InsertTestTypeResult();
        try {
            result.testType = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertTestTypeResult updateContactDetail(TestType p) {
        InsertTestTypeResult result = new InsertTestTypeResult();

        try {
            result.testType = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertTestTypeResult deleteContactDetail(Long id) {
        InsertTestTypeResult result = new InsertTestTypeResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetTestTypeResult getAll() {
        GetTestTypeResult result = new GetTestTypeResult();
        try {
            List<TestType> temp = repo.findTestTypeEntities();
            result.testType = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleTestTypeResult getById(Long id) {
        GetSingleTestTypeResult result = new GetSingleTestTypeResult();
        try {
            TestType patient = repo.findTestType(id);

            result.testType = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
