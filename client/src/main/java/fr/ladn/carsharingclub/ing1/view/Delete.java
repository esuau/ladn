package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.sockets.Client;
import fr.ladn.carsharingclub.ing1.utils.Operation;
import fr.ladn.carsharingclub.ing1.model.Part;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view for Part deletion.
 */
class Delete extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Delete.class.getName());

    /** The client. */
    private Client client;

    /** The text field for the part ID. */
    private JTextField textId = new JTextField();

    /** The "delete" button. */
    private JButton deleteButton = new JButton("Delete");

    /**
     * Sets up UI for part deletion.
     * This UI includes an single input, getting the ID of the part to be removed.
     * It also includes a button to operate the deletion action.
     *
     * @param client the client singleton.
     */
    Delete(Client client) {
        this.client = client;
        GridLayout layout = new GridLayout(5, 3);
        this.setLayout(layout);
        JLabel labelId = new JLabel("ID");
        this.add(labelId);
        this.add(textId);
        JLabel space6 = new JLabel(" ");
        this.add(space6);
        this.add(deleteButton);
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
        deleteButton.addActionListener(listener);

        this.setVisible(true);
        logger.info("Displayed deletion tab.");
    }

    /**
     * Clears the form field after a successful deletion.
     */
    private void clearForm() {
        textId.setText("");
    }

    /**
     * Listens for an event from the button.
     */
    private class Listener implements ActionListener {

        /**
         * The push of this button will remove the part permanently from the database, using the <tt>PartDOA</tt> class of the server.
         *
         * @param e The information of the action performed on the "delete" button.
         */
        public void actionPerformed(ActionEvent e) {
            Integer id = Integer.parseInt(textId.getText());

            if (e.getSource() == deleteButton) {
                Part a = new Part(id, null, null, 0, 0);
                logger.info("Attempting to delete part #" + a.getId() + " in database...");
                try {
                    client.removePart(id);
                    JOptionPane.showMessageDialog(null, "La pièce #" + id + " a bien été supprimée.");
                    clearForm();
                } catch (Exception err) {
                    logger.error("Failed to delete part in database. Exception:" + err.getMessage());
                }
            }
        }
    }


}
