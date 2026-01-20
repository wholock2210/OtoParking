package otoparking.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {

    private static final String connectionString = "jdbc:mysql://100.101.143.118:3306/OtoParking?useSSL=false&serverTimezone=UTC";

    private static final String _USER = "wholock";
    private static final String _PASSWORD = "221004";

    public static Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, _USER, _PASSWORD);
    }
}
