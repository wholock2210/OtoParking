package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import otoparking.model.*;
import otoparking.utilities.*;

public class RoleDAO {

    public Role FirstOfDefault(int id){
        String query = "select *\n" + //
                        "from `Role` r \n" + //
                        "where r.id = ?\n" + //
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
                    Role roleResult = new Role(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                    return roleResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
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
