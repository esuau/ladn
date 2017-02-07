
package fr.ladn.carsharingclub.ing1.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main view (prototype)
 */
public class MainFrame extends JFrame {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu partMenu = new JMenu("Part");
    private JMenuItem itemCreate = new JMenuItem("Create");
    private JMenuItem itemRead = new JMenuItem("Read");
    private JMenuItem itemUpdate = new JMenuItem("Update");
    private JMenuItem itemDelete = new JMenuItem("Delete");
    private JMenuItem itemExit = new JMenuItem("Exit");
    private Listener listener = new Listener();

    /**
     * <p>
     * Sets up the main window of the prototype.
     * <br>
     * This window includes a menu which includes all the commands to interacts with the database.
     * </p>
     */
    public MainFrame() {
        this.setTitle("Workshop manager");

        partMenu.add(itemCreate);
        partMenu.add(itemRead);
        partMenu.add(itemUpdate);
        partMenu.add(itemDelete);
        partMenu.addSeparator();
        partMenu.add(itemExit);

        itemCreate.addActionListener(listener);
        itemRead.addActionListener(listener);
        itemUpdate.addActionListener(listener);
        itemDelete.addActionListener(listener);
        itemExit.addActionListener(listener);

        menuBar.add(partMenu);
        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);

    }

    /**
     * <p>
     * Listens for an action from the menu. Selecting a menu item will open a pop-up window.
     * </p>
     */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == itemCreate) {
                new Create();
            }
            if (e.getSource() == itemRead) {
                new Read();
            }
            if (e.getSource() == itemUpdate) {
                new Update();
            }
            if (e.getSource() == itemDelete) {
                new Delete();
            }
            if (e.getSource() == itemExit) {
                System.exit(0);
            }

        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
