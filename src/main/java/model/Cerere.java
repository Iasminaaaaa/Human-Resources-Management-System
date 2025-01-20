package model;

import java.sql.Date;

/**
 * Clasa Cerere contine informatii despre cererile angajatilor unei firme precum id-ul angajatului, id-ul managerului, statusul cererii, data de inceput si de sfarsit pentru care se face cererea, numar de telefon si tipul cererii
 */
public class Cerere {
    private int cerereId;
    private int angajatId;
    private int managerId;
    private String status;
    private Date dataInceput;
    private Date dataSfarsit;
    public enum TipCerere {Leave, Resignation, Vacation};
    private TipCerere tipCerere;
    
    /**
     * Constructor fara parametrii pentru clasa Cerere
     */
    public Cerere() {}

    /**
     * Constructor cu parametrii pentru clasa Cerere
     * @param cerereId id-ul cererii
     * @param angajatId id-ul angajatului
     * @param status statusul cererii, poate fii Accepted, Denied sau Pending
     * @param dataInceput data inceperii din care se aplica cererea daca aceasta este acceptata
     * @param dataSfarsit data la care cererea se inceheie(daca cererea este de    
     * @param tipCerere tipul cererii (Leave, Resignation, Vacation).
     * @param managerId ID-ul managerului responsabil cu raspunderea la cerere 
    */
    public Cerere(int cerereId, int angajatId, String status, Date dataInceput, Date dataSfarsit, TipCerere tipCerere, int managerId) throws ExceptieAplicatie {
        this.cerereId=cerereId;
        this.angajatId=angajatId;
        if (!status.equals("Accepted") && !status.equals("Declined") && !status.equals("Pending")) {
            throw new ExceptieAplicatie("Statusul poate fi doar Accepted sau Declined!");
        } else {
            this.status=status;
        }
        this.dataInceput=dataInceput;
        this.dataSfarsit=dataSfarsit;
        this.tipCerere=tipCerere;
        this.managerId=managerId;
    }

    /**
     * Metoda getCerereId() nu are parametri.
     * @return ID-ul unic al cererii.
     */
    public int getCerereId() {
        return cerereId;
    }

    /**
     * Metoda setCerereId() primește un singur parametru
     * @param cerereId ID-ul unic al cererii
     */
    public void setCerereId(int cerereId) {
        this.cerereId=cerereId;
    }

    /**
     * Metoda getAngajatId() nu are parametri
     * @return ID-ul angajatului care a inițiat cererea
     */
    public int getAngajatId() {
        return angajatId;
    }

    /**
     * Metoda setAngajatId() primește un singur parametru
     * @param angajatId ID-ul angajatului care a inițiat cererea
     */
    public void setAngajatId(int angajatId) {
        this.angajatId=angajatId;
    }

    /**
     * Metoda getManagerId() nu are parametri
     * @return ID-ul managerului responsabil pentru cerere
     */
    public int getManagerId() {
        return managerId;
    }

    /**
     * Metoda setManagerId() primește un singur parametru
     * @param managerId ID-ul managerului responsabil pentru cerere
     */
    public void setManagerId(int managerId) {
        this.managerId=managerId;
    }

    /**
     * Metoda getStatus() nu are parametri
     * @return Statusul cererii (Accepted, Declined sau Pending)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Metoda setStatus() primește un singur parametru.
     * @param status Statusul cererii
     */
    public void setStatus(String status) {
        this.status=status;
    }

    /**
     * Metoda getDataInceput() nu are parametri
     * @return Data de început a cererii
     */
    public Date getDataInceput() {
        return dataInceput;
    }

    /**
     * Metoda setDataInceput() primește un singur parametru
     * @param dataInceput Data de început a cererii
     */
    public void setDataInceput(Date dataInceput) {
        this.dataInceput=dataInceput;
    }

    /**
     * Metoda getDataSfarsit() nu are parametri
     * @return Data de sfârșit a cererii
     */
    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    /**
     * Metoda setDataSfarsit() primește un singur parametru
     * @param dataSfarsit Data de sfârșit a cererii
     */
    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit=dataSfarsit;
    }

    /**
     * Metoda getTipCerere() nu are parametri
     * @return Tipul cererii (Leave, Resignation, Vacation)
     */
    public TipCerere getTipCerere() {
        return tipCerere;
    }

    /**
     * Metoda setTipCerere() primește un singur parametru.
     * @param tipCerere Tipul cererii (Leave, Resignation, Vacation).
     */
    public void setTipCerere(TipCerere tipCerere) {
        this.tipCerere=tipCerere;
    }

    /**
     * Metoda toString() nu are parametri.
     * @return o descriere detaliată a cererii sub forma unui șir de caractere,
     */
    @Override
    public String toString() {
        return "Cerere ID: " + cerereId  + ", Status: " + status +
                ", Data Inceput: " + dataInceput + ", Data Sfarsit: " + dataSfarsit + ", Tip Cerere: " + tipCerere;
    }
}

