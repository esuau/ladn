
package fr.ladn.carsharingclub.ing1.view;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
 
public class MainFrame extends JPanel {
    public MainFrame() {
       super(new GridLayout(1, 1));
         
        JTabbedPane tabbedPane = new JTabbedPane();
        //ImageIcon icon = createImageIcon("images/middle.gif");
         
        JPanel panel1 = new Read();
        tabbedPane.addTab("Consulter une pi�ce",null, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        JPanel panel2 = new Create();
        tabbedPane.addTab("Ajouter une pi�ce", null, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
         
        JPanel panel3 = new Update();
        tabbedPane.addTab("modifier une piece", null, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
         
        JPanel panel4 = new Delete();
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("supprimer une pi�ce", null, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
         
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
     
   /* protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
     
    /** Returns an ImageIcon, or null if the path was invalid. */
  /*  protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
     */
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MAINTENANCE WORLD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         MainFrame m=new MainFrame();
       //  m.setSize(50,100);
        //Add content to the window.
        frame.add(m, BorderLayout.CENTER);
         
        //Display the window.
        frame.setSize(50, 200);
        frame.pack();
       
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        createAndShowGUI();
            }
        });
    }
}


