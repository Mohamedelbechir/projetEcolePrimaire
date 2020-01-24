package classeMetier;

public class Tuteur {
	private Long cinT;
	private String nomT;
	private String prenomT;
	private String nationnaliteT;
	private String date_naissT;
	private String addresseT;
	private String villeT;
	private String sexeT;
	private String profT;
	private Long TelMob;
	private Long Telfixe;
	private String Email;
	
	
	public Tuteur(){
		
	}
	
	public Tuteur(Long cinT, String nomT, String prenomT, String date_naissT, String sexeT, String nationnaliteT,String profT, String addresseT,
			String villeT, Long telMob, Long telfixe, String email) {
		super();
		this.cinT = cinT;
		this.nomT = nomT;
		this.prenomT = prenomT;
		this.nationnaliteT = nationnaliteT;
		this.date_naissT = date_naissT;
		this.addresseT = addresseT;
		this.villeT = villeT;
		this.sexeT = sexeT;
		this.profT = profT;
		TelMob = telMob;
		Telfixe = telfixe;
		Email = email;
	}
	public Long getCinT() {
		return cinT;
	}
	public void setCinT(Long cinT) {
		this.cinT = cinT;
	}
	public String getNomT() {
		return nomT;
	}
	public void setNomT(String nomT) {
		this.nomT = nomT;
	}
	public String getPrenomT() {
		return prenomT;
	}
	public void setPrenomT(String prenomT) {
		this.prenomT = prenomT;
	}
	public String getNationnaliteT() {
		return nationnaliteT;
	}
	public void setNationnaliteT(String nationnaliteT) {
		this.nationnaliteT = nationnaliteT;
	}
	public String getDate_naissT() {
		return date_naissT;
	}
	public void setDate_naissT(String date_naissT) {
		this.date_naissT = date_naissT;
	}
	public String getAddresseT() {
		return addresseT;
	}
	public void setAddresseT(String addresseT) {
		this.addresseT = addresseT;
	}
	public String getVilleT() {
		return villeT;
	}
	public void setVilleT(String villeT) {
		this.villeT = villeT;
	}
	public String getSexeT() {
		return sexeT;
	}
	public void setSexeT(String sexeT) {
		this.sexeT = sexeT;
	}
	public String getProfT() {
		return profT;
	}
	public void setProfT(String profT) {
		this.profT = profT;
	}
	public Long getTelMob() {
		return TelMob;
	}
	public void setTelMob(Long telMob) {
		TelMob = telMob;
	}
	public Long getTelfixe() {
		return Telfixe;
	}
	public void setTelfixe(Long telfixe) {
		Telfixe = telfixe;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String toString() {
		return "Tuteur [cinT=" + cinT + ", nomT=" + nomT + ", prenomT=" + prenomT + ", nationnaliteT=" + nationnaliteT
				+ ", date_naissT=" + date_naissT + ", addresseT=" + addresseT + ", villeT=" + villeT + ", sexeT="
				+ sexeT + ", profT=" + profT + ", TelMob=" + TelMob + ", Telfixe=" + Telfixe + ", Email=" + Email + "]";
	}


}
