/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.drug.GetDrugResult;
import com.krispeklaric.virgohospital_bl.Messages.drug.GetSingleDrugResult;
import com.krispeklaric.virgohospital_bl.Messages.drug.InsertDrugResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IDrugRepo;
import com.krispeklaric.virgohospital_dal.Models.Drug;
import java.util.List;

/**
 *
 * @author Kris
 */
public class DrugBL {

    private IDrugRepo repo;

    public DrugBL() {
        this.repo = RepoFactory.getDrugRepo();
    }

    public InsertDrugResult insertContactDetail(Drug p) {
        InsertDrugResult result = new InsertDrugResult();
        try {
            result.drug = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertDrugResult updateContactDetail(Drug p) {
        InsertDrugResult result = new InsertDrugResult();

        try {
            result.drug = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertDrugResult deleteContactDetail(Long id) {
        InsertDrugResult result = new InsertDrugResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetDrugResult getAll() {
        GetDrugResult result = new GetDrugResult();
        try {
            List<Drug> temp = repo.findDrugEntities();
            result.drugs = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleDrugResult getById(Long id) {
        GetSingleDrugResult result = new GetSingleDrugResult();
        try {
            Drug patient = repo.findDrug(id);

            result.drug = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
