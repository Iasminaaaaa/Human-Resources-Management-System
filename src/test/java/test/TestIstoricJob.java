package test;

import org.junit.jupiter.api.Test;

import model.IstoricJob;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
class TestIstoricJob {

    @Test
    void testIstoricJob() {
        Date startDate=Date.valueOf("2023-01-01"); 
        Date endDate=Date.valueOf("2024-01-01");
        String pozitiaAnterioara="Junior Developer";
        String motiv="Promovare";

        IstoricJob istoricJob=new IstoricJob(startDate, endDate, pozitiaAnterioara, motiv);

        assertEquals(pozitiaAnterioara, istoricJob.getPozitiaAnterioara(), "Pozitia anterioara nu a fost setata corect.");

        assertEquals(startDate, istoricJob.getStartDate(), "Data de început nu este corecta");
        assertEquals(endDate, istoricJob.getEndDate(), "Data de sfarsit nu este corecta");
        assertEquals(motiv, istoricJob.getMotiv(), "Motivul schimbarii nu este corect");
    }

     @Test
    void testGetPozitiaAnterioara() {
         String pozitiaAnterioara="Software Engineer";
        IstoricJob istoricJob=new IstoricJob(Date.valueOf("2023-01-01"), Date.valueOf("2024-01-01"), pozitiaAnterioara, "Transfer");

       assertEquals(pozitiaAnterioara, istoricJob.getPozitiaAnterioara(), "Metoda getPozitiaAnterioara nu returnează valoarea corectă.");
    }
}
