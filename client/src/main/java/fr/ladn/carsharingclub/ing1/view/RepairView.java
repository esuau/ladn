package fr.ladn.carsharingclub.ing1.view;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.regex.Pattern;

import fr.ladn.carsharingclub.ing1.model.*;
import fr.ladn.carsharingclub.ing1.sockets.Client;

import org.apache.log4j.Logger;

/**
 * The RepairView.
 * Manages the view for operation execution.
 */
class RepairView extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(RepairView.class.getName());

    /** The client. */
    private Client client;

    /* Creation of JLabels*/
    private JLabel lblTechnicien;
    private JLabel lblVehicule;
    private JLabel lblPiecesNecessairesPour;
    private JLabel lblAjouter;

    /* Creation of JTextFields*/
    private JTextField infoTechnicien;
    private JTextField infoVehicule;
    private JTextField nbPieces;

    /* Creation of JComboBox*/
    private JComboBox cbPieces;

    /* Creation of JButtons*/
    private JButton btnValider;
    private JButton btnSuspendre;
    private JButton btnTerminer;

    /* Creation of JTables*/
    private JTable tabPannes;
    private JTable tabPieces;

    /* Creation of JTextArea*/
    private JTextArea tCommentaire;

    /* Creation of variables*/
    private Operation operation;
    private java.sql.Timestamp dateStart;
    private java.sql.Timestamp dateEnd;
    //private Client client;

    /**
     * Instantiates the view for operation execution.
     *
     * @param client the client singleton.
     */
    RepairView(Client client) {

        this.client = client;

        /* Definition of Labels*/
        lblTechnicien = new JLabel("Technicien : ");
        lblVehicule = new JLabel("Véhicule : ");
        lblPiecesNecessairesPour = new JLabel("Pièces nécessaires pour cette opération");
        lblAjouter = new JLabel("Ajouter");

		/* Definition of JTextFields*/
        infoTechnicien = new JTextField(10);
        infoTechnicien.setEditable(false);
        infoVehicule = new JTextField(10);
        infoVehicule.setEditable(false);
        nbPieces = new JTextField(5);

		/* Definition of JComboBox*/
        String[] pieces = new String[]{"Pneu", "Roue", "Moteur"};
        cbPieces = new JComboBox(pieces);
        cbPieces.setToolTipText("Sélectionner une pièce");

		/* Definition of JButton*/
        btnValider = new JButton("Valider");
        btnSuspendre = new JButton("Suspendre");
        btnTerminer = new JButton("Terminer");

		/* Definition of JTextArea*/
        tCommentaire = new JTextArea();

		/* Layout*/
        BorderLayout layout = new BorderLayout();

		/* Variables*/
		Failure f1 = new Failure("Failure 1", new FailureType(1, "Moteur"), "Instructions", Duration.ofHours(10));
        Failure f2 = new Failure("Failure 2", new FailureType(3, "Priority"), "Instructions", Duration.ofHours(10));
        Failure[] failures = {f1, f2};

        operation = new Operation(1, new Vehicle("registration", "brand", "manufacturer", "version"), failures, OperationStatus.DIAGNOSED);

        if (!operation.getStatus().equals(OperationStatus.PENDING)) {
            this.dateStart = new java.sql.Timestamp(new Date().getTime());
        }

        JPanel infos = new JPanel();

        infos.setPreferredSize(new Dimension(650, 100));
        lblTechnicien.setPreferredSize(new Dimension(350, 25));
        infoTechnicien.setPreferredSize(new Dimension(200, 25));
        lblVehicule.setPreferredSize(new Dimension(350, 25));
        infoVehicule.setPreferredSize(new Dimension(200, 25));
        infoVehicule.setText(operation.getVehicle().getRegistrationNumber());

        infos.setBorder(BorderFactory.createTitledBorder("Informations"));
        infos.add(lblTechnicien);
        infos.add(infoTechnicien);
        infos.add(lblVehicule);
        infos.add(infoVehicule);

        String[] entetesPannes = {"ID Panne", "Nom panne", "Pièces nécessaires"};
        Object[][] donnees = new Object[operation.getFailures().length][3];
        for (int i = 0; i < operation.getFailures().length; i++) {
            donnees[i][0] = operation.getFailures()[i].getId();
            donnees[i][1] = operation.getFailures()[i].getName();
            donnees[i][2] = operation.getFailures()[i].getType().getName();
        }

        String[] entetesPieces = {"ID Pièce", "Nom pièce", "Quantité nécéssaire"};

        JPanel pannes = new JPanel();
        tabPannes = new JTable(donnees, entetesPannes);
        tabPieces = new JTable(donnees, entetesPieces);
        JScrollPane spPannes = new JScrollPane(tabPannes);
        JScrollPane spPicesNecessaires = new JScrollPane(tabPieces);

        tabPannes.setBackground(Color.WHITE);
        tabPannes.setSurrendersFocusOnKeystroke(true);
        tabPannes.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabPannes.setGridColor(Color.BLACK);
        tabPannes.setRowHeight(20);
        tabPannes.setColumnSelectionAllowed(false);
        tabPannes.setRowSelectionAllowed(false);
        tabPannes.setCellSelectionEnabled(false);
        tabPannes.setFocusable(false);
        tabPannes.setEnabled(false);
        tabPieces.setBackground(Color.WHITE);
        tabPieces.setSurrendersFocusOnKeystroke(true);
        tabPieces.setBorder(new LineBorder(new Color(0, 0, 0)));
        tabPieces.setGridColor(Color.BLACK);
        tabPieces.setRowHeight(20);
        tabPieces.setColumnSelectionAllowed(false);
        tabPieces.setRowSelectionAllowed(false);
        tabPieces.setCellSelectionEnabled(false);
        tabPieces.setFocusable(false);
        tabPieces.setEnabled(false);
        lblAjouter.setPreferredSize(new Dimension(60, 50));
        cbPieces.setPreferredSize(new Dimension(150, 50));
        nbPieces.setPreferredSize(new Dimension(50, 25));
        btnValider.setPreferredSize(new Dimension(150, 25));

        pannes.setBorder(BorderFactory.createTitledBorder("Opération"));
        pannes.setPreferredSize(new Dimension(650, 315));
        spPannes.setPreferredSize(new Dimension(600, 120));
        tabPannes.setPreferredSize(new Dimension(600, 100));
        pannes.add(tabPannes.getTableHeader());
        pannes.add(spPannes);
        lblPiecesNecessairesPour.setPreferredSize(new Dimension(250, 25));
        pannes.add(lblPiecesNecessairesPour);
        spPicesNecessaires.setPreferredSize(new Dimension(600, 120));
        tabPieces.setPreferredSize(new Dimension(600, 100));
        pannes.add(tabPieces.getTableHeader());
        pannes.add(spPicesNecessaires);
        pannes.add(lblAjouter);
        pannes.add(cbPieces);
        pannes.add(nbPieces);
        pannes.add(btnValider);

        JPanel commentaire = new JPanel();
        commentaire.setPreferredSize(new Dimension(650, 170));
        commentaire.setBorder(BorderFactory.createTitledBorder("Commentaire"));
        JScrollPane spCommentaire = new JScrollPane(tCommentaire);
        spCommentaire.setPreferredSize(new Dimension(550, 100));
        btnSuspendre.setPreferredSize(new Dimension(150, 25));
        btnTerminer.setPreferredSize(new Dimension(150, 25));

        commentaire.add(spCommentaire);
        commentaire.add(btnSuspendre);
        commentaire.add(btnTerminer);

        Listener listener = new Listener();
        btnValider.addActionListener(listener);
        btnSuspendre.addActionListener(listener);
        btnTerminer.addActionListener(listener);

        this.add(infos, BorderLayout.NORTH);
        this.add(pannes, BorderLayout.CENTER);
        this.add(commentaire, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnValider && !nbPieces.getText().isEmpty()) {
                String namePart = String.valueOf(cbPieces.getSelectedItem());
                int qtPart = Integer.valueOf(nbPieces.getText());

                tCommentaire.setText(tCommentaire.getText() + " +" + qtPart + "x" + namePart);
                System.out.println(namePart);
                System.out.println(qtPart);
            }

            if (e.getSource() == btnSuspendre || e.getSource() == btnTerminer) {
                String comment = tCommentaire.getText();
                System.out.println(comment);

                if (e.getSource() == btnSuspendre) {
                    System.out.println("Suspendre");
                } else if (e.getSource() == btnTerminer) {
                    System.out.println("Terminer");
                    System.out.println(dateStart);
                    dateEnd = new java.sql.Timestamp(new Date().getTime());
                    System.out.println(dateEnd);
                    System.exit(0);
                }
            }
        }
    }
}
