package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static DBConnection.connection ob;
    private Connection connection;

    private connection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Clothify_Store", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return  connection;
    }

    public static DBConnection.connection getInstance() {
        if(ob==null){
            ob=new connection();
        }
        return ob;
    }
}
