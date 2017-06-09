package fr.ladn.carsharingclub.ing1.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.LimitExceededException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

import fr.ladn.carsharingclub.ing1.model.Operation;
import fr.ladn.carsharingclub.ing1.model.Vehicle;
import fr.ladn.carsharingclub.ing1.sockets.Client;

class AddVehicleView extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Create.class.getName());

    /** The client. */
    private Client client;

    private JTextField searchId;
    private JTextField vehicleId;
    private JTextField registrationNumber;
    private JTextField date;

    /**
     * Instantiates the view for vehicle adding.
     *
     * @param client the client singleton.
     */
    AddVehicleView(Client client) {

        // Client initialization.
        this.client = client;
        
        


        JLabel searchByIdLabel = new JLabel("Rechercher par ID :");

        searchId = new JTextField();
        searchId.setColumns(10);

        JSeparator separator = new JSeparator();

        JLabel vehicleIdLabel = new JLabel("ID vehicule");
        JLabel registrationNumberLabel = new JLabel("Immatriculation");

        vehicleId = new JTextField();
        vehicleId.setEditable(false);
        vehicleId.setColumns(10);

        registrationNumber = new JTextField();
        registrationNumber.setEditable(false);
        registrationNumber.setColumns(10);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        
        JButton btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (e.getSource() == btnRechercher) {

                try {
                    if (!searchId.getText().isEmpty()) {
                    	
                    	date.setText(dtf.format(now));
                    	System.out.println("Marche");
                        Integer id = Integer.parseInt(searchId.getText());
                        Vehicle v = client.getVehicle(id);
                        vehicleId.setText("" + v.getId());
                        vehicleId.setText("" + searchId.getText());
                        registrationNumber.setText(v.getRegistrationNumber());
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Le vehicule n'existe pas.");
                    logger.error("Failed to read vehicle. Exception: ");
                }
            }

        });

        JLabel lblDateEntrejjmmaaaa = new JLabel("Date entrée (JJ/MM/AAAA)");

        date = new JTextField();
        date.setEditable(false);
        date.setColumns(10);

        JComboBox panne = new JComboBox();

        JButton btnNewButton = new JButton("Ajouter panne");

        JSeparator separator_1 = new JSeparator();

        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String vId = vehicleId.getText();
//	            String provider = String.valueOf(.getText());
//	            int availableQuantity = Integer.parseInt(String.valueOf(.getText()));
//	            float price = Float.parseFloat(String.valueOf(.getText()));

	                Operation o = new Operation();
	                logger.info("Attempting to create part #" + o.getId() + " in database...");
	                client.createRepairWork(o);
	                JOptionPane.showMessageDialog(null, "La pièce " + o.getId() + " a bien été ajoutée dans la base.");
	            
			}
		});
    
        JButton btnReset = new JButton("Reset");

        btnReset.addActionListener(e -> reset());

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(410)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(panne, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        				.addComponent(registrationNumberLabel)
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        					.addComponent(vehicleIdLabel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblDateEntrejjmmaaaa, Alignment.LEADING)))
        			.addGap(77)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(date)
        				.addComponent(registrationNumber)
        				.addComponent(vehicleId, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(511, Short.MAX_VALUE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1266, Short.MAX_VALUE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(293)
        					.addComponent(searchByIdLabel, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE)
        					.addGap(30)
        					.addComponent(searchId, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        					.addGap(44)
        					.addComponent(btnRechercher, GroupLayout.PREFERRED_SIZE, 125, Short.MAX_VALUE)
        					.addGap(383))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(50)
        					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1222, Short.MAX_VALUE)))
        			.addGap(68))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(430)
        			.addComponent(btnEnregistrer)
        			.addGap(126)
        			.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(560, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(23)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(searchId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnRechercher)
        				.addComponent(searchByIdLabel))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(vehicleIdLabel)
        				.addComponent(vehicleId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(29)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(registrationNumberLabel)
        				.addComponent(registrationNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(26)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblDateEntrejjmmaaaa)
        				.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(32)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(panne, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE))
        			.addGap(45)
        			.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(26)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnReset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnEnregistrer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(375))
        );
        this.setLayout(groupLayout);
        this.setVisible(true);
    }

    /**
     * Resets the form fields.
     */
    private void reset() {
        searchId.setText("");
        vehicleId.setText("");
        registrationNumber.setText("");
        date.setText("");
    }
}
