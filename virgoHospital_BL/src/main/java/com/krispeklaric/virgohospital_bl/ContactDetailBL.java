/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.contact_detail.GetContactDetailResult;
import com.krispeklaric.virgohospital_bl.Messages.contact_detail.GetSingleContactDetailResult;
import com.krispeklaric.virgohospital_bl.Messages.contact_detail.InsertContactDetailResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IContactDetailRepo;
import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import java.util.List;

/**
 *
 * @author Kris
 */
public class ContactDetailBL {

    private IContactDetailRepo repo;

    public ContactDetailBL() {
        this.repo = RepoFactory.getContactDetailRepo();
    }

    public InsertContactDetailResult insertContactDetail(ContactDetail p) {
        InsertContactDetailResult result = new InsertContactDetailResult();
        try {
            result.contactDetail = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertContactDetailResult updateContactDetail(ContactDetail p) {
        InsertContactDetailResult result = new InsertContactDetailResult();

        try {
            result.contactDetail = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertContactDetailResult deleteContactDetail(Long id) {
        InsertContactDetailResult result = new InsertContactDetailResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetContactDetailResult getAll() {
        GetContactDetailResult result = new GetContactDetailResult();
        try {
            List<ContactDetail> temp = repo.findContactDetailEntities();
            result.contactDetails = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleContactDetailResult getById(Long id) {
        GetSingleContactDetailResult result = new GetSingleContactDetailResult();
        try {
            ContactDetail patient = repo.findContactDetail(id);

            result.contactDetail = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
