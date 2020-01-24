/**
 * 
 */
package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import classeMetier.ConseilClasse;
import classeMetier.Conseil_Instituteur;
import dao.DAO;

/**
 * @author Mohamed El Béchir
 *
 */
public class Conseil_InstituteurDAO extends DAO<Conseil_Instituteur> {

	/**
	 * 
	 */
	public Conseil_InstituteurDAO() {
		
	}

	/* (non-Javadoc)
	 * @see dao.DAO#create(java.lang.Object)
	 */
	public boolean create(Conseil_Instituteur obj,ArrayList<Long> list_Identifiant_Participant) {
		
		try {
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO CONSEIL_INSTITUTEUR VALUES (?,?)");
			stmt.setLong(1,obj.getId_Conseil());
			stmt.setLong(2,obj.getId_inst());
			stmt.executeQuery();
			
			stmt.close();
			
		} catch (Exception  e) {
			
			e.getMessage();
			//System.out.println("False el");
			return false;
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public boolean delete(Conseil_Instituteur obj) {
		
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM CONSEIL_INSTITUTEUR WHERE ID_CONSEIL = ? AND ID_INST = ?");
			stmt.setLong(1,obj.getId_Conseil());
			stmt.setLong(2,obj.getId_inst());
			
			stmt.executeQuery();
			stmt.close();System.out.println("Supprimer avec succès");
			
		} catch (Exception  e) {
			
			e.getMessage();
			System.out.println("False el");
			return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see dao.DAO#update(java.lang.Object)
	 */
	@Override
	public boolean update(Conseil_Instituteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see dao.DAO#find(java.lang.Long)
	 */
	@Override
	public Conseil_Instituteur find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Cette permet de retouner les tuple qui ont comme id_Conseil = id
	 */
	@Override
	public Vector<Conseil_Instituteur> findCollection(String id) {
		Vector<Conseil_Instituteur> po = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CONSEIL_INSTITUTEUR WHERE ID_CONSEIL LIKE ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Conseil_Instituteur c = new Conseil_Instituteur();
				c = new Conseil_Instituteur();
				c.setId_Conseil(rs.getLong(1));	
				c.setId_inst(rs.getLong(2));
				
				po.addElement(c);
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return po;
	}
/* --------- Cette methde permet de retourner la liste Conseil classe instituteur-------*/
	public Vector<Conseil_Instituteur> findAll() {
			Vector<Conseil_Instituteur> po = new Vector<>();
			try {
				PreparedStatement stmt = connect.prepareStatement("SELECT * FROM CONSEIL_INSTITUTEUR ");
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					Conseil_Instituteur c = new Conseil_Instituteur();
					c = new Conseil_Instituteur();
					c.setId_Conseil(rs.getLong(1));	
					c.setId_inst(rs.getLong(2));
					
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
	public boolean create(Conseil_Instituteur obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO CONSEIL_INSTITUTEUR VALUES (?,?)");
			stmt.setLong(1,obj.getId_Conseil());
			stmt.setLong(2,obj.getId_inst());
			stmt.executeQuery();
			
			stmt.close();
			
		} catch (Exception  e) {
			
			e.getMessage();
			return false;
		}
		
		return true;
	}

}
