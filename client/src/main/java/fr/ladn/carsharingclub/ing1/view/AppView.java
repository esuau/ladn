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

        JPanel panel1 = new MonitorView(client);
        tabbedPane.addTab("Consulter une opérations", null, panel1, "Consulter les information d'une operation.");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JPanel panel2 = new AddVehicleView(client);
        tabbedPane.addTab("Ajouter un véhicule", null, panel2, "Ajouter un véhicule entrant.");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JPanel panel3 = new RepairView(client);
        tabbedPane.addTab("Opérations", null, panel3, "Réaliser une opération de maintenance.");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JPanel panel5 = new StatsView(client);
        panel5.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Statistiques", null, panel5, "Consulter les statistiques de l'activité du dépôt");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        // Add the tabbed pane to this panel.
        add(tabbedPane);

        
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

        m.setSize(650, 650);

        // Add content to the window.
        frame.add(m, BorderLayout.CENTER);

        //Display the window.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();

        // Set main view visible.
        frame.setVisible(true);
        logger.info("Displayed application GUI.");
    }

}


