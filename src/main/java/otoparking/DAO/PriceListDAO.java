package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import otoparking.model.PriceList;
import otoparking.model.TypeCar;
import otoparking.utilities.DBConection;

public class PriceListDAO {

    public boolean Update(PriceList priceList){
        String query = "update PriceList \n" + //
                        "set idType = ?, pricePerHour = ?" +
                        "where id = ?\n";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, priceList.getTypeCar().getId());
            ps.setDouble(2, priceList.getPricePerHour());
            ps.setInt(3, priceList.getId());

            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(PriceList PriceList){
        String query = "delete FROM PriceList\n" + //
                        "where id = ?";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, PriceList.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return false;
    }

    public boolean Insert(PriceList priceList){
        String query = "INSERT into PriceList (idType, pricePerHour)\n" + //
                        "values (?, ?)";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, priceList.getTypeCar().getId());
            ps.setDouble(2, priceList.getPricePerHour());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PriceList FindByTypeOfDefault(int idType){
        String query = "SELECT * FROM `PriceList` \n" +
                        "WHERE `idType` = ? \n" +
                        "LIMIT 1";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, idType);
            try (
                ResultSet rs = ps.executeQuery();
            ) {
                if(rs.next()){

                    TypeCarDAO tcDAO = new TypeCarDAO();

                    TypeCar typeCar = tcDAO.FirstOfDefault(rs.getInt("idType"));

                    PriceList typeCarResult = new PriceList(
                        rs.getInt("id"),
                        typeCar,
                        rs.getDouble("pricePerHour")
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

    public PriceList FirstOfDefault(int id){
        String query = "select *\n" + //
                        "from PriceList \n" + //
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

                    TypeCarDAO tcDAO = new TypeCarDAO();

                    TypeCar typeCar = tcDAO.FirstOfDefault(rs.getInt("idType"));

                    PriceList typeCarResult = new PriceList(
                        rs.getInt("id"),
                        typeCar,
                        rs.getDouble("pricePerHour")
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
    public List<PriceList> FindAll(){
        List<PriceList> list = new ArrayList<>();

        String query = "select *\n" + //
                        "from PriceList pl";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {

                TypeCarDAO tcDAO = new TypeCarDAO();

                TypeCar typeCar = tcDAO.FirstOfDefault(rs.getInt("idType"));


                list.add(new PriceList(
                    rs.getInt("id"),
                    typeCar,
                    rs.getDouble("pricePerHour")
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
