package model;


public abstract class Person {
    private String id;
    private String name;


    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public abstract String getRole(); // Abstract method


    public String getId() { return id; }
    public String getName() { return name; }
}

