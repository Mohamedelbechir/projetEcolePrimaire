package classeMetier;

import java.sql.Date;

public class ConseilClasse {
	
	private Long id_Conseil;
	private String titre_Conseil;
	private String date_Conseil;
	private String description;
	public ConseilClasse(Long id_Conseil, String titre_Conseil, String date_Conseil, String description) {
		super();
		this.id_Conseil = id_Conseil;
		this.titre_Conseil = titre_Conseil;
		this.date_Conseil = date_Conseil;
		this.description = description;
	}
	
	public ConseilClasse() {
		super();
	}

	public Long getId_Conseil() {
		return id_Conseil;
	}
	public void setId_Conseil(Long id_Conseil) {
		this.id_Conseil = id_Conseil;
	}
	public String getTitre_Conseil() {
		return titre_Conseil;
	}
	public void setTitre_Conseil(String titre_Conseil) {
		this.titre_Conseil = titre_Conseil;
	}
	public String getDate_Conseil() {
		return date_Conseil;
	}
	public void setDate_Conseil(String date_Conseil) {
		this.date_Conseil = date_Conseil;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
