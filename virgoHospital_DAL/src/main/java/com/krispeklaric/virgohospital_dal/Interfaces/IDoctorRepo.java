/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Doctor;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IDoctorRepo {

    public void create(Doctor doctor);

    public void edit(Doctor doctor) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Doctor> findDoctorEntities();

    public List<Doctor> findDoctorEntities(int maxResults, int firstResult);

    public Doctor findDoctor(Long id);

    public int getDoctorCount();
}
