package pl.staudt.crm.model;

import java.time.LocalDate;

public class Vehicle {
    private int id;
    private String model;
    private String brand;
    private int year;
    private String carRegistrationNumber;
    private LocalDate reviewDate;
    private Integer customerId;

    public Vehicle() {
    }

    public Vehicle(int id, Integer customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public Vehicle(String model, String brand, int year, String carRegistrationNumber, LocalDate reviewDate, Integer customerId) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.carRegistrationNumber = carRegistrationNumber;
        this.reviewDate = reviewDate;
        this.customerId = customerId;
    }

    public Vehicle(String model, String brand, int year, String carRegistrationNumber, LocalDate reviewDate) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.carRegistrationNumber = carRegistrationNumber;
        this.reviewDate = reviewDate;
    }

    public Vehicle(int id, String model, String brand, int year, String carRegistrationNumber, LocalDate reviewDate, int customerId) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.carRegistrationNumber = carRegistrationNumber;
        this.reviewDate = reviewDate;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return model + " " + carRegistrationNumber + "#" + id;
    }
}
