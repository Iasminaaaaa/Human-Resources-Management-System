package test;

import org.junit.jupiter.api.Test;

import model.Cont;

import static org.junit.jupiter.api.Assertions.*;

class TestCont {

    @Test
    void testConstructorCuParametrii() {
        Cont cont=new Cont("angajat@gmail.com", "parola123", "angajat");

        assertEquals("angajat@gmail.com", cont.getEmail(), "Email-ul nu este corect.");

        assertEquals("parola123", cont.getParola(), "Parola nu este corectă.");

        assertEquals("angajat", cont.getTipAngajat(), "Tipul angajatului nu este corect.");
    }
    
    @Test
    void testGetEmail() {
        Cont cont=new Cont("angajat@gmail.com", "parola123", "angajat");

        assertEquals("angajat@gmail.com", cont.getEmail(), "Email-ul nu este corect.");
    }

     @Test
    void testGetParola() {
        Cont cont=new Cont("angajat@gmail.com", "parola123", "angajat");

        assertEquals("parola123", cont.getParola(), "Parola nu este corectă.");
    }

     @Test
    void testGetTipAngajat() {
        Cont cont=new Cont("angajat@gmail.com", "parola123", "angajat");

        assertEquals("angajat", cont.getTipAngajat(), "Tipul angajatului nu este corect.");
    }
}
