package Clase;

/**
 * Clasa ExceptieAplicatie extinde Clasa Exception, utila pentru a arunca exceptii specifice aplicatiei
 * 
 */
public class ExceptieAplicatie extends Exception{

static final long serialVersionUID = 3218914868173478299L;

/**
 * Constructorul clasei ExceptieAplicatie 
 * @param mesaj reprezinta mesajul care va aparea in momentul in care apare exceptia
 */
public ExceptieAplicatie(String mesaj) {
	super(mesaj);
	}
}
