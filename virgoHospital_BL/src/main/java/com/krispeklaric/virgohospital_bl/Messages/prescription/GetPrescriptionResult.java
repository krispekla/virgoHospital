/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl.Messages.prescription;

import com.krispeklaric.virgohospital_bl.Messages.MessagingBase;
import com.krispeklaric.virgohospital_dal.Models.Prescription;
import java.util.List;

/**
 *
 * @author Kris
 */
public class GetPrescriptionResult extends MessagingBase {

    public List<Prescription> prescription;
}
