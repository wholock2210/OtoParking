package otoparking.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import otoparking.model.*;

import otoparking.utilities.DBConection;;

public class ParkingHistoryDAO {
    public boolean Update(ParkingHistory parkingHistory){
        String query = "update ParkingHistory " +
                        "set idCar = ?, idRowCell = ?, startTime = ?, endTime = ?, parkingMinutes = ? " +
                        "where id = ? ";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, parkingHistory.getCar().getId());
            if(parkingHistory.getRowCell() == null){
                ps.setNull(2, java.sql.Types.INTEGER);
            }else{
                ps.setInt(2, parkingHistory.getRowCell().getId());
            }
            ps.setDate(3, parkingHistory.getStartTime());
            ps.setDate(4, parkingHistory.getEndTime());
            ps.setDouble(5, parkingHistory.getParkingMinutes());
            ps.setInt(6, parkingHistory.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(ParkingHistory parkingHistory){
        String query = "delete from ParkingHistory where id = ? ";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, parkingHistory.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public ParkingHistory FindParkedCar(int idCar){
        String query = "SELECT * FROM `ParkingHistory` \n" + //
                        "WHERE idCar = ? \n" + //
                        "ORDER BY (NOW() - `startTime`) \n" + 
                        "LIMIT 1 \n";
        
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, idCar);
            try(
                ResultSet rs = ps.executeQuery();
            ){
                CarDAO cDao = new CarDAO();
                RowCellDAO rcDAO = new RowCellDAO();

                if(rs.next()){
                    Car car = cDao.FirstOfDefault(rs.getInt("idCar"));
                    RowCell rowCell = rcDAO.FirstOfDefault(rs.getInt("idRowCell"));

                    ParkingHistory parkingHistory = new ParkingHistory(rs.getInt("id"), car, rowCell, rs.getDate("startTime"), rs.getDate("endTime"), rs.getDouble("parkingMinutes"));
                    return parkingHistory;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean Insert(ParkingHistory parkingHistory){
        String query = "Insert into ParkingHistory(idCar, idRowCell, startTime, endTime, parkingMinutes)" + 
                        "values (?, ?, ?, ?, ?)";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){

            ps.setInt(1, parkingHistory.getCar().getId());
            if(parkingHistory.getRowCell() == null){
                ps.setNull(2, java.sql.Types.INTEGER);
            }else{
                ps.setInt(2, parkingHistory.getRowCell().getId());
            }
            ps.setDate(3, parkingHistory.getStartTime());
            if(parkingHistory.getEndTime() == null){
                ps.setNull(4, java.sql.Types.DATE);
            }else{
                ps.setDate(4, parkingHistory.getEndTime());
            }
            ps.setDouble(5, parkingHistory.getParkingMinutes());
            

            return ps.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public ParkingHistory FirstOfDefault(int id){
        String query = "select * from ParkingHistory where id = ? LIMIT 1";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, id);
            try(
                ResultSet rs = ps.executeQuery();
            ){
                CarDAO cDao = new CarDAO();
                RowCellDAO rcDAO = new RowCellDAO();

                Car car = cDao.FirstOfDefault(rs.getInt("idCar"));
                RowCell rowCell = rcDAO.FirstOfDefault(rs.getInt("idRowCell"));

                ParkingHistory parkingHistory = new ParkingHistory(id, car, rowCell, null, null, id);
                return parkingHistory;
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ParkingHistory> FindAll(){
        List<ParkingHistory> list = new ArrayList<>();
        // String query = "select c.licensePlate, rc.id, ph.startTime, ph.endTime, ph.parkingMinutes" +
        //                 "from ParkingHistory ph " + 
        //                 "join Car c on c.id = ph.idCar " +
        //                 "join RowCell rc on rc.id = ph.idRowCell " +
        //                 "join Cell ce on ce.id = ph.idCell " +
        //                 "join ";

        String query = "select *" +
                        "from ParkingHistory ph " + 
                        "join Car c on c.id = ph.idCar " +
                        "join RowCell rc on rc.id = ph.idRowCell " +
                        "join Cell ce on ce.id = rc.idCell " +
                        "join Floor fl on fl.id = rc.idFloor " +
                        "join ParkingRow pr on pr.id = rc.idRow " +
                        "join Status st on st.id = rc.idStatus ";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){  
            while (rs.next()) {
                Car car = new Car(rs.getInt("idCar"), 
                                rs.getString("licensePlate"));
                                
                RowCellDAO rowCellDAO = new RowCellDAO();
                RowCell rowCell = rowCellDAO.FirstOfDefault(rs.getInt("id"));
                

                list.add(new ParkingHistory(rs.getInt("id"), 
                                            car, 
                                            rowCell, 
                                            rs.getDate("startTime"), 
                                            rs.getDate("endTime"), 
                                            rs.getDouble("parkingMinutes")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
}
