package qc.ca.ggodin.visuel;

import java.awt.Color;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import qc.ca.ggodin.modele.DBManager;
import qc.ca.ggodin.modele.Film;
import qc.ca.ggodin.modele.Livre;
import qc.ca.ggodin.modele.Produit;
import qc.ca.ggodin.modele.ProduitException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.CardLayout;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;


public class NouveauProduit extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitre;
	private JTextField txtPrixAchat;
	private JTextField txtPrixVente;
	private JTextField TxtAuteur;
	private JTextField txtEditeur;
	private JTextField txtCollection;
	private JTextField txtIsbn;
	private File selectedFile;
	private File selectedFileFilm;
	private CardLayout cardLayout;
	private JPanel panelChoix;
	private JPanel panelLivre;
	private JPanel panelFilm;
	private JTextField txtTitreFilm;
	private JTextField txtPrixDAchatFilm;
	private JTextField txtPrixVenteFilm;
	private JTextField txtRealisateur;
	private JTextField txtStudio;
	private JTextField txtActeur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DBManager.createConnection("localhost", "bibliotheque_gerald_godinTest", "root", "AECgodin.21012023", 3306);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NouveauProduit frame = new NouveauProduit();
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
	public NouveauProduit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 792, 514);
		contentPane = new JPanel();
		setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		panelLivre = new JPanel();
		panelLivre.setLayout(null);

		panelFilm = new JPanel();
		
		panelChoix = new JPanel();
		panelChoix.setLayout(null);
		
		contentPane.add(panelLivre, "Livre");
		contentPane.add(panelFilm, "Film");
		contentPane.add(panelChoix, "Choix");
		
		cardLayout = new CardLayout();
		contentPane.setLayout(cardLayout);
		panelFilm.setLayout(null);
		
		JButton btnVersLivre = new JButton("Enregistrement Livre");
		btnVersLivre.setBounds(571, 23, 158, 47);
		panelFilm.add(btnVersLivre);
		btnVersLivre.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        cardLayout.show(contentPane, "Livre");
		        panelLivre.setVisible(true);
		        panelFilm.setVisible(false);
		    }
		});
		
		JButton btnVersFilm = new JButton("Enregistrement Film");
		btnVersFilm.setBounds(588, 11, 168, 47);
		panelLivre.add(btnVersFilm);
		btnVersFilm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        cardLayout.show(contentPane, "Film");
		        panelFilm.setVisible(true);
		        panelLivre.setVisible(false);
		    }
		});

		JButton btnVersFilm_1 = new JButton("Enregistrement Film");
		btnVersFilm_1.setBounds(127, 179, 195, 97);
		panelChoix.add(btnVersFilm_1);
		btnVersFilm_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        cardLayout.show(contentPane, "Film");
		        panelFilm.setVisible(true);
		        panelChoix.setVisible(false);
		    }
		});
		
		JButton btnVersLivre_1 = new JButton("Enregistrement Livre");
		btnVersLivre_1.setBounds(410, 179, 206, 97);
		panelChoix.add(btnVersLivre_1);
		btnVersLivre_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        cardLayout.show(contentPane, "Livre");
		        panelLivre.setVisible(true);
		        panelChoix.setVisible(false);
		    }
		});
		
		JLabel lblNewLabel_2 = new JLabel("Veuillez choisir le produit a enregistrer");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(127, 21, 482, 133);
		panelChoix.add(lblNewLabel_2);
		
		panelLivre.setBounds(28, 28, 719, 436);
		contentPane.add(panelLivre);
	
		
		JLabel lblNewLabel = new JLabel("Enregistrement de Livre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(203, 11, 300, 70);
		panelLivre.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titre :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(70, 87, 72, 36);
		panelLivre.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Prix d'achat :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(70, 181, 112, 26);
		panelLivre.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Prix de vente :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(70, 218, 122, 36);
		panelLivre.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("État");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(70, 274, 72, 36);
		panelLivre.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Utilisation");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_5.setBounds(70, 328, 109, 36);
		panelLivre.add(lblNewLabel_1_5);
		
		txtTitre = new JTextField();
		txtTitre.setBounds(136, 92, 155, 26);
		panelLivre.add(txtTitre);
		txtTitre.setColumns(10);
		
		txtPrixAchat = new JTextField();
		txtPrixAchat.setColumns(10);
		txtPrixAchat.setBounds(202, 184, 55, 26);
		panelLivre.add(txtPrixAchat);
		
		txtPrixVente = new JTextField();
		txtPrixVente.setColumns(10);
		txtPrixVente.setBounds(202, 224, 55, 26);
		panelLivre.add(txtPrixVente);
		
		JComboBox<String>  comboBoxEtat = new JComboBox<>();
		comboBoxEtat.setBounds(174, 284, 83, 22);
		panelLivre.add(comboBoxEtat);
		
		comboBoxEtat.addItem("disponible");
		comboBoxEtat.addItem("emprunte");
		
		Object selectedEtat = comboBoxEtat.getSelectedItem();
		String etatChoisi = selectedEtat.toString();
		
		JComboBox<String> comboBoxUtil = new JComboBox<>();
		comboBoxUtil.setBounds(174, 338, 83, 22);
		panelLivre.add(comboBoxUtil);
		
		comboBoxUtil.addItem("a vendre");
		comboBoxUtil.addItem("a emprunte");
		
		Object selectedUtil = comboBoxUtil.getSelectedItem();
		String UtilChoisi = selectedUtil.toString();
		
		JLabel lblNewLabel_1_6 = new JLabel("Auteur:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_6.setBounds(344, 92, 72, 36);
		panelLivre.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Editeur:");
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_6_1.setBounds(344, 134, 72, 36);
		panelLivre.add(lblNewLabel_1_6_1);
		
		JLabel lblNewLabel_1_6_2 = new JLabel("Collection :");
		lblNewLabel_1_6_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_6_2.setBounds(344, 190, 88, 36);
		panelLivre.add(lblNewLabel_1_6_2);
		
		JLabel lblNewLabel_1_6_3 = new JLabel("Langue:");
		lblNewLabel_1_6_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_6_3.setBounds(344, 255, 72, 36);
		panelLivre.add(lblNewLabel_1_6_3);
		
		JLabel lblNewLabel_1_6_4 = new JLabel("Date de publication:");
		lblNewLabel_1_6_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_6_4.setBounds(344, 302, 159, 36);
		panelLivre.add(lblNewLabel_1_6_4);
		
		JLabel lblNewLabel_1_6_5 = new JLabel("ISBN");
		lblNewLabel_1_6_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_6_5.setBounds(344, 349, 72, 36);
		panelLivre.add(lblNewLabel_1_6_5);
		
		TxtAuteur = new JTextField();
		TxtAuteur.setColumns(10);
		TxtAuteur.setBounds(426, 98, 155, 26);
		panelLivre.add(TxtAuteur);
		
		txtEditeur = new JTextField();
		txtEditeur.setColumns(10);
		txtEditeur.setBounds(414, 144, 155, 26);
		panelLivre.add(txtEditeur);
		
		txtCollection = new JTextField();
		txtCollection.setColumns(10);
		txtCollection.setBounds(442, 201, 155, 26);
		panelLivre.add(txtCollection);
		
		
		JComboBox<String> comboBoxLang = new JComboBox<>();
		comboBoxLang.setBounds(442, 265, 83, 22);
		panelLivre.add(comboBoxLang);

		comboBoxLang.addItem("Francais");
		comboBoxLang.addItem("Anglais");
		
		Object selectedLang = comboBoxLang.getSelectedItem();
		String LangChoisi = selectedLang.toString();
		
		JFormattedTextField formattedTxtDate = new JFormattedTextField();
		formattedTxtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
		formattedTxtDate.setBounds(531, 313, 83, 20);
		formattedTxtDate.setText("yyyy-MM-dd"); // Texte d'espace réservé initial

		formattedTxtDate.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (formattedTxtDate.getText().equals("yyyy-MM-dd")) {
		            formattedTxtDate.setText(""); // Effacer le texte d'espace réservé lorsqu'il gagne le focus
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (formattedTxtDate.getText().isEmpty()) {
		            formattedTxtDate.setText("yyyy-MM-dd"); // Restaurer le texte d'espace réservé s'il est vide lorsqu'il perd le focus
		        }
		    }
		});

		panelLivre.add(formattedTxtDate);
		
		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(414, 349, 155, 26);
		panelLivre.add(txtIsbn);
		
		JLabel lblImage = new JLabel("");
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImage.setBounds(161, 134, 130, 36);
		panelLivre.add(lblImage);
		
		JButton btnChoisirImage = new JButton("Chosir Image");
		btnChoisirImage.setBounds(39, 134, 112, 36);
		panelLivre.add(btnChoisirImage);
		
		btnChoisirImage.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        
		        String dossierImages = System.getProperty("user.dir") + "/images";
		        fileChooser.setCurrentDirectory(new File(dossierImages));
		        
		        // Filtre pour afficher uniquement les fichiers d'image
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
		        fileChooser.setFileFilter(filter);
		        
		        int returnValue = fileChooser.showOpenDialog(null);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            selectedFile = fileChooser.getSelectedFile();
		           
		            String nomImage = selectedFile.getName();
		            lblImage.setText(nomImage);
		           
		        }
		    }
		});
		
		
		
		JButton btnEnregistrerLivre = new JButton("Enregistrer");
		btnEnregistrerLivre.setBounds(426, 402, 105, 34);
		panelLivre.add(btnEnregistrerLivre);
		
		JLabel lblEnregistrementRe = new JLabel("Enregistrement Réussi!");
		lblEnregistrementRe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnregistrementRe.setForeground(new Color(255, 0, 0));
		lblEnregistrementRe.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnregistrementRe.setBounds(70, 375, 221, 41);
		panelLivre.add(lblEnregistrementRe);
		
		
		lblEnregistrementRe.setVisible(false);
		
		
		
		btnEnregistrerLivre.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Code à exécuter lors du clic sur le bouton
		    	try {
		    	String titre = txtTitre.getText();
		    	String image = selectedFile.getAbsolutePath();
		    	Double prixAchat = Double.parseDouble(txtPrixAchat.getText());
		    	Double prixVente = Double.parseDouble(txtPrixVente.getText());
		    	String etat = etatChoisi;
		    	String utilisation = UtilChoisi;
		    	
		    	
		    	
		    	String auteur = TxtAuteur.getText();
		    	String editeur = txtEditeur.getText();
		    	String collection = txtCollection.getText();
		    	String langue = LangChoisi;
		    	Date DateDePub = Date.valueOf(formattedTxtDate.getText());
		    	String isbn = txtIsbn.getText();
		    	
		    	
					Produit livre = new Livre(titre, image, prixAchat, prixVente, etat, utilisation, auteur, editeur, collection, langue, DateDePub, isbn);
					livre.enregistrer();
					lblEnregistrementRe.setVisible(true);
				} catch (SQLException | ProduitException e1) {
					
					e1.printStackTrace();
				}
				
		    }
		});
		
		
		 	getContentPane().add(panelChoix);
	        getContentPane().add(panelLivre);
	        getContentPane().add(panelFilm);
	        
	        JLabel lblEnregistrementDeFilm = new JLabel("Enregistrement de Film");
	        lblEnregistrementDeFilm.setHorizontalAlignment(SwingConstants.CENTER);
	        lblEnregistrementDeFilm.setFont(new Font("Tahoma", Font.PLAIN, 25));
	        lblEnregistrementDeFilm.setBounds(174, 11, 300, 70);
	        panelFilm.add(lblEnregistrementDeFilm);
	        
	        JLabel lblNewLabel_1_1 = new JLabel("Titre :");
	        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_1.setBounds(41, 87, 72, 36);
	        panelFilm.add(lblNewLabel_1_1);
	        
	        txtTitreFilm = new JTextField();
	        txtTitreFilm.setColumns(10);
	        txtTitreFilm.setBounds(107, 92, 155, 26);
	        panelFilm.add(txtTitreFilm);
	        
	        JLabel lblImageFilme = new JLabel("");
	        lblImageFilme.setHorizontalAlignment(SwingConstants.CENTER);
	        lblImageFilme.setBounds(145, 129, 155, 41);
	        panelFilm.add(lblImageFilme);
	        
	        JButton btnChoisirImageFilm = new JButton("Chosir Image");
	        btnChoisirImageFilm.setBounds(10, 134, 112, 36);
	        panelFilm.add(btnChoisirImageFilm);
	        btnChoisirImageFilm.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
			        
			        String dossierImages = System.getProperty("user.dir") + "/images";
			        fileChooser.setCurrentDirectory(new File(dossierImages));
			        
			        // Filtre pour afficher uniquement les fichiers d'image
			        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
			        fileChooser.setFileFilter(filter);
			        
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	selectedFileFilm = fileChooser.getSelectedFile();
			           
			            String nomImageFilm = selectedFileFilm.getName();
			            lblImageFilme.setText(nomImageFilm);
			           
			        }
			    }
			});
	        
	        txtPrixDAchatFilm = new JTextField();
	        txtPrixDAchatFilm.setColumns(10);
	        txtPrixDAchatFilm.setBounds(173, 184, 55, 26);
	        panelFilm.add(txtPrixDAchatFilm);
	        
	        JLabel lblNewLabel_1_2_1 = new JLabel("Prix d'achat :");
	        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_2_1.setBounds(41, 181, 112, 26);
	        panelFilm.add(lblNewLabel_1_2_1);
	        
	        JLabel lblNewLabel_1_3_1 = new JLabel("Prix de vente :");
	        lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_3_1.setBounds(41, 218, 122, 36);
	        panelFilm.add(lblNewLabel_1_3_1);
	        
	        txtPrixVenteFilm = new JTextField();
	        txtPrixVenteFilm.setColumns(10);
	        txtPrixVenteFilm.setBounds(173, 224, 55, 26);
	        panelFilm.add(txtPrixVenteFilm);
	        
	        JComboBox<String> comboBoxEtatFilm = new JComboBox<String>();
	        comboBoxEtatFilm.setBounds(163, 275, 83, 22);
	        panelFilm.add(comboBoxEtatFilm);
	        comboBoxEtatFilm.addItem("disponible");
	        comboBoxEtatFilm.addItem("emprunte");
			
			Object selectedEtatFilm = comboBoxEtatFilm.getSelectedItem();
			String etatChoisiFilm = selectedEtatFilm.toString();
	        
	        JComboBox<String> comboBoxUtilFilm = new JComboBox<String>();
	        comboBoxUtilFilm.setBounds(163, 329, 83, 22);
	        panelFilm.add(comboBoxUtilFilm);
	        comboBoxUtilFilm.addItem("a vendre");
	        comboBoxUtilFilm.addItem("a emprunte");
			
			Object selectedUtilFilm = comboBoxUtilFilm.getSelectedItem();
			String UtilChoisiFilm = selectedUtilFilm.toString();
	        
	        JLabel lblNewLabel_1_6_6 = new JLabel("Realisateur :");
	        lblNewLabel_1_6_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_6_6.setBounds(333, 83, 72, 36);
	        panelFilm.add(lblNewLabel_1_6_6);
	        
	        JLabel lblNewLabel_1_6_1_1 = new JLabel("Studio");
	        lblNewLabel_1_6_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_6_1_1.setBounds(333, 125, 72, 36);
	        panelFilm.add(lblNewLabel_1_6_1_1);
	        
	        JLabel lblNewLabel_1_6_2_1 = new JLabel("Format:");
	        lblNewLabel_1_6_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_6_2_1.setBounds(333, 181, 88, 36);
	        panelFilm.add(lblNewLabel_1_6_2_1);
	        
	        JLabel lblNewLabel_1_6_3_1 = new JLabel(" Langue audio :");
	        lblNewLabel_1_6_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_6_3_1.setBounds(333, 293, 124, 36);
	        panelFilm.add(lblNewLabel_1_6_3_1);
	        
	        JLabel lblNewLabel_1_6_5_1 = new JLabel("Acteur");
	        lblNewLabel_1_6_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_6_5_1.setBounds(333, 242, 72, 36);
	        panelFilm.add(lblNewLabel_1_6_5_1);
	        
	        txtRealisateur = new JTextField();
	        txtRealisateur.setColumns(10);
	        txtRealisateur.setBounds(415, 89, 155, 26);
	        panelFilm.add(txtRealisateur);
	        
	        txtStudio = new JTextField();
	        txtStudio.setColumns(10);
	        txtStudio.setBounds(403, 135, 155, 26);
	        panelFilm.add(txtStudio);
	        
	        JComboBox<String> comboBoxLangAudioFilm = new JComboBox<String>();
	        comboBoxLangAudioFilm.setBounds(498, 303, 83, 22);
	        panelFilm.add(comboBoxLangAudioFilm);
	        
	        comboBoxLangAudioFilm.addItem("Francais");
	        comboBoxLangAudioFilm.addItem("Anglais");
	        
	        Object selectedLangFilm = comboBoxLangAudioFilm.getSelectedItem();
			String LangChoisiFilm = selectedLangFilm.toString();
	        
	        txtActeur = new JTextField();
	        txtActeur.setColumns(10);
	        txtActeur.setBounds(392, 250, 155, 26);
	        panelFilm.add(txtActeur);
	        
	        JLabel lblNewLabel_1_4_1 = new JLabel("État");
	        lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_4_1.setBounds(41, 265, 72, 36);
	        panelFilm.add(lblNewLabel_1_4_1);
	        
	        JLabel lblNewLabel_1_5_1 = new JLabel("Utilisation");
	        lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNewLabel_1_5_1.setBounds(41, 319, 109, 36);
	        panelFilm.add(lblNewLabel_1_5_1);
	        
	        JButton btnEnregistrerFilm = new JButton("Enregistrer");
	        btnEnregistrerFilm.setBounds(498, 392, 105, 34);
	        panelFilm.add(btnEnregistrerFilm);
	        
	        JComboBox<String> comboBoxFormatFilm = new JComboBox<String>();
	        comboBoxFormatFilm.setBounds(431, 191, 83, 22);
	        panelFilm.add(comboBoxFormatFilm);
	        
	        JLabel lbleEnregistrementRéussiFilm = new JLabel("Enregistrement Réussi!");
	        lbleEnregistrementRéussiFilm.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        lbleEnregistrementRéussiFilm.setHorizontalAlignment(SwingConstants.CENTER);
	        lbleEnregistrementRéussiFilm.setForeground(new Color(255, 0, 0));
	        lbleEnregistrementRéussiFilm.setBounds(85, 382, 261, 54);
	        panelFilm.add(lbleEnregistrementRéussiFilm);
	        lbleEnregistrementRéussiFilm.setVisible(false);
	        
	        
	        comboBoxFormatFilm.addItem("DVD");
	        comboBoxFormatFilm.addItem("Blue Ray");
	        
	        Object selectedFormatFilm = comboBoxFormatFilm.getSelectedItem();
			String FormatChoisiFilm = selectedFormatFilm.toString();
	        
			btnEnregistrerFilm.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        // Code à exécuter lors du clic sur le bouton
			    	try {
			    	String titre = txtTitreFilm.getText();
			    	String image = selectedFileFilm.getAbsolutePath();
			    	Double prixAchat = Double.parseDouble(txtPrixDAchatFilm.getText());
			    	Double prixVente = Double.parseDouble(txtPrixVenteFilm.getText());
			    	String etat = etatChoisiFilm;
			    	String utilisation = UtilChoisiFilm;
			    	
			    	
			    	
			    	String realisateur = txtRealisateur.getText();
			    	String studio = txtStudio.getText();
			    	String langFilm = LangChoisiFilm;
			    	String acteur = txtActeur.getText();;
			    	String formatFilm = FormatChoisiFilm;
			    	
			    	
			    	
						Produit Film = new Film(titre, image, prixAchat, prixVente, etat, utilisation, realisateur, studio, formatFilm, acteur, langFilm);
						Film.enregistrer();
						lbleEnregistrementRéussiFilm.setVisible(true);
					} catch (SQLException | ProduitException e1) {
						
						e1.printStackTrace();
					}
					
			    }
			});
	       
		
		
		
		
		
		
	}
}
