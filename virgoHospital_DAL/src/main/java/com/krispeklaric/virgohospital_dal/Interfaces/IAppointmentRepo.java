/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Appointment;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IAppointmentRepo {

    public Appointment create(Appointment appointment);

    public Appointment edit(Appointment appointment) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Appointment> findAppointmentEntities();

    public List<Appointment> findAppointmentEntities(int maxResults, int firstResult);

    public Appointment findAppointment(Long id);

    public int getAppointmentCount();
}
