package classeMetier;

public class Niveau{
	private Long id_Niveau;
	private String nom_Niveau;
	public Niveau(Long id_Niveau, String nom_Niveau) {
		super();
		this.id_Niveau = id_Niveau;
		this.nom_Niveau = nom_Niveau;
	}
	
	public Niveau() {
		super();
	}

	public Long getId_Niveau() {
		return id_Niveau;
	}
	public void setId_Niveau(Long id_Niveau) {
		this.id_Niveau = id_Niveau;
	}
	public String getNom_Niveau() {
		return nom_Niveau;
	}
	public void setNom_Niveau(String nom_Niveau) {
		this.nom_Niveau = nom_Niveau;
	}


}
