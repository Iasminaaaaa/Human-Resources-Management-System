package controller;

import java.awt.HeadlessException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.RandomStringUtils;

import bd.BazaDate;
import model.Cont;

public class GestionareConturi {
	 public static void creareTabelConturi() {
		    Connection conectare = BazaDate.conectareBD();
		 
		 if (conectare != null) {
	            String query = "CREATE TABLE IF NOT EXISTS conturi (" +
	                           "email VARCHAR(255) PRIMARY KEY, " +
	                           "parola VARCHAR(255) NOT NULL, " +
	                           "tip_angajat VARCHAR(255) NOT NULL, " +
	                           "FOREIGN KEY (email) REFERENCES angajati(email) ON DELETE CASCADE" + 
	                           ")";

	            try (PreparedStatement stmt = conectare.prepareStatement(query)) {
	                stmt.executeUpdate();
	                JOptionPane.showMessageDialog(null, "Tabelul 'conturi' a fost creat sau există deja.");
	            } catch (SQLException e) {
	                JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului 'conturi': " + e.getMessage());
	            }
		 }
}
	 
	 
	 public static void inserareCont(Cont c){

	        Connection conectare=BazaDate.conectareBD();
	        if(conectare!=null) {
	        try{
	           String query="INSERT INTO conturi (email, parola, tip_angajat) VALUES (?, ?, ?)";
	           PreparedStatement stmt=conectare.prepareStatement(query);            
             stmt.setString(1, c.getEmail());
	      	 stmt.setString(2, c.getParola());
	      	 stmt.setString(3, c.getTipAngajat());
	        
	      stmt.execute();
	      JOptionPane.showMessageDialog(null, "Datele au fost adăugate în tabelul ");
	      

	        }catch(SQLException | HeadlessException e){
	            JOptionPane.showMessageDialog(null, e);
	        }
	        }
	    }

	 public static String generateRandomPassword(int length) {
	        char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?").toCharArray();
	        String randomStr = RandomStringUtils.random(length, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
	        return randomStr;
	    }	 
	 
/**
 * 	 
 * @param c
 * @return 1, 2, 3 sau 4. 
 * Daca se obtine 1 datele contului corespund cu datele din baza de date 
 * Daca se obtine 2 tipul de angajat nu corespunde
 * Daca se obtine 3 parola este gresita
 * Daca se obtine 4 email-ul este gresit
 * @throws SQLException 
 */ 
	 public static int autentificareCont(Cont c) throws SQLException{

	        Connection conectare=BazaDate.conectareBD();
	        if(conectare!=null) {
	          PreparedStatement stmt=conectare.prepareStatement("SELECT * FROM conturi WHERE email=?");            
              ResultSet rs= stmt.executeQuery();
              if(rs.next()==true) {
            	 if(c.getEmail()==rs.getString("email")){
            		 if(c.getParola()==rs.getString("parola")){
            			 if(c.getTipAngajat()==rs.getString("tip_angajat")) return 1;
            			 else return 2;  
            			 }
            		 else return 3;
                   }
            	 else return 4;        
              }
	        }
		return 0;
	 }



}
