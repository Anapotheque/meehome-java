package fr.home.socket.server;

import fr.home.socket.server.model.Server;
import fr.home.socket.server.util.PropertiesEnum;
import fr.home.socket.server.util.Util;

public class ConsoleServeur {

    public static final int port = Integer.parseInt(Util.getData(PropertiesEnum.PORT));

    private static Server server = new Server(port);

    public static void main(String[] args) {
        server.runServeur();
        server.close();
    }
}
