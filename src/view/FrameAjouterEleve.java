package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.EmailValidator;
import org.apache.velocity.runtime.directive.Parse;

import classeMetier.Classe;
import classeMetier.Eleve;
import classeMetier.Tuteur;
import connxion_Requete.Connexion;
import dao.implement.ClasseDAO;
import dao.implement.EleveDAO;
import dao.implement.TuteurDAO;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FrameAjouterEleve extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Inscription_Etudiant;
	private JTextField textField_Nom_Etudiant;
	private JTextField textField_Prenom_Etudiant;
	private JTextField textField_Ville_Etudiant;
	private JTextField textField_Addresse_Etudiant;
	private JTextField textField_Nationnalite_Etudiant;
	private JTextField textField_DateIns__Etudiant;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private JComboBox comboBoxNumClasse ;

	
	private JTextField textField_Nom_Tuteur;
	private JTextField textField_Prenom_Tuteur;
	private JTextField textField_Adresse_Tuteur;
	private JTextField textField_Ville_Tuteur;
	private JTextField textField_Nationalité_Tuteur;
	private JTextField textField_TelMobile_Tuteur;
	private JTextField textField_TelFixe_Tuteur;
	private JTextField textField_Mail_Tuteur;
	private JTextField textField_Profession_Tuteur;
	private JTextField textField_Date_Naiss_Tuteur;
	private JRadioButton rdbtnMTut;
	private JRadioButton rdbtnFTut;
	private JTextField textField_CIN_Tuteur;
	private JTextField txtpath;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel image;
	private String path;
	private JLabel errEIns;
	private JLabel errENom;
	private JLabel errEPren;
	private JLabel errEDatN;
	private JLabel errENat;
	private JLabel errEAdr;
	private JLabel errEVille;
	private JLabel errEDateIns;
	private JLabel errTCin;
	private JLabel errTNom;
	private JLabel errTPren;
	private JLabel errTNa;
	private JLabel errTDateN;
	private JLabel errAdr;
	private JLabel errTVille;
	private JLabel errTTelM;
	private JLabel errTTelDom;
	private JLabel errTMail;
	private JLabel errTPro;
	private EleveDAO eleveDAO;
	private TuteurDAO tuteurDAO ;
	private JTextField textField_Age_Etudiant;
	private JDateChooser txtdtnaissance;
	private JTextField textField_Age_Tuteur;
	private JDateChooser txtdtnaissance_tuteur;
	/*private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;*/
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameAjouterEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameAjouterEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameAjouterEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameAjouterEleve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelGestionEleve PanelGestionEleve = null;
					FrameAjouterEleve frame = new FrameAjouterEleve(PanelGestionEleve );
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
	public FrameAjouterEleve(PanelGestionEleve p) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameAjouterEleve.class.getResource("/image/lib.png")));
		setTitle("Ajout_Eleve");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 142, 987, 534);
		//setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Eleve", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		panel.setBounds(10, 11, 576, 373);
		contentPane.add(panel);
		
		eleveDAO= new EleveDAO();
		tuteurDAO= new TuteurDAO();
		JLabel label = new JLabel("<html>N\u00B0 inscription : <font color= 'red'> * </font></html>");
		label.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(10, 12, 131, 28);
		panel.add(label);
		
		JLabel label_1 = new JLabel("<html>Nom : <font color= 'red'> * </font></html>");
		label_1.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_1.setBounds(10, 83, 95, 28);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("<html>Prenom : <font color= 'red'> * </font></html>");
		label_2.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_2.setBounds(10, 110, 125, 24);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("<html>Date de naissance : <font color= 'red'> * </font></html>");
		label_3.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_3.setBounds(10, 136, 146, 28);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("<html>Nationnalit\u00E9 : <font color= 'red'> * </font></html>");
		label_4.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_4.setBounds(10, 187, 125, 25);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Adresse");
		label_5.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_5.setBounds(10, 214, 125, 28);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Ville");
		label_6.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_6.setBounds(10, 241, 125, 28);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Sexe");
		label_7.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_7.setBounds(10, 307, 52, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Date d'inscription");
		label_8.setVisible(false);
		label_8.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_8.setBounds(10, 266, 125, 30);
		panel.add(label_8);
		
		textField_Inscription_Etudiant = new JTextField();
		//textField_Inscription_Etudiant.setEditable(false);
		textField_Inscription_Etudiant.setColumns(10);
		textField_Inscription_Etudiant.setBounds(145, 12, 203, 28);
		panel.add(textField_Inscription_Etudiant);
		
		textField_Nom_Etudiant = new JTextField();
		//textField_Nom_Etudiant.setEditable(false);
		textField_Nom_Etudiant.setColumns(10);
		textField_Nom_Etudiant.setBounds(145, 83, 203, 28);
		panel.add(textField_Nom_Etudiant);
		
		textField_Prenom_Etudiant = new JTextField();
		//textField_Prenom_Etudiant.setEditable(false);
		textField_Prenom_Etudiant.setColumns(10);
		textField_Prenom_Etudiant.setBounds(145, 110, 203, 28);
		panel.add(textField_Prenom_Etudiant);
		
		textField_Ville_Etudiant = new JTextField();
		//textField_Ville_Etudiant.setEditable(false);
		textField_Ville_Etudiant.setColumns(10);
		textField_Ville_Etudiant.setBounds(145, 241, 203, 28);
		panel.add(textField_Ville_Etudiant);
		
		textField_Addresse_Etudiant = new JTextField();
	//	textField_Addresse_Etudiant.setEditable(false);
		textField_Addresse_Etudiant.setColumns(10);
		textField_Addresse_Etudiant.setBounds(145, 214, 203, 28);
		panel.add(textField_Addresse_Etudiant);
		
		textField_Nationnalite_Etudiant = new JTextField();
		//textField_Nationnalite_Etudiant.setEditable(false);
		textField_Nationnalite_Etudiant.setColumns(10);
		textField_Nationnalite_Etudiant.setBounds(145, 187, 203, 28);
		panel.add(textField_Nationnalite_Etudiant);
		
		textField_DateIns__Etudiant = new JTextField();
		textField_DateIns__Etudiant.setVisible(false);
		//textField_DateIns__Etudiant.setEditable(false);
		textField_DateIns__Etudiant.setColumns(10);
		textField_DateIns__Etudiant.setBounds(145, 266, 203, 28);
		panel.add(textField_DateIns__Etudiant);
		
		 rdbtnM = new JRadioButton("M");
		 rdbtnM.setSelected(true);
		 rdbtnM.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		 buttonGroup.add(rdbtnM);
		//rdbtnM.setEnabled(true);
		rdbtnM.setBounds(145, 303, 42, 23);
		panel.add(rdbtnM);
		
		rdbtnF = new JRadioButton("F");
		rdbtnF.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		buttonGroup.add(rdbtnF);
		//rdbtnF.setEnabled(true);
		rdbtnF.setBounds(189, 303, 42, 23);
		panel.add(rdbtnF);
		
		JLabel label_9 = new JLabel("<html>N\u00B0 Classe : <font color= 'red'> * </font></html>");
		label_9.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_9.setBounds(10, 44, 95, 28);
		panel.add(label_9);
		
		comboBoxNumClasse = new JComboBox();
		comboBoxNumClasse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		comboBoxNumClasse.setBounds(145, 44, 203, 28);
		// ICI on charge le combobox des classe
		chargerCombo();
		
		panel.add(comboBoxNumClasse);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Identit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(384, 22, 178, 194);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		image = new JLabel("");
		image.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/file_image.png")));
		image.setBorder(new LineBorder(new Color(0, 0, 0)));
		image.setBounds(10, 26, 158, 157);
		panel_3.add(image);
		
		txtpath = new JTextField();
		txtpath.setBounds(385, 225, 141, 23);
		panel.add(txtpath);
		txtpath.setColumns(10);
		
		JButton btnParcourir = new JButton("Parcourir");
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				images();
			}
		});
		btnParcourir.setBounds(382, 259, 109, 28);
		panel.add(btnParcourir);
		
		errEIns = new JLabel("");
		errEIns.setVisible(false);
		errEIns.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errEIns.setBounds(351, 12, 24, 28);
		panel.add(errEIns);
		
		errENom = new JLabel("");
		errENom.setVisible(false);
		errENom.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errENom.setBounds(351, 83, 24, 28);
		panel.add(errENom);
		
		errEDatN = new JLabel("");
		errEDatN.setVisible(false);
		errEDatN.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errEDatN.setBounds(222, 163, 31, 28);
		panel.add(errEDatN);
		
		errEPren = new JLabel("");
		errEPren.setVisible(false);
		errEPren.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errEPren.setBounds(351, 110, 24, 28);
		panel.add(errEPren);
		
		errEDateIns = new JLabel("");
		errEDateIns.setVisible(false);
		errEDateIns.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errEDateIns.setBounds(351, 268, 24, 28);
		panel.add(errEDateIns);
		
		errEVille = new JLabel("");
		errEVille.setVisible(false);
		errEVille.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errEVille.setBounds(351, 241, 24, 28);
		panel.add(errEVille);
		
		errEAdr = new JLabel("");
		errEAdr.setVisible(false);
		errEAdr.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errEAdr.setBounds(351, 214, 24, 28);
		panel.add(errEAdr);
		
		errENat = new JLabel("");
		errENat.setVisible(false);
		errENat.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errENat.setBounds(351, 187, 24, 28);
		panel.add(errENat);
		
		JLabel lblAge = new JLabel("<html>Age : <font color= 'red'> * </font></html>");
		lblAge.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblAge.setBounds(10, 163, 125, 28);
		panel.add(lblAge);
		
		textField_Age_Etudiant = new JTextField();
		textField_Age_Etudiant.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		textField_Age_Etudiant.setEditable(false);
		textField_Age_Etudiant.setBounds(145, 163, 74, 28);
		panel.add(textField_Age_Etudiant);
		textField_Age_Etudiant.setColumns(10);
		
		txtdtnaissance = new JDateChooser();
		txtdtnaissance.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		txtdtnaissance.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				try {
					Date actuelle = new Date();
		            DateFormat dateFormat = new SimpleDateFormat("yyyy");
		            String date = dateFormat.format(actuelle);
		            String dc = date;

		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
		            String d = sdf.format(txtdtnaissance.getDate());

		            int d1 = Integer.parseInt(dc);
		            int d2 = Integer.parseInt(d);

		            int r = (d1 - d2);
		            String resultat = String.valueOf(r);
		            textField_Age_Etudiant.setText(resultat);
		            
			} catch (Exception e) {
				
			}
			}
		});
		txtdtnaissance.setDateFormatString("dd/MM/yyyy");
		txtdtnaissance.setBounds(145, 136, 204, 28);
		panel.add(txtdtnaissance);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tuteur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(596, 11, 365, 373);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField_Nom_Tuteur = new JTextField();
		//textField_Nom_Tuteur.setEditable(true);
		textField_Nom_Tuteur.setColumns(10);
		textField_Nom_Tuteur.setBounds(139, 34, 173, 28);
		panel_1.add(textField_Nom_Tuteur);
		
		JLabel label_10 = new JLabel("<html>Nom : <font color= 'red'> * </font></html>");
		label_10.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_10.setBounds(10, 42, 119, 20);
		panel_1.add(label_10);
		
		textField_Prenom_Tuteur = new JTextField();
		//textField_Prenom_Tuteur.setEditable(true);
		textField_Prenom_Tuteur.setColumns(10);
		textField_Prenom_Tuteur.setBounds(139, 61, 173, 28);
		panel_1.add(textField_Prenom_Tuteur);
		
		JLabel label_11 = new JLabel("<html>Prenom : <font color= 'red'> * </font></html>");
		label_11.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_11.setBounds(10, 67, 119, 22);
		panel_1.add(label_11);
		
		JLabel label_12 = new JLabel("<html>Nationnalit\u00E9 : <font color= 'red'> * </font></html>");
		label_12.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_12.setBounds(10, 92, 119, 24);
		panel_1.add(label_12);
		
		JLabel label_13 = new JLabel("<html>Adresse : <font color= 'red'> * </font></html>");
		label_13.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_13.setBounds(10, 176, 119, 28);
		panel_1.add(label_13);
		
		JLabel label_14 = new JLabel("<html>Ville : <font color= 'red'> * </font></html>");
		label_14.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_14.setBounds(10, 201, 119, 28);
		panel_1.add(label_14);
		
		textField_Adresse_Tuteur = new JTextField();
	//	textField_Adresse_Tuteur.setEditable(true);
		textField_Adresse_Tuteur.setColumns(10);
		textField_Adresse_Tuteur.setBounds(139, 176, 173, 28);
		panel_1.add(textField_Adresse_Tuteur);
		
		textField_Ville_Tuteur = new JTextField();
	//	textField_Ville_Tuteur.setEditable(true);
		textField_Ville_Tuteur.setColumns(10);
		textField_Ville_Tuteur.setBounds(139, 201, 173, 28);
		panel_1.add(textField_Ville_Tuteur);
		
		textField_Nationalité_Tuteur = new JTextField();
		//textField_Nationalité_Tuteur.setEditable(true);
		textField_Nationalité_Tuteur.setColumns(10);
		textField_Nationalité_Tuteur.setBounds(139, 88, 173, 28);
		panel_1.add(textField_Nationalité_Tuteur);
		
		JLabel label_15 = new JLabel("<html>CIN : <font color= 'red'> * </font></html>");
		label_15.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_15.setBounds(10, 14, 119, 21);
		panel_1.add(label_15);
		
		JLabel label_16 = new JLabel("<html>Tel_Mobile : <font color= 'red'> * </font></html>");
		label_16.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_16.setBounds(10, 228, 119, 28);
		panel_1.add(label_16);
		
		textField_TelMobile_Tuteur = new JTextField();
		//textField_TelMobile_Tuteur.setEditable(true);
		textField_TelMobile_Tuteur.setColumns(10);
		textField_TelMobile_Tuteur.setBounds(139, 228, 173, 28);
		panel_1.add(textField_TelMobile_Tuteur);
		
		JLabel label_17 = new JLabel("<html>Tel_Domicile : <font color= 'red'> * </font></html>");
		label_17.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_17.setBounds(10, 253, 119, 28);
		panel_1.add(label_17);
		
		textField_TelFixe_Tuteur = new JTextField();
		//textField_TelFixe_Tuteur.setEditable(true);
		textField_TelFixe_Tuteur.setColumns(10);
		textField_TelFixe_Tuteur.setBounds(139, 253, 173, 28);
		panel_1.add(textField_TelFixe_Tuteur);
		
		JLabel label_18 = new JLabel("<html>E-mail : <font color= 'red'> * </font></html>");
		label_18.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_18.setBounds(10, 280, 119, 23);
		panel_1.add(label_18);
		
		textField_Mail_Tuteur = new JTextField();
		//textField_Mail_Tuteur.setEditable(true);
		textField_Mail_Tuteur.setColumns(10);
		textField_Mail_Tuteur.setBounds(139, 280, 173, 28);
		panel_1.add(textField_Mail_Tuteur);
		
		JLabel label_19 = new JLabel("Profession");
		label_19.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_19.setBounds(10, 307, 119, 28);
		panel_1.add(label_19);
		
		textField_Profession_Tuteur = new JTextField();
		//textField_Profession_Tuteur.setEditable(true);
		textField_Profession_Tuteur.setColumns(10);
		textField_Profession_Tuteur.setBounds(139, 307, 173, 28);
		panel_1.add(textField_Profession_Tuteur);
		
		JLabel label_20 = new JLabel("Sexe");
		label_20.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_20.setBounds(10, 335, 52, 14);
		panel_1.add(label_20);
		
	    rdbtnMTut = new JRadioButton("M");
	    rdbtnMTut.setSelected(true);
	    rdbtnMTut.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
	    buttonGroup_1.add(rdbtnMTut);
	//	rdbtnMTut.setEnabled(true);
		rdbtnMTut.setBounds(137, 331, 52, 23);
		panel_1.add(rdbtnMTut);
		
		 rdbtnFTut = new JRadioButton("F");
		 rdbtnFTut.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		 buttonGroup_1.add(rdbtnFTut);
		//rdbtnFTut.setEnabled(true);
		rdbtnFTut.setBounds(191, 331, 58, 23);
		panel_1.add(rdbtnFTut);
		
		JLabel label_21 = new JLabel("<html>Date_Naissance : <font color= 'red'> * </font></html>");
		label_21.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_21.setBounds(10, 117, 130, 26);
		panel_1.add(label_21);
		
		textField_CIN_Tuteur = new JTextField();
		textField_CIN_Tuteur.setColumns(10);
		textField_CIN_Tuteur.setBounds(139, 7, 173, 28);
		panel_1.add(textField_CIN_Tuteur);
		
		errTCin = new JLabel("");
		errTCin.setVisible(false);
		errTCin.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTCin.setBounds(318, 7, 24, 28);
		panel_1.add(errTCin);
		
		errTNom = new JLabel("");
		errTNom.setVisible(false);
		errTNom.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTNom.setBounds(318, 34, 24, 28);
		panel_1.add(errTNom);
		
		errTNa = new JLabel("");
		errTNa.setVisible(false);
		errTNa.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTNa.setBounds(318, 88, 24, 28);
		panel_1.add(errTNa);
		
		errAdr = new JLabel("");
		errAdr.setVisible(false);
		errAdr.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errAdr.setBounds(318, 176, 24, 28);
		panel_1.add(errAdr);
		
		errTPro = new JLabel("");
		errTPro.setVisible(false);
		errTPro.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTPro.setBounds(318, 307, 24, 28);
		panel_1.add(errTPro);
		
		errTTelM = new JLabel("");
		errTTelM.setVisible(false);
		errTTelM.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTTelM.setBounds(318, 228, 24, 28);
		panel_1.add(errTTelM);
		
		errTPren = new JLabel("");
		errTPren.setVisible(false);
		errTPren.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTPren.setBounds(318, 61, 24, 28);
		panel_1.add(errTPren);
		
		errTTelDom = new JLabel("");
		errTTelDom.setVisible(false);
		errTTelDom.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTTelDom.setBounds(318, 253, 24, 28);
		panel_1.add(errTTelDom);
		
		errTVille = new JLabel("");
		errTVille.setVisible(false);
		errTVille.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTVille.setBounds(318, 201, 24, 28);
		panel_1.add(errTVille);
		
		errTDateN = new JLabel("");
		errTDateN.setVisible(false);
		errTDateN.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTDateN.setBounds(318, 115, 24, 28);
		panel_1.add(errTDateN);
		
		errTMail = new JLabel("");
		errTMail.setVisible(false);
		errTMail.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/error.png")));
		errTMail.setBounds(318, 280, 24, 28);
		panel_1.add(errTMail);
		
		txtdtnaissance_tuteur = new JDateChooser();
		txtdtnaissance_tuteur.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				try {
					Date actuelle = new Date();
		            DateFormat dateFormat = new SimpleDateFormat("yyyy");
		            String date = dateFormat.format(actuelle);
		            String dc = date;

		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
		            String d = sdf.format(txtdtnaissance_tuteur.getDate());

		            int d1 = Integer.parseInt(dc);
		            int d2 = Integer.parseInt(d);

		            int r = (d1 - d2);
		            String resultat = String.valueOf(r);
		            textField_Age_Tuteur.setText(resultat);
		            
			} catch (Exception e) {
				
			}
			}
		});
		txtdtnaissance_tuteur.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		txtdtnaissance_tuteur.setDateFormatString("dd/MM/yyyy");
		txtdtnaissance_tuteur.setBounds(138, 115, 173, 28);
		panel_1.add(txtdtnaissance_tuteur);
		
		JLabel lblAge_1 = new JLabel("Age");
		lblAge_1.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblAge_1.setBounds(10, 147, 119, 28);
		panel_1.add(lblAge_1);
		
		textField_Age_Tuteur = new JTextField();
		textField_Age_Tuteur.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		textField_Age_Tuteur.setEditable(false);
		textField_Age_Tuteur.setColumns(10);
		textField_Age_Tuteur.setBounds(139, 143, 74, 32);
		panel_1.add(textField_Age_Tuteur);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Validation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(305, 395, 322, 89);
		contentPane.add(panel_2);
		
		JLabel lblValider = new JLabel("");
		lblValider.setHorizontalAlignment(SwingConstants.CENTER);
		lblValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(validation_Eleve() & validation_Tuteur()){
					Tuteur t =new Tuteur();
					t=genererTuteur();
					Eleve e = new Eleve();
					e=genererEleve();
					if(tuteurDAO.create(t)){
						if(eleveDAO.create(e)){
							JOptionPane.showMessageDialog(null, "Succèes !");
							p.chargerTable_eleve();
						}
					}else
						JOptionPane.showMessageDialog(null, "Echec !");;
				
					
					
				}
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});
		lblValider.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/Ok-icon.png")));
		lblValider.setBounds(10, 19, 93, 59);
		panel_2.add(lblValider);
		
		JLabel lblAnnuler = new JLabel("");
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//setVisible(false);
				System.exit(3);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});
		lblAnnuler.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/Button-Close-icon.png")));
		lblAnnuler.setBounds(108, 19, 93, 59);
		panel_2.add(lblAnnuler);
		
		JLabel label_23 = new JLabel("");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setIcon(new ImageIcon(FrameAjouterEleve.class.getResource("/image/clear.png")));
		label_23.setBounds(235, 19, 77, 59);
		panel_2.add(label_23);
		
		JLabel label_24 = new JLabel("Nettoyer");
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_24.setBounds(235, 64, 77, 14);
		panel_2.add(label_24);
		
		JLabel lblAnnuler_1 = new JLabel("Annuler");
		lblAnnuler_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAnnuler_1.setBounds(113, 64, 88, 14);
		panel_2.add(lblAnnuler_1);
		
		JLabel lblValider_1 = new JLabel("Valider");
		lblValider_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblValider_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblValider_1.setBounds(10, 64, 93, 14);
		panel_2.add(lblValider_1);
		
		textField_Date_Naiss_Tuteur = new JTextField();
		textField_Date_Naiss_Tuteur.setVisible(false);
		textField_Date_Naiss_Tuteur.setBounds(722, 413, 173, 28);
		contentPane.add(textField_Date_Naiss_Tuteur);
		//textField_Date_Naiss_Tuteur.setEditable(true);
		textField_Date_Naiss_Tuteur.setColumns(10);
	}
	
	// cette methode crée un éleve et le retoune à partir des informations saisis dans le champs textes de l' éleve 

			Eleve genererEleve(){
				Long num=Long.parseLong(textField_Inscription_Etudiant.getText());
				String s = ((String)comboBoxNumClasse.getSelectedItem());
				Long numclass= new ClasseDAO().findByNom(s).getNum_Clase();
				String nom=textField_Nom_Etudiant.getText();
				String prenom=textField_Prenom_Etudiant.getText();
				String nat=textField_Nationnalite_Etudiant.getText();
				String datenaiss=((JTextField)txtdtnaissance.getDateEditor().getUiComponent()).getText();
				
				//String dateins=textField_DateIns__Etudiant.getText();
				String addresse=textField_Addresse_Etudiant.getText();
				String v=textField_Ville_Etudiant.getText();
				String sex = null;
				Long cinT=Long.parseLong(textField_CIN_Tuteur.getText());
				String photos_Eleve=txtpath.getText();
				if(rdbtnM.isSelected())
					sex ="masculin";
				
				else if(rdbtnF.isSelected())
					sex="feminin";
					 
				Eleve eleve=new Eleve(num,cinT,numclass,nom,prenom,nat,datenaiss,addresse,v,sex,photos_Eleve);
				return eleve;
			}
			
			// Cette methode permet de charger le combo en nom des Classe
			public void chargerCombo(){
				ClasseDAO da = new ClasseDAO();
				Vector<Classe> v = new Vector<Classe>();
				v=da.findAll();
				for (Classe classe : v) {
					comboBoxNumClasse.addItem(classe.getNom_Clase());
				}
			}
			// cette methode crée un tuteur et le retoune à partir des informations saisis dans le champs textes du tuteur 
			Tuteur genererTuteur(){
				Long cin=Long.parseLong(textField_CIN_Tuteur.getText());
				String nom=textField_Nom_Tuteur.getText();
				String prenom=textField_Prenom_Tuteur.getText();
				String adr=textField_Adresse_Tuteur.getText();
				String nat=textField_Nationalité_Tuteur.getText();
				
				String datenaiss=((JTextField)txtdtnaissance_tuteur.getDateEditor().getUiComponent()).getText();
				String sex = null;
				if(rdbtnMTut.isSelected())
					 sex="masculin" ;
					
				else if(rdbtnFTut.isSelected())
					 	sex="feminin";
	            String prof=textField_Profession_Tuteur.getText();
	            Long telM=Long.parseLong(textField_TelMobile_Tuteur.getText());
	            Long telfix=Long.parseLong(textField_TelFixe_Tuteur.getText());
	            String villeT=textField_Ville_Tuteur.getText();
	            String mail=textField_Mail_Tuteur.getText();
				
				Tuteur tut=new Tuteur(cin,nom,prenom,datenaiss,sex,nat,prof,adr,villeT,telM,telfix,mail);
				return tut;
				
			}
			public void effacer_Champs(){
				 textField_Inscription_Etudiant.setText("");
				 textField_Nom_Etudiant.setText("");
				// textField_DateNaiss_Etudiant.setText("");
				 textField_Prenom_Etudiant.setText("");
			     textField_Ville_Etudiant.setText("");
				 textField_Addresse_Etudiant.setText("");
				 textField_Nationnalite_Etudiant.setText("");
				 textField_DateIns__Etudiant.setText("");
				 textField_Nom_Tuteur.setText("");
				 textField_Prenom_Tuteur.setText("");
				 textField_Date_Naiss_Tuteur.setText("");
			     textField_Adresse_Tuteur.setText("");
				 textField_Ville_Tuteur.setText("");
				 textField_Nationalité_Tuteur.setText("");
			     textField_CIN_Tuteur.setText("");
				 textField_TelMobile_Tuteur.setText("");
			     textField_TelFixe_Tuteur.setText("");
				 textField_Mail_Tuteur.setText("");
			     textField_Profession_Tuteur.setText("");
			   // rdbtnF.setSelected(false);
				//rdbtnM.setSelected(false);
				//rdbtnFTut.setSelected(false);
				 //rdbtnMTut.setSelected(false);
			}
			public void Activer_Champs(boolean b){
				 textField_Inscription_Etudiant.setEditable(b);
				 textField_Nom_Etudiant.setEditable(b);
				 //textField_DateNaiss_Etudiant.setEditable(b);
				 textField_Prenom_Etudiant.setEditable(b);
				 textField_Ville_Etudiant.setEditable(b);
				 textField_Addresse_Etudiant.setEditable(b);
				 textField_Nationnalite_Etudiant.setEditable(b);
				 textField_DateIns__Etudiant.setEditable(b);
				 textField_Nom_Tuteur.setEditable(b);
				 textField_Prenom_Tuteur.setEditable(b);
				 textField_Adresse_Tuteur.setEditable(b);
				 textField_Ville_Tuteur.setEditable(b);
				 textField_Nationalité_Tuteur.setEditable(b);
				 textField_CIN_Tuteur.setEditable(b);
				 textField_TelMobile_Tuteur.setEditable(b);
				 textField_TelFixe_Tuteur.setEditable(b);
				 textField_Mail_Tuteur.setEditable(b);
				 textField_Profession_Tuteur.setEditable(b);
				 textField_Date_Naiss_Tuteur.setEditable(b);
				
			}
			public boolean validation_Tuteur(){
				boolean bol= true;
				String regNom= "^[a-zA-Z\\s]+";
				/* Validation Email */
				EmailValidator emailValidator= EmailValidator.getInstance() ;
				if(!emailValidator.isValid(textField_Mail_Tuteur.getText())){
					errTMail.setVisible(true);
					bol=false;
				}	
				else
					errTMail.setVisible(false);
				
				/* Validation Cin */
				try{
					Integer.parseInt(textField_CIN_Tuteur.getText().toString());
					String numero=textField_CIN_Tuteur.getText();
					if(numero.length()!=8 ){
						errTCin.setVisible(true);
						errTCin.setToolTipText("numero incorrecte");
						bol=false;
					}else{
						errTCin.setVisible(false);
					}
						
				}catch (NumberFormatException  e) {
					errTCin.setVisible(true);
					errTCin.setToolTipText("pas de chaine");
					errTCin.setText("numero ne doit pas pas contenir de lettre");
					bol=false;
				}
				
				/* validaton de numero de tel du tuteur*/
				try{
					Integer.parseInt(textField_TelFixe_Tuteur.getText().toString());
					String numero=textField_TelFixe_Tuteur.getText();
					if(numero.charAt(0)=='0' || numero.length()!=8 ){
						errTTelDom.setVisible(true);
						errTTelDom.setToolTipText("numero incorrecte");
						bol=false;
					}else{
						errTTelDom.setVisible(false);
					}
						
				}catch (NumberFormatException  e) {
					errTTelDom.setVisible(true);
					errTTelDom.setText("numero ne doit pas pas contenir de lettre");
					bol=false;
				}
				try{
					Integer.parseInt(textField_TelMobile_Tuteur.getText());
					String numero=textField_TelMobile_Tuteur.getText();
					if(numero.charAt(0)=='0' || numero.length()!=8 ){
						errTTelM.setVisible(true);
						errTTelM.setToolTipText("numero incorrecte");
						bol=false;
					}else{
						errTTelM.setVisible(false);
					}
						
				}catch (NumberFormatException  e) {
					errTTelM.setVisible(true);
					errTTelM.setToolTipText("numero ne doit pas pas contenir de lettre");
					bol=false;
				} 
				/* Validation Nom Tuteur*/ 
				
				if(!textField_Nom_Tuteur.getText().matches(regNom)){
					errTNom.setVisible(true);
					errTNom.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errTNom.setVisible(false);
				}
				
				/* Validation Prenom*/

				if(!textField_Prenom_Tuteur.getText().matches(regNom)){
					errTPren.setVisible(true);
					errTPren.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errTPren.setVisible(false);
				}
				
				/* Validation Proffession*/

				if(!textField_Profession_Tuteur.getText().matches(regNom)){
					errTPro.setVisible(true);
					errTPro.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errTPro.setVisible(false);
				}
				
				/* Valiadtion Ville*/
				if(!textField_Ville_Tuteur.getText().matches(regNom)){
					errTVille.setVisible(true);
					errTVille.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errTVille.setVisible(false);
				}
				
				/* Validation Nationnalité*/ 
				if(!textField_Nationalité_Tuteur.getText().matches(regNom)){
					errTNa.setVisible(true);
					errTNa.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errTNa.setVisible(false);
				}
				
				// Verification de l'age
				try {
					Long age=Long.parseLong(textField_Age_Tuteur.getText());
					if(!(age>= 20 && age<100) ){
						errTDateN.setVisible(true);
						errTDateN.setToolTipText("Age 20 -->> 100 !");
						bol=false;
					}else
						errTDateN.setVisible(false);
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
				
				return bol;
			}
			
			/* Validation  Eleve */
			public boolean validation_Eleve(){
				boolean bol=true;
				String regNom= "^[a-zA-Z\\s]+";
				
				/* Validation Numero Inscription */ 
				try{
					Long num=Long.parseLong(textField_Inscription_Etudiant.getText());
					String numero=textField_Inscription_Etudiant.getText();
					
					if(numero.length()!=8 || eleveDAO.find(num)!=null){
						errEIns.setVisible(true);
						if(eleveDAO.find(num)!=null)
							errEIns.setToolTipText("Identifiant Existant !");
						if(numero.length()!=8)
							errEIns.setToolTipText("numero incorrecte");
						bol=false;
						
					}else{
						errEIns.setVisible(false);
					}
						
				}catch (NumberFormatException  e) {
					errEIns.setVisible(true);
					errEIns.setToolTipText("numero d'inscription ne doit pas contenir de lettre");
					bol=false;
				} 
				
				/* Validation Nationnalité*/ 
				if(!textField_Nationnalite_Etudiant.getText().matches(regNom)){
					errENat.setVisible(true);
					errENat.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errENat.setVisible(false);
				}
				
				/* Valiadtion Ville*/
				if(!textField_Ville_Etudiant.getText().matches(regNom)){
					errEVille.setVisible(true);
					errEVille.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errEVille.setVisible(false);
				}
				
				/* Validation Nom Eleve*/ 
				
				if(!textField_Nom_Etudiant.getText().matches(regNom)){
					errENom.setVisible(true);
					errENom.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errENom.setVisible(false);
				}
				
				/* Validation Prenom Eleve*/

				if(!textField_Prenom_Etudiant.getText().matches(regNom)){
					errEPren.setVisible(true);
					errEPren.setToolTipText("chaine non Valide");
					bol=false;
				}else{
					errEPren.setVisible(false);
				}
				/* Validation date Naissance */
				try {
					Long age=Long.parseLong(textField_Age_Etudiant.getText());
					if(!(age>= 7 && age<15)){
						errEDatN.setVisible(true);
						errEDatN.setToolTipText("Age 7 -->> 15 !");
						bol=false;
					}else
						errEDatN.setVisible(false);
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
				
					
				
				return bol;
			}
			
			/*-------------- La methode pour changer l'image et le path ------------*/
			 public void images() {
			        filen();
			        try {
			        if (path != null) {
			        	image.setIcon(new ImageIcon(path));
			            txtpath.setText(path);            
			            } 
			            }catch (Exception e) {
			                e.printStackTrace();
			        }

			    }
			 /*--------- La methode pour choisir une photo dans le pc ----------------*/
			 public void filen() {
				  String filename= null;
			        try {

			            JFileChooser chooser = new JFileChooser();
			            chooser.setDialogTitle("Choisir une image png");
			            chooser.setApproveButtonText("Ajouter une image");
			            chooser.showOpenDialog(null);
			            File f = chooser.getSelectedFile();
			            filename = f.getAbsolutePath();
			            path = (filename);
			        } catch (Exception e) {
			            System.out.println(e);
			            JOptionPane.showMessageDialog(null," Veuiler choisir une image ");;
			        }
			      
			}
}
