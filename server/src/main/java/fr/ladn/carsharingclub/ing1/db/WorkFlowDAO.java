package fr.ladn.carsharingclub.ing1.db;

import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.model.WorkFlowRep;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DOA Object for Part.
 * Contains SQL statements for CRUD in database.
 *
 * @see Part
 */
public class WorkFlowDAO {

    /**
     * The logger.
     */
    private final static Logger logger = Logger.getLogger(WorkFlowDAO.class.getName());

    /**
     * The connection pool.
     */
    private ConnectionPool pool;

    /**
     * Constructor. References connection pool.
     *
     * @param p The connection pool.
     * @see ConnectionPool
     */
    public WorkFlowDAO(ConnectionPool p) {
        logger.info("Creating PartDAO object...");
        pool = p;
        logger.info("Established link with connection pool " + pool + ".");
    }


    /**
     * Gets information from an existing part by its ID.
     *
     * @param s
     * @return the information on the part.
     * @throws Exception if a connection issue is encountered.
     */
    public ArrayList<WorkFlowRep> displayCars() throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        String requete = "SELECT DISTINCT id_vehicule FROM reparer WHERE date_sortie_vehicule IS NULL";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(requete);
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<WorkFlowRep> vehicules = new ArrayList<>();

        while (rs.next()) {
            int vehicule = rs.getInt("id_vehicule");

            logger.info("Successfully get id_vehicules #" + vehicule + " information from database.");
            vehicules.add(new WorkFlowRep(0, "", null, 0, null, vehicule, ""));
        }
        return vehicules;
    }

    public ArrayList<WorkFlowRep> WorkflowCar(int v) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        String requete = "SELECT reparer.id_vehicule,statut,date_debut,reparation_histo_temps.id_place,date_fin, reparer.id_reparation, immatriculation FROM reparer INNER JOIN reparation_histo_temps ON reparer.id_reparation=reparation_histo_temps.id_reparation INNER JOIN vehicule ON reparer.id_vehicule=vehicule.id_vehicule WHERE reparer.id_vehicule=" + v + " AND reparer.date_sortie_vehicule IS NULL ORDER BY date_debut";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(requete);
        logger.info("Database request has been successfully executed.");

        pool.returnConnection(conn);
        logger.info("Connection " + conn + " returned to the connection pool.");

        ArrayList<WorkFlowRep> vehicules = new ArrayList<>();

        while (rs.next()) {
            int id_rep = rs.getInt("id_reparation");
            String statut = rs.getString("statut");
            java.sql.Timestamp debut = rs.getTimestamp("date_debut");
            int place = rs.getInt("id_place");
            java.sql.Timestamp fin = rs.getTimestamp("date_fin");
            int vehicule = rs.getInt("id_vehicule");
            String immat = rs.getString("immatriculation");
            logger.info("Successfully get id_vehicules #" + v + " information from database.");
            vehicules.add(new WorkFlowRep(id_rep, statut, debut, place, fin, vehicule, immat));
        }
        return vehicules;
    }

    public ArrayList<WorkFlowRep> calculStats(int choix) throws Exception {

        Connection conn = pool.getConnection();
        logger.info("Successfully pulled connection " + conn + " from the connection pool.");
        ArrayList<WorkFlowRep> stat = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        String requete;
        switch (choix) {
            case 1:
                requete = "SELECT reparer.id_technicien,technicien.nom_technicien, COUNT(id_vehicule) AS NombreVehicule FROM `reparer` LEFT JOIN technicien ON reparer.id_technicien=technicien.id_technicien WHERE DATEDIFF(SYSDATE(),date_entree_vehicule)<365 GROUP BY reparer.id_technicien ORDER BY NombreVehicule DESC";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(requete);
                logger.info("Database request has been successfully executed.");
                pool.returnConnection(conn);
                logger.info("Connection " + conn + " returned to the connection pool.");
                while (rs.next()) {
                    int techID = rs.getInt("id_technicien");
                    String name = rs.getString("nom_technicien");
                    int count = rs.getInt("NombreVehicule");
                    logger.info("Successfully get number of vehicules by #" + techID + " information from database.");
                    stat.add(new WorkFlowRep(techID, name, count));
                }
                break;
            case 2:
                requete = "SELECT statut_reparation, COUNT(id_vehicule) AS NombreVehicule FROM `reparer` WHERE date_sortie_vehicule IS NULL GROUP BY statut_reparation";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(requete);
                logger.info("Database request has been successfully executed.");
                pool.returnConnection(conn);
                logger.info("Connection " + conn + " returned to the connection pool.");
                while (rs.next()) {

                    String status = rs.getString("statut_reparation");
                    int count = rs.getInt("NombreVehicule");
                    logger.info("Successfully get number of vehicules by #" + status + " information from database.");
                    stat.add(new WorkFlowRep(-1, status, count));
                }
                break;
            case 3:
                requete = "SELECT DATE(date_entree_vehicule) AS date , statut_reparation, count(id_vehicule) AS NombreVehicule FROM `reparer` GROUP BY DATE(date_entree_vehicule),statut_reparation ORDER BY DATE(date_entree_vehicule) DESC";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(requete);
                logger.info("Database request has been successfully executed.");
                pool.returnConnection(conn);
                logger.info("Connection " + conn + " returned to the connection pool.");
                while (rs.next()) {

                    java.sql.Date entry = rs.getDate("date");
                    String status = rs.getString("statut_reparation");
                    int count = rs.getInt("NombreVehicule");
                    logger.info("Successfully get number of vehicules by #" + entry + " information from database.");
                    stat.add(new WorkFlowRep(entry, status, count));
                }
                break;
            case 4:
                requete = "SELECT reparer.id_technicien,technicien.nom_technicien, COUNT(id_vehicule) AS NombreVehicule FROM `reparer` LEFT JOIN technicien  ON reparer.id_technicien=technicien.id_technicien INNER JOIN reparation_histo_temps ON reparer.id_reparation=reparation_histo_temps.id_reparation WHERE DATEDIFF(SYSDATE(),reparation_histo_temps.date_debut)<2 AND reparation_histo_temps.statut='reparé' GROUP BY reparer.id_technicien ORDER BY NombreVehicule DESC";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(requete);
                logger.info("Database request has been successfully executed.");
                pool.returnConnection(conn);
                logger.info("Connection " + conn + " returned to the connection pool.");
                while (rs.next()) {
                    int techID = rs.getInt("id_technicien");
                    String name = rs.getString("nom_technicien");
                    int count = rs.getInt("NombreVehicule");
                    logger.info("Successfully get number of vehicules by #" + techID + " information from database.");
                    stat.add(new WorkFlowRep(techID, name, count));
                }
                break;

            default:
                return null;
        }
        return stat;
    }

}

    

