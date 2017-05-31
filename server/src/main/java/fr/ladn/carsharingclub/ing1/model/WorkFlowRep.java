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
    private Date date_deb;
    private int id_place;
    private Date date_fin;


    /**
     * The default constructor.
     * Never actually used in the project.
     * Allows serialization.
     */
    public WorkFlowRep() { }

    /**
     * Custom constructor.
     * Used to create a reparation with defined ID.
     *
     */
    public WorkFlowRep(int id,String statut,Date date_deb,int id_place,Date date_fin) {
        logger.info("Creating new Workflow reparation instance with custom ID...");
        this.statut=statut;
        this.date_deb=date_deb;
        this.id_place=id_place;
        this.date_fin=date_fin;
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

    public Date getDate_deb() {
        return date_deb;
    }

    public int getId_place() {
        return id_place;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setId_reparation(int id_reparation) {
        this.id_reparation = id_reparation;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

  

   

    
}
