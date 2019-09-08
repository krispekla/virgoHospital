/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_console;

import com.krispeklaric.virgohospital_bl.Messages.patient.GetPatientsResult;
import com.krispeklaric.virgohospital_bl.*;
import com.krispeklaric.virgohospital_bl.Messages.address.InsertAddressResult;
import com.krispeklaric.virgohospital_bl.Messages.basic_complaint.InsertBasicComplaintResult;
import com.krispeklaric.virgohospital_bl.Messages.contact_detail.InsertContactDetailResult;
import com.krispeklaric.virgohospital_bl.Messages.doctor.GetDoctorResult;
import com.krispeklaric.virgohospital_bl.Messages.medical_complaint.InsertMedicalComplaintResult;
import com.krispeklaric.virgohospital_bl.Messages.next_of_kin.InsertNextOfKinResult;
import com.krispeklaric.virgohospital_bl.Messages.patient.InsertPatientResult;
import com.krispeklaric.virgohospital_bl.Messages.patient_lifestyle.InsertPatientLifestyleResult;
import com.krispeklaric.virgohospital_bl.Messages.personal_details.InsertPersonalDetailResult;
import com.krispeklaric.virgohospital_bl.Messages.phone_number.InsertPhoneNumberResult;
import com.krispeklaric.virgohospital_dal.Models.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kris
 */
public class Main {

    private static PatientsBL _patientBL = new PatientsBL();
    private static List<Patient> _patients;

    public static void main(String[] args) {
        Boolean close = false;

        PrintMenu();
        do {

            Scanner scann = new Scanner(System.in);

            Integer number;
            while (!scann.hasNextInt()) {
                scann.nextLine(); //clear the invalid input before prompting again
                PrintMenu();

            }

            number = scann.nextInt();

            switch (number) {
                case 0:
                    System.out.println("0: Exit");
                    close = true;
                    break;
                case 1:
                    System.out.println("1: Get all Patients");
                    GetAllPatients();
                    break;
                case 2:
                    System.out.println("2: Create Patient");
                    CreatePatient();
                    break;
                case 3:
                    System.out.println("3: Update Patient");
                    UpdatePatient();
                    break;
                case 4:
                    System.out.println("4: Delete Patient");
                    DeletePatient();
                    break;

                default:
                    System.out.println("Wrong input");
                    break;
            }

        } while (!close);

    }

    public static void PrintMenu() {
        System.out.println("VIRGO HOSPITAL - OUTPATIENT MODULE");
        System.out.println("Please select number:");
        System.out.println("0: Exit");
        System.out.println("1: Get all Patients");
        System.out.println("2: Create Patient");
        System.out.println("3: Update Patient");
        System.out.println("4: Delete Patient");
    }

    private static void GetAllPatients() {
        GetPatientsResult allPatients = _patientBL.getAll();
        for (Patient patient : allPatients.patients) {
            System.out.println(patient.getId().toString() + " " + patient.toString());
        }
        if (allPatients.isOK) {
            _patients = allPatients.patients;
        }
    }

    private static void CreatePatient() {
        Scanner scann = new Scanner(System.in);

        System.out.println("Type first name:");
        while (!scann.hasNext()) {
            scann.nextLine();
            System.out.println("Please type correct format");
        }
        String firstName = scann.next();

        System.out.println("Type last name:");
        while (!scann.hasNext()) {
            scann.nextLine();
            System.out.println("Please type correct format");
        }
        String lastName = scann.next();

        PatientsBL patientBL = new PatientsBL();
        BasicComplaintBL basicComplaintBL = new BasicComplaintBL();
        NextOfKinBL nextOfKinBL = new NextOfKinBL();
        ContactDetailBL contactDetailBL = new ContactDetailBL();
        PhoneNumberBL phoneNumberBL = new PhoneNumberBL();
        AddressBL addressBL = new AddressBL();
        DoctorBL doctorBL = new DoctorBL();
        MedicalComplaintBL medicalComplaintBL = new MedicalComplaintBL();
        PatientLifestyleBL patientLifestyleBL = new PatientLifestyleBL();
        PersonalDetailsBL personalDetailsBL = new PersonalDetailsBL();

        Patient patient = new Patient();
        patient.setFirstname(firstName);
        patient.setLastname(lastName);
        patient.setGender('M');

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate birthdate = LocalDate.parse("12/12/1992", formatter);
        patient.setBirthdate(birthdate);

        NextOfKin kin = new NextOfKin();

        BasicComplaint basicComplaint = new BasicComplaint();

        ContactDetail contact = new ContactDetail();
        ContactDetail contactNextOfKin = new ContactDetail();

        PhoneNumber phone1 = new PhoneNumber();
        PhoneNumber phone2 = new PhoneNumber();

        phone1.setPhoneType(PhoneType.Work);
        phone2.setPhoneType(PhoneType.Mobile);

        try {
            InsertBasicComplaintResult res1 = basicComplaintBL.insertBasicComplaint(basicComplaint);
            if (res1.isOK) {
                patient.setBasicComplaints(res1.basicComplaint);
            }

            Address a1 = new Address();
            Address a2 = new Address();
            Address a3 = new Address();
            Address a4 = new Address();

            InsertAddressResult adr1 = addressBL.insertAddress(a1);
            InsertAddressResult adr2 = addressBL.insertAddress(a2);
            InsertAddressResult adr3 = addressBL.insertAddress(a3);
            InsertAddressResult adr4 = addressBL.insertAddress(a4);

            contact.setPermanentAddress(adr1.address);
            contact.setPresentAddress(adr2.address);

            contactNextOfKin.setPermanentAddress(adr3.address);
            contactNextOfKin.setPresentAddress(adr4.address);

            InsertContactDetailResult contactPatient = contactDetailBL.insertContactDetail(contact);
            InsertContactDetailResult resNextOfKinContact = contactDetailBL.insertContactDetail(contactNextOfKin);

            phone1.setContact(contactPatient.contactDetail);
            phone2.setContact(contactPatient.contactDetail);

            InsertPhoneNumberResult resPhone1 = phoneNumberBL.insertPhoneNumber(phone1);
            InsertPhoneNumberResult resPhone2 = phoneNumberBL.insertPhoneNumber(phone2);

            GetDoctorResult doctors = doctorBL.getAll();
            Doctor availableDoctor = doctors.doctors.stream().filter(doc -> doc.isUnavailable() == false).findFirst().orElse(null);

            if (availableDoctor != null) {
                patient.setDoctor(availableDoctor);
            }

            MedicalComplaint medCompl = new MedicalComplaint();
            PatientLifestyle patLife = new PatientLifestyle();
            PersonalDetail persDet = new PersonalDetail();

            InsertMedicalComplaintResult medical = new InsertMedicalComplaintResult();
            InsertPatientLifestyleResult lifestyle = new InsertPatientLifestyleResult();
            InsertPersonalDetailResult personal = new InsertPersonalDetailResult();

            medical = medicalComplaintBL.insertMedicalComplaint(medCompl);
            lifestyle = patientLifestyleBL.insertPatientLifestyle(patLife);
            personal = personalDetailsBL.insertContactDetail(persDet);

            patient.setContactDetail(contactPatient.contactDetail);
            patient.setMedicalComplaints(medical.medicalComplaint);
            patient.setPatientLifestyle(lifestyle.patientLifestyle);
            patient.setPersonalDetail(personal.personalDetail);

            InsertPatientResult res2 = patientBL.insertPatient(patient);
            if (res2.isOK) {
                kin.setPatient(res2.patient);
            }

            kin.setContactDetailNextOf(contactNextOfKin);
            InsertNextOfKinResult res3 = nextOfKinBL.insertNextOfKin(kin);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void UpdatePatient() {
        System.out.println("Select Patient number you wish to update:");
        GetAllPatients();

        Scanner scann = new Scanner(System.in);

        Integer id;
        while (!scann.hasNextInt()) {
            scann.nextLine();
        }

        id = scann.nextInt() - 1;

        System.out.println("Select what you wish to update:");
        System.out.println("0: Cancel");
        System.out.println("1: Change first name");
        System.out.println("2: Change last name");

        Integer updateSelection;
        while (!scann.hasNextInt()) {
            scann.nextLine();
        }
        updateSelection = scann.nextInt();

        switch (updateSelection) {
            case 0:
                System.out.println("0: Cancel");
                break;
            case 1:
                System.out.println("Type new first name:");
                while (!scann.hasNext()) {
                    scann.nextLine();
                    System.out.println("Please type correct format");
                }
                String tempFirst = scann.next();
                _patients.get(id).setFirstname(tempFirst);
                _patientBL.updatePatient(_patients.get(id));
                break;
            case 2:
                System.out.println("Type new last name:");
                while (!scann.hasNext()) {
                    scann.nextLine();
                    System.out.println("Please type correct format");
                }
                String tempLast = scann.next();
                _patients.get(id).setFirstname(tempLast);
                _patientBL.updatePatient(_patients.get(id));

                break;
        }

    }

    private static void DeletePatient() {
        System.out.println("Select Patient number you wish to delete:");
        GetAllPatients();

        Scanner scann = new Scanner(System.in);

        Integer id;
        while (!scann.hasNextInt()) {
            scann.nextLine();
        }

        id = scann.nextInt();
        _patientBL.deletePatient(id.longValue());
    }

}
