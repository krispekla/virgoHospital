/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_bl.Messages;

import com.krispeklaric.virgohospital_dal.Models.ContactDetail;
import java.util.List;

/**
 *
 * @author Kris
 */
public class GetContactDetailResult extends MessagingBase {

    public List<ContactDetail> contactDetails;
}
