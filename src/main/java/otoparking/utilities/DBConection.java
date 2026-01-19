package otoparking.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    private static final String connectionString = 
        "jdbc:mysql://<YOUR_IP>:<YOUR_PORT>/OtoParking?useSSL=false&serverTimezone=UTC";
    private static final String _USER = "<YOUR_USER>";
    private static final String _PASSWORD = "<YOUR_PASS>";

    public static Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, _USER, _PASSWORD);
    }
}
