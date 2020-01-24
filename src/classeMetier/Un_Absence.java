package classeMetier;

public class Un_Absence {
	private Long num_Inscription;
	private String motif;
	private Boolean matin;
	private Boolean soir;

	public Un_Absence() {
		// TODO Auto-generated constructor stub
	}

	public Un_Absence(Long num_Inscription, String motif, Boolean matin, Boolean soir) {
		super();
		this.num_Inscription = num_Inscription;
		this.motif = motif;
		this.matin = matin;
		this.soir = soir;
	}

	public Long getNum_Inscription() {
		return num_Inscription;
	}

	public void setNum_Inscription(Long num_Inscription) {
		this.num_Inscription = num_Inscription;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Boolean getMatin() {
		return matin;
	}

	public void setMatin(Boolean matin) {
		this.matin = matin;
	}

	public Boolean getSoir() {
		return soir;
	}

	public void setSoir(Boolean soir) {
		this.soir = soir;
	}

}
