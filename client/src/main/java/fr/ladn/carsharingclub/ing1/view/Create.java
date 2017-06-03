package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.sockets.Client;
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

    /** The client. */
    private Client client;

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
     *
     * @param client the client singleton.
     */
    Create(Client client) {
        this.client = client;

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
     * Clears the form fields after a successful creation.
     */
    private void clearForm() {
        textReference.setText("");
        textProvider.setText("");
        textAvailableQuantity.setText("");
        textPrice.setText("");
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
                logger.info("Attempting to create part #" + a.getId() + " in database...");
                client.createPart(a);
                JOptionPane.showMessageDialog(null, "La pièce " + a.getReference() + " a bien été ajoutée dans la base.");
                clearForm();
            }
        }
    }

}
