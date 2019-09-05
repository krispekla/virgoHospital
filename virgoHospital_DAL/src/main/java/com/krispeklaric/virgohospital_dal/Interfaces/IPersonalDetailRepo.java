/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.PersonalDetail;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IPersonalDetailRepo {

    public PersonalDetail create(PersonalDetail personalDetail);

    public PersonalDetail edit(PersonalDetail personalDetail) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<PersonalDetail> findPersonalDetailEntities();

    public List<PersonalDetail> findPersonalDetailEntities(int maxResults, int firstResult);

    public PersonalDetail findPersonalDetail(Long id);

    public int getPersonalDetailCount();
}
