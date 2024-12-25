package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Cerere;
import model.ExceptieAplicatie;

class TestCerere {
	private Cerere cerere1;
	private Cerere cerere2;
	private Cerere cerere3;
	    
    @BeforeEach
	    public void setUp() throws ExceptieAplicatie {
	        Date dataInceput1=Date.valueOf("2023-01-01");
	        Date dataSfarsit1=Date.valueOf("2023-01-10");
	        cerere1=new Cerere("C001", "Comentarii manager", "Acceptat", dataInceput1, dataSfarsit1, Cerere.tipCerere.Concediu);
	        
	        Date dataInceput2=Date.valueOf("2023-02-01");
	        Date dataSfarsit2=Date.valueOf("2023-02-05");
	        cerere2=new Cerere("C002", "Comentarii pentru respingere", "Respins", dataInceput2, dataSfarsit2, Cerere.tipCerere.Invoire);
	        
	        Date dataInceput3=Date.valueOf("2022-12-01");
	        Date dataSfarsit3=Date.valueOf("2022-12-10");
	        cerere3=new Cerere("C003", "Comentarii manager", "Acceptat", dataInceput3, dataSfarsit3, Cerere.tipCerere.Demisie);
	    }

	    @Test
	    public void testGetCerereId() {
	        assertEquals("C001", cerere1.getCerereId(), "ID-ul cererii 1 ar trebui să fie C001.");
	        assertEquals("C002", cerere2.getCerereId(), "ID-ul cererii 2 ar trebui să fie C002.");
	        assertEquals("C003", cerere3.getCerereId(), "ID-ul cererii 3 ar trebui să fie C003.");
	    }

	    @Test
	    public void testGetComentariiManager() {
	        assertEquals("Comentarii manager", cerere1.getComentariiManager(), "Comentariile managerului pentru cererea 1 ar trebui să fie corecte.");
	        assertEquals("Comentarii pentru respingere", cerere2.getComentariiManager(), "Comentariile managerului pentru cererea 2 ar trebui să fie corecte.");
	        assertEquals("Comentarii manager", cerere3.getComentariiManager(), "Comentariile managerului pentru cererea 3 ar trebui să fie corecte.");
	    }

	    @Test
	    public void testGetStatus() {
	        assertEquals("Acceptat", cerere1.getStatus(), "Statusul cererii 1 ar trebui să fie Acceptat.");
	        assertEquals("Respins", cerere2.getStatus(), "Statusul cererii 2 ar trebui să fie Respins.");
	        assertEquals("Acceptat", cerere3.getStatus(), "Statusul cererii 3 ar trebui să fie Acceptat.");
	    }

	    @Test
	    public void testGetDataInceput() {
	        assertEquals(Date.valueOf("2023-01-01"), cerere1.getDataInceput(), "Data de început pentru cererea 1 ar trebui să fie 2023-01-01.");
	        assertEquals(Date.valueOf("2023-02-01"), cerere2.getDataInceput(), "Data de început pentru cererea 2 ar trebui să fie 2023-02-01.");
	        assertEquals(Date.valueOf("2022-12-01"), cerere3.getDataInceput(), "Data de început pentru cererea 3 ar trebui să fie 2022-12-01.");
	    }

	    @Test
	    public void testGetDataSfarsit() {
	        assertEquals(Date.valueOf("2023-01-10"), cerere1.getDataSfarsit(), "Data de sfârșit pentru cererea 1 ar trebui să fie 2023-01-10.");
	        assertEquals(Date.valueOf("2023-02-05"), cerere2.getDataSfarsit(), "Data de sfârșit pentru cererea 2 ar trebui să fie 2023-02-05.");
	        assertEquals(Date.valueOf("2022-12-10"), cerere3.getDataSfarsit(), "Data de sfârșit pentru cererea 3 ar trebui să fie 2022-12-10.");
	    }

	    @Test
	    public void testGetTipCerere() {
	        assertEquals(Cerere.tipCerere.Concediu, cerere1.getTipCerere(), "Tipul cererii 1 ar trebui să fie Concediu.");
	        assertEquals(Cerere.tipCerere.Invoire, cerere2.getTipCerere(), "Tipul cererii 2 ar trebui să fie Invoire.");
	        assertEquals(Cerere.tipCerere.Demisie, cerere3.getTipCerere(), "Tipul cererii 3 ar trebui să fie Demisie.");
	    }

	    @Test
	    public void testToString() {
	        String expectedToString1="Cerere ID: C001, Comentarii Manager: Comentarii manager, Status: Acceptat, Data Inceput: 2023-01-01, Data Sfarsit: 2023-01-10, Tip Cerere: Concediu";
	        assertEquals(expectedToString1, cerere1.toString(), "Metoda toString() pentru cererea 1 nu returnează valoarea corectă.");

	        String expectedToString2="Cerere ID: C002, Comentarii Manager: Comentarii pentru respingere, Status: Respins, Data Inceput: 2023-02-01, Data Sfarsit: 2023-02-05, Tip Cerere: Invoire";
	        assertEquals(expectedToString2, cerere2.toString(), "Metoda toString() pentru cererea 2 nu returnează valoarea corectă.");

	        String expectedToString3="Cerere ID: C003, Comentarii Manager: Comentarii manager, Status: Acceptat, Data Inceput: 2022-12-01, Data Sfarsit: 2022-12-10, Tip Cerere: Demisie";
	        assertEquals(expectedToString3, cerere3.toString(), "Metoda toString() pentru cererea 3 nu returnează valoarea corectă.");
	    }
}

