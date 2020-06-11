package pl.lublin.wsei.core;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLManager {
    private SQLManager() {
        connection =null;
        this.initDriver();
    }
    public Connection connection;
//TODO Implementacja metod SQL.
    public static SQLManager initConnection() {
        SQLManager manager = new SQLManager();
        manager.connection = null;
        try {
            manager.connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/aplikacja_dietetyczna?" +
                            "user=dataAdmin&password=MwWh3TjdnGdYT4Tf");
            // Do something with the Connection
        }  catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return manager;
    }

    private void initDriver() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            //modified for Java 9 and up.
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void shutdownConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }


}
