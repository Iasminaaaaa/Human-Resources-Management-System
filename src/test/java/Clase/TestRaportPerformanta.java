package Clase;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clase.RaportPerformanta;

class TestRaportPerformanta {

	  private RaportPerformanta raport1;
	    private RaportPerformanta raport2;
	    private RaportPerformanta raport3;

	    @BeforeEach
	    public void setUp() {
	        Date dataEvaluare1=Date.valueOf("2023-02-01"); 
	        raport1=new RaportPerformanta("R001", "Comentarii pozitive", 90, dataEvaluare1);

	        Date dataEvaluare2=Date.valueOf("2023-05-10");  
	        raport2=new RaportPerformanta("R002", "Comentarii de îmbunătățire", 75, dataEvaluare2);

	        Date dataEvaluare3=Date.valueOf("2023-09-22"); 
	        raport3=new RaportPerformanta("R003", "Comentarii excelente", 95, dataEvaluare3);
	    }

	    @Test
	    public void testGetRaportId() {
	        assertEquals("R001", raport1.getRaprtId(), "ID-ul raportului 1 ar trebui să fie R001");
	        assertEquals("R002", raport2.getRaprtId(), "ID-ul raportului 2 ar trebui să fie R002");
	        assertEquals("R003", raport3.getRaprtId(), "ID-ul raportului 3 ar trebui să fie R003");
	    }

	    @Test
	    public void testGetComentarii() {
	        assertEquals("Comentarii pozitive", raport1.getComentarii(), "Comentariile raportului 1 ar trebui să fie corecte");
	        assertEquals("Comentarii de îmbunătățire", raport2.getComentarii(), "Comentariile raportului 2 ar trebui să fie corecte");
	        assertEquals("Comentarii excelente", raport3.getComentarii(), "Comentariile raportului 3 ar trebui să fie corecte");
	    }

	    @Test
	    public void testGetScor() {
	        assertEquals(90, raport1.getScor(), "Scorul raportului 1 ar trebui să fie 90");
	        assertEquals(75, raport2.getScor(), "Scorul raportului 2 ar trebui să fie 75");
	        assertEquals(95, raport3.getScor(), "Scorul raportului 3 ar trebui să fie 95");
	    }

	    @Test
	    public void testGetDataEvaluare() {
	        Date expectedData1=Date.valueOf("2023-02-01");  
	        Date expectedData2=Date.valueOf("2023-05-10");  
	        Date expectedData3=Date.valueOf("2023-09-22");   

	        assertEquals(expectedData1, raport1.getDataEvaluare(), "Data evaluării raportului 1 ar trebui să fie 2023-02-01");
	        assertEquals(expectedData2, raport2.getDataEvaluare(), "Data evaluării raportului 2 ar trebui să fie 2023-05-10");
	        assertEquals(expectedData3, raport3.getDataEvaluare(), "Data evaluării raportului 3 ar trebui să fie 2023-09-22");
	    }

	    @Test
	    public void testSetRaportId() {
	        raport1.setRaprtId("R004");
	        assertEquals("R004", raport1.getRaprtId(), "ID-ul raportului 1 ar trebui să fie actualizat la R004");
	    }

	    @Test
	    public void testSetComentarii() {
	        raport2.setComentarii("Comentarii foarte bune");
	        assertEquals("Comentarii foarte bune", raport2.getComentarii(), "Comentariile raportului 2 ar trebui să fie actualizate");
	    }

	    @Test
	    public void testSetScor() {
	        raport3.setScor(100);
	        assertEquals(100, raport3.getScor(), "Scorul raportului 3 ar trebui să fie actualizat la 100");
	    }

	    @Test
	    public void testSetDataEvaluare() {
	        Date newDataEvaluare=Date.valueOf("2023-11-01");  
	        raport1.setDataEvaluare(newDataEvaluare);
	        assertEquals(newDataEvaluare, raport1.getDataEvaluare(), "Data evaluării raportului 1 ar trebui să fie actualizată la 2023-12-01");
	    }

	    @Test
	    public void testConstructorFaraParametrii() {
	        RaportPerformanta raportFaraParametri=new RaportPerformanta();
	        assertNull(raportFaraParametri.getRaprtId(), "ID-ul raportului ar trebui să fie null");
	        assertNull(raportFaraParametri.getComentarii(), "Comentariile raportului ar trebui să fie null");
	        assertEquals(0, raportFaraParametri.getScor(), "Scorul raportului ar trebui să fie 0");
	        assertNull(raportFaraParametri.getDataEvaluare(), "Data evaluării raportului ar trebui să fie null");
	    }

	    @Test
	    public void testToString() {
	        String expectedToString1="Raport ID: R001, Comentarii: Comentarii pozitive, Scor: 90, Data Evaluare: 2023-02-01";
	        assertEquals(expectedToString1, raport1.toString(), "Metoda toString() pentru raportul 1 nu returnează valoarea corectă");

	        String expectedToString2="Raport ID: R002, Comentarii: Comentarii de îmbunătățire, Scor: 75, Data Evaluare: 2023-05-10";
	        assertEquals(expectedToString2, raport2.toString(), "Metoda toString() pentru raportul 2 nu returnează valoarea corectă");

	        String expectedToString3="Raport ID: R003, Comentarii: Comentarii excelente, Scor: 95, Data Evaluare: 2023-09-22";
	        assertEquals(expectedToString3, raport3.toString(), "Metoda toString() pentru raportul 3 nu returnează valoarea corectă");
       }

}
