package fr.ladn.carsharingclub.ing1.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.db.PartDAO;
import org.apache.log4j.Logger;

/**
 * Part reading view
 */
class Read extends JPanel {

    private final static Logger logger = Logger.getLogger(Delete.class.getName());
    private ConnectionPool pool;
    private JButton readButton = new JButton("Read");
    private JTextField textId = new JTextField();
    private JTextField textReference = new JTextField();
    private JTextField textProvider = new JTextField();
    private JTextField textAvailableQuantity = new JTextField();
    private JTextField textPrice = new JTextField();

    /**
     * Sets up a UI to display part information.
     */
    Read(ConnectionPool p) {
        pool = p;
        this.setName("Read");
        GridLayout layout2 = new GridLayout(5, 3);
        this.setLayout(layout2);
        JLabel labelId = new JLabel("ID");
        this.add(labelId);
        this.add(textId);
        this.add(readButton);
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
        JLabel labelPrice = new JLabel("Price");
        this.add(labelPrice);
        this.add(textPrice);
        JLabel space3 = new JLabel(" ");
        this.add(space3);
        JLabel labelAvailableQuantity = new JLabel("Available quantity");
        this.add(labelAvailableQuantity);
        this.add(textAvailableQuantity);

        Listener listener = new Listener();
        readButton.addActionListener(listener);
        //this.pack();
        this.setVisible(true);
        logger.info("Displayed reading tab.");
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
                    Part a = new PartDAO(pool).read(id);
                    textReference.setText(a.getReference());
                    textProvider.setText(a.getProvider());
                    textPrice.setText("" + a.getPrice());
                    textAvailableQuantity.setText("" + a.getAvailableQuantity());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "La pièce n'existe pas.");
                    logger.error("Failed to read part #" + id + ". Exception: " + err.getMessage());
                }

            }

        }
    }


}
