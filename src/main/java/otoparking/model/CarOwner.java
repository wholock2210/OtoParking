package otoparking.model;

public class CarOwner {
    private AppUser appUser;
    private Car car;

    public CarOwner(AppUser appUser, Car car) {
        this.appUser = appUser;
        this.car = car;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
