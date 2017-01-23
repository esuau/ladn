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
 * Part deletion view
 */
public class Delete extends JFrame {

    private JLabel labelId = new JLabel("ID");
    private JTextField textId = new JTextField();
    private JButton deleteButton = new JButton("Delete");
    private Listener listener = new Listener();

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
    public Delete() {
        GridLayout layout = new GridLayout(1, 3);
        this.setLayout(layout);
        this.add(labelId);
        this.add(textId);
        this.add(deleteButton);

        deleteButton.addActionListener(listener);

        this.pack();
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
                    Part a = new PartDAO().read(id);
                    new PartDAO().delete(a);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        }
    }


}
