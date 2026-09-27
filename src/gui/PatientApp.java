package gui;


import model.Patient;
import model.Doctor;
import data.FileStorage;
import exception.InvalidInputException;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class PatientApp extends JFrame {
    // --- PATIENT TAB COMPONENTS ---
    private JTextField txtPatientId = new JTextField(8);
    private JTextField txtPatientName = new JTextField(12);
    private JTextField txtDisease = new JTextField(12);
    private JTextField txtPatientSearch = new JTextField(10);


    private DefaultTableModel patientTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Disease"}, 0);
    private JTable patientTable = new JTable(patientTableModel);
    private List<Patient> patientList;


    // --- DOCTOR TAB COMPONENTS ---
    private JTextField txtDoctorId = new JTextField(8);
    private JTextField txtDoctorName = new JTextField(12);
    private JTextField txtSpecialization = new JTextField(12);


    private DefaultTableModel doctorTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Specialization"}, 0);
    private JTable doctorTable = new JTable(doctorTableModel);
    private List<Doctor> doctorList = new ArrayList<>();


    public PatientApp() {
        setTitle("Hospital Management System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen


        // Load patient records from file on startup
        patientList = FileStorage.loadPatients();


        // JTabbedPane creates separate tabs for Patients and Doctors
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Patients Management", createPatientPanel());
        tabbedPane.addTab("Doctors Management", createDoctorPanel());


        add(tabbedPane);


        // Populate tables initially
        refreshPatientTable(patientList);
    }


    // ==========================================
    // 1. PATIENTS MANAGEMENT PANEL
    // ==========================================
    private JPanel createPatientPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));


        // Form Inputs Panel
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));


        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Register New Patient"));
        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtPatientId);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtPatientName);
        formPanel.add(new JLabel("Disease:"));
        formPanel.add(txtDisease);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnAdd = new JButton("Add Patient");
        JButton btnDelete = new JButton("Delete Selected");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);


        topContainer.add(formPanel);
