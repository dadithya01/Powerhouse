package edu.ijse.powerhouse.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection{
    private static DBConnection dBConnection;

    private Connection connection;

    private DBConnection()throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/powerhouse", "adithya", "2000");
    }

    public static DBConnection getInstance() throws SQLException,ClassNotFoundException{
        if (dBConnection==null){
            dBConnection = new DBConnection();
        }
        return dBConnection;
    }

    public Connection getConnection(){
        return connection;
    }

}
