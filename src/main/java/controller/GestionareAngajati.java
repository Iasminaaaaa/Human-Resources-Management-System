package controller;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import bd.BazaDate;
import model.Angajat;

public class GestionareAngajati {
	 /**
     * Creeaza tabelul angajați în baza de date daca acesta nu exista deja
     */
	 public static void creareTabelAngajati() {
	    Connection conectare = BazaDate.conectareBD();

	    if (conectare != null) {
	        String query = "CREATE TABLE IF NOT EXISTS angajati (" +
	                "angajat_id INT(11) AUTO_INCREMENT PRIMARY KEY, " +
	                "nume VARCHAR(255) NOT NULL, " +
	                "job VARCHAR(255) NOT NULL, " +
	                "salariu DECIMAL(10,2), " +
	                "data_angajare DATE, " +
	                "numar_telefon VARCHAR(15), " +
	                "email VARCHAR(255) UNIQUE, " +
	                "adresa VARCHAR(255), " +
	                "manager_id INT(11) NULL, " +
	                "FOREIGN KEY (manager_id) REFERENCES angajati(angajat_id) ON DELETE SET NULL" +
	                ")";

	        try (PreparedStatement stmt = conectare.prepareStatement(query)) {
	            stmt.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Tabelul 'angajati' a fost creat sau există deja.");
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului: " + e.getMessage());
	        }
	    }
	}

	
	
	
	/**
    * Creeaza tabelul angajați_arhivati în baza de date daca acesta nu exista deja
    * In acesta se adauga angajatii in momentul in care acestia demisioneaza pentru a pastra un registru cu angajatii care au lucrat in companie
    */
	 public static void creareTabelAngajatiArhivati() {
		    Connection conectare = BazaDate.conectareBD();

		    if (conectare != null) {
		        String query = "CREATE TABLE IF NOT EXISTS angajati_arhivati (" +
		                "angajat_id INT(11) PRIMARY KEY, " +
		                "nume VARCHAR(255) NOT NULL, " +
		                "job VARCHAR(255) NOT NULL, " +
		                "salariu DECIMAL(10,2), " +
		                "data_angajare DATE, " +
		                "numar_telefon VARCHAR(15), " +
		                "email VARCHAR(255), " +
		                "adresa VARCHAR(255), " +
		                "manager_id INT(11), " +
		                "data_demisie DATE " +
		                ")";

		        try (PreparedStatement stmt = conectare.prepareStatement(query)) {
		            stmt.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Tabelul 'angajati_arhivati' a fost creat sau există deja.");
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului angajati_arhivati: " + e.getMessage());
		        }
		    }
		}


/**
* Obtine informatii despre angajații din baza de date
* @return o lista de obiecte de tipul Angajat ce contine informatiile despre angajați
*/
public static List<Angajat> infoAngajati() {
 List <Angajat> angajati=new ArrayList<>();
 Connection conectare=BazaDate.conectareBD();
	    
	  if(conectare!=null) {
	    String query="SELECT * FROM angajati";
	    try(PreparedStatement stmt=conectare.prepareStatement(query);
	        ResultSet rs=stmt.executeQuery()) {
	    	
	        while (rs.next()) {
	            int angajatId=rs.getInt("angajat_id");
	            String nume=rs.getString("nume");
	            String job=rs.getString("job");
	            int salariu=rs.getInt("salariu");
	            Date dataAngajare=rs.getDate("data_angajare");
	            String nrTelefon=rs.getString("numar_telefon");
	            String email=rs.getString("email");
	            String adresa=rs.getString("adresa");
	            int managerId=rs.getInt("manager_id");
	            Angajat a=new Angajat(angajatId, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId);  
	            angajati.add(a);   
	        }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	return angajati;
}

/**
 * Afisează informatiile despre angajați în consola
 * @param angajati lista de angajati ce trebuie afisata
 */
public static void vizualizareInfoAngajati(List <Angajat> angajati) {
	for(Angajat e: angajati) {
       	System.out.println(e);
       }
}

/**
 * Insereaza un angajat in baza de date
 * @param a obiectul de tipul Angajat ce trebuie inserat
 * @param tabel tab
 */
 public static void inserareAngajati(Angajat a, String tabel, boolean adaugId){

        Connection conectare=BazaDate.conectareBD();
        if(conectare!=null) {
        try{
        	 String query;
        	 PreparedStatement stmt;
        	 int nr=1;
             if (adaugId==true) {
                 query="INSERT INTO " + tabel + " (angajat_id, data_demisie, nume, job, salariu, data_angajare, numar_telefon, email, adresa, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                 stmt=conectare.prepareStatement(query);
                 stmt.setInt(nr++, a.getId()); 
                 stmt.setDate(nr++, new Date(System.currentTimeMillis()));
             } else {
                 query="INSERT INTO " + tabel + " (nume, job, salariu, data_angajare, numar_telefon, email, adresa, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                 stmt=conectare.prepareStatement(query);
             }
             
     
         	 stmt.setString(nr++, a.getNume());
        	 stmt.setString(nr++, a.getJob());
        	 stmt.setInt(nr++, a.getSalariu());
             stmt.setDate(nr++, a.getData());
             stmt.setString(nr++, a.getNrTelefon());
             stmt.setString(nr++, a.getEmail());
             stmt.setString(nr++, a.getAdresa());
             if (a.getManagerId() > 0) {
                 stmt.setInt(nr++, a.getManagerId());
             } else {
                 stmt.setNull(nr++, java.sql.Types.INTEGER);
             }
     
      stmt.execute();
      JOptionPane.showMessageDialog(null, "Datele au fost adăugate în tabelul " + tabel);
      

        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }
 
 /**
  * Inserarea unui angajat în tabelul specificat
  * @param a Angajatul care trebuie inserat
  * @param tabel Numele tabelului în care se face inserarea
  */
 public static void inserareAngajatNou(Angajat a) {
	 inserareAngajati(a, "angajati", false);
 }
 
 public static void arhivareAngajati(Angajat a) {
	   inserareAngajati(a, "angajati_arhivati", true);
}

 
 /**
  * Sterge un angajat din baza de date
  * @param id ID-ul angajatului ce trebuie sters
  */	
 public static void stergereAngajat(int id) {
	 Connection conectare=BazaDate.conectareBD();
		    
		  if(conectare!=null) {
			  try (PreparedStatement stmt1 = conectare.prepareStatement("SELECT * FROM angajati WHERE angajat_id = ?")) {
	                stmt1.setInt(1, id);
	                try (ResultSet rs = stmt1.executeQuery()) {
	                    if (rs.next()==true) {
	                        int angajatId=rs.getInt("angajat_id");
	                        String nume=rs.getString("nume");
	                        String job=rs.getString("job");
	                        int salariu=rs.getInt("salariu");
	                        Date dataAngajare=rs.getDate("data_angajare");
	                        String nrTelefon=rs.getString("numar_telefon");
	                        String email=rs.getString("email");
	                        String adresa=rs.getString("adresa");
	                        int managerId=rs.getInt("manager_id");
	                        
	                        Angajat a=new Angajat(angajatId, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId);
	                        
	                        arhivareAngajati(a);
	                        
	                        GestionareRapoarte.arhivareRapoarte(id);
	                        
	                        try (PreparedStatement stmt2=conectare.prepareStatement("DELETE FROM angajati WHERE angajat_id = ?")) {
	                            stmt2.setInt(1, id);
	                            stmt2.executeUpdate();
	                            JOptionPane.showMessageDialog(null, "Angajatul a fost șters cu succes.");
	                        }
	                } else {
	                        JOptionPane.showMessageDialog(null, "Nu a fost găsit niciun angajat cu ID-ul furnizat.");
	                }
	            }
	        } catch (SQLException e) {
	                JOptionPane.showMessageDialog(null, "Eroare la ștergerea angajatului: " + e.getMessage());
	        }		  
	    }   
 }

 /**
  * Metoda actualizareAngajat este utila pentru modificarea unui camp din tabela angajati
  * @param coloanaModif reprezinta coloana care va fi acutualizata
  * @param valoare valoarea care se va adauga in tabela in locul celei existente deja
  * @param id id-ul angajatului pentru care se face modificarea
  */
 public static void actualizareAngajat(String coloanaModif, String valoare, int id) {
	    Connection conectare = BazaDate.conectareBD();
	    String query = "UPDATE angajati SET " + coloanaModif + " = ? WHERE angajat_id = ?";
	    
	    try (PreparedStatement stmt = conectare.prepareStatement(query)) {
	        stmt.setString(1, valoare);
	        stmt.setInt(2, id);
	        
	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(null, "Angajatul a fost actualizat cu succes.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Nu a fost găsit niciun angajat cu ID-ul furnizat.");
	        }
	    } catch (SQLException | HeadlessException e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
	}
 
}
