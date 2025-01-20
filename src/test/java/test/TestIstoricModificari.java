package test;

import org.junit.jupiter.api.Test;

import model.IstoricModificari;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

class TestIstoricModificari {

    @Test
    void testIstoricModificari() {
        Date startDate=Date.valueOf("2023-01-01");
        Date endDate=Date.valueOf("2023-12-31"); 
        String motiv="Schimbare de departament";

        IstoricModificari istoricModificari=new IstoricModificari(startDate, endDate, motiv);

        assertEquals(startDate, istoricModificari.getStartDate(), "Data de inceput nu este corecta");
        assertEquals(endDate, istoricModificari.getEndDate(), "Data de sfarșit nu este corecta");
        assertEquals(motiv, istoricModificari.getMotiv(), "Motivul modificarii nu este corect");
    }

    @Test
    void testGetStartDate() {
        Date startDate=Date.valueOf("2023-01-01");
        IstoricModificari istoricModificari=new IstoricModificari(startDate, Date.valueOf("2023-12-31"), "Transfer");
        
        assertEquals(startDate, istoricModificari.getStartDate(), "Metoda getStartDate nu returneaza valoarea corecta");
    }

    @Test
    void testGetEndDate() {
        Date endDate=Date.valueOf("2023-12-31");
        IstoricModificari istoricModificari=new IstoricModificari(Date.valueOf("2023-01-01"), endDate, "Schimbare de salariu");
        
         assertEquals(endDate, istoricModificari.getEndDate(), "Metoda getEndDate nu returneaza valoarea corecta");
    }

    @Test
    void testGetMotiv() {
        String motiv="Promovare";
        IstoricModificari istoricModificari=new IstoricModificari(Date.valueOf("2023-01-01"), Date.valueOf("2023-12-31"), motiv);

        assertEquals(motiv, istoricModificari.getMotiv(), "Metoda getMotiv nu returneaza valoarea corecta");
    }
}
