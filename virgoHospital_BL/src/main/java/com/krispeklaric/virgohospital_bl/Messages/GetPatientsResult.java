package com.krispeklaric.virgohospital_bl.Messages;

import com.krispeklaric.virgohospital_bl.Messages.MessagingBase;
import com.krispeklaric.virgohospital_dal.Models.Patient;
import java.util.ArrayList;

/**
 *
 * @author Kris
 */
public class GetPatientsResult extends MessagingBase {

    public ArrayList<Patient> patients;
}
