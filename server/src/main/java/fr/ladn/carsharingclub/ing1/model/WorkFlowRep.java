package fr.ladn.carsharingclub.ing1.model;

import org.apache.log4j.Logger;
import java.io.Serializable;
import java.util.Date;

/**
 * Part business object
 */
public class WorkFlowRep implements Serializable {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(WorkFlowRep.class.getName());

    /** The identifier of the workflow. */
    private int id_reparation;
    
    /** Caracteristics of the workflow*/
    
    private String statut;
    private java.sql.Timestamp date_deb;
    private int id_place;
    private java.sql.Timestamp date_fin;
    private int id_vehicule;
    private String modele; 
    private int count;
    private String nom_tech;
    private int id_tech;
    private java.sql.Date dateStat;

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
    

 
    /**
     * The default constructor.
     * Never actually used in the project.
     * Allows serialization.
     */
    public WorkFlowRep() {
    }

    /**
     * Custom constructor.
     * Used to create a reparation with defined ID.
     *
     * @param id
     * @param statut
     * @param id_vehicule
     */
    public WorkFlowRep(int id,String statut,java.sql.Timestamp date_deb,int id_place,java.sql.Timestamp date_fin,int id_vehicule,String modele) {
        logger.info("Creating new Workflow reparation instance with custom ID...");
        this.id_reparation=id;
        this.statut=statut;
        this.date_deb=date_deb;
        this.id_place=id_place;
        this.date_fin=date_fin;
        this.id_vehicule=id_vehicule;
        this.modele=modele;
    }
    
    public WorkFlowRep(int id_technicien,String nom,int count ) {
        logger.info("Creating new Workflow reparation instance with custom ID...");
        this.id_tech=id_technicien;
        this.nom_tech=nom;
        this.count=count;
    }
    
    public WorkFlowRep(java.sql.Date dt,String status,int count ) {
        logger.info("Creating new Workflow reparation instance with custom ID...");
        this.dateStat=dt;
        this.statut=status;
        this.count=count;
    }

    public void setDateStat(java.sql.Date dateStat) {
        this.dateStat = dateStat;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public java.sql.Date getDateStat() {
        return dateStat;
    }

    public void setNom_tech(String nom_tech) {
        this.nom_tech = nom_tech;
    }

    public void setId_tech(int id_tech) {
        this.id_tech = id_tech;
    }

    public int getCount() {
        return count;
    }

    public String getNom_tech() {
        return nom_tech;
    }

    public int getId_tech() {
        return id_tech;
    }

    public static Logger getLogger() {
        return logger;
    }

  

    public int getId_reparation() {
        return id_reparation;
    }

    public String getStatut() {
        return statut;
    }

    public java.sql.Timestamp getDate_deb() {
        return date_deb;
    }

    public int getId_place() {
        return id_place;
    }

    public java.sql.Timestamp getDate_fin() {
        return date_fin;
    }

    public void setId_reparation(int id_reparation) {
        this.id_reparation = id_reparation;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setDate_deb( java.sql.Timestamp date_deb) {
        this.date_deb = date_deb;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public String getModele() {
        return modele;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public void setDate_fin(java.sql.Timestamp date_fin) {
        this.date_fin = date_fin;
    }    
}
