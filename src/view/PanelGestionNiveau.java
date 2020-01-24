package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.record.PageBreakRecord.Break;

import classeMetier.Niveau;
import classeMetier.Salle;
import dao.implement.MatiereDAO;
import dao.implement.NiveauDAO;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PanelGestionNiveau extends JPanel {
	private JTable table_Niveau;
	private NiveauDAO niveauDAO;
	private MatiereDAO matiereDAO;
	private DefaultTableCellRenderer centerRender; 
	private Vector<String> oldList;
	private Vector<String> newList;
	private JCheckBox chckbx1e;
	private JCheckBox chckbx2e;
	private JCheckBox chckbx3e;
	private JCheckBox chckbx4e;
	private JCheckBox chckbx5e;
	private JCheckBox chckbx6e;
	private Vector<JCheckBox> listChchckbx;
	private final int LIER=1;
	private final int NONLIER=0;
	public PanelGestionNiveau() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		
		niveauDAO=new NiveauDAO();
		oldList= new Vector<>();
		newList= new Vector<>();
		matiereDAO= new MatiereDAO();
		listChchckbx=new Vector<>();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Les Niveaux de l'Ecole", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(113, 80, 913, 399);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "La liste des Niveaux", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(94, 46, 244, 305);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 224, 266);
		panel_1.add(scrollPane);
		
		table_Niveau = new JTable();
		table_Niveau.setRowSelectionAllowed(false);
		table_Niveau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Niveau.setSelectionBackground(new Color(128, 128, 128));
		table_Niveau.setRowHeight(30);
		table_Niveau.setDefaultEditor(Object.class,null);
		table_Niveau.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		scrollPane.setViewportView(table_Niveau);
		
		centerRender= new DefaultTableCellRenderer() ;
		centerRender.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Gestion_Niveau", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(467, 46, 360, 305);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		chckbx1e = new JCheckBox("1\u00E8re ann\u00E9e");
		chckbx1e.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		chckbx1e.setBounds(56, 40, 147, 23);
		listChchckbx.add(chckbx1e);
		panel_2.add(chckbx1e);
		
		chckbx2e = new JCheckBox("2\u00E8me ann\u00E9e");
		chckbx2e.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		chckbx2e.setBounds(55, 91, 148, 23);
		listChchckbx.add(chckbx2e);
		panel_2.add(chckbx2e);
		
		chckbx3e = new JCheckBox("3\u00E8me ann\u00E9e");
		chckbx3e.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		chckbx3e.setBounds(56, 146, 147, 23);
		listChchckbx.add(chckbx3e);
		panel_2.add(chckbx3e);
		
		chckbx4e = new JCheckBox("4\u00E8me ann\u00E9e");
		chckbx4e.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		chckbx4e.setBounds(205, 40, 125, 23);
		listChchckbx.add(chckbx4e);
		panel_2.add(chckbx4e);
		
		chckbx5e = new JCheckBox("5\u00E8me ann\u00E9e");
		chckbx5e.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		chckbx5e.setBounds(205, 91, 125, 23);
		listChchckbx.add(chckbx5e);
		panel_2.add(chckbx5e);
		
		chckbx6e = new JCheckBox("6\u00E8me ann\u00E9e");
		chckbx6e.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		chckbx6e.setBounds(205, 146, 119, 23);
		listChchckbx.add(chckbx6e);
		panel_2.add(chckbx6e);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 215, 334, 79);
		panel_2.add(panel_3);
		panel_3.setBorder(new TitledBorder(null, "Op\u00E9ration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setLayout(null);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setIcon(new ImageIcon(PanelGestionNiveau.class.getResource("/image/Ok-icon.png")));
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(JOptionPane.showConfirmDialog(null, "Voulez-Vous enregister ces modification ?")==JOptionPane.OK_OPTION)
				{
					int verification =supression_Niveau_Lier();
					switch (verification ) {
					case LIER:
						if(JOptionPane.showConfirmDialog(null, "<html>Attention ce Niveau est lié à d'autres élements<br> sa suppression entraine leur suppression aussi !</html>")!= JOptionPane.OK_OPTION)
							break;
					case NONLIER:	
							if(mise_jourListe()){
								JOptionPane.showMessageDialog(null, "Succèes!");
								charger_Table_Niveau();
								chargerOld_List();
								chargement_chckbx();
							}
							else
								JOptionPane.showMessageDialog(null, "Echec !");
						
							break;
					}
					
				}
			}
		});
		btnValider.setBounds(10, 21, 150, 47);
		panel_3.add(btnValider);
		
		JButton btnRe = new JButton("Annuler");
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				charger_Table_Niveau();
				chargerOld_List();
				chargement_chckbx();
			}
		});
		btnRe.setIcon(new ImageIcon(PanelGestionNiveau.class.getResource("/image/Fairytale_undo.png")));
		btnRe.setBounds(170, 21, 150, 47);
		panel_3.add(btnRe);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 44, 1079, 11);
		add(separator);
		
		JLabel lblGestionDesClasses = new JLabel("Gestion des Niveaux");
		lblGestionDesClasses.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblGestionDesClasses.setBounds(26, 11, 252, 34);
		add(lblGestionDesClasses);
		charger_Table_Niveau();
		chargerOld_List();
		chargement_chckbx();
	}

	public void charger_Table_Niveau(){
		DefaultTableModel  modelEleve= new DefaultTableModel();
		modelEleve.addColumn("id_Niveau");	
		modelEleve.addColumn("Nom du Niveau");			
		Vector<Niveau> collection_niveau ;
		collection_niveau=niveauDAO.findAll();
			
			if(collection_niveau!=null){
				for (Niveau niveau : collection_niveau) {
					
					modelEleve.addRow(new Object[]{niveau.getId_Niveau(),niveau.getNom_Niveau()});	
					
				}
				table_Niveau.setModel(modelEleve);
				proprietetTable();
			}
			
	}

	
	public void proprietetTable(){
		table_Niveau.getColumnModel().getColumn(0).setMinWidth(0);
		table_Niveau.getColumnModel().getColumn(0).setMaxWidth(0);
		table_Niveau.getColumnModel().getColumn(0).setWidth(0);	
		table_Niveau.getColumnModel().getColumn(1).setCellRenderer(centerRender);
		table_Niveau.getTableHeader().setDefaultRenderer(centerRender);
	}
	
	
	// Cette methode permet de charger la nouvelle  liste
	@SuppressWarnings("deprecation")
	public void chargerNew_List(){
		newList.clear();
		for (JCheckBox jCheckBox : listChchckbx) {
			if(jCheckBox.isSelected()){
			newList.addElement(jCheckBox.getLabel());
			}
		}
		
	}
	
	// Cette methode permet de charger l'anncienne liste
	public void chargerOld_List(){
		Vector<Niveau> collectionNiveau=niveauDAO.findAll();
		oldList.clear();
		for (Niveau niveau : collectionNiveau) {
			oldList.addElement(niveau.getNom_Niveau());
		}
	}
	
	// La methode de mise à jour 
	public boolean mise_jourListe(){
		// la mise à jour des listes
		boolean succees=true;
		chargerOld_List();
		chargerNew_List();
		
		Niveau niveau=new Niveau();
		
		for (String newelement : newList) {
			niveau.setNom_Niveau(newelement);
			// si le nouveau element n'est pas dans la l'ancienne liste alors on l'a crée
			if(!oldList.contains(newelement)){
				if(!niveauDAO.create(niveau)){
					succees=false;
				}
			}		
		}
		
		for (String newelement : oldList) {
			niveau.setNom_Niveau(newelement);
			// si l'ancien  element n'est pas dans la nouvelle liste alors on l'a supprime
			if(!newList.contains(newelement)){
				if(!niveauDAO.delete(niveau)){
					succees=false;
				}
			}
		}
		chargerOld_List();
		return succees;
		
	}
	
	// Ici on verifie si le niveau n'est pas affecter à une autre chose
	public int supression_Niveau_Lier(){
		

		chargerOld_List();
		chargerNew_List();
		for (String newelement : oldList) {
			
			if(!newList.contains(newelement)){
				if(matiereDAO.verifier_Presence_Niveau(newelement)!=null){
					
					return LIER;
				}
			}	
			
		}
		
		return NONLIER;
	}
	public void chargement_chckbx(){
		for (JCheckBox jCheckBox : listChchckbx) {
			if (oldList.contains(jCheckBox.getLabel())) {
				
				jCheckBox.setSelected(true);
			}else{
				jCheckBox.setSelected(false);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		 frame.setBounds(100, 100, 1500, 726);
	//	frame.setSize(1000,1000);
		frame.getContentPane().add(new PanelGestionNiveau(), BorderLayout.CENTER );
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		//frame.getContentPane().add(new PanelGestionEleve());

	}
}
