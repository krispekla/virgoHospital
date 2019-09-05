/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Drug;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IDrugRepo {

    public Drug create(Drug medicalComplaint);

    public Drug edit(Drug medicalComplaint) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Drug> findDrugEntities();

    public List<Drug> findDrugEntities(int maxResults, int firstResult);

    public Drug findDrug(Long id);

    public int getDrugCount();
}
