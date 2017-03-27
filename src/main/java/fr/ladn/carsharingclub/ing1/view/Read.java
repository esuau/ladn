package fr.ladn.carsharingclub.ing1.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.ladn.carsharingclub.ing1.db.ConnectionPool;
import fr.ladn.carsharingclub.ing1.model.Part;
import fr.ladn.carsharingclub.ing1.db.PartDAO;

/**
 * Part reading view
 */
class Read extends JPanel {

    private ConnectionPool pool;
    private JButton readButton = new JButton("Read");
    private JTextField textId = new JTextField();
    
    JFrame fen;
    
   
    /**
     * Sets up a UI to display part information.
     */
    Read(ConnectionPool p,JFrame fen) {
    	this.fen=fen;
        pool = p;
        this.setName("Read");
        GridLayout layout2 = new GridLayout(5, 3);
        this.setLayout(layout2);
        JLabel labelId = new JLabel("ID");
        this.add(labelId);
        this.add(textId);
       
        JLabel space6 = new JLabel(" ");
        this.add(space6);
        this.add(readButton);
        JLabel space = new JLabel(" ");
        this.add(space);
        JLabel space5 = new JLabel(" ");
        this.add(space5);
        JLabel space2 = new JLabel(" ");
        this.add(space2);
        JLabel space7 = new JLabel(" ");
        this.add(space7);
        JLabel space3 = new JLabel(" ");
        this.add(space3);
        JLabel space8 = new JLabel(" ");
        this.add(space8);

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
                		Integer id = Integer.parseInt(textId.getText());
                		JFrame fenetre=new JFrame();
                    	fenetre.setTitle("djodjo");
                		Object[][] donnees =new PartDAO(pool).read(id); 
                        String[] entete = {"id_piece","libelle_piece","fabricant","qte_dispo","prix"};
                        JTable tableau = new JTable(donnees, entete);
                        JScrollPane menuder=new JScrollPane(tableau);
                        fenetre.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
                        fenetre.getContentPane().add(menuder, BorderLayout.CENTER);
                        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        fenetre.pack();
                        fen.pack();
                        fen.setVisible(true);
                        fenetre.setVisible(true);
                        if(donnees.length==0)  JOptionPane.showMessageDialog(null, "La piece n'existe pas.");
                        
                        
                		/*Integer id = Integer.parseInt(textId.getText());
                		Part a=new PartDAO(pool).read(id);
                		textReference.setText(a.getReference());
                        textProvider.setText(a.getProvider());
                        textPrice.setText("" + a.getPrice());
                        textAvailableQuantity.setText("" + a.getAvailableQuantity());
*/
                	}
                	else{

                
                	JFrame fenetre=new JFrame();
                	fenetre.setTitle("djodjo");
                	Object[][] donnees =new PartDAO(pool).read_t(); 
                    String[] entete = {"id_piece","libelle_piece","fabricant","qte_dispo","prix"};
                    JTable tableau = new JTable(donnees, entete);
                    JScrollPane menuder=new JScrollPane(tableau);
                    fenetre.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
                    fenetre.getContentPane().add(menuder, BorderLayout.CENTER);
                    fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fenetre.pack();
                    fen.pack();
                    fen.setVisible(true);
                    fenetre.setVisible(true);
                    
                	}
                   
                 
                } catch (Exception err) {
                	
                    JOptionPane.showMessageDialog(null, "La piece n'existe pas.");
                    System.out.println("Exception: " + err.getMessage());
                }

            }

        }
    }


}
