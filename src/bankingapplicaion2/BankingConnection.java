package bankingapplicaion2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankingConnection {
    
    
    public static Connection connec() {
        String url = "jdbc:mysql://localhost:3306/mydb2";
        String username = "root";
        String password = "Merlin_2";
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BankingConnection.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return con;
    }
}
