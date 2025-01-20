package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Cerere;
import model.Cerere.TipCerere;
import model.ExceptieAplicatie;

class TestCerere {

    private Cerere cerere1;
    private Cerere cerere2;
    private Cerere cerere3;

    @BeforeEach
    public void verifConstructor() throws ExceptieAplicatie {
        cerere1=new Cerere(1, 101, "Accepted", Date.valueOf("2023-01-01"), Date.valueOf("2023-01-10"), TipCerere.Vacation, 201);
        cerere2=new Cerere(2, 102, "Pending", Date.valueOf("2023-02-15"), Date.valueOf("2023-02-20"), TipCerere.Leave, 202);
        cerere3=new Cerere(3, 103, "Declined", Date.valueOf("2023-03-01"), Date.valueOf("2023-03-05"), TipCerere.Resignation, 203);
    }

    @Test
    public void testGetCerereId() {
        assertEquals(1, cerere1.getCerereId(), "ID-ul cererii 1 ar trebui să fie 1.");
        assertEquals(2, cerere2.getCerereId(), "ID-ul cererii 2 ar trebui să fie 2.");
        assertEquals(3, cerere3.getCerereId(), "ID-ul cererii 3 ar trebui să fie 3.");
    }

    @Test
    public void testGetAngajatId() {
        assertEquals(101, cerere1.getAngajatId(), "ID-ul angajatului pentru cererea 1 ar trebui să fie 101.");
        assertEquals(102, cerere2.getAngajatId(), "ID-ul angajatului pentru cererea 2 ar trebui să fie 102.");
        assertEquals(103, cerere3.getAngajatId(), "ID-ul angajatului pentru cererea 3 ar trebui să fie 103.");
    }

    @Test
    public void testGetManagerId() {
        assertEquals(201, cerere1.getManagerId(), "ID-ul managerului pentru cererea 1 ar trebui să fie 201.");
        assertEquals(202, cerere2.getManagerId(), "ID-ul managerului pentru cererea 2 ar trebui să fie 202.");
        assertEquals(203, cerere3.getManagerId(), "ID-ul managerului pentru cererea 3 ar trebui să fie 203.");
    }

    @Test
    public void testGetStatus() {
        assertEquals("Accepted", cerere1.getStatus(), "Statusul cererii 1 ar trebui să fie Accepted.");
        assertEquals("Pending", cerere2.getStatus(), "Statusul cererii 2 ar trebui să fie Pending.");
        assertEquals("Declined", cerere3.getStatus(), "Statusul cererii 3 ar trebui să fie Declined.");
    }

    @Test
    public void testGetDataInceput() {
        assertEquals(Date.valueOf("2023-01-01"), cerere1.getDataInceput(), "Data de început a cererii 1 ar trebui să fie 2023-01-01.");
        assertEquals(Date.valueOf("2023-02-15"), cerere2.getDataInceput(), "Data de început a cererii 2 ar trebui să fie 2023-02-15.");
        assertEquals(Date.valueOf("2023-03-01"), cerere3.getDataInceput(), "Data de început a cererii 3 ar trebui să fie 2023-03-01.");
    }

    @Test
    public void testGetDataSfarsit() {
        assertEquals(Date.valueOf("2023-01-10"), cerere1.getDataSfarsit(), "Data de sfârșit a cererii 1 ar trebui să fie 2023-01-10.");
        assertEquals(Date.valueOf("2023-02-20"), cerere2.getDataSfarsit(), "Data de sfârșit a cererii 2 ar trebui să fie 2023-02-20.");
        assertEquals(Date.valueOf("2023-03-05"), cerere3.getDataSfarsit(), "Data de sfârșit a cererii 3 ar trebui să fie 2023-03-05.");
    }

    @Test
    public void testGetTipCerere() {
        assertEquals(TipCerere.Vacation, cerere1.getTipCerere(), "Tipul cererii 1 ar trebui să fie Vacation.");
        assertEquals(TipCerere.Leave, cerere2.getTipCerere(), "Tipul cererii 2 ar trebui să fie Leave.");
        assertEquals(TipCerere.Resignation, cerere3.getTipCerere(), "Tipul cererii 3 ar trebui să fie Resignation.");
    }

    @Test
    public void testToString() {
        String expectedToString1="Cerere ID: 1, Status: Accepted, Data Inceput: 2023-01-01, Data Sfarsit: 2023-01-10, Tip Cerere: Vacation";
        assertEquals(expectedToString1, cerere1.toString(), "Metoda toString() pentru cererea 1 nu returnează valoarea corectă.");

        String expectedToString2="Cerere ID: 2, Status: Pending, Data Inceput: 2023-02-15, Data Sfarsit: 2023-02-20, Tip Cerere: Leave";
        assertEquals(expectedToString2, cerere2.toString(), "Metoda toString() pentru cererea 2 nu returnează valoarea corectă.");

        String expectedToString3="Cerere ID: 3, Status: Declined, Data Inceput: 2023-03-01, Data Sfarsit: 2023-03-05, Tip Cerere: Resignation";
        assertEquals(expectedToString3, cerere3.toString(), "Metoda toString() pentru cererea 3 nu returnează valoarea corectă.");
    }

    @Test
    public void testConstructorCuExceptie() {
        Exception exceptie=assertThrows(ExceptieAplicatie.class, () -> {
            new Cerere(4, 104, "InvalidStatus", Date.valueOf("2023-04-01"), Date.valueOf("2023-04-05"), TipCerere.Leave, 204);
        });
        assertEquals("Statusul poate fi doar Accepted sau Declined!", exceptie.getMessage(), "Constructorul nu aruncă excepția corectă pentru un status invalid.");
    }
}
