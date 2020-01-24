package classeMetier;

public class Instituteur {

	private Long id;
	private String nom;
	private String prenom;
	private String dateN;

	private Long numTel;
	private String email;
	private String adresse;
	private String nationalite;
	private String sexe;
	
	public Instituteur(){
		
	}

	
	
	
	public Instituteur(Long id, String nom, String prenom, String dateN, Long numTel, String email, String adresse,
			String nationalite, String sexe) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateN = dateN;
		this.numTel = numTel;
		this.email = email;
		this.adresse = adresse;
		this.nationalite = nationalite;
		this.sexe = sexe;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateN() {
		return dateN;
	}

	public void setDateN(String dateN) {
		this.dateN = dateN;
	}

	public Long getNumTel() {
		return numTel;
	}

	public void setNumTel(Long numTel) {
		this.numTel = numTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	
	
	
	
	
}
