package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.Salle_Classe_Affectation;
import dao.DAO;

public class Salle_Classe_AffectationDAO extends DAO<Salle_Classe_Affectation> {

	public Salle_Classe_AffectationDAO() {
		//super(conn);
		// TODO Auto-generated constructor stub
	}
	/* ------------Cette methode permet de verifier si une affectation existe déjà ---------*/
	public boolean verifier_Affectation(long id_Salle,long id_Classe){
		
		try {
			PreparedStatement stmt =connect.prepareStatement("SELECT * FROM SALLE_CLASSE VALUES (?,?)");
			stmt.setLong(1,id_Salle);
			stmt.setLong(2, id_Classe);
			ResultSet rs= stmt.executeQuery();
			if(rs.first()){
				return true;
			}
		} catch (Exception e) {
			
		}
				return false;
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
	/*---- Cette methode permet de verifier si une Si une Classe est déjà affecter à une Salle ---------*/
	public boolean deja_Affecter_Classe(long id_Classe){
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM SALLE_CLASSE WHERE NUM_CLASSE=?");
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
	@Override
	public boolean create(Salle_Classe_Affectation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* ------------Cette methode permet d'affecter une salle à une Classe dans la base de donnée ---------*/
	public boolean affecter_Salle_Classe(long id_Salle,long id_Classe){
		if(verifier_Affectation(id_Salle,id_Classe));
		delete(id_Salle);
			try {
				PreparedStatement stmt =this.connect.prepareStatement("INSERT INTO SALLE_CLASSE VALUES (?,?)");
				stmt.setLong(1,id_Salle);
				stmt.setLong(2, id_Classe);
				stmt.executeQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Deloler l'affectation n'est pas reussi !!");
			}
		
		return true;
	}

	

	@Override
	public boolean update(Salle_Classe_Affectation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/*---------- Cette methode permet de retourner un objet de type Salle_Classe_Affectation utile pour recuper id de sa Classe --------*/
	public Salle_Classe_Affectation find_Ojet_Salle_Classe_Affectation(Long id_Salle) {
		Salle_Classe_Affectation salle_Classe_Affectation=null;
		try {
			PreparedStatement stmt = this.connect.prepareStatement("SELECT * FROM SALLE_CLASSE WHERE ID_SALLE=?");
			stmt.setLong(1,id_Salle);
			ResultSet rs= stmt.executeQuery();
			rs.next();
			salle_Classe_Affectation= new Salle_Classe_Affectation();
			salle_Classe_Affectation.setId_Salle(rs.getLong(1));
			salle_Classe_Affectation.setId_Classe(rs.getLong(2));
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return salle_Classe_Affectation;	
	}
	

	@Override
	public Vector<Salle_Classe_Affectation> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	/* ------------Cette methode permet de Supprimer une Affectation ---------*/
	//@Override
	public boolean delete(Long id_Salle) {
		try {
			PreparedStatement stmt = this.connect.prepareStatement("DELETE FROM SALLE_CLASSE WHERE ID_SALLE=?");
			stmt.setLong(1,id_Salle);
			stmt.executeQuery();				
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean delete_By_Id_Classe(Long id_Classe){
		try {
			PreparedStatement stmt = this.connect.prepareStatement("DELETE FROM SALLE_CLASSE WHERE NUM_CLASSE=?");
			stmt.setLong(1,id_Classe);
			stmt.executeQuery();
		
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean delete(Salle_Classe_Affectation obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Salle_Classe_Affectation find(Long id_Classe) {
		
		return null;
	}

}
