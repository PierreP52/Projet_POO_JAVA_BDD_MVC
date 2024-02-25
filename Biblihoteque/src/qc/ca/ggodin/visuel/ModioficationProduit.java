package qc.ca.ggodin.visuel;

import java.awt.CardLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import qc.ca.ggodin.modele.DBManager;
import qc.ca.ggodin.modele.Film;
import qc.ca.ggodin.modele.FilmException;
import qc.ca.ggodin.modele.Livre;
import qc.ca.ggodin.modele.LivreException;
import qc.ca.ggodin.modele.Produit;
import qc.ca.ggodin.modele.ProduitException;
import javax.swing.JToggleButton;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;

public class ModioficationProduit extends JFrame {

	private JPanel contentPane;
	private JPanel panelModifierLivre;
	private JPanel panelModifierFilm;
	private JPanel panelModifierChoix;
	private CardLayout cardLayout;
	private JLabel lblNewLabel;
	private JTextField txtEntrerCode;
	private JTextField txtModification;
	private JComboBox<String> comboBoxChoixModif;
	private String code;
	private String prefix;
	private String etatChoisi;
	private String formatChoisi;
	private String utilisationChoisie;
	private String langueChoisie;
	private String langueOdChoisi;
	
	

	
	//methode pour LIB ou Fil
	private void Prefix(String prefix) {
		
		if(prefix.equals("LIV")) {
			comboBoxChoixModif.setVisible(true);
			comboBoxChoixModif.addItem("Titre");
			comboBoxChoixModif.addItem("image");
			comboBoxChoixModif.addItem("prix achat");
			comboBoxChoixModif.addItem("prix vente");
			comboBoxChoixModif.addItem("etat");
			comboBoxChoixModif.addItem("utilisation");
			
			comboBoxChoixModif.addItem("auteur");
			comboBoxChoixModif.addItem("editeur");
			comboBoxChoixModif.addItem("collection");
			comboBoxChoixModif.addItem("langue");
			comboBoxChoixModif.addItem("date de publication");
			comboBoxChoixModif.addItem("isbn");
		}
		else if(prefix.equals("FIL")){
			comboBoxChoixModif.setVisible(true);
			comboBoxChoixModif.addItem("Titre");
			comboBoxChoixModif.addItem("image");
			comboBoxChoixModif.addItem("prix achat");
			comboBoxChoixModif.addItem("prix vente");
			comboBoxChoixModif.addItem("etat");
			comboBoxChoixModif.addItem("utilisation");
			
			comboBoxChoixModif.addItem("realisateur");
			comboBoxChoixModif.addItem("studio");
			comboBoxChoixModif.addItem("format");
			comboBoxChoixModif.addItem("acteurs");
			comboBoxChoixModif.addItem("Langue de la piste sonore");
			
		}
		else {
			txtEntrerCode.setText("Code inconnue");
		}
	}
	//methode pour les info sur le prosuit
	private String infoProduit(String code, String prefix) {
		
		String infoLabel = "";

	    try {
	        if (prefix.equals("LIV")) {
	            Livre info = new Livre(code);
	            infoLabel = " Titre: " + info.getTitre() + ", Prix d'Achat: " + info.getPrixAchat() + ", Prix de vente: " + info.getPrixVente() + ", \nEtat: " + info.getEtat() + ", Utilisation : " + info.getUtilisation()  
	            		+ ", Auteur = " + info.getAuteur() + ", \nEditeur : " + info.getEditeur() + ", Collection : " + info.getCollection() + ", Langue : " + info.getLangue() + ", \nDate de publication: " + info.getDatePublication() + ", Isbn = " + info.getIsbn();
	        } else if (prefix.equals("FIL")) {
	            Film info = new Film(code);
	            infoLabel = "Titre: " + info.getTitre() + ", prix d'Achat: " + info.getPrixAchat() + ", prix de vente: " + info.getPrixVente() + ", \nEtat: " + info.getEtat() + ", Utilisation : " + info.getUtilisation() 
	            		+ ", realisateur = " + info.getRealisateur() + ", \nStudio : " + info.getStudio() + ", format : " + info.getFormat() + ", acteurs : " + info.getActeurs() + ", \nLangue de la pisteSonore: " + info.getLanguePisteSonore();
	        }
	    } catch (SQLException | ProduitException | LivreException | FilmException e) {
	        e.printStackTrace();
	        return null; // 
	    }

	    return infoLabel; 
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBManager.createConnection("localhost", "bibliotheque_gerald_godinTest", "root", "AECgodin.21012023", 3306);
					ModioficationProduit frame = new ModioficationProduit();
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
	public ModioficationProduit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Modifier Produit");
		lblNewLabel_1.setBounds(287, 64, 176, 31);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Code");
		lblNewLabel_2.setBounds(172, 114, 79, 39);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2);
		
		//choix de la modification a faire
		comboBoxChoixModif = new JComboBox<>();
		comboBoxChoixModif.setBounds(246, 282, 176, 22);
		comboBoxChoixModif.setBackground(new Color(255, 255, 255));
		comboBoxChoixModif.setForeground(new Color(0, 0, 0));
		contentPane.add(comboBoxChoixModif);
		comboBoxChoixModif.setVisible(false);
		
		
		
		
		
		
		//comboBoxChoixModif.addActionListener(new ActionListener() {
		//    public void actionPerformed(ActionEvent e) {
		 //       String code = txtEntrerCode.getText();
		   //     String prefix = code.substring(0, 3);
		  //      Prefix(prefix);
		  //      String info =infoProduit(code,prefix);
		  //      lblNewLabel_3.setVisible(true);
		  //      lblInformationProduit.setText(info);
		//    }
		//});
		//Entrer du code 
		txtEntrerCode = new JTextField();
		txtEntrerCode.setBounds(287, 127, 122, 20);
		txtEntrerCode.setHorizontalAlignment(SwingConstants.TRAILING);
		txtEntrerCode.setText("xxx-000");
		contentPane.add(txtEntrerCode);
		txtEntrerCode.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Choisir le champ a modifier :");
		lblNewLabel_3.setBounds(172, 240, 310, 31);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setVisible(false);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_3);
		
		JTextArea lblInformationProduit = new JTextArea("");
		lblInformationProduit.setBounds(115, 164, 483, 73);
		lblInformationProduit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInformationProduit.setEditable(false);
		lblInformationProduit.setLineWrap(true);
		lblInformationProduit.setWrapStyleWord(true);
		contentPane.add(lblInformationProduit);
		
		
		//Modification du combo box
		txtEntrerCode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		        code = txtEntrerCode.getText();
		        prefix = code.substring(0, 3);
		        Prefix(prefix);
		        String info =infoProduit(code,prefix);
		        lblNewLabel_3.setVisible(true);
		        lblInformationProduit.setText(info);
		    }
		});
		
		
		
		
		txtModification = new JTextField();
		txtModification.setBounds(244, 315, 178, 20);
		contentPane.add(txtModification);
		txtModification.setColumns(10);
		txtModification.setVisible(false);
		
		JButton btnModifImage = new JButton("Choisir nouvelle image");
		btnModifImage.setBounds(246, 346, 176, 23);
		contentPane.add(btnModifImage);
		btnModifImage.setVisible(false);
		
		JComboBox<String> comboBoxEtatModif = new JComboBox<String>();
		comboBoxEtatModif.setBounds(143, 346, 93, 22);
		comboBoxEtatModif.setBackground(new Color(255, 255, 255));
		contentPane.add(comboBoxEtatModif);
		comboBoxEtatModif.setVisible(false);
		comboBoxEtatModif.addItem("disponible");
		comboBoxEtatModif.addItem("emprunte");
		
		comboBoxEtatModif.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Object selectedEtat = comboBoxEtatModif.getSelectedItem();
				 etatChoisi = selectedEtat.toString();
		    }
		});
		
		JComboBox<String> comboBoxUtilisationModif = new JComboBox<String>();
		comboBoxUtilisationModif.setBounds(432, 346, 93, 22);
		contentPane.add(comboBoxUtilisationModif);
		comboBoxUtilisationModif.setVisible(false);
		comboBoxUtilisationModif.addItem("a vendre");
		comboBoxUtilisationModif.addItem("a emprunte");
		comboBoxUtilisationModif.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Object selectedUtilisation  = comboBoxUtilisationModif.getSelectedItem();
				 utilisationChoisie  = selectedUtilisation .toString();
		    }
		});
		
		
		
		JComboBox<String> comboBoxLangueModif = new JComboBox<String>();
		comboBoxLangueModif.setBounds(143, 379, 93, 22);
		comboBoxLangueModif.setBackground(new Color(255, 255, 255));
		contentPane.add(comboBoxLangueModif);
		comboBoxLangueModif.setVisible(false);
		comboBoxLangueModif.addItem("Francais");
		comboBoxLangueModif.addItem("Anglais");
		comboBoxLangueModif.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object selectedLangue = comboBoxLangueModif.getSelectedItem();
			langueChoisie = selectedLangue.toString();
	    }
	});
		
		
		
		JComboBox<String> comboBoxFormatModif_1 = new JComboBox<String>();
		comboBoxFormatModif_1.setBounds(432, 379, 93, 22);
		contentPane.add(comboBoxFormatModif_1);
		comboBoxFormatModif_1.setVisible(false);
		comboBoxFormatModif_1.addItem("DVD");
		comboBoxFormatModif_1.addItem("Blue Ray");
		comboBoxFormatModif_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectedFormat = comboBoxFormatModif_1.getSelectedItem();
				formatChoisi = selectedFormat.toString();
		    }
		});
		
		
		//zone texte format
		JFormattedTextField formattedTextFieldDate = new JFormattedTextField();
		formattedTextFieldDate.setText("yyyy-MM-dd");
		formattedTextFieldDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
		formattedTextFieldDate.setBounds(453, 283, 72, 20);
		contentPane.add(formattedTextFieldDate);
		formattedTextFieldDate.setVisible(false);
		formattedTextFieldDate.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (formattedTextFieldDate.getText().equals("yyyy-MM-dd")) {
		        	formattedTextFieldDate.setText(""); // Effacer le texte d'espace réservé lorsqu'il gagne le focus
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (formattedTextFieldDate.getText().isEmpty()) {
		        	formattedTextFieldDate.setText("yyyy-MM-dd"); // Restaurer le texte d'espace réservé s'il est vide lorsqu'il perd le focus
		        }
		    }
		});
		
		JLabel lblModifReussi = new JLabel("Modification réussie!");
		lblModifReussi.setBounds(172, 425, 201, 31);
		lblModifReussi.setForeground(new Color(255, 0, 0));
		lblModifReussi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblModifReussi);
		lblModifReussi.setVisible(false);
		
		JComboBox<String> comboBoxLangueAudio = new JComboBox<String>();
		comboBoxLangueAudio.setBounds(246, 380, 176, 22);
		contentPane.add(comboBoxLangueAudio);
		comboBoxLangueAudio.setVisible(false);
		comboBoxLangueAudio.addItem("Francais");
		comboBoxLangueAudio.addItem("Anglais");
		comboBoxLangueAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectedLangueOd = comboBoxLangueAudio.getSelectedItem();
				langueOdChoisi = selectedLangueOd.toString();
		    }
		});
		
		
		comboBoxChoixModif.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  Object selectedChoix = comboBoxChoixModif.getSelectedItem();
				  if(selectedChoix != null) {
						String choix = selectedChoix.toString();
						switch (choix) {
						case "Titre":
						case "prix achat":
						case "prix vente":
						case "auteur":
						case "editeur":
						case "collection":
						case "isbn":
						case "realisateur":
						case "studio":
						case "acteurs":
							txtModification.setVisible(true);
							comboBoxUtilisationModif.setVisible(false);
							comboBoxLangueModif.setVisible(false);
							formattedTextFieldDate.setVisible(false);
							comboBoxFormatModif_1.setVisible(false);
							comboBoxLangueAudio.setVisible(false);
							break;
						case "etat":
							comboBoxEtatModif.setVisible(true);
							txtModification.setVisible(false);
							comboBoxLangueModif.setVisible(false);
							formattedTextFieldDate.setVisible(false);
							comboBoxFormatModif_1.setVisible(false);
							comboBoxLangueAudio.setVisible(false);
							comboBoxUtilisationModif.setVisible(false);
							break;
						case "utilisation":
							comboBoxUtilisationModif.setVisible(true);
							txtModification.setVisible(false);
							comboBoxEtatModif.setVisible(false);
							comboBoxLangueModif.setVisible(false);
							formattedTextFieldDate.setVisible(false);
							comboBoxFormatModif_1.setVisible(false);
							comboBoxLangueAudio.setVisible(false);
							break;
						case "langue" :
							comboBoxLangueModif.setVisible(true);
							txtModification.setVisible(false);
							formattedTextFieldDate.setVisible(false);
							comboBoxFormatModif_1.setVisible(false);
							comboBoxLangueAudio.setVisible(false);
							comboBoxUtilisationModif.setVisible(false);
							comboBoxEtatModif.setVisible(false);
							break;
						case "date de publication" :
							formattedTextFieldDate.setVisible(true);
							txtModification.setVisible(false);
							comboBoxFormatModif_1.setVisible(false);
							comboBoxLangueAudio.setVisible(false);
							comboBoxUtilisationModif.setVisible(false);
							comboBoxEtatModif.setVisible(false);
							comboBoxLangueModif.setVisible(false);
							break;
						case "format":
							comboBoxFormatModif_1.setVisible(true);
							txtModification.setVisible(false);
							comboBoxLangueAudio.setVisible(false);
							comboBoxUtilisationModif.setVisible(false);
							comboBoxEtatModif.setVisible(false);
							comboBoxLangueModif.setVisible(false);
							formattedTextFieldDate.setVisible(false);
							break;
						case "Langue de la piste sonore":
							comboBoxLangueAudio.setVisible(true);
							txtModification.setVisible(false);
							comboBoxUtilisationModif.setVisible(false);
							comboBoxEtatModif.setVisible(false);
							comboBoxLangueModif.setVisible(false);
							formattedTextFieldDate.setVisible(false);
							comboBoxFormatModif_1.setVisible(false);
							break;
						}
					}
			    }
			});
		
		JButton btnModification = new JButton("Modifier");
		btnModification.setBounds(541, 439, 122, 49);
		contentPane.add(btnModification);
		btnModification.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			    try {
			        if (prefix.equals("LIV")) {
			            Livre info = new Livre(code);
			            Object selectedChoix = comboBoxChoixModif.getSelectedItem();
			            if (selectedChoix != null) {
			                String choix = selectedChoix.toString();
			                switch (choix) {
			                    case "Titre":
			                        info.setTitre(txtModification.getText());
			                        break;
			                    case "prix achat":
			                    	info.setPrixAchat(Double.parseDouble(txtModification.getText()));
			                        break;
			                    case "prix vente":
			                    	info.setPrixVente(Double.parseDouble(txtModification.getText()));
			                        break;
			                    case "etat":
			                    	info.setEtat(etatChoisi);
			                    	break;
			                    case "auteur":
			                    	 info.setAuteur(txtModification.getText());
			                        break;
			                    case "editeur":
			                    	 info.setEditeur(txtModification.getText());
			                        break;
			                    case "collection":
			                    	 info.setCollection(txtModification.getText());
			                        break;
			                    case "isbn":
			                    	info.setIsbn(txtModification.getText());
			                        break;
			                    case "utilisation":
			                    	info.setUtilisation(utilisationChoisie);
			                    	break;
			                    case "langue":
			                    	info.setLangue(langueChoisie);
			                    	break;
			                    
			                    
			                }
			            }
			            info.modifier();
			            lblModifReussi.setVisible(true);
			            
			        } else if (prefix.equals("FIL")) {
			            Film info = new Film(code);
			            Object selectedChoix = comboBoxChoixModif.getSelectedItem();
			            if (selectedChoix != null) {
			                String choix = selectedChoix.toString();
			                switch (choix) {
			                    case "Titre":
			                        info.setTitre(txtModification.getText());
			                        break;
			                    case "prix achat":
			                    	info.setPrixAchat(Double.parseDouble(txtModification.getText()));
			                        break;
			                    case "prix vente":
			                    	info.setPrixAchat(Double.parseDouble(txtModification.getText()));
			                        break;
			                    case "realisateur":
			                    	info.setRealisateur(txtModification.getText());
			                        break;
			                    case "studio":
			                    	info.setStudio(txtModification.getText());
			                        break;
			                    case "acteurs":
			                    	info.setActeurs(txtModification.getText());
			                        break;
			                    case "etat":
			                    	info.setEtat(etatChoisi);
			                    	break;
			                    case "utilisation":
			                    	info.setUtilisation(utilisationChoisie);
			                    	break;
			                    case "Langue de la piste sonore":
			                    	info.setLanguePisteSonore(langueOdChoisi);
			                    	break;
			                    case "format":
			                    	info.setFormat(formatChoisi);
			                    
			                   
			                }
			                
			            }
			            info.modifier();
			            lblModifReussi.setVisible(true);
			        }
			        
			    } catch (SQLException | ProduitException | LivreException | FilmException e1) {
			        e1.printStackTrace();
			    }
			}
	});

		
		
		
		JButton btnValiderCode = new JButton("valider");
		btnValiderCode.setBounds(453, 126, 89, 23);
		contentPane.add(btnValiderCode);
		
	
		
		
		
		
		btnValiderCode.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	code = txtEntrerCode.getText();
		        prefix = code.substring(0, 3);
		        Prefix(prefix);
		        String info =infoProduit(code,prefix);
		        lblNewLabel_3.setVisible(true);
		        lblInformationProduit.setText(info);
		    }
		});
		
	
		
		
	}
}
