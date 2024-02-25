package qc.ca.ggodin.modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduitConcret extends Produit{
	public ProduitConcret(String code, String titre, String image, double prixAchat, double prixVente, String etat, String utilisation) throws SQLException {
     
		super(code, titre, image, prixAchat, prixVente, etat, utilisation);
    }
	

	

	@Override
	public void genererCode() throws ProduitException {
		// TODO Auto-generated method stub
		
	}




	
	
}
