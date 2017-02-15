package fr.ladn.carsharingclub.ing1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.ladn.carsharingclub.ing1.model.Part;

public class PartDAO {

    private ConnectionPool pool;

    public PartDAO(ConnectionPool p) {
        pool = p;
    }

    /**
     * Creates a part in the database.
     * <p>
     * Part appearing into the database are considered available in the stock.
     * </p>
     *
     * @param part to add to the stock
     * @throws Exception
     */
    public void create(Part part) throws Exception {
        String reference = part.getReference();
        String provider = part.getProvider();
        int availableQuantity = part.getAvailableQuantity();
        float price = part.getPrice();

        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO pieces ( libelle_piece, fabricant, qte_dispo, valeur_piece ) VALUES ( ?, ?, ?, ? )");
        ps.setString(1, reference);
        ps.setString(2, provider);
        ps.setInt(3, availableQuantity);
        ps.setFloat(4, price);
        ps.execute();
        pool.returnConnection(conn);
    }

    /**
     * Gets information from an existing part
     *
     * @param id of the part to be read
     * @return part information
     * @throws Exception
     */
    public Part read(int id) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM pieces WHERE id_piece = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        pool.returnConnection(conn);

        if (rs.next()) {

            String reference = rs.getString("libelle_piece");
            String provider = rs.getString("fabricant");
            int availableQuantity = rs.getInt("qte_dispo");
            float price = rs.getFloat("valeur_piece");

            return new Part(id, reference, provider, availableQuantity, price);
        } else {
            return null;
        }
    }

    /**
     * Updates part information in the stock
     *
     * @param part to be updated
     * @throws Exception
     */
    public void update(Part part) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = pool.getConnection().prepareStatement("UPDATE pieces SET libelle_piece = ?, fabricant = ?, qte_dispo = ?, valeur_piece = ? WHERE id_piece = ?");
        ps.setString(1, part.getReference());
        ps.setString(2, part.getProvider());
        ps.setInt(3, part.getAvailableQuantity());
        ps.setFloat(4, part.getPrice());
        ps.setInt(5, part.getId());
        ps.execute();
        pool.returnConnection(conn);
    }

    /**
     * Permanently removes a part from the database
     *
     * @param part to be removed from the stock
     * @throws Exception
     */
    public void delete(Part part) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM pieces WHERE id_piece = ?");
        ps.setInt(1, part.getId());
        ps.execute();
        pool.returnConnection(conn);
    }
}
