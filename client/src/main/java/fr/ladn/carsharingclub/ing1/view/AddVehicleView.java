package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.model.*;
import fr.ladn.carsharingclub.ing1.sockets.Client;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class AddVehicleView extends JPanel {

    /**
     * The logger.
     */
    private final static Logger logger = Logger.getLogger(Create.class.getName());

    /**
     * The client.
     */
    private Client client;

    /**
     * The vehicle to register.
     */
    private Vehicle vehicle;

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

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date now = new Date();


        JButton btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(e -> {
            if (e.getSource() == btnRechercher) {

                try {
                    if (!searchId.getText().isEmpty()) {

                        date.setText(dateFormat.format(now));
                        System.out.println("Marche");
                        Integer id = Integer.parseInt(searchId.getText());
                        vehicle = client.getVehicle(id);
                        vehicleId.setText("" + vehicle.getId());
                        vehicleId.setText("" + searchId.getText());
                        registrationNumber.setText(vehicle.getRegistrationNumber());
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

        JComboBox<Failure> panne;
        
        DefaultComboBoxModel<Failure> model = new DefaultComboBoxModel<>();
        ArrayList<Failure> fails = client.getFailures();
        
        for (Failure f : fails) {
            model.addElement(f);
        }
        panne = new JComboBox<>(model);
        panne.setToolTipText("Sélectionner une pièce");

        JButton btnNewButton = new JButton("Ajouter panne");

        // The failures to add to the operation.
        ArrayList<Failure> failures =  new ArrayList<>();

        btnNewButton.addActionListener(e -> {
            failures.add((Failure) panne.getSelectedItem());
            logger.info("Adding failure #" + ((Failure) panne.getSelectedItem()).getName() + ".");
        });

        JSeparator separator_1 = new JSeparator();

        // Set operation priorities
        JLabel lblPriorites = new JLabel("Prioritées");

        JComboBox<OperationPriority> comboBox = new JComboBox<>();
        comboBox.addItem(OperationPriority.URGENT);
        comboBox.addItem(OperationPriority.CRITICAL);
        comboBox.addItem(OperationPriority.MAJOR);
        comboBox.addItem(OperationPriority.NORMAL);
        comboBox.addItem(OperationPriority.MINOR);
        comboBox.setToolTipText("Sélectionner une priorité d'opération.");

        JButton btnEnregistrer = new JButton("Enregistrer");

        btnEnregistrer.addActionListener(e -> {
            Failure[] failuresArray = new Failure[failures.size()];
            int i = 0;
            for (Failure failure : failures) {
                failuresArray[i] = failure;
                i++;
            }
            Operation o = new Operation(vehicle, failuresArray, OperationStatus.DIAGNOSED, (OperationPriority) comboBox.getSelectedItem(), new java.sql.Timestamp(now.getTime()));
            logger.info("Attempting to create new operation in database...");
            o = client.createRepairWork(o);
            JOptionPane.showMessageDialog(null, "L'opération " + o.getId() + " a bien été enregistrée.");
        });

        JButton btnReset = new JButton("Reset");

        btnReset.addActionListener(e -> reset());

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
                    .addGap(429)
                    .addComponent(btnEnregistrer)
                    .addGap(127)
                    .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(560, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1266, Short.MAX_VALUE)
                    .addGap(65))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(410)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(panne, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                            .addComponent(registrationNumberLabel)
                            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(vehicleIdLabel, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDateEntrejjmmaaaa, Alignment.LEADING)))
                        .addComponent(lblPriorites, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addGap(77)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date)
                            .addComponent(registrationNumber)
                            .addComponent(vehicleId, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(510, Short.MAX_VALUE))
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
                    .addGap(35)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPriorites))
                    .addGap(37)
                    .addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnEnregistrer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(283))
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
