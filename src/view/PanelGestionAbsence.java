package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.lf5.viewer.FilteredLogTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import classeMetier.Absence_Eleve;
import classeMetier.Absence_Instituteur;
import classeMetier.Classe;
import classeMetier.Conseil_Instituteur;
import classeMetier.Eleve;
import classeMetier.Instituteur;
import connxion_Requete.Connexion;
import dao.implement.Absence_EleveDAO;
import dao.implement.Absence_InstituteurDAO;
import dao.implement.ClasseDAO;
import dao.implement.EleveDAO;
import dao.implement.InstituteurDAO;
import dao.implement.NiveauDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class PanelGestionAbsence extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_Liste_Absence;
	private JScrollPane scrollPane;
	private JTable table_Absence;
	private JButton btnCharger_Ajout;
	private JComboBox comboBox_Liste_Classe;
	private JButton btnImprimer;
	private ClasseDAO classeDAO;
	private NiveauDAO niveauDAO;
	private EleveDAO eleveDAO;
	private InstituteurDAO instituteurDAO;
	private Absence_InstituteurDAO absence_InstituteurDAO;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JDateChooser textDate_Ajout;
	private DefaultTableCellRenderer centerRender;
	private Absence_EleveDAO absence_EleveDAO;
	private JLabel label;
	private JPanel operation_Modif;
	private JLabel label_3;
	private JLabel label_2;
	private JPanel panel_2;
	private JPanel panel_Absence;
	private JRadioButton rdbtnModifierabsence;
	private JDateChooser txtDate_Modifier;
	private JRadioButton rdbtnAjouterAbsence;
	private JPanel panel_Modifier_Absence;
	private JLabel lblChoisirLaDate_Modifier;
	private JLabel lblChoisir_date_Ajouter;
	private JPanel operation_Ajout;
	private JPanel panel_Liste_Instituteurs;
	private JScrollPane scrollPane_1;
	private JPanel panel_4;
	private JButton button;
	private JPanel panel_Opertion_Ajout_Inst;
	private JLabel label_1;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JPanel panel_Absence_Instituteur;
	private JPanel panel_Ajout_Absence_Ins;
	private JLabel label_8;
	private JDateChooser date_Ajout_Inst;
	private JButton button_1;
	private JRadioButton radioButton_Inst;
	private JRadioButton radioButton_1;
	private JPanel panel_Modifier_Absence_Operation;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JPanel panel_Modifier_Isnt;
	private JDateChooser dateChooser_Modifier;
	private JButton button_2;
	private JLabel label_7;
	private JTable table_Absence_Inst;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final int AJOUT=1;
	private final int MODIF=0;
	public PanelGestionAbsence() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		classeDAO=new ClasseDAO();
		niveauDAO= new NiveauDAO();
		absence_EleveDAO= new Absence_EleveDAO();
		absence_InstituteurDAO= new Absence_InstituteurDAO();
		instituteurDAO= new InstituteurDAO();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 54, 1091, 469);
		add(tabbedPane);
		
		JPanel panel_Eleves = new JPanel();
		tabbedPane.addTab("Absence des Elèves", null, panel_Eleves, null);
		panel_Eleves.setLayout(null);
		
		panel_Liste_Absence = new JPanel();
		panel_Liste_Absence.setBorder(new TitledBorder(null, "Liste Eleves", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Liste_Absence.setBounds(10, 11, 603, 393);
		panel_Eleves.add(panel_Liste_Absence);
		panel_Liste_Absence.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 25, 583, 271);
		panel_Liste_Absence.add(scrollPane);
		
		centerRender= new DefaultTableCellRenderer();
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableModel modelTable= new DefaultTableModel();
		modelTable.addColumn("Num inscription ");
		modelTable.addColumn("Nom");
		modelTable.addColumn("Prenom");
		modelTable.addColumn("Absent");
		modelTable.addColumn("Justifier");
		modelTable.addColumn("Montif");
		
		modelTable.addRow(new Object[]{null,null,null,null,null});
		modelTable.addRow(new Object[]{null,null,null,null,null});
		modelTable.addRow(new Object[]{null,null,null,null,null});
		modelTable.addRow(new Object[]{null,null,null,null,null});
		modelTable.addRow(new Object[]{null,null,null,null,null});
		table_Absence = new JTable();
		table_Absence.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		table_Absence.setFillsViewportHeight(true);
		table_Absence.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Absence.setSelectionBackground(new Color(128, 128, 128));
		table_Absence.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*Jtable not draggble , not resizble*/
		table_Absence.getTableHeader().setReorderingAllowed(false);
		table_Absence.getTableHeader().setResizingAllowed(false);
		//table_Participant.setDefaultEditor(Object.class,null);
		table_Absence.setRowHeight(30);
		
		
		scrollPane.setViewportView(table_Absence);
		
		panel_2 = new JPanel();
		panel_2.setBounds(377, 307, 216, 75);
		panel_Liste_Absence.add(panel_2);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setLayout(null);
		
		btnImprimer = new JButton("imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date =((JTextField)textDate_Ajout.getDateEditor().getUiComponent()).getText();
				if(comboBox_Liste_Classe.getSelectedIndex()!=-1 & (! date.equals(""))  ){
					print();
				}else{
					JOptionPane.showMessageDialog(null, "<html><b><font color=red>Vérifier la date et la Classe !</font></b></html>");
				}
				
			}
		});
		btnImprimer.setFont(new Font("Sitka Text", Font.BOLD, 11));
		btnImprimer.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/print-icon.png")));
		btnImprimer.setBounds(10, 25, 196, 39);
		panel_2.add(btnImprimer);
		
		operation_Ajout = new JPanel();
		operation_Ajout.setBounds(10, 307, 229, 75);
		panel_Liste_Absence.add(operation_Ajout);
		operation_Ajout.setBorder(new TitledBorder(null, "Op\u00E9ration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		operation_Ajout.setLayout(null);
		
		operation_Modif = new JPanel();
		operation_Modif.setBounds(10, 286, 229, 96);
		//panel_Liste_Absence.add(operation_Modif);
		operation_Modif.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mofication", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		operation_Modif.setLayout(null);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Voulez-Vous enregister les modification ?")==JOptionPane.OK_OPTION){
					if(mise_A_Jour(old_Liste(), new_Liste())){
						JOptionPane.showMessageDialog(null, "Mise à jour effectué avec Succèes !");
						panel_Liste_Absence.remove(operation_Modif);
						panel_Liste_Absence.add(operation_Ajout);
						
						vider_Table_Eleve();
						repaint();
					}else{
						JOptionPane.showMessageDialog(null, "Erreur !");
					}
				}
			}
		});
		label_2.setBounds(10, 23, 83, 62);
		operation_Modif.add(label_2);
		label_2.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Ok-icon.png")));
		label_2.setHorizontalTextPosition(SwingConstants.CENTER);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblValider = new JLabel("Valider");
		lblValider.setBounds(10, 71, 83, 14);
		operation_Modif.add(lblValider);
		lblValider.setHorizontalAlignment(SwingConstants.CENTER);
		lblValider.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		
		label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vider_Table_Eleve();
				panel_Liste_Absence.remove(operation_Modif);
				panel_Liste_Absence.add(operation_Ajout);
				vider_Table_Eleve();
				repaint();
			}
		});
		label_3.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Close-icon.png")));
		label_3.setHorizontalTextPosition(SwingConstants.CENTER);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(108, 23, 83, 62);
		operation_Modif.add(label_3);
		
		JLabel lblAnnuler_1 = new JLabel("Annuler");
		lblAnnuler_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler_1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAnnuler_1.setBounds(108, 71, 83, 14);
		operation_Modif.add(lblAnnuler_1);
		
		label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(((JTextField)textDate_Ajout.getDateEditor().getUiComponent()).getText().equals("")){
					JOptionPane.showMessageDialog(null, "<html><b><font color=red>IMPOSSIBLE !</font></b></html>");
					
				}
				else if(JOptionPane.showConfirmDialog(null, "Voulez-Vous ajouter ces Absences ? ")==JOptionPane.OK_OPTION){
						if(new_Liste().size()==0){
						JOptionPane.showMessageDialog(null, "Acune Absence marquée !");
					}else if(ajouter_Les_Absence(new_Liste())){
						JOptionPane.showMessageDialog(null, "Ajout effectué avec succès !");
						
						vider_Table_Eleve();
					}
				}
				
				
			}
		});
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/plus.png")));
		label.setBounds(10, 11, 83, 56);
		operation_Ajout.add(label);
		
		JLabel label_Annuler_Ajout = new JLabel("");
		label_Annuler_Ajout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//table_Absence.setModel(new MaTableModel(new Object[][] {null, null, null, null, null}));
				vider_Table_Eleve();
			}
		});
		label_Annuler_Ajout.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Close-icon.png")));
		label_Annuler_Ajout.setHorizontalTextPosition(SwingConstants.CENTER);
		label_Annuler_Ajout.setHorizontalAlignment(SwingConstants.CENTER);
		label_Annuler_Ajout.setBounds(110, 11, 83, 56);
		operation_Ajout.add(label_Annuler_Ajout);
		
		JLabel lblAnnuler = new JLabel("Annuler");
		lblAnnuler.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnuler.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAnnuler.setBounds(110, 51, 83, 14);
		operation_Ajout.add(lblAnnuler);
		
		JLabel lblAjouter = new JLabel("Ajouter");
		lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjouter.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblAjouter.setBounds(10, 51, 83, 14);
		operation_Ajout.add(lblAjouter);
		
		panel_Absence = new JPanel();
		panel_Absence.setBorder(new TitledBorder(null, "Absence", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Absence.setBounds(623, 11, 456, 393);
		panel_Eleves.add(panel_Absence);
		panel_Absence.setLayout(null);
		
		comboBox_Liste_Classe = new JComboBox();
		comboBox_Liste_Classe.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		comboBox_Liste_Classe.setBounds(150, 56, 188, 28);
		chargerCombo_Classe();
		panel_Absence.add(comboBox_Liste_Classe);
		
		JLabel lblChoirLaClasse = new JLabel("<html>Choisir la Classe : <font color= 'red'> * </font></html>");
		lblChoirLaClasse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblChoirLaClasse.setBounds(12, 58, 128, 25);
		panel_Absence.add(lblChoirLaClasse);
		
		JPanel panel_Ajout_Absence = new JPanel();
		panel_Ajout_Absence.setBorder(new TitledBorder(null, "Ajouter_Absence", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Ajout_Absence.setBounds(10, 206, 436, 176);
		panel_Absence.add(panel_Ajout_Absence);
		panel_Ajout_Absence.setLayout(null);
		
		lblChoisir_date_Ajouter = new JLabel("<html>Choisir la Date  : <font color= 'red'> * </font></html>");
		lblChoisir_date_Ajouter.setBounds(37, 64, 128, 28);
		panel_Ajout_Absence.add(lblChoisir_date_Ajouter);
		lblChoisir_date_Ajouter.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		
		textDate_Ajout = new JDateChooser();
		//textDate_Ajout.getDateEditor().setEnabled(false);
		((JTextField)textDate_Ajout.getDateEditor().getUiComponent()).setEditable(false);
		textDate_Ajout.setBounds(192, 64, 162, 28);
		panel_Ajout_Absence.add(textDate_Ajout);
		textDate_Ajout.setDateFormatString("dd/MM/yyyy");
		
		btnCharger_Ajout = new JButton("<html><center>Charger<br>la Liste</center></html> ");
		btnCharger_Ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nom_Classe=(String)comboBox_Liste_Classe.getSelectedItem();
				Long id_Classe= classeDAO.findByNom(nom_Classe).getNum_Clase();
				String dateAbsence=((JTextField)textDate_Ajout.getDateEditor().getUiComponent()).getText();
				
				if(dateAbsence.equals("")){
					JOptionPane.showMessageDialog(null, "Choisir la Date SVP !");
					// On recherhe s'il y a deja une absence à cette date
				}else if(absence_EleveDAO.liste_Absence_Num_Classe_Date(id_Classe,dateAbsence).size()!=0){
					JOptionPane.showMessageDialog(null, "Fiche Absence Déjà Existante");
					vider_Table_Eleve();
						}
						else{
								panel_Liste_Absence.remove(operation_Modif);
								panel_Liste_Absence.add(operation_Ajout);
								repaint();
								charger_Table_Pour_Ajout_Absence();
							}
			}
		});
		btnCharger_Ajout.setBounds(189, 119, 109, 35);
		panel_Ajout_Absence.add(btnCharger_Ajout);
		btnCharger_Ajout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCharger_Ajout.setHorizontalAlignment(SwingConstants.LEFT);
		btnCharger_Ajout.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Refresh-icon (1).png")));
		
		panel_Modifier_Absence = new JPanel();
		panel_Modifier_Absence.setBorder(new TitledBorder(null, "Modifier Absence", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Modifier_Absence.setBounds(10, 206, 436, 176);
		//panel_Absence.add(panel_Modifier_Absence);
		panel_Modifier_Absence.setLayout(null);
		
		txtDate_Modifier = new JDateChooser();
		((JTextField)txtDate_Modifier.getDateEditor().getUiComponent()).setEditable(false);	
		txtDate_Modifier.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		txtDate_Modifier.setFocusTraversalKeysEnabled(false);
		txtDate_Modifier.setBounds(192, 64, 162, 28);
		panel_Modifier_Absence.add(txtDate_Modifier);
		txtDate_Modifier.setDateFormatString("dd/MM/yyyy");
		
		JButton button_Char_Modifier = new JButton("<html><center>Charger<br>la Liste</center></html> ");
		button_Char_Modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nom_Classe=(String)comboBox_Liste_Classe.getSelectedItem();
				Long id_Classe= classeDAO.findByNom(nom_Classe).getNum_Clase();
				
				String dateAbsence=((JTextField)txtDate_Modifier.getDateEditor().getUiComponent()).getText();
				if(dateAbsence.equals("")){
					JOptionPane.showMessageDialog(null, "Choisir la Date SVP !");
				}else if(absence_EleveDAO.liste_Absence_Num_Classe_Date(id_Classe,dateAbsence).size()==0){
							JOptionPane.showMessageDialog(null, "Désolé pas d'absence !");
							vider_Table_Eleve();
						}else{
								panel_Liste_Absence.remove(operation_Ajout);
								panel_Liste_Absence.add(operation_Modif);
								charger_Table_Pour_Modifier_Absence();
								repaint();
				}			
			}
		});
		button_Char_Modifier.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Refresh-icon (1).png")));
		button_Char_Modifier.setBounds(189, 119, 109, 35);
		panel_Modifier_Absence.add(button_Char_Modifier);
		button_Char_Modifier.setHorizontalAlignment(SwingConstants.LEFT);
		button_Char_Modifier.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblChoisirLaDate_Modifier = new JLabel("<html>Choisir la Date   : <font color= 'red'> * </font></html>");
		lblChoisirLaDate_Modifier.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblChoisirLaDate_Modifier.setBounds(37, 64, 128, 28);
		panel_Modifier_Absence.add(lblChoisirLaDate_Modifier);
		
		rdbtnAjouterAbsence = new JRadioButton("Ajouter Absence");
		buttonGroup_2.add(rdbtnAjouterAbsence);
		rdbtnAjouterAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		rdbtnAjouterAbsence.setSelected(true);
		rdbtnAjouterAbsence.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int state = arg0.getStateChange();
				if(state==ItemEvent.SELECTED){
					try {
						
						panel_Absence.remove(panel_Modifier_Absence);
						panel_Absence.add(panel_Ajout_Absence);
					
						panel_Liste_Absence.remove(operation_Modif);
						panel_Liste_Absence.add(operation_Ajout);
					
						vider_Table_Eleve();
						repaint();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
		});
		
		rdbtnModifierabsence = new JRadioButton("Modifier_Absence");
		buttonGroup_2.add(rdbtnModifierabsence);
		rdbtnModifierabsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		rdbtnModifierabsence.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
					
					
				
			}
		});
		rdbtnModifierabsence.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int state =arg0.getStateChange();
				if(state==ItemEvent.SELECTED){
					try {
						panel_Absence.remove(panel_Ajout_Absence);
						panel_Absence.add(panel_Modifier_Absence);
						vider_Table_Eleve();
						panel_Absence.revalidate();
						repaint();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
		});
		rdbtnModifierabsence.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		rdbtnModifierabsence.setBounds(12, 160, 145, 23);
		panel_Absence.add(rdbtnModifierabsence);
		rdbtnAjouterAbsence.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		rdbtnAjouterAbsence.setBounds(10, 121, 145, 23);
		panel_Absence.add(rdbtnAjouterAbsence);
		
		JPanel panel_Instituteur = new JPanel();
		tabbedPane.addTab("Absence des Instituteurs", null, panel_Instituteur, null);
		panel_Instituteur.setLayout(null);
		
		panel_Liste_Instituteurs = new JPanel();
		panel_Liste_Instituteurs.setLayout(null);
		panel_Liste_Instituteurs.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Liste Instituteurs", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Liste_Instituteurs.setBounds(10, 11, 603, 393);
		panel_Instituteur.add(panel_Liste_Instituteurs);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 25, 583, 271);
		panel_Liste_Instituteurs.add(scrollPane_1);
		
		table_Absence_Inst = new JTable();
		table_Absence_Inst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Absence_Inst.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		table_Absence_Inst.setFillsViewportHeight(true);
		table_Absence_Inst.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Absence_Inst.getTableHeader().setReorderingAllowed(false);
		table_Absence_Inst.getTableHeader().setResizingAllowed(false);
		//table_Participant.setDefaultEditor(Object.class,null);
		table_Absence_Inst.setRowHeight(30);
		scrollPane_1.setViewportView(table_Absence_Inst);
		//scrollPane_1.setColumnHeaderView(table_Absence_Inst);
		//vider_Table_Instituteur();
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(377, 307, 216, 75);
		panel_Liste_Instituteurs.add(panel_4);
		
		button = new JButton("imprimer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date =((JTextField)date_Ajout_Inst.getDateEditor().getUiComponent()).getText().toString();

				if(!date.equals("")){
					print_Instituteur();
				}else{
					JOptionPane.showMessageDialog(null, "Choisir la date !");
				}
				
			}
		});
		button.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/print-icon.png")));
		button.setFont(new Font("Sitka Text", Font.BOLD, 11));
		button.setBounds(10, 25, 196, 39);
		panel_4.add(button);
		
		panel_Modifier_Absence_Operation = new JPanel();
		panel_Modifier_Absence_Operation.setLayout(null);
		panel_Modifier_Absence_Operation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mofication", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Modifier_Absence_Operation.setBounds(10, 286, 229, 96);
		//panel_Liste_Instituteurs.add(panel_Modifier_Absence_Operation);
		
		label_9 = new JLabel("");
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Voulez-Vous enregister les modification ?")==JOptionPane.OK_OPTION){
					if(mise_A_Jour_Instituteur(old_Liste_Institeur(), new_Liste_Isnt())){
						JOptionPane.showMessageDialog(null, "Mise à jour effectué avec Succèes !");
						panel_Liste_Instituteurs.remove(panel_Modifier_Absence_Operation);
						panel_Liste_Instituteurs.add(panel_Opertion_Ajout_Inst);
						
						vider_Table_Instituteur();
						repaint();
					}else{
						JOptionPane.showMessageDialog(null, "Erreur !");
					}
				}
			}
		});
		label_9.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Ok-icon.png")));
		label_9.setHorizontalTextPosition(SwingConstants.CENTER);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(10, 23, 83, 62);
		panel_Modifier_Absence_Operation.add(label_9);
		
		label_10 = new JLabel("Valider");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_10.setBounds(10, 71, 83, 14);
		panel_Modifier_Absence_Operation.add(label_10);
		
		label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Close-icon.png")));
		label_11.setHorizontalTextPosition(SwingConstants.CENTER);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(108, 23, 83, 62);
		panel_Modifier_Absence_Operation.add(label_11);
		
		label_12 = new JLabel("Annuler");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_12.setBounds(108, 71, 83, 14);
		panel_Modifier_Absence_Operation.add(label_12);
		
		panel_Opertion_Ajout_Inst = new JPanel();
		panel_Opertion_Ajout_Inst.setLayout(null);
		panel_Opertion_Ajout_Inst.setBorder(new TitledBorder(null, "Op\u00E9ration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Opertion_Ajout_Inst.setBounds(10, 307, 229, 75);
		panel_Liste_Instituteurs.add(panel_Opertion_Ajout_Inst);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// Ajout Absence Instituteur
				if(((JTextField)date_Ajout_Inst.getDateEditor().getUiComponent()).getText().equals("")){
							JOptionPane.showMessageDialog(null, "<html><font color=red><b>IMPOSSIBLE ! </b></font></html>");
				}else if(JOptionPane.showConfirmDialog(null, "Voulez-Vous ajouter ces Absences ? ")==JOptionPane.OK_OPTION){
						if(new_Liste_Isnt().size()==0){
							JOptionPane.showMessageDialog(null, "Acune Absence marquée !");
				}else if(ajouter_Les_Absence_Instituteur(new_Liste_Isnt())){
							JOptionPane.showMessageDialog(null, "Ajout effectué avec succès !");
							vider_Table_Instituteur();
				}
			}
			}
		});
		label_1.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/plus.png")));
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 11, 83, 56);
		panel_Opertion_Ajout_Inst.add(label_1);
		
		label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vider_Table_Instituteur();
			}
		});
		label_4.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Close-icon.png")));
		label_4.setHorizontalTextPosition(SwingConstants.CENTER);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(110, 11, 83, 56);
		panel_Opertion_Ajout_Inst.add(label_4);
		
		label_5 = new JLabel("Annuler");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_5.setBounds(110, 51, 83, 14);
		panel_Opertion_Ajout_Inst.add(label_5);
		
		label_6 = new JLabel("Ajouter");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		label_6.setBounds(10, 51, 83, 14);
		panel_Opertion_Ajout_Inst.add(label_6);
		
		panel_Absence_Instituteur = new JPanel();
		panel_Absence_Instituteur.setLayout(null);
		panel_Absence_Instituteur.setBorder(new TitledBorder(null, "Absence", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Absence_Instituteur.setBounds(623, 11, 456, 393);
		panel_Instituteur.add(panel_Absence_Instituteur);
		
		panel_Ajout_Absence_Ins = new JPanel();
		panel_Ajout_Absence_Ins.setLayout(null);
		panel_Ajout_Absence_Ins.setBorder(new TitledBorder(null, "Ajouter_Absence", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Ajout_Absence_Ins.setBounds(10, 206, 436, 176);
		panel_Absence_Instituteur.add(panel_Ajout_Absence_Ins);
		
		label_8 = new JLabel("<html>Choisir la Date   : <font color= 'red'> * </font></html>");
		label_8.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_8.setBounds(37, 64, 128, 28);
		panel_Ajout_Absence_Ins.add(label_8);
		
		date_Ajout_Inst = new JDateChooser();
		((JTextField)date_Ajout_Inst.getDateEditor().getUiComponent()).setEditable(false);
		date_Ajout_Inst.setDateFormatString("dd/MM/yyyy");
		date_Ajout_Inst.setBounds(192, 64, 162, 28);
		panel_Ajout_Absence_Ins.add(date_Ajout_Inst);
		
		button_1 = new JButton("<html><center>Charger<br>la Liste</center></html> ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dateAbsence=((JTextField)date_Ajout_Inst.getDateEditor().getUiComponent()).getText();
				
				if(date_Ajout_Inst.equals("")){
					JOptionPane.showMessageDialog(null, "Choisir la Date SVP !");
					// On recherhe s'il y a deja une absence à cette date
				}else if(absence_InstituteurDAO.liste_Absence_Istituteur_Date(dateAbsence).size()!=0){
						JOptionPane.showMessageDialog(null, "Fiche Absence Déjà Existante");
						vider_Table_Eleve();
						}
						else{
								panel_Liste_Instituteurs.remove(panel_Modifier_Absence_Operation);
								panel_Liste_Instituteurs.add(panel_Opertion_Ajout_Inst);
								//vider_Table_Instituteur();
								repaint();
								charger_Table_Pour_Ajout_Absence_Tuteur();
							}
			}
		});	
		button_1.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Refresh-icon (1).png")));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBounds(189, 119, 109, 35);
		panel_Ajout_Absence_Ins.add(button_1);
		
		radioButton_Inst = new JRadioButton("Ajouter Absence");
		buttonGroup_3.add(radioButton_Inst);
		radioButton_Inst.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(radioButton_Inst.isSelected()){
					
					panel_Absence_Instituteur.remove(panel_Modifier_Isnt);
					panel_Absence_Instituteur.add(panel_Ajout_Absence_Ins);
					
					panel_Liste_Instituteurs.remove(panel_Modifier_Absence_Operation);
					panel_Liste_Instituteurs.add(panel_Opertion_Ajout_Inst);
					
					
					vider_Table_Instituteur();
					repaint();
			}}
		});
		
		panel_Modifier_Isnt = new JPanel();
		panel_Modifier_Isnt.setLayout(null);
		panel_Modifier_Isnt.setBorder(new TitledBorder(null, "Modifier Absence", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Modifier_Isnt.setBounds(10, 206, 436, 176);
		//panel_Absence_Instituteur.add(panel_Modifier_Isnt);
		
		dateChooser_Modifier = new JDateChooser();
		((JTextField)dateChooser_Modifier.getDateEditor().getUiComponent()).setEditable(false);	
		dateChooser_Modifier.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		dateChooser_Modifier.setFocusTraversalKeysEnabled(false);
		dateChooser_Modifier.setDateFormatString("dd/MM/yyyy");
		dateChooser_Modifier.setBounds(192, 64, 162, 28);
		panel_Modifier_Isnt.add(dateChooser_Modifier);
		
		button_2 = new JButton("<html><center>Charger<br>la Liste</center></html> ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Instituteur
				String dateAbsence=((JTextField)dateChooser_Modifier.getDateEditor().getUiComponent()).getText();
				if(dateAbsence.equals("")){
					JOptionPane.showMessageDialog(null, "Choisir la Date SVP !");
				}else if(absence_InstituteurDAO.liste_Absence_Istituteur_Date(dateAbsence).size()==0){
							JOptionPane.showMessageDialog(null, "Désolé pas d'absence !");
							vider_Table_Eleve();
						}else{
								panel_Liste_Instituteurs.remove(panel_Opertion_Ajout_Inst);
								panel_Liste_Instituteurs.add(panel_Modifier_Absence_Operation);
								charger_Table_Pour_Modifier_Absence_Instituteur();
								repaint();
				}
			}
		});
		button_2.setIcon(new ImageIcon(PanelGestionAbsence.class.getResource("/image/Button-Refresh-icon (1).png")));
		button_2.setHorizontalAlignment(SwingConstants.LEFT);
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBounds(189, 119, 109, 35);
		panel_Modifier_Isnt.add(button_2);
		
		label_7 = new JLabel("Choisir la Date :");
		label_7.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		label_7.setBounds(37, 64, 128, 28);
		panel_Modifier_Isnt.add(label_7);
		radioButton_Inst.setSelected(true);
		radioButton_Inst.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		radioButton_Inst.setBounds(10, 121, 145, 23);
		panel_Absence_Instituteur.add(radioButton_Inst);
		
		radioButton_1 = new JRadioButton("Modifier_Absence");
		buttonGroup_3.add(radioButton_1);
		radioButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(radioButton_1.isSelected()){
					
					panel_Absence_Instituteur.remove(panel_Ajout_Absence_Ins);
					panel_Absence_Instituteur.add(panel_Modifier_Isnt);
				
					vider_Table_Instituteur();
					repaint();
				}
				
			}
		});
		radioButton_1.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		radioButton_1.setBounds(12, 160, 145, 23);
		panel_Absence_Instituteur.add(radioButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 44, 1079, 11);
		add(separator);
		
		JLabel lblGestionDesAbsences = new JLabel("Gestion des Absences");
		lblGestionDesAbsences.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblGestionDesAbsences.setBounds(26, 11, 205, 34);
		add(lblGestionDesAbsences);
		
		vider_Table_Eleve();
		vider_Table_Instituteur();
	}

	public void chargerCombo_Classe(){
		classeDAO=new ClasseDAO();
		Vector<Classe> collection_Classe = classeDAO.findAll();
		for (Classe classe : collection_Classe) {
			comboBox_Liste_Classe.addItem(classe.getNom_Clase());
		}
		
	}
	
	
	
	// La methode pour recuper les la Liste des Absence dans la Table Absence
	public ArrayList<Absence_Eleve> new_Liste(){
		
		 ArrayList<Absence_Eleve> liste_Absent = new ArrayList<Absence_Eleve>();
		 Absence_Eleve absence_Eleve=null;
		 
		  // Ici on recupere ligne par ligne les element de la table 
		 for (int i = 0; i < table_Absence.getRowCount(); i++) {
			 
			 // Ici on verifie si la case absence est cocher
			 if((Boolean)table_Absence.getValueAt(i, 4)){
				 absence_Eleve= new Absence_Eleve();
				 absence_Eleve.setNum_Inscription((Long)table_Absence.getValueAt(i, 0));
				 absence_Eleve.setDate_Absence((String)table_Absence.getValueAt(i, 3));
				 absence_Eleve.setMotif((String)table_Absence.getValueAt(i, 5));
				 liste_Absent.add(absence_Eleve);
			 }
				
		}
		 
		return liste_Absent;
	}
	// La methode pour recuper les la Liste des Absence dans la Table Absence Instituteur
		public ArrayList<Absence_Instituteur> new_Liste_Isnt(){
			
			 ArrayList<Absence_Instituteur> liste_Absent = new ArrayList<Absence_Instituteur>();
			 Absence_Instituteur absence_Instituteur=null;
			 
			  // Ici on recupere ligne par ligne les element de la table 
			 for (int i = 0; i < table_Absence_Inst.getRowCount(); i++) {
				 
				 // Ici on verifie si la case absence est cocher
				 if((Boolean)table_Absence_Inst.getValueAt(i, 4)){
					 absence_Instituteur= new Absence_Instituteur();
					 absence_Instituteur.setId((Long)table_Absence_Inst.getValueAt(i, 0));
					 absence_Instituteur.setDate_Absence((String)table_Absence_Inst.getValueAt(i, 3));
					 absence_Instituteur.setMotif((String)table_Absence_Inst.getValueAt(i, 5));
					 liste_Absent.add(absence_Instituteur);
				 }
					
			}
			 
			return liste_Absent;
		}
		
	
	// Cette methode permet de retourner l'acien Liste qui contient initialement la liste des Absents
	
	public ArrayList<Absence_Eleve> old_Liste(){
		
		absence_EleveDAO= new Absence_EleveDAO();
		classeDAO= new ClasseDAO();
		
		String nom_Classe= (String)comboBox_Liste_Classe.getSelectedItem();
		Long numClasse= classeDAO.findByNom(nom_Classe).getNum_Clase();
		String dateAbsence=((JTextField)txtDate_Modifier.getDateEditor().getUiComponent()).getText();
	
		return absence_EleveDAO.liste_Absence_Num_Classe_Date(numClasse,dateAbsence);	
	}
	// Cette methode permet de retourner l'acien Liste qui contient initialement la liste des Absents
	
		public ArrayList<Absence_Instituteur> old_Liste_Institeur(){			
			
			String dateAbsence=((JTextField)dateChooser_Modifier.getDateEditor().getUiComponent()).getText();
			return absence_InstituteurDAO.liste_Absence_Istituteur_Date(dateAbsence);	
		}
		
	// Cette methode permet de faire la mise à jour les Absence apres la modification des Salles
	public boolean mise_A_Jour(ArrayList<Absence_Eleve> ancien,ArrayList<Absence_Eleve> nouveau){
		
		ArrayList<Long> liste_Identifint_Ancien = new ArrayList<>();
		ArrayList<Long> liste_Identifint_Nouveau = new ArrayList<>();
		Absence_Eleve absence_Eleve=null;
		Absence_Eleve absence_Eleve1=null;
		// Ce boolean permetr de préciser si la mise à jour ç'est bien dérouler
		boolean succees=true;
		
		// La liste des indentifiant pour l'ancinne liste
		for (Absence_Eleve element : ancien) {
			liste_Identifint_Ancien.add(element.getNum_Inscription());
		}
		
		// La liste des indentifiant pour la nouvelle liste
		for (Absence_Eleve element : nouveau) {
			

			liste_Identifint_Nouveau.add(element.getNum_Inscription());
		}
		
		// On commence la mise à jour
		for (Long newelement_Id : liste_Identifint_Nouveau) {
			
			
			// si le nouveau element n'est pas dans la l'ancienne liste alors on l'a crée
			if(!liste_Identifint_Ancien.contains(newelement_Id)){
				// On recupere Un objet de type Absence Dans la liste Nouvelle Pour l'a créer
				absence_Eleve=obtenir_Absence_Liste(nouveau,newelement_Id);
				if(!absence_EleveDAO.create(absence_Eleve)){
					succees=false;
				}
				
				// S'il est dans l'ancien et nouveau en meme temps on condere le motif du nouveau
			}else if(liste_Identifint_Ancien.contains(newelement_Id) && liste_Identifint_Nouveau.contains(newelement_Id)){
				
				//absence_Eleve=obtenir_Absence_Liste(ancien,newelement_Id);
				absence_Eleve1=obtenir_Absence_Liste(nouveau,newelement_Id);
				// Lancien motif est diffenrent du nouveau motif on fait un update
				if(!absence_EleveDAO.update(absence_Eleve1))
					succees=false;

			}		
		}
		
		for (Long ancienelement_id : liste_Identifint_Ancien) {
			
			
			// si l'ancien  element n'est pas dans la nouvelle liste alors on l'a supprime
			if(!liste_Identifint_Nouveau.contains(ancienelement_id)){
				
				// On recupere Un objet de type Absence Dans la liste Ancienne pour la supprimer
				absence_Eleve=obtenir_Absence_Liste(ancien,ancienelement_id);
				
				if(!absence_EleveDAO.delete(absence_Eleve)){
					succees=false;
				}
			}
		}
		
		// Fin Mise à Jour
		return succees;
	}
	// Cette methode permet de faire la mise à jour les Absence apres la modification des Salles
		public boolean mise_A_Jour_Instituteur(ArrayList<Absence_Instituteur> ancien,ArrayList<Absence_Instituteur> nouveau){
			
			ArrayList<Long> liste_Identifint_Ancien = new ArrayList<>();
			ArrayList<Long> liste_Identifint_Nouveau = new ArrayList<>();
			Absence_Instituteur absence_Instituteur=null;
			Absence_Instituteur absence_Instituteur1=null;
			// Ce boolean permetr de préciser si la mise à jour ç'est bien dérouler
			boolean succees=true;
			
			// La liste des indentifiant pour l'ancinne liste
			for (Absence_Instituteur element : ancien) {
				liste_Identifint_Ancien.add(element.getId());
			}
			
			// La liste des indentifiant pour la nouvelle liste
			for (Absence_Instituteur element : nouveau) {
				

				liste_Identifint_Nouveau.add(element.getId());
			}
			
			// On commence la mise à jour
			for (Long newelement_Id : liste_Identifint_Nouveau) {
				
				
				// si le nouveau element n'est pas dans la l'ancienne liste alors on l'a crée
				if(!liste_Identifint_Ancien.contains(newelement_Id)){
					// On recupere Un objet de type Absence Dans la liste Nouvelle Pour l'a créer
					absence_Instituteur=obtenir_Absence_Liste_Instituteur(nouveau,newelement_Id);
					if(!absence_InstituteurDAO.create(absence_Instituteur)){
						succees=false;
					}
					
					// S'il est dans l'ancien et nouveau en meme temps on condere le motif du nouveau
				}else if(liste_Identifint_Ancien.contains(newelement_Id) && liste_Identifint_Nouveau.contains(newelement_Id)){
					
					//absence_Eleve=obtenir_Absence_Liste(ancien,newelement_Id);
					absence_Instituteur1=obtenir_Absence_Liste_Instituteur(nouveau,newelement_Id);
					// Lancien motif est diffenrent du nouveau motif on fait un update
					if(!absence_InstituteurDAO.update(absence_Instituteur1))
						succees=false;

				}		
			}
			
			for (Long ancienelement_id : liste_Identifint_Ancien) {
				
				
				// si l'ancien  element n'est pas dans la nouvelle liste alors on l'a supprime
				if(!liste_Identifint_Nouveau.contains(ancienelement_id)){
					
					// On recupere Un objet de type Absence Dans la liste Ancienne pour la supprimer
					absence_Instituteur=obtenir_Absence_Liste_Instituteur(ancien,ancienelement_id);
					
					if(!absence_InstituteurDAO.delete(absence_Instituteur)){
						succees=false;
					}
				}
			}
			
			// Fin Mise à Jour
			return succees;
		}
	
	// Cette metode permet de retourner une absence à partir d'une liste 
	public Absence_Eleve obtenir_Absence_Liste(ArrayList<Absence_Eleve> liste,Long id){
		Absence_Eleve absence_Eleve = null;
		for (Absence_Eleve element : liste) {
			if((long)element.getNum_Inscription()==(long)id){
				absence_Eleve= element;
			}
				
		}
		return absence_Eleve;
	}
	public Absence_Instituteur obtenir_Absence_Liste_Instituteur(ArrayList<Absence_Instituteur> liste,Long id){
		Absence_Instituteur absence_Instituteur= null;
		for (Absence_Instituteur element : liste) {
			if((long)element.getId()==(long)id){
				absence_Instituteur= element;
			}
				
		}
		return absence_Instituteur;
	}
	// Cette methode permet d'ajouter les absence a partir une liste passée en parametre
	public boolean ajouter_Les_Absence(ArrayList<Absence_Eleve> liste_Absence){
		
		absence_EleveDAO =  new Absence_EleveDAO();
		for (Absence_Eleve absence_Eleve : liste_Absence) {
			
			if(!absence_EleveDAO.create(absence_Eleve))
				return false;
		}
		return true;
	}
	
	// Cette methode permet d'ajouter les absence a partir une liste passée en parametre (Instituteur)
	public boolean ajouter_Les_Absence_Instituteur(ArrayList<Absence_Instituteur> liste_Absence){
			
			
			for (Absence_Instituteur absence_Eleve : liste_Absence) {
				
				if(!absence_InstituteurDAO.create(absence_Eleve))
					return false;
			}
			return true;
	}
		
	/* -------------Cette methode permet de faire la mise des propriete de la table  Absence ----------*/
	public void changer_Propriete_Table_Absence( JTable table){
		
		table.getColumnModel().getColumn(0).setMinWidth(170);
		table.getColumnModel().getColumn(0).setMaxWidth(170);
		
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		
		table.getColumnModel().getColumn(2).setMinWidth(170);
		table.getColumnModel().getColumn(2).setMaxWidth(170);
		
		table.getColumnModel().getColumn(3).setMinWidth(150);
		table.getColumnModel().getColumn(3).setMaxWidth(150);
		
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		
		for (int i = 0; i < 4; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
			table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
		}
		table.getTableHeader().setDefaultRenderer(centerRender);
		table.revalidate();
		
	}
	// Cetemethode permet de chager La table Absence pour ajout des Absence 
	public void charger_Table_Pour_Ajout_Absence(){
		
		classeDAO=new ClasseDAO();
		eleveDAO= new EleveDAO();
		
		String nom_Classe=(String)comboBox_Liste_Classe.getSelectedItem();
		Long id_Classe= classeDAO.findByNom(nom_Classe).getNum_Clase();
		Vector<Eleve> collectionEleve= eleveDAO.findByNumClasse(id_Classe);
		String dateAbsence=((JTextField)textDate_Ajout.getDateEditor().getUiComponent()).getText();
		String[] columns = {
				"NUMERO D'INSCRIPTION",
				"NOM",
				"PRENOM",
				"DATE D'ABSENCE",
				"ABSENT",
				"MOTIF"
		};
		Object[][] data= new Object[collectionEleve.size()][6];
		for (int i = 0; i < data.length; i++) {
			data[i][0]=collectionEleve.get(i).getNum_ins();
			data[i][1]=collectionEleve.get(i).getNom();
			data[i][2]=collectionEleve.get(i).getPrenom();
			data[i][3]=dateAbsence;
			data[i][4]=Boolean.FALSE;// Present
			data[i][5]="";// Motif
		}
		table_Absence.setModel(new MaTableModel(data,columns)); 
		changer_Propriete_Table_Absence(table_Absence);
	}
	
	// Cetemethode permet de chager La table Absence pour ajout des Absence 
		public void charger_Table_Pour_Ajout_Absence_Tuteur(){
			
			Vector<Instituteur> collection_Instituteur=instituteurDAO.findAll();
			String dateAbsence=((JTextField)date_Ajout_Inst.getDateEditor().getUiComponent()).getText();
			String[] columns = {
					"NUMERO CIN",
					"NOM",
					"PRENOM",
					"DATE D'ABSENCE",
					"ABSENT",
					"MOTIF"
			};
			Object[][] data= new Object[collection_Instituteur.size()][6];
			for (int i = 0; i < data.length; i++) {
				data[i][0]=collection_Instituteur.get(i).getId();
				data[i][1]=collection_Instituteur.get(i).getNom();
				data[i][2]=collection_Instituteur.get(i).getPrenom();
				data[i][3]=dateAbsence;
				data[i][4]=Boolean.FALSE;// Present
				data[i][5]="";// Motif
			}
			table_Absence_Inst.setModel(new MaTableModel(data, columns)); 
			changer_Propriete_Table_Absence(table_Absence_Inst);
		}
	
	// Cette methode permet de chager la table Absence pour la modifiacation
	public void charger_Table_Pour_Modifier_Absence(){
		classeDAO=new ClasseDAO();
		eleveDAO= new EleveDAO();
		Absence_Eleve absence_Elevee;
		absence_EleveDAO = new Absence_EleveDAO();
		ArrayList<Long> liste_Identifiant =new ArrayList<Long>();
		String nom_Classe=(String)comboBox_Liste_Classe.getSelectedItem();
		Long id_Classe= classeDAO.findByNom(nom_Classe).getNum_Clase();
		// Collection des eleve
		Vector<Eleve> collectionEleve= eleveDAO.findByNumClasse(id_Classe);
		String dateAbsence=((JTextField)txtDate_Modifier.getDateEditor().getUiComponent()).getText();
		
		// Liste des absent pour ctte date our pour la classe
		ArrayList<Absence_Eleve> liste_Absence=absence_EleveDAO.liste_Absence_Num_Classe_Date(id_Classe,dateAbsence);
		// On recupere la liste des Indentifiant des Absent
		for (Absence_Eleve absence_Eleve : liste_Absence) {
			liste_Identifiant.add(absence_Eleve.getNum_Inscription());
		}
		String[] columns = {
				"NUMERO D'INSCRIPTION",
				"NOM",
				"PRENOM",
				"DATE D'ABSENCE",
				"ABSENT",
				"MOTIF"
		};
		Object[][] data= new Object[collectionEleve.size()][6];
		for (int i = 0; i < data.length; i++) {
			
			data[i][0]=collectionEleve.get(i).getNum_ins();
			data[i][1]=collectionEleve.get(i).getNom();
			data[i][2]=collectionEleve.get(i).getPrenom();
			data[i][3]=dateAbsence;
			
			if(liste_Identifiant.contains(collectionEleve.get(i).getNum_ins())){
				data[i][4]=Boolean.TRUE;// Absent
				absence_Elevee=absence_EleveDAO.find(collectionEleve.get(i).getNum_ins(),dateAbsence);
				data[i][5]=absence_Elevee.getMotif();// Motif
			}
				
			else{
				data[i][4]=Boolean.FALSE;// Present
				data[i][5]="";// Motif
			}
				
			
		}
		table_Absence.setModel(new MaTableModel(data,columns)); 
		table_Absence.repaint();
		changer_Propriete_Table_Absence(table_Absence);
		
	}
	
	// Cette methode permet de chager la table Absence pour la modifiacation
		public void charger_Table_Pour_Modifier_Absence_Instituteur(){
			
			Absence_Instituteur absence_Elevee;
			
			ArrayList<Long> liste_Identifiant =new ArrayList<Long>();	
			// Collection des Instituteurs
			ArrayList<Instituteur> collectionInstituteur= instituteurDAO.findAllV();
			
			String dateAbsence=((JTextField)dateChooser_Modifier.getDateEditor().getUiComponent()).getText();
			
			// Liste des absent pour ctte date our pour la classe
			ArrayList<Absence_Instituteur> liste_Absence=absence_InstituteurDAO.liste_Absence_Istituteur_Date(dateAbsence);
			// On recupere la liste des Indentifiant des Absent
			for (Absence_Instituteur absence_Eleve : liste_Absence) {
				liste_Identifiant.add(absence_Eleve.getId());
			}
			String[] columns = {
					"NUMERO CIN",
					"NOM",
					"PRENOM",
					"DATE D'ABSENCE",
					"ABSENT",
					"MOTIF"
			};
			Object[][] data= new Object[collectionInstituteur.size()][6];
			for (int i = 0; i < data.length; i++) {
				
				data[i][0]=collectionInstituteur.get(i).getId();
				data[i][1]=collectionInstituteur.get(i).getNom();
				data[i][2]=collectionInstituteur.get(i).getPrenom();
				data[i][3]=dateAbsence;
				
				if(liste_Identifiant.contains(collectionInstituteur.get(i).getId())){
					data[i][4]=Boolean.TRUE;// Absent
					absence_Elevee=absence_InstituteurDAO.find(collectionInstituteur.get(i).getId(),dateAbsence);
					data[i][5]=absence_Elevee.getMotif();// Motif
				}
					
				else{
					data[i][4]=Boolean.FALSE;// Present
					data[i][5]="";// Motif
				}
					
				
			}
			table_Absence_Inst.setModel(new MaTableModel(data,columns)); 
			table_Absence_Inst.repaint();
			changer_Propriete_Table_Absence(table_Absence_Inst);
			
		}
	
	// Cette methode permet de vider le tableau 
	
	public void vider_Table_Eleve(){
		table_Absence.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
					{null, null, null,  null,null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
						"NUMERO D'INSCRIPTION",
						"NOM",
						"PRENOM",
						"DATE D'ABSENCE",
						"ABSENT",
						"MOTIF"
				}));
		((JTextField)txtDate_Modifier.getDateEditor().getUiComponent()).setText("");
		((JTextField)textDate_Ajout.getDateEditor().getUiComponent()).setText("");
		changer_Propriete_Table_Absence(table_Absence);
	}
	
	// Cette methode permet de vider le tableau 
		public void vider_Table_Instituteur(){
			table_Absence_Inst.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null},
						{null, null, null,  null,null, null},
						{null, null, null, null, null, null},
					},
					new String[] {
							"NUMERO CIN",
							"NOM",
							"PRENOM",
							"DATE D'ABSENCE",
							"ABSENT",
							"MOTIF"
					}));
			((JTextField)dateChooser_Modifier.getDateEditor().getUiComponent()).setText("");
			((JTextField)date_Ajout_Inst.getDateEditor().getUiComponent()).setText("");
			changer_Propriete_Table_Absence(table_Absence_Inst);
		}
		
	class MaTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String[] columns =null;
		Object[][] data =null;
		
		public MaTableModel(Object[][] data,String[] columns) {
			super();
			//this.columns = columns;
			this.data = data;
			this.columns= columns;
		}
		public int getRowCount() { 
			return data.length;
			} 
		public int getColumnCount() {
			return columns.length;
			} 
		@Override 
		public boolean isCellEditable(int rowIndex,int columnIndex){
			
			
			return true;
		}	
		@Override
		public void setValueAt(Object value,int rowindex,int columnidex){
			
			    if(columnidex==4 || columnidex==5){
			    	data[rowindex][columnidex]=value;
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
			
			//return data[0][columnIndex].getClass(); 
			if(columnIndex==4)
				return Boolean.class;
			else
				return super.getColumnClass(columnIndex);
		
			
			}
		}
	/*----------------------- Cette method permet de faire Impression ----------------------------*/
	public void print(){

		org.apache.log4j.BasicConfigurator.configure();
		Map param = new HashMap();
		String nom_Classe=comboBox_Liste_Classe.getSelectedItem().toString();
		Integer  Id_Conseil_Classe=Integer.parseInt(classeDAO.findByNom(nom_Classe).getNum_Clase().toString());
		String date =((JTextField)textDate_Ajout.getDateEditor().getUiComponent()).getText().toString();
		param.put("id_Classe", Id_Conseil_Classe);
		param.put("date_Absence", date);
		try{
			 
			JasperReport report = JasperCompileManager.compileReport("Rapport\\Liste_Des_Absence_Eleve.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, param, new Connexion().getConnection());
			JasperViewer.viewReport(print,false);
	        
	        }
		 catch(Exception e)
	    {
	           JOptionPane.showMessageDialog(null, e);

	    }

	}
	/*----------------------- Cette method permet de faire Impression ----------------------------*/
	public void print_Instituteur(){

		org.apache.log4j.BasicConfigurator.configure();
		Map param = new HashMap();
		String date =((JTextField)date_Ajout_Inst.getDateEditor().getUiComponent()).getText().toString();
		param.put("date_Absence", date);
		try{
			 
			JasperReport report = JasperCompileManager.compileReport("Rapport\\Liste_Des_Absence_Instuteur.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, param, new Connexion().getConnection());
			JasperViewer.viewReport(print,false);
	        
	        }
		 catch(Exception e)
	    {
	           JOptionPane.showMessageDialog(null, e);

	    }

	}
	public static void main(String[] args) {
		JFrame frame= new JFrame();
		frame.getContentPane().add(new PanelGestionAbsence(),BorderLayout.CENTER);
		frame.setBounds(100, 100, 1500, 726);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
