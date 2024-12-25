package app;

import java.sql.Date;

import bd.BazaDate;
import controller.GestionareAngajati;
import controller.GestionareConturi;
import controller.GestionareRapoarte;
import model.Angajat;
import model.RaportPerformanta;

public class Main {

    public static void main(String[] args) {
    	GestionareAngajati.creareTabelAngajati();
    	//new InterfataAplicatie1();
//    	GestionareAngajati.creareTabelAngajatiArhivati();
//    	GestionareAngajati.vizualizareInfoAngajati(GestionareAngajati.infoAngajati());
//        GestionareAngajati.inserareAngajatNou(new Angajat(17,"Mihaile Ana", "Dezvoltator Software", "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", 4500, "0724540806", Date.valueOf("2022-12-10"), 0));
//        GestionareAngajati.inserareAngajatNou(new Angajat(17, "Mihaile Ana", "Dezvoltator Software", "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", 4500, "0724540806", Date.valueOf("2022-12-10"), 1));
//        GestionareAngajati.inserareAngajatNou(new Angajat(18, "Popescu Ion", "Manager de Proiect", "ion.popescu@gmail.com", "Strada Mihai Eminescu, 10", 6000, "0723456789", Date.valueOf("2021-03-15"), 1));
//        GestionareAngajati.inserareAngajatNou(new Angajat(19, "Ionescu Maria", "Analist Financiar", "maria.ionescu@gmail.com", "Strada Alexandru Ioan Cuza, 15", 5000, "0729876543", Date.valueOf("2020-07-20"), 1));
//        GestionareAngajati.inserareAngajatNou(new Angajat(20, "Vasilescu Andrei", "Inginer de Sisteme", "andrei.vasilescu@gmail.com", "Strada Stefan cel Mare, 22", 4800, "0721234567", Date.valueOf("2019-11-25"), 0));
//        GestionareAngajati.inserareAngajatNou(new Angajat(21, "Georgescu Elena", "Specialist Marketing", "elena.georgescu@gmail.com", "Strada Nicolae Iorga, 30", 4500, "0721122334", Date.valueOf("2021-05-05"), 5));
//        GestionareAngajati.inserareAngajatNou(new Angajat(22, "Marinescu Adrian", "Consultant IT", "adrian.marinescu@gmail.com", "Strada Grigore Alexandrescu, 8", 5500, "0722233445", Date.valueOf("2022-09-10"), 5));
//        GestionareAngajati.inserareAngajatNou(new Angajat(23, "Radu Cristina", "Designer Grafic", "cristina.radu@gmail.com", "Strada George Enescu, 16", 4200, "0723344556", Date.valueOf("2023-01-20"), 5));
//        GestionareAngajati.inserareAngajatNou(new Angajat(24, "Dumitrescu Mihai", "Arhitect Software", "mihai.dumitrescu@gmail.com", "Strada Ion Creanga, 12", 7000, "0724455667", Date.valueOf("2018-06-30"), 0));
//        GestionareAngajati.inserareAngajatNou(new Angajat(25, "Tudor Violeta", "Responsabil Resurse Umane", "violeta.tudor@gmail.com", "Strada Gheorghe Doja, 28", 4600, "0725566778", Date.valueOf("2020-04-15"), 9));
//        GestionareAngajati.inserareAngajatNou(new Angajat(26, "Stanescu Robert", "Administrator de Retea", "robert.stanescu@gmail.com", "Strada Ionel Teodoreanu, 5", 4800, "0726677889", Date.valueOf("2019-10-05"), 9));

        
     //  GestionareAngajati.stergereAngajat(11);
       //GestionareAngajati.modificareInfoAngajat(4, "Mihaile Andra", "Dezvoltator Software", 4500, "andra.mihaile@gmail.com", "Strada Vasile Alecsandri, 6", "0724540806", Date.valueOf("2023-12-20"), 2);
       //GestionareAngajati.modificareInfoAngajat(4, "Mihai Andru", null, 0, "andru.mihai@gmail.com", null, "0724550806", null, 2);
        
//        GestionareAngajati.modificareNumeAngajat(1, "Jane Doe");
//        GestionareAngajati.modificareJobAngajat(1, "Senior Developer");
//        GestionareAngajati.modificareSalariuAngajat(1, 6000);
//       GestionareAngajati.modificareEmailAngajat(1, "jane.doe@gmail.com");
//        GestionareAngajati.modificareAdresaAngajat(1, "Strada Mihai Viteazu 29");
//        GestionareAngajati.modificareNrTelAngajat(1, "0787654321");
//        GestionareAngajati.modificareDataAngajare(1, Date.valueOf("2023-01-01"));
//        GestionareAngajati.modificareManagerId(1, 7);

//        GestionareRapoarte.creareTabelRapoarte();
//        GestionareRapoarte.inserareRaportNou(new RaportPerformanta(0, "Foarte bine lucrat!", 5, Date.valueOf("2024-12-23"), 1));
//         GestionareRapoarte.inserareRaportNou(new RaportPerformanta(0, "Data viitoare mai atent!", 2, Date.valueOf("2024-12-21"), 5));
//        
//        GestionareRapoarte.vizualizareInfoRapoarte(GestionareRapoarte.infoRapoarte());
//         GestionareRapoarte.creareTabelRapoarteArhivate();
//         GestionareRapoarte.arhivareRapoarte(2);
       //  GestionareRapoarte.modificareComentariiRaport(3, "Data viitoare lucram mai mult!");
       //  GestionareRapoarte.creareTabelCereri();
    	//GestionareAngajati.vizualizareInfoAngajati(GestionareAngajati.infoAngajati());
    	GestionareConturi.creareTabelConturi();
    }
    
}
