package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import classeMetier.Classe;
import classeMetier.ConseilClasse;
import classeMetier.Conseil_Instituteur;
import classeMetier.Instituteur;
import classeMetier.Salle;
import connxion_Requete.Connexion;
import dao.implement.ConseilClasseDAO;
import dao.implement.Conseil_InstituteurDAO;
import dao.implement.InstituteurDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import java.awt.ComponentOrientation;
import javax.swing.DebugGraphics;

public class PanelGestionConseilClasse extends JPanel {
	private JTextField textField_Titre;
	private JTable table_Participant;
	private JTextField txtTitreChercher;
	private JTable table_List_Conseil_Classe;
	private ConseilClasseDAO conseilClasseDAO;
	private Conseil_InstituteurDAO conseil_InstituteurDAO;
	private InstituteurDAO liste_Instituteur ;
	private JTextArea textArea_Descrip;
	private JButton btnAnnuler;
	private JLabel errTitre;
	
	private boolean  misajour=false;
	private boolean  ajout;
	private JButton btnModifier;
	private JButton btnEnregister;
	private JButton btnSupprimer;
	private JButton btnImprimer;
	private JButton btnNouveau;
	String activeajout="ajout";
	private JPanel panel_1;
	private JDateChooser dateChooser;
	
	public PanelGestionConseilClasse() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Liste des Conseils de Classe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 56, 244, 386);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 223, 304);
		panel.add(scrollPane);
		
		conseilClasseDAO= new ConseilClasseDAO();
		conseil_InstituteurDAO = new Conseil_InstituteurDAO();
		liste_Instituteur = new InstituteurDAO();
		
		
		table_List_Conseil_Classe = new JTable();
		table_List_Conseil_Classe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_List_Conseil_Classe.getTableHeader().setReorderingAllowed(false);
		table_List_Conseil_Classe.getTableHeader().setResizingAllowed(false);
		table_List_Conseil_Classe.setDefaultEditor(Object.class,null);
		
		table_List_Conseil_Classe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clique_Table_Conseil_Classe();
				dateChooser.setVisible(true);
				
			}
		});
		//charger_table_Conseil_Classe(false);
		
		table_List_Conseil_Classe.setSelectionBackground(new Color(128, 128, 128));
		
		table_List_Conseil_Classe.setRowHeight(30);
		scrollPane.setViewportView(table_List_Conseil_Classe);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 26, 224, 28);
		panel.add(panel_3);
		
		txtTitreChercher = new JTextField();
		txtTitreChercher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtTitreChercher.getText().equals("Titre à chercher"))
					txtTitreChercher.setText("");
			}
		});
		txtTitreChercher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				/*************************/
				txtTitreChercher.setForeground(Color.BLUE);
				charger_table_Conseil_Classe(true);
			}
		});
		txtTitreChercher.setToolTipText("Titre \u00E0 chercher");
		txtTitreChercher.setText("Titre \u00E0 chercher");
		txtTitreChercher.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitreChercher.setForeground(new Color(128, 128, 128));
		txtTitreChercher.setFont(new Font("Dialog", Font.ITALIC, 13));
		txtTitreChercher.setColumns(10);
		txtTitreChercher.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtTitreChercher.setBounds(1, 5, 196, 20);
		panel_3.add(txtTitreChercher);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtTitreChercher.setText("");
				charger_table_Participant_Case_Non_Coche_Coonseil_Classe();
				charger_table_Conseil_Classe(false);
			}
		});
		label.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/Red X copie.png")));
		label.setToolTipText("Vider le champ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(197, 5, 27, 20);
		panel_3.add(label);
		
		
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Information Conseil de Classe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(264, 56, 545, 386);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 70, 524, 305);
		panel_1.add(scrollPane_1);
		
		textArea_Descrip = new JTextArea();
		textArea_Descrip.setEditable(false);
		textArea_Descrip.setWrapStyleWord(true);
		textArea_Descrip.setFont(new Font("Dialog", Font.PLAIN, 14));
		textArea_Descrip.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_Descrip);
		
		JLabel lblTitre = new JLabel("<html>Titre  : <font color= 'red'> * </font></html>");
		lblTitre.setBounds(10, 24, 73, 29);
		lblTitre.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		panel_1.add(lblTitre);
		
		textField_Titre = new JTextField();
		textField_Titre.setBounds(88, 24, 154, 29);
		textField_Titre.setEditable(false);
		panel_1.add(textField_Titre);
		textField_Titre.setColumns(10);
		
		errTitre = new JLabel("");
		errTitre.setBounds(252, 24, 27, 25);
		errTitre.setVisible(false);
		errTitre.setHorizontalTextPosition(SwingConstants.CENTER);
		errTitre.setHorizontalAlignment(SwingConstants.LEFT);
		errTitre.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/error.png")));
		panel_1.add(errTitre);
		
		
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(321, 24, 50, 29);
		lblDate.setHorizontalTextPosition(SwingConstants.LEFT);
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		panel_1.add(lblDate);
		
		dateChooser = new JDateChooser();
		dateChooser.setFocusCycleRoot(true);
		dateChooser.setBounds(368, 24, 166, 29);
		dateChooser.setVisible(false);
		((JTextField)dateChooser.getDateEditor().getUiComponent()).setEditable(false);	
		dateChooser.setDateFormatString("dd/MM/yyyy");
		panel_1.add(dateChooser);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(148, 453, 843, 70);
		add(panel_2);
		panel_2.setLayout(null);
		
		btnEnregister = new JButton("Enregister");
		btnEnregister.setEnabled(false);
		btnEnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				/*----------- Si le boolean contient vrai alors c'est une mise à jour ----*/ 
				if(misajour){
					
					if (validation_Mise_A_Jour()) {
						
						if(conseilClasseDAO.update(obtenir_Saisie_Conseil_Classe()))
							JOptionPane.showMessageDialog(null, "Mise à jour effectuée !");
						misajour=false;	ajout=false;btnNouveau.setEnabled(true);
						vider_Champs();
						charger_table_Participant_Case_Non_Coche_Coonseil_Classe();
						charger_table_Conseil_Classe(false);
						activer_Buton((JButton)arg0.getSource());	
					}
					
				}else{
						if(validation_Ajouter()){
						
						ajouter_Conseil_Classe();
						misajour=false;	ajout=false;btnNouveau.setEnabled(true);
						vider_Champs();
						charger_table_Participant_Case_Non_Coche_Coonseil_Classe();
						charger_table_Conseil_Classe(false);
						activer_Buton((JButton)arg0.getSource());	
					}
				}
				
			}
		});
		btnEnregister.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/Ok-icon.png")));
		btnEnregister.setBounds(23, 24, 111, 35);
		panel_2.add(btnEnregister);
		
		btnModifier = new JButton("modifier");
		btnModifier.setEnabled(false);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				activer_Buton((JButton)e.getSource());
				misajour=true;
			}
		});
		btnModifier.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/edit.png")));
		btnModifier.setBounds(154, 24, 111, 35);
		panel_2.add(btnModifier);
		
		btnNouveau = new JButton("nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table_List_Conseil_Classe.clearSelection();
				vider_Champs();
				ajout=true;
				misajour=false;
				textField_Titre.setEditable(true);
				textArea_Descrip.setEditable(true);
				charger_table_Participant_Case_Non_Coche_Coonseil_Classe();
				activer_Buton((JButton)e.getSource());
				
			}
		});
		btnNouveau.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/Button-Add-icon.png")));
		btnNouveau.setBounds(429, 24, 111, 35);
		panel_2.add(btnNouveau);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setEnabled(false);
		btnSupprimer.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/Red X copie.png")));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Voulez-Vous supprimer ce Conseil de Classe ?")== JOptionPane.OK_OPTION){
					if(conseilClasseDAO.delete(obtenir_Selection_Conseil()))
						JOptionPane.showMessageDialog(null, "Le conseil de Classe supprimé avec succès !");
					// Raffrachir la table conseil Classe
					charger_table_Conseil_Classe(false);
					// Raffraichir la table des Participants
					charger_table_Participant_Case_Non_Coche_Coonseil_Classe();
					vider_Champs();
					
				}
			}
		});
		btnSupprimer.setBounds(561, 24, 111, 35);
		panel_2.add(btnSupprimer);
		
		btnImprimer = new JButton("imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				print();
			}
		});
		btnImprimer.setEnabled(false);
		btnImprimer.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/print-icon.png")));
		btnImprimer.setBounds(695, 24, 111, 35);
		panel_2.add(btnImprimer);
		
		btnAnnuler = new JButton("annuler");
		btnAnnuler.setEnabled(false);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activer_Buton((JButton)e.getSource());
				vider_Champs();	misajour=false;	ajout=false;
				charger_table_Participant_Case_Non_Coche_Coonseil_Classe();
				charger_table_Conseil_Classe(false);
			}
		});
		btnAnnuler.setIcon(new ImageIcon(PanelGestionConseilClasse.class.getResource("/image/Fairytale_undo.png")));
		btnAnnuler.setBounds(292, 24, 111, 35);
		panel_2.add(btnAnnuler);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Liste des Participants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(819, 56, 282, 386);
		add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 21, 262, 345);
		panel_4.add(scrollPane_2);
		
		
		table_Participant = new JTable();
	//	charger_table_Participant_Case_Non_Coche_Coonseil_Classe();
		table_Participant.setSelectionBackground(new Color(128, 128, 128));
		
		/*Single line selection , column not modifiable*/
		scrollPane_2.setViewportView(table_Participant);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 44, 1079, 11);
		add(separator);
		
		JLabel lblGestionDeConseils = new JLabel("Gestion de Conseils de Classe");
		lblGestionDeConseils.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblGestionDeConseils.setBounds(26, 11, 264, 34);
		add(lblGestionDeConseils);
		
		charger_table_Conseil_Classe(false);
		dateChooser.revalidate();
		dateChooser.repaint();
		revalidate();
	}
	
	
	/* Cette methode permet de charger la table de Coonseil  de Classe
	 * Si variable contient vrai alors C'est une Recherche sinon on charge tou les conseil dans la base 
	 */
	public void charger_table_Conseil_Classe(boolean charger_Recherche_Ou_tous_Conseil){
			
		DefaultTableModel  modelConseilClasse= new DefaultTableModel();
		modelConseilClasse.addColumn("id_Conseil");
		modelConseilClasse.addColumn("Titre");
		modelConseilClasse.addColumn("Date");
		modelConseilClasse.addColumn("Description");
		
		Vector<ConseilClasse> vectorConseilClasse;
		if(charger_Recherche_Ou_tous_Conseil)
			vectorConseilClasse =  conseilClasseDAO.findby_titre_Collection("%"+txtTitreChercher.getText()+"%");
		else
			vectorConseilClasse= conseilClasseDAO.findAll();
		
		for (ConseilClasse conseilClasse : vectorConseilClasse) {
			modelConseilClasse.addRow(new Object[]{conseilClasse.getId_Conseil(),conseilClasse.getTitre_Conseil(),conseilClasse.getDate_Conseil(),conseilClasse.getDescription()});	
		}
		
		table_List_Conseil_Classe.setModel(modelConseilClasse);dateChooser.setVisible(true);
		changer_Propriete_Table_Conseil_Classe();
		
		
	}
	
	// Cette methode permet de remplir les champs pour le conseil de classe utile lorsque qun'on clique la table conseil Classe 
	public void remplir_Champs_Pour_Conseil(ConseilClasse  conseilClasse){
		textField_Titre.setText(conseilClasse.getTitre_Conseil());
		textArea_Descrip.setText(conseilClasse.getDescription());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date_Naissance=null;
				
		try {
			date_Naissance = dateFormat.parse(conseilClasse.getDate_Conseil());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateChooser.setDate(date_Naissance);
	}
	
	public void vider_Champs(){
		textArea_Descrip.setText("");
		textField_Titre.setText("");
		((JTextField)dateChooser.getDateEditor().getUiComponent()).setText("");	
		textArea_Descrip.setEditable(false);
		textField_Titre.setEditable(false);
	}
	// Cette methode de retourner les information saisie dans les champs de conseil de classe
	
	public ConseilClasse obtenir_Saisie_Conseil_Classe(){
		
		ConseilClasse conseilClasse = new ConseilClasse();
		String date=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
		conseilClasse.setDate_Conseil(date);
		conseilClasse.setId_Conseil(obtenir_Selection_Conseil().getId_Conseil());
		conseilClasse.setTitre_Conseil(textField_Titre.getText());
		conseilClasse.setDescription(textArea_Descrip.getText());
		return conseilClasse;
	}
	
	// Cette methode de retourner les information saisie dans les champs de conseil de classe
	
		public ConseilClasse obtenir_Saisie_Conseil_Classe_Ajout(){
			
			ConseilClasse conseilClasse = new ConseilClasse();
			String date=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
			//conseilClasse.setId_Conseil(obtenir_Selection_Conseil().getId_Conseil());
			conseilClasse.setDate_Conseil(date);
			conseilClasse.setTitre_Conseil(textField_Titre.getText());
			conseilClasse.setDescription(textArea_Descrip.getText());
			return conseilClasse;
		}
	
	/*------------------ la methode pour charger la table  des Participant  avec aucune case coché-------------*/
	public void charger_table_Participant_Case_Non_Coche_Coonseil_Classe(){
		
			
			Vector<Instituteur> vec = liste_Instituteur.findAll();
			String[] columns = {"Id_Participant", "Nom", "Prénom","Assisté"}; 
			Object[][] data= new Object[vec.size()][4];
			for (int i = 0; i < data.length; i++) {
				data[i][0]=vec.get(i).getId();
				data[i][1]=vec.get(i).getNom();
				data[i][2]=vec.get(i).getPrenom();
				data[i][3]=Boolean.FALSE;
			}
			
			table_Participant.setModel(new BooleanTableModel(columns,data));
			changer_Propriete_Table_Participant();
		} 
	
	/*------------------ la methode pour charger la table  des Participant -------------*/
	public void charger_table_Participant_Coonseil_Classe(){
		
			
			Vector<Instituteur> liste_Tous_lesInstituteurs = liste_Instituteur.findAll();
			Vector<Conseil_Instituteur> conseil_Classe_Donnee = new Vector<>();
			
			/* Cici Conserne le conseil de Classe Selectionné sur la table */
			conseil_Classe_Donnee = conseil_InstituteurDAO.findCollection(obtenir_Selection_Conseil().getId_Conseil().toString());
			
			/* Cette liste contient tous les indentifiant des participant d'un conseil de Classe Donnée */
			ArrayList<Long> liste_Indentifiant_Participant = new ArrayList<>();
			
			for (Conseil_Instituteur conseil_Classe: conseil_Classe_Donnee) {
				liste_Indentifiant_Participant.add(conseil_Classe.getId_inst());
			}
			
			String[] columns = {"Id_Participant", "Nom", "Prénom","Assisté"}; 
			Object[][] data= new Object[liste_Tous_lesInstituteurs.size()][4];
			
			for (int i = 0; i < data.length; i++) {
				data[i][0]=liste_Tous_lesInstituteurs.get(i).getId();
				data[i][1]=liste_Tous_lesInstituteurs.get(i).getNom();
				data[i][2]=liste_Tous_lesInstituteurs.get(i).getPrenom();
				if(liste_Indentifiant_Participant.contains(liste_Tous_lesInstituteurs.get(i).getId()))
					data[i][3]=Boolean.TRUE;
				else
					data[i][3]=Boolean.FALSE;
			}
			BooleanTableModel mamodel = new BooleanTableModel(columns,data);
			mamodel.addTableModelListener(new TableModelListener() {
				
				@Override
				public void tableChanged(TableModelEvent arg0) {
					System.out.println("Bonjour");
					
				}
			});
			table_Participant.setModel(mamodel);
			
			changer_Propriete_Table_Participant();
		} 
	
	/* -------------Cette methode permet de faire la mise des propriete de la table Participant ----------*/
	
	public void changer_Propriete_Table_Participant(){
		
		table_Participant.getColumnModel().getColumn(0).setMinWidth(0);
		table_Participant.getColumnModel().getColumn(0).setMaxWidth(0);
		table_Participant.getColumnModel().getColumn(0).setWidth(0);
		
		table_Participant.getColumnModel().getColumn(3).setMinWidth(50);
		table_Participant.getColumnModel().getColumn(3).setMaxWidth(50);
		table_Participant.getColumnModel().getColumn(3).setWidth(50);
		table_Participant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*Jtable not draggble , not resizble*/
		table_Participant.getTableHeader().setReorderingAllowed(false);
		table_Participant.getTableHeader().setResizingAllowed(false);
		//table_Participant.setDefaultEditor(Object.class,null);
		table_Participant.setRowHeight(30);
	}
	
	/* -------------Cette methode permet de faire la mise des propriete de la table Conseil Classe ----------*/
	public void changer_Propriete_Table_Conseil_Classe(){
		
		table_List_Conseil_Classe.getColumnModel().getColumn(0).setMinWidth(0);
		table_List_Conseil_Classe.getColumnModel().getColumn(0).setMaxWidth(0);
		table_List_Conseil_Classe.getColumnModel().getColumn(0).setWidth(0);
		table_List_Conseil_Classe.getColumnModel().getColumn(3).setMinWidth(0);
		table_List_Conseil_Classe.getColumnModel().getColumn(3).setMaxWidth(0);
		table_List_Conseil_Classe.getColumnModel().getColumn(3).setWidth(0);
		table_List_Conseil_Classe.getColumnModel().getColumn(2).setMinWidth(75);
		table_List_Conseil_Classe.getColumnModel().getColumn(2).setMaxWidth(75);
		table_List_Conseil_Classe.getColumnModel().getColumn(2).setWidth(75);
	}
	
	/* Cette methode permet de parcourir la table participatnt
	 *  retourner une liste contenant les indentifiants des 
	 *  instituteur dont leur champs Assisté est coché */
	
	public ArrayList<Long> list_Indentifiant_Participant(){
		ArrayList<Long> liste_Participant =new ArrayList<>();
		for (int i = 0; i < table_Participant.getRowCount(); i++) {
			if((Boolean)table_Participant.getValueAt(i,3))
				liste_Participant.add((Long)table_Participant.getValueAt(i,0));
		}
		return liste_Participant;
	}
	
	
	/* ------------ Cette methode permet de retourner le conseil selectionner sur la table ------------*/
	
	public ConseilClasse obtenir_Selection_Conseil(){
		
		ConseilClasse conseilClasse = new ConseilClasse();
		conseilClasse.setId_Conseil((Long)table_List_Conseil_Classe.getValueAt(table_List_Conseil_Classe.getSelectedRow(), 0));
		conseilClasse.setTitre_Conseil((String)table_List_Conseil_Classe.getValueAt(table_List_Conseil_Classe.getSelectedRow(), 1));
		conseilClasse.setDate_Conseil((String)table_List_Conseil_Classe.getValueAt(table_List_Conseil_Classe.getSelectedRow(), 2));
		conseilClasse.setDescription((String)table_List_Conseil_Classe.getValueAt(table_List_Conseil_Classe.getSelectedRow(), 3));
		return conseilClasse;
	}
	
	/* ----------- Cette methode permet de vamidation de la saisies ----------*/
	public boolean validation_Ajouter(){
		
		boolean bol = true;
		
		/* ------------Verification du titre ---------*/
		if(textField_Titre.getText().equals("")){
			errTitre.setVisible(true);
			errTitre.setToolTipText("Titre Obligatoire");
			bol=false;	
		}else if(conseilClasseDAO.findby_titre(textField_Titre.getText())!=null){
			errTitre.setVisible(true);
			errTitre.setToolTipText("Desolé ce titre existe déjà");
			bol=false;	
		}	else
				errTitre.setVisible(false);
		/*----------Verification de la description --------------*/
		 
		if (textArea_Descrip.getText().equals("")) {
			if(JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter un conseil sans description ?")!=JOptionPane.OK_OPTION)
				bol=false;
		}
		return bol;	
	}
	/* ----------- Cette methode permet de validation de la saisies ----------*/
	public boolean validation_Mise_A_Jour(){
		
		boolean bol = true;
		
		/* ------------Verification du titre ---------*/
		if(textField_Titre.getText().equals("")){
			errTitre.setVisible(true);
			errTitre.setToolTipText("Titre Obligatoire");
			bol=false;	
			
		}else if(!textField_Titre.getText().equals(obtenir_Selection_Conseil().getTitre_Conseil())){
			
			if(conseilClasseDAO.findby_titre(textField_Titre.getText())!=null){
				
				errTitre.setVisible(true);
				errTitre.setToolTipText("Desolé ce titre existe déjà");
				bol=false;	
			}else
				errTitre.setVisible(false);
			
		}	
		/*----------Verification de la description --------------*/
		 
		if (textArea_Descrip.getText().equals("")) {
			if(JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter un conseil sans description ?")!=JOptionPane.OK_OPTION)
				bol=false;
		}
		return bol;	
	}
	
	/*--------------- Cette methode es appelé lors de l'ajout d'un conseil  de classe ---------------*/
	public void ajouter_Conseil_Classe(){
		
		if(conseilClasseDAO.create(obtenir_Saisie_Conseil_Classe_Ajout())){
			
			ConseilClasse conseilClasse =obtenir_Saisie_Conseil_Classe_Ajout();
			Conseil_Instituteur conseil_Instituteur; 
			
			Long id_Conseil = conseilClasseDAO.findby_titre(conseilClasse.getTitre_Conseil()).getId_Conseil();
			// Cette liste contient les case coché des participant
			ArrayList<Long> list_Indentifiant_Participant =list_Indentifiant_Participant();
			
			for (Long iterable_element : list_Indentifiant_Participant) {
				conseil_Instituteur =new Conseil_Instituteur(id_Conseil,iterable_element);
				conseil_InstituteurDAO.create(conseil_Instituteur);
				
			}
			JOptionPane.showMessageDialog(null, "Succes");
		}
	}
	
	public void clique_Table_Conseil_Classe(){
		btnModifier.setEnabled(true);
		btnImprimer.setEnabled(true);
		btnSupprimer.setEnabled(true);
		btnNouveau.setEnabled(true);
		btnAnnuler.setEnabled(false);
		btnEnregister.setEnabled(false);
		errTitre.setVisible(false); misajour=false; ajout=false;
		txtTitreChercher.setForeground(new Color(128, 128, 128));
		txtTitreChercher.setText("Titre à chercher");
		remplir_Champs_Pour_Conseil(obtenir_Selection_Conseil());
		charger_table_Participant_Coonseil_Classe();
	}
	
	public void activer_Buton(JButton button){
		if(button.getLabel().equals("Enregister")){
			btnEnregister.setEnabled(false);
			btnAnnuler.setEnabled(false);
			btnNouveau.setEnabled(true);
			btnImprimer.setEnabled(false);
			btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			textArea_Descrip.setEditable(false);
			textField_Titre.setEditable(false);	
		}else if(button.getLabel().equals("nouveau") || button.getLabel().equals("modifier")){
			btnEnregister.setEnabled(true);
			btnAnnuler.setEnabled(true);
			btnNouveau.setEnabled(false);
			btnImprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			btnSupprimer.setEnabled(false);
			textArea_Descrip.setEditable(true);
			textField_Titre.setEditable(true);
		}else if(button.getLabel().equals("annuler")){
			btnEnregister.setEnabled(false);
			btnAnnuler.setEnabled(false);
			btnImprimer.setEnabled(false);
			btnSupprimer.setEnabled(false);
			btnModifier.setEnabled(false);
			textArea_Descrip.setEditable(false);
			textField_Titre.setEditable(false);	
		}
	}
	
	/*----------------------- Cette method permet de faire Impression ----------------------------*/
	public void print(){

		org.apache.log4j.BasicConfigurator.configure();
		Map param = new HashMap();
		Integer  Id_Conseil_Classe=Integer.parseInt(obtenir_Selection_Conseil().getId_Conseil().toString());
		param.put("Id_Conseil_Classe", Id_Conseil_Classe);
		param.put("id_Des", Id_Conseil_Classe);
		try{
			 
			JasperReport report = JasperCompileManager.compileReport("Rapport\\Rapport_Conseil_Classe.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, param, new Connexion().getConnection());
			JasperViewer.viewReport(print,false);
	        
	        }
		 catch(Exception e)
	    {
	           JOptionPane.showMessageDialog(null, e);

	    }

	}
	
	/* -------Cette classe dest une classe interne qui nous permet d'avoir des cases à cocher au niveau de notre table ------*/
	class BooleanTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String[] columns = null;
		Object[][] data =null;
		
		public BooleanTableModel(String[] columns, Object[][] data) {
			super();
			this.columns = columns;
			this.data = data;
		}
		public int getRowCount() { 
			return data.length;
			} 
		public int getColumnCount() {
			return columns.length;
			} 
		@Override 
		public boolean isCellEditable(int rowIndex,int columnIndex){
			if(columnIndex==3 && (misajour || ajout)) 
			return true;
			else
			return false;
		}	
		@Override
		public void setValueAt(Object value,int rowindex,int columnidex){
			if(columnidex==3){
				data[rowindex][columnidex]=value;
				// Si c'est une mise à jour on modifie directement
				if(misajour){
					
					Conseil_Instituteur  conseil_Instituteur=new Conseil_Instituteur();
					conseil_Instituteur.setId_Conseil(obtenir_Selection_Conseil().getId_Conseil());
					conseil_Instituteur.setId_inst((Long)table_Participant.getValueAt(rowindex, 0));
					if((Boolean)value)
						conseil_InstituteurDAO.create(conseil_Instituteur);
					else
						conseil_InstituteurDAO.delete(conseil_Instituteur);	

				}
			}
				
			fireTableCellUpdated(rowindex, columnidex);
		}
		public Object getValueAt(int rowIndex, int columnIndex){	
			return data[rowIndex][columnIndex]; }
		@Override 
		public String getColumnName(int column) {
			return columns[column]; 
		} 
		@Override
		public Class<?> getColumnClass(int columnIndex) { 
			return data[0][columnIndex].getClass(); 
			}
		} 
		
	
		public static void main(String[] args) {
			try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		JFrame frame = new JFrame("PanelGestionConseilClasse");
		 frame.getContentPane().setLayout(new BorderLayout());
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setBounds(100, 100, 1500, 726);
		 frame.setLocationRelativeTo(null);
		 frame.getContentPane().add(new PanelGestionConseilClasse(), BorderLayout.CENTER );
		 frame.setVisible(true);
	}
}
