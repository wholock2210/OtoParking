package otoparking.model;

public class RowCell {
    private int id;
    private Cell cell;
    private Floor floor;
    private ParkingRow parkingRow;
    private Status status;

    public RowCell(int id, Cell cell, Floor floor, ParkingRow parkingRow, Status status) {
        this.id = id;
        this.cell = cell;
        this.floor = floor;
        this.parkingRow = parkingRow;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public ParkingRow getParkingRow() {
        return parkingRow;
    }

    public void setParkingRow(ParkingRow parkingRow) {
        this.parkingRow = parkingRow;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
