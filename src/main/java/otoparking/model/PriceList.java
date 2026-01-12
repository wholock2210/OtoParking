package otoparking.model;

public class PriceList {
    private int id;
    private TypeCar typeCar;
    private double pricePerHour;

    public PriceList(int id, TypeCar typeCar, double pricePerHour) {
        this.id = id;
        this.typeCar = typeCar;
        this.pricePerHour = pricePerHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
