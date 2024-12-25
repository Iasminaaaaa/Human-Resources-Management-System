package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Angajat;

class TestAngajat {

    private Angajat angajat1;
    private Angajat angajat2;
    private Angajat angajat3;
    
    @BeforeEach
    public void verifConstructor() {
        angajat1=new Angajat(1, "Ion Popescu", "Programator", "ion.popescu@gmail.com", "Strada Lalelelor 10", 5000, "0723123456", Date.valueOf("2023-01-01"), 100);
     
        angajat2=new Angajat(2, "Maria Ionescu", "Designer", "maria.ionescu@gmail.com", "Strada Florilor 15", 4000, "0723987654", Date.valueOf("2023-02-15"), 101);
        
        angajat3=new Angajat(3, "George Popa", "Analist", "george.popa@gmail.com", "Bulevardul Victoriei 50", 6000, "0745123456", Date.valueOf("2022-12-05"), 102);
    }

    @Test
    public void testGetId() {
        assertEquals(1, angajat1.getId(), "ID-ul angj 1 ar trebui să fie 1.");
        assertEquals(2, angajat2.getId(), "ID-ul angj 2 ar trebui să fie 2.");
        assertEquals(3, angajat3.getId(), "ID-ul angj 3 ar trebui să fie 3.");
    }

    @Test
    public void testGetNume() {
        assertEquals("Ion Popescu", angajat1.getNume(), "Numele angj 1 ar trebui să fie Ion Popescu.");
        assertEquals("Maria Ionescu", angajat2.getNume(), "Numele angj 2 ar trebui să fie Maria Ionescu.");
        assertEquals("George Popa", angajat3.getNume(), "Numele angj 3 ar trebui să fie George Popa.");
    }

    @Test
    public void testGetJob() {
        assertEquals("Programator", angajat1.getJob(), "Job-ul angj 1 ar trebui să fie Programator.");
        assertEquals("Designer", angajat2.getJob(), "Job-ul angj 2 ar trebui să fie Designer.");
        assertEquals("Analist", angajat3.getJob(), "Job-ul angj 3 ar trebui să fie Analist.");
    }

    @Test
    public void testGetEmail() {
        assertEquals("ion.popescu@gmail.com", angajat1.getEmail(), "Email-ul angj 1 ar trebui să fie ion.popescu@gmail.com.");
        assertEquals("maria.ionescu@gmail.com", angajat2.getEmail(), "Email-ul angj 2 ar trebui să fie maria.ionescu@gmail.com.");
        assertEquals("george.popa@gmail.com", angajat3.getEmail(), "Email-ul angj 3 ar trebui să fie george.popa@gmail.com.");
    }

    @Test
    public void testGetAdresa() {
        assertEquals("Strada Lalelelor 10", angajat1.getAdresa(), "Adresa angj 1 ar trebui să fie Strada Lalelelor 10.");
        assertEquals("Strada Florilor 15", angajat2.getAdresa(), "Adresa angj 2 ar trebui să fie Strada Florilor 15.");
        assertEquals("Bulevardul Victoriei 50", angajat3.getAdresa(), "Adresa angj 3 ar trebui să fie Bulevardul Victoriei 50.");
    }

    @Test
    public void testGetSalariu() {
        assertEquals(5000, angajat1.getSalariu(), "Salariul angj 1 ar trebui să fie 5000.");
        assertEquals(4000, angajat2.getSalariu(), "Salariul angj 2 ar trebui să fie 4000.");
        assertEquals(6000, angajat3.getSalariu(), "Salariul angj 3 ar trebui să fie 6000.");
    }

    @Test
    public void testGetNrTelefon() {
        assertEquals("0723123456", angajat1.getNrTelefon(), "Nr de telefon al angj 1 ar trebui să fie 0723123456");
        assertEquals("0723987654", angajat2.getNrTelefon(), "Nr de telefon al angj 2 ar trebui să fie 0723987654");
        assertEquals("0745123456", angajat3.getNrTelefon(), "Nr de telefon al angj 3 ar trebui să fie 0745123456");
    }

    @Test
    public void testGetData() {
        assertEquals(Date.valueOf("2023-01-01"), angajat1.getData(), "Data pt angj 1 ar trebui să fie 2023-01-01");
        assertEquals(Date.valueOf("2023-02-15"), angajat2.getData(), "Data pt angj 2 ar trebui să fie 2023-02-15");
        assertEquals(Date.valueOf("2022-12-05"), angajat3.getData(), "Data pt angj 3 ar trebui să fie 2022-12-05");
    }

    @Test
    public void testGetManagerId() {
        assertEquals(100, angajat1.getManagerId(), "ID-ul managerului angj 1 ar trebui să fie 100");
        assertEquals(101, angajat2.getManagerId(), "ID-ul managerului angj 2 ar trebui să fie 101");
        assertEquals(102, angajat3.getManagerId(), "ID-ul managerului angj 3 ar trebui să fie 102");
    }

    @Test
    public void testToString() {
        String expectedToString1="ID: 1, Nume: Ion Popescu, Poziție: Programator, Salariu: 5000, Data Angajare: 2023-01-01, Număr Telefon: 0723123456, Email: ion.popescu@gmail.com, Adresă: Strada Lalelelor 10, Manager ID: 100";
        assertEquals(expectedToString1, angajat1.toString(), "Metoda toString() pentru angajatul 1 nu returnează valoarea corectă.");
        
        String expectedToString2="ID: 2, Nume: Maria Ionescu, Poziție: Designer, Salariu: 4000, Data Angajare: 2023-02-15, Număr Telefon: 0723987654, Email: maria.ionescu@gmail.com, Adresă: Strada Florilor 15, Manager ID: 101";
        assertEquals(expectedToString2, angajat2.toString(), "Metoda toString() pentru angajatul 2 nu returnează valoarea corectă.");
        
        String expectedToString3="ID: 3, Nume: George Popa, Poziție: Analist, Salariu: 6000, Data Angajare: 2022-12-05, Număr Telefon: 0745123456, Email: george.popa@gmail.com, Adresă: Bulevardul Victoriei 50, Manager ID: 102";
        assertEquals(expectedToString3, angajat3.toString(), "Metoda toString() pentru angajatul 3 nu returnează valoarea corectă.");
    }
}
