package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import otoparking.model.*;

import otoparking.utilities.DBConection;;

public class FloorDAO {
    public boolean Update(Floor floor){
        String query = "update Floor " + 
                        "set symbol = ? " + 
                        "where id = ? ";

        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, floor.getSymbol());

            return ps.executeUpdate() > 0;

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(Floor floor){
        String query = "delete from Floor " + 
                        "where id = ? ";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, floor.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Insert(Floor floor){
        String query = "Insert into Floor (symbol) value (?) ";
        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, floor.getSymbol());
            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Floor FirstOfDefault(int id){
        String query = "select * " + //
                        "from Floor " + //
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
                    Floor floorResult = new Floor(rs.getInt("id"), rs.getString("symbol"));
                    return floorResult;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Floor> FindAll(){
        List<Floor> list = new ArrayList<>();

        String query = "select * from Floor";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
                list.add(new Floor(rs.getInt("id"), rs.getString("symbol")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
