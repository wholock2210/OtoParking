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

                    RowCell rowCellResult = new RowCell(id, floor, rowpParkingRow, cell, status);

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
        String query =
                                        "select\n" + //
                                        "    rc.id as rowcell_id,\n" + //
                                        "\n" + //
                                        "    f.id as floor_id,\n" + //
                                        "    f.symbol as floor_symbol,\n" + //
                                        "\n" + //
                                        "    pr.id as row_id,\n" + //
                                        "    pr.symbol as row_symbol,\n" + //
                                        "\n" + //
                                        "    c.id as cell_id,\n" + //
                                        "    c.symbol as cell_symbol,\n" + //
                                        "\n" + //
                                        "    s.id as status_id,\n" + //
                                        "    s.description as status_description, s.name\n" + //
                                        "from RowCell rc\n" + //
                                        "join Floor f on f.id = rc.idFloor\n" + //
                                        "join Cell c on c.id = rc.idCell\n" + //
                                        "join ParkingRow pr on pr.id = rc.idRow\n" + //
                                        "join Status s on s.id = rc.idStatus;\n" + //
                                        "";


        try (
            Connection conn = DBConection.GetConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
            Floor floor = new Floor(
                rs.getInt("floor_id"),
                rs.getString("floor_symbol")
            );

            ParkingRow parkingRow = new ParkingRow(
                rs.getInt("row_id"),
                rs.getString("row_symbol")
            );

            Cell cell = new Cell(
                rs.getInt("cell_id"),
                rs.getString("cell_symbol")
            );

            Status status = new Status(
                rs.getInt("status_id"),
                rs.getString("name"),
                rs.getString("status_description")
            );

            RowCell rowCell = new RowCell(
                rs.getInt("rowcell_id"),
                floor,
                parkingRow,
                cell,
                status
            );

            list.add(rowCell);
        }

        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

