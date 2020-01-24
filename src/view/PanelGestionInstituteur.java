package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import classeMetier.Inst_Mat_Affectation;
import classeMetier.Instituteur;
import classeMetier.Matiere;
import connxion_Requete.Connexion;
import dao.implement.Inst_Mat_Affectation_DAO;
import dao.implement.InstituteurDAO;
import dao.implement.MatiereDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

public class PanelGestionInstituteur extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table_Instituteurs;
	private JTextField textField_Rechercher_Nom;
	private JPanel panel_Liste_Instituteurs;
	private JPanel panel_Opreation;
	private JPanel panel_Opreation_Valider;
	private JPanel panel_Impression;
	private JPanel panel_Affectation;
	private JButton btnNewButton_1;
	private JButton btnAttestationnDe;
	private JPanel panel_Fiche_Instituteurs;
	private JScrollPane scrollPane;
	private JLabel lblAdresse;
	private JLabel lblNumTel;
	private JLabel lblSexe;
	private JLabel lblDateDinscription;
	private JLabel lblAjouter;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField_ID_Instituteurs;
	private JTextField textField_Nom_Instituteurs;
	private JTextField textField_Prenom_Instituteurs;
	private JTextField textField_NumTel_Instituteurs;
	private JTextField textField_Addresse_Instituteurs;
	private JTextField textField_Nationnalite_Instituteurs;
	private JTextField textField_Email__Instituteurs;
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup = new ButtonGroup();
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private DefaultTableModel  modelTableMatiereEnseigne;
	

	//private JTextField textField_Date_Naiss_Tuteur;
	private JLabel label;
	private JLabel label_10;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JLabel lblNewLabel;
	private JPanel panelDroite;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JDateChooser txtdtnaissance;
	private JLabel label_3;
	private JLabel label_4;
	public PanelGestionInstituteur() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		panel_Liste_Instituteurs = new JPanel();
		panel_Affectation = new JPanel();
		
		/*apres le chargements du panel Affectation , on recupere l'id du instituteurs selectionne
		 * dans la liste , puis on fait une recherche des matieres enseigné par cet inst
		 * apres on les affiche dans matieres enseigné
		 * @@@@ pour l'affectation , il faut que jtable.selectedIndex <> -1 , puis on recupere l'id
		 * de l'instituteur , apres on ajoute la matiere pour cet instituer et on reaffiche les matiere
		 * enseigné par cet instituteur dans le jtable du droite
		 * */
		panel_Liste_Instituteurs.setBorder(new TitledBorder(null, "Liste des Instituteurs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Liste_Instituteurs.setBounds(10, 43, 252, 476);
		add(panel_Liste_Instituteurs);
		panel_Liste_Instituteurs.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 235, 397);
		panel_Liste_Instituteurs.add(scrollPane);
		
		
		
		
		table_Instituteurs = new JTable();
		table_Instituteurs.setFont(new Font("Dialog", Font.PLAIN, 14));
		table_Instituteurs.setSelectionBackground(new Color(128, 128, 128));
		table_Instituteurs.setRowHeight(30);
		// cette permet de charger Instituteur selectionné dans les champs
		table_Instituteurs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				chargerInstituteur(obtenir_Instituteur_Selection());
				add(panel_Opreation);
				try {
					remove(panel_Opreation_Valider);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		});
		/*Single line selection , column not modifiable*/
		table_Instituteurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*Jtable not draggble , not resizble*/
		table_Instituteurs.getTableHeader().setReorderingAllowed(false);
		table_Instituteurs.getTableHeader().setResizingAllowed(false);
		table_Instituteurs.setDefaultEditor(Object.class,null);
		scrollPane.setViewportView(table_Instituteurs);
		chargerTable_Instituteur();
		
		textField_Rechercher_Nom = new JTextField();
		textField_Rechercher_Nom.setBounds(10, 28, 195, 29);
		panel_Liste_Instituteurs.add(textField_Rechercher_Nom);
		textField_Rechercher_Nom.setColumns(10);
		
		JLabel label_Recherche_Etudiant = new JLabel("");
		label_Recherche_Etudiant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chargerTable_Inst_Rechercher();
				if(textField_Rechercher_Nom.getText().equals(""))
					chargerTable_Instituteur();
			}
		});
		label_Recherche_Etudiant.setIcon(new ImageIcon(PanelGestionInstituteur.class.getResource("/image/search.png")));
		label_Recherche_Etudiant.setBounds(208, 28, 34, 29);
		panel_Liste_Instituteurs.add(label_Recherche_Etudiant);
		
		
		panel_Fiche_Instituteurs = new JPanel();
		panel_Fiche_Instituteurs.setBorder(new TitledBorder(null, "Fiche Instituteur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Fiche_Instituteurs.setBounds(272, 43, 829, 416);
		add(panel_Fiche_Instituteurs);
		panel_Fiche_Instituteurs.setLayout(null);
		
		JTabbedPane tabbedPane_Fiche_Instituteur = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_Fiche_Instituteur.setBounds(10, 24, 809, 381);
		panel_Fiche_Instituteurs.add(tabbedPane_Fiche_Instituteur);
		
		
		JPanel panel_Etudiant = new JPanel();
		tabbedPane_Fiche_Instituteur.addTab("Instituteur", null, panel_Etudiant, null);
		panel_Etudiant.setLayout(null);
		
		tabbedPane_Fiche_Instituteur.addTab("Affectation Matiere", null, panel_Affectation, null);
		panel_Affectation.setLayout(null);
		
		JPanel panelGauche = new JPanel();
		panelGauche.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Affectation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGauche.setBounds(26, 26, 436, 273);
		panel_Affectation.add(panelGauche);
		panelGauche.setLayout(null);
		
		JLabel lblChoisirMAtiere = new JLabel("Veuillez choisir la mati\u00E8re \u00E0 affecter\r\n\u00E0 l'instituteur ");
		lblChoisirMAtiere.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblChoisirMAtiere.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisirMAtiere.setBounds(10, 53, 416, 33);
		panelGauche.add(lblChoisirMAtiere);
		/*Si on est le tab Affectation donc le panel operation n'est plus fonctionnel*/
		tabbedPane_Fiche_Instituteur.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane_Fiche_Instituteur.getSelectedIndex()==1){
					panel_Opreation.setEnabled(false);
					lblAjouter.setEnabled(false);
					label_1.setEnabled(false);
					label_2.setEnabled(false);
					
				}
				else
				{
					panel_Opreation.setEnabled(true);
					lblAjouter.setEnabled(true);
					label_1.setEnabled(true);
					label_2.setEnabled(true);
					
				}
				
			}
		});
		
		lblNewLabel = new JLabel("selectionn\u00E9 :");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(57, 99, 94, 14);
		panelGauche.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(57, 124, 243, 30);
		/* On charge ce comboBox par les noms de toutes les matieres 
		 * on utilise la fonction findALL() du MatiereDAO , ça nous retourne un vector de
		 * matiere , on le parcours et mon seulement le nom du matiere dans les combobox
		 * */
		panelGauche.add(comboBox);
		
		Vector<Matiere> listeMat = new MatiereDAO().findAll();
		
		for(int ir=0;ir<listeMat.size();ir++){
			comboBox.addItem(listeMat.elementAt(ir).getNom());
			System.out.println(listeMat.elementAt(ir).getNom());
		}
		
		
		
		
		
		
		
		
		/*   Charger Dans ce combobox toutes les matiere , on utilise le DAOMatiere fait par dhie et
		 *   on charge le vector retourné par la methode findALL dans ce combobox
		 *    
		 *   
		 *   */
		
		JLabel lblImageValifAff = new JLabel("");
		lblImageValifAff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(table_Instituteurs.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(getParent(), "Tu dois selectionner l'instituteur d'abord.");
				}
				
				else{
					
					
					Long idInst = (Long) table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(),0);
					System.out.println(idInst);
					Matiere m=new Matiere();
					
					/*Find matiere par nom : depuis le jCOmbobox */
					String requet="SELECT * from MATIERE where NOM_MATIERE=? ";
					try
					   {
						PreparedStatement stmt = new Connexion().getConnection().prepareStatement(requet);
						stmt.setString(1,(String)comboBox.getSelectedItem());
						ResultSet rs = stmt.executeQuery();
						
						
						
						if(rs.next())
						{
							
							m.setId_matiere(rs.getLong("ID_MATIERE"));
							m.setNom(rs.getString("NOM_MATIERE"));
							m.setId_niveau(rs.getLong("ID_NIVEAU"));
							
						}
						
					   }
					
					catch(Exception e)
					   {
						System.out.println("erreur de connexion");
						
					   }	
					
					/* ça se termine içi , faut deplacer ça apres dans DAOmatiere 
					 * fn quis'appelle , get Matiere by name
					 * 
					 * */
					Inst_Mat_Affectation k = new Inst_Mat_Affectation(idInst,m.getId_matiere());
					
					new Inst_Mat_Affectation_DAO().create(k);
					remplirListeMatPourInst();
					
				}
				
				
			}
		});
		lblImageValifAff.setIcon(new ImageIcon(PanelGestionInstituteur.class.getResource("/image/Ok-icon.png")));
		lblImageValifAff.setBounds(150, 165, 35, 63);
		panelGauche.add(lblImageValifAff);
		
		panelDroite = new JPanel();
		panelDroite.setBorder(new TitledBorder(null, "Liste Mati\u00E9res enseign\u00E9es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDroite.setBounds(472, 26, 268, 263);
		panel_Affectation.add(panelDroite);
		panelDroite.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 22, 224, 230);
		panelDroite.add(scrollPane_1);
		
		table = new JTable();
		table.setRowHeight(30);
		scrollPane_1.setViewportView(table);
		
		
		
		modelTableMatiereEnseigne= new DefaultTableModel();
		modelTableMatiereEnseigne.addColumn("ID MATIERE");
		modelTableMatiereEnseigne.addColumn("MATIERE");
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*Jtable not draggble , not resizble*/
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setDefaultEditor(Object.class,null);
		/*On ne peut rien selectionné ici , seulement affiché*/
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		
		
		table.setModel(modelTableMatiereEnseigne);
		
		/* Dans ce JTABLE on va charger les matieres enseigné par l'instituteur selectionné depuis la liste
		 * des instituteurs qui se trouve à gauche
		 * donc on aura une table qui Matiere_institu : idEnseigne , idMAtiere
		 * , on recupere le id de l'instituteur slectionné puis on recherche dans la table 
		 * Matiere_institu et puis on fait la jointure avec la table matiere pour recuperer le nom du matiere
		 * et on les affiche dans le JTable 
		 *  ? on affiche quoi pour les matieres enseigné ? seulement une seule colonne qui contient les
		 *  noms des matieres ?
		 * */
		
		table_Instituteurs.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				remplirListeMatPourInst();
			}
		});
		
		
		
		JLabel lbl_ID_Instituteurs = new JLabel("<html>Numéro Cin  : <font color= 'red'> * </font></html>");
		lbl_ID_Instituteurs.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lbl_ID_Instituteurs.setBounds(10, 12, 164, 24);
		panel_Etudiant.add(lbl_ID_Instituteurs);
		
		JLabel lblNom = new JLabel("<html>Nom : <font color= 'red'> * </font></html>");
		lblNom.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNom.setBounds(10, 40, 164, 28);
		panel_Etudiant.add(lblNom);
		
		JLabel lblPrenom = new JLabel("<html>Prénom : <font color= 'red'> * </font></html>");
		lblPrenom.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblPrenom.setBounds(10, 72, 164, 24);
		panel_Etudiant.add(lblPrenom);
		
		JLabel lblDateDeNaissance = new JLabel("<html>Date de naissance : <font color= 'red'> * </font></html>");
		lblDateDeNaissance.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblDateDeNaissance.setBounds(10, 98, 164, 28);
		panel_Etudiant.add(lblDateDeNaissance);
		
		JLabel lblNationnalit = new JLabel("Nationnalit\u00E9");
		lblNationnalit.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNationnalit.setBounds(10, 126, 164, 28);
		panel_Etudiant.add(lblNationnalit);
		
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblAdresse.setBounds(10, 154, 164, 27);
		panel_Etudiant.add(lblAdresse);
		
		lblNumTel = new JLabel("<html>Num\u00E9ro Tel  : <font color= 'red'> * </font></html>");
		lblNumTel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNumTel.setBounds(10, 185, 164, 25);
		panel_Etudiant.add(lblNumTel);
		
		lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblSexe.setBounds(10, 253, 164, 23);
		panel_Etudiant.add(lblSexe);
		
		lblDateDinscription = new JLabel("E-mail ");
		lblDateDinscription.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblDateDinscription.setBounds(10, 214, 164, 28);
		panel_Etudiant.add(lblDateDinscription);
		
		textField_ID_Instituteurs = new JTextField();
		textField_ID_Instituteurs.setEditable(false);
		textField_ID_Instituteurs.setBounds(184, 12, 219, 28);
		panel_Etudiant.add(textField_ID_Instituteurs);
		textField_ID_Instituteurs.setColumns(10);
		
		textField_Nom_Instituteurs = new JTextField();
		textField_Nom_Instituteurs.setEditable(false);
		textField_Nom_Instituteurs.setColumns(10);
		textField_Nom_Instituteurs.setBounds(184, 40, 219, 28);
		panel_Etudiant.add(textField_Nom_Instituteurs);
		
		textField_Prenom_Instituteurs = new JTextField();
		textField_Prenom_Instituteurs.setEditable(false);
		textField_Prenom_Instituteurs.setColumns(10);
		textField_Prenom_Instituteurs.setBounds(184, 72, 219, 28);
		panel_Etudiant.add(textField_Prenom_Instituteurs);
		
		textField_NumTel_Instituteurs = new JTextField();
		textField_NumTel_Instituteurs.setEditable(false);
		textField_NumTel_Instituteurs.setColumns(10);
		textField_NumTel_Instituteurs.setBounds(184, 185, 219, 28);
		panel_Etudiant.add(textField_NumTel_Instituteurs);
		
		textField_Addresse_Instituteurs = new JTextField();
		textField_Addresse_Instituteurs.setEditable(false);
		textField_Addresse_Instituteurs.setColumns(10);
		textField_Addresse_Instituteurs.setBounds(184, 154, 219, 28);
		panel_Etudiant.add(textField_Addresse_Instituteurs);
		
		textField_Nationnalite_Instituteurs = new JTextField();
		textField_Nationnalite_Instituteurs.setEditable(false);
		textField_Nationnalite_Instituteurs.setColumns(10);
		textField_Nationnalite_Instituteurs.setBounds(184, 126, 219, 28);
		panel_Etudiant.add(textField_Nationnalite_Instituteurs);
		
		textField_Email__Instituteurs = new JTextField();
		textField_Email__Instituteurs.setEditable(false);
		textField_Email__Instituteurs.setColumns(10);
		textField_Email__Instituteurs.setBounds(184, 214, 219, 28);
		panel_Etudiant.add(textField_Email__Instituteurs);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.setSelected(true);
		rdbtnM.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		buttonGroup_2.add(rdbtnM);
		rdbtnM.setBounds(184, 253, 42, 23);
		panel_Etudiant.add(rdbtnM);
		
		
		 rdbtnF = new JRadioButton("F");
		 rdbtnF.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		 buttonGroup_2.add(rdbtnF);
		 rdbtnF.setBounds(228, 253, 42, 23);
		 panel_Etudiant.add(rdbtnF);
		 
		 txtdtnaissance = new JDateChooser();
		 txtdtnaissance.setDateFormatString("dd/MM/yyyy");
		 txtdtnaissance.setBounds(184, 98, 219, 28);
		 panel_Etudiant.add(txtdtnaissance);
		

		DefaultTableModel modelTable_Inscription = new DefaultTableModel();
		modelTable_Inscription.addColumn("Date_Inscription");
		modelTable_Inscription.addColumn("Classe");
		modelTable_Inscription.addRow(new Object[]{"12-10-2017","1 annee"});
		DefaultTableModel modelTable_Absence = new DefaultTableModel();
		modelTable_Absence.addColumn("Date_Absence");
		modelTable_Absence.addColumn("Heure_Debut");
		modelTable_Absence.addColumn("Heure_Fin");
		modelTable_Absence.addColumn("Motif");
		modelTable_Absence.addColumn("Type");

		modelTable_Absence.addRow(new Object[]{"12-10-2017","8h00","10h00","Consultation Medicale","Santé"});
		
		panel_Opreation = new JPanel();
		panel_Opreation.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Opreation.setBounds(272, 458, 238, 73);
		add(panel_Opreation);
		panel_Opreation.setLayout(null);
		
		panel_Opreation_Valider =new JPanel();
		panel_Opreation_Valider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Validation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Opreation_Valider.setBounds(282, 458, 214, 73);
		//add(panel_Opreation_Valider);
		panel_Opreation_Valider.setLayout(null);
		
		/*--------------------------------------------------------------------------------------------*/
		/*------------------------------  Modifier  un   Instituteur  ---------------------------------------*/
		/*--------------------------------------------------------------------------------------------*/
	
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				 Activer_Champs(true);
			int a=JOptionPane.showConfirmDialog(null,"Voulez vous Modifier cet Instituteur?");  
				  if(a==JOptionPane.OK_OPTION){
				Instituteur e = new Instituteur();
				e=genererInstituteur();
				
				
				new InstituteurDAO().update(e);
				chargerTable_Instituteur();
				JOptionPane.showMessageDialog(null, "Modifier avec succes");
				effacer_Champs();
				Activer_Champs(false);
				remove(panel_Opreation_Valider);
				add(panel_Opreation);
				repaint();
				}
				  else{
				  remove(panel_Opreation_Valider);
					add(panel_Opreation);
					repaint();
				  }
			}
		});
		
		label.setIcon(new ImageIcon(PanelGestionInstituteur.class.getResource("/image/Ok-icon.png")));
		label.setBounds(10, 11, 72, 51);
		panel_Opreation_Valider.add(label);
		
		label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setHorizontalTextPosition(SwingConstants.CENTER);
		label_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(panel_Opreation_Valider);
				add(panel_Opreation);
				repaint();
				effacer_Champs();
			}
		});
		label_10.setIcon(new ImageIcon(PanelGestionInstituteur.class.getResource("/image/Button-Close-icon.png")));
		label_10.setBounds(132, 11, 72, 51);
		panel_Opreation_Valider.add(label_10);
		
		label_3 = new JLabel("Valider");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_3.setBounds(10, 48, 82, 14);
		panel_Opreation_Valider.add(label_3);
		
		label_4 = new JLabel("Annuler");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_4.setBounds(126, 48, 88, 14);
		panel_Opreation_Valider.add(label_4);
		
		
		
		/*--------------------------------------------------------------------------------------------*/
		/*------------------------------ Ajouter un Instituteur  ---------------------------------------*/
		/*--------------------------------------------------------------------------------------------*/
		
		lblAjouter = new JLabel("");

		
		lblAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Permet d'ajouter un Instituteur seulement lorsque qu'on se trouve dans le panel l'ajouter
				//if(tabbedPane_Fiche_Etudiant.getSelectedIndex()==1){
				
				Activer_Champs(true);
				if(table_Instituteurs.getSelectedRow()!=-1){
					JOptionPane.showMessageDialog(null,"Désoler cet Instituteur existe!");
					table_Instituteurs.clearSelection();
					effacer_Champs();
					
					
				}else{
					Instituteur e = new Instituteur();
					e=genererInstituteur();
					if(e.getId().equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez Remplir ID de L'instituteur");
					}
					else{
						System.out.println(e);
					
					InstituteurDAO ea = new InstituteurDAO ();
				    ea.create(e);
					
					JOptionPane.showMessageDialog(null, "Ajouter avec succes");
//					 Activer_Champs(false);
					 effacer_Champs();
					chargerTable_Instituteur();
					
				//}
					}
					
				}
					
			}
		});
		lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter.setIcon(new ImageIcon(PanelGestionInstituteur.class.getResource("/image/plus.png")));
		lblAjouter.setBounds(20, 11, 47, 50);
		panel_Opreation.add(lblAjouter);
		
		label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				InstituteurDAO dao = new InstituteurDAO();
				  int a=JOptionPane.showConfirmDialog(null,"Voulez vous supprimer cet Instituteur?");  
				  if(a==JOptionPane.YES_OPTION){  
					  dao.delete(obtenir_Instituteur_Selection());
					  JOptionPane.showMessageDialog(null,"Supprimer avec succes");
					  chargerTable_Instituteur();
					  effacer_Champs();
				  }  
				 
					
			}
		});
		
		label_1.setIcon(new ImageIcon(PanelGestionInstituteur.class.getResource("/image/Button-Close-icon.png")));
		label_1.setBounds(157, 11, 71, 51);
		panel_Opreation.add(label_1);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Activer_Champs(true);

				if(table_Instituteurs.getSelectedRow()==-1){
					
				}else{
					remove(panel_Opreation);
					repaint();
					add(panel_Opreation_Valider);
					
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setIcon(new ImageIcon(PanelGestionInstituteur.class.getResource("/image/edit.png")));
		label_2.setBounds(77, 11, 70, 47);
		panel_Opreation.add(label_2);
		
		JLabel lblAjouter_1 = new JLabel("Ajouter");
		lblAjouter_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAjouter_1.setBounds(0, 48, 83, 14);
		panel_Opreation.add(lblAjouter_1);
		
		JLabel lblModifier = new JLabel("Modifier");
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblModifier.setBounds(77, 47, 83, 14);
		panel_Opreation.add(lblModifier);
		
		JLabel lblSupprimer = new JLabel("Supprimer");
		lblSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupprimer.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblSupprimer.setBounds(157, 44, 81, 14);
		panel_Opreation.add(lblSupprimer);
		
		panel_Impression = new JPanel();
		panel_Impression.setBorder(new TitledBorder(null, "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Impression.setBounds(520, 458, 568, 73);
		add(panel_Impression);
		panel_Impression.setLayout(null);
		
		btnNewButton_1 = new JButton("<html><center>Liste <br> d'instituteurs</center></html>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				org.apache.log4j.BasicConfigurator.configure();
				Connection con = new Connexion().getConnection();
				String myRep ="Rapport\\Instituteurs.jrxml";
			    JasperReport jasperReport;
			    
			    try{
			    	jasperReport = JasperCompileManager.compileReport(myRep);
			    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,con);
			    	JasperViewer.viewReport(jasperPrint,false);
			    }
				catch(Exception ex){
					
					JOptionPane.showMessageDialog(null, ex);
					
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(10, 17, 134, 45);
		panel_Impression.add(btnNewButton_1);
		
		btnAttestationnDe = new JButton("<html><center>Liste <br>inst&Mat </center></html>");
		btnAttestationnDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAttestationnDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAttestationnDe.setBounds(154, 17, 111, 45);
		panel_Impression.add(btnAttestationnDe);
		 Activer_Champs(true);
	}
	// cette methode crée un éleve et le retoune à partir des informations saisis dans le champs textes de l' éleve 

		Instituteur genererInstituteur(){
			
			Long num=Long.parseLong(textField_ID_Instituteurs.getText());
			String nom=textField_Nom_Instituteurs.getText();
			String prenom=textField_Prenom_Instituteurs.getText();
			String nat=textField_Nationnalite_Instituteurs.getText();
			//String datenaiss=textField_DateNaiss_Instituteurs.getText();
			String datenaiss=((JTextField)txtdtnaissance.getDateEditor().getUiComponent()).getText();
			String email=textField_Email__Instituteurs.getText();
			String addresse=textField_Addresse_Instituteurs.getText();
			Long numTel=Long.parseLong(textField_NumTel_Instituteurs.getText());
			String sex="";
			if(rdbtnM.isSelected())
				sex= "masculin" ;
			
			else if(rdbtnF.isSelected())
				 sex="feminin";
			Instituteur ins=new Instituteur(num,nom,prenom,datenaiss,numTel,email,addresse,nat,sex);
			return ins;
		}
		
		
	/*------------------ la methode pour charger la table  Instituteurs -------------*/
	public void chargerTable_Instituteur(){
		
			DefaultTableModel  modelInst= new DefaultTableModel();
			modelInst.addColumn("ID");
			modelInst.addColumn("NOM");
			modelInst.addColumn("PRENOM");
			modelInst.addColumn("DATE DE NAISSANCE");
			modelInst.addColumn("NUM_TEL");
			modelInst.addColumn("Email");
			modelInst.addColumn("ADRESSE");
			modelInst.addColumn("NATIONALITE");
			modelInst.addColumn("SEXE");
			
			
			Vector<Instituteur> po = new Vector<Instituteur>();
			po=new InstituteurDAO().findAll();System.out.println("Le nombre"+po.size());
			
			for (Instituteur cl : po) {
				modelInst.addRow(new Object[]{cl.getId(),cl.getNom(),cl.getPrenom(),cl.getDateN(),
						cl.getNumTel(),cl.getEmail(),cl.getAdresse(),cl.getNationalite(),cl.getSexe()});
				
			}
			
			table_Instituteurs.setModel(modelInst);
			//Cette partie permet de cacher les autres informations c est à dire autres que l'id;le nom ;le prenom dans le tableau
 			for (int i = 3; i < 9; i++) {
				table_Instituteurs.getColumnModel().getColumn(i).setMinWidth(0);
				table_Instituteurs.getColumnModel().getColumn(i).setMaxWidth(0);
				table_Instituteurs.getColumnModel().getColumn(i).setWidth(0);
				
			}
			
		}
	
	

	/*------------------ la methode pour charger la table  Instituteurs rechercher -------------*/
	public void chargerTable_Inst_Rechercher(){
		
		DefaultTableModel  modelInst= new DefaultTableModel();
		modelInst.addColumn("ID");
		modelInst.addColumn("NOM");
		modelInst.addColumn("PRENOM");
		modelInst.addColumn("DATE DE NAISSANCE");
		modelInst.addColumn("NUM_TEL");
		modelInst.addColumn("Email");
		modelInst.addColumn("ADRESSE");
		modelInst.addColumn("NATIONALITE");
		modelInst.addColumn("SEXE");
			
		Vector<Instituteur> po = new Vector<Instituteur>();
		po=new InstituteurDAO().findByName(textField_Rechercher_Nom.getText());
		
		for (Instituteur cl : po) {
			modelInst.addRow(new Object[]{cl.getId(),cl.getNom(),cl.getPrenom(),cl.getDateN(),
					cl.getNumTel(),cl.getEmail(),cl.getAdresse(),cl.getNationalite(),cl.getSexe()});
			
		}
		
		table_Instituteurs.setModel(modelInst);
		//Cette partie permet de cacher les autres informations c est à dire autres que l'id;le nom ;le prenom dans le tableau
			for (int i = 3; i < 9; i++) {
			table_Instituteurs.getColumnModel().getColumn(i).setMinWidth(0);
			table_Instituteurs.getColumnModel().getColumn(i).setMaxWidth(0);
			table_Instituteurs.getColumnModel().getColumn(i).setWidth(0);
			
		}
			
		} 
	
	
	
	/*------------------ Pour charger Instituteur selection dans les champs --------*/
	
	public void chargerInstituteur(Instituteur e){
		 Activer_Champs(false);
		textField_ID_Instituteurs.setText(e.getId().toString());
		
		textField_Nom_Instituteurs.setText(e.getNom());
		textField_Prenom_Instituteurs.setText(e.getPrenom());	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date_Naissance=null;
				
		try {
			date_Naissance = dateFormat.parse(e.getDateN());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtdtnaissance.setDate(date_Naissance);
		//textField_DateNaiss_Instituteurs.setText(e.getDateN());
		textField_Nationnalite_Instituteurs.setText(e.getNationalite());
		textField_Addresse_Instituteurs.setText(e.getAdresse());
		textField_NumTel_Instituteurs.setText(e.getNumTel().toString());
		textField_Email__Instituteurs.setText(e.getEmail());
		
		if(e.getSexe().equals("masculin"))
			rdbtnM.setSelected(true);
		else
			rdbtnF.setSelected(true);
		
		
	
		
		
	}
	
	/*Retourne la classe Selectionné depuis le jtable */
	public Instituteur obtenir_Instituteur_Selection(){
		Instituteur c=new Instituteur();
		c.setId((Long)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 0));
		c.setNom((String)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 1));
		c.setPrenom((String)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 2));
		c.setDateN((String)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 3));
		c.setNumTel((Long)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 4));
		c.setEmail((String)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(),5));
		c.setAdresse((String)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 6));
		c.setNationalite((String)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 7));
		c.setSexe((String)table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 8));
		
		return c;
	}
	
	//   ACtiver les chachamps text
	public void Activer_Champs(boolean b){
		 textField_ID_Instituteurs.setEditable(b);
		 textField_Nom_Instituteurs.setEditable(b);
		 textField_Prenom_Instituteurs.setEditable(b);
		 textField_NumTel_Instituteurs.setEditable(b);
		 textField_Addresse_Instituteurs.setEditable(b);
		 textField_Nationnalite_Instituteurs.setEditable(b);
		 textField_Email__Instituteurs.setEditable(b);
		 rdbtnF.setEnabled(b);
		 rdbtnM.setEnabled(b);
	}
	 
	// cette  methode permet d'effacer Tous les champs de Instituteurs
	
	public void effacer_Champs(){
		 textField_ID_Instituteurs.setText("");
		 textField_Nom_Instituteurs.setText("");
		// textField_DateNaiss_Instituteurs.setText("");
		 textField_Prenom_Instituteurs.setText("");
	     textField_NumTel_Instituteurs.setText("");
		 textField_Addresse_Instituteurs.setText("");
		 textField_Nationnalite_Instituteurs.setText("");
		 textField_Email__Instituteurs.setText("");
		
	     rdbtnF.setSelected(false);
		 rdbtnM.setSelected(false);
		
	}
	
	/* remplir lle jtable des matieres pour chaque instituteur selectionné*/
	
	public void remplirListeMatPourInst(){
		
		modelTableMatiereEnseigne.setRowCount(0);
		Long idInst = Long.parseLong( table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(),0).toString());
		Inst_Mat_Affectation m = new Inst_Mat_Affectation(idInst);
		Vector<Matiere> mat = new Inst_Mat_Affectation_DAO().Matiere_d_instituteur(m);

		System.out.println(table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(), 0).toString());
		//modelTable_Absence.addRow(new Object[]{"12-10-2017","8h00","10h00","Consultation Medicale","Santé"});
		if(mat.size()!=0){
			for(int i=0;i<mat.size();i++){
				modelTableMatiereEnseigne.addRow(new Object[]{mat.elementAt(i).getId_matiere(),mat.elementAt(i).getNom()});
			}
		
		table.setModel(modelTableMatiereEnseigne);
		}
		
	}

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		 frame.setBounds(100, 100, 1500, 726);
	//	frame.setSize(1000,1000);
		frame.getContentPane().add(new PanelGestionInstituteur(), BorderLayout.CENTER );
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
			

	}
}
