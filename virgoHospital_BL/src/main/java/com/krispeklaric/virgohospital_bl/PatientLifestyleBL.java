/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.patient_lifestyle.GetPatientLifestyleResult;
import com.krispeklaric.virgohospital_bl.Messages.patient_lifestyle.GetSinglePatientLifestyleResult;
import com.krispeklaric.virgohospital_bl.Messages.patient_lifestyle.InsertPatientLifestyleResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IPatientLifestyleRepo;
import com.krispeklaric.virgohospital_dal.Models.PatientLifestyle;
import java.util.List;

/**
 *
 * @author Kris
 */
public class PatientLifestyleBL {

    private IPatientLifestyleRepo repo;

    public PatientLifestyleBL() {
        this.repo = RepoFactory.getPatientLifestyleRepo();
    }

    public InsertPatientLifestyleResult insertPatientLifestyle(PatientLifestyle p) {
        InsertPatientLifestyleResult result = new InsertPatientLifestyleResult();
        try {
            result.patientLifestyle = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertPatientLifestyleResult updatePatientLifestyle(PatientLifestyle p) {
        InsertPatientLifestyleResult result = new InsertPatientLifestyleResult();

        try {
            result.patientLifestyle = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertPatientLifestyleResult deletePatientLifestyle(Long id) {
        InsertPatientLifestyleResult result = new InsertPatientLifestyleResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetPatientLifestyleResult getAll() {
        GetPatientLifestyleResult result = new GetPatientLifestyleResult();
        try {
            List<PatientLifestyle> temp = repo.findPatientLifestyleEntities();
            result.patientLifestyle = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSinglePatientLifestyleResult getById(Long id) {
        GetSinglePatientLifestyleResult result = new GetSinglePatientLifestyleResult();
        try {
            PatientLifestyle patient = repo.findPatientLifestyle(id);

            result.patientLifestyle = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
