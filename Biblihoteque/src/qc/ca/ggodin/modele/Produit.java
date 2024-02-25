package qc.ca.ggodin.modele;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Produit {
	
	
	protected String code;
	protected String titre ;
	protected String image;
	protected double prixAchat;
	protected double prixVente;
	protected String etat;
	protected String utilisation;
	
	
	public Produit(String titre, String image, double prixAchat, double prixVente, String etat, String utilisation) throws SQLException, ProduitException{
		
		this.titre = titre;
		this.image = image;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.etat = etat;
		this.utilisation = utilisation;
		this.code = "";
		
	}
	
	public Produit(String code, String titre, String image, double prixAchat, double prixVente, String etat, String utilisation) throws SQLException{
		this.code=code;
		this.titre = titre;
		this.image = image;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.etat = etat;
		this.utilisation = utilisation;
		
	}
	
	public Produit(String code) throws SQLException, ProduitException{
		String requete = "select * from Produit where code='" + code + "'";
		ResultSet res = DBManager.executeQuery(requete);
		boolean trouve = false;
		while(res.next()) {
			this.code = res.getString("code");
			titre = res.getString("titre");
			image = res.getString("image");
			prixAchat = res.getDouble("prixAchat");
			prixVente = res.getDouble("prixVente");
			etat = res.getString("etat");
			utilisation = res.getString("utilisation");
			trouve = true;
		}
		if(!trouve) {
			throw new ProduitException ("Ce produit n'existe pas");
		}
	}
	
	protected abstract void genererCode() throws ProduitException, SQLException;
	
	
	
	
	
	public void enregistrer() throws SQLException, ProduitException{
		
		String requete = "insert into Produit values ('" + code + "', '" + titre + "', '" + image + "', '" + prixAchat + "', '" + prixVente + "', '" + etat + "', '" + utilisation + "')";
		DBManager.executeUpdate(requete);
	}
	
	
	
	

	public void setCode(String code) {
		this.code = code;
	}

	public void modifier() throws SQLException{
		String requete = "update Produit set titre ='" + titre + "', image='" + image + "', prixAchat= '" + prixAchat + "', prixVente='" + prixVente + "', etat='"	+ etat + "' , utilisation='"	+ utilisation + "'where code='" + code + "'";
		DBManager.executeUpdate(requete);

	}
	
	public void supprimer() throws SQLException{
		String requete = "delete from Produit WHERE code='" + code + "'";
		DBManager.executeUpdate(requete);
		
		}
	
	 
	
	public static ArrayList<Produit> getListProduitEmprunts() throws SQLException, ProduitException{
		ArrayList<Produit> liste = new ArrayList<Produit>();
		
		
			String requete = "select * from Produit where etat = 'emprunte'";
					
			ResultSet res = DBManager.executeQuery(requete);
			
			while (res.next()) {
				String code = res.getString("code");
				String titre = res.getString("titre");
				String image = res.getString("image");
				double prixAchat = res.getDouble("prixAchat");
				double prixVente = res.getDouble("prixVente");
				String etat = res.getString("etat");
				String utilisation = res.getString("utilisation");
				Produit pl = new ProduitConcret(code, titre,  image, prixAchat, prixVente, etat, utilisation);
				liste.add(pl);

			}
		return liste;
		
	}
	
	public static ArrayList<Produit> getListProduit(HashMap < String, String > criteres) throws SQLException{		
		ArrayList<Produit> liste = new ArrayList<Produit>();
		String requete = "select * from Produit";
		
		if(!criteres.isEmpty()) {
			requete += " where ";
			boolean premierCritere = true;
			
			for (String champ : criteres.keySet()) {
                String valeur = criteres.get(champ);

                if (premierCritere) {
                    requete += champ + " = '" + valeur + "'";
                    premierCritere = false;
                } else {
                    requete += " AND " + champ + " = '" + valeur + "'";
                }
            }	
		}
			
			ResultSet res = DBManager.executeQuery(requete);

			while (res.next()) {
				String code = res.getString("code");
				String titre = res.getString("titre");
				String image = res.getString("image");
				double prixAchat = res.getDouble("prixAchat");
				double prixVente = res.getDouble("prixVente");
				String etat = res.getString("etat");
				String utilisation = res.getString("utilisation");
				Produit pl = new ProduitConcret(code, titre,  image, prixAchat, prixVente, etat, utilisation);
				liste.add(pl);
			}
			return liste;
			
	}
	
	public String toString() {
		return "code :" +code + " titre : " + titre + " image : " + image + " prixAchat : " + prixAchat + " prixVente : " + prixVente + " etat : " + etat + " utilisation : " + utilisation;
	}

	public String getCode() {
		return code;
	}

	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getUtilisation() {
		return utilisation;
	}

	public void setUtilisation(String utilisation) {
		this.utilisation = utilisation;
	}


	
	
}
