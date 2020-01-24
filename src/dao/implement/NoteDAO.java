package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import classeMetier.Classe;
import classeMetier.Note;
import dao.DAO;

public class NoteDAO extends DAO<Note> {

	public NoteDAO() {
		//super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Note obj) {
		try {
			PreparedStatement stmt = this.connect.prepareStatement("INSERT INTO NOTE(NUM_INSCRIPTION,ID_MATIERE,NOTE_EXAMEN,NOTE_DS) VALUES (?,?,?,?)");
			stmt.setLong(1, obj.getNum_ins());
			stmt.setInt(2,obj.getCode_mat());
			stmt.setFloat(3,obj.getExamen());
			stmt.setFloat(4, obj.getDs());
			stmt.executeQuery();
			stmt.close();
			
		} catch (Exception  e) {
			
			e.getMessage();
			System.out.println("False el");
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Note obj) {
		try {
			PreparedStatement stmt = this.connect.prepareStatement("DELETE FROM NOTE WHERE NUM_INSCRIPTION=? AND ID_MATIERE=? ");
			stmt.setLong(1,obj.getNum_ins());
			stmt.setInt(1,obj.getCode_mat());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Note obj) {
		try {
			PreparedStatement stmt = this.connect.prepareStatement("UPDATE NOTE SET NOTE_EXAMEN=?,NOTE_DS= ? WHERE NUM_INSCRIPTION = ? AND ID_MATIERE=?");
		      stmt.setFloat(1,obj.getExamen());
		      stmt.setFloat(2,obj.getDs());
		      stmt.setLong(3,obj.getNum_ins());
		      stmt.setInt(4,obj.getCode_mat());
			//stmt.executeQuery();
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}

	public Note find(String id) {
		Note n=new Note() ;
		try {
			PreparedStatement stmt = this.connect.prepareStatement("SELECT * FROM NOTE WHERE NUM_INSCRIPTION = ?");
			stmt.setString(1,id);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				n.setNum_ins(rs.getLong(1));
				n.setCode_mat(rs.getInt(2));
				n.setDs(rs.getFloat(4));
				n.setExamen(rs.getFloat(3));
				
			}
			stmt.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	public Note findNote(Long id,int code) {
		Note n= null;
		
		try {
			PreparedStatement stmt = this.connect.prepareStatement("SELECT * FROM NOTE WHERE NUM_INSCRIPTION = ? AND ID_MATIERE = ?");
			stmt.setLong(1,id);
			stmt.setInt(2, code);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				n= new Note();
				n.setNum_ins(rs.getLong(1));
				n.setCode_mat(rs.getInt(2));
				n.setExamen(rs.getFloat(3));
				n.setDs(rs.getFloat(4));
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Vector<Note> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
