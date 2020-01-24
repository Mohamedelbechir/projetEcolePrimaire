package view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import connxion_Requete.Connexion;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.Frame;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_Logo_Ecole;
	private JPanel panel_Menu; 
	private JPanel panel_Ecole;
	private JLabel lblNomEcole ;
	private JLabel label_Nom;
	private JLabel labHeure;
	private JLabel label_Mail;
	private JLabel label_Fax; 
	private JLabel label_Prop;
	private JLabel lbl_Adresse;
	private JLabel label_BP;
	private JLabel label_Ville;
	private JLabel label_Cel;
	private JLabel lblHeure; 
	private JLabel seconde;
	private JLabel lblPm;
	private LinkedList<JButton> listButtom;
	private int tik_Montre=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Connexion x = new Connexion();
		x.getConnection();
		
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
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Gestion_Ecole");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/image/lib.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 726);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		listButtom=new LinkedList<>();
		
		
		
		
		panel_Ecole = new JPanel();
		panel_Ecole.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Ecole.setBounds(241, 5, 1111, 126);
		panel_Ecole.setLayout(null);
		contentPane.add(panel_Ecole);
		
		PanelGestionConseilClasse conseil =new PanelGestionConseilClasse();
		conseil.repaint();
		conseil.revalidate();
		
		lblNomEcole = new JLabel("Nom Ecole : ");
		lblNomEcole.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNomEcole.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomEcole.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblNomEcole.setBounds(10, 11, 138, 27);
		panel_Ecole.add(lblNomEcole);
		
		label_Nom = new JLabel("__________");
		label_Nom.setForeground(Color.RED);
		label_Nom.setFont(new Font("Lucida Fax", Font.BOLD | Font.ITALIC, 15));
		label_Nom.setBounds(147, 11, 162, 27);
		panel_Ecole.add(label_Nom);
		
		String filename="Font_Police/digital-7 (mono italic).ttf";
		Font font = null;
		Font fontSeconde=null;
		try {
			font=Font.createFont(Font.TRUETYPE_FONT, new File(filename));
			fontSeconde=Font.createFont(Font.TRUETYPE_FONT, new File(filename));
			font=font.deriveFont(Font.BOLD,40);
			fontSeconde=fontSeconde.deriveFont(Font.BOLD,18);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			ge.registerFont(fontSeconde);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		labHeure = new JLabel("11:59:30");
		labHeure.setBackground(Color.WHITE);
		labHeure.setBorder(null);
		labHeure.setHorizontalTextPosition(SwingConstants.CENTER);
		labHeure.setHorizontalAlignment(SwingConstants.RIGHT);
		labHeure.setForeground(Color.DARK_GRAY);
		//labHeure.setFont(new java.awt.Font("Digital-7", 1, 24));
		labHeure.setFont(font);
		labHeure.setBounds(493, 34, 128, 53);
		panel_Ecole.add(labHeure);
		
		lblHeure = new JLabel("01/01/2017");
		lblHeure.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHeure.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeure.setForeground(Color.DARK_GRAY);
		//lblHeure.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHeure.setFont(font);
		lblHeure.setBounds(310, 34, 200, 53);
		panel_Ecole.add(lblHeure);
		
		JLabel label = new JLabel("Fax :");
		label.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label.setBounds(793, 11, 57, 27);
		panel_Ecole.add(label);
		
		JLabel label_2 = new JLabel("Cell :");
		label_2.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_2.setBounds(793, 34, 46, 26);
		panel_Ecole.add(label_2);
		
		JLabel label_3 = new JLabel("E-mail");
		label_3.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		label_3.setBounds(793, 86, 74, 27);
		panel_Ecole.add(label_3);
		
		label_Fax = new JLabel("____________");
		label_Fax.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_Fax.setBounds(901, 11, 200, 27);
		panel_Ecole.add(label_Fax);
		
		label_Cel = new JLabel("__________________");
		label_Cel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_Cel.setBounds(901, 34, 200, 26);
		panel_Ecole.add(label_Cel);
		
		label_Mail = new JLabel("______________");
		label_Mail.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		label_Mail.setForeground(Color.BLUE);
		label_Mail.setBounds(901, 86, 200, 27);
		panel_Ecole.add(label_Mail);
		
		JLabel lblAdresse = new JLabel("Adresse : ");
		lblAdresse.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblAdresse.setBounds(10, 60, 106, 27);
		panel_Ecole.add(lblAdresse);
		
		JLabel lblVille = new JLabel("Ville : ");
		lblVille.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblVille.setBounds(10, 86, 106, 27);
		panel_Ecole.add(lblVille);
		
		JLabel lblPropritaire = new JLabel("Propri\u00E9taire");
		lblPropritaire.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		lblPropritaire.setBounds(10, 34, 106, 27);
		panel_Ecole.add(lblPropritaire);
		
		label_Prop = new JLabel("________________");
		label_Prop.setFont(new Font("Dialog", Font.ITALIC, 14));
		label_Prop.setBounds(146, 34, 110, 27);
		panel_Ecole.add(label_Prop);
		
		JLabel lblBp = new JLabel("BP :");
		lblBp.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 14));
		lblBp.setBounds(793, 60, 46, 27);
		panel_Ecole.add(lblBp);
		
		label_BP = new JLabel("___________________");
		label_BP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_BP.setBounds(901, 60, 200, 27);
		panel_Ecole.add(label_BP);
		
		label_Ville = new JLabel("_________________");
		label_Ville.setFont(new Font("Dialog", Font.ITALIC, 14));
		label_Ville.setBounds(147, 86, 110, 27);
		panel_Ecole.add(label_Ville);
		
		lbl_Adresse = new JLabel("");
		lbl_Adresse.setFont(new Font("Dialog", Font.ITALIC, 14));
		lbl_Adresse.setBounds(147, 60, 108, 27);
		panel_Ecole.add(lbl_Adresse);
		
		seconde = new JLabel("30");
		seconde.setForeground(Color.DARK_GRAY);
		seconde.setHorizontalTextPosition(SwingConstants.LEFT);
		seconde.setBounds(618, 34, 35, 26);
		seconde.setFont(fontSeconde);
		panel_Ecole.add(seconde);
		
		lblPm = new JLabel("PM");
		lblPm.setForeground(Color.DARK_GRAY);
		lblPm.setBounds(631, 58, 35, 14);
		lblPm.setFont(new Font("Dialog", Font.PLAIN, 9));
		panel_Ecole.add(lblPm);
		
	    panel_Menu = new JPanel();
		panel_Menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Menu.setBounds(12, 5, 219, 671);
		contentPane.add(panel_Menu);
		panel_Menu.setLayout(null);
		
		JButton btnGestionDesElves = new JButton("Gestion des El\u00E8ves");
		btnGestionDesElves.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesElves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				colorButton(btnGestionDesElves);
				remove_Panel_Gestion(new PanelGestionEleve());
				
				
				
			}
		});
		btnGestionDesElves.setBounds(10, 136, 196, 30);
		listButtom.add(btnGestionDesElves);
		panel_Menu.add(btnGestionDesElves);
		
		JButton btnGestionDesClasses = new JButton("Gestion des Classes");
		btnGestionDesClasses.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesClasses);
			    remove_Panel_Gestion(new PanelGestionClasse1());
				
			}
		});
		btnGestionDesClasses.setBounds(10, 218, 196, 30);
		listButtom.add(btnGestionDesClasses);
		panel_Menu.add(btnGestionDesClasses);
		
		JButton btnGestionDesSalles = new JButton("Gestion des Salles");
		btnGestionDesSalles.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesSalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesSalles);
				remove_Panel_Gestion(new PanelGestionSalle());
				
			}
		});
		btnGestionDesSalles.setBounds(10, 259, 196, 30);
		listButtom.add(btnGestionDesSalles);
		panel_Menu.add(btnGestionDesSalles);
		
		JButton btnGestionDesEmploies = new JButton("Gestion des Emplois");
		btnGestionDesEmploies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesEmploies);
				remove_Panel_Gestion(new PanelGestionEmploi());
				
			}
		});
		btnGestionDesEmploies.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesEmploies.setBounds(10, 300,196, 30);
		listButtom.add(btnGestionDesEmploies);
		panel_Menu.add(btnGestionDesEmploies);
		
		JButton btnGestionDesMatires = new JButton("Gestion des Mati\u00E8res");
		btnGestionDesMatires.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesMatires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesMatires);
				remove_Panel_Gestion(new PanelGestionMatiere());
			}
		});
		btnGestionDesMatires.setBounds(10, 341, 196, 30);
		listButtom.add(btnGestionDesMatires);
		panel_Menu.add(btnGestionDesMatires);
		
		JButton btnGestionDesNotes = new JButton("Gestion des Notes");
		btnGestionDesNotes.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesNotes);
				remove_Panel_Gestion(new PanelGestionNote());
			}
		});
		btnGestionDesNotes.setBounds(10, 382,196, 30);
		listButtom.add(btnGestionDesNotes);
		panel_Menu.add(btnGestionDesNotes);
		
		JButton btnGestionDesCarnets = new JButton("Gestion des Carnets/ Livrets");
		btnGestionDesCarnets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesCarnets);
			}
		});
		btnGestionDesCarnets.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesCarnets.setBounds(10, 423, 196, 30);
		listButtom.add(btnGestionDesCarnets);
		panel_Menu.add(btnGestionDesCarnets);
		
		JButton btnNewButton = new JButton("Gestion des Absences");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnNewButton.setBounds(10, 464, 196, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnNewButton);
				
				remove_Panel_Gestion(new PanelGestionAbsence());
			}
		});
		listButtom.add(btnNewButton);
		panel_Menu.add(btnNewButton);
		
		JButton btnAccueil = new JButton("Accueil");
		btnAccueil.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		btnAccueil.setIcon(new ImageIcon(Main.class.getResource("/image/lib.png")));
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnAccueil);
				remove_Panel_Gestion(panel_Logo_Ecole);
			}
		});
		btnAccueil.setBounds(10, 22, 196, 62);
		listButtom.add(btnAccueil);
		panel_Menu.add(btnAccueil);
		
		JButton btnGestionDesInstituteurs = new JButton("Gestion des Instituteurs");
		btnGestionDesInstituteurs.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesInstituteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesInstituteurs);
				remove_Panel_Gestion(new PanelGestionInstituteur());
				
			}
		});
		btnGestionDesInstituteurs.setBounds(10, 177, 196, 30);
		listButtom.add(btnGestionDesInstituteurs);
		panel_Menu.add(btnGestionDesInstituteurs);
		
		JButton btnGestionDesConseil = new JButton("Gestion des Conseil de Classe");
		btnGestionDesConseil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesConseil);
				remove_Panel_Gestion(new PanelGestionConseilClasse());
			}
		});
		btnGestionDesConseil.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesConseil.setBounds(10, 505,196, 30);
		listButtom.add(btnGestionDesConseil);
		panel_Menu.add(btnGestionDesConseil);
		
		JButton btnModidierLesInformation = new JButton("Param\u00E8trage de l'Ecole");
		btnModidierLesInformation.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnModidierLesInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnModidierLesInformation);
				// Ici on passe la reference du fenetre courant à la panel_Parametrage
				remove_Panel_Gestion(new PanelParametrage((Main) SwingUtilities.getRoot(contentPane)));
			}
		});
		btnModidierLesInformation.setBounds(10, 630, 196, 30);
		listButtom.add(btnModidierLesInformation);
		panel_Menu.add(btnModidierLesInformation);
		
		JButton btnGestionDesNiveau = new JButton("Gestion Des Niveaux");
		btnGestionDesNiveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesNiveau);
				remove_Panel_Gestion(new PanelGestionNiveau());
			}
		});
		
		JButton btnGestionDesUtilisateurs = new JButton("Gestion Des Utilisateurs");
		btnGestionDesUtilisateurs.setBounds(10, 546, 196, 30);
		panel_Menu.add(btnGestionDesUtilisateurs);
		btnGestionDesUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				colorButton(btnGestionDesUtilisateurs);
			}
		});
		btnGestionDesUtilisateurs.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		listButtom.add(btnGestionDesUtilisateurs);
		btnGestionDesNiveau.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnGestionDesNiveau.setBounds(10, 589, 196, 30);
		listButtom.add(btnGestionDesNiveau);
		panel_Menu.add(btnGestionDesNiveau);
		
		panel_Logo_Ecole = new JPanel();
		panel_Logo_Ecole.setBounds(241, 142, 1111, 534);
		contentPane.add(panel_Logo_Ecole);
		panel_Logo_Ecole.setLayout(null);
		
		JLabel logo_Ecole =  new JLabel("");
		logo_Ecole.setBorder(new LineBorder(new Color(0, 0, 0)));
		logo_Ecole.setHorizontalTextPosition(SwingConstants.CENTER);
		logo_Ecole.setHorizontalAlignment(SwingConstants.CENTER);
		logo_Ecole.setIcon(new ImageIcon(Main.class.getResource("/image/logo_Intellos-httpwww.parigneleveque1.jpg")));
		logo_Ecole.setBounds(0, 0, 1111, 534);
		panel_Logo_Ecole.add(logo_Ecole);
		
		charger_Info_Ecole();
		setLocationRelativeTo(null);
		datecourante();
	}
	public void remove_Panel_Gestion(JPanel panel){
		contentPane.removeAll();
		contentPane.add(panel_Ecole);
		contentPane.add(panel_Menu);
		contentPane.add(panel);
		repaint();
	}
	
	
	/*--------------------------- Cette permet de charger la date et heure ------------------*/
    public void datecourante() {
    	
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar Cal = new GregorianCalendar();
                  
                    int scondes = Cal.get(Calendar.SECOND);
                    int minute = Cal.get(Calendar.MINUTE);
                    int heure = Cal.get(Calendar.HOUR_OF_DAY);
                    int AM_PM = Cal.get(Calendar.AM_PM);  
                    String pa;
                    if(AM_PM==1){
                        pa="PM";
                    }else{
                        pa="AM";
                    }
                    if(tik_Montre % 2 ==0){
                    	labHeure.setText( + heure + ":" + (minute)  );
                    }
                    	
                    else
                        labHeure.setText( + heure + " " + (minute) );
                    seconde.setText(""+scondes);
                    lblPm.setText(pa);
                    tik_Montre++;
                    
                    int mois = Cal.get(Calendar.MONTH);
                    int annee = Cal.get(Calendar.YEAR);
                    int jour = Cal.get(Calendar.DAY_OF_MONTH);

                    lblHeure.setText( + jour + "/" + (mois+1) + "/" + annee);              
                    
                    
                    try {
                        sleep(1000);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        };
        
        clock.start();
    }
    
    public void colorButton(JButton buton){
    	for (	JButton jButton : listButtom) {
    		jButton.setForeground(null);
    		jButton.setBackground(null);		
		}
    	buton.setForeground(SystemColor.window);
    	buton.setBackground(SystemColor.textInactiveText);
    }
    
    /* ---------------Ctte methode permet d'Afficher les Information de l'Ecole dans les Champs -------------*/
    public void charger_Info_Ecole(){
    		Connexion connexion = new Connexion();
    		String requete="SELECT * FROM ECOLE WHERE ID_ECOLE =1";
    		try {
    			PreparedStatement stm = connexion.getConnection().prepareStatement(requete);
    			//stm.setString(1,"1");	
    			ResultSet rs = stm.executeQuery();
    			if(rs.next()){
    				label_Nom.setText(rs.getString(2));
    				label_Prop.setText(rs.getString(3));
    				lbl_Adresse.setText(rs.getString(4));
    				label_Ville.setText(rs.getString(5));
    				label_BP.setText(rs.getString(6));
    				label_Mail.setText(rs.getString(7));
    				label_Fax.setText("+216"+rs.getString(8));
    				label_Cel.setText("+216"+rs.getString(9));
    			//String pathim = (rs.getString(10));
    			//image.setIcon(new ImageIcon(pathim));
    			//txtpath.setText(pathim);
    			}
    			
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			//e.printStackTrace();
    		} 
    }
}
