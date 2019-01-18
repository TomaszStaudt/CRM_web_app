package pl.staudt.crm.model;

import java.math.BigDecimal;

public class Employee {

    private int id;
    private String name;
    private String surname;
    private String address;
    private String note;
    private BigDecimal hcost;
    private int phone;

    public Employee() {
    }

    public Employee(String name, String surname, String address, String note, BigDecimal hcost, int phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.note = note;
        this.hcost = hcost;
        this.phone = phone;
    }

    public Employee(int id, String name, String surname, String address, String note, BigDecimal hcost, int phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.note = note;
        this.hcost = hcost;
        this.phone = phone;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getHcost() {
        return hcost;
    }

    public void setHcost(BigDecimal hcost) {
        this.hcost = hcost;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " " + surname + "#" + id;
    }
}
