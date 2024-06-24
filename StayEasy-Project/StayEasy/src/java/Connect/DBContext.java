package Connect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    public Connection connection;
    
    public DBContext() {
        try {
            
            String username = "sa";

            String password = "12345";
          
            String url = "jdbc:sqlserver://localhost:1433;databaseName=HouseBooking2";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Database connection error: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new DBContext().connection);
    }
}
