/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Prescription;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IPrescriptionRepo {

    public void create(Prescription prescription);

    public void edit(Prescription prescription) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Prescription> findPrescriptionEntities();

    public List<Prescription> findPrescriptionEntities(int maxResults, int firstResult);

    public Prescription findPrescription(Long id);

    public int getPrescriptionCount();
}
