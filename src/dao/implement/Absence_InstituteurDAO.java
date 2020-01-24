package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import classeMetier.Absence_Eleve;
import classeMetier.Absence_Instituteur;
import dao.DAO;

public class Absence_InstituteurDAO extends DAO<Absence_Instituteur> {

	@Override
	public boolean create(Absence_Instituteur obj) {
		try {
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO ABSENCE_INSTITU  VALUES (?,?,?)");
			stmt.setLong(1, obj.getId());
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
	public boolean delete(Absence_Instituteur obj) {
		try {
			
			PreparedStatement stmt = connect.prepareStatement("DELETE FROM ABSENCE_INSTITU WHERE (ABSENCE_INSTITU.ID=? AND ABSENCE_INSTITU.DATE_ABSENCE_INST=?)");
			stmt.setLong(1,obj.getId());
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
	public boolean update(Absence_Instituteur obj) {
		try {
			
			PreparedStatement stmt = connect.prepareStatement("UPDATE  ABSENCE_INSTITU SET MOTIF_INST=? WHERE ID=? AND DATE_ABSENCE_INST=?");
			stmt.setString(1,obj.getMotif());
			//stmt.setDate(2,new java.sql.Date(maDate.getTime()) );
			stmt.setLong(2, obj.getId());
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
	public Vector<Absence_Instituteur> Absence_Instituteur_find_By_ID(Long id) {
		Absence_Instituteur absence_Institu=null;
		Vector<Absence_Instituteur> vector=null;
		try {
			PreparedStatement stmt = connect.prepareStatement("SELECT * FROM ABSENCE_INSTITU WHERE ID=?");
			stmt.setLong(1,id);
			
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet.next()){
				vector= new Vector<>();
				do {
					absence_Institu= new Absence_Instituteur();
					absence_Institu.setId(resultSet.getLong(1));
					absence_Institu.setDate_Absence(resultSet.getString(2));
					absence_Institu.setMotif(resultSet.getString(3));
				} while (resultSet.next());
			}
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vector;
	}
	
	//cette methode permet de Retourner une absence suivant la date et id d'Instituteur
		public Absence_Instituteur find(Long id,String date) {
			
			Absence_Instituteur absence_Instituteur =null;
				try {
					PreparedStatement stmt = connect.prepareStatement("SELECT ID,to_char(DATE_ABSENCE_INST,'DD/MM/YYYY'),MOTIF_INST FROM ABSENCE_INSTITU WHERE ( ID=? AND DATE_ABSENCE_INST=?)");
					stmt.setLong(1,id);
					stmt.setString(2, date);
					ResultSet resultSet = stmt.executeQuery();
					if(resultSet.next()){
						absence_Instituteur= new Absence_Instituteur();
						absence_Instituteur.setId(resultSet.getLong(1));
						absence_Instituteur.setDate_Absence(resultSet.getString(2));
						absence_Instituteur.setMotif(resultSet.getString(3));
						
					}
					stmt.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return absence_Instituteur;
		}
		
	/* Cette methode permet de retourner une liste d'absence suivant le numero de la Classe et la date passé en parametre*/
	public ArrayList<Absence_Instituteur> liste_Absence_Istituteur_Date(String date){	
			
		
		ArrayList<Absence_Instituteur> liste_Absence = new ArrayList<>();
		Absence_Instituteur absence_Instituteur=null;
			
		try {
				
			PreparedStatement stmt = connect.prepareStatement("SELECT ABSENCE_INSTITU.ID,to_char(DATE_ABSENCE_INST,'DD/MM/YYYY'),ABSENCE_INSTITU.MOTIF_INST"
						+ " FROM ABSENCE_INSTITU,INST WHERE (INST.ID=ABSENCE_INSTITU.ID AND ABSENCE_INSTITU.DATE_ABSENCE_INST=?)");
				
				stmt.setString(1,date);
				ResultSet resultSet = stmt.executeQuery();
				
				if(resultSet.next()){
					
					do{
						
						absence_Instituteur= new Absence_Instituteur();
						absence_Instituteur.setId(resultSet.getLong(1));
						absence_Instituteur.setDate_Absence(resultSet.getString(2));
						absence_Instituteur.setMotif(resultSet.getString(3));
						liste_Absence.add(absence_Instituteur);
					}while (resultSet.next());
						
				}
				stmt.close();

			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return liste_Absence;	
		}
	@Override
	public Absence_Instituteur find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Absence_Instituteur> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
