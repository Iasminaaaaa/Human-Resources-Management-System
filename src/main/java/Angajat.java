
public class Angajat {
private String nume, job, email, adresa;
private int salariu;
private int nrTelefon;

public Angajat(String n, String j, String em, String adr, int sal, int nrTel) {
	nume=n;
	job=j;
	email=em;
	adresa=adr;
	salariu=sal;
	nrTelefon=nrTel;
}

public String getNume() {
	return nume;
}

public String getJob() {
	return job;
}

public String getEmail() {
	return email;
}

public String getAdresa() {
	return adresa;
}

public int getSalariu() {
	return salariu;
}

public int getNrTelefon() {
	return nrTelefon;
}

@Override
	public String toString() {
		return "Nume angajat: "+nume+"\n Pozitie: "+job+ "\n Email: "+ email+"\n Adresa:"+ adresa+"\n Salariu "+salariu+"\n Numa de telefon " +nrTelefon;
	}

}
