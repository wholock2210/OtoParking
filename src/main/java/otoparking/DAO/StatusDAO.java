package otoparking.DAO;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import otoparking.model.Cell;
import otoparking.utilities.DBConection;

public class StatusDAO {
    public boolean Update(otoparking.model.Status status){
        String query = "update Status " + 
                        "set name = ?, description = ? " + 
                        "where id = ? ";

        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, status.getName());
            ps.setString(2, status.getDescription());

            return ps.executeUpdate() > 0;

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(otoparking.model.Status status){
        String query = "delete from Status " + 
                        "where id = ? ";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, status.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Insert(otoparking.model.Status status){
        String query = "Insert into Ststus (name, description) value (?, ?) ";
        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, status.getName());
            ps.setString(2, status.getDescription());
            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public otoparking.model.Status FirstOfDefault(int id){
        String query = "select * " + //
                        "from Status " + //
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
                    otoparking.model.Status statusResult = new otoparking.model.Status(rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("description"));
                    return statusResult;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<otoparking.model.Status> FindAll(){
        List<otoparking.model.Status> list = new ArrayList<>();

        String query = "select * from Status";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
                
                list.add(new otoparking.model.Status(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
