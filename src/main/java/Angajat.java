import java.sql.Date;

public class Angajat {
private String nume, job, email, adresa;
private int id;
private int salariu;
private String nrTelefon;
private Date dataAngajare;
private int managerId;

public Angajat(int i, String n, String j, String em, String adr, int sal, String nrTel, Date d, int mId) {
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

public int getId() {
	return id;
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

public String getNrTelefon() {
	return nrTelefon;
}

public Date getData() {
	return dataAngajare;
}

public int getManagerId() {
	return managerId;
}

@Override
	public String toString() {
		return "ID: "+id+", Nume: "+nume +", Poziție: "+job+", Salariu: " + salariu + ", Data Angajare: " + dataAngajare +", Număr Telefon: " + nrTelefon + ", Email: " + email +", Adresă: " + adresa + ", Manager ID: " + managerId;
        }

}
