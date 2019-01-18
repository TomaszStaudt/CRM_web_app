package pl.staudt.crm.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
    private int id;
    private LocalDate orderDate;
    private LocalDate estimatedStartDate;
    private LocalDate startDate;
    private String problemDescription;
    private String repairDescription;
    private int status;
    private int vehicleId;
    private int employeeId;
    private int totalHours;
    private BigDecimal repairCost;
    private BigDecimal materialCost;

    public Order() {
    }

    public Order(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public Order(LocalDate orderDate, LocalDate estimatedStartDate, LocalDate startDate, String problemDescription, String repairDescription, int status, int vehicleId, int employeeId, int totalHours, BigDecimal repairCost, BigDecimal materialCost) {
        this.orderDate = orderDate;
        this.estimatedStartDate = estimatedStartDate;
        this.startDate = startDate;
        this.problemDescription = problemDescription;
        this.repairDescription = repairDescription;
        this.status = status;
        this.vehicleId = vehicleId;
        this.employeeId = employeeId;
        this.totalHours = totalHours;
        this.repairCost = repairCost;
        this.materialCost = materialCost;
    }

    public Order(int id, LocalDate orderDate, LocalDate estimatedStartDate, LocalDate startDate, String problemDescription, String repairDescription, int status, int vehicleId, int employeeId, int totalHours, BigDecimal repairCost, BigDecimal materialCost) {
        this.id = id;
        this.orderDate = orderDate;
        this.estimatedStartDate = estimatedStartDate;
        this.startDate = startDate;
        this.problemDescription = problemDescription;
        this.repairDescription = repairDescription;
        this.status = status;
        this.vehicleId = vehicleId;
        this.employeeId = employeeId;
        this.totalHours = totalHours;
        this.repairCost = repairCost;
        this.materialCost = materialCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getEstimatedStartDate() {
        return estimatedStartDate;
    }

    public void setEstimatedStartDate(LocalDate estimatedStartDate) {
        this.estimatedStartDate = estimatedStartDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public BigDecimal getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(BigDecimal repairCost) {
        this.repairCost = repairCost;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }
}
