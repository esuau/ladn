package fr.ladn.carsharingclub.ing1.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.sockets.Client;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatsView extends JPanel {

	/**
	 * Create the panel.
	 */
	/** The logger. */
    private final static Logger logger = Logger.getLogger(StatsView.class.getName());

    /** The client. */
    private Client client;

    /** The text field for the part ID. */
    private JTextField textId = new JTextField();
    
	public StatsView(Client client) {
		this.client = client;
		JPanel panel = new JPanel();
		
		panel.setBorder(new LineBorder(UIManager.getColor("TextPane.foreground")));
		
		JPanel panel_1 = new JPanel();
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextPane txtpnChoixDeLindicateur = new JTextPane();
		txtpnChoixDeLindicateur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnChoixDeLindicateur.setDisabledTextColor(Color.BLACK);
		txtpnChoixDeLindicateur.setEnabled(false);
		txtpnChoixDeLindicateur.setBackground(UIManager.getColor("Panel.background"));
		txtpnChoixDeLindicateur.setText("Choix de l'indicateur :");
		
		
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Nombre d'opérations");
		
		JRadioButton rdbtnDureMoyenneDopration = new JRadioButton("Durée moyenne d'opération");
		
		JRadioButton rdbtCoutMoyen = new JRadioButton("Coût moyen d'opération");
		
		JRadioButton rdbtnNbPiece = new JRadioButton("Nombre de pièces consommées");
		
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnNewRadioButton);
        group.add(rdbtnDureMoyenneDopration);
        group.add(rdbtCoutMoyen);
        group.add(rdbtnNbPiece);
        JButton btnValider = new JButton("Valider");
		
		
		JTextPane txtpnSuiviGlobal = new JTextPane();
		txtpnSuiviGlobal.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnSuiviGlobal.setDisabledTextColor(new Color(0, 0, 0));
		txtpnSuiviGlobal.setEnabled(false);
		txtpnSuiviGlobal.setText("| Suivi global de l'activité du dépôt et de ses performances |");
		txtpnSuiviGlobal.setBackground(SystemColor.menu);
		GroupLayout gl_panel = new GroupLayout(panel);
		
		
		
		
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(287)
							.addComponent(txtpnChoixDeLindicateur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpnSuiviGlobal, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnNewRadioButton)
										.addComponent(rdbtnDureMoyenneDopration))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(rdbtnNbPiece)
											.addGap(133)
											.addComponent(btnValider))
										.addComponent(rdbtCoutMoyen))))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtpnSuiviGlobal, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtpnChoixDeLindicateur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnNewRadioButton)
								.addComponent(rdbtCoutMoyen))
							.addPreferredGap(ComponentPlacement.UNRELATED, 50, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnDureMoyenneDopration)
								.addComponent(rdbtnNbPiece))
							.addGap(37))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnValider)
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		logger.info("Displayed stats tab.");
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()){
				panel_1.removeAll();
				JPanel panel2 = new StatsViewNbOp(client);
				panel_1.add(panel2);
				}
				if(rdbtnDureMoyenneDopration.isSelected()){
				panel_1.removeAll();
				JPanel panel2 = new StatsViewTmpsOp(client);
				panel_1.add(panel2);
				}
				if(rdbtCoutMoyen.isSelected()){
					panel_1.removeAll();
					JPanel panel2 = new StatsViewCtMoyen(client);
					panel_1.add(panel2);
					}
				if(rdbtnNbPiece.isSelected()){
					panel_1.removeAll();
					JPanel panel2 = new StatsViewConso(client);
					panel_1.add(panel2);
					}
			}
		});
	}
}
