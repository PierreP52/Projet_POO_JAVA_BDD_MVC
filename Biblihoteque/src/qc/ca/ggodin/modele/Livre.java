package qc.ca.ggodin.modele;

import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Livre extends Produit{
	
	protected String code = "";
	protected String auteur;
	protected String editeur;
	protected String collection;
	protected String langue;
	protected Date datePublication;
	protected String isbn;
	
	
	public Livre(String code, String titre, String image, double prixAchat, double prixVente, String etat,
			String utilisation, String auteur, String editeur, String collection, String langue, Date datePublication, String isbn) throws SQLException {
		super(code, titre, image, prixAchat, prixVente, etat, utilisation);
		this.auteur = auteur;
		this.editeur = editeur;
		this.collection = collection;
		this.langue = langue;
		this.datePublication = datePublication;
		this.isbn = isbn;
		
	}
	
	public Livre(String code) throws SQLException, ProduitException, LivreException{
		super(code);
		String requete = "select * from Livre where code='" + code + "'";
		ResultSet res = DBManager.executeQuery(requete);
		boolean trouve = false;
		while(res.next()) {
			this.auteur = res.getString("auteur");
			editeur = res.getString("editeur");
			collection = res.getString("collection");
			langue = res.getString("langue");
			datePublication = res.getDate("datePublication");
			isbn = res.getString("isbn");
			trouve = true;
		}
		if(!trouve) {
			throw new LivreException ("Ce livre n'existe pas");
		}
		
	}
	
	
	public Livre(String titre, String image, double prixAchat, double prixVente, String etat,
           String utilisation, String auteur, String editeur, String collection, String langue,
           Date datePublication, String isbn) throws SQLException, ProduitException {
		   super(titre, image, prixAchat, prixVente, etat, utilisation);
		   this.auteur = auteur;
		   this.editeur = editeur;
		   this.collection = collection;
		   this.langue = langue;
		   this.datePublication = datePublication;
		   this.isbn = isbn;
		   genererCode();
}

	public void genererCode() throws ProduitException, SQLException{
		String requete = "select * from nombreLivre";
		ResultSet res = DBManager.executeQuery(requete);
		int nombre = 0;
		while(res.next()) {
			nombre = res.getInt("nombre");
		}
		nombre++;
		requete = "update nombreLivre set nombre =" + nombre;
		DBManager.executeUpdate(requete);
		if(nombre < 10) {
			code = "LIV-00" + nombre;
		}
		else if(nombre < 100) {
			code = "LIV-0" + nombre;
		}
		else {
			code = "LIV-" + nombre;
		}
		
	}

	public void enregistrer() throws SQLException, ProduitException{
		super.setCode(code);
	    super.enregistrer();
	    
		String requete = "insert into Livre values ('" + code + "', '"  + auteur + "', '" + editeur + "', '" + collection + "', '" + langue + "', '" + datePublication + "', '" + isbn + "')";
		DBManager.executeUpdate(requete);
	}
	
	public void modifier() throws SQLException{
		super.modifier();
		String requete = "update Livre set auteur='" + auteur + "', editeur='" + editeur + "', collection= '" + collection + "', langue='" + langue + "', datePublication='"	+ datePublication + "' , isbn='"	+ isbn + "'where code='" + code + "'";
		DBManager.executeUpdate(requete);

	}
	
	public void supprimer() throws SQLException{
		
		String requete = "delete from Livre WHERE code='" + code + "'";
		DBManager.executeUpdate(requete);
		super.supprimer();
		}
	
	public static ArrayList<Produit> getListProduitEmprunts() throws SQLException, ProduitException{
		ArrayList<Produit> liste = new ArrayList<Produit>();
		
		
			String requete = "select * from Produit " +
					"inner join Livre on Livre.code = Produit.code " +
					"where etat = 'emprunte'";
					
			ResultSet res = DBManager.executeQuery(requete);
			
			while (res.next()) {
				
				String code = res.getString("code");
		        String titre = res.getString("titre");
		        String image = res.getString("image");
		        double prixAchat = res.getDouble("prixAchat");
		        double prixVente = res.getDouble("prixVente");
		        String etat = res.getString("etat");
		        String utilisation = res.getString("utilisation");
		        String auteur = res.getString("auteur");
		        String editeur = res.getString("editeur");
		        String collection = res.getString("collection");
		        String langue = res.getString("langue");
		        Date datePublication = res.getDate("datePublication");
		        String isbn = res.getString("isbn");
				
				Livre ll = new Livre( code, titre, image, prixAchat, prixVente, etat, utilisation, auteur, editeur,
		                collection, langue, datePublication, isbn);

				liste.add(ll);

			}
		return liste;
		
	}
	
	
	public static ArrayList<Produit> getListProduit(HashMap < String, String > criteres) throws SQLException{		
		ArrayList<Produit> liste = new ArrayList<Produit>();
		String requete = "select * from Produit ";
		
		if(!criteres.isEmpty()) {
			requete += "inner join Livre on Produit.code = Livre.code where ";
			boolean premierCritere = true;
			
			for (String champ : criteres.keySet()) {
                String valeur = criteres.get(champ);

                if (premierCritere) {
                    requete += "Livre." + champ + " = '" + valeur + "'";
                    premierCritere = false;
                } else {
                    requete += " AND Livre." + champ + " = '" + valeur + "'";
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
		        String auteur = res.getString("auteur");
		        String editeur = res.getString("editeur");
		        String collection = res.getString("collection");
		        String langue = res.getString("langue");
		        Date datePublication = res.getDate("datePublication");
		        String isbn = res.getString("isbn");
				
				Livre ll = new Livre( code, titre, image, prixAchat, prixVente, etat, utilisation, auteur, editeur,
		                collection, langue, datePublication, isbn);
				liste.add(ll);
			}
			return liste;
			
	}
	
	public String toString() {
		
		return super.toString() + " auteur : " + auteur + " editeur : " + editeur + " collection : " + collection + " langue : " + langue + " datePublication: " + datePublication + " isbn " + isbn;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCodeLivre() {
		return code;
	}

	
		
	

}
