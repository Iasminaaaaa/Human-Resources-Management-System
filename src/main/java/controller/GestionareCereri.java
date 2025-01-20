package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bd.BazaDate;
import model.Cerere;
import model.ExceptieAplicatie;

public class GestionareCereri {
/**
 * Creaza tabelul 'cereri' in baza de date daca nu exista deja
 */
public static void creareTabelCereri() {
    Connection conectare=BazaDate.conectareBD();
    
    if (conectare!=null) {
    	 String query="CREATE TABLE IF NOT EXISTS cereri (" +
              "cerere_id INT(11) AUTO_INCREMENT PRIMARY KEY, "+
              "angajat_id INT(11), "+
              "tip_cerere VARCHAR(50), "+
              "data_inceput DATE, "+
              "data_sfarsit DATE, "+
              "status VARCHAR(50), "+
			  "manager_id INT(11) "+
              ")";
        
        try (PreparedStatement stmt=conectare.prepareStatement(query)) {
            stmt.executeUpdate();
            System.out.println("Tabel 'cereri' a fost creat sau exista deja.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului: " + e.getMessage());
        }
    }
}

/**
 * Creaza tabelul 'cereri_arhivate' in baza de date daca nu exista deja
 */
 public static void creareTabelaCereriArhivate() {
	        Connection conectare=BazaDate.conectareBD();
	        if (conectare!=null) {
	            String query="CREATE TABLE IF NOT EXISTS cereri_arhivate (" +
	                    "cerere_id INT PRIMARY KEY, " +
	                    "angajat_id INT NOT NULL, " +
	                    "tip_cerere VARCHAR(255) NOT NULL, " +
	                    "data_inceput DATE NOT NULL, " +
	                    "data_sfarsit DATE NOT NULL, " +
	                    "status VARCHAR(255) NOT NULL, " +
	                    "manager_id INT NOT NULL " +
	                    ")";

	            try (PreparedStatement stmt=conectare.prepareStatement(query)) {
	                stmt.executeUpdate();
	                System.out.println("Tabela cereri_arhivate a fost creată cu succes!");
	            } catch (SQLException e) {
	                System.err.println("Eroare la crearea tabelei cereri_arhivate: " + e.getMessage());
	            }
	        }
	    }
	
 /**
  * Vizualizeaza cererile aflate in asteptare pentru un manager dat
  * @param managerId ID-ul managerului
  * @return lista cererilor aflate in asteptare
  * @throws ExceptieAplicatie daca apare o eroare in procesul de obtinere a cererilor
  */
public static List<Cerere> vizualizareCereriInAsteptare(int managerId) throws ExceptieAplicatie  {
		List<Cerere> cereriInAsteptare=new ArrayList<>();
		Connection conectare=BazaDate.conectareBD();
			    
			  if(conectare!=null) {
				  String query="SELECT * FROM cereri WHERE status='Pending' AND (manager_id=? OR manager_id IS NULL)";

			        try (PreparedStatement stmt=conectare.prepareStatement(query)){
			        	   stmt.setInt(1, managerId);
			        
			        		ResultSet rs=stmt.executeQuery();

			            while (rs.next()) {
			                 int cerereId=rs.getInt("cerere_id");
			                int angajatId=rs.getInt("angajat_id");
			                String tipCerereStr=rs.getString("tip_cerere");
			                Cerere.TipCerere tipCerere=Cerere.TipCerere.valueOf(tipCerereStr); 
			                Date dataInceput=rs.getDate("data_inceput");
			                Date dataSfarsit=rs.getDate("data_sfarsit");
			                String status=rs.getString("status");
			                Cerere cerere;
			                int managerIdDinBD=rs.getInt("manager_id");
			                if (managerIdDinBD==0) {
			                    cerere=new Cerere(cerereId, angajatId, status, dataInceput, dataSfarsit, tipCerere, -1);
			                } else {
			                    cerere=new Cerere(cerereId, angajatId, status, dataInceput, dataSfarsit, tipCerere, managerIdDinBD);
			                }

			                System.out.println(cerere);
			                cereriInAsteptare.add(cerere);
			            }
			        } catch (SQLException e) {
			            e.printStackTrace(); 
			        }
			    }
			    return cereriInAsteptare;
	}

/**
 * Actualizeaza statusul unei cereri
 * @param requestId ID-ul cererii
 * @param newStatus Noul status al cererii
 * @return true daca statusul a fost actualizat cu succes, false in caz contrar
 * @throws ExceptieAplicatie daca apare o eroare in procesul de actualizare
 */
public static boolean actualizareStatus(String requestId, String newStatus) throws ExceptieAplicatie {
		Connection conectare=BazaDate.conectareBD();
	    
		  if(conectare!=null) {
			  try {
				  PreparedStatement stmt=conectare.prepareStatement("UPDATE cereri SET status=? WHERE cerere_id=?");
	  
	        stmt.setString(1, newStatus); 
	        stmt.setString(2, requestId); 
	        
	        int rowsUpdated=stmt.executeUpdate();
	        GestionareCereri.arhivareCereri();
	        return rowsUpdated > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    }
		return true;
	}
		
/**
 * Arhiveaza cererile care nu mai sunt in statusul 'Pending'
 * Cererile arhivate sunt mutate in tabelul 'cereri_arhivate' si sterse din tabelul 'cereri'
 * @throws ExceptieAplicatie daca apare o eroare in procesul de arhivare a cererilor
 */
public static void arhivareCereri() throws ExceptieAplicatie {
	    Connection conectare=BazaDate.conectareBD();

	    if (conectare!=null) {
	        String selectQuery="SELECT * FROM cereri WHERE status!='Pending'";
	        String insertQuery="INSERT INTO cereri_arhivate (cerere_id, angajat_id, tip_cerere, data_inceput, data_sfarsit, status, manager_id) "
	                           + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        String deleteQuery="DELETE FROM cereri WHERE cerere_id=?";

	        try (PreparedStatement selectStmt=conectare.prepareStatement(selectQuery);
	             PreparedStatement insertStmt=conectare.prepareStatement(insertQuery);
	             PreparedStatement deleteStmt=conectare.prepareStatement(deleteQuery);
	             ResultSet rs=selectStmt.executeQuery()) {

	            while (rs.next()) {
	                int cerereId=rs.getInt("cerere_id");
	                int angajatId=rs.getInt("angajat_id");
	                String tipCerere=rs.getString("tip_cerere");
	                Date dataInceput=rs.getDate("data_inceput");
	                Date dataSfarsit=rs.getDate("data_sfarsit");
	                String status=rs.getString("status");
	                int managerId=rs.getInt("manager_id");

	                insertStmt.setInt(1, cerereId);
	                insertStmt.setInt(2, angajatId);
	                insertStmt.setString(3, tipCerere);
	                insertStmt.setDate(4, dataInceput);
	                insertStmt.setDate(5, dataSfarsit);
	                insertStmt.setString(6, status);
	                insertStmt.setInt(7, managerId);
	                insertStmt.executeUpdate();

	                if ("Resignation".equals(tipCerere) && "Accepted".equals(status)) {
	                   GestionareAngajati.stergereAngajat(angajatId);
	                }
	                
	                deleteStmt.setInt(1, cerereId);
	                deleteStmt.executeUpdate();
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ExceptieAplicatie("Eroare la arhivarea cererilor: " + e.getMessage());
	        }
	    }
	}

/**
 * Vizualizeaza cererile arhivate ale unui manager
 * @param managerId ID-ul managerului
 * @return lista cererilor arhivate pentru managerul dat
 * @throws ExceptieAplicatie daca apare o eroare in procesul de obtinere a cererilor arhivate
 */
public static List<Cerere> vizualizareCereriArhivate(int managerId) throws ExceptieAplicatie {
	    List<Cerere> cereriArhivate=new ArrayList<>();
	    Connection conectare=BazaDate.conectareBD();

	    if (conectare!=null) {
	        String query="SELECT * FROM cereri_arhivate WHERE manager_id=?";

	        try (PreparedStatement stmt=conectare.prepareStatement(query)){
	        		
	        		 stmt.setInt(1, managerId);
	        		ResultSet rs=stmt.executeQuery();

	            while (rs.next()) {
	                int cerereId=rs.getInt("cerere_id");
	                int angajatId=rs.getInt("angajat_id");
	                String tipCerere=rs.getString("tip_cerere");
	                Date dataInceput=rs.getDate("data_inceput");
	                Date dataSfarsit=rs.getDate("data_sfarsit");
	                String status=rs.getString("status");
	               
	                Cerere cerere=new Cerere(cerereId, angajatId, status, dataInceput, dataSfarsit, Cerere.TipCerere.valueOf(tipCerere), managerId);
	                cereriArhivate.add(cerere);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ExceptieAplicatie("Eroare la vizualizarea cererilor arhivate: " + e.getMessage());
	        }
	    }

	    return cereriArhivate;
	}

/**
 * Vizualizare cereri asociate unui angajat pt administrator
 * @param angajatId ID-ul angajatului pentru care se vor cauta cererile
 * @return lista de cereri asociate angajatului respectiv
 * @throws ExceptieAplicatie dacă apare o eroare la interacțiunea cu baza de date
 */
public static List<Cerere> vizualizareCereriPerAngajat(int angajatId) throws ExceptieAplicatie  {
		List<Cerere> cereri=new ArrayList<>();
		Connection conectare=BazaDate.conectareBD();
			    
			  if(conectare!=null) {
				  String query="SELECT * FROM cereri WHERE (manager_id=? OR manager_id IS NULL) ORDER BY cerere_id DESC";

			        try (PreparedStatement stmt=conectare.prepareStatement(query)){
			        	   stmt.setInt(1, angajatId);
			        
			        		ResultSet rs=stmt.executeQuery();

			            while (rs.next()) {
			                 int cerereId=rs.getInt("cerere_id");
			                String tipCerereStr=rs.getString("tip_cerere");
			                Cerere.TipCerere tipCerere=Cerere.TipCerere.valueOf(tipCerereStr); 
			                Date dataInceput=rs.getDate("data_inceput");
			                Date dataSfarsit=rs.getDate("data_sfarsit");
			                String status=rs.getString("status");
			                Cerere cerere;
			                int managerIdDinBD=rs.getInt("manager_id");
			                if (managerIdDinBD==0) {
			                    cerere=new Cerere(cerereId, angajatId, status, dataInceput, dataSfarsit, tipCerere, -1);
			                } else {
			                    cerere=new Cerere(cerereId, angajatId, status, dataInceput, dataSfarsit, tipCerere, managerIdDinBD);
			                }

			                System.out.println(cerere);
			                cereri.add(cerere);
			            }
			        } catch (SQLException e) {
			            e.printStackTrace(); 
			        }
			    }
			    return cereri;
	}
	
/**
 * Vizualizeaza cererile facute de un anumit angajat
 * @param angajatId ID-ul angajatului pentru care se vor căuta cererile
 * @return lista de cereri făcute de angajatul respectiv
 * @throws ExceptieAplicatie dacă apare o eroare la interacțiunea cu baza de date
 */
public static List<Cerere> vizualizareCereriAngajat(int angajatId) throws ExceptieAplicatie  {
		List<Cerere> cereri=new ArrayList<>();
		Connection conectare=BazaDate.conectareBD();
			    
			  if(conectare!=null) {
				  String query="SELECT * FROM cereri WHERE angajat_id=?";

			        try (PreparedStatement stmt=conectare.prepareStatement(query)){
			        	   stmt.setInt(1, angajatId);
			        
			        		ResultSet rs=stmt.executeQuery();

			            while (rs.next()) {
			                 int cerereId=rs.getInt("cerere_id");
			                String tipCerereStr=rs.getString("tip_cerere");
			                Cerere.TipCerere tipCerere=Cerere.TipCerere.valueOf(tipCerereStr); 
			                Date dataInceput=rs.getDate("data_inceput");
			                Date dataSfarsit=rs.getDate("data_sfarsit");
			                String status=rs.getString("status");
			                Cerere cerere;
			                int managerIdDinBD=rs.getInt("manager_id");
			                if (managerIdDinBD==0) {
			                    cerere=new Cerere(cerereId, angajatId, status, dataInceput, dataSfarsit, tipCerere, -1);
			                } else {
			                    cerere=new Cerere(cerereId, angajatId, status, dataInceput, dataSfarsit, tipCerere, managerIdDinBD);
			                }

			                System.out.println(cerere);
			                cereri.add(cerere);
			            }
			        } catch (SQLException e) {
			            e.printStackTrace(); 
			        }
			    }
			    return cereri;
	}

/**
* Adauga o cerere noua in baza de date
* @param cerere Obiectul Cerere care trebuie adaugat
*/
	public static void adaugareCerereNoua(Cerere cerere) {
        String query="INSERT INTO cereri (angajat_id, tip_cerere, data_inceput, data_sfarsit, status, manager_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conectare=BazaDate.conectareBD();
	    
		if(conectare!=null) {
        try (PreparedStatement preparedStatement=conectare.prepareStatement(query)) {

            preparedStatement.setInt(1, cerere.getAngajatId());
            preparedStatement.setInt(6, cerere.getManagerId());
            preparedStatement.setString(2, cerere.getTipCerere().toString());
            preparedStatement.setDate(3, cerere.getDataInceput());
            preparedStatement.setDate(4, cerere.getDataSfarsit());
            preparedStatement.setString(5, cerere.getStatus());

            int rowsInserted=preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cererea a fost adăugată cu succes!");
            } else {
                System.out.println("Eroare la adăugarea cererii.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("A apărut o eroare la conectarea cu baza de date.");
        }
    }
	}
}
