/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.bill.GetBillResult;
import com.krispeklaric.virgohospital_bl.Messages.bill.GetSingleBillResult;
import com.krispeklaric.virgohospital_bl.Messages.bill.InsertBillResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IBillRepo;
import com.krispeklaric.virgohospital_dal.Models.Bill;
import java.util.List;

/**
 *
 * @author Kris
 */
public class BillBL {

    private IBillRepo repo;

    public BillBL() {
        this.repo = RepoFactory.getBillRepo();
    }

    public InsertBillResult insertContactDetail(Bill p) {
        InsertBillResult result = new InsertBillResult();
        try {
            result.bill = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertBillResult updateContactDetail(Bill p) {
        InsertBillResult result = new InsertBillResult();

        try {
            result.bill = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertBillResult deleteContactDetail(Long id) {
        InsertBillResult result = new InsertBillResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetBillResult getAll() {
        GetBillResult result = new GetBillResult();
        try {
            List<Bill> temp = repo.findBillEntities();
            result.bill = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleBillResult getById(Long id) {
        GetSingleBillResult result = new GetSingleBillResult();
        try {
            Bill patient = repo.findBill(id);

            result.bill = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
