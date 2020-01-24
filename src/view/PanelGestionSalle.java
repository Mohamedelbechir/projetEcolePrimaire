package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.print.DocFlavor.URL;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import classeMetier.Classe;
import classeMetier.Salle;
import connxion_Requete.Connexion;
import dao.implement.ClasseDAO;
import dao.implement.SalleDAO;
import dao.implement.Salle_Classe_AffectationDAO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelGestionSalle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table_liste_Salle;
	private JTextField textField_Nom_Salle;
	private JTextField textField_Capacite_Salle;
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radio_Button_Affectation ;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_Liste_Classe;
	private JButton btnSupprimer;
	private JLabel lblNomDeLa;
	private JTextField txtDonnerLeNom;
	private JPanel panel_Affectation;
	private long id_Salle;
	private long id_Classe;
	private ClasseDAO classeDAO ;
	private Salle_Classe_AffectationDAO salle_Classe_AffectationDAO;
	private SalleDAO salleDAO;
	private JLabel errCapSalle;
	private JLabel errNonSalle;
	private DefaultTableCellRenderer centerRender;
	private JLabel label_annuler;
	private JLabel label_nettoyer;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_valider;
	private JPanel panel_Opreation_Valider;
	private JPanel panel_Operation;
	@SuppressWarnings("rawtypes")
	public PanelGestionSalle() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		JPanel panel_ListeSalle = new JPanel();
		panel_ListeSalle.setBorder(new TitledBorder(null, "Liste des Salles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ListeSalle.setBounds(10, 56, 449, 467);
		add(panel_ListeSalle);
		panel_ListeSalle.setLayout(null);
		
		classeDAO =new ClasseDAO();
		salle_Classe_AffectationDAO =new Salle_Classe_AffectationDAO();
		salleDAO=new SalleDAO();
		
		centerRender= new DefaultTableCellRenderer() ;
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 432, 394);
		panel_ListeSalle.add(scrollPane);
		
		table_liste_Salle = new JTable();
		table_liste_Salle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_liste_Salle.setSelectionBackground(new Color(128, 128, 128));
		table_liste_Salle.setRowHeight(30);
		table_liste_Salle.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		table_liste_Salle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// On charge l'element selectionner dans les champs
				txtDonnerLeNom.setText("Donner le nom de la Salle à chercher");
				txtDonnerLeNom.setForeground(new Color(128, 128, 128));
				btnSupprimer.setEnabled(false);
				radio_Button_Affectation.setEnabled(false);
				activer_Champs(false);
				activer_Champs_Affectation(false);
				remove(panel_Opreation_Valider);
				repaint();
				add(panel_Operation);
				remplir_Champ_Salle(obtenir_Salle_Selection());
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {

				
			}
		});
		table_liste_Salle.getTableHeader().setReorderingAllowed(false);
		table_liste_Salle.getTableHeader().setResizingAllowed(false);
		table_liste_Salle.setDefaultEditor(Object.class,null);
		table_liste_Salle.getTableHeader().setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		scrollPane.setViewportView(table_liste_Salle);
		
		JPanel panel_recherche = new JPanel();
		panel_recherche.setBackground(Color.WHITE);
		panel_recherche.setForeground(Color.WHITE);
		panel_recherche.setBounds(11, 23, 265, 28);
		panel_ListeSalle.add(panel_recherche);
		panel_recherche.setLayout(null);
		
		txtDonnerLeNom = new JTextField();
		txtDonnerLeNom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Si le champ contient cette message on la supprimer sinon on laisse
				if(txtDonnerLeNom.getText().equals("Donner le nom de la Salle à chercher"))
				txtDonnerLeNom.setText("");
			}
		});
		txtDonnerLeNom.setFont(new Font("Dialog", Font.ITALIC, 13));
		txtDonnerLeNom.setForeground(new Color(128, 128, 128));
		txtDonnerLeNom.setText("Donner le nom de la Salle \u00E0 chercher");
		txtDonnerLeNom.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonnerLeNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				txtDonnerLeNom.setForeground(Color.BLUE);
				chargerTable_Salle_Rechercher();
			}
		});
		txtDonnerLeNom.setToolTipText("Rechercher un Salle");
		txtDonnerLeNom.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
				
				
				
			}
		});
		txtDonnerLeNom.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtDonnerLeNom.setBounds(1, 5, 230, 20);
		panel_recherche.add(txtDonnerLeNom);
		txtDonnerLeNom.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setToolTipText("Vider le champ");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDonnerLeNom.setText("");
				chargerTable_Salle();
			}
		});
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/Red X copie.png")));
		label.setBounds(235, 5, 26, 20);
		panel_recherche.add(label);
		
		JPanel panel_Information_Salle = new JPanel();
		panel_Information_Salle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information de la Salle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Information_Salle.setBounds(472, 56, 559, 381);
		add(panel_Information_Salle);
		panel_Information_Salle.setLayout(null);
		
		JLabel lblNonDeLa = new JLabel("<html>Non de la Salle  : <font color= 'red'> * </font></html>");
		lblNonDeLa.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNonDeLa.setBounds(20, 61, 173, 25);
		panel_Information_Salle.add(lblNonDeLa);
		
		textField_Nom_Salle = new JTextField();
		textField_Nom_Salle.setBounds(203, 58, 241, 28);
		panel_Information_Salle.add(textField_Nom_Salle);
		textField_Nom_Salle.setColumns(10);
		
		textField_Capacite_Salle = new JTextField();
		textField_Capacite_Salle.setBounds(202, 104, 93, 28);
		panel_Information_Salle.add(textField_Capacite_Salle);
		textField_Capacite_Salle.setColumns(10);
		
		
		panel_Affectation= new JPanel();
		panel_Affectation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Affecter la Salle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Affectation.setLayout(null);
		
		panel_Affectation.setBounds(20, 170, 446, 156);
		panel_Information_Salle.add(panel_Affectation);
		
		
		lblNomDeLa = new JLabel("Nom de la Salle");
		lblNomDeLa.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNomDeLa.setBounds(17, 49, 117, 29);
		panel_Affectation.add(lblNomDeLa);
		
		JLabel label_5 = new JLabel("----->>");
		label_5.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_5.setBounds(127, 49, 46, 29);
		panel_Affectation.add(label_5);
		
		comboBox_Liste_Classe = new JComboBox();
		comboBox_Liste_Classe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				remplir_Combo_affectation();
			}
		});
		comboBox_Liste_Classe.setBounds(176, 49, 230, 28);
		remplir_Combo_affectation();
		panel_Affectation.add(comboBox_Liste_Classe);
		
		btnSupprimer = new JButton("Supprimer Affectation");
		btnSupprimer.setEnabled(false);
		btnSupprimer.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/Red X copie.png")));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(salleDAO.deja_Affecter_Salle(obtenir_Salle_Selection().getId_SALLE())){
					if(JOptionPane.showConfirmDialog(null, "Voulez vous supprimer l'affectation Courant ?")==0){
				
					if(salleDAO.delete_Affection(obtenir_Salle_Selection().getId_SALLE())){
						JOptionPane.showMessageDialog(null, "Affectation supprimer avec succèes");
					}else{
						JOptionPane.showMessageDialog(null, "Une erreur s'est deroulée lors du traitement veuillez verifier les valeurs");
					}
					
				}
			}else
					JOptionPane.showMessageDialog(null, "Cette Salle n'est encore affectée!");
			}
		});
		btnSupprimer.setBounds(176, 89, 193, 33);
		panel_Affectation.add(btnSupprimer);
		
		JLabel lblCapacit = new JLabel("<html>Capacité : <font color= 'red'> * </font></html>");
		lblCapacit.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblCapacit.setBounds(20, 104, 93, 28);
		panel_Information_Salle.add(lblCapacit);
		
		errNonSalle = new JLabel("");
		errNonSalle.setVisible(false);
		errNonSalle.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/error.png")));
		errNonSalle.setBounds(447, 58, 21, 28);
		panel_Information_Salle.add(errNonSalle);
		
		errCapSalle = new JLabel("");
		errCapSalle.setVisible(false);
		errCapSalle.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/error.png")));
		errCapSalle.setBounds(298, 104, 21, 28);
		panel_Information_Salle.add(errCapSalle);
		
		
		radio_Button_Affectation = new JRadioButton("Affectation à une Classe");
		radio_Button_Affectation.setBounds(20, 139, 250, 23);
		panel_Information_Salle.add(radio_Button_Affectation);
		radio_Button_Affectation.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		
		radio_Button_Affectation.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if( radio_Button_Affectation.isSelected()){
					activer_Champs_Affectation(true);
					if(table_liste_Salle.getSelectedRow()!=-1)
						btnSupprimer.setEnabled(true);
				}else{
					activer_Champs_Affectation(false);
					btnSupprimer.setEnabled(false);
					
				}
					
			}
		});
		
		panel_Operation = new JPanel();
		panel_Operation.setBorder(new TitledBorder(null, "Op\u00E9ration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Operation.setBounds(469, 448, 370, 75);
		add(panel_Operation);
		panel_Operation.setLayout(null);
		
		panel_Opreation_Valider =new JPanel();
		panel_Opreation_Valider.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Validation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Opreation_Valider.setBounds(469, 448, 340, 75);
		//add(panel_Opreation_Valider);
		panel_Opreation_Valider.setLayout(null);
		
		label_valider = new JLabel("");
		label_valider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(validation_Mise_Jour()){

					if(salleDAO.update(creer_Objet_Salle_Saisi_Champ_Mise_Jour())){
						comboBox_Liste_Classe.setSelectedItem(null);
						chargerTable_Salle();
						vider_champ();
						remove(panel_Opreation_Valider);
						repaint();
						add(panel_Operation);	
						JOptionPane.showMessageDialog(null, "La mise à jour est effetuée");
					
					};
					
				}
			}
		});
		label_valider.setHorizontalAlignment(SwingConstants.CENTER);
		label_valider.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/Ok-icon.png")));
		label_valider.setBounds(21, 11,   74, 53);
		panel_Opreation_Valider.add(label_valider);
		
		label_annuler = new JLabel("");
		label_annuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				add(panel_Operation);
				remove(panel_Opreation_Valider);
				vider_champ();
				repaint();
			}
		});
		label_annuler.setHorizontalAlignment(SwingConstants.CENTER);
		label_annuler.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/Button-Close-icon.png")));
		label_annuler.setBounds(119, 11, 74, 53);
		panel_Opreation_Valider.add(label_annuler);
		
		label_nettoyer = new JLabel("");
		label_nettoyer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vider_champ();
			}
		});
		label_nettoyer.setHorizontalAlignment(SwingConstants.CENTER);
		label_nettoyer.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/clear.png")));
		label_nettoyer.setBounds(240, 11, 73, 53);
		panel_Opreation_Valider.add(label_nettoyer);
		
		label_1 = new JLabel("Annuler");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_1.setBounds(119, 50, 74, 14);
		panel_Opreation_Valider.add(label_1);
		
		label_2 = new JLabel("Valider");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_2.setBounds(10, 50, 90, 14);
		panel_Opreation_Valider.add(label_2);
		
		label_3 = new JLabel("Nettoyer");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_3.setBounds(239, 50, 74, 14);
		panel_Opreation_Valider.add(label_3);
		
		JLabel label_Ajouter_Salle = new JLabel("");
		label_Ajouter_Salle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0){
				
//				//JFrame frame  =(JFrame)SwingUtilities.getWindowAncestor(panel_Operation);
//				new FrameAjouterSalle((PanelGestionSalle)panel_Operation.getParent()).setVisible(true);
//				chargerTable_Salle();
				if(validation_Ajout()){
					salleDAO.create(creer_Objet_Salle_Saisi_Champ_PourAjout());
					JOptionPane.showMessageDialog(null, "Ajout effectué avec succès !");
					chargerTable_Salle();
				}
			}
		});
		label_Ajouter_Salle.setHorizontalAlignment(SwingConstants.CENTER);
		label_Ajouter_Salle.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/plus.png")));
		label_Ajouter_Salle.setBounds(22, 11, 72, 53);
		panel_Operation.add(label_Ajouter_Salle);
		
		JLabel label_Modifier_Salle = new JLabel("");
		label_Modifier_Salle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				radio_Button_Affectation.setEnabled(true);
				if(table_liste_Salle.getSelectedRow()==-1){
					
					JOptionPane.showMessageDialog(null, "Désolé  aucune salle Sélectionnée");
					
				}else{
					remove(panel_Operation);
					repaint();
					add(panel_Opreation_Valider);	
					remplir_Champ_Salle(obtenir_Salle_Selection());
					activer_Champs(true);
					
				}
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});
		label_Modifier_Salle.setHorizontalAlignment(SwingConstants.CENTER);
		label_Modifier_Salle.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/edit.png")));
		label_Modifier_Salle.setBounds(104, 11,  73, 53);
		panel_Operation.add(label_Modifier_Salle);
		
		JLabel label_Supprimer_Salle = new JLabel("");
		label_Supprimer_Salle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(!(table_liste_Salle.getSelectedRow()==-1)){
					if(JOptionPane.showConfirmDialog(null, "Voulez vous supprimer cette Salle ?")==0){
						
						boolean succes= salleDAO.delete(obtenir_Salle_Selection());
						if(succes){
							JOptionPane.showMessageDialog(null, "Succès");
							vider_champ();
							activer_Champs(false);
							chargerTable_Salle();
						}
					}
				}else
					JOptionPane.showMessageDialog(null, "Aucune Salle Selectionée !");
			}
		});
		label_Supprimer_Salle.setHorizontalAlignment(SwingConstants.CENTER);
		label_Supprimer_Salle.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/Button-Close-icon.png")));
		label_Supprimer_Salle.setBounds(180, 11, 88, 53);
		panel_Operation.add(label_Supprimer_Salle);
		
		JLabel lblAjouter = new JLabel("Ajouter");
		lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAjouter.setBounds(22, 50, 72, 14);
		panel_Operation.add(lblAjouter);
		
		JLabel lblModifier = new JLabel("Modifier");
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblModifier.setBounds(104, 50, 73, 14);
		panel_Operation.add(lblModifier);
		
		JLabel lblSupprimer = new JLabel("Supprimer");
		lblSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupprimer.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblSupprimer.setBounds(187, 50, 81, 14);
		panel_Operation.add(lblSupprimer);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table_liste_Salle.clearSelection();
			
				vider_champ();	
				radio_Button_Affectation.setEnabled(true);
				activer_Champs(true);
			}
		});
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/clear.png")));
		label_4.setBounds(278, 11, 82, 53);
		panel_Operation.add(label_4);
		
		JLabel lblNettoyer = new JLabel("Nettoyer");
		lblNettoyer.setHorizontalAlignment(SwingConstants.CENTER);
		lblNettoyer.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblNettoyer.setBounds(279, 50, 81, 14);
		panel_Operation.add(lblNettoyer);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 44, 1079, 11);
		add(separator);
		
		JLabel lblGestionDesSalle = new JLabel("Gestion des Salles :");
		lblGestionDesSalle.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblGestionDesSalle.setBounds(26, 11, 205, 34);
		add(lblGestionDesSalle);
		
		JPanel panel_Impression = new JPanel();
		panel_Impression.setBounds(860, 448, 171, 75);
		add(panel_Impression);
		panel_Impression.setBorder(new TitledBorder(null, "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Impression.setLayout(null);
		
		JButton btnListeDesSalles = new JButton("<html><center>Liste des <br>Salles</center></html>");
		btnListeDesSalles.setIcon(new ImageIcon(PanelGestionSalle.class.getResource("/image/print-icon.png")));
		btnListeDesSalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 print();
			}
		});
		btnListeDesSalles.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListeDesSalles.setBounds(10, 21, 151, 43);
		panel_Impression.add(btnListeDesSalles);
		
		activer_Champs(false);
		activer_Champs_Affectation(false);
		chargerTable_Salle();
	}
	
	
	/*------------------ la methode pour obtenir_Salle_Selection -----------------------*/
	public Salle obtenir_Salle_Selection(){
		Salle salle=new Salle();
		salle.setId_SALLE((long)table_liste_Salle.getValueAt(table_liste_Salle.getSelectedRow(),0));
		salle.setNom_SALLE((String)table_liste_Salle.getValueAt(table_liste_Salle.getSelectedRow(),1));
		salle.setCapacite((int)table_liste_Salle.getValueAt(table_liste_Salle.getSelectedRow(),2));
		return salle;
	}
	
	
	/*------------------ la methode pour remplir les champs Salle------------------------*/
	public void remplir_Champ_Salle(Salle salle){
		//Salle_Classe_AffectationDAO da = new Salle_Classe_AffectationDAO();
		textField_Nom_Salle.setText(salle.getNom_SALLE());
		textField_Capacite_Salle.setText(salle.getCapacite().toString());
		
		if(salleDAO.deja_Affecter_Salle(salle.getId_SALLE())){
			//ClasseDAO dc = new ClasseDAO();
			// on recupere de la Classe Correspodant Ã  la Salle
			Classe classe = classeDAO.find(salleDAO.find(salle.getId_SALLE()).getNum_Clase());
			String nom_Classe = classe.getNom_Clase();
			// On change l'element selectioner du Combobox 
			comboBox_Liste_Classe.setSelectedItem(nom_Classe);
		}else{
			radio_Button_Affectation.setSelected(false);
			comboBox_Liste_Classe.setSelectedItem(null);
		}
		lblNomDeLa.setText(salle.getNom_SALLE());  
		
	}
	
	
	/*------------------ la methode pour vider les champs Salle----------------------------*/
	public void vider_champ(){
		
		textField_Nom_Salle.setText("");
		textField_Capacite_Salle.setText("");
		lblNomDeLa.setText("Nom de la Salle");
		comboBox_Liste_Classe.setSelectedItem(null);
		comboBox_Liste_Classe.setEnabled(false);
		radio_Button_Affectation.setSelected(false);
		if(table_liste_Salle.getSelectedRow()==-1)
			radio_Button_Affectation.setEnabled(false);
		btnSupprimer.setEnabled(false);
		errCapSalle.setVisible(false);
		errNonSalle.setVisible(false);
	}
	
	/*---------Cette methode permet de raffraichir le id_Salle et id_Classe qui contient---*/
	
	public void rafraichir_id_Salle_id_Classe(){
		
		String nom_Classe = comboBox_Liste_Classe.getSelectedItem().toString();
		if(nom_Classe.equals(""))
			JOptionPane.showMessageDialog(null,"SelectionÃ© une Classe");
		//ClasseDAO dc =new ClasseDAO();
		id_Salle = obtenir_Salle_Selection().getId_SALLE();
		id_Classe= classeDAO.findByNom(nom_Classe).getNum_Clase();
	}

	/*---------la Methode pour remplir le combobox d'affectation
	 * -------- l'ensemble de noms des classe prÃ©sentes dans la base de donnÃ©-----*/
	@SuppressWarnings("unchecked")
	public void remplir_Combo_affectation(){
		//ClasseDAO da = new ClasseDAO();
		Vector<Classe> v = new Vector<Classe>();
		v=classeDAO.findAll();
		comboBox_Liste_Classe.removeAllItems();
		for (Classe classe : v) {
			comboBox_Liste_Classe.addItem(classe.getNom_Clase());
		}
		comboBox_Liste_Classe.setSelectedItem(null);
		
	}
	/*------------------ la methode pour charger la table  des Salles -------------*/
	public void chargerTable_Salle(){
		
			DefaultTableModel  modelEleve= new DefaultTableModel();
			modelEleve.addColumn("ID_SALLE");
			modelEleve.addColumn("NOM_SALLE");
			modelEleve.addColumn("CAPACITE");
			
			Vector<Salle> po = new Vector<Salle>();
			po=salleDAO.findAll();
			
			for (Salle cl : po) {
				modelEleve.addRow(new Object[]{cl.getId_SALLE(),cl.getNom_SALLE(),cl.getCapacite(),});	
			}
			
			table_liste_Salle.setModel(modelEleve);
			proprietetTable();
		} 
	/*------------------ la methode pour charger la table  par le nom de la Salle recherchÃ©e -------------*/
	public void chargerTable_Salle_Rechercher(){
		
		DefaultTableModel  modelEleve= new DefaultTableModel();
		modelEleve.addColumn("ID_SALLE");
		modelEleve.addColumn("NOM_SALLE");
		modelEleve.addColumn("CAPACITE");
			
			Vector<Salle> collection_salle ;
			collection_salle=salleDAO.findCollection("%"+txtDonnerLeNom.getText()+"%");
			
			if(collection_salle!=null){
				for (Salle salle2 : collection_salle) {
					modelEleve.addRow(new Object[]{salle2.getId_SALLE(),salle2.getNom_SALLE(),salle2.getCapacite()});	
					
				}
				table_liste_Salle.setModel(modelEleve);
				proprietetTable();
			}
			
				
			
		}
	public void proprietetTable(){
		table_liste_Salle.getColumnModel().getColumn(0).setMinWidth(0);
		table_liste_Salle.getColumnModel().getColumn(0).setMaxWidth(0);
		table_liste_Salle.getColumnModel().getColumn(0).setWidth(0);
		table_liste_Salle.getColumnModel().getColumn(1).setCellRenderer(centerRender);
		table_liste_Salle.getColumnModel().getColumn(2).setCellRenderer(centerRender);
		table_liste_Salle.getTableHeader().setDefaultRenderer(centerRender);
	}
	/*------------ La permettant de crÃ©er un objet Salle Ã  partir des champs text--------------*/
	public Salle creer_Objet_Salle_Saisi_Champ_Mise_Jour(){
		Salle salle = null;
		String nom_Classe;	
		salle = new Salle();
		salle.setId_SALLE(obtenir_Salle_Selection().getId_SALLE());
		salle.setNom_SALLE(textField_Nom_Salle.getText());
		salle.setCapacite(Integer.parseInt(textField_Capacite_Salle.getText()));
		try {
				nom_Classe=comboBox_Liste_Classe.getSelectedItem().toString();
				Classe classe = classeDAO.findByNom(nom_Classe) ;
				salle.setNum_Clase(classe.getNum_Clase());
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
		return salle;
	}
	
	/*------------ La permettant de créer un objet Salle à partir des champs text--------------*/
	public Salle creer_Objet_Salle_Saisi_Champ_PourAjout(){
		
		Salle salle = new Salle();
		String nom_Classe;
		try {
			nom_Classe=comboBox_Liste_Classe.getSelectedItem().toString();
			Classe classe = classeDAO.findByNom(nom_Classe) ;
			salle.setNum_Clase(classe.getNum_Clase());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}		
		salle.setNom_SALLE(textField_Nom_Salle.getText());
		salle.setCapacite(Integer.parseInt(textField_Capacite_Salle.getText()));
		
		
		return salle;
	}
	/*------------------ cette methode perment de desactiver le champs Salle ----------------*/
	public void activer_Champs(boolean champs){
	}
	
	/*------------------ cette methode perment de desactiver le champs Affectation -----------*/
	public void activer_Champs_Affectation(boolean panel_radio){
		
		comboBox_Liste_Classe.setEnabled(panel_radio);
		//comboBox_Liste_Classe.setSelectedItem(panel_radio);
		radio_Button_Affectation.setSelected(panel_radio);
		
	}
	/*----------------------- Cette method permet de faire Impression ----------------------------*/
	public void print(){

		org.apache.log4j.BasicConfigurator.configure();
		 try{
			 
			JasperReport report = JasperCompileManager.compileReport("Rapport\\Liste_Des_Salle.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, null, new Connexion().getConnection());
			JasperViewer.viewReport(print,false);
	        
	        }
		 catch(Exception e)
	    {
	           JOptionPane.showMessageDialog(null, e);

	    }

	}
	
	/* ----------------------Cette methode permet de Verifier les champs de Saisie utilse pour la mise à jour d'un salle-----------*/
	public boolean validation_Mise_Jour(){
		boolean bol = true;
		//String regexId = "^[0-9]{4}$";
		String regexNom ="^[a-zA-Z\\s]+[0-9]*[a-zA-Z\\s]*$";
		
		/* --------------------Verification  du nom de la Salle -----------*/
		if(!textField_Nom_Salle.getText().matches(regexNom)){
			errNonSalle.setVisible(true);
			errNonSalle.setToolTipText("Verifié le nom");
			bol=false;
		}
		else if(!(obtenir_Salle_Selection().getNom_SALLE().equals(textField_Nom_Salle.getText()))){
			if(salleDAO.find_ByNom(textField_Nom_Salle.getText())!=null){
				errNonSalle.setVisible(true);
				errNonSalle.setToolTipText("Ce nom appartient à une autre salle");
				bol=false;
			}
				
		}
		else
			errNonSalle.setVisible(false);
		
		/* ---------------Verification de la capacite----------------------*/
		/* ---------------Verification de la capacite----------------------*/
		try{
			if(Long.parseLong(textField_Capacite_Salle.getText())>100){
				errCapSalle.setVisible(true);
				errCapSalle.setToolTipText("la capacité max est de 100 places ");
				bol=false;
			}else
				errCapSalle.setVisible(false);
			
		}catch (NumberFormatException e) {
			errCapSalle.setToolTipText("Desolé pas chaine de character");
			bol=false;
			errCapSalle.setVisible(true);
		}
		try {
			if(salleDAO.adeja_Une_Salle(classeDAO.findByNom(comboBox_Liste_Classe.getSelectedItem().toString()).getNum_Clase())){
			if(JOptionPane.showConfirmDialog(null, "La Classe choisie a déjaà une salle voulez vous continuer")==JOptionPane.OK_OPTION){
				Classe classe = classeDAO.findByNom(comboBox_Liste_Classe.getSelectedItem().toString()) ;
				Salle salle=salleDAO.find_By_Id_Classe(classe.getNum_Clase());
				if(salleDAO.delete_Affection(salle.getId_SALLE()))
					JOptionPane.showMessageDialog(null, "suppimer");
			}else{
				bol=false;
			}
		}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		/* Verification si c'est deja affecter */
		
		return bol;
	}
	// Validation pour Ajout
	public boolean validation_Ajout(){
		boolean bol = true;
		String regexNom ="^[a-zA-Z\\s]+[0-9]*[a-zA-Z\\s]*$";
		
	
		/* --------------------Verification  du nom de la Salle -----------*/
		if(!textField_Nom_Salle.getText().matches(regexNom)){
			errNonSalle.setVisible(true);
			errNonSalle.setToolTipText("Verifié le nom");
			bol=false;
		}
		else if(salleDAO.find_ByNom(textField_Nom_Salle.getText())!=null){
			
			errNonSalle.setVisible(true);
			errNonSalle.setToolTipText("Ce nom appartient à une autre salle");
			bol=false;		
		}
		else
			errNonSalle.setVisible(false);
		
		/* ---------------Verification de la capacite----------------------*/
		try{
			if(Long.parseLong(textField_Capacite_Salle.getText())>100){
				errCapSalle.setVisible(true);
				errCapSalle.setToolTipText("la capacité max est de 100 places ");
				bol=false;
			}else
				errCapSalle.setVisible(false);
			
		}catch (NumberFormatException e) {
			errCapSalle.setToolTipText("Desolé pas chaine de character");
			bol=false;
			errCapSalle.setVisible(true);
		}
		/* Verification si c'est deja affecter */
		try {
			if(salleDAO.adeja_Une_Salle(classeDAO.findByNom(comboBox_Liste_Classe.getSelectedItem().toString()).getNum_Clase())){
				
			if(JOptionPane.showConfirmDialog(null, "La Classe choisie a déjaà une salle voulez vous continuer")==JOptionPane.OK_OPTION){
					
				Classe classe = classeDAO.findByNom(comboBox_Liste_Classe.getSelectedItem().toString()) ;
				Salle salle=salleDAO.find_By_Id_Classe(classe.getNum_Clase());
				if(salleDAO.delete_Affection(salle.getId_SALLE()))
						JOptionPane.showMessageDialog(null, "suppimer");;
				}else{
					bol=false;
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
	
			
		return bol;
	}
}

class Pricinpal1 {
	
	 public static void main(String[] args)
	 {
		 SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					String name= UIManager.getInstalledLookAndFeels()[3].getClassName();
					UIManager.setLookAndFeel(name);
				} catch (Exception e) {
					// TODO: handle exception
				}
				JFrame frame = new JFrame("Gestion Classe");
				 frame.setLayout(new BorderLayout());
				 frame.add(new PanelGestionSalle(), BorderLayout.CENTER );
				 frame.setVisible(true);
			}
		});
		 
		 
	 }
	
}