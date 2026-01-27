package otoparking.model;

import java.sql.Timestamp;

public class ParkingHistory {
    private int id;
    private Car car;
    private RowCell rowCell;
    private Timestamp startTime;
    private Timestamp endTime;
    private double parkingMinutes = 0.0;

    public ParkingHistory(Car car, Timestamp startTime) {
        this.car = car;
        this.startTime = startTime;
    }

    public ParkingHistory(){
        this.id = -1;
    }

    public ParkingHistory(int id, Car car, RowCell rowCell, Timestamp startTime, Timestamp endTime, double parkingMinutes) {
        this.id = id;
        this.car = car;
        this.rowCell = rowCell;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parkingMinutes = parkingMinutes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public RowCell getRowCell() {
        return rowCell;
    }

    public void setRowCell(RowCell rowCell) {
        this.rowCell = rowCell;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public double getParkingMinutes() {
        return parkingMinutes;
    }

    public void setParkingMinutes(double parkingMinutes) {
        this.parkingMinutes = parkingMinutes;
    }

}
