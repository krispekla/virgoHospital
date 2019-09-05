package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.patient.InsertPatientResult;
import com.krispeklaric.virgohospital_bl.Messages.patient.GetSinglePatientResult;
import com.krispeklaric.virgohospital_bl.Messages.patient.GetPatientsResult;
import com.krispeklaric.virgohospital_bl.Messages.*;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IPatientRepo;
import com.krispeklaric.virgohospital_dal.Models.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kris
 */
public class PatientsBL {

    private IPatientRepo repo;

    public PatientsBL() {
        this.repo = RepoFactory.getPatientRepo();
    }

    public InsertPatientResult insertPatient(Patient p) {
        InsertPatientResult result = new InsertPatientResult();
        try {
            result.patient = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertPatientResult updatePatient(Patient p) {
        InsertPatientResult result = new InsertPatientResult();

        try {
            result.patient = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertPatientResult deletePatient(Long id) {
        InsertPatientResult result = new InsertPatientResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetPatientsResult getAll() {
        GetPatientsResult result = new GetPatientsResult();
        try {
            List<Patient> patients = repo.findPatientEntities();
            result.patients = patients;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSinglePatientResult getById(Long id) {
        GetSinglePatientResult result = new GetSinglePatientResult();
        try {
            Patient patient = repo.findPatient(id);

            result.patient = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
