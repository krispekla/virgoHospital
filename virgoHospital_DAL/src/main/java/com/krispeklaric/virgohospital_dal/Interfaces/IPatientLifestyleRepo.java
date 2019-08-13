/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.PatientLifestyle;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IPatientLifestyleRepo {

    public void create(PatientLifestyle patientLifestyle);

    public void edit(PatientLifestyle patientLifestyle) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<PatientLifestyle> findPatientLifestyleEntities();

    public List<PatientLifestyle> findPatientLifestyleEntities(int maxResults, int firstResult);

    public PatientLifestyle findPatientLifestyle(Long id);

    public int getPatientLifestyleCount();
}
