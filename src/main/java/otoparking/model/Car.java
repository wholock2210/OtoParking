package otoparking.model;

public class Car {
    private int id;
    private String licensePlate;
    private TypeCar typeCar;

    public Car(String licensePlate, TypeCar typeCar) {
        this.licensePlate = licensePlate;
        this.typeCar = typeCar;
    }

    public Car(int id, String licensePlate, TypeCar typeCar) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.typeCar = typeCar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }
}
