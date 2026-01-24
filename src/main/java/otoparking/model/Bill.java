package otoparking.model;

import java.sql.Date;

public class Bill {
    private int id;
    private ParkingHistory parkingHistory;
    private double totalAmount;
    private Date createdAt;

    public Bill(ParkingHistory parkingHistory, double totalAmount, Date createdAt) {
        this.parkingHistory = parkingHistory;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Bill(int id, ParkingHistory parkingHistory, double totalAmount, Date createdAt) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
