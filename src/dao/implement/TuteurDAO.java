package dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import classeMetier.Classe;
import classeMetier.Tuteur;
import dao.DAO;

public class TuteurDAO extends DAO<Tuteur> {

	public TuteurDAO() {
		//super(conn);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean create(Tuteur obj) {
		try {
			PreparedStatement stmt =connect.prepareStatement("INSERT INTO TUTEUR VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setLong(1,obj.getCinT());
			stmt.setString(2,obj.getNomT());
			stmt.setString(3, obj.getPrenomT());
			stmt.setString(4, obj.getDate_naissT());
			stmt.setString(5, obj.getSexeT());
			stmt.setString(6, obj.getNationnaliteT());
			stmt.setString(7, obj.getProfT());
			stmt.setString(8, obj.getAddresseT());
			stmt.setString(9, obj.getVilleT());
			stmt.setLong(10, obj.getTelMob());
			stmt.setLong(11, obj.getTelfixe());
			stmt.setString(12, obj.getEmail());
			
			stmt.executeQuery();
			stmt.close();
		
			
		} catch (Exception e ) {
			System.out.println("Failed add tuteur");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Tuteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Tuteur obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("UPDATE TUTEUR SET  NOM=?, PRENOM=?, DATE_NAISSANCE=?,SEXE=?,"
					+ "NATIONALITE=?,PROFESSION=?, ADRESSE=?, VILLE=?,TEL_MOBILE=?,TEL_DOMICILE=?,E_MAIL=?  WHERE CIN = ?  ");

			stmt.setString(1,obj.getNomT());
			stmt.setString(2, obj.getPrenomT());
			stmt.setString(3, obj.getDate_naissT());
			stmt.setString(4, obj.getSexeT());
			stmt.setString(5, obj.getNationnaliteT());
			stmt.setString(6, obj.getProfT());
			stmt.setString(7, obj.getAddresseT());
			stmt.setString(8, obj.getVilleT());
			stmt.setLong(9, obj.getTelMob());
			stmt.setLong(10, obj.getTelfixe());
			stmt.setString(11, obj.getEmail());
			stmt.setLong(12,obj.getCinT());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Tuteur find(Long id) {
		Tuteur c = new Tuteur();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT CIN, NOM, PRENOM,to_char(DATE_NAISSANCE,'dd/MM/yyyy'),SEXE,NATIONALITE,PROFESSION, ADRESSE, VILLE,TEL_MOBILE,TEL_DOMICILE,E_MAIL FROM TUTEUR WHERE CIN=?");
			stmt.setLong(1,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				c.setCinT(rs.getLong(1));
				c.setNomT(rs.getString(2));
				c.setPrenomT(rs.getString(3));
				c.setDate_naissT(rs.getString(4));
				c.setSexeT(rs.getString(5));
				c.setNationnaliteT(rs.getString(6));
				c.setProfT(rs.getString(7)) ;
				c.setAddresseT(rs.getString(8));
				c.setVilleT(rs.getString(9));
				c.setTelMob(rs.getLong(10));
				c.setTelfixe(rs.getLong(11));
				c.setEmail(rs.getString(12));
				
			}
			stmt.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Vector<Tuteur> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
