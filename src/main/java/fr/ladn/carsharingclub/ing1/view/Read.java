
package fr.ladn.carsharingclub.ing1.view;


import fr.ladn.carsharingclub.ing1.db.PartDAO;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * Part reading view
 */
public class Read extends JPanel {

    private JButton readButton = new JButton("Read");
    private JLabel labelId = new JLabel("ID");
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
     * Sets up a UI to display part information.
     */
    public Read() {
    	this.setName("Read");
        GridLayout layout2 = new GridLayout(5, 3);
        this.setLayout(layout2);
        this.add(labelId);
        this.add(textId);
        this.add(readButton);
        this.add(labelReference);
        this.add(textReference);
        this.add(space);
        this.add(labelProvider);
        this.add(textProvider);
        this.add(space2);
        this.add(labelPrice);
        this.add(textPrice);
        this.add(space3);
        this.add(labelAvailableQuantity);
        this.add(textAvailableQuantity);

        readButton.addActionListener(listener);
        //this.pack();
        this.setVisible(true);
    }

    /**
     * Listens for an action from the button <tt>readButton</tt>
     * <p>
     * This method gets part information from the database via an object <tt>PartDAO</tt> and
     * </p>
     */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Integer id = Integer.parseInt(textId.getText());


            if (e.getSource() == readButton) {

                try {
                    Part a = new PartDAO().read(id);
                    textReference.setText(a.getReference());
                    textProvider.setText(a.getProvider());
                    textPrice.setText(""+a.getPrice());
                    textAvailableQuantity.setText("" + a.getAvailableQuantity());
                } catch (Exception err) {
                	JOptionPane.showMessageDialog(null,"La pièce n'existe pas!!!!");
                    System.out.println(err);
                }

            }

        }
    }


}
