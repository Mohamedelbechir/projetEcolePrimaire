package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.EmailValidator;

import connxion_Requete.Connexion;
import mondrian.olap.type.NumericType;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

public class PanelParametrage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_nomEcole;
	private JTextField textField_Proprietaire;
	private JTextField textField_Adresse;
	private JTextField textField_Ville;
	private JTextField textField_codePostal;
	private JTextField textField_Email;
	private JTextField textField_TelephoneFixe;
	private JTextField textField_TelephonePortable;
	private JLabel errEmail;
	private JLabel errTelFix;
	private JLabel errTelP;
	private JLabel errPro;
	private Main main;
	private JButton btnValider;
	private JButton btnParcourir;
	private JTextField txtpath;
	private String path= null;
	private JLabel image;
    private String filename= null;
    private JPanel panel;
    private JLabel label;
    private JPanel panel_2;
    private JLabel label_1;
    private JPanel panel_3;
    private JPanel panel_4;

	public PanelParametrage(Main main) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(241, 142, 1111, 534);
		setLayout(null);
		this.main=main;
		JPanel panel_Parmetrage_Ecole = new JPanel();
		panel_Parmetrage_Ecole.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_Parmetrage_Ecole.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information sur l'Ecole", TitledBorder.LEFT, TitledBorder.TOP, new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15), new Color(0, 0, 0)));
		panel_Parmetrage_Ecole.setBounds(10, 143, 1091, 307);
		add(panel_Parmetrage_Ecole);
		panel_Parmetrage_Ecole.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom d'Ecole :");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(10, 37, 124, 30);
		panel_Parmetrage_Ecole.add(lblNewLabel);
		
		JLabel lblPropritaire = new JLabel("Propri\u00E9taire :");
		lblPropritaire.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblPropritaire.setBounds(10, 64, 124, 28);
		panel_Parmetrage_Ecole.add(lblPropritaire);
		
		JLabel lblNewLabel_1 = new JLabel("Adresse :");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(10, 91, 124, 29);
		panel_Parmetrage_Ecole.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ville :");
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setBounds(10, 118, 124, 30);
		panel_Parmetrage_Ecole.add(lblNewLabel_2);
		
		textField_nomEcole = new JTextField();
		textField_nomEcole.setBounds(108, 37, 202, 28);
		panel_Parmetrage_Ecole.add(textField_nomEcole);
		textField_nomEcole.setColumns(10);
		
		textField_Proprietaire = new JTextField();
		textField_Proprietaire.setBounds(108, 64, 202, 28);
		panel_Parmetrage_Ecole.add(textField_Proprietaire);
		textField_Proprietaire.setColumns(10);
		
		textField_Adresse = new JTextField();
		textField_Adresse.setBounds(108, 91, 202, 28);
		panel_Parmetrage_Ecole.add(textField_Adresse);
		textField_Adresse.setColumns(10);
		
		textField_Ville = new JTextField();
		textField_Ville.setBounds(108, 118, 202, 28);
		panel_Parmetrage_Ecole.add(textField_Ville);
		textField_Ville.setColumns(10);
		
		JLabel lblCodePostal = new JLabel("Code Postal :");
		lblCodePostal.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblCodePostal.setBounds(395, 37, 137, 30);
		panel_Parmetrage_Ecole.add(lblCodePostal);
		
		JLabel lblEmail = new JLabel(" Email:");
		lblEmail.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblEmail.setBounds(393, 64, 139, 28);
		panel_Parmetrage_Ecole.add(lblEmail);
		
		JLabel lblTlphoneFixe = new JLabel("Téléphone Fixe :");
		lblTlphoneFixe.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblTlphoneFixe.setBounds(395, 91, 137, 29);
		panel_Parmetrage_Ecole.add(lblTlphoneFixe);
		
		JLabel lblTlphonePortable = new JLabel("Téléphone Cellulaire :");
		lblTlphonePortable.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 13));
		lblTlphonePortable.setBounds(395, 117, 149, 31);
		panel_Parmetrage_Ecole.add(lblTlphonePortable);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(540, 37, 202, 28);
		panel_Parmetrage_Ecole.add(panel_4);
		panel_4.setLayout(null);
		
		textField_codePostal = new JTextField();
		textField_codePostal.setBounds(0, 0, 201, 27);
		panel_4.add(textField_codePostal);
		textField_codePostal.setBorder(new LineBorder(new Color(171, 173, 179)));
		textField_codePostal.setColumns(10);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(540, 64, 202, 28);
		panel_Parmetrage_Ecole.add(panel_3);
		panel_3.setLayout(null);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(0, 0, 201, 27);
		panel_3.add(textField_Email);
		textField_Email.setBorder(new LineBorder(new Color(171, 173, 179)));
		textField_Email.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(540, 91, 202, 28);
		panel_Parmetrage_Ecole.add(panel_2);
		panel_2.setLayout(null);
		
		textField_TelephoneFixe = new JTextField();
		textField_TelephoneFixe.setBorder(null);
		textField_TelephoneFixe.setBounds(39, 0, 162, 26);
		panel_2.add(textField_TelephoneFixe);
		textField_TelephoneFixe.setToolTipText("num\u00E9rique \u00E0 8 chiffres");
		textField_TelephoneFixe.setColumns(10);
		
		label_1 = new JLabel("+216");
		label_1.setBorder(null);
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(0, 0, 41, 28);
		panel_2.add(label_1);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(540, 118, 202, 28);
		panel_Parmetrage_Ecole.add(panel);
		panel.setLayout(null);
		
		textField_TelephonePortable = new JTextField();
		textField_TelephonePortable.setBorder(null);
		textField_TelephonePortable.setBounds(38, 0, 162, 27);
		panel.add(textField_TelephonePortable);
		textField_TelephonePortable.setToolTipText("num\u00E9rique \u00E0 8 chiffres");
		textField_TelephonePortable.setColumns(10);
		
		label = new JLabel("+216");
		label.setBorder(null);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setForeground(Color.BLUE);
		label.setBounds(0, 0, 42, 27);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Mise \u00E0 jour", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(227, 215, 413, 70);
		panel_Parmetrage_Ecole.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				activer_champ(true);
			}
		});
		btnModifier.setIcon(new ImageIcon(PanelParametrage.class.getResource("/image/edit.png")));
		btnModifier.setBounds(152, 23, 118, 36);
		panel_1.add(btnModifier);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errEmail.setText("");
				errTelFix.setText("");
				errTelP.setText("");
				activer_champ(true);
				charger_Champ();
				
			}
		});
		btnAnnuler.setIcon(new ImageIcon(PanelParametrage.class.getResource("/image/Red X copie.png")));
		btnAnnuler.setBounds(289, 23, 114, 36);
		panel_1.add(btnAnnuler);
		
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				mis_ajour();
				main.charger_Info_Ecole();
			}
		});
		btnValider.setIcon(new ImageIcon(PanelParametrage.class.getResource("/image/Ok-icon.png")));
		btnValider.setBounds(10, 23, 118, 36);
		panel_1.add(btnValider);
		
		JPanel panel_logo = new JPanel();
		panel_logo.setBorder(new TitledBorder(null, "Logo :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_logo.setBounds(824, 11, 257, 188);
		panel_Parmetrage_Ecole.add(panel_logo);
		panel_logo.setLayout(null);
		
		image = new JLabel("");
		image.setBounds(10, 23, 235, 154);
		panel_logo.add(image);
		image.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		txtpath = new JTextField();
		txtpath.setEditable(false);
		txtpath.setBounds(834, 206, 185, 33);
		panel_Parmetrage_Ecole.add(txtpath);
		txtpath.setColumns(10);
		
		btnParcourir = new JButton("Parcourir");
		btnParcourir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				images();
			}
		});
		btnParcourir.setBounds(832, 248, 89, 36);
		panel_Parmetrage_Ecole.add(btnParcourir);
		
		errEmail = new JLabel("");
		errEmail.setToolTipText("Email invalide");
		errEmail.setVisible(false);
		errEmail.setIcon(new ImageIcon(PanelParametrage.class.getResource("/image/error.png")));
		errEmail.setForeground(Color.RED);
		errEmail.setBounds(745, 63, 22, 24);
		panel_Parmetrage_Ecole.add(errEmail);
		
		errTelFix = new JLabel("");
		errTelFix.setVisible(false);
		errTelFix.setIcon(new ImageIcon(PanelParametrage.class.getResource("/image/error.png")));
		errTelFix.setToolTipText("numero non valide");
		errTelFix.setForeground(Color.RED);
		errTelFix.setBounds(745, 91, 22, 24);
		panel_Parmetrage_Ecole.add(errTelFix);
		
		errTelP = new JLabel("");
		errTelP.setVisible(false);
		errTelP.setIcon(new ImageIcon(PanelParametrage.class.getResource("/image/error.png")));
		errTelP.setForeground(Color.RED);
		errTelP.setBounds(745, 118, 22, 24);
		panel_Parmetrage_Ecole.add(errTelP);
		
		errPro = new JLabel("");
		errPro.setVisible(false);
		errPro.setHorizontalAlignment(SwingConstants.LEFT);
		errPro.setIcon(new ImageIcon(PanelParametrage.class.getResource("/image/error.png")));
		errPro.setForeground(Color.RED);
		errPro.setBounds(315, 64, 22, 28);
		panel_Parmetrage_Ecole.add(errPro);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 44, 1079, 11);
		add(separator);
		
		JLabel lblGestionDesClasses = new JLabel("Parametrage de l'Ecole");
		lblGestionDesClasses.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblGestionDesClasses.setBounds(26, 11, 252, 34);
		add(lblGestionDesClasses);
		charger_Champ();
		activer_champ(false);

	}
	
	/*-------------- La methode pour changer l'image et le path ------------*/
	 public void images() {
	        filen();
	        try {
	        if (path != null) {
	        	image.setIcon(new ImageIcon(path));
	            txtpath.setText(path);            
	            } 
	            }catch (Exception e) {
	                e.printStackTrace();
	        }

	    }
	 /*--------- La methode pour choisir une photo dans le pc ----------------*/
	 public void filen() {
	        try {

	            JFileChooser chooser = new JFileChooser();
	            chooser.setDialogTitle("Choisir une image png");
	            chooser.setApproveButtonText("Ajouter une image");
	            chooser.showOpenDialog(null);
	            File f = chooser.getSelectedFile();
	            filename = f.getAbsolutePath();
	            this.path = (filename);
	        } catch (Exception e) {
	            System.out.println(e);
	            JOptionPane.showMessageDialog(null," Veuiler choisir une image ");;
	        }
	      
	}
	 
	 /*--------------- Cette methode permet d'activer l'edition des champs ou de les desactiver --------- */
	public void activer_champ(boolean bol){
		
		textField_Adresse.setEditable(bol);
		textField_codePostal.setEditable(bol);
		textField_Email.setEditable(bol);
		textField_nomEcole.setEditable(bol);
		textField_Proprietaire.setEditable(bol);
		textField_TelephoneFixe.setEditable(bol);
		textField_TelephonePortable.setEditable(bol);
		textField_Ville.setEditable(bol);
		btnValider.setEnabled(bol);
		btnParcourir.setEnabled(bol);
	}

	/* ---------- Cette permet de charger les informations d'Ecole -----------*/
	public void charger_Champ(){
		Connexion connexion = new Connexion();
		String requete="SELECT * FROM ECOLE WHERE ID_ECOLE =?";
		try {
			PreparedStatement stm = connexion.getConnection().prepareStatement(requete);
			stm.setString(1,"1");	
			ResultSet rs = stm.executeQuery();
			rs.next();
			textField_nomEcole.setText(rs.getString(2));
			textField_Proprietaire.setText(rs.getString(3));
			textField_Adresse.setText(rs.getString(4));
			textField_Ville.setText(rs.getString(5));
			textField_codePostal.setText(rs.getString(6));
			textField_Email.setText(rs.getString(7));
			textField_TelephoneFixe.setText(rs.getString(8));
			textField_TelephonePortable.setText(rs.getString(9));
			String pathim = (rs.getString(10));
			image.setIcon(new ImageIcon(pathim));
			txtpath.setText(pathim);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/*---------- Cette methode permet de faire la mise à jour ----------*/
	public void mis_ajour(){
	
		if(validation()){
			Connexion connexion = new Connexion();
			String requete="UPDATE ECOLE SET NOM_ECOLE=?,PROPRIETAIRE=?,ADRESSE_ECOLE=?,VILLE=?,CODE_POSTALE=?,EMAIL_ECOLE=?,TEL_FIXE=?,TEL_PORTABLE=?,LOGO=? WHERE ID_ECOLE =1";
			try {
				PreparedStatement stm = connexion.getConnection().prepareStatement(requete);
				stm.setString(1, textField_nomEcole.getText());
				stm.setString(2, textField_Proprietaire.getText());
				stm.setString(3, textField_Adresse.getText());
				stm.setString(4, textField_Ville.getText());
				stm.setString(5, textField_codePostal.getText());
				stm.setString(6,textField_Email.getText());
				stm.setString(7,textField_TelephoneFixe.getText());
				stm.setString(8,textField_TelephonePortable.getText());
				stm.setString(9,txtpath.getText());	
				ResultSet rs = stm.executeQuery();
				activer_champ(false);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
/*-------------- Cette methode permet de controler la saisie des information de l'ecole-------------*/
	@SuppressWarnings("deprecation")
	public boolean validation(){
		boolean bol=true;
		EmailValidator emailValidator= EmailValidator.getInstance() ;
		if(!emailValidator.isValid(textField_Email.getText())){
			errEmail.setVisible(true);
			bol=false;
		}	
		else
			errEmail.setVisible(false);
		try{
			Integer.parseInt(textField_TelephoneFixe.getText().toString());
			String numero=textField_TelephoneFixe.getText();
			if(numero.charAt(0)=='0' || numero.length()!=8 ){
				errTelFix.setVisible(true);
				errTelFix.setToolTipText("numero incorrecte");
				bol=false;
			}else{
				errTelFix.setVisible(false);
			}
				
		}catch (NumberFormatException  e) {
			errTelFix.setVisible(true);
			errTelFix.setText("numero ne doit pas pas contenir de lettre");
			bol=false;
		}
		try{
			Integer.parseInt(textField_TelephonePortable.getText());
			String numero=textField_TelephonePortable.getText();
			if(numero.charAt(0)=='0' || numero.length()!=8 ){
				errTelP.setVisible(true);
				errTelP.setToolTipText("numero incorrecte");
				bol=false;
			}else{
				errTelP.setVisible(false);
			}
				
		}catch (NumberFormatException  e) {
			errTelP.setVisible(true);
			errTelP.setToolTipText("numero ne doit pas pas contenir de lettre");
			bol=false;
		} 
		// Verification du champs proprietaire du parametrage de l'ecole
		String expression= "^[a-zA-Z\\s]+";
		if(!textField_Proprietaire.getText().matches(expression)){
			errPro.setVisible(true);
			errPro.setToolTipText("chaine non Valide");
			bol=false;
		}else{
			errPro.setVisible(false);
		}
			
			
		return bol;
	}
}
