package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.rmi.CORBA.UtilDelegate;

import classeMetier.ConseilClasse;
import classeMetier.Eleve;
import dao.DAO;

public class ConseilClasseDAO extends DAO<ConseilClasse> {

	
	public ConseilClasseDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(ConseilClasse obj) {
		try {
			
			java.sql.Date sqldate= new java.sql.Date(System.currentTimeMillis());
			ResultSet rs =   connect.prepareStatement("SELECT SEQUENCE_CONSEIL.nextval FROM DUAL").executeQuery();
			if(rs.next()){
				String key = rs.getString(1);
				PreparedStatement stmt = connect.prepareStatement("INSERT INTO CONSEIL_CLASSE VALUES (?,?,?,?)");
				stmt.setString(1,key);//obj.getId_Conseil()
				stmt.setString(2,obj.getTitre_Conseil());
				stmt.setDate(3, sqldate);	//obj.getDate_Conseil()
				stmt.setString(4, obj.getDescription());
				stmt.executeQuery();
				rs.close();
				stmt.close();
			}		
		} catch (Exception  e) {
			
			e.getMessage();
			System.out.println("False el");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(ConseilClasse obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE  FROM CONSEIL_CLASSE WHERE ID_CONSEIL = ? ");
			stmt.setLong(1, obj.getId_Conseil());
			stmt.executeQuery();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean update(ConseilClasse obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("UPDATE CONSEIL_CLASSE  SET TITRE = ? ,DESCRIPTION=? WHERE ID_CONSEIL = ?");
			
			stmt.setString(1,obj.getTitre_Conseil());
			stmt.setString(2, obj.getDescription());
			stmt.setLong(3,obj.getId_Conseil());
			stmt.executeQuery();
			
			stmt.close();
			
		} catch (Exception  e) {
			
			e.getMessage();
			return false;
		}
		
		return true;
	}

	
	/*----- Cette metode permet de retourner un conseil de Classe  --------*/
	@Override
	public ConseilClasse find(Long id) {
		
		ConseilClasse c = null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CONSEIL_CLASSE ");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				c = new ConseilClasse();
				c.setId_Conseil(rs.getLong(1));	
				c.setTitre_Conseil(rs.getString(2));
				c.setDate_Conseil(rs.getString(3));
				c.setDescription(rs.getString(4));
				
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return c;
	}
	
	/*----------- Cette methode permet de retourner un conseil de classe suivant le titre passer comme parametre---------*/
	public ConseilClasse findby_titre(String titre) {
		
		ConseilClasse ConseilClasse = null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CONSEIL_CLASSE WHERE TITRE LIKE ?");
			stmt.setString(1, titre);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				ConseilClasse = new ConseilClasse();
				ConseilClasse.setId_Conseil(rs.getLong(1));	
				ConseilClasse.setTitre_Conseil(rs.getString(2));
				ConseilClasse.setDate_Conseil(rs.getString(3));
				ConseilClasse.setDescription(rs.getString(4));
				
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return ConseilClasse;
	}

	/*------- Cette methode permet de rtourner une collection utile lors d'une recherche par titre --------*/
	public Vector<ConseilClasse> findby_titre_Collection(String titre) {
		
		Vector<ConseilClasse> po = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID_CONSEIL,TITRE,to_char(DATE_CONSEIL,'DD/MM/YYYY'),DESCRIPTION FROM CONSEIL_CLASSE WHERE TITRE LIKE ?");
			stmt.setString(1, titre);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ConseilClasse c = new ConseilClasse();
				c = new ConseilClasse();
				c.setId_Conseil(rs.getLong(1));	
				c.setTitre_Conseil(rs.getString(2));
				c.setDate_Conseil(rs.getString(3));
				c.setDescription(rs.getString(4));
				po.addElement(c);
		}
		
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	
		return po;
	}
	public Vector<ConseilClasse> findAll() {
		Vector<ConseilClasse> po = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT ID_CONSEIL,TITRE,to_char(DATE_CONSEIL,'DD/MM/YYYY'),DESCRIPTION FROM CONSEIL_CLASSE ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ConseilClasse c = new ConseilClasse();
				c = new ConseilClasse();
				c.setId_Conseil(rs.getLong(1));	
				c.setTitre_Conseil(rs.getString(2));
				c.setDate_Conseil(rs.getString(3));
				c.setDescription(rs.getString(4));
				po.addElement(c);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return po;
	}

	@Override
	public Vector<ConseilClasse> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
