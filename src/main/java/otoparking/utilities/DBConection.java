package otoparking.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
<<<<<<< Updated upstream
    private static final String connectionString = 
<<<<<<< HEAD
        "jdbc:mysql://localhost:3306/OtoParking?useSSL=false&serverTimezone=UTC";
=======
    private static final String connectionString = "jdbc:mysql://100.101.143.118:3306/OtoParking?useSSL=false&serverTimezone=UTC";
>>>>>>> Stashed changes
    private static final String _USER = "wholock";
    private static final String _PASSWORD = "221004";
=======
        "jdbc:mysql://<YOUR_SERVER>:3306/OtoParking?useSSL=false&serverTimezone=UTC";
    private static final String _USER = "<YOUR_USER>";
    private static final String _PASSWORD = "<YOUR_PASSWORD>";
>>>>>>> main

    public static Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, _USER, _PASSWORD);
    }
}
