package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.model.Part;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view for Part creation.
 */
class Create extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Create.class.getName());

    /** The "create" button. */
    private JButton createButton = new JButton("Create");

    /** The text field for the part name. */
    private JTextField textReference = new JTextField();

    /** The text field for the part provider. */
    private JTextField textProvider = new JTextField();

    /** The text field for the part quantity. */
    private JTextField textAvailableQuantity = new JTextField();

    /** The text field for the part price. */
    private JTextField textPrice = new JTextField();

    /**
     * Sets up UI for part creation.
     * It basically includes a form with a submit button <code>createButton</code>.
     */
    Create() {
        GridLayout layout = new GridLayout(5, 2);
        this.setLayout(layout);
        JLabel labelReference = new JLabel("Reference");
        this.add(labelReference);
        this.add(textReference);
        JLabel labelProvider = new JLabel("Provider");
        this.add(labelProvider);
        this.add(textProvider);
        JLabel labelAvailableQuantity = new JLabel("Available quantity");
        this.add(labelAvailableQuantity);
        this.add(textAvailableQuantity);
        JLabel labelPrice = new JLabel("Price");
        this.add(labelPrice);
        this.add(textPrice);
        JLabel labelSpace = new JLabel("");
        this.add(labelSpace);
        this.add(createButton);

        Listener listener = new Listener();
        createButton.addActionListener(listener);
        this.setVisible(true);
        logger.info("Displayed creation tab.");
    }

    /**
     * The listener for the "create" button.
     */
    private class Listener implements ActionListener {

        /**
         * Once the button is pressed, the data from the form is converted into a <tt>Part</tt> object and then transmitted to the server as a serialized object.
         * @param e The information on the action performed on the "create" button.
         */
        public void actionPerformed(ActionEvent e) {
            String reference = String.valueOf(textReference.getText());
            String provider = String.valueOf(textProvider.getText());
            int availableQuantity = Integer.parseInt(String.valueOf(textAvailableQuantity.getText()));
            float price = Float.parseFloat(String.valueOf(textPrice.getText()));

            if (e.getSource() == createButton) {
                Part a = new Part(reference, provider, availableQuantity, price);
                try {
                    logger.info("Attempting to create part #" + a.getId() + " in database...");
                    // TODO Implement client-side create
                } catch (Exception err) {
                    logger.error("Failed to create part #" + a.getId() + " in database. \nException: " + err.getMessage());
                }
            }
        }
    }

}
