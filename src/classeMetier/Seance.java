package classeMetier;

import java.util.Date;

public class Seance {
private Long idSeance;
private String jour;
private String heureDebut;
private String heurFin;
private Long numClasse;
private Long  numInstituteur;
private Long numMatiere;




public Seance() {
	super();
	
}




public Seance(Long idSeance, String jour, String heureDebut, String heurFin, Long numClasse,
		Long numInstituteur, Long numMatiere) {
	super();
	this.idSeance = idSeance;
	this.jour = jour;
	this.heureDebut = heureDebut;
	this.heurFin = heurFin;
	this.numClasse = numClasse;
	this.numInstituteur = numInstituteur;
	this.numMatiere = numMatiere;
}

public Seance( String jour, String heureDebut, String heurFin, Long numClasse,
		Long numInstituteur, Long numMatiere) {
	super();
	this.jour = jour;
	this.heureDebut = heureDebut;
	this.heurFin = heurFin;
	this.numClasse = numClasse;
	this.numInstituteur = numInstituteur;
	this.numMatiere = numMatiere;
}

public Long getIdSeance() {
	return idSeance;
}

public String getJour() {
	return jour;
}




public void setJour(String jour) {
	this.jour = jour;
}


public Long getNumClasse() {
	return numClasse;
}


public Long getNumInstituteur() {
	return numInstituteur;
}


public Long getNumMatiere() {
	return numMatiere;
}


public String getHeureDebut() {
	return heureDebut;
}


public String getHeurFin() {
	return heurFin;
}


public void setIdSeance(Long idSeance) {
	this.idSeance = idSeance;
}


public void setNumClasse(Long numClasse) {
	this.numClasse = numClasse;
}


public void setNumInstituteur(Long numInstituteur) {
	this.numInstituteur = numInstituteur;
}


public void setNumMatiere(Long numMatiere) {
	this.numMatiere = numMatiere;
}


public void setHeureDebut(String heureDebut) {
	this.heureDebut = heureDebut;
}


public void setHeurFin(String heurFin) {
	this.heurFin = heurFin;
}


@Override
public String toString() {
	return "Seance [idSeance=" + idSeance + ", jour=" + jour  +" heureDebut=" + heureDebut
			+ ", heurFin=" + heurFin + ", numClasse=" + numClasse + ", numInstituteur=" + numInstituteur
			+ ", numMatiere=" + numMatiere + "]";
}


	
}
