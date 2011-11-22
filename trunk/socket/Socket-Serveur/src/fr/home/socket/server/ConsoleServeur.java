package fr.home.socket.server;

import fr.home.socket.server.model.Server;
import fr.home.socket.server.util.PropertiesEnum;
import fr.home.socket.server.util.UtilProperties;

public class ConsoleServeur {

    public static final int port = Integer.parseInt(UtilProperties.getData(PropertiesEnum.PORT));

    private static Server server = new Server();

    public static void main(String[] args) {
        server.runServeur(port);
    }
}
