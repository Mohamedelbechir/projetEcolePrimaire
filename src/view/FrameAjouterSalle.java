package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import classeMetier.Salle;
import connxion_Requete.Connexion;
import dao.implement.SalleDAO;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameAjouterSalle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Nom_Salle;
	private JTextField textField_Capacite_Salle;
	private JTextField textField_Identifiant_Salle;
	private PanelGestionSalle panel_Gestion_Salle;
	private JLabel errNonSalle;
	private JLabel errCapSalle;
	private JLabel errId;
	private SalleDAO salleDAO;
	/**
	 * Launch the application.
	 * @param panel_Gestion_Salle 
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAjouterSalle frame = new FrameAjouterSalle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameAjouterSalle(PanelGestionSalle panel_Gestion_Salle) {
		setAlwaysOnTop(true);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Ajout_Salle");
		
		salleDAO= new SalleDAO();
		this.panel_Gestion_Salle= panel_Gestion_Salle;
		setBounds(100, 100, 557, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information de la Salle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(42, 58, 451, 324);
		contentPane.add(panel);
		
		JLabel lblNomDeLa = new JLabel("<html>Nom de la Salle : <font color='red'> *</font></html> ");
		lblNomDeLa.setBounds(20, 61, 108, 14);
		panel.add(lblNomDeLa);
		
		textField_Nom_Salle = new JTextField();
		textField_Nom_Salle.setColumns(10);
		textField_Nom_Salle.setBounds(167, 58, 201, 28);
		panel.add(textField_Nom_Salle);
		
		textField_Capacite_Salle = new JTextField();
		textField_Capacite_Salle.setColumns(10);
		textField_Capacite_Salle.setBounds(167, 104, 128, 28);
		panel.add(textField_Capacite_Salle);
		
		JLabel label_3 = new JLabel("<html>Capacit\u00E9 : <font color= 'red'> * </font></html>");
		label_3.setBounds(20, 111, 93, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("<html>Identifiant de la Sallle :<font color='red'> *</font></html> ");
		label_4.setBounds(19, 27, 131, 14);
		panel.add(label_4);
		
		textField_Identifiant_Salle = new JTextField();
		textField_Identifiant_Salle.setColumns(10);
		textField_Identifiant_Salle.setBounds(167, 24, 201, 28);
		panel.add(textField_Identifiant_Salle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Validation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(162, 208, 231, 94);
		panel.add(panel_1);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnRetour.setIcon(new ImageIcon(FrameAjouterSalle.class.getResource("/image/Fairytale_undo.png")));
		btnRetour.setBounds(132, 36, 89, 42);
		panel_1.add(btnRetour);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(validation()){
					salleDAO.create(creer_Objet_Salle_Saisi_Champ());
					JOptionPane.showMessageDialog(null, "La Salle est crée avec succès !");
					vider_champ();
				}
			}
		});
		btnAjouter.setIcon(new ImageIcon(FrameAjouterSalle.class.getResource("/image/Ok-icon.png")));
		btnAjouter.setBounds(10, 36, 89, 42);
		panel_1.add(btnAjouter);
		
		errId = new JLabel("");
		errId.setVisible(false);
		errId.setIcon(new ImageIcon(FrameAjouterSalle.class.getResource("/image/error.png")));
		errId.setBounds(371, 27, 22, 25);
		panel.add(errId);
		
		errNonSalle = new JLabel("");
		errNonSalle.setVisible(false);
		errNonSalle.setIcon(new ImageIcon(FrameAjouterSalle.class.getResource("/image/error.png")));
		errNonSalle.setBounds(371, 61, 22, 25);
		panel.add(errNonSalle);
		
		errCapSalle = new JLabel("");
		errCapSalle.setVisible(false);
		errCapSalle.setIcon(new ImageIcon(FrameAjouterSalle.class.getResource("/image/error.png")));
		errCapSalle.setBounds(299, 104, 22, 28);
		panel.add(errCapSalle);
		
		JLabel lblChampObligatoire = new JLabel("* Champ Obligatoire");
		lblChampObligatoire.setForeground(Color.RED);
		lblChampObligatoire.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblChampObligatoire.setBounds(42, 30, 197, 27);
		contentPane.add(lblChampObligatoire);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	/*------------ La permettant de créer un objet Salle à partir des champs text--------------*/
	public Salle creer_Objet_Salle_Saisi_Champ(){
		Salle salle = null;
		if(!(textField_Identifiant_Salle.getText().equals("") || textField_Nom_Salle.getText().equals("") || textField_Capacite_Salle.getText().equals(""))){
			
			salle = new Salle();
			salle.setId_SALLE(Long.parseLong(textField_Identifiant_Salle.getText()));
			salle.setNom_SALLE(textField_Nom_Salle.getText());
			salle.setCapacite(Integer.parseInt(textField_Capacite_Salle.getText()));
		}
		return salle;
	}
	/* methode pour vider le champs */
	public void vider_champ(){
		textField_Identifiant_Salle.setText("");
		textField_Nom_Salle.setText("");
		textField_Capacite_Salle.setText("");
		errCapSalle.setVisible(false);
		errId.setVisible(false);
		errNonSalle.setVisible(false);
	}
	

	/* ----------------------Cette methode permet de Verifier les champs de Saisie utilse pour la mise à jour d'un salle-----------*/
	public boolean validation(){
		boolean bol = true;
		String regexNom ="^[a-zA-Z\\s]+[0-9]*[a-zA-Z\\s]*$";
		
		// la Verification  d'identifiant 
		
		// L'identifiant ne doit pas depasser 4 chiffres
		if(textField_Identifiant_Salle.getText().length()>4 || textField_Identifiant_Salle.getText().equals("") ){
			errId.setVisible(true);
			errId.setToolTipText("Donnné un identifiant à  4 chiffres max");
					bol=false;
		}else
			errId.setVisible(false);
		
		try {
			if(salleDAO.find(Long.parseLong(textField_Identifiant_Salle.getText()))!=null){
				errId.setToolTipText("Identifiant existe déja");
				bol=false;
				errId.setVisible(true);
			}
		}catch (NumberFormatException e) {
			errId.setToolTipText("Desolé pas chaine de character");
			bol=false;
			errId.setVisible(true);
		}
		
		
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
		
			
		return bol;
	}
}
