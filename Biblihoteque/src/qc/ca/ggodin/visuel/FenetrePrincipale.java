package qc.ca.ggodin.visuel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FenetrePrincipale extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNouveauClient = new JButton("Nouveau Client");
		btnNouveauClient.setBounds(106, 104, 163, 41);
		contentPane.add(btnNouveauClient);
		btnNouveauClient.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Code à exécuter lors du clic sur le bouton
		    	NouveauClient NouveauClient = new NouveauClient();
		    	NouveauClient.setVisible(true);
		    }
		});
		
		JButton btnNouveauProduit = new JButton("Nouveau Produit");
		btnNouveauProduit.setBounds(378, 104, 163, 41);
		contentPane.add(btnNouveauProduit);
		btnNouveauProduit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Code à exécuter lors du clic sur le bouton
		    	NouveauProduit NouveauProduit = new NouveauProduit();
		    	NouveauProduit.setVisible(true);
		    }
		});
		
		JButton btnModificationProduit = new JButton("Modification de Produit");
		btnModificationProduit.setBounds(106, 161, 163, 41);
		contentPane.add(btnModificationProduit);
		
		JButton btnSupprimerProduit = new JButton("Suppression de Produit");
		btnSupprimerProduit.setBounds(378, 161, 163, 41);
		contentPane.add(btnSupprimerProduit);
		
		JButton btnRechercheClient = new JButton("Rechercher un Client");
		btnRechercheClient.setBounds(106, 213, 163, 41);
		contentPane.add(btnRechercheClient);
		
		JButton btnRechercherUnLivre = new JButton("Rechercher un Livre");
		btnRechercherUnLivre.setBounds(378, 213, 163, 41);
		contentPane.add(btnRechercherUnLivre);
		
		JButton btnLivreEmprunte = new JButton("Livre Emprunté");
		btnLivreEmprunte.setBounds(106, 271, 163, 41);
		contentPane.add(btnLivreEmprunte);
		
		JButton btnProduitEmprunte = new JButton("Produit Empruntés");
		btnProduitEmprunte.setBounds(378, 271, 163, 41);
		contentPane.add(btnProduitEmprunte);
		
		JLabel lblNewLabel = new JLabel("Accueil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(214, 26, 224, 41);
		contentPane.add(lblNewLabel);
	}
}
