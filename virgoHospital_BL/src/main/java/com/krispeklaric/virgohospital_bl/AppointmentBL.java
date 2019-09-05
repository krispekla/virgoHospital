/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.appointment.GetAppointmentResult;
import com.krispeklaric.virgohospital_bl.Messages.appointment.GetSingleAppointmentResult;
import com.krispeklaric.virgohospital_bl.Messages.appointment.InsertAppointmentResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IAppointmentRepo;
import com.krispeklaric.virgohospital_dal.Models.Appointment;
import java.util.List;

/**
 *
 * @author Kris
 */
public class AppointmentBL {

    private IAppointmentRepo repo;

    public AppointmentBL() {
        this.repo = RepoFactory.getAppointmentRepo();
    }

    public InsertAppointmentResult insertContactDetail(Appointment p) {
        InsertAppointmentResult result = new InsertAppointmentResult();
        try {
            result.appointment = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertAppointmentResult updateContactDetail(Appointment p) {
        InsertAppointmentResult result = new InsertAppointmentResult();

        try {
            result.appointment = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertAppointmentResult deleteContactDetail(Long id) {
        InsertAppointmentResult result = new InsertAppointmentResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetAppointmentResult getAll() {
        GetAppointmentResult result = new GetAppointmentResult();
        try {
            List<Appointment> temp = repo.findAppointmentEntities();
            result.appointment = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleAppointmentResult getById(Long id) {
        GetSingleAppointmentResult result = new GetSingleAppointmentResult();
        try {
            Appointment patient = repo.findAppointment(id);

            result.appointment = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
