/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.PhoneNumber;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IPhoneNumberRepo {

    public PhoneNumber create(PhoneNumber phoneNumber);

    public PhoneNumber edit(PhoneNumber phoneNumber) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<PhoneNumber> findPhoneNumberEntities();

    public List<PhoneNumber> findPhoneNumberEntities(int maxResults, int firstResult);

    public PhoneNumber findPhoneNumber(Long id);

    public int getPhoneNumberCount();
}
