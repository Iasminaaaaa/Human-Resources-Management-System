import java.util.Date;

public class Cerere{
String cerereId, comentariiManager;
String status;
Date dataInceput, dataSfarsit;
enum tipCerere{Concediu, Invoire, Demisie};
tipCerere tc;

public Cerere(String cId, String comManager, String stat, Date dInceput, Date dSfarsit,
tipCerere t) throws ExceptieAplicatie{
	cerereId=cId;
	comentariiManager=comManager;
	if(status!="Acceptat" && status!="Respins") throw new ExceptieAplicatie("Statusul poate fi doar Acceptat sau Respins!");
	else status=stat;
	dataInceput=dInceput;
	dataSfarsit=dSfarsit;
	tc=t;
}




}
