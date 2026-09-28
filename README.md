
# 🏥 HOSPITAL MANAGEMENT SYSTEM

**Topic:** - HOSPITAL PATIENT & DOCTOR MANAGEMENT SYSTEM

**Assignment:** CSE282 JAVA GROUP PROJECT

**Course:** CSE 282.4 - PROGRAMMING LANGUAGE II LAB (JAVA)

### 👥 Group Members

| SL | Student Name | ID |
| --- | --- | --- |
| **1** | **Nafiz Imroze Jyotee** | `2024100000031` |
| **2** | **Kazi Shahriar Nadim** | `2024100000156` |
| **3** | **Sinigdha Islam Jannatul** | `2024100000135` |
| **4** | **Sadia Afrin Dina** | `2024100000315` |

---

## 🖱️ Project Overview

The **Hospital Management System** is a Java desktop application designed to streamline patient registration, doctor tracking, and record searches within small medical clinics and hospital front desks.

The system centralizes patient and doctor records, allowing users to:

* **Register patients and doctors** via a multi-tabbed Java Swing GUI.


* **Search for patient records** instantly by unique Patient ID.


* **Delete outdated entries** with real-time table updates.


* **Persist patient data locally** using automatic file storage.



---

## 🎯 Project Objectives

1. **Centralized Database:** Create a persistent file-backed record system for hospital data.


2. **OOP Principles:** Implement all four fundamental OOP principles (Encapsulation, Abstraction, Inheritance, and Polymorphism).


3. **User-Friendly Interface:** Provide a clean graphical user interface using Java Swing.


4. **Input Validation & Safety:** Validate user input and prevent duplicate entries or missing fields using custom exception handling.


5. **Data Persistence:** Automatically load and save patient records to local text files.



---

## ⚙️ System Theory

In many small clinics, patient records are managed through paper logs or loose files, causing duplicate records and slow retrieval times. Our system overcomes these issues through:

* **Object-Oriented Architecture:** Clear class hierarchies (`Person`, `Patient`, `Doctor`).


* **Structured Data Flow:** User Input $\rightarrow$ Input Validation $\rightarrow$ File Storage $\rightarrow$ GUI Refresh.


* **Encapsulated Modules:** Modular architecture separating GUI elements, data models, exception handling, and file persistence.



### OOP Implementation Matrix

| Principle | Technical Implementation |
| --- | --- |
| **Encapsulation** | Private fields (`id`, `name`, `disease`, `specialization`) with public getter/setter methods.

|
| **Abstraction** | Abstract parent class `Person` hiding role details and enforcing the abstract `getRole()` method.

|
| **Inheritance** | `Patient` and `Doctor` subclasses inherit common attributes from the `Person` base class.

|
| **Polymorphism** | Overridden `getRole()` methods producing specific role string representations for `Patient` and `Doctor`.

|

---

## 🖱️ Methodology

### 1. Requirement Analysis

* Identified common bottleneck issues in clinic patient check-ins.
* Defined core user needs (patient registration, doctor allocation, ID search, record removal).


* Specified data storage requirements for offline file persistence.



### 2. System Design

* **Frontend:** Built using Java Swing with a `JTabbedPane` layout separating Patient and Doctor management.


* **Backend Storage:** `FileStorage` handles CSV reading and writing (`BufferedReader`, `PrintWriter`).


* **Exception Handling:** Custom `InvalidInputException` ensures input safety and non-blocking dialog warnings.



---

## 🧱 Object Model & Module Summary

| Class / Package | Description |
| --- | --- |
| **`gui.PatientApp`** | Main entry point and Swing GUI controller containing tabs, forms, and table models.

|
| **`model.Person` (abstract)** | Abstract base class defining shared entity fields (`id`, `name`) and the `getRole()` abstract method.

|
| **`model.Patient`** | Extends `Person` with a `disease` field and handles CSV data conversion (`toCSV()`).

|
| **`model.Doctor`** | Extends `Person` with a `specialization` field.

|
| **`data.FileStorage`** | Manages file I/O operations (`loadPatients()`, `savePatients()`) to persist data to `patients.txt`.

|
| **`exception.InvalidInputException`** | Custom checked exception thrown when form fields are left empty or duplicate IDs are entered.

|

---

## 🧮 Functional Modules

1. **Register Patients:** Input forms to register Patient ID, Name, and diagnosed Disease.


2. **Register Doctors:** Input forms to register Doctor ID, Name, and Medical Specialization.


3. **Search Patient Records:** Lookup tool filtering table rows dynamically by Patient ID.


4. **Delete Records:** Remove selected records from both memory and local text files.


5. **Persistent Data Sync:** Auto-loads saved patient files on application startup.



---

## 💡 Exception Handling

Custom exceptions ensure input validity and provide non-blocking user feedback via `JOptionPane`:

* **`InvalidInputException`** $\rightarrow$ Thrown when required fields are left empty during registration.


* **`Duplicate ID Guard`** $\rightarrow$ Triggered when attempting to register a Patient ID that already exists in the system.


* **`File I/O Safety`** $\rightarrow$ Handles file read/write exceptions gracefully during load and save operations.



---

## 🧪 Testing & Validation

* **GUI Validation:** Validated form fields to ensure empty strings cannot be submitted.


* **File Persistence Testing:** Verified that added and deleted patients correctly update `patients.txt`.


* **Search Function Testing:** Tested search filtering with existing IDs, non-existing IDs, and table resets.



---

## 🧾 Conclusion

The **Hospital Management System** successfully demonstrates essential Java programming principles while delivering a practical utility for medical clinics. It combines object-oriented architecture, Java Swing UI controls, local file persistence, and robust exception handling into an organized software solution.

---

## ⏳ Future Enhancements

* **Database Integration:** Connect with MySQL or SQLite for relational database storage.
* **Doctor Persistence:** Add persistent file saving for doctor records.
* **Appointment Module:** Link patients with specific on-duty doctors for scheduled appointments.