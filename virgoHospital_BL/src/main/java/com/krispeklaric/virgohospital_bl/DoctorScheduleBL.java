/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.doctor_schedule.GetDoctorScheduleResult;
import com.krispeklaric.virgohospital_bl.Messages.doctor_schedule.GetSingleDoctorScheduleResult;
import com.krispeklaric.virgohospital_bl.Messages.doctor_schedule.InsertDoctorScheduleResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IDoctorScheduleRepo;
import com.krispeklaric.virgohospital_dal.Models.DoctorSchedule;
import java.util.List;

/**
 *
 * @author Kris
 */
public class DoctorScheduleBL {

    private IDoctorScheduleRepo repo;

    public DoctorScheduleBL() {
        this.repo = RepoFactory.getDoctorScheduleRepo();
    }

    public InsertDoctorScheduleResult insertContactDetail(DoctorSchedule p) {
        InsertDoctorScheduleResult result = new InsertDoctorScheduleResult();
        try {
            result.doctorSchedule = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertDoctorScheduleResult updateContactDetail(DoctorSchedule p) {
        InsertDoctorScheduleResult result = new InsertDoctorScheduleResult();

        try {
            result.doctorSchedule = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertDoctorScheduleResult deleteContactDetail(Long id) {
        InsertDoctorScheduleResult result = new InsertDoctorScheduleResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetDoctorScheduleResult getAll() {
        GetDoctorScheduleResult result = new GetDoctorScheduleResult();
        try {
            List<DoctorSchedule> temp = repo.findDoctorScheduleEntities();
            result.doctorSchedule = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleDoctorScheduleResult getById(Long id) {
        GetSingleDoctorScheduleResult result = new GetSingleDoctorScheduleResult();
        try {
            DoctorSchedule patient = repo.findDoctorSchedule(id);

            result.doctorSchedule = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
