/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl.Messages;

import com.krispeklaric.virgohospital_dal.Models.PhoneNumber;
import java.util.List;

/**
 *
 * @author Kris
 */
public class GetPhoneNumberResult extends MessagingBase {

    public List<PhoneNumber> phoneNumbers;
}
