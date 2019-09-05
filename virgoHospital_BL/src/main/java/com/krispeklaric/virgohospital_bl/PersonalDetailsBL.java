/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.personal_details.GetPersonalDetailResult;
import com.krispeklaric.virgohospital_bl.Messages.personal_details.GetSinglePersonalDetailResult;
import com.krispeklaric.virgohospital_bl.Messages.personal_details.InsertPersonalDetailResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IPersonalDetailRepo;
import com.krispeklaric.virgohospital_dal.Models.PersonalDetail;
import java.util.List;

/**
 *
 * @author Kris
 */
public class PersonalDetailsBL {

    private IPersonalDetailRepo repo;

    public PersonalDetailsBL() {
        this.repo = RepoFactory.getPersonalDetailRepo();
    }

    public InsertPersonalDetailResult insertContactDetail(PersonalDetail p) {
        InsertPersonalDetailResult result = new InsertPersonalDetailResult();
        try {
            result.personalDetail = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertPersonalDetailResult updateContactDetail(PersonalDetail p) {
        InsertPersonalDetailResult result = new InsertPersonalDetailResult();

        try {
            result.personalDetail = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertPersonalDetailResult deleteContactDetail(Long id) {
        InsertPersonalDetailResult result = new InsertPersonalDetailResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetPersonalDetailResult getAll() {
        GetPersonalDetailResult result = new GetPersonalDetailResult();
        try {
            List<PersonalDetail> temp = repo.findPersonalDetailEntities();
            result.personalDetail = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSinglePersonalDetailResult getById(Long id) {
        GetSinglePersonalDetailResult result = new GetSinglePersonalDetailResult();
        try {
            PersonalDetail patient = repo.findPersonalDetail(id);

            result.personalDetail = patient;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }
}
