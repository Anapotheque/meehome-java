package fr.home.my.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.home.my.dao.impl.UtilisateurDAO;
import fr.home.my.dao.model.Role;
import fr.home.my.dao.model.Utilisateur;
import fr.home.my.dao.util.DaoCreateException;
import fr.home.my.dao.util.DaoDeleteException;
import fr.home.my.dao.util.DaoFindException;

public class ServicesTest {
	
	XmlBeanFactory services = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
																 
	IUtilisateurService utilisateurService = (IUtilisateurService) services.getBean("utilisateurService");
	IRoleService roleService = (IRoleService) services.getBean("roleService");
	Utilisateur utilisateur = null;
	
	private static Logger log = Logger.getLogger(UtilisateurDAO.class);
	
	public void verifiePresenceUtilisateur(String nom) throws DaoFindException{
		Utilisateur utilisateur = utilisateurService.recupererUnUtilisateur(nom);
		assertTrue(utilisateur != null && utilisateur.getNomUtilisateur().equals(nom));
	}
	
	public void verifieSuppressionUtilisateur(String nom) throws DaoFindException{
		Utilisateur utilisateur = utilisateurService.recupererUnUtilisateur(nom);
		assertTrue(utilisateur == null);
	}
	
	@Test
	public void ajouterUtilisateurTest() throws DaoFindException, DaoCreateException{
		
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_1","mdp_1"));
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_2","mdp_2"));
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleUtilisateur(),"nom_3","mdp_3"));
		verifiePresenceUtilisateur("nom_1");
		verifiePresenceUtilisateur("nom_2");
		verifiePresenceUtilisateur("nom_3");
		
	}
	
	@Test
	public void connexionTest() throws DaoFindException, DaoCreateException{
		
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_1","mdp_1"));
		assertTrue(utilisateurService.estPresent("nom_1","mdp_1"));
		assertFalse(utilisateurService.estPresent("nom_1","mdp_2"));
		
	}
	
	@Test
	public void recupererTousLesUtilisateursTest() throws DaoFindException, DaoCreateException{
		
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_1","mdp_1"));
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_2","mdp_2"));
		assertTrue(utilisateurService.recupererTousLesUtilisateurs().size() > 2);
	}
	
	@Test
	public void recupererDesUtilisateursTest() throws DaoFindException, DaoCreateException{
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_1","mdp_1"));
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_2","mdp_2"));
		assertTrue(utilisateurService.recupererDesUtilisateurs("nom_").size() > 1);
	}
	
	@Test
	public void supprimerUtilisateur() throws DaoCreateException, DaoFindException, DaoDeleteException{
		
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_1","mdp_1"));
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_2","mdp_2"));
		utilisateurService.ajouterUtilisateur(new Utilisateur(roleService.getRoleAdministrateur(),"nom_3","mdp_3"));
		
		verifiePresenceUtilisateur("nom_1");
		verifiePresenceUtilisateur("nom_2");
		verifiePresenceUtilisateur("nom_3");
		
		utilisateurService.supprimerUtilisateur(utilisateurService.recupererUnUtilisateur("nom_1"));
		utilisateurService.supprimerUtilisateur(utilisateurService.recupererUnUtilisateur("nom_2"));
		utilisateurService.supprimerUtilisateur(utilisateurService.recupererUnUtilisateur("nom_3"));
		
		verifieSuppressionUtilisateur("nom_1");
		verifieSuppressionUtilisateur("nom_2");
		verifieSuppressionUtilisateur("nom_3");
	}
}
