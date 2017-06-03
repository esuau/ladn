package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.sockets.Client;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

class AddVehicleView extends JPanel {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(Create.class.getName());

    /** The client. */
    private Client client;

    private JTextField searchId;
    private JTextField vehicleId;
    private JTextField registrationNumber;
    private JTextField date;
    private JTextField operationId;
    private JTextField parkingSpaceNumber;
    private JTextField status;

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

        JLabel statusLabel = new JLabel("Statut");
        JLabel parkingSpaceLabel = new JLabel("Place n°");
        JLabel operationLabel = new JLabel("Reparation n°");

        registrationNumber = new JTextField();
        registrationNumber.setEditable(false);
        registrationNumber.setColumns(10);

        JButton btnRechercher = new JButton("Rechercher");

        JLabel lblDateEntrejjmmaaaa = new JLabel("Date entrée (JJ/MM/AAAA)");

        date = new JTextField();
        date.setEditable(false);
        date.setColumns(10);

        operationId = new JTextField();
        operationId.setEditable(false);
        operationId.setColumns(10);

        parkingSpaceNumber = new JTextField();
        parkingSpaceNumber.setColumns(10);

        status = new JTextField();
        status.setEditable(false);
        status.setColumns(10);

        JComboBox panne = new JComboBox();

        JButton btnNewButton = new JButton("Ajouter panne");

        JSeparator separator_1 = new JSeparator();

        JButton btnEnregistrer = new JButton("Enregistrer");

        JButton btnReset = new JButton("Reset");

        btnReset.addActionListener(e -> reset());

        GroupLayout groupLayout = new GroupLayout(this);
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
                                                                        .addComponent(operationLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(parkingSpaceLabel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(statusLabel))
                                                                .addGap(31))
                                                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                                                .addComponent(panne, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)))
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(operationId, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                                        .addComponent(date, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                        .addComponent(parkingSpaceNumber, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                                        .addComponent(status, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(125)
                                                .addComponent(vehicleIdLabel)
                                                .addGap(38)
                                                .addComponent(vehicleId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(45)
                                                .addComponent(registrationNumberLabel)))
                                .addGap(18)
                                .addComponent(registrationNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(119))
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addGap(90)
                                                .addComponent(searchByIdLabel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(searchId, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(searchByIdLabel)
                                        .addComponent(searchId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnRechercher))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(vehicleId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(vehicleIdLabel)
                                        .addComponent(registrationNumberLabel)
                                        .addComponent(registrationNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(33)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblDateEntrejjmmaaaa)
                                        .addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(33)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(operationLabel, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                                        .addComponent(operationId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(36)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(parkingSpaceLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(parkingSpaceNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(32)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
        operationId.setText("");
        parkingSpaceNumber.setText("");
        status.setText("");
    }
}
