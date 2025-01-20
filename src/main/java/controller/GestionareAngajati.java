package controller;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;  
import javax.swing.JOptionPane;
import bd.BazaDate;
import model.Angajat;
import model.AngajatArhivat;
import model.Cont;
import model.IstoricJob;
import model.IstoricManager;
import model.IstoricSalariu;

public class GestionareAngajati {
/**
* Creeaza tabelul angajați în baza de date daca acesta nu exista deja
*/
public static void creareTabelAngajati() {
	    Connection conectare=BazaDate.conectareBD();

	    if (conectare!=null) {
	        String query="CREATE TABLE IF NOT EXISTS angajati ("+
	                "angajat_id INT(11) AUTO_INCREMENT PRIMARY KEY, "+
	                "nume VARCHAR(255) NOT NULL, "+
	                "job VARCHAR(255) NOT NULL, "+
	                "salariu DECIMAL(10,2), "+
	                "data_angajare DATE, "+
	                "numar_telefon VARCHAR(15), "+
	                "email VARCHAR(255) UNIQUE, "+
	                "adresa VARCHAR(255), "+
	                "manager_id INT(11) NULL, "+
	                "FOREIGN KEY (manager_id) REFERENCES angajati(angajat_id) ON DELETE SET NULL"+
	                ")";

	        try (PreparedStatement stmt=conectare.prepareStatement(query)) {
	            stmt.executeUpdate();
	            System.out.println("Tabelul 'angajati' a fost creat sau există deja.");
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului: "+e.getMessage());
	        }
	    }
	}
	
	
/**
* Creeaza tabelul angajați_arhivati în baza de date daca acesta nu exista deja
* In acesta se adauga angajatii in momentul in care acestia demisioneaza pentru a pastra un registru cu angajatii care au lucrat in companie
*/
public static void creareTabelAngajatiArhivati() {
		    Connection conectare=BazaDate.conectareBD();

		    if (conectare!=null) {
		        String query="CREATE TABLE IF NOT EXISTS angajati_arhivati (" +
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

		        try (PreparedStatement stmt=conectare.prepareStatement(query)) {
		            stmt.executeUpdate();
		           System.out.println("Tabelul 'angajati_arhivati' a fost creat sau există deja.");
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului angajati_arhivati: "+e.getMessage());
		        }
		    }
		}

/**
* Creeaza tabelul istoric_modificari_salariu in cazul in care acesta nu exista deja
* In acesta apar modificarile la nivel de salariu (Ex: daca salariul unui anagajat creste/scade de-a lungul timpului, acest lucru va fi inregistrat in tabel)
*/
public static void creareTabelModificariSalariu() {
		 Connection conectare=BazaDate.conectareBD();

		    if (conectare!=null) {
		        try {
		            String querySalariu="CREATE TABLE IF NOT EXISTS istoric_modificari_salariu (" +
		                    "id_modificare INT AUTO_INCREMENT PRIMARY KEY, " +
		                    "angajat_id INT NOT NULL, " +
		                    "data_start DATE NOT NULL, " +
		                    "data_sfarsit DATE, " +
		                    "salariu_anterior DECIMAL(10,2) NOT NULL, " +
		                    "motiv VARCHAR(255) " +
		                    ")";
		            try (PreparedStatement stmtSalariu=conectare.prepareStatement(querySalariu)) {
		                stmtSalariu.executeUpdate();
		                System.out.println("Tabelul 'istoric_modificari_salariu' a fost creat sau există deja.");
		            }
		        } catch (SQLException e) {
		            System.out.println("Eroare la crearea tabelului 'istoric_modificari_salariu': "+e.getMessage());
		        }
		    }
		    else {
		    	System.out.println("Conexiunea la baza de date nu a fost realizată.");
	    }
	 }

/**
* Creeaza tabelul istoric_modificari_job in cazul in care acesta nu exista deja
* In acesta apar modificarile la nivel de job (Ex: daca un angajat este promovat, acest lucru va aparea in tabel)
*/
public static void creareTabelModificariJob() {
			 Connection conectare=BazaDate.conectareBD();

			if (conectare!=null) {
			       
		        try {
		            String queryJob="CREATE TABLE IF NOT EXISTS istoric_modificari_job (" +
		                    "id_modificare INT AUTO_INCREMENT PRIMARY KEY, " +
		                    "angajat_id INT NOT NULL, " +
		                    "data_start DATE NOT NULL, " +
		                    "data_sfarsit DATE, " +
		                    "pozitie_anterioara VARCHAR(100) NOT NULL, " +
		                    "motiv VARCHAR(255) " +
		                    ")";
		            try (PreparedStatement stmtJob=conectare.prepareStatement(queryJob)) {
		                stmtJob.executeUpdate();
		                System.out.println("Tabelul 'istoric_modificari_job' a fost creat sau există deja.");
		            }
		        } catch (SQLException e) {
		            System.out.println("Eroare la crearea tabelului 'istoric_modificari_job': "+e.getMessage());
		        }
			}
		     else {
		    	  System.out.println("Conexiunea la baza de date nu a fost realizată.");
		        }
			}

/**
 * Creeaza tabelul istoric_modificari_manager
 * In acesta apar modificarile la nivel de job (Ex: daca un angajat este promovat, acest lucru va aparea in tabel)
 */
public static void creareTabelModificariManager() {
		  Connection conectare=BazaDate.conectareBD();
		  
			    if (conectare!=null) {
			        try {
			            String queryManager="CREATE TABLE IF NOT EXISTS istoric_modificari_manager (" +
			                    "id_modificare INT AUTO_INCREMENT PRIMARY KEY, " +
			                    "angajat_id INT NOT NULL, " +
			                    "data_start DATE NOT NULL, " +
			                    "data_sfarsit DATE, " +
			                    "manager_anterior VARCHAR(100), " +
			                    "motiv VARCHAR(255) " +
			                    ")";
			            try (PreparedStatement stmtManager=conectare.prepareStatement(queryManager)) {
			                stmtManager.executeUpdate();
			                System.out.println("Tabelul 'istoric_modificari_manager' a fost creat sau există deja.");
			            }
			        } catch (SQLException e) {
			            System.out.println("Eroare la crearea tabelului 'istoric_modificari_manager': "+e.getMessage());
			        }  
			    } else {
			        System.out.println("Conexiunea la baza de date nu a fost realizată.");
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
	            double salariu=rs.getDouble("salariu");
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
 public static void inserareAngajati(Angajat a, String tabel, boolean adaugId, String tipAngajat){

        Connection conectare=BazaDate.conectareBD();
        if(conectare!=null) {
        try{
        	 String query;
        	 PreparedStatement stmt;
        	 int nr=1;
             if (adaugId==true) {
                 query="INSERT INTO "+tabel+" (angajat_id, data_demisie, nume, job, salariu, data_angajare, numar_telefon, email, adresa, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                 stmt=conectare.prepareStatement(query);
                 stmt.setInt(nr++, a.getId()); 
                 stmt.setDate(nr++, Date.valueOf(LocalDate.now()));
             } else {
                 query="INSERT INTO "+tabel+" (nume, job, salariu, data_angajare, numar_telefon, email, adresa, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                 stmt=conectare.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             }
           
                    
         	 stmt.setString(nr++, a.getNume());
        	 stmt.setString(nr++, a.getJob());
        	 stmt.setDouble(nr++, a.getSalariu());
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
      JOptionPane.showMessageDialog(null, "Datele au fost adăugate în tabelul "+tabel);
      
      if(tabel.equals("angajati")) {
       Cont c=new Cont(a.getEmail(), GestionareConturi.generareParolaRandom(), tipAngajat);
      GestionareConturi.inserareCont(c);
      try (ResultSet rs=stmt.getGeneratedKeys()) {
          if (rs.next()) {
              int aId=rs.getInt(1); 
              System.out.println("Angajat inserat cu ID: "+aId);
              String dataCurenta=LocalDate.now().toString();
	          GestionareAngajati.inserareIstoric(aId, a.getJob(), "job", "Angajat nou adăugat", dataCurenta);
	          GestionareAngajati.inserareIstoric(aId, String.valueOf(a.getSalariu()), "salariu", "Angajat nou adăugat", dataCurenta);
	          GestionareAngajati.inserareIstoric(aId, String.valueOf(a.getManagerId()), "manager_id", "Angajat nou adăugat", dataCurenta);
          }
      }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e);
          }
     }
    }catch(SQLException | HeadlessException e){
          JOptionPane.showMessageDialog(null, e);
     }
       }
        else {
       	System.out.println("Nu s-a putut realiza conexiunea");
   
        }
    
 }
    
 
 /**
  * Inserarea unui angajat în tabelul specificat
  * @param a Angajatul care trebuie inserat
  * @param tabel Numele tabelului în care se face inserarea
  */
 public static void inserareAngajatNou(Angajat a, String tipAngajat) {
	 inserareAngajati(a, "angajati", false, tipAngajat);
   }
 
 public static void arhivareAngajati(Angajat a) {
	   inserareAngajati(a, "angajati_arhivati", true, null);
}

 
 /**
  * Sterge un angajat din baza de date si apeleaza arhivareAngajati care arhiveaza angajatul pentru ca acesta sa existe in continuare in registrele firmei, ca fost angajat
  * @param id ID-ul angajatului ce trebuie sters
  */	
 public static void stergereAngajat(int id) {
	 Connection conectare=BazaDate.conectareBD();
		    
		  if(conectare!=null) {
			  try (PreparedStatement stmt1=conectare.prepareStatement("SELECT * FROM angajati WHERE angajat_id=?")) {
	                stmt1.setInt(1, id);
	                try (ResultSet rs=stmt1.executeQuery()) {
	                    if (rs.next()==true) {
	                        int angajatId=rs.getInt("angajat_id");
	                        String nume=rs.getString("nume");
	                        String job=rs.getString("job");
	                        double salariu=rs.getDouble("salariu");
	                        Date dataAngajare=rs.getDate("data_angajare");
	                        String nrTelefon=rs.getString("numar_telefon");
	                        String email=rs.getString("email");
	                        String adresa=rs.getString("adresa");
	                        int managerId=rs.getInt("manager_id");
	                        
	                        Angajat a=new Angajat(angajatId, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId);
	                        
	                        arhivareAngajati(a);
	                        
	                        GestionareRapoarte.arhivareRapoarte(id);
	                        
	                        try (PreparedStatement stmt2=conectare.prepareStatement("DELETE FROM angajati WHERE angajat_id=?")) {
	                            stmt2.setInt(1, id);
	                            stmt2.executeUpdate();
	                            JOptionPane.showMessageDialog(null, "Angajatul a fost șters cu succes.");
	                        }
	                } else {
	                        JOptionPane.showMessageDialog(null, "Nu a fost găsit niciun angajat cu ID-ul furnizat.");
	                }
	            }
	        } catch (SQLException e) {
	                JOptionPane.showMessageDialog(null, "Eroare la ștergerea angajatului: "+e.getMessage());
	        }		  
	    }   
 }

 /**
  * Metoda getNumeAngajat obtine numele angajatului in functie de ID-ul acestuia din baza de date
  * @param id ID-ul angajatului
  * @return Numele angajatului sau null daca nu se gaseste
  * @throws SQLException Daca apare o eroare la interogarea bazei de date
  */
 public static String getNumeAngajat(int id) throws SQLException {
	    Connection conectare=BazaDate.conectareBD();
	   
	    if(conectare!=null){
			  try (PreparedStatement stmt1=conectare.prepareStatement("SELECT nume FROM angajati WHERE angajat_id=?")) {
	                stmt1.setInt(1, id);
	                ResultSet rs=stmt1.executeQuery();
	                
	                if(rs.next()==true) {
	                        String nume=rs.getString("nume");
	                        return nume;
	                    }
	                }
	       }
		return null;
}

 /**
  * Metoda detaliiAngajat returneaza detaliile unui angajat in functie de ID-ul acestuia
  * @param id ID-ul angajatului
  * @return Obiectul Angajat cu detaliile complete ale angajatului
  */
 public static Angajat detaliiAngajat(int id) {
	 Connection conectare=BazaDate.conectareBD();
	 Angajat a=new Angajat();
		  if(conectare!=null) {
		    String query="SELECT * FROM angajati WHERE angajat_id=?";
		   
		    try{
		    	PreparedStatement stmt=conectare.prepareStatement(query);
		        stmt.setInt(1, id);
		    	ResultSet rs=stmt.executeQuery();
		    	
		        while (rs.next()) {
		            int angajatId=rs.getInt("angajat_id");
		            String nume=rs.getString("nume");
		            String job=rs.getString("job");
		            double salariu=rs.getDouble("salariu");
		            Date dataAngajare=rs.getDate("data_angajare");
		            String nrTelefon=rs.getString("numar_telefon");
		            String email=rs.getString("email");
		            String adresa=rs.getString("adresa");
		            int managerId=rs.getInt("manager_id");
		            a=new Angajat(angajatId, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId);  
		        }
		    }catch(SQLException e) {
		    	JOptionPane.showMessageDialog(null, e);
		    }
		}
		
		return a;
		}
 
 /**
  * Metoda numeSiIdAngajati returneaza o lista cu numele si ID-urile tuturor angajatilor
  * @return O lista de stringuri continand ID-ul si numele angajatilor
  */
 public static List<String> numeSiIdAngajati() {
	 List <String> numeAngj=new ArrayList<>();
	 Connection conectare=BazaDate.conectareBD();
		    
		  if(conectare!=null) {
		    String query="SELECT nume, angajat_id FROM angajati ORDER BY nume";
		    try(PreparedStatement stmt=conectare.prepareStatement(query);
		        ResultSet rs=stmt.executeQuery()) {
		    	
		        while (rs.next()) {
		        	int angajatId=rs.getInt("angajat_id");
		            String nume=rs.getString("nume");
		            numeAngj.add(angajatId+" "+nume);   
		        }
		    }catch(SQLException e) {
		    	e.printStackTrace();
		    }
		}
		
		return numeAngj;
	}
 
 /**
  * Metoda actualizareAngajat este utila pentru modificarea unui camp din tabela angajati
  * @param coloanaModif reprezinta coloana care va fi acutualizata
  * @param valoare valoarea care se va adauga in tabela in locul celei existente deja
  * @param id id-ul angajatului pentru care se face modificarea
  * @return int returneaza 1 daca angajatul a fost actualizat cu succes si 0 daca a aparut o exceptie
  */ 
 public static int actualizareAngajat(String coloanaModif, String valoare, int id) {
	    Connection conectare=BazaDate.conectareBD();
	    String query;
	    if (coloanaModif.equals("job")) {
	        query="UPDATE angajati SET "+coloanaModif+"=?, data_angajare=NOW() WHERE angajat_id=?";
	    } else {
	        query="UPDATE angajati SET "+coloanaModif+"=? WHERE angajat_id=?";
	    }

	    String valoareVeche="";
	    String dataVeche="";

	    if (conectare==null) {
	        JOptionPane.showMessageDialog(null, "Database connection failed.");
	        return 0;
	    }

	    if (valoare==null || valoare.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
	        return 0;
	    }

	    if (coloanaModif.equals("job") || coloanaModif.equals("salariu") || coloanaModif.equals("manager_id")) {
	        String querySelect="SELECT "+coloanaModif+", data_angajare FROM angajati WHERE angajat_id=?";
	        try (PreparedStatement stmtSelect=conectare.prepareStatement(querySelect)) {
	            stmtSelect.setInt(1, id);
	            ResultSet rs=stmtSelect.executeQuery();
	            if (rs.next()) {
	                valoareVeche=rs.getString(1);
	                dataVeche=rs.getString(2);
	            }
	            rs.close();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "An error occurred while retrieving the old value. Please try again!");
	            return 0;
	        }
	    }

	    try (PreparedStatement stmt=conectare.prepareStatement(query)) {
	        if (coloanaModif.equals("salariu")) {
	            stmt.setDouble(1, Double.parseDouble(valoare));
	        } else {
	            stmt.setString(1, valoare);
	        }
	        stmt.setInt(2, id);

	        int rowsUpdated=stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(null, "Angajatul a fost actualizat cu succes.");
	            if (coloanaModif.equals("job") || coloanaModif.equals("salariu") || coloanaModif.equals("manager_id")) {
	                String motiv=JOptionPane.showInputDialog(null, "Introduceți motivul schimbării:", "Motivul schimbării", JOptionPane.INFORMATION_MESSAGE);
	                inserareIstoric(id, valoare, coloanaModif, motiv, dataVeche);
	            }
	        }
	    } catch (SQLException | HeadlessException e) {
	        JOptionPane.showMessageDialog(null, "An error occurred while updating the employee details. Please check your input and try again!");
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	        return 0;
	    }

	    return 1;
	}

 /**
  * Metoda inserareIstoric salveaza o modificare intr-un tabel istoric in functie de tipul de modificare
  * @param idAngajat ID-ul angajatului
  * @param valoare Valoarea veche a modificarii (job, salariu, manager)
  * @param coloanaModif Coloana care a fost modificata (job, salariu, manager_id)
  * @param motiv Motivul pentru modificare
  * @param dataVeche Data la care a avut loc modificarea
  */
 public static void inserareIstoric(int idAngajat, String valoare, String coloanaModif, String motiv, String dataVeche) {
	    Connection conectare=BazaDate.conectareBD();

	    if (conectare==null) {
	        JOptionPane.showMessageDialog(null, "Conexiunea la baza de date nu a fost realizată.");
	        return;
	    }

	    String tabel="";
	    if (coloanaModif.equals("job")) {
	        tabel="istoric_modificari_job";
	    } else if (coloanaModif.equals("salariu")) {
	        tabel="istoric_modificari_salariu";
	    } else if (coloanaModif.equals("manager_id")) {
	        tabel="istoric_modificari_manager";
	    }

	    String queryUpdateUltimaInreg="UPDATE "+tabel+" SET data_sfarsit=? WHERE angajat_id=? AND data_sfarsit IS NULL";

	    try (PreparedStatement stmtUpdate=conectare.prepareStatement(queryUpdateUltimaInreg)) {
	        stmtUpdate.setDate(1, Date.valueOf(LocalDate.now())); 
	        stmtUpdate.setInt(2, idAngajat);
	        stmtUpdate.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Eroare la actualizarea ultimei înregistrări: "+e.getMessage());
	        return;
	    }

	    String queryInsert;
	    if (coloanaModif.equals("job")) {
	        queryInsert="INSERT INTO "+tabel+" (angajat_id, data_start, data_sfarsit, pozitie_anterioara, motiv) " +
	                      "VALUES (?, ?, NULL, ?, ?)";
	    } else if (coloanaModif.equals("salariu")) {
	        queryInsert="INSERT INTO "+tabel+" (angajat_id, data_start, data_sfarsit, salariu_anterior, motiv) " +
	                      "VALUES (?, ?, NULL, ?, ?)";
	    } else {
	        queryInsert="INSERT INTO "+tabel+" (angajat_id, data_start, data_sfarsit, manager_anterior, motiv) " +
	                      "VALUES (?, ?, NULL, ?, ?)";
	    }

	    try (PreparedStatement stmtInsert=conectare.prepareStatement(queryInsert)) {
	        stmtInsert.setInt(1, idAngajat);
	        stmtInsert.setDate(2, Date.valueOf(dataVeche)); 

	        if (coloanaModif.equals("job")) {
	            stmtInsert.setString(3, valoare);
	        } else if (coloanaModif.equals("salariu")) {
	            stmtInsert.setDouble(3, Double.parseDouble(valoare));
	        } else {
	            stmtInsert.setString(3, valoare);
	        }

	        stmtInsert.setString(4, motiv); 
	        stmtInsert.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Eroare la inserarea unei noi înregistrări: "+e.getMessage());
	    }
	}

 /**
  * Metoda infoAngajatiArhivati returneaza o lista cu informatiile despre angajatii arhivati
  * @return Lista de obiecte AngajatArhivat
  */
 public static List<AngajatArhivat> infoAngajatiArhivati() {
	 List <AngajatArhivat> angajati=new ArrayList<>();
	 Connection conectare=BazaDate.conectareBD();
		    
		  if(conectare!=null) {
		    String query="SELECT * FROM angajati_arhivati";
		    try(PreparedStatement stmt=conectare.prepareStatement(query);
		        ResultSet rs=stmt.executeQuery()) {
		    	
		        while (rs.next()) {
		            int angajatId=rs.getInt("angajat_id");
		            String nume=rs.getString("nume");
		            String job=rs.getString("job");
		            double salariu=rs.getDouble("salariu");
		            Date dataAngajare=rs.getDate("data_angajare");
		            String nrTelefon=rs.getString("numar_telefon");
		            String email=rs.getString("email");
		            String adresa=rs.getString("adresa");
		            int managerId=rs.getInt("manager_id");
		            Date dataDemisie= rs.getDate("data_demisie");
		            AngajatArhivat a=new AngajatArhivat(angajatId, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId, dataDemisie);  
		            angajati.add(a);   
		        }
		    }catch(SQLException e) {
		    	e.printStackTrace();
		    }
		}
		
		return angajati;
	}

 /**
  * Metoda vizualizareIstoricSalariu returneaza istoricul salariilor pentru un angajat
  * @param angajatId ID-ul angajatului pentru care se incarca istoricul
  * @return Lista de obiecte IstoricSalariu
  */
 public static List<IstoricSalariu> vizualizareIstoricSalariu(int angajatId) {
	 List<IstoricSalariu> istoricList=new ArrayList<>();
	    Connection conectare=BazaDate.conectareBD();

	    if (conectare!=null) {
	    	 String salariuQuery="SELECT data_start, data_sfarsit, salariu_anterior, motiv FROM istoric_modificari_salariu WHERE angajat_id=?";
		        try (PreparedStatement stmtSalariu=conectare.prepareStatement(salariuQuery)) {
		            stmtSalariu.setInt(1, angajatId);
		            ResultSet rsSalariu=stmtSalariu.executeQuery();

		            while (rsSalariu.next()) {
		                Date startDate=rsSalariu.getDate("data_start");
		                Date endDate=rsSalariu.getDate("data_sfarsit");
		                Double salariulAnterior=rsSalariu.getDouble("salariu_anterior");
		                String motiv=rsSalariu.getString("motiv");
		                IstoricSalariu modificare=new IstoricSalariu(startDate, endDate, salariulAnterior, motiv);
		                istoricList.add(modificare);
		            }
		        } catch (SQLException e) {
		            System.out.println("Eroare la încărcarea istoricului salariilor: "+e.getMessage());
		            e.printStackTrace();
		        }
	    }else {
	        System.out.println("Conexiune la baza de date eșuată.");
	    }

	    return istoricList;
 }
 
 /**
  * Metoda vizualizareIstoricJob returneaza istoricul modificarilor de job pentru un angajat
  * @param angajatId ID-ul angajatului pentru care se incarca istoricul joburilor
  * @return Lista de obiecte IstoricJob
  */
 public static List<IstoricJob> vizualizareIstoricJob(int angajatId) {
	 List<IstoricJob> istoricList=new ArrayList<>();
	    Connection conectare=BazaDate.conectareBD();

	    if (conectare!=null) {
	    	 String jobQuery="SELECT data_start, data_sfarsit, pozitie_anterioara, motiv FROM istoric_modificari_job WHERE angajat_id=?";
		        try (PreparedStatement stmtJob=conectare.prepareStatement(jobQuery)) {
		            stmtJob.setInt(1, angajatId);
		            ResultSet rsJob=stmtJob.executeQuery();

		            while (rsJob.next()) {
		                Date startDate=rsJob.getDate("data_start");
		                Date endDate=rsJob.getDate("data_sfarsit");
		                String pozitiaAnterioara=rsJob.getString("pozitie_anterioara");
		                String motiv=rsJob.getString("motiv");
			              
		                istoricList.add(new IstoricJob(startDate, endDate, pozitiaAnterioara, motiv));
		      	          
		            }
		        } catch (SQLException e) {
		            System.out.println("Eroare la încărcarea istoricului joburilor: "+e.getMessage());
		            e.printStackTrace();
		        }
	    	
	    }else {
	        System.out.println("Conexiune la baza de date eșuată.");
	    }

	    return istoricList;
}
 
 /**
  * Metoda vizualizareIstoricManager returneaza istoricul modificarilor de manager pentru un angajat
  * @param angajatId ID-ul angajatului pentru care se incarca istoricul managerilor
  * @return Lista de obiecte IstoricManager
  */
 public static List<IstoricManager> vizualizareIstoricManager(int angajatId) {
	 List<IstoricManager> istoricList=new ArrayList<>();
	    Connection conectare=BazaDate.conectareBD();

	    if (conectare!=null) {
	    	  String managerQuery="SELECT data_start, data_sfarsit, manager_anterior, motiv FROM istoric_modificari_manager WHERE angajat_id=?";
		        try (PreparedStatement stmtManager=conectare.prepareStatement(managerQuery)) {
		            stmtManager.setInt(1, angajatId);
		            ResultSet rsManager=stmtManager.executeQuery();

		            while (rsManager.next()) {
		                Date startDate=rsManager.getDate("data_start");
		                Date endDate=rsManager.getDate("data_sfarsit");
		                String managerAnterior=rsManager.getString("manager_anterior");
		                String motiv=rsManager.getString("motiv");
			              
		                istoricList.add(new IstoricManager(startDate, endDate, managerAnterior, motiv));
		     	       
		               }
		        } catch (SQLException e) {
		            System.out.println("Eroare la încărcarea istoricului managerilor: "+e.getMessage());
		            e.printStackTrace();
		        }
		    
	    }else {
	        System.out.println("Conexiune la baza de date eșuată.");
	    }

	    return istoricList;
	}

 /**
  * Metoda getManagerId obtine ID-ul managerului unui angajat
  * @param idAngajat ID-ul angajatului
  * @return ID-ul managerului sau -1 daca nu exista manager
  */
public static int getManagerId(int idAngajat) {
	        int idManager=-1; 
	        String query="SELECT manager_id FROM angajati WHERE angajat_id=?";
	        Connection conectare=BazaDate.conectareBD();

		    if (conectare!=null) {
	        try (PreparedStatement stmt=conectare.prepareStatement(query)) {
	            
	            stmt.setInt(1, idAngajat);
	            ResultSet rs=stmt.executeQuery();
	            
	            if (rs.next()) {
	                idManager=rs.getInt("manager_id");
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	       
		    }
			return idManager;
	    }
	
/**
 * Metoda infoEchipa returneaza echipa unui manager
 * @param managerId ID-ul managerului
 * @return Lista de angajati care fac parte din echipa respectiva
 */
public static List<Angajat> infoEchipa(int managerId) {
	    	 List <Angajat> angajati=new ArrayList<>();
	    	 Connection conectare=BazaDate.conectareBD();
	    		    
	    		  if(conectare!=null) {
	    		    String query="SELECT * FROM angajati WHERE manager_id=?";
	    		    try(PreparedStatement stmt=conectare.prepareStatement(query)){
	    		    	  stmt.setInt(1, managerId);        
	    		    	  ResultSet rs=stmt.executeQuery();
	    		    	
	    		        while (rs.next()) {
	    		            int angajatId=rs.getInt("angajat_id");
	    		            String nume=rs.getString("nume");
	    		            String job=rs.getString("job");
	    		            double salariu=rs.getDouble("salariu");
	    		            Date dataAngajare=rs.getDate("data_angajare");
	    		            String nrTelefon=rs.getString("numar_telefon");
	    		            String email=rs.getString("email");
	    		            String adresa=rs.getString("adresa");
	    		           
	    		            Angajat a=new Angajat(angajatId, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId);  
	    		            angajati.add(a);   
	    		        }
	    		    }catch(SQLException e) {
	    		    	e.printStackTrace();
	    		    }
	    		}
	    		
	    		return angajati;
	    	}

}