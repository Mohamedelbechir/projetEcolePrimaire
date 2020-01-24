package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.Salle;
import dao.DAO;

public class SalleDAO extends DAO<Salle> {

	public SalleDAO() {
		//super(conn);
		// TODO Auto-generated constructor stub
	}

	
	
	/*-------------------------Cette methode permet de créer un Salle --------------------------------*/
	@Override
	public boolean create(Salle obj) {
		try {
			ResultSet rs =   connect.prepareStatement("SELECT SEQUENCE_CLASSE.nextval FROM DUAL").executeQuery();
			if(rs.next()){
				long key = rs.getLong(1);
				PreparedStatement stmt;
				if(obj.getNum_Clase()!=null){
					stmt = connect.prepareStatement("INSERT INTO SALLE VALUES (?,?,?,?)");
					stmt.setLong(1,key);//obj.getId_SALLE()
					stmt.setString(2, obj.getNom_SALLE());
					stmt.setLong(3, obj.getCapacite());
					
					stmt.setLong(4, obj.getNum_Clase());
				}
				else{
					JOptionPane.showMessageDialog(null, obj.getNum_Clase());
					stmt = connect.prepareStatement("INSERT INTO SALLE (ID_SALLE,NOM_SALLE,CAPACITE) VALUES (?,?,?)");
					stmt.setLong(1,key);//obj.getId_SALLE()
					stmt.setString(2, obj.getNom_SALLE());
					stmt.setLong(3, obj.getCapacite());
				}
					
				
				stmt.executeQuery();
				stmt.close();
			}
		} catch (Exception  e) {
			
			e.printStackTrace();
			
			return false;
		}
		return true;
	}
	
	
	/* ------------Cette methode permet de Supprimer une Salle ---------*/
	@Override
	public boolean delete(Salle obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM SALLE WHERE ID_SALLE=?");
			stmt.setLong(1,obj.getId_SALLE());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	/* ------------Cette methode permet de Supprimer une Affectation ---------*/
	public boolean delete_Affection(Long id_Salle) {
		try {
			
			PreparedStatement stmt = connect.prepareStatement("UPDATE SALLE SET NUM_CLASSE =NULL WHERE ID_SALLE=? ");
			stmt.setLong(1,id_Salle);
			stmt.executeQuery();				
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	/* ------------Cette methode permet de faire la mise à jour d'une Salle ---------*/
	@Override
	public boolean update(Salle obj) {
		try {
			PreparedStatement stmt;
			if(obj.getNum_Clase()!=null){
				stmt=connect.prepareStatement("UPDATE SALLE SET NOM_SALLE=?, CAPACITE= ?, NUM_CLASSE =? WHERE ID_SALLE=? ");
				stmt.setString(1, obj.getNom_SALLE());
				stmt.setLong(2, obj.getCapacite());
				stmt.setLong(3,obj.getNum_Clase());
				stmt.setLong(4, obj.getId_SALLE());
				
			}else{
				stmt=connect.prepareStatement("UPDATE SALLE SET NOM_SALLE=?, CAPACITE= ? WHERE ID_SALLE=? ");
				stmt.setString(1, obj.getNom_SALLE());
				stmt.setLong(2, obj.getCapacite());
				stmt.setLong(3, obj.getId_SALLE());
			}
			
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}

	public boolean update_Affecter(Salle obj) {
		try {
			PreparedStatement stmt =connect.prepareStatement("UPDATE SALLE SET NOM_SALLE=?, CAPACITE= ?, NUM_CLASSE =? WHERE ID_SALLE=? ");
			stmt.setString(1, obj.getNom_SALLE());
			stmt.setLong(2, obj.getCapacite());
			stmt.setLong(3, obj.getId_SALLE());
			stmt.setLong(4, obj.getNum_Clase());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}
	/* ------------ Cette methode permet de Chercher toutes les Salles ---------*/
	public Vector<Salle> findAll(){
		Vector<Salle> vecteur = new Vector<>();
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE ORDER BY NOM_SALLE ");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Salle c = new Salle();
				c.setId_SALLE(rs.getLong(1));
				c.setNom_SALLE(rs.getString(2));
				c.setCapacite(rs.getInt(3));
				vecteur.addElement(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vecteur;
	}

	
	/*------------ On retour un veteur de Salle---------*/

	@Override
	public Salle find(Long id) {
		
		Salle salle=null;
		if(id!=null){
			try {
				PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE WHERE ID_SALLE= ?");
				stmt.setLong(1,id);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					salle = new Salle();
					salle.setId_SALLE(rs.getLong(1));
					salle.setNom_SALLE(rs.getString(2));
					salle.setCapacite(rs.getInt(3));
					salle.setNum_Clase(rs.getLong(4));
					
				}
				rs.close();
				stmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
		}
		
		return salle;
	}
	
	/*---------- Cette methode permet de retourner un objet Salle suivant id_Classe passé en parametre----------*/
	public Salle find_By_Id_Classe(Long id_Classe) {
		
		Salle salle=null;
		if(id_Classe!=null){
			try {
				PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE WHERE NUM_CLASSE= ?");
				stmt.setLong(1,id_Classe);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					salle = new Salle();
					salle.setId_SALLE(rs.getLong(1));
					salle.setNom_SALLE(rs.getString(2));
					salle.setCapacite(rs.getInt(3));
					
				}
				rs.close();
				stmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
		}
		
		return salle;
	}
	/*------------ On retourne une de Salle---------*/
	public Salle find_ByNom(String nom) {
		Salle salle =null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE WHERE NOM_SALLE LIKE ? ");
			stmt.setString(1, nom);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				salle = new Salle();
				salle.setId_SALLE(rs.getLong(1));
				salle.setNom_SALLE(rs.getString(2));
				salle.setCapacite(rs.getInt(3));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return salle;
	}
		
	/* Cette methode permet de retourner une collection de salle Salle utile pour les recherches des Salles  */
	@Override
	public Vector<Salle> findCollection(String id) {
		Vector<Salle> collectionSalle = null;
		Salle salle =null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE WHERE NOM_SALLE LIKE ? ");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				collectionSalle=new Vector<>();
				do {
					salle = new Salle();
					salle.setId_SALLE(rs.getLong(1));
					salle.setNom_SALLE(rs.getString(2));
					salle.setCapacite(rs.getInt(3));
					collectionSalle.add(salle);
				} while (rs.next());
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return collectionSalle;
	}
	
	/*---- Cette methode permet de verifier si une salle est déjà affecter à une Classe ---------*/
	public boolean deja_Affecter_Salle(long id_Salle){
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE WHERE ID_SALLE=? AND NUM_CLASSE IS NOT NULL");
			stmt.setLong(1,id_Salle);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return false;
	}
	
	/*---- Cette methode permet de verifier si une classe a deja une salle ---------*/
	public boolean adeja_Une_Salle(long id_Classe){
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE WHERE NUM_CLASSE=?");
			stmt.setLong(1,id_Classe);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return false;
	}


}
