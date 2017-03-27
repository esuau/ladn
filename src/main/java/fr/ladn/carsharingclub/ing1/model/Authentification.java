package fr.ladn.carsharingclub.ing1.model;

public class Authentification {
	String id;
	String mdp;
	
	public Authentification(String ident, String mot){
		id=ident;
		mdp=mot;
	}
	
	public String get_id(){
		return this.id;
	}
	
	public String get_mdp(){
		return this.mdp;
	}
	
	public void set_mdp(String ident){
		id=ident;
	}

}
