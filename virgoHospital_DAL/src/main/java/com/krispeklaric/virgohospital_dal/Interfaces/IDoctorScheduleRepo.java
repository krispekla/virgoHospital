/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.DoctorSchedule;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IDoctorScheduleRepo {

    public DoctorSchedule create(DoctorSchedule doctorSchedule);

    public DoctorSchedule edit(DoctorSchedule doctorSchedule) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<DoctorSchedule> findDoctorScheduleEntities();

    public List<DoctorSchedule> findDoctorScheduleEntities(int maxResults, int firstResult);

    public DoctorSchedule findDoctorSchedule(Long id);

    public int getDoctorScheduleCount();
}
