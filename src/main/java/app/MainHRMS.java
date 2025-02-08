package app;


import bd.BazaDate;
import controller.GestionareAngajati;
import controller.GestionareCereri;
import controller.GestionareConturi;
import controller.GestionareRapoarte;
import gui.InterfataAp;

/**
 * Clasa principala a aplicatiei care initializeaza baza de date si porneste interfata grafica
 */
public class MainHRMS {

	 /**
     * Metoda principala a aplicatiei
     * Se ocupa de crearea tuturor tabelelor necesare in baza de date si de lansarea interfetei grafice
     * @param args argumentele liniei de comanda (neutilizate in aceasta aplicatie)
     */
    public static void main(String[] args) {
    	BazaDate.creareBazaDate();
    	GestionareAngajati.creareTabelAngajati();
    	GestionareAngajati.creareTabelAngajatiArhivati();
    	GestionareConturi.creareTabelConturi(); 
        GestionareAngajati.creareTabelModificariSalariu();
        GestionareAngajati.creareTabelModificariJob();
        GestionareAngajati.creareTabelModificariManager();
        GestionareCereri.creareTabelCereri();
        GestionareCereri.creareTabelaCereriArhivate();
        GestionareRapoarte.creareTabelRapoarte();
        GestionareRapoarte.creareTabelRapoarteArhivate();
        InterfataAp frame = new InterfataAp();
        frame.setVisible(true);
    }
    
}
