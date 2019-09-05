/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.MedicalComplaint;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IMedicalComplaintRepo {

    public MedicalComplaint create(MedicalComplaint medicalComplaint);

    public MedicalComplaint edit(MedicalComplaint medicalComplaint) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<MedicalComplaint> findMedicalComplaintEntities();

    public List<MedicalComplaint> findMedicalComplaintEntities(int maxResults, int firstResult);

    public MedicalComplaint findMedicalComplaint(Long id);

    public int getMedicalComplaintCount();
}
