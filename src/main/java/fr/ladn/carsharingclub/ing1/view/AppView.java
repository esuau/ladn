package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;

import javax.naming.CompositeName;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class AppView extends JPanel {

    private ConnectionPool pool;
    JFrame fen;

    /**
     * AppView constructor.
     */
    public AppView(ConnectionPool p,JFrame fen) {
        super(new GridLayout(1, 1));
        this.fen=fen;
        pool = p;

        JTabbedPane tabbedPane = new JTabbedPane();
        
       /* JPanel panel5 = new Tech_view(pool);
        tabbedPane.addTab("ajouter technicien ", null, panel5,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);*/
        
        JPanel panel1 = new Read(pool,fen);
        tabbedPane.addTab("Consulter une piece", null, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JPanel panel2 = new Create(pool);
        tabbedPane.addTab("Ajouter une piece", null, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JPanel panel3 = new Update(pool);
        tabbedPane.addTab("modifier une piece", null, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JPanel panel4 = new Delete(pool);
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("supprimer une piece", null, panel4,
                "Does nothing at all");
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
       
        fen.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        AppView m = new AppView(pool,fen);
        //  m.setSize(50,100);
        //Add content to the window.
        fen.add(m, BorderLayout.CENTER);

        //Display the window.
        fen.setSize(50, 200);
        fen.pack();
        fen.setVisible(true);
    }

}


