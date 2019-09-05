/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.payment.GetPaymentResult;
import com.krispeklaric.virgohospital_bl.Messages.payment.GetSinglePaymentResult;
import com.krispeklaric.virgohospital_bl.Messages.payment.InsertPaymentResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IPaymentRepo;
import com.krispeklaric.virgohospital_dal.Models.Payment;
import java.util.List;

/**
 *
 * @author Kris
 */
public class PaymentBL {

    private IPaymentRepo repo;

    public PaymentBL() {
        this.repo = RepoFactory.getPaymentRepo();
    }

    public InsertPaymentResult insertContactDetail(Payment p) {
        InsertPaymentResult result = new InsertPaymentResult();
        try {
            result.payment = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertPaymentResult updateContactDetail(Payment p) {
        InsertPaymentResult result = new InsertPaymentResult();

        try {
            result.payment = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertPaymentResult deleteContactDetail(Long id) {
        InsertPaymentResult result = new InsertPaymentResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetPaymentResult getAll() {
        GetPaymentResult result = new GetPaymentResult();
        try {
            List<Payment> temp = repo.findPaymentEntities();
            result.payment = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSinglePaymentResult getById(Long id) {
        GetSinglePaymentResult result = new GetSinglePaymentResult();
        try {
            Payment patient = repo.findPayment(id);

            result.payment = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
