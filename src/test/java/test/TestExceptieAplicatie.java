package test;

import org.junit.jupiter.api.Test;

import model.ExceptieAplicatie;

import static org.junit.jupiter.api.Assertions.*;

class TestExceptieAplicatie {

    @Test
    void testExceptieAplicatie() {
       String mesaj = "A apărut o eroare în aplicație";
        ExceptieAplicatie exceptie = new ExceptieAplicatie(mesaj);

       assertEquals(mesaj, exceptie.getMessage(), "Mesajul excepției nu este corect.");
    }

    @Test
    void testTipExceptie() {
        ExceptieAplicatie exceptie = new ExceptieAplicatie("Eroare de aplicație");

        assertTrue(exceptie instanceof ExceptieAplicatie, "Excepția nu este de tipul ExceptieAplicatie.");
    }
}
