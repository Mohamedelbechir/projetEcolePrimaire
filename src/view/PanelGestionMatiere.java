package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classeMetier.Matiere;
import classeMetier.Niveau;
import classeMetier.Salle;
import connxion_Requete.Connexion;
import dao.implement.MatiereDAO;
import dao.implement.NiveauDAO;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelGestionMatiere extends JPanel {
	private JTable table;
	private JTextField txt_nom;
	private MatiereDAO matiereDAO;
	private NiveauDAO niveauDAO;
	private JTextField txtDonnerLeNom;
	private JLabel errNom;
	private JComboBox cb;
	private JLabel label_3;
	private JPanel panel_Operation;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_7;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_Opreation_Valider;
	private JLabel label_valider;
	private JLabel label_annuler;
	private JLabel label_nettoyer;
	private JLabel label_1Annuler;
	private JLabel label_2Valider;
	private JLabel label_3Nettoyer;
	private JTextField textCoef;
	private JLabel errCoff;
	private JLabel errCb;

	/**
	 * Create the panel.
	 */
	public PanelGestionMatiere() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(241, 142, 1111, 534);
		
		
		matiereDAO=new MatiereDAO();
		niveauDAO= new NiveauDAO();
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "LISTES DES MATIERES", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 56, 452, 467);
		add(panel);
		panel.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		scrollPane.setBounds(10, 62, 432, 394);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setSelectionBackground(new Color(128, 128, 128));
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtDonnerLeNom.setForeground(Color.GRAY);
				txtDonnerLeNom.setText("Donner le nom de du matière à chercher");
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chargerElement_Matiere_Champs(obtenir_matiere_Selection());
				activer_champs(false);
				errCb.setVisible(false);
				errCoff.setVisible(false);
				errNom.setVisible(false);
				remove(panel_Opreation_Valider);
				add(panel_Operation);
				repaint();
			}
		});
		scrollPane.setViewportView(table);
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"ID_MATIERE", "NOM_MATIERE"
			}
		));*/
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setForeground(Color.WHITE);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 25, 297, 28);
		panel.add(panel_4);
		
		txtDonnerLeNom = new JTextField();
		txtDonnerLeNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtDonnerLeNom.setForeground(Color.BLUE);
				recherche_Automatique();
			}
		});
		txtDonnerLeNom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(txtDonnerLeNom.getText().equals("Donner le nom de du matière à chercher"))
					txtDonnerLeNom.setText("");
			}
		});
		txtDonnerLeNom.setToolTipText("Rechercher un Salle");
		txtDonnerLeNom.setText("Donner le nom de du mati\u00E8re \u00E0 chercher");
		txtDonnerLeNom.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonnerLeNom.setForeground(Color.GRAY);
		txtDonnerLeNom.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDonnerLeNom.setColumns(10);
		txtDonnerLeNom.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtDonnerLeNom.setBounds(1, 5, 250, 20);
		panel_4.add(txtDonnerLeNom);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDonnerLeNom.setText("");
				affich_all_matiere();
			}
		});
		label.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/Red X copie.png")));
		label.setToolTipText("Vider le champ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(261, 5, 26, 20);
		panel_4.add(label);
		/*Jtable not draggble , not resizble*/
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
		table.setDefaultEditor(Object.class,null);
		
		affich_all_matiere();
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "INFORMATIONS MATIERES", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(472, 56, 559, 381);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNommatiere = new JLabel("<html>Nom _Mati\u00E8re : <font color= 'red'> * </font></html>");
		lblNommatiere.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNommatiere.setBounds(54, 148, 146, 28);
		panel_1.add(lblNommatiere);
		
		txt_nom = new JTextField();
		txt_nom.setColumns(10);
		txt_nom.setBounds(202, 148, 230, 28);
		panel_1.add(txt_nom);
		
		
		JLabel lblNiveau = new JLabel("<html>Niveau_Mati\u00E8re : <font color= 'red'> * </font></html>");
		lblNiveau.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNiveau.setBounds(54, 232, 146, 28);
		panel_1.add(lblNiveau);
		
		errNom = new JLabel("");
		errNom.setVisible(false);
		errNom.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/error.png")));
		errNom.setBounds(442, 148, 21, 28);
		panel_1.add(errNom);
		
		
		cb = new JComboBox();
		cb.setBounds(202, 232, 230, 28);
		panel_1.add(cb);
		
		JLabel lblCofficient = new JLabel("<html>Co\u00E9fficient : <font color= 'red'> * </font></html>");
		lblCofficient.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblCofficient.setBounds(54, 187, 146, 28);
		panel_1.add(lblCofficient);
		
		textCoef = new JTextField();
		textCoef.setColumns(10);
		textCoef.setBounds(202, 187, 230, 28);
		panel_1.add(textCoef);
		
		errCoff = new JLabel("");
		errCoff.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/error.png")));
		errCoff.setVisible(false);
		errCoff.setBounds(442, 187, 22, 28);
		panel_1.add(errCoff);
		
		errCb = new JLabel("");
		errCb.setToolTipText("Selectionn\u00E9 le nNveau");
		errCb.setVisible(false);
		errCb.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/error.png")));
		errCb.setBounds(442, 232, 21, 28);
		panel_1.add(errCb);
		Vector<Niveau> vectoNiveau =  niveauDAO.findAll();
		for (Niveau niveau : vectoNiveau) {
			cb.addItem(niveau.getNom_Niveau());
		}
		
		panel_Operation = new JPanel();
		panel_Operation.setLayout(null);
		panel_Operation.setBorder(new TitledBorder(null, "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Operation.setBounds(472, 445, 397, 75);
		add(panel_Operation);
		
		
		panel_Opreation_Valider =new JPanel();
		panel_Opreation_Valider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Validation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Opreation_Valider.setBounds(472, 445, 397, 75);
		//add(panel_Opreation_Valider);
		panel_Opreation_Valider.setLayout(null);
		
		label_valider=new JLabel("");
		label_valider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(validation_Mise_Jour()){
					if(JOptionPane.showConfirmDialog(null, "Voulez-Vous enregister ces modification ?")==JOptionPane.OK_OPTION){
						if(matiereDAO.update(obtenir_Saisie_Matire_Mise())){
							JOptionPane.showMessageDialog(null, "Succès !");
							affich_all_matiere();
						}else{
							JOptionPane.showMessageDialog(null, "Erreur !");
						}
						remove(panel_Opreation_Valider);
						add(panel_Operation);
						clear();
						repaint();
					}
				}
				
			}
		});
		label_valider.setHorizontalAlignment(SwingConstants.CENTER);
		label_valider.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/Ok-icon.png")));
		label_valider.setBounds(21, 11,   74, 53);
		panel_Opreation_Valider.add(label_valider);
		
		label_annuler=new JLabel("");
		label_annuler.setHorizontalAlignment(SwingConstants.CENTER);
		label_annuler.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/Button-Close-icon.png")));
		label_annuler.setBounds(119, 11, 74, 53);
		panel_Opreation_Valider.add(label_annuler);
		
		label_nettoyer=new JLabel("");
		label_nettoyer.setHorizontalAlignment(SwingConstants.CENTER);
		label_nettoyer.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/clear.png")));
		label_nettoyer.setBounds(240, 11, 73, 53);
		panel_Opreation_Valider.add(label_nettoyer);
		
		label_1Annuler = new JLabel("Annuler");
		label_1Annuler.setHorizontalAlignment(SwingConstants.CENTER);
		label_1Annuler.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_1Annuler.setBounds(119, 50, 74, 14);
		panel_Opreation_Valider.add(label_1Annuler);
		
		label_2Valider = new JLabel("Valider");
		label_2Valider.setHorizontalAlignment(SwingConstants.CENTER);
		label_2Valider.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_2Valider.setBounds(10, 50, 90, 14);
		panel_Opreation_Valider.add(label_2Valider);
		
		label_3Nettoyer = new JLabel("Nettoyer");
		label_3Nettoyer.setHorizontalAlignment(SwingConstants.CENTER);
		label_3Nettoyer.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_3Nettoyer.setBounds(239, 50, 74, 14);
		panel_Opreation_Valider.add(label_3Nettoyer);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(validation_Ajout()){
					
					Matiere matiere=obtenir_Saisie_Matire();
					if(matiereDAO.create(matiere)){
						JOptionPane.showMessageDialog(null, "Succès !");
						affich_all_matiere();
					}else
						JOptionPane.showMessageDialog(null, "Erreur !");
					clear();
				}
			}
		});
		label_1.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/plus.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(22, 11, 73, 53);
		panel_Operation.add(label_1);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				activer_champs(true);
				remove(panel_Operation);
				add(panel_Opreation_Valider);
				repaint();
			}
		});
		label_2.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/edit.png")));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(112, 11, 73, 53);
		panel_Operation.add(label_2);
		
		label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()!=-1){
					if(JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer  Cette matière ? ")==JOptionPane.OK_OPTION){
						if(matiereDAO.delete(obtenir_matiere_Selection())){
							JOptionPane.showMessageDialog(null, "Succès !");
							affich_all_matiere();
						}
							
					}
				}
			}
		});
		label_3.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/Button-Close-icon.png")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(196, 11, 73, 53);
		panel_Operation.add(label_3);
		
		JLabel label_4 = new JLabel("Ajouter");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_4.setBounds(22, 50, 73, 14);
		panel_Operation.add(label_4);
		
		JLabel label_5 = new JLabel("Modifier");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_5.setBounds(112, 50, 73, 14);
		panel_Operation.add(label_5);
		
		JLabel label_6 = new JLabel("Supprimer");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_6.setBounds(181, 50, 108, 14);
		panel_Operation.add(label_6);
		
		label_7 = new JLabel("");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clear();
				activer_champs(true);
				table.clearSelection();
			}
		});
		label_7.setIcon(new ImageIcon(PanelGestionMatiere.class.getResource("/image/clear.png")));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(292, 11, 73, 53);
		panel_Operation.add(label_7);
		
		JLabel label_8 = new JLabel("Nettoyer");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_8.setBounds(292, 50, 73, 14);
		panel_Operation.add(label_8);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(null, "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(879, 448, 158, 75);
		add(panel_6);
		
		JButton btnlisteDesmatire = new JButton("<html><center>Liste des <br>Mati\u00E8re</center></html>");
		btnlisteDesmatire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				print();
			}
		});
		btnlisteDesmatire.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnlisteDesmatire.setBounds(10, 21, 138, 43);
		panel_6.add(btnlisteDesmatire);

	}
	
	   public void clear(){
			
			textCoef.setText("");
			txt_nom.setText("");
			cb.setSelectedItem(null);
			errNom.setVisible(false);   
			errCoff.setVisible(false);
		}
		public void proprieteTable(){
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0);
			table.getColumnModel().getColumn(0).setWidth(0);
		}
		
		public void chargerElement_Matiere_Champs(Matiere matiere){
			txt_nom.setText(matiere.getNom());
			cb.setSelectedItem(niveauDAO.find(matiere.getId_niveau()).getNom_Niveau());
			textCoef.setText(matiere.getCoef().toString());
		}
		
		// Cette permet d'obtenir la matiere saisi par clavier
		
		public Matiere obtenir_Saisie_Matire(){
			
			Matiere matiere= new Matiere();
			Long id_Niveau= niveauDAO.findBy_Nom(cb.getSelectedItem().toString()).getId_Niveau();
			Long coef=Long.parseLong(textCoef.getText());
			matiere.setId_niveau(id_Niveau);
			matiere.setNom(txt_nom.getText());
			matiere.setCoef(coef);
			return matiere;
		}
		// Cette permet d'obtenir la matiere saisi par clavier
		
		public Matiere obtenir_Saisie_Matire_Mise(){
					
			Matiere matiere= new Matiere();
			Long id_Niveau= niveauDAO.findBy_Nom(cb.getSelectedItem().toString()).getId_Niveau();
			Long coef=Long.parseLong(textCoef.getText());
			matiere.setId_matiere(obtenir_matiere_Selection().getId_matiere());
			matiere.setId_niveau(id_Niveau);
			matiere.setNom(txt_nom.getText());
			matiere.setCoef(coef);
			return matiere;
		}
		// Obtenir la salle selectionner sur la table 
		public Matiere obtenir_matiere_Selection(){
			
			Matiere matiere= new Matiere();
			matiere.setId_matiere(Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString()));
			matiere.setNom((String)table.getValueAt(table.getSelectedRow(), 1));
			matiere.setCoef(Long.parseLong(table.getValueAt(table.getSelectedRow(), 2).toString()));
			matiere.setId_niveau(matiereDAO.getByid(matiere.getId_matiere()).getId_niveau());
			return matiere;
		}
		public void recherche_Automatique(){
			
			DefaultTableModel  modelMatiere= new DefaultTableModel();
			modelMatiere.addColumn("ID_MATIERE");
			modelMatiere.addColumn("NOM_MATIERE");
			modelMatiere.addColumn("COEF");
			modelMatiere.addColumn("NON_NIVEAU");
				
				Vector<Matiere> collection_salle ;
				collection_salle=matiereDAO.getAllmatiere_Recherche("%"+txtDonnerLeNom.getText()+"%");
				
			
				for(Matiere matiere : collection_salle) {
					String nom_Matiere= niveauDAO.find(matiere.getId_niveau()).getNom_Niveau();
					modelMatiere.addRow(new Object[]{matiere.getId_matiere(),matiere.getNom(),matiere.getCoef(),nom_Matiere});	
						
					}
					table.setModel(modelMatiere);
					proprieteTable();	
				
		}
		
		public void activer_champs(boolean bol){
			
			textCoef.setEditable(bol);
			txt_nom.setEditable(bol);
			cb.setEnabled(bol);
			
		}
		
		public void affich_all_matiere()
		{
			ResultSet rs = matiereDAO.getAllmatiere();
			
			if(rs!=null)
			table.setModel(DbUtils.resultSetToTableModel(rs));
			else 
				System.out.println("erreur");
			proprieteTable();	
		}
		
		
		// La mthode validation 
		public boolean validation_Ajout(){
			boolean bol=true;
			// Validation de du Coefficient
			try {
				Long coef = Long.parseLong(textCoef.getText().toString());
				if(coef>=5){
					bol=false;	
					errCoff.setToolTipText("Véficié le coéfficient ");
				}
			} catch (NumberFormatException e) {
				bol=false;	
				errCoff.setVisible(true);
				errCoff.setToolTipText("Pas de chaine ");
			}
			// Si c'est de mise à jour verifie seulement le nom de la Classe
			if(cb.getSelectedItem()==null){
				errCb.setVisible(true);
				bol=false;
			}else
				errCb.setVisible(false);
			// Verification si chaine vide
			if(txt_nom.getText().equals("")){
				bol=false;
				errNom.setVisible(true);
				errNom.setToolTipText("Chaine vide !");
			}else
				errNom.setVisible(false);
				
			try {
				// Verifiction de la matire si existe deja
				Long idNiveau=niveauDAO.findBy_Nom(cb.getSelectedItem().toString()).getId_Niveau();
				if(matiereDAO.find_By_NMat_IdN(txt_nom.getText(),idNiveau)!=null){
					bol=false;
					errNom.setVisible(true);
					errNom.setToolTipText("Désolé cette matière existe déjà !");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return bol;
		}
		
		// Validation pour la mise à jour
		public boolean validation_Mise_Jour(){
			boolean bol=true;
			errCoff.setVisible(false);
			errNom.setVisible(false);
			// Validation de du Coefficient
			try {
				Long coef = Long.parseLong(textCoef.getText().toString());
				if(coef>=5){
					bol=false;	
					errCoff.setToolTipText("Véficié le coéfficient ");
				}
			} catch (NumberFormatException e) {
				bol=false;	
				errCoff.setVisible(true);
				errCoff.setToolTipText("Pas de chaine ");
			}
			
			// Verifiction de la matire si existe deja
			Vector<Matiere> liste_Matiere=matiereDAO.findAll();
			Long idNiveau_Nouveau=niveauDAO.findBy_Nom(cb.getSelectedItem().toString()).getId_Niveau();
			Long idNiveau_Acien=obtenir_matiere_Selection().getId_niveau();
			String ancien_Nom=obtenir_matiere_Selection().getNom();
			String nouveau_Nom=txt_nom.getText();
			
			if( idNiveau_Acien!=idNiveau_Nouveau || !ancien_Nom.equals(nouveau_Nom) ){
				
				for (Matiere matiere : liste_Matiere) {
					// Alors on a trouver un duplication
					if(matiere.getNom().equals(nouveau_Nom) && matiere.getId_niveau()==idNiveau_Nouveau){
						bol=false;
						errNom.setVisible(true);
						errNom.setToolTipText("Désolé cette matière existe déjà !");
					}
				}
			}
			return bol;
		}
		public void print(){

			org.apache.log4j.BasicConfigurator.configure();
			 try{
				 
				JasperReport report = JasperCompileManager.compileReport("Rapport\\Liste_Des_Matiere.jrxml");
				JasperPrint print = JasperFillManager.fillReport(report, null, new Connexion().getConnection());
				JasperViewer.viewReport(print,false);
		        
		        }
			 catch(Exception e)
		    {
		           JOptionPane.showMessageDialog(null, e);

		    }

		}
}
