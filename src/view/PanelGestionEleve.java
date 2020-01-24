package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.batik.gvt.text.TextPath;
import org.apache.commons.validator.EmailValidator;

import classeMetier.Absence_Eleve;
import classeMetier.Classe;
import classeMetier.Eleve;
import classeMetier.Tuteur;
import connxion_Requete.Connexion;
import dao.implement.Absence_EleveDAO;
import dao.implement.ClasseDAO;
import dao.implement.EleveDAO;
import dao.implement.TuteurDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;

public class PanelGestionEleve extends JPanel {
	private JTable table_Etudiant;
	private JTextField textField_Rechercher_Nom;
	private JPanel panel_Liste_Etudiant;
	private JPanel panel_Opreation;
	private JPanel panel_Opreation_Valider;
	private JPanel panel_Impression;
	private JButton btnNewButton_1;
	private JButton btnAttestationnDe;
	private JButton btnAttestationDeScolait;
	private JButton btnHisstoirqueDesAbsences;
	private JPanel panel_Fiche_Etudiant;
	private JPanel panel_Tuteur;
	private JPanel panel_Absence;
	private JScrollPane scrollPane;
	private JLabel lblAdresse;
	private JLabel lblVille;
	private JLabel lblSexe;
	private JLabel lblDateDinscription;
	private JLabel lblAjouter;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField_Inscription_Etudiant;
	private JTextField textField_Nom_Etudiant;
	private JTextField textField_Prenom_Etudiant;
	private JTextField textField_Ville_Etudiant;
	private JTextField textField_Addresse_Etudiant;
	private JTextField textField_Nationnalite_Etudiant;
	private JTextField textField_DateIns__Etudiant;
	private JTextField textField_Nom_Tuteur;
	private JTextField textField_Prenom_Tuteur;
	private JTextField textField_Adresse_Tuteur;
	private JTextField textField_Ville_Tuteur;
	private JTextField textField_Nationalité_Tuteur;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_CIN_Tuteur;
	private JTextField textField_TelMobile_Tuteur;
	private JTextField textField_TelFixe_Tuteur;
	private JTextField textField_Mail_Tuteur;
	private JTextField textField_Profession_Tuteur;
	private JComboBox<String> comboBoxNumClasse;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTable table_Absence;
	private JScrollPane scrollPane_2;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private JRadioButton rdbtnMTut;
	private JRadioButton rdbtnFTut;
	private EleveDAO eleveDAO;
	private Absence_EleveDAO absence_EleveDAO;
	private TuteurDAO tuteurDAO;
	//private JTextField textField_Date_Naiss_Tuteur;
	private JLabel label;
	private JLabel label_10;
	private JTextField txtpath;
	private JLabel lblAjouter_1;
	private JLabel errTDateN;
	private JLabel errTNom;
	private JLabel errTPren;
	private JLabel errTNa;
	private JLabel errAdr;
	private JLabel errTVille;
	private JLabel errTTelM;
	private JLabel errTTelDom;
	private JLabel errTMail;
	private JLabel errTPro;
	private JLabel errENom;
	private JLabel errEPren;
	private JLabel errEVille;
	private JLabel errEDateIns;
	private JLabel errENat;
	private JLabel lblAge;
	private JTextField ageEleve;
	private JLabel errEDatN;
	private JDateChooser txtdtnaissance;
	private String path;
	private JLabel image;
	private JDateChooser textField_DateIns__Etudiant_1;
	private JDateChooser txtdtnaissance_Tuteur;
	private JLabel label_11;
	private JLabel lblValider;
	private JLabel lblAnnuler;
	private JLabel lblNettoyer;
	private JPanel panel_1;
	private DefaultTableCellRenderer centerRender;
	public PanelGestionEleve() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		eleveDAO=new EleveDAO();
		tuteurDAO=new TuteurDAO();
		absence_EleveDAO= new Absence_EleveDAO();
		centerRender= new DefaultTableCellRenderer() ;
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		
		panel_Liste_Etudiant = new JPanel();
		panel_Liste_Etudiant.setBorder(new TitledBorder(null, "Liste des Etudiants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Liste_Etudiant.setBounds(10, 43, 252, 476);
		add(panel_Liste_Etudiant);
		panel_Liste_Etudiant.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 235, 397);
		panel_Liste_Etudiant.add(scrollPane);
		
		table_Etudiant = new JTable();
		table_Etudiant.setSelectionBackground(new Color(128, 128, 128));
		table_Etudiant.setRowHeight(30);
		// cette permet de charger elev selectionné dans les champs
		table_Etudiant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Activer_Champs(false);
				chargerEleve_Tuteur_Dans_Champ(obtenir_Eleve_Selection());
				remove(panel_Opreation_Valider);
				repaint();
		        add(panel_Opreation);
				
			}
		});
		/*Single line selection , column not modifiable*/
		table_Etudiant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*Jtable not draggble , not resizble*/
		table_Etudiant.getTableHeader().setReorderingAllowed(false);
		table_Etudiant.getTableHeader().setResizingAllowed(false);
		table_Etudiant.setDefaultEditor(Object.class,null);
		scrollPane.setViewportView(table_Etudiant);
		chargerTable_eleve();
		
		textField_Rechercher_Nom = new JTextField();
		textField_Rechercher_Nom.setBounds(10, 28, 195, 29);
		panel_Liste_Etudiant.add(textField_Rechercher_Nom);
		textField_Rechercher_Nom.setColumns(10);
		
		JLabel label_Recherche_Etudiant = new JLabel("");
		label_Recherche_Etudiant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					chargerTable_eleve_Rechercher();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if(textField_Rechercher_Nom.getText().equals(""))
					chargerTable_eleve();
			}
		});
		label_Recherche_Etudiant.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/search.png")));
		label_Recherche_Etudiant.setBounds(208, 28, 34, 29);
		panel_Liste_Etudiant.add(label_Recherche_Etudiant);
		
		
		panel_Fiche_Etudiant = new JPanel();
		panel_Fiche_Etudiant.setBorder(new TitledBorder(null, "Fiche Etudiant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Fiche_Etudiant.setBounds(272, 43, 829, 404);
		add(panel_Fiche_Etudiant);
		panel_Fiche_Etudiant.setLayout(null);
		
		JTabbedPane tabbedPane_Fiche_Etudiant = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_Fiche_Etudiant.setBounds(10, 24, 809, 369);
		panel_Fiche_Etudiant.add(tabbedPane_Fiche_Etudiant);
		
		JPanel panel_Etudiant = new JPanel();
		tabbedPane_Fiche_Etudiant.addTab("Etudiant", null, panel_Etudiant, null);
		panel_Etudiant.setLayout(null);
		
		JLabel lbl_Num_Inscription = new JLabel("<html>N\u00B0 inscription : <font color= 'red'> * </font></html>");
		lbl_Num_Inscription.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lbl_Num_Inscription.setBounds(10, 8, 150, 28);
		panel_Etudiant.add(lbl_Num_Inscription);
		
		JLabel lblNom = new JLabel("<html>Nom : <font color= 'red'> * </font></html>");
		lblNom.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNom.setBounds(10, 70, 150, 26);
		panel_Etudiant.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblPrenom.setBounds(10, 97, 150, 28);
		panel_Etudiant.add(lblPrenom);
		
		JLabel lblDateDeNaissance = new JLabel("<html>Date de naissance  : <font color= 'red'> * </font></html>");
		lblDateDeNaissance.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblDateDeNaissance.setBounds(10, 123, 150, 28);
		panel_Etudiant.add(lblDateDeNaissance);
		
		JLabel lblNationnalit = new JLabel("Nationnalit\u00E9");
		lblNationnalit.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNationnalit.setBounds(10, 180, 150, 25);
		panel_Etudiant.add(lblNationnalit);
		
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblAdresse.setBounds(10, 207, 150, 28);
		panel_Etudiant.add(lblAdresse);
		
		lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblVille.setBounds(10, 234, 150, 28);
		panel_Etudiant.add(lblVille);
		
		lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblSexe.setBounds(10, 300, 52, 14);
		panel_Etudiant.add(lblSexe);
		
		lblDateDinscription = new JLabel("Date d'inscription");
		lblDateDinscription.setVisible(false);
		lblDateDinscription.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblDateDinscription.setBounds(10, 266, 150, 23);
		panel_Etudiant.add(lblDateDinscription);
		
		textField_Inscription_Etudiant = new JTextField();
		textField_Inscription_Etudiant.setEnabled(false);
		textField_Inscription_Etudiant.setBounds(170, 8, 194, 28);
		panel_Etudiant.add(textField_Inscription_Etudiant);
		textField_Inscription_Etudiant.setColumns(10);
		
		textField_Nom_Etudiant = new JTextField();
		textField_Nom_Etudiant.setEditable(false);
		textField_Nom_Etudiant.setColumns(10);
		textField_Nom_Etudiant.setBounds(170, 70, 194, 28);
		panel_Etudiant.add(textField_Nom_Etudiant);
		
		textField_Prenom_Etudiant = new JTextField();
		textField_Prenom_Etudiant.setEditable(false);
		textField_Prenom_Etudiant.setColumns(10);
		textField_Prenom_Etudiant.setBounds(170, 97, 194, 28);
		panel_Etudiant.add(textField_Prenom_Etudiant);
		
		textField_Ville_Etudiant = new JTextField();
		textField_Ville_Etudiant.setEditable(false);
		textField_Ville_Etudiant.setColumns(10);
		textField_Ville_Etudiant.setBounds(170, 234, 194, 28);
		panel_Etudiant.add(textField_Ville_Etudiant);
		
		textField_Addresse_Etudiant = new JTextField();
		textField_Addresse_Etudiant.setEditable(false);
		textField_Addresse_Etudiant.setColumns(10);
		textField_Addresse_Etudiant.setBounds(170, 207, 194, 28);
		panel_Etudiant.add(textField_Addresse_Etudiant);
		
		textField_Nationnalite_Etudiant = new JTextField();
		textField_Nationnalite_Etudiant.setEditable(false);
		textField_Nationnalite_Etudiant.setColumns(10);
		textField_Nationnalite_Etudiant.setBounds(170, 180, 194, 28);
		panel_Etudiant.add(textField_Nationnalite_Etudiant);
		
		textField_DateIns__Etudiant = new JTextField();
		textField_DateIns__Etudiant.setEditable(false);
		textField_DateIns__Etudiant.setColumns(10);
		textField_DateIns__Etudiant.setBounds(347, 302, 194, 28);
		//panel_Etudiant.add(textField_DateIns__Etudiant);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		buttonGroup.add(rdbtnM);
		rdbtnM.setBounds(171, 296, 42, 23);
		panel_Etudiant.add(rdbtnM);
		
		
		 rdbtnF = new JRadioButton("F");
		 rdbtnF.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		buttonGroup.add(rdbtnF);
		rdbtnF.setBounds(215, 296, 48, 23);
		panel_Etudiant.add(rdbtnF);
		
		JLabel lblNClasse = new JLabel("<html>N\u00B0 Classe : <font color= 'red'> * </font></html>");
		lblNClasse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNClasse.setBounds(10, 47, 150, 25);
		panel_Etudiant.add(lblNClasse);
		
		comboBoxNumClasse = new JComboBox();
		comboBoxNumClasse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		comboBoxNumClasse.setBounds(170, 40, 194, 28);
		// ICI on charge le combobox des classe
		ClasseDAO da = new ClasseDAO();
		Vector<Classe> v = new Vector<Classe>();
		v=da.findAll();
		for (Classe classe : v) {
			comboBoxNumClasse.addItem(classe.getNom_Clase());
		}
		
		
		
		
		panel_Etudiant.add(comboBoxNumClasse);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Identit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(458, 8, 178, 194);
		panel_Etudiant.add(panel);
		
		image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.LEFT);
		image.setHorizontalTextPosition(SwingConstants.CENTER);
		image.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/file_image.png")));
		image.setBorder(new LineBorder(new Color(0, 0, 0)));
		image.setBounds(10, 26, 158, 157);
		panel.add(image);
		
		txtpath = new JTextField();
		txtpath.setEditable(false);
		txtpath.setBounds(458, 217, 142, 28);
		panel_Etudiant.add(txtpath);
		txtpath.setColumns(10);
		
		JButton btnParourir = new JButton("Parourir");
		btnParourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				images();
			}
		});
		btnParourir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnParourir.setBounds(458, 252, 106, 28);
		panel_Etudiant.add(btnParourir);
		
		errENom = new JLabel("");
		errENom.setVisible(false);
		errENom.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errENom.setBounds(369, 70, 20, 26);
		panel_Etudiant.add(errENom);
		
		errEPren = new JLabel("");
		errEPren.setVisible(false);
		errEPren.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errEPren.setBounds(369, 97, 20, 24);
		panel_Etudiant.add(errEPren);
		
		errEVille = new JLabel("");
		errEVille.setVisible(false);
		errEVille.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errEVille.setBounds(369, 234, 20, 28);
		panel_Etudiant.add(errEVille);
		
		JLabel errEAdr = new JLabel("");
		errEAdr.setVisible(false);
		errEAdr.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errEAdr.setBounds(368, 207, 21, 28);
		panel_Etudiant.add(errEAdr);
		
		errEDatN = new JLabel("");
		errEDatN.setVisible(false);
		errEDatN.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errEDatN.setBounds(369, 123, 20, 32);
		panel_Etudiant.add(errEDatN);
		
		errENat = new JLabel("");
		errENat.setVisible(false);
		errENat.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errENat.setBounds(369, 180, 20, 28);
		panel_Etudiant.add(errENat);
		
		errEDateIns = new JLabel("");
		errEDateIns.setVisible(false);
		errEDateIns.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errEDateIns.setBounds(369, 259, 20, 28);
		panel_Etudiant.add(errEDateIns);
		
		txtdtnaissance = new JDateChooser();
		((JTextField)txtdtnaissance.getDateEditor().getUiComponent()).setEditable(false);	
		txtdtnaissance.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				try {
					Date actuelle = new Date();
		            DateFormat dateFormat = new SimpleDateFormat("yyyy");
		            String date = dateFormat.format(actuelle);
		            String dateCourante = date;

		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
		            String dateChoisie = sdf.format(txtdtnaissance.getDate());

		            int dateCourant = Integer.parseInt(dateCourante);
		            int dateChoisi = Integer.parseInt(dateChoisie);

		            int r = (dateCourant - dateChoisi);
		            String resultat = String.valueOf(r);
		            ageEleve.setText(resultat);
			} catch (Exception e) {
				
			}
			}
		});
		txtdtnaissance.setDateFormatString("dd/MM/yyyy");
		txtdtnaissance.setBounds(170, 123, 194, 28);
		panel_Etudiant.add(txtdtnaissance);
		
		lblAge = new JLabel("<html>Age : <font color= 'red'> * </font></html>");
		lblAge.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblAge.setBounds(10, 147, 150, 26);
		panel_Etudiant.add(lblAge);
		
		ageEleve = new JTextField();
		ageEleve.setEditable(false);
		
		ageEleve.setColumns(10);
		ageEleve.setBounds(170, 150, 93, 29);
		panel_Etudiant.add(ageEleve);
		
		textField_DateIns__Etudiant_1 = new JDateChooser();
		textField_DateIns__Etudiant_1.setVisible(false);
		textField_DateIns__Etudiant_1.setEnabled(false);
		textField_DateIns__Etudiant_1.setDateFormatString("dd/MM/yyyy");
		textField_DateIns__Etudiant_1.setBounds(170, 263, 194, 28);
		panel_Etudiant.add(textField_DateIns__Etudiant_1);
		
		panel_Tuteur = new JPanel();
		tabbedPane_Fiche_Etudiant.addTab("Tuteur", null, panel_Tuteur, null);
		panel_Tuteur.setLayout(null);
		
		textField_Nom_Tuteur = new JTextField();
		textField_Nom_Tuteur.setEditable(false);
		textField_Nom_Tuteur.setBounds(164, 33, 220, 28);
		textField_Nom_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_Nom_Tuteur);
		
		JLabel label_3 = new JLabel("<html>Nom : <font color= 'red'> * </font></html>");
		label_3.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_3.setBounds(10, 34, 144, 28);
		panel_Tuteur.add(label_3);
		
		textField_Prenom_Tuteur = new JTextField();
		textField_Prenom_Tuteur.setEditable(false);
		textField_Prenom_Tuteur.setBounds(164, 61, 220, 28);
		textField_Prenom_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_Prenom_Tuteur);
		
		JLabel label_4 = new JLabel("<html>Prenom : <font color= 'red'> * </font></html>");
		label_4.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_4.setBounds(10, 61, 144, 28);
		panel_Tuteur.add(label_4);
		
		JLabel label_5 = new JLabel("Nationnalit\u00E9");
		label_5.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_5.setBounds(10, 88, 144, 28);
		panel_Tuteur.add(label_5);
		
		JLabel label_6 = new JLabel("<html>Adresse : <font color= 'red'> * </font></html>");
		label_6.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_6.setBounds(10, 147, 144, 28);
		panel_Tuteur.add(label_6);
		
		JLabel label_7 = new JLabel("Ville");
		label_7.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_7.setBounds(10, 172, 144, 28);
		panel_Tuteur.add(label_7);
		
		textField_Adresse_Tuteur = new JTextField();
		textField_Adresse_Tuteur.setEditable(false);
		textField_Adresse_Tuteur.setBounds(164, 148, 220, 28);
		textField_Adresse_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_Adresse_Tuteur);
		
		textField_Ville_Tuteur = new JTextField();
		textField_Ville_Tuteur.setEditable(false);
		textField_Ville_Tuteur.setBounds(164, 173, 220, 28);
		textField_Ville_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_Ville_Tuteur);
		
		textField_Nationalité_Tuteur = new JTextField();
		textField_Nationalité_Tuteur.setEditable(false);
		textField_Nationalité_Tuteur.setBounds(164, 88, 220, 28);
		textField_Nationalité_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_Nationalité_Tuteur);
		
		textField_CIN_Tuteur = new JTextField();
		textField_CIN_Tuteur.setEnabled(false);
		textField_CIN_Tuteur.setBounds(164, 7, 220, 28);
		textField_CIN_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_CIN_Tuteur);
		
		JLabel lblCin = new JLabel("<html>CIN : <font color= 'red'> * </font></html>");
		lblCin.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblCin.setBounds(10, 7, 144, 28);
		panel_Tuteur.add(lblCin);
		
		JLabel lblTelmobile = new JLabel("<html>Tel_Mobile : <font color= 'red'> * </font></html>");
		lblTelmobile.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblTelmobile.setBounds(10, 199, 144, 28);
		panel_Tuteur.add(lblTelmobile);
		
		textField_TelMobile_Tuteur = new JTextField();
		textField_TelMobile_Tuteur.setEditable(false);
		textField_TelMobile_Tuteur.setBounds(164, 200, 220, 28);
		textField_TelMobile_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_TelMobile_Tuteur);
		
		JLabel lblTeldomicile = new JLabel("<html>Tel_Domicile : <font color= 'red'> * </font></html>");
		lblTeldomicile.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblTeldomicile.setBounds(10, 224, 144, 28);
		panel_Tuteur.add(lblTeldomicile);
		
		textField_TelFixe_Tuteur = new JTextField();
		textField_TelFixe_Tuteur.setEditable(false);
		textField_TelFixe_Tuteur.setBounds(164, 225, 220, 28);
		textField_TelFixe_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_TelFixe_Tuteur);
		
		JLabel lblEmail = new JLabel("<html>E-mail : <font color= 'red'> * </font></html>");
		lblEmail.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblEmail.setBounds(10, 251, 144, 28);
		panel_Tuteur.add(lblEmail);
		
		textField_Mail_Tuteur = new JTextField();
		textField_Mail_Tuteur.setEditable(false);
		textField_Mail_Tuteur.setBounds(164, 252, 220, 28);
		textField_Mail_Tuteur.setColumns(10);
		panel_Tuteur.add(textField_Mail_Tuteur);
		
		JLabel lblProfession = new JLabel("Profession");
		lblProfession.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblProfession.setBounds(10, 278, 144, 28);
		panel_Tuteur.add(lblProfession);
		
		textField_Profession_Tuteur = new JTextField();
		textField_Profession_Tuteur.setEditable(false);
		textField_Profession_Tuteur.setColumns(10);
		textField_Profession_Tuteur.setBounds(164, 279, 220, 28);
		panel_Tuteur.add(textField_Profession_Tuteur);
		
		JLabel label_8 = new JLabel("Sexe");
		label_8.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_8.setBounds(12, 318, 52, 14);
		panel_Tuteur.add(label_8);
		
		 rdbtnMTut = new JRadioButton("M");
		 rdbtnMTut.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		buttonGroup_1.add(rdbtnMTut);
		rdbtnMTut.setBounds(164, 319, 49, 21);
		panel_Tuteur.add(rdbtnMTut);
		
		 rdbtnFTut = new JRadioButton("F");
		 rdbtnFTut.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		buttonGroup_1.add(rdbtnFTut);
		rdbtnFTut.setBounds(215, 320, 52, 18);
		panel_Tuteur.add(rdbtnFTut);
		
		JLabel lblDatenaiss = new JLabel("Date_Naissance");
		lblDatenaiss.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblDatenaiss.setBounds(10, 115, 144, 22);
		panel_Tuteur.add(lblDatenaiss);
		
		errTNom = new JLabel("");
		errTNom.setVisible(false);
		errTNom.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTNom.setBounds(390, 34, 27, 28);
		panel_Tuteur.add(errTNom);
		
		errTNa = new JLabel("");
		errTNa.setVisible(false);
		errTNa.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTNa.setBounds(390, 88, 27, 28);
		panel_Tuteur.add(errTNa);
		
		errTPren = new JLabel("");
		errTPren.setVisible(false);
		errTPren.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTPren.setBounds(390, 61, 27, 28);
		panel_Tuteur.add(errTPren);
		
		errTTelM = new JLabel("");
		errTTelM.setVisible(false);
		errTTelM.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTTelM.setBounds(390, 202, 27, 28);
		panel_Tuteur.add(errTTelM);
		
		errTVille = new JLabel("");
		errTVille.setVisible(false);
		errTVille.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTVille.setBounds(390, 175, 27, 28);
		panel_Tuteur.add(errTVille);
		
		errAdr = new JLabel("");
		errAdr.setVisible(false);
		errAdr.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errAdr.setBounds(390, 148, 27, 28);
		panel_Tuteur.add(errAdr);
		
		errTDateN = new JLabel("");
		errTDateN.setVisible(false);
		errTDateN.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTDateN.setBounds(390, 115, 27, 28);
		panel_Tuteur.add(errTDateN);
		
		errTPro = new JLabel("");
		errTPro.setVisible(false);
		errTPro.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTPro.setBounds(390, 278, 27, 28);
		panel_Tuteur.add(errTPro);
		
		errTMail = new JLabel("");
		errTMail.setVisible(false);
		errTMail.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTMail.setBounds(390, 251, 27, 28);
		panel_Tuteur.add(errTMail);
		
		errTTelDom = new JLabel("");
		errTTelDom.setVisible(false);
		errTTelDom.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/error.png")));
		errTTelDom.setBounds(390, 224, 27, 28);
		panel_Tuteur.add(errTTelDom);
		
		txtdtnaissance_Tuteur = new JDateChooser();
		((JTextField)txtdtnaissance_Tuteur.getDateEditor().getUiComponent()).setEditable(false);
		txtdtnaissance_Tuteur.setDateFormatString("dd/MM/yyyy");
		txtdtnaissance_Tuteur.setBounds(164, 115, 220, 28);
		panel_Tuteur.add(txtdtnaissance_Tuteur);
		

		DefaultTableModel modelTable_Inscription = new DefaultTableModel();
		modelTable_Inscription.addColumn("Date_Inscription");
		modelTable_Inscription.addColumn("Classe");
		modelTable_Inscription.addRow(new Object[]{"12-10-2017","1 annee"});
		
		panel_Absence = new JPanel();
		tabbedPane_Fiche_Etudiant.addTab("Les Absences", null, panel_Absence, null);
		panel_Absence.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Historique des absences", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(22, 32, 678, 298);
		panel_Absence.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 40, 658, 247);
		panel_1.add(scrollPane_2);
		
		table_Absence = new JTable();
		table_Absence.setRowHeight(30);
		table_Absence.setSelectionBackground(new Color(128, 128, 128));
		table_Absence.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		table_Absence.getTableHeader().setReorderingAllowed(false);
		table_Absence.getTableHeader().setResizingAllowed(false);
		table_Absence.setDefaultEditor(Object.class,null);
		
		
		scrollPane_2.setViewportView(table_Absence);
		
		panel_Opreation = new JPanel();
		panel_Opreation.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Opreation.setBounds(272, 458, 245, 75);
		add(panel_Opreation);
		
		panel_Opreation_Valider =new JPanel();
		panel_Opreation_Valider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Validation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Opreation_Valider.setBounds(272, 458, 245, 73);
		//add(panel_Opreation_Valider);
		panel_Opreation_Valider.setLayout(null);
		
		/*--------------------------------------------------------------------------------------------*/
		/*------------------------------  Modifier  un   eleve  ---------------------------------------*/
		/*--------------------------------------------------------------------------------------------*/
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				Eleve 	eleve  = new Eleve();
				Tuteur  tuteur = new Tuteur();
				
				if(validation_Tuteur() && validation_Eleve()){
					
					if(JOptionPane.showConfirmDialog(null,"Voulez vous Modifier cet Elève ?")==JOptionPane.OK_OPTION){
						eleve  =  genererEleve();
						tuteur =  genererTuteur();
						if(tuteurDAO.update(tuteur) && eleveDAO.update(eleve)){
							
							JOptionPane.showMessageDialog(null, "Modifier avec succes");
							
							remove(panel_Opreation_Valider);
							add(panel_Opreation);
							repaint();
							Activer_Champs(false);
							effacer_Champs();
							chargerTable_eleve();
						}
					}
					
				}
				
				
				  
			}
		});
		label.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/Ok-icon.png")));
		label.setBounds(10, 11, 56, 51);
		panel_Opreation_Valider.add(label);
		
		label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(panel_Opreation_Valider);
				  repaint();
				add(panel_Opreation);
				//Activer_Champs(false);
				effacer_Champs();
				table_Etudiant.clearSelection();
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});
		label_10.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/Button-Close-icon.png")));
		label_10.setBounds(76, 11, 67, 51);
		panel_Opreation_Valider.add(label_10);
		
		label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setHorizontalTextPosition(SwingConstants.CENTER);
		label_11.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/clear.png")));
		label_11.setBounds(153, 11, 77, 51);
		panel_Opreation_Valider.add(label_11);
		
		lblValider = new JLabel("Valider");
		lblValider.setHorizontalAlignment(SwingConstants.CENTER);
		lblValider.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblValider.setBounds(0, 48, 77, 14);
		panel_Opreation_Valider.add(lblValider);
		
		lblAnnuler = new JLabel("Annuler");
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAnnuler.setBounds(70, 48, 77, 14);
		panel_Opreation_Valider.add(lblAnnuler);
		
		lblNettoyer = new JLabel("Nettoyer");
		lblNettoyer.setHorizontalAlignment(SwingConstants.CENTER);
		lblNettoyer.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblNettoyer.setBounds(153, 48, 77, 14);
		panel_Opreation_Valider.add(lblNettoyer);
		
		/*--------------------------------------------------------------------------------------------*/
		/*------------------------------ Ajouter un eleve  ---------------------------------------*/
		/*--------------------------------------------------------------------------------------------*/
		
		lblAjouter = new JLabel("");
		lblAjouter.setBounds(6, 14, 61, 53);
		lblAjouter.setHorizontalTextPosition(SwingConstants.CENTER);

		
		lblAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Permet d'ajouter un etudiant seulement lorsque qu'on se trouve dans le panel l'ajouter
				//if(tabbedPane_Fiche_Etudiant.getSelectedIndex()==1){
				int a;
				a=JOptionPane.showConfirmDialog(null,"Voulez vous Ajouter un eleve?");
				if(a==JOptionPane.OK_OPTION){
					table_Etudiant.clearSelection();	
					//effacer_Champs();
				//Activer_Champs(true);
				/*if(table_Etudiant.getSelectedRow()!=-1){
					JOptionPane.showMessageDialog(null,"Désoler cet Eleve existe!");
					table_Etudiant.clearSelection();
					effacer_Champs();
					
					
				}else{*/
					/*Eleve e = new Eleve();
					e=genererEleve();
					Tuteur t =new Tuteur();
					t=genererTuteur();
					if(t.getCinT().equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez Remplir CIN du Tuteur");
					}
					else{
						System.out.println(e);
						System.out.println(t);
					TuteurDAO ta = new TuteurDAO(new Connexion().getConnection());
					ta.create(t);
					
					EleveDAO ea = new EleveDAO (new Connexion().getConnection());
				    ea.create(e);
				   // chargerComboBoxEleve2(obtenirClasseSelection());
					JOptionPane.showMessageDialog(null, "Ajouter avec succes");
//					 Activer_Champs(false);
					 effacer_Champs();
					chargerTable_eleve();*/
				FrameAjouterEleve f= new FrameAjouterEleve((PanelGestionEleve)panel_Opreation.getParent());
				//f.Activer_Champs(true);
				f.setVisible(true);
				chargerTable_eleve();
				//}
					//}
					
				/*}*/
					
			}
				}
		});
		panel_Opreation.setLayout(null);
		lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/plus.png")));
		panel_Opreation.add(lblAjouter);
		
		label_1 = new JLabel("");
		label_1.setBounds(157, 15, 71, 49);
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				  int a=JOptionPane.showConfirmDialog(null,"Voulez vous supprimer cet Elève?");  
				  if(a==JOptionPane.YES_OPTION){  
					  eleveDAO.delete(obtenir_Eleve_Selection());
					  JOptionPane.showMessageDialog(null,"Supprimer avec succes");
					  chargerTable_eleve();
					  effacer_Champs();
				  }  
				 
					
			}
		});
		label_1.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/Button-Close-icon.png")));
		panel_Opreation.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setBounds(89, 19, 62, 45);
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				

				if(table_Etudiant.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null,"Selectionnée l'eleve pour la modifier");
					
				}else{
					Activer_Champs(true);
					remove(panel_Opreation);
					repaint();
					add(panel_Opreation_Valider);
					
				}	
			}
		});
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setIcon(new ImageIcon(PanelGestionEleve.class.getResource("/image/edit.png")));
		panel_Opreation.add(label_2);
		
		lblAjouter_1 = new JLabel("Ajouter");
		lblAjouter_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAjouter_1.setBounds(8, 52, 55, 15);
		panel_Opreation.add(lblAjouter_1);
		
		JLabel lblModifier = new JLabel("Modifier");
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblModifier.setBounds(77, 52, 74, 15);
		panel_Opreation.add(lblModifier);
		
		JLabel lblSupprimer = new JLabel("Supprimer");
		lblSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupprimer.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblSupprimer.setBounds(157, 52, 81, 15);
		panel_Opreation.add(lblSupprimer);
		
		panel_Impression = new JPanel();
		panel_Impression.setBorder(new TitledBorder(null, "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Impression.setBounds(527, 458, 574, 73);
		add(panel_Impression);
		panel_Impression.setLayout(null);
		
		btnNewButton_1 = new JButton("<html><center>Attestation <br> d'inscription</center></html>");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(10, 17, 134, 45);
		panel_Impression.add(btnNewButton_1);
		
		btnAttestationnDe = new JButton("<html><center>Attestation <br> de Reussite</center></html>");
		btnAttestationnDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAttestationnDe.setBounds(154, 17, 111, 45);
		panel_Impression.add(btnAttestationnDe);
		
		btnAttestationDeScolait = new JButton("<html><center>Attestation <br> de Scolait\u00E9</center></html>");
		btnAttestationDeScolait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table_Etudiant.getSelectedRow()!=-1){
					String fichier="Rapport\\Certificatt_Scolarite.jrxml";
					print_Eleve(fichier);
				}else{
					JOptionPane.showMessageDialog(null, "Aucun Eleve selectionné sur la liste !");
				}
			}
		});
		btnAttestationDeScolait.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAttestationDeScolait.setBounds(275, 17, 134, 45);
		panel_Impression.add(btnAttestationDeScolait);
		
		btnHisstoirqueDesAbsences = new JButton("<html><center>Histoirque des<br >  absences</center> </html>");
		btnHisstoirqueDesAbsences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table_Etudiant.getSelectedRow()!=-1){
					String fichier="Rapport\\Liste_Des_Absence_Eleve_Un.jrxml";
					print_Eleve(fichier);
				}else{
					JOptionPane.showMessageDialog(null, "Aucun Eleve selectionné sur la liste !");
				}
			}
		});
		btnHisstoirqueDesAbsences.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnHisstoirqueDesAbsences.setBounds(415, 17, 134, 45);
		panel_Impression.add(btnHisstoirqueDesAbsences);
		 
	}
	// cette methode crée un éleve et le retoune à partir des informations saisis dans le champs textes de l' éleve 

	public 	Eleve genererEleve(){
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
			String sex="";
			Long cinT=Long.parseLong(textField_CIN_Tuteur.getText());
			String photos_Eleve=txtpath.getText();
			if(rdbtnM.isSelected()){
				sex= "masculin" ;
			}
			else if(rdbtnF.isSelected()){
				 sex="feminin";}
			Eleve eleve=new Eleve(num,cinT,numclass,nom,prenom,nat,datenaiss,addresse,v,sex,photos_Eleve);
			return eleve;
		}
		// cette methode crée un tuteur et le retoune à partir des informations saisis dans le champs textes du tuteur 
	public	Tuteur genererTuteur(){
		
			Long cin=Long.parseLong(textField_CIN_Tuteur.getText());
			String nom=textField_Nom_Tuteur.getText();
			String prenom=textField_Prenom_Tuteur.getText();
			String adr=textField_Adresse_Tuteur.getText();
			String nat=textField_Nationalité_Tuteur.getText();
			String datenaiss=((JTextField)txtdtnaissance_Tuteur.getDateEditor().getUiComponent()).getText();
			String sex="";
			if(rdbtnMTut.isSelected()){
				 sex= new String("masculin") ;
				}
				else if(rdbtnFTut.isSelected()){
					 sex="feminin";}
            String prof=textField_Profession_Tuteur.getText();
            Long telM = null;
            if(!textField_TelMobile_Tuteur.getText().equals(""))
            	 telM =Long.parseLong(textField_TelMobile_Tuteur.getText());
            else
            	telM=(long) 0;
        
             Long telfix = null;
             if(!textField_TelFixe_Tuteur.getText().equals(""))
            	 telfix=Long.parseLong(textField_TelFixe_Tuteur.getText());
             else
            	 telfix=(long) 0;
            
            String villeT=textField_Ville_Tuteur.getText();
            String mail=textField_Mail_Tuteur.getText();
			
			Tuteur tut=new Tuteur(cin,nom,prenom,datenaiss,sex,nat,prof,adr,villeT,telM,telfix,mail);
			return tut;
			
		}
	
	/*------------------ la methode pour charger la table  etudiant -------------*/
	public void chargerTable_eleve(){
		
			DefaultTableModel  modelEleve= new DefaultTableModel();
			modelEleve.addColumn("ID");
			modelEleve.addColumn("NOM");
			modelEleve.addColumn("PRENOM");
			modelEleve.addColumn("DATE DE NAISSANCE");
			modelEleve.addColumn("NATIONALITE");
			modelEleve.addColumn("ADRESSE");
			modelEleve.addColumn("VILLE");
			modelEleve.addColumn("SEXE");
			modelEleve.addColumn("NUM_CLASSE");
			modelEleve.addColumn("CIN");
			modelEleve.addColumn("photos");
			Vector<Eleve> po = new Vector<Eleve>();
			po=eleveDAO.findAll();
			
			for (Eleve cl : po) {
				modelEleve.addRow(new Object[]{cl.getNum_ins(),cl.getNom(),cl.getPrenom(),cl.getDate_naiss(),
						cl.getNationnalite(),cl.getAddresse(),cl.getVille(),cl.getSexe(),cl.getNum_classe(),cl.getCin(),cl.getPhotos_Eleve()});
				
			}
			
			table_Etudiant.setModel(modelEleve);
			//Cette partie permet de cacher les autres informations c est à dire autres que l'id;le nom ;le prenom dans le tableau
 			for (int i = 3; i < 11; i++) {
				table_Etudiant.getColumnModel().getColumn(i).setMinWidth(0);
				table_Etudiant.getColumnModel().getColumn(i).setMaxWidth(0);
				table_Etudiant.getColumnModel().getColumn(i).setWidth(0);
				
			}
			
		}
	
	

	/*------------------ la methode pour charger la table  etudiant rechercher -------------*/
	public void chargerTable_eleve_Rechercher(){
		
			DefaultTableModel  modelEleve= new DefaultTableModel();
			modelEleve.addColumn("ID");
			modelEleve.addColumn("NOM");
			modelEleve.addColumn("PRENOM");
			modelEleve.addColumn("DATE DE NAISSANCE");
			modelEleve.addColumn("NATIONALITE");
			modelEleve.addColumn("ADRESSE");
			modelEleve.addColumn("VILLE");
			modelEleve.addColumn("SEXE");
			modelEleve.addColumn("NUM_CLASSE");
			modelEleve.addColumn("CIN");
			modelEleve.addColumn("photos");
			Vector<Eleve> po = new Vector<Eleve>();
			po=eleveDAO.findByNom(textField_Rechercher_Nom.getText());
			
			if(po==null){
				JOptionPane.showMessageDialog(null, "Aucun résultat !");
				chargerTable_eleve();
			}else{
				for (Eleve cl : po) {
					modelEleve.addRow(new Object[]{cl.getNum_ins(),cl.getNom(),cl.getPrenom(),cl.getDate_naiss(),
							cl.getNationnalite(),cl.getAddresse(),cl.getVille(),cl.getSexe(),cl.getNum_classe(),cl.getCin()});
					
				}
			}
			
			
			table_Etudiant.setModel(modelEleve);
			//Cette partie permet de cacher les autres informations c est à dire autres que l'id;le nom ;le prenom dans le tableau
 			for (int i = 3; i < 11; i++) {
				table_Etudiant.getColumnModel().getColumn(i).setMinWidth(0);
				table_Etudiant.getColumnModel().getColumn(i).setMaxWidth(0);
				table_Etudiant.getColumnModel().getColumn(i).setWidth(0);
				
			}
			
		} 
	
	
	
	/*------------------ Pour charger eleve selection dans les champs --------*/
	
	public void chargerEleve_Tuteur_Dans_Champ(Eleve e){
		 //Activer_Champs(false);
		Tuteur t= tuteurDAO.find(e.getCin());
		textField_Inscription_Etudiant.setText(e.getNum_ins().toString());
		Classe ec=new ClasseDAO().find(e.getNum_classe());
		try {
			comboBoxNumClasse.setSelectedItem(ec.getNom_Clase());
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		textField_Nom_Etudiant.setText(e.getNom());
		textField_Prenom_Etudiant.setText(e.getPrenom());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date_Naissance=null;
				
		try {
			date_Naissance = dateFormat.parse(e.getDate_naiss());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtdtnaissance.setDate(date_Naissance);
		textField_Nationnalite_Etudiant.setText(e.getNationnalite());
		textField_Addresse_Etudiant.setText(e.getAddresse());
		textField_Ville_Etudiant.setText(e.getVille());
		txtpath.setText(e.getPhotos_Eleve());
		path=e.getPhotos_Eleve();
		image.setIcon(new ImageIcon(e.getPhotos_Eleve()));
		if(e.getSexe().equals("masculin"))
			rdbtnM.setSelected(true);
		else
			rdbtnF.setSelected(true);
		
		// Chargement information pour tuteur
		
		textField_CIN_Tuteur.setText(t.getCinT().toString());
		textField_Nom_Tuteur.setText(t.getNomT());
		textField_Prenom_Tuteur.setText(t.getPrenomT());
		textField_Nationalité_Tuteur.setText(t.getNationnaliteT());
		textField_Adresse_Tuteur.setText(t.getAddresseT());
		textField_Ville_Tuteur.setText(t.getVilleT());
		textField_TelMobile_Tuteur.setText(t.getTelMob().toString());
		textField_TelFixe_Tuteur.setText(t.getTelfixe().toString());
		textField_Mail_Tuteur.setText(t.getEmail());
		textField_Profession_Tuteur.setText(t.getProfT());
		if(t.getSexeT().equals("masculin"))
			rdbtnMTut.setSelected(true);
		else
			rdbtnFTut.setSelected(true);
		try {
			date_Naissance = dateFormat.parse(t.getDate_naissT());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtdtnaissance_Tuteur.setDate(date_Naissance);
		
		charger_Table_Absence(e.getCin());
	
		
		
	}
	
	/*Retourne la classe Selectionné depuis le jtable */
	public Eleve obtenir_Eleve_Selection(){
		Eleve c=new Eleve();
		c.setNum_ins((Long)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 0));
		c.setNom((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 1));
		c.setPrenom((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 2));
		c.setDate_naiss((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 3));
		c.setNationnalite((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 4));
		c.setAddresse((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(),5));
		c.setVille((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 6));
		c.setSexe((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 7));
		c.setNum_classe((Long)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 8));
		c.setCin((Long)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 9));
		c.setPhotos_Eleve((String)table_Etudiant.getValueAt(table_Etudiant.getSelectedRow(), 10));
		return c;
	}
	
	//   ACtiver les chachamps text
	public void Activer_Champs(boolean b){
			
		 /*-------------- Champs pour l'eleve Eleve------------*/
		
		 textField_Nom_Etudiant.setEditable(b);
		 textField_Prenom_Etudiant.setEditable(b);
		 textField_Ville_Etudiant.setEditable(b);
		 textField_Addresse_Etudiant.setEditable(b);
		 textField_Nationnalite_Etudiant.setEditable(b);
		 textField_DateIns__Etudiant.setEditable(b);
		 txtdtnaissance.setEnabled(b);
		 rdbtnF.setEnabled(b);
		 rdbtnM.setEnabled(b);
		 
		 /*-------------- Champs pour l'eleve Tuteur------------*/
		 textField_Nom_Tuteur.setEditable(b);
		 textField_Prenom_Tuteur.setEditable(b);
		 textField_Adresse_Tuteur.setEditable(b);
		 textField_Ville_Tuteur.setEditable(b);
		 textField_Nationalité_Tuteur.setEditable(b);
		
		 textField_TelMobile_Tuteur.setEditable(b);
		 textField_TelFixe_Tuteur.setEditable(b);
		 textField_Mail_Tuteur.setEditable(b);
		 textField_Profession_Tuteur.setEditable(b);
		 txtdtnaissance_Tuteur.setEnabled(b);
		 rdbtnFTut.setEnabled(b);
		 rdbtnMTut.setEnabled(b);
	}
	 
	// cette  methode permet d'effacer Tous les champs de l'eleve
	
	public void effacer_Champs(){
		 textField_Inscription_Etudiant.setText("");
		 comboBoxNumClasse.setSelectedItem(new String(""));;
		 textField_Nom_Etudiant.setText("");
		 //textField_DateNaiss_Etudiant.setText("");
		 textField_Prenom_Etudiant.setText("");
	     textField_Ville_Etudiant.setText("");
		 textField_Addresse_Etudiant.setText("");
		 textField_Nationnalite_Etudiant.setText("");
		 textField_DateIns__Etudiant.setText("");
		 textField_Nom_Tuteur.setText("");
		 textField_Prenom_Tuteur.setText("");
		 txtdtnaissance_Tuteur.setDate(null);
	     textField_Adresse_Tuteur.setText("");
		 textField_Ville_Tuteur.setText("");
		 textField_Nationalité_Tuteur.setText("");
	     textField_CIN_Tuteur.setText("");
		 textField_TelMobile_Tuteur.setText("");
	     textField_TelFixe_Tuteur.setText("");
		 textField_Mail_Tuteur.setText("");
	     textField_Profession_Tuteur.setText("");
	     rdbtnF.setSelected(false);
		 rdbtnM.setSelected(false);
		 rdbtnFTut.setSelected(false);
		 rdbtnMTut.setSelected(false);
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
		// Validation  date naissance
		try {
			Date actuelle = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            String date = dateFormat.format(actuelle);
            String dc = date;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
            String d = sdf.format(txtdtnaissance_Tuteur.getDate());

            int d1 = Integer.parseInt(dc);
            int d2 = Integer.parseInt(d);

            int r = (d1 - d2);
            if(!(r>=20 && r <100)){
            	bol=false;
            	errTDateN.setVisible(true);
            	errTDateN.setToolTipText("Age 20 -->> 100 !");
            }
            	
            	
            
            
	} catch (Exception e) {
		
	}
		return bol;
	}
	
	/* Validation  Eleve */
	public boolean validation_Eleve(){
		boolean bol=true;
		String regNom= "^[a-zA-Z\\s]+";
		
		
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
			Long age=Long.parseLong(ageEleve.getText());
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
	
	// Cette methode permet de charger les Absence dans la Table
	
	public void charger_Table_Absence(Long id){
		
		DefaultTableModel  modelEleve= new DefaultTableModel();
		modelEleve.addColumn("Numero Inscription");
		modelEleve.addColumn("Date d'Absence");
		modelEleve.addColumn("Motif");
		
		Vector<Absence_Eleve> collection_Absence=absence_EleveDAO.Absence_Eleve_find_By_ID(id);
		
		for (Absence_Eleve cl : collection_Absence) {
			modelEleve.addRow(new Object[]{cl.getNum_Inscription(),cl.getDate_Absence(),cl.getMotif(),});		
		}
		
		table_Absence.setModel(modelEleve);
		for(int i=0;i<table_Absence.getColumnCount();i++){
				table_Absence.getColumnModel().getColumn(i).setCellRenderer(centerRender);
				table_Absence.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}
		table_Absence.getTableHeader().setDefaultRenderer(centerRender);
		
		
		
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
	 /*----------------------- Cette method permet de faire Impression ----------------------------*/
		public void print_Eleve(String fichier){

			org.apache.log4j.BasicConfigurator.configure();
			Map param = new HashMap();
			Integer  Id_eleve=Integer.parseInt(obtenir_Eleve_Selection().getNum_ins().toString());
			param.put("id_Eleve", Id_eleve);
			try{
				 
				JasperReport report = JasperCompileManager.compileReport(fichier);
				JasperPrint print = JasperFillManager.fillReport(report, param, new Connexion().getConnection());
				JasperViewer.viewReport(print,false);
		        
		        }
			 catch(Exception e)
		    {
		           JOptionPane.showMessageDialog(null, e);

		    }

		}
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		 frame.setBounds(100, 100, 1500, 726);
	//	frame.setSize(1000,1000);
		frame.getContentPane().add(new PanelGestionEleve(), BorderLayout.CENTER );
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		//frame.getContentPane().add(new PanelGestionEleve());
		

	}
}
