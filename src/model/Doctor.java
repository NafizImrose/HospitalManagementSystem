package model;


public class Doctor extends Person {
    private String specialization;


    public Doctor(String id, String name, String specialization) {
        super(id, name);
        this.specialization = specialization;
    }

    @Override
    public String getRole() {
        return "Doctor (" + specialization + ")";
    }


    public String getSpecialization() { return specialization; }

    public String toCSV() {
        return getId() + "," + getName() + "," + specialization;
    }
}