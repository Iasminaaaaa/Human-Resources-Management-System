package clase;
import java.util.Date;


/**
 *Clasa RaportPerformanta conține informații despre raportul de performanță al unui angajat.
 * Aceasta include detalii despre scorul evaluării, comentarii și data evaluării.
 * Clasa este folosită pentru a stoca și manipula rapoartele de performanță ale angajaților
 */
public class RaportPerformanta {
private String raportId, comentarii;
private int scor;
private Date dataEvaluare;

/**
* Constructor fara parametrii pentru clasa RaportPerformanta
*/ 
public RaportPerformanta() {};

/**
* Constructor cu parametrii pentru clasa RaportPerformanta 
* @param rId ID-ul raportului de performanta
* @param com comentarii referitoare la raportul de performanta
* @param s scorul obtinut de angajat
* @param dataEv data evaluarii performanței angajatului
*/
public RaportPerformanta(String rId, String com, int s, Date dataEv) {
	raportId=rId;
    comentarii=com;
	scor=s;
	dataEvaluare=dataEv;
}

/**
 * Obține ID-ul raportului de performanta
 * @return ID-ul raportului
 */
public String getRaprtId() {
    return raportId;
}

/**
 * Obtine comentariile raportului de performanta
 * @return comentariile raportului
 */
public String getComentarii() {
    return comentarii;
}

/**
 * Obtine scorul evaluarii performantei.
 * @return scorul obținut
 */
public int getScor() {
    return scor;
}

/**
 * Obtine data evaluarii raportului de performanta
 * @return data evaluarii
 */
public Date getDataEvaluare() {
    return dataEvaluare;
}

/**
 * Seteaza ID-ul raportului de performanta.
 * @param rapId ID-ul raportului
 */
public void setRaprtId(String rapId) {
    this.raportId=rapId;
}

/**
 * Seteaza comentariile raportului de performanta
 * @param comentarii comentariile raportului
 */
public void setComentarii(String comentarii) {
    this.comentarii=comentarii;
}

/**
 * Seteaza scorul evaluarii performantei
 * @param scor scorul obtinut
 */
public void setScor(int scor) {
    this.scor=scor;
}

/**
 * Seteaza data evaluarii raportului de performanta
 * @param dataEvaluare data evaluarii performantei
 */
public void setDataEvaluare(Date dataEvaluare) {
    this.dataEvaluare=dataEvaluare;
}

/**
 * Suprascrierea metodei toString() pentru a returna o descriere detaliată a raportului
 * @return un string ce contine informatiile despre raport
 */
@Override
public String toString() {
    return "Raport ID: "+raportId+", Comentarii: "+comentarii+", Scor: "+scor+", Data Evaluare: "+dataEvaluare;
}

}
