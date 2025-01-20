package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BazaDate {
	/**
     * Metoda realizeaza conectarea la baza de date
     * @return o conexiune la baza de date sau null dacă apare o eroare
     */
	public static Connection conectareBD(){
        String url="jdbc:mysql://localhost:3306/mydb";
        String username="root";
        String password="";
        
        try {
            Connection connection=DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }        
 }
 
}
 
