package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.sockets.Client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * The updated view of the app.
 */
public class MainView extends JPanel implements ListSelectionListener {

    private JLabel picture;
    private JSplitPane splitPane;
    private String[] sideMenuItems = { "Opérations", "Autres" };
    private Client client;

    /**
     * The
     * @param client the utilities for data exchange with server.
     */
    public MainView(Client client) {
        this.client = client;

        JList<String> list = new JList<>(sideMenuItems);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);


        JScrollPane listScrollPane = new JScrollPane(list);
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane pictureScrollPane = new JScrollPane(picture);

        //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, pictureScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);

        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(400, 200));
        updateLabel(sideMenuItems[list.getSelectedIndex()]);
    }

    //Listens to the list
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        updateLabel(sideMenuItems[list.getSelectedIndex()]);
    }

    //Renders the selected image
    private void updateLabel(String name) {
        ImageIcon icon = createImageIcon("images/" + name + ".gif");
        picture.setIcon(icon);
        if  (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }

    private JSplitPane getSplitPane() {
        return splitPane;
    }


    /** Returns an ImageIcon, or null if the path was invalid. */
    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Creates the GUI and show it.
     */
    private void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("MainView");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainView mainView = new MainView(this.client);
        frame.getContentPane().add(mainView.getSplitPane());

        //Display the window.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void display() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            createAndShowGUI();
        });
    }

}