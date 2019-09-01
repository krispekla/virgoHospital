/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.*;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.INextOfKinRepo;
import com.krispeklaric.virgohospital_dal.Models.NextOfKin;
import java.util.List;

/**
 *
 * @author Kris
 */
public class NextOfKinBL {

    private INextOfKinRepo repo;

    public NextOfKinBL() {
        this.repo = RepoFactory.getNextOfKinRepo();
    }

    public InsertNextOfKinResult insertNextOfKin(NextOfKin p) {
        InsertNextOfKinResult result = new InsertNextOfKinResult();
        try {
            result.nextOfKin = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertNextOfKinResult updateNextOfKin(NextOfKin p) {
        InsertNextOfKinResult result = new InsertNextOfKinResult();

        try {
            result.nextOfKin = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertNextOfKinResult deleteNextOfKin(Long id) {
        InsertNextOfKinResult result = new InsertNextOfKinResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetNextOfKinResult getAll() {
        GetNextOfKinResult result = new GetNextOfKinResult();
        try {
            List<NextOfKin> temp = repo.findNextOfKinEntities();
            result.nextOfKin = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleNextOfKinResult getById(Long id) {
        GetSingleNextOfKinResult result = new GetSingleNextOfKinResult();
        try {
            NextOfKin temp = repo.findNextOfKin(id);

            result.nextOfKin = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

}
