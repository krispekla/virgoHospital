/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Patient;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IPatientRepo {

    public Patient create(Patient patient);

    public Patient edit(Patient patient) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Patient> findPatientEntities();

    public List<Patient> findPatientEntities(int maxResults, int firstResult);

    public Patient findPatient(Long id);

    public int getPatientCount();
}
