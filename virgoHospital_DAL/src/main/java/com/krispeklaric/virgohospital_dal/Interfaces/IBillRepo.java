/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Bill;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IBillRepo {

    public void create(Bill bill);

    public void edit(Bill bill) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Bill> findBillEntities();

    public List<Bill> findBillEntities(int maxResults, int firstResult);

    public Bill findBill(Long id);

    public int getBillCount();
}
