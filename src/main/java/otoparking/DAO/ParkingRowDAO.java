package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import otoparking.model.Cell;
import otoparking.model.ParkingRow;
import otoparking.utilities.DBConection;

public class ParkingRowDAO {
    public boolean Update(ParkingRow parkingRow){
        String query = "update ParkingRow " + 
                        "set symbol = ? " + 
                        "where id = ? ";

        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, parkingRow.getSymbol());

            return ps.executeUpdate() > 0;

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(ParkingRow parkingRow){
        String query = "delete from ParkingRow " + 
                        "where id = ? ";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, parkingRow.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Insert(Cell cell){
        String query = "Insert into ParkingRow (symbol) value (?) ";
        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, cell.getSymbol());
            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public ParkingRow FirstOfDefault(int id){
        String query = "select * " + //
                        "from ParkingRow " + //
                        "where id = ? " + 
                        "limit 1 ";
        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, id);
            try (
                ResultSet rs = ps.executeQuery();
            ){
                if(rs.next()){
                    ParkingRow parkingRowResult = new ParkingRow(rs.getInt("id"), rs.getString("symbol"));
                    return parkingRowResult;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ParkingRow> FindAll(){
        List<ParkingRow> list = new ArrayList<>();

        String query = "select * from ParkingRow";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
                list.add(new ParkingRow(rs.getInt("id"), rs.getString("symbol")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
