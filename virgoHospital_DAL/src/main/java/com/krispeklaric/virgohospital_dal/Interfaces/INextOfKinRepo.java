/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.NextOfKin;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface INextOfKinRepo {

    public NextOfKin create(NextOfKin nextOfKin);

    public NextOfKin edit(NextOfKin nextOfKin) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<NextOfKin> findNextOfKinEntities();

    public List<NextOfKin> findNextOfKinEntities(int maxResults, int firstResult);

    public NextOfKin findNextOfKin(Long id);

    public int getNextOfKinCount();
}
