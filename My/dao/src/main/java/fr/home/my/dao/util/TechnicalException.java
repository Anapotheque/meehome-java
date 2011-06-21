package fr.home.my.dao.util;

@SuppressWarnings("serial")
public class TechnicalException extends Exception {

	public TechnicalException() {
		super();
	}
    
	public TechnicalException(String aMessage) {
		super(aMessage);
	}
}
