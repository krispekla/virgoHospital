/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.drug_prescription.GetDrugPrescriptionResult;
import com.krispeklaric.virgohospital_bl.Messages.drug_prescription.GetSingleDrugPrescriptionResult;
import com.krispeklaric.virgohospital_bl.Messages.drug_prescription.InsertDrugPrescriptionResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IDrugPrescriptionRepo;
import com.krispeklaric.virgohospital_dal.Models.DrugPrescriptions;
import java.util.List;

/**
 *
 * @author Kris
 */
public class DrugPrescriptionBL {

    private IDrugPrescriptionRepo repo;

    public DrugPrescriptionBL() {
        this.repo = RepoFactory.getDrugPrscriptionRepo();
    }

    public InsertDrugPrescriptionResult insertContactDetail(DrugPrescriptions p) {
        InsertDrugPrescriptionResult result = new InsertDrugPrescriptionResult();
        try {
            result.drugPrescriptions = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertDrugPrescriptionResult updateContactDetail(DrugPrescriptions p) {
        InsertDrugPrescriptionResult result = new InsertDrugPrescriptionResult();

        try {
            result.drugPrescriptions = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertDrugPrescriptionResult deleteContactDetail(Long id) {
        InsertDrugPrescriptionResult result = new InsertDrugPrescriptionResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetDrugPrescriptionResult getAll() {
        GetDrugPrescriptionResult result = new GetDrugPrescriptionResult();
        try {
            List<DrugPrescriptions> temp = repo.findDrugPrescriptionsEntities();
            result.drugPrescriptions = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleDrugPrescriptionResult getById(Long id) {
        GetSingleDrugPrescriptionResult result = new GetSingleDrugPrescriptionResult();
        try {
            DrugPrescriptions patient = repo.findDrugPrescriptions(id);

            result.drugPrescriptions = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
