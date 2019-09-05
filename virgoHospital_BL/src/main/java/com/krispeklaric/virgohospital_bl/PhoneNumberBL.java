/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.phone_number.GetPhoneNumberResult;
import com.krispeklaric.virgohospital_bl.Messages.phone_number.GetSinglePhoneNumberResult;
import com.krispeklaric.virgohospital_bl.Messages.phone_number.InsertPhoneNumberResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IPhoneNumberRepo;
import com.krispeklaric.virgohospital_dal.Models.PhoneNumber;
import java.util.List;

/**
 *
 * @author Kris
 */
public class PhoneNumberBL {

    private IPhoneNumberRepo repo;

    public PhoneNumberBL() {
        this.repo = RepoFactory.getPhoneNumberRepo();
    }

    public InsertPhoneNumberResult insertPhoneNumber(PhoneNumber p) {
        InsertPhoneNumberResult result = new InsertPhoneNumberResult();
        try {
            result.phoneNumber = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertPhoneNumberResult updatePhoneNumber(PhoneNumber p) {
        InsertPhoneNumberResult result = new InsertPhoneNumberResult();

        try {
            result.phoneNumber = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertPhoneNumberResult deletePhoneNumber(Long id) {
        InsertPhoneNumberResult result = new InsertPhoneNumberResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetPhoneNumberResult getAll() {
        GetPhoneNumberResult result = new GetPhoneNumberResult();
        try {
            List<PhoneNumber> temp = repo.findPhoneNumberEntities();
            result.phoneNumbers = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSinglePhoneNumberResult getById(Long id) {
        GetSinglePhoneNumberResult result = new GetSinglePhoneNumberResult();
        try {
            PhoneNumber patient = repo.findPhoneNumber(id);

            result.phoneNumber = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
