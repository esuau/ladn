
package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.PartDAO;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * Part creation view
 */
class Create extends JPanel {

    private ConnectionPool pool;
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
    Create(ConnectionPool p) {
        pool = p;
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
    }

    /**
     * This method listens for an event from the createButton
     * <p>
     * Once the button is pressed, data in the form is transmitted to the <tt>PartDAO</tt> object to be written in the database.
     * </p>
     *
     * @see PartDAO
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
                    new PartDAO(pool).create(a);
                } catch (Exception err) {
                    System.out.println("Exception: " + err.getMessage());
                }
            }

        }
    }

}
