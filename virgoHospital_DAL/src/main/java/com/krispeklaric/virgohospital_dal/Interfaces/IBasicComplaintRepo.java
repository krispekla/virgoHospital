/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_dal.Interfaces;

import com.krispeklaric.virgohospital_dal.Models.BasicComplaint;
import com.krispeklaric.virgohospital_dal.Repositories.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Kris
 */
public interface IBasicComplaintRepo {

    public BasicComplaint create(BasicComplaint basicComplaint);

    public BasicComplaint edit(BasicComplaint basicComplaint) throws NonexistentEntityException, Exception;

    public void destroy(Long id) throws NonexistentEntityException;

    public List<BasicComplaint> findBasicComplaintEntities();

    public List<BasicComplaint> findBasicComplaintEntities(int maxResults, int firstResult);

    public BasicComplaint findBasicComplaint(Long id);

    public int getBasicComplaintCount();
}
