package test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.RaportPerformanta;

class TestRaportPerformanta {

    private RaportPerformanta raport1;
    private RaportPerformanta raport2;
    private RaportPerformanta raport3;

    @BeforeEach
    public void setUp() {
        raport1=new RaportPerformanta(1, "Comentarii pozitive", 90, Date.valueOf("2023-02-01"), 101);
        raport2=new RaportPerformanta(2, "Comentarii de îmbunătățire", 75, Date.valueOf("2023-05-10"), 102);
        raport3=new RaportPerformanta(3, "Comentarii excelente", 95, Date.valueOf("2023-09-22"), 103);
    }

    @Test
    public void testGetRaportId() {
       assertEquals(1, raport1.getRaprtId(), "ID-ul raportului 1 ar trebui să fie 1");
       assertEquals(2, raport2.getRaprtId(), "ID-ul raportului 2 ar trebui să fie 2");
       assertEquals(3, raport3.getRaprtId(), "ID-ul raportului 3 ar trebui să fie 3");
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
        Date Data1=Date.valueOf("2023-02-01");
        Date Data2=Date.valueOf("2023-05-10");
        Date Data3=Date.valueOf("2023-09-22");

        assertEquals(Data1, raport1.getDataEvaluare(), "Data evaluării raportului 1 ar trebui să fie 2023-02-01");
        assertEquals(Data2, raport2.getDataEvaluare(), "Data evaluării raportului 2 ar trebui să fie 2023-05-10");
        assertEquals(Data3, raport3.getDataEvaluare(), "Data evaluării raportului 3 ar trebui să fie 2023-09-22");
    }

    @Test
    public void testSetRaportId() {
        raport1.setRaprtId(4);
        assertEquals(4, raport1.getRaprtId(), "ID-ul raportului 1 ar trebui să fie actualizat la 4");
    
        raport2.setRaprtId(6);
        assertEquals(6, raport2.getRaprtId(), "ID-ul raportului 2 ar trebui să fie actualizat la 6");
    
        raport3.setRaprtId(8);
        assertEquals(8, raport3.getRaprtId(), "ID-ul raportului 3 ar trebui să fie actualizat la 8");
    }

    @Test
    public void testSetComentarii() {
    	raport1.setComentarii("Comentarii foarte bune");
        assertEquals("Comentarii foarte bune", raport1.getComentarii(), "Comentariile raportului 1 ar trebui să fie actualizate");
  
    	raport2.setComentarii("Comentarii foarte rele");
        assertEquals("Comentarii foarte rele", raport2.getComentarii(), "Comentariile raportului 2 ar trebui să fie actualizate");
        
        raport3.setComentarii("Comentarii indoielnice");
        assertEquals("Comentarii indoielnice", raport3.getComentarii(), "Comentariile raportului 3 ar trebui să fie actualizate");
    }

    @Test
    public void testSetScor() {
        raport1.setScor(100);
        assertEquals(100, raport1.getScor(), "Scorul raportului 1 ar trebui să fie actualizat la 100");
    
        raport2.setScor(10);
        assertEquals(10, raport2.getScor(), "Scorul raportului 2 ar trebui să fie actualizat la 100");
    
        raport3.setScor(40);
        assertEquals(40, raport3.getScor(), "Scorul raportului 3 ar trebui să fie actualizat la 100");
    
    }

    @Test
    public void testSetDataEvaluare() {
    	    Date newDataEvaluare1=Date.valueOf("2023-11-01");
    	    Date newDataEvaluare2=Date.valueOf("2023-12-12");
    	    Date newDataEvaluare3=Date.valueOf("2023-10-10");

    	    raport1.setDataEvaluare(newDataEvaluare1);
    	    raport2.setDataEvaluare(newDataEvaluare2);
    	    raport3.setDataEvaluare(newDataEvaluare3);

    	    assertEquals(newDataEvaluare1, raport1.getDataEvaluare(), "Data evaluării(raport1) ar trebui să fie 2023-11-01");
    	    assertEquals(newDataEvaluare2, raport2.getDataEvaluare(), "Data evaluării(raport2) trebui să fie la 2023-11-01");
    	    assertEquals(newDataEvaluare3, raport3.getDataEvaluare(), "Data evaluării(raport3) ar trebui să fie 2023-11-01");
    }

    @Test
    public void testToString() {
        String ToString1="Raport ID: 1, Comentarii: Comentarii pozitive, Scor: 90, Data Evaluare: 2023-02-01";
        assertEquals(ToString1, raport1.toString(), "Metoda toString() pentru raportul 1 nu returnează valoarea corectă");

        String ToString2="Raport ID: 2, Comentarii: Comentarii de îmbunătățire, Scor: 75, Data Evaluare: 2023-05-10";
        assertEquals(ToString2, raport2.toString(), "Metoda toString() pentru raportul 2 nu returnează valoarea corectă");

        String ToString3="Raport ID: 3, Comentarii: Comentarii excelente, Scor: 95, Data Evaluare: 2023-09-22";
        assertEquals(ToString3, raport3.toString(), "Metoda toString() pentru raportul 3 nu returnează valoarea corectă");
    }
}
