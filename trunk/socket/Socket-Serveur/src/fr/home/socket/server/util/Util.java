package fr.home.socket.server.util;

import java.io.IOException;
import java.util.Properties;

public class Util {

	private final String CLIENT_PROPERTIES_FILE = "fr/home/socket/server/server.properties";
	private Properties properties;
	
	public void checkProperties(){
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(getClass().getClassLoader().getResourceAsStream(CLIENT_PROPERTIES_FILE));
	        } catch (final IOException e) {
	        	System.out.println("Erreur lors du chargement des propriétés '" + CLIENT_PROPERTIES_FILE + "'.");
	        }
		}
	}
	
	public String getData(PropertiesEnum propertiesEnum){
		checkProperties();
		return properties.getProperty(propertiesEnum.getValue());
	}
}
