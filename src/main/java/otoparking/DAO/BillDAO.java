package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import otoparking.model.*;

import otoparking.utilities.DBConection;

public class BillDAO {
    public boolean Update(Bill bill){
        String query = "update Bill set idParkingHistory = ?, totalAmount = ?, createdAt = ? where id = ? ";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){

            ps.setInt(1, bill.getParkingHistory().getId());
            ps.setDouble(2, bill.getTotalAmount());
            ps.setDate(3, bill.getCreatedAt());

            return ps.executeUpdate() > 0;

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(Bill bill){
        String query = "delete from Bill where id = ? ";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, bill.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean Insert(Bill bill){
        String query = "Insert into Bill(idParkingHistory, totalAmount, createdAt) values (?, ?, ?); ";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, bill.getParkingHistory().getId());
            ps.setDouble(2, bill.getTotalAmount());
            ps.setDate(3, bill.getCreatedAt());

            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();

        }
        return false;
    }

    public Bill FirstOfDefault(int id){
        String query = "select * from Bill b " +
                        "join ParkingHistory ph on ph.id = b.idParkingHistory ";

        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){  
            ps.setInt(1, id);
            try(
                ResultSet rs = ps.executeQuery();
            ){
                if(rs.next()){
                    ParkingHistoryDAO pDao = new ParkingHistoryDAO();
                    ParkingHistory parkingHistory = pDao.FirstOfDefault(rs.getInt("id"));
                    Bill billResult = new Bill(rs.getInt("id"), 
                                                parkingHistory, 
                                                rs.getDouble("totalAmount"), 
                                                rs.getDate("createdAt"));

                    return billResult;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Bill> FindAll(){
        List<Bill> list = new ArrayList<>();

        String query = "select * from Bill";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
                ParkingHistoryDAO pDao = new ParkingHistoryDAO();
                ParkingHistory parkingHistory = pDao.FirstOfDefault(rs.getInt("id"));

                list.add(new Bill(rs.getInt("id"), parkingHistory, rs.getDouble("totalAmount"), rs.getDate("createdAt")));
                
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
