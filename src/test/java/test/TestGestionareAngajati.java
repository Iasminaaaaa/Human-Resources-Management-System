package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GestionareAngajati;
import model.Angajat;
import model.AngajatArhivat;
import model.IstoricJob;
import model.IstoricManager;
import model.IstoricSalariu;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

class TestGestionareAngajati {

    private static String url="jdbc:mysql://localhost:3306/mydb";
    private static String username="root";
    private static String password="";
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection=DriverManager.getConnection(url, username, password);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testInfoAngajati() throws SQLException {
      List<Angajat> angajati=GestionareAngajati.infoAngajati();

		assertNotNull(angajati, "Lista de angajați nu ar trebui să fie null.");
		assertTrue(angajati.size() > 0, "Lista de angajați ar trebui să conțină cel puțin un angajat.");
    }

   
    @Test
    void testInfoAngajatiArhivati() throws SQLException {
       List<AngajatArhivat> angajatiArhivati=GestionareAngajati.infoAngajatiArhivati();

		assertNotNull(angajatiArhivati, "Lista de angajați arhivați nu ar trebui să fie null.");
		assertTrue(angajatiArhivati.size() > 0, "Lista de angajați arhivați ar trebui să conțină cel puțin un angajat.");
    }

    @Test
    void testVizualizareIstoricSalariu() throws SQLException {
        int angajatId=1; 

      List<IstoricSalariu> istoricSalariu=GestionareAngajati.vizualizareIstoricSalariu(angajatId);

		assertNotNull(istoricSalariu, "Lista de istorice salarii nu ar trebui să fie null.");
		assertTrue(istoricSalariu.size() > 0, "Lista de istorice salarii ar trebui să conțină cel puțin un element.");
    }

    @Test
    void testVizualizareIstoricJob() throws SQLException {
        int angajatId=1; 

       List<IstoricJob> istoricJob=GestionareAngajati.vizualizareIstoricJob(angajatId);

		assertEquals(istoricJob.size(), 0, "Lista de istorice joburi ar trebui să fie goala.");
	 }

    @Test
    void testVizualizareIstoricManager() throws SQLException {
        int angajatId=1; 

        
		List<IstoricManager> istoricManager=GestionareAngajati.vizualizareIstoricManager(angajatId);

		
		assertEquals(istoricManager.size(), 0,  "Lista de istorice manageri ar trebui să fie goala.");
	 }

    @Test
    void testGetManagerId() throws SQLException {
        int angajatId=1; 

      	int managerId=GestionareAngajati.getManagerId(angajatId);
		assertNotEquals(3, managerId, "ID-ul managerului nu ar trebui să fie 3.");
    }

    @Test
    void testInfoEchipa() throws SQLException {
        int managerId=1; 

      List<Angajat> echipa=GestionareAngajati.infoEchipa(managerId);

	assertEquals(echipa.size(), 0, "Lista de angajați din echipă ar trebui să fie goala");
    }
    
   
}
