package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import classeMetier.Matiere;
import classeMetier.Salle;
import dao.DAO;

public class MatiereDAO extends DAO<Matiere>{
	
	
	public MatiereDAO() {
			
	}
	

	@Override
	public boolean create(Matiere obj) {
		
		try {
			ResultSet rs =   connect.prepareStatement("SELECT SEQUENCE_MARTIERE.nextval FROM DUAL").executeQuery();
	
			if(rs.next()){
				Long key = rs.getLong(1);
				PreparedStatement stmt = connect.prepareStatement("INSERT INTO MATIERE VALUES(?,?,?,?)");
				stmt.setLong(1,key);
				stmt.setString(2,obj.getNom());
				stmt.setLong(3,obj.getId_niveau());
				stmt.setLong(4, obj.getCoef());
				stmt.executeQuery();
			}
		}
		catch (Exception  e)
		    {
				e.printStackTrace();
				return false;
		    }
		
		return true;
	}

	@Override
	public boolean delete(Matiere obj) {
		
		try
		   {
			PreparedStatement stmt = this.connect.prepareStatement("DELETE FROM MATIERE WHERE ID_MATIERE = ?  ");
			stmt.setLong(1,obj.getId_matiere());
			stmt.executeUpdate();
		    } 
		catch (SQLException e) 
		{
			e.printStackTrace();
	   	}
		return true;
	}

	@Override
	public boolean update(Matiere obj) {
		
		try {
			PreparedStatement stmt = connect.prepareStatement("update  MATIERE set NOM_MATIERE=?,ID_NIVEAU=? , COEF=? WHERE ID_MATIERE =? ");
			
		
			stmt.setString(1,obj.getNom());
			stmt.setLong(2,obj.getId_niveau());
			stmt.setLong(3, obj.getCoef());
			stmt.setLong(4,obj.getId_matiere());
			
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Matiere find(String id) {
		
		return null;
	}
	
	public Matiere getByid(Long id )
	{
		String requet="SELECT * from MATIERE where ID_MATIERE=? ";
		try
		   {
			PreparedStatement stmt = connect.prepareStatement(requet);
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			Matiere m=new Matiere();
			
			if(rs.next())
			{
				m.setId_matiere(rs.getLong("ID_MATIERE"));
				m.setNom(rs.getString("NOM_MATIERE"));
				m.setId_niveau(rs.getLong("ID_NIVEAU"));
				m.setCoef(rs.getLong("COEF"));
				return m;
			}
			return null;
		   }
		
		catch(Exception e)
		   {
			System.out.println("erreur de connexion");
			return null;
		   }	
	}
	
	
	
	public ResultSet getAllmatiere()
	{
		String requet="SELECT ID_MATIERE,NOM_MATIERE,COEF,NON_NIVEAU FROM MATIERE,NIVEAU WHERE MATIERE.ID_NIVEAU = NIVEAU.ID_NIVEAU";
		try
		   {
			PreparedStatement stmt = connect.prepareStatement(requet);
			ResultSet rs = stmt.executeQuery();
			return rs;
		   }
		
		catch(Exception e)
		   {
			System.out.println("erreur de connexion");
			return null;
		   }		
	}
	

	public Vector<Matiere> getAllmatiere_Recherche(String nom_Natiere)
	{
		Vector<Matiere> collectionMatiere=new Vector<>();
		Matiere	matiere  =null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM MATIERE WHERE  NOM_MATIERE LIKE ?");
			stmt.setString(1, nom_Natiere);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				
				do {
					matiere= new Matiere();
					matiere.setId_matiere(rs.getLong("ID_MATIERE"));
					matiere.setNom(rs.getString("NOM_MATIERE"));
					matiere.setId_niveau(rs.getLong("ID_NIVEAU"));
					matiere.setCoef(rs.getLong("COEF"));
					collectionMatiere.add(matiere);
				} while (rs.next());
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return collectionMatiere;
		
	}

	@Override
		public Vector<Matiere> findCollection(String niv) {
			
			return null;
	}
	
	public Matiere getMatiere(Long id_niveau , String nom_matiere){
		
		String requet="SELECT nom_matiere,id_niveau from MATIERE where id_niveau=?,nom_matiere=? ";
		try
		   {
			PreparedStatement stmt = connect.prepareStatement(requet);
			stmt.setLong(1,id_niveau);
			stmt.setString(1,nom_matiere);
			
			ResultSet rs = stmt.executeQuery();
            Matiere m=new Matiere();
			
			if(rs.next())
			{
				m.setNom(rs.getString("NOM_MATIERE"));
				m.setId_niveau(rs.getLong("ID_NIVEAU"));
				m.setCoef(rs.getLong("COEF"));
				return m;
			}
			return null;
		   }
		
		catch(Exception e)
		   {
			System.out.println("erreur de connexion");
			return null;
		   }	
	}

	public Vector<Matiere> findAll() {
		Vector<Matiere> v = new Vector<>();
		String requet="SELECT * FROM MATIERE";
		try
		   {
			PreparedStatement stmt = connect.prepareStatement(requet);
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next())
				
			{   Matiere m=new Matiere();
				m.setId_matiere(rs.getLong("ID_MATIERE"));
				m.setNom(rs.getString("NOM_MATIERE"));
				m.setId_niveau(rs.getLong("ID_NIVEAU"));
				m.setCoef(rs.getLong("COEF"));
				v.add(m);
			}	
			rs.close();
			stmt.close();
		   }
	
		catch(Exception e)
		   {
			System.out.println("erreur de connexion");
			return null;
		   }
		return v;
	}
	
	
	// Cette method permet de retourner une matiere suivant son nom id de son niveau
	public Matiere find_By_NMat_IdN(String NomMatiere,Long identifiantNiveau){
		Matiere matiere=null;
		String requet="SELECT * from MATIERE WHERE NOM_MATIERE LIKE ? AND ID_NIVEAU=? ";
		try
		   {
			PreparedStatement stmt = connect.prepareStatement(requet);
			stmt.setString(1,NomMatiere);
			stmt.setLong(2,identifiantNiveau);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{  
				matiere=new Matiere();
				matiere.setId_matiere(rs.getLong("ID_MATIERE"));
				matiere.setNom(rs.getString("NOM_MATIERE"));
				matiere.setId_niveau(rs.getLong("ID_NIVEAU"));
				matiere.setCoef(rs.getLong("COEF"));
			}
			
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		return matiere;
	}
	public Matiere verifier_Presence_Niveau(String nomNiveau){
		Matiere m=null;
		String requet="SELECT * FROM MATIERE,NIVEAU WHERE MATIERE.ID_NIVEAU=NIVEAU.ID_NIVEAU AND NIVEAU.NON_NIVEAU LIKE ?";
		try
		   {
			PreparedStatement stmt = connect.prepareStatement(requet);
			stmt.setString(1, nomNiveau);
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()){  
				m=new Matiere();
				m.setId_matiere(rs.getLong("ID_MATIERE"));
				m.setNom(rs.getString("NOM_MATIERE"));
				m.setId_niveau(rs.getLong("ID_NIVEAU"));
				m.setCoef(rs.getLong("COEF"));
				
			}	
			rs.close();
			stmt.close();
		   }catch(Exception e){
			
			   e.printStackTrace();
		   }
		return m;
	}
	@Override
	public Matiere find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}