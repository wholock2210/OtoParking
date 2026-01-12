package otoparking.model;

import java.sql.Date;

public class ParkingHistory {
    private int id;
    private Car car;
    private RowCell rowCell;
    private Date startTime;
    private Date endTime;
    private double parkingMinutes;

    public ParkingHistory(int id, Car car, RowCell rowCell, Date startTime, Date endTime, double parkingMinutes) {
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getParkingMinutes() {
        return parkingMinutes;
    }

    public void setParkingMinutes(double parkingMinutes) {
        this.parkingMinutes = parkingMinutes;
    }

}
