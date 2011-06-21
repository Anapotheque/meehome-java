package fr.home.my.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.home.my.dao.IUtilisateurDao;
import fr.home.my.dao.model.Utilisateur;
import fr.home.my.dao.util.DaoCreateException;
import fr.home.my.dao.util.DaoDeleteException;
import fr.home.my.dao.util.DaoFindException;
import fr.home.my.services.IUtilisateurService;

public class UtilisateurService extends AbstractService implements IUtilisateurService {
    
	// Verifie si un utilisateur existe
	@Override
	public boolean estPresent(String nom, String pass)throws DaoFindException {
	    return (recupererUnUtilisateurParMotDePasse(nom,pass)!= null)? true : false;
	}
    
	// Suppression d'un utilisateur
    public void supprimerUtilisateur(Utilisateur utilisateur) throws DaoDeleteException {
        dao.supprimer(utilisateur);
    }
    
    // Ajout d'un utilisateur
    public void ajouterUtilisateur(Utilisateur utilisateur)  throws DaoCreateException {
        dao.ajouter(utilisateur);
    }

    // Recupere l'ensemble des utilisateurs
	@Override
	public List<Utilisateur> recupererTousLesUtilisateurs() throws DaoFindException {
		return ((IUtilisateurDao) dao).getUsersByFilters(null);
	}

	// Recupere l'ensemble des utilisateurs avec filtre sur le nom
	@Override
	public List<Utilisateur> recupererDesUtilisateurs(String nom) throws DaoFindException {
		HashMap<String, Object> filter = new HashMap<String, Object>();
	    filter.put("nomUtilisateur", nom);
	    return ((IUtilisateurDao) dao).getUsersByFilters(filter);
	}
	
	// Recupere un utilisateur avec filtre sur le nom
	@Override
	public Utilisateur recupererUnUtilisateur(String nom) throws DaoFindException {
		HashMap<String, Object> filter = new HashMap<String, Object>();
	    filter.put("nomUtilisateur", nom);
	    List<Utilisateur> list = ((IUtilisateurDao) dao).getUsersByFilters(filter);
	    return list.iterator().hasNext()?list.iterator().next():null;
	}
	
	// Recupere un utilisateur avec filtre sur le nom et mot de passe
	@Override
	public Utilisateur recupererUnUtilisateurParMotDePasse(String nom,String pass) throws DaoFindException {
		HashMap<String, Object> filter = new HashMap<String, Object>();
		filter.put("nomUtilisateur", nom);
	    filter.put("passwordUtilisateur", pass);
	    List<Utilisateur> list = ((IUtilisateurDao) dao).getUsersByFilters(filter);
	    return list.iterator().hasNext()?list.iterator().next():null;
	}
}
