package solidfock.srp.employee_architector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private Connection connection;
    private static DatabaseConnectionManager connectionManager=
            new DatabaseConnectionManager();
    private DatabaseConnectionManager(){}

    public void connect() throws SQLException   {
        connection= DriverManager.getConnection("Database URL");
        System.out.println("Connection to DB is established");
    }

    public Connection getConnection(){
        return connection;
    }

    public void disconnect() throws SQLException{
        connection.close();
        System.out.println("Disconnect fron DB");
    }
}
