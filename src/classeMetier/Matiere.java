package classeMetier;

public class Matiere {
	
	private String nom;
	private Long id_matiere;
	private Long id_niveau;
	private Long coef;
    public Matiere(){
		
	}

	public Matiere(String nom, Long id_matiere, Long id_niveau,Long coef) {
		super();
		this.nom = nom;
		this.id_matiere = id_matiere;
		this.id_niveau = id_niveau;
		this.coef=coef;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getId_matiere() {
		return id_matiere;
	}

	public void setId_matiere(Long id_matiere) {
		this.id_matiere = id_matiere;
	}

	public Long getId_niveau() {
		return id_niveau;
	}

	public void setId_niveau(Long id_niveau) {
		this.id_niveau = id_niveau;
	}

	public Long getCoef() {
		return coef;
	}

	public void setCoef(Long coef) {
		this.coef = coef;
	}
    
    
}
