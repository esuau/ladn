package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.model.Part;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Part creation view
 */
class Create extends JPanel {

    private final static Logger logger = Logger.getLogger(Create.class.getName());
    private JButton createButton = new JButton("Create");
    private JTextField textReference = new JTextField();
    private JTextField textProvider = new JTextField();
    private JTextField textAvailableQuantity = new JTextField();
    private JTextField textPrice = new JTextField();

    /**
     * Sets up UI for part creation
     * <p>
     * It basically includes a form with a submit button <code>createButton</code>
     * </p>
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
     * This method listens for an event from the createButton
     * <p>
     * Once the button is pressed, data in the form is transmitted to the <tt>PartDAO</tt> object to be written in the database.
     * </p>
     */
    private class Listener implements ActionListener {

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
