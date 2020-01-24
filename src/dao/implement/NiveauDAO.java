package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.Niveau;
import dao.DAO;

public class NiveauDAO extends DAO<Niveau> {

	@Override
	public boolean create(Niveau obj) {
		try {
			ResultSet rs =   connect.prepareStatement("SELECT SEQUENCE_NIVEAU.nextval FROM DUAL").executeQuery();
			if(rs.next()){
				long key = rs.getLong(1);
				PreparedStatement stmt = connect.prepareStatement("INSERT INTO NIVEAU VALUES (?,?)");
				stmt.setLong(1,key);
				stmt.setString(2, obj.getNom_Niveau());			
				stmt.executeQuery();
				stmt.close();	
			}		
		} catch (Exception  e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Niveau obj) {
		try {
			
			PreparedStatement stmt = connect.prepareStatement("DELETE  FROM NIVEAU WHERE NON_NIVEAU LIKE ? ");
			stmt.setString(1, obj.getNom_Niveau());
			stmt.executeQuery();
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Niveau obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Niveau find(Long id) {
		Niveau niveau=null;
		try {
			
			PreparedStatement stmt =connect.prepareStatement("SELECT  * FROM NIVEAU WHERE ID_NIVEAU =? ");
			stmt.setLong(1, id);
			ResultSet resultat= stmt.executeQuery();
			if(resultat.next()){
				niveau=new Niveau();
				niveau.setId_Niveau(resultat.getLong(1));
				niveau.setNom_Niveau(resultat.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return niveau;
	}
	

	/*--------- Cette methode permet de retourner un niveu suivant le nom donner par parametre---*/
	
	public Niveau findBy_Nom(String nom_niveau) {
		Niveau niveau=null;
		try {
			
			PreparedStatement stmt =connect.prepareStatement("SELECT * FROM NIVEAU WHERE NON_NIVEAU LIKE ? ");
			stmt.setString(1, nom_niveau);
			ResultSet resultat= stmt.executeQuery();
			if(resultat.next()){
				niveau=new Niveau();
				niveau.setId_Niveau(resultat.getLong(1));
				niveau.setNom_Niveau(resultat.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return niveau;
	}
	
	public Vector<Niveau> findAll() {
		Vector<Niveau> vector=new Vector<Niveau>();
		Niveau niveau=null;
		try {
			PreparedStatement stmt =connect.prepareStatement("SELECT * FROM NIVEAU ORDER BY NON_NIVEAU ");
			ResultSet  resultat = stmt.executeQuery();
			while (resultat.next()) {
				niveau = new Niveau();
				niveau.setId_Niveau(resultat.getLong(1));
				niveau.setNom_Niveau(resultat.getString(2));
				vector.add(niveau);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vector;
	}

	@Override
	public Vector<Niveau> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
