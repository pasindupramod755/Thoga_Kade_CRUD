package DataBaseConnection;

import java.sql.*;

public class DataBaseConnection {
    private Connection connection;
    private static DataBaseConnection dataBaseConnection;

    private DataBaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "1234"); 
    }
    
    public static DataBaseConnection getInstance() throws ClassNotFoundException, SQLException{
        if (dataBaseConnection == null) {
            dataBaseConnection = new DataBaseConnection();
        }
        return dataBaseConnection;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    
}
