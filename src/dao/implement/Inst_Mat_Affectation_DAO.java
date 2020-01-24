package dao.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;

import classeMetier.Inst_Mat_Affectation;
import classeMetier.Matiere;
import dao.DAO;

public class Inst_Mat_Affectation_DAO extends DAO<Inst_Mat_Affectation> {

	@Override
	public boolean create(Inst_Mat_Affectation obj) {
		
		
		if(verif_Affectation_deja_faite(obj)==false){
			JOptionPane.showMessageDialog(null,"Matiere deja affecte à cet instituteur");
		}
		
		else{
			try {
				PreparedStatement stmt =this.connect.prepareStatement("INSERT INTO INST_MAT VALUES(?,?)");
				stmt.setLong(1,obj.getIdMAt());
				stmt.setLong(2, obj.getIdInst());
				ResultSet rs= stmt.executeQuery();
				JOptionPane.showMessageDialog(null,"Matiere affecte à cet instituteur aves succes");

				
			} catch (Exception e) {
				
			}
		}
		
	
				return false;
		
	}
	
	
	
	
	public boolean verif_Affectation_deja_faite(Inst_Mat_Affectation obj){
		
		
		try {
			PreparedStatement stmt =this.connect.prepareStatement("SELECT * FROM INST_MAT where ID_MATIERE=? and ID=?");
			stmt.setLong(1,obj.getIdMAt());
			stmt.setLong(2, obj.getIdInst());
			ResultSet rs= stmt.executeQuery();
			if(rs.next()){
				return false;
			}
			else return true;
		} catch (Exception e) {
			
		}
				
		
		return true;
		
	}

	@Override
	public boolean delete(Inst_Mat_Affectation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Inst_Mat_Affectation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Inst_Mat_Affectation find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Inst_Mat_Affectation> findCollection(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Vector<Matiere> Matiere_d_instituteur(Inst_Mat_Affectation obj){
		
		Vector<Matiere> v = new Vector<>();
		
		String requet="SELECT * from MATIERE M,INST_MAT IM"
				+ " where ID=? AND M.ID_MATIERE=IM.ID_MATIERE";
		try
		   {
			PreparedStatement stmt = this.connect.prepareStatement(requet);
			stmt.setLong(1,obj.getIdInst());
			ResultSet rs = stmt.executeQuery();
			
			
			
			while(rs.next())
			{
				Matiere m=new Matiere();
				m.setId_matiere(rs.getLong("ID_MATIERE"));
				m.setNom(rs.getString("NOM_MATIERE"));
				m.setId_niveau(rs.getLong("ID_NIVEAU"));
				v.add(m);
			}
		   }
		catch(Exception r){
			
		}
		return v;
	}
	
	
	
	

}
