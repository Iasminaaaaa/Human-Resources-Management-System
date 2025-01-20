package model;

import java.util.Date;

/**
 * Clasa IstoricSalariu reprezinta istoricul modificarilor salariului unui angajat
 * Aceasta extinde clasa IstoricModificari pentru a include informatii despre salariul anterior
 */
public class IstoricSalariu extends IstoricModificari{
	private Double salariuAnterior;
	 /**
     * Constructor cu parametrii pentru clasa IstoricSalariu
     * @param startDate Data de inceput a modificarilor
     * @param endDate Data de sfarsit a modificarilor
     * @param salariuAnterior Salariul anterior al angajatului inainte de modificare
     * @param motiv Motivul schimbarii salariului
     */
	public IstoricSalariu(Date startDate, Date endDate, Double salariuAnterior, String motiv) {
		super(startDate, endDate, motiv);
		this.salariuAnterior=salariuAnterior;
	}
	
	 /**
     * Metoda getSalariulAnterior() nu are parametri
     * @return Salariul anterior al angajatului inainte de modificare
     */
	 public Double getSalariulAnterior() {
	        return salariuAnterior;
	    }
}
