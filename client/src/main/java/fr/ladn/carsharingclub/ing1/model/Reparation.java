package fr.ladn.carsharingclub.ing1.model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Part business object
 */
public class Reparation implements Serializable {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Reparation.class.getName());

    /** The identifier of the reparation. */
    private int id_reparation;

    /** Caracteristics of reparation */

    private String statut_reparation;
    private int priorite;
    private java.sql.Date date_entrée_vehicule;
    private java.sql.Date date_sortie;
    private int id_technicien;
    private String id_panne;
    private String id_vehicule;
    private int id_place;
    private ArrayList<String> list;

    /**
     * The default constructor.
     * Never actually used in the project.
     * Allows serialization.
     */
    public Reparation() { }

    public Reparation(int id, ArrayList<String> l) {
        this.id_reparation = id;
        this.list = l;
    }


    /**
     * Custom constructor.
     * Used to create a reparation with defined ID.
     */
    public Reparation(int id, String statut_reparation, int priorite, java.sql.Date date_entrée_vehicule, java.sql.Date date_sortie, int id_technicien, String id_panne, String id_vehicule, int id_place) {
        logger.info("Creating new reparation instance with custom ID...");
        this.id_reparation = id;
        this.date_entrée_vehicule = date_entrée_vehicule;
        this.priorite = priorite;
        this.statut_reparation = statut_reparation;
        this.date_sortie = date_sortie;
        this.id_technicien = id_technicien;
        this.id_panne = id_panne;
        this.id_vehicule = id_vehicule;
        this.id_place = id_place;
    }

    /**
     * Custom constructor.
     * Used with undefined ID.
     */
    public Reparation(java.sql.Date date_entrée_vehicule, String statut_reparation, java.sql.Date date_sortie, int id_technicien, String id_panne, String id_vehicule, int id_place) {
        logger.info("Creating new reparation instance...");
        this.date_entrée_vehicule = date_entrée_vehicule;
        this.statut_reparation = statut_reparation;
        this.date_sortie = date_sortie;
        this.id_technicien = id_technicien;
        this.id_panne = id_panne;
        this.id_vehicule = id_vehicule;
        this.id_place = id_place;
    }

    public static Logger getLogger() {
        return logger;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public int getPriorite() {
        return priorite;
    }

    public int getId_reparation() {
        return id_reparation;
    }

    public java.sql.Date getDate_entrée_vehicule() {
        return date_entrée_vehicule;
    }

    public String getStatut_reparation() {
        return statut_reparation;
    }

    public java.sql.Date getDate_sortie() {
        return date_sortie;
    }

    public int getId_technicien() {
        return id_technicien;
    }

    public String getId_panne() {
        return id_panne;
    }

    public String getId_vehicule() {
        return id_vehicule;
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_reparation(int id_reparation) {
        this.id_reparation = id_reparation;
    }

    public void setDate_entrée_vehicule(java.sql.Date date_entrée_vehicule) {
        this.date_entrée_vehicule = date_entrée_vehicule;
    }

    public void setStatut_reparation(String statut_reparation) {
        this.statut_reparation = statut_reparation;
    }

    public void setDate_sortie(java.sql.Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public void setId_technicien(int id_technicien) {
        this.id_technicien = id_technicien;
    }

    public void setId_panne(String id_panne) {
        this.id_panne = id_panne;
    }

    public void setId_vehicule(String id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public Object getDate_entree_vehicule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
