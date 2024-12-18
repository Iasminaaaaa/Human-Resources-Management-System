package clase;
import java.util.Date;

/**
 * Clasa Cerere contine informatii despre o cerere facuta de un angajat, cum ar fi ID-ul cererii, comentariile managerului, statusul cererii, data de inceput si data de sfarsit ale cererii si tipul cererii
 * Aceasta poate fi de tipul concediu, invoire sau demisie.
 */
public class Cerere{
private String cerereId, comentariiManager;
private String status;
private Date dataInceput, dataSfarsit;
public enum tipCerere{Concediu, Invoire, Demisie};
private tipCerere tc;

/**
 * Constructorul fara parametrii al clasei Cerere
 */
public Cerere() {};

/**
 * Constructorul cu parametrii al clasei Cerere
 * @param cId ID-ul cererii
 * @param comManager comentarii din partea managerului referitoare la cerere
 * @param stat statusul cererii(Acceptat sau Respins)
 * @param dInceput data de inceput a cererii
 * @param dSfarsit data de sfarsit a cererii
 * @param t tipul cererii(concediu, invoire sau demisie)
 * @throws ExceptieAplicatie Daca statusul cererii nu este "Acceptat" sau "Respins", se va arunca o exceptie
 */
public Cerere(String cId, String comManager, String stat, Date dInceput, Date dSfarsit, tipCerere t) throws ExceptieAplicatie{
	cerereId=cId;
	comentariiManager=comManager;
	if(!stat.equals("Acceptat") && !stat.equals("Respins")) throw new ExceptieAplicatie("Statusul poate fi doar Acceptat sau Respins!");
	else status=stat;
	dataInceput=dInceput;
	dataSfarsit=dSfarsit;
	tc=t;
}

/**
 * Obtine ID-ul cererii.
 * @return ID-ul cererii
 */
public String getCerereId() {
    return cerereId;
}

/**
 * Obtine comentariile managerului
 * @return comentariile managerului
 */
public String getComentariiManager() {
    return comentariiManager;
}

/**
 * Obtine statusul cererii
 * @return statusul cererii
 */
public String getStatus() {
    return status;
}

/**
 * Obtine data de inceput a cererii
 * @return data de inceput a cererii
 */
public Date getDataInceput() {
    return dataInceput;
}

/**
 * Obtine data de sfarsit a cererii 
 * @return data de sfarsit a cererii
 */
public Date getDataSfarsit() {
    return dataSfarsit;
}

/**
 * Obtine tipul cererii.
 * @return tipul cererii(concediu, invoire, demisie)
 */
public tipCerere getTipCerere() {
    return tc;
}

/**
 * Suprascrierea metodei toString() pentru a returna o descriere detaliată a cererii
 * @return un string ce contine informatiile cererii
 */
@Override
public String toString() {
    return "Cerere ID: "+cerereId+", Comentarii Manager: "+comentariiManager+", Status: "+status+", Data Inceput: " +dataInceput+", Data Sfarsit: "+dataSfarsit+", Tip Cerere: "+tc;
}

}
