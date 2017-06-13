package fr.ladn.carsharingclub.ing1.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.ladn.carsharingclub.ing1.model.Failure;
import fr.ladn.carsharingclub.ing1.model.ParamNbOp;
import fr.ladn.carsharingclub.ing1.model.Technician;
import fr.ladn.carsharingclub.ing1.sockets.Client;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.sql.Date;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

public class StatsResFrame extends JFrame {

	private JPanel contentPane;
	private Client client;
	

	/**
	 * Create the frame for the result.
	 */
	public StatsResFrame(Client client, Date début, Date fin, Technician tech, Failure fail) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 869, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setDisabledTextColor(UIManager.getColor("TextPane.foreground"));
		textPane.setEnabled(false);
		textPane.setText("| Résultat |");
		textPane.setFont(new Font("Tahoma", Font.BOLD, 18));
		textPane.setBackground(SystemColor.menu);
		
		
		ParamNbOp param = new ParamNbOp(début,fin,tech,fail);
				
		 String[] entetesRes = {"Technicien", "Panne", "Date de début","Date de fin","Nombre d'opérations"};
		 Integer res = client.getNbOp(param);
	     Object[][] dataRes = new Object[1][5];

		 if(res!=null){
	            dataRes[0][0] = tech.toString();
	            dataRes[0][1] = fail.getName();
	            dataRes[0][2] = début;
	            dataRes[0][3] = fin;
	            dataRes[0][4] = client.getNbOp(param);
		 }else{
	            dataRes[0][0] = tech.toString();
	            dataRes[0][1] = fail.getName();
	            dataRes[0][2] = début;
	            dataRes[0][3] = fin;
	            dataRes[0][4] = "données inexistantes pour la sélection"; 
		 }
	            JTable tabbedPane = new JTable(dataRes,entetesRes);
		 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 813, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		
		contentPane.setLayout(gl_contentPane);
	}
}
