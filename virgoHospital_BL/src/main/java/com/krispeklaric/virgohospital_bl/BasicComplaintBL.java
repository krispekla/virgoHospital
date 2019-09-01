/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.*;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IBasicComplaintRepo;
import com.krispeklaric.virgohospital_dal.Models.BasicComplaint;
import java.util.List;

/**
 *
 * @author Kris
 */
public class BasicComplaintBL {

    private IBasicComplaintRepo repo;

    public BasicComplaintBL() {
        this.repo = RepoFactory.getBasicComplaintRepo();
    }

    public InsertBasicComplaintResult insertBasicComplaint(BasicComplaint p) {
        InsertBasicComplaintResult result = new InsertBasicComplaintResult();
        try {
            result.basicComplaint = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertBasicComplaintResult updateBasicComplaint(BasicComplaint p) {
        InsertBasicComplaintResult result = new InsertBasicComplaintResult();

        try {
            result.basicComplaint = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertBasicComplaintResult deleteBasicComplaint(Long id) {
        InsertBasicComplaintResult result = new InsertBasicComplaintResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetBasicComplaintResult getAll() {
        GetBasicComplaintResult result = new GetBasicComplaintResult();
        try {
            List<BasicComplaint> temp = repo.findBasicComplaintEntities();
            result.basicComplaints = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleBasicComplaintResult getById(Long id) {
        GetSingleBasicComplaintResult result = new GetSingleBasicComplaintResult();
        try {
            BasicComplaint temp = repo.findBasicComplaint(id);

            result.basicComplaint = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

}
