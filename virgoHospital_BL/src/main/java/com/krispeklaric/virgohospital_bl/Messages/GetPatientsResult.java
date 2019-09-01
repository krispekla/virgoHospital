package com.krispeklaric.virgohospital_bl.Messages;

import com.krispeklaric.virgohospital_dal.Models.Patient;
import java.util.List;

/**
 *
 * @author Kris
 */
public class GetPatientsResult extends MessagingBase {

    public List<Patient> patients;
}
