package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import otoparking.model.*;

import otoparking.utilities.DBConection;

public class UserDAO {

    public boolean Update(AppUser user){
        String query = "update AppUser au \n" + //
                        "set name = ?, phone = ?, email = ?, birth = ?, address = ?, username = ? ," +
                        "passwordHash = ?, startDate = ?, salary = ?, idRole = ?\n" + //
                        "where id = ?\n";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getEmail());
            ps.setDate(4, user.getBrith());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getUserName());
            ps.setString(7, user.getPasswordHash());
            ps.setDate(8, user.getStartDate());
            ps.setDouble(9, user.getSalary());
            ps.setInt(10, user.getRole().getId());
            ps.setInt(11, user.getId());

            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(AppUser user){
        String query = "delete FROM AppUser\n" + //
                        "where id = ?";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, user.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Insert(AppUser user){
        String query = "INSERT into AppUser ( name, phone, email , birth, address, username , passwordHash ,startDate , salary, idRole)\n" + //
                        "values (?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getEmail());
            ps.setDate(4, user.getBrith());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getUserName());
            ps.setString(7, user.getPasswordHash());
            ps.setDate(8, user.getStartDate());
            ps.setDouble(9, user.getSalary());
            ps.setInt(10, user.getRole().getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public AppUser FirstOfDefault(int id){
        String query = "select *\n" + //
                        "from AppUser au \n" + //
                        "where au.id = ? \n" + //
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

                    RoleDAO rDAO = new RoleDAO();
                    Role role = rDAO.FirstOfDefault(rs.getInt("idRole"));


                    AppUser userResult = new AppUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getDate("birth"),
                        rs.getString("address"),
                        rs.getString("username"),
                        rs.getString("passwordHash"),
                        rs.getDate("startDate"),
                        rs.getDouble("salary"),
                        role
                    );
                    return userResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<AppUser> FindAll(){
        List<AppUser> list = new ArrayList<>();

        String query = "select au.*, r.name as roleName\n" + //
                        "from AppUser au \n" + //
                        "join `Role` r on au.idRole = r.id";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {

                Role role = new Role(
                    rs.getInt("idRole"),
                    rs.getString("roleName")
                );

                list.add(new AppUser(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getDate("birth"),
                    rs.getString("address"),
                    rs.getString("username"),
                    rs.getString("passwordHash"),
                    rs.getDate("startDate"),
                    rs.getDouble("salary"),
                    role
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
