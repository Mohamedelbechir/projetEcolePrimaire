package dao;

import java.sql.Connection;
import java.util.Vector;

import connxion_Requete.Connexion;

public abstract class DAO <T>{
	
	protected static Connection connect = null;
	public DAO(){
	connect = new Connexion().getConnection();
	}
	/**
	* Méthode de création
	* @param obj
	* @return boolean
	*/
	public abstract boolean create(T obj);
	/**
	* Méthode pour effacer
	* @param obj
	* @return boolean
	*/
	public abstract boolean delete(T obj);
	/**
	* Méthode de mise à jour
	* @param obj
	* @return boolean
	*/
	public abstract boolean update(T obj);
	
	public abstract T find(Long id);
	
	public abstract Vector<T> findCollection(String id);

}
