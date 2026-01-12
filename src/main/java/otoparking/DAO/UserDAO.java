package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import otoparking.model.*;

import otoparking.utilities.DBConection;

public class UserDAO {
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
