/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.drug_prescription.GetSingleDrugPrescriptionResult;
import com.krispeklaric.virgohospital_bl.Messages.prescription.GetPrescriptionResult;
import com.krispeklaric.virgohospital_bl.Messages.prescription.GetSinglePrescriptionResult;
import com.krispeklaric.virgohospital_bl.Messages.prescription.InsertPrescriptionResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IPrescriptionRepo;
import com.krispeklaric.virgohospital_dal.Models.Prescription;
import java.util.List;

/**
 *
 * @author Kris
 */
public class PrescriptionBL {

    private IPrescriptionRepo repo;

    public PrescriptionBL() {
        this.repo = RepoFactory.getPrescriptionRepo();
    }

    public InsertPrescriptionResult insertContactDetail(Prescription p) {
        InsertPrescriptionResult result = new InsertPrescriptionResult();
        try {
            result.prescription = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertPrescriptionResult updateContactDetail(Prescription p) {
        InsertPrescriptionResult result = new InsertPrescriptionResult();

        try {
            result.prescription = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertPrescriptionResult deleteContactDetail(Long id) {
        InsertPrescriptionResult result = new InsertPrescriptionResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetPrescriptionResult getAll() {
        GetPrescriptionResult result = new GetPrescriptionResult();
        try {
            List<Prescription> temp = repo.findPrescriptionEntities();
            result.prescription = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSinglePrescriptionResult getById(Long id) {
        GetSinglePrescriptionResult result = new GetSinglePrescriptionResult();
        try {
            Prescription patient = repo.findPrescription(id);

            result.prescription = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
