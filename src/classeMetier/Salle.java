package classeMetier;

public class Salle {
	
	
	private Long id_SALLE;
	private String nom_SALLE;
	private Integer capacite;
	private Long num_Classe;
	/*------------  Constructeur de la Classe Salle --------------*/
	
	public Salle(long id_SALLE, String nom_SALLE, int capacite) {
		super();
		this.id_SALLE = id_SALLE;
		this.nom_SALLE = nom_SALLE;
		this.capacite = capacite;
		this.num_Classe=null;
	}
	public Salle(long id_SALLE,Long num_Clase, String nom_SALLE, int capacite) {
		super();
		this.id_SALLE = id_SALLE;
		this.num_Classe=num_Clase;
		this.nom_SALLE = nom_SALLE;
		this.capacite = capacite;
	}
	public Salle() {
		// TODO Auto-generated constructor stub
	}

	public Long getId_SALLE() {
		return id_SALLE;
	}

	public void setId_SALLE(long id_SALLE) {
		this.id_SALLE = id_SALLE;
	}

	public String getNom_SALLE() {
		return nom_SALLE;
	}

	public void setNom_SALLE(String nom_SALLE) {
		this.nom_SALLE = nom_SALLE;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public Long getNum_Clase() {
		return num_Classe;
	}

	public void setNum_Clase(Long num_Clase) {
		this.num_Classe = num_Clase;
	}
	
	
	
}
