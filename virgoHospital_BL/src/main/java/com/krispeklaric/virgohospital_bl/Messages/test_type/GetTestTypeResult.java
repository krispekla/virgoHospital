/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl.Messages.test_type;

import com.krispeklaric.virgohospital_bl.Messages.MessagingBase;
import com.krispeklaric.virgohospital_dal.Models.TestType;
import java.util.List;

/**
 *
 * @author Kris
 */
public class GetTestTypeResult extends MessagingBase {

 public List<TestType> testType;
}
