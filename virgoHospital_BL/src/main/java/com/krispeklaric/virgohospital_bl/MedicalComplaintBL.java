/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.medical_complaint.GetMedicalComplaintResult;
import com.krispeklaric.virgohospital_bl.Messages.medical_complaint.GetSingleMedicalComplaintResult;
import com.krispeklaric.virgohospital_bl.Messages.medical_complaint.InsertMedicalComplaintResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IMedicalComplaintRepo;
import com.krispeklaric.virgohospital_dal.Models.MedicalComplaint;
import java.util.List;

/**
 *
 * @author Kris
 */
public class MedicalComplaintBL {

    private IMedicalComplaintRepo repo;

    public MedicalComplaintBL() {
        this.repo = RepoFactory.getMedicalComplaintRepo();
    }

    public InsertMedicalComplaintResult insertMedicalComplaint(MedicalComplaint p) {
        InsertMedicalComplaintResult result = new InsertMedicalComplaintResult();
        try {
            result.medicalComplaint = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertMedicalComplaintResult updateMedicalComplaint(MedicalComplaint p) {
        InsertMedicalComplaintResult result = new InsertMedicalComplaintResult();

        try {
            result.medicalComplaint = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertMedicalComplaintResult deleteMedicalComplaint(Long id) {
        InsertMedicalComplaintResult result = new InsertMedicalComplaintResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetMedicalComplaintResult getAll() {
        GetMedicalComplaintResult result = new GetMedicalComplaintResult();
        try {
            List<MedicalComplaint> temp = repo.findMedicalComplaintEntities();
            result.medicalComplaint = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleMedicalComplaintResult getById(Long id) {
        GetSingleMedicalComplaintResult result = new GetSingleMedicalComplaintResult();
        try {
            MedicalComplaint patient = repo.findMedicalComplaint(id);

            result.medicalComplaint = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
