package model;
import java.sql.Date;

/**
 * Clasa Angajat contine informatii despre angajatii unei firme precum  nume, job, email, adresa, salariu, numar de telefon, data angajarii, id-ul managerului si al angajatului
 */
public class Angajat {
private String nume, job, email, adresa;
private int id;
private double salariu;
private String nrTelefon;
private Date dataAngajare;
private int managerId;

/**
 * Constructor fara parametrii pentru clasa Angajat
 */
public Angajat() {};

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
public Angajat(int i, String n, String j, String em, String adr, double sal, String nrTel, Date d, int mId) {
	id=i;
	nume=n;
	job=j;
	email=em;
	adresa=adr;
	salariu=sal;
	nrTelefon=nrTel;
	dataAngajare=d;
	managerId=mId;
}

/**
 * Metoda getId() nu are parametrii 
 * @return ID-ul angajatului.
 */
public int getId() {
	return id;
}

/**
 * Metoda getId() nu are parametrii 
 * @return ID-ul angajatului.
 */
public String getNume() {
	return nume;
}

/**
 * Metoda getJob() nu are parametri
 * @return job-ul angajatului.
 */
public String getJob() {
	return job;
}

/**
 * Metoda getEmail() nu are parametri.
 * @return email-ul angajatului.
 */
public String getEmail() {
	return email;
}

/**
 * Metoda getAdresa() nu are parametri.
 * @return adresa angajatului.
 */
public String getAdresa() {
	return adresa;
}

/**
 * Metoda getSalariu() nu are parametri.
 * @return salariul angajatului.
 */
public double getSalariu() {
	return salariu;
}

/**
 * Metoda getNrTelefon() nu are parametri
 * @return numărul de telefon al angajatului
 */
public String getNrTelefon() {
	return nrTelefon;
}

/**
 * Metoda getData() nu are parametri
 * @return data angajării.
 */
public Date getData() {
	return dataAngajare;
}

/**
 * Metoda getManagerId() nu are parametri
 * @return ID-ul managerului
 */
public int getManagerId() {
	return managerId;
}

/**
 * Metoda toString() este suprascrisa pentru a afisa informatiile unui angajat
 * @return sub forma de string detaliile angajatului
 */
@Override
	public String toString() {
		return "ID: "+id+", Nume: "+nume +", Poziție: "+job+", Salariu: " + salariu + ", Data Angajare: " + dataAngajare +", Număr Telefon: " + nrTelefon + ", Email: " + email +", Adresă: " + adresa + ", Manager ID: " + managerId;
        }

}
