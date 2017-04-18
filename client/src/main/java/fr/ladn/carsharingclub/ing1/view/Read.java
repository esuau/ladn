package fr.ladn.carsharingclub.ing1.view;

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

    /** The "read" button. */
    private JButton readButton = new JButton("Read");

    /** The text field for the part ID. */
    private JTextField textId = new JTextField();

    /**
     * Sets up a UI to display the information of the Part object.
     */
    Read() {
        this.setName("Read");
        GridLayout layout2 = new GridLayout(5, 3);
        this.setLayout(layout2);
        JLabel labelId = new JLabel("ID");
        this.add(labelId);
        this.add(textId);
        this.add(readButton);
        JLabel labelReference = new JLabel("Reference");
        this.add(labelReference);
        JTextField textReference = new JTextField();
        this.add(textReference);
        JLabel space = new JLabel(" ");
        this.add(space);
        JLabel labelProvider = new JLabel("Provider");
        this.add(labelProvider);
        JTextField textProvider = new JTextField();
        this.add(textProvider);
        JLabel space2 = new JLabel(" ");
        this.add(space2);
        JLabel labelPrice = new JLabel("Price");
        this.add(labelPrice);
        JTextField textPrice = new JTextField();
        this.add(textPrice);
        JLabel space3 = new JLabel(" ");
        this.add(space3);
        JLabel labelAvailableQuantity = new JLabel("Available quantity");
        this.add(labelAvailableQuantity);
        JTextField textAvailableQuantity = new JTextField();
        this.add(textAvailableQuantity);

        Listener listener = new Listener();
        readButton.addActionListener(listener);
        //this.pack();
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
            Integer id = Integer.parseInt(textId.getText());

            if (e.getSource() == readButton) {
                Part a = new Part(id, "", "", "", "");
                try {
                    sendData(Operation.READ, a);
                    // TODO Implement client-side read
                    /*
                    textReference.setText(a.getReference());
                    textProvider.setText(a.getProvider());
                    textPrice.setText("" + a.getPrice());
                    textAvailableQuantity.setText("" + a.getAvailableQuantity());
                    */
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "La pièce n'existe pas.");
                    logger.error("Failed to read part #" + id + ". Exception: " + err.getMessage());
                }
            }

        }
    }


}
