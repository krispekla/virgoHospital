/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl;

import com.krispeklaric.virgohospital_bl.Messages.GetAddressResult;
import com.krispeklaric.virgohospital_bl.Messages.GetSingleAddressResult;
import com.krispeklaric.virgohospital_bl.Messages.InsertAddressResult;
import com.krispeklaric.virgohospital_dal.Factory.RepoFactory;
import com.krispeklaric.virgohospital_dal.Interfaces.IAddressRepo;
import com.krispeklaric.virgohospital_dal.Models.Address;
import java.util.List;

/**
 *
 * @author Kris
 */
public class AddressBL {

    private IAddressRepo repo;

    public AddressBL() {
        this.repo = RepoFactory.getAddressRepo();
    }

    public InsertAddressResult insertAddress(Address p) {
        InsertAddressResult result = new InsertAddressResult();
        try {
            result.address = repo.create(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public InsertAddressResult updateAddress(Address p) {
        InsertAddressResult result = new InsertAddressResult();

        try {
            result.address = repo.edit(p);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public InsertAddressResult deleteAddress(Long id) {
        InsertAddressResult result = new InsertAddressResult();

        try {
            repo.destroy(id);
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }
        return result;
    }

    public GetAddressResult getAll() {
        GetAddressResult result = new GetAddressResult();
        try {
            List<Address> temp = repo.findAddressEntities();
            result.addresses = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

    public GetSingleAddressResult getById(Long id) {
        GetSingleAddressResult result = new GetSingleAddressResult();
        try {
            Address temp = repo.findAddress(id);

            result.address = temp;
        } catch (Exception ex) {
            result.isOK = false;
            result.msg = ex.getMessage();
        }

        return result;
    }

}
