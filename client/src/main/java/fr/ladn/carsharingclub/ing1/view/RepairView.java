package fr.ladn.carsharingclub.ing1.view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import fr.ladn.carsharingclub.ing1.model.*;

public class RepairView extends JFrame {
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
	private Reparation r;
	
	
	public RepairView() {
		initComponents();
		this.setVisible(true);
		this.setTitle("Réaliser une opération de maintenance");
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setSize(650,650);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private void initComponents() {
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
		String[] pieces = new String[]{"Pneu","Roue","Moteur"};
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
		r = new Reparation(1, "diagnostiqué", 1, null, null, 2, "2+3+6", "M3X-48-DZ", 23);
	    
		JPanel infos = new JPanel();
		
		infos.setPreferredSize(new Dimension(650, 100));
		lblTechnicien.setPreferredSize(new Dimension(350, 25));
		infoTechnicien.setPreferredSize(new Dimension(200, 25));
		lblVehicule.setPreferredSize(new Dimension(350, 25));
		infoVehicule.setPreferredSize(new Dimension(200, 25));
		infoVehicule.setText(r.getId_vehicule());
	
		infos.setBorder(BorderFactory.createTitledBorder("Informations"));
		infos.add(lblTechnicien);
		infos.add(infoTechnicien);
		infos.add(lblVehicule);
		infos.add(infoVehicule);
		
		
		String[] entetes = {"ID Panne", "Nom panne", "Pièces nécessaires"};
		Object[][] donnees = {
			{"Johnathan", "Sykes", "po"},
			{"Nicolas", "Van de Kampf", "po"},
			{"Damien", "Cuthbert", "po"}
		};
		
		String[] tPieces = {"Option 1", "Option 2", "Option 3", "Option 4"};
		
		JPanel pannes = new JPanel();
		tabPannes = new JTable(donnees, entetes);
		tabPieces = new JTable(donnees, entetes);
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
		
		this.getContentPane().add(infos, BorderLayout.NORTH);
		this.getContentPane().add(pannes, BorderLayout.CENTER);
		this.getContentPane().add(commentaire, BorderLayout.SOUTH);
    }
}