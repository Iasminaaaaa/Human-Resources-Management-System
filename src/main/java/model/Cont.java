package model;
 
public class Cont {
    private String email;
    private String parola;
    private String tipAngajat;

    public Cont() {}
    
    public Cont(String email, String parola, String tipAngajat) {
        this.email=email;
        this.parola=parola;
        this.tipAngajat=tipAngajat;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    public String getTipAngajat() {
        return tipAngajat;
    }

}

