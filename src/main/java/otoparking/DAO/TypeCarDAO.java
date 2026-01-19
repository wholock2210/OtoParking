package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import otoparking.model.*;

import otoparking.utilities.DBConection;

public class TypeCarDAO {

    public boolean Update(TypeCar type){
        String query = "update TypeCar \n" + //
                        "set name = ?" +
                        "where id = ?\n";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, type.getName());
            ps.setInt(2, type.getId());

            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(TypeCar type){
        String query = "delete FROM TypeCar\n" + //
                        "where id = ?";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, type.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return false;
    }

    public boolean Insert(TypeCar type){
        String query = "INSERT into TypeCar (name)\n" + //
                        "values (?)";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, type.getName());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public TypeCar FirstOfDefault(int id){
        String query = "select *\n" + //
                        "from TypeCar \n" + //
                        "where id = ? \n" + //
                        "LIMIT 1";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, id);
            try (
                ResultSet rs = ps.executeQuery();
            ) {
                if(rs.next()){

                    TypeCar typeCarResult = new TypeCar(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                    return typeCarResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<TypeCar> FindAll(){
        List<TypeCar> list = new ArrayList<>();

        String query = "select *\n" + //
                        "from TypeCar";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {

                list.add(new TypeCar(
                    rs.getInt("id"),
                    rs.getString("name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
