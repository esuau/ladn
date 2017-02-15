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
        GridLayout layout = new GridLayout(1, 3);
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
                    Part a = new PartDAO(pool).read(id);
                    new PartDAO(pool).delete(a);
                } catch (Exception err) {
                    System.out.println("Exception:" + err.getMessage());
                }
            }
        }
    }


}
