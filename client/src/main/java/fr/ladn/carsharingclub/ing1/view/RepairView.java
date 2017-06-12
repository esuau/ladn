package fr.ladn.carsharingclub.ing1.view;

import fr.ladn.carsharingclub.ing1.model.*;
import fr.ladn.carsharingclub.ing1.sockets.Client;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

/**
 * The RepairView.
 * Manages the view for operation execution.
 */
class RepairView extends JFrame implements ActionListener {

    /** The logger. */
    private final static Logger logger = Logger.getLogger(RepairView.class.getName());

    /** The client. */
    private Client client;

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
    private JComboBox<Part> cbPieces;

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
    private Operation operation;
    private java.sql.Timestamp dateStart;
    private OperationStatus statusStart;

    /**
     * Instantiates the view for operation execution.
     *
     * @param client the client singleton.
     */
    RepairView(Client client) {

        this.client = client;
        
        /* Variables*/
        /* Delete object f1, f2, failures, operation t... here when Evan's function will be implemented here */
        operation = client.getNewOperation();
        // Failure f1 = new Failure(1, "Failure 1", new FailureType(1, "Moteur"), "Instructions", 10);
        // Failure f2 = new Failure(2, "Failure 2", new FailureType(3, "Priority"), "Instructions", 10);
        // Failure[] failures = {f1, f2};
        // operation = new Operation(1, new Vehicle("MZX-YS-34", "Peugeot", "manufacturer", "306"), failures, OperationStatus.DIAGNOSED, "C le pre a pé é boi");
        Technician t = new Technician(1, "Louis", "Endelicher", "00000000", "LOL", TechnicianRights.TECHNICIAN);
        // operation.setTechnician(t);
        
        // We keep in memory the status at the beginning to update the date of the end of this status in reparation_histo_temps
        operation.setOldStatus(operation.getStatus());
        
        
        // ArrayList containning all the parts needed for a failure
        ArrayList<Part> lPartsFailure = new ArrayList<>();
        
        for (Failure f : operation.getFailures()) {
            lPartsFailure.addAll(client.getPartsFailure(f.getId()));
        }
        statusStart = operation.getStatus();

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
        // We put an objet part in the JComboBox to simplify the manipulation/updates of the parts
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<Part> lParts = client.getParts();
        
        for (Part p : lParts) {
            model.addElement(p);
        }
        
        cbPieces = new JComboBox<Part>(model);
        cbPieces.setToolTipText("Sélectionner une pièce");

		/* Definition of JButton*/
        btnValider = new JButton("Valider");
        btnSuspendre = new JButton("Suspendre");
        btnTerminer = new JButton("Terminer");

		/* Definition of JTextArea*/
        // We get the comment of the operation because if it has been previously suspended it could have one
        tCommentaire = new JTextArea(operation.getComment());

		/* Layout*/
        BorderLayout layout = new BorderLayout();
        
        // We get all the failures of the operation
        Failure[] tPannesOperation = operation.getFailures();

        JPanel infos = new JPanel();

        infos.setPreferredSize(new Dimension(650, 100));
        lblTechnicien.setPreferredSize(new Dimension(350, 25));
        infoTechnicien.setPreferredSize(new Dimension(200, 25));
        lblVehicule.setPreferredSize(new Dimension(350, 25));
        infoVehicule.setPreferredSize(new Dimension(200, 25));
        infoVehicule.setText(operation.getVehicle().getRegistrationNumber());
        
        infoTechnicien.setText(t.getFirstName() + " " + t.getLastName());

        infos.setBorder(BorderFactory.createTitledBorder("Informations"));
        infos.add(lblTechnicien);
        infos.add(infoTechnicien);
        infos.add(lblVehicule);
        infos.add(infoVehicule);

        String[] entetesPannes = {"ID Panne", "Nom panne", "ID Pièces nécessaires"};
        Object[][] dataPannes = new Object[operation.getFailures().length][3];
        for (int i = 0; i < operation.getFailures().length; i++) {
            dataPannes[i][0] = operation.getFailures()[i].getId();
            dataPannes[i][1] = operation.getFailures()[i].getName();
            dataPannes[i][2] = setStringPartsFailure(client.getPartsFailure(operation.getFailures()[i].getId()));
        }
        
        Object[][] dataPieces = new Object[lPartsFailure.size()][3];
        int index = 0;
        for (Part p : lPartsFailure) {
            dataPieces[index][0] = p.getId();
            dataPieces[index][1] = client.getPart(p.getId()).getReference();
            dataPieces[index][2] = p.getAvailableQuantity();
            index++;
        }

        String[] entetesPieces = {"ID Pièce", "Nom pièce", "Quantité nécéssaire"};

        JPanel pannes = new JPanel();
        tabPannes = new JTable(dataPannes, entetesPannes);
        tabPieces = new JTable(dataPieces, entetesPieces);
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
        pannes.setPreferredSize(new Dimension(650, 320));
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

        btnValider.addActionListener(this);
        btnSuspendre.addActionListener(this);
        btnTerminer.addActionListener(this);

        this.getContentPane().add(infos, BorderLayout.NORTH);
        this.getContentPane().add(pannes, BorderLayout.CENTER);
        this.getContentPane().add(commentaire, BorderLayout.SOUTH);
        // We put the parkingSpace at -1 because java doesn't accept null as a value for an int, -1 here signifies no parking space so in progress
        operation.setParkingSpace(-1);
        operation.setStatus(OperationStatus.INPROGRESS);
        // We define the timestamp of the beginning of the current status (also the end of the previous status) with the current date
        operation.setDateBS(new java.sql.Timestamp(new Date().getTime()));
        
        // To update the operation status, parkingSpace... in reparation (DB)
        client.updateOperation(operation);
        // To update the previous status in reparation_histo_temps (DB)
        client.updateWorkflow(operation);
        // To create a new status in reparation_histo_temps (DB)
        client.createWorkflow(operation);
        // Now we can put the oldStatus at INPROGRESS to be able to update the date of the end when we'll click on "Suspendre" or "Terminer"
        operation.setOldStatus(operation.getStatus());

        this.setSize(680, 680);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // DO_NOTHING_ON_CLOSE because if you close the JFrame without saving (Suspending/Finishing) the work, it could cause problems in DB with operations INPROGRESS whom are not
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    // We create the string of the parts to display in the first table of the interface
    public String setStringPartsFailure(ArrayList<Part> lParts) {
        String partsForFailure = "";
        int i = 1;
        
        for(Part p : lParts) {
            partsForFailure = partsForFailure + p.getId();
            
            if (i < lParts.size()) {
                partsForFailure = partsForFailure + " - ";
            }
            
            i++;
        }
        
        return partsForFailure;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnValider && !nbPieces.getText().isEmpty()) {
            // We get the item selected with the ComboBox
            Part mPart = (Part) cbPieces.getSelectedItem();
            mPart = client.getPart(mPart.getId());
            int qtPart = Integer.valueOf(nbPieces.getText());
            
            // Manage the space (or not) before putting the information of additional parts in the comment area :  if it is empty we don't put space, if it isn't we put one...
            if (qtPart > 0) {
                String space = "";
                if(!tCommentaire.getText().equals("")) {
                    space = " ";
                }
                
                // We update in DB the stock of the part added
                mPart.setAvailableQuantity(mPart.getAvailableQuantity() - qtPart);
                client.updatePart(mPart);
                    
                tCommentaire.setText(tCommentaire.getText() + space + "+" + qtPart + "x" + mPart);
            }
        }

        if (e.getSource() == btnSuspendre || e.getSource() == btnTerminer) {
            operation.setComment(tCommentaire.getText());
            operation.setDateBS(new java.sql.Timestamp(new Date().getTime()));
            // We get an empty parking space
            operation.setParkingSpace(client.getEmptySpace());

            if (e.getSource() == btnSuspendre) {
                operation.setStatus(OperationStatus.PENDING);
                
                client.updateOperation(operation);
                
                if (!statusStart.equals(OperationStatus.PENDING)) {
                    try {
                        client.updateWorkflow(operation);
                        client.createWorkflow(operation);
                        
                        // We close the JFrame to be able to open another one and call, again Evan's function
                        this.dispose();
                    } catch (Exception exception) {
                        
                    }
                }
            } else if (e.getSource() == btnTerminer) {
                operation.setStatus(OperationStatus.REPARED);
                System.out.println(operation.getStatus());
                    
                ArrayList<Part> lParts = client.getParts();
                    
                ArrayList<Part> lPartsFailure = new ArrayList<>();
                    
                for (Failure f : operation.getFailures()) {
                    lPartsFailure.addAll(client.getPartsFailure(f.getId()));
                }
                 
                
                // We update the stock of the parts we use by default for this operation  
                // We could add "volatile" for lParts to avoid eventual concurential access 
                for(Part p : lParts) {
                    for(Part removeParts : lPartsFailure) {
                        if(p.getId() == removeParts.getId()) {
                            p.setAvailableQuantity(p.getAvailableQuantity() - removeParts.getAvailableQuantity());
                            client.updatePart(p);
                        }
                    }
                }
                    
                try {
                    client.updateOperation(operation);
                    client.updateWorkflow(operation);
                    client.createWorkflow(operation);
                        
                    this.dispose();
                } catch (Exception exception) {
                    
                }
            }
        }
    }
}