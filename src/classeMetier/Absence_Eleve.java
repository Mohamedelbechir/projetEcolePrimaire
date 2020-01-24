package classeMetier;

public class Absence_Eleve {
	private Long num_Inscription;
	private String date_Absence; 
	private String motif ;
	
	public Absence_Eleve() {
		
	}
	
	public Absence_Eleve(Long num_Inscription, String date_Absence, String motif) {
		super();
		this.num_Inscription = num_Inscription;
		this.date_Absence = date_Absence;
		this.motif = motif;
	}
	public Long getNum_Inscription() {
		return num_Inscription;
	}
	public void setNum_Inscription(Long num_Inscription) {
		this.num_Inscription = num_Inscription;
	}
	public String getDate_Absence() {
		return date_Absence;
	}
	public void setDate_Absence(String date_Absence) {
		this.date_Absence = date_Absence;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	

}
