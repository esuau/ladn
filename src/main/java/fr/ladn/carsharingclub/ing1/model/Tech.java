package fr.ladn.carsharingclub.ing1.model;

public class Tech {
	int id;
	String nom; 
	String prenom; 
	String tel; 
	String pass;
	String right; 
	
	public Tech(int ide,String nome,String prenome, String tele,String passe,String rights){
		 id=ide;
		nom=nome; 
		prenom=prenome; 
		tel=tele; 
		pass=passe;
		right=rights; 
	}
	public Tech(String nome,String prenome, String tele,String passe,String rights){
		 
		nom=nome; 
		prenom=prenome; 
		tel=tele; 
		pass=passe;
		right=rights; 
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	

}
