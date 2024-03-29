/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krispeklaric.virgohospital_gui;

import com.krispeklaric.virgohospital_bl.Messages.patient.GetPatientsResult;
import com.krispeklaric.virgohospital_bl.*;
import com.krispeklaric.virgohospital_bl.Messages.*;
import com.krispeklaric.virgohospital_bl.Messages.address.InsertAddressResult;
import com.krispeklaric.virgohospital_bl.Messages.appointment.GetAppointmentResult;
import com.krispeklaric.virgohospital_bl.Messages.appointment.GetSingleAppointmentResult;
import com.krispeklaric.virgohospital_bl.Messages.appointment.InsertAppointmentResult;
import com.krispeklaric.virgohospital_bl.Messages.bill.GetBillResult;
import com.krispeklaric.virgohospital_bl.Messages.contact_detail.InsertContactDetailResult;
import com.krispeklaric.virgohospital_bl.Messages.doctor.GetDoctorResult;
import com.krispeklaric.virgohospital_bl.Messages.doctor.GetSingleDoctorResult;
import com.krispeklaric.virgohospital_bl.Messages.drug.GetDrugResult;
import com.krispeklaric.virgohospital_bl.Messages.drug_prescription.GetDrugPrescriptionResult;
import com.krispeklaric.virgohospital_bl.Messages.patient.GetSinglePatientResult;
import com.krispeklaric.virgohospital_bl.Messages.payment.InsertPaymentResult;
import com.krispeklaric.virgohospital_bl.Messages.phone_number.InsertPhoneNumberResult;
import com.krispeklaric.virgohospital_bl.Messages.prescription.InsertPrescriptionResult;
import com.krispeklaric.virgohospital_bl.Messages.test_type.GetTestTypeResult;
import com.krispeklaric.virgohospital_dal.Models.*;
import com.sun.glass.events.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Kris
 */
public class OutpatientModule extends javax.swing.JFrame {

    private static Patient _currentPatient;
    private static Appointment _currentAppointment;
    private static Appointment _currentAppointmentByDoctor;
    private static Doctor _currentDoctor;
    private static PatientsBL _patientsBL;
    private static BasicComplaintBL _basicComplaintBL;
    private static MedicalComplaintBL _medicalComplaintBL;
    private static PatientLifestyleBL _patientLifestyleBL;
    private static PersonalDetailsBL _personalDetailsBL;
    private static NextOfKinBL _nextOfKinBL;
    private static AddressBL _addressBL;
    private static PhoneNumberBL _phoneNumberBL;
    private static DoctorBL _doctorBL;
    private static ContactDetailBL _contactDetailBL;
    private static AppointmentBL _appointmentBL;
    private static DrugBL _drugBL;
    private static TestTypeBL _testTypeBL;
    private static List<Doctor> _generalDoctors;
    private static List<Doctor> _specDoctors;
    private static List<Doctor> _allDoctors;
    private static List<Patient> _allPatients;
    private static List<Appointment> _allAppointments;
    private static Boolean addingDoctor;
    private static Boolean addingAppointment;
    private static Boolean addingAppointmentByDoctor;
    private static Patient _currentlySelected;
    private static List<Drug> _allDrugs;
    private static List<TestType> _allTestTypes;
    private static TestBL _testBL;
    private static PrescriptionBL _prescriptionsBL;
    private static DrugPrescriptionBL _drugPrescriptionBL;
    private static BillBL _billBL;
    private static PaymentBL _paymentBL;
    private static Boolean refferingToSpecialist = false;
    private static final String EMAIL_REGEX
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public OutpatientModule() {
        _patientsBL = new PatientsBL();
        _basicComplaintBL = new BasicComplaintBL();
        _medicalComplaintBL = new MedicalComplaintBL();
        _patientLifestyleBL = new PatientLifestyleBL();
        _personalDetailsBL = new PersonalDetailsBL();
        _nextOfKinBL = new NextOfKinBL();
        _addressBL = new AddressBL();
        _phoneNumberBL = new PhoneNumberBL();
        _doctorBL = new DoctorBL();
        _contactDetailBL = new ContactDetailBL();
        _appointmentBL = new AppointmentBL();
        addingDoctor = false;
        addingAppointment = false;
        addingAppointmentByDoctor = false;
        _drugBL = new DrugBL();
        _testTypeBL = new TestTypeBL();
        _testBL = new TestBL();
        _prescriptionsBL = new PrescriptionBL();
        _drugPrescriptionBL = new DrugPrescriptionBL();
        _billBL = new BillBL();
        _paymentBL = new PaymentBL();

        initComponents();

        SetupPatientTable();
        SetupDoctorTable();
        SetupAppointmentTable();
        SetupAppointmentByDocTable();
        SetupBillIssued();
        SetupBillUnissued();

        FillPatientTable();
        FillDoctorTable();
        FillAppointmentTable();
        FillAppointmentByDocTable();
        FillBillUnissued();
        FillBillIssued();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneMain = new javax.swing.JTabbedPane();
        jPanelPatients = new javax.swing.JPanel();
        jLabelPatientTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatients = new javax.swing.JTable();
        jButtonAddSimpleForm = new javax.swing.JButton();
        jButtonAddExtensiveForm = new javax.swing.JButton();
        jButtonEditPatients = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jTabbedPanePatientData = new javax.swing.JTabbedPane();
        jPanelBasicDetailsTab = new javax.swing.JPanel();
        jTextFieldPatientMidd = new javax.swing.JTextField();
        jTextSurrnameEdit = new javax.swing.JTextField();
        jLabelSurname3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jOutpatOPIDEdit = new javax.swing.JFormattedTextField();
        jLabelFirstname3 = new javax.swing.JLabel();
        jTextFieLDPatientFirstEdit = new javax.swing.JTextField();
        jLabelMiddle3 = new javax.swing.JLabel();
        jLabelSex3 = new javax.swing.JLabel();
        jComboBoxSexEdit = new javax.swing.JComboBox<>();
        jLabelBirthdate3 = new javax.swing.JLabel();
        jFormattedTextFieldBirthdateEdit = new javax.swing.JFormattedTextField();
        jLabel51 = new javax.swing.JLabel();
        jComboBoxDoctorsForPatient = new javax.swing.JComboBox<>();
        jPanelContactDetailsTab = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldStatePresentEdit = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldCityPresentEdit = new javax.swing.JTextField();
        jTextFieldStreetPresentEdit = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jFormattedTextFieldHouseNumberPresentEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate = new javax.swing.JLabel();
        jLabelBirthdate1 = new javax.swing.JLabel();
        jFormattedTextFieldAreacodePresentEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate2 = new javax.swing.JLabel();
        jFormattedTextZipcodePresentEdit = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jFormattedTextFieldAreacodePermanentEdit = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabelBirthdate5 = new javax.swing.JLabel();
        jTextFieldStatePermanentEdit = new javax.swing.JTextField();
        jFormattedTextFieldZipcodePermanentEdit = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldCityPermanentEdit = new javax.swing.JTextField();
        jTextFieldStreetPermanentEdit = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jFormattedTextFieldHousenumberPermanentEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate6 = new javax.swing.JLabel();
        jLabelBirthdate4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jFormattedTextFieldPhoneNumWorkEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate7 = new javax.swing.JLabel();
        jFormattedTextFieldPhoneNumHomeEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate8 = new javax.swing.JLabel();
        jFormattedTextFieldPhoneNumMobileEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldFaxEdit = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldEmailEdit = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldPagerEdit = new javax.swing.JTextField();
        jPanelNextKinTab = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldFirstNextOfKinEdit = new javax.swing.JTextField();
        jLabelMiddle4 = new javax.swing.JLabel();
        jTextFieldMiddleNextOfKinEdit = new javax.swing.JTextField();
        jTextFieldSurnameNextOfKinEdit = new javax.swing.JTextField();
        jLabelSurname4 = new javax.swing.JLabel();
        jLabelFirstname4 = new javax.swing.JLabel();
        jLabelSurname14 = new javax.swing.JLabel();
        jTextFieldNextOfKinRelatinshipEdit = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldStateNextOfKinEdit = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldCityNextOfKinEdit = new javax.swing.JTextField();
        jTextFieldStreetNextOfKinEdit = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jFormattedHouseNbNextOfKinEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate15 = new javax.swing.JLabel();
        jLabelBirthdate13 = new javax.swing.JLabel();
        jFormattedAreaNextOfKinEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate14 = new javax.swing.JLabel();
        jFormattedZipCodeNextOfKinEdit = new javax.swing.JFormattedTextField();
        jFormattedTelephoneMobileNextOfKinEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate10 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTelephoneWorkNextOfKinEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate11 = new javax.swing.JLabel();
        jFormattedTelephoneHomeNextOfKinEdit = new javax.swing.JFormattedTextField();
        jLabelBirthdate12 = new javax.swing.JLabel();
        jTextFieldPagerNextOfKinEdit = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldFaxNextOfKinEdit = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldEmailNextOfKinEdit = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jPanelPersonalDetailsTab = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jCheckBoxMarriedEdit = new javax.swing.JCheckBox();
        jLabelFirstname5 = new javax.swing.JLabel();
        jLabelMiddle5 = new javax.swing.JLabel();
        jComboBoxNbDependentsEdit = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabelSurname5 = new javax.swing.JLabel();
        jSliderHeightEdit = new javax.swing.JSlider();
        jLabel37 = new javax.swing.JLabel();
        jSliderWeightEdit = new javax.swing.JSlider();
        jLabelSurname7 = new javax.swing.JLabel();
        jComboBoxBloodTypeEdit = new javax.swing.JComboBox<>();
        jTextFieldOccupationEdit = new javax.swing.JTextField();
        jFormattedTextFieldGrossIncomeEdit = new javax.swing.JFormattedTextField();
        jLabelSurname6 = new javax.swing.JLabel();
        jLabelMiddle6 = new javax.swing.JLabel();
        jLabelSurname8 = new javax.swing.JLabel();
        jPanelLifestyleTab = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabelFirstname6 = new javax.swing.JLabel();
        jCheckBoxVegeterianEdit = new javax.swing.JCheckBox();
        jCheckBoxSmokerEdit = new javax.swing.JCheckBox();
        jLabelFirstname7 = new javax.swing.JLabel();
        jCheckBoxAlcoholEdit = new javax.swing.JCheckBox();
        jLabelFirstname8 = new javax.swing.JLabel();
        jLabelSurname9 = new javax.swing.JLabel();
        jLabelSurname10 = new javax.swing.JLabel();
        jLabelFirstname10 = new javax.swing.JLabel();
        jTextFieldRegularMealsEdit = new javax.swing.JTextField();
        jComboBoxCoffeeEdit = new javax.swing.JComboBox<>();
        jComboBoxSoftDrinksEdit = new javax.swing.JComboBox<>();
        jLabelFirstname9 = new javax.swing.JLabel();
        jTextFieldStimulansEdit = new javax.swing.JTextField();
        jSliderAvgCigarettesEdit = new javax.swing.JSlider();
        jLabelSurname11 = new javax.swing.JLabel();
        jLabelSurname12 = new javax.swing.JLabel();
        jSliderAvgDrinksEdit = new javax.swing.JSlider();
        jLabelFirstname11 = new javax.swing.JLabel();
        jCheckBoxEatingHomeEdit = new javax.swing.JCheckBox();
        jPanelComplaintsTab = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabelFirstname12 = new javax.swing.JLabel();
        jLabelMiddle7 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplainTreatmentHistoryEdit = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintComplaintStatementEdit = new javax.swing.JTextArea();
        jTextFieldHospitalTreatedEdit = new javax.swing.JTextField();
        jLabelSurname13 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextFieldFirstNameDiabeticEdit = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldFirstNameHypertensiveEdit = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintAllergiesEdit = new javax.swing.JTextArea();
        jLabelFirstname16 = new javax.swing.JLabel();
        jLabelFirstname17 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintNeurologicalEdit = new javax.swing.JTextArea();
        jLabelFirstname18 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintDrugEdit = new javax.swing.JTextArea();
        jLabelFirstname19 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintRespiratoryEdit = new javax.swing.JTextArea();
        jLabelFirstname20 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintSurgeriesEdit = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelFirstname14 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintCardiacEdit = new javax.swing.JTextArea();
        jLabelFirstname13 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintDigestiveEdit = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintMuscularEdit = new javax.swing.JTextArea();
        jLabelFirstname21 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextAreaStateOfComplaintOrthopedicEdit = new javax.swing.JTextArea();
        jLabelFirstname15 = new javax.swing.JLabel();
        jButtonPatientsRefresh = new javax.swing.JButton();
        jButtonDeletePatient = new javax.swing.JButton();
        jPanelPersonel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePersonel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonAddDoctor = new javax.swing.JButton();
        jButtonEditPersonel = new javax.swing.JButton();
        jButtonSavePersonel = new javax.swing.JButton();
        jButtonDeleteDoctor = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldFirstNameDoctor = new javax.swing.JTextField();
        jLabelFirstname22 = new javax.swing.JLabel();
        jLabelSurname15 = new javax.swing.JLabel();
        jTextFieldSurnameDoctor = new javax.swing.JTextField();
        jComboBoxDoctorType = new javax.swing.JComboBox<>();
        jLabelSex4 = new javax.swing.JLabel();
        jCheckBoxDocAvailable = new javax.swing.JCheckBox();
        jLabelFirstname23 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextFieldStatePresentDoctor = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextFieldCityPresentDoctor = new javax.swing.JTextField();
        jTextFieldStreetPresentDoctor = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jFormattedTextFieldHouseNumberPresentDoctor = new javax.swing.JFormattedTextField();
        jLabelBirthdate16 = new javax.swing.JLabel();
        jLabelBirthdate17 = new javax.swing.JLabel();
        jFormattedTextFieldAreacodePresentDoctor = new javax.swing.JFormattedTextField();
        jLabelBirthdate18 = new javax.swing.JLabel();
        jFormattedTextZipcodePresentDoctor = new javax.swing.JFormattedTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jFormattedTextFieldPhoneNumWorkDoctor = new javax.swing.JFormattedTextField();
        jLabelBirthdate19 = new javax.swing.JLabel();
        jFormattedTextFieldPhoneNumHomeDoctor = new javax.swing.JFormattedTextField();
        jLabelBirthdate20 = new javax.swing.JLabel();
        jFormattedTextFieldPhoneNumMobileDoctor = new javax.swing.JFormattedTextField();
        jLabelBirthdate21 = new javax.swing.JLabel();
        jTextFieldPagerDoctor = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextFieldFaxDoctor = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextFieldEmailDoctor = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jButtonRefreshPersonel = new javax.swing.JButton();
        jPanelDoctors = new javax.swing.JPanel();
        jComboBoxDoctorsList = new javax.swing.JComboBox<>();
        jComboBoxPatientList = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAppointmentsByDoctor = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButtonRemoveAppointmentByDoctor = new javax.swing.JButton();
        jButtonAddApointmentByDoctor = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDiagnosisByDoctor = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTests = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTablePrescriptions = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButtonReferToSpecialist = new javax.swing.JButton();
        jToggleButtonEditAppointmentByDoctor = new javax.swing.JToggleButton();
        jButtonSaveAppointmentByDoctor = new javax.swing.JButton();
        jButtonRefreshAppointmentsByDoc = new javax.swing.JButton();
        jFormattedAppointmentDateByDoctor = new javax.swing.JFormattedTextField();
        jLabelBirthdate25 = new javax.swing.JLabel();
        jFormattedAppointmentStartByDoctor = new javax.swing.JFormattedTextField();
        jLabelBirthdate26 = new javax.swing.JLabel();
        jLabelBirthdate27 = new javax.swing.JLabel();
        jFormattedAppointmentEndByDoctor = new javax.swing.JFormattedTextField();
        jButtonAddTestByDoctor = new javax.swing.JButton();
        jButtonRemoveTestByDoctor = new javax.swing.JButton();
        jComboBoxListOfTestTypes = new javax.swing.JComboBox<>();
        jComboBoxListOfDrugsForPrescription = new javax.swing.JComboBox<>();
        jButtonAddPrescriptionByDoctor = new javax.swing.JButton();
        jButtonRemovePrescriptionByDoctor = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextAreaInstructions = new javax.swing.JTextArea();
        jLabel54 = new javax.swing.JLabel();
        jComboBoxSpecialistDoctorLists = new javax.swing.JComboBox<>();
        jPanelAppointments = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableAppointments = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jButtonSaveAppointment = new javax.swing.JButton();
        jButtonEditAppointment = new javax.swing.JButton();
        jButtonDeleteAppointment = new javax.swing.JButton();
        jButtonAddAppointment = new javax.swing.JButton();
        jButtonRefreshAppointments = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxAppointmentPatient = new javax.swing.JComboBox<>();
        jComboBoxAppointmentDoctor = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jFormattedAppointmentDate = new javax.swing.JFormattedTextField();
        jLabelBirthdate22 = new javax.swing.JLabel();
        jFormattedAppointmentStart = new javax.swing.JFormattedTextField();
        jLabelBirthdate23 = new javax.swing.JLabel();
        jLabelBirthdate24 = new javax.swing.JLabel();
        jFormattedAppointmentEnd = new javax.swing.JFormattedTextField();
        jPanelBills = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableBillsIssued = new javax.swing.JTable();
        jButtonIssueBill = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTableBillsUnissued = new javax.swing.JTable();
        jLabel55 = new javax.swing.JLabel();
        jButtonRefreshBills = new javax.swing.JButton();
        jCheckBoxCreditCard = new javax.swing.JCheckBox();
        jOutpatCreditCardNumber = new javax.swing.JFormattedTextField();
        jPanelReports = new javax.swing.JPanel();
        jPanelTitleBackground = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(247, 252, 255));
        setLocation(new java.awt.Point(0, 0));

        jTabbedPaneMain.setBackground(new java.awt.Color(241, 240, 240));
        jTabbedPaneMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPaneMain.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jPanelPatients.setBackground(new java.awt.Color(255, 255, 255));

        jLabelPatientTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelPatientTitle.setText("Patients");

        jTablePatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ){public boolean isCellEditable(int row, int column){return false;}}
    );
    jTablePatients.getTableHeader().setReorderingAllowed(false);
    jTablePatients.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTablePatientsMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(jTablePatients);

    jButtonAddSimpleForm.setBackground(new java.awt.Color(204, 255, 204));
    jButtonAddSimpleForm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonAddSimpleForm.setText("Add simple");
    jButtonAddSimpleForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonAddSimpleForm.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonAddSimpleFormMouseClicked(evt);
        }
    });

    jButtonAddExtensiveForm.setBackground(new java.awt.Color(0, 204, 102));
    jButtonAddExtensiveForm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonAddExtensiveForm.setText("Add extensive");
    jButtonAddExtensiveForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonAddExtensiveForm.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonAddExtensiveFormMouseClicked(evt);
        }
    });

    jButtonEditPatients.setBackground(new java.awt.Color(255, 204, 51));
    jButtonEditPatients.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonEditPatients.setText("Edit");
    jButtonEditPatients.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonEditPatients.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonEditPatientsMouseClicked(evt);
        }
    });

    jButtonSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonSave.setText("Save");
    jButtonSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonSave.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonSaveMouseClicked(evt);
        }
    });

    jTabbedPanePatientData.setTabPlacement(javax.swing.JTabbedPane.LEFT);

    jTextFieldPatientMidd.setEnabled(false);
    jTextFieldPatientMidd.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldPatientMiddKeyTyped(evt);
        }
    });

    jTextSurrnameEdit.setEnabled(false);
    jTextSurrnameEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextSurrnameEditKeyTyped(evt);
        }
    });

    jLabelSurname3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname3.setText("Surname:");

    jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel13.setText("Outpatient ID (OPID):");

    jOutpatOPIDEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jOutpatOPIDEdit.setEnabled(false);

    jLabelFirstname3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname3.setText("First name:");

    jTextFieLDPatientFirstEdit.setEnabled(false);
    jTextFieLDPatientFirstEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieLDPatientFirstEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabelMiddle3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelMiddle3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelMiddle3.setText("Middle name:");

    jLabelSex3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSex3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSex3.setText("Sex(M/F):");

    jComboBoxSexEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
    jComboBoxSexEdit.setEnabled(false);

    jLabelBirthdate3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate3.setText("Date of Birth(dd/mm/yyyy):");

    jFormattedTextFieldBirthdateEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    jFormattedTextFieldBirthdateEdit.setText("01/01/2000");
    jFormattedTextFieldBirthdateEdit.setEnabled(false);

    jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel51.setText("Doctor:");

    jComboBoxDoctorsForPatient.setEnabled(false);

    javax.swing.GroupLayout jPanelBasicDetailsTabLayout = new javax.swing.GroupLayout(jPanelBasicDetailsTab);
    jPanelBasicDetailsTab.setLayout(jPanelBasicDetailsTabLayout);
    jPanelBasicDetailsTabLayout.setHorizontalGroup(
        jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBasicDetailsTabLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelSex3)
            .addGap(18, 18, 18)
            .addComponent(jComboBoxSexEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(52, 52, 52))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBasicDetailsTabLayout.createSequentialGroup()
            .addGap(142, 142, 142)
            .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelBasicDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelMiddle3)
                        .addComponent(jLabelSurname3)
                        .addComponent(jLabelFirstname3))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieLDPatientFirstEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addComponent(jTextFieldPatientMidd, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextSurrnameEdit)))
                .addGroup(jPanelBasicDetailsTabLayout.createSequentialGroup()
                    .addComponent(jLabel13)
                    .addGap(18, 18, 18)
                    .addComponent(jOutpatOPIDEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
            .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBasicDetailsTabLayout.createSequentialGroup()
                    .addComponent(jLabel51)
                    .addGap(30, 30, 30)
                    .addComponent(jComboBoxDoctorsForPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelBasicDetailsTabLayout.createSequentialGroup()
                    .addComponent(jLabelBirthdate3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jFormattedTextFieldBirthdateEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(36, 36, 36))
    );
    jPanelBasicDetailsTabLayout.setVerticalGroup(
        jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBasicDetailsTabLayout.createSequentialGroup()
            .addGap(39, 39, 39)
            .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jOutpatOPIDEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(3, 3, 3)
            .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelSex3)
                .addComponent(jComboBoxSexEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(28, 28, 28)
            .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelBasicDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFirstname3)
                        .addComponent(jTextFieLDPatientFirstEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMiddle3)
                        .addComponent(jTextFieldPatientMidd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBirthdate3)
                    .addComponent(jFormattedTextFieldBirthdateEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextSurrnameEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelSurname3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelBasicDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel51)
                .addComponent(jComboBoxDoctorsForPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(71, Short.MAX_VALUE))
    );

    jTabbedPanePatientData.addTab("Basic Details", jPanelBasicDetailsTab);

    jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel14.setText("State:");

    jTextFieldStatePresentEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStatePresentEdit.setEnabled(false);
    jTextFieldStatePresentEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldStatePresentEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel15.setText("City:");

    jTextFieldCityPresentEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldCityPresentEdit.setEnabled(false);
    jTextFieldCityPresentEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldCityPresentEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jTextFieldStreetPresentEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStreetPresentEdit.setEnabled(false);

    jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel16.setText("Street:");

    jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel17.setText("Present Address");

    jFormattedTextFieldHouseNumberPresentEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
    jFormattedTextFieldHouseNumberPresentEdit.setEnabled(false);
    jFormattedTextFieldHouseNumberPresentEdit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextFieldHouseNumberPresentEditActionPerformed(evt);
        }
    });

    jLabelBirthdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate.setText("House number:");

    jLabelBirthdate1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate1.setText("Area code:");

    try {
        jFormattedTextFieldAreacodePresentEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*******")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextFieldAreacodePresentEdit.setEnabled(false);

    jLabelBirthdate2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate2.setText("Zip code:");

    jFormattedTextZipcodePresentEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("######"))));
    jFormattedTextZipcodePresentEdit.setEnabled(false);

    jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel18.setText("Permament Address");

    try {
        jFormattedTextFieldAreacodePermanentEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*******")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextFieldAreacodePermanentEdit.setEnabled(false);

    jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel19.setText("State:");

    jLabelBirthdate5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate5.setText("Zip code:");

    jTextFieldStatePermanentEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStatePermanentEdit.setEnabled(false);
    jTextFieldStatePermanentEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldStatePermanentEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jFormattedTextFieldZipcodePermanentEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("######"))));
    jFormattedTextFieldZipcodePermanentEdit.setEnabled(false);

    jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel20.setText("City:");

    jTextFieldCityPermanentEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldCityPermanentEdit.setEnabled(false);
    jTextFieldCityPermanentEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldCityPermanentEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jTextFieldStreetPermanentEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStreetPermanentEdit.setEnabled(false);

    jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel21.setText("Street:");

    jFormattedTextFieldHousenumberPermanentEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
    jFormattedTextFieldHousenumberPermanentEdit.setEnabled(false);

    jLabelBirthdate6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate6.setText("House number:");

    jLabelBirthdate4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate4.setText("Area code:");

    jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel22.setText("Telephones/Fax/Email");

    jFormattedTextFieldPhoneNumWorkEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTextFieldPhoneNumWorkEdit.setToolTipText("+385");
    jFormattedTextFieldPhoneNumWorkEdit.setEnabled(false);

    jLabelBirthdate7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate7.setText("Telephone (Work):");

    jFormattedTextFieldPhoneNumHomeEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTextFieldPhoneNumHomeEdit.setToolTipText("+385");
    jFormattedTextFieldPhoneNumHomeEdit.setEnabled(false);

    jLabelBirthdate8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate8.setText("Telephone (Home):");

    jFormattedTextFieldPhoneNumMobileEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTextFieldPhoneNumMobileEdit.setToolTipText("+385");
    jFormattedTextFieldPhoneNumMobileEdit.setEnabled(false);

    jLabelBirthdate9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate9.setText("Mobile:");

    jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel23.setText("Pager:");

    jTextFieldFaxEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldFaxEdit.setEnabled(false);
    jTextFieldFaxEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldFaxEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel24.setText("Fax:");

    jTextFieldEmailEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldEmailEdit.setEnabled(false);
    jTextFieldEmailEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldEmailEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel25.setText("Email:");

    jTextFieldPagerEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldPagerEdit.setEnabled(false);
    jTextFieldPagerEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldPagerEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    javax.swing.GroupLayout jPanelContactDetailsTabLayout = new javax.swing.GroupLayout(jPanelContactDetailsTab);
    jPanelContactDetailsTab.setLayout(jPanelContactDetailsTabLayout);
    jPanelContactDetailsTabLayout.setHorizontalGroup(
        jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelBirthdate2)
                        .addComponent(jLabelBirthdate1)
                        .addComponent(jLabelBirthdate))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jFormattedTextFieldHouseNumberPresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextFieldAreacodePresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextZipcodePresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldStatePresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldCityPresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addComponent(jLabel16)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTextFieldStreetPresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(46, 46, 46)
            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldStatePermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCityPermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                        .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelBirthdate5)
                            .addComponent(jLabelBirthdate4)
                            .addComponent(jLabel21)
                            .addComponent(jLabelBirthdate6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldHousenumberPermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldStreetPermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldAreacodePermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldZipcodePermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addComponent(jLabel22)
                    .addGap(71, 71, 71))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createSequentialGroup()
                                .addComponent(jLabelBirthdate7)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldPhoneNumWorkEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                                    .addComponent(jLabelBirthdate9)
                                    .addGap(18, 18, 18)
                                    .addComponent(jFormattedTextFieldPhoneNumMobileEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                                    .addComponent(jLabelBirthdate8)
                                    .addGap(18, 18, 18)
                                    .addComponent(jFormattedTextFieldPhoneNumHomeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldFaxEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldEmailEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldPagerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(23, 23, 23))))
    );
    jPanelContactDetailsTabLayout.setVerticalGroup(
        jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel22))
                    .addGap(32, 32, 32)
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jTextFieldStatePermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(jTextFieldCityPermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(jTextFieldStreetPermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldPhoneNumWorkEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelBirthdate7))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldPhoneNumHomeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelBirthdate8))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldPhoneNumMobileEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelBirthdate9))))
                    .addGap(31, 31, 31)
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelBirthdate6)
                                .addComponent(jFormattedTextFieldHousenumberPermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelBirthdate4)
                                .addComponent(jFormattedTextFieldAreacodePermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelBirthdate5)
                                .addComponent(jFormattedTextFieldZipcodePermanentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jTextFieldEmailEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(jTextFieldFaxEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(jTextFieldPagerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel17)
                        .addGroup(jPanelContactDetailsTabLayout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jTextFieldStatePresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jTextFieldCityPresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jTextFieldStreetPresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBirthdate)
                        .addComponent(jFormattedTextFieldHouseNumberPresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBirthdate1)
                        .addComponent(jFormattedTextFieldAreacodePresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelContactDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBirthdate2)
                        .addComponent(jFormattedTextZipcodePresentEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(0, 30, Short.MAX_VALUE))
    );

    jTabbedPanePatientData.addTab("Contact Details", jPanelContactDetailsTab);

    jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel26.setText("Basic data");

    jTextFieldFirstNextOfKinEdit.setEnabled(false);
    jTextFieldFirstNextOfKinEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldFirstNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabelMiddle4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelMiddle4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelMiddle4.setText("Middle name:");

    jTextFieldMiddleNextOfKinEdit.setEnabled(false);
    jTextFieldMiddleNextOfKinEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldMiddleNextOfKinEditKeyTyped(evt);
        }
    });

    jTextFieldSurnameNextOfKinEdit.setEnabled(false);
    jTextFieldSurnameNextOfKinEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldSurnameNextOfKinEditKeyTyped(evt);
        }
    });

    jLabelSurname4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname4.setText("Surname:");

    jLabelFirstname4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname4.setText("First name:");

    jLabelSurname14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname14.setText("Outpatient relationship:");

    jTextFieldNextOfKinRelatinshipEdit.setEnabled(false);
    jTextFieldNextOfKinRelatinshipEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldNextOfKinRelatinshipEditKeyTyped(evt);
        }
    });

    jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel27.setText("Present Address");

    jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel28.setText("State:");

    jTextFieldStateNextOfKinEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStateNextOfKinEdit.setEnabled(false);
    jTextFieldStateNextOfKinEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldStateNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel29.setText("City:");

    jTextFieldCityNextOfKinEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldCityNextOfKinEdit.setEnabled(false);
    jTextFieldCityNextOfKinEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldCityNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jTextFieldStreetNextOfKinEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStreetNextOfKinEdit.setEnabled(false);
    jTextFieldStreetNextOfKinEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldStreetNextOfKinEditKeyTyped(evt);
        }
    });

    jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel30.setText("Street:");

    jFormattedHouseNbNextOfKinEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
    jFormattedHouseNbNextOfKinEdit.setEnabled(false);

    jLabelBirthdate15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate15.setText("House number:");

    jLabelBirthdate13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate13.setText("Area code:");

    try {
        jFormattedAreaNextOfKinEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*******")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedAreaNextOfKinEdit.setEnabled(false);

    jLabelBirthdate14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate14.setText("Zip code:");

    jFormattedZipCodeNextOfKinEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedZipCodeNextOfKinEdit.setEnabled(false);

    jFormattedTelephoneMobileNextOfKinEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTelephoneMobileNextOfKinEdit.setToolTipText("+385");
    jFormattedTelephoneMobileNextOfKinEdit.setEnabled(false);

    jLabelBirthdate10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate10.setText("Mobile:");

    jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel31.setText("Telephones/Fax/Email");

    jFormattedTelephoneWorkNextOfKinEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTelephoneWorkNextOfKinEdit.setToolTipText("+385");
    jFormattedTelephoneWorkNextOfKinEdit.setEnabled(false);

    jLabelBirthdate11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate11.setText("Telephone (Work):");

    jFormattedTelephoneHomeNextOfKinEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTelephoneHomeNextOfKinEdit.setToolTipText("+385");
    jFormattedTelephoneHomeNextOfKinEdit.setEnabled(false);

    jLabelBirthdate12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate12.setText("Telephone (Home):");

    jTextFieldPagerNextOfKinEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldPagerNextOfKinEdit.setEnabled(false);

    jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel32.setText("Pager:");

    jTextFieldFaxNextOfKinEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldFaxNextOfKinEdit.setEnabled(false);

    jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel33.setText("Fax:");

    jTextFieldEmailNextOfKinEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldEmailNextOfKinEdit.setEnabled(false);

    jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel34.setText("Email:");

    javax.swing.GroupLayout jPanelNextKinTabLayout = new javax.swing.GroupLayout(jPanelNextKinTab);
    jPanelNextKinTab.setLayout(jPanelNextKinTabLayout);
    jPanelNextKinTabLayout.setHorizontalGroup(
        jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelFirstname4)
                                .addComponent(jLabelMiddle4)
                                .addComponent(jLabelSurname4))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldSurnameNextOfKinEdit, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldFirstNextOfKinEdit, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldMiddleNextOfKinEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                            .addGap(95, 95, 95)
                            .addComponent(jLabel26)))
                    .addGap(46, 46, 46)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldCityNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNextKinTabLayout.createSequentialGroup()
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(jTextFieldStateNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldStreetNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNextKinTabLayout.createSequentialGroup()
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelBirthdate14)
                                        .addComponent(jLabelBirthdate13))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jFormattedAreaNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFormattedZipCodeNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                                    .addComponent(jLabelBirthdate15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jFormattedHouseNbNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(40, 40, 40)))
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNextKinTabLayout.createSequentialGroup()
                                    .addComponent(jLabelBirthdate11)
                                    .addGap(18, 18, 18)
                                    .addComponent(jFormattedTelephoneWorkNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2))
                                .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                                        .addComponent(jLabelBirthdate10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jFormattedTelephoneMobileNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                                        .addComponent(jLabelBirthdate12)
                                        .addGap(18, 18, 18)
                                        .addComponent(jFormattedTelephoneHomeNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNextKinTabLayout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldFaxNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNextKinTabLayout.createSequentialGroup()
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldEmailNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNextKinTabLayout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldPagerNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNextKinTabLayout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(37, 37, 37))))
                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                    .addComponent(jLabelSurname14)
                    .addGap(18, 18, 18)
                    .addComponent(jTextFieldNextOfKinRelatinshipEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanelNextKinTabLayout.setVerticalGroup(
        jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jLabel27)
                        .addComponent(jLabel31))
                    .addGap(27, 27, 27)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelFirstname4)
                                .addComponent(jTextFieldFirstNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelMiddle4)
                                .addComponent(jTextFieldMiddleNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(jTextFieldStateNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jTextFieldCityNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSurname4)
                            .addComponent(jTextFieldSurnameNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jTextFieldStreetNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTelephoneWorkNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBirthdate11))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTelephoneHomeNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBirthdate12))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTelephoneMobileNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBirthdate10))))
            .addGap(18, 18, 18)
            .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBirthdate15)
                        .addComponent(jFormattedHouseNbNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSurname14)
                        .addComponent(jTextFieldNextOfKinRelatinshipEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBirthdate13)
                        .addComponent(jFormattedAreaNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBirthdate14)
                        .addComponent(jFormattedZipCodeNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelNextKinTabLayout.createSequentialGroup()
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(jTextFieldEmailNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(jTextFieldFaxNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelNextKinTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jTextFieldPagerNextOfKinEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(25, Short.MAX_VALUE))
    );

    jTabbedPanePatientData.addTab("Next Of Kin", jPanelNextKinTab);

    jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel35.setText("Details");

    jCheckBoxMarriedEdit.setText("Married");
    jCheckBoxMarriedEdit.setEnabled(false);

    jLabelFirstname5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname5.setText("Marital status");

    jLabelMiddle5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelMiddle5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelMiddle5.setText("Number of dependents:");

    jComboBoxNbDependentsEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
    jComboBoxNbDependentsEdit.setEnabled(false);

    jLabel36.setText("cm");

    jLabelSurname5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname5.setText("Height:");

    jSliderHeightEdit.setMajorTickSpacing(5);
    jSliderHeightEdit.setMaximum(210);
    jSliderHeightEdit.setMinimum(150);
    jSliderHeightEdit.setMinorTickSpacing(2);
    jSliderHeightEdit.setPaintLabels(true);
    jSliderHeightEdit.setPaintTicks(true);
    jSliderHeightEdit.setSnapToTicks(true);
    jSliderHeightEdit.setEnabled(false);

    jLabel37.setText("kg");

    jSliderWeightEdit.setMajorTickSpacing(10);
    jSliderWeightEdit.setMaximum(120);
    jSliderWeightEdit.setMinimum(3);
    jSliderWeightEdit.setMinorTickSpacing(2);
    jSliderWeightEdit.setPaintLabels(true);
    jSliderWeightEdit.setPaintTicks(true);
    jSliderWeightEdit.setSnapToTicks(true);
    jSliderWeightEdit.setEnabled(false);

    jLabelSurname7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname7.setText("Weight:");

    jComboBoxBloodTypeEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "AB", "0" }));
    jComboBoxBloodTypeEdit.setEnabled(false);

    jTextFieldOccupationEdit.setEnabled(false);
    jTextFieldOccupationEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldOccupationEditKeyTyped(evt);
        }
    });

    jFormattedTextFieldGrossIncomeEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("########"))));
    jFormattedTextFieldGrossIncomeEdit.setEnabled(false);

    jLabelSurname6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname6.setText("Blood Type - RH:");

    jLabelMiddle6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelMiddle6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelMiddle6.setText("Occupation:");

    jLabelSurname8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname8.setText("Gross Annual Income:");

    javax.swing.GroupLayout jPanelPersonalDetailsTabLayout = new javax.swing.GroupLayout(jPanelPersonalDetailsTab);
    jPanelPersonalDetailsTab.setLayout(jPanelPersonalDetailsTabLayout);
    jPanelPersonalDetailsTabLayout.setHorizontalGroup(
        jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
            .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jLabelFirstname5)
                            .addGap(18, 18, 18)
                            .addComponent(jCheckBoxMarriedEdit))))
                .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                                .addComponent(jLabelSurname7)
                                .addGap(529, 529, 529))
                            .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jSliderWeightEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPersonalDetailsTabLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                                .addComponent(jLabelSurname5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSliderHeightEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                                .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                                        .addComponent(jLabelMiddle5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxNbDependentsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabelSurname6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxBloodTypeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(83, 83, 83)))
                                .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelSurname8)
                                    .addComponent(jLabelMiddle6))
                                .addGap(37, 37, 37)
                                .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jFormattedTextFieldGrossIncomeEdit, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldOccupationEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
            .addGap(32, 32, 32))
    );
    jPanelPersonalDetailsTabLayout.setVerticalGroup(
        jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                            .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelFirstname5)
                                .addComponent(jCheckBoxMarriedEdit))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelMiddle5)
                                .addComponent(jComboBoxNbDependentsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSurname6)
                            .addComponent(jComboBoxBloodTypeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(31, 31, 31)
                    .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                            .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelSurname5)
                                .addComponent(jSliderHeightEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                            .addComponent(jLabelSurname7)
                            .addGap(20, 20, 20))
                        .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSliderWeightEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(71, Short.MAX_VALUE))
                .addGroup(jPanelPersonalDetailsTabLayout.createSequentialGroup()
                    .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMiddle6)
                        .addComponent(jTextFieldOccupationEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelPersonalDetailsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSurname8)
                        .addComponent(jFormattedTextFieldGrossIncomeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    jTabbedPanePatientData.addTab("Personal Details", jPanelPersonalDetailsTab);

    jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel38.setText("Lifestyle");

    jLabelFirstname6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname6.setText("Vegeterian:");

    jCheckBoxVegeterianEdit.setText("Yes");
    jCheckBoxVegeterianEdit.setEnabled(false);

    jCheckBoxSmokerEdit.setText("Yes");
    jCheckBoxSmokerEdit.setEnabled(false);

    jLabelFirstname7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname7.setText("Smoker:");

    jCheckBoxAlcoholEdit.setText("Yes");
    jCheckBoxAlcoholEdit.setEnabled(false);

    jLabelFirstname8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname8.setText("Consuming alcohol:");

    jLabelSurname9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname9.setText("Coffe per day:");

    jLabelSurname10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname10.setText("Soft drinks per day:");

    jLabelFirstname10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname10.setText("Regular meals:");

    jTextFieldRegularMealsEdit.setEnabled(false);

    jComboBoxCoffeeEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
    jComboBoxCoffeeEdit.setEnabled(false);

    jComboBoxSoftDrinksEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
    jComboBoxSoftDrinksEdit.setEnabled(false);

    jLabelFirstname9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname9.setText("Using stimulans:");

    jTextFieldStimulansEdit.setEnabled(false);

    jSliderAvgCigarettesEdit.setMajorTickSpacing(5);
    jSliderAvgCigarettesEdit.setMaximum(40);
    jSliderAvgCigarettesEdit.setMinorTickSpacing(2);
    jSliderAvgCigarettesEdit.setPaintLabels(true);
    jSliderAvgCigarettesEdit.setPaintTicks(true);
    jSliderAvgCigarettesEdit.setSnapToTicks(true);
    jSliderAvgCigarettesEdit.setEnabled(false);

    jLabelSurname11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname11.setText("Avg cigarettes per day:");

    jLabelSurname12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname12.setText("Avg drinks per day:");

    jSliderAvgDrinksEdit.setMajorTickSpacing(1);
    jSliderAvgDrinksEdit.setMaximum(8);
    jSliderAvgDrinksEdit.setMinorTickSpacing(1);
    jSliderAvgDrinksEdit.setPaintLabels(true);
    jSliderAvgDrinksEdit.setPaintTicks(true);
    jSliderAvgDrinksEdit.setSnapToTicks(true);
    jSliderAvgDrinksEdit.setEnabled(false);

    jLabelFirstname11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname11.setText("Eating home predominantely:");

    jCheckBoxEatingHomeEdit.setText("Yes");
    jCheckBoxEatingHomeEdit.setEnabled(false);

    javax.swing.GroupLayout jPanelLifestyleTabLayout = new javax.swing.GroupLayout(jPanelLifestyleTab);
    jPanelLifestyleTab.setLayout(jPanelLifestyleTabLayout);
    jPanelLifestyleTabLayout.setHorizontalGroup(
        jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jLabelFirstname6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBoxVegeterianEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSurname11)
                            .addGap(18, 18, 18)
                            .addComponent(jSliderAvgCigarettesEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                            .addComponent(jLabelFirstname7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBoxSmokerEdit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSurname12)
                            .addGap(29, 29, 29)
                            .addComponent(jSliderAvgDrinksEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                    .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelSurname10)
                                .addComponent(jLabelSurname9))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxSoftDrinksEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxCoffeeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(144, 144, 144))
                        .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                                .addComponent(jLabelFirstname8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxAlcoholEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                                .addComponent(jLabelFirstname9)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldStimulansEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(63, 63, 63)
                    .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                            .addComponent(jLabelFirstname11)
                            .addGap(18, 18, 18)
                            .addComponent(jCheckBoxEatingHomeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                            .addComponent(jLabelFirstname10)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldRegularMealsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 146, Short.MAX_VALUE)))
            .addGap(52, 52, 52))
    );
    jPanelLifestyleTabLayout.setVerticalGroup(
        jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFirstname6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxVegeterianEdit))
                    .addGap(18, 18, 18))
                .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabelSurname11))
                        .addComponent(jSliderAvgCigarettesEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderAvgDrinksEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLifestyleTabLayout.createSequentialGroup()
                        .addComponent(jLabelSurname12)
                        .addGap(17, 17, 17)))
                .addGroup(jPanelLifestyleTabLayout.createSequentialGroup()
                    .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFirstname7)
                        .addComponent(jCheckBoxSmokerEdit))
                    .addGap(28, 28, 28)
                    .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFirstname8)
                        .addComponent(jCheckBoxAlcoholEdit))))
            .addGap(22, 22, 22)
            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelFirstname9)
                .addComponent(jTextFieldStimulansEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelFirstname11)
                .addComponent(jCheckBoxEatingHomeEdit))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelSurname9)
                .addComponent(jComboBoxCoffeeEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelLifestyleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSurname10)
                    .addComponent(jComboBoxSoftDrinksEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jTextFieldRegularMealsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelFirstname10))
            .addGap(49, 49, 49))
    );

    jTabbedPanePatientData.addTab("Lifestyle", jPanelLifestyleTab);

    jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel39.setText("Basic complaints");

    jLabelFirstname12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname12.setText("Complaint statement:");

    jLabelMiddle7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelMiddle7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelMiddle7.setText("Treatment history:");

    jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplainTreatmentHistoryEdit.setColumns(20);
    jTextAreaStateOfComplainTreatmentHistoryEdit.setLineWrap(true);
    jTextAreaStateOfComplainTreatmentHistoryEdit.setRows(5);
    jTextAreaStateOfComplainTreatmentHistoryEdit.setText("-");
    jTextAreaStateOfComplainTreatmentHistoryEdit.setWrapStyleWord(true);
    jTextAreaStateOfComplainTreatmentHistoryEdit.setEnabled(false);
    jScrollPane9.setViewportView(jTextAreaStateOfComplainTreatmentHistoryEdit);

    jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintComplaintStatementEdit.setColumns(20);
    jTextAreaStateOfComplaintComplaintStatementEdit.setLineWrap(true);
    jTextAreaStateOfComplaintComplaintStatementEdit.setRows(5);
    jTextAreaStateOfComplaintComplaintStatementEdit.setText("-");
    jTextAreaStateOfComplaintComplaintStatementEdit.setWrapStyleWord(true);
    jTextAreaStateOfComplaintComplaintStatementEdit.setEnabled(false);
    jScrollPane10.setViewportView(jTextAreaStateOfComplaintComplaintStatementEdit);

    jTextFieldHospitalTreatedEdit.setText("-");
    jTextFieldHospitalTreatedEdit.setEnabled(false);

    jLabelSurname13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname13.setText("Hospital treated:");

    jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel40.setText("Medical complaints");

    jTextFieldFirstNameDiabeticEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldFirstNameDiabeticEdit.setText("-");
    jTextFieldFirstNameDiabeticEdit.setEnabled(false);
    jTextFieldFirstNameDiabeticEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldFirstNameDiabeticEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel41.setText("Diabetic:");

    jTextFieldFirstNameHypertensiveEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldFirstNameHypertensiveEdit.setText("-");
    jTextFieldFirstNameHypertensiveEdit.setEnabled(false);
    jTextFieldFirstNameHypertensiveEdit.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldFirstNameHypertensiveEditjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel42.setText("Hypertensive:");

    jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintAllergiesEdit.setColumns(20);
    jTextAreaStateOfComplaintAllergiesEdit.setRows(5);
    jTextAreaStateOfComplaintAllergiesEdit.setText("-");
    jTextAreaStateOfComplaintAllergiesEdit.setEnabled(false);
    jScrollPane11.setViewportView(jTextAreaStateOfComplaintAllergiesEdit);

    jLabelFirstname16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname16.setText("Allergies:");

    jLabelFirstname17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname17.setText("Neurological condition:");

    jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintNeurologicalEdit.setColumns(20);
    jTextAreaStateOfComplaintNeurologicalEdit.setRows(5);
    jTextAreaStateOfComplaintNeurologicalEdit.setText("-");
    jTextAreaStateOfComplaintNeurologicalEdit.setEnabled(false);
    jScrollPane12.setViewportView(jTextAreaStateOfComplaintNeurologicalEdit);

    jLabelFirstname18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname18.setText("Drug reactions:");

    jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintDrugEdit.setColumns(20);
    jTextAreaStateOfComplaintDrugEdit.setRows(5);
    jTextAreaStateOfComplaintDrugEdit.setText("-");
    jTextAreaStateOfComplaintDrugEdit.setEnabled(false);
    jScrollPane13.setViewportView(jTextAreaStateOfComplaintDrugEdit);

    jLabelFirstname19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname19.setText("Respiratory condition:");

    jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintRespiratoryEdit.setColumns(20);
    jTextAreaStateOfComplaintRespiratoryEdit.setRows(5);
    jTextAreaStateOfComplaintRespiratoryEdit.setText("-");
    jTextAreaStateOfComplaintRespiratoryEdit.setEnabled(false);
    jScrollPane14.setViewportView(jTextAreaStateOfComplaintRespiratoryEdit);

    jLabelFirstname20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname20.setText("History of surgeries:");

    jScrollPane15.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintSurgeriesEdit.setColumns(20);
    jTextAreaStateOfComplaintSurgeriesEdit.setRows(5);
    jTextAreaStateOfComplaintSurgeriesEdit.setText("-");
    jTextAreaStateOfComplaintSurgeriesEdit.setEnabled(false);
    jScrollPane15.setViewportView(jTextAreaStateOfComplaintSurgeriesEdit);

    jLabelFirstname14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname14.setText("Digestive condition:");

    jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintCardiacEdit.setColumns(20);
    jTextAreaStateOfComplaintCardiacEdit.setRows(5);
    jTextAreaStateOfComplaintCardiacEdit.setText("-");
    jTextAreaStateOfComplaintCardiacEdit.setEnabled(false);
    jScrollPane16.setViewportView(jTextAreaStateOfComplaintCardiacEdit);

    jLabelFirstname13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname13.setText("Cardiac condition:");

    jScrollPane17.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintDigestiveEdit.setColumns(20);
    jTextAreaStateOfComplaintDigestiveEdit.setRows(5);
    jTextAreaStateOfComplaintDigestiveEdit.setText("-");
    jTextAreaStateOfComplaintDigestiveEdit.setEnabled(false);
    jScrollPane17.setViewportView(jTextAreaStateOfComplaintDigestiveEdit);

    jScrollPane18.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintMuscularEdit.setColumns(20);
    jTextAreaStateOfComplaintMuscularEdit.setRows(5);
    jTextAreaStateOfComplaintMuscularEdit.setText("-");
    jTextAreaStateOfComplaintMuscularEdit.setEnabled(false);
    jScrollPane18.setViewportView(jTextAreaStateOfComplaintMuscularEdit);

    jLabelFirstname21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname21.setText("Muscular condition:");

    jScrollPane19.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jTextAreaStateOfComplaintOrthopedicEdit.setColumns(20);
    jTextAreaStateOfComplaintOrthopedicEdit.setRows(5);
    jTextAreaStateOfComplaintOrthopedicEdit.setText("-");
    jTextAreaStateOfComplaintOrthopedicEdit.setEnabled(false);
    jScrollPane19.setViewportView(jTextAreaStateOfComplaintOrthopedicEdit);

    jLabelFirstname15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname15.setText("Orthopedic condition:");

    javax.swing.GroupLayout jPanelComplaintsTabLayout = new javax.swing.GroupLayout(jPanelComplaintsTab);
    jPanelComplaintsTab.setLayout(jPanelComplaintsTabLayout);
    jPanelComplaintsTabLayout.setHorizontalGroup(
        jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jLabel39)
                    .addGap(229, 229, 229)
                    .addComponent(jLabel40)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelComplaintsTabLayout.createSequentialGroup()
                    .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelComplaintsTabLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                                    .addComponent(jLabelMiddle7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                                    .addComponent(jLabelFirstname12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldFirstNameDiabeticEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldFirstNameHypertensiveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFirstname18, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelFirstname16, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(47, 47, 47))
                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                    .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addComponent(jLabelSurname13)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldHospitalTreatedEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabelFirstname17)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGap(88, 88, 88))
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFirstname14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelFirstname13, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                                    .addComponent(jLabelFirstname21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                                    .addComponent(jLabelFirstname15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelFirstname19)
                                .addComponent(jLabelFirstname20))))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(33, 33, 33)))
            .addContainerGap())
    );
    jPanelComplaintsTabLayout.setVerticalGroup(
        jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel39)
                .addComponent(jLabel40))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                    .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFirstname12)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelMiddle7)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel41)
                                .addComponent(jTextFieldFirstNameDiabeticEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel42)
                                .addComponent(jTextFieldFirstNameHypertensiveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSurname13)
                        .addComponent(jTextFieldHospitalTreatedEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabelFirstname16)
                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addComponent(jLabelFirstname18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFirstname17)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addGap(9, 9, 9)
            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                    .addComponent(jLabelFirstname19)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                    .addComponent(jLabelFirstname20)
                    .addGap(22, 22, 22))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelComplaintsTabLayout.createSequentialGroup()
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFirstname13)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFirstname14)
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFirstname15)
                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelComplaintsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelFirstname21)))))
                .addGroup(jPanelComplaintsTabLayout.createSequentialGroup()
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );

    jTabbedPanePatientData.addTab("Complaints", jPanelComplaintsTab);

    jButtonPatientsRefresh.setBackground(new java.awt.Color(51, 153, 255));
    jButtonPatientsRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonPatientsRefresh.setText("Refresh");
    jButtonPatientsRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonPatientsRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonPatientsRefreshMouseClicked(evt);
        }
    });

    jButtonDeletePatient.setBackground(new java.awt.Color(255, 102, 102));
    jButtonDeletePatient.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonDeletePatient.setText("Delete");
    jButtonDeletePatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonDeletePatient.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonDeletePatientMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanelPatientsLayout = new javax.swing.GroupLayout(jPanelPatients);
    jPanelPatients.setLayout(jPanelPatientsLayout);
    jPanelPatientsLayout.setHorizontalGroup(
        jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPatientsLayout.createSequentialGroup()
            .addGroup(jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPatientsLayout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(jLabelPatientTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelPatientsLayout.createSequentialGroup()
                    .addGroup(jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPatientsLayout.createSequentialGroup()
                            .addGap(116, 116, 116)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPatientsLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(jTabbedPanePatientData, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPatientsLayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonAddSimpleForm, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                .addComponent(jButtonAddExtensiveForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDeletePatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonEditPatients, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPatientsLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonPatientsRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanelPatientsLayout.setVerticalGroup(
        jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPatientsLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabelPatientTitle)
            .addGap(18, 18, 18)
            .addGroup(jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanelPatientsLayout.createSequentialGroup()
                    .addComponent(jButtonPatientsRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(75, 75, 75)
                    .addComponent(jButtonAddSimpleForm, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddExtensiveForm, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelPatientsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelPatientsLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                    .addComponent(jTabbedPanePatientData, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelPatientsLayout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addComponent(jButtonDeletePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEditPatients, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(42, 42, 42)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(23, 23, 23))
    );

    jTabbedPaneMain.addTab("Patients", jPanelPatients);

    jPanelPersonel.setBackground(new java.awt.Color(255, 255, 255));

    jTablePersonel.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){return false;}});
    jTablePersonel.getTableHeader().setReorderingAllowed(false);
    jTablePersonel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTablePersonelMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(jTablePersonel);

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel1.setText("Personel");

    jButtonAddDoctor.setBackground(new java.awt.Color(51, 204, 0));
    jButtonAddDoctor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonAddDoctor.setText("Add new");
    jButtonAddDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonAddDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonAddDoctorMouseClicked(evt);
        }
    });

    jButtonEditPersonel.setBackground(new java.awt.Color(255, 204, 102));
    jButtonEditPersonel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonEditPersonel.setText("Edit");
    jButtonEditPersonel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonEditPersonel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonEditPersonelMouseClicked(evt);
        }
    });

    jButtonSavePersonel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonSavePersonel.setText("Save");
    jButtonSavePersonel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonSavePersonel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonSavePersonelMouseClicked(evt);
        }
    });

    jButtonDeleteDoctor.setBackground(new java.awt.Color(255, 153, 153));
    jButtonDeleteDoctor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonDeleteDoctor.setText("Delete");
    jButtonDeleteDoctor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonDeleteDoctor.setEnabled(false);
    jButtonDeleteDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonDeleteDoctorMouseClicked(evt);
        }
    });

    jTextFieldFirstNameDoctor.setEnabled(false);
    jTextFieldFirstNameDoctor.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldFirstNameDoctorjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabelFirstname22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname22.setText("First name:");

    jLabelSurname15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSurname15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSurname15.setText("Last name:");

    jTextFieldSurnameDoctor.setEnabled(false);
    jTextFieldSurnameDoctor.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldSurnameDoctorKeyTyped(evt);
        }
    });

    jComboBoxDoctorType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jComboBoxDoctorType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "Cardiologist", "Psychiatrist", "Dermatologists", "Endocrinologists", "Neurologists", "Oncologists", "Pediatricians", "Radiologists" }));
    jComboBoxDoctorType.setEnabled(false);

    jLabelSex4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelSex4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelSex4.setText("Doctor type:");

    jCheckBoxDocAvailable.setEnabled(false);

    jLabelFirstname23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelFirstname23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFirstname23.setText("Unavailable:");

    jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel43.setText("State:");

    jTextFieldStatePresentDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStatePresentDoctor.setEnabled(false);
    jTextFieldStatePresentDoctor.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldStatePresentDoctorjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel44.setText("City:");

    jTextFieldCityPresentDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldCityPresentDoctor.setEnabled(false);
    jTextFieldCityPresentDoctor.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextFieldCityPresentDoctorjTextFieldOnlyAplhabeticKeyTyped(evt);
        }
    });

    jTextFieldStreetPresentDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldStreetPresentDoctor.setEnabled(false);

    jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel45.setText("Street:");

    jFormattedTextFieldHouseNumberPresentDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###"))));
    jFormattedTextFieldHouseNumberPresentDoctor.setEnabled(false);
    jFormattedTextFieldHouseNumberPresentDoctor.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextFieldHouseNumberPresentDoctorActionPerformed(evt);
        }
    });

    jLabelBirthdate16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate16.setText("House number:");

    jLabelBirthdate17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate17.setText("Area code:");

    try {
        jFormattedTextFieldAreacodePresentDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*******")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextFieldAreacodePresentDoctor.setEnabled(false);

    jLabelBirthdate18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate18.setText("Zip code:");

    jFormattedTextZipcodePresentDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("######"))));
    jFormattedTextZipcodePresentDoctor.setEnabled(false);

    jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel46.setText("Address");

    jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel47.setText("Telephones/Fax/Email");

    jFormattedTextFieldPhoneNumWorkDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTextFieldPhoneNumWorkDoctor.setToolTipText("+385");
    jFormattedTextFieldPhoneNumWorkDoctor.setEnabled(false);

    jLabelBirthdate19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate19.setText("Telephone (Work):");

    jFormattedTextFieldPhoneNumHomeDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTextFieldPhoneNumHomeDoctor.setToolTipText("+385");
    jFormattedTextFieldPhoneNumHomeDoctor.setEnabled(false);

    jLabelBirthdate20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate20.setText("Telephone (Home):");

    jFormattedTextFieldPhoneNumMobileDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
    jFormattedTextFieldPhoneNumMobileDoctor.setToolTipText("+385");
    jFormattedTextFieldPhoneNumMobileDoctor.setEnabled(false);

    jLabelBirthdate21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate21.setText("Mobile:");

    jTextFieldPagerDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldPagerDoctor.setEnabled(false);

    jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel48.setText("Pager:");

    jTextFieldFaxDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldFaxDoctor.setEnabled(false);

    jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel49.setText("Fax:");

    jTextFieldEmailDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jTextFieldEmailDoctor.setEnabled(false);

    jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel50.setText("Email:");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelFirstname23)
                        .addComponent(jLabelSex4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCheckBoxDocAvailable)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelBirthdate18)
                                .addComponent(jLabelBirthdate17)
                                .addComponent(jLabelBirthdate16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextFieldHouseNumberPresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextFieldAreacodePresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextZipcodePresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jComboBoxDoctorType, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(105, 105, 105)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldCityPresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldStatePresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel45)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldStreetPresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelSurname15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelFirstname22))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldSurnameDoctor)
                        .addComponent(jTextFieldFirstNameDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(146, 146, 146)
                    .addComponent(jLabel47)
                    .addGap(137, 137, 137))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabelBirthdate19)
                            .addGap(18, 18, 18)
                            .addComponent(jFormattedTextFieldPhoneNumWorkDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelBirthdate21)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldPhoneNumMobileDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelBirthdate20)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldPhoneNumHomeDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldFaxDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldEmailDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldPagerDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(105, 105, 105))))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(4, 4, 4)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel46)
                .addComponent(jLabel47))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelFirstname22)
                .addComponent(jTextFieldFirstNameDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSurname15)
                        .addComponent(jTextFieldSurnameDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(36, 36, 36)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSex4)
                        .addComponent(jComboBoxDoctorType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43)
                        .addComponent(jTextFieldStatePresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(jTextFieldCityPresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(jTextFieldStreetPresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFieldPhoneNumWorkDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBirthdate19))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFieldPhoneNumHomeDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBirthdate20))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFieldPhoneNumMobileDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelBirthdate21))))
            .addGap(34, 34, 34)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFirstname23)
                    .addComponent(jCheckBoxDocAvailable)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBirthdate16)
                            .addComponent(jFormattedTextFieldHouseNumberPresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBirthdate17)
                            .addComponent(jFormattedTextFieldAreacodePresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBirthdate18)
                            .addComponent(jFormattedTextZipcodePresentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(jTextFieldEmailDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel49)
                        .addComponent(jTextFieldFaxDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(jTextFieldPagerDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(35, Short.MAX_VALUE))
    );

    jButtonRefreshPersonel.setBackground(new java.awt.Color(51, 153, 255));
    jButtonRefreshPersonel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonRefreshPersonel.setText("Refresh");
    jButtonRefreshPersonel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonRefreshPersonel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonRefreshPersonelMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanelPersonelLayout = new javax.swing.GroupLayout(jPanelPersonel);
    jPanelPersonel.setLayout(jPanelPersonelLayout);
    jPanelPersonelLayout.setHorizontalGroup(
        jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPersonelLayout.createSequentialGroup()
            .addGap(73, 73, 73)
            .addGroup(jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addComponent(jScrollPane2))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
            .addGroup(jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButtonSavePersonel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEditPersonel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAddDoctor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addComponent(jButtonDeleteDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonRefreshPersonel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(22, 22, 22))
    );
    jPanelPersonelLayout.setVerticalGroup(
        jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelPersonelLayout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelPersonelLayout.createSequentialGroup()
                    .addComponent(jButtonRefreshPersonel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanelPersonelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPersonelLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDeleteDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(73, 73, 73)
                    .addComponent(jButtonEditPersonel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(jButtonSavePersonel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelPersonelLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGap(26, 26, 26))
    );

    jTabbedPaneMain.addTab("Personel", jPanelPersonel);

    jPanelDoctors.setBackground(new java.awt.Color(255, 255, 255));

    jComboBoxDoctorsList.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBoxDoctorsListItemStateChanged(evt);
        }
    });

    jComboBoxPatientList.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBoxPatientListItemStateChanged(evt);
        }
    });

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel2.setText("Doctor:");

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel3.setText("Patient:");

    jTableAppointmentsByDoctor.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){return false;}});
    jTableAppointmentsByDoctor.getTableHeader().setReorderingAllowed(false);
    jTableAppointmentsByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTableAppointmentsByDoctorMouseClicked(evt);
        }
    });
    jScrollPane3.setViewportView(jTableAppointmentsByDoctor);

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel4.setText("Appointments");

    jButtonRemoveAppointmentByDoctor.setText("Remove");
    jButtonRemoveAppointmentByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonRemoveAppointmentByDoctorMouseClicked(evt);
        }
    });

    jButtonAddApointmentByDoctor.setText("New appointment");
    jButtonAddApointmentByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonAddApointmentByDoctorMouseClicked(evt);
        }
    });

    jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel5.setText("Appointment");

    jTextAreaDiagnosisByDoctor.setColumns(20);
    jTextAreaDiagnosisByDoctor.setRows(5);
    jTextAreaDiagnosisByDoctor.setEnabled(false);
    jScrollPane4.setViewportView(jTextAreaDiagnosisByDoctor);

    jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    jLabel8.setText("Diagnosis");

    jTableTests.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){return false;}});
    jTableTests.getTableHeader().setReorderingAllowed(false);
    jScrollPane5.setViewportView(jTableTests);

    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    jLabel9.setText("Tests");

    jTablePrescriptions.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){return false;}});
    jTablePrescriptions.getTableHeader().setReorderingAllowed(false);
    jScrollPane6.setViewportView(jTablePrescriptions);

    jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    jLabel10.setText("Prescription");

    jButtonReferToSpecialist.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButtonReferToSpecialist.setText("Refer to specialist");
    jButtonReferToSpecialist.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonReferToSpecialistMouseClicked(evt);
        }
    });

    jToggleButtonEditAppointmentByDoctor.setText("Edit");
    jToggleButtonEditAppointmentByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jToggleButtonEditAppointmentByDoctorMouseClicked(evt);
        }
    });

    jButtonSaveAppointmentByDoctor.setText("Save");
    jButtonSaveAppointmentByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonSaveAppointmentByDoctorMouseClicked(evt);
        }
    });

    jButtonRefreshAppointmentsByDoc.setBackground(new java.awt.Color(51, 153, 255));
    jButtonRefreshAppointmentsByDoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonRefreshAppointmentsByDoc.setText("Refresh");
    jButtonRefreshAppointmentsByDoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonRefreshAppointmentsByDoc.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonRefreshAppointmentsByDocMouseClicked(evt);
        }
    });

    jFormattedAppointmentDateByDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    jFormattedAppointmentDateByDoctor.setEnabled(false);
    jFormattedAppointmentDateByDoctor.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedAppointmentDateByDoctorActionPerformed(evt);
        }
    });

    jLabelBirthdate25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate25.setText("Date:");

    jFormattedAppointmentStartByDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
    jFormattedAppointmentStartByDoctor.setToolTipText("");
    jFormattedAppointmentStartByDoctor.setEnabled(false);

    jLabelBirthdate26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate26.setText("Start time:");

    jLabelBirthdate27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate27.setText("End time:");

    jFormattedAppointmentEndByDoctor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
    jFormattedAppointmentEndByDoctor.setEnabled(false);

    jButtonAddTestByDoctor.setText("Add");
    jButtonAddTestByDoctor.setEnabled(false);
    jButtonAddTestByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonAddTestByDoctorMouseClicked(evt);
        }
    });

    jButtonRemoveTestByDoctor.setText("Remove");
    jButtonRemoveTestByDoctor.setEnabled(false);
    jButtonRemoveTestByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonRemoveTestByDoctorMouseClicked(evt);
        }
    });

    jComboBoxListOfTestTypes.setEnabled(false);

    jComboBoxListOfDrugsForPrescription.setEnabled(false);

    jButtonAddPrescriptionByDoctor.setText("Add");
    jButtonAddPrescriptionByDoctor.setEnabled(false);
    jButtonAddPrescriptionByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonAddPrescriptionByDoctorMouseClicked(evt);
        }
    });

    jButtonRemovePrescriptionByDoctor.setText("Remove");
    jButtonRemovePrescriptionByDoctor.setEnabled(false);
    jButtonRemovePrescriptionByDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonRemovePrescriptionByDoctorMouseClicked(evt);
        }
    });

    jTextAreaInstructions.setColumns(20);
    jTextAreaInstructions.setRows(5);
    jTextAreaInstructions.setEnabled(false);
    jScrollPane20.setViewportView(jTextAreaInstructions);

    jLabel54.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
    jLabel54.setText("Instructions");

    javax.swing.GroupLayout jPanelDoctorsLayout = new javax.swing.GroupLayout(jPanelDoctors);
    jPanelDoctors.setLayout(jPanelDoctorsLayout);
    jPanelDoctorsLayout.setHorizontalGroup(
        jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDoctorsLayout.createSequentialGroup()
            .addGap(27, 27, 27)
            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBoxDoctorsList, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(31, 31, 31)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxPatientList, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(64, 64, 64)
                    .addComponent(jButtonRefreshAppointmentsByDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addGap(60, 60, 60)
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabelBirthdate25)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jFormattedAppointmentDateByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(59, 59, 59)
                    .addComponent(jLabelBirthdate26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jFormattedAppointmentStartByDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addGap(32, 32, 32)
                    .addComponent(jLabelBirthdate27)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jFormattedAppointmentEndByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(257, 257, 257)
                    .addComponent(jButtonRemoveAppointmentByDoctor)
                    .addGap(39, 39, 39)
                    .addComponent(jButtonAddApointmentByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(56, 56, 56))
        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
            .addGap(58, 58, 58)
            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(jLabel8)
                    .addGap(219, 219, 219)
                    .addComponent(jLabel9)
                    .addGap(190, 190, 190)
                    .addComponent(jLabel10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54)
                    .addGap(102, 102, 102)
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jToggleButtonEditAppointmentByDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jButtonSaveAppointmentByDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addComponent(jComboBoxSpecialistDoctorLists, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDoctorsLayout.createSequentialGroup()
                            .addComponent(jButtonRemoveTestByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAddTestByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jComboBoxListOfTestTypes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                                .addComponent(jButtonRemovePrescriptionByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAddPrescriptionByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jComboBoxListOfDrugsForPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonReferToSpecialist)))
            .addGap(24, 24, 24))
        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jSeparator1)
            .addGap(292, 292, 292))
    );
    jPanelDoctorsLayout.setVerticalGroup(
        jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxDoctorsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxPatientList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addGap(12, 12, 12))
                        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonRefreshAppointmentsByDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)))
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonRemoveAppointmentByDoctor)
                        .addComponent(jButtonAddApointmentByDoctor)))
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedAppointmentEndByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBirthdate27))
                        .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBirthdate25)
                            .addComponent(jFormattedAppointmentDateByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedAppointmentStartByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBirthdate26)))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)
                        .addComponent(jLabel54))
                    .addGap(18, 18, 18))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDoctorsLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBoxSpecialistDoctorLists, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)))
            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                    .addComponent(jButtonReferToSpecialist, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonEditAppointmentByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addComponent(jButtonSaveAppointmentByDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDoctorsLayout.createSequentialGroup()
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                            .addComponent(jComboBoxListOfTestTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonAddTestByDoctor)
                                .addComponent(jButtonRemoveTestByDoctor)))
                        .addGroup(jPanelDoctorsLayout.createSequentialGroup()
                            .addComponent(jComboBoxListOfDrugsForPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelDoctorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonAddPrescriptionByDoctor)
                                .addComponent(jButtonRemovePrescriptionByDoctor))))))
            .addContainerGap(58, Short.MAX_VALUE))
    );

    jTabbedPaneMain.addTab("Doctor", jPanelDoctors);

    jTableAppointments.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){return false;}});
    jTableAppointments.getTableHeader().setReorderingAllowed(false);
    jTableAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTableAppointmentsMouseClicked(evt);
        }
    });
    jScrollPane8.setViewportView(jTableAppointments);

    jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel12.setText("Appointments");

    jButtonSaveAppointment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonSaveAppointment.setText("Save");
    jButtonSaveAppointment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonSaveAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonSaveAppointmentMouseClicked(evt);
        }
    });

    jButtonEditAppointment.setBackground(new java.awt.Color(255, 204, 102));
    jButtonEditAppointment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonEditAppointment.setText("Edit");
    jButtonEditAppointment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonEditAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonEditAppointmentMouseClicked(evt);
        }
    });

    jButtonDeleteAppointment.setBackground(new java.awt.Color(255, 153, 153));
    jButtonDeleteAppointment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonDeleteAppointment.setText("Delete");
    jButtonDeleteAppointment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonDeleteAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonDeleteAppointmentMouseClicked(evt);
        }
    });

    jButtonAddAppointment.setBackground(new java.awt.Color(51, 204, 0));
    jButtonAddAppointment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonAddAppointment.setText("Add new");
    jButtonAddAppointment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonAddAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonAddAppointmentMouseClicked(evt);
        }
    });

    jButtonRefreshAppointments.setBackground(new java.awt.Color(51, 153, 255));
    jButtonRefreshAppointments.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonRefreshAppointments.setText("Refresh");
    jButtonRefreshAppointments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonRefreshAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonRefreshAppointmentsMouseClicked(evt);
        }
    });

    jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

    jComboBoxAppointmentPatient.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jComboBoxAppointmentPatient.setEnabled(false);

    jComboBoxAppointmentDoctor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jComboBoxAppointmentDoctor.setEnabled(false);

    jLabel52.setText("Patient:");

    jLabel53.setText("Doctor:");

    jFormattedAppointmentDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
    jFormattedAppointmentDate.setText("10/09/2019");
    jFormattedAppointmentDate.setEnabled(false);
    jFormattedAppointmentDate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedAppointmentDateActionPerformed(evt);
        }
    });

    jLabelBirthdate22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate22.setText("Date:");

    jFormattedAppointmentStart.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
    jFormattedAppointmentStart.setToolTipText("");
    jFormattedAppointmentStart.setEnabled(false);

    jLabelBirthdate23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate23.setText("Start time:");

    jLabelBirthdate24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelBirthdate24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBirthdate24.setText("End time:");

    jFormattedAppointmentEnd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
    jFormattedAppointmentEnd.setEnabled(false);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(46, 46, 46)
            .addComponent(jLabelBirthdate22)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jFormattedAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(59, 59, 59)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabelBirthdate23)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jFormattedAppointmentStart, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addComponent(jComboBoxAppointmentPatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jComboBoxAppointmentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabelBirthdate24)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jFormattedAppointmentEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(192, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGap(58, 58, 58)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedAppointmentEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBirthdate24))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBirthdate22)
                    .addComponent(jFormattedAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedAppointmentStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBirthdate23)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBoxAppointmentDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBoxAppointmentPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(65, 65, 65))
    );

    javax.swing.GroupLayout jPanelAppointmentsLayout = new javax.swing.GroupLayout(jPanelAppointments);
    jPanelAppointments.setLayout(jPanelAppointmentsLayout);
    jPanelAppointmentsLayout.setHorizontalGroup(
        jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelAppointmentsLayout.createSequentialGroup()
            .addGroup(jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelAppointmentsLayout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addComponent(jLabel12))
                .addGroup(jPanelAppointmentsLayout.createSequentialGroup()
                    .addGap(134, 134, 134)
                    .addGroup(jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane8)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
            .addGroup(jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSaveAppointment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEditAppointment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddAppointment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jButtonDeleteAppointment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jButtonRefreshAppointments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(41, 41, 41))
    );
    jPanelAppointmentsLayout.setVerticalGroup(
        jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelAppointmentsLayout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addComponent(jLabel12)
            .addGap(18, 18, 18)
            .addGroup(jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelAppointmentsLayout.createSequentialGroup()
                    .addComponent(jButtonRefreshAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(98, 98, 98)
                    .addComponent(jButtonAddAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
            .addGroup(jPanelAppointmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanelAppointmentsLayout.createSequentialGroup()
                    .addComponent(jButtonDeleteAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(73, 73, 73)
                    .addComponent(jButtonEditAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(jButtonSaveAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(35, 35, 35))
    );

    jTabbedPaneMain.addTab("Appointments", jPanelAppointments);

    jTableBillsIssued.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){return false;}});
    jTableBillsIssued.getTableHeader().setReorderingAllowed(false);
    jScrollPane7.setViewportView(jTableBillsIssued);

    jButtonIssueBill.setBackground(new java.awt.Color(0, 204, 0));
    jButtonIssueBill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButtonIssueBill.setText("Issue");
    jButtonIssueBill.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonIssueBillMouseClicked(evt);
        }
    });

    jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton9.setText("Print");

    jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel11.setText("Bills issued");

    jTableBillsUnissued.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){return false;}});
    jTableBillsUnissued.getTableHeader().setReorderingAllowed(false);
    jScrollPane21.setViewportView(jTableBillsUnissued);

    jLabel55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel55.setText("Bills unissued");

    jButtonRefreshBills.setText("Refresh");
    jButtonRefreshBills.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonRefreshBillsMouseClicked(evt);
        }
    });

    jCheckBoxCreditCard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jCheckBoxCreditCard.setText("Credit card");

    jOutpatCreditCardNumber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));

    javax.swing.GroupLayout jPanelBillsLayout = new javax.swing.GroupLayout(jPanelBills);
    jPanelBills.setLayout(jPanelBillsLayout);
    jPanelBillsLayout.setHorizontalGroup(
        jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBillsLayout.createSequentialGroup()
            .addGap(83, 83, 83)
            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBillsLayout.createSequentialGroup()
            .addGap(66, 66, 66)
            .addGroup(jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBillsLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
            .addGroup(jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonIssueBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jOutpatCreditCardNumber)
                .addComponent(jCheckBoxCreditCard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addComponent(jButtonRefreshBills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(23, 23, 23))
    );
    jPanelBillsLayout.setVerticalGroup(
        jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBillsLayout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addComponent(jLabel11)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButtonRefreshBills, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(82, 82, 82)
            .addComponent(jLabel55)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelBillsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelBillsLayout.createSequentialGroup()
                    .addComponent(jCheckBoxCreditCard, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jOutpatCreditCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonIssueBill, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(31, 31, 31))
    );

    jTabbedPaneMain.addTab("Bills", jPanelBills);

    javax.swing.GroupLayout jPanelReportsLayout = new javax.swing.GroupLayout(jPanelReports);
    jPanelReports.setLayout(jPanelReportsLayout);
    jPanelReportsLayout.setHorizontalGroup(
        jPanelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 1195, Short.MAX_VALUE)
    );
    jPanelReportsLayout.setVerticalGroup(
        jPanelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 665, Short.MAX_VALUE)
    );

    jTabbedPaneMain.addTab("Reports", jPanelReports);

    jPanelTitleBackground.setBackground(new java.awt.Color(153, 204, 255));

    Title.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
    Title.setForeground(new java.awt.Color(0, 51, 51));
    Title.setText("VIRGO HOSPITAL - Outpatient module");

    jButtonClose.setBackground(new java.awt.Color(255, 153, 153));
    jButtonClose.setText("Close");
    jButtonClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
    jButtonClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jButtonClose.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButtonCloseMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanelTitleBackgroundLayout = new javax.swing.GroupLayout(jPanelTitleBackground);
    jPanelTitleBackground.setLayout(jPanelTitleBackgroundLayout);
    jPanelTitleBackgroundLayout.setHorizontalGroup(
        jPanelTitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelTitleBackgroundLayout.createSequentialGroup()
            .addGap(33, 33, 33)
            .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(21, 21, 21))
    );
    jPanelTitleBackgroundLayout.setVerticalGroup(
        jPanelTitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelTitleBackgroundLayout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addGroup(jPanelTitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelTitleBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPaneMain)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jPanelTitleBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPaneMain, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jButtonCloseMouseClicked

    private void jButtonAddSimpleFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddSimpleFormMouseClicked
        SimpleForm sf = new SimpleForm(this, rootPaneCheckingEnabled);
        sf.setLocationRelativeTo(null);
        sf.setVisible(true);
    }//GEN-LAST:event_jButtonAddSimpleFormMouseClicked

    private void jButtonPatientsRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPatientsRefreshMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTablePatients.getModel();
        model.setRowCount(0);
//        RefreshAll();

        this.FillPatientTable();
    }//GEN-LAST:event_jButtonPatientsRefreshMouseClicked

    private void jButtonAddExtensiveFormMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddExtensiveFormMouseClicked
        ExtensiveForm extensive = new ExtensiveForm(this, rootPaneCheckingEnabled);
        extensive.setLocationRelativeTo(null);
        extensive.setVisible(true);
    }//GEN-LAST:event_jButtonAddExtensiveFormMouseClicked

    private void jTextFieldPatientMiddKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPatientMiddKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldPatientMiddKeyTyped

    private void jTextSurrnameEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextSurrnameEditKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextSurrnameEditKeyTyped

    private void jTextFieLDPatientFirstEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieLDPatientFirstEditjTextFieldOnlyAplhabeticKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieLDPatientFirstEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTablePatientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePatientsMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTablePatients.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTablePatients.getSelectedRow());

            GetSinglePatientResult patientRes = _patientsBL.getById((Long) selectedRow.get(0));

            if (patientRes.isOK) {
                _currentPatient = patientRes.patient;

                ClearInputFields();
                SetEditValues();
            }
        }


    }//GEN-LAST:event_jTablePatientsMouseClicked

    private void jTextFieldStatePresentEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStatePresentEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldStatePresentEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldCityPresentEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCityPresentEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldCityPresentEditjTextFieldOnlyAplhabeticKeyTyped

    private void jFormattedTextFieldHouseNumberPresentEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldHouseNumberPresentEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldHouseNumberPresentEditActionPerformed

    private void jTextFieldStatePermanentEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStatePermanentEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldStatePermanentEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldCityPermanentEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCityPermanentEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldCityPermanentEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldFaxEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFaxEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldFaxEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldEmailEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldEmailEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldPagerEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPagerEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldPagerEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldFirstNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFirstNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldFirstNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldMiddleNextOfKinEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMiddleNextOfKinEditKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldMiddleNextOfKinEditKeyTyped

    private void jTextFieldSurnameNextOfKinEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSurnameNextOfKinEditKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldSurnameNextOfKinEditKeyTyped

    private void jTextFieldNextOfKinRelatinshipEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNextOfKinRelatinshipEditKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNextOfKinRelatinshipEditKeyTyped

    private void jTextFieldStateNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStateNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldStateNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldCityNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCityNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldCityNextOfKinEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldStreetNextOfKinEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStreetNextOfKinEditKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStreetNextOfKinEditKeyTyped

    private void jTextFieldOccupationEditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldOccupationEditKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldOccupationEditKeyTyped

    private void jTextFieldFirstNameDiabeticEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameDiabeticEditjTextFieldOnlyAplhabeticKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameDiabeticEditjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldFirstNameHypertensiveEditjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameHypertensiveEditjTextFieldOnlyAplhabeticKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameHypertensiveEditjTextFieldOnlyAplhabeticKeyTyped

    private void jButtonEditPatientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditPatientsMouseClicked
        if ((String) jButtonEditPatients.getText() == "Edit") {
            jButtonEditPatients.setText("Cancel");
            ToggleEnabledPatient(true);
        } else {
            jButtonEditPatients.setText("Edit");
            ToggleEnabledPatient(false);
        }

    }//GEN-LAST:event_jButtonEditPatientsMouseClicked

    private void jButtonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSaveMouseClicked
        if (jButtonEditPatients.getText().equalsIgnoreCase("Edit")) {
            return;
        }

        if (!PatientInputValidation()) {
            return;
        }

        UpdatePatient();
    }//GEN-LAST:event_jButtonSaveMouseClicked

    private void jButtonDeletePatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeletePatientMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTablePatients.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTablePatients.getSelectedRow());

            GetSinglePatientResult patientRes = _patientsBL.getById((Long) selectedRow.get(0));

            if (patientRes.isOK) {
                _patientsBL.deletePatient(patientRes.patient.getId());

            }
        }
    }//GEN-LAST:event_jButtonDeletePatientMouseClicked

    private void jButtonRefreshPersonelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRefreshPersonelMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTablePersonel.getModel();
        model.setRowCount(0);
        RefreshAll();

        this.FillDoctorTable();
    }//GEN-LAST:event_jButtonRefreshPersonelMouseClicked

    private void jButtonEditPersonelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditPersonelMouseClicked
        if ((String) jButtonEditPersonel.getText() == "Edit") {
            jButtonEditPersonel.setText("Cancel");
            ToggleEnabledPersonel(true);
        } else {
            jButtonEditPersonel.setText("Edit");
            ToggleEnabledPersonel(false);
        }

    }//GEN-LAST:event_jButtonEditPersonelMouseClicked

    private void jButtonSavePersonelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSavePersonelMouseClicked
        if (jButtonEditPersonel.getText().equalsIgnoreCase("Edit")) {
            return;
        }

        if (!DoctorInputValidation()) {
            return;
        }

        UpdateOrCreateDoctor();
        jButtonEditPersonel.setText("Edit");
        ToggleEnabledPersonel(false);
    }//GEN-LAST:event_jButtonSavePersonelMouseClicked

    private void jTextFieldFirstNameDoctorjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameDoctorjTextFieldOnlyAplhabeticKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldFirstNameDoctorjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldSurnameDoctorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSurnameDoctorKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldSurnameDoctorKeyTyped

    private void jTextFieldStatePresentDoctorjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStatePresentDoctorjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldStatePresentDoctorjTextFieldOnlyAplhabeticKeyTyped

    private void jTextFieldCityPresentDoctorjTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCityPresentDoctorjTextFieldOnlyAplhabeticKeyTyped
        jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(evt);
    }//GEN-LAST:event_jTextFieldCityPresentDoctorjTextFieldOnlyAplhabeticKeyTyped

    private void jFormattedTextFieldHouseNumberPresentDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldHouseNumberPresentDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldHouseNumberPresentDoctorActionPerformed

    private void jTablePersonelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePersonelMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTablePersonel.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTablePersonel.getSelectedRow());

            GetSingleDoctorResult docRes = _doctorBL.getById((Long) selectedRow.get(0));

            if (docRes.isOK) {
                _currentDoctor = docRes.doctors;

                ClearInputFieldsPersonel();
                SetEditValuesPersonel();
            }
        }
    }//GEN-LAST:event_jTablePersonelMouseClicked

    private void jButtonAddDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddDoctorMouseClicked
        this._currentDoctor = new Doctor();
        this.addingDoctor = true;
        this.ClearInputFieldsPersonel();
        if ((String) jButtonEditPersonel.getText() == "Edit") {
            jButtonEditPersonel.setText("Cancel");
            ToggleEnabledPersonel(true);
        }
    }//GEN-LAST:event_jButtonAddDoctorMouseClicked

    private void jButtonDeleteDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeleteDoctorMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTablePersonel.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTablePersonel.getSelectedRow());

            GetSingleDoctorResult docRes = _doctorBL.getById((Long) selectedRow.get(0));

            if (docRes.isOK) {
                _doctorBL.deleteContactDetail(docRes.doctors.getId());

            }
        }    }//GEN-LAST:event_jButtonDeleteDoctorMouseClicked

    private void jComboBoxDoctorsListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDoctorsListItemStateChanged
        if (_allPatients.isEmpty()) {
            return;
        }

        if (_allAppointments.isEmpty()) {
            return;
        }

        Integer docId = jComboBoxDoctorsList.getSelectedIndex() + 1;

        List<Patient> tempPatient = new ArrayList<Patient>();
        for (int i = 0; i < _allAppointments.size(); i++) {
            if (_allAppointments.get(i).getDoctor() == null) {
                continue;
            }
            if (_allAppointments.get(i).getPatient() == null) {
                continue;
            }

            Integer tempPat = _allAppointments.get(i).getDoctor().getId().intValue();
            if (tempPat == docId) {
                if (!tempPatient.isEmpty()) {

                    Integer patientId = _allAppointments.get(i).getPatient().getId().intValue();
                    if (tempPatient.stream().anyMatch(x -> x.getId().intValue() == patientId)) {
                        continue;
                    }
                }
                tempPatient.add(_allAppointments.get(i).getPatient());
            }
        }

        jComboBoxPatientList.setModel(new DefaultComboBoxModel(tempPatient.toArray()));

    }//GEN-LAST:event_jComboBoxDoctorsListItemStateChanged

    private void jComboBoxPatientListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxPatientListItemStateChanged
        if (jComboBoxPatientList.getItemCount() == 0) {
            _currentlySelected = new Patient();
            return;
        }
        _currentlySelected = (Patient) jComboBoxPatientList.getSelectedItem();
    }//GEN-LAST:event_jComboBoxPatientListItemStateChanged

    private void jTableAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAppointmentsMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableAppointments.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTableAppointments.getSelectedRow());

            GetSingleAppointmentResult appointmentRes = _appointmentBL.getById((Long) selectedRow.get(0));

            if (appointmentRes.isOK) {
                _currentAppointment = appointmentRes.appointment;

                ClearInputFieldsAppointment();
                SetEditValuesAppointment();
            }
        }    }//GEN-LAST:event_jTableAppointmentsMouseClicked

    private void jButtonSaveAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSaveAppointmentMouseClicked
        if (jButtonEditAppointment.getText().equalsIgnoreCase("Edit")) {
            return;
        }

        if (!AppointmentInputValidation()) {
            return;
        }

        UpdateOrCreateAppointment();
        jButtonEditAppointment.setText("Edit");
        ToggleEnabledAppointment(false);
    }//GEN-LAST:event_jButtonSaveAppointmentMouseClicked

    private void jButtonEditAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditAppointmentMouseClicked
        if ((String) jButtonEditAppointment.getText() == "Edit") {
            jButtonEditAppointment.setText("Cancel");
            ToggleEnabledAppointment(true);
        } else {
            jButtonEditAppointment.setText("Edit");
            ToggleEnabledAppointment(false);
        }


    }//GEN-LAST:event_jButtonEditAppointmentMouseClicked

    private void jButtonDeleteAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeleteAppointmentMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableAppointments.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTableAppointments.getSelectedRow());

            GetSingleAppointmentResult appRes = _appointmentBL.getById((Long) selectedRow.get(0));

            if (appRes.isOK) {
                _appointmentBL.deleteContactDetail(appRes.appointment.getId());

            }
        }
    }//GEN-LAST:event_jButtonDeleteAppointmentMouseClicked

    private void jButtonAddAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddAppointmentMouseClicked
        this._currentAppointment = new Appointment();
        this.addingAppointment = true;
        this.ClearInputFieldsAppointment();
        if ((String) jButtonEditAppointment.getText() == "Edit") {
            jButtonEditAppointment.setText("Cancel");
            ToggleEnabledAppointment(true);
        }    }//GEN-LAST:event_jButtonAddAppointmentMouseClicked

    private void jButtonRefreshAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRefreshAppointmentsMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableAppointments.getModel();
        model.setRowCount(0);
        RefreshAll();

        this.FillAppointmentTable();
    }//GEN-LAST:event_jButtonRefreshAppointmentsMouseClicked

    private void jFormattedAppointmentDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedAppointmentDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedAppointmentDateActionPerformed

    private void jButtonRefreshAppointmentsByDocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRefreshAppointmentsByDocMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableAppointmentsByDoctor.getModel();
        model.setRowCount(0);
        _currentlySelected = (Patient) jComboBoxPatientList.getSelectedItem();
        RefreshAll();
        this.FillAppointmentByDocTable();
    }//GEN-LAST:event_jButtonRefreshAppointmentsByDocMouseClicked

    private void jFormattedAppointmentDateByDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedAppointmentDateByDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedAppointmentDateByDoctorActionPerformed

    private void jTableAppointmentsByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAppointmentsByDoctorMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableAppointmentsByDoctor.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTableAppointmentsByDoctor.getSelectedRow());

            GetSingleAppointmentResult appointmentRes = _appointmentBL.getById((Long) selectedRow.get(0));

            if (appointmentRes.isOK) {
                _currentAppointmentByDoctor = appointmentRes.appointment;

                ClearInputFieldsAppointmentByDoctor();
                SetEditValuesAppointmentByDoctor();
            }
        }
    }//GEN-LAST:event_jTableAppointmentsByDoctorMouseClicked

    private void jToggleButtonEditAppointmentByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButtonEditAppointmentByDoctorMouseClicked
        if (addingAppointmentByDoctor) {
            this.addingAppointmentByDoctor = false;
        }
        if (_currentAppointmentByDoctor == null) {
            return;
        }
        if ((String) jToggleButtonEditAppointmentByDoctor.getText() == "Edit") {
            jToggleButtonEditAppointmentByDoctor.setText("Cancel");
            ToggleEnabledAppointmentByDoctor(true);
        } else {
            jToggleButtonEditAppointmentByDoctor.setText("Edit");
            ToggleEnabledAppointmentByDoctor(false);
        }    }//GEN-LAST:event_jToggleButtonEditAppointmentByDoctorMouseClicked

    private void jButtonAddApointmentByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddApointmentByDoctorMouseClicked
        if (jComboBoxPatientList.getSelectedIndex() == -1) {
            return;
        }

        this._currentAppointmentByDoctor = new Appointment();
        this.addingAppointmentByDoctor = true;
        this.ClearInputFieldsAppointmentByDoctor();
        if ((String) jToggleButtonEditAppointmentByDoctor.getText() == "Edit") {
            jToggleButtonEditAppointmentByDoctor.setText("Cancel");
            ToggleEnabledAppointmentByDoctor(true);
        }
    }//GEN-LAST:event_jButtonAddApointmentByDoctorMouseClicked

    private void jButtonAddTestByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddTestByDoctorMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableTests.getModel();

        List<TestType> tests = new ArrayList<TestType>();
        TestType tempTest = (TestType) jComboBoxListOfTestTypes.getSelectedItem();
        for (int count = 0; count < model.getRowCount(); count++) {
            String temp = model.getValueAt(count, 1).toString();
            tests.add((TestType) _allTestTypes.stream().filter(x -> x.getName().equalsIgnoreCase(temp)).findFirst().orElse(null));
        }

        if (tests.stream().anyMatch(x -> x.getId().intValue() == tempTest.getId().intValue())) {
            return;
        }
        Object rowData[] = new Object[2];
        rowData[0] = tempTest.getId();
        rowData[1] = tempTest.getName();
        model.addRow(rowData);
    }//GEN-LAST:event_jButtonAddTestByDoctorMouseClicked

    private void jButtonRemoveTestByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRemoveTestByDoctorMouseClicked
        if (jTableTests.getSelectedRow() == -1) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) jTableTests.getModel();

        model.removeRow(jTableTests.getSelectedRow());
    }//GEN-LAST:event_jButtonRemoveTestByDoctorMouseClicked

    private void jButtonRemovePrescriptionByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRemovePrescriptionByDoctorMouseClicked
        if (jTablePrescriptions.getSelectedRow() == -1) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) jTablePrescriptions.getModel();

        model.removeRow(jTablePrescriptions.getSelectedRow());    }//GEN-LAST:event_jButtonRemovePrescriptionByDoctorMouseClicked

    private void jButtonAddPrescriptionByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddPrescriptionByDoctorMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTablePrescriptions.getModel();

        List<Drug> drugs = new ArrayList<Drug>();
        Drug tempTest = (Drug) jComboBoxListOfDrugsForPrescription.getSelectedItem();
        for (int count = 0; count < model.getRowCount(); count++) {
            String temp = model.getValueAt(count, 1).toString();
            drugs.add((Drug) _allDrugs.stream().filter(x -> x.getName().equalsIgnoreCase(temp)).findFirst().orElse(null));
        }

        if (drugs.stream().anyMatch(x -> x.getId().intValue() == tempTest.getId().intValue())) {
            return;
        }
        Object rowData[] = new Object[2];
        rowData[0] = tempTest.getId();
        rowData[1] = tempTest.getName();
        model.addRow(rowData);    }//GEN-LAST:event_jButtonAddPrescriptionByDoctorMouseClicked

    private void jButtonSaveAppointmentByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSaveAppointmentByDoctorMouseClicked
        if (jToggleButtonEditAppointmentByDoctor.getText().equalsIgnoreCase("Edit")) {
            return;
        }

        if (!AppointmentByDoctorInputValidation()) {
            return;
        }

        UpdateOrCreateAppointmentByDoctor();
        jToggleButtonEditAppointmentByDoctor.setText("Edit");
        ToggleEnabledAppointmentByDoctor(false);
    }//GEN-LAST:event_jButtonSaveAppointmentByDoctorMouseClicked

    private void jButtonReferToSpecialistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonReferToSpecialistMouseClicked
        this.refferingToSpecialist = true;
    }//GEN-LAST:event_jButtonReferToSpecialistMouseClicked

    private void jButtonIssueBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonIssueBillMouseClicked
        if (jTableBillsUnissued.getSelectedRow() == -1) {
            return;
        }
        if (!BillInputValidation()) {
            return;
        }

        Payment p = new Payment();

        if (jCheckBoxCreditCard.isSelected()) {
            p.setCardNumber(Integer.parseInt(jOutpatCreditCardNumber.getText()));
            p.setPaymentType(PaymentType.CreditCard);
        } else {
            p.setPaymentType(PaymentType.Cash);
        }
        InsertPaymentResult payRes = _paymentBL.insertContactDetail(p);

        Bill b = new Bill();
        DefaultTableModel model = (DefaultTableModel) jTableBillsUnissued.getModel();
        Vector selectedRow = (Vector) model.getDataVector().elementAt(jTableBillsUnissued.getSelectedRow());
        GetSingleAppointmentResult singlAppRes = _appointmentBL.getById((Long) selectedRow.get(0));
        Appointment appoint = singlAppRes.appointment;
        appoint.setPaid(true);
        _appointmentBL.updateContactDetail(appoint);
        b.setAppointment(appoint);
        b.setConsultationPrice(new BigDecimal(200));
        b.setPayment(payRes.payment);

        b.setPrescriptionQuantity(Integer.parseInt(selectedRow.get(3).toString()));
        b.setPrescriptionsPrice(new BigDecimal(selectedRow.get(4).toString()));
        b.setTestQuantity(Integer.parseInt(selectedRow.get(5).toString()));
        b.setTestsPrice(new BigDecimal(selectedRow.get(6).toString()));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);

        String date = selectedRow.get(7).toString();

        LocalDateTime dt = LocalDateTime.now();

        dt.format(timeFormatter);

        b.setTimeIssued(dt);
        b.setTotalPrice(new BigDecimal(selectedRow.get(10).toString()));

        _billBL.insertContactDetail(b);
    }//GEN-LAST:event_jButtonIssueBillMouseClicked

    private void jButtonRefreshBillsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRefreshBillsMouseClicked
        RefreshAll();
        DefaultTableModel model = (DefaultTableModel) jTableBillsIssued.getModel();
        model.setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) jTableBillsUnissued.getModel();
        model2.setRowCount(0);
        this.FillBillIssued();
        this.FillBillUnissued();
}//GEN-LAST:event_jButtonRefreshBillsMouseClicked

    private void jButtonRemoveAppointmentByDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRemoveAppointmentByDoctorMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableAppointmentsByDoctor.getModel();
        if (model.getRowCount() > 0) {
            Vector selectedRow = (Vector) model.getDataVector().elementAt(jTableAppointmentsByDoctor.getSelectedRow());

            GetSingleAppointmentResult appRes = _appointmentBL.getById((Long) selectedRow.get(0));

            if (appRes.isOK) {
                _appointmentBL.deleteContactDetail(appRes.appointment.getId());

            }
        }
    }//GEN-LAST:event_jButtonRemoveAppointmentByDoctorMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OutpatientModule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OutpatientModule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAddApointmentByDoctor;
    private javax.swing.JButton jButtonAddAppointment;
    private javax.swing.JButton jButtonAddDoctor;
    private javax.swing.JButton jButtonAddExtensiveForm;
    private javax.swing.JButton jButtonAddPrescriptionByDoctor;
    private javax.swing.JButton jButtonAddSimpleForm;
    private javax.swing.JButton jButtonAddTestByDoctor;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDeleteAppointment;
    private javax.swing.JButton jButtonDeleteDoctor;
    private javax.swing.JButton jButtonDeletePatient;
    private javax.swing.JButton jButtonEditAppointment;
    private javax.swing.JButton jButtonEditPatients;
    private javax.swing.JButton jButtonEditPersonel;
    private javax.swing.JButton jButtonIssueBill;
    private javax.swing.JButton jButtonPatientsRefresh;
    private javax.swing.JButton jButtonReferToSpecialist;
    private javax.swing.JButton jButtonRefreshAppointments;
    private javax.swing.JButton jButtonRefreshAppointmentsByDoc;
    private javax.swing.JButton jButtonRefreshBills;
    private javax.swing.JButton jButtonRefreshPersonel;
    private javax.swing.JButton jButtonRemoveAppointmentByDoctor;
    private javax.swing.JButton jButtonRemovePrescriptionByDoctor;
    private javax.swing.JButton jButtonRemoveTestByDoctor;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveAppointment;
    private javax.swing.JButton jButtonSaveAppointmentByDoctor;
    private javax.swing.JButton jButtonSavePersonel;
    private javax.swing.JCheckBox jCheckBoxAlcoholEdit;
    private javax.swing.JCheckBox jCheckBoxCreditCard;
    private javax.swing.JCheckBox jCheckBoxDocAvailable;
    private javax.swing.JCheckBox jCheckBoxEatingHomeEdit;
    private javax.swing.JCheckBox jCheckBoxMarriedEdit;
    private javax.swing.JCheckBox jCheckBoxSmokerEdit;
    private javax.swing.JCheckBox jCheckBoxVegeterianEdit;
    private javax.swing.JComboBox<String> jComboBoxAppointmentDoctor;
    private javax.swing.JComboBox<String> jComboBoxAppointmentPatient;
    private javax.swing.JComboBox<String> jComboBoxBloodTypeEdit;
    private javax.swing.JComboBox<String> jComboBoxCoffeeEdit;
    private javax.swing.JComboBox<String> jComboBoxDoctorType;
    private javax.swing.JComboBox<String> jComboBoxDoctorsForPatient;
    private javax.swing.JComboBox<String> jComboBoxDoctorsList;
    private javax.swing.JComboBox<String> jComboBoxListOfDrugsForPrescription;
    private javax.swing.JComboBox<String> jComboBoxListOfTestTypes;
    private javax.swing.JComboBox<String> jComboBoxNbDependentsEdit;
    private javax.swing.JComboBox<String> jComboBoxPatientList;
    private javax.swing.JComboBox<String> jComboBoxSexEdit;
    private javax.swing.JComboBox<String> jComboBoxSoftDrinksEdit;
    private javax.swing.JComboBox<String> jComboBoxSpecialistDoctorLists;
    private javax.swing.JFormattedTextField jFormattedAppointmentDate;
    private javax.swing.JFormattedTextField jFormattedAppointmentDateByDoctor;
    private javax.swing.JFormattedTextField jFormattedAppointmentEnd;
    private javax.swing.JFormattedTextField jFormattedAppointmentEndByDoctor;
    private javax.swing.JFormattedTextField jFormattedAppointmentStart;
    private javax.swing.JFormattedTextField jFormattedAppointmentStartByDoctor;
    private javax.swing.JFormattedTextField jFormattedAreaNextOfKinEdit;
    private javax.swing.JFormattedTextField jFormattedHouseNbNextOfKinEdit;
    private javax.swing.JFormattedTextField jFormattedTelephoneHomeNextOfKinEdit;
    private javax.swing.JFormattedTextField jFormattedTelephoneMobileNextOfKinEdit;
    private javax.swing.JFormattedTextField jFormattedTelephoneWorkNextOfKinEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldAreacodePermanentEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldAreacodePresentDoctor;
    private javax.swing.JFormattedTextField jFormattedTextFieldAreacodePresentEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldBirthdateEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldGrossIncomeEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldHouseNumberPresentDoctor;
    private javax.swing.JFormattedTextField jFormattedTextFieldHouseNumberPresentEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldHousenumberPermanentEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldPhoneNumHomeDoctor;
    private javax.swing.JFormattedTextField jFormattedTextFieldPhoneNumHomeEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldPhoneNumMobileDoctor;
    private javax.swing.JFormattedTextField jFormattedTextFieldPhoneNumMobileEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldPhoneNumWorkDoctor;
    private javax.swing.JFormattedTextField jFormattedTextFieldPhoneNumWorkEdit;
    private javax.swing.JFormattedTextField jFormattedTextFieldZipcodePermanentEdit;
    private javax.swing.JFormattedTextField jFormattedTextZipcodePresentDoctor;
    private javax.swing.JFormattedTextField jFormattedTextZipcodePresentEdit;
    private javax.swing.JFormattedTextField jFormattedZipCodeNextOfKinEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBirthdate;
    private javax.swing.JLabel jLabelBirthdate1;
    private javax.swing.JLabel jLabelBirthdate10;
    private javax.swing.JLabel jLabelBirthdate11;
    private javax.swing.JLabel jLabelBirthdate12;
    private javax.swing.JLabel jLabelBirthdate13;
    private javax.swing.JLabel jLabelBirthdate14;
    private javax.swing.JLabel jLabelBirthdate15;
    private javax.swing.JLabel jLabelBirthdate16;
    private javax.swing.JLabel jLabelBirthdate17;
    private javax.swing.JLabel jLabelBirthdate18;
    private javax.swing.JLabel jLabelBirthdate19;
    private javax.swing.JLabel jLabelBirthdate2;
    private javax.swing.JLabel jLabelBirthdate20;
    private javax.swing.JLabel jLabelBirthdate21;
    private javax.swing.JLabel jLabelBirthdate22;
    private javax.swing.JLabel jLabelBirthdate23;
    private javax.swing.JLabel jLabelBirthdate24;
    private javax.swing.JLabel jLabelBirthdate25;
    private javax.swing.JLabel jLabelBirthdate26;
    private javax.swing.JLabel jLabelBirthdate27;
    private javax.swing.JLabel jLabelBirthdate3;
    private javax.swing.JLabel jLabelBirthdate4;
    private javax.swing.JLabel jLabelBirthdate5;
    private javax.swing.JLabel jLabelBirthdate6;
    private javax.swing.JLabel jLabelBirthdate7;
    private javax.swing.JLabel jLabelBirthdate8;
    private javax.swing.JLabel jLabelBirthdate9;
    private javax.swing.JLabel jLabelFirstname10;
    private javax.swing.JLabel jLabelFirstname11;
    private javax.swing.JLabel jLabelFirstname12;
    private javax.swing.JLabel jLabelFirstname13;
    private javax.swing.JLabel jLabelFirstname14;
    private javax.swing.JLabel jLabelFirstname15;
    private javax.swing.JLabel jLabelFirstname16;
    private javax.swing.JLabel jLabelFirstname17;
    private javax.swing.JLabel jLabelFirstname18;
    private javax.swing.JLabel jLabelFirstname19;
    private javax.swing.JLabel jLabelFirstname20;
    private javax.swing.JLabel jLabelFirstname21;
    private javax.swing.JLabel jLabelFirstname22;
    private javax.swing.JLabel jLabelFirstname23;
    private javax.swing.JLabel jLabelFirstname3;
    private javax.swing.JLabel jLabelFirstname4;
    private javax.swing.JLabel jLabelFirstname5;
    private javax.swing.JLabel jLabelFirstname6;
    private javax.swing.JLabel jLabelFirstname7;
    private javax.swing.JLabel jLabelFirstname8;
    private javax.swing.JLabel jLabelFirstname9;
    private javax.swing.JLabel jLabelMiddle3;
    private javax.swing.JLabel jLabelMiddle4;
    private javax.swing.JLabel jLabelMiddle5;
    private javax.swing.JLabel jLabelMiddle6;
    private javax.swing.JLabel jLabelMiddle7;
    private javax.swing.JLabel jLabelPatientTitle;
    private javax.swing.JLabel jLabelSex3;
    private javax.swing.JLabel jLabelSex4;
    private javax.swing.JLabel jLabelSurname10;
    private javax.swing.JLabel jLabelSurname11;
    private javax.swing.JLabel jLabelSurname12;
    private javax.swing.JLabel jLabelSurname13;
    private javax.swing.JLabel jLabelSurname14;
    private javax.swing.JLabel jLabelSurname15;
    private javax.swing.JLabel jLabelSurname3;
    private javax.swing.JLabel jLabelSurname4;
    private javax.swing.JLabel jLabelSurname5;
    private javax.swing.JLabel jLabelSurname6;
    private javax.swing.JLabel jLabelSurname7;
    private javax.swing.JLabel jLabelSurname8;
    private javax.swing.JLabel jLabelSurname9;
    private javax.swing.JFormattedTextField jOutpatCreditCardNumber;
    private javax.swing.JFormattedTextField jOutpatOPIDEdit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAppointments;
    private javax.swing.JPanel jPanelBasicDetailsTab;
    private javax.swing.JPanel jPanelBills;
    private javax.swing.JPanel jPanelComplaintsTab;
    private javax.swing.JPanel jPanelContactDetailsTab;
    private javax.swing.JPanel jPanelDoctors;
    private javax.swing.JPanel jPanelLifestyleTab;
    private javax.swing.JPanel jPanelNextKinTab;
    private javax.swing.JPanel jPanelPatients;
    private javax.swing.JPanel jPanelPersonalDetailsTab;
    private javax.swing.JPanel jPanelPersonel;
    private javax.swing.JPanel jPanelReports;
    private javax.swing.JPanel jPanelTitleBackground;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSlider jSliderAvgCigarettesEdit;
    private javax.swing.JSlider jSliderAvgDrinksEdit;
    private javax.swing.JSlider jSliderHeightEdit;
    private javax.swing.JSlider jSliderWeightEdit;
    private javax.swing.JTabbedPane jTabbedPaneMain;
    private javax.swing.JTabbedPane jTabbedPanePatientData;
    private javax.swing.JTable jTableAppointments;
    private javax.swing.JTable jTableAppointmentsByDoctor;
    private javax.swing.JTable jTableBillsIssued;
    private javax.swing.JTable jTableBillsUnissued;
    private javax.swing.JTable jTablePatients;
    private javax.swing.JTable jTablePersonel;
    private javax.swing.JTable jTablePrescriptions;
    private javax.swing.JTable jTableTests;
    private javax.swing.JTextArea jTextAreaDiagnosisByDoctor;
    private javax.swing.JTextArea jTextAreaInstructions;
    private javax.swing.JTextArea jTextAreaStateOfComplainTreatmentHistoryEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintAllergiesEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintCardiacEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintComplaintStatementEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintDigestiveEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintDrugEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintMuscularEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintNeurologicalEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintOrthopedicEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintRespiratoryEdit;
    private javax.swing.JTextArea jTextAreaStateOfComplaintSurgeriesEdit;
    private javax.swing.JTextField jTextFieLDPatientFirstEdit;
    private javax.swing.JTextField jTextFieldCityNextOfKinEdit;
    private javax.swing.JTextField jTextFieldCityPermanentEdit;
    private javax.swing.JTextField jTextFieldCityPresentDoctor;
    private javax.swing.JTextField jTextFieldCityPresentEdit;
    private javax.swing.JTextField jTextFieldEmailDoctor;
    private javax.swing.JTextField jTextFieldEmailEdit;
    private javax.swing.JTextField jTextFieldEmailNextOfKinEdit;
    private javax.swing.JTextField jTextFieldFaxDoctor;
    private javax.swing.JTextField jTextFieldFaxEdit;
    private javax.swing.JTextField jTextFieldFaxNextOfKinEdit;
    private javax.swing.JTextField jTextFieldFirstNameDiabeticEdit;
    private javax.swing.JTextField jTextFieldFirstNameDoctor;
    private javax.swing.JTextField jTextFieldFirstNameHypertensiveEdit;
    private javax.swing.JTextField jTextFieldFirstNextOfKinEdit;
    private javax.swing.JTextField jTextFieldHospitalTreatedEdit;
    private javax.swing.JTextField jTextFieldMiddleNextOfKinEdit;
    private javax.swing.JTextField jTextFieldNextOfKinRelatinshipEdit;
    private javax.swing.JTextField jTextFieldOccupationEdit;
    private javax.swing.JTextField jTextFieldPagerDoctor;
    private javax.swing.JTextField jTextFieldPagerEdit;
    private javax.swing.JTextField jTextFieldPagerNextOfKinEdit;
    private javax.swing.JTextField jTextFieldPatientMidd;
    private javax.swing.JTextField jTextFieldRegularMealsEdit;
    private javax.swing.JTextField jTextFieldStateNextOfKinEdit;
    private javax.swing.JTextField jTextFieldStatePermanentEdit;
    private javax.swing.JTextField jTextFieldStatePresentDoctor;
    private javax.swing.JTextField jTextFieldStatePresentEdit;
    private javax.swing.JTextField jTextFieldStimulansEdit;
    private javax.swing.JTextField jTextFieldStreetNextOfKinEdit;
    private javax.swing.JTextField jTextFieldStreetPermanentEdit;
    private javax.swing.JTextField jTextFieldStreetPresentDoctor;
    private javax.swing.JTextField jTextFieldStreetPresentEdit;
    private javax.swing.JTextField jTextFieldSurnameDoctor;
    private javax.swing.JTextField jTextFieldSurnameNextOfKinEdit;
    private javax.swing.JTextField jTextSurrnameEdit;
    private javax.swing.JToggleButton jToggleButtonEditAppointmentByDoctor;
    // End of variables declaration//GEN-END:variables

    private void SetupPatientTable() {
        String[] columnNames = {"Id",
            "First name",
            "Middle name",
            "Last name",
            "Gender",
            "Birthdate",
            "Email",
            "Mobile"
        };

        DefaultTableModel model = (DefaultTableModel) jTablePatients.getModel();

        model.setColumnIdentifiers(columnNames);
        jTablePatients.setRowSelectionAllowed(true);
        jTablePatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTablePatients.getColumn(columnNames[0]).setPreferredWidth(10);

    }

    private void FillPatientTable() {
        DefaultTableModel model = (DefaultTableModel) jTablePatients.getModel();

        GetPatientsResult result = _patientsBL.getAll();

        if (!result.isOK) {
            System.out.println(result.msg);
        }
        _allPatients = result.patients;

        List<Patient> patients = result.patients;

        for (int i = 0; i < patients.size(); i++) {
            Object rowData[] = new Object[8];
            rowData[0] = patients.get(i).getId();
            rowData[1] = patients.get(i).getFirstname();
            rowData[2] = patients.get(i).getMiddlename();
            rowData[3] = patients.get(i).getLastname();
            rowData[4] = patients.get(i).getGender();
            rowData[5] = patients.get(i).getBirthdate();

            if (patients.get(i).getContactDetail() != null) {
                List<PhoneNumber> tempPhone = patients.get(i).getContactDetail().getPhones();

                for (PhoneNumber phoneNumber : tempPhone) {
                    if (phoneNumber.getPhoneType().equals(PhoneType.Email)) {
                        rowData[6] = phoneNumber.getNumber();
                    }
                    if (phoneNumber.getPhoneType().equals(PhoneType.Mobile)) {
                        rowData[7] = phoneNumber.getNumber();
                    }
                }
            }

            model.addRow(rowData);
        }
    }

    private void ToggleEnabledPatient(Boolean val) {
        jOutpatOPIDEdit.setEnabled(val);
        jTextFieLDPatientFirstEdit.setEnabled(val);
        jTextFieldPatientMidd.setEnabled(val);
        jTextSurrnameEdit.setEnabled(val);
        jComboBoxSexEdit.setEnabled(val);
        jFormattedTextFieldBirthdateEdit.setEnabled(val);
        jComboBoxDoctorsForPatient.setEnabled(val);

        jTextFieldStatePresentEdit.setEnabled(val);
        jTextFieldCityPresentEdit.setEnabled(val);
        jTextFieldStreetPresentEdit.setEnabled(val);
        jFormattedTextFieldHouseNumberPresentEdit.setEnabled(val);
        jFormattedTextFieldAreacodePresentEdit.setEnabled(val);
        jFormattedTextZipcodePresentEdit.setEnabled(val);

        jTextFieldStatePermanentEdit.setEnabled(val);
        jTextFieldCityPermanentEdit.setEnabled(val);
        jTextFieldStreetPermanentEdit.setEnabled(val);
        jFormattedTextFieldHousenumberPermanentEdit.setEnabled(val);
        jFormattedTextFieldAreacodePermanentEdit.setEnabled(val);
        jFormattedTextFieldZipcodePermanentEdit.setEnabled(val);

        jFormattedTextFieldPhoneNumWorkEdit.setEnabled(val);
        jFormattedTextFieldPhoneNumHomeEdit.setEnabled(val);
        jFormattedTextFieldPhoneNumMobileEdit.setEnabled(val);
        jTextFieldEmailEdit.setEnabled(val);
        jTextFieldFaxEdit.setEnabled(val);
        jTextFieldPagerEdit.setEnabled(val);

        jTextFieldFirstNextOfKinEdit.setEnabled(val);
        jTextFieldMiddleNextOfKinEdit.setEnabled(val);
        jTextFieldSurnameNextOfKinEdit.setEnabled(val);
        jTextFieldNextOfKinRelatinshipEdit.setEnabled(val);

        jTextFieldStateNextOfKinEdit.setEnabled(val);
        jTextFieldCityNextOfKinEdit.setEnabled(val);
        jTextFieldStreetNextOfKinEdit.setEnabled(val);
        jFormattedHouseNbNextOfKinEdit.setEnabled(val);
        jFormattedAreaNextOfKinEdit.setEnabled(val);
        jFormattedZipCodeNextOfKinEdit.setEnabled(val);

        jFormattedTelephoneWorkNextOfKinEdit.setEnabled(val);
        jFormattedTelephoneHomeNextOfKinEdit.setEnabled(val);
        jFormattedTelephoneMobileNextOfKinEdit.setEnabled(val);
        jTextFieldEmailNextOfKinEdit.setEnabled(val);
        jTextFieldFaxNextOfKinEdit.setEnabled(val);
        jTextFieldPagerNextOfKinEdit.setEnabled(val);

        jCheckBoxMarriedEdit.setEnabled(val);
        jComboBoxNbDependentsEdit.setEnabled(val);
        jComboBoxBloodTypeEdit.setEnabled(val);
        jTextFieldOccupationEdit.setEnabled(val);
        jFormattedTextFieldGrossIncomeEdit.setEnabled(val);
        jSliderHeightEdit.setEnabled(val);
        jSliderWeightEdit.setEnabled(val);
        jCheckBoxVegeterianEdit.setEnabled(val);
        jCheckBoxSmokerEdit.setEnabled(val);
        jCheckBoxAlcoholEdit.setEnabled(val);
        jCheckBoxEatingHomeEdit.setEnabled(val);
        jComboBoxCoffeeEdit.setEnabled(val);
        jComboBoxSoftDrinksEdit.setEnabled(val);
        jTextFieldStimulansEdit.setEnabled(val);
        jTextFieldRegularMealsEdit.setEnabled(val);
        jSliderAvgDrinksEdit.setEnabled(val);
        jSliderAvgCigarettesEdit.setEnabled(val);
        jTextAreaStateOfComplaintComplaintStatementEdit.setEnabled(val);
        jTextAreaStateOfComplainTreatmentHistoryEdit.setEnabled(val);
        jTextFieldHospitalTreatedEdit.setEnabled(val);

        jTextFieldFirstNameDiabeticEdit.setEnabled(val);
        jTextFieldFirstNameHypertensiveEdit.setEnabled(val);
        jTextAreaStateOfComplaintNeurologicalEdit.setEnabled(val);
        jTextAreaStateOfComplaintAllergiesEdit.setEnabled(val);
        jTextAreaStateOfComplaintDrugEdit.setEnabled(val);
        jTextAreaStateOfComplaintOrthopedicEdit.setEnabled(val);
        jTextAreaStateOfComplaintMuscularEdit.setEnabled(val);
        jTextAreaStateOfComplaintRespiratoryEdit.setEnabled(val);
        jTextAreaStateOfComplaintSurgeriesEdit.setEnabled(val);
        jTextAreaStateOfComplaintCardiacEdit.setEnabled(val);
        jTextAreaStateOfComplaintDigestiveEdit.setEnabled(val);

    }

    private void SetEditValues() {
        //Basic
        if (_currentPatient.getOPID() != null) {
            jOutpatOPIDEdit.setText(_currentPatient.getOPID().toString());
        }

        if (_currentPatient.getFirstname() != null) {
            jTextFieLDPatientFirstEdit.setText(_currentPatient.getFirstname());
        }

        if (_currentPatient.getMiddlename() != null) {
            jTextFieldPatientMidd.setText(_currentPatient.getMiddlename());
        }

        if (_currentPatient.getLastname() != null) {
            jTextSurrnameEdit.setText(_currentPatient.getLastname());
        }

        if (_currentPatient.getGender() == 'M') {
            jComboBoxSexEdit.setSelectedIndex(0);
        } else {
            jComboBoxSexEdit.setSelectedIndex(1);
        }

        if (_currentPatient.getBirthdate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            jFormattedTextFieldBirthdateEdit.setText(formatter.format(_currentPatient.getBirthdate()));
        }

        if (_currentPatient.getDoctor() != null) {
            jComboBoxDoctorsForPatient.setSelectedItem(_currentPatient.getDoctor());
        }

        //Address present
        if (_currentPatient.getContactDetail().getPresentAddress() != null) {
            if (_currentPatient.getContactDetail().getPresentAddress().getState() != null) {
                jTextFieldStatePresentEdit.setText(_currentPatient.getContactDetail().getPresentAddress().getState());
            }
            if (_currentPatient.getContactDetail().getPresentAddress().getCity() != null) {
                jTextFieldCityPresentEdit.setText(_currentPatient.getContactDetail().getPresentAddress().getCity());
            }
            if (_currentPatient.getContactDetail().getPresentAddress().getStreet() != null) {
                jTextFieldStreetPresentEdit.setText(_currentPatient.getContactDetail().getPresentAddress().getStreet());
            }

            jFormattedTextFieldHouseNumberPresentEdit.setText(Integer.toString(_currentPatient.getContactDetail().getPresentAddress().getHouseNumber()));

            if (_currentPatient.getContactDetail().getPresentAddress().getArea() != null) {
                jFormattedTextFieldAreacodePresentEdit.setText(_currentPatient.getContactDetail().getPresentAddress().getArea());
            }
            if (_currentPatient.getContactDetail().getPresentAddress().getZipCode() != null) {
                jFormattedTextZipcodePresentEdit.setText(_currentPatient.getContactDetail().getPresentAddress().getZipCode());
            }
        }
        //Address permanent
        if (_currentPatient.getContactDetail().getPermanentAddress() != null) {

            if (_currentPatient.getContactDetail().getPermanentAddress().getState() != null) {
                jTextFieldStatePermanentEdit.setText(_currentPatient.getContactDetail().getPermanentAddress().getState());
            }
            if (_currentPatient.getContactDetail().getPermanentAddress().getCity() != null) {
                jTextFieldCityPermanentEdit.setText(_currentPatient.getContactDetail().getPermanentAddress().getCity());
            }
            if (_currentPatient.getContactDetail().getPermanentAddress().getStreet() != null) {
                jTextFieldStreetPermanentEdit.setText(_currentPatient.getContactDetail().getPermanentAddress().getStreet());
            }

            jFormattedTextFieldHousenumberPermanentEdit.setText(Integer.toString(_currentPatient.getContactDetail().getPermanentAddress().getHouseNumber()));

            if (_currentPatient.getContactDetail().getPermanentAddress().getArea() != null) {
                jFormattedTextFieldAreacodePermanentEdit.setText(_currentPatient.getContactDetail().getPermanentAddress().getArea());
            }
            if (_currentPatient.getContactDetail().getPermanentAddress().getZipCode() != null) {
                jFormattedTextFieldZipcodePermanentEdit.setText(_currentPatient.getContactDetail().getPermanentAddress().getZipCode());
            }
        }

        //Phones
        List<PhoneNumber> phones = _currentPatient.getContactDetail().getPhones();

        if (!phones.isEmpty()) {
            for (PhoneNumber phone : phones) {
                if (phone.getPhoneType().equals(PhoneType.Work)) {
                    jFormattedTextFieldPhoneNumWorkEdit.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Home)) {
                    jFormattedTextFieldPhoneNumHomeEdit.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Mobile)) {
                    jFormattedTextFieldPhoneNumMobileEdit.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Email)) {
                    jTextFieldEmailEdit.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Fax)) {
                    jTextFieldFaxEdit.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Pager)) {
                    jTextFieldPagerEdit.setText(phone.getNumber());
                }
            }
        }

        //Next of Kin
        if (_currentPatient.getNextOfKin() != null) {

            if (_currentPatient.getNextOfKin().getFirstname() != null) {
                jTextFieldFirstNextOfKinEdit.setText(_currentPatient.getNextOfKin().getFirstname());
            }

            if (_currentPatient.getNextOfKin().getMiddlename() != null) {
                jTextFieldMiddleNextOfKinEdit.setText(_currentPatient.getNextOfKin().getMiddlename());
            }

            if (_currentPatient.getNextOfKin().getLastname() != null) {
                jTextFieldSurnameNextOfKinEdit.setText(_currentPatient.getNextOfKin().getLastname());
            }
            if (_currentPatient.getNextOfKin().getOutpatientRelationship() != null) {
                jTextFieldNextOfKinRelatinshipEdit.setText(_currentPatient.getNextOfKin().getOutpatientRelationship());
            }
            //Next of kin address
            if (_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress() != null) {
                if (_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getState() != null) {
                    jTextFieldStateNextOfKinEdit.setText(_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getState());
                }
                if (_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getCity() != null) {
                    jTextFieldCityNextOfKinEdit.setText(_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getCity());
                }
                if (_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getStreet() != null) {
                    jTextFieldStreetNextOfKinEdit.setText(_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getStreet());
                }
                jFormattedHouseNbNextOfKinEdit.setText(Integer.toString(_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getHouseNumber()));

                if (_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getArea() != null) {
                    jFormattedAreaNextOfKinEdit.setText(_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getArea());
                }
                if (_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getZipCode() != null) {
                    jFormattedZipCodeNextOfKinEdit.setText(_currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress().getZipCode());
                }
            }

            //Phones
            List<PhoneNumber> nextOfKinPhones = _currentPatient.getNextOfKin().getContactDetailNextOf().getPhones();

            if (!nextOfKinPhones.isEmpty()) {
                for (PhoneNumber phone : nextOfKinPhones) {
                    if (phone.getPhoneType().equals(PhoneType.Work)) {
                        jFormattedTelephoneWorkNextOfKinEdit.setText(phone.getNumber());
                    }
                    if (phone.getPhoneType().equals(PhoneType.Home)) {
                        jFormattedTelephoneHomeNextOfKinEdit.setText(phone.getNumber());
                    }
                    if (phone.getPhoneType().equals(PhoneType.Mobile)) {
                        jFormattedTelephoneMobileNextOfKinEdit.setText(phone.getNumber());
                    }
                    if (phone.getPhoneType().equals(PhoneType.Email)) {
                        jTextFieldEmailNextOfKinEdit.setText(phone.getNumber());
                    }
                    if (phone.getPhoneType().equals(PhoneType.Fax)) {
                        jTextFieldFaxNextOfKinEdit.setText(phone.getNumber());
                    }
                    if (phone.getPhoneType().equals(PhoneType.Pager)) {
                        jTextFieldPagerNextOfKinEdit.setText(phone.getNumber());
                    }
                }
            }
        }

        //Personal details
        jCheckBoxMarriedEdit.setSelected(_currentPatient.getPersonalDetail().isMaritalStatus());
        jComboBoxNbDependentsEdit.setSelectedIndex(_currentPatient.getPersonalDetail().getNbOfDependents() + 1);

        if (_currentPatient.getPersonalDetail().getBloodType() != null) {
            String bloodType = _currentPatient.getPersonalDetail().getBloodType();
            if (bloodType.equalsIgnoreCase("A")) {
                jComboBoxBloodTypeEdit.setSelectedIndex(0);
            } else if (bloodType.equalsIgnoreCase("B")) {
                jComboBoxBloodTypeEdit.setSelectedIndex(1);
            } else if (bloodType.equalsIgnoreCase("AB")) {
                jComboBoxBloodTypeEdit.setSelectedIndex(2);
            } else if (bloodType.equalsIgnoreCase("0")) {
                jComboBoxBloodTypeEdit.setSelectedIndex(3);
            } else {
                jComboBoxBloodTypeEdit.setSelectedIndex(0);
            }
        }

        if (_currentPatient.getPersonalDetail().getOccupation() != null) {
            jTextFieldOccupationEdit.setText(_currentPatient.getPersonalDetail().getOccupation());
        }

        if (_currentPatient.getPersonalDetail().getGrossAnnualIncome() != null) {
            jFormattedTextFieldGrossIncomeEdit.setText(_currentPatient.getPersonalDetail().getGrossAnnualIncome().toString());
        }

        jSliderHeightEdit.setValue(_currentPatient.getPersonalDetail().getHeight());
        jSliderWeightEdit.setValue(_currentPatient.getPersonalDetail().getWeight());

        //Lifestyle
        jCheckBoxVegeterianEdit.setSelected(_currentPatient.getPatientLifestyle().isVegeterian());
        jCheckBoxSmokerEdit.setSelected(_currentPatient.getPatientLifestyle().isSmoker());
        jCheckBoxAlcoholEdit.setSelected(_currentPatient.getPatientLifestyle().isAlcoholConsumer());
        jCheckBoxEatingHomeEdit.setSelected(_currentPatient.getPatientLifestyle().isEatingHomePredominantly());

        jComboBoxCoffeeEdit.setSelectedIndex(_currentPatient.getPatientLifestyle().getCoffePerDay() + 1);
        jComboBoxSoftDrinksEdit.setSelectedIndex(_currentPatient.getPatientLifestyle().getSoftDrinksPerDay() + 1);

        if (_currentPatient.getPatientLifestyle().getUsingStimulans() != null) {
            jTextFieldStimulansEdit.setText(_currentPatient.getPatientLifestyle().getUsingStimulans());
        }
        if (_currentPatient.getPatientLifestyle().getRegularMelas() != null) {
            jTextFieldRegularMealsEdit.setText(_currentPatient.getPatientLifestyle().getRegularMelas());
        }

        jSliderAvgDrinksEdit.setValue(_currentPatient.getPatientLifestyle().getAvgDrinksDay());
        jSliderAvgCigarettesEdit.setValue(_currentPatient.getPatientLifestyle().getAvgCigarettesDay());

        //BasicComplaint
        if (_currentPatient.getBasicComplaints().getComplaintStatement() != null) {
            jTextAreaStateOfComplaintComplaintStatementEdit.setText(_currentPatient.getBasicComplaints().getComplaintStatement());
        }
        if (_currentPatient.getBasicComplaints().getTreatmentHistory() != null) {
            jTextAreaStateOfComplainTreatmentHistoryEdit.setText(_currentPatient.getBasicComplaints().getTreatmentHistory());
        }
        if (_currentPatient.getBasicComplaints().getHospitalTreated() != null) {
            jTextFieldHospitalTreatedEdit.setText(_currentPatient.getBasicComplaints().getHospitalTreated());
        }

        //Medical complaint
        if (_currentPatient.getMedicalComplaints().getDiabetic() != null) {
            jTextFieldFirstNameDiabeticEdit.setText(_currentPatient.getMedicalComplaints().getDiabetic());
        }
        if (_currentPatient.getMedicalComplaints().getHypertensive() != null) {
            jTextFieldFirstNameHypertensiveEdit.setText(_currentPatient.getMedicalComplaints().getHypertensive());
        }
        if (_currentPatient.getMedicalComplaints().getNeurologicalCondition() != null) {
            jTextAreaStateOfComplaintNeurologicalEdit.setText(_currentPatient.getMedicalComplaints().getNeurologicalCondition());
        }
        if (_currentPatient.getMedicalComplaints().getAllergies() != null) {
            jTextAreaStateOfComplaintAllergiesEdit.setText(_currentPatient.getMedicalComplaints().getAllergies());
        }
        if (_currentPatient.getMedicalComplaints().getDrugsReaction() != null) {
            jTextAreaStateOfComplaintDrugEdit.setText(_currentPatient.getMedicalComplaints().getDrugsReaction());
        }
        if (_currentPatient.getMedicalComplaints().getOrthopedicCondition() != null) {
            jTextAreaStateOfComplaintOrthopedicEdit.setText(_currentPatient.getMedicalComplaints().getOrthopedicCondition());
        }
        if (_currentPatient.getMedicalComplaints().getMuscularCondition() != null) {
            jTextAreaStateOfComplaintMuscularEdit.setText(_currentPatient.getMedicalComplaints().getMuscularCondition());
        }
        if (_currentPatient.getMedicalComplaints().getRespiratoryCondition() != null) {
            jTextAreaStateOfComplaintRespiratoryEdit.setText(_currentPatient.getMedicalComplaints().getRespiratoryCondition());
        }
        if (_currentPatient.getMedicalComplaints().getDiabetic() != null) {
            jTextAreaStateOfComplaintSurgeriesEdit.setText(_currentPatient.getMedicalComplaints().getDiabetic());
        }
        if (_currentPatient.getMedicalComplaints().getCardiacCondition() != null) {
            jTextAreaStateOfComplaintCardiacEdit.setText(_currentPatient.getMedicalComplaints().getCardiacCondition());
        }
        if (_currentPatient.getMedicalComplaints().getDigestiveCondition() != null) {
            jTextAreaStateOfComplaintDigestiveEdit.setText(_currentPatient.getMedicalComplaints().getDigestiveCondition());
        }
    }

    private void jTextFieldFirstNameExtensivejTextFieldOnlyAplhabeticKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }

    private void ClearInputFields() {
        jOutpatOPIDEdit.setText("");
        jTextFieLDPatientFirstEdit.setText("");
        jTextFieldPatientMidd.setText("");
        jTextSurrnameEdit.setText("");
        jComboBoxSexEdit.setSelectedIndex(0);
        jFormattedTextFieldBirthdateEdit.setText("");
        jComboBoxDoctorsForPatient.setSelectedIndex(-1);
        jComboBoxDoctorsForPatient.removeAll();

        jTextFieldStatePresentEdit.setText("");
        jTextFieldCityPresentEdit.setText("");
        jTextFieldStreetPresentEdit.setText("");
        jFormattedTextFieldHouseNumberPresentEdit.setText("");
        jFormattedTextFieldAreacodePresentEdit.setText("");
        jFormattedTextZipcodePresentEdit.setText("");

        jTextFieldStatePermanentEdit.setText("");
        jTextFieldCityPermanentEdit.setText("");
        jTextFieldStreetPermanentEdit.setText("");
        jFormattedTextFieldHousenumberPermanentEdit.setText("");
        jFormattedTextFieldAreacodePermanentEdit.setText("");
        jFormattedTextFieldZipcodePermanentEdit.setText("");

        jFormattedTextFieldPhoneNumWorkEdit.setText("");
        jFormattedTextFieldPhoneNumHomeEdit.setText("");
        jFormattedTextFieldPhoneNumMobileEdit.setText("");
        jTextFieldEmailEdit.setText("");
        jTextFieldFaxEdit.setText("");
        jTextFieldPagerEdit.setText("");

        jTextFieldFirstNextOfKinEdit.setText("");
        jTextFieldMiddleNextOfKinEdit.setText("");
        jTextFieldSurnameNextOfKinEdit.setText("");
        jTextFieldNextOfKinRelatinshipEdit.setText("");

        jTextFieldStateNextOfKinEdit.setText("");
        jTextFieldCityNextOfKinEdit.setText("");
        jTextFieldStreetNextOfKinEdit.setText("");
        jFormattedHouseNbNextOfKinEdit.setText("");
        jFormattedAreaNextOfKinEdit.setText("");
        jFormattedZipCodeNextOfKinEdit.setText("");

        jFormattedTelephoneWorkNextOfKinEdit.setText("");
        jFormattedTelephoneHomeNextOfKinEdit.setText("");
        jFormattedTelephoneMobileNextOfKinEdit.setText("");
        jTextFieldEmailNextOfKinEdit.setText("");
        jTextFieldFaxNextOfKinEdit.setText("");
        jTextFieldPagerNextOfKinEdit.setText("");

        jCheckBoxMarriedEdit.setText("");
        jComboBoxNbDependentsEdit.setSelectedIndex(0);
        jComboBoxBloodTypeEdit.setSelectedIndex(0);
        jTextFieldOccupationEdit.setText("");
        jFormattedTextFieldGrossIncomeEdit.setText("");
        jSliderHeightEdit.setValue(179);
        jSliderWeightEdit.setValue(80);

        jCheckBoxVegeterianEdit.setText("");
        jCheckBoxSmokerEdit.setText("");
        jCheckBoxAlcoholEdit.setText("");
        jCheckBoxEatingHomeEdit.setText("");
        jComboBoxCoffeeEdit.setSelectedIndex(0);
        jComboBoxSoftDrinksEdit.setSelectedIndex(0);
        jTextFieldStimulansEdit.setText("");
        jTextFieldRegularMealsEdit.setText("");
        jSliderAvgDrinksEdit.setValue(0);
        jSliderAvgCigarettesEdit.setValue(0);

        jTextAreaStateOfComplaintComplaintStatementEdit.setText("");
        jTextAreaStateOfComplainTreatmentHistoryEdit.setText("");
        jTextFieldHospitalTreatedEdit.setText("");

        jTextFieldFirstNameDiabeticEdit.setText("");
        jTextFieldFirstNameHypertensiveEdit.setText("");
        jTextAreaStateOfComplaintNeurologicalEdit.setText("");
        jTextAreaStateOfComplaintAllergiesEdit.setText("");
        jTextAreaStateOfComplaintDrugEdit.setText("");
        jTextAreaStateOfComplaintOrthopedicEdit.setText("");
        jTextAreaStateOfComplaintMuscularEdit.setText("");
        jTextAreaStateOfComplaintRespiratoryEdit.setText("");
        jTextAreaStateOfComplaintSurgeriesEdit.setText("");
        jTextAreaStateOfComplaintCardiacEdit.setText("");
        jTextAreaStateOfComplaintDigestiveEdit.setText("");

    }

    private boolean PatientInputValidation() {
        String validationStatusMessages = "";
        if (jTextFieLDPatientFirstEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Patient first name is required";
        }

        if (jTextSurrnameEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Patient last name is required";
        }

        if (jOutpatOPIDEdit.getText().length() != 11) {
            validationStatusMessages
                    += "\n Patient OID needs to have 11 numbers and is required";
        }

        if (!jTextFieldEmailEdit.getText().matches(EMAIL_REGEX) || jTextFieldEmailEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Email is not valid!";
        }

        //Address
        if (jTextFieldStatePresentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Present address state is required";
        }
        if (jTextFieldCityPresentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Present address city is required";
        }
        if (jTextFieldStreetPresentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Present address street is required";
        }
        if (jFormattedTextFieldHouseNumberPresentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Present address house number is required";
        }

        //Permanent address
        if (jTextFieldStatePermanentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Permanent address state is required";
        }
        if (jTextFieldCityPermanentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Permanent address city is required";
        }
        if (jTextFieldStreetPermanentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Permanent address street is required";
        }
        if (jFormattedTextFieldHousenumberPermanentEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Permanent address house number is required";
        }

        if (jFormattedTextFieldPhoneNumMobileEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Mobile phone contact number is required";
        }

        if (jTextFieldFirstNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin first name is required";
        }
        if (jTextFieldSurnameNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin last name is required";
        }
        if (jTextFieldNextOfKinRelatinshipEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin relationship is required";
        }

        //Address
        if (jTextFieldStateNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin Present address state is required";
        }
        if (jTextFieldCityNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin Present address city is required";
        }
        if (jTextFieldStreetNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin Present address street is required";
        }
        if (jFormattedHouseNbNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin Present address house number is required";
        }

        if (!jTextFieldEmailNextOfKinEdit.getText().matches(EMAIL_REGEX) || jTextFieldEmailNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin email is not valid!";
        }

        if (jFormattedTelephoneMobileNextOfKinEdit.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Next of kin mobile is required";
        }

        if (validationStatusMessages.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, validationStatusMessages, "Validation check failed:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void UpdatePatient() {

        //Basic Complain
        BasicComplaint tempBasic = _currentPatient.getBasicComplaints();
        tempBasic.setComplaintStatement(jTextAreaStateOfComplaintComplaintStatementEdit.getText());
        tempBasic.setTreatmentHistory(jTextAreaStateOfComplainTreatmentHistoryEdit.getText());
        tempBasic.setHospitalTreated(jTextFieldHospitalTreatedEdit.getText());

        //Medical complains
        MedicalComplaint tempMedical = _currentPatient.getMedicalComplaints();
        tempMedical.setDiabetic(jTextFieldFirstNameDiabeticEdit.getText());
        tempMedical.setHypertensive(jTextFieldFirstNameHypertensiveEdit.getText());
        tempMedical.setNeurologicalCondition(jTextAreaStateOfComplaintNeurologicalEdit.getText());
        tempMedical.setAllergies(jTextAreaStateOfComplaintAllergiesEdit.getText());
        tempMedical.setDrugsReaction(jTextAreaStateOfComplaintDrugEdit.getText());
        tempMedical.setOrthopedicCondition(jTextAreaStateOfComplaintOrthopedicEdit.getText());
        tempMedical.setMuscularCondition(jTextAreaStateOfComplaintMuscularEdit.getText());
        tempMedical.setRespiratoryCondition(jTextAreaStateOfComplaintRespiratoryEdit.getText());
        tempMedical.setHistoryOfSurgeries(jTextAreaStateOfComplaintSurgeriesEdit.getText());
        tempMedical.setCardiacCondition(jTextAreaStateOfComplaintCardiacEdit.getText());
        tempMedical.setDigestiveCondition(jTextAreaStateOfComplaintDigestiveEdit.getText());

        //Lifestyle
        PatientLifestyle tempLifestyle = _currentPatient.getPatientLifestyle();
        tempLifestyle.setVegeterian(jCheckBoxVegeterianEdit.isSelected());
        tempLifestyle.setSmoker(jCheckBoxSmokerEdit.isSelected());
        tempLifestyle.setAlcoholConsumer(jCheckBoxAlcoholEdit.isSelected());
        tempLifestyle.setEatingHomePredominantly(jCheckBoxEatingHomeEdit.isSelected());
        tempLifestyle.setCoffePerDay(jComboBoxCoffeeEdit.getSelectedIndex());
        tempLifestyle.setSoftDrinksPerDay(jComboBoxSoftDrinksEdit.getSelectedIndex());
        tempLifestyle.setUsingStimulans(jTextFieldStimulansEdit.getText());
        tempLifestyle.setRegularMelas(jTextFieldRegularMealsEdit.getText());
        tempLifestyle.setAvgDrinksDay(jSliderAvgDrinksEdit.getValue());
        tempLifestyle.setAvgCigarettesDay(jSliderAvgCigarettesEdit.getValue());

        //PersonalDetail
        PersonalDetail tempPersonal = _currentPatient.getPersonalDetail();
        tempPersonal.setMaritalStatus(jCheckBoxMarriedEdit.isSelected());
        tempPersonal.setNbOfDependents(jComboBoxNbDependentsEdit.getSelectedIndex());
        tempPersonal.setBloodType((String) jComboBoxBloodTypeEdit.getSelectedItem());
        tempPersonal.setOccupation(jTextFieldOccupationEdit.getText());
        tempPersonal.setOccupation(jFormattedTextFieldGrossIncomeEdit.getText());
        tempPersonal.setHeight(jSliderHeightEdit.getValue());
        tempPersonal.setWeight(jSliderWeightEdit.getValue());

        //Next of kin
        NextOfKin tempNextOf = _currentPatient.getNextOfKin();
        tempNextOf.setFirstname(jTextFieldFirstNextOfKinEdit.getText());
        tempNextOf.setMiddlename(jTextFieldMiddleNextOfKinEdit.getText());
        tempNextOf.setLastname(jTextFieldSurnameNextOfKinEdit.getText());
        tempNextOf.setOutpatientRelationship(jTextFieldNextOfKinRelatinshipEdit.getText());

        //Next of kin Address
        Address tempAddressNext = _currentPatient.getNextOfKin().getContactDetailNextOf().getPresentAddress();
        tempAddressNext.setState(jTextFieldStateNextOfKinEdit.getText());
        tempAddressNext.setCity(jTextFieldCityNextOfKinEdit.getText());
        tempAddressNext.setStreet(jTextFieldStreetNextOfKinEdit.getText());
        tempAddressNext.setHouseNumber(Integer.parseInt(jFormattedHouseNbNextOfKinEdit.getText()));
        tempAddressNext.setArea(jFormattedAreaNextOfKinEdit.getText());
        tempAddressNext.setZipCode(jFormattedZipCodeNextOfKinEdit.getText());

        //Next of kin Phones
        List<PhoneNumber> tempPhonesNext = _currentPatient.getNextOfKin().getContactDetailNextOf().getPhones();
        if (!tempPhonesNext.isEmpty()) {
            for (int i = 0; i < tempPhonesNext.size(); i++) {

                if (tempPhonesNext.get(i).getPhoneType().equals(PhoneType.Work)) {
                    tempPhonesNext.get(i).setNumber(jFormattedTelephoneWorkNextOfKinEdit.getText());
                }
                if (tempPhonesNext.get(i).getPhoneType().equals(PhoneType.Home)) {
                    tempPhonesNext.get(i).setNumber(jFormattedTelephoneHomeNextOfKinEdit.getText());
                }
                if (tempPhonesNext.get(i).getPhoneType().equals(PhoneType.Mobile)) {
                    tempPhonesNext.get(i).setNumber("asdfg");
                }
                if (tempPhonesNext.get(i).getPhoneType().equals(PhoneType.Email)) {
                    tempPhonesNext.get(i).setNumber(jTextFieldEmailNextOfKinEdit.getText());
                }
                if (tempPhonesNext.get(i).getPhoneType().equals(PhoneType.Fax)) {
                    tempPhonesNext.get(i).setNumber(jTextFieldFaxNextOfKinEdit.getText());
                }
                if (tempPhonesNext.get(i).getPhoneType().equals(PhoneType.Pager)) {
                    tempPhonesNext.get(i).setNumber(jTextFieldPagerNextOfKinEdit.getText());
                }
            }
        }

        //Phones
        List<PhoneNumber> tempPhones = _currentPatient.getNextOfKin().getContactDetailNextOf().getPhones();
        if (!tempPhones.isEmpty()) {
            for (int i = 0; i < tempPhones.size(); i++) {

                if (tempPhones.get(i).getPhoneType().equals(PhoneType.Work)) {
                    tempPhones.get(i).setNumber(jFormattedTextFieldPhoneNumWorkEdit.getText());
                }
                if (tempPhones.get(i).getPhoneType().equals(PhoneType.Home)) {
                    tempPhones.get(i).setNumber(jFormattedTextFieldPhoneNumHomeEdit.getText());
                }
                if (tempPhones.get(i).getPhoneType().equals(PhoneType.Mobile)) {
                    tempPhones.get(i).setNumber(jFormattedTextFieldPhoneNumMobileEdit.getText());
                }
                if (tempPhones.get(i).getPhoneType().equals(PhoneType.Email)) {
                    tempPhones.get(i).setNumber(jTextFieldEmailEdit.getText());
                }
                if (tempPhones.get(i).getPhoneType().equals(PhoneType.Fax)) {
                    tempPhones.get(i).setNumber(jTextFieldFaxEdit.getText());
                }
                if (tempPhones.get(i).getPhoneType().equals(PhoneType.Pager)) {
                    tempPhones.get(i).setNumber(jTextFieldPagerEdit.getText());
                }
            }
        }

        //Address Present(patient)
        Address tempAddressPresent = _currentPatient.getContactDetail().getPresentAddress();
        tempAddressPresent.setState(jTextFieldStatePresentEdit.getText());
        tempAddressPresent.setCity(jTextFieldCityPresentEdit.getText());
        tempAddressPresent.setStreet(jTextFieldStreetPresentEdit.getText());
        tempAddressPresent.setHouseNumber(Integer.parseInt(jFormattedTextFieldHouseNumberPresentEdit.getText()));
        tempAddressPresent.setArea(jFormattedTextFieldAreacodePresentEdit.getText());
        tempAddressPresent.setZipCode(jFormattedTextZipcodePresentEdit.getText());

        //Address Permanent(patient)
        Address tempAddressPermanent = _currentPatient.getContactDetail().getPermanentAddress();
        tempAddressPermanent.setState(jTextFieldStatePermanentEdit.getText());
        tempAddressPermanent.setCity(jTextFieldCityPermanentEdit.getText());
        tempAddressPermanent.setStreet(jTextFieldStreetPermanentEdit.getText());
        tempAddressPermanent.setHouseNumber(Integer.parseInt(jFormattedTextFieldHousenumberPermanentEdit.getText()));
        tempAddressPermanent.setArea(jFormattedTextFieldAreacodePermanentEdit.getText());
        tempAddressPermanent.setZipCode(jFormattedTextFieldZipcodePermanentEdit.getText());

        //Patient
        _currentPatient.setOPID(Long.parseLong(jOutpatOPIDEdit.getText()));
        _currentPatient.setFirstname(jTextFieLDPatientFirstEdit.getText());
        _currentPatient.setMiddlename(jTextFieldPatientMidd.getText());
        _currentPatient.setLastname(jTextSurrnameEdit.getText());
        _currentPatient.setGender(jComboBoxSexEdit.getSelectedItem().toString().charAt(0));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthday = LocalDate.parse(jFormattedTextFieldBirthdateEdit.getText(), formatter);
        _currentPatient.setBirthdate(birthday);

        if (jComboBoxDoctorsForPatient.getSelectedIndex() == -1) {
            return;
        }
        _currentPatient.setDoctor(_generalDoctors.get(jComboBoxDoctorsForPatient.getSelectedIndex()));
        //----------------------
        try {
            _patientsBL.updatePatient(_currentPatient);
            _addressBL.updateAddress(tempAddressPermanent);
            _addressBL.updateAddress(tempAddressPresent);
            _addressBL.updateAddress(tempAddressNext);

            for (PhoneNumber tempPhone : tempPhones) {
                _phoneNumberBL.updatePhoneNumber(tempPhone);
            }
            for (PhoneNumber tempPhone : tempPhonesNext) {
                _phoneNumberBL.updatePhoneNumber(tempPhone);
            }

            _nextOfKinBL.updateNextOfKin(tempNextOf);
            _personalDetailsBL.updateContactDetail(tempPersonal);
            _patientLifestyleBL.updatePatientLifestyle(tempLifestyle);
            _medicalComplaintBL.updateMedicalComplaint(tempMedical);
            _basicComplaintBL.updateBasicComplaint(tempBasic);
        } catch (Exception e) {
            System.out.println(e);
        }
        ToggleEnabledPatient(false);
    }

    private void SetupDoctorTable() {
        String[] columnNames = {"Id",
            "First name",
            "Last name",
            "Doctor type",
            "Unavailable",
            "Email",
            "Mobile"
        };

        DefaultTableModel model = (DefaultTableModel) jTablePersonel.getModel();

        model.setColumnIdentifiers(columnNames);
        jTablePersonel.setRowSelectionAllowed(true);
        jTablePersonel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTablePersonel.getColumn(columnNames[0]).setPreferredWidth(5);
        jTablePersonel.getColumn(columnNames[4]).setPreferredWidth(30);
        jTablePersonel.getColumn(columnNames[5]).setPreferredWidth(160);

    }

    private void FillDoctorTable() {
        DefaultTableModel model = (DefaultTableModel) jTablePersonel.getModel();

        GetDoctorResult result = _doctorBL.getAll();

        if (!result.isOK) {
            System.out.println(result.msg);
        }
        _allDoctors = result.doctors;
        List<Doctor> tempDocs = result.doctors.stream().filter(x -> (DoctorType.General).equals(x.getDoctorType())).collect(Collectors.toList());
        List<Doctor> tempDocsOthers = result.doctors.stream().filter(x -> !(DoctorType.General).equals(x.getDoctorType())).collect(Collectors.toList());

        _generalDoctors = tempDocs;
        _specDoctors = tempDocsOthers;

        jComboBoxDoctorsForPatient.setModel(new DefaultComboBoxModel(_generalDoctors.toArray()));
        jComboBoxDoctorsList.setModel(new DefaultComboBoxModel(_allDoctors.toArray()));
        jComboBoxSpecialistDoctorLists.setModel(new DefaultComboBoxModel(_specDoctors.toArray()));

        List<Doctor> docs = result.doctors;

        for (int i = 0; i < docs.size(); i++) {
            Object rowData[] = new Object[7];
            rowData[0] = docs.get(i).getId();
            rowData[1] = docs.get(i).getFirstname();
            rowData[2] = docs.get(i).getLastname();
            rowData[3] = docs.get(i).getDoctorType();
            rowData[4] = docs.get(i).isUnavailable();

            if (docs.get(i).getContactDetail() != null) {
                List<PhoneNumber> tempPhone = docs.get(i).getContactDetail().getPhones();

                for (PhoneNumber phoneNumber : tempPhone) {
                    if (phoneNumber.getPhoneType().equals(PhoneType.Email)) {
                        rowData[5] = phoneNumber.getNumber();
                    }
                    if (phoneNumber.getPhoneType().equals(PhoneType.Mobile)) {
                        rowData[6] = phoneNumber.getNumber();
                    }
                }
            }

            model.addRow(rowData);
        }
    }

    private void ToggleEnabledPersonel(boolean val) {
        jTextFieldFirstNameDoctor.setEnabled(val);
        jTextFieldSurnameDoctor.setEnabled(val);
        jComboBoxDoctorType.setEnabled(val);
        jCheckBoxDocAvailable.setEnabled(val);

        jTextFieldStatePresentDoctor.setEnabled(val);
        jTextFieldCityPresentDoctor.setEnabled(val);
        jTextFieldStreetPresentDoctor.setEnabled(val);
        jFormattedTextFieldHouseNumberPresentDoctor.setEnabled(val);
        jFormattedTextFieldAreacodePresentDoctor.setEnabled(val);
        jFormattedTextZipcodePresentDoctor.setEnabled(val);

        jFormattedTextFieldPhoneNumWorkDoctor.setEnabled(val);
        jFormattedTextFieldPhoneNumHomeDoctor.setEnabled(val);
        jFormattedTextFieldPhoneNumMobileDoctor.setEnabled(val);
        jTextFieldEmailDoctor.setEnabled(val);
        jTextFieldFaxDoctor.setEnabled(val);
        jTextFieldPagerDoctor.setEnabled(val);

    }

    private boolean DoctorInputValidation() {
        String validationStatusMessages = "";
        if (jTextFieldFirstNameDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Doctor first name is required";
        }

        if (jTextFieldSurnameDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Doctor last name is required";
        }

        if (!jTextFieldEmailDoctor.getText().matches(EMAIL_REGEX) || jTextFieldEmailDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Email is not valid!";
        }

        //Address
        if (jTextFieldStatePresentDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Address state is required";
        }
        if (jTextFieldCityPresentDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Address city is required";
        }
        if (jTextFieldStreetPresentDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Address street is required";
        }
        if (jFormattedTextFieldHouseNumberPresentDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Address house number is required";
        }

        if (jFormattedTextFieldPhoneNumMobileDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Mobile number is required";
        }

        if (validationStatusMessages.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, validationStatusMessages, "Validation check failed:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void UpdateOrCreateDoctor() {
        //Address Present(patient)
        Address tempDocAddressPresent = new Address();
        if (!this.addingDoctor) {
            if (_currentDoctor.getContactDetail().getPresentAddress() != null) {
                tempDocAddressPresent = _currentDoctor.getContactDetail().getPresentAddress();
            }
        } else {
            tempDocAddressPresent = new Address();
        }
        tempDocAddressPresent.setState(jTextFieldStatePresentDoctor.getText());
        tempDocAddressPresent.setCity(jTextFieldCityPresentDoctor.getText());
        tempDocAddressPresent.setStreet(jTextFieldStreetPresentDoctor.getText());
        if (!jFormattedTextFieldHouseNumberPresentDoctor.getText().isEmpty()) {
            tempDocAddressPresent.setHouseNumber(Integer.parseInt((String) jFormattedTextFieldHouseNumberPresentDoctor.getText()));
        }
        tempDocAddressPresent.setArea(jFormattedTextFieldAreacodePresentDoctor.getText());
        tempDocAddressPresent.setZipCode(jFormattedTextZipcodePresentDoctor.getText());

        _currentDoctor.setFirstname(jTextFieldFirstNameDoctor.getText());
        _currentDoctor.setLastname(jTextFieldSurnameDoctor.getText());
        _currentDoctor.setDoctorType(DoctorType.valueOf(jComboBoxDoctorType.getSelectedItem().toString()));
        _currentDoctor.setUnavailable(jCheckBoxDocAvailable.isSelected());

        //Create
        if (this.addingDoctor) {
            try {

                Address addressDoc2 = new Address();
                InsertAddressResult addressDoc1Result = _addressBL.insertAddress(tempDocAddressPresent);
                InsertAddressResult addressDoc2Result = _addressBL.insertAddress(addressDoc2);

                ContactDetail contactDetailDoc = new ContactDetail();
                contactDetailDoc.setPresentAddress(addressDoc1Result.address);
                contactDetailDoc.setPermanentAddress(addressDoc2Result.address);
                InsertContactDetailResult contactDetailDocRes = _contactDetailBL.insertContactDetail(contactDetailDoc);

                PhoneNumber phoneNumberWork1 = new PhoneNumber();
                PhoneNumber phoneNumberHome1 = new PhoneNumber();
                PhoneNumber phoneNumberMobile1 = new PhoneNumber();
                PhoneNumber phoneNumberEmail1 = new PhoneNumber();
                PhoneNumber phoneNumberFax1 = new PhoneNumber();
                PhoneNumber phoneNumberPager1 = new PhoneNumber();

                phoneNumberWork1.setNumber(jFormattedTextFieldPhoneNumWorkDoctor.getText());
                phoneNumberHome1.setNumber(jFormattedTextFieldPhoneNumHomeDoctor.getText());
                phoneNumberMobile1.setNumber(jFormattedTextFieldPhoneNumMobileDoctor.getText());
                phoneNumberEmail1.setNumber(jTextFieldEmailDoctor.getText());
                phoneNumberFax1.setNumber(jTextFieldFaxDoctor.getText());
                phoneNumberPager1.setNumber(jTextFieldPagerDoctor.getText());

                phoneNumberWork1.setPhoneType(PhoneType.Work);
                phoneNumberHome1.setPhoneType(PhoneType.Home);
                phoneNumberMobile1.setPhoneType(PhoneType.Mobile);
                phoneNumberEmail1.setPhoneType(PhoneType.Email);
                phoneNumberFax1.setPhoneType(PhoneType.Fax);
                phoneNumberPager1.setPhoneType(PhoneType.Pager);

                phoneNumberWork1.setContact(contactDetailDocRes.contactDetail);
                phoneNumberHome1.setContact(contactDetailDocRes.contactDetail);
                phoneNumberMobile1.setContact(contactDetailDocRes.contactDetail);
                phoneNumberEmail1.setContact(contactDetailDocRes.contactDetail);
                phoneNumberFax1.setContact(contactDetailDocRes.contactDetail);
                phoneNumberPager1.setContact(contactDetailDocRes.contactDetail);

                _phoneNumberBL.insertPhoneNumber(phoneNumberWork1);
                _phoneNumberBL.insertPhoneNumber(phoneNumberHome1);
                _phoneNumberBL.insertPhoneNumber(phoneNumberMobile1);
                _phoneNumberBL.insertPhoneNumber(phoneNumberEmail1);
                _phoneNumberBL.insertPhoneNumber(phoneNumberFax1);
                _phoneNumberBL.insertPhoneNumber(phoneNumberPager1);

                _currentDoctor.setContactDetail(contactDetailDocRes.contactDetail);
                _doctorBL.insertContactDetail(_currentDoctor);
            } catch (Exception e) {
                System.out.println(e);
            }
        } //Update
        else {
            try {
                //Phones
                List<PhoneNumber> tempDocPhones = _currentDoctor.getContactDetail().getPhones();

                for (int i = 0; i < tempDocPhones.size(); i++) {

                    if (tempDocPhones.get(i).getPhoneType().equals(PhoneType.Work)) {
                        tempDocPhones.get(i).setNumber(jFormattedTextFieldPhoneNumWorkDoctor.getText());
                    }
                    if (tempDocPhones.get(i).getPhoneType().equals(PhoneType.Home)) {
                        tempDocPhones.get(i).setNumber(jFormattedTextFieldPhoneNumHomeDoctor.getText());

                    }
                    if (tempDocPhones.get(i).getPhoneType().equals(PhoneType.Mobile)) {
                        tempDocPhones.get(i).setNumber(jFormattedTextFieldPhoneNumMobileDoctor.getText());

                    }
                    if (tempDocPhones.get(i).getPhoneType().equals(PhoneType.Email)) {
                        tempDocPhones.get(i).setNumber(jTextFieldEmailDoctor.getText());

                    }
                    if (tempDocPhones.get(i).getPhoneType().equals(PhoneType.Fax)) {
                        tempDocPhones.get(i).setNumber(jTextFieldFaxDoctor.getText());

                    }
                    if (tempDocPhones.get(i).getPhoneType().equals(PhoneType.Pager)) {
                        tempDocPhones.get(i).setNumber(jTextFieldPagerDoctor.getText());
                    }

                }

                _doctorBL.updateContactDetail(_currentDoctor);
                _addressBL.updateAddress(tempDocAddressPresent);

                for (PhoneNumber tempPhone : tempDocPhones) {
                    _phoneNumberBL.updatePhoneNumber(tempPhone);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        this.addingDoctor = false;
        ToggleEnabledPersonel(false);
    }

    private void ClearInputFieldsPersonel() {
        jTextFieldFirstNameDoctor.setText("");
        jTextFieldSurnameDoctor.setText("");
        jComboBoxDoctorType.setSelectedIndex(0);
        jCheckBoxDocAvailable.setText("");

        jTextFieldStatePresentDoctor.setText("");
        jTextFieldCityPresentDoctor.setText("");
        jTextFieldStreetPresentDoctor.setText("");
        jFormattedTextFieldHouseNumberPresentDoctor.setText("");
        jFormattedTextFieldAreacodePresentDoctor.setText("");
        jFormattedTextZipcodePresentDoctor.setText("");

        jFormattedTextFieldPhoneNumWorkDoctor.setText("");
        jFormattedTextFieldPhoneNumHomeDoctor.setText("");
        jFormattedTextFieldPhoneNumMobileDoctor.setText("");
        jTextFieldEmailDoctor.setText("");
        jTextFieldFaxDoctor.setText("");
        jTextFieldPagerDoctor.setText("");

    }

    private void SetEditValuesPersonel() {
        //Basic

        if (_currentDoctor.getFirstname() != null) {
            jTextFieldFirstNameDoctor.setText(_currentDoctor.getFirstname());
        }

        if (_currentDoctor.getLastname() != null) {
            jTextFieldSurnameDoctor.setText(_currentDoctor.getLastname());
        }
        jComboBoxDoctorType.setSelectedIndex(DoctorType.valueOf(_currentDoctor.getDoctorType().toString()).ordinal());
        jCheckBoxDocAvailable.setSelected(_currentDoctor.isUnavailable());

        //Address
        if (_currentDoctor.getContactDetail().getPresentAddress() != null) {
            if (_currentDoctor.getContactDetail().getPresentAddress().getState() != null) {
                jTextFieldStatePresentDoctor.setText(_currentDoctor.getContactDetail().getPresentAddress().getState());
            }
            if (_currentDoctor.getContactDetail().getPresentAddress().getCity() != null) {
                jTextFieldCityPresentDoctor.setText(_currentDoctor.getContactDetail().getPresentAddress().getCity());
            }
            if (_currentDoctor.getContactDetail().getPresentAddress().getStreet() != null) {
                jTextFieldStreetPresentDoctor.setText(_currentDoctor.getContactDetail().getPresentAddress().getStreet());
            }

            jFormattedTextFieldHouseNumberPresentDoctor.setText(Integer.toString(_currentDoctor.getContactDetail().getPresentAddress().getHouseNumber()));

            if (_currentDoctor.getContactDetail().getPresentAddress().getArea() != null) {
                jFormattedTextFieldAreacodePresentDoctor.setText(_currentDoctor.getContactDetail().getPresentAddress().getArea());
            }
            if (_currentDoctor.getContactDetail().getPresentAddress().getZipCode() != null) {
                jFormattedTextZipcodePresentDoctor.setText(_currentDoctor.getContactDetail().getPresentAddress().getZipCode());
            }
        }

        //Phones
        List<PhoneNumber> contacts = _currentDoctor.getContactDetail().getPhones();

        if (!contacts.isEmpty()) {
            for (PhoneNumber phone : contacts) {
                if (phone.getPhoneType().equals(PhoneType.Work)) {
                    jFormattedTextFieldPhoneNumWorkDoctor.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Home)) {
                    jFormattedTextFieldPhoneNumHomeDoctor.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Mobile)) {
                    jFormattedTextFieldPhoneNumMobileDoctor.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Email)) {
                    jTextFieldEmailDoctor.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Fax)) {
                    jTextFieldFaxDoctor.setText(phone.getNumber());
                }
                if (phone.getPhoneType().equals(PhoneType.Pager)) {
                    jTextFieldPagerDoctor.setText(phone.getNumber());
                }
            }
        }

    }

    private void SetupAppointmentTable() {
        String[] columnNames = {"Id",
            "Patient name",
            "Doctor name",
            "Date",
            "Start time",
            "End time",
            "Diagnosis",
            "Paid"
        };

        DefaultTableModel model = (DefaultTableModel) jTableAppointments.getModel();

        model.setColumnIdentifiers(columnNames);
        jTableAppointments.setRowSelectionAllowed(true);
        jTableAppointments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTableAppointments.getColumn(columnNames[1]).setPreferredWidth(120);
        jTableAppointments.getColumn(columnNames[2]).setPreferredWidth(120);

        jTableAppointments.getColumn(columnNames[3]).setPreferredWidth(120);
        jTableAppointments.getColumn(columnNames[6]).setPreferredWidth(260);

    }

    private void FillAppointmentTable() {
        DefaultTableModel model = (DefaultTableModel) jTableAppointments.getModel();

        GetAppointmentResult result = _appointmentBL.getAll();
        GetDoctorResult docRes = _doctorBL.getAll();
        GetPatientsResult patRes = _patientsBL.getAll();

        if (docRes.isOK) {
            jComboBoxAppointmentDoctor.setModel(new DefaultComboBoxModel(docRes.doctors.toArray()));
        }
        if (patRes.isOK) {
            jComboBoxAppointmentPatient.setModel(new DefaultComboBoxModel(patRes.patients.toArray()));
        }

        if (!result.isOK) {
            System.out.println(result.msg);
        }
        _allAppointments = result.appointment;

        for (int i = 0; i < _allAppointments.size(); i++) {
            Object rowData[] = new Object[8];
            rowData[0] = _allAppointments.get(i).getId();
            rowData[1] = _allAppointments.get(i).getPatient().toString();
            if (_allAppointments.get(i).getDoctor() != null) {
                rowData[2] = _allAppointments.get(i).getDoctor().toString();
            } else {
                rowData[2] = "";
            }
            rowData[3] = _allAppointments.get(i).getStartDateHour().toLocalDate();
            rowData[4] = _allAppointments.get(i).getStartDateHour().toLocalTime();
            rowData[5] = _allAppointments.get(i).getEndDateHour().toLocalTime();
            rowData[6] = _allAppointments.get(i).getDiagnosis();
            rowData[7] = _allAppointments.get(i).isPaid();

            model.addRow(rowData);
        }
    }

    private void ClearInputFieldsAppointment() {
        jFormattedAppointmentDate.setText("10/09/2019");
        jFormattedAppointmentStart.setText("12:30");
        jFormattedAppointmentEnd.setText("13:30");
        jComboBoxAppointmentPatient.setSelectedIndex(0);
        jComboBoxAppointmentDoctor.setSelectedIndex(0);
    }

    private void SetEditValuesAppointment() {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);

        if (_currentAppointment.getStartDateHour() != null) {
            jFormattedAppointmentDate.setText(dateFormatter.format(_currentAppointment.getStartDateHour()));
        }

        if (_currentAppointment.getStartDateHour() != null) {
            jFormattedAppointmentStart.setText(timeFormatter.format(_currentAppointment.getStartDateHour()));
        }

        if (_currentAppointment.getEndDateHour() != null) {
            jFormattedAppointmentEnd.setText(timeFormatter.format(_currentAppointment.getEndDateHour()));
        }

        if (_currentAppointment.getDoctor() != null) {
            jComboBoxAppointmentDoctor.setSelectedIndex(_currentAppointment.getDoctor().getId().intValue() - 1);
        }

        if (_currentAppointment.getPatient() != null) {
            jComboBoxAppointmentPatient.setSelectedIndex(_currentAppointment.getPatient().getId().intValue() - 1);
        }

    }

    private void ToggleEnabledAppointment(boolean val) {
        jFormattedAppointmentDate.setEnabled(val);
        jFormattedAppointmentStart.setEnabled(val);
        jFormattedAppointmentEnd.setEnabled(val);
        jComboBoxAppointmentPatient.setEnabled(val);
        jComboBoxAppointmentDoctor.setEnabled(val);
    }

    private boolean AppointmentInputValidation() {
        String validationStatusMessages = "";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.UK);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);
        if (jFormattedAppointmentDate.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Date cannot be empty!";
        } else {

            LocalDate date = LocalDate.parse(jFormattedAppointmentDate.getText(), dateFormatter);
            LocalTime timeStart = LocalTime.parse(jFormattedAppointmentStart.getText(), timeFormatter);
            LocalTime timeEnd = LocalTime.parse(jFormattedAppointmentEnd.getText(), timeFormatter);

            if (!timeStart.isBefore(timeEnd)) {
                validationStatusMessages
                        += "\n End time needs to be after start time!";
            }
        }

        if (jFormattedAppointmentDate.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Date is required.";
        }

        if (jFormattedAppointmentStart.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Start time is required.";
        }
        if (jFormattedAppointmentEnd.getText().isEmpty()) {
            validationStatusMessages
                    += "\n End time is required.";
        }

        if (validationStatusMessages.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, validationStatusMessages, "Validation check failed:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void UpdateOrCreateAppointment() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.UK);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);

        LocalDate date = LocalDate.parse(jFormattedAppointmentDate.getText(), dateFormatter);
        LocalTime timeStart = LocalTime.parse(jFormattedAppointmentStart.getText(), timeFormatter);
        LocalTime timeEnd = LocalTime.parse(jFormattedAppointmentEnd.getText(), timeFormatter);

        LocalDateTime joinedStartTime = LocalDateTime.of(date, timeStart);
        LocalDateTime joinedEndTime = LocalDateTime.of(date, timeEnd);

        try {
            if (this.addingAppointment) {
                Appointment tempApp = new Appointment();
                tempApp.setPatient((Patient) jComboBoxAppointmentPatient.getSelectedItem());
                tempApp.setDoctor((Doctor) jComboBoxAppointmentDoctor.getSelectedItem());
                tempApp.setStartDateHour(joinedStartTime);
                tempApp.setEndDateHour(joinedEndTime);
                tempApp.setPaid(false);

                _appointmentBL.insertContactDetail(tempApp);
                this.addingAppointment = false;
            } else {
                Appointment tempApp = _currentAppointment;
                tempApp.setPatient((Patient) jComboBoxAppointmentPatient.getSelectedItem());
                tempApp.setDoctor((Doctor) jComboBoxAppointmentDoctor.getSelectedItem());
                tempApp.setStartDateHour(joinedStartTime);
                tempApp.setEndDateHour(joinedEndTime);
                tempApp.setPaid(false);

                _appointmentBL.updateContactDetail(tempApp);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void SetupAppointmentByDocTable() {
        String[] columnNames = {"Id",
            "Patient name",
            "Doctor name",
            "Date",
            "Start time",
            "End time",
            "Paid"
        };

        DefaultTableModel model = (DefaultTableModel) jTableAppointmentsByDoctor.getModel();

        model.setColumnIdentifiers(columnNames);
        jTableAppointmentsByDoctor.setRowSelectionAllowed(true);
        jTableAppointmentsByDoctor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTableAppointmentsByDoctor.getColumn(columnNames[1]).setPreferredWidth(160);
        jTableAppointmentsByDoctor.getColumn(columnNames[2]).setPreferredWidth(160);

        SetupTestTableByDoctor();
        SetupPrescriptionTableByDoctor();

    }

    private void FillAppointmentByDocTable() {
        if (_currentlySelected == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) jTableAppointmentsByDoctor.getModel();

        GetAppointmentResult result = _appointmentBL.getAll();
        GetDoctorResult docRes = _doctorBL.getAll();
        GetPatientsResult patRes = _patientsBL.getAll();
        GetTestTypeResult tests = _testTypeBL.getAll();
        GetDrugResult drugs = _drugBL.getAll();

        if (docRes.isOK) {
            jComboBoxAppointmentDoctor.setModel(new DefaultComboBoxModel(docRes.doctors.toArray()));
        }
        if (patRes.isOK) {
            jComboBoxAppointmentPatient.setModel(new DefaultComboBoxModel(patRes.patients.toArray()));
        }

        if (!result.isOK) {
            System.out.println(result.msg);
        }
        _allAppointments = result.appointment;
        _allDoctors = docRes.doctors;
        _allPatients = patRes.patients;
        _allTestTypes = tests.testType;
        _allDrugs = drugs.drugs;
        jComboBoxListOfTestTypes.setModel(new DefaultComboBoxModel(tests.testType.toArray()));
        jComboBoxListOfDrugsForPrescription.setModel(new DefaultComboBoxModel(drugs.drugs.toArray()));

        Integer currentPatient = _currentlySelected.getId().intValue();

        for (int i = 0; i < _allAppointments.size(); i++) {
            if (currentPatient != _allAppointments.get(i).getPatient().getId().intValue()) {
                continue;
            }
            Object rowData[] = new Object[7];
            rowData[0] = _allAppointments.get(i).getId();
            rowData[1] = _allAppointments.get(i).getPatient().toString();
            if (_allAppointments.get(i).getDoctor() != null) {
                rowData[2] = _allAppointments.get(i).getDoctor().toString();
            } else {
                rowData[2] = "";
            }
            rowData[3] = _allAppointments.get(i).getStartDateHour().toLocalDate();
            rowData[4] = _allAppointments.get(i).getStartDateHour().toLocalTime();
            rowData[5] = _allAppointments.get(i).getEndDateHour().toLocalTime();
            rowData[6] = _allAppointments.get(i).isPaid();

            model.addRow(rowData);
        }
    }

    private void ClearInputFieldsAppointmentByDoctor() {
        jFormattedAppointmentDateByDoctor.setText("10/09/2019");
        jFormattedAppointmentStartByDoctor.setText("12:30");
        jFormattedAppointmentEndByDoctor.setText("");
        jTextAreaDiagnosisByDoctor.setText("");
        jTextAreaInstructions.setText("");

        DefaultTableModel model1 = (DefaultTableModel) jTableTests.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTablePrescriptions.getModel();

        model1.setRowCount(0);
        model2.setRowCount(0);
    }

    private void SetEditValuesAppointmentByDoctor() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);

        if (_currentAppointmentByDoctor.getStartDateHour() != null) {
            jFormattedAppointmentDateByDoctor.setText(dateFormatter.format(_currentAppointmentByDoctor.getStartDateHour()));
        }

        if (_currentAppointmentByDoctor.getStartDateHour() != null) {
            jFormattedAppointmentStartByDoctor.setText(timeFormatter.format(_currentAppointmentByDoctor.getStartDateHour()));
        }

        if (_currentAppointmentByDoctor.getEndDateHour() != null) {
            jFormattedAppointmentEndByDoctor.setText(timeFormatter.format(_currentAppointmentByDoctor.getEndDateHour()));
        }

        if (_currentAppointmentByDoctor.getDiagnosis() != null) {
            jTextAreaDiagnosisByDoctor.setText(_currentAppointmentByDoctor.getDiagnosis());
        }
        if (_currentAppointmentByDoctor.getPrescriptions().size() != 0) {
            jTextAreaInstructions.setText(_currentAppointmentByDoctor.getPrescriptions().get(0).getName());
        }

        FillTestTableByDoctor();
        FillPrescriptionTableByDoctor();

    }

    private void SetupTestTableByDoctor() {
        String[] columnNames = {"Id",
            "Test name"

        };

        DefaultTableModel model = (DefaultTableModel) jTableTests.getModel();

        model.setColumnIdentifiers(columnNames);
        jTableTests.setRowSelectionAllowed(true);
        jTableTests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableTests.getColumn(columnNames[0]).setPreferredWidth(30);

    }

    private void SetupPrescriptionTableByDoctor() {
        String[] columnNames = {"Id",
            "Drug name",};

        DefaultTableModel model = (DefaultTableModel) jTablePrescriptions.getModel();

        model.setColumnIdentifiers(columnNames);
        jTablePrescriptions.setRowSelectionAllowed(true);
        jTablePrescriptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTablePrescriptions.getColumn(columnNames[0]).setPreferredWidth(30);

    }

    private void FillTestTableByDoctor() {
        if (_currentAppointmentByDoctor == null) {
            return;
        }

        if (_currentAppointmentByDoctor.getTests().isEmpty()) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) jTableTests.getModel();

        for (int i = 0; i < _currentAppointmentByDoctor.getTests().size(); i++) {

            Object rowData[] = new Object[2];
            if (_currentAppointmentByDoctor.getTests().get(i).getTestType() != null) {
                rowData[0] = _currentAppointmentByDoctor.getTests().get(i).getTestType().getId();
            }
            if (_currentAppointmentByDoctor.getTests().get(i).getTestType() != null) {
                rowData[1] = _currentAppointmentByDoctor.getTests().get(i).getTestType().getName();
            }

            model.addRow(rowData);
        }
    }

    private void FillPrescriptionTableByDoctor() {
        if (_currentAppointmentByDoctor == null) {
            return;
        }

        if (_currentAppointmentByDoctor.getPrescriptions().isEmpty()) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTablePrescriptions.getModel();

        List<DrugPrescriptions> drugs = _currentAppointmentByDoctor.getPrescriptions().get(0).getPhones();
        for (int i = 0; i < drugs.size(); i++) {

            Object rowData[] = new Object[2];
            rowData[0] = drugs.get(i).getDrug().getId();
            rowData[1] = drugs.get(i).getDrug().getName();

            model.addRow(rowData);
        }
    }

    private void ToggleEnabledAppointmentByDoctor(boolean val) {
        jFormattedAppointmentDateByDoctor.setEnabled(val);
        jFormattedAppointmentStartByDoctor.setEnabled(val);
        jFormattedAppointmentEndByDoctor.setEnabled(val);
        jTextAreaDiagnosisByDoctor.setEnabled(val);
        jTextAreaInstructions.setEnabled(val);
        jComboBoxListOfTestTypes.setEnabled(val);
        jComboBoxListOfDrugsForPrescription.setEnabled(val);

        jButtonRemoveTestByDoctor.setEnabled(val);
        jButtonAddTestByDoctor.setEnabled(val);
        jButtonRemovePrescriptionByDoctor.setEnabled(val);
        jButtonAddPrescriptionByDoctor.setEnabled(val);
    }

    private boolean AppointmentByDoctorInputValidation() {
        String validationStatusMessages = "";
        if (jFormattedAppointmentDateByDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Date is required.";
        }

        if (jFormattedAppointmentStartByDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Start time is required.";
        }
        if (jFormattedAppointmentEndByDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n End time is required.";
        }

        if (jTextAreaDiagnosisByDoctor.getText().isEmpty()) {
            validationStatusMessages
                    += "\n Diagnosis is required.";
        }

        if (validationStatusMessages.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, validationStatusMessages, "Validation check failed:", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void UpdateOrCreateAppointmentByDoctor() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.UK);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.UK);

        LocalDate date = LocalDate.parse(jFormattedAppointmentDateByDoctor.getText(), dateFormatter);
        LocalTime timeStart = LocalTime.parse(jFormattedAppointmentStartByDoctor.getText(), timeFormatter);
        LocalTime timeEnd = LocalTime.parse(jFormattedAppointmentEndByDoctor.getText(), timeFormatter);

        LocalDateTime joinedStartTime = LocalDateTime.of(date, timeStart);
        LocalDateTime joinedEndTime = LocalDateTime.of(date, timeEnd);

        String diagnosis = jTextAreaDiagnosisByDoctor.getText();
        String instructions = jTextAreaInstructions.getText();

        Integer doctorId = jComboBoxDoctorsList.getSelectedIndex() + 1;
        Doctor doc = _allDoctors.get(doctorId);
        Patient pat = (Patient) jComboBoxPatientList.getSelectedItem();
        try {
            if (this.addingAppointmentByDoctor) {
                Appointment tempApp = new Appointment();
                if (this.refferingToSpecialist) {
                    tempApp.setDoctor((Doctor) jComboBoxSpecialistDoctorLists.getSelectedItem());
                    this.refferingToSpecialist = false;
                } else {
                    tempApp.setDoctor(doc);
                }
                tempApp.setPatient(pat);
                tempApp.setStartDateHour(joinedStartTime);
                tempApp.setEndDateHour(joinedEndTime);
                tempApp.setPaid(false);
                tempApp.setDiagnosis(diagnosis);

                InsertAppointmentResult appResult = _appointmentBL.insertContactDetail(tempApp);

                //Prescription
                Prescription tempPres = new Prescription();
                tempPres.setAppointment(appResult.appointment);
                tempPres.setName(instructions);
                tempPres.setPrescriptionDate(date);

                InsertPrescriptionResult prescriptionResult = _prescriptionsBL.insertContactDetail(tempPres);

                //Tests
                List<Long> testsId = new ArrayList<Long>();
                DefaultTableModel model1 = (DefaultTableModel) jTableTests.getModel();

                for (int i = 0; i < model1.getRowCount(); i++) {
                    testsId.add((Long) model1.getValueAt(i, 0));
                }

                for (Long tes : testsId) {
                    Test temp = new Test();
                    temp.setAppointment(appResult.appointment);
                    temp.setTestType(_allTestTypes.get(tes.intValue()));
                    _testBL.insertContactDetail(temp);
                }

                //Prescription
                List<Long> prescriptionId = new ArrayList<Long>();
                DefaultTableModel model2 = (DefaultTableModel) jTablePrescriptions.getModel();

                for (int i = 0; i < model2.getRowCount(); i++) {
                    prescriptionId.add((Long) model2.getValueAt(i, 0));
                }

                for (Long pres : prescriptionId) {
                    DrugPrescriptions temp = new DrugPrescriptions();
                    temp.setPrescription(prescriptionResult.prescription);
                    temp.setDrug(_allDrugs.get(pres.intValue()));
                    _drugPrescriptionBL.insertContactDetail(temp);
                }

                this.addingAppointmentByDoctor = false;
            } else {
                Appointment tempApp = _currentAppointmentByDoctor;
                tempApp.setPatient(pat);
                tempApp.setDoctor(doc);
                tempApp.setStartDateHour(joinedStartTime);
                tempApp.setEndDateHour(joinedEndTime);
                tempApp.setDiagnosis(diagnosis);
                tempApp.setPaid(false);

                _appointmentBL.updateContactDetail(tempApp);

                //Prescription
                Prescription tempPres = _currentAppointmentByDoctor.getPrescriptions().get(0);
                tempPres.setName(instructions);
                tempPres.setPrescriptionDate(date);

                InsertPrescriptionResult prescriptionResult = _prescriptionsBL.updateContactDetail(tempPres);

                //Tests
                List<Long> testsId = new ArrayList<Long>();
                DefaultTableModel model1 = (DefaultTableModel) jTableTests.getModel();

                for (int i = 0; i < model1.getRowCount(); i++) {
                    testsId.add((Long) model1.getValueAt(i, 0));
                }

                for (Long tes : testsId) {
                    Test temp = new Test();
                    temp.setAppointment(_currentAppointmentByDoctor);
                    temp.setTestType(_allTestTypes.get(tes.intValue()));

                    if (_currentAppointmentByDoctor.getTests().stream().filter(x -> x.getTestType().getId().intValue() == temp.getTestType().getId().intValue()).count() > 0) {
                        _testBL.insertContactDetail(temp);

                    } else {

                        _testBL.updateContactDetail(temp);
                    }

                }

                //Prescription
                List<Long> prescriptionId = new ArrayList<Long>();
                DefaultTableModel model2 = (DefaultTableModel) jTablePrescriptions.getModel();

                for (int i = 0; i < model2.getRowCount(); i++) {
                    prescriptionId.add((Long) model2.getValueAt(i, 0));
                }

                for (Long pres : prescriptionId) {
                    DrugPrescriptions temp = new DrugPrescriptions();
                    temp.setPrescription(prescriptionResult.prescription);
                    temp.setDrug(_allDrugs.get(pres.intValue()));
                    if (_currentAppointmentByDoctor.getPrescriptions().get(0).getPhones().stream().filter(x -> x.getDrug().getId().intValue() == temp.getId().intValue()).count() > 0) {
                        _drugPrescriptionBL.insertContactDetail(temp);
                    } else {
                        _drugPrescriptionBL.updateContactDetail(temp);
                    }
                }

                this.addingAppointmentByDoctor = false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void SetupBillIssued() {
        String[] columnNames = {"Id",
            "Patient name",
            "Consultation price",
            "Prescription Quantity",
            "Prescription price",
            "Test Quantity",
            "Test price",
            "Time issued",
            "Appointment ID",
            "Payment type",
            "Total price"
        };

        DefaultTableModel model = (DefaultTableModel) jTableBillsIssued.getModel();

        model.setColumnIdentifiers(columnNames);
        jTableBillsIssued.setRowSelectionAllowed(true);
        jTableBillsIssued.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTableBillsIssued.getColumn(columnNames[0]).setPreferredWidth(25);
    }

    private void SetupBillUnissued() {
        String[] columnNames = {"Id",
            "Patient name",
            "Consultation price",
            "Prescription Quantity",
            "Prescription price",
            "Test Quantity",
            "Test price",
            "Time issued",
            "Appointment ID",
            "Payment type",
            "Total price"
        };

        DefaultTableModel model = (DefaultTableModel) jTableBillsUnissued.getModel();

        model.setColumnIdentifiers(columnNames);
        jTableBillsUnissued.setRowSelectionAllowed(true);
        jTableBillsUnissued.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTableBillsUnissued.getColumn(columnNames[0]).setPreferredWidth(25);
    }

    private void FillBillIssued() {
        DefaultTableModel model = (DefaultTableModel) jTableBillsIssued.getModel();
        GetBillResult billsRes = _billBL.getAll();
        for (int i = 0; i < billsRes.bill.size(); i++) {

            Object rowData[] = new Object[11];
            rowData[0] = billsRes.bill.get(i).getId();
            if (billsRes.bill.get(i).getAppointment() != null) {

                rowData[1] = billsRes.bill.get(i).getAppointment().getPatient().toString();
            } else {
                rowData[1] = "";

            }
            rowData[2] = billsRes.bill.get(i).getConsultationPrice();

            rowData[3] = billsRes.bill.get(i).getPrescriptionQuantity();
            rowData[4] = billsRes.bill.get(i).getPrescriptionsPrice();
            rowData[5] = billsRes.bill.get(i).getTestQuantity();
            rowData[6] = billsRes.bill.get(i).getTestsPrice();
            rowData[7] = billsRes.bill.get(i).getTimeIssued();
            if (billsRes.bill.get(i).getAppointment() != null) {
                rowData[8] = billsRes.bill.get(i).getAppointment().getId().intValue();
            } else {
                rowData[8] = "";
            }
            rowData[9] = billsRes.bill.get(i).getPayment().getPaymentType().toString();
            rowData[10] = billsRes.bill.get(i).getTotalPrice();

            model.addRow(rowData);
        }
    }

    private void FillBillUnissued() {
        DefaultTableModel model = (DefaultTableModel) jTableBillsUnissued.getModel();

        GetAppointmentResult result = _appointmentBL.getAll();
        GetDoctorResult docRes = _doctorBL.getAll();
        GetPatientsResult patRes = _patientsBL.getAll();

        if (!result.isOK) {
            System.out.println(result.msg);
        }
        _allAppointments = result.appointment;
        _allDoctors = docRes.doctors;
        _allPatients = patRes.patients;

        for (int i = 0; i < _allAppointments.size(); i++) {
            if (_allAppointments.get(i).isPaid()) {
                continue;
            }
            Object rowData[] = new Object[11];
            rowData[0] = _allAppointments.get(i).getId();
            rowData[1] = _allAppointments.get(i).getPatient().toString();
            rowData[2] = "200";

            if (_allAppointments.get(i).getPrescriptions() == null || _allAppointments.get(i).getPrescriptions().isEmpty()) {
                rowData[3] = "0";
            } else {
                rowData[3] = _allAppointments.get(i).getPrescriptions().get(0).getPhones().size();
            }

            BigDecimal prescriptionPrices = new BigDecimal(0);
            List<DrugPrescriptions> drugs = new ArrayList<DrugPrescriptions>();
            if (!_allAppointments.get(i).getPrescriptions().isEmpty()) {

                drugs = _allAppointments.get(i).getPrescriptions().get(0).getPhones();
                for (int j = 0; j < drugs.size(); j++) {
                    prescriptionPrices = prescriptionPrices.add(drugs.get(j).getDrug().getPrice());
                }
                rowData[4] = prescriptionPrices.intValue();
            } else {
                rowData[4] = 0;

            }
            if (_allAppointments.get(i).getTests().size() < 1) {
                rowData[5] = "0";
            } else {
                rowData[5] = _allAppointments.get(i).getTests().size();
            }

            BigDecimal testPrices = new BigDecimal(0);
            List<Test> tests = new ArrayList<Test>();
            if (_allAppointments.get(i).getTests() != null) {

                tests = _allAppointments.get(i).getTests();
                for (int j = 0; j < tests.size(); j++) {
                    if (tests.get(j).getTestType() != null) {
                        testPrices = testPrices.add(tests.get(j).getTestType().getPrice());
                    }
                }
                rowData[6] = testPrices.intValue();
            } else {
                rowData[6] = 0;

            }
            rowData[7] = _allAppointments.get(i).getStartDateHour().toLocalDate();

            rowData[8] = _allAppointments.get(i).getId().intValue();
            rowData[9] = jCheckBoxCreditCard.isSelected() ? PaymentType.CreditCard : PaymentType.Cash;
            BigDecimal total = new BigDecimal(200);
            total = total.add(testPrices);
            total = total.add(prescriptionPrices);

            rowData[10] = total.toBigInteger().toString();
            model.addRow(rowData);
        }
    }

    private void RefreshAll() {
        GetAppointmentResult appRes = _appointmentBL.getAll();
        GetDoctorResult docRes = _doctorBL.getAll();
        GetPatientsResult patRes = _patientsBL.getAll();

        _allAppointments = appRes.appointment;
        _allDoctors = docRes.doctors;
        _allPatients = patRes.patients;
    }

    private boolean BillInputValidation() {
        String validationStatusMessages = "";

        if (jOutpatCreditCardNumber.getText().isEmpty() && jCheckBoxCreditCard.isSelected()) {
            validationStatusMessages
                    += "\n Enter credit card number";
        }

        if (validationStatusMessages.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, validationStatusMessages, "Validation check failed:", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
}
