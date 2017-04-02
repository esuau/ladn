package fr.ladn.carsharingclub.ing1.view;

// import fr.ladn.carsharingclub.ing1.model.Part;
// import fr.ladn.carsharingclub.ing1.xml.ReadXMLFile;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Part deletion view
 */
class Delete extends JPanel {

    private final static Logger logger = Logger.getLogger(Delete.class.getName());
    private JTextField textId = new JTextField();
    private JButton deleteButton = new JButton("Delete");

    /**
     * Sets up UI for part deletion
     * <p>
     * This UI includes an single input, getting the ID of the part to be removed.
     * <br>
     * It also includes a button to operate the deletion action.
     * </p>
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
     * Listens for an event from the button
     * <p>
     * The push of this button will immediately create a <tt>PartDAO</tt> object and remove the part permanently from the database.
     * </p>
     */
    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Integer id = Integer.parseInt(textId.getText());

            if (e.getSource() == deleteButton) {

                try {
                    // TODO Implement client-side delete
                    // logger.info("Attempting to delete part #" + a.getId() + " in database...");
                } catch (Exception err) {
                    System.out.println("Failed to delete part in database. Exception:" + err.getMessage());
                }
            }
        }
    }


}
