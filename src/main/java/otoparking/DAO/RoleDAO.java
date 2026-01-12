package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import otoparking.model.*;
import otoparking.utilities.*;

public class RoleDAO {
    public List<Role> FindAll(){
        List<Role> list = new ArrayList<>();

        String query = "select * from Role";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                list.add(new Role(
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
