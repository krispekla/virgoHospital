/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.Payment;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IPaymentRepo {

    public void create(Payment payment);

    public void edit(Payment payment) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<Payment> findPaymentEntities();

    public List<Payment> findPaymentEntities(int maxResults, int firstResult);

    public Payment findPayment(Long id);

    public int getPaymentCount();
}
