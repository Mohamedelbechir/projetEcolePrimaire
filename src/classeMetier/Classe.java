package classeMetier;

public class Classe {
	
	private Long num_Clase;
	private String nom_Clase="";
	private Long id_Niveau;

	public Classe(){
		
	}
	
	
	public Classe(Long num_Clase, String nom_Clase, Long id_Niveau) {
		super();
		this.num_Clase = num_Clase;
		this.nom_Clase = nom_Clase;
		this.id_Niveau = id_Niveau;
	}

	public Classe(String nom_Clase, Long id_Niveau) {
		super();
		//this.num_Clase = num_Clase;
		this.nom_Clase = nom_Clase;
		this.id_Niveau = id_Niveau;
	}

	public Long getNum_Clase() {
		return num_Clase;
	}

	public void setNum_Clase(Long num_Clase) {
		this.num_Clase = num_Clase;
	}

	public String getNom_Clase() {
		return nom_Clase;
	}

	public void setNom_Clase(String nom_Clase) {
		this.nom_Clase = nom_Clase;
	}

	public Long getNiveau_Classe() {
		return id_Niveau;
	}

	public void setNiveau_Classe(Long niveau_Classe) {
		this.id_Niveau = niveau_Classe;
	}


	@Override
	public String toString() {
		return "Classe [num_Clase=" + num_Clase + ", nom_Clase=" + nom_Clase + ", niveau_Classe=" + id_Niveau + "]";
	}


	
	

}
