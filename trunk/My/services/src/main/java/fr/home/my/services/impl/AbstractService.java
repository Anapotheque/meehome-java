package fr.home.my.services.impl;

import fr.home.my.dao.IGenericDao;

public abstract class AbstractService {

	protected IGenericDao dao;

	public IGenericDao getDao() {
		return dao;
	}

	public void setDao(IGenericDao dao) {
		this.dao = dao;
	}
}
