package qc.ca.ggodin.modele;

import java.sql.SQLException;

public class Pret {
	private String codeClient;
	private String codeProduit;
	private String datePret;
	private String dateRetour;
	
	public Pret(String codeClient, String codeProduit, String datePret, String dateRetour) {
		this.codeClient = codeClient;
		this.codeProduit = codeProduit;
		this.datePret = datePret;
		this.dateRetour = dateRetour;
	}
	
	public void enregistrer() throws SQLException{
		String requete = "insert into Pret values ('" + codeClient + "', '" + codeProduit + "', '" + datePret + "', '" + dateRetour + "')";
		DBManager.executeUpdate(requete);
	}

	public String getDatePret() {
		return datePret;
	}

	public void setDatePret(String datePret) {
		this.datePret = datePret;
	}

	public String getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(String dateRetour) {
		this.dateRetour = dateRetour;
	}
	
}
