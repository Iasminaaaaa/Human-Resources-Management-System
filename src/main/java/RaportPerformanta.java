import java.util.Date;

public class RaportPerformanta {
private String raportId, comentarii;
private int scor;
private Date dataEvaluare;

public RaportPerformanta(String rId, String com, int s, Date dataEv) {
	raportId=rId;
    comentarii=com;
	scor=s;
	dataEvaluare=dataEv;
}

public String getRaprtId() {
    return raportId;
}

public String getComentarii() {
    return comentarii;
}

public int getScor() {
    return scor;
}

public Date getDataEvaluare() {
    return dataEvaluare;
}

public void setRaprtId(String rapId) {
    this.raportId = rapId;
}

public void setComentarii(String comentarii) {
    this.comentarii = comentarii;
}

public void setScor(int scor) {
    this.scor = scor;
}

public void setDataEvaluare(Date dataEvaluare) {
    this.dataEvaluare = dataEvaluare;
}

}
