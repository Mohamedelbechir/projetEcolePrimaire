package view;

import java.awt.LayoutManager;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import classeMetier.Classe;
import classeMetier.Instituteur;
import classeMetier.Niveau;
import connxion_Requete.Connexion;
import dao.implement.ClasseDAO;
import dao.implement.InstituteurDAO;
import dao.implement.NiveauDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.ComponentOrientation;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelGestionClasse1 extends JPanel {
	private JTextField txtDonnerLeNom;
	private JComboBox comboBox_Classe;
	private JTextField textField_NomClasse;
	private JLabel lblFgg;
	private JLabel label_Modification;
	private JLabel label_Suppression;
	private JTable table_Classe;
	private ClasseDAO classeDAO ;
	private NiveauDAO niveauDAO;
	private JLabel errNom;
	private JPanel panel_Opreation_Valider;
	private JLabel label_valider;
	private JLabel label_Annuller;
	private JPanel panel_Operation;
	private DefaultTableCellRenderer centerRender;
	private JLabel errCombo;
	public PanelGestionClasse1() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		classeDAO=new ClasseDAO();
		niveauDAO=new NiveauDAO();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Liste des Classes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 56, 452, 467);
		add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 432, 394);
		panel.add(scrollPane);
		
		table_Classe = new JTable();
		table_Classe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDonnerLeNom.setText("Donner le nom de la Classe à chercher");
				txtDonnerLeNom.setForeground(new Color(128, 128, 128));
				remove(panel_Opreation_Valider);
				
				add(panel_Operation);
				errNom.setVisible(false);
				errCombo.setVisible(false);
				repaint();
				activer_champs(false);
		
				chargerClasse_Champs(getSelectedClasse());
			}
		});
		
		

		table_Classe.setSelectionBackground(new Color(128, 128, 128));
		table_Classe.setRowHeight(30);
		table_Classe.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		table_Classe.getTableHeader().setReorderingAllowed(false);
        table_Classe.getTableHeader().setResizingAllowed(false);
		table_Classe.setDefaultEditor(Object.class,null);
		table_Classe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_Classe);
		
		centerRender= new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		
		fillJTable();
		
	
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(11, 23, 297, 28);
		panel.add(panel_1);
		
		txtDonnerLeNom = new JTextField();
		txtDonnerLeNom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(txtDonnerLeNom.getText().equals("Donner le nom de la Classe à chercher"))
					txtDonnerLeNom.setText("");
			}
		});
		txtDonnerLeNom.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDonnerLeNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtDonnerLeNom.setForeground(Color.BLUE);
				collection_Classe_Recherche();
			}
		});
		txtDonnerLeNom.setForeground(new Color(128, 128, 128));
		txtDonnerLeNom.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonnerLeNom.setText("Donner le nom de la Classe \u00E0 chercher");
		txtDonnerLeNom.setToolTipText("Rechercher un Salle");
		txtDonnerLeNom.setColumns(10);
		txtDonnerLeNom.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtDonnerLeNom.setBounds(1, 5, 250, 20);
		panel_1.add(txtDonnerLeNom);
		
		JLabel label_effacerCadeRecherche = new JLabel("");
		label_effacerCadeRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDonnerLeNom.setText(null);
				
			}
		});
		label_effacerCadeRecherche.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/Red X copie.png")));
		label_effacerCadeRecherche.setToolTipText("Vider le champ");
		label_effacerCadeRecherche.setHorizontalAlignment(SwingConstants.CENTER);
		label_effacerCadeRecherche.setBounds(261, 5, 26, 20);
		panel_1.add(label_effacerCadeRecherche);
		
		panel_Operation = new JPanel();
		panel_Operation.setLayout(null);
		panel_Operation.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Operation.setBounds(472, 448, 370, 75);
		add(panel_Operation);
		
		panel_Opreation_Valider =new JPanel();
		panel_Opreation_Valider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Validation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Opreation_Valider.setBounds(472, 448, 370, 75);
		//add(panel_Opreation_Valider);
		panel_Opreation_Valider.setLayout(null);
		
		label_valider = new JLabel("");
		label_valider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(validation_Ajout_Mise_Jour(false)){
					
					if(classeDAO.update(obtenir_Saisi_Classe())){
						
						JOptionPane.showMessageDialog(null, "Miseà jour effectuée avec succès");
						table_Classe.clearSelection();
						remove(panel_Opreation_Valider);
						add(panel_Operation);
						repaint();
						vider_Champs();	
						activer_champs(true);
						fillJTable();
					}
					
				}
			}
		});
		label_valider.setHorizontalAlignment(SwingConstants.CENTER);
		
	
		label_valider.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/Ok-icon.png")));
		label_valider.setBounds(10, 11, 83, 51);
		panel_Opreation_Valider.add(label_valider);
		
		label_Annuller = new JLabel("");
		label_Annuller.setHorizontalAlignment(SwingConstants.CENTER);
		label_Annuller.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remove(panel_Opreation_Valider);
				add(panel_Operation);
				repaint();
				vider_Champs();		
				}
		});
		label_Annuller.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/Button-Close-icon.png")));
		label_Annuller.setBounds(110, 11, 73, 51);
		panel_Opreation_Valider.add(label_Annuller);
		lblFgg = new JLabel("");
		lblFgg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					if(table_Classe.getSelectedRow()!=-1){
						table_Classe.clearSelection();
						JOptionPane.showMessageDialog(null, "Saisir les données de Classes");
						vider_Champs();
					}else{
						if(validation_Ajout_Mise_Jour(true)){
					
						if(classeDAO.create(obtenir_Saisi_Classe_Ajout())){
							JOptionPane.showMessageDialog(null, "La Classe est crée avec succès");
							vider_Champs();
							fillJTable();
						}else
							
							JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout");;
						
						
					}
					}
					
			}
		});
		lblFgg.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/plus.png")));
		lblFgg.setHorizontalAlignment(SwingConstants.CENTER);
		lblFgg.setBounds(22, 11, 73, 53);
		panel_Operation.add(lblFgg);
		
		label_Modification = new JLabel("");
		label_Modification.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(table_Classe.getSelectedRow()!=-1){
					JOptionPane.showMessageDialog(null, "Saisir les nouvelles informations");
					activer_champs(true);
					chargerClasse_Champs(getSelectedClasse());
					remove(panel_Operation);
					repaint();
					add(panel_Opreation_Valider);	
				}else
					JOptionPane.showMessageDialog(null, "Veuillez Choisir la Classe à Modifier !");
			
			}
		});
		label_Modification.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/edit.png")));
		label_Modification.setHorizontalAlignment(SwingConstants.CENTER);
		label_Modification.setBounds(112, 11, 73, 53);
		panel_Operation.add(label_Modification);
		
		label_Suppression = new JLabel("");
		label_Suppression.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int a=JOptionPane.showConfirmDialog(null,"Voulez vous supprimer cette Classe?");  
				  if(a==JOptionPane.YES_OPTION){  
						if(classeDAO.delete(getSelectedClasse()))
							JOptionPane.showMessageDialog(null, "Classe supprimer avec succès !");
						else
							JOptionPane.showMessageDialog(null, "Erreur de la suppression ");;
				  }
				vider_Champs();  
				fillJTable();
			}
		});
		label_Suppression.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/Button-Close-icon.png")));
		label_Suppression.setHorizontalAlignment(SwingConstants.CENTER);
		label_Suppression.setBounds(196, 11, 73, 53);
		panel_Operation.add(label_Suppression);
		
		JLabel lblAjouter = new JLabel("Ajouter");
		lblAjouter.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter.setBounds(22, 50, 73, 14);
		panel_Operation.add(lblAjouter);
		
		JLabel lblModifier = new JLabel("Modifier");
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblModifier.setBounds(112, 50, 73, 14);
		panel_Operation.add(lblModifier);
		
		JLabel lblSupprimer = new JLabel("Supprimer");
		lblSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupprimer.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblSupprimer.setBounds(181, 50, 108, 14);
		panel_Operation.add(lblSupprimer);
		
		JLabel label_Netooyer = new JLabel("");
		label_Netooyer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table_Classe.clearSelection();
				vider_Champs();
			}
		});
		label_Netooyer.setHorizontalAlignment(SwingConstants.CENTER);
		label_Netooyer.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/clear.png")));
		label_Netooyer.setBounds(292, 11, 73, 53);
		panel_Operation.add(label_Netooyer);
		
		JLabel lblVider = new JLabel("Nettoyer");
		lblVider.setHorizontalAlignment(SwingConstants.CENTER);
		lblVider.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblVider.setBounds(292, 50, 73, 14);
		panel_Operation.add(lblVider);
		
		JLabel label_NetooyerpourMise = new JLabel("");
		label_NetooyerpourMise.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vider_Champs();
			}
		});
		label_NetooyerpourMise.setHorizontalAlignment(SwingConstants.CENTER);
		label_NetooyerpourMise.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/clear.png")));
		label_NetooyerpourMise.setBounds(199, 11, 73, 53);
		panel_Opreation_Valider.add(label_NetooyerpourMise);
		
		JLabel lblViderpourMise = new JLabel("Nettoyer");
		lblViderpourMise.setHorizontalAlignment(SwingConstants.CENTER);
		lblViderpourMise.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblViderpourMise.setBounds(199, 48, 73, 14);
		panel_Opreation_Valider.add(lblViderpourMise);
		
		JLabel lblValider = new JLabel("Valider");
		lblValider.setHorizontalAlignment(SwingConstants.CENTER);
		lblValider.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblValider.setBounds(10, 48, 90, 14);
		panel_Opreation_Valider.add(lblValider);
		
		JLabel lblAnnuler = new JLabel("Annuler");
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAnnuler.setBounds(116, 48, 67, 14);
		panel_Opreation_Valider.add(lblAnnuler);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(873, 448, 158, 75);
		add(panel_3);
		
		JButton button = new JButton("<html><center>Liste des <br>Classes</center></html>");
		button.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/print-icon.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				print();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(10, 21, 138, 43);
		panel_3.add(button);
		
		JPanel panel_Information_Classe = new JPanel();
		panel_Information_Classe.setLayout(null);
		panel_Information_Classe.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informations de la Classe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Information_Classe.setBounds(472, 56, 559, 381);
		add(panel_Information_Classe);
		
		JLabel label_5 = new JLabel("<html>Nom Classe : <font color= 'red'> * </font></html>");
		label_5.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_5.setBounds(41, 129, 137, 29);
		panel_Information_Classe.add(label_5);
		
		textField_NomClasse = new JTextField();
		textField_NomClasse.setColumns(10);
		textField_NomClasse.setBounds(188, 129, 256, 29);
		panel_Information_Classe.add(textField_NomClasse);
		
		JLabel label_6 = new JLabel("<html>Niveau : <font color= 'red'> * </font></html>");
		label_6.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_6.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		label_6.setBounds(41, 202, 137, 27);
		panel_Information_Classe.add(label_6);
		
		comboBox_Classe = new JComboBox();
		comboBox_Classe.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
	
		Vector<Niveau> vectoNiveau =  niveauDAO.findAll();
		for (Niveau niveau : vectoNiveau) {
				 comboBox_Classe.addItem(niveau.getNom_Niveau());
		}
		comboBox_Classe.setBounds(188, 200, 256, 29);
		panel_Information_Classe.add(comboBox_Classe);
		
		errNom = new JLabel("");
		errNom.setVisible(false);
		errNom.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/error.png")));
		errNom.setBounds(454, 129, 25, 29);
		panel_Information_Classe.add(errNom);
		
		errCombo = new JLabel("");
		errCombo.setVisible(false);
		errCombo.setToolTipText("choisir le niveau");
		errCombo.setIcon(new ImageIcon(PanelGestionClasse1.class.getResource("/image/error.png")));
		errCombo.setBounds(454, 202, 25, 27);
		panel_Information_Classe.add(errCombo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 44, 1079, 11);
		add(separator);
		
		JLabel lblGestionDesClasses = new JLabel("Gestion des Classes");
		lblGestionDesClasses.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblGestionDesClasses.setBounds(26, 11, 252, 34);
		add(lblGestionDesClasses);
	}

	/*----------------- Cette methode permet de charger la table suite à une recherche----------------*/
	public void collection_Classe_Recherche(){
		DefaultTableModel  modeleClasse= new DefaultTableModel();
		modeleClasse.addColumn("NUM");
		modeleClasse.addColumn("Nom de la Classe");
		modeleClasse.addColumn("Niveau de la Classe");
		Vector<Classe> po = new Vector<Classe>();
		po=classeDAO.findCollection(txtDonnerLeNom.getText());
		for (Classe cl : po) {
			modeleClasse.addRow(new Object[]{cl.getNum_Clase(),cl.getNom_Clase(),cl.getNiveau_Classe()});
			
		}
		table_Classe.setModel(modeleClasse);
		proprietetable();
	}
	
	
	/* -------------------Cette methode permet de charger la table -------------*/
	public void fillJTable(){
		DefaultTableModel  modeleClasse= new DefaultTableModel();
		modeleClasse.addColumn("NUM");
		modeleClasse.addColumn("NOM DE LA CLASSE");
		modeleClasse.addColumn("NIVEAU DE LA CLASSE");
		Vector<Classe> po = new Vector<Classe>();
		po=classeDAO.findAll();
		
		for (Classe cl : po) {
			modeleClasse.addRow(new Object[]{cl.getNum_Clase(),cl.getNom_Clase(),niveauDAO.find(cl.getNiveau_Classe()).getNom_Niveau()});
			
		}
		table_Classe.setModel(modeleClasse);
		proprietetable();
	}
	
	public void proprietetable(){
		table_Classe.getColumnModel().getColumn(0).setMinWidth(0);
		table_Classe.getColumnModel().getColumn(0).setMaxWidth(0);
		table_Classe.getColumnModel().getColumn(0).setWidth(0);
		table_Classe.getColumnModel().getColumn(1).setCellRenderer(centerRender);
		table_Classe.getColumnModel().getColumn(2).setCellRenderer(centerRender);
		table_Classe.getTableHeader().setDefaultRenderer(centerRender);
	}
	/* ------------------------Cette methode permet de vider le contenu les champs----------------*/
	
	public void vider_Champs(){
		
		
		textField_NomClasse.setText("");
		textField_NomClasse.setEditable(true);
		errNom.setVisible(false);
		comboBox_Classe.setEnabled(true);
		comboBox_Classe.setSelectedItem(null);
	}
	
	
	/*---------- Cette methode permet de retourner une instande Classe ( selectionner sur le tableau) ---------*/
	public Classe getSelectedClasse(){
		Classe c=new Classe();
		c.setNum_Clase((Long)table_Classe.getValueAt(table_Classe.getSelectedRow(), 0));
		c.setNom_Clase((String)table_Classe.getValueAt(table_Classe.getSelectedRow(), 1)); 
		//c.setNiveau_Classe((String)table_Classe.getValueAt(table_Classe.getSelectedRow(),2));  
		c.setNiveau_Classe(classeDAO.find(c.getNum_Clase()).getNiveau_Classe());  
		return c;
	}
	
	
	/*-------- Ctte methode permet de créer une instance de Classe utile pour la mise à jour d'une Classe------*/
	public Classe obtenir_Saisi_Classe(){
		
		Classe classe= getSelectedClasse();
		Niveau niveau = new Niveau();
		//Long num_Clase =getSelectedClasse().getNum_Clase();
		String nom_niveau = (String)comboBox_Classe.getSelectedItem();
		String name = textField_NomClasse.getText();
		//classe.setNum_Clase(num_Clase);
		classe.setNom_Clase(name);
		niveau = niveauDAO.findBy_Nom(nom_niveau);
		classe.setNiveau_Classe(niveau.getId_Niveau());
		
		return classe;
	}
	/*-------- Ctte methode permet de créer une instance de Classe utile pour l'ajout d'une Classe------*/
	public Classe obtenir_Saisi_Classe_Ajout(){
		
		
		String name = textField_NomClasse.getText();
		String nom_niveau = (String)comboBox_Classe.getSelectedItem();
		//classe.setNum_Clase(num_Clase);
		Niveau niveau = niveauDAO.findBy_Nom(nom_niveau);
		return new Classe(name,niveau.getId_Niveau());
	}
	
	/* ----------------- Cette methode permet de charger les information d'une classe dans le panel de la modification -------*/
	@SuppressWarnings("unchecked")
	public void chargerClasse_Champs(Classe c ){
		
		textField_NomClasse.setText(c.getNom_Clase());
		comboBox_Classe.setSelectedItem(niveauDAO.find(c.getNiveau_Classe()).getNom_Niveau());
		
	}
	
	
	
	
	
	/*--------- Cette methode permet de faire la verifie=cation des champs pour l'ajout ou mise à jour d'une Classe ----*/
	public boolean validation_Ajout_Mise_Jour(boolean validation){
		boolean bol= true;
		// Si la validation = true alors il s'agit d'un ajout
		
		// Traitement du cas d'ajout
		if(validation){
			
			
			/* --------------------Verification  du nom de la Salle -----------*/
			if(comboBox_Classe.getSelectedItem()==null){
				errCombo.setVisible(true);
				bol=false;
			}else
				errCombo.setVisible(false);
			if(classeDAO.findByNom(textField_NomClasse.getText())!=null){
				
				errNom.setVisible(true);
				errNom.setToolTipText("Ce nom appartient à une autre Classe");
				bol=false;		
			}else
			if(textField_NomClasse.getText().equals("")){
				
				errNom.setVisible(true);
				errNom.setToolTipText("le nom de Classe ne doit pas être null");
				bol=false;
				}
				else
					errNom.setVisible(false);
			
		}else{
			
			// Si c'est de mise à jour verifie seulement le nom de la Classe
			if(comboBox_Classe.getSelectedItem()==null){
				errCombo.setVisible(true);
				bol=false;
			}else
				errCombo.setVisible(false);
			/* --------------------Verification  du nom de la Classe -----------*/
			if(textField_NomClasse.getText().length()>20 ){
				errNom.setVisible(true);
				errNom.setToolTipText("Chaine trop longue");
				bol=false;
			}
			String nom_Niveau_New= comboBox_Classe.getSelectedItem().toString();
			String nom_Niveau_old=(String)table_Classe.getValueAt(table_Classe.getSelectedRow(),2);
			//||(!(nom_Niveau_New.equals(nom_Niveau_old)))
			if((!(getSelectedClasse().getNom_Clase().equals(textField_NomClasse.getText())))  ){
				if(classeDAO.findByNom(textField_NomClasse.getText())!=null){
					errNom.setVisible(true);
					errNom.setToolTipText("Ce nom appartient à une autre Classe");
					bol=false;
				}else
					errNom.setVisible(false);
				// Le champ Nom de la Classe doit  être rempli	
			}else
			if(textField_NomClasse.getText().equals(""))
			{
				errNom.setVisible(true);
				errNom.setToolTipText("le nom de Classe ne doit pas être null");
				bol=false;
			}
			else
				errNom.setVisible(false);
		}
		return bol;
	}
	
	
	/*----------------------- Cette method permet de faire Impression ----------------------------*/
	public void print(){

		org.apache.log4j.BasicConfigurator.configure();
		 try{
			 
			JasperReport report = JasperCompileManager.compileReport("Rapport/Liste_Des_Classe.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, null, new Connexion().getConnection());
			JasperViewer.viewReport(print,false);
	        
	        }
		 catch(Exception e)
	    {
	           JOptionPane.showMessageDialog(null, e);

	    }

	}
	public void activer_champs(boolean bol){
	
		textField_NomClasse.setEditable(bol);
		comboBox_Classe.setEnabled(bol);
		
	}
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		frame.getContentPane().add(new PanelGestionClasse1(),BorderLayout.CENTER);
		frame.setBounds(100, 100, 1500, 726);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
