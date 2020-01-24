package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import classeMetier.Classe;
import classeMetier.Inst_Mat_Affectation;
import classeMetier.Instituteur;
import classeMetier.Matiere;
import classeMetier.Seance;
import connxion_Requete.Connexion;
import dao.implement.ClasseDAO;
import dao.implement.Inst_Mat_Affectation_DAO;
import dao.implement.InstituteurDAO;
import dao.implement.MatiereDAO;
import dao.implement.SeanceDAO;
//import groovy.swing.binding.JTableProperties;
//import groovy.model.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.border.LineBorder;

public class PanelGestionEmploi extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_IDSeance;
	private JComboBox<String> comboBox_Classe;
	//private JComboBox<?> comboBox_Eleve;
	private JComboBox<String> comboBox_Matiere;
	private JComboBox<String> comboBox_Instituteur;
	private JComboBox<String> comboBox_HeureDebut;
	private JComboBox<String> comboBox_Fin;
   //private JLabel lblGestionDesEmplois;
	private JLabel lblErreurIdSeance;
	private MatiereDAO matiereDAO;
	private ClasseDAO classeDAO;
	private InstituteurDAO instDAO;
	private SeanceDAO seancedao;
	private JComboBox<String> comboBox_jour ;
	private JTable tableSeance;
	private JLabel lblSupprimer;
	private int a;
	private JTable table_liste_classe;
	private Vector<Matiere> mat;
	private JLabel labelErrHDebut;
		public  PanelGestionEmploi(){
			setBorder(new LineBorder(new Color(0, 0, 0)));
			setBounds(241, 142, 1111, 534);
			setLayout(null);
			
			
			matiereDAO=new MatiereDAO();
		//	noteDAO=new NoteDAO();
		//	eleveDAO=new EleveDAO();
			classeDAO=new ClasseDAO();
			instDAO=new InstituteurDAO();
			seancedao=new SeanceDAO();
			
			
			JPanel panel_ListeSeance = new JPanel();
			panel_ListeSeance.setBounds(10, 68, 518, 455);
			panel_ListeSeance.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Liste des S\u00E9ances", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			add(panel_ListeSeance);
			panel_ListeSeance.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 23, 498, 421);
			panel_ListeSeance.add(scrollPane);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(22, 44, 1079, 11);
			add(separator);
			
			JLabel lblGestionDesSalle = new JLabel("Gestion des Emplois :");
			lblGestionDesSalle.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
			lblGestionDesSalle.setBounds(26, 11, 205, 34);
			add(lblGestionDesSalle);
			
			tableSeance = new JTable();
			tableSeance.setRowHeight(30);
			tableSeance.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					chargerSeanceSelectionee(obtenirSeanceSelectionnee());
				}
			});
			tableSeance.setRowHeight(20);
			tableSeance.setFont(new Font("Dialog", Font.PLAIN, 14));
			scrollPane.setViewportView(tableSeance);
			
			JPanel panelSeance = new JPanel();
			
			panelSeance.setBorder(new TitledBorder(null, "Seance ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelSeance.setBounds(538, 68, 563, 368);
			add(panelSeance);
			panelSeance.setLayout(null);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(10, 24, 546, 333);
			panelSeance.add(tabbedPane);
			
			JPanel panel_EmploiEleve = new JPanel();
			tabbedPane.addTab("Seance", null, panel_EmploiEleve, null);
			panel_EmploiEleve.setLayout(null);
			
			
			JLabel lblIdSeance = new JLabel("<html>ID S\u00E9ance : <font color= 'red'> * </font></html>");
			lblIdSeance.setVisible(false);
			lblIdSeance.setBounds(10, 36, 95, 29);
			lblIdSeance.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			panel_EmploiEleve.add(lblIdSeance);
			
			textField_IDSeance = new JTextField();
			textField_IDSeance.setVisible(false);
			textField_IDSeance.setBounds(115, 36, 213, 29);
			textField_IDSeance.setColumns(10);
			panel_EmploiEleve.add(textField_IDSeance);
			
			JLabel labelClasse = new JLabel("<html>Classe : <font color= 'red'> * </font></html>");
			labelClasse.setBounds(10, 108, 95, 23);
			labelClasse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			panel_EmploiEleve.add(labelClasse);
			comboBox_Classe = new JComboBox<String>();
			comboBox_Classe.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			comboBox_Classe.setBounds(115, 106, 213, 29);
			panel_EmploiEleve.add(comboBox_Classe);
			
			
			JLabel label_Instituteur = new JLabel("<html>Instituteur : <font color= 'red'> * </font></html>");
			label_Instituteur.setBounds(10, 148, 95, 23);
			label_Instituteur.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			panel_EmploiEleve.add(label_Instituteur);
			////////////////////////////////////////////////////////////////////COmbo Instituteur
			 comboBox_Instituteur = new JComboBox<>();
			 comboBox_Instituteur.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			 comboBox_Instituteur.addItemListener(new ItemListener() {
			 	public void itemStateChanged(ItemEvent arg0) {
			 		//Long idInst = Long.parseLong( table_Instituteurs.getValueAt(table_Instituteurs.getSelectedRow(),0).toString());
			 		
					String ins=comboBox_Instituteur.getSelectedItem().toString();
					String IdIns=ins.substring(0,ins.indexOf("-"));
					Inst_Mat_Affectation m = new Inst_Mat_Affectation(Long.parseLong(IdIns));
					comboBox_Matiere.removeAllItems();
					
					
					 mat = new Inst_Mat_Affectation_DAO().Matiere_d_instituteur(m);
					for (Matiere matiere : mat) {
						comboBox_Matiere.addItem(matiere.getId_matiere()+"-"+matiere.getNom());
					}
					mat.clear();
					/*Vector<Matiere> vectMat = new Vector<Matiere>();
					vectMat=matiereDAO.findAll();
					comboBox_Matiere.addItem("");
					for (Matiere mat : vectMat) {
						comboBox_Matiere.addItem(mat.getId_matiere()+":"+mat.getNom());
					}*/
			 	}
			 });
			 comboBox_Instituteur.setBounds(115, 146, 213, 29);
			 panel_EmploiEleve.add(comboBox_Instituteur);
			 
			 JLabel label_Matiere = new JLabel("<html>Mati\u00E8re : <font color= 'red'> * </font></html>");
			 label_Matiere.setBounds(10, 182, 95, 33);
			 label_Matiere.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			 panel_EmploiEleve.add(label_Matiere);
			 
			  comboBox_Matiere = new JComboBox<>();
			  comboBox_Matiere.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			  comboBox_Matiere.setBounds(115, 186, 213, 29);
			  panel_EmploiEleve.add(comboBox_Matiere);
			  
			  JLabel lblHeure_Debut = new JLabel("<html>Heure Debut : <font color= 'red'> * </font></html>");
			  lblHeure_Debut.setBounds(10, 226, 106, 29);
			  lblHeure_Debut.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			  panel_EmploiEleve.add(lblHeure_Debut);
			  
			   comboBox_HeureDebut = new JComboBox<String>();
			   comboBox_HeureDebut.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			   comboBox_HeureDebut.setBounds(115, 226, 213, 29);
			   panel_EmploiEleve.add(comboBox_HeureDebut);
			   JLabel lblHeureFin = new JLabel("<html>Heure Fin : <font color= 'red'> * </font></html>");
			   lblHeureFin.setBounds(10, 266, 95, 29);
			   lblHeureFin.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			   panel_EmploiEleve.add(lblHeureFin);
			   
			    comboBox_Fin = new JComboBox<String>();
			    comboBox_Fin.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			    comboBox_Fin.setBounds(115, 266, 213, 29);
			    panel_EmploiEleve.add(comboBox_Fin);
			    
			     lblErreurIdSeance = new JLabel("");
			     lblErreurIdSeance.setVisible(false);
			     lblErreurIdSeance.setToolTipText("Saisir une valeur de type <Entier>");
			     lblErreurIdSeance.setHorizontalAlignment(SwingConstants.CENTER);
			     lblErreurIdSeance.setIcon(new ImageIcon(PanelGestionEmploi.class.getResource("/image/error.png")));
			     lblErreurIdSeance.setBounds(331, 36, 26, 29);
			     panel_EmploiEleve.add(lblErreurIdSeance);
			     
			     JLabel lblJourSance = new JLabel("<html>Jour S\u00E9ance : <font color= 'red'> * </font></html>");
			     lblJourSance.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			     lblJourSance.setBounds(10, 67, 106, 30);
			     panel_EmploiEleve.add(lblJourSance);
			     
			     comboBox_jour = new JComboBox<String>();
			     comboBox_jour.setFont(new Font("Sitka Text", Font.PLAIN, 13));
			     comboBox_jour.setBounds(115, 70, 213, 29);
			     panel_EmploiEleve.add(comboBox_jour);
			     
			     labelErrHDebut = new JLabel("");
			     labelErrHDebut.setToolTipText("Heure Debut Superireur \u00E0 heure fin");
			     labelErrHDebut.setIcon(new ImageIcon(PanelGestionEmploi.class.getResource("/image/error.png")));
			     labelErrHDebut.setBounds(341, 226, 16, 29);
			     labelErrHDebut.setVisible(false);
			     panel_EmploiEleve.add(labelErrHDebut);
			     comboBox_jour.addItem("Lundi");
			     comboBox_jour.addItem("Mardi");
			     comboBox_jour.addItem("Mercredi");
			     comboBox_jour.addItem("Jeudi");
			     comboBox_jour.addItem("Vendredi");
			     comboBox_jour.addItem("Samedi");
			
			
			JPanel panel_EmploiProf = new JPanel();
			tabbedPane.addTab("Emploi Eleve", null, panel_EmploiProf, null);
			panel_EmploiProf.setLayout(null);
			
			JPanel panel_Liste_classe = new JPanel();
			panel_Liste_classe.setBorder(new TitledBorder(null, "Liste Des Classes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_Liste_classe.setBounds(10, 23, 323, 271);
			panel_EmploiProf.add(panel_Liste_classe);
			panel_Liste_classe.setLayout(null);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 22, 303, 238);
			panel_Liste_classe.add(scrollPane_1);
			
			table_liste_classe = new JTable();
			table_liste_classe.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
			table_liste_classe.setRowHeight(30);
			table_liste_classe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			scrollPane_1.setViewportView(table_liste_classe);
			
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OPRERATION", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			panel.setBounds(538, 447, 297, 76);
			add(panel);
			
			JLabel lblAjouter = new JLabel("");
			lblAjouter.setBounds(10, 17, 76, 36);
			lblAjouter.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					 
					 a=JOptionPane.showConfirmDialog( null," Voulez vous ajouter une Seance?");
					if(a==JOptionPane.OK_OPTION){
						if(comboBox_Matiere.getSelectedItem()==null){
							JOptionPane.showMessageDialog(null, "Veuillez Entrez une matiere");
						}else{
						Seance s=genererSeance();
						
						if(superposition(s)){
						 System.out.println("Veuillez controler les Heures il y a superposition");
						 JOptionPane.showMessageDialog(null, "<html><b><font color=red>Veuillez controler les Heures: Il y a superposition entre ce cours et un autre </font></b></html>") ;
						}
						else{
						if(seancedao.create2(s)){
						JOptionPane.showMessageDialog(null, "La Seance ete creee avec success");
						chargerTableSeance();
						}
						else
							JOptionPane.showMessageDialog(null, "Erreur lors de L'ajout");
					}
						chargerTableSeance();
						}
					}
					
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
				}
			});
			panel.setLayout(null);
			lblAjouter.setIcon(new ImageIcon(PanelGestionNote.class.getResource("/image/plus.png")));
			lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblAjouter);
			
			JLabel lblModifier = new JLabel("");
			lblModifier.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(tableSeance.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner une seance");
					}
					else{
					 a=JOptionPane.showConfirmDialog( null,"Etes-vous sûr de vouloir modifier cette seance?");
						if(a==JOptionPane.OK_OPTION){
							if(comboBox_Matiere.getSelectedItem()==null){
								JOptionPane.showMessageDialog(null, "Veuillez Entrez une matiere");
							}
							else{
							Seance sa=genererSeanceModif();
							if(TesterValidation(sa)){
							if(superpositionModif(sa)){
								System.out.println("Veuillez controler les Heures il y a superposition");
								JOptionPane.showMessageDialog(null, "Veuillez controler les Heures: Il y a superposition entre ce cours et un autre ");
							}
							else{
							     if(seancedao.update(sa)){
							      JOptionPane.showMessageDialog(null, "Modifiee avec succes");
							      chargerTableSeance();
							      }
							      else
								JOptionPane.showMessageDialog(null, "Erreur survenue lors de la Modification");
							}
							}else{
								//JOptionPane.showMessageDialog(null, "Erreur survenue lors de la Modification");
							}
						}
						}
						}
						
				}
			});
			lblModifier.setBounds(115, 17, 62, 34);
			lblModifier.setIcon(new ImageIcon(PanelGestionNote.class.getResource("/image/edit.png")));

			lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblModifier);
			
			JLabel label_2 = new JLabel("Ajouter");
			label_2.setHorizontalAlignment(SwingConstants.CENTER);
			label_2.setFont(new Font("Sitka Text", Font.PLAIN, 15));
			label_2.setBounds(10, 51, 76, 14);
			panel.add(label_2);
			
			JLabel label_3 = new JLabel("Modifier");
			label_3.setFont(new Font("Sitka Text", Font.PLAIN, 15));
			label_3.setHorizontalAlignment(SwingConstants.CENTER);
			label_3.setBounds(115, 51, 62, 14);
			panel.add(label_3);
			
			 lblSupprimer = new JLabel("");
			lblSupprimer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(tableSeance.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner une seance");
					}else{
					 a=JOptionPane.showConfirmDialog( null,"Etes-vous sûr de vouloir supprimer cette  Seance?");
					if(a==JOptionPane.OK_OPTION){
					if(seancedao.delete(obtenirSeanceSelectionnee())){
					JOptionPane.showMessageDialog(null, "Supprimer avec succes");
					 chargerTableSeance();
					 }
					else 
						JOptionPane.showMessageDialog(null, "Suppression a echouer \n Erreur survenue ");

					
					}
					}
				}
			});
			lblSupprimer.setBounds(187, 25, 80, 28);
			lblSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
			lblSupprimer.setIcon(new ImageIcon(PanelGestionEmploi.class.getResource("/image/Button-Close-icon.png")));
			panel.add(lblSupprimer);
			
			JLabel lblSupprimer_1 = new JLabel("Supprimer");
			lblSupprimer_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblSupprimer_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
			lblSupprimer_1.setBounds(187, 51, 80, 14);
			panel.add(lblSupprimer_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(null, "IMPRESSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(857, 451, 209, 72);
			add(panel_1);
			
			JButton button = new JButton("Imprimer L'emploi");
			button.setIcon(new ImageIcon(PanelGestionEmploi.class.getResource("/image/print-icon.png")));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//print();
					if(tabbedPane.getSelectedIndex()==1){
						if(table_liste_classe.getSelectedRow()!=-1){
							print(Integer.parseInt(getSelectedClasse().getNum_Clase().toString()));
						}
						else
							JOptionPane.showMessageDialog(null, "<html><b><font color=red>Veuillez Selectionner une classe SVP!</font></b></html>");
					}
					else
						JOptionPane.showMessageDialog(null, "<html><b><font color=red>Veuillez Entrer dans L'onglet Emploi ensuite Selectionnez une classe</font></b></html> ");
				}
			});
			button.setFont(new Font("Tahoma", Font.BOLD, 11));
			button.setBounds(28, 23, 159, 38);
			panel_1.add(button);
			
			
/***********************************       COMBOBOX CLASSE               ***************************************/
			

			// ICI on charge le combobox des classe
		
			Vector<Classe> v = new Vector<Classe>();
			     v=classeDAO.findAll();
			for (Classe classe : v) {
				comboBox_Classe.addItem(classe.getNum_Clase()+"-"+classe.getNom_Clase());
			}
			/*********************************** ICI on charge le combobox des Instituteurs   *****************************************/
			Vector<Instituteur> vectins = new Vector<Instituteur>();
			vectins=instDAO.findAll();
			//comboBox_Instituteur.addItem("");
			for (Instituteur ins : vectins) {
				comboBox_Instituteur.addItem(ins.getId()+"-"+ins.getNom()+" "+ins.getPrenom());
			}
			/*********************/
			for(int i=8;i<18;i++){
				comboBox_HeureDebut.addItem(i+"h00");
				comboBox_HeureDebut.addItem(i+"h45");
				}
			for(int i=9;i<18;i+=2){
				//comboBox_Fin.addItem(i+" h 00 min");
				comboBox_Fin.addItem(i+"h45");
				}
			
			/*********************************** ICI on charge le combobox des Matieres   *****************************************/
			/*Vector<Matiere> vectMat = new Vector<Matiere>();
			vectMat=matiereDAO.findAll();
			comboBox_Matiere.addItem("");
			for (Matiere mat : vectMat) {
				comboBox_Matiere.addItem(mat.getId_matiere()+":"+mat.getNom());
			}*/
			/*********************************** ICI on charge le combobox des jours  *****************************************/
			chargerTableSeance();
			filltableClasse();
		}
		boolean TesterValidation(Seance se){
			boolean b=false;
			String d1,d2,f3,f4,sd1,sd2,sf3,sf4;
			int k=0,hd,hf,shd,shf;
			
			 sd1=se.getHeureDebut().substring(0,se.getHeureDebut().indexOf("h"));
			 sd2=se.getHeureDebut().substring(se.getHeureDebut().indexOf("h")+1,se.getHeureDebut().length());
		   shd=(Integer.parseInt(sd1)*60)+Integer.parseInt(sd2);
		   sf3=se.getHeurFin().substring(0,se.getHeurFin().indexOf("h"));
			 sf4=se.getHeurFin().substring(se.getHeurFin().indexOf("h")+1,se.getHeurFin().length());
		   shf=(Integer.parseInt(sf3)*60)+Integer.parseInt(sf4);
			if((shf-shd)<0){
			 labelErrHDebut.setVisible(true);
			 return false;
			 }
			 else {
				 labelErrHDebut.setVisible(false);
				 return true;
				 }
		}
		/*************************  Recuperere  les Informations Entrées    et retourne un objet de type Séance          ***********************/  
		
		public Seance genererSeanceModif(){
			String id=textField_IDSeance.getText();
			String cls=comboBox_Classe.getSelectedItem().toString();
			String ins=comboBox_Instituteur.getSelectedItem().toString();
			String mat=comboBox_Matiere.getSelectedItem().toString();
			String IdIns=ins.substring(0,ins.indexOf("-"));
			String IdClasse= cls.substring(0,cls.indexOf("-"));
			String IdMat=mat.substring(0,mat.indexOf("-"));
			String Hdebut=comboBox_HeureDebut.getSelectedItem().toString();
			String Hfin=comboBox_Fin.getSelectedItem().toString();
			String j=comboBox_jour.getSelectedItem().toString();
			//String d=textField_DateSeance.getText();
			return new Seance((Long.parseLong(id)),j,Hdebut,Hfin,Long.parseLong(IdClasse),Long.parseLong(IdIns),Long.parseLong(IdMat));
		}
		public Seance genererSeance(){
			//String id=textField_IDSeance.getText();
			String cls=comboBox_Classe.getSelectedItem().toString();
			String ins=comboBox_Instituteur.getSelectedItem().toString();
			String mat=comboBox_Matiere.getSelectedItem().toString();
			String IdIns=ins.substring(0,ins.indexOf("-"));
			String IdClasse= cls.substring(0,cls.indexOf("-"));
			String IdMat=mat.substring(0,mat.indexOf("-"));
			String Hdebut=comboBox_HeureDebut.getSelectedItem().toString();
			String Hfin=comboBox_Fin.getSelectedItem().toString();
			String j=comboBox_jour.getSelectedItem().toString();
			//String d=textField_DateSeance.getText();
			return new Seance(j,Hdebut,Hfin,Long.parseLong(IdClasse),Long.parseLong(IdIns),Long.parseLong(IdMat));
		}
		
		/***********************   Permet de charger la table Seance ****************************/
		public void chargerTableSeance(){
			javax.swing.table.DefaultTableModel modelSeance =new javax.swing.table.DefaultTableModel();
			modelSeance.addColumn("ID_Seance");
			modelSeance.addColumn("Matiere");
			modelSeance.addColumn("Instituteur");
			modelSeance.addColumn("Classe");
			modelSeance.addColumn("Jour");
			modelSeance.addColumn("Debut");
			modelSeance.addColumn("Fin");
			
			Vector<Seance> vs=new Vector<Seance>();
			vs=seancedao.findALL();
			String mat,nomInst,nomClass;
			String []Nom=new String [1];
			/*for(Seance seance: vs){
				//n1=seancedao.findNomMatiere(seance.getNumMatiere());
			    // n2=seancedao.findNomInst(seance.getNumInstituteur());
				modelSeance.addRow(new Object[]{seance.getIdSeance(),/*n1,n2});
				}*/
			for (Iterator<Seance> iterator = vs.iterator(); iterator.hasNext();) {
				Seance seance = (Seance) iterator.next();
				 mat=seancedao.findNomMatiere(seance.getNumMatiere());
			    // n2=seancedao.findNomInst(seance.getNumInstituteur());
				 Nom=seancedao.findNomInst(seance.getNumInstituteur());
				 nomInst=Nom[0]+" "+Nom[1];
				 nomClass=seancedao.findNomClasse(seance.getNumClasse());
				modelSeance.addRow(new Object[]{seance.getIdSeance(),mat,nomInst,nomClass,seance.getJour(),seance.getHeureDebut(),seance.getHeurFin()});
			}
			tableSeance.setModel(modelSeance);
			tableSeance.getColumnModel().getColumn(0).setMinWidth(0);
			tableSeance.getColumnModel().getColumn(0).setMaxWidth(0);
			tableSeance.getColumnModel().getColumn(0).setWidth(0);
			tableSeance.getColumnModel().getColumn(2).setMinWidth(100);
			tableSeance.getColumnModel().getColumn(2).setMaxWidth(100);
			tableSeance.getColumnModel().getColumn(2).setWidth(100);
			tableSeance.getColumnModel().getColumn(1).setMinWidth(80);
			tableSeance.getColumnModel().getColumn(1).setMaxWidth(80);
			tableSeance.getColumnModel().getColumn(1).setWidth(80);
			tableSeance.getColumnModel().getColumn(3).setMinWidth(100);
			tableSeance.getColumnModel().getColumn(3).setMaxWidth(100);
			tableSeance.getColumnModel().getColumn(3).setWidth(100);
			tableSeance.getColumnModel().getColumn(5).setMinWidth(50);
			tableSeance.getColumnModel().getColumn(5).setMaxWidth(50);
			tableSeance.getColumnModel().getColumn(5).setWidth(50);
			tableSeance.getColumnModel().getColumn(6).setMinWidth(50);
			tableSeance.getColumnModel().getColumn(6).setMaxWidth(50);
			tableSeance.getColumnModel().getColumn(6).setWidth(50);
			/*Single line selection , column not modifiable*/
			tableSeance.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			/*Jtable not draggble , not resizble*/
			tableSeance.setRowHeight(30);
			tableSeance.getTableHeader().setReorderingAllowed(false);
			tableSeance.getTableHeader().setResizingAllowed(false);
			tableSeance.setDefaultEditor(Object.class,null);
			
		}
		/***       Permet de Charger Les champs       ***/
		
		
		public void chargerSeanceSelectionee(Seance se){
			 textField_IDSeance.setText(se.getIdSeance().toString());
			// textField_DateSeance.setText(se.getDate());
			 Classe ec=classeDAO.find(se.getNumClasse());
			 comboBox_Classe.setSelectedItem(ec.getNum_Clase()+"-"+ec.getNom_Clase());
			 Matiere mat=matiereDAO.getByid(se.getNumMatiere());
			 comboBox_Matiere.setSelectedItem(mat.getId_matiere()+"-"+mat.getNom());
			 Instituteur i=instDAO.find(se.getNumInstituteur());
			  comboBox_Instituteur.setSelectedItem(i.getId()+"-"+i.getNom()+" "+i.getPrenom());;
			  comboBox_HeureDebut.setSelectedItem(se.getHeureDebut());;
			  comboBox_Fin.setSelectedItem(se.getHeurFin());
			//  textField_DateSeance.setText(se.getDate());;
			 comboBox_jour.setSelectedItem(se.getJour());
			 
		}
		
		public Seance obtenirSeanceSelectionnee(){
			Seance s=new Seance();
			String nomComplet= (tableSeance.getValueAt(tableSeance.getSelectedRow(), 2)).toString();
			String nom=nomComplet.substring(0, nomComplet.indexOf(" "));
			String prenom=nomComplet.substring(nomComplet.indexOf(" ")+1, nomComplet.length());
			String nomClasse=(tableSeance.getValueAt(tableSeance.getSelectedRow(), 3)).toString();
			s.setIdSeance((Long)(tableSeance.getValueAt(tableSeance.getSelectedRow(), 0)));
			s.setNumMatiere(seancedao.findIdMatiere((String)(tableSeance.getValueAt(tableSeance.getSelectedRow(), 1))));
			s.setNumInstituteur(seancedao.findIdInst(nom,prenom));
			s.setNumClasse(seancedao.findIdClasse(nomClasse));
			s.setJour((tableSeance.getValueAt(tableSeance.getSelectedRow(), 4)).toString());
			s.setHeureDebut((tableSeance.getValueAt(tableSeance.getSelectedRow(), 5)).toString());
			s.setHeurFin((tableSeance.getValueAt(tableSeance.getSelectedRow(), 6)).toString());
			return s;
		}
		
		/*********
		 * superposition 1
		 */
	public boolean superposition1(Seance se){
			boolean b=false;
			String d1,d2,f3,f4,sd1,sd2,sf3,sf4;
			int k=0,hd,hf,shd,shf;
			
			 sd1=se.getHeureDebut().substring(0,se.getHeureDebut().indexOf("h"));
			 sd2=se.getHeureDebut().substring(se.getHeureDebut().indexOf("h")+1,se.getHeureDebut().length());
		   shd=(Integer.parseInt(sd1)*60)+Integer.parseInt(sd2);
		   sf3=se.getHeurFin().substring(0,se.getHeurFin().indexOf("h"));
			 sf4=se.getHeurFin().substring(se.getHeurFin().indexOf("h")+1,se.getHeurFin().length());
		   shf=(Integer.parseInt(sf3)*60)+Integer.parseInt(sf4);
			Vector<Seance> vs=new Vector<Seance>();
			vs.clear();
			vs=seancedao.findALL();
			
			while(k < vs.size()-1 && b==false){
				 d1=vs.elementAt(k).getHeureDebut().substring(0,vs.elementAt(k).getHeureDebut().indexOf("h"));
				 d2=vs.elementAt(k).getHeureDebut().substring(vs.elementAt(k).getHeureDebut().indexOf("h")+1,vs.elementAt(k).getHeureDebut().length());
				   hd=(Integer.parseInt(d1)*60)+Integer.parseInt(d2);
				   f3=vs.elementAt(k).getHeurFin().substring(0,vs.elementAt(k).getHeurFin().indexOf("h"));
					 f4=vs.elementAt(k).getHeurFin().substring(vs.elementAt(k).getHeurFin().indexOf("h")+1,vs.elementAt(k).getHeurFin().length());
				   hf=(Integer.parseInt(f3)*60)+Integer.parseInt(f4);
				if(( vs.elementAt(k).getJour().equals(se.getJour())) &&( vs.elementAt(k).getNumClasse()==se.getNumClasse())){
				//if(vs.elementAt(k).getHeureDebut().equals(se.getHeureDebut())||vs.elementAt(k).getHeurFin().equals(se.getHeurFin())){
				   if(shd>=hd && shd<hf){
					b=true;
				   }
				   else if(shf>hd && shf<=hf){
						b=true;
					}
				   /* if(shd==hd && shd<=hf)
						b=true;*/
					}
				else if((vs.elementAt(k).getNumInstituteur()==se.getNumInstituteur()) && (vs.elementAt(k).getJour().equals(se.getJour()))){
						/*d1=vs.elementAt(k).getHeureDebut().substring(0,vs.elementAt(k).getHeureDebut().indexOf("h"));
						 d2=vs.elementAt(k).getHeureDebut().substring(vs.elementAt(k).getHeureDebut().indexOf("h")+1,vs.elementAt(k).getHeureDebut().length());
					   hd=(Integer.parseInt(d1)*60)+Integer.parseInt(d2);
					   f3=vs.elementAt(k).getHeurFin().substring(0,vs.elementAt(k).getHeurFin().indexOf("h"));
						 f4=vs.elementAt(k).getHeurFin().substring(vs.elementAt(k).getHeurFin().indexOf("h")+1,vs.elementAt(k).getHeurFin().length());
					   hf=(Integer.parseInt(f3)*60)+Integer.parseInt(f4);*/
					   
					   if(shd>=hd && shd<hf){
							b=true;
							}
					
					   else  if(shf>hd && shf<=hf){
								b=true;
							}
					    /*  if(shd==hd && shd<=hf){
								b=true;}*/
					}
				
				k++;
				}
			return b;
		}
		///////////////////////*************   Teste la  Superposition (chevauchement)  **************///////////////////////
		public boolean superposition(Seance se){
			boolean b=false;
			String d1,d2,f3,f4,sd1,sd2,sf3,sf4;
			int hd,hf,shd,shf;
			
			 sd1=se.getHeureDebut().substring(0,se.getHeureDebut().indexOf("h"));
			 sd2=se.getHeureDebut().substring(se.getHeureDebut().indexOf("h")+1,se.getHeureDebut().length());
		   shd=(Integer.parseInt(sd1)*60)+Integer.parseInt(sd2);
		   sf3=se.getHeurFin().substring(0,se.getHeurFin().indexOf("h"));
			 sf4=se.getHeurFin().substring(se.getHeurFin().indexOf("h")+1,se.getHeurFin().length());
		   shf=(Integer.parseInt(sf3)*60)+Integer.parseInt(sf4);
			Vector<Seance> vs=new Vector<Seance>();
			vs.clear();
			vs=seancedao.findALL();
			for (Seance seance : vs) {
				d1=seance.getHeureDebut().substring(0,seance.getHeureDebut().indexOf("h"));
				 d2=seance.getHeureDebut().substring(seance.getHeureDebut().indexOf("h")+1,seance.getHeureDebut().length());
				   hd=(Integer.parseInt(d1)*60)+Integer.parseInt(d2);
				   f3=seance.getHeurFin().substring(0,seance.getHeurFin().indexOf("h"));
					 f4=seance.getHeurFin().substring(seance.getHeurFin().indexOf("h")+1,seance.getHeurFin().length());
				   hf=(Integer.parseInt(f3)*60)+Integer.parseInt(f4);
				if(( seance.getJour().equals(se.getJour())) &&( seance.getNumClasse()==se.getNumClasse())){
				
				   if(shd>=hd && shd<hf){
					b=true;
					return b;
				   }
				   else if(shf>hd && shf<=hf){
						b=true;
					   return b;
					
					}
				   }
				else if((seance.getNumInstituteur()==se.getNumInstituteur()) && (seance.getJour().equals(se.getJour()))){
					   
					   if(shd>=hd && shd<hf){
							b=true;
							return b;
							}
					
					   else  if(shf>hd && shf<=hf){
								b=true;
								return b;
					}
			}
			
			}
			return b;
		}
		
		public boolean superpositionModif(Seance se){
			boolean b=false,c=false;
			String d1,d2,f3,f4,sd1,sd2,sf3,sf4;
			int hd,hf,shd,shf;
			
			 sd1=se.getHeureDebut().substring(0,se.getHeureDebut().indexOf("h"));
			 sd2=se.getHeureDebut().substring(se.getHeureDebut().indexOf("h")+1,se.getHeureDebut().length());
		   shd=(Integer.parseInt(sd1)*60)+Integer.parseInt(sd2);
		   sf3=se.getHeurFin().substring(0,se.getHeurFin().indexOf("h"));
			 sf4=se.getHeurFin().substring(se.getHeurFin().indexOf("h")+1,se.getHeurFin().length());
		   shf=(Integer.parseInt(sf3)*60)+Integer.parseInt(sf4);
			Vector<Seance> vs=new Vector<Seance>();
			vs.clear();
			vs=seancedao.findALL();
			
			Seance element;
			/*for (Iterator iterator = vs.iterator(); iterator.hasNext();) {
				System.out.println("ong "+vs.size());
				Seance seance1 = (Seance) iterator.next();
				JOptionPane.showMessageDialog(null, se);
				if((long)seance1.getIdSeance()==(long)se.getIdSeance()){
					vs.removeElement(seance1);
					System.out.println("Supprimer");
				}
			}*/
                 for (int i = 0; (i < vs.size() && c==false); i++) {
                	  element = vs.elementAt(i);
                	 if((long)element.getIdSeance()==(long)se.getIdSeance()){
                		 vs.removeElementAt(i);
                		 System.out.println("SUppri;er avec succes");
                		 c=true;
                	 }
					     
				}
			for (Seance seance : vs) {
				d1=seance.getHeureDebut().substring(0,seance.getHeureDebut().indexOf("h"));
				 d2=seance.getHeureDebut().substring(seance.getHeureDebut().indexOf("h")+1,seance.getHeureDebut().length());
				   hd=(Integer.parseInt(d1)*60)+Integer.parseInt(d2);
				   f3=seance.getHeurFin().substring(0,seance.getHeurFin().indexOf("h"));
					 f4=seance.getHeurFin().substring(seance.getHeurFin().indexOf("h")+1,seance.getHeurFin().length());
				   hf=(Integer.parseInt(f3)*60)+Integer.parseInt(f4);
				if(( seance.getJour().equals(se.getJour())) &&( seance.getNumClasse()==se.getNumClasse())){
				
				   if(shd>=hd && shd<hf){
					b=true;
					return b;
				   }
				   else if(shf>hd && shf<=hf){
						b=true;
					   return b;
					
					}
				   }
				else if((seance.getNumInstituteur()==se.getNumInstituteur()) && (seance.getJour().equals(se.getJour()))){
					   
					   if(shd>=hd && shd<hf){
							b=true;
							return b;
							}
					
					   else  if(shf>hd && shf<=hf){
								b=true;
								return b;
					}
			}
			
			}
			return b;
		}
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void print(Integer id){
			org.apache.log4j.BasicConfigurator.configure();
			
			Map param=  new HashMap();
			param.put("ID_CLASSE",id);
			try {
				Connection con = new Connexion().getConnection();
			    JasperReport report=JasperCompileManager.compileReport("Rapport/Emploi.jrxml");
				JasperPrint jp= JasperFillManager.fillReport(report,param,con);
				
				JasperViewer jw=new JasperViewer(jp,false);
			
				jw.setVisible(true);
				JasperExportManager.exportReportToPdf(jp);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, ""+e.getMessage());
			}
		}
		public void filltableClasse(){
			
			DefaultTableModel  modeleClasse= new DefaultTableModel();
			modeleClasse.addColumn("NUM");
			modeleClasse.addColumn("NOM");
			modeleClasse.addColumn("NIVEAU");
			Vector<Classe> po = new Vector<Classe>();
			po=classeDAO.findAll();
			
			for (Classe cl : po) {
				modeleClasse.addRow(new Object[]{cl.getNum_Clase(),cl.getNom_Clase(),cl.getNiveau_Classe()});
				
			}
			table_liste_classe.setModel(modeleClasse);
			table_liste_classe.getColumnModel().getColumn(0).setMinWidth(0);
			table_liste_classe.getColumnModel().getColumn(0).setMaxWidth(0);
			table_liste_classe.getColumnModel().getColumn(0).setWidth(0);
		}
		
		public Classe getSelectedClasse(){
			Classe c=new Classe();
			c.setNum_Clase((Long)table_liste_classe.getValueAt(table_liste_classe.getSelectedRow(), 0));
			c.setNom_Clase((String)table_liste_classe.getValueAt(table_liste_classe.getSelectedRow(), 1));  
			c.setNiveau_Classe((Long)table_liste_classe.getValueAt(table_liste_classe.getSelectedRow(),2));  
			
			return c;
		}
		public static void main(String[] args) {
		
			JFrame frame = new JFrame();
			 frame.setBounds(100, 100, 1500, 726);
			frame.getContentPane().add(new PanelGestionEmploi(), BorderLayout.CENTER );
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);

		}
}
