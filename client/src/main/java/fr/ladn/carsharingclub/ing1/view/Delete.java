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

    /** The text field for the part ID. */
    private JTextField textId = new JTextField();

    /** The "delete" button. */
    private JButton deleteButton = new JButton("Delete");

    /**
     * Sets up UI for part deletion.
     * This UI includes an single input, getting the ID of the part to be removed.
     * It also includes a button to operate the deletion action.
     */
    Delete() {
        GridLayout layout = new GridLayout(1, 3);
        this.setLayout(layout);
        JLabel labelId = new JLabel("ID");
        this.add(labelId);
        this.add(textId);
        this.add(deleteButton);

        Listener listener = new Listener();
        deleteButton.addActionListener(listener);

        this.setVisible(true);
        logger.info("Displayed deletion tab.");
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
                Client client = new Client();
                logger.info("Attempting to delete part #" + a.getId() + " in database...");
                try {
                    client.sendData(Operation.DELETE, a);
                } catch (Exception err) {
                    System.out.println("Failed to delete part in database. Exception:" + err.getMessage());
                }
            }
        }
    }


}
