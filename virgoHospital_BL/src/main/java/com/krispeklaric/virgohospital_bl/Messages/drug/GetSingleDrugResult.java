/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl.Messages.drug;

import com.krispeklaric.virgohospital_bl.Messages.MessagingBase;
import com.krispeklaric.virgohospital_dal.Models.Address;
import com.krispeklaric.virgohospital_dal.Models.Drug;

/**
 *
 * @author Kris
 */
public class GetSingleDrugResult extends MessagingBase {

    public Drug drug;
}
