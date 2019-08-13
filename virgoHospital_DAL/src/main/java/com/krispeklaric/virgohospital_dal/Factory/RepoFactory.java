package com.krispeklaric.virgohospital_dal.Factory;

import com.krispeklaric.virgohospital_dal.Interfaces.*;
import com.krispeklaric.virgohospital_dal.Repositories.*;

/**
 *
 * @author Kris
 */
public class RepoFactory {

    public static IAddressRepo getAddressRepo() {
        return new AddressRepo();
    }

    public static IAppointmentRepo getAppointmentRepo() {
        return new AppointmentRepo();
    }

    public static IBasicComplaintRepo getBasicComplaintRepo() {
        return new BasicComplaintRepo();
    }

    public static IBillRepo getBillRepo() {
        return new BillRepo();
    }

    public static IContactDetailRepo getContactDetailRepo() {
        return new ContactDetailRepo();
    }

    public static IDoctorRepo getDoctorRepo() {
        return new DoctorRepo();
    }

    public static IDoctorScheduleRepo getDoctorScheduleRepo() {
        return new DoctorScheduleRepo();
    }

//    public static IDrugRepo getDrugRepo() {
//        return new DrugRepo();
//    }
    public static IMedicalComplaintRepo getMedicalComplaintRepo() {
        return new MedicalComplaintRepo();
    }

    public static INextOfKinRepo getNextOfKinRepo() {
        return new NextOfKinRepo();
    }

    public static IPatientRepo getPatientRepo() {
        return new PatientRepo();
    }

    public static IPatientLifestyleRepo getPatientLifestyleRepo() {
        return new PatientLifestyleRepo();
    }

    public static IPaymentRepo getPaymentRepo() {
        return new PaymentRepo();
    }

    public static IPersonalDetailRepo getPersonalDetailRepo() {
        return new PersonalDetailRepo();
    }

    public static IPhoneNumberRepo getPhoneNumberRepo() {
        return new PhoneNumberRepo();
    }

    public static IPrescriptionRepo getPrescriptionRepo() {
        return new PrescriptionRepo();
    }

    public static ITestRepo getTestRepo() {
        return new TestRepo();
    }

    public static ITestTypeRepo getTestTypeRepo() {
        return new TestTypeRepo();
    }

}
