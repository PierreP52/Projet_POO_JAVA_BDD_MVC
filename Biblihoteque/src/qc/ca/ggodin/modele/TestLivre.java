package qc.ca.ggodin.modele;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class TestLivre {
	 public static void main(String[] args) throws ProduitException, LivreException {
	        try {
	        	DBManager.createConnection("localhost", "bibliotheque_gerald_godinTest", "root", "AECgodin.21012023", 3306);
				
				
				
				
				
	        	Livre  livre1 = new Livre ("Livre 1", "image1.jpg", 19.99, 10.99, "emprunte", "A vendre", "auteur1", "editeur 1", "collection 1", "anglais", Date.valueOf("2013-03-15"), "1234567890123");
	        	Livre  livre2 = new Livre ("Livre 3", "image1.jpg", 19.99, 10.99, "Disponible", "a emprunte", "auteur2", "editeur 2", "collection 12", "Francais", Date.valueOf("2014-04-14"), "1232367821121");
	        	Livre  livre3 = new Livre ("Livre 4", "image1.jpg", 10.99, 19.99, "emprunte", "a emprunte", "auteru3", "dwr 2", "dwrew 12" , "Francais", Date.valueOf("2014-04-14"), "1232367821121");

	        	
				livre1.enregistrer();
				livre2.enregistrer();
				livre3.enregistrer();
				System.out.println(livre1.getCode());

				ArrayList<Produit> liste4 = Livre.getListProduitEmprunts();
				System.out.println("Affichage de la liste des livre 4");
				
				for(Produit cl : liste4) {
					System.out.println(cl);
				}
				
				livre1.setLangue("Francais");
				livre1.modifier();
				livre3.supprimer();
				
				ArrayList<Produit> liste5 = Livre.getListProduitEmprunts();
				System.out.println("Affichage de la liste des livre 5");
				
				for(Produit cl : liste5) {
					System.out.println(cl);
				}
				
				HashMap<String, String> criteres1 = new HashMap<String, String>();
				criteres1.put("auteur", "auteur1");
				ArrayList<Produit> liste6 = Livre.getListProduit(criteres1);
				System.out.println("Affichage des livre 6");
				for(Produit cl : liste6) {
					System.out.println(cl);
				}
				
				DBManager.fermerConnection();
				
			}
			catch(ClassNotFoundException ex) {
				System.out.println(ex.getMessage());
			}
			catch(SQLException ex) {
				System.out.println(ex.getMessage());
			}
			
	        
		}
}
