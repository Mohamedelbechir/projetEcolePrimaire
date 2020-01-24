package classeMetier;

public class Inst_Mat_Affectation {
	
	private Long idInst;
	private Long idMAt;
	public Inst_Mat_Affectation(Long idInst, Long idMAt) {
		super();
		this.idInst = idInst;
		this.idMAt = idMAt;
	}
	
	public Inst_Mat_Affectation(Long idInst) {
		super();
		this.idInst = idInst;
	}

	public Long getIdInst() {
		return idInst;
	}
	public void setIdInst(Long idInst) {
		this.idInst = idInst;
	}
	public Long getIdMAt() {
		return idMAt;
	}
	public void setIdMAt(Long idMAt) {
		this.idMAt = idMAt;
	}
	
	
	
	

}
