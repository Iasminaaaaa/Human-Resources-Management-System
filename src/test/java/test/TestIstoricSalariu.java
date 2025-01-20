package test;

import org.junit.jupiter.api.Test;

import model.IstoricSalariu;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

class TestIstoricSalariu {

    @Test
    void testIstoricSalariu() {
       Date startDate=Date.valueOf("2023-01-01"); 
        Date endDate=Date.valueOf("2024-01-01"); 
        Double salariuAnterior=3500.50;
        String motiv="Promovare";

        IstoricSalariu istoricSalariu=new IstoricSalariu(startDate, endDate, salariuAnterior, motiv);

        assertEquals(salariuAnterior, istoricSalariu.getSalariulAnterior(), "Salariul anterior nu a fost setat corect");

        assertEquals(startDate, istoricSalariu.getStartDate(), "Data de inceput nu este corecta");
        assertEquals(endDate, istoricSalariu.getEndDate(), "Data de sfarsit nu este corecta");
        assertEquals(motiv, istoricSalariu.getMotiv(), "Motivul schimbarii nu este corect");
    }

    @Test
    void testGetSalariulAnterior() {
        Double salariuAnterior=5000.75;
        IstoricSalariu istoricSalariu=new IstoricSalariu(Date.valueOf("2023-01-01"), Date.valueOf("2024-01-01"), salariuAnterior, "Marire salariu");

        assertEquals(salariuAnterior, istoricSalariu.getSalariulAnterior(), "Metoda getSalariulAnterior nu returneaza valoarea corecta");
    }
}
