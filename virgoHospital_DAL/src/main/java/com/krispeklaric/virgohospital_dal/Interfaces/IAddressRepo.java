/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Address;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IAddressRepo {

    public void create(Address address);

    public void edit(Address address) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Address> findAddressEntities();

    public List<Address> findAddressEntities(int maxResults, int firstResult);

    public Address findAddress(Long id);

    public int getAddressCount();
}
