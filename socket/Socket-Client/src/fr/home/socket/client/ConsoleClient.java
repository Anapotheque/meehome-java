package fr.home.socket.client;

import org.apache.log4j.Logger;

import fr.home.socket.client.model.Client;
import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.Util;

public class ConsoleClient {

    static Logger logger = Logger.getLogger(ConsoleClient.class);

    public static final String IP = Util.getData(PropertiesEnum.IP);

    public static final String LOGIN = Util.getData(PropertiesEnum.LOGIN);

    public static final int PORT = Integer.parseInt(Util.getData(PropertiesEnum.PORT));

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        while (!client.isStop()) {
            if (client.connection(IP, PORT, LOGIN))
                client.run();
            Thread.sleep(10000);
        }
        client.close();
    }
}
