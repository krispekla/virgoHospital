/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.DrugPrescriptions;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IDrugPrescriptionRepo {

    public DrugPrescriptions create(DrugPrescriptions medicalComplaint);

    public DrugPrescriptions edit(DrugPrescriptions medicalComplaint) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<DrugPrescriptions> findDrugPrescriptionsEntities();

    public List<DrugPrescriptions> findDrugPrescriptionsEntities(int maxResults, int firstResult);

    public DrugPrescriptions findDrugPrescriptions(Long id);

    public int getDrugCount();
}
