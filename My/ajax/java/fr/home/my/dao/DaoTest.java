package fr.home.my.dao;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import fr.home.my.dao.util.DaoFindException;

public class DaoTest {
	
	XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("ApplicationContext.xml"));
	IUtilisateurDao utilisateurDao = (IUtilisateurDao) bf.getBean("utilisateurDao");
	IRoleDao roleDao = (IRoleDao) bf.getBean("roleDao");
	
	@Test
	public void getUsersByFiltersTest() throws DaoFindException{
		assertFalse(utilisateurDao.getUsersByFilters(null).isEmpty());
	}
	
	@Test
	public void getRoleByFiltersTest() throws DaoFindException{
		assertFalse(roleDao.getRoleByFilters(null).isEmpty());
	}
}
