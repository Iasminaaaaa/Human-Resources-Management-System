package main;

import java.sql.Date;

import baza_de_date.BazaDate;
import clase.Angajat;

;public class Main {

    public static void main(String[] args) {
    	BazaDate.creareTabelAngajati();
    	//new InterfataAplicatie1();
        BazaDate.vizualizareInfoAngajati(BazaDate.infoAngajati());
      // BazaDate.inserareAngajati(new Angajat(18,"Mihaile Ana", "Dezvoltator Software", "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", 4500, "0724540806", new Date(12,10, 2022), 2));
        BazaDate.stergereAngajat(17);
        BazaDate.modificareInfoAngajat(4, "Mihaile Andra", "Dezvoltator Software", 4500, "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", "0724540806", Date.valueOf("2023-12-20"), 2);
    }
    
}
