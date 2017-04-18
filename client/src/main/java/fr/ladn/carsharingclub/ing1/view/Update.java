package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.model.Part;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view for Part updating.
 */
class Update extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Delete.class.getName());

    /** The "search" button. */
    private JButton searchButton = new JButton("Search");

    /** The "update" button. */
    private JButton updateButton = new JButton("Update");

    /** The text field for the part ID. */
    private JTextField textId = new JTextField();

    /** The text field for the part reference. */
    private JTextField textReference = new JTextField();

    /** The text field for the privder name. */
    private JTextField textProvider = new JTextField();

    /** The text field for the available quantity. */
    private JTextField textAvailableQuantity = new JTextField();

    /** The text field for the part price. */
    private JTextField textPrice = new JTextField();

    /**
     * Sets up UI for updating a part.
     * It includes a form where the user can edit the information of an existing part.
     * The current part data is first displayed in the appropriate fields.
     * The user can then edit one or several of these fields.
     */
    Update() {
        //this.setSize(10, 20);
        GridLayout layout2 = new GridLayout(5, 3);
        this.setLayout(layout2);
        JLabel labelId = new JLabel("Part number");
        this.add(labelId);
        this.add(textId);
        this.add(searchButton);
        JLabel labelReference = new JLabel("Reference");
        this.add(labelReference);
        this.add(textReference);
        JLabel space = new JLabel(" ");
        this.add(space);
        JLabel labelProvider = new JLabel("Provider");
        this.add(labelProvider);
        this.add(textProvider);
        JLabel space2 = new JLabel(" ");
        this.add(space2);
        JLabel labelAvailableQuantity = new JLabel("Available quantity");
        this.add(labelAvailableQuantity);
        this.add(textAvailableQuantity);
        JLabel space3 = new JLabel(" ");
        this.add(space3);
        JLabel labelPrice = new JLabel("Price");
        this.add(labelPrice);
        this.add(textPrice);
        this.add(updateButton);
        updateButton.setVisible(false);

        Listener listener = new Listener();
        searchButton.addActionListener(listener);
        updateButton.addActionListener(listener);

        //this.pack();
        this.setVisible(true);
        logger.info("Displayed updating tab.");
    }

    /**
     * Listener for the "search" and "update" buttons.
     *
     * @see Read
     */
    private class Listener implements ActionListener {

        /**
         * Firstly, the user must enter the part ID to be updated.
         * When the "search" button is pressed, the fields are filled with the corresponding information.
         * When the "update" button is pressed, the edited values are sent to the server.
         *
         * @param e The information of the action performed on a button.
         */
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(textId.getText());

            if (e.getSource() == searchButton) {
                try {
                    // TODO Implement client-side update
                    /*
                    textReference.setText(a.getReference());
                    textProvider.setText(a.getProvider());
                    textAvailableQuantity.setText("" + a.getAvailableQuantity());
                    textPrice.setText("" + a.getPrice());
                    searchButton.setVisible(false);
                    updateButton.setVisible(true);
                    */
                } catch (Exception err) {
                    logger.error("Failed to update part #" + id + " in database. Exception: " + err.getMessage());
                    JOptionPane.showMessageDialog(null, "La pièce n'existe pas.");
                }
            } else if (e.getSource() == updateButton) {
                String reference = textReference.getText();
                String provider = textProvider.getText();
                int availableQuantity = Integer.parseInt(textAvailableQuantity.getText());
                float price = Float.parseFloat(textPrice.getText());
                Part a = new Part(id, reference, provider, availableQuantity, price);
                try {
                    sendData(Operation.UPDATE, a);
                    // TODO implement communication method for update
                } catch (Exception err) {
                    System.out.println("Exception: " + err.getMessage());
                }
            }


        }
    }

}
