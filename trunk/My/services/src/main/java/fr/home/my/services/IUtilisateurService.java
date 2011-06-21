package fr.home.my.services;

import java.util.List;

import fr.home.my.dao.model.Utilisateur;
import fr.home.my.dao.util.DaoCreateException;
import fr.home.my.dao.util.DaoDeleteException;
import fr.home.my.dao.util.DaoFindException;

public interface IUtilisateurService {
	
    static final String UTILISATEUR_SERVICE = "utilisateurService";

	// Verifie si un utilisateur existe
    boolean estPresent(String login, String pass) throws DaoFindException;

    // Ajout d'un utilisateur
    void ajouterUtilisateur(Utilisateur utilisateur) throws DaoCreateException;
    
    // Suppression d'un utilisateur
    void supprimerUtilisateur(Utilisateur utilisateur) throws DaoDeleteException;
    
	// Recupere l'ensemble des utilisateurs
    List<Utilisateur> recupererDesUtilisateurs(String nom) throws DaoFindException;

	// Recupere l'ensemble des utilisateurs avec filtre sur le nom
    List<Utilisateur> recupererTousLesUtilisateurs() throws DaoFindException;
    
    // Recupere un utilisateur avec filtre sur le nom
    Utilisateur recupererUnUtilisateur(String nom) throws DaoFindException;
    
    // Recupere un utilisateur avec filtre sur le nom et mot de passe
    Utilisateur recupererUnUtilisateurParMotDePasse(String nom,String pass) throws DaoFindException;

}
