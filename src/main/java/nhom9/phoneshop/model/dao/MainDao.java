package nhom9.phoneshop.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDao{
    private final Connection connection;

    public MainDao(){
        //Connect to database
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/phoneshop",
                    "root",
                    ""
            );
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot close the connection!", e);
        }
    }



}
