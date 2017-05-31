package pds;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class AjouterVehicule extends JPanel {
	private JTextField rechercheId;
	private JTextField idVehicule;
	private JTextField immatriculation;
	private JTextField date;
	private JTextField idReparation;
	private JTextField numPlace;
	private JTextField statut;


	public AjouterVehicule() {
		
		JFrame f = new JFrame();
				
		f.setSize(787, 616);
		
		JLabel lblRechercher = new JLabel("Rechercher par id :");
		
		rechercheId = new JTextField();
		rechercheId.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblIdVehicule = new JLabel("ID vehicule");
		
		JLabel lblImmatriculation = new JLabel("Immatriculation");
		
		idVehicule = new JTextField();
		idVehicule.setEditable(false);
		idVehicule.setColumns(10);
		
		JLabel lblStatut = new JLabel("Statut");
		
		JLabel lblPlaceN = new JLabel("Place N°");
		
		JLabel lblReparationN = new JLabel("Reparation N°");
		
		immatriculation = new JTextField();
		immatriculation.setEditable(false);
		immatriculation.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		
		JLabel lblDateEntrejjmmaaaa = new JLabel("Date entrée (JJ/MM/AAAA)");
		
		date = new JTextField();
		date.setEditable(false);
		date.setColumns(10);
		
		idReparation = new JTextField();
		idReparation.setEditable(false);
		idReparation.setColumns(10);
		
		numPlace = new JTextField();
		numPlace.setColumns(10);
		
		statut = new JTextField();
		statut.setEditable(false);
		statut.setColumns(10);
		
		JComboBox panne = new JComboBox();
		
		JButton btnNewButton = new JButton("Ajouter panne");
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		
		JButton btnReset = new JButton("Reset");
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(f.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(183)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblDateEntrejjmmaaaa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblReparationN, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPlaceN, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStatut))
									.addGap(31))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(panne, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(idReparation, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(date, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addComponent(numPlace, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(statut, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(125)
							.addComponent(lblIdVehicule)
							.addGap(38)
							.addComponent(idVehicule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(lblImmatriculation)))
					.addGap(18)
					.addComponent(immatriculation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(119))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(90)
							.addComponent(lblRechercher, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rechercheId, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRechercher))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))))
					.addGap(68))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(195)
					.addComponent(btnEnregistrer)
					.addGap(81)
					.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(287, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRechercher)
						.addComponent(rechercheId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRechercher))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(idVehicule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdVehicule)
						.addComponent(lblImmatriculation)
						.addComponent(immatriculation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDateEntrejjmmaaaa)
						.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReparationN, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
						.addComponent(idReparation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlaceN, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(numPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatut, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(statut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(panne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(38)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnregistrer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnReset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(43))
		);
		f.getContentPane().setLayout(groupLayout);
		f.setVisible(true);
		
		
		
	}
	
	public void reset(){
		
		rechercheId.setText("");
		idVehicule.setText("");
		immatriculation.setText("");
		date.setText("");
		idReparation.setText("");
		numPlace.setText("");
		statut.setText("");
		
	}
		
	public static void main(String[] args) {
		AjouterVehicule a = new AjouterVehicule();
	}
}
