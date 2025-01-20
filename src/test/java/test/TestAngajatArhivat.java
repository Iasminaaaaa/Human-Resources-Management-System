package test;

import org.junit.jupiter.api.Test;

import model.AngajatArhivat;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

public class TestAngajatArhivat {

    @Test
    public void testConstructorFaraParametrii() {
        AngajatArhivat angajat=new AngajatArhivat();
        assertNotNull(angajat, "Constructorul fără parametri ar trebui să creeze un obiect valid.");
    }

    @Test
    public void testConstructorCuParametrii() {
        int id=1;
        String nume="Ion Popescu";
        String job="Software Developer";
        String email="ion.popescu@gmail.com";
        String adresa="Str. Libertății, Nr. 10, București";
        double salariu=5000.0;
        String nrTelefon="0723456789";
        Date dataAngajare=Date.valueOf("2020-01-15");
        int managerId=100;
        Date dataDemisie=Date.valueOf("2023-12-31");

        AngajatArhivat angajat=new AngajatArhivat(id, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId, dataDemisie);

        assertEquals(dataAngajare, angajat.getData(), "Data angajarii ar trebui să fie setată corect");
        assertEquals(id, angajat.getId(), "ID-ul angajatului ar trebui să fie setat corect");
        assertEquals(nume, angajat.getNume(), "Numele angajatului ar trebui să fie setat corect");
        assertEquals(job, angajat.getJob(), "Poziția angajatului ar trebui să fie setata corect");
        assertEquals(email, angajat.getEmail(), "Email-ul ar trebui să fie setat corect");
        assertEquals(adresa, angajat.getAdresa(), "Adresa ar trebui să fie setata corect");
        assertEquals(salariu, angajat.getSalariu(), "Salariul ar trebui să fie setat corect");
        assertEquals(nrTelefon, angajat.getNrTelefon(), "Numarul de telefon ar trebui să fie setat corect");
        assertEquals(managerId, angajat.getManagerId(), "ID-ul managerului ar trebui să fie setat corect");
        assertEquals(dataDemisie, angajat.getDataDemisie(), "Data demisiei ar trebui să fie setata corect");
    }

    @Test
    public void testGetDataDemisie() {
        Date dataDemisie=Date.valueOf("2023-12-31");
        AngajatArhivat angajat=new AngajatArhivat();
        
        angajat=new AngajatArhivat(1, "Maria Popescu", "Tester", "maria.popescu@gmail.com", "Str. Păcii, Nr. 3, Cluj-Napoca", 4000.0, "0741234567", Date.valueOf("2019-05-01"), 101, dataDemisie);
        
        assertEquals(dataDemisie, angajat.getDataDemisie(), "Metoda getDataDemisie ar trebui să returneze data corectă.");
    }

    @Test
    public void testToString() {
        int id=2;
        String nume="Maria Ionescu";
        String job="Project Manager";
        String email="maria.ionescu@gmail.com";
        String adresa="Str. Unirii, Nr. 5, Timișoara";
        double salariu=8000.0;
        String nrTelefon="0734567890";
        Date dataAngajare=Date.valueOf("2018-05-10");
        int managerId=101;
        Date dataDemisie=Date.valueOf("2022-06-30");

        AngajatArhivat angajat=new AngajatArhivat(id, nume, job, email, adresa, salariu, nrTelefon, dataAngajare, managerId, dataDemisie);

        String rezultatAsteptat="ID: "+id+", Nume: "+nume+", Poziție: "+job+", Salariu: " + salariu + ", Data Angajare: " + dataAngajare +", Număr Telefon: " + nrTelefon + ", Email: " + email +", Adresă: " + adresa + ", Manager ID: " + managerId+", Data Demisie: "+dataDemisie;
        
        assertEquals(rezultatAsteptat, angajat.toString(), "Metoda toString ar trebui să returneze string-ul corect.");
         
    }
}
