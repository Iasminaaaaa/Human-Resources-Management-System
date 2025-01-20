package model;
import java.util.Date;

/**
 * Clasa IstoricModificari reprezintă istoricul modificarilor facute unui angajat
*/
public class IstoricModificari {
    private Date startDate;
    private Date endDate;
    private String motiv;

    /**
     * Constructor cu parametrii pentru clasa IstoricModificari.
     * @param startDate Data de inceput a modificarii.
     * @param endDate Data de sfarsit a modificarii.
     * @param motiv Motivul modificarii.
     */
     public IstoricModificari(Date startDate, Date endDat, String motiv) {
        this.startDate=startDate;
        this.endDate=endDat;
        this.motiv=motiv;
    }

     /**
      * Metoda getStartDate() nu are parametri
      * @return Data de început a modificarii
      */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Metoda getEndDate() nu are parametri
     * @return Data de sfarsit a modificarii
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Metoda getMotiv() nu are parametri
     * @return Motivul modificarii
     */
    public String getMotiv() {
        return motiv;
    }
}
