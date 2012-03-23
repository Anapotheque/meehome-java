package fr.home.socket.client.model;

import org.apache.log4j.Logger;

import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.UtilProperties;

public class ServeurDistant {

    static Logger logger = Logger.getLogger(ServeurDistant.class);

    public static final String IP = UtilProperties.getData(PropertiesEnum.IP);

    public static final String LOGIN = UtilProperties.getData(PropertiesEnum.LOGIN);

    public static final int PORT = Integer.parseInt(UtilProperties.getData(PropertiesEnum.PORT));

}
