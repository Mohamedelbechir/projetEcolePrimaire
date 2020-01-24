package dao.implement;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import classeMetier.Classe;
import dao.DAO;

public class ClasseDAO extends DAO<Classe> {

	public ClasseDAO() {
		//super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Classe obj) {
		
		
		try {
			ResultSet rs =   connect.prepareStatement("SELECT SEQUENCE_CLASSE.nextval FROM DUAL").executeQuery();
			if(rs.next()){
				long key = rs.getLong(1);
				
				PreparedStatement stmt = connect.prepareStatement("INSERT INTO CLASSE VALUES (?,?,?)");
				stmt.setLong(1,key);
				stmt.setString(2,obj.getNom_Clase());
				stmt.setLong(3, obj.getNiveau_Classe());
				
				stmt.executeQuery();
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
		return true;
	}

	@Override
	public boolean delete(Classe obj) {
		try {
			PreparedStatement stmt =connect.prepareStatement("DELETE FROM CLASSE WHERE NUM_CLASSE = ?  ");
			stmt.setLong(1,obj.getNum_Clase());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(Classe obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("UPDATE CLASSE SET NON_CLASSE=?,ID_NIVEAU=?"
					+ "WHERE NUM_CLASSE = ?  ");
			stmt.setString(1,obj.getNom_Clase());
			stmt.setLong(2,obj.getNiveau_Classe());
			stmt.setLong(3,obj.getNum_Clase());
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Classe find(Long num) {
		Classe c = null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CLASSE WHERE NUM_CLASSE = ?");
			stmt.setLong(1,num);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				c =new Classe();
				c.setNum_Clase(rs.getLong(1));
				c.setNom_Clase(rs.getString(2));
				c.setNiveau_Classe(rs.getLong(3));	
			}
			
			rs.close();
			stmt.close();	
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	
	public Classe findByNom(String nom) {
		Classe c = null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CLASSE WHERE NON_CLASSE LIKE ?");
			stmt.setString(1,nom);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				c=new Classe();
				c.setNum_Clase(rs.getLong(1));
				c.setNom_Clase(rs.getString(2));
				c.setNiveau_Classe(rs.getLong(3));
				
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public Vector<Classe> findAll(){
		Vector<Classe> po = new Vector<Classe>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CLASSE ORDER BY NON_CLASSE ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Classe c = new Classe();
				c.setNum_Clase(rs.getLong(1));
				c.setNom_Clase(rs.getString(2));
				c.setNiveau_Classe(rs.getLong(3));
				po.addElement(c);
			} 
			
			//rs.close();
			//stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return po;
	}
	
	
	@Override
	public Vector<Classe> findCollection(String nom) {
		Vector<Classe> po = new Vector<Classe>();
		Classe c= null;
		try {
			PreparedStatement stmt = this.connect.prepareStatement("SELECT * FROM CLASSE WHERE NON_CLASSE LIKE ?");
			stmt.setString(1,"%"+nom+"%");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				po=new Vector<>();
				c= new Classe();
				c.setNum_Clase(rs.getLong(1));
				c.setNom_Clase(rs.getString(2));
				c.setNiveau_Classe(rs.getLong(3));
				po.addElement(c);
			}
			while(rs.next()){
				c= new Classe();
				c.setNum_Clase(rs.getLong(1));
				c.setNom_Clase(rs.getString(2));
				c.setNiveau_Classe(rs.getLong(3));
				po.addElement(c);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return po;
	}

}
