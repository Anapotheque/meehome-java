package fr.home.socket.server.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {

    static Logger logger = Logger.getLogger(UtilProperties.class);

    private static final String CLIENT_PROPERTIES_FILE = "fr/home/socket/server/server.properties";

    private static Properties properties = new Properties();

    public static void init() {
        try {
            properties.load(UtilProperties.class.getClassLoader().getResourceAsStream(CLIENT_PROPERTIES_FILE));
        } catch (final IOException e) {
            String msg = "Erreur lors du chargement des propriétés '" + CLIENT_PROPERTIES_FILE + "'.";
            logger.error("init :: " + msg);
        }
    }

    public static String getData(PropertiesEnum propertiesEnum) {
        init();
        return properties.getProperty(propertiesEnum.getValue());
    }
}
