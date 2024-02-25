package qc.ca.ggodin.modele;


import java.sql.SQLException;

public class Prepose {
	private String userName;
	private String password;
	
	public Prepose (String userName, String password){
		this.userName=userName;
		this.password=password;
	}
	
	public void verifierLogin(String username, String password) throws SQLException{
		
		String requete = "select * from Prepose where username = '" + username + "' And password = '" + password + "'";
		DBManager.executeQuery(requete);
		boolean trouve = false;
		
		System.out.println("Bienvenue " + username);
		
		if(!trouve) {
			throw new SQLException ("Ce Username ou le Password n'existe pas.");
		}
		
	}
}
