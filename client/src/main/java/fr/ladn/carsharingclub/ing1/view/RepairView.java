package fr.ladn.carsharingclub.ing1.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.regex.Pattern;

import fr.ladn.carsharingclub.ing1.model.*;
//import fr.ladn.carsharingclub.ing1.sockets.Client;
//import fr.ladn.carsharingclub.ing1.utils.Operation;

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
	private java.sql.Timestamp dateDebut;
	private java.sql.Timestamp dateFin;
	//private Client client;
	
	
	public RepairView() {
		createAndShowGUI();
		//this.client = client;
		this.setVisible(true);
		this.setTitle("Réaliser une opération de maintenance");
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false);
		this.setSize(650,650);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private void createAndShowGUI() {
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
		r = new Reparation(1, "Diagnostiqué", 1, null, null, 2, "2+3+6", "M3X-48-DZ", 23);
		
		if (!r.getStatut_reparation().equals("Suspendu")) {
			this.dateDebut = new java.sql.Timestamp(new Date().getTime());
		}
	    
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
		
		String[] lPannes = r.getId_pannes().split(Pattern.quote("+"));
		
		for(int i = 0; i < lPannes.length; i++) {
			System.out.println(lPannes[i]);
		}
		
		String[] entetesPannes = {"ID Panne", "Nom panne", "Pièces nécessaires"};
		Object[][] donnees = {
			{"Johnathan", "Sykes", "po"},
			{"Nicolas", "Van de Kampf", "po"},
			{"Damien", "Cuthbert", "po"}
		};
		
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
		
		this.getContentPane().add(infos, BorderLayout.NORTH);
		this.getContentPane().add(pannes, BorderLayout.CENTER);
		this.getContentPane().add(commentaire, BorderLayout.SOUTH);
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
					System.out.println(dateDebut);
					dateFin = new java.sql.Timestamp(new Date().getTime());
					System.out.println(dateFin);
					System.exit(0);
				}
			}
		}
	}
}