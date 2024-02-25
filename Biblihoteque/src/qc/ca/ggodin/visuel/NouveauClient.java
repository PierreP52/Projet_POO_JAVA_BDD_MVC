package qc.ca.ggodin.visuel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import qc.ca.ggodin.modele.Client;
import qc.ca.ggodin.modele.DBManager;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class NouveauClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JTextField txtTelephone;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DBManager.createConnection("localhost", "bibliotheque_gerald_godinTest", "root", "AECgodin.21012023", 3306);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NouveauClient frame = new NouveauClient();
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
	public NouveauClient() {
		setTitle("Enregistrement de nouveau Client");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nouveau client");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(300, 11, 175, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(56, 93, 55, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(56, 158, 100, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Adresse :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(56, 214, 100, 28);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Téléphone :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(56, 280, 125, 28);
		contentPane.add(lblNewLabel_1_3);
		
		txtNom = new JTextField();
		txtNom.setBounds(185, 96, 242, 28);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(185, 161, 242, 28);
		contentPane.add(txtPrenom);
		
		txtAdresse = new JTextField();
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(185, 219, 242, 25);
		contentPane.add(txtAdresse);
		
		txtTelephone = new JTextField();
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(185, 280, 242, 28);
		contentPane.add(txtTelephone);
		
		JButton btnEnregistrerClient = new JButton("Enregistrer");
		btnEnregistrerClient.setBounds(527, 383, 125, 70);
		contentPane.add(btnEnregistrerClient);
		
		JLabel lblEnregistrementR = new JLabel("Enregistrement Réussie!");
		lblEnregistrementR.setForeground(new Color(255, 0, 0));
		lblEnregistrementR.setBackground(new Color(255, 255, 255));
		lblEnregistrementR.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEnregistrementR.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnregistrementR.setBounds(185, 364, 290, 64);
		contentPane.add(lblEnregistrementR);
		lblEnregistrementR.setVisible(false);
		
		btnEnregistrerClient.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Code à exécuter lors du clic sur le bouton
		    	try {
		    	String nom = txtNom.getText();
		    	String prenom = txtPrenom.getText();
		    	String adresse = txtAdresse.getText();
		    	String telephone = txtTelephone.getText();
		    	
					Client client = new Client(nom, prenom, adresse, telephone);
					client.enregistrer();
					lblEnregistrementR.setVisible(true);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
		    }
		});
	}
}
