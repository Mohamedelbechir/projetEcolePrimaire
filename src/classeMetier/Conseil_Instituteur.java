package classeMetier;

public class Conseil_Instituteur {
	
	private Long id_Conseil;
	private Long id_inst;
	public Conseil_Instituteur(Long id_Conseil, Long id_inst) {
		super();
		this.id_Conseil = id_Conseil;
		this.id_inst = id_inst;
	}
	
	public Conseil_Instituteur() {
		super();
	}

	public Long getId_Conseil() {
		return id_Conseil;
	}
	public void setId_Conseil(Long id_Conseil) {
		this.id_Conseil = id_Conseil;
	}
	public Long getId_inst() {
		return id_inst;
	}
	public void setId_inst(Long id_inst) {
		this.id_inst = id_inst;
	}
	

}
