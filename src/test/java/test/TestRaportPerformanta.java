package test;

import org.junit.jupiter.api.Test;

import model.RaportPerformanta;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class TestRaportPerformanta {

    @Test
    public void testConstructorFaraParametrii() {
        RaportPerformanta raport=new RaportPerformanta();
        assertNotNull(raport, "Testul la constructorul implicit a reusit.");
    }

    @Test
    public void testConstructorCuParametrii() {
        int raportId=1;
        String comentarii="Performanta.";
        int scor=90;
        Date dataEvaluare=Date.valueOf("2023-12-31");
        int angajatId=101;
        int managerId=201;

        RaportPerformanta raport=new RaportPerformanta(raportId, comentarii, scor, dataEvaluare, angajatId, managerId);

        assertEquals(raportId, raport.getRaprtId(), "ID-ul raportului ar trebui sa corespunda celui furnizat");
        assertEquals(comentarii, raport.getComentarii(), "Comentariile ar trebui sa corespunda celor furnizate");
        assertEquals(scor, raport.getScor(), "Scorul ar trebui sa corespunda celui furnizat");
        assertEquals(dataEvaluare, raport.getDataEvaluare(), "Data evaluarii ar trebui sa corespunda celei furnizate");
        assertEquals(angajatId, raport.getId(), "ID-ul angajatului ar trebui sa corespunda celui furnizat");
        assertEquals(managerId, raport.getManId(), "ID-ul managerului ar trebui sa corespunda celui furnizat");
    }

    @Test
    public void testSettereSiGettere() {
        RaportPerformanta raport=new RaportPerformanta();

        int raportId=2;
        String comentarii="Decent";
        int scor=85;
        Date dataEvaluare=Date.valueOf("2024-01-01");

        raport.setRaprtId(raportId);
        raport.setComentarii(comentarii);
        raport.setScor(scor);
        raport.setDataEvaluare(dataEvaluare);

        assertEquals(raportId, raport.getRaprtId(), "ID-ul raportului ar trebui sa corespunda valorii setate");
        assertEquals(comentarii, raport.getComentarii(), "Comentariile ar trebui sa corespunda valorii setate");
        assertEquals(scor, raport.getScor(), "Scorul ar trebui sa corespunda valorii setate");
        assertEquals(dataEvaluare, raport.getDataEvaluare(), "Data evaluarii ar trebui sa corespunda valorii setate");
    }

    @Test
    public void testToString() {
        int raportId=3;
        String comentarii="Satisfacator";
        int scor=75;
        Date dataEvaluare=Date.valueOf("2024-06-01");
        int angajatId=103;
        int managerId=203;

        RaportPerformanta raport=new RaportPerformanta(raportId, comentarii, scor, dataEvaluare, angajatId, managerId);

        String rezultatAsteptat="Raport ID: "+raportId+", Comentarii: "+comentarii+", Scor: "+scor+", Data Evaluare: "+dataEvaluare;
        assertEquals(rezultatAsteptat, raport.toString(), "Metoda toString ar trebui sa returneze reprezentarea corecta a sirului");
    }

}
