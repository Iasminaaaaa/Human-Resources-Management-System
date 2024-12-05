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

public class Main {
	
	 public static Connection conectareBD(){
	        String url = "jdbc:mysql://localhost:3306/mydb";
	        String username = "root";
	        String password = "";
	        
	        try {
	            Connection connection = DriverManager.getConnection(url, username, password);
	            return connection;
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, e);
	            return null;
	        }        
	 }
	
	public static List infoAngajati() {
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
	             
	        	 PreparedStatement preparedStmt = conectare.prepareStatement(query);
	        	 preparedStmt.setString(1, a.getNume());
	             preparedStmt.setString(2, a.getJob());
	             preparedStmt.setInt(3, a.getSalariu());
	             preparedStmt.setDate(4, a.getData());
	             preparedStmt.setString(5, a.getNrTelefon());
	             preparedStmt.setString(6, a.getEmail());
	             preparedStmt.setString(7, a.getAdresa());
	             preparedStmt.setInt(8, a.getManagerId());
	     
	      preparedStmt.execute();
	      JOptionPane.showMessageDialog(null, "Data added");

	        }catch(SQLException | HeadlessException e){
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }
	
 
    public static void main(String[] args) {
    	
    	new InterfataAplicatie();
        vizualizareInfoAngajati(infoAngajati());
        inserareAngajati(new Angajat(10,"Mihaile Andra", "Dezvoltator Software", "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", 4500, "0724540806", new Date(12,10, 2022), 2));
   }
    
}
