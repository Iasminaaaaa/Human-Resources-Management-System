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
import model.RaportPerformanta;

public class GestionareRapoarte {
/**
 * Creeaza tabelul rapoarte în baza de date daca acesta nu exista deja
 */
public static void creareTabelRapoarte(){
		    Connection conectare=BazaDate.conectareBD();
		    
		    if(conectare!=null){
		    	   String query="CREATE TABLE IF NOT EXISTS rapoarte(" +
		                    "raport_id INT(11)AUTO_INCREMENT PRIMARY KEY, " +
		                    "comentarii VARCHAR(255)NOT NULL, " +
		                    "scor INT(11), " +
		                    "data_evaluare DATE, " +
		                    "angajat_id INT(11), " +
		                    "manager_id INT(11), " +
		                    "FOREIGN KEY(angajat_id)REFERENCES angajati(angajat_id)ON DELETE CASCADE" +
		                    ")";
		        
		        try(PreparedStatement stmt=conectare.prepareStatement(query)){
		            stmt.executeUpdate();
		            System.out.println("Tabelul 'rapoarte' a fost creat sau există deja.");
		        } catch(SQLException e){
		            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului: " + e.getMessage());
		        }
		    }
		}

public static void creareTabelRapoarteArhivate(){
	    Connection conectare=BazaDate.conectareBD();
	    
	    if(conectare!=null){
	    	   String query="CREATE TABLE IF NOT EXISTS rapoarte_arhivate(" +
	                    "raport_id INT(11), " +
	                    "comentarii VARCHAR(255)NOT NULL, " +
	                    "scor INT(11), " +
	                    "data_evaluare DATE, " +
	                    "angajat_id INT(11), " +
	                    "manager_id INT(11), " +
	                    "FOREIGN KEY(angajat_id)REFERENCES angajati(angajat_id)" +
	                    ")";
	        
	        try(PreparedStatement stmt=conectare.prepareStatement(query)){
	            stmt.executeUpdate();
	            System.out.println("Tabelul 'rapoarte' a fost creat sau există deja.");
	        } catch(SQLException e){
	            JOptionPane.showMessageDialog(null, "Eroare la crearea tabelului: " + e.getMessage());
	        }
	    }
	}
	
  
/**
 * Insereaza un raport de performanta in baza de date
 * @param r obiectul RaportPerformanta ce trebuie inserat
 */
	 public static void inserareRaport(RaportPerformanta r, String tabel){

	     Connection conectare=BazaDate.conectareBD();
	     try{
	    	 String query;
	    	 int nr=1;
	    	 PreparedStatement stmt;
	    	 if(tabel=="rapoarte"){
	    		 query="INSERT INTO "+tabel+"(comentarii, scor, data_evaluare, angajat_id, manager_id)VALUES(?, ?, ?, ?, ?)";
	    		 stmt=conectare.prepareStatement(query);
	    		 stmt.setInt(5, r.getManId());
	    	 }
	    	 else {
	    		 query="INSERT INTO "+tabel+"(raport_id, comentarii, scor, data_evaluare, angajat_id)VALUES(?, ?, ?, ?, ?)";
		     	 stmt=conectare.prepareStatement(query);
		     	 stmt.setInt(nr++, r.getRaprtId()); 
	    	 }
	     	
	     	 stmt.setString(nr++, r.getComentarii());
	     	 stmt.setInt(nr++, r.getScor());
	     	 stmt.setDate(nr++,(Date)r.getDataEvaluare());
	         stmt.setInt(nr++, r.getId());
	  
	   stmt.execute();
	   JOptionPane.showMessageDialog(null, "Data added");

	     }catch(SQLException | HeadlessException e){
	         JOptionPane.showMessageDialog(null, e);
	     }
	 }
	 
/**
 * Obtine rapoartele de performanta din baza de date
 * @return o listă de obiecte de tipul RaportPerformanta ce contin informatiile despre rapoarte
 */
public static List<RaportPerformanta> infoRapoarte(){
	     List<RaportPerformanta> rapoarte=new ArrayList<>();
	     
	     Connection conectare=BazaDate.conectareBD();
	     if(conectare!=null){
	         String query="SELECT * FROM rapoarte";
	         try(PreparedStatement stmt=conectare.prepareStatement(query);
	              ResultSet rs=stmt.executeQuery()){
	             
	             while(rs.next()){
	                 int raportId=rs.getInt("raport_id");
	                 String comentarii=rs.getString("comentarii");
	                 int scor=rs.getInt("scor");
	                 Date dataEvaluare=rs.getDate("data_evaluare");
	                 int angajatId=rs.getInt("angajat_id");
	                 RaportPerformanta r=new RaportPerformanta(raportId, comentarii, scor, dataEvaluare, angajatId, 0);  
	                 rapoarte.add(r);   
	             }
	         } catch(SQLException e){
	             e.printStackTrace();
	         }
	     }
	     
	     return rapoarte;
	 }

/**
 * Afiseaza informatiile despre rapoartele de performanta în consola
 * @param rapoarte lista de rapoarte ce trebuie afisata
 */
public static void vizualizareInfoRapoarte(List<RaportPerformanta> rapoarte){
	     for(RaportPerformanta r : rapoarte){
	         System.out.println(r);
	     }
	 }

/**
 * Insereaza un nou raport in tabelul 'rapoarte'
 * @param r obiectul RaportPerformanta care contine datele raportului
 */
public static void inserareRaportNou(RaportPerformanta r){
		 inserareRaport(r, "rapoarte");
	 }

/**
 * Arhiveaza rapoartele din tabelul 'rapoarte' in tabelul 'rapoarte_arhivate'
 * @param id ID-ul raportului care trebuie arhivat
 */
public static void arhivareRapoarte(int id){
		 Connection conectare=BazaDate.conectareBD();
	     
	     if(conectare!=null){
	         try(PreparedStatement stmt=conectare.prepareStatement("SELECT * FROM rapoarte WHERE raport_id=?")){
	        	 stmt.setInt(1, id);
	        	try(ResultSet rs=stmt.executeQuery()){
	             
	             while(rs.next()==true){
	             int raportId=rs.getInt("raport_id");
                 String comentarii=rs.getString("comentarii");
                 int scor=rs.getInt("scor");
                 Date dataEvaluare=rs.getDate("data_evaluare");
                 int angajatId=rs.getInt("angajat_id");
                 RaportPerformanta r=new RaportPerformanta(raportId, comentarii, scor, dataEvaluare, angajatId, 0);   
                 inserareRaport(r, "rapoarte_arhivate");
	             }
	        	}catch(Exception e){
		        	 JOptionPane.showMessageDialog(null, e);
		         }
	         }catch(Exception e){
	        	 JOptionPane.showMessageDialog(null, e);
	         }
	      }
	}
	     
/**
 * Returneaza lista de rapoarte de performanta pentru un angajat specific
 * @param idAngajat ID-ul angajatului pentru care se obtin rapoartele
 * @return lista de obiecte RaportPerformanta pentru angajatul specificat
 */
public static List<RaportPerformanta> infoRapoartePtAngajat(int idAngajat){
	     List<RaportPerformanta> rapoarte=new ArrayList<>();
	     
	     Connection conectare=BazaDate.conectareBD();
	     if(conectare!=null){
	         String query="SELECT * FROM rapoarte WHERE angajat_id=?";
	         try(PreparedStatement stmt=conectare.prepareStatement(query)){
	              stmt.setInt(1, idAngajat);
	        		 ResultSet rs=stmt.executeQuery();
	             
	             while(rs.next()){
	                 int raportId=rs.getInt("raport_id");
	                 String comentarii=rs.getString("comentarii");
	                 int scor=rs.getInt("scor");
	                 Date dataEvaluare=rs.getDate("data_evaluare");
	                 int angajatId=rs.getInt("angajat_id");
	                 int managerId=rs.getInt("manager_id");
	                 RaportPerformanta r=new RaportPerformanta(raportId, comentarii, scor, dataEvaluare, angajatId, managerId);  
	                 rapoarte.add(r);   
	             }
	         } catch(SQLException e){
	             e.printStackTrace();
	         }
	     }
	     
	     return rapoarte;
	 }

/**
 * Returneaza lista de rapoarte generate de un manager specific
 * @param managerId ID-ul managerului care a generat rapoartele
 * @return lista de obiecte RaportPerformanta generate de managerul specificat
 */
public static List<RaportPerformanta> rapoarteGenerateManager(int managerId){
		    List<RaportPerformanta> rapoarteGenerate=new ArrayList<>();

		    String query="SELECT * FROM rapoarte WHERE manager_id=?";
		    Connection conectare=BazaDate.conectareBD(); 

		    if(conectare!=null){
		        try(PreparedStatement preparedStatement=conectare.prepareStatement(query)){
		            preparedStatement.setInt(1, managerId);

		            try(ResultSet resultSet=preparedStatement.executeQuery()){
		                while(resultSet.next()){
		                    RaportPerformanta raport=new RaportPerformanta(resultSet.getInt("raport_id"), resultSet.getString("comentarii"), resultSet.getInt("scor"), resultSet.getDate("data_evaluare"), resultSet.getInt("angajat_id"), managerId);
		                       		                    
		                    rapoarteGenerate.add(raport);
		                }
		            }
		        } catch(Exception e){
		            e.printStackTrace();
		            System.out.println("A apărut o eroare la obținerea rapoartelor generate.");
		        }
		    } else {
		        System.out.println("Nu s-a putut stabili conexiunea la baza de date.");
		    }

		    return rapoarteGenerate;
		}

}
