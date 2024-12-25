package bd;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Angajat;
import model.RaportPerformanta;

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


	
 
// public static void creareTabelCereri() {
//	    Connection conectare = conectareBD();
//	    
//	    if (conectare != null) {
//	    	 String query="CREATE TABLE IF NOT EXISTS cereri (" +
//                  "cerere_id INT(11) AUTO_INCREMENT PRIMARY KEY, "+
//                  "angajat_id INT(11), "+
//                  "tip_cerere VARCHAR(50), "+
//                  "data_inceput DATE, "+
//                  "data_sfarsit DATE, "+
//                  "status VARCHAR(50), "+
// 			        "manager_id INT(11), "+
//                  "FOREIGN KEY (angajat_id) REFERENCES angajati(angajat_id)" +
//                  "FOREIGN KEY (manager_id) REFERENCES angajati(angajat_id)" +
//                  ")";
//	        
//	        try (PreparedStatement stmt=conectare.prepareStatement(query)) {
//	            stmt.executeUpdate();
//	            JOptionPane.showMessageDialog(null, "Tabel 'cereri' a fost creat sau exista deja.");
//	        } catch (SQLException e) {
//	            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului: " + e.getMessage());
//	        }
//	    }
//	}

 
}
 
