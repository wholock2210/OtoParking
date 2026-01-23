package otoparking.DAO;
// import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// import com.mysql.cj.x.protobuf.MysqlxResultset.Row;

import otoparking.model.*;

import otoparking.utilities.DBConection;

public class RowCellDAO {
    public boolean Update(RowCell rowCell){
        String query = "update RowCell \n" +
                        "set idFloor = ?, idRow = ?, idCell = ?, idStatus \n" +
                        "where id = ?\n";

        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setString(1, rowCell.getFloor().getSymbol());
            ps.setString(2, rowCell.getParkingRow().getSymbol());
            ps.setString(3, rowCell.getCell().getSymbol());
            ps.setString(4, rowCell.getStatus().getDescription());

            return ps.executeUpdate() > 0;
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Delete(RowCell rowCell){
        String query = "delete from RowCell where id = ?";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, rowCell.getId());
            return ps.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean Insert(RowCell rowCell){
        String query = "Insert into RowCell(idFloor, idRow, idCell, idStatus) value (?, ?, ?, ?)";
        try(
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
        ){
            ps.setInt(1, rowCell.getFloor().getId());
            ps.setInt(2, rowCell.getParkingRow().getId());
            ps.setInt(3, rowCell.getCell().getId());
            ps.setInt(4, rowCell.getStatus().getId());
            return ps.executeUpdate() > 0;

        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public RowCell FirstOfDefault(int id){
        String query = "select * from RowCell where id = ? limit 1";
        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);

        ){
            ps.setInt(1, id);
            try(
                ResultSet rs = ps.executeQuery();
            ) {
                if (rs.next()){
                    FloorDAO fDAO = new FloorDAO();
                    ParkingRowDAO rDAO = new ParkingRowDAO();
                    CellDAO cDAO = new CellDAO();
                    StatusDAO sDAO = new StatusDAO();

                    Floor floor = fDAO.FirstOfDefault(rs.getInt("idFloor"));
                    ParkingRow rowpParkingRow = rDAO.FirstOfDefault(rs.getInt("idRow"));
                    Cell cell = cDAO.FirstOfDefault(rs.getInt("idCell"));
                    Status status = sDAO.FirstOfDefault(rs.getInt("idStatus"));

                    RowCell rowCellResult = new RowCell(id, cell, floor, rowpParkingRow, status);

                    return rowCellResult;

                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<RowCell> FindAll(){
        List<RowCell> list = new ArrayList<>();
        String query = "select rc.symbol, f.symbol, c.symbol, pr.symbol, s.description from RowCell rc" +
                        "join Floor f on f.id = rc.idFloor, " + 
                        "join Cell c on c.id = rc.idCell " +
                        "join ParkingRow pr on pr.id = rc.idRow " +
                        "join Status s on s.id = rc.idStatus ";

        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
                Floor floor = new Floor(rs.getInt("id"), rs.getString("symbol"));
                ParkingRow parkingRow = new ParkingRow(rs.getInt("id"), rs.getString("symbol"));
                Cell cell = new Cell(rs.getInt("id"), rs.getString("symbol"));
                Status status = new Status(rs.getInt("id"), rs.getString("name"), rs.getString("description"));

                list.add(new RowCell(rs.getInt("id"), cell, floor, parkingRow, status));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

