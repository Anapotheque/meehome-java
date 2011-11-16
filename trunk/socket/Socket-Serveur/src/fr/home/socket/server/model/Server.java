package fr.home.socket.server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Server {

    static Logger logger = Logger.getLogger(Server.class);

    private boolean stopServer = false;

    private ServerSocket serverSocket;

    private List<Client> listClient;

    public Server() {
        listClient = new ArrayList<Client>();
    }

    public void runServeur(int port) {
        try {
            serverSocket = new ServerSocket(port);
            logger.debug("runServeur :: En attente de connexion client");
            while (!stopServer) {
                listClient.add(new Client(serverSocket.accept()));
                logger.debug("runServeur :: Nouveau client ajouté");
            }
            serverSocket.close();
        } catch (IOException e) {
            logger.error("runServeur :: " + e);
        }
    }
}
