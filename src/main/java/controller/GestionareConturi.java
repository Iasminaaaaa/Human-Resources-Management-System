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

/**
 * Creaza tabelul 'conturi' in baza de date daca nu exista deja
 * Acest tabel este folosit pentru a stoca datele conturilor utilizatorilor, incluzand email-ul, parola si tipul de angajat
 */
public static void creareTabelConturi(){
		    Connection conectare=BazaDate.conectareBD();
		 
		 if(conectare!=null){
	            String query="CREATE TABLE IF NOT EXISTS conturi(" +
	                           "email VARCHAR(255)PRIMARY KEY, " +
	                           "parola VARCHAR(255)NOT NULL, " +
	                           "tip_angajat VARCHAR(255)NOT NULL, " +
	                           "FOREIGN KEY(email)REFERENCES angajati(email)ON DELETE CASCADE ON UPDATE CASCADE" + 
	                           ")";

	            try(PreparedStatement stmt=conectare.prepareStatement(query)){
	                stmt.executeUpdate();
	                System.out.println("Tabelul 'conturi' a fost creat sau există deja.");
	            } catch(SQLException e){
	                JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului 'conturi': " + e.getMessage());
	            }
		 }
}
	 
/**
 * Insereaza un nou cont in tabelul 'conturi'
 * @param c obiectul Cont care contine datele pentru contul de utilizator
 */	 
public static void inserareCont(Cont c){

	        Connection conectare=BazaDate.conectareBD();
	        if(conectare!=null){
	        try{
	           String query="INSERT INTO conturi(email, parola, tip_angajat)VALUES(?, ?, ?)";
	           PreparedStatement stmt=conectare.prepareStatement(query);            
             stmt.setString(1, c.getEmail());
	      	 stmt.setString(2, c.getParola());
	      	 stmt.setString(3, c.getTipAngajat());
	        
	      stmt.execute();
	      System.out.println("Datele au fost adăugate în tabelul ");
	      

	        }catch(SQLException | HeadlessException e){
	            JOptionPane.showMessageDialog(null, e);
	        }
	        }
	    }

/**
 * Genereaza o parola aleatorie de 10 caractere
 * @return parola generata aleatoriu
 */
public static String generareParolaRandom(){
	        char[] possibleCharacters=("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?").toCharArray();
	        String randomStr=RandomStringUtils.random(10, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
	        return randomStr;
	    }	 
	 
/**
 * 	 
 * @param c obiectul Cont care contine datele de autentificare
 * @return 1, 2, 3 sau 4. 
 * Daca se obtine 0 inseamna eroare la conectarea la baza de date
 * Daca se obtine 1 datele contului corespund cu datele din baza de date, autentificare reusita
 * Daca se obtine 2 tipul de angajat nu corespunde
 * Daca se obtine 3 parola este gresita
 * Daca se obtine 4 email-ul nu exista in baza 
 * @throws SQLException  in caz de eroare la accesarea bazei de date
 */ 
public static int autentificareCont(Cont c)throws SQLException {
		    Connection conectare=BazaDate.conectareBD();
		    
		    if(conectare!=null){
		    	  System.out.println("Conectat la baza de date.");
		          
		    	String query="SELECT * FROM conturi WHERE email=?";
		        PreparedStatement stmt=conectare.prepareStatement(query);
		        stmt.setString(1, c.getEmail());  
		        
		        ResultSet rs=stmt.executeQuery();
		        
		        if(rs.next()){
		             if(c.getEmail().equals(rs.getString("email"))){
		                if(c.getParola().equals(rs.getString("parola"))){
		                    if(c.getTipAngajat().equals(rs.getString("tip_angajat"))){
		                        return 1; 
		                    } 
		                    else {
		                        return 2;  
		                    }
		             } else {
		                 return 3;  
		             } 
		            }
		        }else return 4;
		        
		    }
		    
		    return 0;  
	}
/**
 * Obtine ID-ul angajatului pe baza email-ului
 * @param email adresa de email a angajatului
 * @return ID-ul angajatului sau 0 daca nu este gasit
 * @throws SQLException in caz de eroare la accesarea bazei de date
 */
public static int getIdAngajat(String email)throws SQLException {
	        int idAngajat=0;
	        Connection conectare=BazaDate.conectareBD();
	        String query="SELECT a.angajat_id FROM conturi c JOIN angajati a ON c.email=a.email WHERE c.email=?";
	        PreparedStatement stmt=conectare.prepareStatement(query);
	        stmt.setString(1, email);

	        ResultSet rs=stmt.executeQuery();
	        if(rs.next()){
	            idAngajat=rs.getInt("angajat_id");
	        }
	        return idAngajat;
	  }
	


}
