package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.sockets.Client;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view for Part reading.
 */
class Read extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Delete.class.getName());

    /** The client. */
    private Client client;

    /** The "read" button. */
    private JButton readButton = new JButton("Read");

    /** The text field for the part ID. */
    private JTextField textId = new JTextField();

    /**
     * Sets up a UI to display the information of the Part object.
     */
    Read(Client client) {
        this.client = client;
        GridLayout layout2 = new GridLayout(5, 3);
        this.setLayout(layout2);
        JLabel labelId = new JLabel("ID");
        this.add(labelId);
        this.add(textId);
        JLabel space6 = new JLabel(" ");
        this.add(space6);
        this.add(readButton);
        JLabel space = new JLabel(" ");
        this.add(space);
        JLabel space5 = new JLabel(" ");
        this.add(space5);
        JLabel space2 = new JLabel(" ");
        this.add(space2);
        JLabel space7 = new JLabel(" ");
        this.add(space7);
        JLabel space3 = new JLabel(" ");
        this.add(space3);
        JLabel space8 = new JLabel(" ");
        this.add(space8);

        Listener listener = new Listener();
        readButton.addActionListener(listener);
        this.setVisible(true);
        logger.info("Displayed reading tab.");
    }

    /**
     * The listener for the "read" button.
     */
    private class Listener implements ActionListener {

        /**
         * Listens for an action on the "read" button.
         * This method gets part information from the database via the server.
         *
         * @param e The information on the action performed on the "read" button.
         */
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == readButton) {

                try {
                    String[] tableHeader = {"id_piece","libelle_piece","fabricant","qte_dispo","prix"};

                    JFrame fenetre = new JFrame();

                    if (textId.getText().isEmpty()) {
                        // TODO implement all parts reading server side and client side (follo
                        fenetre.setTitle("Toutes les pièces");
                        Part a = new Part(1, "Pneu", "Autobacs", 20, 76);
                        Object[][] donnees = {
                            { a.getId(), a.getReference(), a.getProvider(), a.getAvailableQuantity(), a.getPrice() }
                        };
                        JTable tableau = new JTable(donnees, tableHeader);
                        JScrollPane menuder = new JScrollPane(tableau);
                        fenetre.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
                        fenetre.getContentPane().add(menuder, BorderLayout.CENTER);
                        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        fenetre.pack();
                        if (donnees.length == 0) JOptionPane.showMessageDialog(null, "La piece n'existe pas.");
                        fenetre.setVisible(true);
                    } else {
                        Integer id = Integer.parseInt(textId.getText());
                        Part a = client.getPart(id);
                        fenetre.setTitle("Pièce " + id);
                        Object[][] receivedPart = {
                            { a.getId(), a.getReference(), a.getProvider(), a.getAvailableQuantity(), a.getPrice() }
                        };
                        JTable tableau = new JTable(receivedPart, tableHeader);
                        JScrollPane menuder = new JScrollPane(tableau);
                        fenetre.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
                        fenetre.getContentPane().add(menuder, BorderLayout.CENTER);
                        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        fenetre.pack();
                        if (receivedPart.length == 0) JOptionPane.showMessageDialog(null, "La piece n'existe pas.");
                        fenetre.setVisible(true);
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "La pièce n'existe pas.");
                    logger.error("Failed to read part. Exception: " + err.getMessage());
                }
            }

        }
    }


}
