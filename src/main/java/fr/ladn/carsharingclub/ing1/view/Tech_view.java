package fr.ladn.carsharingclub.ing1.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;

import fr.ladn.carsharingclub.ing1.db.TechDAO;

import fr.ladn.carsharingclub.ing1.model.Tech;

public class Tech_view extends JPanel {
	 private ConnectionPool pool;
	    private JButton createButton = new JButton("Create");
	    private JTextField textNom = new JTextField();
	    private JTextField textPrenom = new JTextField();
	    private JTextField textTel = new JTextField();
	    private JTextField textMdp = new JTextField();
	    private JTextField textRights = new JTextField();
	    

	    /**
	     * Sets up UI for part creation
	     * <p>
	     * It basically includes a form with a submit button <code>createButton</code>
	     * </p>
	     */
	   public Tech_view(ConnectionPool p) {
	        pool = p;
	        GridLayout layout = new GridLayout(6, 2);
	        this.setLayout(layout);
	        JLabel labelNom = new JLabel("Nom");
	        this.add(labelNom);
	        this.add(textNom);
	        JLabel labelPrenom = new JLabel("Prenom");
	        this.add(labelPrenom);
	        this.add(textPrenom);
	        JLabel labelTel = new JLabel("Telephone");
	        this.add(labelTel);
	        this.add(textTel);
	        JLabel labelMdp = new JLabel("Mot de Passe");
	        this.add(labelMdp);
	        this.add(textMdp);
	        JLabel labelRight = new JLabel("Droit (technicien ou chef d'atelier)");
	        this.add(labelRight);
	        this.add(textRights);
	        JLabel labelSpace = new JLabel("");
	        this.add(labelSpace);
	        this.add(createButton);

	        Listener listener = new Listener();
	        createButton.addActionListener(listener);
	        this.setVisible(true);
	    }

	   public void refresh(){
		   textNom.setText("");
		   textPrenom.setText("");
           textTel.setText("");
           textMdp.setText("");
           textRights.setText("");
	   }
	    /**
	     * This method listens for an event from the createButton
	     * <p>
	     * Once the button is pressed, data in the form is transmitted to the <tt>PartDAO</tt> object to be written in the database.
	     * </p>
	     *
	     * @see PartDAO
	     */
	    private class Listener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            String nom = textNom.getText();
	            String prenom = textPrenom.getText();
	            String tel = textTel.getText();
	            String mdp = textMdp.getText();
	            String right = textRights.getText();
	            

	            if (e.getSource() == createButton) {
	                Tech a = new Tech(nom,prenom,tel,mdp,right);
	                try {
	                    new TechDAO(pool).create(a);
	                    Tech_view.this.refresh();
	                } catch (Exception err) {
	                    System.out.println("Exception: " + err.getMessage());
	                }
	            }

	        }
	    }

}
