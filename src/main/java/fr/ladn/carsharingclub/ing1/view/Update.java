
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
 * Part update view
 */
public class Update extends JFrame {
    private JButton searchButton = new JButton("Search");
    private JButton updateButton = new JButton("Update");
    private JLabel labelId = new JLabel("Part number");
    private JLabel labelReference = new JLabel("Reference");
    private JLabel labelProvider = new JLabel("Provider");
    private JLabel labelAvailableQuantity = new JLabel("Available quantity");
    private JLabel labelPrice = new JLabel("Price");
    private JLabel space = new JLabel(" ");
    private JLabel space2 = new JLabel(" ");
    private JLabel space3 = new JLabel(" ");
    private JTextField textId = new JTextField();
    private JTextField textReference = new JTextField();
    private JTextField textProvider = new JTextField();
    private JTextField textAvailableQuantity = new JTextField();
    private JTextField textPrice = new JTextField();

    private Listener listener = new Listener();

    /**
     * Sets up UI for updating a part
     * <p>
     * It includes a form where the user can edit the information of an existing part. The current part data is first displayed in the appropriate fields. The user can then edit one or several of these fields.
     * </p>
     */
    public Update() {
        GridLayout layout2 = new GridLayout(5, 3);
        this.setLayout(layout2);
        this.add(labelId);
        this.add(textId);
        this.add(searchButton);
        this.add(labelReference);
        this.add(textReference);
        this.add(space);
        this.add(labelProvider);
        this.add(textProvider);
        this.add(space2);
        this.add(labelAvailableQuantity);
        this.add(textAvailableQuantity);
        this.add(space3);
        this.add(labelPrice);
        this.add(textPrice);
        this.add(updateButton);

        searchButton.addActionListener(listener);
        updateButton.addActionListener(listener);

        this.pack();
        this.setVisible(true);
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
            Integer id = Integer.parseInt(textId.getText());
            String reference = String.valueOf(textReference.getText());
            String provider = String.valueOf(textProvider.getText());
            int availableQuantity = Integer.parseInt(String.valueOf(textAvailableQuantity.getText()));
            float price = Float.parseFloat(String.valueOf(textPrice.getText()));


            if (e.getSource() == searchButton) {

                try {
                    Part a = new PartDAO().read(id);
                    textReference.setText(a.getReference());
                    textProvider.setText(a.getProvider());
                    textAvailableQuantity.setText("" + a.getAvailableQuantity());
                    textPrice.setText("" + a.getPrice());
                } catch (Exception err) {
                    System.out.println(err);
                }

            }
            if (e.getSource() == updateButton) {
                Part a = new Part(id, reference, provider, availableQuantity, price);
                try {
                    new PartDAO().update(a);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }


        }
    }

}
