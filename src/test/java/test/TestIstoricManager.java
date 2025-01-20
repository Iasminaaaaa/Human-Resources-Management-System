package test;

import org.junit.jupiter.api.Test;

import model.IstoricManager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

class TestIstoricManager {

    @Test
    void testIstoricManager() {
       Date startDate=Date.valueOf("2023-01-01"); 
        Date endDate=Date.valueOf("2024-01-01"); 
        String managerAnterior="Ion Popescu";
        String motiv="Transfer departament";

        IstoricManager istoricManager=new IstoricManager(startDate, endDate, managerAnterior, motiv);

       assertEquals(managerAnterior, istoricManager.getManagerAnterior(), "Managerul anterior nu a fost setat corect");

        assertEquals(startDate, istoricManager.getStartDate(), "Data de inceput nu este corecta");
        assertEquals(endDate, istoricManager.getEndDate(), "Data de sfarsit nu este corecta");
        assertEquals(motiv, istoricManager.getMotiv(), "Motivul schimbarii nu este corect");
    }

    @Test
    void testGetManagerAnterior() {
        String managerAnterior="Maria Ionescu";
        IstoricManager istoricManager=new IstoricManager(Date.valueOf("2023-01-01"), Date.valueOf("2024-01-01"), managerAnterior, "Mutare");

       assertEquals(managerAnterior, istoricManager.getManagerAnterior(), "Metoda getManagerAnterior nu returneaza valoarea corecta");
    }
}
