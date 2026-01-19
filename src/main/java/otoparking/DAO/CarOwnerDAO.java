package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import otoparking.model.*;

import otoparking.utilities.DBConection;

public class CarOwnerDAO {
    public boolean Update(CarOwner carOwner) {
        String query = "update CarOwner \n" +
                "set idUser = ?, idCar = ? \n" +
                "where id = ? \n";

        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, carOwner.getCar().getId());
            ps.setInt(2, carOwner.getAppUser().getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(CarOwner carOwner) {
        String query = "delete from CarOwner \n" +
                "where id = ? \n";

        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, carOwner.getCar().getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Insert(CarOwner carOwner) {
        String query = "Insert into CarOwner (idUser, idCar) \n" +
                "value (?, ?) \n";
        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, carOwner.getAppUser().getId());
            ps.setInt(2, carOwner.getCar().getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CarOwner FirstOfDefault(int id) {
        String query = "select * \n" +
                "from CarOwner \n" +
                "where id = ? \n";
        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    UserDAO appUserDAO = new UserDAO();
                    CarDAO carDAO = new CarDAO();
                    AppUser appUser = appUserDAO.FirstOfDefault(rs.getInt("idUser"));
                    Car car = carDAO.FirstOfDefault(rs.getInt("idCar"));
                    return new CarOwner(appUser, car);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CarOwner> FindAll() {
        List<CarOwner> list = new ArrayList<>();
        String query = "select * \n" +
                "from CarOwner \n";
        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();) {
            UserDAO appUserDAO = new UserDAO();
            CarDAO carDAO = new CarDAO();
            while (rs.next()) {
                AppUser appUser = appUserDAO.FirstOfDefault(rs.getInt("idUser"));
                Car car = carDAO.FirstOfDefault(rs.getInt("idCar"));
                list.add(new CarOwner(appUser, car));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
