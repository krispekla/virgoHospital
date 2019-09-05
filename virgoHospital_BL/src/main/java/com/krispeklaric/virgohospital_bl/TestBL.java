/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.test.GetSingleTestResult;
import com.krispeklaric.virgohospital_bl.Messages.test.GetTestResult;
import com.krispeklaric.virgohospital_bl.Messages.test.InsertTestResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.ITestRepo;
import com.krispeklaric.virgohospital_dal.Models.Test;
import java.util.List;

/**
 *
 * @author Kris
 */
public class TestBL {

    private ITestRepo repo;

    public TestBL() {
        this.repo = RepoFactory.getTestRepo();
    }

    public InsertTestResult insertContactDetail(Test p) {
        InsertTestResult result = new InsertTestResult();
        try {
            result.test = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertTestResult updateContactDetail(Test p) {
        InsertTestResult result = new InsertTestResult();

        try {
            result.test = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertTestResult deleteContactDetail(Long id) {
        InsertTestResult result = new InsertTestResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetTestResult getAll() {
        GetTestResult result = new GetTestResult();
        try {
            List<Test> temp = repo.findTestEntities();
            result.test = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleTestResult getById(Long id) {
        GetSingleTestResult result = new GetSingleTestResult();
        try {
            Test patient = repo.findTest(id);

            result.test = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
