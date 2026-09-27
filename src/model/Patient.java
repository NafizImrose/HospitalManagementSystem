package model;


public class Patient extends Person {
    private String disease;


    public Patient(String id, String name, String disease) {
        super(id, name);
        this.disease = disease;
    }


    @Override
    public String getRole() {
        return "Patient";
    }


    public String getDisease() { return disease; }



    public String toCSV() {
        return getId() + "," + getName() + "," + disease;
    }



    public static Patient fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) return null;
        return new Patient(parts[0], parts[1], parts[2]);
    }
}