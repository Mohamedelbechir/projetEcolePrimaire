package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import classeMetier.Absence_Eleve;
import classeMetier.Un_Absence;
import dao.DAO;

public class Absence_EleveDAO extends DAO<Absence_Eleve> {

	@Override
	public boolean create(Absence_Eleve obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO ABSENCE_ELEVE  VALUES (?,?,?)");
			stmt.setLong(1, obj.getNum_Inscription());
			stmt.setString(2,obj.getDate_Absence());
			stmt.setString(3, obj.getMotif());
			stmt.executeQuery();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean delete(Absence_Eleve obj) {
		try {
			//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			//java.util.Date maDate = formatter.parse(obj.getDate_Absence());
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM ABSENCE_ELEVE WHERE (ABSENCE_ELEVE.NUM_INSCRIPTION=? AND ABSENCE_ELEVE.DATE_ABSENCE=?)");
			stmt.setLong(1,obj.getNum_Inscription());
			//stmt.setDate(2,new java.sql.Date(maDate.getTime()) );
			stmt.setString(2, obj.getDate_Absence());
			stmt.executeQuery();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Absence_Eleve obj) {
		
			try {
				
				PreparedStatement stmt = connect.prepareStatement("UPDATE  ABSENCE_ELEVE SET MOTIF=? WHERE NUM_INSCRIPTION=? AND DATE_ABSENCE=?");
				stmt.setString(1,obj.getMotif());
				//stmt.setDate(2,new java.sql.Date(maDate.getTime()) );
				stmt.setLong(2, obj.getNum_Inscription());
				stmt.setString(3, obj.getDate_Absence());
				stmt.executeQuery();
				stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}
	
	/* Cette methode permet de retouner un vecteur d'absence de l'identifiant passer en parametre*/
	public Vector<Absence_Eleve> Absence_Eleve_find_By_ID(Long id) {
		Absence_Eleve absence_Eleve =null;
		Vector<Absence_Eleve> vector=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT NUM_INSCRIPTION,to_char(DATE_ABSENCE,'DD/MM/YYYY'),MOTIF FROM ABSENCE_ELEVE WHERE NUM_INSCRIPTION=? ORDER BY DATE_ABSENCE ");
			stmt.setLong(1,id);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				vector= new Vector<>();
				do {
					absence_Eleve= new Absence_Eleve();
					absence_Eleve.setNum_Inscription(resultSet.getLong(1));
					absence_Eleve.setDate_Absence(resultSet.getString(2));
					absence_Eleve.setMotif(resultSet.getString(3));
					vector.add(absence_Eleve);
				} while (resultSet.next());
			}
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}

	//cette methode permet de Retourner une absence suivant la date et id de eleve
	public Absence_Eleve find(Long id,String date) {
		
			Absence_Eleve absence_Eleve =null;
			try {
				PreparedStatement stmt = connect.prepareStatement("SELECT NUM_INSCRIPTION,to_char(DATE_ABSENCE,'DD/MM/YYYY'),MOTIF FROM ABSENCE_ELEVE WHERE ( NUM_INSCRIPTION=? AND DATE_ABSENCE=?)");
				stmt.setLong(1,id);
				stmt.setString(2, date);
				ResultSet resultSet = stmt.executeQuery();
				if(resultSet.next()){
					absence_Eleve= new Absence_Eleve();
					absence_Eleve.setNum_Inscription(resultSet.getLong(1));
					absence_Eleve.setDate_Absence(resultSet.getString(2));
					absence_Eleve.setMotif(resultSet.getString(3));
					
				}
				stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return absence_Eleve;
	}
	@Override
	public Vector<Absence_Eleve> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Absence_Eleve find(Long id) {
		
		return null;
	}
	
	/* Cette methode permet de retourner une liste d'absence suivant le numero de la Classe et la date passé en parametre*/
	public ArrayList<Absence_Eleve> liste_Absence_Num_Classe_Date(Long num_Classe,String date){	
		
	
		ArrayList<Absence_Eleve> liste_Absence = new ArrayList<>();
		Absence_Eleve absence_Eleve=null;
		
		
		try {
			
			PreparedStatement stmt = connect.prepareStatement("SELECT ABSENCE_ELEVE.NUM_INSCRIPTION,to_char(ABSENCE_ELEVE.DATE_ABSENCE,'DD/MM/YYYY'),ABSENCE_ELEVE.MOTIF"
					+ " FROM ABSENCE_ELEVE,ELEVE WHERE (ELEVE.NUM_CLASSE=? AND ELEVE.NUM_INSCRIPTION=ABSENCE_ELEVE.NUM_INSCRIPTION AND ABSENCE_ELEVE.DATE_ABSENCE=?)");
			
			stmt.setLong(1,num_Classe);
			
			stmt.setString(2,date );
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				
				do{
					
					absence_Eleve= new Absence_Eleve();
					absence_Eleve.setNum_Inscription(resultSet.getLong(1));
					absence_Eleve.setDate_Absence(resultSet.getString(2));
					absence_Eleve.setMotif(resultSet.getString(3));
					liste_Absence.add(absence_Eleve);
				}while (resultSet.next());
					
			}
			stmt.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return liste_Absence;	
	}
}
