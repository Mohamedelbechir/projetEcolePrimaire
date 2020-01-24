package classeMetier;

public class Note {
	private Long num_ins;
	private int code_mat;
	private float ds;
	private float examen;
	
	public Note() {
		
	}


	public Note(Long num_ins, int code_mat, float examen, float ds) {
		super();
		this.num_ins = num_ins;
		this.code_mat = code_mat;
		this.examen = examen;
		this.ds = ds;
	}

	public Long getNum_ins() {
		return num_ins;
	}



	public int getCode_mat() {
		return code_mat;
	}



	public float getDs() {
		return ds;
	}



	public float getExamen() {
		return examen;
	}



	public void setNum_ins(Long num_ins) {
		this.num_ins = num_ins;
	}



	public void setCode_mat(int code_mat) {
		this.code_mat = code_mat;
	}



	public void setDs(float ds) {
		this.ds = ds;
	}



	public void setExamen(float examen) {
		this.examen = examen;
	}



	@Override
	public String toString() {
		return "Note [num_ins=" + num_ins + ", code_mat=" + code_mat + ", ds=" + ds + ", examen=" + examen
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
