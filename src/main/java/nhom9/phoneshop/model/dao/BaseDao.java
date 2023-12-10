package nhom9.phoneshop.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {
    private Connection connection;

    protected void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/phoneshop",
                    "root",
                    ""
            );
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void close(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot close the connection!", e);
        }
    }
    protected Connection getConnection(){
        return this.connection;
    }
}
