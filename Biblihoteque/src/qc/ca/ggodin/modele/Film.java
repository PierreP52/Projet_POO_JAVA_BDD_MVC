package qc.ca.ggodin.modele;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;




public class Film extends Produit{
	protected String realisateur;
	protected String studio;
	protected String format;
	protected String acteurs;
	protected String languePisteSonore;
	
	
	
	public Film(String code, String titre, String image, double prixAchat, double prixVente, String etat,
			String utilisation, String realisateur, String studio, String format, String acteurs, String languePisteSonore) throws SQLException {
		super(code, titre, image, prixAchat, prixVente, etat, utilisation);
		this.realisateur = realisateur;
		this.studio = studio;
		this.format = format;
		this.acteurs = acteurs;
		this.languePisteSonore = languePisteSonore;
		
	}
	
	public Film(String code) throws SQLException, ProduitException, FilmException{
		super(code);
		String requete = "select * from Film where code='" + code + "'";
		ResultSet res = DBManager.executeQuery(requete);
		boolean trouve = false;
		while(res.next()) {
			this.realisateur = res.getString("realisateur");
			studio = res.getString("studio");
			format = res.getString("format");
			acteurs = res.getString("acteurs");
			languePisteSonore = res.getString("languePisteSonore");
			trouve = true;
		}
		if(!trouve) {
			throw new FilmException ("Ce film n'existe pas");
		}
	}
	
	public Film( String titre, String image, double prixAchat, double prixVente, String etat,
			String utilisation, String realisateur, String studio, String format, String acteurs, String languePisteSonore) throws SQLException, ProduitException {
		super(titre, image, prixAchat, prixVente, etat, utilisation);
		this.realisateur = realisateur;
		this.studio = studio;
		this.format = format;
		this.acteurs = acteurs;
		this.languePisteSonore = languePisteSonore;
		genererCode();
		
		
	}
	
	public void genererCode() throws ProduitException, SQLException{
		String requete = "select * from nombreFilm";
		ResultSet res = DBManager.executeQuery(requete);
		int nombre = 0;
		while(res.next()) {
			nombre = res.getInt("nombre");
		}
		nombre++;
		requete = "update nombreFilm set nombre =" + nombre;
		DBManager.executeUpdate(requete);
		if(nombre < 10) {
			code = "FIL-00" + nombre;
		}
		else if(nombre < 100) {
			code = "FIL-0" + nombre;
		}
		else {
			code = "FIL-" + nombre;
		}
	}

	public void enregistrer() throws SQLException, ProduitException{
			super.setCode(code);
		    super.enregistrer();
		    
			String requete = "insert into Film values ('" + code  + "','" + realisateur + "', '" + studio + "', '" + format + "', '" + acteurs + "', '" + languePisteSonore + "')";
		DBManager.executeUpdate(requete);
	}
	
	public void modifier() throws SQLException{
		super.modifier();
		String requete = "update Film set realisateur='" + realisateur + "', studio='" + studio + "', format= '" + format + "', acteurs='" + acteurs + "', languePisteSonore='"	+ languePisteSonore  + "'where code='" + code + "'";
		DBManager.executeUpdate(requete);
		

	}
	
	public void supprimerFilm() throws SQLException{
		
		String requete = "delete from Film WHERE code='" + code + "'";
		DBManager.executeUpdate(requete);
		super.supprimer();
		
		}
	
	public static ArrayList<Produit> getListProduitEmprunts() throws SQLException, ProduitException {
		ArrayList<Produit> liste = new ArrayList<Produit>();
		String requete = "select * from Produit " +
				"inner join Film on Film.code = Produit.code " +
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
			String realisateur = res.getString("realisateur");
			String studio = res.getString("studio");
			String format = res.getString("format");
			String acteurs = res.getString("acteurs");
			String languePisteSonore = res.getString("languePisteSonore");
			Film fl = new Film(code, titre, image, prixAchat, prixVente, etat, utilisation, realisateur,  studio, format, acteurs, languePisteSonore);
			liste.add(fl);
		}
		
		return liste;
	}
	
	public static ArrayList<Produit> getListProduit(HashMap < String, String > criteres) throws SQLException{		
		ArrayList<Produit> liste = new ArrayList<Produit>();
		String requete = "select * from Produit";
		
		if(!criteres.isEmpty()) {
			requete += " inner join Film on Produit.code = Film.code where ";
			boolean premierCritere = true;
			
			for (String champ : criteres.keySet()) {
                String valeur = criteres.get(champ);

                if (premierCritere) {
                    requete += "Film." + champ + " = '" + valeur + "'";
                    premierCritere = false;
                } else {
                    requete += " AND Film." + champ + " = '" + valeur + "'";
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
				String realisateur = res.getString("realisateur");
				String studio = res.getString("studio");
				String format = res.getString("format");
				String acteurs = res.getString("acteurs");
				String languePisteSonore = res.getString("languePisteSonore");
				Film fl = new Film(code, titre, image, prixAchat, prixVente, etat, utilisation, realisateur,  studio, format, acteurs, languePisteSonore);
				liste.add(fl);
			}
			return liste;
			
	}
	
	
	public String toString() {
		
		return super.toString() + " " + realisateur + " " + studio + " " + format + " " + acteurs + " " + languePisteSonore ;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getActeurs() {
		return acteurs;
	}

	public void setActeurs(String acteurs) {
		this.acteurs = acteurs;
	}

	public String getLanguePisteSonore() {
		return languePisteSonore;
	}

	public void setLanguePisteSonore(String languePisteSonore) {
		this.languePisteSonore = languePisteSonore;
	}

	
}
