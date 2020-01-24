package classeMetier;

import java.util.Date;

public class Eleve {
	private Long num_ins;
	private Long num_classe;
	private String nom;
	private String prenom;
	private String nationnalite;
	private String date_naiss;
	private String addresse;
	private String ville;
	private String sexe;
	private Long cin;
	private String photos_Eleve;
	
	
	public Eleve(){
		
	}
	
	public Eleve(Long num_ins, Long cin, Long num_classe, String nom, String prenom, String nationnalite, String date_naiss,
			String addresse, String ville, String sexe,String photos_Eleve ) {
		super();
		this.num_ins = num_ins;
		this.num_classe = num_classe;
		this.nom = nom;
		this.prenom = prenom;
		this.nationnalite = nationnalite;
		this.date_naiss = date_naiss;
		this.addresse = addresse;
		this.ville = ville;
		this.sexe = sexe;
		this.cin = cin;
		this.photos_Eleve=photos_Eleve;
	}
	public Long getCin() {
		return cin;
	}
	public void setCin(Long cin) {
		this.cin = cin;
	}
	
	public Long getNum_ins() {
		return num_ins;
	}
	public void setNum_ins(Long num_ins) {
		this.num_ins = num_ins;
	}
	public Long getNum_classe() {
		return num_classe;
	}
	public void setNum_classe(Long num_classe) {
		this.num_classe = num_classe;
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
	public String getNationnalite() {
		return nationnalite;
	}
	public void setNationnalite(String nationnalite) {
		this.nationnalite = nationnalite;
	}
	public String getDate_naiss() {
		return date_naiss;
	}
	public void setDate_naiss(String date_naiss) {
		this.date_naiss = date_naiss;
	}
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return "Eleve [num_ins=" + num_ins + ", num_classe=" + num_classe + ", nom=" + nom + ", prenom=" + prenom
				+ ", nationnalite=" + nationnalite + ", date_naiss=" + date_naiss + ", addresse=" + addresse
				+ ", ville=" + ville + ", sexe=" + sexe + ", cin=" + cin + "]";
	}

	public String getPhotos_Eleve() {
		return photos_Eleve;
	}

	public void setPhotos_Eleve(String photos_Eleve) {
		this.photos_Eleve = photos_Eleve;
	}
	
	
	
	
	
}
