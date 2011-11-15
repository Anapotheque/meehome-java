package fr.home.socket.client;

import org.apache.log4j.Logger;

import fr.home.socket.client.model.Client;
import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.Util;

public class ConsoleClient {

    static Logger logger = Logger.getLogger(ConsoleClient.class);

    public static final String ip = Util.getData(PropertiesEnum.IP);

    public static final String login = Util.getData(PropertiesEnum.LOGIN);

    public static final int port = Integer.parseInt(Util.getData(PropertiesEnum.PORT));

    public static Client client = new Client();

    public static void main(String[] args) throws InterruptedException {
        while (!client.isStop()) {
            if (client.connection(ip, port, login))
                client.run();
            Thread.sleep(10000);
        }
        client.close();
    }
}
