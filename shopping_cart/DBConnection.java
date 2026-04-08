package shopping_cart;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        // Load the MySQL JDBC driver if needed (for older Java/JDBC versions)
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/shopping_cart_db",
            "root",
            "Krishnandu@2006"
        );
    }
}
