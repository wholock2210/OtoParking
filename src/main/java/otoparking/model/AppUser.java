package otoparking.model;

import java.sql.Date;

public class AppUser {
    private int id;
    private String name;
    private String phone;
    private String email;
    private Date brith;
    private String address;
    private String userName;
    private String passwordHash;
    private Date startDate;
    private double salary;
    private Role role;

    public AppUser(int id, String name, String phone, String email, Date brith, String address, String userName,
            String passwordHash, Date startDate, double salary, Role role) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.brith = brith;
        this.address = address;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.startDate = startDate;
        this.salary = salary;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null) ? "" : name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = (phone == null) ? "" : phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email == null) ? "" : email;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = (brith == null) ? new Date(0) : brith;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = (address == null) ? "" : address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = (userName == null) ? "" : userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = (passwordHash == null) ? "" : passwordHash;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = (startDate == null) ? new Date(0) : startDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = (salary < 0) ? 0 : salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = (role == null) ? new Role(0, "") : role;
    }
}
