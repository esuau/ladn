package fr.ladn.carsharingclub.ing1.view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.model.Failure;
import fr.ladn.carsharingclub.ing1.model.Technician;
import fr.ladn.carsharingclub.ing1.sockets.Client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StatsViewNbOp extends JPanel {

	/**
	 * Create the panel.
	 */
	 private final static Logger logger = Logger.getLogger(StatsViewNbOp.class.getName());

	    /** The client. */
	private Client client;
	public StatsViewNbOp(Client client) {
        this.client = client;

		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JTextPane txtpnNombreDoprations = new JTextPane();
		txtpnNombreDoprations.setDisabledTextColor(UIManager.getColor("TextPane.caretForeground"));
		txtpnNombreDoprations.setEnabled(false);
		txtpnNombreDoprations.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnNombreDoprations.setBackground(UIManager.getColor("Panel.background"));
		txtpnNombreDoprations.setText("Nombre d'opérations");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setLayout(new GridLayout(2, 4, 2, 2));
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setDisabledTextColor(UIManager.getColor("TextPane.foreground"));
		textPane.setText("Jour");
		panel_1.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEnabled(false);
		textPane_1.setDisabledTextColor(UIManager.getColor("TextPane.foreground"));
		textPane_1.setText("Mois");
		panel_1.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEnabled(false);
		textPane_2.setDisabledTextColor(UIManager.getColor("TextPane.foreground"));
		textPane_2.setText("Année");
		panel_1.add(textPane_2);
		
		JComboBox comboBox_3 = new JComboBox();
		panel_1.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		panel_1.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		panel_1.add(comboBox_5);
		ArrayList<Integer> mois = new ArrayList<Integer>();
		mois.add(1);
		mois.add(2);
		mois.add(3);
		mois.add(4);
		mois.add(5);
		mois.add(6);
		mois.add(7);
		mois.add(8);
		mois.add(9);
		mois.add(10);
		mois.add(11);
		mois.add(12);
		ArrayList<Integer> année = new ArrayList<Integer>();
		for(int i=2017; i<=2099;i++){
			année.add(i);
		}
		ArrayList<Integer> jour1 = new ArrayList<Integer>();
		for(int i=1; i<=31;i++){
			jour1.add(i);
		}
		ArrayList<Integer> jour2 = new ArrayList<Integer>();
		for(int i=1; i<=30;i++){
			jour2.add(i);
		}
		ArrayList<Integer> jour3 = new ArrayList<Integer>();
		for(int i=1; i<=28;i++){
			jour3.add(i);
		}
		ArrayList<Integer> jour4 = new ArrayList<Integer>();
		for(int i=1; i<=29;i++){
			jour4.add(i);
		}
		comboBox_4.addItem(null);
		for(int m: mois){
			comboBox_4.addItem(m);
		}
		comboBox_5.addItem(null);
		for(int a: année){
			comboBox_5.addItem(a);
		}
		
		comboBox_4.addItemListener(new ItemListener(){	
			public void itemStateChanged(ItemEvent e){

				if(((Integer)comboBox_4.getSelectedItem()==1)||((Integer)comboBox_4.getSelectedItem()==3)||((Integer)comboBox_4.getSelectedItem()==5)||((Integer)comboBox_4.getSelectedItem()==7)||((Integer)comboBox_4.getSelectedItem()==8)||((Integer)comboBox_4.getSelectedItem()==10)||((Integer)comboBox_4.getSelectedItem()==12)){
					comboBox_3.removeAllItems();
					for(int j : jour1){
						comboBox_3.addItem(j);
						logger.info("selected 1st type of months");


					}
					comboBox_3.repaint();
				}
				if(((Integer)comboBox_4.getSelectedItem()==4)||((Integer)comboBox_4.getSelectedItem()==6)||((Integer)comboBox_4.getSelectedItem()==9)||((Integer)comboBox_4.getSelectedItem()==11)){
					comboBox_3.removeAllItems();
					for(int j : jour2){
						comboBox_3.addItem(j);
						logger.info("selected 2nd type of months");

					}
					comboBox_3.repaint();
				}
				if((Integer)comboBox_4.getSelectedItem()==2){
					comboBox_3.removeAllItems();
					if((Integer)comboBox_5.getSelectedItem()%4==0)
						logger.info("selected february29");
						for(int j : jour4){
						comboBox_3.addItem(j);
						

					}
					
					comboBox_3.repaint();
					
				}

				if((Integer)comboBox_4.getSelectedItem()==2){
					comboBox_3.removeAllItems();
					if((Integer)comboBox_5.getSelectedItem()%4!=0)
						logger.info("selected february28");

					for(int j : jour3){
						comboBox_3.addItem(j);
					
					}
					
					comboBox_3.repaint();
					
				}
}
			});
		comboBox_5.addItemListener(new ItemListener(){	
			public void itemStateChanged(ItemEvent e){

				if(((Integer)comboBox_5.getSelectedItem()%4==0)&&((Integer)comboBox_4.getSelectedItem()==2)){
					comboBox_3.removeAllItems();
					logger.info("selected year bissectial");

					for(int j : jour4){
						comboBox_3.addItem(j);


					}
					comboBox_3.repaint();
				}
				if(((Integer)comboBox_5.getSelectedItem()%4!=0)&&((Integer)comboBox_4.getSelectedItem()==2)){
					comboBox_3.removeAllItems();
					logger.info("selected year non-bissectial");

					for(int j : jour3){
						comboBox_3.addItem(j);


					}
					comboBox_3.repaint();
				}
			}
		});
		JTextPane txtpnDateDeDbut = new JTextPane();
		txtpnDateDeDbut.setDisabledTextColor(UIManager.getColor("TextPane.caretForeground"));
		txtpnDateDeDbut.setEnabled(false);
		txtpnDateDeDbut.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnDateDeDbut.setBackground(UIManager.getColor("Panel.background"));
		txtpnDateDeDbut.setText("Date de début :");
		
		JTextPane txtpnDateDeFin = new JTextPane();
		txtpnDateDeFin.setDisabledTextColor(UIManager.getColor("TextPane.caretForeground"));
		txtpnDateDeFin.setEnabled(false);
		txtpnDateDeFin.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnDateDeFin.setText("Date de fin :");
		txtpnDateDeFin.setBackground(SystemColor.menu);
		
		JTextPane txtpnSiVous = new JTextPane();
		txtpnSiVous.setDisabledTextColor(UIManager.getColor("TextPane.caretForeground"));
		txtpnSiVous.setEnabled(false);
		txtpnSiVous.setBackground(UIManager.getColor("Panel.background"));
		txtpnSiVous.setText("- Si vous ne sélectionner pas de dates, par défaut, c'est le nombre d'opérations par semaine depuis le début de l'année qui sera affiché -");
		
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
						.addComponent(btnLancer, Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpnDateDeDbut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnPanne, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnDateDeFin, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnTechnicien, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboBox_6, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
										.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtpnSiVous, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtpnNombreDoprations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnNombreDoprations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addComponent(txtpnDateDeDbut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(44)
									.addComponent(txtpnDateDeFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(61)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnPanne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnTechnicien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtpnSiVous, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(btnLancer)
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(2, 4, 2, 2));
		
		JTextPane txtpnJour = new JTextPane();
		txtpnJour.setEnabled(false);
		txtpnJour.setDisabledTextColor(UIManager.getColor("TextPane.foreground"));
		panel.add(txtpnJour);
		txtpnJour.setText("Jour");
		
		JTextPane txtpnMois = new JTextPane();
		txtpnMois.setEnabled(false);
		txtpnMois.setDisabledTextColor(UIManager.getColor("TextPane.foreground"));
		txtpnMois.setText("Mois");
		panel.add(txtpnMois);
		
		JTextPane txtpnAnne = new JTextPane();
		txtpnAnne.setEnabled(false);
		txtpnAnne.setDisabledTextColor(UIManager.getColor("TextPane.foreground"));
		txtpnAnne.setText("Année");
		panel.add(txtpnAnne);
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		
		JComboBox comboBox_2 = new JComboBox();
		panel.add(comboBox_2);
		setLayout(groupLayout);
		comboBox.addItem(null);
		for(int m: mois){
			comboBox.addItem(m);
		}
		comboBox_2.addItem(null);
		for(int a: année){
			comboBox_2.addItem(a);
		}

		comboBox.addItemListener(new ItemListener(){	
			public void itemStateChanged(ItemEvent e){

				if(((Integer)comboBox.getSelectedItem()==1)||((Integer)comboBox.getSelectedItem()==3)||((Integer)comboBox.getSelectedItem()==5)||((Integer)comboBox.getSelectedItem()==7)||((Integer)comboBox.getSelectedItem()==8)||((Integer)comboBox.getSelectedItem()==10)||((Integer)comboBox.getSelectedItem()==12)){
					comboBox_1.removeAllItems();
					for(int j : jour1){
						comboBox_1.addItem(j);
						logger.info("selected 1st type of months");


					}
					comboBox_1.repaint();
				}
				if(((Integer)comboBox.getSelectedItem()==4)||((Integer)comboBox.getSelectedItem()==6)||((Integer)comboBox.getSelectedItem()==9)||((Integer)comboBox.getSelectedItem()==11)){
					comboBox_1.removeAllItems();
					for(int j : jour2){
						comboBox_1.addItem(j);
						logger.info("selected 2nd type of months");

					}
					comboBox_1.repaint();
				}
				if((Integer)comboBox.getSelectedItem()==2){
					comboBox_1.removeAllItems();
					if((Integer)comboBox_2.getSelectedItem()%4==0)
						logger.info("selected february29");
						for(int j : jour4){
						comboBox_1.addItem(j);
						

					}
					
					comboBox_1.repaint();
					
				}

				if((Integer)comboBox.getSelectedItem()==2){
					comboBox_1.removeAllItems();
					if((Integer)comboBox_2.getSelectedItem()%4!=0)
						logger.info("selected february28");

					for(int j : jour3){
						comboBox_1.addItem(j);
					
					}
					
					comboBox_1.repaint();
					
				}
}
			});
		comboBox_2.addItemListener(new ItemListener(){	
			public void itemStateChanged(ItemEvent e){

				if(((Integer)comboBox_2.getSelectedItem()%4==0)&&((Integer)comboBox.getSelectedItem()==2)){
					comboBox_1.removeAllItems();
					logger.info("selected year bissectial");

					for(int j : jour4){
						comboBox_1.addItem(j);


					}
					comboBox_1.repaint();
				}
				if(((Integer)comboBox_2.getSelectedItem()%4!=0)&&((Integer)comboBox.getSelectedItem()==2)){
					comboBox_1.removeAllItems();
					logger.info("selected year non-bissectial");

					for(int j : jour3){
						comboBox_1.addItem(j);


					}
					comboBox_1.repaint();
				}
			}
		});
				
		/**
		 * Launch the result.
		 */
		btnLancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Date début = new Date((Integer)comboBox_1.getSelectedItem(), (Integer)comboBox.getSelectedItem(),(Integer)comboBox_2.getSelectedItem());
						Date fin = new Date((Integer)comboBox_3.getSelectedItem(), (Integer)comboBox_4.getSelectedItem(),(Integer)comboBox_5.getSelectedItem());
						StatsResFrame frame = new StatsResFrame(client, début, fin,(Technician)comboBox_7.getSelectedItem(),(Failure)comboBox_6.getSelectedItem());
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		});
	}
}
