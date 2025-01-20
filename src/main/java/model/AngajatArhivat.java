package model;
import java.sql.Date;

/**
 * Clasa Angajat contine informatii despre angajatii unei firme precum  nume, job, email, adresa, salariu, numar de telefon, data angajarii, id-ul managerului si al angajatului
 */
public class AngajatArhivat extends Angajat{

private Date dataDemisie;


/**
 * Constructor fara parametrii pentru clasa Angajat
 */
public AngajatArhivat() {};

/**
 * Constructor cu parametrii pentru clasa Angajat
 * @param i ID-ul angajatului
 * @param n numele angajatului
 * @param j job-ul angajatului
 * @param em email-ul angajatului
 * @param adr adresa angajatului
 * @param sal salariul angajatului
 * @param nrTel numarul de telefon al angajatului
 * @param d data angajarii
 * @param mId ID-ul managerului
 */
public AngajatArhivat(int i, String n, String j, String em, String adr, double sal, String nrTel, Date d, int mId, Date dataD) {
	super(i, n, j, em, adr, sal, nrTel, d, mId);
	dataDemisie=dataD;
}

/**
 * Metoda getDataDemisie() nu are parametrii
 * @return data in care un angajat a demisionat
 */
public Date getDataDemisie() {
	return dataDemisie;
}

/**
 * Metoda toString() este suprascrisa pentru a afisa informatiile unui angajat
 * @return sub forma de string detaliile angajatului
 */
@Override
	public String toString() {
		return super.toString() + ", Data Demisie: " + dataDemisie;
        }

}

