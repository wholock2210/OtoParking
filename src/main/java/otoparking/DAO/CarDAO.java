package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import otoparking.model.*;

import otoparking.utilities.DBConection;

public class CarDAO {
    public boolean Update(Car car) {
        String query = "update Car \n" + //
                "set licensePlate = ?, idType = ? \n" +
                "where id = ?\n";
        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setString(1, car.getLicensePlate());
            ps.setInt(2, car.getTypeCar().getId());
            ps.setInt(3, car.getId());

            return ps.executeUpdate() > 0;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(Car car) {
        String query = "delete from Car\n" + //
                "where id = ?";

        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, car.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Insert(Car car) {
        String query = "INSERT into Car (licensePlate, idType)\n" + //
                "values (?, ?)";
        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setString(1, car.getLicensePlate());
            ps.setInt(2, car.getTypeCar().getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Car FirstOfDefault(int id) {
        String query = "select *\n" + //
                "from Car \n" + //
                "where id = ? \n" + //
                "LIMIT 1";
        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);) {
            ps.setInt(1, id);
            try (
                    ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    TypeCarDAO tcDAO = new TypeCarDAO();
                    TypeCar typeCar = tcDAO.FirstOfDefault(rs.getInt("idType"));
                    Car carResult = new Car(
                            rs.getInt("id"),
                            rs.getString("licensePlate"),
                            typeCar);
                    return carResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Car> FindAll() {
        List<Car> list = new ArrayList<>();

        String query = "select * from Car";

        try (
                Connection conn = DBConection.GetConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                TypeCarDAO tcDAO = new TypeCarDAO();
                TypeCar typeCar = tcDAO.FirstOfDefault(rs.getInt("idType"));
                list.add(new Car(
                        rs.getInt("id"),
                        rs.getString("licensePlate"),
                        typeCar));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
