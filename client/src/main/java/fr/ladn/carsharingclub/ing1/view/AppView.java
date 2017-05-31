package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.sockets.Client;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AppView extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(AppView.class.getName());

    /** The client singleton. */
    private Client client;

    /**
     * AppView constructor.
     */
    public AppView(Client client) {
        super(new GridLayout(1, 1));

        this.client = client;

        logger.info("Initializing application UI.");
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new Monitor(client);
        tabbedPane.addTab("Consulter Operations", null, panel1, "Consulter les information d'une operation.");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JPanel panel2 = new Create(client);
        tabbedPane.addTab("Ajouter une pièce", null, panel2, "Créer une nouvelle référence de pièce.");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JPanel panel3 = new Update(client);
        tabbedPane.addTab("Modifier une pièce", null, panel3, "Modifier une référence de pièce existante.");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JPanel panel4 = new Delete(client);
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Supprimer une pièce", null, panel4, "Supprimer une ou plusieurs pièces.");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        // Add the tabbed pane to this panel.
        add(tabbedPane);

        // The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void display() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CarSharingClub - Maintenance App");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AppView m = new AppView(this.client);
        //  m.setSize(50,100);
        //Add content to the window.
        frame.add(m, BorderLayout.CENTER);

        //Display the window.
        frame.setSize(50, 200);
        frame.pack();

        frame.setVisible(true);
        logger.info("Displayed application GUI.");
    }

}


