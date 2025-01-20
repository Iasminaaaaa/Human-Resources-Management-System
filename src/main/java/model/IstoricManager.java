package model;

import java.util.Date;

/**
 * Clasa IstoricManager reprezintă istoricul modificarilor de manager al unui angajat
 * Aceasta extinde clasa IstoricModificari pentru a include informatii despre managerul anterior.
 */
public class IstoricManager extends IstoricModificari {
	private String managerAnterior;
	public IstoricManager(Date startDate, Date endDat, String managerAnterior, String motiv) {
		super(startDate, endDat, motiv);
		this.managerAnterior=managerAnterior;
	}
	 
    /**
     * Constructor cu parametrii pentru clasa IstoricManager.
     * @param startDate Data de început a modificării.
     * @param endDate Data de sfârșit a modificării.
     * @param managerAnterior Managerul anterior al angajatului înainte de schimbare.
     * @param motiv Motivul schimbării managerului.
     */
	public String getManagerAnterior() {
	        return managerAnterior;
	    }
}
