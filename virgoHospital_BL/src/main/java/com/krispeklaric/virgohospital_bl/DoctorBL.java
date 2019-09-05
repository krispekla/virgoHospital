/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.doctor.GetDoctorResult;
import com.krispeklaric.virgohospital_bl.Messages.doctor.GetSingleDoctorResult;
import com.krispeklaric.virgohospital_bl.Messages.doctor.InsertDoctorResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IDoctorRepo;
import com.krispeklaric.virgohospital_dal.Models.Doctor;
import java.util.List;

/**
 *
 * @author Kris
 */
public class DoctorBL {

    private IDoctorRepo repo;

    public DoctorBL() {
        this.repo = RepoFactory.getDoctorRepo();
    }

    public InsertDoctorResult insertContactDetail(Doctor p) {
        InsertDoctorResult result = new InsertDoctorResult();
        try {
            result.doctors = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertDoctorResult updateContactDetail(Doctor p) {
        InsertDoctorResult result = new InsertDoctorResult();

        try {
            result.doctors = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertDoctorResult deleteContactDetail(Long id) {
        InsertDoctorResult result = new InsertDoctorResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetDoctorResult getAll() {
        GetDoctorResult result = new GetDoctorResult();
        try {
            List<Doctor> temp = repo.findDoctorEntities();
            result.doctors = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleDoctorResult getById(Long id) {
        GetSingleDoctorResult result = new GetSingleDoctorResult();
        try {
            Doctor patient = repo.findDoctor(id);

            result.doctors = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
