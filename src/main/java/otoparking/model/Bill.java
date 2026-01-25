package otoparking.model;

import java.sql.Timestamp;

public class Bill {
    private int id;
    private ParkingHistory parkingHistory;
    private double totalAmount;
    private Timestamp createdAt;

    public Bill(ParkingHistory parkingHistory, double totalAmount, Timestamp createdAt) {
        this.parkingHistory = parkingHistory;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Bill(int id, ParkingHistory parkingHistory, double totalAmount, Timestamp createdAt) {
        this.id = id;
        this.parkingHistory = parkingHistory;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingHistory getParkingHistory() {
        return parkingHistory;
    }

    public void setParkingHistory(ParkingHistory parkingHistory) {
        this.parkingHistory = parkingHistory;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
