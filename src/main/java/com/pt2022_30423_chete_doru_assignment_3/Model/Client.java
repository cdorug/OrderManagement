package com.pt2022_30423_chete_doru_assignment_3.Model;

/** This class models a client entity.
 *  It has the same fields as the columns in the DB.
 *  Only has getters and setters.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    public Client() {}

    public Client(int id) {
        this.id = id;
    }

    public Client(int id, String name, String address, String email, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
