package fr.ladn.carsharingclub.ing1.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.model.Failure;
import fr.ladn.carsharingclub.ing1.model.Technician;
import fr.ladn.carsharingclub.ing1.sockets.Client;

public class StatsViewCtMoyen extends JPanel {


	/**
	 * Create the panel.
	 */
	 private final static Logger logger = Logger.getLogger(StatsViewCtMoyen.class.getName());

	    /** The client. */
	private Client client;
	public StatsViewCtMoyen(Client client) {
        this.client = client;

		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JTextPane txtpnNombreDoprations = new JTextPane();
		txtpnNombreDoprations.setDisabledTextColor(UIManager.getColor("TextPane.caretForeground"));
		txtpnNombreDoprations.setEnabled(false);
		txtpnNombreDoprations.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnNombreDoprations.setBackground(UIManager.getColor("Panel.background"));
		txtpnNombreDoprations.setText("Coût moyen d'opération");
		
		JButton btnLancer = new JButton("Lancer");
		
		JComboBox comboBox_6 = new JComboBox();
		ArrayList<Failure> lFailures = client.getFailures();
        for (Failure f : lFailures) {
            comboBox_6.addItem(f);
        }
		
		
		JTextPane txtpnPanne = new JTextPane();
		txtpnPanne.setDisabledTextColor(UIManager.getColor("TextPane.caretForeground"));
		txtpnPanne.setEnabled(false);
		txtpnPanne.setText("Panne :");
		txtpnPanne.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnPanne.setBackground(SystemColor.menu);
		
		JComboBox comboBox_7 = new JComboBox();
		ArrayList<Technician> lTechnicians = client.getTechnicians();
        for (Technician t : lTechnicians) {
            comboBox_7.addItem(t);
        }
		JTextPane txtpnTechnicien = new JTextPane();
		txtpnTechnicien.setText("Technicien :");
		txtpnTechnicien.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnTechnicien.setEnabled(false);
		txtpnTechnicien.setDisabledTextColor(Color.BLACK);
		txtpnTechnicien.setBackground(SystemColor.menu);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpnPanne, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnTechnicien, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox_6, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_7, 0, 230, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 292, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtpnNombreDoprations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(631, Short.MAX_VALUE)
					.addComponent(btnLancer)
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnNombreDoprations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(239)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnPanne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnTechnicien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(btnLancer)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}

}
