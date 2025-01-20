package model;
 
/**
 * Clasa Cont reprezintă un cont de utilizator asociat cu un angajat
 * Aceasta conține informații precum email-ul, parola și tipul angajatului
 */
public class Cont {
    private String email;
    private String parola;
    private String tipAngajat;

    /**
     * Constructor fără parametrii pentru clasa Cont
     */
    public Cont() {}
    
    /**
     * Constructor cu parametrii pentru clasa Cont.
     * @param email Email-ul asociat contului.
     * @param parola Parola asociată contului.
     * @param tipAngajat Tipul angajatului (de exemplu, admin, angajat).
     */
    public Cont(String email, String parola, String tipAngajat) {
        this.email=email;
        this.parola=parola;
        this.tipAngajat=tipAngajat;
    }

    /**
     * Metoda getEmail() nu are parametri.
     * @return Email-ul asociat contului.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metoda getParola() nu are parametri.
     * @return Parola asociată contului.
     */
    public String getParola() {
        return parola;
    }

    /**
     * Metoda getTipAngajat() nu are parametri.
     * @return Tipul angajatului (administrator, employee, manager)
     */
    public String getTipAngajat() {
        return tipAngajat;
    }

}

