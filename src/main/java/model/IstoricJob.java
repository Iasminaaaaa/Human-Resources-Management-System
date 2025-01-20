package model;

import java.util.Date;

/**
 * Clasa IstoricJob reprezintă istoricul unui angajat referitor la schimbările de poziție
 * Aceasta extinde clasa IstoricModificari pentru a include informații specifice despre vechea poziție
 */
public class IstoricJob extends IstoricModificari{
	private String pozitiaAnterioara;
	
	/**
     * Constructor cu parametrii pentru clasa IstoricJob.
     * @param startDate Data de început a modificării.
     * @param endDate Data de sfârșit a modificării.
     * @param pozitiaAnterioara Poziția anterioară a angajatului înainte de modificare.
     * @param motiv Motivul schimbării poziției.
     */
	public IstoricJob(Date startDate, Date endDate, String pozitiaAnterioara, String motiv) {
		super(startDate, endDate, motiv);
		this.pozitiaAnterioara=pozitiaAnterioara;
	}
	
	 /**
     * Metoda getPozitiaAnterioara() nu are parametri.
     * @return Poziția anterioară a angajatului înainte de modificarea sa.
     */
	public String getPozitiaAnterioara() {
        return pozitiaAnterioara;
    }
}
