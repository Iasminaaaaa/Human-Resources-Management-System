import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	
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

	

	 public static void modificareInfoAngajat(int id, String nume, String job, int salariu, String email, String adresa, String nrTel, LocalDate data, int managerId) {
	     Connection conectare = conectareBD();
	     try {
	         String query = "UPDATE angajati SET nume = ?, job = ?, salariu = ?, data_angajare = ?, numar_telefon = ?, email = ?, adresa = ?, manager_id = ? WHERE angajat_id = ?";
	         
	         PreparedStatement stmt = conectare.prepareStatement(query);
	         stmt.setString(1, nume);
	         stmt.setString(2, job);
	         stmt.setInt(3, salariu);
	         stmt.setDate(4, Date.valueOf(data));
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

	 
    public static void main(String[] args) {
    	
    	new InterfataAplicatie();
        vizualizareInfoAngajati(infoAngajati());
       // inserareAngajati(new Angajat(10,"Mihaile Andra", "Dezvoltator Software", "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", 4500, "0724540806", new Date(12,10, 2022), 2));
        stergereAngajat(19);
        modificareInfoAngajat(4, "Mihaile Andra", "Dezvoltator Software", 4500, "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", "0724540806", LocalDate.of(2023, 12, 20), 2);
    }
    
}
