package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.PartDAO;
import fr.ladn.carsharingclub.ing1.model.Part;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Part update view
 */
class Update extends JPanel {

    private final static Logger logger = Logger.getLogger(Delete.class.getName());
    private ConnectionPool pool;
    private JButton searchButton = new JButton("Search");
    private JButton updateButton = new JButton("Update");
    private JTextField textId = new JTextField();
    private JTextField textReference = new JTextField();
    private JTextField textProvider = new JTextField();
    private JTextField textAvailableQuantity = new JTextField();
    private JTextField textPrice = new JTextField();

    /**
     * Sets up UI for updating a part
     * <p>
     * It includes a form where the user can edit the information of an existing part. The current part data is first displayed in the appropriate fields. The user can then edit one or several of these fields.
     * </p>
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
     * Listens for an action from the button <tt>searchButton</tt>
     * <p>
     * This method first acts like the <tt>Listener</tt> method from the <tt>Read</tt> class but submits the edited values to the database via a <tt>PartDAO</tt> object.
     * </p>
     *
     * @see Read
     */
    private class Listener implements ActionListener {
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

            }
            if (e.getSource() == updateButton) {
                String reference = textReference.getText();
                String provider = textProvider.getText();
                int availableQuantity = Integer.parseInt(textAvailableQuantity.getText());
                float price = Float.parseFloat(textPrice.getText());
                Part a = new Part(id, reference, provider, availableQuantity, price);
                try {
                    new PartDAO(pool).update(a);
                } catch (Exception err) {
                    System.out.println("Exception: " + err.getMessage());
                }
            }


        }
    }

}
