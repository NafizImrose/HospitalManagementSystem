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
        topContainer.add(buttonPanel);


        // Search + Table Panel
        JPanel centerContainer = new JPanel(new BorderLayout(5, 5));
        centerContainer.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));


        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Patients"));
        searchPanel.add(new JLabel("Patient ID:"));
        searchPanel.add(txtPatientSearch);
        JButton btnSearch = new JButton("Search");
        JButton btnResetSearch = new JButton("Show All");
        searchPanel.add(btnSearch);
        searchPanel.add(btnResetSearch);


        centerContainer.add(searchPanel, BorderLayout.NORTH);
        centerContainer.add(new JScrollPane(patientTable), BorderLayout.CENTER);


        mainPanel.add(topContainer, BorderLayout.NORTH);
        mainPanel.add(centerContainer, BorderLayout.CENTER);


        // --- Patient Event Listeners ---
        btnAdd.addActionListener(e -> {
            try {
                String id = txtPatientId.getText().trim();
                String name = txtPatientName.getText().trim();
                String disease = txtDisease.getText().trim();


                if (id.isEmpty() || name.isEmpty() || disease.isEmpty()) {
                    throw new InvalidInputException("All patient fields are required!");
                }


                for (Patient p : patientList) {
                    if (p.getId().equalsIgnoreCase(id)) {
                        throw new InvalidInputException("Patient ID '" + id + "' already exists!");
                    }
                }


                Patient patient = new Patient(id, name, disease);
                patientList.add(patient);
                FileStorage.savePatients(patientList);


                refreshPatientTable(patientList);
                clearPatientFields();
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "File error: " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnDelete.addActionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow >= 0) {
                String selectedId = (String) patientTableModel.getValueAt(selectedRow, 0);
                patientList.removeIf(p -> p.getId().equalsIgnoreCase(selectedId));
                try {
                    FileStorage.savePatients(patientList);
                    refreshPatientTable(patientList);
                    JOptionPane.showMessageDialog(this, "Patient deleted successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file changes.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a patient from the table to delete.");
            }
        });


        btnSearch.addActionListener(e -> {
            try {
                String searchId = txtPatientSearch.getText().trim();
                if (searchId.isEmpty()) {
                    throw new InvalidInputException("Enter a Patient ID to search.");
                }
                patientTableModel.setRowCount(0);
                boolean found = false;
                for (Patient p : patientList) {
                    if (p.getId().equalsIgnoreCase(searchId)) {
                        patientTableModel.addRow(new Object[]{p.getId(), p.getName(), p.getDisease()});
                        found = true;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(this, "No patient found with ID: " + searchId);
                    refreshPatientTable(patientList);
                }
            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Search Warning", JOptionPane.WARNING_MESSAGE);
            }
        });


        btnResetSearch.addActionListener(e -> clearPatientFields());


        return mainPanel;
    }


    // ==========================================
    // 2. DOCTORS MANAGEMENT PANEL
    // ==========================================
    private JPanel createDoctorPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));


        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        topContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));


        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Register New Doctor"));
        formPanel.add(new JLabel("Doctor ID:"));
        formPanel.add(txtDoctorId);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtDoctorName);
        formPanel.add(new JLabel("Specialization:"));
        formPanel.add(txtSpecialization);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnAddDoc = new JButton("Add Doctor");
        buttonPanel.add(btnAddDoc);


        topContainer.add(formPanel);
        topContainer.add(buttonPanel);


        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        centerContainer.add(new JScrollPane(doctorTable), BorderLayout.CENTER);


        mainPanel.add(topContainer, BorderLayout.NORTH);
        mainPanel.add(centerContainer, BorderLayout.CENTER);


        // --- Doctor Event Listeners ---
        btnAddDoc.addActionListener(e -> {
            try {
                String id = txtDoctorId.getText().trim();
                String name = txtDoctorName.getText().trim();
                String spec = txtSpecialization.getText().trim();


                if (id.isEmpty() || name.isEmpty() || spec.isEmpty()) {
                    throw new InvalidInputException("All doctor fields are required!");
                }


                Doctor doc = new Doctor(id, name, spec);
                doctorList.add(doc);
                refreshDoctorTable();
                clearDoctorFields();
                JOptionPane.showMessageDialog(this, "Doctor registered successfully!");


            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        });


        return mainPanel;
    }


    // --- HELPER METHODS ---
    private void refreshPatientTable(List<Patient> list) {
        patientTableModel.setRowCount(0);
        for (Patient p : list) {
            patientTableModel.addRow(new Object[]{p.getId(), p.getName(), p.getDisease()});
        }
    }


    private void refreshDoctorTable() {
        doctorTableModel.setRowCount(0);
        for (Doctor d : doctorList) {
            doctorTableModel.addRow(new Object[]{d.getId(), d.getName(), d.getSpecialization()});
        }
    }


    private void clearPatientFields() {
        txtPatientId.setText("");
        txtPatientName.setText("");
        txtDisease.setText("");
        txtPatientSearch.setText("");
        refreshPatientTable(patientList);
    }


    private void clearDoctorFields() {
        txtDoctorId.setText("");
        txtDoctorName.setText("");
        txtSpecialization.setText("");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientApp().setVisible(true));
    }
}
