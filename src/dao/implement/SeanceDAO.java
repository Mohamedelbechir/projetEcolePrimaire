package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import classeMetier.Seance;
import dao.DAO;

public class SeanceDAO extends DAO<Seance>{

	@Override
	public boolean create(Seance obj) {
		try {
			PreparedStatement stmt=this.connect.prepareStatement("INSERT INTO SEANCE VALUES(?,?,?,?,?,?,?)");
			stmt.setLong(1,obj.getIdSeance());
			stmt.setString(2,obj.getJour());
			//stmt.setString(3,obj.getDate());
			stmt.setString(3,obj.getHeureDebut());
			stmt.setString(4,obj.getHeurFin());
			stmt.setLong(5,obj.getNumClasse());
			stmt.setLong(6,obj.getNumInstituteur());
			stmt.setLong(7,obj.getNumMatiere());
			stmt.executeQuery();
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("SEANCE NON CREEE");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean create2(Seance obj) {
		try {
			ResultSet rs =   connect.prepareStatement("SELECT SEANCE_SEQ.nextval FROM DUAL").executeQuery();
			if(rs.next()){
			Long key = rs.getLong(1);
			PreparedStatement stmt=this.connect.prepareStatement("INSERT INTO SEANCE VALUES(?,?,?,?,?,?,?)");
			stmt.setLong(1,key);
			stmt.setString(2,obj.getJour());
			//stmt.setString(3,obj.getDate());
			stmt.setString(3,obj.getHeureDebut());
			stmt.setString(4,obj.getHeurFin());
			stmt.setLong(5,obj.getNumClasse());
			stmt.setLong(6,obj.getNumInstituteur());
			stmt.setLong(7,obj.getNumMatiere());
			stmt.executeQuery();
			stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("SEANCE NON CREEE");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Seance obj) {
		try {
			PreparedStatement stmt = this.connect.prepareStatement("DELETE FROM SEANCE WHERE ID_SEANCE=?");
			stmt.setLong(1,obj.getIdSeance());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Seance obj) {
		try {
		PreparedStatement stmt = this.connect.prepareStatement("UPDATE SEANCE SET JOUR_SEANCE=?,HEUREDEBUT=?,HEUREFIN=?,NUM_CLASSE=?,ID_INSTITUTEUR=?,ID_MATIERE=? WHERE ID_SEANCE=?");
		stmt.setString(1,obj.getJour());
		stmt.setString(2,obj.getHeureDebut());
		stmt.setString(3,obj.getHeurFin());
		stmt.setLong(4,obj.getNumClasse());
		stmt.setLong(5,obj.getNumInstituteur());
		stmt.setLong(6,obj.getNumMatiere());
		stmt.setLong(7,obj.getIdSeance());
		stmt.executeUpdate();
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return true;
	}

	
	@Override
	public Seance find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Seance> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vector<Seance> findALL() {
		Vector<Seance> vs=new Vector<Seance>();
		
		try {
			PreparedStatement stmt=this.connect.prepareStatement("SELECT *  FROM SEANCE");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				Seance s=new Seance();
				s.setIdSeance(rs.getLong(1));
				s.setJour(rs.getString(2));
			//	s.setDate(rs.getString(3));
				s.setHeureDebut(rs.getString(3));
				s.setHeurFin(rs.getString(4));
				s.setNumClasse(rs.getLong(5));
				s.setNumInstituteur(rs.getLong(6));
				s.setNumMatiere(rs.getLong(7));
				vs.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vs;
	}
	public String findNomMatiere(Long idMat) {
		//Vector<Seance> vs=new Vector<Seance>();
		String nom="";
		ResultSet rs=null;
		try {
			PreparedStatement stmt=this.connect.prepareStatement("SELECT NOM_MATIERE FROM MATIERE WHERE ID_MATIERE=? ");
			 stmt.setLong(1,idMat);
			 rs= stmt.executeQuery();
			 rs.next();
			 nom=rs.getString(1);
			  stmt.close();
			  rs.close();
			
	     } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     }
		return nom;
		}
		
	public Long findIdMatiere(String nom) {
		//Vector<Seance> vs=new Vector<Seance>();
		Long id = null;
		ResultSet rs=null;
		try {
			PreparedStatement stmt=this.connect.prepareStatement("SELECT ID_MATIERE FROM MATIERE WHERE NOM_MATIERE=? ");
			 stmt.setString(1,nom);
			 rs= stmt.executeQuery();
			 rs.next();
			 id=rs.getLong(1);
			  stmt.close();
			  rs.close();
			
	     } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     }
		return id;
		}
	
	public String[] findNomInst(Long idinst) {
	
		
		 String tab[]=new String[2];
		String nom="";
		String prenom="";
		ResultSet rs=null;
		try {
			PreparedStatement stmt=this.connect.prepareStatement("SELECT NOM,PRENOM FROM INST WHERE ID=?");
			stmt.setLong(1, idinst);
			 rs= stmt.executeQuery();
			 rs.next();
			/* nom=rs.getString(1);
			 prenom=rs.getString(2);*/
			// tab= {rs.getString(1),rs.getString(2)};
			 tab[0]=rs.getString(1);
			 tab[1]=rs.getString(2);
			 stmt.close();
			  rs.close();
			  
			
	     } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     }
		//return nom;
		return tab;
		}
	public Long findIdInst(String nom,String prenom) {
		Long id = null;
		ResultSet rs=null;
		try {
			PreparedStatement stmt=this.connect.prepareStatement("SELECT ID FROM INST WHERE NOM=? AND PRENOM =?");
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			 rs= stmt.executeQuery();
			 rs.next();
			id=rs.getLong(1);
			 stmt.close();
			  rs.close();
			  
			
	     } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     }
		//return nom;
		return id;
		}
	public Long findIdClasse(String nom ){
		Long id = null;
		ResultSet rs=null;
		try {
			PreparedStatement stmt=this.connect.prepareStatement("SELECT NUM_CLASSE FROM CLASSE WHERE NON_CLASSE=? ");
			stmt.setString(1, nom);
			 rs= stmt.executeQuery();
			 rs.next();
			id=rs.getLong(1);
			 stmt.close();
			  rs.close();
			  
			
	     } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     }
		//return nom;
		return id;
		}
	public String findNomClasse(long id ){
		String nom = "";
		ResultSet rs=null;
		try {
			PreparedStatement stmt=this.connect.prepareStatement("SELECT NON_CLASSE  FROM CLASSE WHERE NUM_CLASSE =? ");
			stmt.setLong(1, id);
			 rs= stmt.executeQuery();
			 rs.next();
			nom=rs.getString(1);
			 stmt.close();
			  rs.close();
			  
			
	     } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	     }
		//return nom;
		return nom;
		}
	
}
