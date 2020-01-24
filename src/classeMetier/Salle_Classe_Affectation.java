package classeMetier;

public class Salle_Classe_Affectation {
	private Long id_Salle;
	private Long id_Classe;
	public Salle_Classe_Affectation(Long id_Salle, long id_Classe) {
		super();
		this.id_Salle = id_Salle;
		this.id_Classe = id_Classe;
	}
	public Salle_Classe_Affectation() {
		// TODO Auto-generated constructor stub
	}
	public Long getId_Salle() {
		return id_Salle;
	}
	public void setId_Salle(Long id_Salle) {
		this.id_Salle = id_Salle;
	}
	public Long getId_Classe() {
		return id_Classe;
	}
	public void setId_Classe(long id_Classe) {
		this.id_Classe = id_Classe;
	}
	
}
