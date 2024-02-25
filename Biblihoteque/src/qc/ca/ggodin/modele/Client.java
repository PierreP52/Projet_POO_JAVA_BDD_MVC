package qc.ca.ggodin.modele;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;





public class Client {
	private String code;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private ArrayList<Produit> livresEmprunts;
	
	
	
	
	public Client(String nom, String prenom, String adresse, String telephone) throws SQLException {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		genererCode();
	}
	
	public Client(String code) throws SQLException, ClientException{
		String requete = "select * from client where code='" + code + "'";
		ResultSet res = DBManager.executeQuery(requete);
		boolean trouve = false;
		while(res.next()) {
			this.code = res.getString("code");
			nom = res.getString("nom");
			prenom = res.getString("Prenom");
			adresse = res.getString("Adresse");
			telephone = res.getString("courriel");
			trouve = true;
		}
		if(!trouve) {
			throw new ClientException ("Ce client n'existe pas");
		}
	}
	
	private void genererCode() throws SQLException{
		String requete = "select * from nombreClient";
		ResultSet res = DBManager.executeQuery(requete);
		int nombre = 0;
		while(res.next()) {
			nombre = res.getInt("nombre");
		}
		nombre++;
		requete = "update nombreClient set nombre =" + nombre;
		DBManager.executeUpdate(requete);
		if(nombre < 10) {
			code = "BIBL-00" + nombre;
		}
		else if(nombre < 100) {
			code = "BIBL-0" + nombre;
		}
		else {
			code = "BIBL-" + nombre;
		}
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public ArrayList<Produit> getLivresEmprunts() {
		return livresEmprunts;
	}

	public void setLivresEmprunts(ArrayList<Produit> livresEmprunts) {
		this.livresEmprunts = livresEmprunts;
	}

	public String getCode() {
		return code;
	}
	
	public String toString() {
		return "code : " + code + " nom : " + nom + " prenom : " + prenom + " telephone : " + telephone + " Livre emprunté : " + livresEmprunts;
	}
	
	public void enregistrer() throws SQLException{
		String requete = "insert into client values ('" + code + "', '" + nom + "', '" + prenom + "', '" + telephone + "', '" + adresse + "')";
		DBManager.executeUpdate(requete);
	}
	
	
	
	public void emprunter(ArrayList<Produit> liste) throws PretException, SQLException {
	    
		try {
		
		String datePret = "";
		
	    String dateRetour = "";
	    
	    	
	    
		    for (Produit produit : liste) {
		    	if (!produit.getEtat().equals("disponible")) {
		            throw new PretException("Ce produit n'est pas disponible pour l'emprunt : " + produit.getCode());
		        }
		        
		        Pret pret = new Pret(code, produit.getCode(), datePret, dateRetour);
		        pret.enregistrer();
		        
		        produit.setEtat("emprunte");
		        produit.modifier();
		    }
	    }
	    catch(PretException e){
	    	System.out.println("Ce produit n'est pas disponible");
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	 public static ArrayList<Produit> getProduitEmprunts(Client code) throws SQLException, ProduitException {
			ArrayList<Produit> liste = new ArrayList<Produit>();
			String requete = "SELECT * FROM Produit " 
					+ " INNER JOIN Pret ON Produit.code = codeProduit"
					+ "WHERE Produit.etat = 'emprunte' AND Pret.codeClient = '" + code + "'";
			ResultSet res = DBManager.executeQuery(requete);
			
			while (res.next()) {
				
				String codeProduit = res.getString("code");
		        String titre = res.getString("titre");
		        String image = res.getString("image");
		        double prixAchat = res.getDouble("prixAchat");
		        double prixVente = res.getDouble("prixVente");
		        String etat = res.getString("etat");
		        String utilisation = res.getString("utilisation");
				
		        Produit pl = new ProduitConcret(codeProduit, titre, image, prixAchat, prixVente, etat, utilisation);
				liste.add(pl);
			}
			
			return liste;
		}
}
