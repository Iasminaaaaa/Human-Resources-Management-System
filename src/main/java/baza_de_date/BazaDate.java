package baza_de_date;

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

import clase.Angajat;

public class BazaDate {
	 String url="jdbc:mysql://localhost:3306/mydb";
     String username="root";
     String password="";
     
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

	public static void creareTabelAngajati() {
	    Connection conectare = conectareBD();
	    
	    if (conectare != null) {
	    	 String query = "CREATE TABLE IF NOT EXISTS angajati (" +
                     "angajat_id INT(11) AUTO_INCREMENT PRIMARY KEY, " +
                     "nume VARCHAR(255) NOT NULL, " +
                     "job VARCHAR(255) NOT NULL, " +
                     "salariu DECIMAL(10,2), " +
                     "data_angajare DATE, " +
                     "numar_telefon VARCHAR(15), " +
                     "email VARCHAR(255), " +
                     "adresa VARCHAR(255), " +
                     "manager_id INT(11), " +
                     "FOREIGN KEY (manager_id) REFERENCES angajati(angajat_id)" +
                     ")";
	        
	        try (PreparedStatement stmt=conectare.prepareStatement(query)) {
	            stmt.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Tabelul 'angajati' a fost creat sau există deja.");
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului: " + e.getMessage());
	        }
	    }
	}

	
public static List<Angajat> infoAngajati() {
 List <Angajat> angajati=new ArrayList<>();
 Connection conectare=conectareBD();
	    
	  if(conectare!=null) {
	    String query="SELECT * FROM angajati";
	    try(PreparedStatement stmt=conectare.prepareStatement(query);
	        ResultSet rs=stmt.executeQuery()) {
	    	
	        while (rs.next()) {
	            int angajatId = rs.getInt("angajat_id");
	            String nume = rs.getString("nume");
	            String job = rs.getString("job");
	            int salariu = rs.getInt("salariu");
	            Date dataAngajare = rs.getDate("data_angajare");
	            String nrTelefon = rs.getString("numar_telefon");
	            String email = rs.getString("email");
	            String adresa = rs.getString("adresa");
	            int managerId = rs.getInt("manager_id");
	            Angajat a=new Angajat(angajatId, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId);  
	            angajati.add(a);   
	        }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	return angajati;
}


public static void vizualizareInfoAngajati(List <Angajat> angajati) {
	for(Angajat e: angajati) {
       	System.out.println(e);
       }
}


 public static void inserareAngajati(Angajat a){

        Connection conectare=conectareBD();
        try{
        	 String query = "INSERT INTO angajati (nume, job, salariu, data_angajare, numar_telefon, email, adresa, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
             
        	 PreparedStatement stmt = conectare.prepareStatement(query);
        	 stmt.setString(1, a.getNume());
        	 stmt.setString(2, a.getJob());
        	 stmt.setInt(3, a.getSalariu());
             stmt.setDate(4, a.getData());
             stmt.setString(5, a.getNrTelefon());
             stmt.setString(6, a.getEmail());
             stmt.setString(7, a.getAdresa());
             stmt.setInt(8, a.getManagerId());
     
      stmt.execute();
      JOptionPane.showMessageDialog(null, "Data added");

        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
 
	
 public static void stergereAngajat(int id) {
	 Connection conectare=conectareBD();
		    
		  if(conectare!=null) {
		    try{
		    	PreparedStatement stmt=conectare.prepareStatement("DELETE FROM angajati WHERE angajat_id= ?");
		        stmt.setInt(1, id);
		    	stmt.executeUpdate();
		    	}catch(Exception e) {
		 
		    	JOptionPane.showMessageDialog(null, e);
		    	}
		  }
 }



 public static void modificareInfoAngajat(int id, String nume, String job, int salariu, String email, String adresa, String nrTel, Date data, int managerId) {
     Connection conectare = conectareBD();
     try {
         String query = "UPDATE angajati SET nume = ?, job = ?, salariu = ?, data_angajare = ?, numar_telefon = ?, email = ?, adresa = ?, manager_id = ? WHERE angajat_id = ?";
         
         PreparedStatement stmt = conectare.prepareStatement(query);
         stmt.setString(1, nume);
         stmt.setString(2, job);
         stmt.setInt(3, salariu);
         stmt.setDate(4, data);
         stmt.setString(5, nrTel);
         stmt.setString(6, email);
         stmt.setString(7, adresa);
         stmt.setInt(8, managerId);
         stmt.setInt(9, id);
         
         int rowsUpdated = stmt.executeUpdate();
         if (rowsUpdated > 0) {
             JOptionPane.showMessageDialog(null, "Data updated successfully");
         } else {
             JOptionPane.showMessageDialog(null, "No record found with the provided ID");
         }

     } catch (SQLException | HeadlessException e) {
         JOptionPane.showMessageDialog(null, e);
     }
 }

 
}
