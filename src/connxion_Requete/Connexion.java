package connxion_Requete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	private static  String url = "jdbc:oracle:thin:@localhost:1521:xe";    
    private static  String driverName = "oracle.jdbc.driver.OracleDriver";   
    private static  String username = "utilisateur";   
    private static  String password = "123";
    private static  Connection connexion;
    

    public  static Connection getConnection() {
    	if(connexion==null){
    		try {
                Class.forName(driverName);
                try {
                    connexion = DriverManager.getConnection(url, username, password);
                    System.out.println("connection Ok !!!");
                } catch (SQLException ex) {
                	
                    System.out.println("Failed to create the database connection."); 
                }
            } catch (ClassNotFoundException ex) {
                
                System.out.println("Driver not found."); 
            }
    	}
        
        return connexion;
    }
	
	
}
