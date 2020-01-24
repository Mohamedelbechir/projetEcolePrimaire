package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.Classe;
import classeMetier.Eleve;
import classeMetier.Salle;
import dao.DAO;

public class EleveDAO  extends DAO<Eleve> {

	public EleveDAO() {
		//super(conn);
		
	}

	@Override
	public boolean create(Eleve obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO ELEVE VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setLong(1,obj.getNum_ins());
			stmt.setString(2, obj.getNom());
			stmt.setString(3, obj.getPrenom());
			stmt.setString(4, obj.getDate_naiss());
			stmt.setString(5, obj.getNationnalite());
			stmt.setString(6, obj.getAddresse());
			stmt.setString(7, obj.getVille());
			stmt.setString(8, obj.getSexe());
			stmt.setLong(9,obj.getNum_classe());
			stmt.setLong(10, obj.getCin());
			stmt.setString(11, obj.getPhotos_Eleve());
			stmt.executeQuery();
			
			stmt.close();
			
		} catch (Exception  e) {
			
			e.printStackTrace();
			System.out.println("False el");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(Eleve obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM ELEVE WHERE NUM_INSCRIPTION=?");
			stmt.setLong(1,obj.getNum_ins());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(Eleve obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("UPDATE ELEVE SET NOM=? , PRENOM=?, DATE_NAISSANCE=?, NATIONALITE=?,"
					+ "ADDRESSE=?, VILLE=?, SEXE=?,NUM_CLASSE=?,PHOTO_ELEVE=? WHERE NUM_INSCRIPTION  = ? ");
			stmt.setString(1, obj.getNom());
			stmt.setString(2, obj.getPrenom());
			stmt.setString(3, obj.getDate_naiss());
			stmt.setString(4, obj.getNationnalite());
			stmt.setString(5, obj.getAddresse());
			stmt.setString(6, obj.getVille());
			stmt.setString(7, obj.getSexe());
			stmt.setLong(8, obj.getNum_classe());
			stmt.setString(9, obj.getPhotos_Eleve());
			
			stmt.setLong(10, obj.getNum_ins());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}
	
	public Vector<Eleve> findByNom(String nom) {
		Vector<Eleve> po = null;
		Eleve c=null;
		try {
			PreparedStatement stmt =connect.prepareStatement("SELECT NUM_INSCRIPTION,NOM,PRENOM,to_char(DATE_NAISSANCE,'dd/MM/yyyy'),NATIONALITE,ADDRESSE,VILLE,SEXE,NUM_CLASSE,CIN,PHOTO_ELEVE  FROM ELEVE WHERE NOM LIKE ?");
			stmt.setString(1,nom);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				c = new Eleve();
				po=new Vector<>();
				do{
					c = new Eleve();
					c.setNum_ins(rs.getLong(1));
					c.setNom(rs.getString(2));
					c.setPrenom(rs.getString(3));
					c.setDate_naiss(rs.getString(4));
					c.setNationnalite(rs.getString(5));
					c.setAddresse(rs.getString(6));
					c.setVille(rs.getString(7));
					c.setSexe(rs.getString(8));
					c.setNum_classe(rs.getLong(9));
					c.setCin(rs.getLong(10));
					c.setPhotos_Eleve((rs.getString(11)));
					po.addElement(c);
				
				}while(rs.next());	
			}
			
			stmt.close();
			rs.close();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return po;
	}

	public Vector<Eleve> findAll(){
		Vector<Eleve> po = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT NUM_INSCRIPTION,NOM,PRENOM,to_char(DATE_NAISSANCE,'dd/MM/yyyy'),NATIONALITE,ADDRESSE,VILLE,SEXE,NUM_CLASSE,CIN,PHOTO_ELEVE  FROM ELEVE ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Eleve c = new Eleve();
				c.setNum_ins(rs.getLong(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				c.setDate_naiss(rs.getString(4));
				c.setNationnalite(rs.getString(5));
				c.setAddresse(rs.getString(6));
				c.setVille(rs.getString(7));
				c.setSexe(rs.getString(8));
				c.setNum_classe(rs.getLong(9));
				c.setCin(rs.getLong(10));
				c.setPhotos_Eleve((rs.getString(11)));
				po.addElement(c);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return po;
	}

	public Vector<Eleve> findByNumClasse(Long num) {
		Vector<Eleve> p = new Vector<>();
		
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM ELEVE WHERE NUM_CLASSE=?");
			stmt.setLong(1,num);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Eleve c = new Eleve();
				c.setNum_ins(rs.getLong(1));
				c.setNom(rs.getString(2));
				c.setPrenom(rs.getString(3));
				/*c.setDate_naiss(rs.getString(4));
				c.setNationnalite(rs.getString(5));
				c.setAddresse(rs.getString(6));
				c.setVille(rs.getString(7));
				c.setSexe(rs.getString(8));
				c.setNum_classe(rs.getString(9));
				c.setCin(rs.getString(10));*/
				p.addElement(c);
				
			}
			stmt.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Vector<Eleve> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Eleve find(Long id) {
		Eleve eleve=null;
		if(id!=null){
			try {
				PreparedStatement stmt = connect.prepareStatement("SELECT NUM_INSCRIPTION,NOM,PRENOM,to_char(DATE_NAISSANCE,'dd/MM/yyyy'),NATIONALITE,ADDRESSE,VILLE,SEXE,NUM_CLASSE,CIN,PHOTO_ELEVE FROM ELEVE WHERE NUM_INSCRIPTION= ?");
				stmt.setLong(1,id);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					eleve=new Eleve();
					eleve.setNum_ins(rs.getLong(1));
					eleve.setNom(rs.getString(2));
					eleve.setPrenom(rs.getString(3));
					eleve.setDate_naiss(rs.getString(4));JOptionPane.showMessageDialog(null, rs.getString(4));
					eleve.setNationnalite(rs.getString(5));
					eleve.setAddresse(rs.getString(6));
					eleve.setVille(rs.getString(7));
					eleve.setSexe(rs.getString(8));
					eleve.setNum_classe(rs.getLong(9));
					eleve.setCin(rs.getLong(10));
					eleve.setPhotos_Eleve((rs.getString(11)));
				}
				rs.close();
				stmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
		}
		
		return eleve;
	}

}
