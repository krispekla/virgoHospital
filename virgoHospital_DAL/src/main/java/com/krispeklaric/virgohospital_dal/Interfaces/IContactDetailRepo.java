/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IContactDetailRepo {

    public void create(ContactDetail contactDetail);

    public void edit(ContactDetail contactDetail) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<ContactDetail> findContactDetailEntities();

    public List<ContactDetail> findContactDetailEntities(int maxResults, int firstResult);

    public ContactDetail findContactDetail(Long id);

    public int getContactDetailCount();
}
