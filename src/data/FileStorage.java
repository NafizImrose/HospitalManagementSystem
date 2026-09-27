package data;


import model.Patient;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileStorage {
    private static final String FILE_NAME = "patients.txt";


    // Save all patients to file
    public static void savePatients(List<Patient> patients) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Patient p : patients) {
                writer.write(p.toCSV());
                writer.newLine();
            }
        }
    }


    // Load patients from file
    public static List<Patient> loadPatients() {
        List<Patient> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Patient p = Patient.fromCSV(line);
                if (p != null) list.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }
}
