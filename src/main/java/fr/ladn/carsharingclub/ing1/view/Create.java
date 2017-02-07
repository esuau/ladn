
package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.db.PartDAO;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * Part creation view
 */
public class Create extends JFrame {
    private JButton createButton = new JButton("Create");
    private JLabel labelReference = new JLabel("Reference");
    private JLabel labelProvider = new JLabel("Provider");
    private JLabel labelAvailableQuantity = new JLabel("Available quantity");
    private JLabel labelPrice = new JLabel("Price");
    private JLabel labelSpace = new JLabel("");
    private JTextField textReference = new JTextField();
    private JTextField textProvider = new JTextField();
    private JTextField textAvailableQuantity = new JTextField();
    private JTextField textPrice = new JTextField();
    private Listener listener = new Listener();

    /**
     * Sets up UI for part creation
     * <p>
     * It basically includes a form with a submit button <code>createButton</code>
     * </p>
     */
    public Create() {

        GridLayout layout = new GridLayout(5, 2);
        this.setLayout(layout);
        this.add(labelReference);
        this.add(textReference);
        this.add(labelProvider);
        this.add(textProvider);
        this.add(labelAvailableQuantity);
        this.add(textAvailableQuantity);
        this.add(labelPrice);
        this.add(textPrice);
        this.add(labelSpace);
        this.add(createButton);

        createButton.addActionListener(listener);
        this.pack();
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
                    new PartDAO().create(a);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }

        }
    }

}
