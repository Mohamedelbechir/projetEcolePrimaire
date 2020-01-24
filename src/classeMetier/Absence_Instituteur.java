package classeMetier;

public class Absence_Instituteur {

	private Long id;
	private String date_Absence;
	private String motif;
	
	public Absence_Instituteur() {
		// TODO Auto-generated constructor stub
	}

	public Absence_Instituteur(Long id, String date_Absence, String motif) {
		super();
		this.id = id;
		this.date_Absence = date_Absence;
		this.motif = motif;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
