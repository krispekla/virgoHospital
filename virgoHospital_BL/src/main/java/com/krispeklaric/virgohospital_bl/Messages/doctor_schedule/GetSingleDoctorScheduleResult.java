/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl.Messages.doctor_schedule;

import com.krispeklaric.virgohospital_bl.Messages.MessagingBase;
import com.krispeklaric.virgohospital_dal.Models.DoctorSchedule;

/**
 *
 * @author Kris
 */
public class GetSingleDoctorScheduleResult  extends MessagingBase {

    public DoctorSchedule doctorSchedule;
}
