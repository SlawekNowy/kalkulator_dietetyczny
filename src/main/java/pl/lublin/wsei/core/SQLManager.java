package pl.lublin.wsei.core;

import java.sql.Connection;
import java.sql.DriverManager;

public final class SQLManager {
//TODO Implementacja metod SQL.
// Serializacja obiektów

    public static Connection getConnection() throws Exception {
        String driver = "org.gjt.mm.mysql.Driver";
        String url = "jdbc:mysql://localhost/databaseName";
        String username = "root";
        String password = "root";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


}
