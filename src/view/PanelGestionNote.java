package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import classeMetier.Classe;
import classeMetier.Eleve;
import classeMetier.Matiere;
import classeMetier.Note;
import connxion_Requete.Connexion;
import dao.implement.ClasseDAO;
import dao.implement.EleveDAO;
import dao.implement.MatiereDAO;
import dao.implement.NoteDAO;
import mondrian.rolap.cache.HardSmartCache;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class PanelGestionNote extends JPanel{
	private JLabel lblClasse;	
	private JLabel lblEleve;
	private JTextField textField_DS;
	private JTextField textField_Examen;
	private JTable table_NoteEleve;
	public static JComboBox comboBox_Classe ;
	public JComboBox comboBox_Eleve;
	private JComboBox  comboBox_Matière;
	private MatiereDAO matiereDAO;
	private NoteDAO noteDAO;
	private EleveDAO eleveDAO;
	private ClasseDAO classeDAO;
	private String o;
	private int cm;
	
	
	public PanelGestionNote(){
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 37, 299, 387);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Saisir", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);
		
		matiereDAO=new MatiereDAO();
		noteDAO=new NoteDAO();
		eleveDAO=new EleveDAO();
		classeDAO=new ClasseDAO();
		
		lblClasse = new JLabel("<html>Classe: <font color= 'red'> * </font></html>");
		lblClasse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblClasse.setBounds(10, 29, 62, 23);
		panel.add(lblClasse);
		
		 comboBox_Classe = new JComboBox();
		
		 comboBox_Classe.setBounds(76, 29, 213, 29);
			// ICI on charge le combobox des classe
					Vector<Classe> v = new Vector<Classe>();
					v=classeDAO.findAll();
					for (Classe classe : v) {
						comboBox_Classe.addItem(classe.getNum_Clase()+":"+classe.getNom_Clase());
					}
					
	/*****************  Listner sur combo claasse   il prend  le num classe du combo classe et charge tous les eleves de cette classe     *******************/
					
		 comboBox_Classe.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent arg0) {
					chargerComboBoxEleve2(obtenirClasseSelection());
					chargerTable_Note_eleve();
			 	}
			 });
		panel.add(comboBox_Classe);
		
		 lblEleve = new JLabel("<html>Eleve: <font color= 'red'> * </font></html>");
		 lblEleve.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblEleve.setBounds(10, 76, 62, 23);
		panel.add(lblEleve);
		
		 comboBox_Eleve = new JComboBox();
		comboBox_Eleve.setBounds(76, 76, 213, 29);
		
 		
		panel.add(comboBox_Eleve);
		
		JLabel lblDS = new JLabel("<html>Note DS : <font color= 'red'> * </font></html>");
		lblDS.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblDS.setBounds(10, 198, 101, 29);
		panel.add(lblDS);
		
		textField_DS = new JTextField();
		textField_DS.setBounds(119, 198, 101, 29);
		panel.add(textField_DS);
		textField_DS.setColumns(10);
		
		JLabel lblExamen = new JLabel("<html>Note Examen : <font color= 'red'> * </font></html>");
		lblExamen.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblExamen.setBounds(10, 258, 111, 29);
		panel.add(lblExamen);
		
		textField_Examen = new JTextField();
		textField_Examen.setColumns(10);
		textField_Examen.setBounds(119, 258, 101, 29);
		panel.add(textField_Examen);
		
		JLabel lblMatière = new JLabel("<html>Mati\u00E8re : <font color= 'red'> * </font></html>");
		lblMatière.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblMatière.setBounds(10, 137, 88, 29);
		panel.add(lblMatière);
		
		 comboBox_Matière = new JComboBox();
		 
		comboBox_Matière.setBounds(119, 140, 155, 29);
		
		/****************** ICI on charge le combobox des Matieres*******************/
		charger_ComboBox_Matiere();
		
		
		/********** Listener sur le combo box Matiere Pour nous permettre de changer chaque fois dans le tableau le nom de la matiere **********/
		
		comboBox_Matière.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {
		 		 o=obtenir_Nom_Matiere();
		 		 cm=obtenir_Code_Matiere();
		 		chargerTable_Note_eleve();
		 	}
		 });
		panel.add(comboBox_Matière);
		
		JPanel panel_Note = new JPanel();
		panel_Note.setBounds(319, 37, 733, 387);
		panel_Note.setBorder(new TitledBorder(null, "Note", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_Note);
		panel_Note.setLayout(null);
		
		JScrollPane scrollPane_Note = new JScrollPane();
		scrollPane_Note.setBounds(24, 27, 682, 338);
		panel_Note.add(scrollPane_Note);
		
		table_NoteEleve = new JTable();
		/*Single line selection , column not modifiable*/// Cette ligne permet de seelectionner une seul ligne : elle evite de selctionner plusieurs lignes a la fois
		table_NoteEleve.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*Jtable not draggble , not resizble*/
		table_NoteEleve.getTableHeader().setReorderingAllowed(false);
		table_NoteEleve.getTableHeader().setResizingAllowed(false);
		//permet de modifier une ligne
		table_NoteEleve.setDefaultEditor(Object.class,null);
		scrollPane_Note.setViewportView(table_NoteEleve);
		/*DefaultTableModel modeltable_NoteEleve = new DefaultTableModel();
		modeltable_NoteEleve.addColumn("ID");
		modeltable_NoteEleve.addColumn("Nom");
		modeltable_NoteEleve.addColumn("Prenom");
		modeltable_NoteEleve.addColumn("Matière");
		modeltable_NoteEleve.addColumn("Note DS");
		modeltable_NoteEleve.addColumn("Note Examen");
		modeltable_NoteEleve.addColumn("Moyenne");
		
		table_NoteEleve.setModel(modeltable_NoteEleve);*/
		
		JPanel panel_Operation = new JPanel();
		panel_Operation.setBounds(10, 435, 222, 90);
		panel_Operation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OPRERATION", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		add(panel_Operation);
		panel_Operation.setLayout(null);
		
		JLabel lbl_Ajouter = new JLabel("");
		lbl_Ajouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String s=(String)comboBox_Eleve.getSelectedItem();
				Long x=Long.parseLong(s.substring(0,s.indexOf(":")));
				
				 if(aUneNote(x)){
					 JOptionPane.showMessageDialog(null,"Cet eleve a deja une note "+"\n"+"Si voulez modifier alors cliquez sur <Modifier>");
				}
				else{
					if(textField_DS.getText().equals("")||textField_Examen.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Veuillez Remplir les champs SVP");
					}else{
				Note n= genererNote();
				noteDAO.create(n);
				JOptionPane.showMessageDialog(null,"Ajouter avec succes");
			     viderChamps();
			     chargerTable_Note_eleve();}
					}
			}
		});
		lbl_Ajouter.setIcon(new ImageIcon(PanelGestionNote.class.getResource("/image/plus.png")));
		lbl_Ajouter.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Ajouter.setBounds(10, 17, 83, 45);
		panel_Operation.add(lbl_Ajouter);
		
		JLabel lbl_Modifier = new JLabel("");
		lbl_Modifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField_DS.getText().equals("")||textField_Examen.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Veuillez Remplir les champs SVP");
				}else{
				if((noteDAO.findNote(obtenir_num_Eleve(), obtenir_Code_Matiere()))==null){
					JOptionPane.showMessageDialog(null,"Cet eleve n'a pas encore une note dans cette matiere"+"\n"+"Veuillez donc l'ajouter d'abord .");
				}
				else{
				int a=JOptionPane.showConfirmDialog(null,"Voulez vous Modifier les Notes de ce eleve?");
				if(a==JOptionPane.OK_OPTION){
				Note n= new Note() ;
				 n=genererNote();
				noteDAO.update(n);
				JOptionPane.showMessageDialog(null,"Modifier  avec succes");
				chargerTable_Note_eleve();
			     viderChamps();
				}
			     }
				
			     }
			}
		});
		lbl_Modifier.setIcon(new ImageIcon(PanelGestionNote.class.getResource("/image/edit.png")));
		lbl_Modifier.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Modifier.setBounds(103, 17, 74, 45);
		panel_Operation.add(lbl_Modifier);
		
		JLabel label_1 = new JLabel("Ajouter");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_1.setBounds(10, 62, 83, 14);
		panel_Operation.add(label_1);
		
		JLabel label_2 = new JLabel("Modifier");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_2.setBounds(101, 62, 76, 14);
		panel_Operation.add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "IMPRESSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(319, 435, 544, 88);
		add(panel_1);
		panel_1.setLayout(null);
		
		/*******************************   IMPRESSION  DE LA LISTE  ******************************************/
		
		JButton btnImprimerLaListe = new JButton("Imprimer la Liste");
		btnImprimerLaListe.setIcon(new ImageIcon(PanelGestionNote.class.getResource("/image/print-icon.png")));
		btnImprimerLaListe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectedEleve=(String)comboBox_Eleve.getSelectedItem();
				int idEleve = Integer.parseInt(  selectedEleve.substring(0,selectedEleve.indexOf(":")) ); 
		Map param = new HashMap();
				param.put("idEleve", idEleve);
				org.apache.log4j.BasicConfigurator.configure();
			     try {
			    	 Connection con = Connexion.getConnection();
				    
				     
				     
				     JasperReport report=JasperCompileManager.compileReport("Rapport/carnet_note.jrxml");
					JasperPrint jp= JasperFillManager.fillReport(report,param,con);
					
					JasperViewer jw=new JasperViewer(jp,false);
				
					jw.setVisible(true);
					JasperExportManager.exportReportToPdf(jp);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e); //e.printStackTrace();
				}
			}
		});
		/***********************************************************************************************/
		
		
		btnImprimerLaListe.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimerLaListe.setBounds(48, 25, 164, 40);
		panel_1.add(btnImprimerLaListe);
		
		JButton btn_imprimer_sem1 = new JButton("Carnet SEM1");
		btn_imprimer_sem1.setIcon(new ImageIcon(PanelGestionNote.class.getResource("/image/print-icon.png")));
		btn_imprimer_sem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String selectedEleve=(String)comboBox_Eleve.getSelectedItem();
				int idEleve = Integer.parseInt(  selectedEleve.substring(0,selectedEleve.indexOf(":")) ); 
		Map param = new HashMap();
				param.put("idEleve", idEleve);
				org.apache.log4j.BasicConfigurator.configure();
			     try {
			    	 Connection con = Connexion.getConnection();
				    
				     
				     
				     JasperReport report=JasperCompileManager.compileReport("Rapport/carnet_note_SEM1.jrxml");
					JasperPrint jp= JasperFillManager.fillReport(report,param,con);
					
					JasperViewer jw=new JasperViewer(jp,false);
				
					jw.setVisible(true);
					JasperExportManager.exportReportToPdf(jp);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e); //e.printStackTrace();
				}
				
				
				
			}
		});
		btn_imprimer_sem1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_imprimer_sem1.setBounds(222, 25, 137, 40);
		panel_1.add(btn_imprimer_sem1);
		
		JButton btn_imprim_SEM2 = new JButton("Carnet SEM2");
		btn_imprim_SEM2.setIcon(new ImageIcon(PanelGestionNote.class.getResource("/image/print-icon.png")));
		btn_imprim_SEM2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String selectedEleve=(String)comboBox_Eleve.getSelectedItem();
				int idEleve = Integer.parseInt(  selectedEleve.substring(0,selectedEleve.indexOf(":")) ); 
		Map param = new HashMap();
				param.put("idEleve", idEleve);
				org.apache.log4j.BasicConfigurator.configure();
			     try {
			    	 Connection con = Connexion.getConnection();
				    
				     
				     
				     JasperReport report=JasperCompileManager.compileReport("Rapport/carnet_note_SEM2.jrxml");
					JasperPrint jp= JasperFillManager.fillReport(report,param,con);
					
					JasperViewer jw=new JasperViewer(jp,false);
				
					jw.setVisible(true);
					JasperExportManager.exportReportToPdf(jp);
				} catch (JRException r) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, r); //e.printStackTrace();
				}
				
				
			}
		});
		btn_imprim_SEM2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_imprim_SEM2.setBounds(369, 25, 137, 40);
		panel_1.add(btn_imprim_SEM2);
		
		 /********************Permet de tout charger Au Demarrage (Tableau, comboboxs) **************/
		// chargerComboBoxEleve1();
		chargerComboBoxEleve2(obtenirClasseSelection());
		 //charger_ComboBox_Matiere();
		  /*********************************************************************************************/
		 /********************Au Demarrage pour mettre le nom et la note dans le tableau **************/
		/*********************************************************************************************/
		                         o=obtenir_Nom_Matiere();
 		                         cm=obtenir_Code_Matiere();
 	 /*********************************************************************************************/
		chargerTable_Note_eleve();
	}
	
	/*------------------ la methode pour charger la table  etudiant -------------*/
	public void chargerTable_Note_eleve(){
		
		DefaultTableModel modeltable_NoteEleve = new DefaultTableModel();
		modeltable_NoteEleve.addColumn("ID");
		modeltable_NoteEleve.addColumn("Nom");
		modeltable_NoteEleve.addColumn("Prenom");
		modeltable_NoteEleve.addColumn("Matiere");
		modeltable_NoteEleve.addColumn("Note DS");
		modeltable_NoteEleve.addColumn("Note Examen");
		modeltable_NoteEleve.addColumn("Moyenne");
		
		table_NoteEleve.setModel(modeltable_NoteEleve);
			
		String s=(String)(comboBox_Classe.getSelectedItem());
		Long numclass= Long.parseLong(s.substring(0,s.indexOf(":")));
		
		
			Vector<Eleve> vect = new Vector<Eleve>();
			vect=eleveDAO.findByNumClasse(numclass);
			
			Note n=new Note();
			
			int i=0;
			for (Iterator iterator = vect.iterator(); iterator.hasNext();) {
	
				Eleve eleve = (Eleve) iterator.next();
				modeltable_NoteEleve.addRow(new Object[]{eleve.getNum_ins(),eleve.getNom(),eleve.getPrenom()});
				n=noteDAO.findNote(eleve.getNum_ins(),cm);
				
				 modeltable_NoteEleve.setValueAt(o, i, 3);
				 if(n==null){
					 modeltable_NoteEleve.setValueAt("Pas de note ", i, 4);
						modeltable_NoteEleve.setValueAt("Pas de note ", i, 5);
				 }else{
				modeltable_NoteEleve.setValueAt(n.getDs(), i, 4);
				modeltable_NoteEleve.setValueAt(n.getExamen(), i, 5);}
				i++;
				//table_NoteEleve.setValueAt(15, 0, 3);
			}
			
			/*for (Eleve cl : vect) {
				modeltable_NoteEleve.addRow(new Object[]{cl.getNum_ins(),cl.getNom(),cl.getPrenom()});
				
				
			}*/
			
			/*table_Etudiant.setModel(modelEleve);
			//Cette partie permet de cacher les autres informations c est à dire autres que l'id;le nom ;le prenom dans le tableau
 			for (int i = 3; i < 10; i++) {
				table_Etudiant.getColumnModel().getColumn(i).setMinWidth(0);
				table_Etudiant.getColumnModel().getColumn(i).setMaxWidth(0);
				table_Etudiant.getColumnModel().getColumn(i).setWidth(0);*/
				
			}
	
	/********** cette methode sans paramettre permet de charger le combobox eleve  *******/
	
	public void chargerComboBoxEleve1(){
		comboBox_Eleve.removeAllItems();
		EleveDAO de = eleveDAO;
		Vector<Eleve> elev = new Vector<Eleve>();
		String c=(String)(comboBox_Classe.getSelectedItem());
		Long numclass= Long.parseLong(c.substring(0,c.indexOf(":")));
		elev =de.findByNumClasse(numclass);
		for (Eleve e : elev) {
			comboBox_Eleve.addItem(e.getNum_ins()+":"+e.getNom()+" "+e.getPrenom());

		}
	}
	/********** cette methode avec paramettre permet de charger le combobox eleve  *******/
	public void chargerComboBoxEleve2(String str){
		comboBox_Eleve.removeAllItems();
		Vector<Eleve> eleve = new Vector<Eleve>();
		//String c=(String)(comboBox_Classe.getSelectedItem());
		Long numclass= Long.parseLong(str.substring(0,str.indexOf(":")));
		eleve =eleveDAO.findByNumClasse(numclass);
		for (Eleve e : eleve) {
			comboBox_Eleve.addItem(e.getNum_ins()+":"+e.getNom()+" "+e.getPrenom());

		}
	}
	
	/********** cette methode permet d'obtenir Classe selectionnee dans le combo box Claase  *******/
	
public static String obtenirClasseSelection(){
	 String c=(String) comboBox_Classe.getSelectedItem();
	return c;
}

	
	/********** cette methode permet d'obtenir le nom de la matiere selectionnee dans le combo box matiere  *******/
	
  String obtenir_Nom_Matiere(){
	 String c=(String) comboBox_Matière.getSelectedItem();
	  return  c= c.substring(c.indexOf(":")+1);
}

/********** cette methode permet d'obtenir le code de la matiere selectionnee dans le combo box matiere *******/

int obtenir_Code_Matiere() {
	 String c=(String) comboBox_Matière.getSelectedItem();
	   c= c.substring(0,c.indexOf(":"));
	   return  Integer.parseInt(c);
}

/********** cette methode permet d'obtenir le numero de l'eleve selectionne dans le combo box eleve *******/

Long obtenir_num_Eleve(){
	 String c=(String) comboBox_Eleve.getSelectedItem();
	 Long  x= Long.parseLong(c.substring(0,c.indexOf(":")).toString());
	   return  x;
}
	/********** cette methode permet de charger  le combo box matiere sans paramettre *******/

	public void charger_ComboBox_Matiere(){
		
		
		Vector<Matiere> vectMat = new Vector<Matiere>();
		vectMat=matiereDAO.findAll();
		for (Matiere mat : vectMat) {
			comboBox_Matière.addItem(mat.getId_matiere()+":"+mat.getNom());
		}
		
	} 
	
	

	
	/********** cette methode permet de creer une note appartir des combobox et des textfields   *******/

	 Note genererNote(){
	
		String numeleve=(String) comboBox_Eleve.getSelectedItem();
		       numeleve=numeleve.substring(0, numeleve.indexOf(":"));
	    String c=(String) comboBox_Matière.getSelectedItem();
	    c= c.substring(0, c.indexOf(":"));
	    int codemat=Integer.parseInt(c);
	    float nds= Float.parseFloat(textField_DS.getText());
	    float nexa= Float.parseFloat(textField_Examen.getText());
	     Note n=new Note(Long.parseLong(numeleve),codemat,nexa, nds);
	     return n;
 
	}
	 
	 /**************** Vider Champs permet de vider les textFileds DS et Examens ************************ */    
	 public void viderChamps(){
		 textField_DS.setText("");
		 textField_Examen.setText("");
	 }
	 
	 /********** cette methode  permet de retourne vrai Si un eleve  une note , Faux sinon  *******/
	 public boolean aUneNote(Long id){
		
		Note n= noteDAO.findNote(id,cm);
		
		if(n==null)
			 return false ; 
		/*else if(n.getDs()==0.0 && n.getExamen()==0.0)
		 return false ; */
		else
			return true;
	 }
	 
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		 frame.setBounds(100, 100, 1500, 726);
		frame.getContentPane().add(new PanelGestionNote(), BorderLayout.CENTER );
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

	}
}
