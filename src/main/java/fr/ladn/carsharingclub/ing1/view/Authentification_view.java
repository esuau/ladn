package fr.ladn.carsharingclub.ing1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import fr.ladn.carsharingclub.ing1.db.AuthentificationDAO;
import fr.ladn.carsharingclub.ing1.db.ConnectionPool;


public class Authentification_view extends JFrame{
	

	    private ConnectionPool pool;
	    
	    private JButton readButton = new JButton("Enter");
	    private JTextField textId = new JTextField();
	    private JTextField textMdp = new JTextField();
	   
	    
	   
	    /**
	     * Sets up a UI to display part information.
	     */
	  public Authentification_view(ConnectionPool p) {
	    	
		  this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		  pool = p;
	        this.setName("Authentification");
	        GridLayout layout2 = new GridLayout(5, 2);
	        this.setLayout(layout2);
	        this.setSize(1000, 500);
	        JLabel labelId = new JLabel("ID");
	        this.add(labelId);
	        this.add(textId);
	        JLabel labelMdp = new JLabel("Mot de passe");
	        this.add(labelMdp);
	        this.add(textMdp);
	        JLabel space6 = new JLabel(" ");
	        this.add(space6);
	        this.add(readButton);

	        Listener listener = new Listener();
	        readButton.addActionListener(listener);
	        //this.pack();
	        this.setVisible(true);
	        
	    }

	    /**
	     * Listens for an action from the button <tt>readButton</tt>
	     * <p>
	     * This method gets part information from the database via an object <tt>PartDAO</tt> and
	     * </p>
	     */
	    private class Listener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        

	            if (e.getSource() == readButton) {

	                try {
	                	
	                	
	                	/*fen.setVisible(false);*/
	                	if(textId.getText().isEmpty()==false){
	                		String id = textId.getText();
	                		String mdp= textMdp.getText();
	                		String[][] donnees =new AuthentificationDAO(pool).read(id); 
	                		
	                        
	                      
	                        if(mdp.equals(donnees[0][1])){
	                        	//ConnectionPool pool = new ConnectionPoolImpl();
	                            JFrame frame = new JFrame("MAINTENANCE WORLD");
	                            AppView view = new AppView(Authentification_view.this.pool,frame);
	                        	Authentification_view.this.setVisible(false);
	                            view.display();
	                        }
	                        else JOptionPane.showMessageDialog(null, "Mot de passe incorrect.");
	                        
	                        
	                		/*Integer id = Integer.parseInt(textId.getText());
	                		Part a=new PartDAO(pool).read(id);
	                		textReference.setText(a.getReference());
	                        textProvider.setText(a.getProvider());
	                        textPrice.setText("" + a.getPrice());
	                        textAvailableQuantity.setText("" + a.getAvailableQuantity());
	*/
	                	}
	                	
	                   
	                 
	                } catch (Exception err) {
	                	
	                    JOptionPane.showMessageDialog(null, "Erreur mdp.");
	                    System.out.println("Exception: " + err.getMessage());
	                }

	            }

	        }
	    }



}
