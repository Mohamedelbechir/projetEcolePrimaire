package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import classeMetier.Eleve;
import classeMetier.Instituteur;
import dao.DAO;


public class InstituteurDAO extends DAO<Instituteur> {

	public InstituteurDAO() {
		//super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Instituteur obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO INST VALUES (?,?,?,?,?,?,?,?,?)");
			stmt.setLong(1,obj.getId());	
			stmt.setString(2, obj.getNom());	
			stmt.setString(3, obj.getPrenom());
			stmt.setString(4, obj.getDateN());
			stmt.setLong(5,obj.getNumTel());
			stmt.setString(6, obj.getEmail());
			stmt.setString(7, obj.getAdresse());
			stmt.setString(8, obj.getNationalite());	
			stmt.setString(9, obj.getSexe());
			
			stmt.executeQuery();
			
		} catch (Exception  e) {
			
			e.printStackTrace();
			System.out.println("False el");
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Instituteur obj) {
		try {
			PreparedStatement stmt =connect.prepareStatement("DELETE FROM INST WHERE ID=?");
			stmt.setLong(1,obj.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(Instituteur obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("UPDATE INST SET ID=?,NOM=?, PRENOM= ?, DATE_NAISS=?,"
					+ " NUM_TEL=?, EMAIL=?, ADRESSE=? ,NATIONALITE=?, SEXE=?"
					+ "WHERE ID = ?  ");
			stmt.setLong(1,obj.getId());
			stmt.setString(2, obj.getNom());
			stmt.setString(3, obj.getPrenom());
			stmt.setString(4, obj.getDateN());
			stmt.setLong(5, obj.getNumTel());
			stmt.setString(6, obj.getEmail());
			stmt.setString(7, obj.getAdresse());
			stmt.setString(8, obj.getNationalite());
			stmt.setString(9,obj.getSexe());
			stmt.setLong(10,obj.getId());		
			//stmt.executeQuery();
			stmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}

	
	
	public Vector<Instituteur> findByName(String id) {
		Vector<Instituteur> po = new Vector<Instituteur>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID,NOM,PRENOM,to_char(DATE_NAISS,'dd/MM/yyyy'),NUM_TEL, EMAIL, ADRESSE, NATIONALITE,SEXE FROM INST WHERE NOM LIKE ?  ");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				stmt.setString(1, id);
				Instituteur c = new Instituteur();
				c.setId(rs.getLong(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setDateN(rs.getString(4));
				c.setNumTel(rs.getLong(5));
				c.setEmail(rs.getString(6));
				c.setAdresse(rs.getString(7));
				c.setNationalite(rs.getString(8));
				c.setSexe(rs.getString(9));
				po.addElement(c);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return po;
	}

	@Override
	public Vector<Instituteur> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Instituteur> findAllV(){
		ArrayList<Instituteur> po = new ArrayList<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID,NOM,PRENOM,to_char(DATE_NAISS,'dd/MM/yyyy'),NUM_TEL, EMAIL, ADRESSE, NATIONALITE,SEXE FROM INST");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Instituteur c = new Instituteur();
				c.setId(rs.getLong(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setDateN(rs.getString(4));
				c.setNumTel(rs.getLong(5));
				c.setEmail(rs.getString(6));
				c.setAdresse(rs.getString(7));
				c.setNationalite(rs.getString(8));
				c.setSexe(rs.getString(9));
				po.add(c);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return po;
	}
	public Vector<Instituteur> findAll(){
		Vector<Instituteur> po = new Vector<Instituteur>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID,NOM,PRENOM,to_char(DATE_NAISS,'dd/MM/yyyy'),NUM_TEL, EMAIL, ADRESSE, NATIONALITE,SEXE FROM INST ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Instituteur c = new Instituteur();
				c.setId(rs.getLong(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setDateN(rs.getString(4));
				c.setNumTel(rs.getLong(5));
				c.setEmail(rs.getString(6));
				c.setAdresse(rs.getString(7));
				c.setNationalite(rs.getString(8));
				c.setSexe(rs.getString(9));
				po.addElement(c);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return po;
	}
	@Override
	public Instituteur find(Long id) {
		PreparedStatement stmt;
		Instituteur in=new Instituteur();
		try {
			stmt = this.connect.prepareStatement("SELECT * FROM INST WHERE ID=?");
			stmt.setLong(1,id);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				in.setId(rs.getLong(1));
				in.setNom(rs.getString(2));
				in.setPrenom(rs.getString(3));
				in.setDateN(rs.getString(4));
				in.setNumTel(rs.getLong(5));
				in.setEmail(rs.getString(6));
				in.setAdresse(rs.getString(7));
				in.setNationalite(rs.getString(8));
				in.setSexe(rs.getString(9));
				
			}
			}catch (SQLException e) {
				e.printStackTrace();
				
			}
		return in;
		
	}
	
	

}
