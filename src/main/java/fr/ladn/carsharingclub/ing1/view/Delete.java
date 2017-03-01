package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.db.PartDAO;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ladn.carsharingclub.ing1.model.Part;

/**
 * Part deletion view
 */
class Delete extends JPanel {

    private ConnectionPool pool;
    private JTextField textId = new JTextField();
    private JButton deleteButton = new JButton("Delete");

    /**
     * Sets up UI for part deletion
     * <p>
     * This UI includes an single input, getting the ID of the part to be removed.
     * <br>
     * It also includes a button to operate the deletion action.
     * </p>
     *
     * @see PartDAO
     */
    Delete(ConnectionPool p) {
        pool = p;
        GridLayout layout = new GridLayout(5, 2);
        this.setLayout(layout);
        JLabel labelId = new JLabel("ID");
        this.add(labelId);
        this.add(textId);
        this.add(deleteButton);

        Listener listener = new Listener();
        deleteButton.addActionListener(listener);

        this.setVisible(true);
    }

    /**
     * Listens for an event from the button
     * <p>
     *     The push of this button will immediately create a <tt>PartDAO</tt> object and remove the part permanently from the database.
     * </p>
     */
    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Integer id = Integer.parseInt(textId.getText());

            if (e.getSource() == deleteButton) {

                try {
                    String[][] a = new PartDAO(pool).read(id);
                    String reference = a[0][1];
                    String provider =  a[0][2];
                    int availableQuantity = Integer.parseInt( a[0][3]);
                    float price = Float.parseFloat( a[0][4]);
                    Part del = new Part(id, reference, provider, availableQuantity, price);
                    
                    new PartDAO(pool).delete(del);
                } catch (Exception err) {
                	JOptionPane.showMessageDialog(null, "La piece n'existe pas.");
                    System.out.println("Exception delete:" + err.getMessage());
                }
            }
        }
    }


}
