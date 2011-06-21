package fr.alliance.docalliance.service.impl;

import fr.alliance.docalliance.dao.IGenericDao;

public abstract class AbstractService {

	protected IGenericDao dao;

	public IGenericDao getDao() {
		return dao;
	}

	public void setDao(IGenericDao dao) {
		this.dao = dao;
	}
	
}
